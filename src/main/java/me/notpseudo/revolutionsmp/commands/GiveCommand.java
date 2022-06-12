package me.notpseudo.revolutionsmp.commands;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.items.ArmorCreator;
import me.notpseudo.revolutionsmp.items.ItemID;
import me.notpseudo.revolutionsmp.items.WeaponCreator;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

// Development and testing command to obtain custom items
public class GiveCommand implements CommandExecutor {

    public GiveCommand(RevolutionSMP plugin) {
        plugin.getCommand("giveitem").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            if (player.isOp()) {
                if (args.length > 0) {
                    if (args[0].equalsIgnoreCase("all")) {
                        for(ItemID id : ItemID.values()) {
                            if(player.getInventory().addItem(id.getItem()).size() > 0) {
                                player.sendMessage(Component.text(id.getDefaultName(), id.getDefaultRarity().getRarityColor()).append(Component.text(" could not be added to the inventory!", NamedTextColor.RED)));
                            }
                        }
                        return true;
                    }
                    String id = args[0].toUpperCase();
                    try {
                        player.getInventory().addItem(ItemID.valueOf(id).getItem());
                    } catch (IllegalArgumentException exception) {
                        player.sendMessage(Component.text("This ItemID could not be found. Check your spelling", NamedTextColor.RED));
                        return true;
                    }
                } else {
                    player.sendMessage(Component.text("Missing arguments!", NamedTextColor.RED));
                    return false;
                }
            }
        }
        return true;
    }
}