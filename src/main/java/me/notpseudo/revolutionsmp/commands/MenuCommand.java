package me.notpseudo.revolutionsmp.commands;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.menus.MainMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MenuCommand implements CommandExecutor {

    public MenuCommand(RevolutionSMP plugin) {
        plugin.getCommand("menu").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            return true;
        }
        new MainMenu(player).open();
        return true;
    }
}
