package me.notpseudo.revolutionsmp.skills;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.itemstats.IncreaseType;
import me.notpseudo.revolutionsmp.listeners.MobListeners;
import me.notpseudo.revolutionsmp.listeners.StatsListeners;
import me.notpseudo.revolutionsmp.mobstats.MobInfo;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.ComplexLivingEntity;
import org.bukkit.entity.Creature;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class SkillUtils implements Listener {

    private static final NamespacedKey skillKey = new NamespacedKey(RevolutionSMP.getPlugin(RevolutionSMP.class), "skillKey");

    public SkillUtils(RevolutionSMP plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static NamespacedKey getSkillKey() {
        return skillKey;
    }

    public static double getXpForNextLevel(SkillType type, int nextLevel) {
        return switch ((nextLevel - 1) / 5) {
            case 0 -> 500 * nextLevel;
            case 1 -> -2500 + 1000 * nextLevel;
            case 2 -> -17500 + 2500 * nextLevel;
            case 3 -> -50500 + 4700 * nextLevel;
            case 4 -> -94500 + 6900 * nextLevel;
            case 5 -> -234500 + 12500 * nextLevel;
            case 6 -> -384000 + 17500 * nextLevel;
            case 7 -> -647000 + 25000 * nextLevel;
            case 8 -> -1327000 + 42500 * nextLevel;
            case 9 -> -2677000 + 72500 * nextLevel;
            case 10 -> -5302000 + 125000 * nextLevel;
            case 11 -> -9427000 + 200000 * nextLevel;
            default -> 0;
        };
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (!(event.getEntity() instanceof Creature || event.getEntity() instanceof ComplexLivingEntity)) {
            return;
        }
        MobInfo info = MobListeners.getMobInfo(event.getEntity());
        if (info == null) {
            return;
        }
        ExpDropObject baseDrop = info.getCustomMobType().getExp(info.getLevel());
        for (UUID id : info.getAttackers().keySet()) {
            Player player = Bukkit.getPlayer(id);
            if (player != null) {
                addCombatXpToPlayer(player, baseDrop.getType(), event.getEntity(), baseDrop.getValue());
            }
        }
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