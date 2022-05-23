package me.notpseudo.revolutionsmp.enchantments;

import me.notpseudo.revolutionsmp.items.ItemType;
import me.notpseudo.revolutionsmp.itemstats.AbilityStats;
import me.notpseudo.revolutionsmp.itemstats.ArmorStats;
import me.notpseudo.revolutionsmp.itemstats.WeaponStats;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public enum EnchantmentType {

    BANE_OF_ARTHROPODS {
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
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    DRAGON_HUNTER {
        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    ENDER_SLAYER {
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
        public int getAllowedMax() {
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
        public int getAllowedMax() {
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
        public int getAllowedMax() {
            return 4;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    GIANT_KILLER {
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
        public int getAllowedMax() {
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
        public int getAllowedMax() {
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
        public int getAllowedMax() {
            return 3;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    LOOTING {
        @Override
        public int getAllowedMax() {
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
        public int getAllowedMax() {
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
        public int getAllowedMax() {
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
        public int getAllowedMax() {
            return 0;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    SYPHON {
        @Override
        public int getAllowedMax() {
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
        public int getAllowedMax() {
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
        public int getAllowedMax() {
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
        public int getAllowedMax() {
            return 0;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    COMBO {
        @Override
        public int getAllowedMax() {
            return 0;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    INFERNO {
        @Override
        public int getAllowedMax() {
            return 0;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    ONE_FOR_ALL {
        @Override
        public int getAllowedMax() {
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
        public int getAllowedMax() {
            return 0;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    SWARM {
        @Override
        public int getAllowedMax() {
            return 0;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    },
    ULTIMATE_WISE {
        @Override
        public int getAllowedMax() {
            return 0;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    };

    public WeaponStats getApplyWeaponStats() {
        return null;
    }

    public ArmorStats getApplyArmorStats() {
        return null;
    }

    public AbilityStats getApplyAbilityStats() {
        return null;
    }

    public double getAddDamage(EntityDamageByEntityEvent event) {
        return 0;
    }

    public double getAddDamageMult(EntityDamageByEntityEvent event) {
        return 0;
    }

    public double getMultDamage(EntityDamageByEntityEvent event) {
        return 1;
    }

    public int getMinLevel() {
        return 1;
    }

    public int getAllowedMax() {
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