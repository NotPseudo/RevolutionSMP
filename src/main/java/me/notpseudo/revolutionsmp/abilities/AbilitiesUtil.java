package me.notpseudo.revolutionsmp.abilities;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.itemstats.StatType;
import me.notpseudo.revolutionsmp.listeners.HealthListeners;
import me.notpseudo.revolutionsmp.listeners.MobListeners;
import me.notpseudo.revolutionsmp.listeners.StatsListeners;
import me.notpseudo.revolutionsmp.mobstats.MobCategory;
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
    private static ArrayList<UUID> noMana = new ArrayList<>();
    private static ArrayList<UUID> noCooldown = new ArrayList<>();

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

    public static ArrayList<UUID> getNoMana() {
        return noMana;
    }

    public static ArrayList<UUID> getNoCooldown() {
        return noCooldown;
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
        Collection<LivingEntity> enemies = player.getLocation().getNearbyLivingEntities(range).stream()
                .filter(c -> c.getPersistentDataContainer().get(mobKey, new MobInfoDataType()) != null
                        && c.getPersistentDataContainer().get(mobKey, new MobInfoDataType()).getMobBehavior() != MobCategory.TAMED
                ).toList();
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1F, 1F);
        Particles.explode(player.getLocation());
        for (LivingEntity entity : enemies) {
            HealthListeners.playerMagicDamageEntity(player, entity, AbilityType.IMPLOSION);
        }
    }

    public static void witherShield(Player player) {
        PlayerStats playerStats = StatsListeners.getPlayerStats(player);
        double currentAbsorption = playerStats.getAbsorption();
        playerStats.setAbsorption(currentAbsorption + playerStats.getStatValue(StatType.CRIT_DAMAGE) * 1.5);
        player.playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_VILLAGER_CURE, 1F, 1F);
        player.getPersistentDataContainer().set(playerKey, new PlayerStatsDataType(), playerStats);
        StatsListeners.updateStats(player);
        BukkitRunnable shieldEnd = new BukkitRunnable() {
            @Override
            public void run() {
                PlayerStats playerStats = StatsListeners.getPlayerStats(player);
                double remainingWitherShield = playerStats.getAbsorption() - currentAbsorption;
                if (remainingWitherShield > 0) {
                    playerStats.setAbsorption(playerStats.getAbsorption() - remainingWitherShield);
                    playerStats.setStatValue(StatType.HEALTH, Math.min(playerStats.getStatValue(StatType.HEALTH) + 0.5 * remainingWitherShield, playerStats.getMaxHealth()));
                    player.getPersistentDataContainer().set(playerKey, new PlayerStatsDataType(), playerStats);
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
                            && c.getPersistentDataContainer().get(mobKey, new MobInfoDataType()).getMobBehavior() != MobCategory.TAMED
                    ).toList();
            player.getWorld().playSound(location, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1F, 1F);
            Particles.explode(location);
            for (LivingEntity entity : enemies) {
                HealthListeners.playerMagicDamageEntity(player, entity, AbilityType.SHADOW_WARP);
            }
            shadowWarpActivate.remove(playerID);
            return false;
        } else {
            shadowWarpActivate.put(playerID, location);
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
                    player.getWorld().playSound(finalLocation, Sound.ENTITY_ENDERMAN_TELEPORT, 1F, (float) (Math.random() * 2));
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
                    player.getWorld().playSound(finalLocation, Sound.ENTITY_ENDERMAN_TELEPORT, 1F, 0.5F + 0.025F * count);
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
                        && c.getPersistentDataContainer().get(mobKey, new MobInfoDataType()).getMobBehavior() != MobCategory.TAMED
                ).toList()) {
            entity.setVelocity(new Location(location.getWorld(), x, entity.getLocation().getY(), z).toVector().subtract(entity.getLocation().toVector()).multiply(Math.random() * 1.5));
        }
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