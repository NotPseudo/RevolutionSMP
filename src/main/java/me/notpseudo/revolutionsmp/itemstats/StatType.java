package me.notpseudo.revolutionsmp.itemstats;

import me.notpseudo.revolutionsmp.items.ItemEditor;
import net.kyori.adventure.text.Component;
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

        @Override
        public StatCategory getStatCategory() {
            return StatCategory.ARMOR;
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
        @Override
        public StatCategory getStatCategory() {
            return StatCategory.ARMOR;
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
        @Override
        public StatCategory getStatCategory() {
            return StatCategory.ARMOR;
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
        @Override
        public StatCategory getStatCategory() {
            return StatCategory.ARMOR;
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
        @Override
        public StatCategory getStatCategory() {
            return StatCategory.INTELLIGENCE;
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
        @Override
        public StatCategory getStatCategory() {
            return StatCategory.INTELLIGENCE;
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
        @Override
        public StatCategory getStatCategory() {
            return StatCategory.INTELLIGENCE;
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
        @Override
        public StatCategory getStatCategory() {
            return StatCategory.FISHING;
        }
    },
    FISHING_SPEED {
        @Override
        public boolean isPercentage() {
            return true;
        }

        @Override
        public String getSymbol() {
            return "☂";
        }

        @Override
        public StatCategory getStatCategory() {
            return StatCategory.FISHING;
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
        @Override
        public StatCategory getStatCategory() {
            return StatCategory.MINING;
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
        @Override
        public StatCategory getStatCategory() {
            return StatCategory.MINING;
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
        @Override
        public StatCategory getStatCategory() {
            return StatCategory.MINING;
        }
    },
    PURITY {
        @Override
        public String getSymbol() {
            return "⥉";
        }
        @Override
        public NamedTextColor getColor() {
            return NamedTextColor.WHITE;
        }
        @Override
        public StatCategory getStatCategory() {
            return StatCategory.MINING;
        }
    },
    BREAKING_POWER {
        @Override
        public StatCategory getStatCategory() {
            return StatCategory.MINING;
        }

        @Override
        public String getSymbol() {
            return "Ⓟ";
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
        @Override
        public StatCategory getStatCategory() {
            return StatCategory.GATHERING;
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
        @Override
        public StatCategory getStatCategory() {
            return StatCategory.GATHERING;
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
        @Override
        public StatCategory getStatCategory() {
            return StatCategory.LUCK;
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
        @Override
        public StatCategory getStatCategory() {
            return StatCategory.LUCK;
        }
    },
    COMBAT_WISDOM {
        @Override
        public String getSymbol() {
            return "☯";
        }

        @Override
        public NamedTextColor getColor() {
            return NamedTextColor.DARK_AQUA;
        }

        @Override
        public StatCategory getStatCategory() {
            return StatCategory.WISDOM;
        }
    },
    MINING_WISDOM {
        @Override
        public String getSymbol() {
            return "☯";
        }

        @Override
        public NamedTextColor getColor() {
            return NamedTextColor.DARK_AQUA;
        }

        @Override
        public StatCategory getStatCategory() {
            return StatCategory.WISDOM;
        }
    },
    FORAGING_WISDOM {
        @Override
        public String getSymbol() {
            return "☯";
        }

        @Override
        public NamedTextColor getColor() {
            return NamedTextColor.DARK_AQUA;
        }

        @Override
        public StatCategory getStatCategory() {
            return StatCategory.WISDOM;
        }
    },
    FARMING_WISDOM {
        @Override
        public String getSymbol() {
            return "☯";
        }

        @Override
        public NamedTextColor getColor() {
            return NamedTextColor.DARK_AQUA;
        }

        @Override
        public StatCategory getStatCategory() {
            return StatCategory.WISDOM;
        }
    },
    FISHING_WISDOM {
        @Override
        public String getSymbol() {
            return "☯";
        }

        @Override
        public NamedTextColor getColor() {
            return NamedTextColor.DARK_AQUA;
        }

        @Override
        public StatCategory getStatCategory() {
            return StatCategory.WISDOM;
        }
    },
    ALCHEMY_WISDOM {
        @Override
        public String getSymbol() {
            return "☯";
        }

        @Override
        public NamedTextColor getColor() {
            return NamedTextColor.DARK_AQUA;
        }

        @Override
        public StatCategory getStatCategory() {
            return StatCategory.WISDOM;
        }
    },
    ENCHANTING_WISDOM {
        @Override
        public String getSymbol() {
            return "☯";
        }

        @Override
        public NamedTextColor getColor() {
            return NamedTextColor.DARK_AQUA;
        }

        @Override
        public StatCategory getStatCategory() {
            return StatCategory.WISDOM;
        }
    },
    HEALTH_REGEN {
        @Override
        public String getSymbol() {
            return "❣";
        }

        @Override
        public StatCategory getStatCategory() {
            return StatCategory.REGEN;
        }
    },
    VITALITY {
        @Override
        public String getSymbol() {
            return "♨";
        }

        @Override
        public NamedTextColor getColor() {
            return NamedTextColor.DARK_RED;
        }

        @Override
        public StatCategory getStatCategory() {
            return StatCategory.REGEN;
        }
    },
    MENDING {
        @Override
        public String getSymbol() {
            return "☄";
        }

        @Override
        public NamedTextColor getColor() {
            return NamedTextColor.GREEN;
        }

        @Override
        public StatCategory getStatCategory() {
            return StatCategory.REGEN;
        }
    },
    MANA_REGEN {
        @Override
        public String getSymbol() {
            return "✒";
        }

        @Override
        public NamedTextColor getColor() {
            return NamedTextColor.AQUA;
        }

        @Override
        public StatCategory getStatCategory() {
            return StatCategory.REGEN;
        }
    }
    ;

    public String getName() {
        return ItemEditor.getStringFromEnum(this);
    }

    public Component getNameWithSymbol() {
        return Component.text(getSymbol() + " " + getName() + " ", getColor());
    }

    public boolean isPercentage() {
        return false;
    }

    public String getSymbol() {
        return "";
    }

    public NamedTextColor getColor() {
        return NamedTextColor.RED;
    }

    public StatCategory getStatCategory() {
        return StatCategory.COMBAT;
    }

}