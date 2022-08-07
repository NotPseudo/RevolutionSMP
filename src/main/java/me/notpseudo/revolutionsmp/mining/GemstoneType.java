package me.notpseudo.revolutionsmp.mining;

import me.notpseudo.revolutionsmp.itemstats.StatType;
import org.bukkit.ChatColor;

public enum GemstoneType {

    RUBY {
        @Override
        public StatType getStat() {
            return StatType.HEALTH;
        }
        @Override
        public ChatColor getColor() {
            return ChatColor.RED;
        }
    },
    AMBER {
        @Override
        public StatType getStat() {
            return StatType.MINING_SPEED;
        }
        @Override
        public ChatColor getColor() {
            return ChatColor.GOLD;
        }
    },
    AMETHYST {
        @Override
        public StatType getStat() {
            return StatType.DEFENSE;
        }
        @Override
        public ChatColor getColor() {
            return ChatColor.DARK_PURPLE;
        }
    },
    JADE {
        @Override
        public StatType getStat() {
            return StatType.MINING_FORTUNE;
        }
        @Override
        public ChatColor getColor() {
            return ChatColor.GREEN;
        }
    },
    OPAL {
        @Override
        public StatType getStat() {
            return StatType.TRUE_DEFENSE;
        }

        @Override
        public ChatColor getColor() {
            return ChatColor.WHITE;
        }
    },
    PEARL {
        @Override
        public StatType getStat() {
            return StatType.PURITY;
        }

        @Override
        public ChatColor getColor() {
            return ChatColor.WHITE;
        }
    },
    SAPPHIRE {
        @Override
        public StatType getStat() {
            return StatType.INTELLIGENCE;
        }

        @Override
        public ChatColor getColor() {
            return ChatColor.AQUA;
        }
    },
    TOPAZ {
        @Override
        public StatType getStat() {
            return StatType.PRISTINE;
        }

        @Override
        public ChatColor getColor() {
            return ChatColor.YELLOW;
        }
    },
    JASPER {
        @Override
        public StatType getStat() {
            return StatType.STRENGTH;
        }

        @Override
        public ChatColor getColor() {
            return ChatColor.LIGHT_PURPLE;
        }
    };

    public abstract StatType getStat();

    public abstract ChatColor getColor();

}