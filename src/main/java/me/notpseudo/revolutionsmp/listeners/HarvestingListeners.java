package me.notpseudo.revolutionsmp.listeners;

import com.destroystokyo.paper.event.block.BlockDestroyEvent;
import io.papermc.paper.event.block.BlockBreakBlockEvent;
import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.drops.ItemDropObject;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentType;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.items.ItemType;
import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.mining.*;
import me.notpseudo.revolutionsmp.playerstats.PlayerStats;
import me.notpseudo.revolutionsmp.specialiteminfo.GemstoneObject;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.type.CaveVinesPlant;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class HarvestingListeners implements Listener {

    private static final NamespacedKey worldPlacedKey = new NamespacedKey(RevolutionSMP.getPlugin(RevolutionSMP.class), "chunkPlaced");
    private static final Set<Material> foragingCollectionBlocks = new HashSet<>();
    private static final Set<Material> miningCollectionBlocks = new HashSet<>();
    private static final Set<Material> affectedByMiningSpeed = new HashSet<>();
    private static final Set<Material> farmingCollectionBlocks = new HashSet<>();

    private static final Set<Material> transparent = new HashSet<>(List.of(Material.AIR, Material.CAVE_AIR, Material.VOID_AIR, Material.WATER, Material.LAVA));

    public HarvestingListeners(RevolutionSMP plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        foragingCollectionBlocks.addAll(List.of(Material.CRIMSON_STEM, Material.STRIPPED_CRIMSON_STEM,
                Material.WARPED_STEM, Material.STRIPPED_WARPED_STEM, Material.WARPED_STEM,
                Material.OAK_WOOD, Material.OAK_LOG, Material.STRIPPED_OAK_WOOD, Material.STRIPPED_OAK_LOG,
                Material.DARK_OAK_LOG,
                Material.BIRCH_LOG,
                Material.JUNGLE_LOG,
                Material.ACACIA_WOOD, Material.ACACIA_LOG, Material.STRIPPED_ACACIA_LOG,
                Material.SPRUCE_WOOD, Material.SPRUCE_LOG, Material.STRIPPED_SPRUCE_WOOD, Material.STRIPPED_SPRUCE_LOG,
                Material.MANGROVE_LOG));
        miningCollectionBlocks.addAll(List.of(Material.COBBLESTONE, Material.STONE, Material.DEEPSLATE, Material.ANDESITE, Material.DIORITE, Material.GRANITE, Material.CALCITE, Material.BONE_BLOCK,
                Material.COAL_ORE, Material.DEEPSLATE_COAL_ORE, Material.COAL_BLOCK,
                Material.GRAVEL, Material.SAND, Material.ICE, Material.PACKED_ICE, Material.BLUE_ICE, Material.FROSTED_ICE,
                Material.GLOWSTONE, Material.BASALT, Material.BLACKSTONE, Material.OBSIDIAN, Material.CRYING_OBSIDIAN, Material.TUFF, Material.MAGMA_BLOCK, Material.NETHERRACK, Material.CRIMSON_NYLIUM, Material.WARPED_NYLIUM,
                Material.COPPER_ORE, Material.DEEPSLATE_COPPER_ORE,
                Material.LAPIS_ORE, Material.DEEPSLATE_LAPIS_ORE, Material.LAPIS_BLOCK,
                Material.IRON_ORE, Material.DEEPSLATE_IRON_ORE, Material.IRON_BLOCK,
                Material.REDSTONE_ORE, Material.DEEPSLATE_REDSTONE_ORE, Material.REDSTONE_BLOCK,
                Material.DIAMOND_ORE, Material.DEEPSLATE_DIAMOND_ORE, Material.DIAMOND_BLOCK,
                Material.GOLD_ORE, Material.GOLD_ORE, Material.NETHER_GOLD_ORE, Material.GOLD_BLOCK,
                Material.EMERALD_ORE, Material.EMERALD_ORE, Material.EMERALD_BLOCK,
                Material.NETHER_QUARTZ_ORE, Material.QUARTZ_BLOCK,
                Material.ANCIENT_DEBRIS, Material.NETHERITE_BLOCK,
                Material.SMALL_AMETHYST_BUD, Material.MEDIUM_AMETHYST_BUD, Material.LARGE_AMETHYST_BUD, Material.AMETHYST_CLUSTER,
                Material.BUDDING_AMETHYST, Material.AMETHYST_BLOCK));
        miningCollectionBlocks.addAll(Arrays.stream(Material.values()).filter(m -> m.isBlock() && m.toString().contains("PRISMARINE") && !m.isLegacy()).collect(Collectors.toSet()));
        farmingCollectionBlocks.addAll(List.of(Material.WHEAT, Material.POTATOES, Material.CARROTS,
                Material.PUMPKIN, Material.CARVED_PUMPKIN, Material.PUMPKIN_STEM, Material.ATTACHED_PUMPKIN_STEM,
                Material.MELON, Material.MELON_STEM, Material.ATTACHED_MELON_STEM,
                Material.CACTUS,
                Material.NETHER_WART,
                Material.COCOA,
                Material.RED_MUSHROOM, Material.RED_MUSHROOM_BLOCK, Material.BROWN_MUSHROOM, Material.BROWN_MUSHROOM_BLOCK, Material.MUSHROOM_STEM,
                Material.SUGAR_CANE,
                Material.BEETROOTS,
                Material.SWEET_BERRY_BUSH,
                Material.CRIMSON_FUNGUS, Material.WARPED_FUNGUS,
                Material.CAVE_VINES, Material.CAVE_VINES_PLANT,
                Material.DEAD_BUSH));
        affectedByMiningSpeed.addAll(miningCollectionBlocks);
        affectedByMiningSpeed.addAll(Arrays.stream(Material.values()).filter(m -> m.isBlock() && m.toString().contains("DEEPSLATE") && !m.isLegacy()).collect(Collectors.toSet()));
        affectedByMiningSpeed.addAll(Arrays.stream(Material.values()).filter(m -> m.isBlock() && m.toString().contains("COPPER") && !m.isLegacy()).collect(Collectors.toSet()));
        affectedByMiningSpeed.addAll(Arrays.stream(Material.values()).filter(m -> m.isBlock() && m.toString().contains("STONE") && !m.isLegacy()).collect(Collectors.toSet()));
        affectedByMiningSpeed.addAll(Arrays.stream(Material.values()).filter(m -> m.isBlock() && m.toString().contains("BRICK") && !m.isLegacy()).collect(Collectors.toSet()));
        affectedByMiningSpeed.addAll(Arrays.stream(Material.values()).filter(m -> m.isBlock() && m.toString().contains("PURPUR") && !m.isLegacy()).collect(Collectors.toSet()));
        affectedByMiningSpeed.addAll(Arrays.stream(Material.values()).filter(m -> m.isBlock() && m.toString().contains("CONCRETE") && !m.isLegacy()).collect(Collectors.toSet()));
        affectedByMiningSpeed.addAll(Arrays.stream(Material.values()).filter(m -> m.isBlock() && m.toString().contains("TERRACOTTA") && !m.isLegacy()).collect(Collectors.toSet()));
        affectedByMiningSpeed.addAll(Arrays.stream(Material.values()).filter(m -> m.isBlock() && m.toString().contains("QUARTZ") && !m.isLegacy()).collect(Collectors.toSet()));
        affectedByMiningSpeed.addAll(Arrays.stream(Material.values()).filter(m -> m.isBlock() && m.toString().contains("RAIL") && !m.isLegacy()).collect(Collectors.toSet()));
        affectedByMiningSpeed.addAll(List.of(
                Material.RESPAWN_ANCHOR, Material.ENDER_CHEST, Material.ANVIL, Material.BELL, Material.CHAIN, Material.ENCHANTING_TABLE,
                Material.IRON_BARS, Material.IRON_DOOR, Material.IRON_TRAPDOOR, Material.SPAWNER,
                Material.BLAST_FURNACE, Material.DISPENSER, Material.DROPPER, Material.FURNACE, Material.LANTERN, Material.OBSERVER, Material.STONECUTTER, Material.SMOKER, Material.LODESTONE, Material.HOPPER,
                Material.BEACON, Material.LIGHTNING_ROD, Material.CONDUIT, Material.CAULDRON, Material.SHULKER_BOX, Material.BREWING_STAND, Material.LIGHT_WEIGHTED_PRESSURE_PLATE, Material.HEAVY_WEIGHTED_PRESSURE_PLATE));
    }

    public static NamespacedKey getWorldPlacedKey() {
        return worldPlacedKey;
    }

    @EventHandler
    public void onOreBreak(BlockDamageEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
            return;
        }
        ItemInfo info = ItemEditor.getMainHandInfo(event.getPlayer());
        double breakingPower = 0, hardness = event.getBlock().getType().getHardness(), breakingPowerNeeded = getBreakingPower(event.getBlock().getType());
        String material = ItemEditor.getStringFromEnum(event.getBlock().getType());
        if (info != null &&
                (info.getItemType() == ItemType.PICKAXE || info.getItemType() == ItemType.DRILL || info.getItemType() == ItemType.GAUNTLET) &&
                info.getMiningStats() != null) {
            breakingPower = info.getMiningStats().getStatValue(StatType.BREAKING_POWER);
        }
        if (getPlacedLocationList(event.getBlock()).containsCustomOre(event.getBlock().getLocation())) {
            CustomOreLocation customOreLocation = getPlacedLocationList(event.getBlock()).getCustomOreFromLocation(event.getBlock().getLocation());
            breakingPowerNeeded = customOreLocation.getType().getBreakingPower();
            hardness = customOreLocation.getBlockStrength();
            material = customOreLocation.getType().getName();
        } else {
            if (!affectedByMiningSpeed.contains(event.getBlock().getType())) {
                return;
            }
        }
        if (breakingPower < breakingPowerNeeded) {
            // event.setCancelled(true);
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1F, 0.5F);
            event.getPlayer().sendMessage(Component.text("You need a stronger tool to mine " + material, NamedTextColor.RED));
            CustomMiningUtils.addSlow(event.getPlayer(), 60);
            return;
        }
        event.getPlayer().sendMessage(Component.text("BlockDamageEvent", NamedTextColor.GREEN));
        Location location = event.getBlock().getLocation();
        event.getPlayer().sendMessage(Component.text("BDE Creating a custom breaking block at X:" + location.getX() + ", Y:" + location.getY() + ", Z:" + location.getZ(), NamedTextColor.YELLOW));
        event.getPlayer().sendMessage(Component.text("BDE Hardness: " + hardness * 3, NamedTextColor.RED));
        CustomMiningUtils.createBreakingBlock(event.getBlock(), hardness * 3);
    }

    @EventHandler
    public void onDamageAbort(BlockDamageAbortEvent event) {
        CustomMiningUtils.removeBreakingBlock(event.getBlock().getLocation());
        CustomMiningUtils.removeSlow(event.getPlayer());
    }

    @EventHandler
    public void onOreBlockDamage(PlayerAnimationEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
            return;
        }
        Player player = event.getPlayer();
        Block block = player.getTargetBlock(transparent, 5);
        ItemInfo info = ItemEditor.getMainHandInfo(event.getPlayer());
        double breakingPower = 0, breakingPowerNeeded = getBreakingPower(block.getType());
        if (info != null &&
                (info.getItemType() == ItemType.PICKAXE || info.getItemType() == ItemType.DRILL || info.getItemType() == ItemType.GAUNTLET) &&
                info.getMiningStats() != null) {
            breakingPower = info.getMiningStats().getStatValue(StatType.BREAKING_POWER);
        }
        if (getPlacedLocationList(block).containsCustomOre(block.getLocation())) {
            CustomOreLocation customOreLocation = getPlacedLocationList(block).getCustomOreFromLocation(block.getLocation());
            breakingPowerNeeded = customOreLocation.getType().getBreakingPower();
            event.getPlayer().sendMessage(Component.text("PlayerAnimationEvent", NamedTextColor.GREEN));
            event.getPlayer().sendMessage(Component.text("PAE Detected custom ore at X:" + block.getX() + ", Y:" + block.getY() + ", Z:" + block.getZ(), NamedTextColor.YELLOW));
        } else {
            if (!affectedByMiningSpeed.contains(block.getType())) {
                return;
            }
        }
        if (breakingPower < breakingPowerNeeded) {
            event.setCancelled(true);
            CustomMiningUtils.addSlow(player, 60);
            return;
        }
        Location blockLoc = block.getLocation();
        if(!CustomMiningUtils.isBreakingBlock(blockLoc)) {
            event.getPlayer().sendMessage(Component.text("PAE Not a custom breaking block at X:" + block.getX() + ", Y:" + block.getY() + ", Z:" + block.getZ(), NamedTextColor.YELLOW));
            return;
        }
        if (player.getLocation().distanceSquared(blockLoc) > 49) {
            return;
        }
        PlayerStats playerStats = StatsListeners.getPlayerStats(player);
        MiningStats add = StatsListeners.getEventMining(player, block, IncreaseType.INCREASE),
                addPercent = StatsListeners.getEventMining(player, block, IncreaseType.ADDITIVE_PERCENT),
                mult = StatsListeners.getEventMining(player, block, IncreaseType.MULTIPLICATIVE_PERCENT);
        double miningSpeed = (playerStats.getMiningSpeed() + add.getStatValue(StatType.MINING_SPEED)) * (1 + (addPercent.getStatValue(StatType.MINING_SPEED) / 100)) * mult.getStatValue(StatType.MINING_SPEED);
        BreakingBlock breakBlock = CustomMiningUtils.getBreakingBlock(blockLoc);
        event.getPlayer().sendMessage(Component.text("PAE Custom breaking block at X:" + block.getX() + ", Y:" + block.getY() + ", Z:" + block.getZ() + " has a damage per stage of " + breakBlock.getDamagePerStage(), NamedTextColor.YELLOW));
        breakBlock.damage(player, Math.max(0.5, miningSpeed / 50));
    }

    @EventHandler
    public void onOreBreak(BlockBreakEvent event) {
        PlacedLocationList locations = getPlacedLocationList(event.getBlock());
        CustomOreLocation customOre = locations.getCustomOreFromLocation(event.getBlock().getLocation());
        boolean isCustom = removeOreLocation(event), playerPlaced = removePlacedLocation(event);
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if (player.getGameMode() == GameMode.CREATIVE) {
            return;
        }
        int breakingPower = 0;
        ItemInfo mainHand = ItemEditor.getMainHandInfo(player);
        if (mainHand != null) {
            if (mainHand.getEnchantmentsHolder() != null && mainHand.getEnchantmentsHolder().containsEnchant(EnchantmentType.SILK_TOUCH)) {
                return;
            }
            if (mainHand.getMiningStats() != null) {
                breakingPower = (int) mainHand.getMiningStats().getStatValue(StatType.BREAKING_POWER);
            }
        }
        List<ItemDropObject> drops;
        PlayerStats playerStats = StatsListeners.getPlayerStats(player);
        MiningStats add = StatsListeners.getEventMining(player, block, IncreaseType.INCREASE),
                addPercent = StatsListeners.getEventMining(player, block, IncreaseType.ADDITIVE_PERCENT),
                mult = StatsListeners.getEventMining(player, block, IncreaseType.MULTIPLICATIVE_PERCENT);
        double miningFortune = (playerStats.getMiningFortune() + add.getStatValue(StatType.MINING_FORTUNE)) * (1 + (addPercent.getStatValue(StatType.MINING_FORTUNE) / 100)) * mult.getStatValue(StatType.MINING_FORTUNE),
        pristine = (playerStats.getPristine() + add.getStatValue(StatType.PRISTINE)) * (1 + (addPercent.getStatValue(StatType.PRISTINE) / 100)) * mult.getStatValue(StatType.PRISTINE);
        if (playerPlaced) {
            miningFortune = -50;
            pristine = -50;
        }
        int breakingPowerNeeded = getBreakingPower(block.getType());
        if (isCustom) {
            event.setDropItems(false);breakingPowerNeeded = customOre.getType().getBreakingPower();
            drops = customOre.getDrops();
        } else {
            if (!miningCollectionBlocks.contains(event.getBlock().getType())) {
                return;
            }
            drops = getMiningDropsForMaterial(block.getType(), miningFortune);
        }
        if (breakingPower < breakingPowerNeeded) {
            event.setDropItems(false);
            return;
        }
        for (ItemDropObject drop : drops) {
            ItemDropObject pristineDrop = GemstoneUtils.handlePristine(drop, miningFortune, pristine);
            if (pristineDrop != null) {
                pristineDrop.drop(block.getLocation());
                GemstoneObject gem = GemstoneUtils.getGemObject(pristineDrop.getItem());
                player.sendMessage(Component.text("PRISTINE ", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD)
                        .append(Component.text("You found ", NamedTextColor.WHITE))
                        .append(Component.text(gem.getGem().getStat().getSymbol() + " " + gem.getRating() + " " + ItemEditor.getStringFromEnum(gem.getGem()) + " ", gem.getGem().getStat().getColor()))
                        .append(Component.text("x" + pristineDrop.getItem().getAmount(), NamedTextColor.DARK_GRAY)).append(Component.text("!", NamedTextColor.WHITE)).decoration(TextDecoration.BOLD, false));
            }
            drop.drop(block.getLocation());
        }
    }

    public static void createCustomBlock(CustomOreType type, Location location) {
        PlacedLocationList placedLocationList = getPlacedLocationList(location.getBlock());
        CustomOreLocation customOreLocation = placedLocationList.addCustomOreLocation(location, type);
        location.getBlock().setType(customOreLocation.getVanillaMaterial());
        CustomMiningUtils.createBreakingBlock(location.getBlock(), customOreLocation.getBlockStrength() * 3);
        location.getBlock().getWorld().getPersistentDataContainer().set(worldPlacedKey, new PlacedLocationListDataType(), placedLocationList);
    }

    @EventHandler
    public void onLogBreak(BlockBreakEvent event) {
        if (!foragingCollectionBlocks.contains(event.getBlock().getType())) {
            return;
        }
        if (removePlacedLocation(event)) {
            return;
        }
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
            return;
        }
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
            return;
        }
        Player player = event.getPlayer();
        PlayerStats playerStats = StatsListeners.getPlayerStats(player);
        GatheringStats add = StatsListeners.getEventGathering(player, event.getBlock(), IncreaseType.INCREASE),
                addPercent = StatsListeners.getEventGathering(player, event.getBlock(), IncreaseType.ADDITIVE_PERCENT),
                mult = StatsListeners.getEventGathering(player, event.getBlock(), IncreaseType.MULTIPLICATIVE_PERCENT);
        double foragingFortune = (playerStats.getForagingFortune() + add.getStatValue(StatType.FORAGING_FORTUNE)) * (1 + (addPercent.getStatValue(StatType.FORAGING_FORTUNE) / 100)) * mult.getStatValue(StatType.FORAGING_FORTUNE);
        for (ItemDropObject drop : getDropsForMaterial(event.getBlock().getType(), foragingFortune)) {
            drop.drop(event.getBlock().getLocation());
        }
    }

    @EventHandler
    public void onGravityCropBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() != Material.SUGAR_CANE || event.getBlock().getType() != Material.CACTUS
                || event.getBlock().getType() != Material.CAVE_VINES || event.getBlock().getType() != Material.CAVE_VINES_PLANT) {
            return;
        }
        PlayerStats playerStats = StatsListeners.getPlayerStats(event.getPlayer());
        List<ItemDropObject> drops = new ArrayList<>();
        if (!removePlacedLocation(event)) {
            drops.addAll(getDropsForMaterial(event.getBlock().getType(), playerStats.getFarmingFortune()));
        }
        removeOreLocation(event);
        Block block = event.getBlock();
        PlacedLocationList placedList = getPlacedLocationList(event.getBlock());
        if (block.getType() == Material.SUGAR_CANE || block.getType() == Material.CACTUS) {
            List<Block> above = getBlocksInDirectionMatching(block.getLocation(), block.getType(), BlockFace.UP);
            for (Block aboveBlock : above) {
                if (!placedList.removePlacedLocation(aboveBlock.getLocation())) {
                    drops.addAll(getDropsForMaterial(aboveBlock.getType(), playerStats.getFarmingFortune()));
                }
            }
        }
        if (block.getType() == Material.CAVE_VINES || block.getType() == Material.CAVE_VINES_PLANT) {
            List<Block> downVines = getBlocksInDirectionMatching(block.getLocation(), Material.CAVE_VINES, BlockFace.DOWN);
            downVines.addAll(getBlocksInDirectionMatching(block.getLocation(), Material.CAVE_VINES_PLANT, BlockFace.DOWN));
            for (Block downBlock : downVines) {
                if (!placedList.removePlacedLocation(downBlock.getLocation()) && downBlock.getBlockData() instanceof CaveVinesPlant vinesPlant && vinesPlant.isBerries()) {
                    drops.addAll(getDropsForMaterial(downBlock.getType(), playerStats.getFarmingFortune()));
                }
            }
        }
        for (ItemDropObject drop : drops) {
            drop.drop(event.getBlock().getLocation());
        }
    }

    @EventHandler
    public void onCropBreak(BlockBreakEvent event) {
        if (!farmingCollectionBlocks.contains(event.getBlock().getType())) {
            return;
        }
        if (event.getBlock().getType() == Material.SUGAR_CANE || event.getBlock().getType() == Material.CACTUS
                || event.getBlock().getType() == Material.CAVE_VINES || event.getBlock().getType() == Material.CAVE_VINES_PLANT) {
            return;
        }
        if (event.getBlock().getBlockData() instanceof Ageable age) {
            if (age.getAge() < age.getMaximumAge()) {
                removePlacedLocation(event);
                removeOreLocation(event);
                return;
            }
        }
        removeOreLocation(event);
        Material mat = event.getBlock().getType();
        if (removePlacedLocation(event)) {
            if (mat == Material.PUMPKIN || mat == Material.CARVED_PUMPKIN
                    || mat == Material.MELON
                    || mat == Material.RED_MUSHROOM || mat == Material.RED_MUSHROOM_BLOCK || mat == Material.BROWN_MUSHROOM || mat == Material.BROWN_MUSHROOM_BLOCK || mat == Material.MUSHROOM_STEM
                    || mat == Material.CRIMSON_FUNGUS || mat == Material.WARPED_FUNGUS
                    || mat == Material.CAVE_VINES || mat == Material.CAVE_VINES_PLANT
                    || mat == Material.DEAD_BUSH) {
                return;
            }
        }
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
            return;
        }
        PlayerStats playerStats = StatsListeners.getPlayerStats(event.getPlayer());
        GatheringStats add = StatsListeners.getEventGathering(event.getPlayer(), event.getBlock(), IncreaseType.INCREASE),
                addPercent = StatsListeners.getEventGathering(event.getPlayer(), event.getBlock(), IncreaseType.ADDITIVE_PERCENT),
                mult = StatsListeners.getEventGathering(event.getPlayer(), event.getBlock(), IncreaseType.MULTIPLICATIVE_PERCENT);
        double farmingFortune = (playerStats.getFarmingFortune() + add.getStatValue(StatType.FARMING_FORTUNE)) * (1 + (addPercent.getStatValue(StatType.FARMING_FORTUNE) / 100)) * mult.getStatValue(StatType.FARMING_FORTUNE);
        for (ItemDropObject drop : getDropsForMaterial(event.getBlock().getType(), farmingFortune)) {
            drop.drop(event.getBlock().getLocation());
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        addPlacedLocation(event);
    }

    @EventHandler
    public void onBlockBurn(BlockBurnEvent event) {
        removePlacedLocation(event);
        removeOreLocation(event);
    }

    @EventHandler
    public void onBlockBreakBlock(BlockBreakBlockEvent event) {
        removePlacedLocation(event);
        removeOreLocation(event);
    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent event) {
        removePlacedLocation(event);
        removeOreLocation(event);
    }

    @EventHandler
    public void onBlockFade(BlockFadeEvent event) {
        removePlacedLocation(event);
        removeOreLocation(event);
    }

    @EventHandler
    public void onLeafDecay(LeavesDecayEvent event) {
        removePlacedLocation(event);
        removeOreLocation(event);
    }

    @EventHandler
    public void onBlockDestroy(BlockDestroyEvent event) {
        removePlacedLocation(event);
        removeOreLocation(event);
    }

    @EventHandler
    public void onPistonPush(BlockPistonExtendEvent event) {
        updatePistonPlaced(event.getDirection(), event.getBlocks(), event);
    }

    @EventHandler
    public void onPistonRetract(BlockPistonRetractEvent event) {
        updatePistonPlaced(event.getDirection(), event.getBlocks(), event);
    }

    private void updatePistonPlaced(BlockFace direction, List<Block> blocks, BlockPistonEvent event) {
        PlacedLocationList placedList = getPlacedLocationList(event.getBlock());
        int changeX = 0, changeY = 0, changeZ = 0;
        switch (direction) {
            case NORTH -> changeZ = -1;
            case SOUTH -> changeZ = 1;
            case EAST -> changeX = 1;
            case WEST -> changeX = -1;
            case UP -> changeY = 1;
            case DOWN -> changeY = -1;
        }
        for (Block block : blocks) {
            PlacedLocation newLocation = placedList.getPlacedFromLocation(block.getLocation());
            if (newLocation != null) {
                newLocation.add(changeX, changeY, changeZ);
            }
            PlacedLocation newOreLocation = placedList.getCustomOreFromLocation(block.getLocation());
            if (newOreLocation != null) {
                newOreLocation.add(changeX, changeY, changeZ);
            }
        }
        event.getBlock().getWorld().getPersistentDataContainer().set(worldPlacedKey, new PlacedLocationListDataType(), placedList);
    }

    public static List<ItemDropObject> getDropsForMaterial(Material material, double fortune) {
        int multiplier = getAddedTimes(fortune);
        return switch (material) {
            default -> List.of(new ItemDropObject(material, multiplier));
            case WHEAT -> List.of(new ItemDropObject(Material.WHEAT, multiplier), new ItemDropObject(Material.WHEAT_SEEDS, ((int) (Math.random() * 3)) * multiplier));
            case POTATOES -> List.of(new ItemDropObject(Material.POTATO, ((int) (Math.random() * 5) + 1) * multiplier), new ItemDropObject(2, Material.POISONOUS_POTATO, multiplier));
            case CARROTS -> List.of(new ItemDropObject(Material.CARROT, ((int) (Math.random() * 4) + 2) * multiplier));
            case PUMPKIN_STEM, ATTACHED_PUMPKIN_STEM -> List.of(new ItemDropObject(Material.PUMPKIN_SEEDS, (int) (Math.random() * 4) * multiplier));
            case MELON -> List.of(new ItemDropObject(Material.MELON_SLICE, ((int) (Math.random() * 5) + 3) * multiplier));
            case MELON_STEM,  ATTACHED_MELON_STEM -> List.of(new ItemDropObject(Material.MELON_SEEDS, (int) (Math.random() * 4) * multiplier));
            case NETHER_WART -> List.of(new ItemDropObject(Material.NETHER_WART, ((int) (Math.random() * 3) + 2) * multiplier));
            case COCOA -> List.of(new ItemDropObject(Material.COCOA_BEANS, ((int) (Math.random() * 2) + 2) * multiplier));
            case RED_MUSHROOM_BLOCK -> List.of(new ItemDropObject(Material.RED_MUSHROOM, (int) (Math.random() * 3) * multiplier));
            case BROWN_MUSHROOM_BLOCK -> List.of(new ItemDropObject(Material.BROWN_MUSHROOM, (int) (Math.random() * 3) * multiplier));
            case MUSHROOM_STEM -> List.of(new ItemDropObject(Math.random() < 0.5 ? Material.RED_MUSHROOM : Material.BROWN_MUSHROOM, (int) (Math.random() * 3) * multiplier));
            case BEETROOTS -> List.of(new ItemDropObject(Material.BEETROOT, multiplier), new ItemDropObject(Material.BEETROOT_SEEDS, ((int) (Math.random() * 4) + 1) * multiplier));
            case SWEET_BERRY_BUSH -> List.of(new ItemDropObject(Material.SWEET_BERRIES, ((int) (Math.random() * 2) + 2) * multiplier));
            case CAVE_VINES, CAVE_VINES_PLANT -> List.of(new ItemDropObject(Material.GLOW_BERRIES, multiplier));
            case DEAD_BUSH -> List.of(new ItemDropObject(75, Material.STICK, (int) (Math.random() * 3)), new ItemDropObject(25, Material.DEAD_BUSH, multiplier));
        };
    }

    public static List<ItemDropObject> getMiningDropsForMaterial(Material material, double fortune) {
        int multiplier = getAddedTimes(fortune);
        return switch (material) {
            default -> List.of(new ItemDropObject(material, multiplier));
            case STONE -> List.of(new ItemDropObject(Material.COBBLESTONE, multiplier));
            case COAL_ORE, DEEPSLATE_COAL_ORE -> List.of(new ItemDropObject(Material.COAL, multiplier));
            case GLOWSTONE -> List.of(new ItemDropObject(Material.GLOWSTONE_DUST, ((int) (Math.random() * 3) + 2) * multiplier));
            case COPPER_ORE, DEEPSLATE_COPPER_ORE -> List.of(new ItemDropObject(Material.RAW_COPPER, ((int) (Math.random() * 4) + 2) * multiplier));
            case LAPIS_ORE, DEEPSLATE_LAPIS_ORE -> List.of(new ItemDropObject(Material.LAPIS_LAZULI, ((int) (Math.random() * 6) + 4) * multiplier));
            case IRON_ORE, DEEPSLATE_IRON_ORE -> List.of(new ItemDropObject(Material.RAW_IRON, multiplier));
            case REDSTONE_ORE, DEEPSLATE_REDSTONE_ORE -> List.of(new ItemDropObject(Material.REDSTONE, ((int) (Math.random() * 2) + 4) * multiplier));
            case DIAMOND_ORE, DEEPSLATE_DIAMOND_ORE -> List.of(new ItemDropObject(Material.DIAMOND, multiplier));
            case GOLD_ORE, DEEPSLATE_GOLD_ORE -> List.of(new ItemDropObject(Material.RAW_GOLD, multiplier));
            case NETHER_GOLD_ORE -> List.of(new ItemDropObject(Material.GOLD_NUGGET, ((int) (Math.random() * 5) + 2) * multiplier));
            case EMERALD_ORE, DEEPSLATE_EMERALD_ORE -> List.of(new ItemDropObject(Material.EMERALD, multiplier));
            case NETHER_QUARTZ_ORE -> List.of(new ItemDropObject(Material.QUARTZ, multiplier));
            case SMALL_AMETHYST_BUD, MEDIUM_AMETHYST_BUD, LARGE_AMETHYST_BUD, AMETHYST_CLUSTER -> List.of(new ItemDropObject(Material.AMETHYST_SHARD, 4 * multiplier));
            case BUDDING_AMETHYST -> List.of(new ItemDropObject(Material.AMETHYST_BLOCK, multiplier));
        };
    }

    public int getBreakingPower(Material material) {
        if (!affectedByMiningSpeed.contains(material)) {
            return 0;
        }
        if (material.toString().contains("COPPER")) {
            return 2;
        }
        return switch (material) {
            default -> 1;
            case DEEPSLATE, IRON_ORE, DEEPSLATE_IRON_ORE, IRON_BLOCK, LAPIS_ORE, DEEPSLATE_LAPIS_ORE, LAPIS_BLOCK, COPPER_ORE, DEEPSLATE_COPPER_ORE, LIGHTNING_ROD -> 2;
            case DIAMOND_ORE, DEEPSLATE_DIAMOND_ORE, DIAMOND_BLOCK, EMERALD_ORE, DEEPSLATE_EMERALD_ORE, EMERALD_BLOCK, GOLD_ORE, DEEPSLATE_GOLD_ORE, GOLD_BLOCK, REDSTONE_ORE, DEEPSLATE_REDSTONE_ORE, REDSTONE_BLOCK -> 3;
            case OBSIDIAN, CRYING_OBSIDIAN, NETHERITE_BLOCK, RESPAWN_ANCHOR, ANCIENT_DEBRIS -> 4;
        };
    }

    public static int getAddedTimes(double chance) {
        int count = (int) (chance / 100);
        if (Math.random() * 100 < chance % 100.0) {
            count++;
        }
        return count;
    }

    private static List<Block> getBlocksInDirectionMatching(Location location, Material material, BlockFace direction) {
        List<Block> blocks = new ArrayList<>();
        Block current = location.getBlock();
        while (current.getRelative(direction).getType() == material && current.getRelative(direction).getType() != Material.AIR) {
            blocks.add(current.getRelative(direction));
            current = current.getRelative(direction);
        }
        return blocks;
    }

    @NotNull
    public static PlacedLocationList getPlacedLocationList(Block block) {
        PlacedLocationList placedList = block.getWorld().getPersistentDataContainer().get(worldPlacedKey, new PlacedLocationListDataType());
        if (placedList == null) {
            placedList = new PlacedLocationList();
            block.getWorld().getPersistentDataContainer().set(worldPlacedKey, new PlacedLocationListDataType(), placedList);
        }
        return placedList;
    }

    public static void setPlacedLocationList(Block block, PlacedLocationList list) {
        block.getWorld().getPersistentDataContainer().set(worldPlacedKey, new PlacedLocationListDataType(), list);
    }

    /**
     * Tries to add the event block's location as player-placed if the block is affected by fortune. Returns if the location was not already player-placed
     *
     * @param event The event containing the block location
     * @return true if the block is affect by fortune and the location was not already marked as player-placed
     */
    private boolean addPlacedLocation(BlockEvent event) {
        if (!(foragingCollectionBlocks.contains(event.getBlock().getType()) || miningCollectionBlocks.contains(event.getBlock().getType()) || farmingCollectionBlocks.contains(event.getBlock().getType()))) {
            return false;
        }
        PlacedLocationList placed = getPlacedLocationList(event.getBlock());
        Location loc = event.getBlock().getLocation();
        boolean isNew = placed.addPlacedLocation(loc);
        event.getBlock().getWorld().getPersistentDataContainer().set(worldPlacedKey, new PlacedLocationListDataType(), placed);
        return isNew;
    }

    /**
     * Tries to remove the event block's location from the player-placed list. Returns if the location was previously player-placed
     *
     * @param event The event containing the block location
     * @return true if the location was marked as player-placed
     */
    private boolean removePlacedLocation(BlockEvent event) {
        PlacedLocationList placed = getPlacedLocationList(event.getBlock());
        return placed.removePlacedLocation(event.getBlock().getLocation());
    }

    private boolean removeOreLocation(BlockEvent event) {
        PlacedLocationList placed = getPlacedLocationList(event.getBlock());
        return placed.removeCustomOreLocation(event.getBlock().getLocation());
    }

}