package me.notpseudo.revolutionsmp.menus;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public class MenuUtils {

    private static final NamespacedKey menuKey = new NamespacedKey(RevolutionSMP.getPlugin(RevolutionSMP.class), "menuKey");

    public static NamespacedKey getMenuKey() {
        return menuKey;
    }

    @Nullable
    public static MenuItem getMenuInfo(ItemStack item) {
        if (item == null || item.getType() == Material.AIR) {
            return null;
        }
        return item.getItemMeta().getPersistentDataContainer().get(menuKey, new MenuItemDataType());
    }

}