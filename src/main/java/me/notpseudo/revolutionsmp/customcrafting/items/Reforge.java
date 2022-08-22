package me.notpseudo.revolutionsmp.customcrafting.items;

import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.itemstats.IncreaseType;
import me.notpseudo.revolutionsmp.skills.SkillHolder;
import me.notpseudo.revolutionsmp.skills.SkillType;
import me.notpseudo.revolutionsmp.skills.SkillUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Enum of Reforges that can be applied to a custom item
public enum Reforge {
    EPIC {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD, ItemType.FISHING_ROD, ItemType.FISHING_WEAPON);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 15, 0, 10, 1, 0);
                case UNCOMMON -> new WeaponStats(0, 20, 0, 15, 2, 0);
                case RARE -> new WeaponStats(0, 25, 0, 20, 4, 0);
                case EPIC -> new WeaponStats(0, 32, 0, 27, 7, 0);
                case LEGENDARY -> new WeaponStats(0, 40, 0, 35, 10, 0);
                case MYTHIC, SPECIAL -> new WeaponStats(0, 50, 0, 45, 15, 0);
                case DIVINE -> new WeaponStats(0, 60, 0, 55, 20, 0);
            };
        }


    },
    FAIR {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD, ItemType.FISHING_ROD, ItemType.FISHING_WEAPON);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 2, 2, 2, 2, 0);
                case UNCOMMON -> new WeaponStats(0, 3, 3, 3, 3, 0);
                case RARE -> new WeaponStats(0, 4, 4, 4, 4, 0);
                case EPIC -> new WeaponStats(0, 7, 7, 7, 7, 0);
                case LEGENDARY -> new WeaponStats(0, 10, 10, 10, 10, 0);
                case MYTHIC, SPECIAL, DIVINE -> new WeaponStats(0, 12, 12, 12, 12, 0);
            };
        }

        @Override
        public @NotNull AbilityStats getAbilityStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new AbilityStats(0, 2);
                case UNCOMMON -> new AbilityStats(0, 3);
                case RARE -> new AbilityStats(0, 4);
                case EPIC -> new AbilityStats(0, 7);
                case LEGENDARY -> new AbilityStats(0, 10);
                case MYTHIC, SPECIAL, DIVINE -> new AbilityStats(0, 12);
            };
        }
    },
    FAST {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD, ItemType.FISHING_ROD, ItemType.FISHING_WEAPON);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 0, 0, 0, 10, 0);
                case UNCOMMON -> new WeaponStats(0, 0, 0, 0, 20, 0);
                case RARE -> new WeaponStats(0, 0, 0, 0, 30, 0);
                case EPIC -> new WeaponStats(0, 0, 0, 0, 40, 0);
                case LEGENDARY -> new WeaponStats(0, 0, 0, 0, 50, 0);
                case MYTHIC, SPECIAL, DIVINE -> new WeaponStats(0, 0, 0, 0, 60, 0);
            };
        }
    },
    GENTLE {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD, ItemType.FISHING_ROD, ItemType.FISHING_WEAPON);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 3, 0, 0, 8, 0);
                case UNCOMMON -> new WeaponStats(0, 5, 0, 0, 10, 0);
                case RARE -> new WeaponStats(0, 7, 0, 0, 15, 0);
                case EPIC -> new WeaponStats(0, 10, 0, 0, 20, 0);
                case LEGENDARY -> new WeaponStats(0, 15, 0, 0, 25, 0);
                case MYTHIC, SPECIAL, DIVINE -> new WeaponStats(0, 20, 0, 0, 30, 0);
            };
        }
    },
    HEROIC {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD, ItemType.FISHING_ROD, ItemType.FISHING_WEAPON);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 15, 0, 0, 1, 0);
                case UNCOMMON -> new WeaponStats(0, 20, 0, 0, 2, 0);
                case RARE -> new WeaponStats(0, 25, 0, 0, 2, 0);
                case EPIC -> new WeaponStats(0, 32, 0, 0, 3, 0);
                case LEGENDARY -> new WeaponStats(0, 40, 0, 0, 5, 0);
                case MYTHIC, SPECIAL -> new WeaponStats(0, 50, 0, 0, 7, 0);
                case DIVINE -> new WeaponStats(0, 60, 0, 0, 9, 0);
            };
        }


        @Override
        public @NotNull AbilityStats getAbilityStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new AbilityStats(0, 40);
                case UNCOMMON -> new AbilityStats(0, 50);
                case RARE -> new AbilityStats(0, 65);
                case EPIC -> new AbilityStats(0, 80);
                case LEGENDARY -> new AbilityStats(0, 100);
                case MYTHIC, SPECIAL -> new AbilityStats(0, 125);
                case DIVINE -> new AbilityStats(0, 150);
            };
        }
    },
    LEGENDARY {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD, ItemType.FISHING_ROD, ItemType.FISHING_WEAPON);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 3, 5, 5, 2, 0);
                case UNCOMMON -> new WeaponStats(0, 7, 7, 10, 3, 0);
                case RARE -> new WeaponStats(0, 12, 9, 15, 5, 0);
                case EPIC -> new WeaponStats(0, 18, 12, 22, 7, 0);
                case LEGENDARY -> new WeaponStats(0, 25, 15, 28, 10, 0);
                case MYTHIC, SPECIAL -> new WeaponStats(0, 32, 18, 36, 15, 0);
                case DIVINE -> new WeaponStats(0, 39, 21, 44, 20, 0);
            };
        }


        @Override
        public @NotNull AbilityStats getAbilityStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new AbilityStats(0, 5);
                case UNCOMMON -> new AbilityStats(0, 8);
                case RARE -> new AbilityStats(0, 12);
                case EPIC -> new AbilityStats(0, 18);
                case LEGENDARY -> new AbilityStats(0, 25);
                case MYTHIC, SPECIAL -> new AbilityStats(0, 35);
                case DIVINE -> new AbilityStats(0, 45);
            };
        }
    },
    ODD {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD, ItemType.FISHING_ROD, ItemType.FISHING_WEAPON);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 0, 10, 5, 0, 0);
                case UNCOMMON -> new WeaponStats(0, 0, 12, 10, 0, 0);
                case RARE -> new WeaponStats(0, 0, 15, 15, 0, 0);
                case EPIC -> new WeaponStats(0, 0, 20, 22, 0, 0);
                case LEGENDARY -> new WeaponStats(0, 0, 25, 30, 0, 0);
                case MYTHIC, SPECIAL, DIVINE -> new WeaponStats(0, 0, 30, 40, 0, 0);
            };
        }
    },
    SHARP {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD, ItemType.FISHING_ROD, ItemType.FISHING_WEAPON);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 0, 10, 20, 0, 0);
                case UNCOMMON -> new WeaponStats(0, 0, 12, 30, 0, 0);
                case RARE -> new WeaponStats(0, 0, 14, 40, 0, 0);
                case EPIC -> new WeaponStats(0, 0, 17, 55, 0, 0);
                case LEGENDARY -> new WeaponStats(0, 0, 20, 75, 0, 0);
                case MYTHIC, SPECIAL -> new WeaponStats(0, 0, 25, 90, 0, 0);
                case DIVINE -> new WeaponStats(0, 0, 30, 105, 0, 0);
            };
        }


    },
    SPICY {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD, ItemType.FISHING_ROD, ItemType.FISHING_WEAPON);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 2, 1, 25, 1, 0);
                case UNCOMMON -> new WeaponStats(0, 3, 1, 35, 2, 0);
                case RARE -> new WeaponStats(0, 4, 1, 45, 4, 0);
                case EPIC -> new WeaponStats(0, 7, 1, 60, 7, 0);
                case LEGENDARY -> new WeaponStats(0, 10, 1, 80, 10, 0);
                case MYTHIC, SPECIAL -> new WeaponStats(0, 12, 1, 100, 15, 0);
                case DIVINE -> new WeaponStats(0, 14, 1, 120, 20, 0);
            };
        }


    },
    DIRTY {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD, ItemType.FISHING_ROD, ItemType.FISHING_WEAPON);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 2, 0, 0, 2, 2);
                case UNCOMMON -> new WeaponStats(0, 4, 0, 0, 3, 3);
                case RARE -> new WeaponStats(0, 6, 0, 0, 5, 6);
                case EPIC -> new WeaponStats(0, 10, 0, 0, 10, 9);
                case LEGENDARY -> new WeaponStats(0, 12, 0, 0, 15, 12);
                case MYTHIC, SPECIAL -> new WeaponStats(0, 15, 0, 0, 20, 15);
                case DIVINE -> new WeaponStats(0, 18, 0, 0, 25, 18);
            };
        }


    },
    FABLED {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 30, 0, 15, 0, 0);
                case UNCOMMON -> new WeaponStats(0, 35, 0, 20, 0, 0);
                case RARE -> new WeaponStats(0, 40, 0, 25, 0, 0);
                case EPIC -> new WeaponStats(0, 50, 0, 32, 0, 0);
                case LEGENDARY -> new WeaponStats(0, 60, 0, 40, 0, 0);
                case MYTHIC, SPECIAL -> new WeaponStats(0, 75, 0, 50, 0, 0);
                case DIVINE -> new WeaponStats(0, 90, 0, 60, 0, 0);
            };
        }

        @Override
        public @NotNull WeaponStats getEventWeapon(Rarity rarity, Player attacker, LivingEntity target, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return WeaponStats.createMult();
            }
            if (type == IncreaseType.ADDITIVE_PERCENT) {
                return WeaponStats.createZero();
            }
            if (Math.random() < 0.1) {
                return new WeaponStats(0, 0, 0, 15 - (Math.random() * 15), 0, 0);
            }
            return WeaponStats.createZero();
        }

        @Override
        public boolean hasBonus() {
            return true;
        }

        @Override
        public List<Component> getBonusLore(Rarity rarity) {
            ArrayList<Component> lore = new ArrayList<>();
            lore.add(Component.text("Critical hits have a chance to deal up to", NamedTextColor.GRAY));
            lore.add(Component.text("15% ", NamedTextColor.GREEN).append(Component.text("extra damage", NamedTextColor.GRAY)));
            return lore;
        }
    },
    SUSPICIOUS {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 0, 1, 30, 0, 0);
                case UNCOMMON -> new WeaponStats(0, 0, 2, 40, 0, 0);
                case RARE -> new WeaponStats(0, 0, 3, 50, 0, 0);
                case EPIC -> new WeaponStats(0, 0, 5, 65, 0, 0);
                case LEGENDARY -> new WeaponStats(0, 0, 7, 85, 0, 0);
                case MYTHIC, SPECIAL -> new WeaponStats(0, 0, 10, 110, 0, 0);
                case DIVINE -> new WeaponStats(0, 0, 13, 135, 0, 0);
            };
        }

        @Override
        public boolean hasBonus() {
            return true;
        }

        @Override
        public List<Component> getBonusLore(Rarity rarity) {
            ArrayList<Component> lore = new ArrayList<>();
            lore.add(Component.text("Increase weapon damage by ", NamedTextColor.GRAY).append(Component.text("+15", NamedTextColor.RED)));
            return lore;
        }

        @Override
        public @NotNull WeaponStats getEventWeapon(Rarity rarity, Player attacker, LivingEntity target, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return WeaponStats.createMult();
            }
            if (type == IncreaseType.ADDITIVE_PERCENT) {
                return WeaponStats.createZero();
            }
            return new WeaponStats(15, 0, 0, 0, 0, 0);
        }
    },
    WITHERED {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            int strength = 0;
            Player holder = Bukkit.getPlayer(player);
            if (holder != null) {
                SkillHolder skillContainer = SkillUtils.getHolder(holder);
                strength = (int) skillContainer.getSkill(SkillType.ABYSS).getLevel();
            }
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 60 + strength, 0, 0, 0, 0);
                case UNCOMMON -> new WeaponStats(0, 75 + strength, 0, 0, 0, 0);
                case RARE -> new WeaponStats(0, 90 + strength, 0, 0, 0, 0);
                case EPIC -> new WeaponStats(0, 110 + strength, 0, 0, 0, 0);
                case LEGENDARY -> new WeaponStats(0, 135 + strength, 0, 0, 0, 0);
                case MYTHIC, SPECIAL -> new WeaponStats(0, 170 + strength, 0, 0, 0, 0);
                case DIVINE -> new WeaponStats(0, 205 + strength, 0, 0, 0, 0);
            };
        }

        @Override
        public boolean hasBonus() {
            return true;
        }

        @Override
        public List<Component> getBonusLore(Rarity rarity) {
            return List.of(Component.text("Grants ", NamedTextColor.GRAY).append(Component.text("+1 ", NamedTextColor.GREEN))
                    .append(StatType.STRENGTH.getNameWithSymbol()).append(Component.text("per ", NamedTextColor.GRAY))
                    .append(Component.text("Abyss ", NamedTextColor.DARK_RED)).append(Component.text("level", NamedTextColor.GRAY)));
        }
    },
    BULKY {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }

        @Override
        public @NotNull ArmorStats getArmorStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new ArmorStats(4, 2, 0);
                case UNCOMMON -> new ArmorStats(6, 3, 0);
                case RARE -> new ArmorStats(9, 5, 0);
                case EPIC -> new ArmorStats(12, 8, 0);
                case LEGENDARY -> new ArmorStats(15, 13, 0);
                case MYTHIC, SPECIAL -> new ArmorStats(20, 21, 0);
                case DIVINE -> new ArmorStats(25, 29, 0);
            };
        }

    },
    AWKWARD {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.BOW);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 0, 10, 5, 0, 0);
                case UNCOMMON -> new WeaponStats(0, 0, 12, 10, 0, 0);
                case RARE -> new WeaponStats(0, 0, 15, 15, 0, 0);
                case EPIC -> new WeaponStats(0, 0, 20, 22, 0, 0);
                case LEGENDARY -> new WeaponStats(0, 0, 25, 30, 0, 0);
                case MYTHIC, SPECIAL -> new WeaponStats(0, 0, 30, 35, 0, 0);
                case DIVINE -> new WeaponStats(0, 0, 35, 40, 0, 0);
            };
        }


        @Override
        public @NotNull AbilityStats getAbilityStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new AbilityStats(0, -5);
                case UNCOMMON -> new AbilityStats(0, -10);
                case RARE -> new AbilityStats(0, -18);
                case EPIC -> new AbilityStats(0, -32);
                case LEGENDARY -> new AbilityStats(0, -50);
                case MYTHIC, SPECIAL -> new AbilityStats(0, -72);
                case DIVINE -> new AbilityStats(0, -94);
            };
        }
    },
    DEADLY {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.BOW);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 0, 10, 15, 0, 0);
                case UNCOMMON -> new WeaponStats(0, 0, 13, 10, 0, 0);
                case RARE -> new WeaponStats(0, 0, 16, 18, 0, 0);
                case EPIC -> new WeaponStats(0, 0, 19, 32, 0, 0);
                case LEGENDARY -> new WeaponStats(0, 0, 22, 50, 0, 0);
                case MYTHIC, SPECIAL, DIVINE -> new WeaponStats(0, 0, 25, 78, 0, 0);
            };
        }
    },
    FINE {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.BOW);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 3, 5, 2, 0, 0);
                case UNCOMMON -> new WeaponStats(0, 7, 7, 4, 0, 0);
                case RARE -> new WeaponStats(0, 12, 9, 7, 0, 0);
                case EPIC -> new WeaponStats(0, 18, 12, 10, 0, 0);
                case LEGENDARY -> new WeaponStats(0, 25, 15, 15, 0, 0);
                case MYTHIC, SPECIAL, DIVINE -> new WeaponStats(0, 33, 18, 20, 0, 0);
            };
        }
    },
    GRAND {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.BOW);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 25, 0, 0, 0, 0);
                case UNCOMMON -> new WeaponStats(0, 32, 0, 0, 0, 0);
                case RARE -> new WeaponStats(0, 40, 0, 0, 0, 0);
                case EPIC -> new WeaponStats(0, 50, 0, 0, 0, 0);
                case LEGENDARY -> new WeaponStats(0, 60, 0, 0, 0, 0);
                case MYTHIC, SPECIAL -> new WeaponStats(0, 75, 0, 0, 0, 0);
                case DIVINE -> new WeaponStats(0, 90, 0, 0, 0, 0);
            };
        }


    },
    HASTY {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.BOW);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 3, 20, 0, 0, 0);
                case UNCOMMON -> new WeaponStats(0, 5, 25, 0, 0, 0);
                case RARE -> new WeaponStats(0, 7, 30, 0, 0, 0);
                case EPIC -> new WeaponStats(0, 10, 40, 0, 0, 0);
                case LEGENDARY -> new WeaponStats(0, 15, 50, 0, 0, 0);
                case MYTHIC, SPECIAL -> new WeaponStats(0, 20, 75, 0, 0, 0);
                case DIVINE -> new WeaponStats(0, 25, 100, 0, 0, 0);
            };
        }


    },
    NEAT {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.BOW);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 0, 10, 4, 0, 0);
                case UNCOMMON -> new WeaponStats(0, 0, 12, 8, 0, 0);
                case RARE -> new WeaponStats(0, 0, 14, 14, 0, 0);
                case EPIC -> new WeaponStats(0, 0, 17, 20, 0, 0);
                case LEGENDARY -> new WeaponStats(0, 0, 20, 30, 0, 0);
                case MYTHIC, SPECIAL, DIVINE -> new WeaponStats(0, 0, 25, 40, 0, 0);
            };
        }

        @Override
        public @NotNull AbilityStats getAbilityStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new AbilityStats(0, 3);
                case UNCOMMON -> new AbilityStats(0, 6);
                case RARE -> new AbilityStats(0, 10);
                case EPIC -> new AbilityStats(0, 15);
                case LEGENDARY -> new AbilityStats(0, 20);
                case MYTHIC, SPECIAL, DIVINE -> new AbilityStats(0, 30);
            };
        }
    },
    RAPID {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.BOW);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 2, 0, 35, 0, 0);
                case UNCOMMON -> new WeaponStats(0, 3, 0, 45, 0, 0);
                case RARE -> new WeaponStats(0, 4, 0, 55, 0, 0);
                case EPIC -> new WeaponStats(0, 7, 0, 65, 0, 0);
                case LEGENDARY -> new WeaponStats(0, 10, 0, 75, 0, 0);
                case MYTHIC, SPECIAL -> new WeaponStats(0, 15, 0, 90, 0, 0);
                case DIVINE -> new WeaponStats(0, 20, 0, 105, 0, 0);
            };
        }


    },
    RICH {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.BOW);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 0, 10, 2, 0, 0);
                case UNCOMMON -> new WeaponStats(0, 0, 12, 4, 0, 0);
                case RARE -> new WeaponStats(0, 0, 14, 7, 0, 0);
                case EPIC -> new WeaponStats(0, 0, 17, 10, 0, 0);
                case LEGENDARY -> new WeaponStats(0, 0, 20, 15, 0, 0);
                case MYTHIC, SPECIAL, DIVINE -> new WeaponStats(0, 0, 25, 20, 0, 0);
            };
        }

        @Override
        public @NotNull AbilityStats getAbilityStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new AbilityStats(0, 3);
                case UNCOMMON -> new AbilityStats(0, 5);
                case RARE -> new AbilityStats(0, 6);
                case EPIC -> new AbilityStats(0, 15);
                case LEGENDARY -> new AbilityStats(0, 20);
                case MYTHIC, SPECIAL, DIVINE -> new AbilityStats(0, 30);
            };
        }
    },
    UNREAL {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.BOW);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 3, 8, 5, 0, 0);
                case UNCOMMON -> new WeaponStats(0, 7, 9, 10, 0, 0);
                case RARE -> new WeaponStats(0, 12, 10, 18, 0, 0);
                case EPIC -> new WeaponStats(0, 18, 11, 32, 0, 0);
                case LEGENDARY -> new WeaponStats(0, 25, 13, 50, 0, 0);
                case MYTHIC, SPECIAL -> new WeaponStats(0, 34, 15, 70, 0, 0);
                case DIVINE -> new WeaponStats(0, 43, 17, 90, 0, 0);
            };
        }


    },
    PRECISE {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.BOW);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 3, 8, 5, 0, 0);
                case UNCOMMON -> new WeaponStats(0, 7, 9, 10, 0, 0);
                case RARE -> new WeaponStats(0, 12, 10, 18, 0, 0);
                case EPIC -> new WeaponStats(0, 18, 11, 32, 0, 0);
                case LEGENDARY -> new WeaponStats(0, 25, 13, 50, 0, 0);
                case MYTHIC, SPECIAL -> new WeaponStats(0, 34, 15, 70, 0, 0);
                case DIVINE -> new WeaponStats(0, 43, 17, 90, 0, 0);
            };
        }


    },
    SPIRITUAL {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.BOW);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 4, 7, 10, 0, 0);
                case UNCOMMON -> new WeaponStats(0, 8, 8, 15, 0, 0);
                case RARE -> new WeaponStats(0, 14, 9, 23, 0, 0);
                case EPIC -> new WeaponStats(0, 20, 10, 37, 0, 0);
                case LEGENDARY -> new WeaponStats(0, 28, 12, 55, 0, 0);
                case MYTHIC, SPECIAL -> new WeaponStats(0, 38, 14, 75, 0, 0);
                case DIVINE -> new WeaponStats(0, 48, 16, 95, 0, 0);
            };
        }


    },
    HEADSTRONG {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.BOW);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 2, 10, 4, 0, 0);
                case UNCOMMON -> new WeaponStats(0, 5, 11, 8, 0, 0);
                case RARE -> new WeaponStats(0, 10, 12, 16, 0, 0);
                case EPIC -> new WeaponStats(0, 16, 13, 28, 0, 0);
                case LEGENDARY -> new WeaponStats(0, 23, 15, 42, 0, 0);
                case MYTHIC, SPECIAL, DIVINE -> new WeaponStats(0, 33, 17, 60, 0, 0);
            };
        }
    },
    CLEAN {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 0, 2, 0, 0, 0);
                case UNCOMMON -> new WeaponStats(0, 0, 4, 0, 0, 0);
                case RARE -> new WeaponStats(0, 0, 6, 0, 0, 0);
                case EPIC -> new WeaponStats(0, 0, 8, 0, 0, 0);
                case LEGENDARY -> new WeaponStats(0, 0, 10, 0, 0, 0);
                case MYTHIC, SPECIAL, DIVINE -> new WeaponStats(0, 0, 12, 0, 0, 0);
            };
        }

        @Override
        public @NotNull ArmorStats getArmorStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new ArmorStats(5, 5, 0);
                case UNCOMMON -> new ArmorStats(7, 7, -0);
                case RARE -> new ArmorStats(10, 10, -0);
                case EPIC -> new ArmorStats(15, 15, -0);
                case LEGENDARY -> new ArmorStats(20, 20, -0);
                case MYTHIC, SPECIAL, DIVINE -> new ArmorStats(25, 25, -0);
            };
        }

    },
    FIERCE {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 2, 2, 4, 0, 0);
                case UNCOMMON -> new WeaponStats(0, 4, 3, 7, 0, 0);
                case RARE -> new WeaponStats(0, 6, 4, 10, 0, 0);
                case EPIC -> new WeaponStats(0, 8, 5, 14, 0, 0);
                case LEGENDARY -> new WeaponStats(0, 10, 6, 18, 0, 0);
                case MYTHIC, SPECIAL -> new WeaponStats(0, 12, 8, 24, 0, 0);
                case DIVINE -> new WeaponStats(0, 14, 10, 30, 0, 0);
            };
        }


    },
    HEAVY {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 0, 0, -1, 0, 0);
                case UNCOMMON, RARE -> new WeaponStats(0, 0, 0, -2, 0, 0);
                case EPIC -> new WeaponStats(0, 0, 0, -3, 0, 0);
                case LEGENDARY -> new WeaponStats(0, 0, 0, -5, 0, 0);
                case MYTHIC, SPECIAL -> new WeaponStats(0, 0, 0, -7, 0, 0);
                case DIVINE -> new WeaponStats(0, 0, 0, -9, 0, 0);
            };
        }

        @Override
        public @NotNull ArmorStats getArmorStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new ArmorStats(0, 25, -1);
                case UNCOMMON -> new ArmorStats(0, 35, -1);
                case RARE -> new ArmorStats(0, 50, -1);
                case EPIC -> new ArmorStats(0, 65, -1);
                case LEGENDARY -> new ArmorStats(0, 80, -1);
                case MYTHIC, SPECIAL -> new ArmorStats(0, 110, -1);
                case DIVINE -> new ArmorStats(0, 140, -1);
            };
        }

    },
    LIGHT {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 0, 1, 1, 1, 0);
                case UNCOMMON -> new WeaponStats(0, 0, 1, 2, 2, 0);
                case RARE -> new WeaponStats(0, 0, 2, 3, 3, 0);
                case EPIC -> new WeaponStats(0, 0, 2, 4, 4, 0);
                case LEGENDARY -> new WeaponStats(0, 0, 3, 5, 5, 0);
                case MYTHIC, SPECIAL -> new WeaponStats(0, 0, 3, 6, 6, 0);
                case DIVINE -> new WeaponStats(0, 0, 4, 7, 7, 0);
            };
        }

        @Override
        public @NotNull ArmorStats getArmorStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new ArmorStats(5, 1, 1);
                case UNCOMMON -> new ArmorStats(7, 2, 2);
                case RARE -> new ArmorStats(10, 3, 3);
                case EPIC -> new ArmorStats(15, 4, 4);
                case LEGENDARY -> new ArmorStats(20, 5, 5);
                case MYTHIC, SPECIAL -> new ArmorStats(25, 6, 6);
                case DIVINE -> new ArmorStats(30, 7, 7);
            };
        }

    },
    MYTHIC {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 2, 1, 0, 0, 0);
                case UNCOMMON -> new WeaponStats(0, 4, 2, 0, 0, 0);
                case RARE -> new WeaponStats(0, 6, 3, 0, 0, 0);
                case EPIC -> new WeaponStats(0, 8, 4, 0, 0, 0);
                case LEGENDARY -> new WeaponStats(0, 10, 5, 0, 0, 0);
                case MYTHIC, SPECIAL -> new WeaponStats(0, 12, 6, 0, 0, 0);
                case DIVINE -> new WeaponStats(0, 14, 7, 0, 0, 0);
            };
        }

        @Override
        public @NotNull ArmorStats getArmorStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new ArmorStats(2, 2, 2);
                case UNCOMMON -> new ArmorStats(4, 4, 2);
                case RARE -> new ArmorStats(6, 6, 2);
                case EPIC -> new ArmorStats(8, 8, 2);
                case LEGENDARY -> new ArmorStats(10, 10, 2);
                case MYTHIC, SPECIAL -> new ArmorStats(12, 12, 2);
                case DIVINE -> new ArmorStats(14, 14, 2);
            };
        }

        @Override
        public @NotNull AbilityStats getAbilityStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new AbilityStats(0, 20);
                case UNCOMMON -> new AbilityStats(0, 25);
                case RARE -> new AbilityStats(0, 30);
                case EPIC -> new AbilityStats(0, 40);
                case LEGENDARY -> new AbilityStats(0, 50);
                case MYTHIC, SPECIAL -> new AbilityStats(0, 60);
                case DIVINE -> new AbilityStats(0, 70);
            };
        }
    },
    PURE {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 2, 2, 2, 1, 0);
                case UNCOMMON -> new WeaponStats(0, 3, 4, 3, 1, 0);
                case RARE -> new WeaponStats(0, 4, 6, 4, 2, 0);
                case EPIC -> new WeaponStats(0, 6, 8, 6, 3, 0);
                case LEGENDARY -> new WeaponStats(0, 8, 10, 8, 4, 0);
                case MYTHIC, DIVINE, SPECIAL -> new WeaponStats(0, 10, 12, 10, 5, 0);
            };
        }

        @Override
        public @NotNull ArmorStats getArmorStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new ArmorStats(2, 2, 0);
                case UNCOMMON -> new ArmorStats(3, 3, 0);
                case RARE -> new ArmorStats(4, 4, 0);
                case EPIC -> new ArmorStats(6, 6, 0);
                case LEGENDARY -> new ArmorStats(8, 8, 0);
                case MYTHIC, SPECIAL, DIVINE -> new ArmorStats(10, 10, 0);
            };
        }

        @Override
        public @NotNull AbilityStats getAbilityStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new AbilityStats(0, 2);
                case UNCOMMON -> new AbilityStats(0, 3);
                case RARE -> new AbilityStats(0, 4);
                case EPIC -> new AbilityStats(0, 6);
                case LEGENDARY -> new AbilityStats(0, 8);
                case MYTHIC, SPECIAL, DIVINE -> new AbilityStats(0, 10);
            };
        }
    },
    TITANIC {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public @NotNull ArmorStats getArmorStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new ArmorStats(10, 10, 0);
                case UNCOMMON -> new ArmorStats(15, 15, 0);
                case RARE -> new ArmorStats(20, 20, 0);
                case EPIC -> new ArmorStats(25, 25, 0);
                case LEGENDARY -> new ArmorStats(35, 35, 0);
                case MYTHIC, SPECIAL -> new ArmorStats(50, 50, 0);
                case DIVINE -> new ArmorStats(65, 65, 0);
            };
        }

    },
    SMART {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public @NotNull ArmorStats getArmorStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new ArmorStats(4, 4, 0);
                case UNCOMMON -> new ArmorStats(6, 6, 0);
                case RARE -> new ArmorStats(9, 9, 0);
                case EPIC -> new ArmorStats(12, 12, 0);
                case LEGENDARY -> new ArmorStats(15, 15, 0);
                case MYTHIC, SPECIAL -> new ArmorStats(20, 20, 0);
                case DIVINE -> new ArmorStats(25, 25, 0);
            };
        }

        @Override
        public @NotNull AbilityStats getAbilityStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new AbilityStats(0, 25);
                case UNCOMMON -> new AbilityStats(0, 50);
                case RARE -> new AbilityStats(0, 75);
                case EPIC -> new AbilityStats(0, 100);
                case LEGENDARY -> new AbilityStats(0, 125);
                case MYTHIC, SPECIAL -> new AbilityStats(0, 150);
                case DIVINE -> new AbilityStats(0, 175);
            };
        }
    },
    WISE {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public @NotNull ArmorStats getArmorStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new ArmorStats(6, 0, 1);
                case UNCOMMON -> new ArmorStats(8, 0, 1);
                case RARE -> new ArmorStats(10, 0, 1);
                case EPIC -> new ArmorStats(12, 0, 2);
                case LEGENDARY -> new ArmorStats(15, 0, 2);
                case MYTHIC, SPECIAL -> new ArmorStats(20, 0, 3);
                case DIVINE -> new ArmorStats(25, 0, 4);
            };
        }

        @Override
        public @NotNull AbilityStats getAbilityStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new AbilityStats(0, 25);
                case UNCOMMON -> new AbilityStats(0, 50);
                case RARE -> new AbilityStats(0, 75);
                case EPIC -> new AbilityStats(0, 100);
                case LEGENDARY -> new AbilityStats(0, 125);
                case MYTHIC, SPECIAL -> new AbilityStats(0, 150);
                case DIVINE -> new AbilityStats(0, 175);
            };
        }
    },
    SUBMERGED {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 0, 2, 0, 0, 0);
                case UNCOMMON -> new WeaponStats(0, 0, 4, 0, 0, 0);
                case RARE -> new WeaponStats(0, 0, 6, 0, 0, 0);
                case EPIC -> new WeaponStats(0, 0, 8, 0, 0, 0);
                case LEGENDARY -> new WeaponStats(0, 0, 10, 0, 0, 0);
                case MYTHIC, DIVINE, SPECIAL -> new WeaponStats(0, 0, 12, 0, 0, 0);
            };
        }

        @Override
        public @NotNull FishingStats getFishingStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new FishingStats(0.5, 0);
                case UNCOMMON -> new FishingStats(0.6, 0);
                case RARE -> new FishingStats(0.7, 0);
                case EPIC -> new FishingStats(0.8, 0);
                case LEGENDARY -> new FishingStats(0.9, 0);
                case MYTHIC, SPECIAL, DIVINE -> new FishingStats(1, 0);
            };
        }
    },
    PERFECT {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public @NotNull ArmorStats getArmorStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new ArmorStats(0, 25, 0);
                case UNCOMMON -> new ArmorStats(0, 35, 0);
                case RARE -> new ArmorStats(0, 50, 0);
                case EPIC -> new ArmorStats(0, 65, 0);
                case LEGENDARY -> new ArmorStats(0, 80, 0);
                case MYTHIC, SPECIAL -> new ArmorStats(0, 110, 0);
                case DIVINE -> new ArmorStats(0, 140, 0);
            };
        }

        @Override
        public boolean hasBonus() {
            return true;
        }

        @Override
        public List<Component> getBonusLore(Rarity rarity) {
            return List.of((Component.text("Increases ", NamedTextColor.GRAY).append(StatType.DEFENSE.getNameWithSymbol()).append(Component.text("by ", NamedTextColor.GRAY)).append(Component.text("+2%", NamedTextColor.GREEN))));
        }

        @Override
        public @NotNull ArmorStats getBonusArmor(Rarity rarity, Player player, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return ArmorStats.createMult();
            }
            if (type == IncreaseType.INCREASE) {
                return ArmorStats.createZero();
            }
            return new ArmorStats(0, 2, 0);
        }
    },
    REINFORCED {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public @NotNull ArmorStats getArmorStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new ArmorStats(0, 25, 0);
                case UNCOMMON -> new ArmorStats(0, 35, 0);
                case RARE -> new ArmorStats(0, 50, 0);
                case EPIC -> new ArmorStats(0, 65, 0);
                case LEGENDARY -> new ArmorStats(0, 80, 0);
                case MYTHIC, SPECIAL, DIVINE -> new ArmorStats(0, 110, 0);
            };
        }
    },
    RENOWNED {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 3, 2, 3, 1, 0);
                case UNCOMMON -> new WeaponStats(0, 4, 4, 4, 1, 0);
                case RARE -> new WeaponStats(0, 6, 6, 6, 2, 0);
                case EPIC -> new WeaponStats(0, 8, 8, 8, 3, 0);
                case LEGENDARY -> new WeaponStats(0, 10, 10, 10, 4, 0);
                case MYTHIC, SPECIAL -> new WeaponStats(0, 12, 12, 12, 5, 0);
                case DIVINE -> new WeaponStats(0, 14, 14, 14, 6, 0);
            };
        }

        @Override
        public @NotNull ArmorStats getArmorStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new ArmorStats(2, 2, 1);
                case UNCOMMON -> new ArmorStats(3, 3, 1);
                case RARE -> new ArmorStats(4, 4, 1);
                case EPIC -> new ArmorStats(6, 6, 1);
                case LEGENDARY -> new ArmorStats(8, 8, 1);
                case MYTHIC, SPECIAL -> new ArmorStats(10, 10, 1);
                case DIVINE -> new ArmorStats(12, 12, 1);
            };
        }

        @Override
        public @NotNull AbilityStats getAbilityStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new AbilityStats(0, 3);
                case UNCOMMON -> new AbilityStats(0, 4);
                case RARE -> new AbilityStats(0, 6);
                case EPIC -> new AbilityStats(0, 8);
                case LEGENDARY -> new AbilityStats(0, 10);
                case MYTHIC, SPECIAL -> new AbilityStats(0, 12);
                case DIVINE -> new AbilityStats(0, 14);
            };
        }

        @Override
        public boolean hasBonus() {
            return true;
        }

        @Override
        public List<Component> getBonusLore(Rarity rarity) {
            return List.of(Component.text("Increases most stats by ", NamedTextColor.GRAY).append(Component.text("1%", NamedTextColor.GREEN)));
        }

        @Override
        public @NotNull WeaponStats getBonusWeapon(Rarity rarity, Player player, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return new WeaponStats(1.01, 1.01, 1.01, 1.01, 1.01, 1.01);
            }
            return WeaponStats.createZero();
        }

        @Override
        public @NotNull ArmorStats getBonusArmor(Rarity rarity, Player player, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return new ArmorStats(1, 1.01, 1.01, 1.01);
            }
            return ArmorStats.createZero();
        }

        @Override
        public @NotNull AbilityStats getBonusAbility(Rarity rarity, Player player, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return new AbilityStats(1.01, 1.01);
            }
            return AbilityStats.createZero();
        }

        @Override
        public @NotNull FishingStats getBonusFishing(Rarity rarity, Player fisher, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return new FishingStats(1.01, 1);
            }
            return FishingStats.createZero();
        }

        @Override
        public @NotNull MiningStats getBonusMining(Rarity rarity, Player miner, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return new MiningStats(1.01, 1.01, 1.01);
            }
            return MiningStats.createZero();
        }

        @Override
        public @NotNull GatheringStats getBonusGathering(Rarity rarity, Player harvester, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return new GatheringStats(1.01, 1.01);
            }
            return GatheringStats.createZero();
        }

        @Override
        public @NotNull LuckStats getBonusLuck(Rarity rarity, Player attacker, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return new LuckStats(1.01, 1.01);
            }
            return LuckStats.createZero();
        }
    },
    SPIKED {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 3, 2, 3, 1, 0);
                case UNCOMMON -> new WeaponStats(0, 4, 4, 4, 1, 0);
                case RARE -> new WeaponStats(0, 6, 6, 6, 2, 0);
                case EPIC -> new WeaponStats(0, 8, 8, 8, 3, 0);
                case LEGENDARY -> new WeaponStats(0, 10, 10, 10, 4, 0);
                case MYTHIC, SPECIAL -> new WeaponStats(0, 12, 12, 12, 5, 0);
                case DIVINE -> new WeaponStats(0, 14, 14, 14, 6, 0);
            };
        }

        @Override
        public @NotNull ArmorStats getArmorStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new ArmorStats(2, 2, 1);
                case UNCOMMON -> new ArmorStats(3, 3, 1);
                case RARE -> new ArmorStats(4, 4, 1);
                case EPIC -> new ArmorStats(6, 6, 1);
                case LEGENDARY -> new ArmorStats(8, 8, 1);
                case MYTHIC, SPECIAL -> new ArmorStats(10, 10, 1);
                case DIVINE -> new ArmorStats(12, 12, 1);
            };
        }

        @Override
        public @NotNull AbilityStats getAbilityStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new AbilityStats(0, 3);
                case UNCOMMON -> new AbilityStats(0, 4);
                case RARE -> new AbilityStats(0, 6);
                case EPIC -> new AbilityStats(0, 8);
                case LEGENDARY -> new AbilityStats(0, 10);
                case MYTHIC, SPECIAL -> new AbilityStats(0, 12);
                case DIVINE -> new AbilityStats(0, 14);
            };
        }
    },
    HYPER {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 2, 0, 0, 2, 0);
                case UNCOMMON -> new WeaponStats(0, 4, 0, 0, 3, 0);
                case RARE -> new WeaponStats(0, 6, 0, 0, 4, 0);
                case EPIC -> new WeaponStats(0, 7, 0, 0, 5, 0);
                case LEGENDARY -> new WeaponStats(0, 10, 0, 0, 6, 0);
                case MYTHIC, SPECIAL, DIVINE -> new WeaponStats(0, 12, 0, 0, 7, 0);
            };
        }

        @Override
        public @NotNull ArmorStats getArmorStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON, UNCOMMON -> new ArmorStats(0, 0, 1);
                case RARE, EPIC -> new ArmorStats(0, 0, 2);
                case LEGENDARY, MYTHIC, SPECIAL, DIVINE -> new ArmorStats(0, 0, 3);
            };
        }
    },
    GIANT {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public @NotNull ArmorStats getArmorStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new ArmorStats(50, 0, 0);
                case UNCOMMON -> new ArmorStats(60, 0, 0);
                case RARE -> new ArmorStats(80, 0, 0);
                case EPIC -> new ArmorStats(120, 0, 0);
                case LEGENDARY -> new ArmorStats(180, 0, 0);
                case MYTHIC, SPECIAL -> new ArmorStats(240, 0, 0);
                case DIVINE -> new ArmorStats(300, 0, 0);
            };
        }

    },
    JADED {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public @NotNull MiningStats getMiningStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new MiningStats(5, 5, 0);
                case UNCOMMON -> new MiningStats(12, 10, 0);
                case RARE -> new MiningStats(20, 15, 0);
                case EPIC -> new MiningStats(30, 20, 0);
                case LEGENDARY -> new MiningStats(45, 25, 0);
                case MYTHIC -> new MiningStats(60, 30, 0);
                case DIVINE, SPECIAL -> new MiningStats(75, 35, 0);
            };
        }
    },
    CUBIC {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 3, 0, 0, 0, 0);
                case UNCOMMON -> new WeaponStats(0, 5, 0, 0, 0, 0);
                case RARE -> new WeaponStats(0, 7, 0, 0, 0, 0);
                case EPIC -> new WeaponStats(0, 10, 0, 0, 0, 0);
                case LEGENDARY -> new WeaponStats(0, 12, 0, 0, 0, 0);
                case MYTHIC, SPECIAL, DIVINE -> new WeaponStats(0, 15, 0, 0, 0, 0);
            };
        }

        @Override
        public @NotNull ArmorStats getArmorStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new ArmorStats(5, 0, 0);
                case UNCOMMON -> new ArmorStats(7, 0, 0);
                case RARE -> new ArmorStats(10, 0, 0);
                case EPIC -> new ArmorStats(15, 0, 0);
                case LEGENDARY -> new ArmorStats(20, 0, 0);
                case MYTHIC, SPECIAL, DIVINE -> new ArmorStats(25, 0, 0);
            };
        }

        @Override
        public boolean hasBonus() {
            return true;
        }

        @Override
        public List<Component> getBonusLore(Rarity rarity) {
            return List.of(Component.text("Decreases damage taken from Nether mobs by ", NamedTextColor.GRAY).append(Component.text("2%", NamedTextColor.GREEN)));
        }

        @Override
        public @NotNull ArmorStats getEventArmor(Rarity rarity, LivingEntity attacker, Player target, IncreaseType type) {
            if (type != IncreaseType.MULTIPLICATIVE_PERCENT) {
                return ArmorStats.createZero();
            }
            EntityType entityType = attacker.getType();
            if (entityType != EntityType.BLAZE || entityType != EntityType.GHAST || entityType != EntityType.HOGLIN ||
                    entityType != EntityType.MAGMA_CUBE || entityType != EntityType.PIGLIN || entityType != EntityType.PIGLIN_BRUTE ||
                    entityType != EntityType.WITHER_SKELETON || entityType != EntityType.ZOMBIFIED_PIGLIN) {
                return ArmorStats.createMult();
            }
            return new ArmorStats(0.98, 1, 1);
        }
    },
    NECROTIC {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }


        @Override
        public @NotNull AbilityStats getAbilityStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new AbilityStats(0, 30);
                case UNCOMMON -> new AbilityStats(0, 60);
                case RARE -> new AbilityStats(0, 90);
                case EPIC -> new AbilityStats(0, 120);
                case LEGENDARY -> new AbilityStats(0, 150);
                case MYTHIC, SPECIAL -> new AbilityStats(0, 200);
                case DIVINE -> new AbilityStats(0, 250);
            };
        }
    },
    EMPOWERED {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public @NotNull ArmorStats getArmorStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new ArmorStats(10, 10, 0);
                case UNCOMMON -> new ArmorStats(15, 15, 0);
                case RARE -> new ArmorStats(20, 20, 0);
                case EPIC -> new ArmorStats(25, 25, 0);
                case LEGENDARY -> new ArmorStats(35, 35, 0);
                case MYTHIC, SPECIAL, DIVINE -> new ArmorStats(50, 50, 0);
            };
        }
    },
    ANCIENT {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            int critDamage = 0;
            if (player != null) {
                Player holder = Bukkit.getPlayer(player);
                if (holder != null) {
                    SkillHolder skillContainer = SkillUtils.getHolder(holder);
                    critDamage = (int) skillContainer.getSkill(SkillType.ABYSS).getLevel();
                }
            }
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 4, 3, critDamage, 0, 0);
                case UNCOMMON -> new WeaponStats(0, 8, 5, critDamage, 0, 0);
                case RARE -> new WeaponStats(0, 12, 7, critDamage, 0, 0);
                case EPIC -> new WeaponStats(0, 18, 9, critDamage, 0, 0);
                case LEGENDARY -> new WeaponStats(0, 25, 12, critDamage, 0, 0);
                case MYTHIC, SPECIAL -> new WeaponStats(0, 35, 15, critDamage, 0, 0);
                case DIVINE -> new WeaponStats(0, 45, 18, critDamage, 0, 0);
            };
        }

        @Override
        public @NotNull ArmorStats getArmorStats(Rarity rarity, UUID player) {
            return new ArmorStats(7, 7, 0);
        }

        @Override
        public @NotNull AbilityStats getAbilityStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new AbilityStats(0, 6);
                case UNCOMMON -> new AbilityStats(0, 9);
                case RARE -> new AbilityStats(0, 12);
                case EPIC -> new AbilityStats(0, 16);
                case LEGENDARY -> new AbilityStats(0, 20);
                case MYTHIC, SPECIAL -> new AbilityStats(0, 25);
                case DIVINE -> new AbilityStats(0, 30);
            };
        }

        @Override
        public boolean hasBonus() {
            return true;
        }

        @Override
        public List<Component> getBonusLore(Rarity rarity) {
            return List.of(Component.text("Grants ", NamedTextColor.GRAY).append(Component.text("+1 ", NamedTextColor.GREEN))
                    .append(StatType.CRIT_DAMAGE.getNameWithSymbol()).append(Component.text("per ", NamedTextColor.GRAY))
                    .append(Component.text("Abyss ", NamedTextColor.DARK_RED)).append(Component.text("level", NamedTextColor.GRAY)));
        }
    },
    UNDEAD {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 1, 0, 0, 1, 0);
                case UNCOMMON -> new WeaponStats(0, 2, 0, 0, 2, 0);
                case RARE -> new WeaponStats(0, 2, 0, 0, 3, 0);
                case EPIC -> new WeaponStats(0, 3, 0, 0, 4, 0);
                case LEGENDARY -> new WeaponStats(0, 5, 0, 0, 5, 0);
                case MYTHIC, SPECIAL, DIVINE -> new WeaponStats(0, 7, 0, 0, 6, 0);
            };
        }

        @Override
        public @NotNull ArmorStats getArmorStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new ArmorStats(6, 6, 0);
                case UNCOMMON -> new ArmorStats(8, 8, 0);
                case RARE -> new ArmorStats(12, 12, 0);
                case EPIC -> new ArmorStats(18, 18, 0);
                case LEGENDARY -> new ArmorStats(25, 25, 0);
                case MYTHIC, SPECIAL, DIVINE -> new ArmorStats(33, 33, 0);
            };
        }

        @Override
        public boolean hasBonus() {
            return true;
        }

        @Override
        public List<Component> getBonusLore(Rarity rarity) {
            return List.of(Component.text("Decreases damage taken from Undead mobs by ", NamedTextColor.GRAY).append(Component.text("25", NamedTextColor.GREEN)));
        }

        @Override
        public @NotNull ArmorStats getEventArmor(Rarity rarity, LivingEntity attacker, Player target, IncreaseType type) {
            if (type != IncreaseType.MULTIPLICATIVE_PERCENT) {
                return ArmorStats.createZero();
            }
            if (attacker.getCategory() != EntityCategory.UNDEAD) {
                return ArmorStats.createMult();
            }
            return new ArmorStats(0.98, 1, 1);
        }
    },
    LOVING {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.CHESTPLATE);
        }

        @Override
        public @NotNull ArmorStats getArmorStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new ArmorStats(4, 4, 0);
                case UNCOMMON -> new ArmorStats(5, 5, 0);
                case RARE -> new ArmorStats(6, 6, 0);
                case EPIC -> new ArmorStats(8, 7, 0);
                case LEGENDARY -> new ArmorStats(10, 10, 0);
                case MYTHIC, SPECIAL -> new ArmorStats(14, 14, 0);
                case DIVINE -> new ArmorStats(18, 18, 0);
            };
        }

        @Override
        public @NotNull AbilityStats getAbilityStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new AbilityStats(0, 20);
                case UNCOMMON -> new AbilityStats(0, 40);
                case RARE -> new AbilityStats(0, 60);
                case EPIC -> new AbilityStats(0, 80);
                case LEGENDARY -> new AbilityStats(0, 100);
                case MYTHIC, SPECIAL -> new AbilityStats(0, 120);
                case DIVINE -> new AbilityStats(0, 140);
            };
        }

        @Override
        public boolean hasBonus() {
            return true;
        }

        @Override
        public List<Component> getBonusLore(Rarity rarity) {
            return List.of(Component.text("Increases ", NamedTextColor.GRAY).append(StatType.ABILITY_DAMAGE.getNameWithSymbol())
                    .append(Component.text("by ", NamedTextColor.GRAY)).append(Component.text("5%", NamedTextColor.GREEN)));
        }

        @Override
        public @NotNull AbilityStats getEventAbility(Rarity rarity, Player attacker, LivingEntity target, IncreaseType type) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return new AbilityStats(1.05, 1);
            }
            return AbilityStats.createZero();
        }
    },
    RIDICULOUS {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new WeaponStats(0, 0, 1, 0, 0, 0);
                case UNCOMMON -> new WeaponStats(0, 0, 2, 0, 0, 0);
                case RARE -> new WeaponStats(0, 0, 3, 0, 0, 0);
                case EPIC -> new WeaponStats(0, 0, 4, 0, 0, 0);
                case LEGENDARY -> new WeaponStats(0, 0, 5, 0, 0, 0);
                case MYTHIC, SPECIAL, DIVINE -> new WeaponStats(0, 6, 0, 0, 0, 0);
            };
        }

        @Override
        public @NotNull ArmorStats getArmorStats(Rarity rarity, UUID player) {
            return switch (rarity) {
                case COMMON -> new ArmorStats(10, 10, 0);
                case UNCOMMON -> new ArmorStats(15, 15, 0);
                case RARE -> new ArmorStats(20, 20, 0);
                case EPIC -> new ArmorStats(25, 25, 0);
                case LEGENDARY -> new ArmorStats(35, 35, 0);
                case MYTHIC, SPECIAL, DIVINE -> new ArmorStats(50, 50, 0);
            };
        }
    },
    ARMORTEST {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return new WeaponStats(0, 100, 100, 100, 100, 100);
        }

        @Override
        public @NotNull ArmorStats getArmorStats(Rarity rarity, UUID player) {
            return new ArmorStats(250, 250, 100);
        }

        @Override
        public @NotNull AbilityStats getAbilityStats(Rarity rarity, UUID player) {
            return new AbilityStats(100, 500);
        }
    },
    WEAPONTEST {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD, ItemType.BOW);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return new WeaponStats(100, 100, 100, 100, 100, 100);
        }


        @Override
        public @NotNull AbilityStats getAbilityStats(Rarity rarity, UUID player) {
            return new AbilityStats(100, 500);
        }
    },
    FEROTEST {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD, ItemType.BOW, ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return new WeaponStats(0, 5, 25, 5, 0, 100);
        }


    },
    NODAMAGE {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD, ItemType.BOW, ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public @NotNull WeaponStats getWeaponStats(Rarity rarity, UUID player) {
            return new WeaponStats(0, 10, 30, 5, 20, 5);
        }
    },
    BLESSED {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.AXE, ItemType.HOE);
        }
    };

    // Gets list of ItemTypes the Reforge can be applied to
    public abstract List<ItemType> getItemTypes();

    public String getName() {
        return ItemEditor.getStringFromEnum(this);
    }

    public boolean hasBonus() {
        return false;
    }

    public ItemStack getReforgeStone() {
        return null;
    }

    public List<Component> getBonusLore(Rarity rarity) {
        return new ArrayList<>();
    }

    // Gets the weapon stats the Reforge boosts
    @NotNull
    public WeaponStats getWeaponStats(Rarity rarity, UUID player) {
        return WeaponStats.createZero();
    }

    // Gets the armor stats the Reforge boosts
    @NotNull
    public ArmorStats getArmorStats(Rarity rarity, UUID player) {
        return ArmorStats.createZero();
    }

    // Gets the ability statsd the Reforge boosts
    @NotNull
    public AbilityStats getAbilityStats(Rarity rarity, UUID player) {
        return AbilityStats.createZero();
    }

    @NotNull
    public FishingStats getFishingStats(Rarity rarity, UUID player) {
        return FishingStats.createZero();
    }

    @NotNull
    public MiningStats getMiningStats(Rarity rarity, UUID player) {
        return MiningStats.createZero();
    }

    @NotNull
    public GatheringStats getGatheringStats(Rarity rarity, UUID player) {
        return GatheringStats.createZero();
    }

    @NotNull
    public LuckStats getLuckStats(Rarity rarity, UUID player) {
        return LuckStats.createZero();
    }

    @NotNull
    public WeaponStats getEventWeapon(Rarity rarity, Player attacker, LivingEntity target, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return WeaponStats.createMult();
        }
        return WeaponStats.createZero();
    }

    @NotNull
    public ArmorStats getEventArmor(Rarity rarity, LivingEntity attacker, Player target, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return ArmorStats.createMult();
        }
        return ArmorStats.createZero();
    }

    @NotNull
    public AbilityStats getEventAbility(Rarity rarity, Player attacker, LivingEntity target, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return AbilityStats.createMult();
        }
        return AbilityStats.createZero();
    }

    @NotNull
    public FishingStats getEventFishing(Rarity rarity, Player fisher, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return FishingStats.createMult();
        }
        return FishingStats.createZero();
    }

    @NotNull
    public MiningStats getEventMining(Rarity rarity, Player miner, Block block, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return MiningStats.createMult();
        }
        return MiningStats.createZero();
    }

    @NotNull
    public GatheringStats getEventGathering(Rarity rarity, Player harvester, Block block, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return GatheringStats.createMult();
        }
        return GatheringStats.createZero();
    }

    @NotNull
    public LuckStats getEventLuck(Rarity rarity, Player attacker, LivingEntity target, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return LuckStats.createMult();
        }
        return LuckStats.createZero();
    }

    @NotNull
    public WeaponStats getBonusWeapon(Rarity rarity, Player player, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return WeaponStats.createMult();
        }
        return WeaponStats.createZero();
    }

    @NotNull
    public ArmorStats getBonusArmor(Rarity rarity, Player player, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return ArmorStats.createMult();
        }
        return ArmorStats.createZero();
    }

    @NotNull
    public AbilityStats getBonusAbility(Rarity rarity, Player player, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return AbilityStats.createMult();
        }
        return AbilityStats.createZero();
    }

    @NotNull
    public FishingStats getBonusFishing(Rarity rarity, Player fisher, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return FishingStats.createMult();
        }
        return FishingStats.createZero();
    }

    @NotNull
    public MiningStats getBonusMining(Rarity rarity, Player miner, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return MiningStats.createMult();
        }
        return MiningStats.createZero();
    }

    @NotNull
    public GatheringStats getBonusGathering(Rarity rarity, Player harvester, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return GatheringStats.createMult();
        }
        return GatheringStats.createZero();
    }

    @NotNull
    public LuckStats getBonusLuck(Rarity rarity, Player attacker, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return LuckStats.createMult();
        }
        return LuckStats.createZero();
    }

}