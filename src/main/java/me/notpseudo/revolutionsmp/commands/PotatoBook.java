package me.notpseudo.revolutionsmp.commands;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// Command to add a Potato Book to the current held item
public class PotatoBook implements CommandExecutor {

  public PotatoBook(RevolutionSMP plugin) {
    plugin.getCommand("potatobook").setExecutor(this);
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if(sender instanceof Player) {
      Player player = (Player) sender;
      if(player.isOp()) {
        ItemEditor.hotPotatoBook(player);
      }
    }
    return true;
  }
}