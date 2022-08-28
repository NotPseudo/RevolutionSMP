package me.notpseudo.revolutionsmp.items;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.abilities.AbilityType;
import me.notpseudo.revolutionsmp.collections.CollectionType;
import me.notpseudo.revolutionsmp.customcrafting.*;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentObject;
import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.mining.CustomOreLocation;
import me.notpseudo.revolutionsmp.mining.GemstoneSlotType;
import me.notpseudo.revolutionsmp.mining.GemstoneType;
import me.notpseudo.revolutionsmp.mining.GemstoneUtils;
import me.notpseudo.revolutionsmp.specialiteminfo.FarmingToolInfo;
import me.notpseudo.revolutionsmp.specialiteminfo.GemstoneObject;
import me.notpseudo.revolutionsmp.specialiteminfo.SpecialItemInfo;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

// Enum used to identify the exact custom item type an item is
public enum ItemID {

    WOODEN_SWORD {
        @Override
        public Material getMaterial() {
            return Material.WOODEN_SWORD;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.SWORD;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(20, 0, 0, 0, 0, 0);
        }
    },
    GOLDEN_SWORD {
        @Override
        public Material getMaterial() {
            return Material.GOLDEN_SWORD;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.SWORD;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(20, 0, 0, 0, 0, 0);
        }
    },
    STONE_SWORD {
        @Override
        public Material getMaterial() {
            return Material.STONE_SWORD;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.SWORD;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(25, 0, 0, 0, 0, 0);
        }
    },
    IRON_SWORD {
        @Override
        public Material getMaterial() {
            return Material.IRON_SWORD;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.SWORD;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(30, 0, 0, 0, 0, 0);
        }
    },
    DIAMOND_SWORD {
        @Override
        public Material getMaterial() {
            return Material.DIAMOND_SWORD;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.SWORD;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(35, 0, 0, 0, 0, 0);
        }
    },
    NETHERITE_SWORD {
        @Override
        public Material getMaterial() {
            return Material.NETHERITE_SWORD;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.SWORD;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(40, 0, 0, 0, 0, 0);
        }
    },
    TRIDENT {
        @Override
        public Material getMaterial() {
            return Material.TRIDENT;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.SWORD;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(45, 0, 0, 0, 0, 0);
        }
    },
    BOW {
        @Override
        public Material getMaterial() {
            return Material.BOW;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.BOW;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(30, 0, 0, 0, 0, 0);
        }
    },
    CROSSBOW {
        @Override
        public Material getMaterial() {
            return Material.CROSSBOW;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.BOW;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(50, 0, 0, 0, 0, 0);
        }
    },
    WOODEN_PICKAXE {
        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(15, 0, 0, 0, 0, 0);
        }

        @Override
        public MiningStats getDefaultMiningStats() {
            return new MiningStats(50, 0, 0, 1);
        }

        @Override
        public ItemType getItemType() {
            return ItemType.PICKAXE;
        }

        @Override
        public Material getMaterial() {
            return Material.WOODEN_PICKAXE;
        }
    },
    STONE_PICKAXE {
        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(20, 0, 0, 0, 0, 0);
        }

        @Override
        public MiningStats getDefaultMiningStats() {
            return new MiningStats(50, 0, 0, 2);
        }

        @Override
        public ItemType getItemType() {
            return ItemType.PICKAXE;
        }

        @Override
        public Material getMaterial() {
            return Material.STONE_PICKAXE;
        }
    },
    GOLDEN_PICKAXE {
        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(15, 0, 0, 0, 0, 0);
        }

        @Override
        public MiningStats getDefaultMiningStats() {
            return new MiningStats(250, 0, 0, 1);
        }

        @Override
        public ItemType getItemType() {
            return ItemType.PICKAXE;
        }

        @Override
        public Material getMaterial() {
            return Material.GOLDEN_PICKAXE;
        }
    },
    IRON_PICKAXE {
        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(25, 0, 0, 0, 0, 0);
        }

        @Override
        public MiningStats getDefaultMiningStats() {
            return new MiningStats(150, 0, 0, 3);
        }

        @Override
        public ItemType getItemType() {
            return ItemType.PICKAXE;
        }

        @Override
        public Material getMaterial() {
            return Material.IRON_PICKAXE;
        }
    },
    DIAMOND_PICKAXE {
        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(30, 0, 0, 0, 0, 0);
        }

        @Override
        public MiningStats getDefaultMiningStats() {
            return new MiningStats(200, 0, 0, 4);
        }

        @Override
        public ItemType getItemType() {
            return ItemType.PICKAXE;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public Material getMaterial() {
            return Material.DIAMOND_PICKAXE;
        }
    },
    NETHERITE_PICKAXE {
        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(35, 0, 0, 0, 0, 0);
        }

        @Override
        public MiningStats getDefaultMiningStats() {
            return new MiningStats(225, 0, 0, 5);
        }

        @Override
        public ItemType getItemType() {
            return ItemType.PICKAXE;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public Material getMaterial() {
            return Material.NETHERITE_PICKAXE;
        }
    },
    WOODEN_AXE {
        @Override
        public Material getMaterial() {
            return Material.WOODEN_AXE;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.AXE;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(35, 0, 0, 0, 0, 0);
        }
    },
    GOLDEN_AXE {
        @Override
        public Material getMaterial() {
            return Material.GOLDEN_AXE;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.AXE;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(35, 0, 0, 0, 0, 0);
        }
    },
    STONE_AXE {
        @Override
        public Material getMaterial() {
            return Material.STONE_AXE;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.AXE;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(45, 0, 0, 0, 0, 0);
        }
    },
    IRON_AXE {
        @Override
        public Material getMaterial() {
            return Material.IRON_AXE;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.AXE;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(45, 0, 0, 0, 0, 0);
        }
    },
    DIAMOND_AXE {
        @Override
        public Material getMaterial() {
            return Material.DIAMOND_AXE;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.AXE;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(45, 0, 0, 0, 0, 0);
        }
    },
    NETHERITE_AXE {
        @Override
        public Material getMaterial() {
            return Material.NETHERITE_AXE;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.AXE;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(50, 0, 0, 0, 0, 0);
        }
    },
    WOODEN_HOE {
        @Override
        public Material getMaterial() {
            return Material.WOODEN_HOE;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.HOE;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(5, 0, 0, 0, 0, 0);
        }
    },
    GOLDEN_HOE {
        @Override
        public Material getMaterial() {
            return Material.GOLDEN_HOE;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.HOE;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(5, 0, 0, 0, 0, 0);
        }
    },
    STONE_HOE {
        @Override
        public Material getMaterial() {
            return Material.STONE_HOE;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.HOE;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(5, 0, 0, 0, 0, 0);
        }
    },
    IRON_HOE {
        @Override
        public Material getMaterial() {
            return Material.IRON_HOE;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.HOE;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(5, 0, 0, 0, 0, 0);
        }
    },
    DIAMOND_HOE {
        @Override
        public Material getMaterial() {
            return Material.DIAMOND_HOE;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.HOE;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(5, 0, 0, 0, 0, 0);
        }
    },
    NETHERITE_HOE {
        @Override
        public Material getMaterial() {
            return Material.NETHERITE_HOE;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.HOE;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(5, 0, 0, 0, 0, 0);
        }
    },
    WOODEN_SHOVEL {
        @Override
        public Material getMaterial() {
            return Material.WOODEN_SHOVEL;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.SHOVEL;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(12.5, 0, 0, 0, 0, 0);
        }
    },
    GOLDEN_SHOVEL {
        @Override
        public Material getMaterial() {
            return Material.GOLDEN_SHOVEL;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.SHOVEL;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(12.5, 0, 0, 0, 0, 0);
        }
    },
    STONE_SHOVEL {
        @Override
        public Material getMaterial() {
            return Material.STONE_SHOVEL;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.SHOVEL;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(17.5, 0, 0, 0, 0, 0);
        }
    },
    IRON_SHOVEL {
        @Override
        public Material getMaterial() {
            return Material.IRON_SHOVEL;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.SHOVEL;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(22.5, 0, 0, 0, 0, 0);
        }
    },
    DIAMOND_SHOVEL {
        @Override
        public Material getMaterial() {
            return Material.DIAMOND_SHOVEL;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.SHOVEL;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(27.5, 0, 0, 0, 0, 0);
        }
    },
    NETHERITE_SHOVEL {
        @Override
        public Material getMaterial() {
            return Material.NETHERITE_SHOVEL;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.SHOVEL;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(32.5, 0, 0, 0, 0, 0);
        }
    },
    FISHING_ROD {
        @Override
        public Material getMaterial() {
            return Material.FISHING_ROD;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.FISHING_ROD;
        }

        @Override
        public WeaponStats getDefaultWeaponStats() {
            return new WeaponStats(10, 5, 0, 0, 0, 0);
        }
    },
    LEATHER_HELMET {
        @Override
        public Material getMaterial() {
            return Material.LEATHER_HELMET;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.HELMET;
        }

        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 5, 0);
        }
    },
    LEATHER_CHESTPLATE {
        @Override
        public Material getMaterial() {
            return Material.LEATHER_CHESTPLATE;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.CHESTPLATE;
        }

        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 15, 0);
        }
    },
    LEATHER_LEGGINGS {
        @Override
        public Material getMaterial() {
            return Material.LEATHER_LEGGINGS;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.LEGGINGS;
        }

        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 10, 0);
        }
    },
    LEATHER_BOOTS {
        @Override
        public Material getMaterial() {
            return Material.LEATHER_BOOTS;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.BOOTS;
        }

        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 5, 0);
        }
    },
    GOLDEN_HELMET {
        @Override
        public Material getMaterial() {
            return Material.GOLDEN_HELMET;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.HELMET;
        }

        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 10, 0);
        }
    },
    GOLDEN_CHESTPLATE {
        @Override
        public Material getMaterial() {
            return Material.GOLDEN_CHESTPLATE;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.CHESTPLATE;
        }

        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 25, 0);
        }
    },
    GOLDEN_LEGGINGS {
        @Override
        public Material getMaterial() {
            return Material.GOLDEN_LEGGINGS;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.LEGGINGS;
        }

        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 15, 0);
        }
    },
    GOLDEN_BOOTS {
        @Override
        public Material getMaterial() {
            return Material.GOLDEN_BOOTS;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.BOOTS;
        }

        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 5, 0);
        }
    },
    IRON_HELMET {
        @Override
        public Material getMaterial() {
            return Material.IRON_HELMET;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.HELMET;
        }

        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 10, 0);
        }
    },
    IRON_CHESTPLATE {
        @Override
        public Material getMaterial() {
            return Material.IRON_CHESTPLATE;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.CHESTPLATE;
        }

        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 30, 0);
        }
    },
    IRON_LEGGINGS {
        @Override
        public Material getMaterial() {
            return Material.IRON_LEGGINGS;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.LEGGINGS;
        }

        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 25, 0);
        }
    },
    IRON_BOOTS {
        @Override
        public Material getMaterial() {
            return Material.IRON_BOOTS;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.BOOTS;
        }

        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 10, 0);
        }
    },
    CHAINMAIL_HELMET {
        @Override
        public Material getMaterial() {
            return Material.CHAINMAIL_HELMET;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.HELMET;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 12, 0);
        }
    },
    CHAINMAIL_CHESTPLATE {
        @Override
        public Material getMaterial() {
            return Material.CHAINMAIL_CHESTPLATE;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.CHESTPLATE;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 30, 0);
        }
    },
    CHAINMAIL_LEGGINGS {
        @Override
        public Material getMaterial() {
            return Material.CHAINMAIL_LEGGINGS;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.LEGGINGS;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 20, 0);
        }
    },
    CHAINMAIL_BOOTS {
        @Override
        public Material getMaterial() {
            return Material.CHAINMAIL_BOOTS;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.BOOTS;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 7, 0);
        }
    },
    DIAMOND_HELMET {
        @Override
        public Material getMaterial() {
            return Material.DIAMOND_HELMET;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.HELMET;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 15, 0);
        }
    },
    DIAMOND_CHESTPLATE {
        @Override
        public Material getMaterial() {
            return Material.DIAMOND_CHESTPLATE;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.CHESTPLATE;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 40, 0);
        }
    },
    DIAMOND_LEGGINGS {
        @Override
        public Material getMaterial() {
            return Material.DIAMOND_LEGGINGS;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.LEGGINGS;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 30, 0);
        }
    },
    DIAMOND_BOOTS {
        @Override
        public Material getMaterial() {
            return Material.DIAMOND_BOOTS;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.BOOTS;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 15, 0);
        }
    },
    NETHERITE_HELMET {
        @Override
        public Material getMaterial() {
            return Material.NETHERITE_HELMET;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.HELMET;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 25, 0);
        }
    },
    NETHERITE_CHESTPLATE {
        @Override
        public Material getMaterial() {
            return Material.NETHERITE_CHESTPLATE;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.CHESTPLATE;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 55, 0);
        }
    },
    NETHERITE_LEGGINGS {
        @Override
        public Material getMaterial() {
            return Material.NETHERITE_LEGGINGS;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.LEGGINGS;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 45, 0);
        }
    },
    NETHERITE_BOOTS {
        @Override
        public Material getMaterial() {
            return Material.NETHERITE_BOOTS;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.BOOTS;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public ArmorStats getDefaultArmorStats() {
            return new ArmorStats(0, 25, 0);
        }
    },

    ENCHANTED_HAY_BALE {
        @Override
        public Material getMaterial() {
            return Material.HAY_BLOCK;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapedRecipe recipe = new CustomShapedRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.setShape("HHH", "HHH", "HHH");
            recipe.setIngredient('H', new ItemStack(Material.HAY_BLOCK, 4));
            return recipe;
        }
    },
    ENCHANTED_POTATO {
        @Override
        public Material getMaterial() {
            return Material.POTATO;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.POTATO, 8);
            recipe.addIngredient(Material.POTATO, 8);
            recipe.addIngredient(Material.POTATO, 8);
            recipe.addIngredient(Material.POTATO, 8);
            recipe.addIngredient(Material.POTATO, 8);
            return recipe;
        }
    },
    ENCHANTED_BAKED_POTATO {
        @Override
        public Material getMaterial() {
            return Material.BAKED_POTATO;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(ENCHANTED_POTATO, 8);
            recipe.addIngredient(ENCHANTED_POTATO, 8);
            recipe.addIngredient(ENCHANTED_POTATO, 8);
            recipe.addIngredient(ENCHANTED_POTATO, 8);
            recipe.addIngredient(ENCHANTED_POTATO, 8);
            return recipe;
        }
    },
    HOT_POTATO_BOOK {
        @Override
        public Material getMaterial() {
            return Material.BOOK;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.EPIC;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapedRecipe recipe = new CustomShapedRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.setShape("PP ", "PB ", "   ");
            recipe.setIngredient('P', Material.PAPER);
            recipe.setIngredient('B', ENCHANTED_BAKED_POTATO);
            return recipe;
        }
    },
    FUMING_POTATO_BOOK {
        @Override
        public Material getMaterial() {
            return Material.BOOK;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.EPIC;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapedRecipe recipe = new CustomShapedRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.setShape("PPP", "PBP", "PPP");
            recipe.setIngredient('P', ENCHANTED_BAKED_POTATO);
            recipe.setIngredient('B', HOT_POTATO_BOOK);
            return recipe;
        }
    },
    ENCHANTED_CARROT {
        @Override
        public Material getMaterial() {
            return Material.CARROT;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.CARROT, 8);
            recipe.addIngredient(Material.CARROT, 8);
            recipe.addIngredient(Material.CARROT, 8);
            recipe.addIngredient(Material.CARROT, 8);
            recipe.addIngredient(Material.CARROT, 8);
            return recipe;
        }
    },
    ENCHANTED_GOLDEN_CARROT {
        @Override
        public Material getMaterial() {
            return Material.GOLDEN_CARROT;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(ENCHANTED_CARROT, 8);
            recipe.addIngredient(ENCHANTED_CARROT, 8);
            recipe.addIngredient(ENCHANTED_CARROT, 8);
            recipe.addIngredient(ENCHANTED_CARROT, 8);
            recipe.addIngredient(ENCHANTED_CARROT, 8);
            return recipe;
        }
    },
    ENCHANTED_MELON {
        @Override
        public Material getMaterial() {
            return Material.MELON_SLICE;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.MELON_SLICE, 8);
            recipe.addIngredient(Material.MELON_SLICE, 8);
            recipe.addIngredient(Material.MELON_SLICE, 8);
            recipe.addIngredient(Material.MELON_SLICE, 8);
            recipe.addIngredient(Material.MELON_SLICE, 8);
            return recipe;
        }
    },
    ENCHANTED_GLISTERING_MELON {
        @Override
        public Material getMaterial() {
            return Material.GLISTERING_MELON_SLICE;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.RARE;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.GLISTERING_MELON_SLICE, 8);
            recipe.addIngredient(Material.GLISTERING_MELON_SLICE, 8);
            recipe.addIngredient(Material.GLISTERING_MELON_SLICE, 8);
            recipe.addIngredient(Material.GLISTERING_MELON_SLICE, 8);
            recipe.addIngredient(Material.GLISTERING_MELON_SLICE, 8);
            recipe.addIngredient(Material.GLISTERING_MELON_SLICE, 8);
            recipe.addIngredient(Material.GLISTERING_MELON_SLICE, 8);
            recipe.addIngredient(Material.GLISTERING_MELON_SLICE, 8);
            return recipe;
        }
    },
    ENCHANTED_MELON_BLOCK {
        @Override
        public Material getMaterial() {
            return Material.MELON;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.RARE;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(ENCHANTED_MELON, 8);
            recipe.addIngredient(ENCHANTED_MELON, 8);
            recipe.addIngredient(ENCHANTED_MELON, 8);
            recipe.addIngredient(ENCHANTED_MELON, 8);
            recipe.addIngredient(ENCHANTED_MELON, 8);
            return recipe;
        }
    },
    ENCHANTED_SUGAR {
        @Override
        public Material getMaterial() {
            return Material.SUGAR;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.SUGAR_CANE, 8);
            recipe.addIngredient(Material.SUGAR_CANE, 8);
            recipe.addIngredient(Material.SUGAR_CANE, 8);
            recipe.addIngredient(Material.SUGAR_CANE, 8);
            recipe.addIngredient(Material.SUGAR_CANE, 8);
            return recipe;
        }
    },
    ENCHANTED_PAPER {
        @Override
        public Material getMaterial() {
            return Material.PAPER;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.SUGAR_CANE, 16);
            recipe.addIngredient(Material.SUGAR_CANE, 16);
            recipe.addIngredient(Material.SUGAR_CANE, 16);
            return recipe;
        }
    },
    ENCHANTED_SUGAR_CANE {
        @Override
        public Material getMaterial() {
            return Material.SUGAR_CANE;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.RARE;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(ENCHANTED_SUGAR, 8);
            recipe.addIngredient(ENCHANTED_SUGAR, 8);
            recipe.addIngredient(ENCHANTED_SUGAR, 8);
            recipe.addIngredient(ENCHANTED_SUGAR, 8);
            recipe.addIngredient(ENCHANTED_SUGAR, 8);
            return recipe;
        }
    },
    ENCHANTED_PUMPKIN {
        @Override
        public Material getMaterial() {
            return Material.PUMPKIN;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.PUMPKIN, 8);
            recipe.addIngredient(Material.PUMPKIN, 8);
            recipe.addIngredient(Material.PUMPKIN, 8);
            recipe.addIngredient(Material.PUMPKIN, 8);
            recipe.addIngredient(Material.PUMPKIN, 8);
            return recipe;
        }
    },
    ENCHANTED_FEATHER {
        @Override
        public Material getMaterial() {
            return Material.FEATHER;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.FEATHER, 8);
            recipe.addIngredient(Material.FEATHER, 8);
            recipe.addIngredient(Material.FEATHER, 8);
            recipe.addIngredient(Material.FEATHER, 8);
            recipe.addIngredient(Material.FEATHER, 8);
            return recipe;
        }
    },
    ENCHANTED_RAW_BEEF {
        @Override
        public Material getMaterial() {
            return Material.BEEF;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.BEEF, 8);
            recipe.addIngredient(Material.BEEF, 8);
            recipe.addIngredient(Material.BEEF, 8);
            recipe.addIngredient(Material.BEEF, 8);
            recipe.addIngredient(Material.BEEF, 8);
            return recipe;
        }
    },
    ENCHANTED_LEATHER {
        @Override
        public Material getMaterial() {
            return Material.LEATHER;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.LEATHER, 8);
            recipe.addIngredient(Material.LEATHER, 8);
            recipe.addIngredient(Material.LEATHER, 8);
            recipe.addIngredient(Material.LEATHER, 8);
            recipe.addIngredient(Material.LEATHER, 8);
            return recipe;
        }
    },
    ENCHANTED_MUTTON {
        @Override
        public Material getMaterial() {
            return Material.MUTTON;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.MUTTON, 8);
            recipe.addIngredient(Material.MUTTON, 8);
            recipe.addIngredient(Material.MUTTON, 8);
            recipe.addIngredient(Material.MUTTON, 8);
            recipe.addIngredient(Material.MUTTON, 8);
            return recipe;
        }
    },
    ENCHANTED_COOKED_MUTTON {
        @Override
        public Material getMaterial() {
            return Material.COOKED_MUTTON;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.RARE;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(ENCHANTED_MUTTON, 8);
            recipe.addIngredient(ENCHANTED_MUTTON, 8);
            recipe.addIngredient(ENCHANTED_MUTTON, 8);
            recipe.addIngredient(ENCHANTED_MUTTON, 8);
            recipe.addIngredient(ENCHANTED_MUTTON, 8);
            return recipe;
        }
    },
    ENCHANTED_CHICKEN {
        @Override
        public Material getMaterial() {
            return Material.CHICKEN;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.CHICKEN, 8);
            recipe.addIngredient(Material.CHICKEN, 8);
            recipe.addIngredient(Material.CHICKEN, 8);
            recipe.addIngredient(Material.CHICKEN, 8);
            recipe.addIngredient(Material.CHICKEN, 8);
            return recipe;
        }
    },
    ENCHANTED_EGG {
        @Override
        public Material getMaterial() {
            return Material.EGG;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.RARE;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.EGG, 4);
            recipe.addIngredient(Material.EGG, 4);
            recipe.addIngredient(Material.EGG, 4);
            recipe.addIngredient(Material.EGG, 4);
            recipe.addIngredient(Material.EGG, 4);
            recipe.addIngredient(Material.EGG, 4);
            recipe.addIngredient(Material.EGG, 4);
            recipe.addIngredient(Material.EGG, 4);
            recipe.addIngredient(Material.EGG, 4);
            return recipe;
        }
    },
    SUPER_ENCHANTED_EGG {
        @Override
        public Material getMaterial() {
            return Material.GHAST_SPAWN_EGG;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.RARE;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(ENCHANTED_EGG, 4);
            recipe.addIngredient(ENCHANTED_EGG, 4);
            recipe.addIngredient(ENCHANTED_EGG, 4);
            recipe.addIngredient(ENCHANTED_EGG, 4);
            recipe.addIngredient(ENCHANTED_EGG, 4);
            recipe.addIngredient(ENCHANTED_EGG, 4);
            recipe.addIngredient(ENCHANTED_EGG, 4);
            recipe.addIngredient(ENCHANTED_EGG, 4);
            recipe.addIngredient(ENCHANTED_EGG, 4);
            return recipe;
        }
    },
    OMEGA_ENCHANTED_EGG {
        @Override
        public Material getMaterial() {
            return Material.ENDERMITE_SPAWN_EGG;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.EPIC;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(SUPER_ENCHANTED_EGG, 4);
            recipe.addIngredient(SUPER_ENCHANTED_EGG, 4);
            recipe.addIngredient(SUPER_ENCHANTED_EGG, 4);
            recipe.addIngredient(SUPER_ENCHANTED_EGG, 4);
            recipe.addIngredient(SUPER_ENCHANTED_EGG, 4);
            recipe.addIngredient(SUPER_ENCHANTED_EGG, 4);
            recipe.addIngredient(SUPER_ENCHANTED_EGG, 4);
            recipe.addIngredient(SUPER_ENCHANTED_EGG, 4);
            recipe.addIngredient(SUPER_ENCHANTED_EGG, 4);
            return recipe;
        }
    },
    ENCHANTED_PORK {
        @Override
        public Material getMaterial() {
            return Material.PORKCHOP;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.PORKCHOP, 8);
            recipe.addIngredient(Material.PORKCHOP, 8);
            recipe.addIngredient(Material.PORKCHOP, 8);
            recipe.addIngredient(Material.PORKCHOP, 8);
            recipe.addIngredient(Material.PORKCHOP, 8);
            return recipe;
        }
    },
    ENCHANTED_GRILLED_PORK {
        @Override
        public Material getMaterial() {
            return Material.COOKED_PORKCHOP;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.RARE;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(ENCHANTED_PORK, 8);
            recipe.addIngredient(ENCHANTED_PORK, 8);
            recipe.addIngredient(ENCHANTED_PORK, 8);
            recipe.addIngredient(ENCHANTED_PORK, 8);
            recipe.addIngredient(ENCHANTED_PORK, 8);
            return recipe;
        }
    },
    ENCHANTED_RABBIT_FOOT {
        @Override
        public Material getMaterial() {
            return Material.RABBIT_FOOT;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.RARE;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.RABBIT_FOOT, 8);
            recipe.addIngredient(Material.RABBIT_FOOT, 8);
            recipe.addIngredient(Material.RABBIT_FOOT, 8);
            recipe.addIngredient(Material.RABBIT_FOOT, 8);
            recipe.addIngredient(Material.RABBIT_FOOT, 8);
            return recipe;
        }
    },
    ENCHANTED_RABBIT_HIDE {
        @Override
        public Material getMaterial() {
            return Material.RABBIT_HIDE;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.RARE;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.RABBIT_HIDE, 16);
            recipe.addIngredient(Material.RABBIT_HIDE, 16);
            recipe.addIngredient(Material.RABBIT_HIDE, 16);
            recipe.addIngredient(Material.RABBIT_HIDE, 16);
            recipe.addIngredient(Material.RABBIT_HIDE, 16);
            recipe.addIngredient(Material.RABBIT_HIDE, 16);
            recipe.addIngredient(Material.RABBIT_HIDE, 16);
            recipe.addIngredient(Material.RABBIT_HIDE, 16);
            recipe.addIngredient(Material.RABBIT_HIDE, 16);
            return recipe;
        }
    },
    ENCHANTED_CACTUS_GREEN {
        @Override
        public Material getMaterial() {
            return Material.GREEN_DYE;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.GREEN_DYE, 8);
            recipe.addIngredient(Material.GREEN_DYE, 8);
            recipe.addIngredient(Material.GREEN_DYE, 8);
            recipe.addIngredient(Material.GREEN_DYE, 8);
            recipe.addIngredient(Material.GREEN_DYE, 8);
            return recipe;
        }
    },
    ENCHANTED_CACTUS {
        @Override
        public Material getMaterial() {
            return Material.COOKED_PORKCHOP;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.RARE;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(ENCHANTED_CACTUS_GREEN, 8);
            recipe.addIngredient(ENCHANTED_CACTUS_GREEN, 8);
            recipe.addIngredient(ENCHANTED_CACTUS_GREEN, 8);
            recipe.addIngredient(ENCHANTED_CACTUS_GREEN, 8);
            recipe.addIngredient(ENCHANTED_CACTUS_GREEN, 8);
            return recipe;
        }
    },
    ENCHANTED_BROWN_MUSHROOM {
        @Override
        public Material getMaterial() {
            return Material.BROWN_MUSHROOM;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.BROWN_MUSHROOM, 8);
            recipe.addIngredient(Material.BROWN_MUSHROOM, 8);
            recipe.addIngredient(Material.BROWN_MUSHROOM, 8);
            recipe.addIngredient(Material.BROWN_MUSHROOM, 8);
            recipe.addIngredient(Material.BROWN_MUSHROOM, 8);
            return recipe;
        }
    },
    ENCHANTED_RED_MUSHROOM {
        @Override
        public Material getMaterial() {
            return Material.RED_MUSHROOM;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.RED_MUSHROOM, 8);
            recipe.addIngredient(Material.RED_MUSHROOM, 8);
            recipe.addIngredient(Material.RED_MUSHROOM, 8);
            recipe.addIngredient(Material.RED_MUSHROOM, 8);
            recipe.addIngredient(Material.RED_MUSHROOM, 8);
            return recipe;
        }
    },
    ENCHANTED_BEETROOT {
        @Override
        public Material getMaterial() {
            return Material.BEETROOT;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.BEETROOT, 8);
            recipe.addIngredient(Material.BEETROOT, 8);
            recipe.addIngredient(Material.BEETROOT, 8);
            recipe.addIngredient(Material.BEETROOT, 8);
            recipe.addIngredient(Material.BEETROOT, 8);
            return recipe;
        }
    },
    ENCHANTED_SWEET_BERRIES {
        @Override
        public Material getMaterial() {
            return Material.SWEET_BERRIES;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.SWEET_BERRIES, 8);
            recipe.addIngredient(Material.SWEET_BERRIES, 8);
            recipe.addIngredient(Material.SWEET_BERRIES, 8);
            recipe.addIngredient(Material.SWEET_BERRIES, 8);
            recipe.addIngredient(Material.SWEET_BERRIES, 8);
            return recipe;
        }
    },
    ENCHANTED_COCOA_BEANS {
        @Override
        public Material getMaterial() {
            return Material.COCOA_BEANS;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.COCOA_BEANS, 8);
            recipe.addIngredient(Material.COCOA_BEANS, 8);
            recipe.addIngredient(Material.COCOA_BEANS, 8);
            recipe.addIngredient(Material.COCOA_BEANS, 8);
            recipe.addIngredient(Material.COCOA_BEANS, 8);
            return recipe;
        }
    },
    ENCHANTED_COOKIE {
        @Override
        public Material getMaterial() {
            return Material.COOKIE;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.RARE;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.WHEAT, 8);
            recipe.addIngredient(ENCHANTED_COCOA_BEANS, 8);
            recipe.addIngredient(ENCHANTED_COCOA_BEANS, 8);
            recipe.addIngredient(ENCHANTED_COCOA_BEANS, 8);
            recipe.addIngredient(ENCHANTED_COCOA_BEANS, 8);
            return recipe;
        }
    },
    ENCHANTED_NETHER_WART {
        @Override
        public Material getMaterial() {
            return Material.NETHER_WART;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.NETHER_WART, 8);
            recipe.addIngredient(Material.NETHER_WART, 8);
            recipe.addIngredient(Material.NETHER_WART, 8);
            recipe.addIngredient(Material.NETHER_WART, 8);
            recipe.addIngredient(Material.NETHER_WART, 8);
            return recipe;
        }
    },
    ENCHANTED_CRIMSON_FUNGUS {
        @Override
        public Material getMaterial() {
            return Material.CRIMSON_FUNGUS;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.CRIMSON_FUNGUS, 8);
            recipe.addIngredient(Material.CRIMSON_FUNGUS, 8);
            recipe.addIngredient(Material.CRIMSON_FUNGUS, 8);
            recipe.addIngredient(Material.CRIMSON_FUNGUS, 8);
            recipe.addIngredient(Material.CRIMSON_FUNGUS, 8);
            return recipe;
        }
    },
    ENCHANTED_WARPED_FUNGUS {
        @Override
        public Material getMaterial() {
            return Material.WARPED_FUNGUS;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.WARPED_FUNGUS, 8);
            recipe.addIngredient(Material.WARPED_FUNGUS, 8);
            recipe.addIngredient(Material.WARPED_FUNGUS, 8);
            recipe.addIngredient(Material.WARPED_FUNGUS, 8);
            recipe.addIngredient(Material.WARPED_FUNGUS, 8);
            return recipe;
        }
    },
    ENCHANTED_GLOW_BERRIES {
        @Override
        public Material getMaterial() {
            return Material.GLOW_BERRIES;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.GLOW_BERRIES, 8);
            recipe.addIngredient(Material.GLOW_BERRIES, 8);
            recipe.addIngredient(Material.GLOW_BERRIES, 8);
            recipe.addIngredient(Material.GLOW_BERRIES, 8);
            recipe.addIngredient(Material.GLOW_BERRIES, 8);
            return recipe;
        }
    },
    ENCHANTED_DEAD_BUSH {
        @Override
        public Material getMaterial() {
            return Material.DEAD_BUSH;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.DEAD_BUSH, 8);
            recipe.addIngredient(Material.DEAD_BUSH, 8);
            recipe.addIngredient(Material.DEAD_BUSH, 8);
            recipe.addIngredient(Material.DEAD_BUSH, 8);
            recipe.addIngredient(Material.DEAD_BUSH, 8);
            return recipe;
        }
    },
    ENCHANTED_COBBLESTONE {
        @Override
        public Material getMaterial() {
            return Material.COBBLESTONE;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.COBBLESTONE, 8);
            recipe.addIngredient(Material.COBBLESTONE, 8);
            recipe.addIngredient(Material.COBBLESTONE, 8);
            recipe.addIngredient(Material.COBBLESTONE, 8);
            recipe.addIngredient(Material.COBBLESTONE, 8);
            return recipe;
        }
    },
    ENCHANTED_FLINT {
        @Override
        public Material getMaterial() {
            return Material.FLINT;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.FLINT, 8);
            recipe.addIngredient(Material.FLINT, 8);
            recipe.addIngredient(Material.FLINT, 8);
            recipe.addIngredient(Material.FLINT, 8);
            recipe.addIngredient(Material.FLINT, 8);
            return recipe;
        }
    },
    ENCHANTED_SAND {
        @Override
        public Material getMaterial() {
            return Material.SAND;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.SAND, 8);
            recipe.addIngredient(Material.SAND, 8);
            recipe.addIngredient(Material.SAND, 8);
            recipe.addIngredient(Material.SAND, 8);
            recipe.addIngredient(Material.SAND, 8);
            return recipe;
        }
    },
    ENCHANTED_ICE {
        @Override
        public Material getMaterial() {
            return Material.ICE;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.ICE, 8);
            recipe.addIngredient(Material.ICE, 8);
            recipe.addIngredient(Material.ICE, 8);
            recipe.addIngredient(Material.ICE, 8);
            recipe.addIngredient(Material.ICE, 8);
            return recipe;
        }
    },
    ENCHANTED_PACKED_ICE {
        @Override
        public Material getMaterial() {
            return Material.PACKED_ICE;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.RARE;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(ENCHANTED_ICE, 8);
            recipe.addIngredient(ENCHANTED_ICE, 8);
            recipe.addIngredient(ENCHANTED_ICE, 8);
            recipe.addIngredient(ENCHANTED_ICE, 8);
            recipe.addIngredient(ENCHANTED_ICE, 8);
            return recipe;
        }
    },
    ENCHANTED_COAL {
        @Override
        public Material getMaterial() {
            return Material.COAL;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.COAL, 8);
            recipe.addIngredient(Material.COAL, 8);
            recipe.addIngredient(Material.COAL, 8);
            recipe.addIngredient(Material.COAL, 8);
            recipe.addIngredient(Material.COAL, 8);
            return recipe;
        }
    },
    ENCHANTED_CHARCOAL {
        @Override
        public Material getMaterial() {
            return Material.CHARCOAL;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.COAL, 8);
            recipe.addIngredient(Material.COAL, 8);
            recipe.addIngredient(Material.ACACIA_LOG, Material.BIRCH_LOG, Material.DARK_OAK_LOG, Material.JUNGLE_LOG, Material.MANGROVE_LOG, Material.OAK_LOG, Material.SPRUCE_LOG);
            recipe.addIngredient(Material.COAL, 8);
            recipe.addIngredient(Material.COAL, 8);
            return recipe;
        }
    },
    ENCHANTED_BLOCK_OF_COAL {
        @Override
        public Material getMaterial() {
            return Material.COAL_BLOCK;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.RARE;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(ENCHANTED_COAL, 8);
            recipe.addIngredient(ENCHANTED_COAL, 8);
            recipe.addIngredient(ENCHANTED_COAL, 8);
            recipe.addIngredient(ENCHANTED_COAL, 8);
            recipe.addIngredient(ENCHANTED_COAL, 8);
            return recipe;
        }
    },
    ENCHANTED_DIAMOND {
        @Override
        public Material getMaterial() {
            return Material.DIAMOND;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.DIAMOND, 8);
            recipe.addIngredient(Material.DIAMOND, 8);
            recipe.addIngredient(Material.DIAMOND, 8);
            recipe.addIngredient(Material.DIAMOND, 8);
            recipe.addIngredient(Material.DIAMOND, 8);
            return recipe;
        }
    },
    ENCHANTED_DIAMOND_BLOCK {
        @Override
        public Material getMaterial() {
            return Material.DIAMOND_BLOCK;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.RARE;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(ENCHANTED_DIAMOND, 8);
            recipe.addIngredient(ENCHANTED_DIAMOND, 8);
            recipe.addIngredient(ENCHANTED_DIAMOND, 8);
            recipe.addIngredient(ENCHANTED_DIAMOND, 8);
            recipe.addIngredient(ENCHANTED_DIAMOND, 8);
            return recipe;
        }
    },
    ENCHANTED_EMERALD {
        @Override
        public Material getMaterial() {
            return Material.EMERALD;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.EMERALD, 8);
            recipe.addIngredient(Material.EMERALD, 8);
            recipe.addIngredient(Material.EMERALD, 8);
            recipe.addIngredient(Material.EMERALD, 8);
            recipe.addIngredient(Material.EMERALD, 8);
            return recipe;
        }
    },
    ENCHANTED_EMERALD_BLOCK {
        @Override
        public Material getMaterial() {
            return Material.EMERALD_BLOCK;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.RARE;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(ENCHANTED_EMERALD, 8);
            recipe.addIngredient(ENCHANTED_EMERALD, 8);
            recipe.addIngredient(ENCHANTED_EMERALD, 8);
            recipe.addIngredient(ENCHANTED_EMERALD, 8);
            recipe.addIngredient(ENCHANTED_EMERALD, 8);
            return recipe;
        }
    },
    ENCHANTED_GOLD {
        @Override
        public Material getMaterial() {
            return Material.GOLD_INGOT;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.GOLD_INGOT, 8);
            recipe.addIngredient(Material.GOLD_INGOT, 8);
            recipe.addIngredient(Material.GOLD_INGOT, 8);
            recipe.addIngredient(Material.GOLD_INGOT, 8);
            recipe.addIngredient(Material.GOLD_INGOT, 8);
            return recipe;
        }
    },
    ENCHANTED_GOLD_BLOCK {
        @Override
        public Material getMaterial() {
            return Material.GOLD_BLOCK;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.RARE;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(ENCHANTED_GOLD, 8);
            recipe.addIngredient(ENCHANTED_GOLD, 8);
            recipe.addIngredient(ENCHANTED_GOLD, 8);
            recipe.addIngredient(ENCHANTED_GOLD, 8);
            recipe.addIngredient(ENCHANTED_GOLD, 8);
            return recipe;
        }
    },
    ENCHANTED_IRON {
        @Override
        public Material getMaterial() {
            return Material.IRON_INGOT;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.IRON_INGOT, 8);
            recipe.addIngredient(Material.IRON_INGOT, 8);
            recipe.addIngredient(Material.IRON_INGOT, 8);
            recipe.addIngredient(Material.IRON_INGOT, 8);
            recipe.addIngredient(Material.IRON_INGOT, 8);
            return recipe;
        }
    },
    ENCHANTED_IRON_BLOCK {
        @Override
        public Material getMaterial() {
            return Material.IRON_BLOCK;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.RARE;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(ENCHANTED_IRON, 8);
            recipe.addIngredient(ENCHANTED_IRON, 8);
            recipe.addIngredient(ENCHANTED_IRON, 8);
            recipe.addIngredient(ENCHANTED_IRON, 8);
            recipe.addIngredient(ENCHANTED_IRON, 8);
            return recipe;
        }
    },
    ENCHANTED_COPPER {
        @Override
        public Material getMaterial() {
            return Material.COPPER_INGOT;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.COPPER_INGOT, 8);
            recipe.addIngredient(Material.COPPER_INGOT, 8);
            recipe.addIngredient(Material.COPPER_INGOT, 8);
            recipe.addIngredient(Material.COPPER_INGOT, 8);
            recipe.addIngredient(Material.COPPER_INGOT, 8);
            return recipe;
        }
    },
    ENCHANTED_COPPER_BLOCK {
        @Override
        public Material getMaterial() {
            return Material.COPPER_BLOCK;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.RARE;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(ENCHANTED_COPPER, 8);
            recipe.addIngredient(ENCHANTED_COPPER, 8);
            recipe.addIngredient(ENCHANTED_COPPER, 8);
            recipe.addIngredient(ENCHANTED_COPPER, 8);
            recipe.addIngredient(ENCHANTED_COPPER, 8);
            return recipe;
        }
    },
    ENCHANTED_LAPIS_LAZULI {
        @Override
        public Material getMaterial() {
            return Material.LAPIS_LAZULI;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.LAPIS_LAZULI, 8);
            recipe.addIngredient(Material.LAPIS_LAZULI, 8);
            recipe.addIngredient(Material.LAPIS_LAZULI, 8);
            recipe.addIngredient(Material.LAPIS_LAZULI, 8);
            recipe.addIngredient(Material.LAPIS_LAZULI, 8);
            return recipe;
        }
    },
    ENCHANTED_LAPIS_BLOCK {
        @Override
        public Material getMaterial() {
            return Material.LAPIS_BLOCK;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.RARE;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(ENCHANTED_LAPIS_LAZULI, 8);
            recipe.addIngredient(ENCHANTED_LAPIS_LAZULI, 8);
            recipe.addIngredient(ENCHANTED_LAPIS_LAZULI, 8);
            recipe.addIngredient(ENCHANTED_LAPIS_LAZULI, 8);
            recipe.addIngredient(ENCHANTED_LAPIS_LAZULI, 8);
            return recipe;
        }
    },
    ENCHANTED_REDSTONE {
        @Override
        public Material getMaterial() {
            return Material.REDSTONE;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.REDSTONE, 8);
            recipe.addIngredient(Material.REDSTONE, 8);
            recipe.addIngredient(Material.REDSTONE, 8);
            recipe.addIngredient(Material.REDSTONE, 8);
            recipe.addIngredient(Material.REDSTONE, 8);
            return recipe;
        }
    },
    ENCHANTED_REDSTONE_BLOCK {
        @Override
        public Material getMaterial() {
            return Material.REDSTONE_BLOCK;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.RARE;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(ENCHANTED_REDSTONE, 8);
            recipe.addIngredient(ENCHANTED_REDSTONE, 8);
            recipe.addIngredient(ENCHANTED_REDSTONE, 8);
            recipe.addIngredient(ENCHANTED_REDSTONE, 8);
            recipe.addIngredient(ENCHANTED_REDSTONE, 8);
            return recipe;
        }
    },
    ENCHANTED_SCULK {
        @Override
        public Material getMaterial() {
            return Material.SCULK;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.SCULK, 8);
            recipe.addIngredient(Material.SCULK, 8);
            recipe.addIngredient(Material.SCULK, 8);
            recipe.addIngredient(Material.SCULK, 8);
            recipe.addIngredient(Material.SCULK, 8);
            return recipe;
        }
    },
    SUPER_ENCHANTED_SCULK {
        @Override
        public Material getMaterial() {
            return Material.SCULK_CATALYST;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.RARE;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(ENCHANTED_SCULK, 8);
            recipe.addIngredient(ENCHANTED_SCULK, 8);
            recipe.addIngredient(ENCHANTED_SCULK, 8);
            recipe.addIngredient(ENCHANTED_SCULK, 8);
            recipe.addIngredient(ENCHANTED_SCULK, 8);
            return recipe;
        }
    },
    ENCHANTED_NETHERRACK {
        @Override
        public Material getMaterial() {
            return Material.NETHERRACK;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.NETHERRACK, 8);
            recipe.addIngredient(Material.NETHERRACK, 8);
            recipe.addIngredient(Material.NETHERRACK, 8);
            recipe.addIngredient(Material.NETHERRACK, 8);
            recipe.addIngredient(Material.NETHERRACK, 8);
            return recipe;
        }
    },
    ENCHANTED_QUARTZ {
        @Override
        public Material getMaterial() {
            return Material.QUARTZ;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.QUARTZ, 8);
            recipe.addIngredient(Material.QUARTZ, 8);
            recipe.addIngredient(Material.QUARTZ, 8);
            recipe.addIngredient(Material.QUARTZ, 8);
            recipe.addIngredient(Material.QUARTZ, 8);
            return recipe;
        }
    },
    ENCHANTED_QUARTZ_BLOCK {
        @Override
        public Material getMaterial() {
            return Material.QUARTZ_BLOCK;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.RARE;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(ENCHANTED_QUARTZ, 8);
            recipe.addIngredient(ENCHANTED_QUARTZ, 8);
            recipe.addIngredient(ENCHANTED_QUARTZ, 8);
            recipe.addIngredient(ENCHANTED_QUARTZ, 8);
            recipe.addIngredient(ENCHANTED_QUARTZ, 8);
            return recipe;
        }
    },
    ENCHANTED_GLOWSTONE_DUST {
        @Override
        public Material getMaterial() {
            return Material.GLOWSTONE_DUST;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.GLOWSTONE_DUST, 8);
            recipe.addIngredient(Material.GLOWSTONE_DUST, 8);
            recipe.addIngredient(Material.GLOWSTONE_DUST, 8);
            recipe.addIngredient(Material.GLOWSTONE_DUST, 8);
            recipe.addIngredient(Material.GLOWSTONE_DUST, 8);
            return recipe;
        }
    },
    ENCHANTED_GLOWSTONE {
        @Override
        public Material getMaterial() {
            return Material.GLOWSTONE;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(ENCHANTED_GLOWSTONE_DUST, 12);
            recipe.addIngredient(ENCHANTED_GLOWSTONE_DUST, 12);
            recipe.addIngredient(ENCHANTED_GLOWSTONE_DUST, 12);
            recipe.addIngredient(ENCHANTED_GLOWSTONE_DUST, 12);
            return recipe;
        }
    },
    ENCHANTED_REDSTONE_LAMP {
        @Override
        public Material getMaterial() {
            return Material.REDSTONE_LAMP;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.RARE;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(ENCHANTED_REDSTONE, 8);
            recipe.addIngredient(ENCHANTED_REDSTONE, 8);
            recipe.addIngredient(ENCHANTED_GLOWSTONE_DUST, 8);
            recipe.addIngredient(ENCHANTED_REDSTONE, 8);
            recipe.addIngredient(ENCHANTED_REDSTONE, 8);
            return recipe;
        }
    },
    ENCHANTED_BASALT {
        @Override
        public Material getMaterial() {
            return Material.BASALT;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.BASALT, 8);
            recipe.addIngredient(Material.BASALT, 8);
            recipe.addIngredient(Material.BASALT, 8);
            recipe.addIngredient(Material.BASALT, 8);
            recipe.addIngredient(Material.BASALT, 8);
            return recipe;
        }
    },
    ENCHANTED_NETHERITE {
        @Override
        public Material getMaterial() {
            return Material.NETHERITE_INGOT;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.NETHERITE_INGOT, 8);
            recipe.addIngredient(Material.NETHERITE_INGOT, 8);
            recipe.addIngredient(Material.NETHERITE_INGOT, 8);
            recipe.addIngredient(Material.NETHERITE_INGOT, 8);
            recipe.addIngredient(Material.NETHERITE_INGOT, 8);
            return recipe;
        }
    },
    ENCHANTED_NETHERITE_BLOCK {
        @Override
        public Material getMaterial() {
            return Material.NETHERITE_BLOCK;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.RARE;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(ENCHANTED_NETHERITE, 8);
            recipe.addIngredient(ENCHANTED_NETHERITE, 8);
            recipe.addIngredient(ENCHANTED_NETHERITE, 8);
            recipe.addIngredient(ENCHANTED_NETHERITE, 8);
            recipe.addIngredient(ENCHANTED_NETHERITE, 8);
            return recipe;
        }
    },
    ENCHANTED_OBSIDIAN {
        @Override
        public Material getMaterial() {
            return Material.OBSIDIAN;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.OBSIDIAN, 8);
            recipe.addIngredient(Material.OBSIDIAN, 8);
            recipe.addIngredient(Material.OBSIDIAN, 8);
            recipe.addIngredient(Material.OBSIDIAN, 8);
            recipe.addIngredient(Material.OBSIDIAN, 8);
            return recipe;
        }
    },
    ENCHANTED_DEEPSLATE {
        @Override
        public Material getMaterial() {
            return Material.DEEPSLATE;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.UNCOMMON;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(Material.DEEPSLATE, 8);
            recipe.addIngredient(Material.DEEPSLATE, 8);
            recipe.addIngredient(Material.DEEPSLATE, 8);
            recipe.addIngredient(Material.DEEPSLATE, 8);
            recipe.addIngredient(Material.DEEPSLATE, 8);
            return recipe;
        }
    },
    ENCHANTED_MITHRIL {
        @Override
        public Material getMaterial() {
            return Material.PRISMARINE_CRYSTALS;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.RARE;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapelessRecipe recipe = new CustomShapelessRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.addIngredient(MITHRIL, 8);
            recipe.addIngredient(MITHRIL, 8);
            recipe.addIngredient(MITHRIL, 8);
            recipe.addIngredient(MITHRIL, 8);
            recipe.addIngredient(MITHRIL, 8);
            return recipe;
        }
    },
    RECOMBOBULATOR_3000 {
        @Override
        public Material getMaterial() {
            return Material.PLAYER_HEAD;
        }
    },
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

        @Override
        public GemstoneSlotType[] getGemstoneSlots() {
            return new GemstoneSlotType[]{GemstoneSlotType.SAPPHIRE, GemstoneSlotType.COMBAT};
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

        @Override
        public GemstoneSlotType[] getGemstoneSlots() {
            return new GemstoneSlotType[]{GemstoneSlotType.SAPPHIRE, GemstoneSlotType.COMBAT};
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

        @Override
        public GemstoneSlotType[] getGemstoneSlots() {
            return new GemstoneSlotType[]{GemstoneSlotType.SAPPHIRE, GemstoneSlotType.COMBAT};
        }
    },
    NECRONS_BLADE {
        @Override
        public Material getMaterial() {
            return Material.IRON_SWORD;
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
            return new WeaponStats(260, 110, 0, 0, 0, 30);
        }

        @Override
        public AbilityStats getDefaultAbilityStats() {
            return new AbilityStats(0, 50);
        }

        @Override
        public CustomRecipe getRecipe() {
            CustomShapedRecipe recipe = new CustomShapedRecipe(new NamespacedKey(RevolutionSMP.getPlugin(), this.toString()), getItem());
            recipe.setShape(" W ", " W ", " H ");
            recipe.setIngredient('W', WITHER_CATALYST, 12);
            recipe.setIngredient('H', NECRONS_HANDLE);
            return recipe;
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

        @Override
        public GemstoneSlotType[] getGemstoneSlots() {
            return new GemstoneSlotType[]{GemstoneSlotType.SAPPHIRE, GemstoneSlotType.COMBAT};
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

        @Override
        public GemstoneSlotType[] getGemstoneSlots() {
            return new GemstoneSlotType[]{GemstoneSlotType.JASPER, GemstoneSlotType.COMBAT};
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

        @Override
        public GemstoneSlotType[] getGemstoneSlots() {
            return new GemstoneSlotType[]{GemstoneSlotType.COMBAT, GemstoneSlotType.COMBAT};
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

        @Override
        public GemstoneSlotType[] getGemstoneSlots() {
            return new GemstoneSlotType[]{GemstoneSlotType.DEFENSIVE, GemstoneSlotType.COMBAT};
        }
    },
    ASPECT_OF_THE_END {
        @Override
        public String getName() {
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

        @Override
        public GemstoneSlotType[] getGemstoneSlots() {
            return new GemstoneSlotType[]{GemstoneSlotType.SAPPHIRE};
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
        public String getName() {
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
        public String getName() {
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
        public MiningStats getDefaultMiningStats() {
            return new MiningStats(10, 10, 10, 5);
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
    TEST_DRILL {
        @Override
        public Rarity getDefaultRarity() {
            return Rarity.MYTHIC;
        }

        @Override
        public MiningStats getDefaultMiningStats() {
            return new MiningStats(1600, 120, 10, 9);
        }

        @Override
        public ItemType getItemType() {
            return ItemType.DRILL;
        }

        @Override
        public Material getMaterial() {
            return Material.PRISMARINE_SHARD;
        }

        @Override
        public GemstoneSlotType[] getGemstoneSlots() {
            return new GemstoneSlotType[]{GemstoneSlotType.AMBER, GemstoneSlotType.JADE, GemstoneSlotType.MINING};
        }
    },
    TITANIUM {
        @Override
        public Material getMaterial() {
            return Material.PLAYER_HEAD;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.RARE;
        }

        @Override
        public String getTexture() {
            return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGM4NjM3YmMwNjI4ZjU3MTBmODViOGQwNjc5Y2MyYzE5YWZlMGUxMDg5YjI0OGI2ZmRiNjdmNjg3YWU4MWRjMiJ9fX0=";
        }
    },
    MITHRIL {
        @Override
        public Material getMaterial() {
            return Material.PRISMARINE_CRYSTALS;
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
    },
    WITHER_CATALYST {
        @Override
        public Material getMaterial() {
            return Material.WITHER_SKELETON_SKULL;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.RARE;
        }
    },
    NECRONS_HANDLE {
        @Override
        public String getName() {
            return "Necron's Handle";
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.EPIC;
        }


        @Override
        public Material getMaterial() {
            return Material.STICK;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }
    },
    NECRONS_LADDER {
        @Override
        public String getName() {
            return "Necron's Ladder";
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.LEGENDARY;
        }


        @Override
        public Material getMaterial() {
            return Material.LADDER;
        }

        @Override
        public boolean isEnchantGlint() {
            return true;
        }
    },
    JUDGEMENT_CORE {
        @Override
        public Material getMaterial() {
            return Material.PLAYER_HEAD;
        }

        @Override
        public Rarity getDefaultRarity() {
            return Rarity.LEGENDARY;
        }

        @Override
        public String getTexture() {
            return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmYzZGRkN2Y4MTA4OWM4NWIyNmVkNTk3Njc1NTE5ZjAzYTFkY2Q2ZDE3MTNlMGNmYzY2YWZiODc0M2NiZTAifX19";
        }
    },
    TURING_SUGAR_CANE_HOE {
        @Override
        public Material getMaterial() {
            return Material.STONE_HOE;
        }

        @Override
        public ItemType getItemType() {
            return ItemType.HOE;
        }

        @Override
        public SpecialItemInfo getSpecialItemInfo(ItemInfo itemInfo) {
            return new FarmingToolInfo(itemInfo, CollectionType.SUGAR_CANE);
        }
    },
    GEMSTONE {
        @Override
        public Material getMaterial() {
            return Material.PLAYER_HEAD;
        }

        @Override
        public SpecialItemInfo getSpecialItemInfo(ItemInfo itemInfo) {
            return new GemstoneObject(itemInfo);
        }

        @Override
        public ItemStack getItem() {
            return GemstoneUtils.createGemstone(GemstoneType.RUBY, Rarity.COMMON);
        }
    },
    ENCHANTED_BOOK {
        @Override
        public Material getMaterial() {
            return Material.ENCHANTED_BOOK;
        }
    },
    TEMP_REFORGE_STONE {
        @Override
        public Material getMaterial() {
            return Material.PLAYER_HEAD;
        }
    };

    // Gets default stats for specific item
    public String getName() {
        return ItemEditor.getStringFromEnum(this);
    }

    public ItemType getItemType() {
        return ItemType.ITEM;
    }

    public Rarity getDefaultRarity() {
        return Rarity.COMMON;
    }

    public GemstoneSlotType[] getGemstoneSlots() {
        return null;
    }

    public List<EnchantmentObject> getDefaultEnchantments() {
        return new ArrayList<>();
    }

    public List<AbilityType> getDefaultAbilities() {
        return new ArrayList<>();
    }

    public abstract Material getMaterial();

    public Color getColor() {
        return null;
    }

    public boolean isUnbreakable() {
        try {
            Material.valueOf(this.toString());
            return false;
        } catch (IllegalArgumentException exception) {
            return true;
        }
    }

    public boolean isEnchantGlint() {
        return false;
    }

    public @Nullable SpecialItemInfo getSpecialItemInfo(ItemInfo itemInfo) {
        return null;
    }

    public String getTexture() {
        return null;
    }

    public List<ItemID> getUpgradedVersion() {
        return null;
    }

    public ItemStack getItem() {
        return ItemEditor.createItem(this);
    }

    public CustomRecipe getRecipe() {
        return null;
    }

    /*
    Default stats that apply on the item
     */

    public WeaponStats getDefaultWeaponStats() {
        return WeaponStats.createZero();
    }

    public ArmorStats getDefaultArmorStats() {
        return ArmorStats.createZero();
    }

    public AbilityStats getDefaultAbilityStats() {
        return AbilityStats.createZero();
    }

    public FishingStats getDefaultFishingStats() {
        return FishingStats.createZero();
    }

    public MiningStats getDefaultMiningStats() {
        return MiningStats.createZero();
    }

    public GatheringStats getDefaultGatheringStats() {
        return GatheringStats.createZero();
    }

    public LuckStats getDefaultLuckStats() {
        return LuckStats.createZero();
    }

    public RegenStats getDefaultRegenStats() {
        return RegenStats.createZero();
    }

    public WisdomStats getDefaultWisdomStats() {
        return WisdomStats.createZero();
    }


    /*
    Boosts that don't apply on the default item but buff players
     */

    @NotNull
    public WeaponStats getEventWeapon(Player damager, LivingEntity target, EntityDamageEvent event, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return WeaponStats.createMult();
        }
        return WeaponStats.createZero();
    }

    @NotNull
    public ArmorStats getEventArmor(LivingEntity damager, Player target, EntityDamageEvent event, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return ArmorStats.createMult();
        }
        return ArmorStats.createZero();
    }

    @NotNull
    public AbilityStats getEventAbility(Player damager, LivingEntity target, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return AbilityStats.createMult();
        }
        return AbilityStats.createZero();
    }

    @NotNull
    public FishingStats getEventFishing(Player fisher, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return FishingStats.createMult();
        }
        return FishingStats.createZero();
    }

    @NotNull
    public MiningStats getEventMining(Player miner, Block block, CustomOreLocation ore, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return MiningStats.createMult();
        }
        return MiningStats.createZero();
    }

    @NotNull
    public GatheringStats getEventGathering(Player harvester, Block block, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return GatheringStats.createMult();
        }
        return GatheringStats.createZero();
    }

    @NotNull
    public LuckStats getEventLuck(Player attacker, LivingEntity target, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return LuckStats.createMult();
        }
        return LuckStats.createZero();
    }

    @NotNull
    public WeaponStats getBonusWeapon(Player player, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return WeaponStats.createMult();
        }
        return WeaponStats.createZero();
    }

    @NotNull
    public ArmorStats getBonusArmor(Player player, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return ArmorStats.createMult();
        }
        return ArmorStats.createZero();
    }

    @NotNull
    public AbilityStats getBonusAbility(Player player, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return AbilityStats.createMult();
        }
        return AbilityStats.createZero();
    }

    @NotNull
    public FishingStats getBonusFishing(Player fisher, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return FishingStats.createMult();
        }
        return FishingStats.createZero();
    }

    @NotNull
    public MiningStats getBonusMining(Player miner, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return MiningStats.createMult();
        }
        return MiningStats.createZero();
    }

    @NotNull
    public GatheringStats getBonusGathering(Player harvester, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return GatheringStats.createMult();
        }
        return GatheringStats.createZero();
    }

    @NotNull
    public LuckStats getBonusLuck(Player attacker, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return LuckStats.createMult();
        }
        return LuckStats.createZero();
    }

    @NotNull
    public RegenStats getBonusRegen(Player player, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return RegenStats.createMult();
        }
        return RegenStats.createZero();
    }

    @NotNull
    public WisdomStats getBonusWisdom(Player player, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return WisdomStats.createMult();
        }
        return WisdomStats.createZero();
    }

    @NotNull
    public WisdomStats getCombatWisdom(Player player, LivingEntity target) {
        return WisdomStats.createZero();
    }

    @NotNull
    public WisdomStats getBreakingWisdom(Player player, Block block, CustomOreLocation ore) {
        return WisdomStats.createZero();
    }

    @NotNull
    public WisdomStats getRegularWisdom(Player player) {
        return WisdomStats.createZero();
    }

}