package me.notpseudo.revolutionsmp.abilities;

import me.notpseudo.revolutionsmp.itemstats.AbilitiesHolder;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.List;


public class ShadowWarpObject extends AbilityObject {

    public ShadowWarpObject(AbilitiesHolder holder) {
        super(holder, AbilityType.SHADOW_WARP);
    }

    @Override
    public void use(Player player) {
        Location location = player.getLocation();
        List<Block> blocks = player.getLineOfSight(AbilitiesUtil.getPassSet(), 10);
        for (Block b : blocks) {
            if (b.isPassable()) {
                location = b.getLocation();
            } else {
                break;
            }
        }
        if (!AbilitiesUtil.shadowWarp(player, location)) {
            AbilityType.SHADOW_WARP.addToCooldownList(player.getUniqueId(), getCooldown());
        } else {
            takeMana(player);
        }
    }

}