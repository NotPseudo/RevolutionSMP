package me.notpseudo.revolutionsmp.abilities;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Methods that will run to simulate abilities
public class AbilitiesUtil {

    // A Set of non-solid block materials that players can walk through. Used in teleportation abilities
    private static Set<Material> passSet = new HashSet<>();
          /* List.of(Material.AIR, Material.WATER,
          Material.GRASS, Material.TALL_GRASS, Material.SEAGRASS, Material.TALL_SEAGRASS,
          Material.TORCH, Material.WALL_TORCH, Material.REDSTONE_TORCH, Material.REDSTONE_WALL_TORCH, Material.SOUL_TORCH, Material.SOUL_WALL_TORCH, Material.ACACIA_SIGN, Material.BIRCH_SIGN, Material.SPRUCE_SIGN));*/

    public static void createPassSet() {
        for (Material material : Material.values()) {
            if (material.isBlock() && !material.isCollidable()) {
                passSet.add(material);
            }
        }
    }

    // Method that runs when Instant Transmission ability is used
    public static void teleportWithSound(Player player, int distance) {
        float yaw = player.getLocation().getYaw();
        float pitch = player.getLocation().getPitch();
        Location loc = player.getLocation();
        boolean cont = true;
        List<Block> blocks = player.getLineOfSight(passSet, distance);
        for (Block b : blocks) {
            if (b.isPassable() && cont) {
                loc = b.getLocation();
            } else {
                cont = false;
                break;
            }
        }
        if (!cont) {
            player.sendMessage("§cThere are blocks in the way!");
        }
        loc.setPitch(pitch);
        loc.setYaw(yaw);
        player.teleport(loc);
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
    }

}