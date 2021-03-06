package me.notpseudo.revolutionsmp.items;

import me.notpseudo.revolutionsmp.abilities.AbilityObject;
import me.notpseudo.revolutionsmp.abilities.AbilityType;
import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.specialiteminfo.SpecialItemInfo;
import org.bukkit.Color;
import org.bukkit.Material;
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
        public Material getMaterial() {
            return Material.LEATHER_CHESTPLATE;
        }

        @Override
        public Color getColor() {
            return Color.fromRGB(0x1793C4);
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
        public Material getMaterial() {
            return Material.LEATHER_LEGGINGS;
        }

        @Override
        public Color getColor() {
            return Color.fromRGB(0x17A8C4);
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
        public Material getMaterial() {
            return Material.LEATHER_BOOTS;
        }

        @Override
        public Color getColor() {
            return Color.fromRGB(0x1CD4E4);
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
        public Material getMaterial() {
            return Material.IRON_SWORD;
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
        public Material getMaterial() {
            return Material.IRON_SWORD;
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
        public Material getMaterial() {
            return Material.IRON_SWORD;
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
            return new ArmorStats(0, 250, 0, 20);
        }

        @Override
        public AbilityStats getDefaultAbilityStats() {
            return new AbilityStats(0, 50);
        }

        @Override
        public Material getMaterial() {
            return Material.IRON_SWORD;
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
        public Material getMaterial() {
            return Material.DIAMOND_SWORD;
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
        public Material getMaterial() {
            return Material.BOW;
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
        public Material getMaterial() {
            return Material.BOW;
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
        public Material getMaterial() {
            return Material.WOODEN_SWORD;
        }
    },
    EVERYTHING {
        @Override
        public String getDefaultName() {
            return "All Stats Drill";
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.MYTHIC;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(10, 10, 10, 10, 10, 10);
        }

        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(10, 10, 10, 10);
        }

        @Override
        public AbilityStats getDefaultAbilityStats() {
            return new AbilityStats(10, 10);
        }

        @Override
        public FishingStats getDefaultFishingStats() {
            return new FishingStats(10, 10);
        }

        @Override
        public MiningStats getDafaultMiningStats() {
            return new MiningStats(10, 10, 10);
        }

        @Override
        public GatheringStats getDefaultGatheringStats() {
            return new GatheringStats(10, 10);
        }

        @Override
        public LuckStats getDefaultLuckStats() {
            return new LuckStats(10, 10);
        }

        @Override
        public ItemType getItemType() {
            return ItemType.PICKAXE;
        }

        @Override
        public Material getMaterial() {
            return Material.PRISMARINE_SHARD;
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
        public Material getMaterial() {
            return Material.BLAZE_ROD;
        }
    },
    JUNGLE_AXE {
        @Override
        public ItemType getItemType() {
            return ItemType.AXE;
        }

        @Override
        public Material getMaterial() {
            return Material.WOODEN_AXE;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public GatheringStats getDefaultGatheringStats() {
            return new GatheringStats(200, 200);
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
        return new ArmorStats(0, 0, 0, 0);
    }

    public AbilityStats getDefaultAbilityStats() {
        return new AbilityStats(0, 0);
    }

    public FishingStats getDefaultFishingStats() {
        return new FishingStats(0, 0);
    }

    public MiningStats getDafaultMiningStats() {
        return new MiningStats(0, 0, 0);
    }

    public GatheringStats getDefaultGatheringStats() {
        return new GatheringStats(0, 0);
    }

    public LuckStats getDefaultLuckStats() {
        return new LuckStats(0, 0);
    }

    public List<AbilityType> getDefaultAbilities() {
        return new ArrayList<>();
    }

    public abstract Material getMaterial();

    public Color getColor() {
        return null;
    }

    public boolean isUnbreakable() {
        return true;
    }

    public SpecialItemInfo getSpecialItemInfo() {
        return null;
    }

    public ItemStack getItem() {
        return ItemEditor.createItem(this);
    }

}