package me.notpseudo.revolutionsmp.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.reflect.FieldAccessException;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.abilities.AbilityType;
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
                    if (entity instanceof LivingEntity living) {
                        updateHealthBar(living, player);
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
    public static int getNextIndicatorCount() {
        indicatorCount++;
        return indicatorCount - 1;
    }

    /**
     * Edits the specified LivingEntity's health bar with packets for all players
     *
     * @param entity The LivingEntity to update the health bar for
     */
    public static void updateHealthBar(LivingEntity entity) {
        if (!(entity instanceof Creature || entity instanceof ComplexLivingEntity)) {
            return;
        }
        MobInfo mobInfo = entity.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
        if (mobInfo == null) {
            mobInfo = MobListeners.createMobInfo(entity);
            if (mobInfo == null) {
                return;
            }
        }
        String nameString = "" + mobInfo.getMobBehavior().getBehaviorColor() + mobInfo.getName();
        String healthString;
        // Gets max Health of the LivingEntity
        double currentHealth = mobInfo.getArmorStatValue(StatType.HEALTH), maxHealth = mobInfo.getMaxHealth();
        if (currentHealth < maxHealth / 2) {
            if (currentHealth < 0) {
                currentHealth = 0;
            }
            healthString = "" + ChatColor.YELLOW + ItemEditor.numberFormatted((int) Math.round(currentHealth));
        } else {
            if (currentHealth > maxHealth) {
                currentHealth = (int) maxHealth;
            }
            healthString = "" + ChatColor.GREEN + ItemEditor.numberFormatted((int) Math.round(currentHealth));
        }
        // Health bar shows name and current Health out of max Health
        String healthBarString = ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv" + mobInfo.getLevel() + ChatColor.DARK_GRAY + "] " + nameString + " " + healthString + ChatColor.GRAY + "/" + ChatColor.GREEN + ItemEditor.numberFormatted(Math.round(maxHealth)) + ChatColor.RED + "❤";
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
    public static void updateHealthBar(LivingEntity entity, Player player) {
        if (!(entity instanceof Creature || entity instanceof ComplexLivingEntity)) {
            return;
        }
        MobInfo mobInfo = entity.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
        if (mobInfo == null) {
            mobInfo = MobListeners.createMobInfo(entity);
            if (mobInfo == null) {
                return;
            }
        }
        String nameString = "" + mobInfo.getMobBehavior().getBehaviorColor() + mobInfo.getName();
        String healthString;
        double currentHealth = mobInfo.getArmorStatValue(StatType.HEALTH), maxHealth = mobInfo.getMaxHealth();
        if (currentHealth < maxHealth / 2) {
            if (currentHealth < 0) {
                currentHealth = 0;
            }
            healthString = "" + ChatColor.YELLOW + ItemEditor.numberFormatted((int) Math.round(currentHealth));
        } else {
            if (currentHealth > maxHealth) {
                currentHealth = (int) maxHealth;
            }
            healthString = "" + ChatColor.GREEN + ItemEditor.numberFormatted((int) Math.round(currentHealth));
        }
        String healthBarString = ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv" + mobInfo.getLevel() + ChatColor.DARK_GRAY + "] " + nameString + " " + healthString + ChatColor.GRAY + "/" + ChatColor.GREEN + ItemEditor.numberFormatted(Math.round(maxHealth)) + ChatColor.RED + "❤";
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
        if (event instanceof EntityDamageByEntityEvent) {
            return;
        }
        if (event.getEntity() instanceof Player player) {
            double trueDefense = 0;
            ChatColor color = null;
            if (event.getCause() == EntityDamageEvent.DamageCause.FIRE ||
                    event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK ||
                    event.getCause() == EntityDamageEvent.DamageCause.LAVA ||
                    event.getCause() == EntityDamageEvent.DamageCause.HOT_FLOOR ||
                    event.getCause() == EntityDamageEvent.DamageCause.SUICIDE) {
                PlayerStats playerStats = StatsListeners.getPlayerStats(player);
                trueDefense = playerStats.getArmorStatValue(StatType.TRUE_DEFENSE);
                color = ChatColor.GOLD;
            }
            if (event.getCause() == EntityDamageEvent.DamageCause.MAGIC) {
                color = ChatColor.DARK_AQUA;
            }
            double damageAfterTrueDef = 1 - (trueDefense / (100 + trueDefense));
            double damage = calculateDamageAfterAbsorption(event.getDamage() * damageAfterTrueDef, player);
            event.setDamage(getVanillaDamage(player, damage));
            showDamage(player, damage, false, color);
            return;
        }
        if (event.getEntity() instanceof LivingEntity entity) {
            double trueDefense = 0, magicResistance = 0;
            ChatColor color = null;
            MobInfo mobInfo = MobListeners.getMobInfo(entity);
            if (event.getCause() == EntityDamageEvent.DamageCause.FIRE ||
                    event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK ||
                    event.getCause() == EntityDamageEvent.DamageCause.LAVA ||
                    event.getCause() == EntityDamageEvent.DamageCause.HOT_FLOOR ||
                    event.getCause() == EntityDamageEvent.DamageCause.SUICIDE) {
                if (mobInfo != null) {
                    trueDefense = mobInfo.getArmorStatValue(StatType.TRUE_DEFENSE);
                }
                color = ChatColor.GOLD;
            }
            if (event.getCause() == EntityDamageEvent.DamageCause.MAGIC) {
                if (mobInfo != null) {
                    magicResistance = mobInfo.getMagicResistance();
                }
                color = ChatColor.DARK_AQUA;
            }
            double damageAfterTrueDef = 1 - (trueDefense / (100 + trueDefense)), damageAfterMagicRes = 1 - (magicResistance / 100);
            double damage = event.getDamage() * damageAfterTrueDef * damageAfterMagicRes;
            event.setDamage(getVanillaDamage(entity, damage));
            showDamage(entity, damage, false, color);
        }
    }

    /**
     * Updates health bar when a LivingEntity regenerates health
     *
     * @param event The Event fired when an Entity regains health
     */
    @EventHandler
    public void onRegen(EntityRegainHealthEvent event) {
        if (event.getEntity() instanceof Player player) {
            event.setAmount(getVanillaHealing(player, event.getAmount()));
            return;
        }
        if (event.getEntity() instanceof LivingEntity entity) {
            event.setAmount(getVanillaHealing(entity, event.getAmount()));
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
        if (!(event.getEntity() instanceof LivingEntity target)) {
            return;
        }
        LivingEntity attacker;
        if (!(event.getDamager() instanceof LivingEntity)) {
            if (!(event.getDamager() instanceof Projectile projectile)) {
                return;
            }
            if (!(projectile.getShooter() instanceof LivingEntity shooter)) {
                return;
            }
            if (shooter instanceof Player) {
                if (!(projectile instanceof Arrow) && !(projectile instanceof SpectralArrow) && !(projectile instanceof FishHook)) {
                    return;
                }
            }
            attacker = shooter;
        } else {
            attacker = (LivingEntity) event.getDamager();
        }
        double weaponDamage = event.getDamage(), strength = 0, critDamage = 50, critChance = 30, defense = 0, damageRemainAfterDefense, ferocity = 0, attackCharge = 1;
        WeaponStats damageInc = new WeaponStats(0, 0, 0, 0, 0, 0), damageAddPercent = new WeaponStats(0, 0, 0, 0, 0, 0), damageMult = new WeaponStats(1, 1, 1, 1, 1, 1);
        ArmorStats healthInc = new ArmorStats(0, 0, 0), healthAddPercent = new ArmorStats(0, 0, 0), healthMult = new ArmorStats(1, 1, 1);
        BaseEntityStats damagerStats, targetStats;
        if (attacker instanceof Player player) {
            damagerStats = StatsListeners.getPlayerStats(player);
            attackCharge = player.getAttackCooldown();
            if (player.getInventory().getItemInMainHand().getType() != Material.AIR && player.getInventory().getItemInMainHand().getItemMeta() != null) {
                ItemMeta mainHandMeta = player.getInventory().getItemInMainHand().getItemMeta();
                if (mainHandMeta != null) {
                    ItemInfo mainHandInfo = mainHandMeta.getPersistentDataContainer().get(ItemEditor.getItemKey(), new ItemInfoDataType());
                    if (mainHandInfo != null) {
                        weaponDamage = mainHandInfo.getWeaponStats().getStatValue(StatType.DAMAGE) + 5;
                    }
                }
            }
            damageInc = StatsListeners.getEventWeaponStats(player, target, IncreaseType.INCREASE);
            damageAddPercent = StatsListeners.getEventWeaponStats(player, target, IncreaseType.ADDITIVE_PERCENT);
            damageMult = StatsListeners.getEventWeaponStats(player, target, IncreaseType.MULTIPLICATIVE_PERCENT);
        } else {
            damagerStats = MobListeners.getMobInfo(attacker);
            if (damagerStats != null) {
                weaponDamage = damagerStats.getCombatStatValue(StatType.DAMAGE);
            }
        }
        if (target instanceof Player player) {
            targetStats = StatsListeners.getPlayerStats(player);
            healthInc = StatsListeners.getEventArmorStats(attacker, player, IncreaseType.INCREASE);
            healthAddPercent = StatsListeners.getEventArmorStats(attacker, player, IncreaseType.ADDITIVE_PERCENT);
            healthMult = StatsListeners.getEventArmorStats(attacker, player, IncreaseType.MULTIPLICATIVE_PERCENT);
        } else {
            targetStats = MobListeners.getMobInfo(target);
        }
        if (damagerStats != null) {
            strength = (damagerStats.getCombatStatValue(StatType.STRENGTH) + damageInc.getStatValue(StatType.STRENGTH)) * (1 + (damageAddPercent.getStatValue(StatType.STRENGTH) / 100)) * damageMult.getStatValue(StatType.STRENGTH);
            critDamage = (damagerStats.getCombatStatValue(StatType.CRIT_DAMAGE) + damageInc.getStatValue(StatType.CRIT_DAMAGE)) * (1 + (damageAddPercent.getStatValue(StatType.CRIT_DAMAGE) / 100)) * damageMult.getStatValue(StatType.CRIT_DAMAGE);
            critChance = (damagerStats.getCombatStatValue(StatType.CRIT_CHANCE) + damageInc.getStatValue(StatType.CRIT_CHANCE)) * (1 + (damageAddPercent.getStatValue(StatType.CRIT_CHANCE) / 100)) * damageMult.getStatValue(StatType.CRIT_CHANCE);
            ferocity = (damagerStats.getCombatStatValue(StatType.FEROCITY) + damageInc.getStatValue(StatType.FEROCITY)) * (1 + (damageAddPercent.getStatValue(StatType.FEROCITY) / 100)) * damageMult.getStatValue(StatType.FEROCITY);
        }
        if (targetStats != null) {
            defense = (targetStats.getArmorStatValue(StatType.DEFENSE) + healthInc.getStatValue(StatType.DEFENSE)) * (1 + (healthAddPercent.getStatValue(StatType.DEFENSE) / 100)) * healthMult.getStatValue(StatType.DEFENSE);
        }
        boolean critical = Math.random() * 100 < critChance;
        if (!critical) {
            critDamage = 0;
        }
        double finalDamage = (weaponDamage * (1 + (strength / 100))) * (1 + (critDamage / 100)) * (1 + (damageAddPercent.getStatValue(StatType.DAMAGE) / 100)) * damageMult.getStatValue(StatType.DAMAGE) * attackCharge;
        damageRemainAfterDefense = 1 - (defense / (defense + 100));
        finalDamage *= damageRemainAfterDefense * healthMult.getStatValue(StatType.HEALTH);
        double remainDamage = finalDamage;
        if (attacker instanceof Player player) {
            for (EnchantmentsHolder holder : StatsListeners.getEnchantmentHolders(player)) {
                for (EnchantmentObject enchant : holder.getEnchants()) {
                    if (enchant instanceof ActionEnchantment) {
                        ((ActionEnchantment) enchant).action(attacker, target, finalDamage, critical, finalDamage);
                    }
                }
            }
        }
        if (target instanceof Player player) {
            remainDamage = calculateDamageAfterAbsorption(finalDamage, player);
        }
        event.setDamage(getVanillaDamage(target, remainDamage));
        showDamage(target, finalDamage, critical, null);
        if (ferocity > 0) {
            ferocityAttack(attacker, target, finalDamage, ferocity, critical);
        }
    }

    /**
     * Repeatedly damages the target based on the attacker's ferocity
     *
     * @param damager  The attacking entity
     * @param target   The LivingEntity to be attacked
     * @param damage   The amount of damage originally dealt
     * @param ferocity The ferocity stat of the attacker
     * @param critical Represents if the damage was a critical hit or not
     */
    private void ferocityAttack(LivingEntity damager, LivingEntity target, double damage, double ferocity, boolean critical) {
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
                        if (damager instanceof Player player) {
                            player.playSound(player, Sound.ITEM_FLINTANDSTEEL_USE, 2f, 0.605f);
                        }
                    } else {
                        count[0] = hits + 1;
                    }
                }
            }
        };
        feroHit.runTaskTimer(plugin, 2, 1);
    }

    private static double calculateDamageAfterAbsorption(double damage, Player player) {
        PlayerStats playerStats = StatsListeners.getPlayerStats(player);
        double damageLeft = damage - playerStats.getAbsorption();
        double remainAbsorption = playerStats.getAbsorption() - damage;
        if (remainAbsorption <= 0) {
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

    private static double getVanillaDamage(LivingEntity target, double amount) {
        if (target instanceof Player player) {
            PlayerStats playerStats = StatsListeners.getPlayerStats(player);
            double currentHealth = playerStats.getArmorStatValue(StatType.HEALTH), healthLeft = Math.max(currentHealth - amount, 0);
            playerStats.setArmorStatValue(StatType.HEALTH, healthLeft);
            player.getPersistentDataContainer().set(playerKey, new PlayerStatsDataType(), playerStats);
            if (currentHealth > 2048) {
                if (healthLeft < 2048) {
                    return 2048 - healthLeft;
                }
                return 0;
            }
            return amount;
        }
        MobInfo mobInfo = MobListeners.getMobInfo(target);
        if (mobInfo == null) {
            return amount;
        }
        double currentHealth = mobInfo.getArmorStatValue(StatType.HEALTH), healthLeft = Math.max(currentHealth - amount, 0);
        mobInfo.setArmorStatValue(StatType.HEALTH, healthLeft);
        target.getPersistentDataContainer().set(mobKey, new MobInfoDataType(), mobInfo);
        StatsListeners.updateEntityStats(target);
        updateHealthBar(target);
        if (currentHealth > 2048) {
            if (healthLeft < 2048) {
                return 2048 - healthLeft;
            }
            return 0;
        }
        return amount;
    }

    private static double getVanillaHealing(LivingEntity target, double amount) {
        if (target instanceof Player player) {
            PlayerStats playerStats = StatsListeners.getPlayerStats(player);
            double original = playerStats.getArmorStatValue(StatType.HEALTH), updated = Math.min(original + amount, playerStats.getMaxHealth());
            playerStats.setArmorStatValue(StatType.HEALTH, updated);
            player.getPersistentDataContainer().set(playerKey, new PlayerStatsDataType(), playerStats);
            StatsListeners.updateStats(player);
            if (original >= 2048) {
                return 0;
            }
            if (updated >= 2048) {
                return 2048 - original;
            }
            return amount;
        }
        MobInfo mobInfo = MobListeners.getMobInfo(target);
        if (mobInfo == null) {
            return amount;
        }
        double original = mobInfo.getArmorStatValue(StatType.HEALTH), updated = Math.min(original + amount, mobInfo.getMaxHealth());
        mobInfo.setArmorStatValue(StatType.HEALTH, updated);
        target.getPersistentDataContainer().set(mobKey, new MobInfoDataType(), mobInfo);
        StatsListeners.updateEntityStats(target);
        updateHealthBar(target);
        if (original >= 2048) {
            return 0;
        }
        if (updated >= 2048) {
            return 2048 - original;
        }
        return amount;
    }

    public static void playerMagicDamageEntity(Player player, LivingEntity target, AbilityType type) {
        double damage = calculateAbilityDamage(type, player, target);
        if (target instanceof Mob mob && mob.getTarget() == null) {
            mob.setTarget(player);
        }
        Bukkit.getServer().getPluginManager().callEvent(new EntityDamageEvent(target, EntityDamageEvent.DamageCause.MAGIC, damage));
    }

    public static void mobMagicAttack(Player target, double amount) {
        Bukkit.getServer().getPluginManager().callEvent(new EntityDamageEvent(target, EntityDamageEvent.DamageCause.MAGIC, amount));
    }

    public static void playerTrueDmaageEntity(Player attacker, LivingEntity target, double amount) {
        Bukkit.getServer().getPluginManager().callEvent(new EntityDamageEvent(target, EntityDamageEvent.DamageCause.SUICIDE, amount));
    }

    public static void mobTrueDamage(LivingEntity attacker, Player target, double amount) {
        Bukkit.getServer().getPluginManager().callEvent(new EntityDamageEvent(target, EntityDamageEvent.DamageCause.SUICIDE, amount));
    }

    public static double calculateAbilityDamage(AbilityType type, Player player, LivingEntity target) {
        PlayerStats playerStats = StatsListeners.getPlayerStats(player);
        AbilityStats add = StatsListeners.getEventAbilityStats(player, target, IncreaseType.INCREASE),
                addPercent = StatsListeners.getEventAbilityStats(player, target, IncreaseType.ADDITIVE_PERCENT),
                mult = StatsListeners.getEventAbilityStats(player, target, IncreaseType.MULTIPLICATIVE_PERCENT);
        double abilityDamage = (playerStats.getAbilityDamage() + add.getStatValue(StatType.ABILITY_DAMAGE)) * (1 + (addPercent.getStatValue(StatType.ABILITY_DAMAGE) / 100)) * mult.getStatValue(StatType.ABILITY_DAMAGE),
                intel = (playerStats.getIntelligence() + add.getStatValue(StatType.INTELLIGENCE)) * (1 + (addPercent.getStatValue(StatType.INTELLIGENCE) / 100)) * mult.getStatValue(StatType.INTELLIGENCE);
        double intelligenceScale = 1 + (intel / 100) * type.getAbilityScaling();
        double critScale = 1;
        if (type.allowCrit() && Math.random() < playerStats.getCombatStatValue(StatType.CRIT_CHANCE)) {
            critScale = 1 + (playerStats.getCombatStatValue(StatType.CRIT_DAMAGE) / 100);
        }
        double abilityDamageScale = 1 + (abilityDamage / 100);
        return type.getBaseAbilityDamage() * intelligenceScale * critScale * abilityDamageScale;
    }

}