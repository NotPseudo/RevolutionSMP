package me.notpseudo.revolutionsmp;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import me.notpseudo.revolutionsmp.abilities.Abilities;
import me.notpseudo.revolutionsmp.commands.PotatoBook;
import me.notpseudo.revolutionsmp.commands.Recombobulate;
import me.notpseudo.revolutionsmp.commands.ReforgeCommand;
import me.notpseudo.revolutionsmp.commands.Test;
import me.notpseudo.revolutionsmp.items.ArmorCreator;
import me.notpseudo.revolutionsmp.items.WeaponCreator;
import me.notpseudo.revolutionsmp.listeners.HealthListeners;
import me.notpseudo.revolutionsmp.listeners.ItemUse;
import me.notpseudo.revolutionsmp.listeners.StatsListeners;
import org.bukkit.plugin.java.JavaPlugin;

public final class RevolutionSMP extends JavaPlugin {

  // MongoDB client to access database and instance of this main plugin class
  private static RevolutionSMP plugin;
  private MongoClient mongoClient;

  // Initializes many of the classes when the plugin gets loaded
  @Override
  public void onEnable() {
    this.saveDefaultConfig();
    // Create instance of a MongoClient to access data
    if(this.getConfig().getString("mongodb_connection_string") == null || this.getConfig().getString("mongodb_connection_string").isEmpty()) {
      this.getLogger().warning("There is no MongoDB connection String provided. Many features will not work");
    } else {
      mongoClient = MongoClients.create(this.getConfig().getString("mongodb_connection_string"));
    }
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

  // Returns the instance of this main plugin class
  public static RevolutionSMP getPlugin() {
    return plugin;
  }

  // Returns instance of the Mongo client to access data
  public MongoClient getMongoClient() {
    return mongoClient;
  }

}