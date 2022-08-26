package me.notpseudo.revolutionsmp.commands;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PotatoBookCommand implements CommandExecutor {

    public PotatoBookCommand(RevolutionSMP plugin) {
        plugin.getCommand("potatobook").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            if (!player.isOp()) {
                player.sendMessage(Component.text("You do not have permission to use this command", NamedTextColor.RED));
                return true;
            }
            int books;
            ItemStack item = player.getInventory().getItemInMainHand();
            if (args[0].toLowerCase().equals("add")) {
                try {
                    books = Integer.parseInt(args[1]);
                } catch (NumberFormatException exception) {
                    books = 1;
                }
                ItemEditor.addPotatoBook(item, books);
            } else if (args[0].toLowerCase().equals("set")) {
                try {
                    books = Integer.parseInt(args[1]);
                } catch (NumberFormatException exception) {
                    player.sendMessage(Component.text("Invalid number for books", NamedTextColor.RED));
                    return false;
                }
                ItemEditor.setPotatoBooks(item, books);
            } else {
                player.sendMessage(Component.text("Missing arguments", NamedTextColor.RED));
                return false;
            }
            player.sendMessage(Component.text("Applied books!", NamedTextColor.GREEN));
            return true;
        }
        return true;
    }
}