package me.notpseudo.revolutionsmp.abilities;

import me.notpseudo.revolutionsmp.items.ItemEditor;

// Enum of how an Ability can be activated
public enum AbilityUseType {
    RIGHT_CLICK {

    },
    SNEAK_RIGHT_CLICK {

    },
    LEFT_CLICK {

    };
    public String toString() {
        return ItemEditor.getStringFromEnum(this);
    }
}