package me.notpseudo.revolutionsmp.menus;

import org.bukkit.entity.Player;

public enum MenuType {

    MAIN,
    PROFILE,
    SKILLS,
    COLLECTIONS,
    FARMING_COLLECTIONS,
    MINING_COLLECTIONS,
    COMBAT_COLLECTIONS,
    FORAGING_COLLECTIONS,
    FISHING_COLLECTIONS,
    BUILDER,
    ENCHANT,
    BANK,
    REFORGE,
    ADVANCED_REFORGE,
    SLAYER,
    CLOSE;

    public Menu getNext(Player player) {
        return null;
    }

    public boolean meetsRequirement(Player player) {
        return true;
    }

}