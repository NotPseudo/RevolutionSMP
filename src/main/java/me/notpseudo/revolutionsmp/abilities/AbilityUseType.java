package me.notpseudo.revolutionsmp.abilities;

// Enum of how an Ability can be activated
public enum AbilityUseType {
    RIGHT_CLICK {

    },
    SNEAK_RIGHT_CLICK {

    },
    LEFT_CLICK {

    };
    public String toString() {
        String[] split = super.toString().split("_");
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            name.append(split[i].charAt(0)).append(split[i].substring(1).toLowerCase());
            if (i < split.length - 1) {
                name.append(" ");
            }
        }
        return name.toString();
    }
}