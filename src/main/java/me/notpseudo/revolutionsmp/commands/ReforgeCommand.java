package me.notpseudo.revolutionsmp.commands;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.items.Reforge;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

// Command to Reforge the current held item to a specified reforge
public class ReforgeCommand implements CommandExecutor {

    public ReforgeCommand(RevolutionSMP plugin) {
        plugin.getCommand("reforge").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            if (!player.isOp()) {
                player.sendMessage(Component.text("You do not have permission to use this command", NamedTextColor.RED));
                return true;
            }
            String reforgeString = args[0].toUpperCase();
            try {
                Reforge reforge = Reforge.valueOf(reforgeString);
                ItemEditor.reforge(player.getInventory().getItemInMainHand(), reforge);
                player.sendMessage(Component.text("Reforge successful!", NamedTextColor.GREEN));
                return true;
            } catch (IllegalArgumentException exception) {
                player.sendMessage(Component.text("This reforge could not be found. Check your spelling", NamedTextColor.RED));
                return false;
            }

        }
        return true;
    }
}