package me.notpseudo.revolutionsmp.listeners;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.playerstats.PlayerStats;
import me.notpseudo.revolutionsmp.playerstats.PlayerStatsDataType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bson.Document;
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
  private final static NamespacedKey playerStatsKey = new NamespacedKey(RevolutionSMP.getPlugin(RevolutionSMP.class), "playerStats");
  private final static NamespacedKey itemKey = ItemEditor.getItemKey();
  // Gets the database with Player info
  private static MongoDatabase playerDatabase;
  private static MongoClient mongoClient;

  // Gets an instance of the main plugin
  private final RevolutionSMP plugin;

  public StatsListeners(RevolutionSMP plugin) {
    this.plugin = plugin;
    mongoClient = plugin.getMongoClient();
    playerDatabase = mongoClient.getDatabase("players");
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

  // Returns NamespacedKey for other classes to use
  public NamespacedKey getPlayerStatsKey() {
    return playerStatsKey;
  }

  // Updates a Player's stats
  public static void updateStats(Player player) {
    MongoCollection<Document> playerCollection = playerDatabase.getCollection("" + player.getUniqueId());
    // Assigns base values for each stat
    double health = 100, defense = 0, strength = 0, speed = 100, critChance = 30, critDamage = 50, attackSpeed = 0, intelligence = 100, abilityDamage = 0, ferocity = 0;
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
            attackSpeed += helmetWeaponStats.getAttackSpeed();
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
            attackSpeed += chestplateWeaponStats.getAttackSpeed();
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
            attackSpeed += leggingsWeaponStats.getAttackSpeed();
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
            attackSpeed += bootsWeaponStats.getAttackSpeed();
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
            attackSpeed += mainHandWeaponStats.getAttackSpeed();
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
    PlayerStats playerStats = player.getPersistentDataContainer().get(playerStatsKey, new PlayerStatsDataType());
    if(playerStats == null) {
      playerStats = new PlayerStats();
    }
    playerStats.setMaxHealth(health);
    playerStats.setDefense(defense);
    playerStats.setSpeed(speed);
    playerStats.setStrength(strength);
    playerStats.setCritChance(critChance);
    playerStats.setCritDamage(critDamage);
    playerStats.setAttackSpeed(attackSpeed);
    playerStats.setFerocity(ferocity);
    playerStats.setIntelligence(intelligence);
    playerStats.setAbilityDamage(abilityDamage);
    // Edits the Player's stat values in the database
    long count = playerCollection.countDocuments(new Document("docType", "PLAYERSTATS"));
    if(count > 0) {
      playerCollection.updateOne(new Document("docType", "PLAYERSTATS"), Updates.combine(Updates.set("health", health)));
      playerCollection.updateOne(new Document("docType", "PLAYERSTATS"), Updates.combine(Updates.set("defense", defense)));
      playerCollection.updateOne(new Document("docType", "PLAYERSTATS"), Updates.combine(Updates.set("strength", strength)));
      playerCollection.updateOne(new Document("docType", "PLAYERSTATS"), Updates.combine(Updates.set("speed", speed)));
      playerCollection.updateOne(new Document("docType", "PLAYERSTATS"), Updates.combine(Updates.set("critChance", critChance)));
      playerCollection.updateOne(new Document("docType", "PLAYERSTATS"), Updates.combine(Updates.set("critDamage", critDamage)));
      playerCollection.updateOne(new Document("docType", "PLAYERSTATS"), Updates.combine(Updates.set("attackSpeed", attackSpeed)));
      playerCollection.updateOne(new Document("docType", "PLAYERSTATS"), Updates.combine(Updates.set("intelligence", intelligence)));
      playerCollection.updateOne(new Document("docType", "PLAYERSTATS"), Updates.combine(Updates.set("abilityDamage", abilityDamage)));
      playerCollection.updateOne(new Document("docType", "PLAYERSTATS"), Updates.combine(Updates.set("ferocity", ferocity)));
    } else {
      playerCollection.insertOne(new Document("docType", "PLAYERSTATS")
              .append("health", health)
              .append("defense", defense)
              .append("strength", strength)
              .append("speed", speed)
              .append("critChance",critChance)
              .append("critDamage", critDamage)
              .append("attackSpeed", attackSpeed)
              .append("intelligence", intelligence)
              .append("abilityDamage", abilityDamage)
              .append("ferocity", ferocity));
      playerDatabase = mongoClient.getDatabase("players");
    }
    // Adjusts Player's Health to new max Health by keeping the same percentage
    double maxHealth = Math.min(2048, health);
    double healthPercent = player.getHealth() / player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
    player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
    // Player will always see 40 hit points or 20 hearts on their screen
    player.setHealthScale(40);
    player.setHealth(healthPercent * maxHealth);
    playerStats.setCurrentHealth(healthPercent * health);
    // Adjusts a Player's Speed
    player.setWalkSpeed((float) (speed / 500));
    // Set a Player's Attack Speed
    player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(4 * (1 + (attackSpeed / 100)));
    player.getPersistentDataContainer().set(playerStatsKey, new PlayerStatsDataType(), playerStats);
  }

  // Regenerates Health and Mana for the Player
  public static void naturalRegen(Player player) {
    MongoCollection<Document> playerCollection = playerDatabase.getCollection("" + player.getUniqueId());
    Document playerStats = playerCollection.find(new Document("docType", "PLAYERSTATS")).first();
    // Gets max Health, Intelligence, and current Mana from the database
    double health = playerStats.getDouble("health"), intelligence = playerStats.getDouble("intelligence"), mana = playerStats.getDouble("mana");
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
      // Edits the Player's Mana value in the database
      playerCollection.updateOne(new Document("docType", "PLAYERSTATS"), Updates.combine(Updates.set("mana", mana)));
    }
  }

  // Shows action bar with health, defense, mana
  public static void showActionBar(Player player) {
    Document playerStats = playerDatabase.getCollection("" + player.getUniqueId()).find(new Document().append("docType", "PLAYERSTATS")).first();
    // Gets max Health, Defense, Intelligence or max Mana, and current Mana
    double health = playerStats.getDouble("health"), defense = playerStats.getDouble("defense"), intelligence = playerStats.getDouble("intelligence"), mana = playerStats.getDouble("mana");
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
    MongoCollection<Document> playerCollection = playerDatabase.getCollection("" + event.getPlayer().getUniqueId());
    Document playerStats = playerCollection.find(new Document("docType", "PLAYERSTATS")).first();    // Update stats, give full mana
    updateStats(event.getPlayer());
    playerCollection.updateOne(new Document("docType", "PLAYERSTATS"), Updates.combine(Updates.set("mana", playerStats.getDouble("intelligence"))));
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