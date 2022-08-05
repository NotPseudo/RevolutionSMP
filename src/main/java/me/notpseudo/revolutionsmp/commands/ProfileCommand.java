package me.notpseudo.revolutionsmp.commands;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.itemstats.StatType;
import me.notpseudo.revolutionsmp.listeners.StatsListeners;
import me.notpseudo.revolutionsmp.playerstats.PlayerStats;
import me.notpseudo.revolutionsmp.playerstats.PlayerStatsDataType;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ProfileCommand implements CommandExecutor {

    private static final NamespacedKey playerKey = StatsListeners.getPlayerStatsKey();

    public ProfileCommand(RevolutionSMP plugin) {
        plugin.getCommand("profile").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player player) {
            PlayerStats playerStats = player.getPersistentDataContainer().get(playerKey, new PlayerStatsDataType());
            if(playerStats == null) {
                player.sendMessage("Your Player Stats were null");
                return false;
            }
            player.sendMessage("Current Health: " + playerStats.getArmorStatValue(StatType.HEALTH));
            player.sendMessage("Max Health: " + playerStats.getMaxHealth());
            player.sendMessage("Defense: " + playerStats.getArmorStatValue(StatType.DEFENSE));
            player.sendMessage("Speed: " + playerStats.getArmorStatValue(StatType.SPEED));
            player.sendMessage("Strength: " + playerStats.getCombatStatValue(StatType.STRENGTH));
            player.sendMessage("Crit Chance: " + playerStats.getCombatStatValue(StatType.CRIT_CHANCE));
            player.sendMessage("Crit Damage: " + playerStats.getCombatStatValue(StatType.CRIT_DAMAGE));
            player.sendMessage("Attack Speed: " + playerStats.getCombatStatValue(StatType.ATTACK_SPEED));
            player.sendMessage("Ferocity: " + playerStats.getCombatStatValue(StatType.FEROCITY));
            player.sendMessage("Intelligence: " + playerStats.getIntelligence());
            player.sendMessage("Ability Damage: " + playerStats.getAbilityDamage());
            player.sendMessage("Absorption: " + playerStats.getAbsorption());
        }
        return true;
    }
}
