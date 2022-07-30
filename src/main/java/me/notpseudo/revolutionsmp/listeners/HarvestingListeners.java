package me.notpseudo.revolutionsmp.listeners;

import com.destroystokyo.paper.event.block.BlockDestroyEvent;
import io.papermc.paper.event.block.BlockBreakBlockEvent;
import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.playerstats.PlayerStats;
import me.notpseudo.revolutionsmp.playerstats.PlayerStatsDataType;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.type.CaveVinesPlant;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

public class HarvestingListeners implements Listener {

    private static final NamespacedKey playerKey = StatsListeners.getPlayerStatsKey();
    private static final NamespacedKey worldPlacedKey = new NamespacedKey(RevolutionSMP.getPlugin(RevolutionSMP.class), "chunkPlaced");
    private static final Set<Material> logs = Arrays.stream(Material.values()).filter(m -> m.isBlock()
            && (m.toString().toLowerCase().contains("_log") && !m.toString().toLowerCase().contains("legacy"))).collect(Collectors.toSet());
    private static Set<Material> ores = Arrays.stream(Material.values()).filter(m -> m.isBlock()
            && (m.toString().toLowerCase().contains("_ore") && !m.toString().toLowerCase().contains("legacy"))).collect(Collectors.toSet());
    private static Set<Material> crops = new HashSet<>() {
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
    public void onGravityCropBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() != Material.SUGAR_CANE || event.getBlock().getType() != Material.CACTUS
                || event.getBlock().getType() != Material.CAVE_VINES || event.getBlock().getType() != Material.CAVE_VINES_PLANT) {
            return;
        }
        boolean playerPlaced = removePlacedLocation(event);
        Block block = event.getBlock();
        PlacedLocationList placedList = event.getBlock().getWorld().getPersistentDataContainer().get(worldPlacedKey, new PlacedLocationListDataType());
        if (placedList == null) {
            placedList = new PlacedLocationList();
        }
        PlayerStats playerStats = event.getPlayer().getPersistentDataContainer().get(playerKey, new PlayerStatsDataType());
        if (playerStats == null) {
            playerStats = new PlayerStats();
        }
        int notPlayerPlacedCount = !playerPlaced ? 1 : 0, multiplier = getAddedTimes(playerStats.getFarmingFortune());
        Material primaryMaterial = block.getType();
        if (block.getType() == Material.SUGAR_CANE || block.getType() == Material.CACTUS) {
            List<Block> above = getBlocksInDirectionMatching(block.getLocation(), block.getType(), BlockFace.UP);
            for (Block aboveBlock : above) {
                if (!placedList.remove(aboveBlock.getLocation())) {
                    notPlayerPlacedCount++;
                }
            }
        }
        if (block.getType() == Material.CAVE_VINES || block.getType() == Material.CAVE_VINES_PLANT) {
            primaryMaterial = Material.GLOW_BERRIES;
            List<Block> downVines = getBlocksInDirectionMatching(block.getLocation(), Material.CAVE_VINES, BlockFace.DOWN);
            downVines.addAll(getBlocksInDirectionMatching(block.getLocation(), Material.CAVE_VINES_PLANT, BlockFace.DOWN));
            for (Block downBlock : downVines) {
                if (!placedList.remove(downBlock.getLocation()) && downBlock.getBlockData() instanceof CaveVinesPlant vinesPlant && vinesPlant.isBerries()) {
                    notPlayerPlacedCount++;
                }
            }
        }
        event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(primaryMaterial, notPlayerPlacedCount * multiplier));
        event.getBlock().getWorld().getPersistentDataContainer().set(worldPlacedKey, new PlacedLocationListDataType(), placedList);
        event.getPlayer().getPersistentDataContainer().set(playerKey, new PlayerStatsDataType(), playerStats);
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
            return;
        }
        if (event.getBlock().getBlockData() instanceof Ageable age) {
            if (age.getAge() < age.getMaximumAge()) {
                event.getPlayer().sendMessage(ChatColor.YELLOW + "Not at full age!");
                removePlacedLocation(event);
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
        PlayerStats playerStats = event.getPlayer().getPersistentDataContainer().get(playerKey, new PlayerStatsDataType());
        if (playerStats == null) {
            playerStats = new PlayerStats();
        }
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
        event.getPlayer().getPersistentDataContainer().set(playerKey, new PlayerStatsDataType(), playerStats);
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
            PlayerStats playerStats = event.getPlayer().getPersistentDataContainer().get(playerKey, new PlayerStatsDataType());
            if (playerStats == null) {
                playerStats = new PlayerStats();
            }
            int bushAge = age.getAge();
            int count = bushAge == age.getMaximumAge() ? 2 : 1, multiplier = getAddedTimes(playerStats.getFarmingFortune());
            count += (int) (Math.random() * 2);
            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.SWEET_BERRIES, count * multiplier));
            event.getPlayer().getPersistentDataContainer().set(playerKey, new PlayerStatsDataType(), playerStats);
        }
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
    public void onBlockDestroy(BlockDestroyEvent event) {
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

    private static List<Block> getBlocksInDirectionMatching(Location location, Material material, BlockFace direction) {
        List<Block> blocks = new ArrayList<>();
        Block current = location.getBlock();
        while (current.getRelative(direction).getType() == material && current.getRelative(direction).getType() != Material.AIR) {
            blocks.add(current.getRelative(direction));
            current = current.getRelative(direction);
        }
        return blocks;
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