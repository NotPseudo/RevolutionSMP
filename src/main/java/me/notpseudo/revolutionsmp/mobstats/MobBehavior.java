package me.notpseudo.revolutionsmp.mobstats;

import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;

public enum MobBehavior {

    PASSIVE {
        @Override
        public ChatColor getBehaviorColor() {
            return ChatColor.GREEN;
        }
        @Override
        public int getLowestLevel() {
            return 1;
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
        public int getLowestLevel() {
            return 1;
        }
        @Override
        public int getHighestLevel() {
            return 40;
        }
    },
    HOSTILE {
        @Override
        public ChatColor getBehaviorColor() {
            return ChatColor.RED;
        }
        @Override
        public int getLowestLevel() {
            return 1;
        }
        @Override
        public int getHighestLevel() {
            return 40;
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
    };

    public abstract ChatColor getBehaviorColor();
    public abstract int getLowestLevel();
    public abstract int getHighestLevel();

}
