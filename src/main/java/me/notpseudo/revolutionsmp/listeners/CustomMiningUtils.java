package me.notpseudo.revolutionsmp.listeners;

import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.HashMap;

public class CustomMiningUtils {

    private static HashMap<Location, BreakingBlock> breakingBlocks = new HashMap<>();

    public static HashMap<Location, BreakingBlock> getBreakingBlocks() {
        return breakingBlocks;
    }

    public static void createBreakingBlock(Block block) {
        createBreakingBlock(block, -1);
    }

    public static void createBreakingBlock(Block block, double damagePerStage) {
        if (isBreakingBlock(block.getLocation())) {
            return;
        }
        BreakingBlock breakingBlock;
        if (damagePerStage == -1) {
            breakingBlock = new BreakingBlock(block, 0);
        } else {
            breakingBlock = new BreakingBlock(block, damagePerStage);
        }
        breakingBlocks.put(block.getLocation(), breakingBlock);
    }

    public static void removeBreakingBlock(Location location) {
        if(breakingBlocks.get(location) != null) {
            breakingBlocks.get(location).destroyBlockObject();
        }
        breakingBlocks.remove(location);
    }

    public static BreakingBlock getBreakingBlock(Location location) {
        createBreakingBlock(location.getBlock());
        return breakingBlocks.get(location);
    }

    public static boolean isBreakingBlock(Location location) {
        return breakingBlocks.containsKey(location);
    }


}