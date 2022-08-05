package me.notpseudo.revolutionsmp.commands;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.listeners.CustomOreLocation;
import me.notpseudo.revolutionsmp.listeners.HarvestingListeners;
import me.notpseudo.revolutionsmp.listeners.PlacedLocationList;
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
            PlacedLocationList locationList = HarvestingListeners.getPlacedLocationList(player.getLocation().getBlock());
            player.sendMessage(locationList.getOreLocations().size() + " custom ore locations");
            for (CustomOreLocation oreLocation : locationList.getOreLocations()) {
                player.sendMessage(oreLocation.getType() + " at X: " + oreLocation.getX() + ", Y: " + oreLocation.getY() + ", Z: " + oreLocation.getZ());
            }
        }
        return true;
    }

}