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
        @Override
        public boolean allowModifiers() {
            return false;
        }

    },
    PICKAXE {
    },
    HOE {

        @Override
        public boolean allowAbilities() {
            return false;
        }
    },
    SHOVEL {
        @Override
        public boolean allowModifiers() {
            return false;
        }

        @Override
        public boolean allowAbilities() {
            return false;
        }
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

        @Override
        public boolean allowAbilities() {
            return false;
        }

        @Override
        public boolean allowModifiers() {
            return false;
        }

        @Override
        public boolean allowRecomb() {
            return false;
        }

        public boolean canInteract() {
            return false;
        }
    },
    ACCESSORY {
        @Override
        public boolean allowEnchants() {
            return false;
        }

        @Override
        public boolean allowModifiers() {
            return false;
        }
    },
    REFORGE_STONE {
        @Override
        public boolean allowReforge() {
            return false;
        }

        @Override
        public boolean allowAbilities() {
            return false;
        }

        @Override
        public boolean allowEnchants() {
            return false;
        }

        @Override
        public boolean allowModifiers() {
            return false;
        }

        @Override
        public boolean allowAnvil() {
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

        @Override
        public boolean allowModifiers() {
            return false;
        }

        @Override
        public boolean allowAnvil() {
            return false;
        }

        @Override
        public boolean allowRecomb() {
            return false;
        }

        @Override
        public boolean canPlace() {
            return true;
        }
    },
    WAND {
        @Override
        public boolean allowReforge() {
            return false;
        }

        @Override
        public boolean allowModifiers() {
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

    public boolean allowModifiers() {
        return true;
    }

    public boolean allowAnvil() {
        return true;
    }

    public boolean allowRecomb() {
        return true;
    }

    public boolean canInteract() {
        return true;
    }

    public boolean canPlace() {
        return false;
    }

}