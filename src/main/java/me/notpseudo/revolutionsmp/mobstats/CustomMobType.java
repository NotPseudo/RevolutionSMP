package me.notpseudo.revolutionsmp.mobstats;

import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.skills.ExpDropObject;
import me.notpseudo.revolutionsmp.skills.SkillType;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.stream.Stream;

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
            return 15;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.NEUTRAL;
        }

    },
    BLAZE {
        @Override
        public double getHealth() {
            return 300;
        }

        @Override
        public double getDamage() {
            return 50;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.HOSTILE;
        }

    },
    CAT {

    },
    CAVE_SPIDER {
        @Override
        public double getHealth() {
            return 110;
        }

        @Override
        public double getDamage() {
            return 40;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.NEUTRAL;
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
            return 80;
        }

        @Override
        public double getDamage() {
            return 20;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.HOSTILE;
        }

    },
    DOLPHIN {

        @Override
        public double getDamage() {
            return 20;
        }

        @Override
        public double getStrength() {
            return 15;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.NEUTRAL;
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
        public double getSpeed() {
            return 20;
        }

        @Override
        public double getDamage() {
            return 20;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.HOSTILE;
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
        public MobCategory getMobBehavior() {
            return MobCategory.HOSTILE;
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
        public double getCritDamage() {
            return 75;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.BOSS;
        }
    },
    ENDERMAN {
        @Override
        public double getHealth() {
            return 250;
        }

        @Override
        public double getDamage() {
            return 50;
        }

        @Override
        public double getStrength() {
            return 20;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.NEUTRAL;
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
        public MobCategory getMobBehavior() {
            return MobCategory.HOSTILE;
        }


    },
    EVOKER {
        @Override
        public double getHealth() {
            return 120;
        }

        @Override
        public double getDamage() {
            return 30;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.HOSTILE;
        }

    },
    FOX {

    },
    GHAST {
        @Override
        public double getHealth() {
            return 150;
        }

        @Override
        public double getDamage() {
            return 60;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.HOSTILE;
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
        public double getCritDamage() {
            return 50;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.HOSTILE;
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
        public MobCategory getMobBehavior() {
            return MobCategory.NEUTRAL;
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
        public MobCategory getMobBehavior() {
            return MobCategory.HOSTILE;
        }

    },
    HOGLIN {
        @Override
        public double getHealth() {
            return 150;
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
            return 20;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.NEUTRAL;
        }


    },
    HORSE {

    },
    HUSK {
        @Override
        public double getHealth() {
            return 120;
        }

        @Override
        public double getSpeed() {
            return 10;
        }

        @Override
        public double getDamage() {
            return 25;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.HOSTILE;
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
        public MobCategory getMobBehavior() {
            return MobCategory.HOSTILE;
        }

    },
    IRON_GOLEM {
        @Override
        public double getHealth() {
            return 500;
        }

        @Override
        public double getDefense() {
            return 100;
        }

        @Override
        public double getDamage() {
            return 60;
        }

        @Override
        public double getStrength() {
            return 70;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.NEUTRAL;
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
        public MobCategory getMobBehavior() {
            return MobCategory.NEUTRAL;
        }


    },
    MAGMA_CUBE {
        @Override
        public double getHealth() {
            return 80;
        }

        @Override
        public double getDamage() {
            return 30;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.HOSTILE;
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
        public MobCategory getMobBehavior() {
            return MobCategory.NEUTRAL;
        }
    },
    PARROT {

    },
    PHANTOM {
        @Override
        public double getHealth() {
            return 120;
        }

        @Override
        public double getDamage() {
            return 10;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.HOSTILE;
        }

    },
    PIG {


    },
    PIGLIN {
        @Override
        public double getHealth() {
            return 100;
        }

        @Override
        public double getDamage() {
            return 40;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.NEUTRAL;
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
        public MobCategory getMobBehavior() {
            return MobCategory.HOSTILE;
        }

    },
    PILLAGER {
        @Override
        public double getHealth() {
            return 120;
        }

        @Override
        public double getDamage() {
            return 20;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.HOSTILE;
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
        public double getCritDamage() {
            return 50;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.NEUTRAL;
        }

    },
    PUFFERFISH {

    },
    RABBIT {


    },
    RAVAGER {
        @Override
        public double getHealth() {
            return 400;
        }

        @Override
        public double getDefense() {
            return 100;
        }

        @Override
        public double getDamage() {
            return 60;
        }

        @Override
        public double getStrength() {
            return 40;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.HOSTILE;
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
        public MobCategory getMobBehavior() {
            return MobCategory.HOSTILE;
        }

    },
    SILVERFISH {
        @Override
        public double getHealth() {
            return 50;
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
        public MobCategory getMobBehavior() {
            return MobCategory.HOSTILE;
        }

    },
    SKELETON {
        @Override
        public double getHealth() {
            return 100;
        }

        @Override
        public double getSpeed() {
            return 10;
        }

        @Override
        public double getDamage() {
            return 15;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.HOSTILE;
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
        public double getDamage() {
            return 15;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.HOSTILE;
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
        public MobCategory getMobBehavior() {
            return MobCategory.NEUTRAL;
        }

    },
    SPIDER {
        @Override
        public double getHealth() {
            return 120;
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
        public MobCategory getMobBehavior() {
            return MobCategory.NEUTRAL;
        }

    },
    SQUID {


    },
    OCTOPUS {
        @Override
        public double getHealth() {
            return 200;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.SEA_CREATURE;
        }

        @Override
        public EntityType getVanillaVersion() {
            return EntityType.SQUID;
        }

    },
    NIGHT_SQUID {
        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.SEA_CREATURE;
        }

        @Override
        public EntityType getVanillaVersion() {
            return EntityType.GLOW_SQUID;
        }

    },
    STRAY {
        @Override
        public double getHealth() {
            return 120;
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
        public MobCategory getMobBehavior() {
            return MobCategory.HOSTILE;
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
        public MobCategory getMobBehavior() {
            return MobCategory.NEUTRAL;
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
        public double getDamage() {
            return 45;
        }

        @Override
        public double getCritDamage() {
            return 50;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.HOSTILE;
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
        public double getCritDamage() {
            return 80;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.HOSTILE;
        }

    },
    WANDERING_TRADER {

    },
    WARDEN {
        @Override
        public double getHealth() {
            return 1300;
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
            return 80;
        }

        @Override
        public double getStrength() {
            return 100;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.BOSS;
        }
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
        public MobCategory getMobBehavior() {
            return MobCategory.HOSTILE;
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
        public double getDamage() {
            return 40;
        }

        @Override
        public double getStrength() {
            return 70;
        }

        @Override
        public double getCritDamage() {
            return 75;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.BOSS;
        }

    },
    WITHER_SKELETON {
        @Override
        public double getHealth() {
            return 300;
        }

        @Override
        public double getDamage() {
            return 50;
        }

        @Override
        public double getCritDamage() {
            return 70;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.HOSTILE;
        }

    },
    WOLF {
        @Override
        public double getHealth() {
            return 100;
        }

        @Override
        public double getSpeed() {
            return 20;
        }

        @Override
        public double getDamage() {
            return 50;
        }

        @Override
        public double getStrength() {
            return 30;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.NEUTRAL;
        }

    },
    ZOGLIN {
        @Override
        public double getHealth() {
            return 250;
        }

        @Override
        public double getDefense() {
            return 50;
        }

        @Override
        public double getDamage() {
            return 30;
        }

        @Override
        public double getStrength() {
            return 30;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.HOSTILE;
        }

    },
    ZOMBIE {
        @Override
        public double getHealth() {
            return 100;
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
        public MobCategory getMobBehavior() {
            return MobCategory.HOSTILE;
        }

    },
    ZOMBIE_HORSE {

    },
    ZOMBIE_VILLAGER {
        @Override
        public double getHealth() {
            return 120;
        }


        @Override
        public double getDamage() {
            return 24;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.HOSTILE;
        }

    },
    ZOMBIFIED_PIGLIN {
        @Override
        public double getHealth() {
            return 200;
        }

        @Override
        public double getSpeed() {
            return 10;
        }

        @Override
        public double getDamage() {
            return 80;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.NEUTRAL;
        }
    },
    SEA_WALKER {
        public double getHealth() {
            return 300;
        }

        public double getDamage() {
            return 30;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.SEA_CREATURE;
        }

        @Override
        public EntityType getVanillaVersion() {
            return EntityType.ZOMBIE;
        }

    },
    SEA_GUARDIAN {
        @Override
        public double getHealth() {
            return 500;
        }

        @Override
        public double getDamage() {
            return 30;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.SEA_CREATURE;
        }

        @Override
        public EntityType getVanillaVersion() {
            return EntityType.GUARDIAN;
        }

    },
    SEA_WITCH {
        @Override
        public double getHealth() {
            return 600;
        }

        @Override
        public double getDamage() {
            return 60;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.SEA_CREATURE;
        }

        @Override
        public EntityType getVanillaVersion() {
            return EntityType.WITCH;
        }

    },
    SEA_ARCHER {
        @Override
        public double getHealth() {
            return 700;
        }

        @Override
        public double getDamage() {
            return 70;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.SEA_CREATURE;
        }

        @Override
        public EntityType getVanillaVersion() {
            return EntityType.SKELETON;
        }

    },
    RIDER_OF_THE_DEEP {
        @Override
        public double getHealth() {
            return 2000;
        }

        @Override
        public double getDamage() {
            return 150;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.SEA_CREATURE;
        }

        @Override
        public EntityType getVanillaVersion() {
            return EntityType.ZOMBIE;
        }

    },
    CATFISH {
        @Override
        public double getHealth() {
            return 2600;
        }

        @Override
        public double getDamage() {
            return 200;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.SEA_CREATURE;
        }

        @Override
        public EntityType getVanillaVersion() {
            return EntityType.GUARDIAN;
        }

    },
    SEA_LEECH {
        @Override
        public double getHealth() {
            return 6000;
        }

        @Override
        public double getDamage() {
            return 200;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.SEA_CREATURE;
        }

        @Override
        public EntityType getVanillaVersion() {
            return EntityType.SILVERFISH;
        }

    },
    GUARDIAN_DEFENDER {
        @Override
        public double getHealth() {
            return 7600;
        }

        @Override
        public double getDamage() {
            return 250;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.SEA_CREATURE;
        }

        @Override
        public EntityType getVanillaVersion() {
            return EntityType.GUARDIAN;
        }

    },
    DEEP_SEA_PROTECTOR {
        @Override
        public double getHealth() {
            return 15000;
        }

        @Override
        public double getDamage() {
            return 300;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.SEA_CREATURE;
        }

        @Override
        public EntityType getVanillaVersion() {
            return EntityType.IRON_GOLEM;
        }

    },
    WATER_HYDRA {
        @Override
        public double getHealth() {
            return 50000;
        }

        @Override
        public double getDamage() {
            return 400;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.SEA_CREATURE;
        }

        @Override
        public EntityType getVanillaVersion() {
            return EntityType.ZOMBIE;
        }

    },
    SEA_EMPEROR {
        @Override
        public double getHealth() {
            return 75000;
        }

        @Override
        public double getDamage() {
            return 300;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.SEA_CREATURE;
        }

        @Override
        public EntityType getVanillaVersion() {
            return EntityType.SKELETON;
        }

    },
    WATER_WORM {
        @Override
        public double getHealth() {
            return 5000;
        }

        @Override
        public double getDamage() {
            return 300;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.SEA_CREATURE;
        }

        @Override
        public EntityType getVanillaVersion() {
            return EntityType.SILVERFISH;
        }

    },
    POISONED_WATER_WORM {
        @Override
        public double getHealth() {
            return 7500;
        }

        @Override
        public double getDamage() {
            return 400;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.SEA_CREATURE;
        }

        @Override
        public EntityType getVanillaVersion() {
            return EntityType.ENDERMITE;
        }

    },
    ZOMBIE_MINER {
        @Override
        public double getHealth() {
            return 200000;
        }

        @Override
        public double getDamage() {
            return 600;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.SEA_CREATURE;
        }

        @Override
        public EntityType getVanillaVersion() {
            return EntityType.ZOMBIE;
        }

    },
    FLAMING_WORM {
        @Override
        public double getHealth() {
            return 10000;
        }

        @Override
        public double getDamage() {
            return 500;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.SEA_CREATURE;
        }

        @Override
        public EntityType getVanillaVersion() {
            return EntityType.SILVERFISH;
        }

    },
    LAVA_BLAZE {
        @Override
        public double getHealth() {
            return 40000;
        }

        @Override
        public double getDamage() {
            return 600;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.SEA_CREATURE;
        }

        @Override
        public EntityType getVanillaVersion() {
            return EntityType.BLAZE;
        }

    },
    LAVA_PIGLIN {
        @Override
        public double getHealth() {
            return 45000;
        }

        @Override
        public double getDamage() {
            return 700;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.SEA_CREATURE;
        }

        @Override
        public EntityType getVanillaVersion() {
            return EntityType.ZOMBIFIED_PIGLIN;
        }

    },
    MAGMA_SLUG {
        @Override
        public double getHealth() {
            return 100000;
        }

        @Override
        public double getDamage() {
            return 8000;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.SEA_CREATURE;
        }

        @Override
        public EntityType getVanillaVersion() {
            return EntityType.MAGMA_CUBE;
        }
    },
    LAVA_LEECH {
        @Override
        public double getHealth() {
            return 300000;
        }

        @Override
        public double getDamage() {
            return 10000;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.SEA_CREATURE;
        }

        @Override
        public EntityType getVanillaVersion() {
            return EntityType.ENDERMITE;
        }
    },
    PYROCLASTIC_WORM {
        @Override
        public double getHealth() {
            return 250000;
        }

        @Override
        public double getDamage() {
            return 9000;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.SEA_CREATURE;
        }

        @Override
        public EntityType getVanillaVersion() {
            return EntityType.MAGMA_CUBE;
        }
    },
    LAVA_FLAME {
        @Override
        public double getHealth() {
            return 300000;
        }

        @Override
        public double getDamage() {
            return 11000;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.SEA_CREATURE;
        }

        @Override
        public EntityType getVanillaVersion() {
            return EntityType.BLAZE;
        }
    },
    FIRE_EEL {
        @Override
        public double getHealth() {
            return 400000;
        }

        @Override
        public double getDamage() {
            return 12000;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.SEA_CREATURE;
        }

        @Override
        public EntityType getVanillaVersion() {
            return EntityType.SILVERFISH;
        }
    },
    TAURUS {
        @Override
        public double getHealth() {
            return 500000;
        }

        @Override
        public double getDamage() {
            return 13000;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.SEA_CREATURE;
        }

        @Override
        public EntityType getVanillaVersion() {
            return EntityType.HOGLIN;
        }
    },
    THUNDER {
        @Override
        public double getHealth() {
            return 3500000;
        }

        @Override
        public double getDamage() {
            return 10000;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.SEA_CREATURE;
        }

        @Override
        public EntityType getVanillaVersion() {
            return EntityType.ELDER_GUARDIAN;
        }
    },
    LORD_JAWBUS {
        @Override
        public double getHealth() {
            return 10000000;
        }

        @Override
        public double getDamage() {
            return 15000;
        }

        @Override
        public MobCategory getMobBehavior() {
            return MobCategory.SEA_CREATURE;
        }

        @Override
        public EntityType getVanillaVersion() {
            return EntityType.IRON_GOLEM;
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
        return 30;
    }

    public double getCritDamage() {
        return 50;
    }

    public double getMagicResistance() {
        return 0;
    }

    public int getMinLevel() {
        return getMobBehavior().getLowestLevel();
    }

    public int getMaxLevel() {
        return getMobBehavior().getHighestLevel();
    }

    public double getAverageLevel() {
        return (getMinLevel() + getMaxLevel()) / 2.0;
    }

    public double getVanillaExp() {
        return -1;
    }
    public double getExpMult() {
        return getMobBehavior().getExpMult();
    }

    public int getCoins(int level) {
        return level * 5;
    }

    public ItemStack[] getEquipment() {
        return null;
    }

    public MobCategory getMobBehavior() {
        return MobCategory.PASSIVE;
    }

    public EntityType getVanillaVersion() {
        try {
            return EntityType.valueOf(this.toString());
        } catch (IllegalArgumentException exception) {
            return null;
        }
    }

    public ExpDropObject getExp(int level) {
        return switch (getMobBehavior()) {
            case PASSIVE, TAMED -> new ExpDropObject(SkillType.COMBAT, level);
            case NEUTRAL, HOSTILE -> new ExpDropObject(SkillType.COMBAT, 2 * level);
            case BOSS -> new ExpDropObject(SkillType.COMBAT, 10 * level);
            case SEA_CREATURE -> new ExpDropObject(SkillType.FISHING, SeaCreature.valueOf(this.toString()).getFishXP());
        };
    }

    public String getName() {
        return ItemEditor.getStringFromEnum(this);
    }

    public static List<CustomMobType> getCustomMobTypeList(EntityType entityType) {
        return Stream.of(CustomMobType.values()).filter(c -> c.getVanillaVersion() == entityType &&
                (c.getMobBehavior() != MobCategory.SEA_CREATURE))
                .toList();
    }

}