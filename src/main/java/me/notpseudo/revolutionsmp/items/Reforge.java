package me.notpseudo.revolutionsmp.items;

import me.notpseudo.revolutionsmp.itemstats.*;

import java.util.List;

// Enum of Reforges that can be applied to a custom item
public enum Reforge {
    EPIC {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new WeaponStats(0, 15, 0, 10, 1, 0);
                case UNCOMMON:
                    return new WeaponStats(0, 20, 0, 15, 2, 0);
                case RARE:
                    return new WeaponStats(0, 25, 0, 20, 4, 0);
                case EPIC:
                    return new WeaponStats(0, 32, 0, 27, 7, 0);
                case LEGENDARY:
                    return new WeaponStats(0, 40, 0, 35, 10, 0);
                case MYTHIC:
                case SPECIAL:
                    return new WeaponStats(0, 50, 0, 45, 15, 0);
                case DIVINE:
                    return new WeaponStats(0, 60, 0, 55, 20, 0);
                default:
                    return null;
            }
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            return new ArmorStats(0, 0, 0);
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            return new AbilityStats(0, 0);
        }
    },
    SHARP {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new WeaponStats(0, 0, 10, 20, 0, 0);
                case UNCOMMON:
                    return new WeaponStats(0, 0, 12, 30, 0, 0);
                case RARE:
                    return new WeaponStats(0, 0, 14, 40, 0, 0);
                case EPIC:
                    return new WeaponStats(0, 0, 17, 55, 0, 0);
                case LEGENDARY:
                    return new WeaponStats(0, 0, 20, 75, 0, 0);
                case MYTHIC:
                case SPECIAL:
                    return new WeaponStats(0, 0, 25, 90, 0, 0);
                case DIVINE:
                    return new WeaponStats(0, 0, 30, 105, 0, 0);
                default:
                    return null;
            }
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            return new ArmorStats(0, 0, 0);
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            return new AbilityStats(0, 0);
        }
    },
    HEROIC {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new WeaponStats(0, 15, 0, 0, 1, 0);
                case UNCOMMON:
                    return new WeaponStats(0, 20, 0, 0, 2, 0);
                case RARE:
                    return new WeaponStats(0, 25, 0, 0, 2, 0);
                case EPIC:
                    return new WeaponStats(0, 32, 0, 0, 3, 0);
                case LEGENDARY:
                    return new WeaponStats(0, 40, 0, 0, 5, 0);
                case MYTHIC:
                case SPECIAL:
                    return new WeaponStats(0, 50, 0, 0, 7, 0);
                case DIVINE:
                    return new WeaponStats(0, 60, 0, 0, 9, 0);
                default:
                    return null;
            }
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            return new ArmorStats(0, 0, 0);
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new AbilityStats(0, 40);
                case UNCOMMON:
                    return new AbilityStats(0, 50);
                case RARE:
                    return new AbilityStats(0, 65);
                case EPIC:
                    return new AbilityStats(0, 80);
                case LEGENDARY:
                    return new AbilityStats(0, 100);
                case MYTHIC:
                case SPECIAL:
                    return new AbilityStats(0, 125);
                case DIVINE:
                    return new AbilityStats(0, 150);
                default:
                    return null;
            }
        }
    },
    SPICY {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new WeaponStats(0, 2, 1, 25, 1, 0);
                case UNCOMMON:
                    return new WeaponStats(0, 3, 1, 35, 2, 0);
                case RARE:
                    return new WeaponStats(0, 4, 1, 45, 4, 0);
                case EPIC:
                    return new WeaponStats(0, 7, 1, 60, 7, 0);
                case LEGENDARY:
                    return new WeaponStats(0, 10, 1, 80, 10, 0);
                case MYTHIC:
                case SPECIAL:
                    return new WeaponStats(0, 12, 1, 100, 15, 0);
                case DIVINE:
                    return new WeaponStats(0, 14, 1, 120, 20, 0);
                default:
                    return null;
            }
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            return new ArmorStats(0, 0, 0);
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            return new AbilityStats(0, 0);
        }
    },
    LEGENDARY {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new WeaponStats(0, 3, 5, 5, 2, 0);
                case UNCOMMON:
                    return new WeaponStats(0, 7, 7, 10, 3, 0);
                case RARE:
                    return new WeaponStats(0, 12, 9, 15, 5, 0);
                case EPIC:
                    return new WeaponStats(0, 18, 12, 22, 7, 0);
                case LEGENDARY:
                    return new WeaponStats(0, 25, 15, 28, 10, 0);
                case MYTHIC:
                case SPECIAL:
                    return new WeaponStats(0, 32, 18, 36, 15, 0);
                case DIVINE:
                    return new WeaponStats(0, 39, 21, 44, 20, 0);
                default:
                    return null;
            }
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            return new ArmorStats(0, 0, 0);
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new AbilityStats(0, 5);
                case UNCOMMON:
                    return new AbilityStats(0, 8);
                case RARE:
                    return new AbilityStats(0, 12);
                case EPIC:
                    return new AbilityStats(0, 18);
                case LEGENDARY:
                    return new AbilityStats(0, 25);
                case MYTHIC:
                case SPECIAL:
                    return new AbilityStats(0, 35);
                case DIVINE:
                    return new AbilityStats(0, 45);
                default:
                    return null;
            }
        }
    },
    DIRTY {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new WeaponStats(0, 2, 0, 0, 2, 2);
                case UNCOMMON:
                    return new WeaponStats(0, 4, 0, 0, 3, 3);
                case RARE:
                    return new WeaponStats(0, 6, 0, 0, 5, 6);
                case EPIC:
                    return new WeaponStats(0, 10, 0, 0, 10, 9);
                case LEGENDARY:
                    return new WeaponStats(0, 12, 0, 0, 15, 12);
                case MYTHIC:
                case SPECIAL:
                    return new WeaponStats(0, 15, 0, 0, 20, 15);
                case DIVINE:
                    return new WeaponStats(0, 18, 0, 0, 25, 18);
                default:
                    return null;
            }
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            return new ArmorStats(0, 0, 0);
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            return new AbilityStats(0, 0);
        }
    },
    FABLED {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new WeaponStats(0, 30, 0, 15, 0, 0);
                case UNCOMMON:
                    return new WeaponStats(0, 35, 0, 20, 0, 0);
                case RARE:
                    return new WeaponStats(0, 40, 0, 25, 0, 0);
                case EPIC:
                    return new WeaponStats(0, 50, 0, 32, 0, 0);
                case LEGENDARY:
                    return new WeaponStats(0, 60, 0, 40, 0, 0);
                case MYTHIC:
                case SPECIAL:
                    return new WeaponStats(0, 75, 0, 50, 0, 0);
                case DIVINE:
                    return new WeaponStats(0, 90, 0, 60, 0, 0);
                default:
                    return null;
            }
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            return new ArmorStats(0, 0, 0);
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            return new AbilityStats(0, 0);
        }
    },
    SUSPICIOUS {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new WeaponStats(0, 0, 1, 30, 0, 0);
                case UNCOMMON:
                    return new WeaponStats(0, 0, 2, 40, 0, 0);
                case RARE:
                    return new WeaponStats(0, 0, 3, 50, 0, 0);
                case EPIC:
                    return new WeaponStats(0, 0, 5, 65, 0, 0);
                case LEGENDARY:
                    return new WeaponStats(0, 0, 7, 85, 0, 0);
                case MYTHIC:
                case SPECIAL:
                    return new WeaponStats(0, 0, 10, 110, 0, 0);
                case DIVINE:
                    return new WeaponStats(0, 0, 13, 135, 0, 0);
                default:
                    return null;
            }
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            return new ArmorStats(0, 0, 0);
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            return new AbilityStats(0, 0);
        }
    },

    WITHERED {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new WeaponStats(0, 60, 0, 0, 0, 0);
                case UNCOMMON:
                    return new WeaponStats(0, 75, 0, 0, 0, 0);
                case RARE:
                    return new WeaponStats(0, 90, 0, 0, 0, 0);
                case EPIC:
                    return new WeaponStats(0, 110, 0, 0, 0, 0);
                case LEGENDARY:
                    return new WeaponStats(0, 135, 0, 0, 0, 0);
                case MYTHIC:
                case SPECIAL:
                    return new WeaponStats(0, 170, 0, 0, 0, 0);
                case DIVINE:
                    return new WeaponStats(0, 205, 0, 0, 0, 0);
                default:
                    return null;
            }
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            return new ArmorStats(0, 0, 0);
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            return new AbilityStats(0, 0);
        }
    },

    BULKY {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            return new WeaponStats(0, 0, 0, 0, 0, 0);
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new ArmorStats(4, 2, 0);
                case UNCOMMON:
                    return new ArmorStats(6, 3, 0);
                case RARE:
                    return new ArmorStats(9, 5, 0);
                case EPIC:
                    return new ArmorStats(12, 8, 0);
                case LEGENDARY:
                    return new ArmorStats(15, 13, 0);
                case MYTHIC:
                case SPECIAL:
                    return new ArmorStats(20, 21, 0);
                case DIVINE:
                    return new ArmorStats(25, 29, 0);
                default:
                    return null;
            }
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            return new AbilityStats(0, 0);
        }
    },

    GRAND {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.BOW);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new WeaponStats(0, 25, 0, 0, 0, 0);
                case UNCOMMON:
                    return new WeaponStats(0, 32, 0, 0, 0, 0);
                case RARE:
                    return new WeaponStats(0, 40, 0, 0, 0, 0);
                case EPIC:
                    return new WeaponStats(0, 50, 0, 0, 0, 0);
                case LEGENDARY:
                    return new WeaponStats(0, 60, 0, 0, 0, 0);
                case MYTHIC:
                case SPECIAL:
                    return new WeaponStats(0, 75, 0, 0, 0, 0);
                case DIVINE:
                    return new WeaponStats(0, 90, 0, 0, 0, 0);
                default:
                    return null;
            }
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            return new ArmorStats(0, 0, 0);
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            return new AbilityStats(0, 0);
        }
    },

    HASTY {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.BOW);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new WeaponStats(0, 3, 20, 0, 0, 0);
                case UNCOMMON:
                    return new WeaponStats(0, 5, 25, 0, 0, 0);
                case RARE:
                    return new WeaponStats(0, 7, 30, 0, 0, 0);
                case EPIC:
                    return new WeaponStats(0, 10, 40, 0, 0, 0);
                case LEGENDARY:
                    return new WeaponStats(0, 15, 50, 0, 0, 0);
                case MYTHIC:
                case SPECIAL:
                    return new WeaponStats(0, 20, 75, 0, 0, 0);
                case DIVINE:
                    return new WeaponStats(0, 25, 100, 0, 0, 0);
                default:
                    return null;
            }
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            return new ArmorStats(0, 0, 0);
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            return new AbilityStats(0, 0);
        }
    },

    RAPID {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.BOW);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new WeaponStats(0, 2, 0, 35, 0, 0);
                case UNCOMMON:
                    return new WeaponStats(0, 3, 0, 45, 0, 0);
                case RARE:
                    return new WeaponStats(0, 4, 0, 55, 0, 0);
                case EPIC:
                    return new WeaponStats(0, 7, 0, 65, 0, 0);
                case LEGENDARY:
                    return new WeaponStats(0, 10, 0, 75, 0, 0);
                case MYTHIC:
                case SPECIAL:
                    return new WeaponStats(0, 15, 0, 90, 0, 0);
                case DIVINE:
                    return new WeaponStats(0, 20, 0, 105, 0, 0);
                default:
                    return null;
            }
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            return new ArmorStats(0, 0, 0);
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            return new AbilityStats(0, 0);
        }
    },

    UNREAL {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.BOW);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new WeaponStats(0, 3, 8, 5, 0, 0);
                case UNCOMMON:
                    return new WeaponStats(0, 7, 9, 10, 0, 0);
                case RARE:
                    return new WeaponStats(0, 12, 10, 18, 0, 0);
                case EPIC:
                    return new WeaponStats(0, 18, 11, 32, 0, 0);
                case LEGENDARY:
                    return new WeaponStats(0, 25, 13, 50, 0, 0);
                case MYTHIC:
                case SPECIAL:
                    return new WeaponStats(0, 34, 15, 70, 0, 0);
                case DIVINE:
                    return new WeaponStats(0, 43, 17, 90, 0, 0);
                default:
                    return null;
            }
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            return new ArmorStats(0, 0, 0);
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            return new AbilityStats(0, 0);
        }
    },

    AWKWARD {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.BOW);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new WeaponStats(0, 0, 10, 5, 0, 0);
                case UNCOMMON:
                    return new WeaponStats(0, 0, 12, 10, 0, 0);
                case RARE:
                    return new WeaponStats(0, 0, 15, 15, 0, 0);
                case EPIC:
                    return new WeaponStats(0, 0, 20, 22, 0, 0);
                case LEGENDARY:
                    return new WeaponStats(0, 0, 25, 30, 0, 0);
                case MYTHIC:
                case SPECIAL:
                    return new WeaponStats(0, 0, 30, 35, 0, 0);
                case DIVINE:
                    return new WeaponStats(0, 0, 35, 40, 0, 0);
                default:
                    return null;
            }
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            return new ArmorStats(0, 0, 0);
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new AbilityStats(0, -5);
                case UNCOMMON:
                    return new AbilityStats(0, -10);
                case RARE:
                    return new AbilityStats(0, -18);
                case EPIC:
                    return new AbilityStats(0, -32);
                case LEGENDARY:
                    return new AbilityStats(0, -50);
                case MYTHIC:
                case SPECIAL:
                    return new AbilityStats(0, -72);
                case DIVINE:
                    return new AbilityStats(0, -94);
                default:
                    return null;
            }
        }
    },

    PRECISE {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.BOW);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new WeaponStats(0, 3, 8, 5, 0, 0);
                case UNCOMMON:
                    return new WeaponStats(0, 7, 9, 10, 0, 0);
                case RARE:
                    return new WeaponStats(0, 12, 10, 18, 0, 0);
                case EPIC:
                    return new WeaponStats(0, 18, 11, 32, 0, 0);
                case LEGENDARY:
                    return new WeaponStats(0, 25, 13, 50, 0, 0);
                case MYTHIC:
                case SPECIAL:
                    return new WeaponStats(0, 34, 15, 70, 0, 0);
                case DIVINE:
                    return new WeaponStats(0, 43, 17, 90, 0, 0);
                default:
                    return null;
            }
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            return new ArmorStats(0, 0, 0);
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            return new AbilityStats(0, 0);
        }
    },

    SPIRITUAL {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.BOW);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new WeaponStats(0, 4, 7, 10, 0, 0);
                case UNCOMMON:
                    return new WeaponStats(0, 8, 8, 15, 0, 0);
                case RARE:
                    return new WeaponStats(0, 14, 9, 23, 0, 0);
                case EPIC:
                    return new WeaponStats(0, 20, 10, 37, 0, 0);
                case LEGENDARY:
                    return new WeaponStats(0, 28, 12, 55, 0, 0);
                case MYTHIC:
                case SPECIAL:
                    return new WeaponStats(0, 38, 14, 75, 0, 0);
                case DIVINE:
                    return new WeaponStats(0, 48, 16, 95, 0, 0);
                default:
                    return null;
            }
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            return new ArmorStats(0, 0, 0);
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            return new AbilityStats(0, 0);
        }
    },

    FIERCE {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new WeaponStats(0, 2, 2, 4, 0, 0);
                case UNCOMMON:
                    return new WeaponStats(0, 4, 3, 7, 0, 0);
                case RARE:
                    return new WeaponStats(0, 6, 4, 10, 0, 0);
                case EPIC:
                    return new WeaponStats(0, 8, 5, 14, 0, 0);
                case LEGENDARY:
                    return new WeaponStats(0, 10, 6, 18, 0, 0);
                case MYTHIC:
                case SPECIAL:
                    return new WeaponStats(0, 12, 8, 24, 0, 0);
                case DIVINE:
                    return new WeaponStats(0, 14, 10, 30, 0, 0);
                default:
                    return null;
            }
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            return new ArmorStats(0, 0, 0);
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            return new AbilityStats(0, 0);
        }
    },

    HEAVY {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new WeaponStats(0, 0, 0, -1, 0, 0);
                case UNCOMMON:
                case RARE:
                    return new WeaponStats(0, 0, 0, -2, 0, 0);
                case EPIC:
                    return new WeaponStats(0, 0, 0, -3, 0, 0);
                case LEGENDARY:
                    return new WeaponStats(0, 0, 0, -5, 0, 0);
                case MYTHIC:
                case SPECIAL:
                    return new WeaponStats(0, 0, 0, -7, 0, 0);
                case DIVINE:
                    return new WeaponStats(0, 0, 0, -9, 0, 0);
                default:
                    return null;
            }
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new ArmorStats(0, 25, -1);
                case UNCOMMON:
                    return new ArmorStats(0, 35, -1);
                case RARE:
                    return new ArmorStats(0, 50, -1);
                case EPIC:
                    return new ArmorStats(0, 65, -1);
                case LEGENDARY:
                    return new ArmorStats(0, 80, -1);
                case MYTHIC:
                case SPECIAL:
                    return new ArmorStats(0, 110, -1);
                case DIVINE:
                    return new ArmorStats(0, 140, -1);
                default:
                    return null;
            }
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            return new AbilityStats(0, 0);
        }
    },

    LIGHT {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new WeaponStats(0, 0, 1, 1, 1, 0);
                case UNCOMMON:
                    return new WeaponStats(0, 0, 1, 2, 2, 0);
                case RARE:
                    return new WeaponStats(0, 0, 2, 3, 3, 0);
                case EPIC:
                    return new WeaponStats(0, 0, 2, 4, 4, 0);
                case LEGENDARY:
                    return new WeaponStats(0, 0, 3, 5, 5, 0);
                case MYTHIC:
                case SPECIAL:
                    return new WeaponStats(0, 0, 3, 6, 6, 0);
                case DIVINE:
                    return new WeaponStats(0, 0, 4, 7, 7, 0);
                default:
                    return null;
            }
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new ArmorStats(5, 1, 1);
                case UNCOMMON:
                    return new ArmorStats(7, 2, 2);
                case RARE:
                    return new ArmorStats(10, 3, 3);
                case EPIC:
                    return new ArmorStats(15, 4, 4);
                case LEGENDARY:
                    return new ArmorStats(20, 5, 5);
                case MYTHIC:
                case SPECIAL:
                    return new ArmorStats(25, 6, 6);
                case DIVINE:
                    return new ArmorStats(30, 7, 7);
                default:
                    return null;
            }
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            return new AbilityStats(0, 0);
        }
    },

    MYTHIC {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new WeaponStats(0, 2, 1, 0, 0, 0);
                case UNCOMMON:
                    return new WeaponStats(0, 4, 2, 0, 0, 0);
                case RARE:
                    return new WeaponStats(0, 6, 3, 0, 0, 0);
                case EPIC:
                    return new WeaponStats(0, 8, 4, 0, 0, 0);
                case LEGENDARY:
                    return new WeaponStats(0, 10, 5, 0, 0, 0);
                case MYTHIC:
                case SPECIAL:
                    return new WeaponStats(0, 12, 6, 0, 0, 0);
                case DIVINE:
                    return new WeaponStats(0, 14, 7, 0, 0, 0);
                default:
                    return null;
            }
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new ArmorStats(2, 2, 2);
                case UNCOMMON:
                    return new ArmorStats(4, 4, 2);
                case RARE:
                    return new ArmorStats(6, 6, 2);
                case EPIC:
                    return new ArmorStats(8, 8, 2);
                case LEGENDARY:
                    return new ArmorStats(10, 10, 2);
                case MYTHIC:
                case SPECIAL:
                    return new ArmorStats(12, 12, 2);
                case DIVINE:
                    return new ArmorStats(14, 14, 2);
                default:
                    return null;
            }
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new AbilityStats(0, 20);
                case UNCOMMON:
                    return new AbilityStats(0, 25);
                case RARE:
                    return new AbilityStats(0, 30);
                case EPIC:
                    return new AbilityStats(0, 40);
                case LEGENDARY:
                    return new AbilityStats(0, 50);
                case MYTHIC:
                case SPECIAL:
                    return new AbilityStats(0, 60);
                case DIVINE:
                    return new AbilityStats(0, 70);
                default:
                    return null;
            }
        }
    },

    SMART {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            return new WeaponStats(0, 0, 0, 0, 0, 0);
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new ArmorStats(4, 4, 0);
                case UNCOMMON:
                    return new ArmorStats(6, 6, 0);
                case RARE:
                    return new ArmorStats(9, 9, 0);
                case EPIC:
                    return new ArmorStats(12, 12, 0);
                case LEGENDARY:
                    return new ArmorStats(15, 15, 0);
                case MYTHIC:
                case SPECIAL:
                    return new ArmorStats(20, 20, 0);
                case DIVINE:
                    return new ArmorStats(25, 25, 0);
                default:
                    return null;
            }
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new AbilityStats(0, 25);
                case UNCOMMON:
                    return new AbilityStats(0, 50);
                case RARE:
                    return new AbilityStats(0, 75);
                case EPIC:
                    return new AbilityStats(0, 100);
                case LEGENDARY:
                    return new AbilityStats(0, 125);
                case MYTHIC:
                case SPECIAL:
                    return new AbilityStats(0, 150);
                case DIVINE:
                    return new AbilityStats(0, 175);
                default:
                    return null;
            }
        }
    },

    TITANIC {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            return new WeaponStats(0, 0, 0, 0, 0, 0);
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new ArmorStats(10, 10, 0);
                case UNCOMMON:
                    return new ArmorStats(15, 15, 0);
                case RARE:
                    return new ArmorStats(20, 20, 0);
                case EPIC:
                    return new ArmorStats(25, 25, 0);
                case LEGENDARY:
                    return new ArmorStats(35, 35, 0);
                case MYTHIC:
                case SPECIAL:
                    return new ArmorStats(50, 50, 0);
                case DIVINE:
                    return new ArmorStats(65, 65, 0);
                default:
                    return null;
            }
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            return new AbilityStats(0, 0);
        }
    },

    WISE {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            return new WeaponStats(0, 0, 0, 0, 0, 0);
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new ArmorStats(6, 0, 1);
                case UNCOMMON:
                    return new ArmorStats(8, 0, 1);
                case RARE:
                    return new ArmorStats(10, 0, 1);
                case EPIC:
                    return new ArmorStats(12, 0, 2);
                case LEGENDARY:
                    return new ArmorStats(15, 0, 2);
                case MYTHIC:
                case SPECIAL:
                    return new ArmorStats(20, 0, 3);
                case DIVINE:
                    return new ArmorStats(25, 0, 4);
                default:
                    return null;
            }
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new AbilityStats(0, 25);
                case UNCOMMON:
                    return new AbilityStats(0, 50);
                case RARE:
                    return new AbilityStats(0, 75);
                case EPIC:
                    return new AbilityStats(0, 100);
                case LEGENDARY:
                    return new AbilityStats(0, 125);
                case MYTHIC:
                case SPECIAL:
                    return new AbilityStats(0, 150);
                case DIVINE:
                    return new AbilityStats(0, 175);
                default:
                    return null;
            }
        }
    },

    PERFECT {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            return new WeaponStats(0, 0, 0, 0, 0, 0);
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new ArmorStats(0, 25, 0);
                case UNCOMMON:
                    return new ArmorStats(0, 35, 0);
                case RARE:
                    return new ArmorStats(0, 50, 0);
                case EPIC:
                    return new ArmorStats(0, 65, 0);
                case LEGENDARY:
                    return new ArmorStats(0, 80, 0);
                case MYTHIC:
                case SPECIAL:
                    return new ArmorStats(0, 110, 0);
                case DIVINE:
                    return new ArmorStats(0, 140, 0);
                default:
                    return null;
            }
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            return new AbilityStats(0, 0);
        }
    },

    NECROTIC {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            return new WeaponStats(0, 0, 0, 0, 0, 0);
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            return new ArmorStats(0, 0, 0);
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new AbilityStats(0, 30);
                case UNCOMMON:
                    return new AbilityStats(0, 60);
                case RARE:
                    return new AbilityStats(0, 90);
                case EPIC:
                    return new AbilityStats(0, 120);
                case LEGENDARY:
                    return new AbilityStats(0, 150);
                case MYTHIC:
                case SPECIAL:
                    return new AbilityStats(0, 200);
                case DIVINE:
                    return new AbilityStats(0, 250);
                default:
                    return null;
            }
        }
    },

    ANCIENT {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new WeaponStats(0, 4, 3, 0, 0, 0);
                case UNCOMMON:
                    return new WeaponStats(0, 8, 5, 0, 0, 0);
                case RARE:
                    return new WeaponStats(0, 12, 7, 0, 0, 0);
                case EPIC:
                    return new WeaponStats(0, 18, 9, 0, 0, 0);
                case LEGENDARY:
                    return new WeaponStats(0, 25, 12, 0, 0, 0);
                case MYTHIC:
                case SPECIAL:
                    return new WeaponStats(0, 35, 15, 0, 0, 0);
                case DIVINE:
                    return new WeaponStats(0, 45, 18, 0, 0, 0);
                default:
                    return null;
            }
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            return new ArmorStats(7, 7, 0);
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new AbilityStats(0, 6);
                case UNCOMMON:
                    return new AbilityStats(0, 9);
                case RARE:
                    return new AbilityStats(0, 12);
                case EPIC:
                    return new AbilityStats(0, 16);
                case LEGENDARY:
                    return new AbilityStats(0, 20);
                case MYTHIC:
                case SPECIAL:
                    return new AbilityStats(0, 25);
                case DIVINE:
                    return new AbilityStats(0, 30);
                default:
                    return null;
            }
        }
    },

    RENOWNED {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new WeaponStats(0, 3, 2, 3, 1, 0);
                case UNCOMMON:
                    return new WeaponStats(0, 4, 4, 4, 1, 0);
                case RARE:
                    return new WeaponStats(0, 6, 6, 6, 2, 0);
                case EPIC:
                    return new WeaponStats(0, 8, 8, 8, 3, 0);
                case LEGENDARY:
                    return new WeaponStats(0, 10, 10, 10, 4, 0);
                case MYTHIC:
                case SPECIAL:
                    return new WeaponStats(0, 12, 12, 12, 5, 0);
                case DIVINE:
                    return new WeaponStats(0, 14, 14, 14, 6, 0);
                default:
                    return null;
            }
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new ArmorStats(2, 2, 1);
                case UNCOMMON:
                    return new ArmorStats(3, 3, 1);
                case RARE:
                    return new ArmorStats(4, 4, 1);
                case EPIC:
                    return new ArmorStats(6, 6, 1);
                case LEGENDARY:
                    return new ArmorStats(8, 8, 1);
                case MYTHIC:
                case SPECIAL:
                    return new ArmorStats(10, 10, 1);
                case DIVINE:
                    return new ArmorStats(12, 12, 1);
                default:
                    return null;
            }
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new AbilityStats(0, 3);
                case UNCOMMON:
                    return new AbilityStats(0, 4);
                case RARE:
                    return new AbilityStats(0, 6);
                case EPIC:
                    return new AbilityStats(0, 8);
                case LEGENDARY:
                    return new AbilityStats(0, 10);
                case MYTHIC:
                case SPECIAL:
                    return new AbilityStats(0, 12);
                case DIVINE:
                    return new AbilityStats(0, 14);
                default:
                    return null;
            }
        }
    },

    LOVING {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.CHESTPLATE);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            return new WeaponStats(0, 0, 0, 0, 0, 0);
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new ArmorStats(4, 4, 0);
                case UNCOMMON:
                    return new ArmorStats(5, 5, 0);
                case RARE:
                    return new ArmorStats(6, 6, 0);
                case EPIC:
                    return new ArmorStats(8, 7, 0);
                case LEGENDARY:
                    return new ArmorStats(10, 10, 0);
                case MYTHIC:
                case SPECIAL:
                    return new ArmorStats(14, 14, 0);
                case DIVINE:
                    return new ArmorStats(18, 18, 0);
                default:
                    return null;
            }
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new AbilityStats(0, 20);
                case UNCOMMON:
                    return new AbilityStats(0, 40);
                case RARE:
                    return new AbilityStats(0, 60);
                case EPIC:
                    return new AbilityStats(0, 80);
                case LEGENDARY:
                    return new AbilityStats(0, 100);
                case MYTHIC:
                case SPECIAL:
                    return new AbilityStats(0, 120);
                case DIVINE:
                    return new AbilityStats(0, 140);
                default:
                    return null;
            }
        }
    },
    GIANT {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            return new WeaponStats(0, 0, 0, 0, 0, 0);
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            switch (rarity) {
                case COMMON:
                    return new ArmorStats(50, 0, 0);
                case UNCOMMON:
                    return new ArmorStats(60, 0, 0);
                case RARE:
                    return new ArmorStats(80, 0, 0);
                case EPIC:
                    return new ArmorStats(120, 0, 0);
                case LEGENDARY:
                    return new ArmorStats(180, 0, 0);
                case MYTHIC:
                case SPECIAL:
                    return new ArmorStats(240, 0, 0);
                case DIVINE:
                    return new ArmorStats(300, 0, 0);
                default:
                    return null;
            }
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            return new AbilityStats(0, 0);
        }
    },
    ARMORTEST {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            return new WeaponStats(0, 100, 100, 100, 100, 100);
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            return new ArmorStats(250, 250, 100);
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            return new AbilityStats(100, 500);
        }
    },
    WEAPONTEST {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD, ItemType.BOW);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            return new WeaponStats(100, 100, 100, 100, 100, 100);
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            return new ArmorStats(0, 0, 0);
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            return new AbilityStats(100, 500);
        }
    },
    FEROTEST {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD, ItemType.BOW, ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
            return new WeaponStats(0, 5, 25, 5, 0, 100);
        }

        @Override
        public ArmorStats getArmorStats(Rarity rarity) {
            return new ArmorStats(0, 0, 0);
        }

        @Override
        public AbilityStats getAbilityStats(Rarity rarity) {
            return new AbilityStats(0, 0);
        }
    },
    NODAMAGE {
        @Override
        public List<ItemType> getItemTypes() {
            return List.of(ItemType.SWORD, ItemType.BOW, ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);
        }

        @Override
        public WeaponStats getWeaponStats(Rarity rarity) {
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

    // Gets the weapon stats the Reforge boosts
    public WeaponStats getWeaponStats(Rarity rarity) {
        return new WeaponStats(0, 0, 0, 0, 0, 0);
    };

    // Gets the armor stats the Reforge boosts
    public ArmorStats getArmorStats(Rarity rarity) {
        return new ArmorStats(0, 0, 0);
    };

    // Gets the ability statsd the Reforge boosts
    public AbilityStats getAbilityStats(Rarity rarity) {
        return new AbilityStats(0, 0);
    };

    public FishingStats getFishingStats(Rarity rarity) {
        return new FishingStats(0, 0);
    }

    public MiningStats getMiningStats(Rarity rarity) {
        return new MiningStats(0, 0, 0);
    }

    public GatheringStats getGatheringStats(Rarity rarity) {
        return new GatheringStats(0, 0);
    }

    public LuckStats getLuckStats(Rarity rarity) {
        return new LuckStats(0, 0);
    }

}