package me.notpseudo.revolutionsmp.mobstats;

import org.bukkit.ChatColor;

public enum MobCategory {

    PASSIVE {
        @Override
        public ChatColor getBehaviorColor() {
            return ChatColor.GREEN;
        }
        @Override
        public int getHighestLevel() {
            return 15;
        }
    },
    TAMED {
        @Override
        public ChatColor getBehaviorColor() {
            return ChatColor.GREEN;
        }
        @Override
        public int getLowestLevel() {
            return 25;
        }
        @Override
        public int getHighestLevel() {
            return 40;
        }
    },
    NEUTRAL {
        @Override
        public ChatColor getBehaviorColor() {
            return ChatColor.YELLOW;
        }
        @Override
        public int getHighestLevel() {
            return 40;
        }

        @Override
        public double getExpMult() {
            return 1.5;
        }
    },
    HOSTILE {
        @Override
        public ChatColor getBehaviorColor() {
            return ChatColor.RED;
        }
        @Override
        public int getHighestLevel() {
            return 40;
        }

        @Override
        public double getExpMult() {
            return 2;
        }
    },
    BOSS {
        @Override
        public ChatColor getBehaviorColor() {
            return ChatColor.RED;
        }
        @Override
        public int getLowestLevel() {
            return 50;
        }
        @Override
        public int getHighestLevel() {
            return 100;
        }

        @Override
        public double getExpMult() {
            return 3;
        }
    },
    SEA_CREATURE {
        @Override
        public ChatColor getBehaviorColor() {
            return ChatColor.RED;
        }

        @Override
        public int getHighestLevel() {
            return 0;
        }
    };

    public abstract ChatColor getBehaviorColor();
    public int getLowestLevel() {
        return 1;
    }
    public int getHighestLevel() {
        return 25;
    }

    public double getExpMult() {
        return 1;
    }

}
