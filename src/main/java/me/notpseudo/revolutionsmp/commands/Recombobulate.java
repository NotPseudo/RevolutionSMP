package me.notpseudo.revolutionsmp.commands;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Recombobulate implements CommandExecutor {

  private RevolutionSMP plugin;

  public Recombobulate(RevolutionSMP plugin) {
    this.plugin = plugin;
    plugin.getCommand("recombobulate").setExecutor(this);
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if(sender instanceof Player) {
      Player player = (Player) sender;
      if(player.isOp()) {
        ItemEditor.recombobulate(player);
      }
    }
    return true;
  }
}
