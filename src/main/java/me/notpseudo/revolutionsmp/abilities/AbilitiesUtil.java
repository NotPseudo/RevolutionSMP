package me.notpseudo.revolutionsmp.abilities;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.listeners.HealthListeners;
import me.notpseudo.revolutionsmp.listeners.MobListeners;
import me.notpseudo.revolutionsmp.listeners.StatsListeners;
import me.notpseudo.revolutionsmp.mobstats.MobBehavior;
import me.notpseudo.revolutionsmp.mobstats.MobInfo;
import me.notpseudo.revolutionsmp.mobstats.MobInfoDataType;
import me.notpseudo.revolutionsmp.particles.Particles;
import me.notpseudo.revolutionsmp.playerstats.PlayerStats;
import me.notpseudo.revolutionsmp.playerstats.PlayerStatsDataType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

// Methods that will run to simulate abilities
public class AbilitiesUtil {

    // A Set of non-solid block materials that players can walk through. Used in teleportation abilities
    private static Set<Material> passSet = new HashSet<>();
    private static final NamespacedKey playerKey = StatsListeners.getPlayerStatsKey();
    private static final NamespacedKey mobKey = MobListeners.getMobKey();
    private static HashMap<UUID, Location> shadowWarpActivate = new HashMap<>();

    public static void createPassSet() {
        for (Material material : Material.values()) {
            if (material.isBlock() && !material.isCollidable()) {
                passSet.add(material);
            }
        }
    }

    public static Set<Material> getPassSet() {
        return passSet;
    }

    // Method that runs when Instant Transmission ability is used
    public static void teleportWithSound(Player player, int distance) {
        float yaw = player.getLocation().getYaw();
        float pitch = player.getLocation().getPitch();
        Location loc = player.getLocation();
        boolean cont = true;
        List<Block> blocks = player.getLineOfSight(passSet, distance);
        for (Block b : blocks) {
            if (b.isPassable() && cont) {
                loc = b.getLocation();
            } else {
                cont = false;
                break;
            }
        }
        if (!cont) {
            player.sendMessage("§cThere are blocks in the way!");
        }
        loc.setPitch(pitch);
        loc.setYaw(yaw);
        player.teleport(loc);
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1F, 1F);
    }

    public static void implosion(Player player, double range) {
        double damage = calculateAbilityDamage(AbilityType.IMPLOSION, player);
        Collection<LivingEntity> enemies = player.getLocation().getNearbyLivingEntities(range).stream()
                .filter(c -> c.getPersistentDataContainer().get(mobKey, new MobInfoDataType()) != null
                        && c.getPersistentDataContainer().get(mobKey, new MobInfoDataType()).getMobBehavior() != MobBehavior.TAMED
                ).toList();
        player.playSound(player.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1F, 1F);
        Particles.explode(player.getLocation());
        for (LivingEntity entity : enemies) {
            MobInfo targetStats = entity.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
            double vanillaDamage = damage;
            if (targetStats != null && targetStats.getMaxHealth() > 2048) {
                vanillaDamage = damage / targetStats.getMaxHealth() * 2048;
            }
            entity.damage(vanillaDamage);
            HealthListeners.showDamage(entity, damage, false, ChatColor.GRAY);
        }
    }

    public static void witherShield(Player player) {
        final PlayerStats[] playerStats = {player.getPersistentDataContainer().get(playerKey, new PlayerStatsDataType())};
        if (playerStats[0] == null) {
            playerStats[0] = new PlayerStats();
        }
        double currentAbsorption = playerStats[0].getAbsorption();
        playerStats[0].setAbsorption(currentAbsorption + playerStats[0].getCritDamage() * 1.5);
        player.playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_VILLAGER_CURE, 1F, 1F);
        playerStats[0].setDamageTakenMultiplier(playerStats[0].getDamageTakenMultiplier() - 0.1);
        player.getPersistentDataContainer().set(playerKey, new PlayerStatsDataType(), playerStats[0]);
        StatsListeners.updateStats(player);
        BukkitRunnable shieldEnd = new BukkitRunnable() {
            @Override
            public void run() {
                playerStats[0] = player.getPersistentDataContainer().get(playerKey, new PlayerStatsDataType());
                double remainingWitherShield = playerStats[0].getAbsorption() - currentAbsorption;
                if (remainingWitherShield > 0) {
                    playerStats[0].setAbsorption(playerStats[0].getAbsorption() - remainingWitherShield);
                    playerStats[0].setDamageTakenMultiplier(playerStats[0].getDamageTakenMultiplier() + 0.1);
                    playerStats[0].setCurrentHealth(Math.min(playerStats[0].getCurrentHealth() + 0.5 * remainingWitherShield, playerStats[0].getMaxHealth()));
                    player.getPersistentDataContainer().set(playerKey, new PlayerStatsDataType(), playerStats[0]);
                    StatsListeners.updateStats(player);
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 2F);
                    Particles.hearts(player.getLocation());
                }
            }
        };
        shieldEnd.runTaskLater(RevolutionSMP.getPlugin(), 100);
    }

    public static boolean shadowWarp(Player player, Location location) {
        UUID playerID = player.getUniqueId();
        if (shadowWarpActivate.containsKey(playerID)) {
            location = shadowWarpActivate.get(playerID);
            Collection<LivingEntity> enemies = location.getNearbyLivingEntities(3).stream()
                    .filter(c -> c.getPersistentDataContainer().get(mobKey, new MobInfoDataType()) != null
                            && c.getPersistentDataContainer().get(mobKey, new MobInfoDataType()).getMobBehavior() != MobBehavior.TAMED
                    ).toList();
            double damage = calculateAbilityDamage(AbilityType.SHADOW_WARP, player);
            player.playSound(location, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1F, 1F);
            Particles.explode(location);
            for (LivingEntity entity : enemies) {
                MobInfo targetStats = entity.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
                double vanillaDamage = damage;
                if (targetStats != null && targetStats.getMaxHealth() > 2048) {
                    vanillaDamage = damage / targetStats.getMaxHealth() * 2048;
                }
                entity.damage(vanillaDamage);
                HealthListeners.showDamage(entity, damage, false, ChatColor.GRAY);
            }
            shadowWarpActivate.remove(playerID);
            return false;
        } else {
            shadowWarpActivate.put(playerID, location);
            Collection<LivingEntity> enemies = location.getNearbyLivingEntities(3).stream()
                    .filter(c -> c.getPersistentDataContainer().get(mobKey, new MobInfoDataType()) != null
                            && c.getPersistentDataContainer().get(mobKey, new MobInfoDataType()).getMobBehavior() != MobBehavior.TAMED
                    ).toList();
            Location finalLocation = location;
            Particles.dualSpiralUp(Particle.PORTAL, location, 3, 3, 5, 10);
            BukkitRunnable distortion = new BukkitRunnable() {
                int count = 0;

                @Override
                public void run() {
                    if (!shadowWarpActivate.containsKey(playerID)) {
                        this.cancel();
                        return;
                    }
                    suckEnemies(finalLocation, 3);
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1F, (float) (Math.random() * 2));
                    count += 2;
                    if (count >= 100) {
                        this.cancel();
                    }
                }
            };
            distortion.runTaskTimer(RevolutionSMP.getPlugin(), 0, 2);
            BukkitRunnable remove = new BukkitRunnable() {
                @Override
                public void run() {
                    shadowWarpActivate.remove(playerID);
                }
            };
            remove.runTaskLaterAsynchronously(RevolutionSMP.getPlugin(), 100);
            return true;
        }
    }

    public static void gravityStorm(Player player) {
        List<Block> blocks = player.getLineOfSight(passSet, 26);
        Location location = player.getLocation();
        for (Block b : blocks) {
            if (!b.isPassable()) {
                location = b.getLocation();
                break;
            }
        }
        if(getNumPassableBlocksAbove(location, 10) < 5) {
            return;
        }
        PlayerStats playerStats = player.getPersistentDataContainer().get(playerKey, new PlayerStatsDataType());
        if(playerStats == null) {
            playerStats = new PlayerStats();
        }
        playerStats.setManaRegenRate(playerStats.getManaRegenRate() * 0.1);
        Material[] materials = new Material[]{Material.PURPLE_CONCRETE, Material.PURPLE_STAINED_GLASS, Material.OBSIDIAN};
        Location finalLocation = location;
        Particles.gravityStormParticles(location, 8, materials, 16, 3);
        BukkitRunnable storm = new BukkitRunnable() {
            int count = 0;
            @Override
            public void run() {
                count++;
                suckEnemies(finalLocation, 8);
                for(int i = 0; i < 7; i++) {
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1F, 0.5F + 0.025F * count);
                }
                if(count >= 60) {
                    this.cancel();
                }
            }
        };
        storm.runTaskTimer(RevolutionSMP.getPlugin(), 0, 1);
        BukkitRunnable redoMana = new BukkitRunnable() {
            @Override
            public void run() {
                PlayerStats playerStats = player.getPersistentDataContainer().get(playerKey, new PlayerStatsDataType());
                playerStats.setManaRegenRate(playerStats.getManaRegenRate() * 10);
            }
        };
        redoMana.runTaskLater(RevolutionSMP.getPlugin(), 60);
    }

    public static void suckEnemies(Location location, double range) {
        double x  = location.getX(), z = location.getZ();
        for (LivingEntity entity : location.getNearbyLivingEntities(range).stream()
                .filter(c -> c.getPersistentDataContainer().get(mobKey, new MobInfoDataType()) != null
                        && c.getPersistentDataContainer().get(mobKey, new MobInfoDataType()).getMobBehavior() != MobBehavior.TAMED
                ).toList()) {
            entity.setVelocity(new Location(location.getWorld(), x, entity.getLocation().getY(), z).toVector().subtract(entity.getLocation().toVector()).multiply(Math.random() * 1.5));
        }
    }

    public static double calculateAbilityDamage(AbilityType type, Player player) {
        PlayerStats playerStats = player.getPersistentDataContainer().get(playerKey, new PlayerStatsDataType());
        if (playerStats == null) {
            playerStats = new PlayerStats();
        }
        double intelligenceScale = 1 + (playerStats.getIntelligence() / 100) * type.getAbilityScaling();
        double critScale = 1 + (playerStats.getCritDamage() / 100);
        double abilityDamageScale = 1 + (playerStats.getAbilityDamage() / 100);
        return type.getBaseAbilityDamage() * intelligenceScale * critScale * abilityDamageScale;
    }

    public static int getNumPassableBlocksAbove(Location location, int limit) {
        int count = 0;
        for (int i = 0; i < limit; i++) {
            if(passSet.contains(location.clone().add(0, i, 0).getBlock().getType())) {
                count++;
            }
        }
        return count;
    }

    public static void sendCooldownMessage(Player player) {
        player.sendMessage(Component.text("This ability is on cooldown!", NamedTextColor.RED));
    }

    public static boolean hasEnoughMana(Player player, AbilityObject ability) {
        PlayerStats playerStats = player.getPersistentDataContainer().get(playerKey, new PlayerStatsDataType());
        if (playerStats == null) {
            playerStats = new PlayerStats();
        }
        return playerStats.getMana() - ability.getManaCost() >= 0;
    }

    public static boolean outOfCooldown(Player player, AbilityObject ability) {
        if (ability.getAbilityType().getCooldownList() == null) {
            return true;
        }
        return !ability.getAbilityType().getCooldownList().contains(player.getUniqueId());
    }

}