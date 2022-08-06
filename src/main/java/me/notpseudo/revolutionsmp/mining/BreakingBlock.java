package me.notpseudo.revolutionsmp.mining;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.reflect.FieldAccessException;
import com.comphenix.protocol.wrappers.BlockPosition;
import org.bukkit.SoundGroup;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class BreakingBlock {

    private double damagePerStage;
    private int previousStage;
    private double damage;
    private Block block;

    public BreakingBlock(Block block, double damagePerStage) {
        this.block = block;
        this.damagePerStage = damagePerStage;
        damage = -1;
    }

    public double getDamagePerStage() {
        return damagePerStage;
    }

    public void damage(Player breaker, double amount) {
        if (isBroken()) {
            return;
        }
        damage += amount;
        int animation = getAnimation();
        if (animation != previousStage) {
            if (animation < 10) {
                sendBlockUpdatePacket(animation);
            } else {
                breakBlock(breaker);
                return;
            }
        }
        previousStage = animation;
    }

    public boolean isBroken() {
        return getAnimation() >= 10;
    }

    public void breakBlock(Player breaker) {
        destroyBlockObject();
        if (breaker == null) {
            return;
        }
        forceBreak(breaker);
    }

    public void destroyBlockObject() {
        sendBlockUpdatePacket(-5);
    }

    public int getAnimation() {
        return Math.min(10, (int) (damage / damagePerStage));
    }

    public void sendBlockUpdatePacket(int animation) {
        PacketContainer packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.BLOCK_BREAK_ANIMATION);
        BlockPosition blockPosition = new BlockPosition(new Vector(block.getX(), block.getY(), block.getZ()));
        packet.getBlockPositionModifier().write(0, blockPosition);
        packet.getIntegers().write(0, 99999);
        packet.getIntegers().write(1, animation);
        try {
            ProtocolLibrary.getProtocolManager().broadcastServerPacket(packet);
        } catch (FieldAccessException exception) {
            exception.printStackTrace();
        }
    }

    public void forceBreak(Player breaker) {
        breaker.breakBlock(block);
    }

}