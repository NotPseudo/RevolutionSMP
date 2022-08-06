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
    SHOVEL {

    },
    FISHING_ROD {

    },
    FISHING_WEAPON {

    },
    ITEM {
        @Override
        public boolean allowReforge() {
            return false;
        }
    },
    ACCESSORY {

    },
    VANILLA_ITEM {

    },
    WAND {

    },
    DEPLOYABLE {

    },
    DRILL {

    },
    GAUNTLET {

    },
    LONGSWORD {

    };

    public boolean allowEnchants() {
        return true;
    }

    public boolean allowAbilities() {
        return true;
    }

    public boolean allowReforge() {
        return true;
    }

}