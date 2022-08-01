package me.notpseudo.revolutionsmp.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.reflect.FieldAccessException;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.enchantments.ActionEnchantment;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentObject;
import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.mobstats.BaseEntityStats;
import me.notpseudo.revolutionsmp.mobstats.MobInfo;
import me.notpseudo.revolutionsmp.mobstats.MobInfoDataType;
import me.notpseudo.revolutionsmp.playerstats.PlayerStats;
import me.notpseudo.revolutionsmp.playerstats.PlayerStatsDataType;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * This class holds listeners that handle how entities take damage and attack
 *
 * @author NotPseudo
 */
public class HealthListeners implements Listener {

    /**
     * Holds an instance of the plugin
     */
    private static RevolutionSMP plugin = RevolutionSMP.getPlugin();
    /**
     * The NamespacedKey from the MobListeners class used to access MobInfo stored in Persistent Data
     */
    private final static NamespacedKey mobKey = MobListeners.getMobKey();
    /**
     * The NamespacedKey from the StatsListeners class used to access PlayerStats stored in Persistent Data
     */
    private final static NamespacedKey playerKey = StatsListeners.getPlayerStatsKey();
    /**
     * Number to represent the next available Entity ID used for creating custom armor stands with packets
     */
    private static int indicatorCount = 999999;

    /**
     * List of ChatColors used to decorate critical damage Strings
     */
    private final static ChatColor[] criticalColors = {ChatColor.WHITE, ChatColor.GOLD, ChatColor.YELLOW, ChatColor.RED};

    /**
     * Instantiates a new HealthListeners object to allow the listeners to work<p>Starts a repeating task to show health bars of mobs each second</p>
     *
     * @param plugin Instance of the plugin
     */
    public HealthListeners(RevolutionSMP plugin) {
        HealthListeners.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, HealthListeners.plugin);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(HealthListeners.plugin, () -> {
            // Every 15 ticks, for every player, get LivingEntities in specified radius and show their health bars
            for (Player player : Bukkit.getOnlinePlayers()) {
                for (Entity entity : player.getLocation().getNearbyLivingEntities(15, 10, 15)) {
                    if (entity instanceof Creature) {
                        updateHealthBar((Creature) entity, player);
                    }
                }
            }
        }, 100, 15);
    }

    /**
     * Generates a random number from -0.5 to 0.5
     *
     * @return The random number
     */
    private static double getRandomOffset() {
        return ((int) (Math.random() * 2) - 0.5) / 2.0;
    }

    /**
     * Gets the next available Entity ID to create a custom armor stand with packets
     *
     * @return The available Entity ID
     */
    private static int getNextIndicatorCount() {
        indicatorCount++;
        return indicatorCount - 1;
    }

    /**
     * Edits the specified LivingEntity's health bar with packets for all players
     *
     * @param entity The LivingEntity to update the health bar for
     */
    public static void updateHealthBar(Creature entity) {
        MobInfo mobInfo = entity.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
        if (mobInfo == null) {
            mobInfo = MobListeners.createMobInfo(entity);
            if (mobInfo == null) return;
        }
        String nameString = "" + mobInfo.getMobBehavior().getBehaviorColor() + mobInfo.getName();
        String healthString;
        // Gets max Health of the LivingEntity
        double currentHealth = mobInfo.getCurrentHealth(), maxHealth = mobInfo.getMaxHealth();
        if (currentHealth < maxHealth / 2) {
            if (currentHealth < 0) {
                currentHealth = 0;
            }
            healthString = "" + ChatColor.YELLOW + (int) Math.round(currentHealth);
        } else {
            if (currentHealth > maxHealth) {
                currentHealth = (int) maxHealth;
            }
            healthString = "" + ChatColor.GREEN + (int) Math.round(currentHealth);
        }
        // Health bar shows name and current Health out of max Health
        String healthBarString = ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv" + mobInfo.getLevel() + ChatColor.DARK_GRAY + "] " + nameString + " " + healthString + ChatColor.GRAY + "/" + ChatColor.GREEN + Math.round(maxHealth) + ChatColor.RED + "❤";
        // Working with packets using ProtocolLib
        WrappedDataWatcher dataWatcher = WrappedDataWatcher.getEntityWatcher(entity).deepClone();
        WrappedDataWatcher.Serializer chatSerializer = WrappedDataWatcher.Registry.getChatComponentSerializer(true);
        WrappedDataWatcher.WrappedDataWatcherObject watcherObject = new WrappedDataWatcher.WrappedDataWatcherObject(2, chatSerializer);
        Optional<Object> optional = Optional.of(WrappedChatComponent.fromChatMessage(healthBarString)[0].getHandle());
        // Sets entity data's custom name as the healthBarString with a chat serializer that reads the healthBarString
        dataWatcher.setObject(watcherObject, optional);
        // Allows the entity's custom name to be visible
        dataWatcher.setObject(3, true);
        // Creates new Entity Metadata or info packet to be sent
        PacketContainer packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.ENTITY_METADATA);
        // The data with the custom name is added to the packet
        packet.getWatchableCollectionModifier().write(0, dataWatcher.getWatchableObjects());
        // The entity this packet refers to is the entity's health bar we want to update or display
        packet.getIntegers().write(0, entity.getEntityId());
        try {
            // Tries to send this info packet or health bar to all players
            ProtocolLibrary.getProtocolManager().broadcastServerPacket(packet);
        } catch (FieldAccessException e) {
            // Prints stack trace in console if an error occurs
            plugin.getLogger().severe("Unable to update Health Bar packet! Stack trace:" + e);
        }
    }

    /**
     * Edits the specified LivingEntity's health bar with packets for a specific player
     *
     * @param entity The LivingEntity to update the health bar for
     * @param player The Player to show the health bar to
     */
    public static void updateHealthBar(Creature entity, Player player) {
        MobInfo mobInfo = entity.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
        if (mobInfo == null) {
            mobInfo = MobListeners.createMobInfo(entity);
            if (mobInfo == null) return;
        }
        String nameString = "" + mobInfo.getMobBehavior().getBehaviorColor() + mobInfo.getName();
        String healthString;
        double currentHealth = mobInfo.getCurrentHealth(), maxHealth = mobInfo.getMaxHealth();
        if (currentHealth < maxHealth / 2) {
            if (currentHealth < 0) {
                currentHealth = 0;
            }
            healthString = "" + ChatColor.YELLOW + (int) Math.round(currentHealth);
        } else {
            if (currentHealth > maxHealth) {
                currentHealth = (int) maxHealth;
            }
            healthString = "" + ChatColor.GREEN + (int) Math.round(currentHealth);
        }
        String healthBarString = ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv" + mobInfo.getLevel() + ChatColor.DARK_GRAY + "] " + nameString + " " + healthString + ChatColor.GRAY + "/" + ChatColor.GREEN + Math.round(maxHealth) + ChatColor.RED + "❤";
        WrappedDataWatcher dataWatcher = WrappedDataWatcher.getEntityWatcher(entity).deepClone();
        WrappedDataWatcher.Serializer chatSerializer = WrappedDataWatcher.Registry.getChatComponentSerializer(true);
        WrappedDataWatcher.WrappedDataWatcherObject watcherObject = new WrappedDataWatcher.WrappedDataWatcherObject(2, chatSerializer);
        Optional<Object> optional = Optional.of(WrappedChatComponent.fromChatMessage(healthBarString)[0].getHandle());
        dataWatcher.setObject(watcherObject, optional);
        dataWatcher.setObject(3, true);
        PacketContainer packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.ENTITY_METADATA);
        packet.getWatchableCollectionModifier().write(0, dataWatcher.getWatchableObjects());
        packet.getIntegers().write(0, entity.getEntityId());
        try {
            // Tries to send this info packet or health bar to specified Player
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
        } catch (InvocationTargetException e) {
            // Prints stack trace in console if the packet could not be sent to the Player
            plugin.getLogger().severe("Unable to update Health Bar packet for player " + player.getName() + "! Stack trace:" + e);
        }
    }

    /**
     * Creates and shows a Damage Indicator with custom armor stand packets
     *
     * @param entity   The LivingEntity to spawn the Damage Indicator by
     * @param damage   The amount of damage dealt and to be shown
     * @param critical Represents if the damage was a critical hit or not
     */
    public static void showDamage(LivingEntity entity, double damage, boolean critical, ChatColor color) {
        if (color == null) {
            color = ChatColor.GRAY;
        }
        String damageString = "" + color + Math.round(damage);
        if (critical) {
            // If it was a critical hit, add stars before and after
            damageString = "✧" + Math.round(damage) + "✧";
            // Adds colors to critical damage message
            StringBuilder critString = new StringBuilder();
            for (int i = 0; i < damageString.length(); i++) {
                critString.append(criticalColors[i % 4]).append(damageString.charAt(i));
            }
            damageString = critString.toString();
        }
        // Generates entity ID and unique ID for packet
        int entityID = getNextIndicatorCount();
        UUID uuid = UUID.randomUUID();
        // Creates a new Spawn Entity packet to be sent. This is a fake packet that will trick clients that an invisible Armor Stand spawned
        PacketContainer spawnPacket = new PacketContainer(PacketType.Play.Server.SPAWN_ENTITY);
        // Packet will refer to unique entity
        spawnPacket.getIntegers().write(0, entityID);
        spawnPacket.getUUIDs().write(0, uuid);
        // Make the entity that spawns an Armor Stand
        spawnPacket.getEntityTypeModifier().write(0, EntityType.ARMOR_STAND);
        // Sets the X, Y, and Z location coordinates for the Damage Indicator
        spawnPacket.getDoubles().write(0, entity.getLocation().getX() + getRandomOffset());
        spawnPacket.getDoubles().write(1, entity.getLocation().getY() + entity.getHeight() + getRandomOffset());
        spawnPacket.getDoubles().write(2, entity.getLocation().getZ() + getRandomOffset());
        // Creates a new Entity Metadata packet to be sent. This fake packet will trick clients that the invisible armor stand's name is the damageString
        PacketContainer editPacket = new PacketContainer(PacketType.Play.Server.ENTITY_METADATA);
        WrappedDataWatcher dataWatcher = new WrappedDataWatcher();
        WrappedDataWatcher.Serializer chatSerializer = WrappedDataWatcher.Registry.getChatComponentSerializer(true);
        WrappedDataWatcher.WrappedDataWatcherObject watcherObject = new WrappedDataWatcher.WrappedDataWatcherObject(2, chatSerializer);
        Optional<Object> optional = Optional.of(WrappedChatComponent.fromChatMessage(damageString)[0].getHandle());
        // Sets entity data's custom name as the damageString with a chat serializer that reads the damageString
        dataWatcher.setObject(watcherObject, optional);
        dataWatcher.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(0, WrappedDataWatcher.Registry.get(Byte.class)), (byte) 0x20); // Invisible
        dataWatcher.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(5, WrappedDataWatcher.Registry.get(Boolean.class)), true); // noGravity
        dataWatcher.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(15, WrappedDataWatcher.Registry.get(Byte.class)), (byte) (0x01 | 0x08 | 0x10)); // isSmall, noBasePlate, set Marker
        dataWatcher.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(3, WrappedDataWatcher.Registry.get(Boolean.class)), true); // customNameVisible
        // Puts the data into the packet
        editPacket.getWatchableCollectionModifier().write(0, dataWatcher.getWatchableObjects());
        // Makes the packet edit the invisible armor stand that was spawned
        editPacket.getIntegers().write(0, entityID);
        List<Integer> entityIDList = List.of(entityID);
        // Makes an Entity Destroy packet to remove the invisible armor stand
        PacketContainer removePacket = new PacketContainer(PacketType.Play.Server.ENTITY_DESTROY);
        // Makes the packet remove the invisible armor stand that was created and edited
        removePacket.getIntLists().write(0, entityIDList);
        try {
            // Try to send the spawn and edit packet
            ProtocolLibrary.getProtocolManager().broadcastServerPacket(spawnPacket);
            ProtocolLibrary.getProtocolManager().broadcastServerPacket(editPacket);
            Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {
                try {
                    // After 20 game ticks, try to send the remove packet
                    ProtocolLibrary.getProtocolManager().broadcastServerPacket(removePacket);
                } catch (FieldAccessException e) {
                    e.printStackTrace();
                }
            }, 20);
        } catch (FieldAccessException e) {
            plugin.getLogger().severe("Unable to update Damage Indicator Edit packet for player! Stack trace:");
            e.printStackTrace();
        }
    }

    /**
     * Handles damage taken when a LivingEntity takes damage not caused by an Entity
     *
     * @param event The Event fired when an Entity takes damage
     */
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Creature entity) {
            if (event instanceof EntityDamageByEntityEvent) {
                return;
            }
            if (entity instanceof Player player) {
                double damage = calculateDamageAfterAbsorption(event.getDamage(), player);
                if(damage > 0) {
                    PlayerStats playerStats = player.getPersistentDataContainer().get(playerKey, new PlayerStatsDataType());
                    playerStats.setCurrentHealth(playerStats.getCurrentHealth() - damage);
                    if(playerStats.getMaxHealth() > 2048) {
                        damage = damage / playerStats.getMaxHealth() * 2048;
                    }
                    event.setDamage(damage);
                    player.getPersistentDataContainer().set(playerKey, new PlayerStatsDataType(), playerStats);
                    return;
                }
            }
            MobInfo mobInfo = entity.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
            if (mobInfo == null) {
                mobInfo = MobListeners.createMobInfo(entity);
                if (mobInfo == null) return;
            }
            double health = mobInfo.getCurrentHealth() - event.getDamage();
            if (mobInfo.getMaxHealth() > 2048) {
                event.setDamage((event.getDamage() / mobInfo.getMaxHealth()) * 2048);
            }
            mobInfo.setCurrentHealth(health);
            entity.getPersistentDataContainer().set(mobKey, new MobInfoDataType(), mobInfo);
            updateHealthBar(entity);
        }
    }

    /**
     * Updates health bar when a LivingEntity regenerates health
     *
     * @param event The Event fired when an Entity regains health
     */
    @EventHandler
    public void onRegen(EntityRegainHealthEvent event) {
        if (event.getEntity() instanceof Creature entity) {
            if (entity instanceof Player player) {
                PlayerStats playerStats = player.getPersistentDataContainer().get(playerKey, new PlayerStatsDataType());
                if(playerStats == null) {
                    playerStats = new PlayerStats();
                }
                double regenAmount = event.getAmount();
                if(playerStats.getMaxHealth() > 2048) {
                    regenAmount = regenAmount / 2048 * playerStats.getMaxHealth();
                    playerStats.setCurrentHealth(playerStats.getCurrentHealth() + regenAmount);
                    player.getPersistentDataContainer().set(playerKey, new PlayerStatsDataType(), playerStats);
                }
                return;
            }
            MobInfo mobInfo = entity.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
            if (mobInfo == null) {
                mobInfo = MobListeners.createMobInfo(entity);
                if (mobInfo == null) return;
            }
            double health = mobInfo.getCurrentHealth() + event.getAmount();
            mobInfo.setCurrentHealth(health);
            entity.getPersistentDataContainer().set(mobKey, new MobInfoDataType(), mobInfo);
            updateHealthBar(entity);
        }
    }

    /**
     * Handles damage when a LivingEntity is attacked by another Entity
     *
     * @param event The Event fired when an Entity takes damage from an Entity
     */
    @EventHandler
    public void onDamageEntity(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof LivingEntity target) || !(event.getDamager() instanceof LivingEntity || event.getDamager() instanceof Arrow))
            return;
        LivingEntity damager = null;
        if (event.getDamager() instanceof LivingEntity) {
            damager = (LivingEntity) event.getDamager();
        } else if (event.getDamager() instanceof Arrow) {
            if (((Arrow) event.getDamager()).getShooter() instanceof LivingEntity) {
                damager = (LivingEntity) ((Arrow) event.getDamager()).getShooter();
            }
        }
        // Sets base stat values
        double weaponDamage =  event.getDamage(), strength = 0, critDamage = 50, critChance = 30, defense = 0, actualDamagePercent = 1, ferocity = 0, damageTakenMultiplier = 1, attackCharge = 1;
        StatObject damageAdditivePercent = new StatObject(StatType.DAMAGE, 0);
        EnchantmentsHolder enchantHolder = null;
        BaseEntityStats damagerStats = damager.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
        BaseEntityStats targetStats = target.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
        if (damager instanceof Player player) {
            weaponDamage += 5;
            attackCharge = player.getAttackCooldown();
            if (player.getInventory().getItemInMainHand().getType() != Material.AIR && player.getInventory().getItemInMainHand().getItemMeta() != null) {
                ItemMeta mainHandMeta = player.getInventory().getItemInMainHand().getItemMeta();
                if (mainHandMeta != null) {
                    ItemInfo mainHandInfo = mainHandMeta.getPersistentDataContainer().get(ItemEditor.getItemKey(), new ItemInfoDataType());
                    if (mainHandInfo != null) {
                        weaponDamage = mainHandInfo.getWeaponStats().getStatValue(StatType.DAMAGE);
                        enchantHolder = mainHandInfo.getEnchantmentsHolder();
                        if(enchantHolder != null) {
                            for(EnchantmentObject enchant : enchantHolder.getEnchants()) {
                                damageAdditivePercent.add(enchant.getDamageStatAdditivePercent(damager, target, StatType.DAMAGE));
                            }
                        }
                    }
                }
            }
            damagerStats = damager.getPersistentDataContainer().get(playerKey, new PlayerStatsDataType());
            if (damagerStats != null) {
                ferocity = ((PlayerStats) damagerStats).getFerocity();
            }
        }
        if (target instanceof Player) {
            targetStats = target.getPersistentDataContainer().get(playerKey, new PlayerStatsDataType());
            damageTakenMultiplier = ((PlayerStats) targetStats).getDamageTakenMultiplier();
        }
        if (damagerStats != null) {
            strength = damagerStats.getStrength();
            critDamage = damagerStats.getCritDamage();
            critChance = damagerStats.getCritChance();
        }
        if (targetStats != null) {
            defense = targetStats.getDefense();
        }
        double randomCrit = Math.random() * 100;
        boolean critical = randomCrit <= critChance;
        if (!critical) {
            critDamage = 0;
        }
        double finalDamage = ((weaponDamage) * (1 + (strength / 100))) * (1 + (critDamage / 100)) * (1 + (damageAdditivePercent.getValue() / 100)) * attackCharge;
        actualDamagePercent = 1 - (defense / (defense + 100));
        finalDamage *= actualDamagePercent * damageTakenMultiplier;
        double vanillaDamage = finalDamage;
        double remainDamage = finalDamage;
        if(enchantHolder != null) {
            for(EnchantmentObject enchant : enchantHolder.getEnchants()) {
                if(enchant instanceof ActionEnchantment) {
                    ((ActionEnchantment) enchant).action(damager, target, vanillaDamage, critical, finalDamage);
                }
            }
        }
        if(target instanceof Player player) {
            remainDamage = calculateDamageAfterAbsorption(finalDamage, player);
            vanillaDamage = remainDamage;
        }
        if (targetStats != null && targetStats.getMaxHealth() > 2048) {
            vanillaDamage = finalDamage / targetStats.getMaxHealth() * 2048;
        }
        event.setDamage(vanillaDamage);
        showDamage(target, finalDamage, critical, null);
        if (targetStats != null) {
            targetStats.setCurrentHealth(targetStats.getCurrentHealth() - remainDamage);
            if (target instanceof Player) {
                target.getPersistentDataContainer().set(playerKey, new PlayerStatsDataType(), (PlayerStats) targetStats);
            } else {
                target.getPersistentDataContainer().set(mobKey, new MobInfoDataType(), (MobInfo) targetStats);
                updateHealthBar((Creature) target);
            }
        }
        if (damager instanceof Player && ferocity > 0) {
            ferocityAttack((Player) damager, target, finalDamage, ferocity, critical);
        }
    }

    /**
     * Repeatedly damages the target based on the attacker's ferocity
     *
     * @param damager  The attacking Player
     * @param target   The LivingEntity to be attacked
     * @param damage   The amount of damage originally dealt
     * @param ferocity The ferocity stat of the attacker
     * @param critical Represents if the damage was a critical hit or not
     */
    private void ferocityAttack(Player damager, LivingEntity target, double damage, double ferocity, boolean critical) {
        int hits = HarvestingListeners.getAddedTimes(ferocity);
        final int[] count = {0};
        BukkitRunnable feroHit = new BukkitRunnable() {
            @Override
            public void run() {
                count[0]++;
                if (count[0] > hits) {
                    this.cancel();
                } else {
                    if (!target.isDead()) {
                        target.setMaximumNoDamageTicks(0);
                        target.setNoDamageTicks(0);
                        target.damage(damage);
                        showDamage(target, damage, critical, null);
                        damager.playSound(damager, Sound.ITEM_FLINTANDSTEEL_USE, 2f, 0.605f);
                    } else {
                        count[0] = hits + 1;
                    }
                }
            }
        };
        feroHit.runTaskTimer(plugin, 2, 1);
    }

    private static double calculateDamageAfterAbsorption(double damage, Player player) {
        PlayerStats playerStats = player.getPersistentDataContainer().get(playerKey, new PlayerStatsDataType());
        if(playerStats == null) {
            playerStats = new PlayerStats();
        }
        double damageLeft = damage - playerStats.getAbsorption();
        double remainAbsorption = playerStats.getAbsorption() - damage;
        if(remainAbsorption <= 0) {
            playerStats.setAbsorption(0);
            player.setAbsorptionAmount(0);
            player.getPersistentDataContainer().set(playerKey, new PlayerStatsDataType(), playerStats);
            return Math.max(0, damageLeft);
        } else {
            playerStats.setAbsorption(remainAbsorption);
            player.setAbsorptionAmount(Math.min(40, remainAbsorption));
            player.getPersistentDataContainer().set(playerKey, new PlayerStatsDataType(), playerStats);
            return 0;
        }
    }

}