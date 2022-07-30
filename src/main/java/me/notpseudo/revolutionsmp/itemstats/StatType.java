package me.notpseudo.revolutionsmp.itemstats;

public enum StatType {

    DAMAGE,
    STRENGTH,
    CRIT_CHANCE {
        @Override
        public boolean isPercentage() {
            return true;
        }
    },
    CRIT_DAMAGE {
        @Override
        public boolean isPercentage() {
            return true;
        }
    },
    ATTACK_SPEED {
        @Override
        public boolean isPercentage() {
            return true;
        }
    },
    FEROCITY,
    HEALTH,
    DEFENSE,
    TRUE_DEFENSE,
    SPEED,
    INTELLIGENCE,
    MANA,
    ABILITY_DAMAGE {
        @Override
        public boolean isPercentage() {
            return true;
        }
    },
    SEA_CREATURE_CHANCE {
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
    MINING_SPEED,
    MINING_FORTUNE,
    PRISTINE,
    FARMING_FORTUNE,
    FORAGING_FORTUNE,
    MAGIC_FIND,
    PET_LUCK;

    public boolean isPercentage() {
        return false;
    }

}