package me.notpseudo.revolutionsmp.commands;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.particles.Particles;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Particle;
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
            int sides = 4;
            try {
                sides = Integer.parseInt(args[0]);
            } catch (NumberFormatException exception) {
                player.sendMessage(Component.text("Could not read this number", NamedTextColor.RED));
                return true;
            }
            Particles.polygon(player.getLocation(), sides, 5);
        }
        return true;
    }

}