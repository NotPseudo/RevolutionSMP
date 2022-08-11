package me.notpseudo.revolutionsmp.menus;

import java.io.Serializable;

public class MenuItem implements Serializable {

    private final MenuType next;

    public MenuItem() {
        next = null;
    }

    public MenuItem(MenuType next) {
        this.next = next;
    }

    public MenuType getNext() {
        return next;
    }

}