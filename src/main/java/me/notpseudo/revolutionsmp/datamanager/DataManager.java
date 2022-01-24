package me.notpseudo.revolutionsmp.datamanager;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

// Credit: CodedRed's Custom Config File tutorial: https://www.youtube.com/watch?v=-ZrIjYXOkn0
// Used to work with config files
public class DataManager {

  private final RevolutionSMP plugin;
  private FileConfiguration dataConfig;
  private File configFile;

  public DataManager(RevolutionSMP plugin) {
    this.plugin = plugin;
    dataConfig = null;
    configFile = null;
    saveDefaultConfig();
  }

  public void reloadConfig() {
    if(this.configFile == null) {
      configFile = new File(this.plugin.getDataFolder(), "data.yml");
    }
    dataConfig = YamlConfiguration.loadConfiguration(this.configFile);
    InputStream defaultStream = this.plugin.getResource("data.yml");
    if(defaultStream != null) {
      YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
      dataConfig.setDefaults(defaultConfig);
    }
  }

  public FileConfiguration getConfig() {
    if(dataConfig == null) {
      reloadConfig();
    }
    return this.dataConfig;
  }

  public void saveConfig() {
    if(dataConfig != null && configFile != null) {
      try {
        getConfig().save(configFile);
      } catch (IOException e) {
        plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.configFile, e);
      }
    }
  }

  public void saveDefaultConfig() {
    if(configFile == null) {
      configFile = new File(plugin.getDataFolder(), "data.yml");
    }
    if(!configFile.exists()) {
      plugin.saveResource("data.yml", false);
    }
  }

}
