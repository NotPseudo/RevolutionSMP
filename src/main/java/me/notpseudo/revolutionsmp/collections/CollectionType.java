package me.notpseudo.revolutionsmp.collections;

import me.notpseudo.revolutionsmp.items.ItemID;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public enum CollectionType {

    WHEAT,
    POTATO,
    CARROT,
    MELON,
    SUGAR_CANE,
    PUMPKIN,
    FEATHER,
    LEATHER,
    MUTTON,
    CHICKEN,
    PORK,
    RABBIT,
    CACTUS,
    MUSHROOM,
    BEETROOT,
    SWEET_BERRY,
    COCOA,
    NETHER_WART,
    FUNGUS,
    GLOW_BERRY,
    DEAD_BUSH,
    COBBLESTONE,
    GRAVEL,
    SAND,
    ICE,
    COAL,
    DIAMOND,
    EMERALD,
    GOLD,
    IRON,
    COPPER,
    LAPIS,
    REDSTONE,
    NETHERRACK,
    QUARTZ,
    GLOWSTONE,
    BASALT,
    NETHERITE,
    OBSIDIAN,
    DEEPSLATE,
    MITHRIL,
    GEMSTONE,
    ROTTEN_FLESH,
    BONE,
    GUNPOWDER,
    SPIDER_EYE,
    STRING,
    ENDER_PEARL,
    BLAZE_ROD,
    MAGMA_CREAM,
    GHAST_TEAR,
    SLIMEBALL,
    PHANTOM_MEMBRANE,
    ACACIA_WOOD,
    BITCH_WOOD,
    DARK_OAK_WOOD,
    JUNGLE_WOOD,
    OAK_WOOD,
    SPRUCE_WOOD,
    CRIMSON_WOOD,
    WARPED_WOOD,
    CLAY,
    TROPICAL_FISH,
    INK_SAC,
    LILY_PAD,
    PRISMARINE,
    PUFFERFISH,
    COD,
    SALMON,
    SPONGE,
    SEAWEED;

    public CollectionCategory getCategory() {
        return null;
    }

    public List<Material> getVanillaMaterials() {
        return new ArrayList<>();
    }

    public List<ItemID> getCustomMaterials() {
        return new ArrayList<>();
    }

}