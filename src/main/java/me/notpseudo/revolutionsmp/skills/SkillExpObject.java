package me.notpseudo.revolutionsmp.skills;

import java.io.Serializable;

public class SkillExpObject implements Serializable {

    private final SkillType TYPE;
    private double totalXP;
    private double currentXP;
    private double xpForNextLevel;
    private double level;

    public SkillExpObject(SkillType type) {
        TYPE = type;
        totalXP = 0;
        currentXP = 0;
        xpForNextLevel = SkillUtils.getXpForNextLevel(TYPE, 1);
        level = 0;
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
        while (currentXP >= xpForNextLevel) {
            currentXP -= xpForNextLevel;
            xpForNextLevel = SkillUtils.getXpForNextLevel(TYPE, (int) level + 1);
            level = Math.ceil(level) + currentXP / xpForNextLevel;
        }
        level = Math.floor(level) + currentXP / xpForNextLevel;
    }

}