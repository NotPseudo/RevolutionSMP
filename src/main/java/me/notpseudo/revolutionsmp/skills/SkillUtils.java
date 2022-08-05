package me.notpseudo.revolutionsmp.skills;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SkillUtils {

    private static final NamespacedKey skillKey = new NamespacedKey(RevolutionSMP.getPlugin(RevolutionSMP.class), "skillKey");

    public static NamespacedKey getSkillKey() {
        return skillKey;
    }

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

    public static double getTotalXpForMaxLevel(SkillType type) {
        double total = 0;
        for (int i = 0; i < type.getMaxLevel(); i++) {
            total += getXpForNextLevel(type, i + 1);
        }
        return total;
    }

    @NotNull
    public static SkillHolder getHolder(Player player) {
        SkillHolder holder = player.getPersistentDataContainer().get(skillKey, new SkillsDataType());
        if (holder == null) {
            holder = new SkillHolder();
            player.getPersistentDataContainer().set(skillKey, new SkillsDataType(), holder);
        }
        return holder;
    }

}