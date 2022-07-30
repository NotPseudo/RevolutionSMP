package me.notpseudo.revolutionsmp.enchantments;

import me.notpseudo.revolutionsmp.items.ItemType;
import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.listeners.MobListeners;
import me.notpseudo.revolutionsmp.mobstats.BaseEntityStats;
import me.notpseudo.revolutionsmp.mobstats.MobBehavior;
import me.notpseudo.revolutionsmp.mobstats.MobInfoDataType;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.Creature;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.Collection;

public enum EnchantmentType {

    BANE_OF_ARTHROPODS {
        @Override
        public StatObject getDamageStatAdditivePercent(LivingEntity damager, LivingEntity target, int level, StatType type) {
            if (type != StatType.DAMAGE) {
                return new StatObject(type, 0);
            }
            if (!(target.getType() == EntityType.SPIDER || target.getType() == EntityType.CAVE_SPIDER || target.getType() == EntityType.SILVERFISH || target.getType() == EntityType.ENDERMITE)) {
                return new StatObject(StatType.DAMAGE, 0);
            }
            switch (level) {
                case 1:
                case 2:
                case 3:
                case 4:
                    return new StatObject(StatType.DAMAGE, level * 10);
                case 5:
                case 6:
                case 7:
                default:
                    return new StatObject(StatType.DAMAGE, (level - 2) * 20);
            }
        }

        @Override
        public int getMaxLevel() {
            return 7;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    CLEAVE {
        @Override
        public int getMaxLevel() {
            return 6;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }

        @Override
        public EnchantmentObject createObject(EnchantmentType type) {
            return new CleaveEnchantmentObject();
        }

    },
    CRITICAL {
        @Override
        public WeaponStats getApplyWeaponStats(int level) {
            switch (level) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    return new WeaponStats(0, 0, 0, level * 10, 0, 0);
                case 6:
                    return new WeaponStats(0, 0, 0, 70, 0, 0);
                case 7:
                    return new WeaponStats(0, 0, 0, 100, 0, 0);
                default:
                    return new WeaponStats(0, 0, 0, level * 15, 0, 0);
            }
        }

        @Override
        public int getMaxLevel() {
            return 7;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    CUBISM {
        @Override
        public StatObject getDamageStatAdditivePercent(LivingEntity damager, LivingEntity target, int level, StatType type) {
            if (type != StatType.DAMAGE) {
                return new StatObject(type, 0);
            }
            if (!(target.getType() == EntityType.CREEPER || target.getType() == EntityType.SLIME || target.getType() == EntityType.MAGMA_CUBE)) {
                return new StatObject(StatType.DAMAGE, 0);
            }
            switch (level) {
                case 1:
                case 2:
                case 3:
                case 4:
                    return new StatObject(StatType.DAMAGE, level * 10);
                case 5:
                    return new StatObject(StatType.DAMAGE, 60);
                case 6:
                    return new StatObject(StatType.DAMAGE, 80);
                default:
                    return new StatObject(StatType.DAMAGE, level * 15);
            }
        }

        @Override
        public int getMaxLevel() {
            return 6;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    DRAGON_HUNTER {
        @Override
        public StatObject getDamageStatAdditivePercent(LivingEntity damager, LivingEntity target, int level, StatType type) {
            if (type != StatType.DAMAGE) {
                return new StatObject(type, 0);
            }
            if (target.getType() != EntityType.ENDER_DRAGON) {
                return new StatObject(StatType.DAMAGE, 0);
            }
            return new StatObject(StatType.DAMAGE, level * 8);
        }

        @Override
        public int getEnchTableMax() {
            return 0;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    ENDER_SLAYER {
        @Override
        public StatObject getDamageStatAdditivePercent(LivingEntity damager, LivingEntity target, int level, StatType type) {
            if (type != StatType.DAMAGE) {
                return new StatObject(type, 0);
            }
            if (!(target.getType() == EntityType.ENDER_DRAGON || target.getType() == EntityType.ENDERMAN || target.getType() == EntityType.ENDERMITE)) {
                return new StatObject(StatType.DAMAGE, 0);
            }
            switch (level) {
                case 1:
                case 2:
                case 3:
                case 4:
                    return new StatObject(StatType.DAMAGE, level * 15);
                case 5:
                case 6:
                default:
                    return new StatObject(StatType.DAMAGE, (level - 1) * 20);
                case 7:
                    return new StatObject(StatType.DAMAGE, 130);
            }
        }

        @Override
        public int getMaxLevel() {
            return 7;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    EXECUTE {
        @Override
        public StatObject getDamageStatAdditivePercent(LivingEntity damager, LivingEntity target, int level, StatType type) {
            if (type != StatType.DAMAGE) {
                return new StatObject(type, 0);
            }
            BaseEntityStats targetStats = target.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
            int percentMissing;
            if (targetStats == null) {
                percentMissing = (int) ((target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() - target.getHealth()) / target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()) * 100;
            } else {
                percentMissing = (int) ((targetStats.getMaxHealth() - targetStats.getCurrentHealth()) / targetStats.getMaxHealth()) * 100;
            }
            switch (level) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                default:
                    return new StatObject(StatType.DAMAGE, level * 0.2 * percentMissing);
                case 6:
                    return new StatObject(StatType.DAMAGE, 1.25 * percentMissing);
            }
        }

        @Override
        public int getMaxLevel() {
            return 6;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    EXPERIENCE {
        @Override
        public int getEnchTableMax() {
            return 3;
        }

        @Override
        public int getMaxLevel() {
            return 4;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    FIRE_ASPECT {
        @Override
        public int getEnchTableMax() {
            return 2;
        }

        @Override
        public int getMaxLevel() {
            return 3;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }

        @Override
        public EnchantmentObject createObject(EnchantmentType type) {
            return new FireAspectEnchantmentObject();
        }

    },
    FIRST_STRIKE {
        @Override
        public int getEnchTableMax() {
            return 4;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }

        @Override
        public EnchantmentObject createObject(EnchantmentType type) {
            return new FirstStrikeEnchantmentObject();
        }
    },
    GIANT_KILLER {
        @Override
        public StatObject getDamageStatAdditivePercent(LivingEntity damager, LivingEntity target, int level, StatType type) {
            if (type != StatType.DAMAGE) {
                return new StatObject(type, 0);
            }
            BaseEntityStats targetStats = target.getPersistentDataContainer().get(mobKey, new MobInfoDataType()), damagerStats = damager.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
            int morePercent;
            if (targetStats == null || damagerStats == null) {
                morePercent = (int) ((target.getHealth() - damager.getHealth()) / damager.getHealth()) * 100;
            } else {
                morePercent = (int) ((targetStats.getCurrentHealth() - damagerStats.getCurrentHealth()) / damagerStats.getCurrentHealth()) * 100;
            }
            switch (level) {
                case 1:
                case 2:
                case 3:
                case 4:
                    return new StatObject(StatType.DAMAGE, Math.min(level * 5, morePercent) * level * 0.1);
                case 5:
                    return new StatObject(StatType.DAMAGE, Math.min(30, morePercent) * 0.6);
                case 6:
                    return new StatObject(StatType.DAMAGE, Math.min(45, morePercent) * 0.9);
                case 7:
                    return new StatObject(StatType.DAMAGE, Math.min(65, morePercent) * 1.2);
                default:
                    return new StatObject(StatType.DAMAGE, Math.min(level * 9, morePercent) * level * 0.2);
            }
        }

        @Override
        public int getMaxLevel() {
            return 7;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    IMPALING {
        @Override
        public StatObject getDamageStatAdditivePercent(LivingEntity damager, LivingEntity target, int level, StatType type) {
            if (type != StatType.DAMAGE) {
                return new StatObject(type, 0);
            }
            if (!(target.getType() == EntityType.GUARDIAN || target.getType() == EntityType.SQUID || target.getType() == EntityType.GLOW_SQUID || target.getType() == EntityType.ELDER_GUARDIAN)) {
                return new StatObject(StatType.DAMAGE, 0);
            }
            return new StatObject(StatType.DAMAGE, level * 25);
        }

        @Override
        public int getEnchTableMax() {
            return 3;
        }

        @Override
        public int getMaxLevel() {
            return 3;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    KNOCKBACK {
        @Override
        public int getEnchTableMax() {
            return 2;
        }

        @Override
        public int getMaxLevel() {
            return 2;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    LETHALITY {
        @Override
        public int getMaxLevel() {
            return 6;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }

        @Override
        public EnchantmentObject createObject(EnchantmentType type) {
            return new LethalityEnchantmentObject();
        }
    },
    LIFE_STEAL {
        @Override
        public int getEnchTableMax() {
            return 3;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }

        @Override
        public EnchantmentObject createObject(EnchantmentType type) {
            return new LifeStealEnchantmentObject();
        }
    },
    LOOTING {
        @Override
        public int getEnchTableMax() {
            return 3;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    LUCK {
        @Override
        public int getMaxLevel() {
            return 7;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    MANA_STEAL {
        @Override
        public int getEnchTableMax() {
            return 0;
        }

        @Override
        public int getMaxLevel() {
            return 3;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }

        @Override
        public EnchantmentObject createObject(EnchantmentType type) {
            return new ManaStealEnchantmentObject();
        }
    },
    PROSECUTE {
        @Override
        public StatObject getDamageStatAdditivePercent(LivingEntity damager, LivingEntity target, int level, StatType type) {
            if (type != StatType.DAMAGE) {
                return new StatObject(type, 0);
            }
            BaseEntityStats targetStats = target.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
            int percentHealth;
            if (targetStats == null) {
                percentHealth = (int) (target.getHealth() / target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()) * 100;
            } else {
                percentHealth = (int) (targetStats.getCurrentHealth() / targetStats.getMaxHealth()) * 100;
            }
            switch (level) {
                case 1:
                case 2:
                case 3:
                case 4:
                    return new StatObject(StatType.DAMAGE, level * 0.1 * percentHealth);
                case 5:
                    return new StatObject(StatType.DAMAGE, 0.7 * percentHealth);
                case 6:
                    return new StatObject(StatType.DAMAGE, percentHealth);
                default:
                    return new StatObject(StatType.DAMAGE, level * 1.2 * percentHealth);
            }
        }

        @Override
        public int getMaxLevel() {
            return 6;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    SCAVENGER {
        @Override
        public int getEnchTableMax() {
            return 3;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    SHARPNESS {
        @Override
        public StatObject getDamageStatAdditivePercent(LivingEntity damager, LivingEntity target, int level, StatType type) {
            if (type != StatType.DAMAGE) {
                return new StatObject(type, 0);
            }
            switch (level) {
                case 1:
                case 2:
                case 3:
                case 4:
                    return new StatObject(StatType.DAMAGE, level * 5);
                case 5:
                    return new StatObject(StatType.DAMAGE, 30);
                case 6:
                    return new StatObject(StatType.DAMAGE, 45);
                case 7:
                    return new StatObject(StatType.DAMAGE, 65);
                default:
                    return new StatObject(StatType.DAMAGE, level * 10);
            }
        }

        @Override
        public int getMaxLevel() {
            return 7;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    SMITE {
        @Override
        public StatObject getDamageStatAdditivePercent(LivingEntity damager, LivingEntity target, int level, StatType type) {
            if (type != StatType.DAMAGE) {
                return new StatObject(type, 0);
            }
            if (target.getCategory() != EntityCategory.UNDEAD) {
                return new StatObject(StatType.DAMAGE, 0);
            }
            switch (level) {
                case 1:
                case 2:
                case 3:
                case 4:
                    return new StatObject(StatType.DAMAGE, level * 10);
                case 5:
                    return new StatObject(StatType.DAMAGE, 60);
                case 6:
                    return new StatObject(StatType.DAMAGE, 80);
                case 7:
                    return new StatObject(StatType.DAMAGE, 100);
                default:
                    return new StatObject(StatType.DAMAGE, level * 15);
            }
        }

        @Override
        public int getMaxLevel() {
            return 7;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    SMOLDERING {
        @Override
        public StatObject getDamageStatAdditivePercent(LivingEntity damager, LivingEntity target, int level, StatType type) {
            if (type != StatType.DAMAGE) {
                return new StatObject(type, 0);
            }
            if (target.getType() != EntityType.BLAZE) {
                return new StatObject(StatType.DAMAGE, 0);
            }
            return new StatObject(StatType.DAMAGE, level * 3);
        }

        @Override
        public int getEnchTableMax() {
            return 0;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    SYPHON {
        @Override
        public int getEnchTableMax() {
            return 3;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }

        @Override
        public EnchantmentObject createObject(EnchantmentType type) {
            return new SyphonEnchantmentObject();
        }
    },
    THUNDERBOLT {
        @Override
        public int getMaxLevel() {
            return 6;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }

        @Override
        public EnchantmentObject createObject(EnchantmentType type) {
            return new ThunderboltEnchantmentObject();
        }
    },
    THUNDERLORD {
        @Override
        public int getMaxLevel() {
            return 7;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }

        @Override
        public EnchantmentObject createObject(EnchantmentType type) {
            return new ThunderlordEnchantmentObject();
        }
    },
    TITAN_KILLER {
        @Override
        public StatObject getDamageStatAdditivePercent(LivingEntity damager, LivingEntity target, int level, StatType type) {
            if (type != StatType.DAMAGE) {
                return new StatObject(type, 0);
            }
            BaseEntityStats targetStats = target.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
            int defenseCount = 0;
            if (targetStats != null) {
                defenseCount = (int) (targetStats.getDefense() / 100);
            }
            switch (level) {
                case 1:
                case 2:
                case 3:
                case 4:
                    return new StatObject(StatType.DAMAGE, Math.min(level * 6, level * 2 * defenseCount));
                case 5:
                    return new StatObject(StatType.DAMAGE, Math.min(40, 12 * defenseCount));
                case 6:
                    return new StatObject(StatType.DAMAGE, Math.min(60, 16 * defenseCount));
                case 7:
                    return new StatObject(StatType.DAMAGE, Math.min(80, 20 * defenseCount));
                default:
                    return new StatObject(StatType.DAMAGE, Math.min(level * 15, level * 3 * defenseCount));
            }
        }

        @Override
        public int getMaxLevel() {
            return 7;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    TRIPLE_STRIKE {
        @Override
        public int getEnchTableMax() {
            return 3;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }

        @Override
        public EnchantmentObject createObject(EnchantmentType type) {
            return new TripleStrikeEnchantmentObject();
        }
    },
    VAMPIRISM {
        @Override
        public int getMaxLevel() {
            return 6;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }

        @Override
        public EnchantmentObject createObject(EnchantmentType type) {
            return new VampirismEnchantmentObject();
        }
    },
    VENEMOUS {
        @Override
        public int getEnchTableMax() {
            return 6;
        }

        @Override
        public int getMaxLevel() {
            return 6;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }

        @Override
        public EnchantmentObject createObject(EnchantmentType type) {
            return new VenemousEnchantmentObject();
        }
    },
    VICIOUS {
        @Override
        public WeaponStats getApplyWeaponStats(int level) {
            return new WeaponStats(0, 0, 0, 0, 0, level);
        }

        @Override
        public int getMinLevel() {
            return 3;
        }

        @Override
        public int getEnchTableMax() {
            return 0;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    COMBO {
        @Override
        public int getEnchTableMax() {
            return 0;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }

        @Override
        public boolean isUltimate() {
            return true;
        }
    },
    INFERNO {
        @Override
        public int getEnchTableMax() {
            return 0;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }

        @Override
        public boolean isUltimate() {
            return true;
        }

        @Override
        public EnchantmentObject createObject(EnchantmentType type) {
            return new InfernoEnchantmentObject();
        }
    },
    ONE_FOR_ALL {
        @Override
        public StatObject getDamageStatAdditivePercent(LivingEntity damager, LivingEntity target, int level, StatType type) {
            if (type != StatType.DAMAGE) {
                return new StatObject(type, 0);
            }
            return new StatObject(StatType.DAMAGE, level * 500);
        }

        @Override
        public int getEnchTableMax() {
            return 0;
        }

        @Override
        public int getMaxLevel() {
            return 1;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }

        @Override
        public boolean isUltimate() {
            return true;
        }
    },
    SOUL_EATER {
        @Override
        public int getEnchTableMax() {
            return 0;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }

        @Override
        public boolean isUltimate() {
            return true;
        }
    },
    SWARM {
        @Override
        public StatObject getDamageStatAdditivePercent(LivingEntity damager, LivingEntity target, int level, StatType type) {
            if (type != StatType.DAMAGE) {
                return new StatObject(type, 0);
            }
            Collection<LivingEntity> enemies = damager.getLocation().getNearbyLivingEntities(10).stream()
                    .filter(c -> c instanceof Creature && c.getPersistentDataContainer().get(mobKey, new MobInfoDataType()) != null
                            && c.getPersistentDataContainer().get(mobKey, new MobInfoDataType()).getMobBehavior() != MobBehavior.PASSIVE
                            && c.getPersistentDataContainer().get(mobKey, new MobInfoDataType()).getMobBehavior() != MobBehavior.TAMED
                    ).toList();
            int enemyCount = Math.min(16, enemies.size());
            return new StatObject(StatType.DAMAGE, level * 1.25 * enemyCount);
        }

        @Override
        public int getEnchTableMax() {
            return 0;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }

        @Override
        public boolean isUltimate() {
            return true;
        }
    },
    ULTIMATE_WISE {
        @Override
        public int getEnchTableMax() {
            return 0;
        }

        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.SWORD, ItemType.WAND};
        }

        @Override
        public boolean isUltimate() {
            return true;
        }
    };

    /**
     * The NamespacedKey from the MobListeners class used to access MobInfo stored in Persistent Data
     */
    private final static NamespacedKey mobKey = MobListeners.getMobKey();

    public WeaponStats getApplyWeaponStats(int level) {
        return null;
    }

    public ArmorStats getApplyArmorStats(int level) {
        return null;
    }

    public AbilityStats getApplyAbilityStats(int level) {
        return null;
    }

    public FishingStats getApplyFishingStats(int level) {
        return null;
    }

    public MiningStats getApplyMiningStats(int level) {
        return null;
    }

    public GatheringStats getApplyGatheringStats(int level) {
        return null;
    }

    public LuckStats getApplyLuckStats(int level) {
        return null;
    }

    public StatObject getDamageStatAdditiveAmount(LivingEntity damager, LivingEntity target, int level, StatType type) {
        return new StatObject(type, 0);
    }

    public StatObject getDamageStatAdditivePercent(LivingEntity damager, LivingEntity target, int level, StatType type) {
        return new StatObject(type, 0);
    }

    public StatObject getDamageStatMultiplicativePercent(LivingEntity damager, LivingEntity target, int level, StatType type) {
        return new StatObject(type, 1);
    }

    public StatObject getHealthStatAdditiveAmount(LivingEntity damager, LivingEntity target, int level, StatType type) {
        return new StatObject(type, 0);
    }

    public StatObject getHealthStatAdditivePercent(LivingEntity damager, LivingEntity target, int level, StatType type) {
        return new StatObject(type, 0);
    }

    public StatObject getHealthStatMultiplicativePercent(LivingEntity damager, LivingEntity target, int level, StatType type) {
        return new StatObject(type, 1);
    }

    public StatObject getAbilityStatAdditiveAmount(LivingEntity damager, int level, StatType type) {
        return new StatObject(type, 0);
    }

    public StatObject getAbilityStatAdditivePercent(LivingEntity damager, int level, StatType type) {
        return new StatObject(type, 0);
    }

    public StatObject getAbilityStatMultiplicativePercent(LivingEntity damager, int level, StatType type) {
        return new StatObject(type, 1);
    }

    public StatObject getFishingStatAdditiveAmount(LivingEntity damager, int level, StatType type) {
        return new StatObject(type, 0);
    }

    public StatObject getFishingStatAdditivePercent(LivingEntity damager, int level, StatType type) {
        return new StatObject(type, 0);
    }

    public StatObject getFishingStatMultiplicativePercent(LivingEntity damager, int level, StatType type) {
        return new StatObject(type, 1);
    }

    public StatObject geBreakingStatAdditiveAmount(LivingEntity harvester, Block block, int level, StatType type) {
        return new StatObject(type, 0);
    }

    public StatObject getBreakingStatAdditivePercent(LivingEntity harvester, Block block, int level, StatType type) {
        return new StatObject(type, 0);
    }

    public StatObject getBreakingStatMultiplicativePercent(LivingEntity harvester, Block block, int level, StatType type) {
        return new StatObject(type, 1);
    }

    public StatObject getLuckStatAdditiveAmount(LivingEntity damager, LivingEntity target, int level, StatType type) {
        return new StatObject(type, 0);
    }

    public StatObject getLuckStatAdditivePercent(LivingEntity damager, LivingEntity target, int level, StatType type) {
        return new StatObject(type, 0);
    }

    public StatObject getLuckStatMultiplicativePercent(LivingEntity damager, LivingEntity target, int level, StatType type) {
        return new StatObject(type, 1);
    }

    public int getMinLevel() {
        return 1;
    }

    public int getEnchTableMax() {
        return 5;
    }

    public int getMaxLevel() {
        return 5;
    }

    public abstract ItemType[] getItemTypes();

    public EnchantmentObject createObject(EnchantmentType type) {
        return new EnchantmentObject(type);
    }

    public ArrayList<EnchantmentType> getIncompatibleEnchants() {
        return new ArrayList<>();
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

    public boolean isUltimate() {
        return false;
    }

}