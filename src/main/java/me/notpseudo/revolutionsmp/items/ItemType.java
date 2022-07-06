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

    };

    public boolean allowEnchants() {
        return true;
    }

    public boolean allowAbilities() {
        return true;
    }

    }