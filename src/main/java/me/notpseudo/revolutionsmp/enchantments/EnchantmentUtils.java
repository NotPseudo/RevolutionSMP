package me.notpseudo.revolutionsmp.enchantments;

import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.items.ItemID;
import me.notpseudo.revolutionsmp.itemstats.EnchantmentsHolder;
import me.notpseudo.revolutionsmp.itemstats.ItemInfo;
import me.notpseudo.revolutionsmp.itemstats.ItemInfoDataType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class EnchantmentUtils {

    private static final NamespacedKey itemKey = ItemEditor.getItemKey();

    public static ItemStack createEnchantedBook(EnchantmentType enchant) {
        return createEnchantedBook(enchant, enchant.getMinLevel());
    }

    public static ItemStack createEnchantedBook(EnchantmentType enchant, int level) {
        ItemStack book = ItemEditor.createItem(ItemID.ENCHANTED_BOOK);
        ItemInfo info = ItemEditor.getInfo(book);
        EnchantmentsHolder holder = new EnchantmentsHolder(info);
        holder.addEnchant(enchant.createObject(level));
        info.setEnchantmentsHolder(holder);
        ItemMeta meta = book.getItemMeta();
        meta.getPersistentDataContainer().set(itemKey, new ItemInfoDataType(), info);
        updateEnchantLore(book);
        book.setItemMeta(meta);
        return book;
    }

    public static void updateEnchantLore(ItemStack item) {
        ItemInfo info = ItemEditor.getInfo(item);
        if (info == null) {
            return;
        }
        if (info.getItemID() != ItemID.ENCHANTED_BOOK) {
            return;
        }
        if (info.getEnchantmentsHolder() == null) {
            return;
        }
        ArrayList<Component> lore = new ArrayList<>();
        int totalLevel = 0;
        for (EnchantmentObject enchant : info.getEnchantmentsHolder().getEnchants()) {
            lore.add(Component.text(enchant.getText()).decoration(TextDecoration.ITALIC, false));
            totalLevel += enchant.getType().getExpLevelsNeeded(enchant.getLevel());
            if (info.getEnchantmentsHolder().getEnchants().size() < 4) {
                lore.addAll(enchant.getType().getEnchantLore(enchant.getLevel()));
            }
        }
        int finalLevel = (int) getLevelFromExp((long) (0.75 * getExpFromLevel(totalLevel)));
        if (finalLevel > 0) {
            lore.add(Component.text("Apply Cost: ", NamedTextColor.GRAY).append(Component.text(finalLevel + " Exp Levels", NamedTextColor.DARK_AQUA)).decoration(TextDecoration.ITALIC, false));
        }
        lore.add(Component.text(info.getRarity().getName() + " Enchanted Book", info.getRarity().getRarityColor()).decoration(TextDecoration.ITALIC, false));
        item.getItemMeta().lore(lore);
    }

    public static ItemStack createGeneral(EnchantmentType enchant) {
        ItemStack book = createEnchantedBook(enchant);
        ItemMeta meta = book.getItemMeta();
        NamedTextColor titleColor = NamedTextColor.GREEN;
        if (enchant.isUltimate()) {
            titleColor = NamedTextColor.LIGHT_PURPLE;
        }
        meta.displayName(Component.text(enchant.getName(), titleColor).decoration(TextDecoration.ITALIC, false));
        meta.lore(enchant.getEnchantLore(enchant.getMinLevel()));
        book.setItemMeta(meta);
        return book;
    }

    public static ItemStack createGeneral(EnchantmentType type, int level) {
        ItemStack book = createEnchantedBook(type, level);
        EnchantmentObject temp = type.createObject(level);
        ItemMeta meta = book.getItemMeta();
        meta.displayName(Component.text(temp.getText()).decoration(TextDecoration.ITALIC, false));
        ArrayList<Component> lore = type.getEnchantLore(level);
        lore.add(Component.empty());
        if (lore.addAll(type.getConflictLore())) {
            lore.add(Component.empty());
        }
        int levelNeed = type.getExpLevelsNeeded(level);
        if (levelNeed > 0) {
            lore.add(Component.text("Apply Cost: ", NamedTextColor.GRAY).append(Component.text(levelNeed + " Exp Levels", NamedTextColor.DARK_AQUA)).decoration(TextDecoration.ITALIC, false));
        }
        meta.lore(lore);
        book.setItemMeta(meta);
        return book;
    }

    private static int getExpFromLevel(int level) {
        if (level > 30) {
            return (int) (4.5 * level * level - 162.5 * level + 2220);
        }
        if (level > 15) {
            return (int) (2.5 * level * level - 40.5 * level + 360);
        }
        return level * level + 6 * level;
    }

    private static double getLevelFromExp(long exp) {
        if (exp > 1395) {
            return (Math.sqrt(72 * exp - 54215) + 325) / 18;
        }
        if (exp > 315) {
            return Math.sqrt(40 * exp - 7839) / 10 + 8.1;
        }
        if (exp > 0) {
            return Math.sqrt(exp + 9) - 3;
        }
        return 0;
    }

}