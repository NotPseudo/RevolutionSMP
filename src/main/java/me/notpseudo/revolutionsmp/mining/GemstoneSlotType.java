package me.notpseudo.revolutionsmp.mining;

public enum GemstoneSlotType {

    RUBY {
        @Override
        public String getSymbol() {
            return "❤";
        }
    },
    AMBER {
        @Override
        public String getSymbol() {
            return "⸕";
        }
    },
    AMETHYST {
        @Override
        public String getSymbol() {
            return "❈";
        }
    },
    JADE {
        @Override
        public String getSymbol() {
            return "☘";
        }
    },
    OPAL {
        @Override
        public String getSymbol() {
            return "❂";
        }
    },
    SAPPHIRE {
        @Override
        public String getSymbol() {
            return "✎";
        }
    },
    TOPAZ {
        @Override
        public String getSymbol() {
            return "✧";
        }
    },
    JASPER {
        @Override
        public String getSymbol() {
            return "❁";
        }
    },
    OFFENSIVE {
        @Override
        public GemstoneType[] getAllowedGemstones() {
            return new GemstoneType[] {GemstoneType.JASPER, GemstoneType.SAPPHIRE};
        }

        @Override
        public String getSymbol() {
            return "☠";
        }
    },
    DEFENSIVE {
        @Override
        public GemstoneType[] getAllowedGemstones() {
            return new GemstoneType[] {GemstoneType.RUBY, GemstoneType.AMETHYST};
        }

        @Override
        public String getSymbol() {
            return "☤";
        }
    },
    COMBAT {
        @Override
        public GemstoneType[] getAllowedGemstones() {
            return new GemstoneType[] {GemstoneType.JASPER, GemstoneType.SAPPHIRE, GemstoneType.RUBY, GemstoneType.AMETHYST};
        }

        @Override
        public String getSymbol() {
            return "⚔";
        }
    },
    MINING {
        @Override
        public GemstoneType[] getAllowedGemstones() {
            return new GemstoneType[] {GemstoneType.JADE, GemstoneType.AMBER, GemstoneType.TOPAZ};
        }

        @Override
        public String getSymbol() {
            return "✦";
        }
    },
    UNIVERSAL {
        @Override
        public GemstoneType[] getAllowedGemstones() {
            return GemstoneType.values();
        }

        @Override
        public String getSymbol() {
            return "❂";
        }
    };

    public GemstoneType[] getAllowedGemstones() {
        return new GemstoneType[]{GemstoneType.valueOf(this.toString())};
    }

    public abstract String getSymbol();

    public boolean allows(GemstoneType type) {
        for (GemstoneType allowed : getAllowedGemstones()) {
            if (allowed == type) {
                return true;
            }
        }
        return false;
    }

}