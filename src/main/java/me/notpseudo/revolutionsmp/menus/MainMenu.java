package me.notpseudo.revolutionsmp.menus;

import me.notpseudo.revolutionsmp.collections.CollectionUtils;
import me.notpseudo.revolutionsmp.itemstats.StatType;
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
import java.util.Map;

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
        Map<StatType, Component> lines = getStatComponents(player);
        statLore.add(lines.get(StatType.HEALTH));
        statLore.add(lines.get(StatType.DEFENSE));
        statLore.add(lines.get(StatType.SPEED));
        statLore.add(lines.get(StatType.STRENGTH));
        statLore.add(lines.get(StatType.INTELLIGENCE));
        statLore.add(lines.get(StatType.CRIT_CHANCE));
        statLore.add(lines.get(StatType.CRIT_DAMAGE));
        statLore.add(lines.get(StatType.ABILITY_DAMAGE));
        statLore.add(lines.get(StatType.MAGIC_FIND));
        statLore.add(lines.get(StatType.PET_LUCK));
        statLore.add(lines.get(StatType.TRUE_DEFENSE));
        statLore.add(lines.get(StatType.SEA_CREATURE_CHANCE));
        statLore.add(lines.get(StatType.MINING_SPEED));
        statLore.add(lines.get(StatType.MINING_FORTUNE));
        statLore.add(lines.get(StatType.FARMING_FORTUNE));
        statLore.add(lines.get(StatType.FORAGING_FORTUNE));
        statLore.add(lines.get(StatType.PRISTINE));
        statLore.add(lines.get(StatType.PURITY));
        statLore.add(lines.get(StatType.COMBAT_WISDOM));
        statLore.add(lines.get(StatType.HEALTH_REGEN));
        statLore.add(lines.get(StatType.MANA_REGEN));
        statsMeta.lore(statLore);
        ((SkullMeta) statsMeta).setPlayerProfile(player.getPlayerProfile());
        stats.setItemMeta(statsMeta);
        inventory.setItem(13, makeMenuType(stats, MenuType.PROFILE));

        ItemStack skills = new ItemStack(Material.NETHERITE_SWORD, 1);
        ItemMeta skillsMeta = skills.getItemMeta();
        skillsMeta.displayName(Component.text("Skills", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        skillsMeta.lore(List.of(Component.text("View your Skill progression and rewards", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)));
        skills.setItemMeta(skillsMeta);
        inventory.setItem(19, makeMenuType(skills, MenuType.SKILLS));

        ItemStack collections = new ItemStack(Material.PAINTING, 1);
        ItemMeta collectionsMeta = collections.getItemMeta();
        collectionsMeta.displayName(Component.text("Collections", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        collectionsMeta.lore(getTotalCollectionProgress(CollectionUtils.getCollectionHolder(player), null));
        collections.setItemMeta(collectionsMeta);
        inventory.setItem(20, makeMenuType(collections, MenuType.COLLECTIONS));

        ItemStack money = new ItemStack(Material.GOLD_BLOCK, 1);
        ItemMeta moneyMeta = money.getItemMeta();
        moneyMeta.displayName(Component.text("Money", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        moneyMeta.lore(List.of(
                Component.text("View your Bank and Purse", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                Component.text("Speak to any Nitwit to deposit or withdraw from your bank", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)));
        money.setItemMeta(moneyMeta);
        inventory.setItem(21, makeMenuType(money, MenuType.MONEY));

        ItemStack crafting = new ItemStack(Material.CRAFTING_TABLE);
        ItemMeta craftingMeta = crafting.getItemMeta();
        craftingMeta.displayName(Component.text("Custom Crafting Table", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        craftingMeta.lore(List.of(Component.text("Access the crafting table to make custom items", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)));
        crafting.setItemMeta(craftingMeta);
        inventory.setItem(22, makeMenuType(crafting, MenuType.CRAFTING));

        ItemStack enchant = new ItemStack(Material.ENCHANTING_TABLE);
        ItemMeta enchantMeta = enchant.getItemMeta();
        enchantMeta.displayName(Component.text("Enchantment Table", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        enchantMeta.lore(List.of(Component.text("Access the enchantment table", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)));
        enchant.setItemMeta(enchantMeta);
        inventory.setItem(23, makeMenuType(enchant, MenuType.ENCHANT));

        ItemStack anvil = new ItemStack(Material.ANVIL);
        ItemMeta anvilMeta = anvil.getItemMeta();
        anvilMeta.displayName(Component.text("Anvil", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        anvilMeta.lore(List.of(Component.text("Access the anvil to combine", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                Component.text("and upgrade items", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)));
        anvil.setItemMeta(anvilMeta);
        inventory.setItem(24, makeMenuType(anvil, MenuType.ANVIL));

        ItemStack reforge = new ItemStack(Material.ANVIL);
        ItemMeta reforgeMeta = reforge.getItemMeta();
        reforgeMeta.displayName(Component.text("Basic Reforge", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        reforgeMeta.lore(List.of(Component.text("Access the basic reforge menu", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)));
        reforge.setItemMeta(reforgeMeta);
        inventory.setItem(25, makeMenuType(reforge, MenuType.REFORGE));

        ItemStack advReforge = new ItemStack(Material.ANVIL);
        ItemMeta advancedMeta = advReforge.getItemMeta();
        advancedMeta.displayName(Component.text("Advanced Reforge", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        advancedMeta.lore(List.of(Component.text("Access the advanced reforge menu", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)));
        advReforge.setItemMeta(advancedMeta);
        inventory.setItem(34, makeMenuType(advReforge, MenuType.ADVANCED_REFORGE));

        ItemStack builder = new ItemStack(Material.CRAFTING_TABLE, 1);
        ItemMeta builderMeta = builder.getItemMeta();
        builderMeta.displayName(Component.text("Builder Menu", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        builderMeta.lore(List.of(Component.text("Reach higher Builder skill", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                Component.text("to access building tools from anywhere", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)));
        builder.setItemMeta(builderMeta);
        inventory.setItem(31, makeMenuType(builder, MenuType.BUILDER));


    }

}