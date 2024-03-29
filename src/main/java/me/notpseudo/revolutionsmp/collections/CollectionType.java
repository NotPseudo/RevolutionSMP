package me.notpseudo.revolutionsmp.collections;

import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.items.ItemID;
import me.notpseudo.revolutionsmp.skills.SkillType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public enum CollectionType {

    WHEAT {
        @Override
        public SkillType getCategory() {
            return SkillType.FARMING;
        }

        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.WHEAT, Material.WHEAT_SEEDS);
        }
    },
    POTATO {
        @Override
        public SkillType getCategory() {
            return SkillType.FARMING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.POTATO, Material.POISONOUS_POTATO);
        }
    },
    CARROT {
        @Override
        public SkillType getCategory() {
            return SkillType.FARMING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.CARROT);
        }
    },
    MELON {
        @Override
        public SkillType getCategory() {
            return SkillType.FARMING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.MELON, Material.MELON_SLICE);
        }
    },
    SUGAR_CANE {
        @Override
        public SkillType getCategory() {
            return SkillType.FARMING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.SUGAR_CANE);
        }
    },
    PUMPKIN {
        @Override
        public SkillType getCategory() {
            return SkillType.FARMING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.PUMPKIN);
        }
    },
    FEATHER {
        @Override
        public SkillType getCategory() {
            return SkillType.FARMING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.FEATHER);
        }
    },
    LEATHER {
        @Override
        public SkillType getCategory() {
            return SkillType.FARMING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.LEATHER, Material.BEEF, Material.COOKED_BEEF);
        }
    },
    MUTTON {
        @Override
        public SkillType getCategory() {
            return SkillType.FARMING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.MUTTON, Material.COOKED_MUTTON);
        }
    },
    CHICKEN {
        @Override
        public SkillType getCategory() {
            return SkillType.FARMING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.CHICKEN, Material.COOKED_CHICKEN, Material.EGG);
        }
    },
    PORK {
        @Override
        public SkillType getCategory() {
            return SkillType.FARMING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.PORKCHOP, Material.COOKED_PORKCHOP);
        }
    },
    RABBIT {
        @Override
        public SkillType getCategory() {
            return SkillType.FARMING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.RABBIT, Material.COOKED_RABBIT, Material.RABBIT_HIDE, Material.RABBIT_FOOT);
        }
    },
    CACTUS {
        @Override
        public SkillType getCategory() {
            return SkillType.FARMING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.CACTUS);
        }
    },
    MUSHROOM {
        @Override
        public SkillType getCategory() {
            return SkillType.FARMING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.RED_MUSHROOM, Material.BROWN_MUSHROOM, Material.MUSHROOM_STEM, Material.BROWN_MUSHROOM_BLOCK, Material.RED_MUSHROOM_BLOCK);
        }
    },
    BEETROOT {
        @Override
        public SkillType getCategory() {
            return SkillType.FARMING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.BEETROOT, Material.BEETROOT_SEEDS);
        }
    },
    SWEET_BERRY {
        @Override
        public SkillType getCategory() {
            return SkillType.FARMING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.SWEET_BERRIES);
        }
    },
    COCOA {
        @Override
        public SkillType getCategory() {
            return SkillType.FARMING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.COCOA_BEANS);
        }
    },
    NETHER_WART {
        @Override
        public SkillType getCategory() {
            return SkillType.FARMING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.NETHER_WART, Material.NETHER_WART_BLOCK, Material.WARPED_WART_BLOCK);
        }
    },
    FUNGUS {
        @Override
        public SkillType getCategory() {
            return SkillType.FARMING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.CRIMSON_FUNGUS, Material.WARPED_FUNGUS);
        }
    },
    GLOW_BERRY {
        @Override
        public SkillType getCategory() {
            return SkillType.FARMING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.GLOW_BERRIES);
        }
    },
    DEAD_BUSH {
        @Override
        public SkillType getCategory() {
            return SkillType.FARMING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.DEAD_BUSH);
        }
    },
    COBBLESTONE {
        @Override
        public SkillType getCategory() {
            return SkillType.MINING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.COBBLESTONE, Material.STONE, Material.ANDESITE, Material.DIORITE, Material.GRANITE, Material.CALCITE);
        }
        @Override
        public List<ItemID> getLevelRecipeRewards(int level) {
            return switch (level) {
                default -> new ArrayList<>(0);
                case 1 -> List.of(ItemID.ENCHANTED_COBBLESTONE);
            };
        }
    },
    GRAVEL {
        @Override
        public SkillType getCategory() {
            return SkillType.MINING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.GRAVEL);
        }

    },
    SAND {
        @Override
        public SkillType getCategory() {
            return SkillType.MINING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.SAND);
        }
        @Override
        public List<ItemID> getLevelRecipeRewards(int level) {
            return switch (level) {
                default -> new ArrayList<>(0);
                case 1 -> List.of(ItemID.ENCHANTED_SAND);
            };
        }
    },
    ICE {
        @Override
        public SkillType getCategory() {
            return SkillType.MINING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.ICE, Material.PACKED_ICE, Material.BLUE_ICE);
        }
        @Override
        public List<ItemID> getLevelRecipeRewards(int level) {
            return switch (level) {
                default -> new ArrayList<>(0);
                case 1 -> List.of(ItemID.ENCHANTED_ICE);
            };
        }
    },
    COAL {
        @Override
        public SkillType getCategory() {
            return SkillType.MINING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.COAL, Material.COAL_BLOCK);
        }
        @Override
        public List<ItemID> getLevelRecipeRewards(int level) {
            return switch (level) {
                default -> new ArrayList<>(0);
                case 1 -> List.of(ItemID.ENCHANTED_COAL);
            };
        }
    },
    DIAMOND {
        @Override
        public SkillType getCategory() {
            return SkillType.MINING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.DIAMOND, Material.DIAMOND_BLOCK);
        }
        @Override
        public List<ItemID> getLevelRecipeRewards(int level) {
            return switch (level) {
                default -> new ArrayList<>(0);
                case 1 -> List.of(ItemID.ENCHANTED_DIAMOND);
            };
        }
    },
    EMERALD {
        @Override
        public SkillType getCategory() {
            return SkillType.MINING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.EMERALD, Material.EMERALD_BLOCK);
        }
        @Override
        public List<ItemID> getLevelRecipeRewards(int level) {
            return switch (level) {
                default -> new ArrayList<>(0);
                case 1 -> List.of(ItemID.ENCHANTED_EMERALD);
            };
        }
    },
    GOLD {
        @Override
        public SkillType getCategory() {
            return SkillType.MINING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.GOLD_INGOT, Material.GOLD_NUGGET, Material.RAW_GOLD, Material.GOLD_BLOCK, Material.RAW_GOLD_BLOCK);
        }
        @Override
        public List<ItemID> getLevelRecipeRewards(int level) {
            return switch (level) {
                default -> new ArrayList<>(0);
                case 1 -> List.of(ItemID.ENCHANTED_GOLD);
            };
        }
    },
    IRON {
        @Override
        public SkillType getCategory() {
            return SkillType.MINING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.IRON_INGOT, Material.RAW_IRON, Material.IRON_BLOCK, Material.RAW_IRON_BLOCK);
        }
        @Override
        public List<ItemID> getLevelRecipeRewards(int level) {
            return switch (level) {
                default -> new ArrayList<>(0);
                case 1 -> List.of(ItemID.ENCHANTED_IRON);
            };
        }
    },
    COPPER {
        @Override
        public SkillType getCategory() {
            return SkillType.MINING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.COPPER_INGOT, Material.RAW_COPPER, Material.COPPER_BLOCK, Material.RAW_COPPER_BLOCK);
        }
        @Override
        public List<ItemID> getLevelRecipeRewards(int level) {
            return switch (level) {
                default -> new ArrayList<>(0);
                case 1 -> List.of(ItemID.ENCHANTED_COPPER);
            };
        }
    },
    LAPIS {
        @Override
        public SkillType getCategory() {
            return SkillType.MINING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.LAPIS_LAZULI, Material.LAPIS_BLOCK);
        }
        @Override
        public List<ItemID> getLevelRecipeRewards(int level) {
            return switch (level) {
                default -> new ArrayList<>(0);
                case 1 -> List.of(ItemID.ENCHANTED_LAPIS_LAZULI);
            };
        }
    },
    REDSTONE {
        @Override
        public SkillType getCategory() {
            return SkillType.MINING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.REDSTONE, Material.REDSTONE_BLOCK);
        }
        @Override
        public List<ItemID> getLevelRecipeRewards(int level) {
            return switch (level) {
                default -> new ArrayList<>(0);
                case 1 -> List.of(ItemID.ENCHANTED_REDSTONE);
            };
        }
    },
    SCULK {
        @Override
        public SkillType getCategory() {
            return SkillType.MINING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.SCULK, Material.SCULK_VEIN, Material.SCULK_SENSOR, Material.SCULK_CATALYST, Material.SCULK_SHRIEKER);
        }
        @Override
        public List<ItemID> getLevelRecipeRewards(int level) {
            return switch (level) {
                default -> new ArrayList<>(0);
                case 1 -> List.of(ItemID.ENCHANTED_SCULK);
            };
        }
    },
    NETHERRACK {
        @Override
        public SkillType getCategory() {
            return SkillType.MINING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.NETHERRACK, Material.CRIMSON_NYLIUM, Material.WARPED_NYLIUM);
        }
        @Override
        public List<ItemID> getLevelRecipeRewards(int level) {
            return switch (level) {
                default -> new ArrayList<>(0);
                case 1 -> List.of(ItemID.ENCHANTED_NETHERRACK);
            };
        }
    },
    QUARTZ {
        @Override
        public SkillType getCategory() {
            return SkillType.MINING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.QUARTZ, Material.QUARTZ_BLOCK);
        }
        @Override
        public List<ItemID> getLevelRecipeRewards(int level) {
            return switch (level) {
                default -> new ArrayList<>(0);
                case 1 -> List.of(ItemID.ENCHANTED_QUARTZ);
            };
        }
    },
    GLOWSTONE {
        @Override
        public SkillType getCategory() {
            return SkillType.MINING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.GLOWSTONE_DUST, Material.GLOWSTONE);
        }
        @Override
        public List<ItemID> getLevelRecipeRewards(int level) {
            return switch (level) {
                default -> new ArrayList<>(0);
                case 1 -> List.of(ItemID.ENCHANTED_GLOWSTONE_DUST);
            };
        }
    },
    BASALT {
        @Override
        public SkillType getCategory() {
            return SkillType.MINING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.BASALT, Material.BLACKSTONE, Material.POLISHED_BASALT, Material.SMOOTH_BASALT);
        }
        @Override
        public List<ItemID> getLevelRecipeRewards(int level) {
            return switch (level) {
                default -> new ArrayList<>(0);
                case 1 -> List.of(ItemID.ENCHANTED_BASALT);
            };
        }
    },
    NETHERITE {
        @Override
        public SkillType getCategory() {
            return SkillType.MINING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.NETHERITE_INGOT, Material.ANCIENT_DEBRIS, Material.NETHERITE_BLOCK, Material.NETHERITE_SCRAP);
        }
        @Override
        public List<ItemID> getLevelRecipeRewards(int level) {
            return switch (level) {
                default -> new ArrayList<>(0);
                case 1 -> List.of(ItemID.ENCHANTED_NETHERITE);
            };
        }
    },
    OBSIDIAN {
        @Override
        public SkillType getCategory() {
            return SkillType.MINING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.OBSIDIAN, Material.CRYING_OBSIDIAN, Material.TUFF);
        }
        @Override
        public List<ItemID> getLevelRecipeRewards(int level) {
            return switch (level) {
                default -> new ArrayList<>(0);
                case 1 -> List.of(ItemID.ENCHANTED_OBSIDIAN);
            };
        }
    },
    DEEPSLATE {
        @Override
        public SkillType getCategory() {
            return SkillType.MINING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.DEEPSLATE, Material.COBBLED_DEEPSLATE);
        }
        @Override
        public List<ItemID> getLevelRecipeRewards(int level) {
            return switch (level) {
                default -> new ArrayList<>(0);
                case 1 -> List.of(ItemID.ENCHANTED_DEEPSLATE);
            };
        }
    },
    MITHRIL {
        @Override
        public SkillType getCategory() {
            return SkillType.MINING;
        }
        @Override
        public List<ItemID> getCustomMaterials() {
            return List.of(ItemID.MITHRIL, ItemID.TITANIUM);
        }
        @Override
        public List<ItemID> getLevelRecipeRewards(int level) {
            return switch (level) {
                default -> new ArrayList<>(0);
                case 1 -> List.of(ItemID.ENCHANTED_MITHRIL);
            };
        }
    },
    GEMSTONE {
        @Override
        public SkillType getCategory() {
            return SkillType.MINING;
        }
        @Override
        public List<ItemID> getCustomMaterials() {
            return List.of(ItemID.GEMSTONE);
        }
    },
    ROTTEN_FLESH {
        @Override
        public SkillType getCategory() {
            return SkillType.COMBAT;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.ROTTEN_FLESH);
        }
    },
    BONE {
        @Override
        public SkillType getCategory() {
            return SkillType.COMBAT;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.BONE, Material.BONE_BLOCK);
        }
    },
    GUNPOWDER {
        @Override
        public SkillType getCategory() {
            return SkillType.COMBAT;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.GUNPOWDER);
        }
    },
    SPIDER_EYE {
        @Override
        public SkillType getCategory() {
            return SkillType.COMBAT;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.SPIDER_EYE, Material.FERMENTED_SPIDER_EYE);
        }
    },
    STRING {
        @Override
        public SkillType getCategory() {
            return SkillType.COMBAT;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.STRING);
        }
    },
    ENDER_PEARL {
        @Override
        public SkillType getCategory() {
            return SkillType.COMBAT;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.ENDER_PEARL, Material.ENDER_EYE);
        }
    },
    BLAZE_ROD {
        @Override
        public SkillType getCategory() {
            return SkillType.COMBAT;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.BLAZE_ROD, Material.BLAZE_POWDER);
        }
    },
    MAGMA_CREAM {
        @Override
        public SkillType getCategory() {
            return SkillType.COMBAT;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.MAGMA_CREAM, Material.MAGMA_BLOCK);
        }
    },
    GHAST_TEAR {
        @Override
        public SkillType getCategory() {
            return SkillType.COMBAT;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.GHAST_TEAR);
        }
    },
    SLIMEBALL {
        @Override
        public SkillType getCategory() {
            return SkillType.COMBAT;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.SLIME_BALL, Material.SLIME_BLOCK);
        }
    },
    PHANTOM_MEMBRANE {
        @Override
        public SkillType getCategory() {
            return SkillType.COMBAT;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.PHANTOM_MEMBRANE);
        }
    },
    ACACIA_WOOD {
        @Override
        public SkillType getCategory() {
            return SkillType.FORAGING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.ACACIA_LOG,Material.ACACIA_WOOD, Material.STRIPPED_ACACIA_LOG);
        }
    },
    BIRCH_WOOD {
        @Override
        public SkillType getCategory() {
            return SkillType.FORAGING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.BIRCH_LOG);
        }
    },
    DARK_OAK_WOOD {
        @Override
        public SkillType getCategory() {
            return SkillType.FORAGING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.DARK_OAK_LOG);
        }
    },
    JUNGLE_WOOD {
        @Override
        public SkillType getCategory() {
            return SkillType.FORAGING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.JUNGLE_LOG);
        }
    },
    OAK_WOOD {
        @Override
        public SkillType getCategory() {
            return SkillType.FORAGING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.OAK_LOG, Material.OAK_WOOD, Material.STRIPPED_OAK_WOOD, Material.STRIPPED_OAK_LOG);
        }
    },
    SPRUCE_WOOD {
        @Override
        public SkillType getCategory() {
            return SkillType.FORAGING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.SPRUCE_LOG, Material.SPRUCE_WOOD, Material.STRIPPED_SPRUCE_WOOD, Material.STRIPPED_SPRUCE_LOG);
        }
    },
    MANGROVE_WOOD {
        @Override
        public SkillType getCategory() {
            return SkillType.FORAGING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.MANGROVE_LOG);
        }
    },
    CRIMSON_WOOD {
        @Override
        public SkillType getCategory() {
            return SkillType.FORAGING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.CRIMSON_STEM, Material.STRIPPED_CRIMSON_STEM);
        }
    },
    WARPED_WOOD {
        @Override
        public SkillType getCategory() {
            return SkillType.FORAGING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.WARPED_STEM, Material.STRIPPED_WARPED_STEM);
        }
    },
    CLAY {
        @Override
        public SkillType getCategory() {
            return SkillType.FISHING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.CLAY);
        }
    },
    TROPICAL_FISH {
        @Override
        public SkillType getCategory() {
            return SkillType.FISHING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.TROPICAL_FISH);
        }
    },
    INK_SAC {
        @Override
        public SkillType getCategory() {
            return SkillType.FISHING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.INK_SAC, Material.GLOW_INK_SAC);
        }
    },
    LILY_PAD {
        @Override
        public SkillType getCategory() {
            return SkillType.FISHING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.LILY_PAD);
        }
    },
    PRISMARINE {
        @Override
        public SkillType getCategory() {
            return SkillType.FISHING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.PRISMARINE, Material.PRISMARINE_CRYSTALS, Material.PRISMARINE_SHARD, Material.PRISMARINE_BRICKS, Material.DARK_PRISMARINE);
        }
    },
    PUFFERFISH {
        @Override
        public SkillType getCategory() {
            return SkillType.FISHING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.PUFFERFISH);
        }
    },
    COD {
        @Override
        public SkillType getCategory() {
            return SkillType.FISHING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.COD);
        }
    },
    SALMON {
        @Override
        public SkillType getCategory() {
            return SkillType.FISHING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.SALMON);
        }
    },
    SPONGE {
        @Override
        public SkillType getCategory() {
            return SkillType.FISHING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.SPONGE);
        }
    },
    SEAWEED {
        @Override
        public SkillType getCategory() {
            return SkillType.FISHING;
        }
        @Override
        public List<Material> getVanillaMaterials() {
            return List.of(Material.KELP, Material.SEAGRASS, Material.SEA_PICKLE);
        }
    };

    public String getName() {
        return ItemEditor.getStringFromEnum(this);
    }
    public SkillType getCategory() {
        return null;
    }

    public List<Material> getVanillaMaterials() {
        return new ArrayList<>(0);
    }

    public List<ItemID> getCustomMaterials() {
        return new ArrayList<>(0);
    }

    public List<ItemID> getLevelRecipeRewards(int level) {
        return new ArrayList<>(0);
    }

    public List<Component> getLevelUpMessage(int level) {
        ArrayList<Component> message = new ArrayList<>();
        message.add(Component.text("------------------------------", NamedTextColor.YELLOW, TextDecoration.BOLD));
        message.add(Component.text(" COLLECTION LEVEL UP", NamedTextColor.GOLD, TextDecoration.BOLD));
        message.add(Component.empty());
        message.add(Component.text(" " + getName(), NamedTextColor.YELLOW).append(Component.text(" " + (level - 1) + "➡", NamedTextColor.DARK_GRAY)).append(Component.text(level, NamedTextColor.YELLOW)));
        message.add(Component.empty());
        message.add(Component.text(" REWARDS", NamedTextColor.GREEN, TextDecoration.BOLD));
        for (ItemID item : getLevelRecipeRewards(level)) {
            message.add(Component.text("  " + item.getName(), item.getDefaultRarity().getRarityColor()).append(Component.text(" Recipe", NamedTextColor.GRAY)));
        }
        message.add(Component.text("------------------------------", NamedTextColor.YELLOW, TextDecoration.BOLD));
        return message;
    }

    public int getMaxLevel() {
        return 10;
    }

}