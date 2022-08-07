package me.notpseudo.revolutionsmp.skills;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.itemstats.IncreaseType;
import me.notpseudo.revolutionsmp.listeners.StatsListeners;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
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

    public static void addCombatXpToPlayer(Player player, SkillType type, LivingEntity target, double exp) {
        ExpDropObject add = StatsListeners.getCombatXpBoost(player, type, target, IncreaseType.INCREASE),
                addPercent = StatsListeners.getCombatXpBoost(player, type, target, IncreaseType.ADDITIVE_PERCENT),
                mult = StatsListeners.getCombatXpBoost(player, type, target, IncreaseType.MULTIPLICATIVE_PERCENT);
        double finalExp = (exp + add.getValue()) * (1 + (addPercent.getValue() / 100)) * mult.getValue();
        getHolder(player).addExp(new ExpDropObject(type, finalExp));
    }

    public static void addBreakingXpToPlayer(Player player, SkillType type, Block block, double exp) {
        ExpDropObject add = StatsListeners.getBreakXpBoost(player, type, block, IncreaseType.INCREASE),
                addPercent = StatsListeners.getBreakXpBoost(player, type, block, IncreaseType.ADDITIVE_PERCENT),
                mult = StatsListeners.getBreakXpBoost(player, type, block, IncreaseType.MULTIPLICATIVE_PERCENT);
        double finalExp = (exp + add.getValue()) * (1 + (addPercent.getValue() / 100)) * mult.getValue();
        getHolder(player).addExp(new ExpDropObject(type, finalExp));
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
            holder = new SkillHolder(player.getUniqueId());
            player.getPersistentDataContainer().set(skillKey, new SkillsDataType(), holder);
        }
        return holder;
    }

}