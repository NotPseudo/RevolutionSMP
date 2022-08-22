package me.notpseudo.revolutionsmp.customcrafting.items;

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

        @Override
        public boolean allowEnchants() {
            return false;
        }
    },
    ACCESSORY {
        @Override
        public boolean allowEnchants() {
            return false;
        }
    },
    REFORGE_STONE {
        @Override
        public boolean allowReforge() {
            return false;
        }

        @Override
        public boolean allowEnchants() {
            return false;
        }
    },
    VANILLA_ITEM {
        @Override
        public boolean allowReforge() {
            return false;
        }

        @Override
        public boolean allowEnchants() {
            return false;
        }
    },
    WAND {
        @Override
        public boolean allowReforge() {
            return false;
        }
    },
    DEPLOYABLE {
        @Override
        public boolean allowReforge() {
            return false;
        }

        @Override
        public boolean allowEnchants() {
            return false;
        }
    },
    DRILL {

    },
    GAUNTLET {

    },
    LONGSWORD {

    };

    public String getName() {
        return ItemEditor.getStringFromEnum(this);
    }

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