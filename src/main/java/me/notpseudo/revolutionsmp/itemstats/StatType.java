package me.notpseudo.revolutionsmp.itemstats;

import net.kyori.adventure.text.format.NamedTextColor;

public enum StatType {

    DAMAGE {
        @Override
        public String getSymbol() {
            return "❁";
        }
    },
    STRENGTH {
        @Override
        public String getSymbol() {
            return "❁";
        }
    },
    CRIT_CHANCE {
        @Override
        public String getSymbol() {
            return "☣";
        }
        @Override
        public NamedTextColor getColor() {
            return NamedTextColor.BLUE;
        }
        @Override
        public boolean isPercentage() {
            return true;
        }
    },
    CRIT_DAMAGE {
        @Override
        public String getSymbol() {
            return "☠";
        }
        @Override
        public NamedTextColor getColor() {
            return NamedTextColor.BLUE;
        }
        @Override
        public boolean isPercentage() {
            return true;
        }
    },
    ATTACK_SPEED {
        @Override
        public String getSymbol() {
            return "⚔";
        }
        @Override
        public NamedTextColor getColor() {
            return NamedTextColor.YELLOW;
        }
        @Override
        public boolean isPercentage() {
            return true;
        }
    },
    FEROCITY {
        @Override
        public String getSymbol() {
            return "⫽";
        }
    },
    HEALTH {
        @Override
        public String getSymbol() {
            return "❤";
        }
    },
    DEFENSE {
        @Override
        public String getSymbol() {
            return "❈";
        }
        @Override
        public NamedTextColor getColor() {
            return NamedTextColor.GREEN;
        }
    },
    TRUE_DEFENSE {
        @Override
        public String getSymbol() {
            return "❂";
        }
        @Override
        public NamedTextColor getColor() {
            return NamedTextColor.WHITE;
        }
    },
    SPEED {
        @Override
        public String getSymbol() {
            return "✦";
        }
        @Override
        public NamedTextColor getColor() {
            return NamedTextColor.WHITE;
        }
    },
    INTELLIGENCE {
        @Override
        public String getSymbol() {
            return "✎";
        }
        @Override
        public NamedTextColor getColor() {
            return NamedTextColor.AQUA;
        }
    },
    MANA {
        @Override
        public String getSymbol() {
            return "✎";
        }
        @Override
        public NamedTextColor getColor() {
            return NamedTextColor.AQUA;
        }
    },
    ABILITY_DAMAGE {
        @Override
        public String getSymbol() {
            return "๑";
        }
        @Override
        public boolean isPercentage() {
            return true;
        }
    },
    SEA_CREATURE_CHANCE {
        @Override
        public String getSymbol() {
            return "α";
        }
        @Override
        public NamedTextColor getColor() {
            return NamedTextColor.DARK_AQUA;
        }
        @Override
        public boolean isPercentage() {
            return true;
        }
    },
    FISHING_SPEED {
        @Override
        public boolean isPercentage() {
            return true;
        }
    },
    MINING_SPEED {
        @Override
        public String getSymbol() {
            return "⸕";
        }
        @Override
        public NamedTextColor getColor() {
            return NamedTextColor.GOLD;
        }
    },
    MINING_FORTUNE {
        @Override
        public String getSymbol() {
            return "☘";
        }
        @Override
        public NamedTextColor getColor() {
            return NamedTextColor.GOLD;
        }
    },
    PRISTINE {
        @Override
        public String getSymbol() {
            return "✧";
        }
        @Override
        public NamedTextColor getColor() {
            return NamedTextColor.DARK_PURPLE;
        }
    },
    FARMING_FORTUNE {
        @Override
        public String getSymbol() {
            return "☘";
        }
        @Override
        public NamedTextColor getColor() {
            return NamedTextColor.GOLD;
        }
    },
    FORAGING_FORTUNE {
        @Override
        public String getSymbol() {
            return "☘";
        }
        @Override
        public NamedTextColor getColor() {
            return NamedTextColor.GOLD;
        }
    },
    MAGIC_FIND {
        @Override
        public String getSymbol() {
            return "✯";
        }
        @Override
        public NamedTextColor getColor() {
            return NamedTextColor.AQUA;
        }
    },
    PET_LUCK {
        @Override
        public String getSymbol() {
            return "♣";
        }
        @Override
        public NamedTextColor getColor() {
            return NamedTextColor.LIGHT_PURPLE;
        }
    };

    public boolean isPercentage() {
        return false;
    }

    public String getSymbol() {
        return "";
    }

    public NamedTextColor getColor() {
        return NamedTextColor.RED;
    }

}