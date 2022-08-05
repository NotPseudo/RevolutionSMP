package me.notpseudo.revolutionsmp.listeners;

import com.destroystokyo.paper.event.block.BlockDestroyEvent;
import io.papermc.paper.event.block.BlockBreakBlockEvent;
import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.items.ItemType;
import me.notpseudo.revolutionsmp.itemstats.ItemInfo;
import me.notpseudo.revolutionsmp.itemstats.MiningStats;
import me.notpseudo.revolutionsmp.itemstats.StatType;
import me.notpseudo.revolutionsmp.playerstats.PlayerStats;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
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
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class HarvestingListeners implements Listener {

    private static final NamespacedKey worldPlacedKey = new NamespacedKey(RevolutionSMP.getPlugin(RevolutionSMP.class), "chunkPlaced");
    private static final Set<Material> logs = new HashSet<>();
    private static final Set<Material> ores = new HashSet<>();
    private static final Set<Material> crops = new HashSet<>();

    private static final Set<Material> transparent = new HashSet<>(List.of(Material.AIR, Material.CAVE_AIR, Material.VOID_AIR, Material.WATER, Material.LAVA));

    public HarvestingListeners(RevolutionSMP plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        logs.addAll(List.of(Material.CRIMSON_STEM, Material.STRIPPED_CRIMSON_STEM,
                Material.WARPED_STEM, Material.STRIPPED_WARPED_STEM, Material.WARPED_STEM,
                Material.OAK_WOOD, Material.OAK_LOG, Material.STRIPPED_OAK_WOOD, Material.STRIPPED_OAK_LOG,
                Material.DARK_OAK_LOG,
                Material.BIRCH_LOG,
                Material.JUNGLE_LOG,
                Material.ACACIA_WOOD, Material.ACACIA_LOG, Material.STRIPPED_ACACIA_LOG,
                Material.SPRUCE_WOOD, Material.SPRUCE_LOG, Material.STRIPPED_SPRUCE_WOOD, Material.STRIPPED_SPRUCE_LOG));
        ores.addAll(List.of(Material.COBBLESTONE, Material.STONE, Material.DEEPSLATE, Material.ANDESITE, Material.DIORITE,
                Material.COAL_ORE, Material.DEEPSLATE_COAL_ORE,
                Material.GRAVEL, Material.SAND, Material.ICE, Material.PACKED_ICE, Material.BLUE_ICE,
                Material.GLOWSTONE, Material.BASALT, Material.BLACKSTONE, Material.OBSIDIAN,
                Material.COPPER_ORE, Material.DEEPSLATE_COPPER_ORE,
                Material.LAPIS_ORE, Material.DEEPSLATE_LAPIS_ORE,
                Material.IRON_ORE, Material.DEEPSLATE_IRON_ORE,
                Material.REDSTONE_ORE, Material.DEEPSLATE_REDSTONE_ORE,
                Material.DIAMOND_ORE, Material.DEEPSLATE_DIAMOND_ORE,
                Material.GOLD_ORE, Material.GOLD_ORE, Material.NETHER_GOLD_ORE,
                Material.EMERALD_ORE, Material.EMERALD_ORE,
                Material.NETHER_QUARTZ_ORE,
                Material.ANCIENT_DEBRIS,
                Material.SMALL_AMETHYST_BUD, Material.MEDIUM_AMETHYST_BUD, Material.LARGE_AMETHYST_BUD, Material.AMETHYST_CLUSTER,
                Material.BUDDING_AMETHYST, Material.AMETHYST_BLOCK, Material.CALCITE));
        crops.addAll(List.of(Material.WHEAT, Material.POTATOES, Material.CARROTS,
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
                Material.GLOW_BERRIES, Material.CAVE_VINES, Material.CAVE_VINES_PLANT,
                Material.DEAD_BUSH));
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
        double breakingPower = 0, hardness = event.getBlock().getType().getHardness(), breakingPowerNeeded = 1;
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
            if (!ores.contains(event.getBlock().getType())) {
                return;
            }
        }
        if (breakingPower < breakingPowerNeeded) {
            event.setCancelled(true);
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1F, 0.5F);
            event.getPlayer().sendMessage(Component.text("You need a stronger tool to mine " + material, NamedTextColor.RED));
            return;
        }
        CustomMiningUtils.createBreakingBlock(event.getBlock(), hardness * 3);
    }

    @EventHandler
    public void onDamageAbort(BlockDamageAbortEvent event) {
        CustomMiningUtils.removeBreakingBlock(event.getBlock().getLocation());
    }

    @EventHandler
    public void onOreBlockDamage(PlayerAnimationEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
            return;
        }
        Player player = event.getPlayer();
        Block block = player.getTargetBlock(transparent, 5);
        ItemInfo info = ItemEditor.getMainHandInfo(event.getPlayer());
        double breakingPower = 0, breakingPowerNeeded = 0;
        String material = ItemEditor.getStringFromEnum(block.getType());
        if (info != null &&
                (info.getItemType() == ItemType.PICKAXE || info.getItemType() == ItemType.DRILL || info.getItemType() == ItemType.GAUNTLET) &&
                info.getMiningStats() != null) {
            breakingPower = info.getMiningStats().getStatValue(StatType.BREAKING_POWER);
        }
        if (getPlacedLocationList(block).containsCustomOre(block.getLocation())) {
            CustomOreLocation customOreLocation = getPlacedLocationList(block).getCustomOreFromLocation(block.getLocation());
            breakingPowerNeeded = customOreLocation.getType().getBreakingPower();
            material = customOreLocation.getType().getName();
        } else {
            if (!ores.contains(block.getType())) {
                return;
            }
        }
        if (breakingPower < breakingPowerNeeded) {
            event.setCancelled(true);
            player.playSound(event.getPlayer().getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1F, 0.5F);
            player.sendMessage(Component.text("You need a stronger tool to mine " + material, NamedTextColor.RED));
            return;
        }
        Location blockLoc = block.getLocation();
        if(!CustomMiningUtils.isBreakingBlock(blockLoc)) {
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
        CustomMiningUtils.getBreakingBlock(blockLoc).damage(player, Math.max(1 / 50.0, miningSpeed / 50));
    }

    @EventHandler
    public void onOreBreak(BlockBreakEvent event) {
        PlacedLocationList locations = getPlacedLocationList(event.getBlock());
        CustomOreLocation customOre = locations.getCustomOreFromLocation(event.getBlock().getLocation());
        if (getPlacedLocationList(event.getBlock()).removeCustomOreLocation(event.getBlock().getLocation())) {

        } else {

        }
    }

    public static void createCustomBlock(CustomOreType type, Location location) {
        PlacedLocationList placedLocationList = getPlacedLocationList(location.getBlock());
        CustomOreLocation customOreLocation = placedLocationList.addCustomOreLocation(location, type);
        location.getBlock().setType(customOreLocation.getVanillaMaterial());
        location.getBlock().getWorld().getPersistentDataContainer().set(worldPlacedKey, new PlacedLocationListDataType(), placedLocationList);
    }

    @EventHandler
    public void onLogBreak(BlockBreakEvent event) {
        if (!logs.contains(event.getBlock().getType())) {
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
        int count = getAddedTimes(playerStats.getForagingFortune());
        event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(event.getBlock().getType(), count));
    }

    @EventHandler
    public void onGravityCropBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() != Material.SUGAR_CANE || event.getBlock().getType() != Material.CACTUS
                || event.getBlock().getType() != Material.CAVE_VINES || event.getBlock().getType() != Material.CAVE_VINES_PLANT) {
            return;
        }
        boolean playerPlaced = removePlacedLocation(event);
        removeOreLocation(event);
        Block block = event.getBlock();
        PlacedLocationList placedList = getPlacedLocationList(event.getBlock());
        PlayerStats playerStats = StatsListeners.getPlayerStats(event.getPlayer());
        int notPlayerPlacedCount = !playerPlaced ? 1 : 0, multiplier = getAddedTimes(playerStats.getFarmingFortune());
        Material primaryMaterial = block.getType();
        if (block.getType() == Material.SUGAR_CANE || block.getType() == Material.CACTUS) {
            List<Block> above = getBlocksInDirectionMatching(block.getLocation(), block.getType(), BlockFace.UP);
            for (Block aboveBlock : above) {
                if (!placedList.removePlacedLocation(aboveBlock.getLocation())) {
                    notPlayerPlacedCount++;
                }
            }
        }
        if (block.getType() == Material.CAVE_VINES || block.getType() == Material.CAVE_VINES_PLANT) {
            primaryMaterial = Material.GLOW_BERRIES;
            List<Block> downVines = getBlocksInDirectionMatching(block.getLocation(), Material.CAVE_VINES, BlockFace.DOWN);
            downVines.addAll(getBlocksInDirectionMatching(block.getLocation(), Material.CAVE_VINES_PLANT, BlockFace.DOWN));
            for (Block downBlock : downVines) {
                if (!placedList.removePlacedLocation(downBlock.getLocation()) && downBlock.getBlockData() instanceof CaveVinesPlant vinesPlant && vinesPlant.isBerries()) {
                    notPlayerPlacedCount++;
                }
            }
        }
        event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(primaryMaterial, notPlayerPlacedCount * multiplier));
        event.getBlock().getWorld().getPersistentDataContainer().set(worldPlacedKey, new PlacedLocationListDataType(), placedList);
    }

    @EventHandler
    public void onCropBreak(BlockBreakEvent event) {
        if (!crops.contains(event.getBlock().getType())) {
            return;
        }
        if (event.getBlock().getType() == Material.SUGAR_CANE || event.getBlock().getType() == Material.CACTUS
                || event.getBlock().getType() == Material.CAVE_VINES || event.getBlock().getType() == Material.CAVE_VINES_PLANT) {
            return;
        }
        if (event.getBlock().getType() == Material.SWEET_BERRY_BUSH) {
            removePlacedLocation(event);
            removeOreLocation(event);
            return;
        }
        if (event.getBlock().getBlockData() instanceof Ageable age) {
            if (age.getAge() < age.getMaximumAge()) {
                event.getPlayer().sendMessage(ChatColor.YELLOW + "Not at full age!");
                removePlacedLocation(event);
                removeOreLocation(event);
                return;
            }
            event.getPlayer().sendMessage(ChatColor.GREEN + "At full age!");
        }
        Material primary = event.getBlock().getType(), secondary = null;
        if (removePlacedLocation(event)) {
            if (primary == Material.PUMPKIN || primary == Material.CARVED_PUMPKIN
                    || primary == Material.MELON
                    || primary == Material.RED_MUSHROOM || primary == Material.RED_MUSHROOM_BLOCK || primary == Material.BROWN_MUSHROOM || primary == Material.BROWN_MUSHROOM_BLOCK || primary == Material.MUSHROOM_STEM
                    || primary == Material.CRIMSON_FUNGUS || primary == Material.WARPED_FUNGUS
                    || primary == Material.CAVE_VINES || primary == Material.CAVE_VINES_PLANT
                    || primary == Material.DEAD_BUSH) {
                return;
            }
        }
        PlayerStats playerStats = StatsListeners.getPlayerStats(event.getPlayer());
        int count = 1, secondaryCount = 0, multiplier = getAddedTimes(playerStats.getFarmingFortune());
        switch (event.getBlock().getType()) {
            case WHEAT:
                secondary = Material.WHEAT_SEEDS;
                secondaryCount = (int) (Math.random() * 3);
                break;
            case POTATOES:
                primary = Material.POTATO;
                count = (int) (Math.random() * 5) + 1;
                secondary = Material.POISONOUS_POTATO;
                secondaryCount = Math.random() < 0.02 ? 1 : 0;
                break;
            case CARROTS:
                primary = Material.CARROT;
                count = (int) (Math.random() * 4) + 2;
                break;
            case PUMPKIN:
            case CARVED_PUMPKIN:
                break;
            case PUMPKIN_STEM:
            case ATTACHED_PUMPKIN_STEM:
                primary = Material.PUMPKIN_SEEDS;
                double pumpkinDropChance = Math.random();
                if (pumpkinDropChance < 0.1517) {
                    count = 3;
                } else if (pumpkinDropChance < 0.5499) {
                    count = 2;
                } else if (pumpkinDropChance < 0.8983) {
                    count = 1;
                } else {
                    count = 0;
                }
                break;
            case MELON:
                primary = Material.MELON_SLICE;
                count = (int) (Math.random() * 5) + 3;
            case MELON_STEM:
            case ATTACHED_MELON_STEM:
                primary = Material.MELON_SEEDS;
                double melonDropChance = Math.random();
                if (melonDropChance < 0.1517) {
                    count = 3;
                } else if (melonDropChance < 0.5499) {
                    count = 2;
                } else if (melonDropChance < 0.8983) {
                    count = 1;
                } else {
                    count = 0;
                }
                break;
            case NETHER_WART:
                count = (int) (Math.random() * 3) + 2;
                break;
            case COCOA:
                primary = Material.COCOA_BEANS;
                count = (int) (Math.random() * 2) + 2;
                break;
            case RED_MUSHROOM_BLOCK:
                primary = Material.RED_MUSHROOM;
                count = (int) (Math.random() * 3);
                break;
            case BROWN_MUSHROOM_BLOCK:
                primary = Material.BROWN_MUSHROOM;
                count = (int) (Math.random() * 3);
                break;
            case MUSHROOM_STEM:
                primary = Math.random() < 0.5 ? Material.RED_MUSHROOM : Material.BROWN_MUSHROOM;
                count = (int) (Math.random() * 3);
                break;
            case BEETROOTS:
                primary = Material.BEETROOT;
                secondary = Material.BEETROOT_SEEDS;
                secondaryCount = (int) (Math.random() * 4) + 1;
                break;
        }
        event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(primary, count * multiplier));
        if (secondary != null && secondary != Material.AIR) {
            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(secondary, secondaryCount * multiplier));
        }
    }

    @EventHandler
    public void onSweetBerryBreak(BlockDropItemEvent event) {
        if (event.getBlock().getType() != Material.SWEET_BERRY_BUSH) {
            return;
        }
        if (event.getBlock().getBlockData() instanceof Ageable age) {
            if (age.getAge() < age.getMaximumAge() - 1) {
                return;
            }
            PlayerStats playerStats = StatsListeners.getPlayerStats(event.getPlayer());
            int bushAge = age.getAge();
            int count = bushAge == age.getMaximumAge() ? 2 : 1, multiplier = getAddedTimes(playerStats.getFarmingFortune());
            count += (int) (Math.random() * 2);
            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.SWEET_BERRIES, count * multiplier));}
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

    /**
     * Tries to add the event block's location as player-placed if the block is affected by fortune. Returns if the location was not already player-placed
     *
     * @param event The event containing the block location
     * @return true if the block is affect by fortune and the location was not already marked as player-placed
     */
    private boolean addPlacedLocation(BlockEvent event) {
        if (!(logs.contains(event.getBlock().getType()) || ores.contains(event.getBlock().getType()) || crops.contains(event.getBlock().getType()))) {
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