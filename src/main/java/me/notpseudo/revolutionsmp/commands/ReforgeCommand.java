package me.notpseudo.revolutionsmp.commands;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.items.Reforge;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// Command to Reforge the current held item to a specified reforge
public class ReforgeCommand implements CommandExecutor {

  private RevolutionSMP plugin;

  public ReforgeCommand(RevolutionSMP plugin) {
    this.plugin = plugin;
    plugin.getCommand("reforge").setExecutor(this);
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if(sender instanceof Player) {
      Player player = (Player) sender;
      if(player.isOp()) {
        String reforgeString = args[0];
        Reforge reforge = Reforge.valueOf(reforgeString);
        if(reforge != null) {
          ItemEditor.reforge(player, reforge);
        } else {
          player.sendMessage(Component.text("This reforge could not be found!"));
        }
      }
    }
    return true;
  }
}
