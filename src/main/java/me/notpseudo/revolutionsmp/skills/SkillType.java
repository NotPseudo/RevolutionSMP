package me.notpseudo.revolutionsmp.skills;

public enum SkillType {

    FARMING,
    MINING,
    COMBAT,
    FORAGING,
    FISHING,
    ENCHANTING,
    ALCHEMY,
    TAMING;

    public int getMaxLevel() {
        return 50;
    }

}