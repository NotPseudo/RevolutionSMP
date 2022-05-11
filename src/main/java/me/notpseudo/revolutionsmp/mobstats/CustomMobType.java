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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.AXOLOTL;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.BAT;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.BEE;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.BLAZE;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.CAT;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.CAVE_SPIDER;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.CHICKEN;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.COD;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.COW;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.CREEPER;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.DOLPHIN;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.DONKEY;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.DROWNED;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.ELDER_GUARDIAN;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.ENDER_DRAGON;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.ENDERMAN;
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
        }        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.ENDERMITE;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.EVOKER;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.FOX;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.GHAST;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.GIANT;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.GLOW_SQUID;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.GOAT;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.GUARDIAN;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.HOGLIN;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.HORSE;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.HUSK;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.ILLUSIONER;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.IRON_GOLEM;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.LLAMA;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.MAGMA_CUBE;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.MULE;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.MUSHROOM_COW;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.OCELOT;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.PANDA;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.PARROT;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.PHANTOM;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.PIG;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.PIGLIN;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.PIGLIN_BRUTE;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.PILLAGER;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.POLAR_BEAR;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.PUFFERFISH;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.RABBIT;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.RAVAGER;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.SALMON;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.SHEEP;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.SHULKER;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.SILVERFISH;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.SKELETON;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.SKELETON_HORSE;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.SLIME;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.SNOWMAN;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.SPIDER;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.SQUID;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.STRAY;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.STRIDER;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.TRADER_LLAMA;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.TROPICAL_FISH;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.TURTLE;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.VEX;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.VILLAGER;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.VINDICATOR;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.WANDERING_TRADER;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.WITCH;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.WITHER;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.WITHER_SKELETON;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.WOLF;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.ZOGLIN;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.ZOMBIE;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.ZOMBIE_HORSE;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.ZOMBIE_VILLAGER;
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
        @Override
        public VanillaMobType getVanillaMobType() {
            return VanillaMobType.ZOMBIFIED_PIGLIN;
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
    public abstract VanillaMobType getVanillaMobType();

}