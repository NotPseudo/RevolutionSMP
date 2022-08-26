package me.notpseudo.revolutionsmp.commands;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.itemstats.StatType;
import me.notpseudo.revolutionsmp.listeners.StatsListeners;
import me.notpseudo.revolutionsmp.playerstats.PlayerStats;
import me.notpseudo.revolutionsmp.playerstats.PlayerStatsDataType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
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
            if (!player.isOp()) {
                player.sendMessage(Component.text("You do not have permission to use this command", NamedTextColor.RED));
                return true;
            }
            PlayerStats playerStats = player.getPersistentDataContainer().get(playerKey, new PlayerStatsDataType());
            if(playerStats == null) {
                player.sendMessage("Your Player Stats were null");
                return false;
            }
            player.sendMessage("Current Health: " + playerStats.getStatValue(StatType.HEALTH));
            player.sendMessage("Max Health: " + playerStats.getMaxHealth());
            player.sendMessage("Defense: " + playerStats.getStatValue(StatType.DEFENSE));
            player.sendMessage("Speed: " + playerStats.getStatValue(StatType.SPEED));
            player.sendMessage("Strength: " + playerStats.getStatValue(StatType.STRENGTH));
            player.sendMessage("Crit Chance: " + playerStats.getStatValue(StatType.CRIT_CHANCE));
            player.sendMessage("Crit Damage: " + playerStats.getStatValue(StatType.CRIT_DAMAGE));
            player.sendMessage("Attack Speed: " + playerStats.getStatValue(StatType.ATTACK_SPEED));
            player.sendMessage("Ferocity: " + playerStats.getStatValue(StatType.FEROCITY));
            player.sendMessage("Intelligence: " + playerStats.getStatValue(StatType.INTELLIGENCE));
            player.sendMessage("Ability Damage: " + playerStats.getStatValue(StatType.ABILITY_DAMAGE));
            player.sendMessage("Absorption: " + playerStats.getAbsorption());
        }
        return true;
    }
}
