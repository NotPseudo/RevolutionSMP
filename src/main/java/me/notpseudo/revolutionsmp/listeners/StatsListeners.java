package me.notpseudo.revolutionsmp.listeners;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.abilities.AbilityObject;
import me.notpseudo.revolutionsmp.abilities.AbilityType;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentObject;
import me.notpseudo.revolutionsmp.items.ItemType;
import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.mobstats.MobInfo;
import me.notpseudo.revolutionsmp.mobstats.MobInfoDataType;
import me.notpseudo.revolutionsmp.playerstats.PlayerStats;
import me.notpseudo.revolutionsmp.playerstats.PlayerStatsDataType;
import me.notpseudo.revolutionsmp.skills.ExpDropObject;
import me.notpseudo.revolutionsmp.skills.SkillType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.Creature;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
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

    private final static NamespacedKey mobKey = MobListeners.getMobKey();

    private static ArrayList<UUID> showAbilities = new ArrayList<>();
    private static ArrayList<UUID> noMana = new ArrayList<>();

    private static ArrayList<UUID> showSkillXP = new ArrayList<>();

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
        if (player.isDead()) {
            return;
        }
        ItemEditor.updateItem(player.getInventory().getItemInMainHand(), player.getUniqueId());
        // Assign base values for each stat
        WeaponStats damageStats = new WeaponStats(0, 0, 30, 50, 0, 0);
        ArmorStats healthStats = new ArmorStats(100, 0, 100, 0);
        AbilityStats abilityStats = new AbilityStats(0, 100);
        FishingStats fishingStats = new FishingStats(20, 0);
        MiningStats miningStats = MiningStats.createZero();
        GatheringStats gatheringStats = GatheringStats.createZero();
        LuckStats luckStats = LuckStats.createZero();
        for (ItemInfo info : getInfos(player)) {
            damageStats.combine(info.getWeaponStats());
            healthStats.combine(info.getArmorStats());
            abilityStats.combine(info.getAbilityStats());
            fishingStats.combine(info.getFishingStats());
            miningStats.combine(info.getMiningStats());
            gatheringStats.combine(info.getGatheringStats());
            luckStats.combine(info.getLuckStats());
        }
        damageStats.combine(getBonusWeapon(player, IncreaseType.INCREASE));
        healthStats.combine(getBonusArmor(player, IncreaseType.INCREASE));
        abilityStats.combine(getBonusAbility(player, IncreaseType.INCREASE));
        fishingStats.combine(getBonusFishing(player, IncreaseType.INCREASE));
        miningStats.combine(getBonusMining(player, IncreaseType.INCREASE));
        gatheringStats.combine(getBonusGathering(player, IncreaseType.INCREASE));
        luckStats.combine(getBonusLuck(player, IncreaseType.INCREASE));
        damageStats.additivePercent(getBonusWeapon(player, IncreaseType.ADDITIVE_PERCENT));
        healthStats.additivePercent(getBonusArmor(player, IncreaseType.ADDITIVE_PERCENT));
        abilityStats.additivePercent(getBonusAbility(player, IncreaseType.ADDITIVE_PERCENT));
        fishingStats.additivePercent(getBonusFishing(player, IncreaseType.ADDITIVE_PERCENT));
        miningStats.additivePercent(getBonusMining(player, IncreaseType.ADDITIVE_PERCENT));
        gatheringStats.additivePercent(getBonusGathering(player, IncreaseType.ADDITIVE_PERCENT));
        luckStats.additivePercent(getBonusLuck(player, IncreaseType.ADDITIVE_PERCENT));
        damageStats.multiply(getBonusWeapon(player, IncreaseType.MULTIPLICATIVE_PERCENT));
        healthStats.multiply(getBonusArmor(player, IncreaseType.MULTIPLICATIVE_PERCENT));
        abilityStats.multiply(getBonusAbility(player, IncreaseType.MULTIPLICATIVE_PERCENT));
        fishingStats.multiply(getBonusFishing(player, IncreaseType.MULTIPLICATIVE_PERCENT));
        miningStats.multiply(getBonusMining(player, IncreaseType.MULTIPLICATIVE_PERCENT));
        gatheringStats.multiply(getBonusGathering(player, IncreaseType.MULTIPLICATIVE_PERCENT));
        luckStats.multiply(getBonusLuck(player, IncreaseType.MULTIPLICATIVE_PERCENT));
        PlayerStats playerStats = getPlayerStats(player);
        player.setHealthScale(40);
        // Adjusts Player's Health to new max Health by keeping the same percentage
        double vanillaMaxHealth = Math.min(2048, healthStats.getStatValue(StatType.HEALTH));
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(vanillaMaxHealth);
        // Player will always see 40 hit points or 20 hearts on their screen
        double healthPercent = playerStats.getArmorStatValue(StatType.HEALTH) / playerStats.getMaxHealth();
        playerStats.setMaxHealth(healthStats.getStatValue(StatType.HEALTH));
        player.setAbsorptionAmount(Math.min(playerStats.getAbsorption(), 40));
        playerStats.setArmorStatValue(StatType.HEALTH, healthPercent * healthStats.getStatValue(StatType.HEALTH));
        player.setHealth(Math.min(2048, playerStats.getArmorStatValue(StatType.HEALTH)));
        playerStats.setArmorStatValue(StatType.DEFENSE, healthStats.getStatValue(StatType.DEFENSE));
        playerStats.setArmorStatValue(StatType.TRUE_DEFENSE, healthStats.getStatValue(StatType.TRUE_DEFENSE));
        playerStats.setArmorStatValue(StatType.SPEED, healthStats.getStatValue(StatType.SPEED));
        playerStats.setCombatStatValue(StatType.STRENGTH, damageStats.getStatValue(StatType.STRENGTH));
        playerStats.setCombatStatValue(StatType.CRIT_CHANCE, damageStats.getStatValue(StatType.CRIT_CHANCE));
        playerStats.setCombatStatValue(StatType.CRIT_DAMAGE, damageStats.getStatValue(StatType.CRIT_DAMAGE));
        playerStats.setCombatStatValue(StatType.ATTACK_SPEED, damageStats.getStatValue(StatType.ATTACK_SPEED));
        playerStats.setCombatStatValue(StatType.FEROCITY, damageStats.getStatValue(StatType.FEROCITY));
        playerStats.setAbilityStats(abilityStats);
        playerStats.setFishingStats(fishingStats);
        playerStats.setMiningStats(miningStats);
        playerStats.setGatheringStats(gatheringStats);
        playerStats.setLuckStats(luckStats);
        player.setWalkSpeed(Math.min(1f, (float) (healthStats.getStatValue(StatType.SPEED) / 500)));
        player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(4 * (1 + (damageStats.getStatValue(StatType.ATTACK_SPEED) / 100)));
        player.getPersistentDataContainer().set(playerStatsKey, new PlayerStatsDataType(), playerStats);
    }

    public static void updateEntityStats(LivingEntity entity) {
        if (entity instanceof Player player) {
            updateStats(player);
            return;
        }
        if (!(entity instanceof Creature)) {
            return;
        }
        MobInfo mobInfo = MobListeners.getMobInfo(entity);
        if (mobInfo == null) {
            return;
        }
        WeaponStats damageStats = new WeaponStats(mobInfo.getCombatStatValue(StatType.DAMAGE), mobInfo.getCombatStatValue(StatType.STRENGTH), mobInfo.getCombatStatValue(StatType.CRIT_CHANCE), mobInfo.getCombatStatValue(StatType.CRIT_DAMAGE), mobInfo.getCombatStatValue(StatType.ATTACK_SPEED), mobInfo.getCombatStatValue(StatType.FEROCITY));
        ArmorStats healthStats = new ArmorStats(mobInfo.getMaxHealth(), mobInfo.getArmorStatValue(StatType.DEFENSE), mobInfo.getArmorStatValue(StatType.SPEED), mobInfo.getArmorStatValue(StatType.TRUE_DEFENSE));
        for (ItemInfo info : getInfos(entity)) {
            damageStats.combine(info.getWeaponStats());
            healthStats.combine(info.getArmorStats());
        }
        double vanillaMaxHealth = Math.min(2048, healthStats.getStatValue(StatType.HEALTH));
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(vanillaMaxHealth);
        double healthPercent = mobInfo.getArmorStatValue(StatType.HEALTH) / mobInfo.getMaxHealth();
        mobInfo.setMaxHealth(healthStats.getStatValue(StatType.HEALTH));
        mobInfo.setArmorStatValue(StatType.HEALTH, healthPercent * healthStats.getStatValue(StatType.HEALTH));
        entity.setHealth(Math.min(2048, mobInfo.getArmorStatValue(StatType.HEALTH)));
        mobInfo.setArmorStatValue(StatType.DEFENSE, healthStats.getStatValue(StatType.DEFENSE));
        mobInfo.setArmorStatValue(StatType.TRUE_DEFENSE, healthStats.getStatValue(StatType.TRUE_DEFENSE));
        mobInfo.setArmorStatValue(StatType.SPEED, (healthStats.getStatValue(StatType.SPEED)));
        mobInfo.setCombatStatValue(StatType.STRENGTH, damageStats.getStatValue(StatType.STRENGTH));
        mobInfo.setCombatStatValue(StatType.CRIT_CHANCE, damageStats.getStatValue(StatType.CRIT_CHANCE));
        mobInfo.setCombatStatValue(StatType.CRIT_DAMAGE, damageStats.getStatValue(StatType.CRIT_DAMAGE));
        mobInfo.setCombatStatValue(StatType.ATTACK_SPEED, damageStats.getStatValue(StatType.ATTACK_SPEED));
        mobInfo.setCombatStatValue(StatType.FEROCITY, damageStats.getStatValue(StatType.FEROCITY));
        entity.getPersistentDataContainer().set(mobKey, new MobInfoDataType(), mobInfo);
    }

    /**
     * Regenerates health and mana for the player
     *
     * @param player The Player to regenerate health and mana for
     */
    public static void naturalRegen(Player player) {
        PlayerStats playerStats = getPlayerStats(player);
        double currentHealth = playerStats.getArmorStatValue(StatType.HEALTH), maxHealth = playerStats.getMaxHealth(), intelligence = playerStats.getIntelligence(), mana = playerStats.getMana();
        if (!player.isDead() && player.getFoodLevel() >= 17) {
            if (currentHealth != maxHealth) {
                // If the Player is not dead and their current Health is not already full
                // Amount of Health to add is (0.75 + 0.5% of max Health) rounded up to the nearest tenth
                double addHealth = (Math.ceil((0.75 + (maxHealth * 0.005)) * 10) / 10) * playerStats.getHealthRegenRate();
                // If adding the amount to add will exceed the max Health, it will just regenerate up to the max Health
                // Adds the amount to add to the Player's current Health
                double finalHealth = Math.min((currentHealth + addHealth), maxHealth);
                playerStats.setArmorStatValue(StatType.HEALTH, finalHealth);
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
        if (showAbilities.contains(player.getUniqueId()) || noMana.contains(player.getUniqueId()) || showSkillXP.contains(player.getUniqueId())) {
            return;
        }
        PlayerStats playerStats = getPlayerStats(player);
        // Gets current Health, max Health, Defense, Intelligence or max Mana, and current Mana
        double currentHealth = playerStats.getArmorStatValue(StatType.HEALTH), maxHealth = playerStats.getMaxHealth(), defense = playerStats.getArmorStatValue(StatType.DEFENSE), intelligence = playerStats.getIntelligence(), mana = playerStats.getMana(), currentAbsorption = playerStats.getAbsorption();
        NamedTextColor healthColor = NamedTextColor.RED;
        if (currentAbsorption != 0) {
            // If Player has any absorption, the Health section will be gold instead of red
            healthColor = NamedTextColor.GOLD;
        }
        // Shows player their current Health out of max Health, Defense, and current Mana out of max Mana
        player.sendActionBar(Component.text(Math.round(currentHealth + currentAbsorption) + "/" + Math.round(maxHealth) + "❤     ", healthColor).append(Component.text(Math.round(defense) + "❈ Defense     ", NamedTextColor.GREEN)).append(Component.text(Math.round(mana) + "/" + Math.round(intelligence) + "✎ Mana", NamedTextColor.AQUA)));
    }

    /**
     * Shows the player the Ability they used and its mana cost on their action bar
     *
     * @param player The Player to update the action bar for
     * @param type   The AbilityType that was used
     * @param cost   How much mana was used
     */
    public static void showAbilityActionBar(Player player, AbilityType type, double cost) {
        PlayerStats playerStats = getPlayerStats(player);
        double currentHealth = playerStats.getArmorStatValue(StatType.HEALTH), maxHealth = playerStats.getMaxHealth(), intelligence = playerStats.getIntelligence(), mana = playerStats.getMana(), currentAbsorption = playerStats.getAbsorption();
        NamedTextColor healthColor = NamedTextColor.RED;
        if (currentAbsorption != 0) {
            healthColor = NamedTextColor.GOLD;
        }
        player.sendActionBar(Component.text(Math.round(currentHealth + currentAbsorption) + "/" + Math.round(maxHealth) + "❤     ", healthColor)
                .append(Component.text(ItemEditor.getStatString(-1 * Math.round(cost)) + " Mana (", NamedTextColor.AQUA))
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

    /**
     * Shows an action bar with a not enough mana warning to a Player who failed to use an ability
     *
     * @param player The player to show the action bar to
     */
    public static void showNoManaActionBar(Player player) {
        PlayerStats playerStats = getPlayerStats(player);
        double currentHealth = playerStats.getArmorStatValue(StatType.HEALTH), maxHealth = playerStats.getMaxHealth(), defense = playerStats.getArmorStatValue(StatType.DEFENSE), currentAbsorption = playerStats.getAbsorption();
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

    public static void showExpGainBar(Player player, ExpDropObject expDrop, double percent) {
        PlayerStats playerStats = getPlayerStats(player);
        double currentHealth = playerStats.getArmorStatValue(StatType.HEALTH), maxHealth = playerStats.getMaxHealth(), intelligence = playerStats.getIntelligence(), mana = playerStats.getMana(), currentAbsorption = playerStats.getAbsorption();
        NamedTextColor healthColor = NamedTextColor.RED;
        if (currentAbsorption != 0) {
            healthColor = NamedTextColor.GOLD;
        }
        player.sendActionBar(Component.text(Math.round(currentHealth + currentAbsorption) + "/" + Math.round(maxHealth) + "❤     ", healthColor)
                .append(Component.text(ItemEditor.getStatString(expDrop.getValue()) + ItemEditor.getStringFromEnum(expDrop.getType()) + " (" + String.format("%.2f", percent) + "%)", NamedTextColor.DARK_AQUA))
                .append(Component.text(Math.round(mana) + "/" + Math.round(intelligence) + "✎ Mana", NamedTextColor.AQUA)));
        showSkillXP.add(player.getUniqueId());
        BukkitRunnable remove = new BukkitRunnable() {
            @Override
            public void run() {
                showSkillXP.remove(player.getUniqueId());
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
        PlayerStats playerStats = getPlayerStats(player);
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
                ItemEditor.updateItem(item, event.getPlayer().getUniqueId());
                updateStats(event.getPlayer());
            }
        };
        update.runTaskLaterAsynchronously(plugin, 1);
    }

    /**
     * Updates player stats when a player respawns
     *
     * @param event The Event fired when a player respawns
     */
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        PlayerStats playerStats = getPlayerStats(player);
        playerStats.setArmorStatValue(StatType.HEALTH, playerStats.getMaxHealth());
        playerStats.setMana(playerStats.getIntelligence() / 2);
        player.getPersistentDataContainer().set(playerStatsKey, new PlayerStatsDataType(), playerStats);
    }

    @NotNull
    public static List<ItemStack> getAllEquipped(LivingEntity entity) {
        if (entity instanceof Player player) {
            return getAllEquipped(player);
        }
        ArrayList<ItemStack> equippedItems = new ArrayList<>();
        EntityEquipment equipment = entity.getEquipment();
        if (equipment == null) {
            return equippedItems;
        }
        for (ItemStack item : equipment.getArmorContents()) {
            if (item != null && item.getType() != Material.AIR) {
                equippedItems.add(item);
            }
        }
        if (equipment.getItemInMainHand().getType() != Material.AIR) {
            ItemInfo mainHandInfo = equipment.getItemInMainHand().getItemMeta().getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
            if (mainHandInfo != null && !ItemEditor.isArmor(mainHandInfo)) {
                equippedItems.add(equipment.getItemInMainHand());
            }
        }
        if (equipment.getItemInOffHand().getType() != Material.AIR) {
            ItemInfo offHandInfo = equipment.getItemInOffHand().getItemMeta().getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
            if (offHandInfo != null && !ItemEditor.isArmor(offHandInfo)) {
                equippedItems.add(equipment.getItemInOffHand());
            }
        }
        return equippedItems;
    }

    @NotNull
    public static List<ItemInfo> getInfos(LivingEntity entity) {
        if (entity instanceof Player player) {
            return getInfos(player);
        }
        ArrayList<ItemInfo> infos = new ArrayList<>();
        for (ItemStack item : getAllEquipped(entity)) {
            ItemInfo info = item.getItemMeta().getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
            if (info != null) {
                infos.add(info);
            }
        }
        return infos;
    }

    @NotNull
    public static List<ItemStack> getAllEquipped(Player player) {
        ArrayList<ItemStack> equippedItems = new ArrayList<>();
        for (ItemStack item : player.getInventory().getArmorContents()) {
            if (item != null && item.getType() != Material.AIR) {
                equippedItems.add(item);
            }
        }
        if (player.getInventory().getItemInMainHand().getType() != Material.AIR) {
            ItemInfo mainHandInfo = player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
            if (mainHandInfo != null && !ItemEditor.isArmor(mainHandInfo)) {
                equippedItems.add(player.getInventory().getItemInMainHand());
            }
        }
        if (player.getInventory().getItemInOffHand().getType() != Material.AIR) {
            ItemInfo offHandInfo = player.getInventory().getItemInOffHand().getItemMeta().getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
            if (offHandInfo != null && !ItemEditor.isArmor(offHandInfo)) {
                equippedItems.add(player.getInventory().getItemInMainHand());
            }
        }
        return equippedItems;
    }

    @NotNull
    public static List<ItemInfo> getInfos(Player player) {
        ArrayList<ItemInfo> infos = new ArrayList<>();
        for (ItemStack item : getAllEquipped(player)) {
            ItemInfo info = item.getItemMeta().getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
            if (info != null && info.getItemType() != ItemType.VANILLA_ITEM) {
                infos.add(info);
            }
        }
        return infos;
    }

    @NotNull
    public static List<EnchantmentsHolder> getEnchantmentHolders(Player player) {
        ArrayList<EnchantmentsHolder> holders = new ArrayList<>();
        for (ItemInfo info : getInfos(player)) {
            if (info.getEnchantmentsHolder() != null) {
                holders.add(info.getEnchantmentsHolder());
            }
        }
        return holders;
    }

    @NotNull
    public static WeaponStats getEventWeaponStats(Player damager, LivingEntity target, IncreaseType type) {
        WeaponStats eventWeapon = WeaponStats.createZero();
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            eventWeapon = WeaponStats.createMult();
        }
        for (ItemInfo info : getInfos(damager)) {
            if (info.getReforge() != null && info.getRarity() != null) {
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    eventWeapon.multiply(info.getReforge().getEventWeapon(info.getRarity(), damager, target, type));
                } else {
                    eventWeapon.combine(info.getReforge().getEventWeapon(info.getRarity(), damager, target, type));
                }
            }
            if (info.getEnchantmentsHolder() != null) {
                for (EnchantmentObject enchant : info.getEnchantmentsHolder().getEnchants()) {
                    if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                        eventWeapon.multiply(enchant.getEventWeapon(damager, target, type));
                    } else {
                        eventWeapon.combine(enchant.getEventWeapon(damager, target, type));
                    }
                }
            }
            if (info.getAbilitiesHolder() != null) {
                for (AbilityObject ability : info.getAbilitiesHolder().getAbilities()) {
                    if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                        eventWeapon.multiply(ability.getEventWeapon(damager, target, type));
                    } else {
                        eventWeapon.combine(ability.getEventWeapon(damager, target, type));
                    }
                }
            }
            if (info.getExtraInfo() != null) {
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    eventWeapon.multiply(info.getExtraInfo().getEventWeapon(damager, target, type));
                } else {
                    eventWeapon.combine(info.getExtraInfo().getEventWeapon(damager, target, type));
                }
            }
        }
        return eventWeapon;
    }

    @NotNull
    public static ArmorStats getEventArmorStats(LivingEntity damager, Player target, IncreaseType type) {
        ArmorStats eventArmor = ArmorStats.createZero();
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            eventArmor = ArmorStats.createMult();
        }
        for (ItemInfo info : getInfos(damager)) {
            if (info.getReforge() != null && info.getRarity() != null) {
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    eventArmor.multiply(info.getReforge().getEventArmor(info.getRarity(), damager, target, type));
                } else {
                    eventArmor.combine(info.getReforge().getEventArmor(info.getRarity(), damager, target, type));
                }
            }
            if (info.getEnchantmentsHolder() != null) {
                for (EnchantmentObject enchant : info.getEnchantmentsHolder().getEnchants()) {
                    if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                        eventArmor.multiply(enchant.getEventArmor(damager, target, type));
                    } else {
                        eventArmor.combine(enchant.getEventArmor(damager, target, type));
                    }
                }
            }
            if (info.getAbilitiesHolder() != null) {
                for (AbilityObject ability : info.getAbilitiesHolder().getAbilities()) {
                    if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                        eventArmor.multiply(ability.getEventArmor(damager, target, type));
                    } else {
                        eventArmor.combine(ability.getEventArmor(damager, target, type));
                    }
                }
            }
            if (info.getExtraInfo() != null) {
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    eventArmor.multiply(info.getExtraInfo().getEventArmor(damager, target, type));
                } else {
                    eventArmor.combine(info.getExtraInfo().getEventArmor(damager, target, type));
                }
            }
        }
        return eventArmor;
    }

    @NotNull
    public static AbilityStats getEventAbilityStats(Player damager, LivingEntity target, IncreaseType type) {
        AbilityStats eventAbility = AbilityStats.createZero();
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            eventAbility = AbilityStats.createMult();
        }
        for (ItemInfo info : getInfos(damager)) {
            if (info.getReforge() != null && info.getRarity() != null) {
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    eventAbility.multiply(info.getReforge().getEventAbility(info.getRarity(), damager, target, type));
                } else {
                    eventAbility.combine(info.getReforge().getEventAbility(info.getRarity(), damager, target, type));
                }
            }
            if (info.getEnchantmentsHolder() != null) {
                for (EnchantmentObject enchant : info.getEnchantmentsHolder().getEnchants()) {
                    if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                        eventAbility.multiply(enchant.getEventAbility(damager, target, type));
                    } else {
                        eventAbility.combine(enchant.getEventAbility(damager, target, type));
                    }
                }
            }
            if (info.getAbilitiesHolder() != null) {
                for (AbilityObject ability : info.getAbilitiesHolder().getAbilities()) {
                    if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                        eventAbility.multiply(ability.getEventAbility(damager, target, type));
                    } else {
                        eventAbility.combine(ability.getEventAbility(damager, target, type));
                    }
                }
            }
            if (info.getExtraInfo() != null) {
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    eventAbility.multiply(info.getExtraInfo().getEventWeapon(damager, target, type));
                } else {
                    eventAbility.combine(info.getExtraInfo().getEventWeapon(damager, target, type));
                }
            }
        }
        return eventAbility;
    }

    @NotNull
    public static FishingStats getEventFishing(Player fisher, IncreaseType type) {
        FishingStats eventFishing = FishingStats.createZero();
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            eventFishing = FishingStats.createMult();
        }
        for (ItemInfo info : getInfos(fisher)) {
            if (info.getReforge() != null && info.getRarity() != null) {
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    eventFishing.multiply(info.getReforge().getEventFishing(info.getRarity(), fisher, type));
                } else {
                    eventFishing.combine(info.getReforge().getEventFishing(info.getRarity(), fisher, type));
                }
            }
            if (info.getEnchantmentsHolder() != null) {
                for (EnchantmentObject enchant : info.getEnchantmentsHolder().getEnchants()) {
                    if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                        eventFishing.multiply(enchant.getEventFishing(fisher, type));
                    } else {
                        eventFishing.combine(enchant.getEventFishing(fisher, type));
                    }
                }
            }
            if (info.getAbilitiesHolder() != null) {
                for (AbilityObject ability : info.getAbilitiesHolder().getAbilities()) {
                    if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                        eventFishing.multiply(ability.getEventFishing(fisher, type));
                    } else {
                        eventFishing.combine(ability.getEventFishing(fisher, type));
                    }
                }
            }
            if (info.getExtraInfo() != null) {
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    eventFishing.multiply(info.getExtraInfo().getEventFishing(fisher, type));
                } else {
                    eventFishing.combine(info.getExtraInfo().getEventFishing(fisher, type));
                }
            }
        }
        return eventFishing;
    }

    @NotNull
    public static MiningStats getEventMining(Player miner, Block block, IncreaseType type) {
        MiningStats eventMining = MiningStats.createZero();
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            eventMining = new MiningStats(1, 1, 1);
        }
        for (ItemInfo info : getInfos(miner)) {
            if (info.getReforge() != null && info.getRarity() != null) {
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    eventMining.multiply(info.getReforge().getEventMining(info.getRarity(), miner, block, type));
                } else {
                    eventMining.combine(info.getReforge().getEventMining(info.getRarity(), miner, block, type));
                }
            }
            if (info.getEnchantmentsHolder() != null) {
                for (EnchantmentObject enchant : info.getEnchantmentsHolder().getEnchants()) {
                    if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                        eventMining.multiply(enchant.getEventMining(miner, block, type));
                    } else {
                        eventMining.combine(enchant.getEventMining(miner, block, type));
                    }
                }
            }
            if (info.getAbilitiesHolder() != null) {
                for (AbilityObject ability : info.getAbilitiesHolder().getAbilities()) {
                    if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                        eventMining.multiply(ability.getEventMining(miner, block, type));
                    } else {
                        eventMining.combine(ability.getEventMining(miner, block, type));
                    }
                }
            }
            if (info.getExtraInfo() != null) {
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    eventMining.multiply(info.getExtraInfo().getEventMining(miner, block, type));
                } else {
                    eventMining.combine(info.getExtraInfo().getEventMining(miner, block, type));
                }
            }
        }
        return eventMining;
    }

    @NotNull
    public static GatheringStats getEventGathering(Player miner, Block block, IncreaseType type) {
        GatheringStats eventGathering = GatheringStats.createZero();
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            eventGathering = GatheringStats.createMult();
        }
        for (ItemInfo info : getInfos(miner)) {
            if (info.getReforge() != null && info.getRarity() != null) {
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    eventGathering.multiply(info.getReforge().getEventGathering(info.getRarity(), miner, block, type));
                } else {
                    eventGathering.combine(info.getReforge().getEventGathering(info.getRarity(), miner, block, type));
                }
            }
            if (info.getEnchantmentsHolder() != null) {
                for (EnchantmentObject enchant : info.getEnchantmentsHolder().getEnchants()) {
                    if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                        eventGathering.multiply(enchant.getEventGathering(miner, block, type));
                    } else {
                        eventGathering.combine(enchant.getEventGathering(miner, block, type));
                    }
                }
            }
            if (info.getAbilitiesHolder() != null) {
                for (AbilityObject ability : info.getAbilitiesHolder().getAbilities()) {
                    if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                        eventGathering.multiply(ability.getEventGathering(miner, block, type));
                    } else {
                        eventGathering.combine(ability.getEventGathering(miner, block, type));
                    }
                }
            }
            if (info.getExtraInfo() != null) {
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    eventGathering.multiply(info.getExtraInfo().getEventGathering(miner, block, type));
                } else {
                    eventGathering.combine(info.getExtraInfo().getEventGathering(miner, block, type));
                }
            }
        }
        return eventGathering;
    }

    @NotNull
    public static LuckStats getEventLuck(Player player, LivingEntity target, IncreaseType type) {
        LuckStats eventLuck = LuckStats.createZero();
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            eventLuck = LuckStats.createMult();
        }
        for (ItemInfo info : getInfos(player)) {
            if (info.getReforge() != null && info.getRarity() != null) {
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    eventLuck.multiply(info.getReforge().getEventLuck(info.getRarity(), player, target, type));
                } else {
                    eventLuck.combine(info.getReforge().getEventLuck(info.getRarity(), player, target, type));
                }
            }
            if (info.getEnchantmentsHolder() != null) {
                for (EnchantmentObject enchant : info.getEnchantmentsHolder().getEnchants()) {
                    if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                        eventLuck.multiply(enchant.getEventLuck(player, target, type));
                    } else {
                        eventLuck.combine(enchant.getEventLuck(player, target, type));
                    }
                }
            }
            if (info.getAbilitiesHolder() != null) {
                for (AbilityObject ability : info.getAbilitiesHolder().getAbilities()) {
                    if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                        eventLuck.multiply(ability.getEventLuck(player, target, type));
                    } else {
                        eventLuck.combine(ability.getEventLuck(player, target, type));
                    }
                }
            }
            if (info.getExtraInfo() != null) {
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    eventLuck.multiply(info.getExtraInfo().getEventLuck(player, target, type));
                } else {
                    eventLuck.combine(info.getExtraInfo().getEventLuck(player, target, type));
                }
            }
        }
        return eventLuck;
    }

    @NotNull
    public static WeaponStats getBonusWeapon(Player player, IncreaseType type) {
        WeaponStats bonusWeapon = WeaponStats.createZero();
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            bonusWeapon = WeaponStats.createMult();
        }
        for (ItemInfo info : getInfos(player)) {
            if (info.getReforge() != null && info.getRarity() != null) {
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusWeapon.multiply(info.getReforge().getBonusWeapon(info.getRarity(), player, type));
                } else {
                    bonusWeapon.combine(info.getReforge().getBonusWeapon(info.getRarity(), player, type));
                }
            }
            if (info.getEnchantmentsHolder() != null) {
                for (EnchantmentObject enchant : info.getEnchantmentsHolder().getEnchants()) {
                    if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                        bonusWeapon.multiply(enchant.getBonusWeapon(player, type));
                    } else {
                        bonusWeapon.combine(enchant.getBonusWeapon(player, type));
                    }
                }
            }
            if (info.getAbilitiesHolder() != null) {
                for (AbilityObject ability : info.getAbilitiesHolder().getAbilities()) {
                    if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                        bonusWeapon.multiply(ability.getBonusWeapon(player, type));
                    } else {
                        bonusWeapon.combine(ability.getBonusWeapon(player, type));
                    }
                }
            }
            if (info.getExtraInfo() != null) {
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusWeapon.multiply(info.getExtraInfo().getBonusWeapon(player, type));
                } else {
                    bonusWeapon.combine(info.getExtraInfo().getBonusWeapon(player, type));
                }
            }
        }
        return bonusWeapon;
    }

    @NotNull
    public static ArmorStats getBonusArmor(Player player, IncreaseType type) {
        ArmorStats bonusArmor = ArmorStats.createZero();
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            bonusArmor = ArmorStats.createMult();
        }
        for (ItemInfo info : getInfos(player)) {
            if (info.getReforge() != null && info.getRarity() != null) {
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusArmor.multiply(info.getReforge().getBonusArmor(info.getRarity(), player, type));
                } else {
                    bonusArmor.combine(info.getReforge().getBonusArmor(info.getRarity(), player, type));
                }
            }
            if (info.getEnchantmentsHolder() != null) {
                for (EnchantmentObject enchant : info.getEnchantmentsHolder().getEnchants()) {
                    if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                        bonusArmor.multiply(enchant.getBonusArmor(player, type));
                    } else {
                        bonusArmor.combine(enchant.getBonusArmor(player, type));
                    }
                }
            }
            if (info.getAbilitiesHolder() != null) {
                for (AbilityObject ability : info.getAbilitiesHolder().getAbilities()) {
                    if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                        bonusArmor.multiply(ability.getBonusArmor(player, type));
                    } else {
                        bonusArmor.combine(ability.getBonusArmor(player, type));
                    }
                }
            }
            if (info.getExtraInfo() != null) {
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusArmor.multiply(info.getExtraInfo().getBonusArmor(player, type));
                } else {
                    bonusArmor.combine(info.getExtraInfo().getBonusArmor(player, type));
                }
            }
        }
        return bonusArmor;
    }

    @NotNull
    public static AbilityStats getBonusAbility(Player player, IncreaseType type) {
        AbilityStats bonusAbility = AbilityStats.createZero();
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            bonusAbility = AbilityStats.createMult();
        }
        for (ItemInfo info : getInfos(player)) {
            if (info.getReforge() != null && info.getRarity() != null) {
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusAbility.multiply(info.getReforge().getBonusAbility(info.getRarity(), player, type));
                } else {
                    bonusAbility.combine(info.getReforge().getBonusAbility(info.getRarity(), player, type));
                }
            }
            if (info.getEnchantmentsHolder() != null) {
                for (EnchantmentObject enchant : info.getEnchantmentsHolder().getEnchants()) {
                    if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                        bonusAbility.multiply(enchant.getBonusAbility(player, type));
                    } else {
                        bonusAbility.combine(enchant.getBonusAbility(player, type));
                    }
                }
            }
            if (info.getAbilitiesHolder() != null) {
                for (AbilityObject ability : info.getAbilitiesHolder().getAbilities()) {
                    if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                        bonusAbility.multiply(ability.getBonusAbility(player, type));
                    } else {
                        bonusAbility.combine(ability.getBonusAbility(player, type));
                    }
                }
            }
            if (info.getExtraInfo() != null) {
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusAbility.multiply(info.getExtraInfo().getBonusAbility(player, type));
                } else {
                    bonusAbility.combine(info.getExtraInfo().getBonusAbility(player, type));
                }
            }
        }
        return bonusAbility;
    }

    @NotNull
    public static FishingStats getBonusFishing(Player player, IncreaseType type) {
        FishingStats bonusFishing = FishingStats.createZero();
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            bonusFishing = FishingStats.createMult();
        }
        for (ItemInfo info : getInfos(player)) {
            if (info.getReforge() != null && info.getRarity() != null) {
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusFishing.multiply(info.getReforge().getBonusFishing(info.getRarity(), player, type));
                } else {
                    bonusFishing.combine(info.getReforge().getBonusFishing(info.getRarity(), player, type));
                }
            }
            if (info.getEnchantmentsHolder() != null) {
                for (EnchantmentObject enchant : info.getEnchantmentsHolder().getEnchants()) {
                    if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                        bonusFishing.multiply(enchant.getBonusFishing(player, type));
                    } else {
                        bonusFishing.combine(enchant.getBonusFishing(player, type));
                    }
                }
            }
            if (info.getAbilitiesHolder() != null) {
                for (AbilityObject ability : info.getAbilitiesHolder().getAbilities()) {
                    if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                        bonusFishing.multiply(ability.getBonusFishing(player, type));
                    } else {
                        bonusFishing.combine(ability.getBonusFishing(player, type));
                    }
                }
            }
            if (info.getExtraInfo() != null) {
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusFishing.multiply(info.getExtraInfo().getBonusFishing(player, type));
                } else {
                    bonusFishing.combine(info.getExtraInfo().getBonusFishing(player, type));
                }
            }
        }
        return bonusFishing;
    }

    @NotNull
    public static MiningStats getBonusMining(Player player, IncreaseType type) {
        MiningStats bonusMining = MiningStats.createZero();
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            bonusMining = MiningStats.createMult();
        }
        for (ItemInfo info : getInfos(player)) {
            if (info.getReforge() != null && info.getRarity() != null) {
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusMining.multiply(info.getReforge().getBonusMining(info.getRarity(), player, type));
                } else {
                    bonusMining.combine(info.getReforge().getBonusMining(info.getRarity(), player, type));
                }
            }
            if (info.getEnchantmentsHolder() != null) {
                for (EnchantmentObject enchant : info.getEnchantmentsHolder().getEnchants()) {
                    if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                        bonusMining.multiply(enchant.getBonusMining(player, type));
                    } else {
                        bonusMining.combine(enchant.getBonusMining(player, type));
                    }
                }
            }
            if (info.getAbilitiesHolder() != null) {
                for (AbilityObject ability : info.getAbilitiesHolder().getAbilities()) {
                    if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                        bonusMining.multiply(ability.getBonusMining(player, type));
                    } else {
                        bonusMining.combine(ability.getBonusMining(player, type));
                    }
                }
            }
            if (info.getExtraInfo() != null) {
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusMining.multiply(info.getExtraInfo().getBonusMining(player, type));
                } else {
                    bonusMining.combine(info.getExtraInfo().getBonusMining(player, type));
                }
            }
        }
        return bonusMining;
    }

    @NotNull
    public static GatheringStats getBonusGathering(Player player, IncreaseType type) {
        GatheringStats bonusGathering = GatheringStats.createZero();
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            bonusGathering = GatheringStats.createMult();
        }
        for (ItemInfo info : getInfos(player)) {
            if (info.getReforge() != null && info.getRarity() != null) {
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusGathering.multiply(info.getReforge().getBonusGathering(info.getRarity(), player, type));
                } else {
                    bonusGathering.combine(info.getReforge().getBonusGathering(info.getRarity(), player, type));
                }
            }
            if (info.getEnchantmentsHolder() != null) {
                for (EnchantmentObject enchant : info.getEnchantmentsHolder().getEnchants()) {
                    if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                        bonusGathering.multiply(enchant.getBonusGathering(player, type));
                    } else {
                        bonusGathering.combine(enchant.getBonusGathering(player, type));
                    }
                }
            }
            if (info.getAbilitiesHolder() != null) {
                for (AbilityObject ability : info.getAbilitiesHolder().getAbilities()) {
                    if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                        bonusGathering.multiply(ability.getBonusGathering(player, type));
                    } else {
                        bonusGathering.combine(ability.getBonusGathering(player, type));
                    }
                }
            }
            if (info.getExtraInfo() != null) {
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusGathering.multiply(info.getExtraInfo().getBonusGathering(player, type));
                } else {
                    bonusGathering.combine(info.getExtraInfo().getBonusGathering(player, type));
                }
            }
        }
        return bonusGathering;
    }

    @NotNull
    public static LuckStats getBonusLuck(Player player, IncreaseType type) {
        LuckStats bonusLuck = LuckStats.createZero();
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            bonusLuck = LuckStats.createMult();
        }
        for (ItemInfo info : getInfos(player)) {
            if (info.getReforge() != null && info.getRarity() != null) {
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusLuck.multiply(info.getReforge().getBonusLuck(info.getRarity(), player, type));
                } else {
                    bonusLuck.combine(info.getReforge().getBonusLuck(info.getRarity(), player, type));
                }
            }
            if (info.getEnchantmentsHolder() != null) {
                for (EnchantmentObject enchant : info.getEnchantmentsHolder().getEnchants()) {
                    if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                        bonusLuck.multiply(enchant.getBonusLuck(player, type));
                    } else {
                        bonusLuck.combine(enchant.getBonusLuck(player, type));
                    }
                }
            }
            if (info.getAbilitiesHolder() != null) {
                for (AbilityObject ability : info.getAbilitiesHolder().getAbilities()) {
                    if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                        bonusLuck.multiply(ability.getBonusLuck(player, type));
                    } else {
                        bonusLuck.combine(ability.getBonusLuck(player, type));
                    }
                }
            }
            if (info.getExtraInfo() != null) {
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusLuck.multiply(info.getExtraInfo().getBonusLuck(player, type));
                } else {
                    bonusLuck.combine(info.getExtraInfo().getBonusLuck(player, type));
                }
            }
        }
        return bonusLuck;
    }

    public static ExpDropObject getCombatXpBoost(Player player, SkillType type, LivingEntity target, IncreaseType inc) {
        ExpDropObject boost = new ExpDropObject(type, 0);
        if (inc == IncreaseType.MULTIPLICATIVE_PERCENT) {
            boost = new ExpDropObject(type, 1);
        }
        for (ItemInfo info : getInfos(player)) {
            if (info.getExtraInfo() != null) {
                if (inc == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    boost.multiply(info.getExtraInfo().getCombatEventExpBoost(type, IncreaseType.MULTIPLICATIVE_PERCENT, target));
                } else {
                    boost.add(info.getExtraInfo().getCombatEventExpBoost(type, inc, target));
                }
            }
        }
        return boost;
    }

    public static ExpDropObject getBreakXpBoost(Player player, SkillType type, Block block, IncreaseType inc) {
        ExpDropObject boost = new ExpDropObject(type, 0);
        if (inc == IncreaseType.MULTIPLICATIVE_PERCENT) {
            boost = new ExpDropObject(type, 1);
        }
        for (ItemInfo info : getInfos(player)) {
            if (info.getExtraInfo() != null) {
                if (inc == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    boost.multiply(info.getExtraInfo().getBreakEventExpBoost(type, IncreaseType.MULTIPLICATIVE_PERCENT, block));
                } else {
                    boost.add(info.getExtraInfo().getBreakEventExpBoost(type, inc, block));
                }
            }
        }
        return boost;
    }

    @NotNull
    public static PlayerStats getPlayerStats(Player player) {
        PlayerStats playerStats = player.getPersistentDataContainer().get(playerStatsKey, new PlayerStatsDataType());
        if (playerStats == null) {
            playerStats = new PlayerStats();
            player.getPersistentDataContainer().set(playerStatsKey, new PlayerStatsDataType(), playerStats);
        }
        return playerStats;
    }

}