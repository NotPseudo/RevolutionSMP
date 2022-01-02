package me.notpseudo.revolutionsmp;

import me.notpseudo.revolutionsmp.abilities.Abilities;
import me.notpseudo.revolutionsmp.commands.PotatoBook;
import me.notpseudo.revolutionsmp.commands.Recombobulate;
import me.notpseudo.revolutionsmp.commands.ReforgeCommand;
import me.notpseudo.revolutionsmp.commands.Test;
import me.notpseudo.revolutionsmp.datamanager.DataManager;
import me.notpseudo.revolutionsmp.items.ArmorCreator;
import me.notpseudo.revolutionsmp.items.WeaponCreator;
import me.notpseudo.revolutionsmp.listeners.HealthListeners;
import me.notpseudo.revolutionsmp.listeners.ItemUse;
import me.notpseudo.revolutionsmp.listeners.StatsListeners;
import org.bukkit.plugin.java.JavaPlugin;

public final class RevolutionSMP extends JavaPlugin {

  // DataManager to work with config files and instance of this main plugin class
  private DataManager dataManager = new DataManager(this);
  private static RevolutionSMP plugin;

  // Initializes many of the classes when the plugin gets loaded
  @Override
  public void onEnable() {
    new StatsListeners(this);
    new HealthListeners(this);
    new ItemUse(this);
    new Test(this);
    new Recombobulate(this);
    new PotatoBook(this);
    new ReforgeCommand(this);
    WeaponCreator.createWeapons();
    ArmorCreator.createArmors();
    Abilities.createPassSet();
    plugin = this;
  }

  // Nothing is required to run when this plugin gets disabled
  @Override
  public void onDisable() {

  }

  // Returns the DataManager for working with config files
  public DataManager getDataManager() {
    return dataManager;
  }

  // Returns the instance of this main plugin class
  public static RevolutionSMP getPlugin() {
    return plugin;
  }

}
