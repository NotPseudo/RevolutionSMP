package me.notpseudo.revolutionsmp.commands;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.abilities.AbilityType;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class AddAbilityCommand implements CommandExecutor {

    public AddAbilityCommand(RevolutionSMP plugin) {
        plugin.getCommand("addability").setExecutor(this);
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            AbilityType type;
            if (args.length >= 1) {
                try {
                    type = AbilityType.valueOf(args[0].toUpperCase());
                } catch (IllegalArgumentException exception) {
                    player.sendMessage(Component.text("This ability could not be found. Check your spelling", NamedTextColor.RED));
                    return true;
                }
                ItemStack item = player.getInventory().getItemInMainHand();
                ItemEditor.addAbility(item, type);
                player.sendMessage(Component.text("Added ability", NamedTextColor.GREEN));
            } else {
                return false;
            }
        }
        return true;
    }
}