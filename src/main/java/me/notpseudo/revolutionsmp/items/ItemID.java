package me.notpseudo.revolutionsmp.items;

import me.notpseudo.revolutionsmp.abilities.AbilityObject;
import me.notpseudo.revolutionsmp.abilities.AbilityType;
import me.notpseudo.revolutionsmp.specialiteminfo.SpecialItemInfo;
import me.notpseudo.revolutionsmp.itemstats.AbilityStats;
import me.notpseudo.revolutionsmp.itemstats.ArmorStats;
import me.notpseudo.revolutionsmp.itemstats.WeaponStats;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// Enum used to identify the exact custom item type an item is
public enum ItemID {
    STORM_CHESTPLATE {

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
        public List<AbilityType> getDefaultAbilities() {
            return List.of(AbilityType.INSTANT_TRANSMISSION);
        }

        @Override
        public ItemStack getItem() {
            return WeaponCreator.aote;
        }
    },
    JUJU_SHORTBOW {

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
    },
    GYROKINETIC_WAND {
        @Override
        public ItemType getItemType() {
            return ItemType.WAND;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.EPIC;
        }

        @Override
        public List<AbilityType> getDefaultAbilities() {
            return List.of(AbilityType.GRAVITY_STORM);
        }

        @Override
        public ItemStack getItem() {
            return WeaponCreator.gyro;
        }
    };

    // Gets default stats for specific item
    public String getDefaultName() {
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

    public List<AbilityType> getDefaultAbilities() {
        return new ArrayList<>();
    }

    public boolean isUnbreakable() {
        return true;
    }

    public SpecialItemInfo getSpecialItemInfo() {
        return null;
    }

    public abstract ItemStack getItem();

}