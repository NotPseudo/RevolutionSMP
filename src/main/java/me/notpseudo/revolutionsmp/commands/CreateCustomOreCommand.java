package me.notpseudo.revolutionsmp.commands;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.mining.CustomOreType;
import me.notpseudo.revolutionsmp.listeners.HarvestingListeners;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CreateCustomOreCommand implements CommandExecutor {

    public CreateCustomOreCommand(RevolutionSMP plugin) {
        plugin.getCommand("customore").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            return true;
        }
        if (!player.isOp()) {
            player.sendMessage(Component.text("You do not have permission to use this command", NamedTextColor.RED));
            return true;
        }
        if (args.length < 1) {
            return false;
        }
        CustomOreType type = null;
        try {
            type = CustomOreType.valueOf(args[0].toUpperCase());
        } catch (IllegalArgumentException exception) {
            type = CustomOreType.values()[(int) (Math.random() * CustomOreType.values().length)];
            player.sendMessage(Component.text("Could not find that custom ore. Spawned a random custom ore instead", NamedTextColor.YELLOW));
        }
        HarvestingListeners.createCustomBlock(type, player.getLocation().getBlock().getLocation());
        player.sendMessage(Component.text(ItemEditor.getStringFromEnum(type) + " spawned at your location", NamedTextColor.GREEN));
        return true;
    }

}