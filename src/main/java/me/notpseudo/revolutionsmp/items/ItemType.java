package me.notpseudo.revolutionsmp.items;

// Enum of ItemTypes a custom item can be
public enum ItemType {
    HELMET {

    },
    CHESTPLATE {

    },
    LEGGINGS {

    },
    BOOTS {

    },
    SWORD {

    },
    BOW {

    },
    AXE {

    },
    PICKAXE {

    },
    HOE {

    },
    FISHING_ROD {

    },
    FISHING_WEAPON {

    },
    ITEM {

    },
    WAND {

    },
    DEPLOYABLE {

    },
    DRILL {

    },
    LONGSWORD {

    };

    public boolean allowEnchants() {
        return true;
    }

    public boolean allowAbilities() {
        return true;
    }

}