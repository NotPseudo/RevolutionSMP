package me.notpseudo.revolutionsmp.commands;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

// Command to Recombobulate the current held item
public class RecombCommand implements CommandExecutor {

    public RecombCommand(RevolutionSMP plugin) {
        plugin.getCommand("recombobulate").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            if (player.isOp()) {
                ItemEditor.recombobulate(player.getInventory().getItemInMainHand());
                player.sendMessage(Component.text("Recombobulated successfully", NamedTextColor.GREEN));
            }
        }
        return true;
    }
}