package me.notpseudo.revolutionsmp.commands;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.itemstats.ItemInfo;
import me.notpseudo.revolutionsmp.mining.CustomOreLocation;
import me.notpseudo.revolutionsmp.listeners.HarvestingListeners;
import me.notpseudo.revolutionsmp.mining.PlacedLocationList;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ParticleTestCommand implements CommandExecutor {

    public ParticleTestCommand(RevolutionSMP plugin) {
        plugin.getCommand("particletest").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (!player.isOp()) {
                player.sendMessage(Component.text("You do not have permission to use this command", NamedTextColor.RED));
                return true;
            }
            ItemInfo info = ItemEditor.getInfo(player.getInventory().getItemInMainHand());
            if (info == null) {
                return true;
            }
            player.sendMessage("Owner: " + info.getOwner());
        }
        return true;
    }

}