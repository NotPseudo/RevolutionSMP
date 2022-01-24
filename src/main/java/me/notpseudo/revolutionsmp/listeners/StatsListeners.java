package me.notpseudo.revolutionsmp.listeners;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.statobjects.*;
import me.notpseudo.revolutionsmp.datamanager.DataManager;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.ItemMeta;

// Methods that deal with updating player stats
public class StatsListeners implements Listener {

  // Gets NamespacedKeys from ItemEditor to access stats stored in Persistent Data
  private final static NamespacedKey itemKey = ItemEditor.getItemKey();

  // Get DataManager to access and edit config files
  private final RevolutionSMP plugin;
  private static DataManager dataManager;

  public StatsListeners(RevolutionSMP plugin) {
    this.plugin = plugin;
    dataManager = plugin.getDataManager();
    Bukkit.getPluginManager().registerEvents(this, this.plugin);
    Bukkit.getScheduler().scheduleSyncRepeatingTask(this.plugin, () -> {
      // Every 20 game ticks, for all players, update player stats. regen health and mana, show action bar with info
      for(Player player : Bukkit.getOnlinePlayers()) {
        updateStats(player);
        naturalRegen(player);
        showActionBar(player);
      }
    }, 20, 20);
  }

  // Updates a Player's stats
  public static void updateStats(Player player) {
    // Assigns base values for each stat
    double health = 20, defense = 0, strength = 0, speed = 100, critChance = 30, critDamage = 50, intelligence = 100, abilityDamage = 0, ferocity = 0;
    // Checks to make sure each item that will affect stats is not null, checks to make sure each item's meta is not null
    // Gets and adds item stat boosts to base amounts
    if(player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getItemMeta() != null) {
      ItemMeta helmetMeta = player.getInventory().getHelmet().getItemMeta();
      if(helmetMeta != null) {
        ItemInfo helmetItemInfo = helmetMeta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
        if(helmetItemInfo != null) {
          WeaponStats helmetWeaponStats = helmetItemInfo.getWeaponStats();
          ArmorStats helmetArmorStats = helmetItemInfo.getArmorStats();
          AbilityStats helmetAbilityStats = helmetItemInfo.getAbilityStats();
          if(helmetWeaponStats != null) {
            strength += helmetWeaponStats.getStrength();
            critChance += helmetWeaponStats.getCritChance();
            critDamage += helmetWeaponStats.getCritDamage();
            ferocity += helmetWeaponStats.getFerocity();
          }
          if(helmetArmorStats != null) {
            health += helmetArmorStats.getHealth();
            defense += helmetArmorStats.getDefense();
            speed += helmetArmorStats.getSpeed();
          }
          if(helmetAbilityStats != null) {
            abilityDamage += helmetAbilityStats.getAbilityDamage();
            intelligence += helmetAbilityStats.getIntelligence();
          }
        }
      }
    }
    if(player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta() != null) {
      ItemMeta chestplateMeta = player.getInventory().getChestplate().getItemMeta();
      if(chestplateMeta != null) {
        ItemInfo chestplateItemInfo = chestplateMeta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
        if(chestplateItemInfo != null) {
         WeaponStats chestplateWeaponStats = chestplateItemInfo.getWeaponStats();
         ArmorStats chestplateArmorStats = chestplateItemInfo.getArmorStats();
         AbilityStats chestplateAbilityStats = chestplateItemInfo.getAbilityStats();
         if(chestplateWeaponStats != null) {
           strength += chestplateWeaponStats.getStrength();
           critChance += chestplateWeaponStats.getCritChance();
           critDamage += chestplateWeaponStats.getCritDamage();
           ferocity += chestplateWeaponStats.getFerocity();
         }
         if(chestplateArmorStats != null) {
           health += chestplateArmorStats.getHealth();
           defense += chestplateArmorStats.getDefense();
           speed += chestplateArmorStats.getSpeed();
         }
         if(chestplateAbilityStats != null) {
           abilityDamage += chestplateAbilityStats.getAbilityDamage();
           intelligence += chestplateAbilityStats.getIntelligence();
         }
       }
      }
    }
    if(player.getInventory().getLeggings() != null && player.getInventory().getLeggings().getItemMeta() != null) {
      ItemMeta leggingsMeta = player.getInventory().getLeggings().getItemMeta();
      if(leggingsMeta != null) {
        ItemInfo leggingsItemInfo = leggingsMeta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
        if(leggingsItemInfo != null) {
          WeaponStats leggingsWeaponStats = leggingsItemInfo.getWeaponStats();
          ArmorStats leggingsArmorStats = leggingsItemInfo.getArmorStats();
          AbilityStats leggingsAbilityStats = leggingsItemInfo.getAbilityStats();
          if(leggingsWeaponStats != null) {
            strength += leggingsWeaponStats.getStrength();
            critChance += leggingsWeaponStats.getCritChance();
            critDamage += leggingsWeaponStats.getCritDamage();
            ferocity += leggingsWeaponStats.getFerocity();
          }
          if(leggingsArmorStats != null) {
            health += leggingsArmorStats.getHealth();
            defense += leggingsArmorStats.getDefense();
            speed += leggingsArmorStats.getSpeed();
          }
          if(leggingsAbilityStats != null) {
            abilityDamage += leggingsAbilityStats.getAbilityDamage();
            intelligence += leggingsAbilityStats.getIntelligence();
          }
        }
      }
    }
    if(player.getInventory().getBoots() != null && player.getInventory().getBoots().getItemMeta() != null) {
      ItemMeta bootsMeta = player.getInventory().getBoots().getItemMeta();
      if(bootsMeta != null) {
        ItemInfo bootsItemInfo = bootsMeta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
        if(bootsItemInfo != null) {
          WeaponStats bootsWeaponStats = bootsItemInfo.getWeaponStats();
          ArmorStats bootsArmorStats = bootsItemInfo.getArmorStats();
          AbilityStats bootsAbilityStats = bootsItemInfo.getAbilityStats();
          if(bootsWeaponStats != null) {
            strength += bootsWeaponStats.getStrength();
            critChance += bootsWeaponStats.getCritChance();
            critDamage += bootsWeaponStats.getCritDamage();
            ferocity += bootsWeaponStats.getFerocity();
          }
          if(bootsArmorStats != null) {
            health += bootsArmorStats.getHealth();
            defense += bootsArmorStats.getDefense();
            speed += bootsArmorStats.getSpeed();
          }
          if(bootsAbilityStats != null) {
            abilityDamage += bootsAbilityStats.getAbilityDamage();
            intelligence += bootsAbilityStats.getIntelligence();
          }
        }
      }
    }
    if(player.getInventory().getItemInMainHand().getType() != Material.AIR && player.getInventory().getItemInMainHand().getItemMeta() != null) {
      ItemMeta mainHandMeta = player.getInventory().getItemInMainHand().getItemMeta();
      if (mainHandMeta != null) {
        ItemInfo weaponItemInfo = mainHandMeta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
        if(weaponItemInfo != null) {
          WeaponStats mainHandWeaponStats = weaponItemInfo.getWeaponStats();
          ArmorStats mainHandArmorStats = weaponItemInfo.getArmorStats();
          AbilityStats mainHandAbilityStats = weaponItemInfo.getAbilityStats();
          if (mainHandWeaponStats != null) {
            strength += mainHandWeaponStats.getStrength();
            critChance += mainHandWeaponStats.getCritChance();
            critDamage += mainHandWeaponStats.getCritDamage();
            ferocity += mainHandWeaponStats.getFerocity();
          }
          if (mainHandArmorStats != null) {
            health += mainHandArmorStats.getHealth();
            defense += mainHandArmorStats.getDefense();
            speed += mainHandArmorStats.getSpeed();
          }
          if (mainHandAbilityStats != null) {
            abilityDamage += mainHandAbilityStats.getAbilityDamage();
            intelligence += mainHandAbilityStats.getIntelligence();
          }
        }
      }
    }
    // Edits the Player's stat values in the config file
    dataManager.getConfig().set(player.getUniqueId() + ".health", health);
    dataManager.getConfig().set(player.getUniqueId() + ".defense", defense);
    dataManager.getConfig().set(player.getUniqueId() + ".strength", strength);
    dataManager.getConfig().set(player.getUniqueId() + ".speed", speed);
    dataManager.getConfig().set(player.getUniqueId() + ".critChance", critChance);
    dataManager.getConfig().set(player.getUniqueId() + ".critDamage", critDamage);
    dataManager.getConfig().set(player.getUniqueId() + ".intelligence", intelligence);
    dataManager.getConfig().set(player.getUniqueId() + ".abilityDamage", abilityDamage);
    dataManager.getConfig().set(player.getUniqueId() + ".ferocity", ferocity);
    dataManager.saveConfig();
    // Adjusts Player's Health to new max Health by keeping the same percentage
    double healthPercent = player.getHealth() / player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
    player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(dataManager.getConfig().getDouble(player.getUniqueId() + ".health"));
    // Player will always see 40 hit points or 20 hearts on their screen
    player.setHealthScale(40);
    player.setHealth(healthPercent * player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
    // Adjusts a Player's Speed based on their Speed stat in the config file
    player.setWalkSpeed((float) dataManager.getConfig().getDouble(player.getUniqueId() + ".speed") / 500);
    showActionBar(player);
  }

  // Regenerates Health and Mana for the Player
  public static void naturalRegen(Player player) {
    // Gets max Health, Intelligence, and current Mana from the config file
    double health = dataManager.getConfig().getDouble(player.getUniqueId() + ".health"), intelligence = dataManager.getConfig().getDouble(player.getUniqueId() + ".intelligence"), mana = dataManager.getConfig().getDouble(player.getUniqueId() + ".mana");
    if(!player.isDead()) {
      if(player.getHealth() != health) {
        // If the Player is not dead and their current Health is not already full
        // Amount of Health to add is (0.75 + 0.5% of max Health) rounded up to the nearest tenth
        double addHealth = Math.ceil((0.75 + (health * 0.005)) * 10) / 10;
        // If adding the amount to add will exceed the max Health, it will just regenerate up to the max Health
        // Adds the amount to add to the Player's current Health
        player.setHealth(Math.min((player.getHealth() + addHealth), health));
      }
    }
    if(mana != intelligence) {
      // If current Mana is not already full
      // Amount of Mana to add is 2% of max Mana
      double addMana = intelligence * 0.02;
      if((mana + addMana) >= intelligence) {
        // If adding the amount to add will exceed the max Mana, it will just regenerate up to the max Mana
        mana = intelligence;
      } else {
        // Adds the amount to add to the Player's current Mana
        mana += addMana;
      }
      // Edits the Player's Mana value in the config file
      dataManager.getConfig().set(player.getUniqueId() + ".mana", mana);
    }
  }

  // SHows action bar with health, defense, mana
  public static void showActionBar(Player player) {
    // Gets max Health, Defense, Intelligence or max Mana, and current Mana
    double health = dataManager.getConfig().getDouble(player.getUniqueId() + ".health"), defense = dataManager.getConfig().getDouble(player.getUniqueId() + ".defense"), intelligence = dataManager.getConfig().getDouble(player.getUniqueId() + ".intelligence"), mana = dataManager.getConfig().getDouble(player.getUniqueId() + ".mana");
    NamedTextColor healthColor = NamedTextColor.RED;
    double currentAbsorption = player.getAbsorptionAmount();
    if(currentAbsorption != 0) {
      // If Player has any absorption, the Health section will be gold instead of red
      healthColor = NamedTextColor.GOLD;
    }
    // Shows player their current Health out of max Health, Defense, and current Mana out of max Mana
    player.sendActionBar(Component.text(Math.round(player.getHealth() + currentAbsorption) + "/" + Math.round(health) + "❤     ", healthColor).append(Component.text(Math.round(defense) + "❈ Defense     ", NamedTextColor.GREEN)).append(Component.text(Math.round(mana) + "/" + Math.round(intelligence) + "✎ Mana", NamedTextColor.AQUA)));
  }

  // When a Player joins
  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    // Update stats, give full mana
    updateStats(event.getPlayer());
    dataManager.getConfig().set(event.getPlayer().getUniqueId() + ".mana", dataManager.getConfig().getDouble(event.getPlayer().getUniqueId() + ".intelligence"));
    showActionBar(event.getPlayer());
  }

  // When a Player switches any armor piece
  @EventHandler
  public void onArmorSwitch(PlayerArmorChangeEvent event) {
    updateStats(event.getPlayer());
  }

  // When a Player switches to holding a different item
  @EventHandler
  public void onHandItemSwitch(PlayerItemHeldEvent event) {
    Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> updateStats(event.getPlayer()), 5);
  }

}