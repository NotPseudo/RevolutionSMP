package me.notpseudo.revolutionsmp.commands;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.listeners.MobListeners;
import me.notpseudo.revolutionsmp.mobstats.CustomMobType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnCustomCommand implements CommandExecutor {

    public SpawnCustomCommand(RevolutionSMP plugin) {
        plugin.getCommand("spawncustom").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (args.length < 1) {
                return false;
            }
            CustomMobType type;
            try {
                type = CustomMobType.valueOf(args[0].toUpperCase());
            } catch (IllegalArgumentException exception) {
                type = CustomMobType.values()[(int) (Math.random() * CustomMobType.values().length)];
            }
            player.sendMessage(Component.text("Spawned " + ItemEditor.getStringFromEnum(type) + " at your location", NamedTextColor.GREEN));
            MobListeners.spawnCustom(type, player.getLocation(), null);
        }
        return true;
    }
}