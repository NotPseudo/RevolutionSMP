package me.notpseudo.revolutionsmp.mobstats;

public enum CustomMobType {

    AXOLOTL {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 0;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 0;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.PASSIVE;}
        @Override
        public String getName() {
            return "Axolotl";
        }
    },
    BAT {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 0;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 0;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.PASSIVE;}
        @Override
        public String getName() {
            return "Bat";
        }
    },
    BEE {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 20;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 10;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.NEUTRAL;}
        @Override
        public String getName() {
            return "Bee";
        }
    },
    BLAZE {
        @Override
        public double getHealth() {return 100;}
        @Override
        public double getDefense() {return 15;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 30;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.HOSTILE;}
        @Override
        public String getName() {
            return "Blaze";
        }
    },
    CAT {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 0;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 0;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.PASSIVE;}
        @Override
        public String getName() {
            return "Cat";
        }
    },
    CAVE_SPIDER {
        @Override
        public double getHealth() {return 50;}
        @Override
        public double getDefense() {return 10;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 10;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.NEUTRAL;}
        @Override
        public String getName() {
            return "Cave Spider";
        }
    },
    CHICKEN {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 0;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 0;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.PASSIVE;}
        @Override
        public String getName() {
            return "Chicken";
        }
    },
    COD {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 0;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 0;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.PASSIVE;}
        @Override
        public String getName() {
            return "Cod";
        }
    },
    COW {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 0;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 0;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.PASSIVE;}
        @Override
        public String getName() {
            return "Cow";
        }
    },
    CREEPER {
        @Override
        public double getHealth() {return 100;}
        @Override
        public double getDefense() {return 10;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 100;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.HOSTILE;}
        @Override
        public String getName() {
            return "Creeper";
        }
    },
    DOLPHIN {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 15;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 15;}
        @Override
        public double getStrength() {return 10;}
        @Override
        public double getCritChance() {return 30;}
        @Override
        public double getCritDamage() {return 50;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.NEUTRAL;}
        @Override
        public String getName() {
            return "Dolphin";
        }
    },
    DONKEY {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 0;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 0;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.PASSIVE;}
        @Override
        public String getName() {
            return "Donkey";
        }
    },
    DROWNED {
        @Override
        public double getHealth() {return 100;}
        @Override
        public double getDefense() {return 20;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 20;}
        @Override
        public double getStrength() {return 10;}
        @Override
        public double getCritChance() {return 30;}
        @Override
        public double getCritDamage() {return 50;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.HOSTILE;}
        @Override
        public String getName() {
            return "Drowned";
        }
    },
    ELDER_GUARDIAN {
        @Override
        public double getHealth() {return 400;}
        @Override
        public double getDefense() {return 50;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 40;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.HOSTILE;}
        @Override
        public String getName() {
            return "Elder Guardian";
        }
    },
    ENDER_DRAGON {
        @Override
        public double getHealth() {return 1500;}
        @Override
        public double getDefense() {return 300;}
        @Override
        public double getSpeed() {return 20;}
        @Override
        public double getDamage() {return 90;}
        @Override
        public double getStrength() {return 100;}
        @Override
        public double getCritChance() {return 30;}
        @Override
        public double getCritDamage() {return 75;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.BOSS;}
        @Override
        public String getName() {
            return "Ender Dragon";
        }
    },
    ENDERMAN {
        @Override
        public double getHealth() {return 200;}
        @Override
        public double getDefense() {return 20;}
        @Override
        public double getSpeed() {return 5;}
        @Override
        public double getDamage() {return 35;}
        @Override
        public double getStrength() {return 20;}
        @Override
        public double getCritChance() {return 50;}
        @Override
        public double getCritDamage() {return 75;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.NEUTRAL;}
        @Override
        public String getName() {
            return "Enderman";
        }
    },
    ENDERMITE {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 10;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 10;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.HOSTILE;}
        @Override
        public String getName() {
            return "Endermite";
        }
    },
    EVOKER {
        @Override
        public double getHealth() {return 120;}
        @Override
        public double getDefense() {return 10;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 30;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.HOSTILE;}
        @Override
        public String getName() {
            return "Evoker";
        }
    },
    FOX {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 0;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 0;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.PASSIVE;}
        @Override
        public String getName() {
            return "Fox";
        }
    },
    GHAST {
        @Override
        public double getHealth() {return 100;}
        @Override
        public double getDefense() {return 5;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 60;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.HOSTILE;}
        @Override
        public String getName() {
            return "Ghast";
        }
    },
    GIANT {
        @Override
        public double getHealth() {return 750;}
        @Override
        public double getDefense() {return 300;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 250;}
        @Override
        public double getStrength() {return 20;}
        @Override
        public double getCritChance() {return 30;}
        @Override
        public double getCritDamage() {return 50;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.HOSTILE;}
        @Override
        public String getName() {
            return "Giant";
        }
    },
    GLOW_SQUID {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 0;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 0;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.PASSIVE;}
        @Override
        public String getName() {
            return "Glow Squid";
        }
    },
    GOAT {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 5;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 10;}
        @Override
        public double getStrength() {return 10;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.NEUTRAL;}
        @Override
        public String getName() {
            return "Goat";
        }
    },
    GUARDIAN {
        @Override
        public double getHealth() {return 120;}
        @Override
        public double getDefense() {return 10;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 30;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.HOSTILE;}
        @Override
        public String getName() {
            return "Guardian";
        }
    },
    HOGLIN {
        @Override
        public double getHealth() {return 200;}
        @Override
        public double getDefense() {return 5;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 30;}
        @Override
        public double getStrength() {return 20;}
        @Override
        public double getCritChance() {return 30;}
        @Override
        public double getCritDamage() {return 50;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.HOSTILE;}
        @Override
        public String getName() {
            return "Hoglin";
        }
    },
    HORSE {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 0;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 0;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.PASSIVE;}
        @Override
        public String getName() {
            return "Horse";
        }
    },
    HUSK {
        @Override
        public double getHealth() {return 100;}
        @Override
        public double getDefense() {return 20;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 20;}
        @Override
        public double getStrength() {return 10;}
        @Override
        public double getCritChance() {return 30;}
        @Override
        public double getCritDamage() {return 50;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.HOSTILE;}
        @Override
        public String getName() {
            return "Husk";
        }
    },
    ILLUSIONER {
        @Override
        public double getHealth() {return 150;}
        @Override
        public double getDefense() {return 10;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 25;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.HOSTILE;}
        @Override
        public String getName() {
            return "Illusioner";
        }
    },
    IRON_GOLEM {
        @Override
        public double getHealth() {return 500;}
        @Override
        public double getDefense() {return 200;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 75;}
        @Override
        public double getStrength() {return 40;}
        @Override
        public double getCritChance() {return 30;}
        @Override
        public double getCritDamage() {return 75;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.NEUTRAL;}
        @Override
        public String getName() {
            return "Iron Golem";
        }
    },
    LLAMA {
        @Override
        public double getHealth() {return 80;}
        @Override
        public double getDefense() {return 5;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 5;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.NEUTRAL;}
        @Override
        public String getName() {
            return "Llama";
        }
    },
    MAGMA_CUBE {
        @Override
        public double getHealth() {return 80;}
        @Override
        public double getDefense() {return 10;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 30;}
        @Override
        public double getStrength() {return 5;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.HOSTILE;}
        @Override
        public String getName() {
            return "Magma Cube";
        }
    },
    MULE {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 0;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 0;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.PASSIVE;}
        @Override
        public String getName() {
            return "Mule";
        }
    },
    MUSHROOM_COW {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 0;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 0;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.PASSIVE;}
        @Override
        public String getName() {
            return "Mooshroom";
        }
    },
    OCELOT {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 0;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 0;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.PASSIVE;}
        @Override
        public String getName() {
            return "Ocelot";
        }
    },
    PANDA {
        @Override
        public double getHealth() {return 100;}
        @Override
        public double getDefense() {return 20;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 30;}
        @Override
        public double getStrength() {return 10;}
        @Override
        public double getCritChance() {return 30;}
        @Override
        public double getCritDamage() {return 50;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.NEUTRAL;}
        @Override
        public String getName() {
            return "Panda";
        }
    },
    PARROT {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 0;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 0;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.PASSIVE;}
        @Override
        public String getName() {
            return "Parrot";
        }
    },
    PHANTOM {
        @Override
        public double getHealth() {return 80;}
        @Override
        public double getDefense() {return 5;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 10;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 30;}
        @Override
        public double getCritDamage() {return 50;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.HOSTILE;}
        @Override
        public String getName() {
            return "Phantom";
        }
    },
    PIG {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 0;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 0;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.PASSIVE;}
        @Override
        public String getName() {
            return "Pig";
        }
    },
    PIGLIN {
        @Override
        public double getHealth() {return 80;}
        @Override
        public double getDefense() {return 10;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 40;}
        @Override
        public double getStrength() {return 10;}
        @Override
        public double getCritChance() {return 30;}
        @Override
        public double getCritDamage() {return 40;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.NEUTRAL;}
        @Override
        public String getName() {
            return "Piglin";
        }
    },
    PIGLIN_BRUTE {
        @Override
        public double getHealth() {return 250;}
        @Override
        public double getDefense() {return 50;}
        @Override
        public double getSpeed() {return 10;}
        @Override
        public double getDamage() {return 65;}
        @Override
        public double getStrength() {return 30;}
        @Override
        public double getCritChance() {return 50;}
        @Override
        public double getCritDamage() {return 100;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.HOSTILE;}
        @Override
        public String getName() {
            return "Piglin Brute";
        }
    },
    PILLAGER {
        @Override
        public double getHealth() {return 120;}
        @Override
        public double getDefense() {return 10;}
        @Override
        public double getSpeed() {return 5;}
        @Override
        public double getDamage() {return 20;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 40;}
        @Override
        public double getCritDamage() {return 60;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.HOSTILE;}
        @Override
        public String getName() {
            return "Pillager";
        }
    },
    POLAR_BEAR {
        @Override
        public double getHealth() {return 150;}
        @Override
        public double getDefense() {return 15;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 25;}
        @Override
        public double getStrength() {return 15;}
        @Override
        public double getCritChance() {return 30;}
        @Override
        public double getCritDamage() {return 50;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.NEUTRAL;}
        @Override
        public String getName() {
            return "Polar Bear";
        }
    },
    PUFFERFISH {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 0;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 0;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.PASSIVE;}
        @Override
        public String getName() {
            return "Pufferfish";
        }
    },
    RABBIT {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 0;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 0;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.PASSIVE;}
        @Override
        public String getName() {
            return "Rabbit";
        }
    },
    RAVAGER {
        @Override
        public double getHealth() {return 500;}
        @Override
        public double getDefense() {return 150;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 60;}
        @Override
        public double getStrength() {return 25;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.HOSTILE;}
        @Override
        public String getName() {
            return "Ravager";
        }
    },
    SALMON {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 0;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 0;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.PASSIVE;}
        @Override
        public String getName() {
            return "Salmon";
        }
    },
    SHEEP {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 0;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 0;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.PASSIVE;}
        @Override
        public String getName() {
            return "Sheep";
        }
    },
    SHULKER {
        @Override
        public double getHealth() {return 150;}
        @Override
        public double getDefense() {return 40;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 20;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.HOSTILE;}
        @Override
        public String getName() {
            return "Shulker";
        }
    },
    SILVERFISH {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 5;}
        @Override
        public double getSpeed() {return 10;}
        @Override
        public double getDamage() {return 5;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 30;}
        @Override
        public double getCritDamage() {return 50;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.HOSTILE;}
        @Override
        public String getName() {
            return "Silverfish";
        }
    },
    SKELETON {
        @Override
        public double getHealth() {return 100;}
        @Override
        public double getDefense() {return 20;}
        @Override
        public double getSpeed() {return 10;}
        @Override
        public double getDamage() {return 20;}
        @Override
        public double getStrength() {return 10;}
        @Override
        public double getCritChance() {return 50;}
        @Override
        public double getCritDamage() {return 70;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.HOSTILE;}
        @Override
        public String getName() {
            return "Skeleton";
        }
    },
    SKELETON_HORSE {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 0;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 0;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.PASSIVE;}
        @Override
        public String getName() {
            return "Skeleton Horse";
        }
    },
    SLIME {
        @Override
        public double getHealth() {return 80;}
        @Override
        public double getDefense() {return 5;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 20;}
        @Override
        public double getStrength() {return 5;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.HOSTILE;}
        @Override
        public String getName() {
            return "Slime";
        }
    },
    SNOWMAN {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 0;}
        @Override
        public double getSpeed() {return 10;}
        @Override
        public double getDamage() {return 15;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 50;}
        @Override
        public double getCritDamage() {return 75;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.NEUTRAL;}
        @Override
        public String getName() {
            return "Snow Golem";
        }
    },
    SPIDER {
        @Override
        public double getHealth() {return 100;}
        @Override
        public double getDefense() {return 20;}
        @Override
        public double getSpeed() {return 20;}
        @Override
        public double getDamage() {return 10;}
        @Override
        public double getStrength() {return 5;}
        @Override
        public double getCritChance() {return 75;}
        @Override
        public double getCritDamage() {return 75;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.NEUTRAL;}
        @Override
        public String getName() {
            return "Spider";
        }
    },
    SQUID {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 0;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 0;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.PASSIVE;}
        @Override
        public String getName() {
            return "Squid";
        }
    },
    STRAY {
        @Override
        public double getHealth() {return 100;}
        @Override
        public double getDefense() {return 20;}
        @Override
        public double getSpeed() {return 10;}
        @Override
        public double getDamage() {return 20;}
        @Override
        public double getStrength() {return 15;}
        @Override
        public double getCritChance() {return 40;}
        @Override
        public double getCritDamage() {return 50;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.HOSTILE;}
        @Override
        public String getName() {
            return "Stray";
        }
    },
    STRIDER {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 0;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 0;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.PASSIVE;}
        @Override
        public String getName() {
            return "Strider";
        }
    },
    TRADER_LLAMA {
        @Override
        public double getHealth() {return 80;}
        @Override
        public double getDefense() {return 5;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 5;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.NEUTRAL;}
        @Override
        public String getName() {
            return "Trade Llama";
        }
    },
    TROPICAL_FISH {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 0;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 0;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.PASSIVE;}
        @Override
        public String getName() {
            return "Tropical Fish";
        }
    },
    TURTLE {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 0;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 0;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.PASSIVE;}
        @Override
        public String getName() {
            return "Turtle";
        }
    },
    VEX {
        @Override
        public double getHealth() {return 70;}
        @Override
        public double getDefense() {return 0;}
        @Override
        public double getSpeed() {return 5;}
        @Override
        public double getDamage() {return 45;}
        @Override
        public double getStrength() {return 15;}
        @Override
        public double getCritChance() {return 30;}
        @Override
        public double getCritDamage() {return 50;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.HOSTILE;}
        @Override
        public String getName() {
            return "Vex";
        }
    },
    VILLAGER {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 0;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 0;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.PASSIVE;}
        @Override
        public String getName() {
            return "Villager";
        }
    },
    VINDICATOR {
        @Override
        public double getHealth() {return 120;}
        @Override
        public double getDefense() {return 20;}
        @Override
        public double getSpeed() {return 10;}
        @Override
        public double getDamage() {return 65;}
        @Override
        public double getStrength() {return 20;}
        @Override
        public double getCritChance() {return 40;}
        @Override
        public double getCritDamage() {return 80;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.HOSTILE;}
        @Override
        public String getName() {
            return "Vindicator";
        }
    },
    WANDERING_TRADER {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 0;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 0;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.PASSIVE;}
        @Override
        public String getName() {
            return "Wandering Trader";
        }
    },
    WITCH {
        @Override
        public double getHealth() {return 100;}
        @Override
        public double getDefense() {return 5;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 30;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.HOSTILE;}
        @Override
        public String getName() {
            return "Witch";
        }
    },
    WITHER {
        @Override
        public double getHealth() {return 1500;}
        @Override
        public double getDefense() {return 300;}
        @Override
        public double getSpeed() {return 20;}
        @Override
        public double getDamage() {return 40;}
        @Override
        public double getStrength() {return 120;}
        @Override
        public double getCritChance() {return 50;}
        @Override
        public double getCritDamage() {return 75;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.BOSS;}
        @Override
        public String getName() {
            return "Wither";
        }
    },
    WITHER_SKELETON {
        @Override
        public double getHealth() {return 120;}
        @Override
        public double getDefense() {return 30;}
        @Override
        public double getSpeed() {return 15;}
        @Override
        public double getDamage() {return 40;}
        @Override
        public double getStrength() {return 60;}
        @Override
        public double getCritChance() {return 70;}
        @Override
        public double getCritDamage() {return 70;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.HOSTILE;}
        @Override
        public String getName() {
            return "Wither Skeleton";
        }
    },
    WOLF {
        @Override
        public double getHealth() {return 100;}
        @Override
        public double getDefense() {return 50;}
        @Override
        public double getSpeed() {return 20;}
        @Override
        public double getDamage() {return 20;}
        @Override
        public double getStrength() {return 30;}
        @Override
        public double getCritChance() {return 70;}
        @Override
        public double getCritDamage() {return 80;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.NEUTRAL;}
        @Override
        public String getName() {
            return "Wolf";
        }
    },
    ZOGLIN {
        @Override
        public double getHealth() {return 200;}
        @Override
        public double getDefense() {return 25;}
        @Override
        public double getSpeed() {return 5;}
        @Override
        public double getDamage() {return 30;}
        @Override
        public double getStrength() {return 40;}
        @Override
        public double getCritChance() {return 30;}
        @Override
        public double getCritDamage() {return 80;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.HOSTILE;}
        @Override
        public String getName() {
            return "Zoglin";
        }
    },
    ZOMBIE {
        @Override
        public double getHealth() {return 100;}
        @Override
        public double getDefense() {return 20;}
        @Override
        public double getSpeed() {return 20;}
        @Override
        public double getDamage() {return 20;}
        @Override
        public double getStrength() {return 10;}
        @Override
        public double getCritChance() {return 30;}
        @Override
        public double getCritDamage() {return 50;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.HOSTILE;}
        @Override
        public String getName() {
            return "Zombie";
        }
    },
    ZOMBIE_HORSE {
        @Override
        public double getHealth() {return 40;}
        @Override
        public double getDefense() {return 0;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 0;}
        @Override
        public double getStrength() {return 0;}
        @Override
        public double getCritChance() {return 0;}
        @Override
        public double getCritDamage() {return 0;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.PASSIVE;}
        @Override
        public String getName() {
            return "Zombie Horse";
        }
    },
    ZOMBIE_VILLAGER {
        @Override
        public double getHealth() {return 100;}
        @Override
        public double getDefense() {return 20;}
        @Override
        public double getSpeed() {return 0;}
        @Override
        public double getDamage() {return 20;}
        @Override
        public double getStrength() {return 10;}
        @Override
        public double getCritChance() {return 30;}
        @Override
        public double getCritDamage() {return 50;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.HOSTILE;}
        @Override
        public String getName() {
            return "Zombie Villager";
        }
    },
    ZOMBIFIED_PIGLIN {
        @Override
        public double getHealth() {return 100;}
        @Override
        public double getDefense() {return 20;}
        @Override
        public double getSpeed() {return 10;}
        @Override
        public double getDamage() {return 40;}
        @Override
        public double getStrength() {return 20;}
        @Override
        public double getCritChance() {return 30;}
        @Override
        public double getCritDamage() {return 60;}
        @Override
        public MobBehavior getMobBehavior() {return MobBehavior.NEUTRAL;}
        @Override
        public String getName() {
            return "Zombified Piglin";
        }
    };

    public abstract double getHealth();
    public abstract double getDefense();
    public abstract double getSpeed();
    public abstract double getDamage();
    public abstract double getStrength();
    public abstract double getCritChance();
    public abstract double getCritDamage();
    public abstract MobBehavior getMobBehavior();
    public abstract String getName();

}