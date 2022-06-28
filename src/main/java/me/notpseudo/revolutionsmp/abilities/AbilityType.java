package me.notpseudo.revolutionsmp.abilities;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

// Enum of abilities that can be placed on items
public enum AbilityType {
    INSTANT_TRANSMISSION {
        @Override
        public List<Component> getAbilityInfo() {
            return null;
        }

        @Override
        public double getManaCost() {
            return 100;
        }

        @Override
        public AbilityObject createObject() {
            return new InstantTransmissionObject();
        }
    },
    WITHER_IMPACT {
        @Override
        public void use(Player player) {

        }

        @Override
        public double getManaCost() {
            return 300;
        }

        @Override
        public double getBaseAbilityDamage() {
            return 10000;
        }

        @Override
        public double getAbilityScaling() {
            return 0.3;
        }


    },
    IMPLOSION {
        @Override
        public void use(Player player) {

        }

        @Override
        public double getManaCost() {
            return 300;
        }

        @Override
        public double getBaseAbilityDamage() {
            return 10000;
        }

        @Override
        public double getAbilityScaling() {
            return 0.3;
        }
    },
    WITHER_SHIELD {
        @Override
        public void use(Player player) {

        }

        @Override
        public double getManaCost() {
            return 300;
        }

        @Override
        public double getBaseAbilityDamage() {
            return 10000;
        }

        @Override
        public double getAbilityScaling() {
            return 0.3;
        }

    },
    SHADOW_WARP {
        @Override
        public void use(Player player) {

        }

        @Override
        public double getManaCost() {
            return 300;
        }

        @Override
        public double getBaseAbilityDamage() {
            return 10000;
        }

        @Override
        public double getAbilityScaling() {
            return 0.3;
        }

    };

    public void use(Player player) {

    }

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

    public double getManaCost() {
        return 0;
    }

    public double getBaseAbilityDamage() {
        return 0;
    }

    public double getAbilityScaling() {
        return 0;
    }

    public ArrayList<Component> getDescription() {
        return new ArrayList<>();
    }

    public AbilityUseType getAbilityUseType() {
        return AbilityUseType.RIGHT_CLICK;
    }

    public AbilityObject createObject() {
        return new AbilityObject(valueOf(name()));
    }

    public List<Component> getAbilityInfo() {
        return new ArrayList<>();
    }
}