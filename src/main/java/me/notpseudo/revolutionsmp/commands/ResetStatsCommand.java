package me.notpseudo.revolutionsmp.commands;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.listeners.StatsListeners;
import me.notpseudo.revolutionsmp.playerstats.PlayerStats;
import me.notpseudo.revolutionsmp.playerstats.PlayerStatsDataType;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetStatsCommand implements CommandExecutor {

    private static final NamespacedKey playerKey = StatsListeners.getPlayerStatsKey();

    public ResetStatsCommand(RevolutionSMP plugin) {
        plugin.getCommand("resetstats").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.getPersistentDataContainer().remove(playerKey);
            PlayerStats playerStats = new PlayerStats();
            player.getPersistentDataContainer().set(playerKey, new PlayerStatsDataType(), playerStats);
        }
        return true;
    }

}
