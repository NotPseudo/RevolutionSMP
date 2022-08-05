package me.notpseudo.revolutionsmp.enchantments;

import me.notpseudo.revolutionsmp.items.ItemType;
import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.listeners.IncreaseType;
import me.notpseudo.revolutionsmp.listeners.MobListeners;
import me.notpseudo.revolutionsmp.listeners.StatsListeners;
import me.notpseudo.revolutionsmp.mobstats.BaseEntityStats;
import me.notpseudo.revolutionsmp.mobstats.MobBehavior;
import me.notpseudo.revolutionsmp.mobstats.MobInfoDataType;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public enum EnchantmentType {

    BANE_OF_ARTHROPODS {
        @Override
        public @NotNull WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return new WeaponStats(1, 1, 1, 1, 1, 1);
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return new WeaponStats(0, 0, 0, 0, 0, 0);
            }
            if (!(target.getType() == EntityType.SPIDER || target.getType() == EntityType.CAVE_SPIDER || target.getType() == EntityType.SILVERFISH || target.getType() == EntityType.ENDERMITE)) {
                return new WeaponStats(0, 0, 0, 0, 0, 0);
            }
            return switch (level) {
                case 1, 2, 3, 4 -> new WeaponStats(level * 10, 0, 0, 0, 0, 0);
                case 5, 6, 7 -> new WeaponStats((level - 2) * 20, 0, 0, 0, 0, 0);
                default -> new WeaponStats((level - 2) * 20, 0, 0, 0, 0, 0);
            };
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
            return switch (level) {
                case 1, 2, 3, 4, 5 -> new WeaponStats(0, 0, 0, level * 10, 0, 0);
                case 6 -> new WeaponStats(0, 0, 0, 70, 0, 0);
                case 7 -> new WeaponStats(0, 0, 0, 100, 0, 0);
                default -> new WeaponStats(0, 0, 0, level * 15, 0, 0);
            };
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
        public @NotNull WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return new WeaponStats(1, 1, 1, 1, 1, 1);
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return new WeaponStats(0, 0, 0, 0, 0, 0);
            }
            if (!(target.getType() == EntityType.CREEPER || target.getType() == EntityType.SLIME || target.getType() == EntityType.MAGMA_CUBE)) {
                return new WeaponStats(0, 0, 0, 0, 0, 0);
            }
            return switch (level) {
                case 1, 2, 3, 4 -> new WeaponStats(level * 10, 0, 0, 0, 0, 0);
                case 5 -> new WeaponStats(60, 0, 0, 0, 0, 0);
                case 6 -> new WeaponStats(80, 0, 0, 0, 0, 0);
                default -> new WeaponStats(level * 15, 0, 0, 0, 0, 0);
            };
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
        public @NotNull WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return new WeaponStats(1, 1, 1, 1, 1, 1);
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return new WeaponStats(0, 0, 0, 0, 0, 0);
            }
            if (target.getType() != EntityType.ENDER_DRAGON) {
                return new WeaponStats(0, 0, 0, 0, 0, 0);
            }
            return new WeaponStats(level * 8, 0, 0, 0, 0, 0);
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
        public @NotNull WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return new WeaponStats(1, 1, 1, 1, 1, 1);
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return new WeaponStats(0, 0, 0, 0, 0, 0);
            }
            if (!(target.getType() == EntityType.ENDER_DRAGON || target.getType() == EntityType.ENDERMAN || target.getType() == EntityType.ENDERMITE)) {
                return new WeaponStats(0, 0, 0, 0, 0, 0);
            }
            return switch (level) {
                case 1, 2, 3, 4 -> new WeaponStats(level * 15, 0, 0, 0, 0, 0);
                case 7 -> new WeaponStats(130, 0, 0, 0, 0, 0);
                default -> new WeaponStats((level - 1) * 20, 0, 0, 0, 0, 0);
            };
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
        public @NotNull WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return new WeaponStats(1, 1, 1, 1, 1, 1);
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return new WeaponStats(0, 0, 0, 0, 0, 0);
            }
            BaseEntityStats targetStats = target.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
            if (target instanceof Player player) {
                targetStats = StatsListeners.getPlayerStats(player);
            }
            int percentMissing;
            if (targetStats == null) {
                percentMissing = (int) ((target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() - target.getHealth()) / target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()) * 100;
            } else {
                percentMissing = (int) ((targetStats.getMaxHealth() - targetStats.getArmorStatValue(StatType.HEALTH)) / targetStats.getMaxHealth()) * 100;
            }
            if (level == 6) {
                return new WeaponStats(1.25 * percentMissing, 0, 0, 0, 0, 0);
            }
            return new WeaponStats(level * 0.2 * percentMissing, 0, 0, 0, 0, 0);
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
        public @NotNull WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return new WeaponStats(1, 1, 1, 1, 1, 1);
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return new WeaponStats(0, 0, 0, 0, 0, 0);
            }
            BaseEntityStats targetStats = target.getPersistentDataContainer().get(mobKey, new MobInfoDataType()), damagerStats = damager.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
            int morePercent;
            if (targetStats == null || damagerStats == null) {
                morePercent = (int) ((target.getHealth() - damager.getHealth()) / damager.getHealth()) * 100;
            } else {
                morePercent = (int) ((targetStats.getArmorStatValue(StatType.HEALTH) - damagerStats.getArmorStatValue(StatType.HEALTH)) / damagerStats.getArmorStatValue(StatType.HEALTH)) * 100;
            }
            return switch (level) {
                case 1, 2, 3, 4 -> new WeaponStats(Math.min(level * 5, morePercent) * level * 0.1, 0, 0, 0, 0, 0);
                case 5 -> new WeaponStats(Math.min(30, morePercent) * 0.6, 0, 0, 0, 0, 0);
                case 6 -> new WeaponStats(Math.min(45, morePercent) * 0.9, 0, 0, 0, 0, 0);
                case 7 -> new WeaponStats(Math.min(65, morePercent) * 1.2, 0, 0, 0, 0, 0);
                default -> new WeaponStats(Math.min(level * 9, morePercent) * level * 0.2, 0, 0, 0, 0, 0);
            };
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
        public @NotNull WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return new WeaponStats(1, 1, 1, 1, 1, 1);
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return new WeaponStats(0, 0, 0, 0, 0, 0);
            }
            if (!(target.getType() == EntityType.GUARDIAN || target.getType() == EntityType.SQUID || target.getType() == EntityType.GLOW_SQUID || target.getType() == EntityType.ELDER_GUARDIAN)) {
                return new WeaponStats(0, 0, 0, 0, 0, 0);
            }
            return new WeaponStats(level * 25, 0, 0, 0, 0, 0);
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
        public @NotNull WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return new WeaponStats(1, 1, 1, 1, 1, 1);
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return new WeaponStats(0, 0, 0, 0, 0, 0);
            }
            BaseEntityStats targetStats = target.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
            if (target instanceof Player player) {
                targetStats = StatsListeners.getPlayerStats(player);
            }
            int percentHealth;
            if (targetStats == null) {
                percentHealth = (int) (target.getHealth() / target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()) * 100;
            } else {
                percentHealth = (int) (targetStats.getArmorStatValue(StatType.HEALTH) / targetStats.getMaxHealth()) * 100;
            }
            return switch (level) {
                case 1, 2, 3, 4 -> new WeaponStats(level * 0.1 * percentHealth, 0, 0, 0, 0, 0);
                case 5 -> new WeaponStats(0.7 * percentHealth, 0, 0, 0, 0, 0);
                case 6 -> new WeaponStats(percentHealth, 0, 0, 0, 0, 0);
                default -> new WeaponStats(level * 1.2 * percentHealth, 0, 0, 0, 0, 0);
            };
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
        public @NotNull WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return new WeaponStats(1, 1, 1, 1, 1, 1);
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return new WeaponStats(0, 0, 0, 0, 0, 0);
            }
            return switch (level) {
                case 1, 2, 3, 4 -> new WeaponStats(level * 5, 0, 0, 0, 0, 0);
                case 5 -> new WeaponStats(30, 0, 0, 0, 0, 0);
                case 6 -> new WeaponStats(45, 0, 0, 0, 0, 0);
                case 7 -> new WeaponStats(65, 0, 0, 0, 0, 0);
                default -> new WeaponStats(level * 10, 0, 0, 0, 0, 0);
            };
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
        public @NotNull WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return new WeaponStats(1, 1, 1, 1, 1, 1);
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return new WeaponStats(0, 0, 0, 0, 0, 0);
            }
            if (target.getCategory() != EntityCategory.UNDEAD) {
                return new WeaponStats(0, 0, 0, 0, 0, 0);
            }
            return switch (level) {
                case 1, 2, 3, 4 -> new WeaponStats(level * 10, 0, 0, 0, 0, 0);
                case 5 -> new WeaponStats(60, 0, 0, 0, 0, 0);
                case 6 -> new WeaponStats(80, 0, 0, 0, 0, 0);
                case 7 -> new WeaponStats(100, 0, 0, 0, 0, 0);
                default -> new WeaponStats(level * 15, 0, 0, 0, 0, 0);
            };
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
        public @NotNull WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return new WeaponStats(1, 1, 1, 1, 1, 1);
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return new WeaponStats(0, 0, 0, 0, 0, 0);
            }
            if (target.getType() != EntityType.BLAZE) {
                return new WeaponStats(0, 0, 0, 0, 0, 0);
            }
            return new WeaponStats(level * 3, 0, 0, 0, 0, 0);
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
        public @NotNull WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return new WeaponStats(1, 1, 1, 1, 1, 1);
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return new WeaponStats(0, 0, 0, 0, 0, 0);
            }
            BaseEntityStats targetStats = target.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
            if (target instanceof Player player) {
                targetStats = StatsListeners.getPlayerStats(player);
            }
            int defenseCount = 0;
            if (targetStats != null) {
                defenseCount = (int) (targetStats.getArmorStatValue(StatType.DEFENSE) / 100);
            }
            return switch (level) {
                case 1, 2, 3, 4 -> new WeaponStats(Math.min(level * 6, level * 2 * defenseCount), 0, 0, 0, 0, 0);
                case 5 -> new WeaponStats(Math.min(40, 12 * defenseCount), 0, 0, 0, 0, 0);
                case 6 -> new WeaponStats(Math.min(60, 16 * defenseCount), 0, 0, 0, 0, 0);
                case 7 -> new WeaponStats(Math.min(80, 20 * defenseCount), 0, 0, 0, 0, 0);
                default -> new WeaponStats(Math.min(level * 15, level * 3 * defenseCount), 0, 0, 0, 0, 0);
            };
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
        public @NotNull WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return new WeaponStats(1, 1, 1, 1, 1, 1);
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return new WeaponStats(0, 0, 0, 0, 0, 0);
            }
            return new WeaponStats(level * 500, 0, 0, 0, 0, 0);
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
        public @NotNull WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return new WeaponStats(1, 1, 1, 1, 1, 1);
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return new WeaponStats(0, 0, 0, 0, 0, 0);
            }
            Collection<LivingEntity> enemies = damager.getLocation().getNearbyLivingEntities(10).stream()
                    .filter(c -> c instanceof Creature && c.getPersistentDataContainer().get(mobKey, new MobInfoDataType()) != null
                            && c.getPersistentDataContainer().get(mobKey, new MobInfoDataType()).getMobBehavior() != MobBehavior.PASSIVE
                            && c.getPersistentDataContainer().get(mobKey, new MobInfoDataType()).getMobBehavior() != MobBehavior.TAMED
                    ).toList();
            int enemyCount = Math.min(16, enemies.size());
            return new WeaponStats(level * 1.25 * enemyCount, 0, 0, 0, 0, 0);
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
    },
    LEGION {
        @Override
        public ItemType[] getItemTypes() {
            return new ItemType[]{ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS};
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

    @NotNull
    public WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new WeaponStats(1, 1, 1, 1, 1, 1);
        }
        return new WeaponStats(0, 0, 0, 0, 0, 0);
    }

    @NotNull
    public ArmorStats getEventArmor(LivingEntity damager, Player target, int level, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new ArmorStats(1, 1, 1, 1);
        }
        return new ArmorStats(0, 0, 0);
    }

    @NotNull
    public AbilityStats getEventAbility(Player damager, LivingEntity target, int level, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new AbilityStats(1, 1);
        }
        return new AbilityStats(0, 0);
    }

    @NotNull
    public FishingStats getEventFishing(Player fisher, int level, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new FishingStats(1, 1);
        }
        return new FishingStats(0, 0);
    }

    @NotNull
    public MiningStats getEventMining(Player miner, Block block, int level, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new MiningStats(1, 1, 1);
        }
        return new MiningStats(0, 0, 0);
    }

    @NotNull
    public GatheringStats getEventGathering(Player harvester, Block block, int level, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new GatheringStats(1, 1);
        }
        return new GatheringStats(0, 0);
    }

    @NotNull
    public LuckStats getEventLuck(Player attacker, LivingEntity target, int level, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new LuckStats(1, 1);
        }
        return new LuckStats(0, 0);
    }

    @NotNull
    public WeaponStats getBonusWeapon(Player player, int level, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new WeaponStats(1, 1, 1, 1, 1, 1);
        }
        return new WeaponStats(0, 0, 0, 0, 0, 0);
    }

    @NotNull
    public ArmorStats getBonusArmor(Player player, int level, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new ArmorStats(1, 1, 1, 1);
        }
        return new ArmorStats(0, 0, 0);
    }

    @NotNull
    public AbilityStats getBonusAbility(Player player, int level, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new AbilityStats(1, 1);
        }
        return new AbilityStats(0, 0);
    }

    @NotNull
    public FishingStats getBonusFishing(Player fisher, int level, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new FishingStats(1, 1);
        }
        return new FishingStats(0, 0);
    }

    @NotNull
    public MiningStats getBonusMining(Player miner, int level, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new MiningStats(1, 1, 1);
        }
        return new MiningStats(0, 0, 0);
    }

    @NotNull
    public GatheringStats getBonusGathering(Player harvester, int level, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new GatheringStats(1, 1);
        }
        return new GatheringStats(0, 0);
    }

    @NotNull
    public LuckStats getBonusLuck(Player attacker, int level, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new LuckStats(1, 1);
        }
        return new LuckStats(0, 0);
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