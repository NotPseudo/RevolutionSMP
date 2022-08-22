package me.notpseudo.revolutionsmp.slayer;

import me.notpseudo.revolutionsmp.items.ItemEditor;

public enum SlayerType {

    ZOMBIE {
        @Override
        public String getName(int tier) {
            if (tier == 5) {
                return "Atoned Horror";
            }
            return "Revenant Horror";
        }
    },
    SPIDER {
        @Override
        public String getName(int tier) {
            return "Tarantula Broodfather";
        }
    },
    WOLF {
        @Override
        public String getName(int tier) {
            return "Sven Packmaster";
        }
    },
    ENDERMAN {
        @Override
        public String getName(int tier) {
            return "Voidgloom Seraph";
        }
    },
    BLAZE {
        @Override
        public String getName(int tier) {
            return "Inferno Demonlord";
        }
    },
    SCULK {
        @Override
        public String getName(int tier) {
            return "Nightmare Executioner";
        }
    };

    public String getName(int tier) {
        return ItemEditor.getStringFromEnum(this);
    }

}