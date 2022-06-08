package me.notpseudo.revolutionsmp.items;

import me.notpseudo.revolutionsmp.extraiteminfo.AspectOfTheEndInfo;
import me.notpseudo.revolutionsmp.extraiteminfo.ExtraItemInfo;
import me.notpseudo.revolutionsmp.itemstats.AbilityStats;
import me.notpseudo.revolutionsmp.itemstats.ArmorStats;
import me.notpseudo.revolutionsmp.itemstats.WeaponStats;
import org.bukkit.inventory.ItemStack;

// Enum used to identify the exact custom item type an item is
public enum ItemID {
    STORM_CHESTPLATE {
        @Override
        public String getDefaultName() {
            return "Storm's Chestplate";
        }

        @Override
        public ItemType getItemType() {
            return ItemType.CHESTPLATE;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.LEGENDARY;
        }

        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(260, 120, 0);
        }

        @Override
        public AbilityStats getDefaultAbilityStats() {
            return new AbilityStats(0, 250);
        }

        @Override
        public ItemStack getItem() {
            return ArmorCreator.stormChest;
        }

    },
    STORM_LEGGINGS {
        @Override
        public String getDefaultName() {
            return "Storm's Leggings";
        }

        @Override
        public ItemType getItemType() {
            return ItemType.LEGGINGS;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.LEGENDARY;
        }

        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(230, 105, 0);
        }

        @Override
        public AbilityStats getDefaultAbilityStats() {
            return new AbilityStats(0, 250);
        }

        @Override
        public ItemStack getItem() {
            return ArmorCreator.stormLeg;
        }

    },
    STORM_BOOTS {
        @Override
        public String getDefaultName() {
            return "Storm's Boots";
        }

        @Override
        public ItemType getItemType() {
            return ItemType.BOOTS;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.LEGENDARY;
        }

        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(145, 65, 0);
        }

        @Override
        public AbilityStats getDefaultAbilityStats() {
            return new AbilityStats(0, 250);
        }

        @Override
        public ItemStack getItem() {
            return ArmorCreator.stormBoot;
        }

    },
    HYPERION {
        @Override
        public String getDefaultName() {
            return "Hyperion";
        }

        @Override
        public ItemType getItemType() {
            return ItemType.SWORD;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.LEGENDARY;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(260, 150, 0, 0, 0, 30);
        }

        @Override
        public AbilityStats getDefaultAbilityStats() {
            return new AbilityStats(0, 350);
        }

        @Override
        public ItemStack getItem() {
            return WeaponCreator.hyperion;
        }

    },
    VALKYRIE {
        @Override
        public String getDefaultName() {
            return "Valkyrie";
        }

        @Override
        public ItemType getItemType() {
            return ItemType.SWORD;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.LEGENDARY;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(270, 145, 0, 0, 0, 60);
        }

        @Override
        public AbilityStats getDefaultAbilityStats() {
            return new AbilityStats(0, 60);
        }

        @Override
        public ItemStack getItem() {
            return WeaponCreator.valkyrie;
        }

    },
    SCYLLA {
        @Override
        public String getDefaultName() {
            return "Scylla";
        }

        @Override
        public ItemType getItemType() {
            return ItemType.SWORD;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.LEGENDARY;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(270, 150, 12, 35, 0, 30);
        }

        @Override
        public AbilityStats getDefaultAbilityStats() {
            return new AbilityStats(0, 50);
        }

        @Override
        public ItemStack getItem() {
            return WeaponCreator.scylla;
        }

    },
    ASTRAEA {
        @Override
        public String getDefaultName() {
            return "Astraea";
        }

        @Override
        public ItemType getItemType() {
            return ItemType.SWORD;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.LEGENDARY;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(270, 150, 0, 0, 0, 30);
        }

        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 250, 0);
        }

        @Override
        public AbilityStats getDefaultAbilityStats() {
            return new AbilityStats(0, 50);
        }

        @Override
        public ItemStack getItem() {
            return WeaponCreator.astraea;
        }

    },
    ASPECT_OF_THE_END {
        @Override
        public String getDefaultName() {
            return "Aspect of the End";
        }

        @Override
        public ItemType getItemType() {
            return ItemType.SWORD;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.RARE;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(100, 100, 0, 0, 0, 0);
        }

        @Override
        public ExtraItemInfo getDefaultExtraInfo() {
            return new AspectOfTheEndInfo();
        }

        @Override
        public ItemStack getItem() {
            return WeaponCreator.aote;
        }
    },
    JUJU_SHORTBOW {
        @Override
        public String getDefaultName() {
            return "Juju Shortbow";
        }

        @Override
        public ItemType getItemType() {
            return ItemType.BOW;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.EPIC;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(310, 40, 10, 110, 0, 0);
        }

        @Override
        public ItemStack getItem() {
            return WeaponCreator.jujuShortbow;
        }

    },
    TERMINATOR {
        @Override
        public String getDefaultName() {
            return "Terminator";
        }

        @Override
        public ItemType getItemType() {
            return ItemType.BOW;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.LEGENDARY;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(310, 50, 0, 250, 0, 0);
        }

        @Override
        public ItemStack getItem() {
            return WeaponCreator.terminator;
        }

    },
    NODAMAGE {
        @Override
        public String getDefaultName() {
            return "Sticky Stick";
        }

        @Override
        public ItemType getItemType() {
            return ItemType.SWORD;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.DIVINE;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(10, 10, 30, 5, 20, 5);
        }

        @Override
        public ItemStack getItem() {
            return WeaponCreator.noDamage;
        }
    };

    // Gets default stats for specific item
    public abstract String getDefaultName();

    public abstract ItemType getItemType();

    public Rarity getDefaultRarity() {
        return Rarity.COMMON;
    }

    public WeaponStats getDefaultWeaponStats() {
        return new WeaponStats(0, 0, 0, 0, 0, 0);
    }

    public ArmorStats getDefaultArmorStats() {
        return new ArmorStats(0, 0, 0);
    }

    public AbilityStats getDefaultAbilityStats() {
        return new AbilityStats(0, 0);
    }

    public boolean isUnbreakable() {
        return true;
    }

    public ExtraItemInfo getDefaultExtraInfo() {
        return null;
    }

    public abstract ItemStack getItem();

}