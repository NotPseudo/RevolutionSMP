package me.notpseudo.revolutionsmp.skills;

public class SkillUtils {

    public static double getXpForNextLevel(SkillType type, int nextLevel) {
        return switch ((nextLevel - 1) / 10) {
            case 0 -> 500 * nextLevel;
            case 1 -> -20000 + 2500 * nextLevel;
            case 2 -> -108000 + 6900 * nextLevel;
            case 3 -> -354000 + 15100 * nextLevel;
            case 4 -> -750000 + 25000 * nextLevel;
            case 5 -> -2000000 + 50000 * nextLevel;
            default -> 0;
        };
    }

}