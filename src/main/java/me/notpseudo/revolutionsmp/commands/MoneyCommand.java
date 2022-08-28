package me.notpseudo.revolutionsmp.commands;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.economy.EcoUtils;
import me.notpseudo.revolutionsmp.economy.PlayerEcoInfo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MoneyCommand implements CommandExecutor {

    public MoneyCommand(RevolutionSMP plugin) {
        plugin.getCommand("money").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            return true;
        }

        PlayerEcoInfo info = EcoUtils.getEcoInfo(player);
        if (args.length < 3) {
            player.sendMessage(Component.text("Your purse: ", NamedTextColor.GRAY).append(Component.text(info.getPurse(), NamedTextColor.GOLD)));
            player.sendMessage(Component.text("Your bank: ", NamedTextColor.GRAY).append(Component.text(info.getBank(), NamedTextColor.GOLD)));
            return true;
        }
        if (!player.isOp()) {
            player.sendMessage(Component.text("You do not have permission to use this command", NamedTextColor.RED));
            return true;
        }
        String type = args[0];
        String action = args[1];
        double amount;
        try {
            amount = Double.parseDouble(args[2]);
        } catch (NumberFormatException exception) {
            amount = 0;
        }
        if (!(type.equalsIgnoreCase("bank") || type.equalsIgnoreCase("purse"))) {
            return false;
        }
        if (!(action.equalsIgnoreCase("add") || action.equalsIgnoreCase("remove") || action.equalsIgnoreCase("set"))) {
            return false;
        }
        if (type.equalsIgnoreCase("bank")) {
            switch (action.toLowerCase()) {
                case "add" -> info.addBank(amount);
                case "remove" -> info.addBank(-1 * amount);
                case "set" -> info.setBank(amount);
            }
            return true;
        }
        if (type.equalsIgnoreCase("purse")) {
            switch (action.toLowerCase()) {
                case "add" -> info.addPurse(amount);
                case "remove" -> info.addPurse(-1 * amount);
                case "set" -> info.setPurse(amount);
            }
            return true;
        }
        return true;
    }
}
