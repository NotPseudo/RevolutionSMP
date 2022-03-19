package me.notpseudo.revolutionsmp.items;

import me.notpseudo.revolutionsmp.extraiteminfo.AspectOfTheEndInfo;
import me.notpseudo.revolutionsmp.extraiteminfo.ExtraItemInfo;
import me.notpseudo.revolutionsmp.itemstats.AbilityStats;
import me.notpseudo.revolutionsmp.itemstats.ArmorStats;
import me.notpseudo.revolutionsmp.itemstats.WeaponStats;

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
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(0, 0, 0, 0, 0, 0);
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
        public boolean isUnbreakable() {
            return true;
        }
        @Override
        public ExtraItemInfo getDefaultExtraInfo() {
            return new ExtraItemInfo(null);
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
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(0, 0, 0, 0, 0, 0);
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
        public boolean isUnbreakable() {
            return true;
        }
        @Override
        public ExtraItemInfo getDefaultExtraInfo() {
            return new ExtraItemInfo(null);
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
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(0, 0, 0, 0, 0, 0);
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
        public boolean isUnbreakable() {
            return true;
        }
        @Override
        public ExtraItemInfo getDefaultExtraInfo() {
            return new ExtraItemInfo(null);
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
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 0, 0);
        }
        @Override
        public AbilityStats getDefaultAbilityStats() {
            return new AbilityStats(0, 350);
        }
        @Override
        public boolean isUnbreakable() {
            return true;
        }
        @Override
        public ExtraItemInfo getDefaultExtraInfo() {
            return new ExtraItemInfo(null);
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
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 0, 0);
        }
        @Override
        public AbilityStats getDefaultAbilityStats() {
            return new AbilityStats(0, 60);
        }
        @Override
        public boolean isUnbreakable() {
            return true;
        }
        @Override
        public ExtraItemInfo getDefaultExtraInfo() {
            return new ExtraItemInfo(null);
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
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 0, 0);
        }
        @Override
        public AbilityStats getDefaultAbilityStats() {
            return new AbilityStats(0, 50);
        }
        @Override
        public boolean isUnbreakable() {
            return true;
        }
        @Override
        public ExtraItemInfo getDefaultExtraInfo() {
            return new ExtraItemInfo(null);
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
        public boolean isUnbreakable() {
            return true;
        }
        @Override
        public ExtraItemInfo getDefaultExtraInfo() {
            return new ExtraItemInfo(null);
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
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 0, 0);
        }
        @Override
        public AbilityStats getDefaultAbilityStats() {
            return new AbilityStats(0, 0);
        }
        @Override
        public boolean isUnbreakable() {
            return true;
        }
        @Override
        public ExtraItemInfo getDefaultExtraInfo() {
            return new AspectOfTheEndInfo();
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
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 0, 0);
        }
        @Override
        public AbilityStats getDefaultAbilityStats() {
            return new AbilityStats(0, 0);
        }
        @Override
        public boolean isUnbreakable() {
            return true;
        }
        @Override
        public ExtraItemInfo getDefaultExtraInfo() {
            return new ExtraItemInfo(null);
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
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 0, 0);
        }
        @Override
        public AbilityStats getDefaultAbilityStats() {
            return new AbilityStats(0, 0);
        }
        @Override
        public boolean isUnbreakable() {
            return true;
        }
        @Override
        public ExtraItemInfo getDefaultExtraInfo() {
            return new ExtraItemInfo(null);
        }
    };

    // Gets default stats for specific item
    public abstract String getDefaultName();
    public abstract ItemType getItemType();
    public abstract Rarity getDefaultRarity();
    public abstract WeaponStats getDefaultWeaponStats();
    public abstract ArmorStats getDefaultArmorStats();
    public abstract AbilityStats getDefaultAbilityStats();
    public abstract boolean isUnbreakable();
    public abstract ExtraItemInfo getDefaultExtraInfo();

}