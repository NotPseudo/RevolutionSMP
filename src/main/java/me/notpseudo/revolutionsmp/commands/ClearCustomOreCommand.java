package me.notpseudo.revolutionsmp.commands;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.listeners.HarvestingListeners;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ClearCustomOreCommand implements CommandExecutor {

    public ClearCustomOreCommand(RevolutionSMP plugin) {
        plugin.getCommand("clearcustomores").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player && player.isOp()) {
            HarvestingListeners.getPlacedLocationList(player.getLocation().getBlock()).getOreLocations().clear();
        }
        return true;
    }
}