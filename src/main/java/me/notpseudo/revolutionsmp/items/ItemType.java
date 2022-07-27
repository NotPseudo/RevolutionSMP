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
        @Override
        public boolean allowEnchants() {
            return false;
        }

        @Override
        public boolean allowAbilities() {
            return false;
        }
    },
    WAND {

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