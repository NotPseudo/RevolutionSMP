package me.notpseudo.revolutionsmp.mining;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.reflect.FieldAccessException;
import com.comphenix.protocol.wrappers.BlockPosition;
import me.notpseudo.revolutionsmp.listeners.HarvestingListeners;
import me.notpseudo.revolutionsmp.mining.BreakingBlock;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class CustomMiningUtils {

    private static HashMap<Location, BreakingBlock> breakingBlocks = new HashMap<>();

    private static ArrayList<UUID> slowDigList = new ArrayList<>();

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
        HarvestingListeners.getPlacedLocationList(location.getBlock()).removePlacedLocation(location);
        HarvestingListeners.getPlacedLocationList(location.getBlock()).removeCustomOreLocation(location);
    }

    public static BreakingBlock getBreakingBlock(Location location) {
        createBreakingBlock(location.getBlock());
        return breakingBlocks.get(location);
    }

    public static boolean isBreakingBlock(Location location) {
        return breakingBlocks.containsKey(location);
    }

    public static void resetBlockDamage(Block block) {
        PacketContainer packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.BLOCK_BREAK_ANIMATION);
        BlockPosition blockPosition = new BlockPosition(new Vector(block.getX(), block.getY(), block.getZ()));
        packet.getBlockPositionModifier().write(0, blockPosition);
        packet.getIntegers().write(0, 99999);
        packet.getIntegers().write(1, -5);
        try {
            ProtocolLibrary.getProtocolManager().broadcastServerPacket(packet);
        } catch (FieldAccessException exception) {
            exception.printStackTrace();
        }
    }

    public static void addSlow(Player player, int seconds) {
        if (!slowDigList.contains(player.getUniqueId())) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, seconds * 20, -1, false, false, false));
            slowDigList.add(player.getUniqueId());
        }
    }

    public static void removeSlow(Player player) {
        if (slowDigList.remove(player.getUniqueId())) {
            player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
        }
    }

}