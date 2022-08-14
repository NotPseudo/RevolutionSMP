package me.notpseudo.revolutionsmp.menus;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

public class MenuItem implements Serializable {

    private final MenuType TYPE;
    private final MenuAction ACTION;

    public MenuItem() {
        TYPE = null;
        ACTION = null;
    }

    public MenuItem(MenuType type) {
        this.TYPE = type;
        ACTION = null;
    }

    public MenuItem(MenuAction action) {
        ACTION = action;
        TYPE = null;
    }

    @Nullable
    public MenuType getType() {
        return TYPE;
    }

    @Nullable
    public MenuAction getAction() {
        return ACTION;
    }

}