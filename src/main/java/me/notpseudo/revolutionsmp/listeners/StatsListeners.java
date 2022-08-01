package me.notpseudo.revolutionsmp.listeners;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.abilities.AbilityType;
import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.playerstats.PlayerStats;
import me.notpseudo.revolutionsmp.playerstats.PlayerStatsDataType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

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

    private static ArrayList<UUID> showAbilities = new ArrayList<>();
    private static ArrayList<UUID> noMana = new ArrayList<>();

    /**
     * Holds an instance of the plugin
     */
    private final RevolutionSMP plugin;

    /**
     * Creates a new StatsListener object to allow the listeners to work<p>Starts a repeating task to update all player stats each second</p>
     *
     * @param plugin Instance of the plugin
     */
    public StatsListeners(RevolutionSMP plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, this.plugin);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this.plugin, () -> {
            // Every 20 game ticks, update player stats, regenerate health and mana, show action bar with info
            for (Player player : Bukkit.getOnlinePlayers().stream().filter(p -> !p.isDead()).toList()) {
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
        WeaponStats damageStats = new WeaponStats(0, 0, 30, 50, 0, 0);
        ArmorStats healthStats = new ArmorStats(100, 0, 100, 0);
        AbilityStats abilityStats = new AbilityStats(0, 100);
        FishingStats fishingStats = new FishingStats(20, 0);
        MiningStats miningStats = new MiningStats(0, 0, 0);
        GatheringStats gatheringStats = new GatheringStats(0, 0);
        LuckStats luckStats = new LuckStats(0, 0);
        // Checks to make sure each item that will affect stats is not null, checks to make sure each item's meta is not null
        // Gets and adds item stat boosts to base amounts
        List<ItemStack> equippedItems = new ArrayList<>();
        Collections.addAll(equippedItems, player.getInventory().getArmorContents());
        for (ItemStack item : equippedItems) {
            if (item != null && item.getItemMeta() != null) {
                ItemMeta meta = item.getItemMeta();
                ItemInfo itemInfo = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
                if (itemInfo != null) {
                    damageStats.combine(itemInfo.getWeaponStats());
                    healthStats.combine(itemInfo.getArmorStats());
                    abilityStats.combine(itemInfo.getAbilityStats());
                    fishingStats.combine(itemInfo.getFishingStats());
                    miningStats.combine(itemInfo.getMiningStats());
                    gatheringStats.combine(itemInfo.getGatheringStats());
                    luckStats.combine(itemInfo.getLuckStats());
                }
            }
        }
        ItemStack mainHand = player.getInventory().getItemInMainHand();
        if (mainHand.getItemMeta() != null) {
            ItemMeta meta = mainHand.getItemMeta();
            ItemInfo itemInfo = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
            if (itemInfo != null) {
                damageStats.combine(itemInfo.getWeaponStats());
                if (!ItemEditor.isArmor(itemInfo)) {
                    healthStats.combine(itemInfo.getArmorStats());
                }
                abilityStats.combine(itemInfo.getAbilityStats());
                fishingStats.combine(itemInfo.getFishingStats());
                miningStats.combine(itemInfo.getMiningStats());
                gatheringStats.combine(itemInfo.getGatheringStats());
                luckStats.combine(itemInfo.getLuckStats());
            }
        }
        ItemStack offHand = player.getInventory().getItemInMainHand();
        if (offHand.getItemMeta() != null) {
            ItemMeta meta = offHand.getItemMeta();
            ItemInfo itemInfo = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
            if (itemInfo != null) {
                damageStats.combine(itemInfo.getWeaponStats());
                if (!ItemEditor.isArmor(itemInfo)) {
                    healthStats.combine(itemInfo.getArmorStats());
                }
                abilityStats.combine(itemInfo.getAbilityStats());
                fishingStats.combine(itemInfo.getFishingStats());
                miningStats.combine(itemInfo.getMiningStats());
                gatheringStats.combine(itemInfo.getGatheringStats());
                luckStats.combine(itemInfo.getLuckStats());
            }
        }
        PlayerStats playerStats = player.getPersistentDataContainer().get(playerStatsKey, new PlayerStatsDataType());
        if (playerStats == null) {
            playerStats = new PlayerStats();
        }
        // Adjusts Player's Health to new max Health by keeping the same percentage
        double vanillaMaxHealth = Math.min(2048, healthStats.getStatValue(StatType.HEALTH));
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(vanillaMaxHealth);
        // Player will always see 40 hit points or 20 hearts on their screen
        player.setHealthScale(40);
        double healthPercent = playerStats.getCurrentHealth() / playerStats.getMaxHealth();
        player.setHealth(Math.max(healthPercent * vanillaMaxHealth, 0));
        player.setAbsorptionAmount(Math.min(playerStats.getAbsorption(), 40));
        playerStats.setCurrentHealth(healthPercent * healthStats.getStatValue(StatType.HEALTH));
        playerStats.setMaxHealth(healthStats.getStatValue(StatType.HEALTH));
        playerStats.setDefense(healthStats.getStatValue(StatType.DEFENSE) * playerStats.getDefenseMultiplier());
        playerStats.setSpeed((healthStats.getStatValue(StatType.SPEED) + playerStats.getAddSpeed()) * playerStats.getSpeedMultiplier());
        playerStats.setStrength(damageStats.getStatValue(StatType.STRENGTH));
        playerStats.setCritChance(damageStats.getStatValue(StatType.CRIT_CHANCE));
        playerStats.setCritDamage(damageStats.getStatValue(StatType.CRIT_DAMAGE));
        playerStats.setAttackSpeed(damageStats.getStatValue(StatType.ATTACK_SPEED));
        playerStats.setFerocity(damageStats.getStatValue(StatType.FEROCITY));
        playerStats.setAbilityStats(abilityStats);
        playerStats.setFishingStats(fishingStats);
        playerStats.setMiningStats(miningStats);
        playerStats.setGatheringStats(gatheringStats);
        playerStats.setLuckStats(luckStats);
        player.setWalkSpeed((float) ((healthStats.getStatValue(StatType.SPEED) + playerStats.getAddSpeed()) * playerStats.getSpeedMultiplier() / 500));
        player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(4 * (1 + (damageStats.getStatValue(StatType.ATTACK_SPEED) / 100)));
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
        if (!player.isDead() && player.getFoodLevel() >= 17) {
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
            double addMana = intelligence * 0.02 * playerStats.getManaRegenRate();
            double finalMana = (Math.min(mana + addMana, intelligence));
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
        if(showAbilities.contains(player.getUniqueId()) || noMana.contains(player.getUniqueId())) {
            return;
        }
        PlayerStats playerStats = player.getPersistentDataContainer().get(playerStatsKey, new PlayerStatsDataType());
        if (playerStats == null) {
            playerStats = new PlayerStats();
        }
        // Gets current Health, max Health, Defense, Intelligence or max Mana, and current Mana
        double currentHealth = playerStats.getCurrentHealth(), maxHealth = playerStats.getMaxHealth(), defense = playerStats.getDefense(), intelligence = playerStats.getIntelligence(), mana = playerStats.getMana(), currentAbsorption = playerStats.getAbsorption();;
        NamedTextColor healthColor = NamedTextColor.RED;
        if (currentAbsorption != 0) {
            // If Player has any absorption, the Health section will be gold instead of red
            healthColor = NamedTextColor.GOLD;
        }
        // Shows player their current Health out of max Health, Defense, and current Mana out of max Mana
        player.sendActionBar(Component.text(Math.round(currentHealth + currentAbsorption) + "/" + Math.round(maxHealth) + "❤     ", healthColor).append(Component.text(Math.round(defense) + "❈ Defense     ", NamedTextColor.GREEN)).append(Component.text(Math.round(mana) + "/" + Math.round(intelligence) + "✎ Mana", NamedTextColor.AQUA)));
    }

    public static void showAbilityActionBar(Player player, AbilityType type, double cost) {
        PlayerStats playerStats = player.getPersistentDataContainer().get(playerStatsKey, new PlayerStatsDataType());
        if (playerStats == null) {
            playerStats = new PlayerStats();
        }
        double currentHealth = playerStats.getCurrentHealth(), maxHealth = playerStats.getMaxHealth(), intelligence = playerStats.getIntelligence(), mana = playerStats.getMana(), currentAbsorption = playerStats.getAbsorption();;
        NamedTextColor healthColor = NamedTextColor.RED;
        if (currentAbsorption != 0) {
            healthColor = NamedTextColor.GOLD;
        }
        player.sendActionBar(Component.text(Math.round(currentHealth + currentAbsorption) + "/" + Math.round(maxHealth) + "❤     ", healthColor)
                .append(Component.text("-" + Math.round(cost) + " Mana (", NamedTextColor.AQUA))
                .append(Component.text(type.toString(), NamedTextColor.GOLD)).append(Component.text(")     ", NamedTextColor.AQUA)
                        .append(Component.text(Math.round(mana) + "/" + Math.round(intelligence) + "✎ Mana", NamedTextColor.AQUA))));
        showAbilities.add(player.getUniqueId());
        BukkitRunnable remove = new BukkitRunnable() {
            @Override
            public void run() {
                showAbilities.remove(player.getUniqueId());
            }
        };
        remove.runTaskLater(RevolutionSMP.getPlugin(), 30);
        showActionBar(player);
    }

    public static void showNoManaActionBar(Player player) {
        PlayerStats playerStats = player.getPersistentDataContainer().get(playerStatsKey, new PlayerStatsDataType());
        if (playerStats == null) {
            playerStats = new PlayerStats();
        }
        double currentHealth = playerStats.getCurrentHealth(), maxHealth = playerStats.getMaxHealth(), defense = playerStats.getDefense(), currentAbsorption = playerStats.getAbsorption();;
        NamedTextColor healthColor = NamedTextColor.RED;
        if (currentAbsorption != 0) {
            healthColor = NamedTextColor.GOLD;
        }
        player.sendActionBar(Component.text(Math.round(currentHealth + currentAbsorption) + "/" + Math.round(maxHealth) + "❤     ", healthColor)
                .append(Component.text(Math.round(defense) + "❈ Defense     ", NamedTextColor.GREEN))
                .append(Component.text("NOT ENOUGH MANA", NamedTextColor.RED, TextDecoration.BOLD)));
        noMana.add(player.getUniqueId());
        BukkitRunnable remove = new BukkitRunnable() {
            @Override
            public void run() {
                noMana.remove(player.getUniqueId());
            }
        };
        remove.runTaskLater(RevolutionSMP.getPlugin(), 30);
        showActionBar(player);
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
        BukkitRunnable update = new BukkitRunnable() {
            @Override
            public void run() {
                ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
                ItemMeta meta = item.getItemMeta();
                if(meta == null) {
                    ItemEditor.createMetaFromMat(meta, item.getType());
                }
                ItemEditor.updateLore(meta);
                item.setItemMeta(meta);
                updateStats(event.getPlayer());
            }
        };
        update.runTaskLaterAsynchronously(plugin, 1);

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