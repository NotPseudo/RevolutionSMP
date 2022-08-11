package me.notpseudo.revolutionsmp.menus;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import org.bukkit.NamespacedKey;

public class MenuUtils {

    private static final NamespacedKey menuKey = new NamespacedKey(RevolutionSMP.getPlugin(RevolutionSMP.class), "menuKey");

    public static NamespacedKey getMenuKey() {
        return menuKey;
    }

}