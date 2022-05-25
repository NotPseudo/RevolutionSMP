package me.notpseudo.revolutionsmp.listeners;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.playerstats.PlayerStats;
import me.notpseudo.revolutionsmp.playerstats.PlayerStatsDataType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.stream.Collectors;

/**
 * This class holds listeners that handle player stats and updating them accordingly
 *
 * @author NotPseudo
 */
public class StatsListeners implements Listener {

    /**
     * The NamespacedKey used to access PlayerStats held in persistent data
     */
    private final static NamespacedKey playerStatsKey = new NamespacedKey(RevolutionSMP.getPlugin(RevolutionSMP.class), "playerStats");
    /**
     * The NamedspacedKey from the ItemEditor class used to access ItemStats stored in Persistent Data
     */
    private final static NamespacedKey itemKey = ItemEditor.getItemKey();

    /**
     * Holds an instance of the plugin
     */
    private final RevolutionSMP plugin;

    /**
     * Instantiates a new StatsListener object to allow the listeners to work<p>Starts a repeating task to update all player stats each second</p>
     *
     * @param plugin Instance of the plugin
     */
    public StatsListeners(RevolutionSMP plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, this.plugin);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this.plugin, () -> {
            // Every 20 game ticks, for all players, update player stats. regen health and mana, show action bar with info
            for (Player player : Bukkit.getOnlinePlayers().stream().filter(p -> !p.isDead()).collect(Collectors.toList())) {
                updateStats(player);
                naturalRegen(player);
                showActionBar(player);
            }
        }, 20, 20);
    }

    /**
     * Returns the PlayerStats NamespacedKey for other classes to access PlayerStats in persistent data
     *
     * @return The PlayerStats NamespacedKey
     */
    public static NamespacedKey getPlayerStatsKey() {
        return playerStatsKey;
    }

    /**
     * Updates a player's stats based on armor and held item
     *
     * @param player The Player to update stats for
     */
    public static void updateStats(Player player) {
        if(player.isDead()) {
            return;
        }
        // Assigns base values for each stat
        double maxHealth = 100, defense = 0, strength = 0, speed = 100, critChance = 30, critDamage = 50, attackSpeed = 0, intelligence = 100, abilityDamage = 0, ferocity = 0;
        // Checks to make sure each item that will affect stats is not null, checks to make sure each item's meta is not null
        // Gets and adds item stat boosts to base amounts
        if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getItemMeta() != null) {
            ItemMeta helmetMeta = player.getInventory().getHelmet().getItemMeta();
            if (helmetMeta != null) {
                ItemInfo helmetItemInfo = helmetMeta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
                if (helmetItemInfo != null) {
                    WeaponStats helmetWeaponStats = helmetItemInfo.getWeaponStats();
                    ArmorStats helmetArmorStats = helmetItemInfo.getArmorStats();
                    AbilityStats helmetAbilityStats = helmetItemInfo.getAbilityStats();
                    if (helmetWeaponStats != null) {
                        strength += helmetWeaponStats.getStrength();
                        critChance += helmetWeaponStats.getCritChance();
                        critDamage += helmetWeaponStats.getCritDamage();
                        attackSpeed += helmetWeaponStats.getAttackSpeed();
                        ferocity += helmetWeaponStats.getFerocity();
                    }
                    if (helmetArmorStats != null) {
                        maxHealth += helmetArmorStats.getHealth();
                        defense += helmetArmorStats.getDefense();
                        speed += helmetArmorStats.getSpeed();
                    }
                    if (helmetAbilityStats != null) {
                        abilityDamage += helmetAbilityStats.getAbilityDamage();
                        intelligence += helmetAbilityStats.getIntelligence();
                    }
                }
            }
        }
        if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta() != null) {
            ItemMeta chestplateMeta = player.getInventory().getChestplate().getItemMeta();
            if (chestplateMeta != null) {
                ItemInfo chestplateItemInfo = chestplateMeta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
                if (chestplateItemInfo != null) {
                    WeaponStats chestplateWeaponStats = chestplateItemInfo.getWeaponStats();
                    ArmorStats chestplateArmorStats = chestplateItemInfo.getArmorStats();
                    AbilityStats chestplateAbilityStats = chestplateItemInfo.getAbilityStats();
                    if (chestplateWeaponStats != null) {
                        strength += chestplateWeaponStats.getStrength();
                        critChance += chestplateWeaponStats.getCritChance();
                        critDamage += chestplateWeaponStats.getCritDamage();
                        attackSpeed += chestplateWeaponStats.getAttackSpeed();
                        ferocity += chestplateWeaponStats.getFerocity();
                    }
                    if (chestplateArmorStats != null) {
                        maxHealth += chestplateArmorStats.getHealth();
                        defense += chestplateArmorStats.getDefense();
                        speed += chestplateArmorStats.getSpeed();
                    }
                    if (chestplateAbilityStats != null) {
                        abilityDamage += chestplateAbilityStats.getAbilityDamage();
                        intelligence += chestplateAbilityStats.getIntelligence();
                    }
                }
            }
        }
        if (player.getInventory().getLeggings() != null && player.getInventory().getLeggings().getItemMeta() != null) {
            ItemMeta leggingsMeta = player.getInventory().getLeggings().getItemMeta();
            if (leggingsMeta != null) {
                ItemInfo leggingsItemInfo = leggingsMeta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
                if (leggingsItemInfo != null) {
                    WeaponStats leggingsWeaponStats = leggingsItemInfo.getWeaponStats();
                    ArmorStats leggingsArmorStats = leggingsItemInfo.getArmorStats();
                    AbilityStats leggingsAbilityStats = leggingsItemInfo.getAbilityStats();
                    if (leggingsWeaponStats != null) {
                        strength += leggingsWeaponStats.getStrength();
                        critChance += leggingsWeaponStats.getCritChance();
                        critDamage += leggingsWeaponStats.getCritDamage();
                        attackSpeed += leggingsWeaponStats.getAttackSpeed();
                        ferocity += leggingsWeaponStats.getFerocity();
                    }
                    if (leggingsArmorStats != null) {
                        maxHealth += leggingsArmorStats.getHealth();
                        defense += leggingsArmorStats.getDefense();
                        speed += leggingsArmorStats.getSpeed();
                    }
                    if (leggingsAbilityStats != null) {
                        abilityDamage += leggingsAbilityStats.getAbilityDamage();
                        intelligence += leggingsAbilityStats.getIntelligence();
                    }
                }
            }
        }
        if (player.getInventory().getBoots() != null && player.getInventory().getBoots().getItemMeta() != null) {
            ItemMeta bootsMeta = player.getInventory().getBoots().getItemMeta();
            if (bootsMeta != null) {
                ItemInfo bootsItemInfo = bootsMeta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
                if (bootsItemInfo != null) {
                    WeaponStats bootsWeaponStats = bootsItemInfo.getWeaponStats();
                    ArmorStats bootsArmorStats = bootsItemInfo.getArmorStats();
                    AbilityStats bootsAbilityStats = bootsItemInfo.getAbilityStats();
                    if (bootsWeaponStats != null) {
                        strength += bootsWeaponStats.getStrength();
                        critChance += bootsWeaponStats.getCritChance();
                        critDamage += bootsWeaponStats.getCritDamage();
                        attackSpeed += bootsWeaponStats.getAttackSpeed();
                        ferocity += bootsWeaponStats.getFerocity();
                    }
                    if (bootsArmorStats != null) {
                        maxHealth += bootsArmorStats.getHealth();
                        defense += bootsArmorStats.getDefense();
                        speed += bootsArmorStats.getSpeed();
                    }
                    if (bootsAbilityStats != null) {
                        abilityDamage += bootsAbilityStats.getAbilityDamage();
                        intelligence += bootsAbilityStats.getIntelligence();
                    }
                }
            }
        }
        if (player.getInventory().getItemInMainHand().getType() != Material.AIR && player.getInventory().getItemInMainHand().getItemMeta() != null) {
            ItemMeta mainHandMeta = player.getInventory().getItemInMainHand().getItemMeta();
            if (mainHandMeta != null) {
                ItemInfo weaponItemInfo = mainHandMeta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
                if (weaponItemInfo != null && !ItemEditor.isArmor(weaponItemInfo)) {
                    WeaponStats mainHandWeaponStats = weaponItemInfo.getWeaponStats();
                    ArmorStats mainHandArmorStats = weaponItemInfo.getArmorStats();
                    AbilityStats mainHandAbilityStats = weaponItemInfo.getAbilityStats();
                    if (mainHandWeaponStats != null) {
                        strength += mainHandWeaponStats.getStrength();
                        critChance += mainHandWeaponStats.getCritChance();
                        critDamage += mainHandWeaponStats.getCritDamage();
                        attackSpeed += mainHandWeaponStats.getAttackSpeed();
                        ferocity += mainHandWeaponStats.getFerocity();
                    }
                    if (mainHandArmorStats != null) {
                        maxHealth += mainHandArmorStats.getHealth();
                        defense += mainHandArmorStats.getDefense();
                        speed += mainHandArmorStats.getSpeed();
                    }
                    if (mainHandAbilityStats != null) {
                        abilityDamage += mainHandAbilityStats.getAbilityDamage();
                        intelligence += mainHandAbilityStats.getIntelligence();
                    }
                }
            }
        }
        PlayerStats playerStats = player.getPersistentDataContainer().get(playerStatsKey, new PlayerStatsDataType());
        if (playerStats == null) {
            playerStats = new PlayerStats();
        }
        // Adjusts Player's Health to new max Health by keeping the same percentage
        double vanillaMaxHealth = Math.min(2048, maxHealth);
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(vanillaMaxHealth);
        // Player will always see 40 hit points or 20 hearts on their screen
        player.setHealthScale(40);
        double healthPercent = playerStats.getCurrentHealth() / playerStats.getMaxHealth();
        player.setHealth(healthPercent * vanillaMaxHealth);
        playerStats.setCurrentHealth(healthPercent * maxHealth);
        // Adjusts a Player's Speed
        player.setWalkSpeed((float) (speed / 500));
        // Set a Player's Attack Speed
        player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(4 * (1 + (attackSpeed / 100)));
        playerStats.setMaxHealth(maxHealth);
        playerStats.setDefense(defense);
        playerStats.setSpeed(speed);
        playerStats.setStrength(strength);
        playerStats.setCritChance(critChance);
        playerStats.setCritDamage(critDamage);
        playerStats.setAttackSpeed(attackSpeed);
        playerStats.setFerocity(ferocity);
        playerStats.setIntelligence(intelligence);
        playerStats.setAbilityDamage(abilityDamage);
        player.getPersistentDataContainer().set(playerStatsKey, new PlayerStatsDataType(), playerStats);
    }

    /**
     * Regenerates health and mana for the player
     *
     * @param player The Player to regenerate health and mana for
     */
    public static void naturalRegen(Player player) {
        PlayerStats playerStats = player.getPersistentDataContainer().get(playerStatsKey, new PlayerStatsDataType());
        if (playerStats == null) {
            playerStats = new PlayerStats();
        }
        double currentHealth = playerStats.getCurrentHealth(), maxHealth = playerStats.getMaxHealth(), intelligence = playerStats.getIntelligence(), mana = playerStats.getMana();
        if (!player.isDead()) {
            if (currentHealth != maxHealth) {
                // If the Player is not dead and their current Health is not already full
                // Amount of Health to add is (0.75 + 0.5% of max Health) rounded up to the nearest tenth
                double addHealth = (Math.ceil((0.75 + (maxHealth * 0.005)) * 10) / 10) * playerStats.getHealthRegenRate();
                // If adding the amount to add will exceed the max Health, it will just regenerate up to the max Health
                // Adds the amount to add to the Player's current Health
                double finalHealth = Math.min((currentHealth + addHealth), maxHealth);
                playerStats.setCurrentHealth(finalHealth);
                player.setHealth(Math.min(finalHealth, (finalHealth / maxHealth) * 2048));
            }
        }
        if (mana != intelligence) {
            // If current Mana is not already full
            // Amount of Mana to add is 2% of max Mana
            double addMana = intelligence * 0.02;
            double finalMana = (Math.min(mana + addMana, intelligence)) * playerStats.getManaRegenRate();
            playerStats.setMana(finalMana);
        }
        player.getPersistentDataContainer().set(playerStatsKey, new PlayerStatsDataType(), playerStats);
    }

    /**
     * Shows health, defense, and mana to the specified player
     *
     * @param player The Player to show the stats to
     */
    public static void showActionBar(Player player) {
        PlayerStats playerStats = player.getPersistentDataContainer().get(playerStatsKey, new PlayerStatsDataType());
        if (playerStats == null) {
            playerStats = new PlayerStats();
        }
        // Gets current Health, max Health, Defense, Intelligence or max Mana, and current Mana
        double currentHealth = playerStats.getCurrentHealth(), maxHealth = playerStats.getMaxHealth(), defense = playerStats.getDefense(), intelligence = playerStats.getIntelligence(), mana = playerStats.getMana();
        NamedTextColor healthColor = NamedTextColor.RED;
        double currentAbsorption = player.getAbsorptionAmount();
        if (currentAbsorption != 0) {
            // If Player has any absorption, the Health section will be gold instead of red
            healthColor = NamedTextColor.GOLD;
        }
        // Shows player their current Health out of max Health, Defense, and current Mana out of max Mana
        player.sendActionBar(Component.text(Math.round(currentHealth + currentAbsorption) + "/" + Math.round(maxHealth) + "❤     ", healthColor).append(Component.text(Math.round(defense) + "❈ Defense     ", NamedTextColor.GREEN)).append(Component.text(Math.round(mana) + "/" + Math.round(intelligence) + "✎ Mana", NamedTextColor.AQUA)));
    }

    /**
     * Updates player stats when a player joins the server
     *
     * @param event The Event fired when a player joins the server
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        // Update stats, give full mana
        Player player = event.getPlayer();
        updateStats(player);
        PlayerStats playerStats = player.getPersistentDataContainer().get(playerStatsKey, new PlayerStatsDataType());
        if (playerStats == null) {
            playerStats = new PlayerStats();
        }
        playerStats.setMana(playerStats.getIntelligence());
        player.getPersistentDataContainer().set(playerStatsKey, new PlayerStatsDataType(), playerStats);
        showActionBar(player);
    }

    /**
     * Updates player stats when a player switches armor
     *
     * @param event The Event fired when a player switches armor pieces
     */
    @EventHandler
    public void onArmorSwitch(PlayerArmorChangeEvent event) {
        updateStats(event.getPlayer());
    }

    /**
     * Updates player stats when a player switched the item in their hand
     *
     * @param event The Event fired when a player switches items in hand
     */
    @EventHandler
    public void onHandItemSwitch(PlayerItemHeldEvent event) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> updateStats(event.getPlayer()), 5);
    }

    /**
     * Updates player stats when a player respawns
     * @param event The Event fired when a player respawns
     */
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        PlayerStats playerStats = player.getPersistentDataContainer().get(playerStatsKey, new PlayerStatsDataType());
        if(playerStats == null) {
            playerStats = new PlayerStats();
        } else {
            playerStats.setCurrentHealth(playerStats.getMaxHealth());
            playerStats.setMana(playerStats.getIntelligence() / 2);
        }
        player.getPersistentDataContainer().set(playerStatsKey, new PlayerStatsDataType(), playerStats);
    }

}