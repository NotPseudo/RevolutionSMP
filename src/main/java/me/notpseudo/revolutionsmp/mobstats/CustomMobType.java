package me.notpseudo.revolutionsmp.mobstats;

import org.bukkit.entity.EntityType;

public enum CustomMobType {

    AXOLOTL {

    },
    BAT {

    },
    BEE {
        @Override
        public double getDefense() {
            return 20;
        }

        @Override
        public double getDamage() {
            return 10;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.NEUTRAL;
        }

    },
    BLAZE {
        @Override
        public double getHealth() {
            return 100;
        }

        @Override
        public double getDefense() {
            return 15;
        }

        @Override
        public double getDamage() {
            return 30;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.HOSTILE;
        }

    },
    CAT {

    },
    CAVE_SPIDER {
        @Override
        public double getHealth() {
            return 50;
        }

        @Override
        public double getDefense() {
            return 10;
        }

        @Override
        public double getDamage() {
            return 10;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.NEUTRAL;
        }

    },
    CHICKEN {

    },
    COD {

    },
    COW {

    },
    CREEPER {
        @Override
        public double getHealth() {
            return 100;
        }

        @Override
        public double getDefense() {
            return 10;
        }

        @Override
        public double getDamage() {
            return 100;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.HOSTILE;
        }

    },
    DOLPHIN {
        @Override
        public double getDefense() {
            return 15;
        }

        @Override
        public double getDamage() {
            return 15;
        }

        @Override
        public double getStrength() {
            return 10;
        }

        @Override
        public double getCritChance() {
            return 30;
        }

        @Override
        public double getCritDamage() {
            return 50;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.NEUTRAL;
        }

    },
    DONKEY {

    },
    DROWNED {
        @Override
        public double getHealth() {
            return 100;
        }

        @Override
        public double getDefense() {
            return 20;
        }

        @Override
        public double getDamage() {
            return 20;
        }

        @Override
        public double getStrength() {
            return 10;
        }

        @Override
        public double getCritChance() {
            return 30;
        }

        @Override
        public double getCritDamage() {
            return 50;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.HOSTILE;
        }

    },
    ELDER_GUARDIAN {
        @Override
        public double getHealth() {
            return 400;
        }

        @Override
        public double getDefense() {
            return 50;
        }

        @Override
        public double getDamage() {
            return 40;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.HOSTILE;
        }
    },
    ENDER_DRAGON {
        @Override
        public double getHealth() {
            return 1500;
        }

        @Override
        public double getDefense() {
            return 300;
        }

        @Override
        public double getSpeed() {
            return 20;
        }

        @Override
        public double getDamage() {
            return 90;
        }

        @Override
        public double getStrength() {
            return 100;
        }

        @Override
        public double getCritChance() {
            return 30;
        }

        @Override
        public double getCritDamage() {
            return 75;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.BOSS;
        }
    },
    ENDERMAN {
        @Override
        public double getHealth() {
            return 200;
        }

        @Override
        public double getDefense() {
            return 20;
        }

        @Override
        public double getSpeed() {
            return 5;
        }

        @Override
        public double getDamage() {
            return 35;
        }

        @Override
        public double getStrength() {
            return 20;
        }

        @Override
        public double getCritChance() {
            return 50;
        }

        @Override
        public double getCritDamage() {
            return 75;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.NEUTRAL;
        }
    },
    ENDERMITE {
        @Override
        public double getDefense() {
            return 10;
        }

        @Override
        public double getDamage() {
            return 10;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.HOSTILE;
        }


    },
    EVOKER {
        @Override
        public double getHealth() {
            return 120;
        }

        @Override
        public double getDefense() {
            return 10;
        }

        @Override
        public double getDamage() {
            return 30;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.HOSTILE;
        }

    },
    FOX {

    },
    GHAST {
        @Override
        public double getHealth() {
            return 100;
        }

        @Override
        public double getDefense() {
            return 5;
        }

        @Override
        public double getDamage() {
            return 60;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.HOSTILE;
        }

    },
    GIANT {
        @Override
        public double getHealth() {
            return 750;
        }

        @Override
        public double getDefense() {
            return 300;
        }

        @Override
        public double getDamage() {
            return 250;
        }

        @Override
        public double getStrength() {
            return 20;
        }

        @Override
        public double getCritChance() {
            return 30;
        }

        @Override
        public double getCritDamage() {
            return 50;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.HOSTILE;
        }

    },
    GLOW_SQUID {
    },
    GOAT {
        @Override
        public double getDefense() {
            return 5;
        }

        @Override
        public double getDamage() {
            return 10;
        }

        @Override
        public double getStrength() {
            return 10;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.NEUTRAL;
        }

    },
    GUARDIAN {
        @Override
        public double getHealth() {
            return 120;
        }

        @Override
        public double getDefense() {
            return 10;
        }

        @Override
        public double getDamage() {
            return 30;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.HOSTILE;
        }

    },
    HOGLIN {
        @Override
        public double getHealth() {
            return 200;
        }

        @Override
        public double getDefense() {
            return 5;
        }

        @Override
        public double getDamage() {
            return 30;
        }

        @Override
        public double getStrength() {
            return 20;
        }

        @Override
        public double getCritChance() {
            return 30;
        }

        @Override
        public double getCritDamage() {
            return 50;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.HOSTILE;
        }


    },
    HORSE {

    },
    HUSK {
        @Override
        public double getHealth() {
            return 100;
        }

        @Override
        public double getDefense() {
            return 20;
        }

        @Override
        public double getDamage() {
            return 20;
        }

        @Override
        public double getStrength() {
            return 10;
        }

        @Override
        public double getCritChance() {
            return 30;
        }

        @Override
        public double getCritDamage() {
            return 50;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.HOSTILE;
        }

    },
    ILLUSIONER {
        @Override
        public double getHealth() {
            return 150;
        }

        @Override
        public double getDefense() {
            return 10;
        }

        @Override
        public double getDamage() {
            return 25;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.HOSTILE;
        }

    },
    IRON_GOLEM {
        @Override
        public double getHealth() {
            return 500;
        }

        @Override
        public double getDefense() {
            return 200;
        }

        @Override
        public double getDamage() {
            return 75;
        }

        @Override
        public double getStrength() {
            return 40;
        }

        @Override
        public double getCritChance() {
            return 30;
        }

        @Override
        public double getCritDamage() {
            return 75;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.NEUTRAL;
        }


    },
    LLAMA {
        @Override
        public double getHealth() {
            return 80;
        }

        @Override
        public double getDefense() {
            return 5;
        }

        @Override
        public double getDamage() {
            return 5;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.NEUTRAL;
        }


    },
    MAGMA_CUBE {
        @Override
        public double getHealth() {
            return 80;
        }

        @Override
        public double getDefense() {
            return 10;
        }

        @Override
        public double getDamage() {
            return 30;
        }

        @Override
        public double getStrength() {
            return 5;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.HOSTILE;
        }


    },
    MULE {

    },
    MUSHROOM_COW {


    },
    OCELOT {

    },
    PANDA {
        @Override
        public double getHealth() {
            return 100;
        }

        @Override
        public double getDefense() {
            return 20;
        }

        @Override
        public double getDamage() {
            return 30;
        }

        @Override
        public double getStrength() {
            return 10;
        }

        @Override
        public double getCritChance() {
            return 30;
        }

        @Override
        public double getCritDamage() {
            return 50;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.NEUTRAL;
        }
    },
    PARROT {

    },
    PHANTOM {
        @Override
        public double getHealth() {
            return 80;
        }

        @Override
        public double getDefense() {
            return 5;
        }

        @Override
        public double getDamage() {
            return 10;
        }

        @Override
        public double getCritChance() {
            return 30;
        }

        @Override
        public double getCritDamage() {
            return 50;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.HOSTILE;
        }

    },
    PIG {


    },
    PIGLIN {
        @Override
        public double getHealth() {
            return 80;
        }

        @Override
        public double getDefense() {
            return 10;
        }

        @Override
        public double getDamage() {
            return 40;
        }

        @Override
        public double getStrength() {
            return 10;
        }

        @Override
        public double getCritChance() {
            return 30;
        }

        @Override
        public double getCritDamage() {
            return 40;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.NEUTRAL;
        }
    },
    PIGLIN_BRUTE {
        @Override
        public double getHealth() {
            return 250;
        }

        @Override
        public double getDefense() {
            return 50;
        }

        @Override
        public double getSpeed() {
            return 10;
        }

        @Override
        public double getDamage() {
            return 65;
        }

        @Override
        public double getStrength() {
            return 30;
        }

        @Override
        public double getCritChance() {
            return 50;
        }

        @Override
        public double getCritDamage() {
            return 100;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.HOSTILE;
        }

    },
    PILLAGER {
        @Override
        public double getHealth() {
            return 120;
        }

        @Override
        public double getDefense() {
            return 10;
        }

        @Override
        public double getSpeed() {
            return 5;
        }

        @Override
        public double getDamage() {
            return 20;
        }

        @Override
        public double getCritChance() {
            return 40;
        }

        @Override
        public double getCritDamage() {
            return 60;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.HOSTILE;
        }


    },
    POLAR_BEAR {
        @Override
        public double getHealth() {
            return 150;
        }

        @Override
        public double getDefense() {
            return 15;
        }

        @Override
        public double getDamage() {
            return 25;
        }

        @Override
        public double getStrength() {
            return 15;
        }

        @Override
        public double getCritChance() {
            return 30;
        }

        @Override
        public double getCritDamage() {
            return 50;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.NEUTRAL;
        }

    },
    PUFFERFISH {

    },
    RABBIT {


    },
    RAVAGER {
        @Override
        public double getHealth() {
            return 500;
        }

        @Override
        public double getDefense() {
            return 150;
        }

        @Override
        public double getDamage() {
            return 60;
        }

        @Override
        public double getStrength() {
            return 25;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.HOSTILE;
        }


    },
    SALMON {


    },
    SHEEP {

    },
    SHULKER {
        @Override
        public double getHealth() {
            return 150;
        }

        @Override
        public double getDefense() {
            return 40;
        }

        @Override
        public double getDamage() {
            return 20;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.HOSTILE;
        }

    },
    SILVERFISH {
        @Override
        public double getDefense() {
            return 5;
        }

        @Override
        public double getSpeed() {
            return 10;
        }

        @Override
        public double getDamage() {
            return 5;
        }

        @Override
        public double getCritChance() {
            return 30;
        }

        @Override
        public double getCritDamage() {
            return 50;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.HOSTILE;
        }

    },
    SKELETON {
        @Override
        public double getHealth() {
            return 100;
        }

        @Override
        public double getDefense() {
            return 20;
        }

        @Override
        public double getSpeed() {
            return 10;
        }

        @Override
        public double getDamage() {
            return 20;
        }

        @Override
        public double getStrength() {
            return 10;
        }

        @Override
        public double getCritChance() {
            return 50;
        }

        @Override
        public double getCritDamage() {
            return 70;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.HOSTILE;
        }


    },
    SKELETON_HORSE {

    },
    SLIME {
        @Override
        public double getHealth() {
            return 80;
        }

        @Override
        public double getDefense() {
            return 5;
        }

        @Override
        public double getDamage() {
            return 20;
        }

        @Override
        public double getStrength() {
            return 5;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.HOSTILE;
        }

    },
    SNOWMAN {
        @Override
        public double getSpeed() {
            return 10;
        }

        @Override
        public double getDamage() {
            return 15;
        }

        @Override
        public double getCritChance() {
            return 50;
        }

        @Override
        public double getCritDamage() {
            return 75;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.NEUTRAL;
        }

    },
    SPIDER {
        @Override
        public double getHealth() {
            return 100;
        }

        @Override
        public double getDefense() {
            return 20;
        }

        @Override
        public double getSpeed() {
            return 20;
        }

        @Override
        public double getDamage() {
            return 10;
        }

        @Override
        public double getStrength() {
            return 5;
        }

        @Override
        public double getCritChance() {
            return 75;
        }

        @Override
        public double getCritDamage() {
            return 75;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.NEUTRAL;
        }

    },
    SQUID {


    },
    STRAY {
        @Override
        public double getHealth() {
            return 100;
        }

        @Override
        public double getDefense() {
            return 20;
        }

        @Override
        public double getSpeed() {
            return 10;
        }

        @Override
        public double getDamage() {
            return 20;
        }

        @Override
        public double getStrength() {
            return 15;
        }

        @Override
        public double getCritChance() {
            return 40;
        }

        @Override
        public double getCritDamage() {
            return 50;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.HOSTILE;
        }

    },
    STRIDER {

    },
    TRADER_LLAMA {
        @Override
        public double getHealth() {
            return 80;
        }

        @Override
        public double getDefense() {
            return 5;
        }

        @Override
        public double getDamage() {
            return 5;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.NEUTRAL;
        }

    },
    TROPICAL_FISH {


    },
    TURTLE {

    },
    VEX {
        @Override
        public double getHealth() {
            return 70;
        }

        @Override
        public double getSpeed() {
            return 5;
        }

        @Override
        public double getDamage() {
            return 45;
        }

        @Override
        public double getStrength() {
            return 15;
        }

        @Override
        public double getCritChance() {
            return 30;
        }

        @Override
        public double getCritDamage() {
            return 50;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.HOSTILE;
        }

    },
    VILLAGER {

    },
    VINDICATOR {
        @Override
        public double getHealth() {
            return 120;
        }

        @Override
        public double getDefense() {
            return 20;
        }

        @Override
        public double getSpeed() {
            return 10;
        }

        @Override
        public double getDamage() {
            return 65;
        }

        @Override
        public double getStrength() {
            return 20;
        }

        @Override
        public double getCritChance() {
            return 40;
        }

        @Override
        public double getCritDamage() {
            return 80;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.HOSTILE;
        }

    },
    WANDERING_TRADER {

    },
    WITCH {
        @Override
        public double getHealth() {
            return 100;
        }

        @Override
        public double getDefense() {
            return 5;
        }

        @Override
        public double getDamage() {
            return 30;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.HOSTILE;
        }

    },
    WITHER {
        @Override
        public double getHealth() {
            return 1500;
        }

        @Override
        public double getDefense() {
            return 300;
        }

        @Override
        public double getSpeed() {
            return 20;
        }

        @Override
        public double getDamage() {
            return 40;
        }

        @Override
        public double getStrength() {
            return 120;
        }

        @Override
        public double getCritChance() {
            return 50;
        }

        @Override
        public double getCritDamage() {
            return 75;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.BOSS;
        }

    },
    WITHER_SKELETON {
        @Override
        public double getHealth() {
            return 120;
        }

        @Override
        public double getDefense() {
            return 30;
        }

        @Override
        public double getSpeed() {
            return 15;
        }

        @Override
        public double getDamage() {
            return 40;
        }

        @Override
        public double getStrength() {
            return 60;
        }

        @Override
        public double getCritChance() {
            return 70;
        }

        @Override
        public double getCritDamage() {
            return 70;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.HOSTILE;
        }

    },
    WOLF {
        @Override
        public double getHealth() {
            return 100;
        }

        @Override
        public double getDefense() {
            return 50;
        }

        @Override
        public double getSpeed() {
            return 20;
        }

        @Override
        public double getDamage() {
            return 20;
        }

        @Override
        public double getStrength() {
            return 30;
        }

        @Override
        public double getCritChance() {
            return 70;
        }

        @Override
        public double getCritDamage() {
            return 80;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.NEUTRAL;
        }

    },
    ZOGLIN {
        @Override
        public double getHealth() {
            return 200;
        }

        @Override
        public double getDefense() {
            return 25;
        }

        @Override
        public double getSpeed() {
            return 5;
        }

        @Override
        public double getDamage() {
            return 30;
        }

        @Override
        public double getStrength() {
            return 40;
        }

        @Override
        public double getCritChance() {
            return 30;
        }

        @Override
        public double getCritDamage() {
            return 80;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.HOSTILE;
        }

    },
    ZOMBIE {
        @Override
        public double getHealth() {
            return 100;
        }

        @Override
        public double getDefense() {
            return 20;
        }

        @Override
        public double getSpeed() {
            return 20;
        }

        @Override
        public double getDamage() {
            return 20;
        }

        @Override
        public double getStrength() {
            return 10;
        }

        @Override
        public double getCritChance() {
            return 30;
        }

        @Override
        public double getCritDamage() {
            return 50;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.HOSTILE;
        }

    },
    ZOMBIE_HORSE {

    },
    ZOMBIE_VILLAGER {
        @Override
        public double getHealth() {
            return 100;
        }

        @Override
        public double getDefense() {
            return 20;
        }

        @Override
        public double getDamage() {
            return 20;
        }

        @Override
        public double getStrength() {
            return 10;
        }

        @Override
        public double getCritChance() {
            return 30;
        }

        @Override
        public double getCritDamage() {
            return 50;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.HOSTILE;
        }

    },
    ZOMBIFIED_PIGLIN {
        @Override
        public double getHealth() {
            return 100;
        }

        @Override
        public double getDefense() {
            return 20;
        }

        @Override
        public double getSpeed() {
            return 10;
        }

        @Override
        public double getDamage() {
            return 40;
        }

        @Override
        public double getStrength() {
            return 20;
        }

        @Override
        public double getCritChance() {
            return 30;
        }

        @Override
        public double getCritDamage() {
            return 60;
        }

        @Override
        public MobBehavior getMobBehavior() {
            return MobBehavior.NEUTRAL;
        }
    };

    public double getHealth() {
        return 40;
    }

    public double getDefense() {
        return 0;
    }

    public double getSpeed() {
        return 0;
    }

    public double getDamage() {
        return 0;
    }

    public double getStrength() {
        return 0;
    }

    public double getCritChance() {
        return 0;
    }

    public double getCritDamage() {
        return 0;
    }

    public MobBehavior getMobBehavior() {
        return MobBehavior.PASSIVE;
    }

    public String getName() {
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

    public static CustomMobType[] getCustomMobTypeList(EntityType entityType) {
        CustomMobType[] customType = null;
        try {
            customType = new CustomMobType[]{CustomMobType.valueOf(entityType.toString())};
            return customType;
        } catch (IllegalArgumentException exception) {
            return null;
        }
    }

}