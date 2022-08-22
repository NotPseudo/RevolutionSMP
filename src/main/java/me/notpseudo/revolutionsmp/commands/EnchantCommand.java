package me.notpseudo.revolutionsmp.commands;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentType;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class EnchantCommand implements CommandExecutor {

    public EnchantCommand(RevolutionSMP plugin) {
        plugin.getCommand("customenchant").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            EnchantmentType type;
            int level;
            if (args.length >= 1) {
                try {
                    type = EnchantmentType.valueOf(args[0].toUpperCase());
                } catch (IllegalArgumentException exception) {
                    player.sendMessage(Component.text("This enchantment could not be found. Check your spelling", NamedTextColor.RED));
                    return true;
                }
                if (args.length >= 2) {
                    try {
                        level = Integer.parseInt(args[1]);
                    } catch (NumberFormatException exception) {
                        level = type.getMinLevel();
                        player.sendMessage(Component.text("Invalid number for level. Level set to lowest level", NamedTextColor.YELLOW));
                    }
                } else {
                    level = type.getMinLevel();
                    player.sendMessage(Component.text("Invalid number for level. Level set to lowest level", NamedTextColor.YELLOW));
                }
                ItemStack item = player.getInventory().getItemInMainHand();
                ItemEditor.addEnchant(item, type, level);
                player.sendMessage(Component.text("Applied enchant", NamedTextColor.GREEN));
            } else {
                return false;
            }
        }
        return true;
    }
}
