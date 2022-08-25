package me.notpseudo.revolutionsmp.listeners;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.abilities.AbilityType;
import me.notpseudo.revolutionsmp.collections.CollectionUtils;
import me.notpseudo.revolutionsmp.collections.CollectionsHolder;
import me.notpseudo.revolutionsmp.items.ItemType;
import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.mobstats.MobInfo;
import me.notpseudo.revolutionsmp.mobstats.MobInfoDataType;
import me.notpseudo.revolutionsmp.playerstats.PlayerStats;
import me.notpseudo.revolutionsmp.playerstats.PlayerStatsDataType;
import me.notpseudo.revolutionsmp.skills.ExpDropObject;
import me.notpseudo.revolutionsmp.skills.SkillHolder;
import me.notpseudo.revolutionsmp.skills.SkillType;
import me.notpseudo.revolutionsmp.skills.SkillUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.Creature;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
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
                naturalRegenMana(player);
                showActionBar(player);
            }
        }, 20, 20);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this.plugin, () -> {
            for (Player player : Bukkit.getOnlinePlayers().stream().filter(p -> !p.isDead()).toList()) {
               naturalRegenHealth(player);
            }
        }, 20, 40);
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
        ItemEditor.updateItemOwner(player.getInventory().getItemInMainHand(), player.getUniqueId());
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
        double healthPercent = playerStats.getStatValue(StatType.HEALTH) / playerStats.getMaxHealth();
        playerStats.setMaxHealth(healthStats.getStatValue(StatType.HEALTH));
        player.setAbsorptionAmount(Math.min(playerStats.getAbsorption(), 40));
        playerStats.setStatValue(StatType.HEALTH, healthPercent * healthStats.getStatValue(StatType.HEALTH));
        player.setHealth(Math.min(2048, playerStats.getStatValue(StatType.HEALTH)));
        playerStats.setStatValue(StatType.DEFENSE, healthStats.getStatValue(StatType.DEFENSE));
        playerStats.setStatValue(StatType.TRUE_DEFENSE, healthStats.getStatValue(StatType.TRUE_DEFENSE));
        playerStats.setStatValue(StatType.SPEED, healthStats.getStatValue(StatType.SPEED));
        playerStats.setStatValue(StatType.STRENGTH, damageStats.getStatValue(StatType.STRENGTH));
        playerStats.setStatValue(StatType.CRIT_CHANCE, damageStats.getStatValue(StatType.CRIT_CHANCE));
        playerStats.setStatValue(StatType.CRIT_DAMAGE, damageStats.getStatValue(StatType.CRIT_DAMAGE));
        playerStats.setStatValue(StatType.ATTACK_SPEED, damageStats.getStatValue(StatType.ATTACK_SPEED));
        playerStats.setStatValue(StatType.FEROCITY, damageStats.getStatValue(StatType.FEROCITY));
        playerStats.setAbilityStats(abilityStats);
        playerStats.setFishingStats(fishingStats);
        playerStats.setMiningStats(miningStats);
        playerStats.setGatheringStats(gatheringStats);
        playerStats.setLuckStats(luckStats);
        player.setWalkSpeed(Math.min(1f, (float) (healthStats.getStatValue(StatType.SPEED) / 500)));
        player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(4 * (1 + (damageStats.getStatValue(StatType.ATTACK_SPEED) / 100)));
        updatePlayerStats(player, playerStats);
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
        WeaponStats damageStats = new WeaponStats(mobInfo.getStatValue(StatType.DAMAGE), mobInfo.getStatValue(StatType.STRENGTH), mobInfo.getStatValue(StatType.CRIT_CHANCE), mobInfo.getStatValue(StatType.CRIT_DAMAGE), mobInfo.getStatValue(StatType.ATTACK_SPEED), mobInfo.getStatValue(StatType.FEROCITY));
        ArmorStats healthStats = new ArmorStats(mobInfo.getMaxHealth(), mobInfo.getStatValue(StatType.DEFENSE), mobInfo.getStatValue(StatType.SPEED), mobInfo.getStatValue(StatType.TRUE_DEFENSE));
        for (ItemInfo info : getInfos(entity)) {
            damageStats.combine(info.getWeaponStats());
            healthStats.combine(info.getArmorStats());
        }
        double vanillaMaxHealth = Math.min(2048, healthStats.getStatValue(StatType.HEALTH));
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(vanillaMaxHealth);
        double healthPercent = mobInfo.getStatValue(StatType.HEALTH) / mobInfo.getMaxHealth();
        mobInfo.setMaxHealth(healthStats.getStatValue(StatType.HEALTH));
        mobInfo.setStatValue(StatType.HEALTH, healthPercent * healthStats.getStatValue(StatType.HEALTH));
        entity.setHealth(Math.min(2048, mobInfo.getStatValue(StatType.HEALTH)));
        mobInfo.setStatValue(StatType.DEFENSE, healthStats.getStatValue(StatType.DEFENSE));
        mobInfo.setStatValue(StatType.TRUE_DEFENSE, healthStats.getStatValue(StatType.TRUE_DEFENSE));
        mobInfo.setStatValue(StatType.SPEED, (healthStats.getStatValue(StatType.SPEED)));
        mobInfo.setStatValue(StatType.STRENGTH, damageStats.getStatValue(StatType.STRENGTH));
        mobInfo.setStatValue(StatType.CRIT_CHANCE, damageStats.getStatValue(StatType.CRIT_CHANCE));
        mobInfo.setStatValue(StatType.CRIT_DAMAGE, damageStats.getStatValue(StatType.CRIT_DAMAGE));
        mobInfo.setStatValue(StatType.ATTACK_SPEED, damageStats.getStatValue(StatType.ATTACK_SPEED));
        mobInfo.setStatValue(StatType.FEROCITY, damageStats.getStatValue(StatType.FEROCITY));
        entity.getPersistentDataContainer().set(mobKey, new MobInfoDataType(), mobInfo);
    }

    /**
     * Regenerates health and mana for the player
     *
     * @param player The Player to regenerate health and mana for
     */
    public static void naturalRegenMana(Player player) {
        PlayerStats playerStats = getPlayerStats(player);
        double intelligence = playerStats.getStatValue(StatType.INTELLIGENCE), mana = playerStats.getMana();
        if (mana != intelligence) {
            // If current Mana is not already full
            // Amount of Mana to add is 2% of max Mana
            double addMana = intelligence * 0.02 * (playerStats.getStatValue(StatType.MANA_REGEN) / 100);
            double finalMana = (Math.min(mana + addMana, intelligence));
            playerStats.setMana(finalMana);
        }
        updatePlayerStats(player, playerStats);
    }

    public static void naturalRegenHealth(Player player) {
        PlayerStats playerStats = getPlayerStats(player);
        double currentHealth = playerStats.getStatValue(StatType.HEALTH), maxHealth = playerStats.getMaxHealth();
        if (!player.isDead() && player.getFoodLevel() >= 17) {
            if (currentHealth != maxHealth) {
                double addHealth = Math.ceil(((1.5 + (maxHealth * 0.01)) * 10) / 10) * (playerStats.getStatValue(StatType.HEALTH_REGEN) / 100);
                Bukkit.getPluginManager().callEvent(new EntityRegainHealthEvent(player, addHealth, EntityRegainHealthEvent.RegainReason.CUSTOM));
            }
        }
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
        double currentHealth = playerStats.getStatValue(StatType.HEALTH), maxHealth = playerStats.getMaxHealth(), defense = playerStats.getStatValue(StatType.DEFENSE), intelligence = playerStats.getStatValue(StatType.INTELLIGENCE), mana = playerStats.getMana(), currentAbsorption = playerStats.getAbsorption();
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
        double currentHealth = playerStats.getStatValue(StatType.HEALTH), maxHealth = playerStats.getMaxHealth(), intelligence = playerStats.getStatValue(StatType.INTELLIGENCE), mana = playerStats.getMana(), currentAbsorption = playerStats.getAbsorption();
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
        double currentHealth = playerStats.getStatValue(StatType.HEALTH), maxHealth = playerStats.getMaxHealth(), defense = playerStats.getStatValue(StatType.DEFENSE), currentAbsorption = playerStats.getAbsorption();
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
        double currentHealth = playerStats.getStatValue(StatType.HEALTH), maxHealth = playerStats.getMaxHealth(), intelligence = playerStats.getStatValue(StatType.INTELLIGENCE), mana = playerStats.getMana(), currentAbsorption = playerStats.getAbsorption();
        NamedTextColor healthColor = NamedTextColor.RED;
        if (currentAbsorption != 0) {
            healthColor = NamedTextColor.GOLD;
        }
        player.sendActionBar(Component.text(Math.round(currentHealth + currentAbsorption) + "/" + Math.round(maxHealth) + "❤     ", healthColor)
                .append(Component.text(ItemEditor.getStatString(expDrop.getValue()) + " " + ItemEditor.getStringFromEnum(expDrop.getType()) + " (" + String.format("%.2f", percent * 100) + "%)     ", NamedTextColor.DARK_AQUA))
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
        if (expDrop.getType() != SkillType.BUILDING) {
            player.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.2f, 1f);
        }
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
        playerStats.setMana(playerStats.getStatValue(StatType.INTELLIGENCE));
        updatePlayerStats(player, playerStats);
        showActionBar(player);
        SkillHolder skills = SkillUtils.getHolder(player);
        for (SkillType type : SkillType.values()) {
            skills.addSkill(type);
        }
        SkillUtils.updatePlayerSkills(player, skills);
        CollectionsHolder collections = CollectionUtils.getCollectionHolder(player);
        collections.addAllTypes();
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
                ItemEditor.updateItemOwner(item, event.getPlayer().getUniqueId());
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
        playerStats.setStatValue(StatType.HEALTH, playerStats.getMaxHealth());
        playerStats.setMana(playerStats.getStatValue(StatType.INTELLIGENCE) / 2);
        updatePlayerStats(player, playerStats);
    }

    public static void updatePlayerStats(Player player, PlayerStats stats) {
        if (player == null || stats == null) {
            return;
        }
        player.getPersistentDataContainer().set(playerStatsKey, new PlayerStatsDataType(), stats);
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
    public static WeaponStats getEventWeaponStats(Player attacker, LivingEntity target, IncreaseType type) {
        WeaponStats eventWeapon = SkillUtils.getHolder(attacker).getEventWeapon(target, type);
        for (ItemInfo info : getInfos(attacker)) {
            WeaponStats infoStat = info.getEventWeaponStats(target, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                eventWeapon.multiply(infoStat);
            } else {
                eventWeapon.combine(infoStat);
            }
        }
        return eventWeapon;
    }

    @NotNull
    public static ArmorStats getEventArmorStats(LivingEntity attacker, Player target, IncreaseType type) {
        ArmorStats eventArmor = SkillUtils.getHolder(target).getEventArmor(attacker, type);
        for (ItemInfo info : getInfos(attacker)) {
            ArmorStats infoStat = info.getEventArmorStats(attacker, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                eventArmor.multiply(infoStat);
            } else {
                eventArmor.combine(infoStat);
            }
        }
        return eventArmor;
    }

    @NotNull
    public static AbilityStats getEventAbilityStats(Player attacker, LivingEntity target, IncreaseType type) {
        AbilityStats eventAbility = SkillUtils.getHolder(attacker).getEventAbility(target, type);
        for (ItemInfo info : getInfos(attacker)) {
            AbilityStats infoStat = info.getEventAbilityStats(target, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                eventAbility.multiply(infoStat);
            } else {
                eventAbility.combine(infoStat);
            }
        }
        return eventAbility;
    }

    @NotNull
    public static FishingStats getEventFishing(Player fisher, IncreaseType type) {
        FishingStats eventFishing = SkillUtils.getHolder(fisher).getEventFishing(type);
        for (ItemInfo info : getInfos(fisher)) {
            FishingStats infoStat = info.getEventFishing(type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                eventFishing.multiply(infoStat);
            } else {
                eventFishing.combine(infoStat);
            }
        }
        return eventFishing;
    }

    @NotNull
    public static MiningStats getEventMining(Player miner, Block block, IncreaseType type) {
        MiningStats eventMining = SkillUtils.getHolder(miner).getEventMining(block, type);
        for (ItemInfo info : getInfos(miner)) {
            MiningStats infoStat = info.getEventMining(block, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                eventMining.multiply(infoStat);
            } else {
                eventMining.combine(infoStat);
            }
        }
        return eventMining;
    }

    @NotNull
    public static GatheringStats getEventGathering(Player miner, Block block, IncreaseType type) {
        GatheringStats eventGathering = SkillUtils.getHolder(miner).getEventGathering(block, type);
        for (ItemInfo info : getInfos(miner)) {
            GatheringStats infoStat = info.getEventGathering(block, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                eventGathering.multiply(infoStat);
            } else {
                eventGathering.combine(infoStat);
            }
        }
        return eventGathering;
    }

    @NotNull
    public static LuckStats getEventLuck(Player player, LivingEntity target, IncreaseType type) {
        LuckStats eventLuck = SkillUtils.getHolder(player).getEventLuck(target, type);
        for (ItemInfo info : getInfos(player)) {
            LuckStats infoStat = info.getEventLuck(target, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                eventLuck.multiply(infoStat);
            } else {
                eventLuck.combine(infoStat);
            }
        }
        return eventLuck;
    }

    @NotNull
    public static WeaponStats getBonusWeapon(Player player, IncreaseType type) {
        WeaponStats bonusWeapon = SkillUtils.getHolder(player).getBonusWeapon(type);
        for (ItemInfo info : getInfos(player)) {
            WeaponStats infoStats = info.getBonusWeapon(type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonusWeapon.multiply(infoStats);
            } else {
                bonusWeapon.combine(infoStats);
            }
        }
        return bonusWeapon;
    }

    @NotNull
    public static ArmorStats getBonusArmor(Player player, IncreaseType type) {
        ArmorStats bonusArmor = SkillUtils.getHolder(player).getBonusArmor(type);
        for (ItemInfo info : getInfos(player)) {
            ArmorStats infoStats = info.getBonusArmor(type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonusArmor.multiply(infoStats);
            } else {
                bonusArmor.combine(infoStats);
            }
        }
        return bonusArmor;
    }

    @NotNull
    public static AbilityStats getBonusAbility(Player player, IncreaseType type) {
        AbilityStats bonusAbility = SkillUtils.getHolder(player).getBonusAbility(type);
        for (ItemInfo info : getInfos(player)) {
            AbilityStats infoStats = info.getBonusAbility(type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonusAbility.multiply(infoStats);
            } else {
                bonusAbility.combine(infoStats);
            }
        }
        return bonusAbility;
    }

    @NotNull
    public static FishingStats getBonusFishing(Player player, IncreaseType type) {
        FishingStats bonusFishing = SkillUtils.getHolder(player).getBonusFishing(type);
        for (ItemInfo info : getInfos(player)) {
            FishingStats infoStats = info.getBonusFishing(type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonusFishing.multiply(infoStats);
            } else {
                bonusFishing.combine(infoStats);
            }
        }
        return bonusFishing;
    }

    @NotNull
    public static MiningStats getBonusMining(Player player, IncreaseType type) {
        MiningStats bonusMining = SkillUtils.getHolder(player).getBonusMining(type);
        for (ItemInfo info : getInfos(player)) {
            MiningStats infoStats = info.getBonusMining(type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonusMining.multiply(infoStats);
            } else {
                bonusMining.combine(infoStats);
            }
        }
        return bonusMining;
    }

    @NotNull
    public static GatheringStats getBonusGathering(Player player, IncreaseType type) {
        GatheringStats bonusGathering = SkillUtils.getHolder(player).getBonusGathering(type);
        for (ItemInfo info : getInfos(player)) {
            GatheringStats infoStats = info.getBonusGathering(type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonusGathering.multiply(infoStats);
            } else {
                bonusGathering.combine(infoStats);
            }
        }
        return bonusGathering;
    }

    @NotNull
    public static LuckStats getBonusLuck(Player player, IncreaseType type) {
        LuckStats bonusLuck = SkillUtils.getHolder(player).getBonusLuck(type);
        for (ItemInfo info : getInfos(player)) {
            LuckStats infoStats = info.getBonusLuck(type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonusLuck.multiply(infoStats);
            } else {
                bonusLuck.combine(infoStats);
            }
        }
        return bonusLuck;
    }

    public static double getCombatWisdomStat(SkillType type, Player player, LivingEntity target) {
        StatType booster = type.getExpBooster();
        if (booster == null) {
            return 0;
        }
        double boost = getPlayerStats(player).getStatValue(booster);
        WisdomStats wisdom = WisdomStats.createZero();
        for (ItemInfo info : getInfos(player)) {
            wisdom.combine(info.getCombatWisdom(target));
        }
        return boost + wisdom.getStatValue(booster);
    }

    public static double getBreakWisdomStat(SkillType type, Player player, Block block) {
        StatType booster = type.getExpBooster();
        if (booster == null) {
            return 0;
        }
        double boost = getPlayerStats(player).getStatValue(booster);
        WisdomStats wisdom = WisdomStats.createZero();
        for (ItemInfo info : getInfos(player)) {
            wisdom.combine(info.getBreakingWisdom(block));
        }
        return boost + wisdom.getStatValue(booster);
    }

    public static double getRegularWisdomStat(SkillType type, Player player) {
        StatType booster = type.getExpBooster();
        if (booster == null) {
            return 0;
        }
        double boost = getPlayerStats(player).getStatValue(booster);
        WisdomStats wisdom = WisdomStats.createZero();
        for (ItemInfo info : getInfos(player)) {
            wisdom.combine(info.getRegularWisdom());
        }
        return boost + wisdom.getStatValue(booster);
    }

    @NotNull
    public static PlayerStats getPlayerStats(Player player) {
        PlayerStats playerStats = player.getPersistentDataContainer().get(playerStatsKey, new PlayerStatsDataType());
        if (playerStats == null) {
            playerStats = new PlayerStats();
            updatePlayerStats(player, playerStats);
        }
        return playerStats;
    }

}