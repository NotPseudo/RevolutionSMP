package me.notpseudo.revolutionsmp.menus;

import me.notpseudo.revolutionsmp.collections.CollectionUtils;
import me.notpseudo.revolutionsmp.collections.CollectionsHolder;
import me.notpseudo.revolutionsmp.skills.SkillType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CollectionsMenu extends Menu {

    public CollectionsMenu(Player player) {
        super(player);
    }

    @Override
    public Component getTitle() {
        return Component.text("Collections");
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void setItems() {
        CollectionsHolder holder = CollectionUtils.getCollectionHolder(player);

        ItemStack collections = new ItemStack(Material.PAINTING, 1);
        ItemMeta collectionsMeta = collections.getItemMeta();
        collectionsMeta.displayName(Component.text("Collections", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        collectionsMeta.lore(getTotalCollectionProgress(holder, null));
        inventory.setItem(4, makeMenuItem(collections, MenuType.COLLECTIONS));

        ItemStack farming = new ItemStack(Material.GOLDEN_HOE, 1);
        ItemMeta farmingMeta = farming.getItemMeta();
        farmingMeta.displayName(Component.text("Farming Collection", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        farmingMeta.lore(getTotalCollectionProgress(holder, SkillType.FARMING));
        inventory.setItem(20, makeMenuItem(farming, MenuType.FARMING_COLLECTIONS));

        ItemStack mining = new ItemStack(Material.STONE_PICKAXE, 1);
        ItemMeta miningMeta = mining.getItemMeta();
        miningMeta.displayName(Component.text("Mining Collection", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        miningMeta.lore(getTotalCollectionProgress(holder, SkillType.MINING));
        inventory.setItem(21, makeMenuItem(mining, MenuType.MINING_COLLECTIONS));

        ItemStack combat = new ItemStack(Material.STONE_SWORD, 1);
        ItemMeta combatMeta = combat.getItemMeta();
        combatMeta.displayName(Component.text("Combat Collection", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        combatMeta.lore(getTotalCollectionProgress(holder, SkillType.COMBAT));
        inventory.setItem(22, makeMenuItem(combat, MenuType.COMBAT_COLLECTIONS));

        ItemStack foraging = new ItemStack(Material.JUNGLE_SAPLING, 1);
        ItemMeta foragingMeta = foraging.getItemMeta();
        foragingMeta.displayName(Component.text("Foraging Collection", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        foragingMeta.lore(getTotalCollectionProgress(holder, SkillType.FORAGING));
        inventory.setItem(23, makeMenuItem(foraging, MenuType.FORAGING_COLLECTIONS));

        ItemStack fishing = new ItemStack(Material.FISHING_ROD, 1);
        ItemMeta fishingMeta = fishing.getItemMeta();
        fishingMeta.displayName(Component.text("Fishing Collection", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        fishingMeta.lore(getTotalCollectionProgress(holder, SkillType.FISHING));
        inventory.setItem(24, makeMenuItem(fishing, MenuType.FISHING_COLLECTIONS));

        ItemStack back = new ItemStack(Material.ARROW, 1);
        back.getItemMeta().displayName(Component.text("Back", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        inventory.setItem(48, makeMenuItem(back, MenuType.MAIN));

        ItemStack close = new ItemStack(Material.BARRIER, 1);
        close.getItemMeta().displayName(Component.text("Close", NamedTextColor.RED).decoration(TextDecoration.ITALIC, false));
        inventory.setItem(49, makeMenuItem(close, MenuType.CLOSE));
    }

}