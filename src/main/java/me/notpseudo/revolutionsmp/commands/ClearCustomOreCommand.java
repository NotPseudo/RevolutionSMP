package me.notpseudo.revolutionsmp.commands;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.listeners.HarvestingListeners;
import me.notpseudo.revolutionsmp.mining.PlacedLocationList;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
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
        if (sender instanceof Player player) {
            if (!player.isOp()) {
                player.sendMessage(Component.text("You do not have permission to use this command", NamedTextColor.RED));
                return true;
            }
            PlacedLocationList list = HarvestingListeners.getPlacedLocationList(player.getLocation().getBlock());
            list.getOreLocations().clear();
            HarvestingListeners.setPlacedLocationList(player.getLocation().getBlock(), list);
        }
        return true;
    }
}