package me.notpseudo.revolutionsmp.menus;

import java.io.Serializable;

public class MenuItem implements Serializable {

    private final MenuType TYPE;

    public MenuItem() {
        TYPE = null;
    }

    public MenuItem(MenuType type) {
        this.TYPE = type;
    }

    public MenuType getType() {
        return TYPE;
    }

}