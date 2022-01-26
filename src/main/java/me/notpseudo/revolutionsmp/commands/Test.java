package me.notpseudo.revolutionsmp.commands;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.items.ArmorCreator;
import me.notpseudo.revolutionsmp.items.WeaponCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// Development and testing command to obtain custom items
public class Test implements CommandExecutor {

  public Test(RevolutionSMP plugin) {
    plugin.getCommand("test").setExecutor(this);
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if(sender instanceof Player) {
      Player player = (Player) sender;
      if(player.isOp()) {
        player.getInventory().addItem(WeaponCreator.aote);
        player.getInventory().addItem(WeaponCreator.hyperion);
        player.getInventory().addItem(WeaponCreator.valkyrie);
        player.getInventory().addItem(WeaponCreator.scylla);
        player.getInventory().addItem(WeaponCreator.astraea);
        player.getInventory().addItem(WeaponCreator.jujuShortbow);
        player.getInventory().addItem(WeaponCreator.terminator);
        player.getInventory().addItem(ArmorCreator.stormChest);
        player.getInventory().addItem(ArmorCreator.stormLeg);
        player.getInventory().addItem(ArmorCreator.stormBoot);
      }
    }
    return true;
  }
}