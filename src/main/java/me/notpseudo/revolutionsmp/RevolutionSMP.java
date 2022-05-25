package me.notpseudo.revolutionsmp;

import me.notpseudo.revolutionsmp.abilities.Abilities;
import me.notpseudo.revolutionsmp.commands.*;
import me.notpseudo.revolutionsmp.items.ArmorCreator;
import me.notpseudo.revolutionsmp.items.WeaponCreator;
import me.notpseudo.revolutionsmp.listeners.HealthListeners;
import me.notpseudo.revolutionsmp.listeners.ItemUse;
import me.notpseudo.revolutionsmp.listeners.MobListeners;
import me.notpseudo.revolutionsmp.listeners.StatsListeners;
import org.bukkit.plugin.java.JavaPlugin;

public final class RevolutionSMP extends JavaPlugin {

  // MongoDB client to access database and instance of this main plugin class
  private static RevolutionSMP plugin;

  // Initializes many of the classes when the plugin gets loaded
  @Override
  public void onEnable() {
    this.saveDefaultConfig();
    new StatsListeners(this);
    new MobListeners(this);
    new HealthListeners(this);
    new ItemUse(this);
    new Test(this);
    new Recombobulate(this);
    new ProfileCommand(this);
    new ResetStats(this);
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

  // Returns the instance of this main plugin class
  public static RevolutionSMP getPlugin() {
    return plugin;
  }

}