package me.notpseudo.revolutionsmp.listeners;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.items.ItemType;
import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.playerstats.PlayerStats;
import me.notpseudo.revolutionsmp.playerstats.PlayerStatsDataType;
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
  private final static NamespacedKey playerStatsKey = new NamespacedKey(RevolutionSMP.getPlugin(RevolutionSMP.class), "playerStats");
  private final static NamespacedKey itemKey = ItemEditor.getItemKey();

  // Gets an instance of the main plugin
  private final RevolutionSMP plugin;

  public StatsListeners(RevolutionSMP plugin) {
    this.plugin = plugin;
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
        if(weaponItemInfo != null && !isArmor(weaponItemInfo)) {
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
    PlayerStats playerStats = player.getPersistentDataContainer().get(playerStatsKey, new PlayerStatsDataType());
    if(playerStats == null) {
      playerStats = new PlayerStats();
    }
    // Gets max Health, Intelligence, and current Mana from the database
    double health = playerStats.getMaxHealth(), intelligence = playerStats.getIntelligence(), mana = playerStats.getIntelligence();
    if(!player.isDead()) {
      if(player.getHealth() != health) {
        // If the Player is not dead and their current Health is not already full
        // Amount of Health to add is (0.75 + 0.5% of max Health) rounded up to the nearest tenth
        double addHealth = Math.ceil((0.75 + (health * 0.005)) * 10) / 10;
        // If adding the amount to add will exceed the max Health, it will just regenerate up to the max Health
        // Adds the amount to add to the Player's current Health
        double finalHealth = Math.min((player.getHealth() + addHealth), health);
        playerStats.setCurrentHealth(finalHealth);
        player.setHealth(finalHealth);
      }
    }
    if(mana != intelligence) {
      // If current Mana is not already full
      // Amount of Mana to add is 2% of max Mana
      double addMana = intelligence * 0.02;
      double finalMana = Math.min(mana + addMana, intelligence);
      playerStats.setMana(finalMana);
      // Edits the Player's Mana value in the database
    }
    player.getPersistentDataContainer().set(playerStatsKey, new PlayerStatsDataType(), playerStats);
  }

  // Shows action bar with health, defense, mana
  public static void showActionBar(Player player) {
    PlayerStats playerStats = player.getPersistentDataContainer().get(playerStatsKey, new PlayerStatsDataType());
    if(playerStats == null) {
      playerStats = new PlayerStats();
    }
    // Gets current Health, max Health, Defense, Intelligence or max Mana, and current Mana
    double currentHealth = playerStats.getCurrentHealth(), maxHealth = playerStats.getMaxHealth(), defense = playerStats.getDefense(), intelligence = playerStats.getIntelligence(), mana = playerStats.getMana();
    NamedTextColor healthColor = NamedTextColor.RED;
    double currentAbsorption = player.getAbsorptionAmount();
    if(currentAbsorption != 0) {
      // If Player has any absorption, the Health section will be gold instead of red
      healthColor = NamedTextColor.GOLD;
    }
    // Shows player their current Health out of max Health, Defense, and current Mana out of max Mana
    player.sendActionBar(Component.text(Math.round(currentHealth + currentAbsorption) + "/" + Math.round(maxHealth) + "❤     ", healthColor).append(Component.text(Math.round(defense) + "❈ Defense     ", NamedTextColor.GREEN)).append(Component.text(Math.round(mana) + "/" + Math.round(intelligence) + "✎ Mana", NamedTextColor.AQUA)));
  }

  // When a Player joins
  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    // Update stats, give full mana
    Player player = event.getPlayer();
    updateStats(player);
    PlayerStats playerStats = player.getPersistentDataContainer().get(playerStatsKey, new PlayerStatsDataType());
    if(playerStats == null) {
      playerStats = new PlayerStats();
    }
    playerStats.setMana(playerStats.getIntelligence());
    player.getPersistentDataContainer().set(playerStatsKey, new PlayerStatsDataType(), playerStats);
    showActionBar(player);
  }

  /**
   * Updates player stats when a player switches armor
   * @param event The Event fired when a player
   */
  @EventHandler
  public void onArmorSwitch(PlayerArmorChangeEvent event) {
    updateStats(event.getPlayer());
  }

  /**
   * Updates player stats when a player switched the item in their hand
   * @param event The Event when a player switched items in hand
   */
  @EventHandler
  public void onHandItemSwitch(PlayerItemHeldEvent event) {
    Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> updateStats(event.getPlayer()), 5);
  }

  /**
   * Returns if the item is a helmet, chestplate, leggings, or boots
   * @param itemInfo The ItemInfo to check
   * @return true if the item is can be worn as armor, false otherwise
   */
  private static boolean isArmor(ItemInfo itemInfo) {
    ItemType type = itemInfo.getItemType();
    return type == ItemType.HELMET || type == ItemType.CHESTPLATE || type == ItemType.LEGGINGS || type == ItemType.BOOTS;
  }

}