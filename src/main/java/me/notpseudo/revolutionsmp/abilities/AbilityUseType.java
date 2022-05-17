package me.notpseudo.revolutionsmp.abilities;

// Enum of how an Ability can be activated
public enum AbilityUseType {
    RIGHT_CLICK {
        @Override
        public String getName() {
            return "RIGHT CLICK";
        }
    },
    SNEAK_RIGHT_CLICK {
        @Override
        public String getName() {
            return "SNEAK RIGHT CLICK";
        }
    },
    LEFT_CLICK {
        @Override
        public String getName() {
            return "LEFT CLICK";
        }
    };
    public abstract String getName();
}