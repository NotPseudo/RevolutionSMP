package me.notpseudo.revolutionsmp.menus;

import me.notpseudo.revolutionsmp.collections.CollectionUtils;
import me.notpseudo.revolutionsmp.itemstats.StatType;
import me.notpseudo.revolutionsmp.listeners.StatsListeners;
import me.notpseudo.revolutionsmp.playerstats.PlayerStats;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends Menu {

    public MainMenu(Player player) {
        super(player);
    }

    @Override
    public Component getTitle() {
        return Component.text("Main Menu");
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void setItems() {
        ItemStack stats = new ItemStack(Material.PLAYER_HEAD, 1);
        ItemMeta statsMeta = stats.getItemMeta();
        statsMeta.displayName(Component.text("Your Profile", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        List<Component> statLore = new ArrayList<>();
        statLore.add(Component.text("View your equipment and stats", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        statLore.add(Component.empty());
        PlayerStats playerStats = StatsListeners.getPlayerStats(player);
        statLore.add(StatType.HEALTH.getNameWithSymbol().append(Component.text(" " + playerStats.getStatValue(StatType.HEALTH), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        statLore.add(StatType.DEFENSE.getNameWithSymbol().append(Component.text(" " + playerStats.getStatValue(StatType.DEFENSE), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        statLore.add(StatType.STRENGTH.getNameWithSymbol().append(Component.text(" " + playerStats.getStatValue(StatType.STRENGTH), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        statLore.add(StatType.SPEED.getNameWithSymbol().append(Component.text(" " + playerStats.getStatValue(StatType.SPEED), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        statLore.add(StatType.CRIT_CHANCE.getNameWithSymbol().append(Component.text(" " + playerStats.getStatValue(StatType.CRIT_CHANCE), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        statLore.add(StatType.CRIT_DAMAGE.getNameWithSymbol().append(Component.text(" " + playerStats.getStatValue(StatType.CRIT_DAMAGE), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        statLore.add(StatType.INTELLIGENCE.getNameWithSymbol().append(Component.text(" " + playerStats.getStatValue(StatType.INTELLIGENCE), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        statLore.add(StatType.MINING_SPEED.getNameWithSymbol().append(Component.text(" " + playerStats.getStatValue(StatType.MINING_SPEED), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        statLore.add(StatType.ATTACK_SPEED.getNameWithSymbol().append(Component.text(" " + playerStats.getStatValue(StatType.ATTACK_SPEED), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        statLore.add(StatType.SEA_CREATURE_CHANCE.getNameWithSymbol().append(Component.text(" " + playerStats.getStatValue(StatType.SEA_CREATURE_CHANCE), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        statLore.add(StatType.MAGIC_FIND.getNameWithSymbol().append(Component.text(" " + playerStats.getStatValue(StatType.MAGIC_FIND), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        statLore.add(StatType.PET_LUCK.getNameWithSymbol().append(Component.text(" " + playerStats.getStatValue(StatType.PET_LUCK), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        statLore.add(StatType.TRUE_DEFENSE.getNameWithSymbol().append(Component.text(" " + playerStats.getStatValue(StatType.TRUE_DEFENSE), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        statLore.add(StatType.FEROCITY.getNameWithSymbol().append(Component.text(" " + playerStats.getStatValue(StatType.FEROCITY), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        statLore.add(StatType.ABILITY_DAMAGE.getNameWithSymbol().append(Component.text(" " + playerStats.getStatValue(StatType.ABILITY_DAMAGE), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        statLore.add(StatType.MINING_FORTUNE.getNameWithSymbol().append(Component.text(" " + playerStats.getStatValue(StatType.MINING_FORTUNE), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        statLore.add(StatType.FARMING_FORTUNE.getNameWithSymbol().append(Component.text(" " + playerStats.getStatValue(StatType.FARMING_FORTUNE), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        statLore.add(StatType.FORAGING_FORTUNE.getNameWithSymbol().append(Component.text(" " + playerStats.getStatValue(StatType.FORAGING_FORTUNE), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        statLore.add(StatType.PRISTINE.getNameWithSymbol().append(Component.text(" " + playerStats.getStatValue(StatType.PRISTINE), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        statLore.add(StatType.PURITY.getNameWithSymbol().append(Component.text(" " + playerStats.getStatValue(StatType.PURITY), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        statsMeta.lore(statLore);
        ((SkullMeta) statsMeta).setPlayerProfile(player.getPlayerProfile());
        stats.setItemMeta(statsMeta);
        inventory.setItem(13, makeMenuItem(stats, MenuType.PROFILE));

        ItemStack skills = new ItemStack(Material.NETHERITE_SWORD, 1);
        ItemMeta skillsMeta = skills.getItemMeta();
        skillsMeta.displayName(Component.text("Skills", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        skillsMeta.lore(List.of(Component.text("View your Skill progression and rewards", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)));
        skills.setItemMeta(skillsMeta);
        inventory.setItem(19, makeMenuItem(skills, MenuType.SKILLS));

        ItemStack collections = new ItemStack(Material.PAINTING, 1);
        ItemMeta collectionsMeta = collections.getItemMeta();
        collectionsMeta.displayName(Component.text("Collections", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        collectionsMeta.lore(getTotalCollectionProgress(CollectionUtils.getCollectionHolder(player), null));
        collections.setItemMeta(collectionsMeta);
        inventory.setItem(20, makeMenuItem(collections, MenuType.COLLECTIONS));

        ItemStack money = new ItemStack(Material.GOLD_BLOCK, 1);
        ItemMeta moneyMeta = money.getItemMeta();
        moneyMeta.displayName(Component.text("Money", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        moneyMeta.lore(List.of(
                Component.text("View your Bank and Purse", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                Component.text("Speak to any Nitwit to deposit or withdraw from your bank", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)));
        money.setItemMeta(moneyMeta);
        inventory.setItem(21, makeMenuItem(money, MenuType.MONEY));

        ItemStack reforge = new ItemStack(Material.ANVIL);
        ItemMeta reforgeMeta = reforge.getItemMeta();
        reforgeMeta.displayName(Component.text("Basic Reforge", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        reforgeMeta.lore(List.of(Component.text("Access the basic reforge menu", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)));
        reforge.setItemMeta(reforgeMeta);
        inventory.setItem(22, makeMenuItem(reforge, MenuType.REFORGE));

        ItemStack advReforge = new ItemStack(Material.ANVIL);
        ItemMeta advancedMeta = advReforge.getItemMeta();
        advancedMeta.displayName(Component.text("Advanced Reforge", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        advancedMeta.lore(List.of(Component.text("Access the advanced reforge menu", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)));
        advReforge.setItemMeta(advancedMeta);
        inventory.setItem(23, makeMenuItem(advReforge, MenuType.ADVANCED_REFORGE));

        ItemStack enchant = new ItemStack(Material.ENCHANTING_TABLE);
        ItemMeta enchantMeta = enchant.getItemMeta();
        enchantMeta.displayName(Component.text("Enchantment Table", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        enchantMeta.lore(List.of(Component.text("Access the enchantment table", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)));
        enchant.setItemMeta(enchantMeta);
        inventory.setItem(24, makeMenuItem(enchant, MenuType.ENCHANT));

        ItemStack builder = new ItemStack(Material.CRAFTING_TABLE, 1);
        ItemMeta builderMeta = builder.getItemMeta();
        builderMeta.displayName(Component.text("Builder Menu", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        builderMeta.lore(List.of(Component.text("Reach higher Builder skill", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                Component.text("to access building tools from anywhere", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)));
        builder.setItemMeta(builderMeta);
        inventory.setItem(31, makeMenuItem(builder, MenuType.BUILDER));


    }

}