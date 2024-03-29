package me.notpseudo.revolutionsmp.commands;

import me.notpseudo.revolutionsmp.RevolutionSMP;
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
import org.jetbrains.annotations.NotNull;

public class ResetStatsCommand implements CommandExecutor {

    private static final NamespacedKey playerKey = StatsListeners.getPlayerStatsKey();

    public ResetStatsCommand(RevolutionSMP plugin) {
        plugin.getCommand("resetstats").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            if (!player.isOp()) {
                player.sendMessage(Component.text("You do not have permission to use this command", NamedTextColor.RED));
                return true;
            }
            player.getPersistentDataContainer().remove(playerKey);
            PlayerStats playerStats = new PlayerStats();
            player.getPersistentDataContainer().set(playerKey, new PlayerStatsDataType(), playerStats);
        }
        return true;
    }

}
