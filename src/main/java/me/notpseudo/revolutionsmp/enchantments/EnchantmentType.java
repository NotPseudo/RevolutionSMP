package me.notpseudo.revolutionsmp.enchantments;

import me.notpseudo.revolutionsmp.items.ItemType;
import me.notpseudo.revolutionsmp.itemstats.AbilityStats;
import me.notpseudo.revolutionsmp.itemstats.ArmorStats;
import me.notpseudo.revolutionsmp.itemstats.WeaponStats;
import me.notpseudo.revolutionsmp.listeners.MobListeners;
import me.notpseudo.revolutionsmp.mobstats.BaseEntityStats;
import me.notpseudo.revolutionsmp.mobstats.MobInfoDataType;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public enum EnchantmentType {

    BANE_OF_ARTHROPODS {
        @Override
        public double getAddDamageMult(EntityDamageByEntityEvent event, int level) {
            LivingEntity target = (LivingEntity) event.getEntity();
            if (target.getType() != EntityType.SPIDER || target.getType() != EntityType.CAVE_SPIDER || target.getType() != EntityType.SILVERFISH || target.getType() != EntityType.ENDERMITE) {
                return 0;
            }
            switch (level) {
                case 1:
                    return 10;
                case 2:
                    return 20;
                case 3:
                    return 30;
                case 4:
                    return 40;
                case 5:
                    return 60;
                case 6:
                    return 80;
                case 7:
                    return 100;
                default:
                    return level * 15;
            }
        }

        @Override
        public int getMaxLevel() {
            return 7;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    CLEAVE {
        @Override
        public int getMaxLevel() {
            return 6;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    CRITICAL {
        @Override
        public WeaponStats getApplyWeaponStats(int level) {
            switch (level) {
                case 1:
                    return new WeaponStats(0, 0, 0, 10, 0, 0);
                case 2:
                    return new WeaponStats(0, 0, 0, 20, 0, 0);
                case 3:
                    return new WeaponStats(0, 0, 0, 30, 0, 0);
                case 4:
                    return new WeaponStats(0, 0, 0, 40, 0, 0);
                case 5:
                    return new WeaponStats(0, 0, 0, 50, 0, 0);
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
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[0];
        }
    },
    CUBISM {
        @Override
        public int getMaxLevel() {
            return 6;
        }

        @Override
        public double getAddDamageMult(EntityDamageByEntityEvent event, int level) {
            LivingEntity target = (LivingEntity) event.getEntity();
            if (target.getType() != EntityType.CREEPER || target.getType() != EntityType.SLIME || target.getType() != EntityType.MAGMA_CUBE) {
                return 0;
            }
            switch (level) {
                case 1:
                    return 10;
                case 2:
                    return 20;
                case 3:
                    return 30;
                case 4:
                    return 40;
                case 5:
                    return 60;
                case 6:
                    return 80;
                default:
                    return level * 15;
            }
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    DRAGON_HUNTER {
        @Override
        public double getAddDamageMult(EntityDamageByEntityEvent event, int level) {
            LivingEntity target = (LivingEntity) event.getEntity();
            if (target.getType() != EntityType.ENDER_DRAGON) {
                return 0;
            }
            return level * 8;
        }

        @Override
        public int getEnchTableMax() {
            return 0;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    ENDER_SLAYER {
        @Override
        public double getAddDamageMult(EntityDamageByEntityEvent event, int level) {
            LivingEntity target = (LivingEntity) event.getEntity();
            if (target.getType() != EntityType.ENDER_DRAGON || target.getType() != EntityType.ENDERMAN || target.getType() != EntityType.ENDERMITE) {
                return 0;
            }
            switch (level) {
                case 1:
                    return 15;
                case 2:
                    return 30;
                case 3:
                    return 45;
                case 4:
                    return 60;
                case 5:
                    return 80;
                case 6:
                    return 100;
                case 7:
                    return 130;
                default:
                    return level * 20;
            }
        }

        @Override
        public int getMaxLevel() {
            return 7;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    EXECUTE {
        @Override
        public double getAddDamageMult(EntityDamageByEntityEvent event, int level) {
            LivingEntity target = (LivingEntity) event.getEntity();
            BaseEntityStats targetStats = target.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
            int percentMissing;
            if (targetStats == null) {
                percentMissing = (int) ((target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() - target.getHealth()) / target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()) * 100;
            } else {
                percentMissing = (int) ((targetStats.getMaxHealth() - targetStats.getCurrentHealth()) / targetStats.getMaxHealth()) * 100;
            }
            switch (level) {
                case 1:
                    return 0.2 * percentMissing;
                case 2:
                    return 0.4 * percentMissing;
                case 3:
                    return 0.6 * percentMissing;
                case 4:
                    return 0.8 * percentMissing;
                case 5:
                    return percentMissing;
                case 6:
                    return 1.25 * percentMissing;
                default:
                    return level * 1.25 * percentMissing;
            }
        }

        @Override
        public int getMaxLevel() {
            return 6;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
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
        public ItemType[] getApplicableItemTypes() {
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
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    FIRST_STRIKE {
        @Override
        public int getEnchTableMax() {
            return 4;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    GIANT_KILLER {
        @Override
        public double getAddDamageMult(EntityDamageByEntityEvent event, int level) {
            LivingEntity target = (LivingEntity) event.getEntity(), damager = (LivingEntity) event.getDamager();
            BaseEntityStats targetStats = target.getPersistentDataContainer().get(mobKey, new MobInfoDataType()), damagerStats = target.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
            int morePercent;
            if (targetStats == null || damagerStats == null) {
                morePercent = (int) ((target.getHealth() - damager.getHealth()) / damager.getHealth()) * 100;
            } else {
                morePercent = (int) ((targetStats.getCurrentHealth() - damagerStats.getCurrentHealth()) / damagerStats.getCurrentHealth()) * 100;
            }
            switch (level) {
                case 1:
                    return Math.min(5, morePercent) * 0.1;
                case 2:
                    return Math.min(10, morePercent) * 0.2;
                case 3:
                    return Math.min(15, morePercent) * 0.3;
                case 4:
                    return Math.min(20, morePercent) * 0.4;
                case 5:
                    return Math.min(30, morePercent) * 0.6;
                case 6:
                    return Math.min(45, morePercent) * 0.9;
                case 7:
                    return Math.min(65, morePercent) * 1.2;
                default:
                    return Math.min(level * 9, morePercent) * level * 0.2;
            }
        }

        @Override
        public int getMaxLevel() {
            return 7;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    IMPALING {
        @Override
        public double getAddDamageMult(EntityDamageByEntityEvent event, int level) {
            LivingEntity target = (LivingEntity) event.getEntity();
            if (target.getType() != EntityType.GUARDIAN || target.getType() != EntityType.SQUID || target.getType() != EntityType.GLOW_SQUID || target.getType() != EntityType.ELDER_GUARDIAN) {
                return 0;
            }
            switch (level) {
                case 1:
                    return 25;
                case 2:
                    return 50;
                case 3:
                    return 75;
                default:
                    return level * 15;
            }
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
        public ItemType[] getApplicableItemTypes() {
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
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    LETHALITY {
        @Override
        public int getMaxLevel() {
            return 6;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    LIFE_STEAL {
        @Override
        public int getEnchTableMax() {
            return 3;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    LOOTING {
        @Override
        public int getEnchTableMax() {
            return 3;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    LUCK {
        @Override
        public int getMaxLevel() {
            return 7;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
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
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    PROSECUTE {
        @Override
        public int getMaxLevel() {
            return 6;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    SCAVENGER {
        @Override
        public int getEnchTableMax() {
            return 3;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    SHARPNESS {
        @Override
        public int getMaxLevel() {
            return 7;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    SMITE {
        @Override
        public int getMaxLevel() {
            return 7;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    SMOLDERING {
        @Override
        public int getEnchTableMax() {
            return 0;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    SYPHON {
        @Override
        public int getEnchTableMax() {
            return 3;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    THUNDERBOLT {
        @Override
        public int getMaxLevel() {
            return 6;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    THUNDERLORD {
        @Override
        public int getMaxLevel() {
            return 7;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    TITAN_KILLER {
        @Override
        public int getMaxLevel() {
            return 7;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    TRIPLE_STRIKE {
        @Override
        public int getEnchTableMax() {
            return 3;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    VAMPIRISM {
        @Override
        public int getMaxLevel() {
            return 6;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
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
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    VICIOUS {
        @Override
        public int getMinLevel() {
            return 3;
        }

        @Override
        public int getEnchTableMax() {
            return 0;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    COMBO {
        @Override
        public int getEnchTableMax() {
            return 0;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    INFERNO {
        @Override
        public int getEnchTableMax() {
            return 0;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    ONE_FOR_ALL {
        @Override
        public int getEnchTableMax() {
            return 0;
        }

        @Override
        public int getMaxLevel() {
            return 1;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    SOUL_EATER {
        @Override
        public int getEnchTableMax() {
            return 0;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    SWARM {
        @Override
        public int getEnchTableMax() {
            return 0;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    ULTIMATE_WISE {
        @Override
        public int getEnchTableMax() {
            return 0;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
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

    public double getAddDamage(EntityDamageByEntityEvent event, int level) {
        return 0;
    }

    public double getAddDamageMult(EntityDamageByEntityEvent event, int level) {
        return 0;
    }

    public double getMultDamage(EntityDamageByEntityEvent event, int level) {
        return 1;
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

    public abstract ItemType[] getApplicableItemTypes();

    public EnchantmentObject createObject(EnchantmentType type) {
        return new EnchantmentObject(type);
    }

}