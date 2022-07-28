package me.notpseudo.revolutionsmp.listeners;

import io.papermc.paper.event.block.BlockBreakBlockEvent;
import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.playerstats.PlayerStats;
import me.notpseudo.revolutionsmp.playerstats.PlayerStatsDataType;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HarvestingListeners implements Listener {

    private static final NamespacedKey playerKey = StatsListeners.getPlayerStatsKey();
    private static final NamespacedKey worldPlacedKey = new NamespacedKey(RevolutionSMP.getPlugin(RevolutionSMP.class), "chunkPlaced");
    private static final Set<Material> logs = Arrays.stream(Material.values()).filter(m -> m.isBlock()
            && (m.toString().toLowerCase().contains("_log") && !m.toString().toLowerCase().contains("legacy"))).collect(Collectors.toSet());
    private static Set<Material> ores = Arrays.stream(Material.values()).filter(m -> m.isBlock()
            && (m.toString().toLowerCase().contains("_ore") && !m.toString().toLowerCase().contains("legacy"))).collect(Collectors.toSet());
    private static Set<Material> crops = new HashSet<Material>() {
    };

    public HarvestingListeners(RevolutionSMP plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        logs.addAll(List.of(Material.CRIMSON_STEM, Material.STRIPPED_CRIMSON_STEM,
                Material.WARPED_STEM, Material.STRIPPED_WARPED_STEM,
                Material.OAK_WOOD, Material.STRIPPED_OAK_WOOD,
                Material.ACACIA_WOOD,
                Material.SPRUCE_WOOD, Material.STRIPPED_SPRUCE_WOOD));
        ores.addAll(List.of(Material.ANCIENT_DEBRIS,
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

    @EventHandler
    public void onLogBreak(BlockBreakEvent event) {
        if (!logs.contains(event.getBlock().getType())) {
            return;
        }
        if (removePlacedLocation(event)) {
            return;
        }
        Player player = event.getPlayer();
        PlayerStats playerStats = player.getPersistentDataContainer().get(playerKey, new PlayerStatsDataType());
        if (playerStats == null) {
            playerStats = new PlayerStats();
            player.getPersistentDataContainer().set(playerKey, new PlayerStatsDataType(), playerStats);
        }
        int count = getAddedTimes(playerStats.getForagingFortune());
        event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(event.getBlock().getType(), count));
    }

    @EventHandler
    public void onCropBreak(BlockBreakEvent event) {
        if(!crops.contains(event.getBlock().getType())) {
            return;
        }
        boolean playerPlaced = removePlacedLocation(event);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        addPlacedLocation(event);
    }

    @EventHandler
    public void onBlockBurn(BlockBurnEvent event) {
        removePlacedLocation(event);
    }

    @EventHandler
    public void onBlockBreakBlock(BlockBreakBlockEvent event) {
        removePlacedLocation(event);
    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent event) {
        removePlacedLocation(event);
    }

    @EventHandler
    public void onBlockFade(BlockFadeEvent event) {
        removePlacedLocation(event);
    }

    @EventHandler
    public void onLeafDecay(LeavesDecayEvent event) {
        removePlacedLocation(event);
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
        PlacedLocationList placedList = event.getBlock().getLocation().getWorld().getPersistentDataContainer().get(worldPlacedKey, new PlacedLocationListDataType());
        if (placedList == null) {
            placedList = new PlacedLocationList();
        }
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
            PlacedLocation newLocation = placedList.getFromLocation(block.getLocation());
            if (newLocation != null) {
                newLocation.add(changeX, changeY, changeZ);
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

    /**
     * Tries to add the event block's location as player-placed is the block is affected by fortune. Returns if the location was not already player-placed
     *
     * @param event The event containing the block location
     * @return true if the block is affect by fortune and the location was not already marked as player-placed
     */
    private boolean addPlacedLocation(BlockEvent event) {
        if (!(logs.contains(event.getBlock().getType()) || ores.contains(event.getBlock().getType()) || crops.contains(event.getBlock().getType()))) {
            return false;
        }
        PlacedLocationList placed = event.getBlock().getWorld().getPersistentDataContainer().get(worldPlacedKey, new PlacedLocationListDataType());
        if (placed == null) {
            placed = new PlacedLocationList();
        }
        Location loc = event.getBlock().getLocation();
        boolean isNew = placed.add(loc);
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
        boolean contained = true;
        PlacedLocationList placed = event.getBlock().getWorld().getPersistentDataContainer().get(worldPlacedKey, new PlacedLocationListDataType());
        if (placed != null) {
            contained = placed.remove(event.getBlock().getLocation());
            event.getBlock().getWorld().getPersistentDataContainer().set(worldPlacedKey, new PlacedLocationListDataType(), placed);
        }
        return contained;
    }

}