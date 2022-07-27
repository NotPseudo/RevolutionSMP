package me.notpseudo.revolutionsmp.commands;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.particles.Particles;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class EditAbilityCommand implements CommandExecutor {

    public EditAbilityCommand(RevolutionSMP plugin) {
        plugin.getCommand("editability").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            double num = 1;
            if (args.length >= 1) {
                if (args.length >= 2) {
                    try {
                        num = Double.parseDouble(args[1]);
                    } catch (NumberFormatException exception) {
                        player.sendMessage(Component.text("Invalid number", NamedTextColor.RED));
                        return true;
                    }
                }
                Particles.gravityStormParticles(player.getLocation(), 8, new Material[]{Material.OBSIDIAN, Material.PURPLE_CONCRETE}, 16, 3);
            } else {
                return false;
            }
        }
        return true;
    }
}
