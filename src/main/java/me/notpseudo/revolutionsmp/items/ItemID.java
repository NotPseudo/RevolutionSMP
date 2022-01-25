package me.notpseudo.revolutionsmp.items;

import me.notpseudo.revolutionsmp.abilities.Ability;
import me.notpseudo.revolutionsmp.statobjects.AbilityStats;
import me.notpseudo.revolutionsmp.statobjects.ArmorStats;
import me.notpseudo.revolutionsmp.statobjects.WeaponStats;

import java.util.ArrayList;
import java.util.List;

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
            return new WeaponStats(0, 0, 0, 0, 0);
        }
        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(260, 120, 0);
        }
        @Override
        public AbilityStats getDefaultAbilityStats() {
            return new AbilityStats(0, 250, null);
        }
        @Override
        public boolean isUnbreakable() {
            return true;
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
            return new WeaponStats(0, 0, 0, 0, 0);
        }
        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(230, 105, 0);
        }
        @Override
        public AbilityStats getDefaultAbilityStats() {
            return new AbilityStats(0, 250, null);
        }
        @Override
        public boolean isUnbreakable() {
            return true;
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
            return new WeaponStats(0, 0, 0, 0, 0);
        }
        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(145, 65, 0);
        }
        @Override
        public AbilityStats getDefaultAbilityStats() {
            return new AbilityStats(0, 250, null);
        }
        @Override
        public boolean isUnbreakable() {
            return true;
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
            return new WeaponStats(260, 150, 0, 0, 30);
        }
        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 0, 0);
        }
        @Override
        public AbilityStats getDefaultAbilityStats() {
            return new AbilityStats(0, 350, null);
        }
        @Override
        public boolean isUnbreakable() {
            return true;
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
            return new WeaponStats(270, 145, 0, 0, 60);
        }
        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 0, 0);
        }
        @Override
        public AbilityStats getDefaultAbilityStats() {
            return new AbilityStats(0, 60, null);
        }
        @Override
        public boolean isUnbreakable() {
            return true;
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
            return new WeaponStats(270, 150, 12, 35, 30);
        }
        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 0, 0);
        }
        @Override
        public AbilityStats getDefaultAbilityStats() {
            return new AbilityStats(0, 50, null);
        }
        @Override
        public boolean isUnbreakable() {
            return true;
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
            return new WeaponStats(270, 150, 0, 0, 30);
        }
        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 250, 0);
        }
        @Override
        public AbilityStats getDefaultAbilityStats() {
            return new AbilityStats(0, 50, null);
        }
        @Override
        public boolean isUnbreakable() {
            return true;
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
            return new WeaponStats(100, 100, 0, 0, 0);
        }
        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 0, 0);
        }
        @Override
        public AbilityStats getDefaultAbilityStats() {
            List<Ability> abilityList = new ArrayList<>(List.of(Ability.INSTANT_TRANSMISSION));
            return new AbilityStats(0, 0, abilityList);
        }
        @Override
        public boolean isUnbreakable() {
            return true;
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
            return new WeaponStats(310, 40, 10, 110, 0);
        }
        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 0, 0);
        }
        @Override
        public AbilityStats getDefaultAbilityStats() {
            return new AbilityStats(0, 0, null);
        }
        @Override
        public boolean isUnbreakable() {
            return true;
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
            return new WeaponStats(310, 50, 0, 250, 0);
        }
        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 0, 0);
        }
        @Override
        public AbilityStats getDefaultAbilityStats() {
            return new AbilityStats(0, 0, null);
        }
        @Override
        public boolean isUnbreakable() {
            return true;
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

}
