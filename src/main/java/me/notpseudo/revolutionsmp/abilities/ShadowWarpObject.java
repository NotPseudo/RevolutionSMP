package me.notpseudo.revolutionsmp.abilities;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.List;


public class ShadowWarpObject extends AbilityObject {

    public ShadowWarpObject() {
        super(AbilityType.SHADOW_WARP);
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
        }
    }

}