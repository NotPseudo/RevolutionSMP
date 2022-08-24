package me.notpseudo.revolutionsmp.skills;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.io.Serializable;

public class SkillObject implements Serializable {

    private final SkillHolder holder;
    private final SkillType TYPE;
    private double totalXP;
    private double currentXP;
    private double xpForNextLevel;
    private double level;

    public SkillObject(SkillHolder holder, SkillType type) {
        this.holder = holder;
        TYPE = type;
        totalXP = 0;
        currentXP = 0;
        xpForNextLevel = SkillUtils.getXpForNextLevel(TYPE, 1);
        level = 0;
        recalculate();
    }

    public SkillObject(SkillType type, int level) {
        TYPE = type;
        this.level = level;
        holder = null;
    }

    public SkillType getType() {
        return TYPE;
    }

    public double getTotalXP() {
        return totalXP;
    }

    public void setTotalXP(double totalXP) {
        this.totalXP = totalXP;
        recalculate();
    }

    public void addXp(ExpDropObject drop) {
        if (drop.getType() != TYPE) {
            return;
        }
        currentXP += drop.getValue();
        totalXP += drop.getValue();
        recalculate();
    }

    public double getCurrentXP() {
        return currentXP;
    }

    public double getXpForNextLevel() {
        return xpForNextLevel;
    }

    public double getLevel() {
        return level;
    }

    private void recalculate() {
        while (currentXP < 0) {
            level--;
            xpForNextLevel = SkillUtils.getXpForNextLevel(TYPE, (int) level + 1);
            currentXP += xpForNextLevel;
        }
        while (currentXP >= xpForNextLevel && level < TYPE.getMaxLevel()) {
            currentXP -= xpForNextLevel;
            level++;
            xpForNextLevel = SkillUtils.getXpForNextLevel(TYPE, (int) level + 1);
            level = Math.floor(level) + currentXP / xpForNextLevel;
            Player player = Bukkit.getPlayer(holder.getPlayer());
            if (player != null) {
                sendLevelUpMessage(player);
            }
        }
        if (xpForNextLevel != 0) {
            level = Math.floor(level) + currentXP / xpForNextLevel;
        }
        xpForNextLevel = SkillUtils.getXpForNextLevel(TYPE, (int) level + 1);
    }

    public double getPercent() {
        if (level >= TYPE.getMaxLevel()) {
            return 100;
        }
        return currentXP / xpForNextLevel;
    }

    private void sendLevelUpMessage(Player player) {
        player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        for (Component line : TYPE.getLevelUpMessage((int) level)) {
            player.sendMessage(line);
        }
    }

}