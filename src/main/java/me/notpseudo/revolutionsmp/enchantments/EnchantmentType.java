package me.notpseudo.revolutionsmp.enchantments;

import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.items.ItemID;
import me.notpseudo.revolutionsmp.items.ItemType;
import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.itemstats.IncreaseType;
import me.notpseudo.revolutionsmp.listeners.MobListeners;
import me.notpseudo.revolutionsmp.listeners.StatsListeners;
import me.notpseudo.revolutionsmp.mobstats.BaseEntityStats;
import me.notpseudo.revolutionsmp.mobstats.MobCategory;
import me.notpseudo.revolutionsmp.mobstats.MobInfoDataType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public enum EnchantmentType {

    BANE_OF_ARTHROPODS {
        @Override
        public WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return null;
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return null;
            }
            if (!(target.getType() == EntityType.SPIDER || target.getType() == EntityType.CAVE_SPIDER || target.getType() == EntityType.SILVERFISH || target.getType() == EntityType.ENDERMITE)) {
                return null;
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
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }
    },
    CLEAVE {
        @Override
        public int getMaxLevel() {
            return 6;
        }

        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }

        @Override
        public EnchantmentObject createObject() {
            return new CleaveEnchantmentObject();
        }

        @Override
        public EnchantmentObject createObject(int level) {
            return new CleaveEnchantmentObject(level);
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
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }
    },
    CUBISM {
        @Override
        public WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return null;
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return null;
            }
            if (!(target.getType() == EntityType.CREEPER || target.getType() == EntityType.SLIME || target.getType() == EntityType.MAGMA_CUBE)) {
                return null;
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
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD, ItemType.BOW);
        }
    },
    DRAGON_HUNTER {
        @Override
        public WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return null;
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return null;
            }
            if (target.getType() != EntityType.ENDER_DRAGON) {
                return null;
            }
            return new WeaponStats(level * 8, 0, 0, 0, 0, 0);
        }

        @Override
        public int getEnchTableMax() {
            return 0;
        }

        public boolean showInEnchantTable() {
            return false;
        }

        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD, ItemType.BOW);
        }
    },
    ENDER_SLAYER {
        @Override
        public WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return null;
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return null;
            }
            if (!(target.getType() == EntityType.ENDER_DRAGON || target.getType() == EntityType.ENDERMAN || target.getType() == EntityType.ENDERMITE)) {
                return null;
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
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }
    },
    EXECUTE {
        @Override
        public WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return null;
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return null;
            }
            BaseEntityStats targetStats = target.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
            if (target instanceof Player player) {
                targetStats = StatsListeners.getPlayerStats(player);
            }
            int percentMissing;
            if (targetStats == null) {
                percentMissing = (int) ((target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() - target.getHealth()) / target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()) * 100;
            } else {
                percentMissing = (int) ((targetStats.getMaxHealth() - targetStats.getStatValue(StatType.HEALTH)) / targetStats.getMaxHealth()) * 100;
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
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
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
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
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
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }

        @Override
        public EnchantmentObject createObject() {
            return new FireAspectEnchantmentObject();
        }

        @Override
        public EnchantmentObject createObject(int level) {
            return new FireAspectEnchantmentObject(level);
        }

    },
    FIRST_STRIKE {
        @Override
        public int getEnchTableMax() {
            return 4;
        }

        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }

        @Override
        public EnchantmentObject createObject() {
            return new FirstStrikeEnchantmentObject();
        }

        @Override
        public EnchantmentObject createObject(int level) {
            return new FirstStrikeEnchantmentObject(level);
        }
    },
    GIANT_KILLER {
        @Override
        public WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return null;
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return null;
            }
            BaseEntityStats targetStats = target.getPersistentDataContainer().get(mobKey, new MobInfoDataType()), damagerStats = damager.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
            int morePercent;
            if (targetStats == null || damagerStats == null) {
                morePercent = (int) ((target.getHealth() - damager.getHealth()) / damager.getHealth()) * 100;
            } else {
                morePercent = (int) ((targetStats.getStatValue(StatType.HEALTH) - damagerStats.getStatValue(StatType.HEALTH)) / damagerStats.getStatValue(StatType.HEALTH)) * 100;
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
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }
    },
    IMPALING {
        @Override
        public WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return null;
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return null;
            }
            if (!(target.getType() == EntityType.GUARDIAN || target.getType() == EntityType.SQUID || target.getType() == EntityType.GLOW_SQUID || target.getType() == EntityType.ELDER_GUARDIAN)) {
                return null;
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
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD, ItemType.BOW);
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
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }
    },
    LETHALITY {
        @Override
        public int getMaxLevel() {
            return 6;
        }

        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }

        @Override
        public EnchantmentObject createObject() {
            return new LethalityEnchantmentObject();
        }

        @Override
        public EnchantmentObject createObject(int level) {
            return new LethalityEnchantmentObject(level);
        }
    },
    LIFE_STEAL {
        @Override
        public int getEnchTableMax() {
            return 3;
        }

        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }

        @Override
        public EnchantmentObject createObject() {
            return new LifeStealEnchantmentObject();
        }

        @Override
        public EnchantmentObject createObject(int level) {
            return new LifeStealEnchantmentObject(level);
        }
    },
    LOOTING {
        @Override
        public int getEnchTableMax() {
            return 3;
        }

        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }
    },
    LUCK {
        @Override
        public int getMaxLevel() {
            return 7;
        }

        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
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
        public boolean showInEnchantTable() {
            return false;
        }

        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }

        @Override
        public EnchantmentObject createObject() {
            return new ManaStealEnchantmentObject();
        }

        @Override
        public EnchantmentObject createObject(int level) {
            return new ManaStealEnchantmentObject(level);
        }
    },
    PROSECUTE {
        @Override
        public WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return null;
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return null;
            }
            BaseEntityStats targetStats = target.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
            if (target instanceof Player player) {
                targetStats = StatsListeners.getPlayerStats(player);
            }
            int percentHealth;
            if (targetStats == null) {
                percentHealth = (int) (target.getHealth() / target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()) * 100;
            } else {
                percentHealth = (int) (targetStats.getStatValue(StatType.HEALTH) / targetStats.getMaxHealth()) * 100;
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
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }
    },
    SCAVENGER {
        @Override
        public int getEnchTableMax() {
            return 3;
        }

        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }
    },
    SHARPNESS {
        @Override
        public WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return null;
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return null;
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
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }
    },
    SMITE {
        @Override
        public WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return null;
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return null;
            }
            if (target.getCategory() != EntityCategory.UNDEAD) {
                return null;
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
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }
    },
    SMOLDERING {
        @Override
        public WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return null;
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return null;
            }
            if (target.getType() != EntityType.BLAZE) {
                return null;
            }
            return new WeaponStats(level * 3, 0, 0, 0, 0, 0);
        }

        @Override
        public int getEnchTableMax() {
            return 0;
        }

        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD, ItemType.BOW);
        }
    },
    SYPHON {
        @Override
        public int getEnchTableMax() {
            return 3;
        }

        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }

        @Override
        public EnchantmentObject createObject() {
            return new SyphonEnchantmentObject();
        }

        @Override
        public EnchantmentObject createObject(int level) {
            return new SyphonEnchantmentObject(level);
        }
    },
    THUNDERBOLT {
        @Override
        public int getMaxLevel() {
            return 6;
        }

        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }

        @Override
        public EnchantmentObject createObject() {
            return new ThunderboltEnchantmentObject();
        }

        @Override
        public EnchantmentObject createObject(int level) {
            return new ThunderboltEnchantmentObject(level);
        }
    },
    THUNDERLORD {
        @Override
        public int getMaxLevel() {
            return 7;
        }

        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }

        @Override
        public EnchantmentObject createObject() {
            return new ThunderlordEnchantmentObject();
        }

        @Override
        public EnchantmentObject createObject(int level) {
            return new ThunderlordEnchantmentObject(level);
        }
    },
    TITAN_KILLER {
        @Override
        public WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return null;
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return null;
            }
            BaseEntityStats targetStats = target.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
            if (target instanceof Player player) {
                targetStats = StatsListeners.getPlayerStats(player);
            }
            int defenseCount = 0;
            if (targetStats != null) {
                defenseCount = (int) (targetStats.getStatValue(StatType.DEFENSE) / 100);
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
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }
    },
    TRIPLE_STRIKE {
        @Override
        public int getEnchTableMax() {
            return 3;
        }

        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }

        @Override
        public EnchantmentObject createObject() {
            return new TripleStrikeEnchantmentObject();
        }

        @Override
        public EnchantmentObject createObject(int level) {
            return new TripleStrikeEnchantmentObject(level);
        }
    },
    VAMPIRISM {
        @Override
        public int getMaxLevel() {
            return 6;
        }

        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }

        @Override
        public EnchantmentObject createObject() {
            return new VampirismEnchantmentObject();
        }

        @Override
        public EnchantmentObject createObject(int level) {
            return new VampirismEnchantmentObject(level);
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
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }

        @Override
        public EnchantmentObject createObject() {
            return new VenemousEnchantmentObject();
        }

        @Override
        public EnchantmentObject createObject(int level) {
            return new VenemousEnchantmentObject(level);
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
        public boolean showInEnchantTable() {
            return false;
        }

        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD, ItemType.BOW);
        }
    },
    POWER {
        @Override
        public WeaponStats getApplyWeaponStats(int level) {
            return switch (level) {
                case 1, 2, 3, 4, 5 -> new WeaponStats(8 * level, 0, 0, 0, 0, 0);
                case 6 -> new WeaponStats(50, 0, 0, 0, 0, 0);
                default -> new WeaponStats(level * 10 - 5, 0, 0, 0, 0, 0);
            };
        }

        @Override
        public int getMaxLevel() {
            return 7;
        }

        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.BOW);
        }
    },
    COMBO {
        @Override
        public int getEnchTableMax() {
            return 0;
        }

        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
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
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }

        @Override
        public boolean isUltimate() {
            return true;
        }

        @Override
        public EnchantmentObject createObject() {
            return new InfernoEnchantmentObject();
        }

        @Override
        public EnchantmentObject createObject(int level) {
            return new InfernoEnchantmentObject(level);
        }
    },
    ONE_FOR_ALL {
        @Override
        public WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return null;
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return null;
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
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
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
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }

        @Override
        public boolean isUltimate() {
            return true;
        }
    },
    SWARM {
        @Override
        public WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return null;
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return null;
            }
            Collection<LivingEntity> enemies = damager.getLocation().getNearbyLivingEntities(10).stream()
                    .filter(c -> c instanceof Creature && c.getPersistentDataContainer().get(mobKey, new MobInfoDataType()) != null
                            && c.getPersistentDataContainer().get(mobKey, new MobInfoDataType()).getMobBehavior() != MobCategory.PASSIVE
                            && c.getPersistentDataContainer().get(mobKey, new MobInfoDataType()).getMobBehavior() != MobCategory.TAMED
                    ).toList();
            int enemyCount = Math.min(16, enemies.size());
            return new WeaponStats(level * 1.25 * enemyCount, 0, 0, 0, 0, 0);
        }

        @Override
        public int getEnchTableMax() {
            return 0;
        }

        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
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
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD, ItemType.WAND);
        }

        @Override
        public boolean isUltimate() {
            return true;
        }
    },
    LEGION {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public boolean isUltimate() {
            return true;
        }
    },
    GROWTH {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public int getMaxLevel() {
            return 7;
        }

        @Override
        public ArmorStats getApplyArmorStats(int level) {
            return new ArmorStats(level * 15, 0, 0);
        }
    },
    PROTECTION {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public int getMaxLevel() {
            return 7;
        }

        @Override
        public ArmorStats getApplyArmorStats(int level) {
            int mult = level < 6 ? 4 : 5;
            return new ArmorStats(0, level * mult, 0);
        }
    },
    AQUA_AFFINITY {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET);
        }

        @Override
        public int getMaxLevel() {
            return 1;
        }

        @Override
        public Enchantment getVanillaEnchantment(ItemID id) {
            return Enchantment.WATER_WORKER;
        }
    },
    BIG_BRAIN {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET);
        }

        @Override
        public AbilityStats getApplyAbilityStats(int level) {
            return new AbilityStats(0, level * 5);
        }

        @Override
        public int getMinLevel() {
            return 3;
        }

        @Override
        public boolean showInEnchantTable() {
            return false;
        }
    },
    RESPIRATION {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET);
        }

        @Override
        public Enchantment getVanillaEnchantment(ItemID id) {
            return Enchantment.OXYGEN;
        }
    },
    REJUVENATE {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public RegenStats getApplyRegenStats(int level) {
            return new RegenStats(level * 2, 0, 0, 0);
        }

        @Override
        public boolean showInEnchantTable() {
            return false;
        }
    },
    TRUE_PROTECTION {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.CHESTPLATE);
        }

        @Override
        public ArmorStats getApplyArmorStats(int level) {
            return new ArmorStats(0, 0, 0, level * 5);
        }

        @Override
        public int getMaxLevel() {
            return 1;
        }

        @Override
        public boolean showInEnchantTable() {
            return false;
        }
    },
    SMARTY_PANTS {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.LEGGINGS);
        }

        @Override
        public AbilityStats getApplyAbilityStats(int level) {
            return new AbilityStats(0, level * 5);
        }

        @Override
        public boolean showInEnchantTable() {
            return false;
        }
    },
    DEPTH_STRIDER {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.BOOTS);
        }

        @Override
        public int getMaxLevel() {
            return 3;
        }

        @Override
        public Enchantment getVanillaEnchantment(ItemID id) {
            return Enchantment.DEPTH_STRIDER;
        }
    },
    FROST_WALKER {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.BOOTS);
        }

        @Override
        public int getMaxLevel() {
            return 2;
        }

        @Override
        public Enchantment getVanillaEnchantment(ItemID id) {
            return Enchantment.FROST_WALKER;
        }
    },
    SUGAR_RUSH {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.BOOTS);
        }

        @Override
        public ArmorStats getApplyArmorStats(int level) {
            return new ArmorStats(0, 0, level * 2);
        }

        @Override
        public int getMaxLevel() {
            return 3;
        }

        @Override
        public boolean showInEnchantTable() {
            return false;
        }
    },
    FORTUNE {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.PICKAXE, ItemType.DRILL);
        }

        @Override
        public MiningStats getApplyMiningStats(int level) {
            return switch (level) {
                case 1, 2, 3 -> new MiningStats(0, level * 10, 0, 0);
                default -> new MiningStats(0, level * 10 + 5, 0, 0);
            };
        }

        @Override
        public int getMaxLevel() {
            return 4;
        }

        @Override
        public int getEnchTableMax() {
            return 3;
        }
    },
    PRISTINE {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.PICKAXE, ItemType.DRILL);
        }

        @Override
        public MiningStats getApplyMiningStats(int level) {
            return new MiningStats(0, 0, level);
        }

        @Override
        public boolean showInEnchantTable() {
            return false;
        }
    },
    SILK_TOUCH {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.AXE, ItemType.PICKAXE, ItemType.HOE, ItemType.SHOVEL);
        }

        @Override
        public int getMaxLevel() {
            return 1;
        }

        @Override
        public Enchantment getVanillaEnchantment(ItemID id) {
            return Enchantment.SILK_TOUCH;
        }
    },
    EFFICIENCY {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.AXE, ItemType.HOE, ItemType.SHOVEL);
        }

        @Override
        public int getMaxLevel() {
            return 10;
        }

        @Override
        public Enchantment getVanillaEnchantment(ItemID id) {
            if (id.getItemType() == ItemType.PICKAXE || id.getItemType() == ItemType.DRILL) {
                return null;
            }
            return Enchantment.DIG_SPEED;
        }
    },
    CUSTOM_EFFICIENCY {
        @Override
        public String getName() {
            return "Efficiency";
        }

        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.PICKAXE, ItemType.DRILL);
        }

        @Override
        public MiningStats getApplyMiningStats(int level) {
            return new MiningStats(10 + level * 20, 0, 0);
        }

        @Override
        public int getMaxLevel() {
            return 10;
        }

    },
    HARVESTING {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HOE);
        }

        @Override
        public GatheringStats getApplyGatheringStats(int level) {
            return new GatheringStats(12.5 * level, 0);
        }

        @Override
        public int getMaxLevel() {
            return 6;
        }
    },
    LURE {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.FISHING_ROD, ItemType.FISHING_WEAPON);
        }
    };

    /**
     * The NamespacedKey from the MobListeners class used to access MobInfo stored in Persistent Data
     */
    private final static NamespacedKey mobKey = MobListeners.getMobKey();

    public String getName() {
        return ItemEditor.getStringFromEnum(this);
    }

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

    public RegenStats getApplyRegenStats(int level) {
        return null;
    }

    public RegenStats getApplyWisdomStats(int level) {
        return null;
    }

    public WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, IncreaseType type) {
        return null;
    }

    public ArmorStats getEventArmor(LivingEntity damager, Player target, int level, IncreaseType type) {
        return null;
    }

    public AbilityStats getEventAbility(Player damager, LivingEntity target, int level, IncreaseType type) {
        return null;
    }

    public FishingStats getEventFishing(Player fisher, int level, IncreaseType type) {
        return null;
    }

    public MiningStats getEventMining(Player miner, Block block, int level, IncreaseType type) {
        return null;
    }

    public GatheringStats getEventGathering(Player harvester, Block block, int level, IncreaseType type) {
        return null;
    }

    public LuckStats getEventLuck(Player attacker, LivingEntity target, int level, IncreaseType type) {
        return null;
    }

    public WeaponStats getBonusWeapon(Player player, int level, IncreaseType type) {
        return null;
    }

    public ArmorStats getBonusArmor(Player player, int level, IncreaseType type) {
        return null;
    }

    public AbilityStats getBonusAbility(Player player, int level, IncreaseType type) {
        return null;
    }

    public FishingStats getBonusFishing(Player fisher, int level, IncreaseType type) {
        return null;
    }

    public MiningStats getBonusMining(Player miner, int level, IncreaseType type) {
        return null;
    }

    public GatheringStats getBonusGathering(Player harvester, int level, IncreaseType type) {
        return null;
    }

    public LuckStats getBonusLuck(Player attacker, int level, IncreaseType type) {
        return null;
    }

    public RegenStats getBonusRegen(Player attacker, int level, IncreaseType type) {
        return null;
    }

    public WisdomStats getBonusWisdom(Player attacker, int level, IncreaseType type) {
        return null;
    }

    public int getMinLevel() {
        return 1;
    }

    public int getEnchTableMax() {
        return Math.min(5, getMaxLevel());
    }

    public int getMaxLevel() {
        return 5;
    }

    public abstract List<ItemType> getItemTypes();

    public EnchantmentObject createObject() {
        return new EnchantmentObject(this);
    }

    public EnchantmentObject createObject(int level) {
        return new EnchantmentObject(this, level);
    }

    public ArrayList<EnchantmentType> getIncompatibleEnchants() {
        return new ArrayList<>();
    }

    public boolean isUltimate() {
        return false;
    }

    public boolean showInEnchantTable() {
        return !isUltimate();
    }

    public int getExpLevelsNeeded(int level) {
        return switch (level) {
            case 1, 2, 3, 4, 5 -> level * 5;
            default -> (level - 2) * 20;
        };
    }

    public ArrayList<Component> getConflictLore() {
        if (getIncompatibleEnchants().size() > 0) {
            ArrayList<Component> lore = new ArrayList<>();
            lore.add(Component.text("Applying will remove conflicting enchantments: ", NamedTextColor.RED).decoration(TextDecoration.ITALIC, false));
            for (EnchantmentType conflict : getIncompatibleEnchants()) {
                lore.add(Component.text("- " + conflict.getName(), NamedTextColor.RED).decoration(TextDecoration.ITALIC, false));
            }
            return lore;
        }
        return new ArrayList<>();
    }

    public ArrayList<Component> getEnchantLore(int level) {
        return new ArrayList<>();
    }

    public Enchantment getVanillaEnchantment(ItemID id) {
        return null;
    }

}