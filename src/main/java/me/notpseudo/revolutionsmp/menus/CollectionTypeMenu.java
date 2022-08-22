package me.notpseudo.revolutionsmp.menus;

import me.notpseudo.revolutionsmp.collections.CollectionObject;
import me.notpseudo.revolutionsmp.collections.CollectionUtils;
import me.notpseudo.revolutionsmp.collections.CollectionsHolder;
import me.notpseudo.revolutionsmp.customcrafting.items.ItemEditor;
import me.notpseudo.revolutionsmp.skills.SkillType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CollectionTypeMenu extends Menu {

    private final SkillType category;

    public CollectionTypeMenu(Player player, SkillType category) {
        super(player);
        this.category = category;
    }

    @Override
    public Component getTitle() {
        return Component.text(ItemEditor.getStringFromEnum(category) + " Collection");
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void setItems() {
        CollectionsHolder holder = CollectionUtils.getCollectionHolder(player);

        Material material = Material.CHEST;
        switch (category) {
            case FARMING -> material = Material.GOLDEN_HOE;
            case MINING -> material = Material.STONE_PICKAXE;
            case COMBAT -> material = Material.STONE_SWORD;
            case FORAGING -> material = Material.JUNGLE_SAPLING;
            case FISHING -> material = Material.FISHING_ROD;
        }
        ItemStack top = new ItemStack(material, 1);
        ItemMeta topMeta = top.getItemMeta();
        topMeta.displayName(Component.text(ItemEditor.getStringFromEnum(category) + " Collection", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        topMeta.lore(getTotalCollectionProgress(holder, category));
        top.setItemMeta(topMeta);
        inventory.setItem(4, makeMenuItem(top, null));

        ArrayList<CollectionObject> collections = holder.getCollectionsFromCategory(category);
        for (int i = 0; i < 28; i++) {
            int row = 1 + i / 7, rowIndex = i % 7 + 1;
            if (i >= collections.size()) {
                inventory.setItem(row * 9 + rowIndex, null);
            } else {
                CollectionObject collection = collections.get(i);
                ItemStack item = new ItemStack(Material.GRAY_DYE);
                if (collection.getTotalCollected() > 0) {
                    if (collection.getType().getVanillaMaterials().size() != 0) {
                        item = new ItemStack(collection.getType().getVanillaMaterials().get(0));
                    } else if (collection.getType().getCustomMaterials().size() != 0) {
                        item = collection.getType().getCustomMaterials().get(0).getItem();
                    }
                }
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.displayName(Component.text(ItemEditor.getStringFromEnum(collection.getType()), NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
                ArrayList<Component> itemLore = new ArrayList<>();
                itemLore.add(Component.text("Total " + ItemEditor.getStringFromEnum(collection.getType()) + " Collection: ", NamedTextColor.GRAY).append(Component.text((int) collection.getTotalCollected(), NamedTextColor.YELLOW)).decoration(TextDecoration.ITALIC, false));
                itemLore.add(Component.empty());
                itemLore.addAll(getCollectionProgressList(collection));
                itemMeta.lore(itemLore);
                item.setItemMeta(itemMeta);
                inventory.setItem(row * 9 + rowIndex, makeMenuItem(item, null));
            }
        }

    }

}