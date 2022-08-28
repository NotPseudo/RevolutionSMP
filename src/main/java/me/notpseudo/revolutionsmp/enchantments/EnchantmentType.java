package me.notpseudo.revolutionsmp.enchantments;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.items.ItemID;
import me.notpseudo.revolutionsmp.items.ItemType;
import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.listeners.HealthListeners;
import me.notpseudo.revolutionsmp.listeners.MobListeners;
import me.notpseudo.revolutionsmp.listeners.StatsListeners;
import me.notpseudo.revolutionsmp.mining.CustomOreLocation;
import me.notpseudo.revolutionsmp.mobstats.BaseEntityStats;
import me.notpseudo.revolutionsmp.mobstats.MobCategory;
import me.notpseudo.revolutionsmp.mobstats.MobInfo;
import me.notpseudo.revolutionsmp.mobstats.MobInfoDataType;
import me.notpseudo.revolutionsmp.playerstats.PlayerStats;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public enum EnchantmentType {

    BANE_OF_ARTHROPODS {
        @Override
        public WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, EntityDamageEvent event, IncreaseType type) {
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

        @Override
        public List<EnchantmentType> getIncompatibleEnchants() {
            return List.of(SMITE, SHARPNESS);
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
        public void playerAttackAction(EnchantmentObject enchant, Player attacker, LivingEntity target, double damage, boolean critical, EntityDamageEvent event) {
            int level = enchant.getLevel();
            double damagePercent, range = 3 + (0.3 * level);
            damagePercent = switch (level) {
                case 1, 2, 3, 4, 5 -> 0.03 * level;
                case 6 -> 0.2;
                default -> 0.4 * level;
            };
            Collection<LivingEntity> enemies = attacker.getLocation().getNearbyLivingEntities(range).stream()
                    .filter(c -> MobListeners.getMobInfo(c) != null
                            && MobListeners.getMobInfo(c).getMobBehavior() != MobCategory.TAMED
                    ).toList();
            for (LivingEntity enemy : enemies) {
                enemy.damage(damagePercent * damage);
                HealthListeners.showDamage(enemy, damagePercent * damage, false, ChatColor.GRAY);
            }
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
        public WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, EntityDamageEvent event, IncreaseType type) {
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
        public WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, EntityDamageEvent event, IncreaseType type) {
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
        public WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, EntityDamageEvent event, IncreaseType type) {
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
        public WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, EntityDamageEvent event, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return null;
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return null;
            }
            BaseEntityStats targetStats = MobListeners.getMobInfo(target);
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

        @Override
        public List<EnchantmentType> getIncompatibleEnchants() {
            return List.of(PROSECUTE);
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
        public void playerAttackAction(EnchantmentObject enchant, Player attacker, LivingEntity target, double damage, boolean critical, EntityDamageEvent event) {
            int level = enchant.getLevel();
            double damagePercent = 0.03 * level;
            int finalSeconds = level == 1 ? 3 : 4;
            BukkitRunnable fireDamage = new BukkitRunnable() {
                int count = 1;

                @Override
                public void run() {
                    if (!target.isDead() && count <= finalSeconds) {
                        target.setMaximumNoDamageTicks(0);
                        target.setNoDamageTicks(0);
                        new EntityDamageEvent(target, EntityDamageEvent.DamageCause.FIRE, damage * damagePercent).callEvent();
                        count++;
                    } else {
                        this.cancel();
                    }
                }
            };
            fireDamage.runTaskTimer(RevolutionSMP.getPlugin(), 0, 20);
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
        public List<EnchantmentType> getIncompatibleEnchants() {
            return List.of(TRIPLE_STRIKE);
        }
    },
    GIANT_KILLER {
        @Override
        public WeaponStats getEventWeapon(Player attacker, LivingEntity target, int level, EntityDamageEvent event, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return null;
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return null;
            }
            BaseEntityStats targetStats = MobListeners.getMobInfo(target);
            if (target instanceof Player player) {
                targetStats = StatsListeners.getPlayerStats(player);
            }
            PlayerStats damagerStats = StatsListeners.getPlayerStats(attacker);
            int morePercent;
            if (targetStats == null) {
                morePercent = (int) ((target.getHealth() - attacker.getHealth()) / attacker.getHealth()) * 100;
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

        @Override
        public List<EnchantmentType> getIncompatibleEnchants() {
            return List.of(TITAN_KILLER);
        }
    },
    IMPALING {
        @Override
        public WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, EntityDamageEvent event, IncreaseType type) {
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
        public Enchantment getVanillaEnchantment(ItemID id) {
            return Enchantment.KNOCKBACK;
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
        public List<EnchantmentType> getIncompatibleEnchants() {
            return List.of(SYPHON, MANA_STEAL);
        }

        @Override
        public void playerAttackAction(EnchantmentObject enchant, Player attacker, LivingEntity target, double damage, boolean critical, EntityDamageEvent event) {
            PlayerStats stats = StatsListeners.getPlayerStats(attacker);
            double percent = enchant.getLevel() * 0.005, maxHealth = stats.getMaxHealth();
            new EntityRegainHealthEvent(attacker, percent * maxHealth, EntityRegainHealthEvent.RegainReason.CUSTOM).callEvent();
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
        public List<EnchantmentType> getIncompatibleEnchants() {
            return List.of(SYPHON, LIFE_STEAL);
        }

        @Override
        public void playerAttackAction(EnchantmentObject enchant, Player attacker, LivingEntity target, double damage, boolean critical, EntityDamageEvent event) {
            if (!critical) {
                return;
            }
            PlayerStats stats = StatsListeners.getPlayerStats(attacker);
            double percent = (0.001 + (enchant.getLevel() * 0.001)) * ((int) Math.min(1000, damage) / 100), maxHealth = stats.getMaxHealth();
            new EntityRegainHealthEvent(attacker, percent * maxHealth, EntityRegainHealthEvent.RegainReason.CUSTOM).callEvent();
        }
    },
    PROSECUTE {
        @Override
        public WeaponStats getEventWeapon(Player attacker, LivingEntity target, int level, EntityDamageEvent event, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return null;
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return null;
            }
            BaseEntityStats targetStats = MobListeners.getMobInfo(target);
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

        @Override
        public List<EnchantmentType> getIncompatibleEnchants() {
            return List.of(EXECUTE);
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
        public WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, EntityDamageEvent event, IncreaseType type) {
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

        @Override
        public List<EnchantmentType> getIncompatibleEnchants() {
            return List.of(SMITE, BANE_OF_ARTHROPODS);
        }
    },
    SMITE {
        @Override
        public WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, EntityDamageEvent event, IncreaseType type) {
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

        @Override
        public List<EnchantmentType> getIncompatibleEnchants() {
            return List.of(SHARPNESS, BANE_OF_ARTHROPODS);
        }
    },
    SMOLDERING {
        @Override
        public WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, EntityDamageEvent event, IncreaseType type) {
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
        public List<EnchantmentType> getIncompatibleEnchants() {
            return List.of(LIFE_STEAL, MANA_STEAL);
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
        public List<EnchantmentType> getIncompatibleEnchants() {
            return List.of(THUNDERLORD);
        }

        private static Map<Player, Integer> thunderbolt = new HashMap<>();

        @Override
        public void playerAttackAction(EnchantmentObject enchant, Player attacker, LivingEntity target, double damage, boolean critical, EntityDamageEvent event) {
            int current = 0;
            if (thunderbolt.containsKey(attacker)) {
                current = thunderbolt.get(attacker);
            }
            current++;
            thunderbolt.put(attacker, current);
            if (current >= 3) {
                thunderbolt.put(attacker, 0);
                int level = enchant.getLevel();
                double damagePercent = switch (level) {
                    case 1, 2, 3, 4, 5 -> level * 0.04;
                    default -> (level - 1) * 0.05;
                };
                Collection<LivingEntity> enemies = attacker.getLocation().getNearbyLivingEntities(2).stream()
                        .filter(c -> MobListeners.getMobInfo(c) != null
                                && MobListeners.getMobInfo(c).getMobBehavior() != MobCategory.TAMED
                        ).toList();
                for (LivingEntity entity : enemies) {
                    entity.getWorld().strikeLightningEffect(entity.getLocation());
                    entity.damage(damagePercent * damage);
                    HealthListeners.showDamage(entity, damagePercent * damage, false, ChatColor.YELLOW);
                }
            }
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
        public List<EnchantmentType> getIncompatibleEnchants() {
            return List.of(THUNDERBOLT);
        }

        private static Map<Player, Integer> thunderlordHitCount = new HashMap<>();

        @Override
        public void playerAttackAction(EnchantmentObject enchant, Player attacker, LivingEntity target, double damage, boolean critical, EntityDamageEvent event) {
            int current = 0;
            if (thunderlordHitCount.containsKey(attacker)) {
                current = thunderlordHitCount.get(attacker);
            }
            current++;
            thunderlordHitCount.put(attacker, current);
            if (current >= 3) {
                thunderlordHitCount.put(attacker, 0);
                int level = enchant.getLevel();
                double damagePercent = switch (level) {
                    case 1, 2, 3, 4, 5 -> level * 0.08;
                    default -> (level - 1) * 0.1;
                };
                target.getWorld().strikeLightningEffect(target.getLocation());
                target.damage(damagePercent * damage);
                HealthListeners.showDamage(target, damagePercent * damage, false, ChatColor.YELLOW);
            }
        }
    },
    TITAN_KILLER {
        @Override
        public WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, EntityDamageEvent event, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return null;
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return null;
            }
            BaseEntityStats targetStats = MobListeners.getMobInfo(target);
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

        @Override
        public List<EnchantmentType> getIncompatibleEnchants() {
            return List.of(GIANT_KILLER);
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
        public List<EnchantmentType> getIncompatibleEnchants() {
            return List.of(FIRST_STRIKE);
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
    },
    ONE_FOR_ALL {
        @Override
        public WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, EntityDamageEvent event, IncreaseType type) {
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
        public WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, EntityDamageEvent event, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return null;
            }
            if (type != IncreaseType.ADDITIVE_PERCENT) {
                return null;
            }
            Collection<LivingEntity> enemies = damager.getLocation().getNearbyLivingEntities(10).stream()
                    .filter(c -> MobListeners.getMobInfo(c) != null
                            && MobListeners.getMobInfo(c).getMobBehavior() != MobCategory.PASSIVE
                            && MobListeners.getMobInfo(c).getMobBehavior() != MobCategory.TAMED
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

        @Override
        public List<EnchantmentType> getIncompatibleEnchants() {
            return List.of(FROST_WALKER);
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

        @Override
        public List<EnchantmentType> getIncompatibleEnchants() {
            return List.of(DEPTH_STRIDER);
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
            if (id == null) {
                return null;
            }
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

    public WeaponStats getEventWeapon(Player damager, LivingEntity target, int level, EntityDamageEvent event, IncreaseType type) {
        return null;
    }

    public ArmorStats getEventArmor(LivingEntity damager, Player target, int level, EntityDamageEvent event, IncreaseType type) {
        return null;
    }

    public AbilityStats getEventAbility(Player damager, LivingEntity target, int level, IncreaseType type) {
        return null;
    }

    public FishingStats getEventFishing(Player fisher, int level, IncreaseType type) {
        return null;
    }

    public MiningStats getEventMining(Player miner, Block block, int level, CustomOreLocation ore, IncreaseType type) {
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

    public EnchantmentObject createObject(int level) {
        return new EnchantmentObject(this, level);
    }

    public List<EnchantmentType> getIncompatibleEnchants() {
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

    public void playerAttackAction(EnchantmentObject enchant, Player attacker, LivingEntity target, double damage, boolean critical, EntityDamageEvent event) {
    }

    public void defenseAction(EnchantmentObject enchant, LivingEntity attacker, Player target, double damage, boolean critical, EntityDamageEvent event) {
    }

}