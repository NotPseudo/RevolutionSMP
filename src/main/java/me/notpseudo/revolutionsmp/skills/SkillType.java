package me.notpseudo.revolutionsmp.skills;

public enum SkillType {

    FARMING,
    MINING,
    COMBAT,
    FORAGING,
    FISHING,
    ENCHANTING,
    ALCHEMY,
    BUILDING,
    TAMING,
    ORIGIN,
    ABYSS,
    ASCENDANCE;

    public int getMaxLevel() {
        return 50;
    }

}