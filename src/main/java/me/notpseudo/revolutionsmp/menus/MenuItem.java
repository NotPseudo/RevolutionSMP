package me.notpseudo.revolutionsmp.menus;

import me.notpseudo.revolutionsmp.enchantments.EnchantmentObject;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

public class MenuItem implements Serializable {

    private final MenuType TYPE;
    private final MenuAction ACTION;

    private final EnchantmentObject ENCHANT;

    private final Menu NEXT;

    public MenuItem() {
        TYPE = null;
        ACTION = null;
        ENCHANT = null;
        NEXT = null;
    }

    public MenuItem(MenuType type) {
        TYPE = type;
        ACTION = null;
        ENCHANT = null;
        NEXT = null;
    }

    public MenuItem(MenuAction action) {
        ACTION = action;
        TYPE = null;
        ENCHANT = null;
        NEXT = null;
    }

    public MenuItem(EnchantmentObject enchant, MenuAction action) {
        ENCHANT = enchant;
        ACTION = action;
        TYPE = null;
        NEXT = null;
    }

    public MenuItem(Menu next) {
        NEXT = next;
        ACTION = null;
        TYPE = null;
        ENCHANT = null;
    }

    @Nullable
    public MenuType getType() {
        return TYPE;
    }

    @Nullable
    public MenuAction getAction() {
        return ACTION;
    }

    @Nullable
    public EnchantmentObject getEnchant() {
        return ENCHANT;
    }

    @Nullable
    public Menu getNext(Player player) {
        if (NEXT == null && TYPE != null && TYPE.meetsRequirement(player)) {
            return TYPE.getNext(player);
        } else {
            return NEXT;
        }
    }

}