package me.notpseudo.revolutionsmp.abilities;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Abilities {

  private static Set<Material> passSet = new HashSet<>();

  public static void createPassSet() {
    passSet.add(Material.AIR);
    passSet.add(Material.WATER);
    passSet.add(Material.GRASS);
    passSet.add(Material.TALL_GRASS);
    passSet.add(Material.SEAGRASS);
    passSet.add(Material.TALL_SEAGRASS);
    passSet.add(Material.TORCH);
    passSet.add(Material.WALL_TORCH);
    passSet.add(Material.REDSTONE_TORCH);
    passSet.add(Material.REDSTONE_WALL_TORCH);
    passSet.add(Material.SOUL_TORCH);
    passSet.add(Material.SOUL_WALL_TORCH);
    passSet.add(Material.ACACIA_SIGN);
    passSet.add(Material.SPRUCE_SIGN);
    passSet.add(Material.BIRCH_SIGN);
  }

  public static void instantTransmission(Player player) {
    Float yaw = player.getLocation().getYaw();
    Float pitch = player.getLocation().getPitch();
    Location loc = player.getLocation();
    boolean cont = true;
    ArrayList<Block> Blocks = (ArrayList<Block>) player.getLineOfSight(passSet, 8);
    for (Block b : Blocks) {
      if (b.isPassable() && cont) {
        loc = b.getLocation();
      }
      if (!(b.isPassable())) {
        cont = false;
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
