package me.notpseudo.revolutionsmp.listeners;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.datacontainers.*;
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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class StatsListeners implements Listener {

  private final static NamespacedKey itemKey = ItemEditor.getItemKey();
  private final static NamespacedKey weaponKey = ItemEditor.getWeaponKey();
  private final static NamespacedKey armorKey = ItemEditor.getArmorKey();
  private final static NamespacedKey abilityKey = ItemEditor.getAbilityKey();

  private RevolutionSMP plugin;
  private static DataManager dataManager;

  public StatsListeners(RevolutionSMP plugin) {
    this.plugin = plugin;
    dataManager = plugin.getDataManager();
    Bukkit.getPluginManager().registerEvents(this, this.plugin);
    Bukkit.getScheduler().scheduleSyncRepeatingTask(this.plugin, () -> {
      for(Player player : Bukkit.getOnlinePlayers()) {
        updateStats(player);
        naturalRegen(player);
        showActionBar(player);
      }
    }, 20, 20);
  }

  public static void updateStats(Player player) {
    double health = 20, defense = 0, strength = 0, speed = 100, critChance = 30, critDamage = 50, intelligence = 100, abilityDamage = 0, ferocity = 0;
    if(player.getInventory().getHelmet() != null) {
      if(player.getInventory().getHelmet().getItemMeta() != null) {
        ItemMeta helmetMeta = player.getInventory().getHelmet().getItemMeta();
        if(helmetMeta != null) {
          WeaponStats helmetWeaponStats = helmetMeta.getPersistentDataContainer().get(weaponKey, new WeaponStatsDataType());
          ArmorStats helmetArmorStats = helmetMeta.getPersistentDataContainer().get(armorKey, new ArmorStatsDataType());
          AbilityStats helmetAbilityStats = helmetMeta.getPersistentDataContainer().get(abilityKey, new AbilityStatsDataType());
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
    if(player.getInventory().getChestplate() != null) {
      if(player.getInventory().getChestplate().getItemMeta() != null) {
        ItemMeta chestplateMeta = player.getInventory().getChestplate().getItemMeta();
        if(chestplateMeta != null) {
          WeaponStats chestplateWeaponStats = chestplateMeta.getPersistentDataContainer().get(weaponKey, new WeaponStatsDataType());
          ArmorStats chestplateArmorStats = chestplateMeta.getPersistentDataContainer().get(armorKey, new ArmorStatsDataType());
          AbilityStats chestplateAbilityStats = chestplateMeta.getPersistentDataContainer().get(abilityKey, new AbilityStatsDataType());
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
    if(player.getInventory().getLeggings() != null) {
      if(player.getInventory().getLeggings().getItemMeta() != null) {
        ItemMeta leggingsMeta = player.getInventory().getLeggings().getItemMeta();
        if(leggingsMeta != null) {
          WeaponStats leggingsWeaponStats = leggingsMeta.getPersistentDataContainer().get(weaponKey, new WeaponStatsDataType());
          ArmorStats leggingsArmorStats = leggingsMeta.getPersistentDataContainer().get(armorKey, new ArmorStatsDataType());
          AbilityStats leggingsAbilityStats = leggingsMeta.getPersistentDataContainer().get(abilityKey, new AbilityStatsDataType());
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
    if(player.getInventory().getBoots() != null) {
      if(player.getInventory().getBoots().getItemMeta() != null) {
        ItemMeta bootsMeta = player.getInventory().getBoots().getItemMeta();
        if(bootsMeta != null) {
          WeaponStats bootsWeaponStats = bootsMeta.getPersistentDataContainer().get(weaponKey, new WeaponStatsDataType());
          ArmorStats bootsArmorStats = bootsMeta.getPersistentDataContainer().get(armorKey, new ArmorStatsDataType());
          AbilityStats bootsAbilityStats = bootsMeta.getPersistentDataContainer().get(abilityKey, new AbilityStatsDataType());
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
    if(player.getInventory().getItemInMainHand().getType() != Material.AIR) {
      if(player.getInventory().getItemInMainHand().getItemMeta() != null) {
        ItemMeta mainHandMeta = player.getInventory().getItemInMainHand().getItemMeta();
        if (mainHandMeta != null) {
          WeaponStats mainHandWeaponStats = mainHandMeta.getPersistentDataContainer().get(weaponKey, new WeaponStatsDataType());
          ArmorStats mainHandArmorStats = mainHandMeta.getPersistentDataContainer().get(armorKey, new ArmorStatsDataType());
          AbilityStats mainHandAbilityStats = mainHandMeta.getPersistentDataContainer().get(abilityKey, new AbilityStatsDataType());
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
    double healthPercent = player.getHealth() / player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
    player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(dataManager.getConfig().getDouble(player.getUniqueId() + ".health"));
    player.setHealthScale(40);
    player.setHealth(healthPercent * player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
    player.setWalkSpeed((float) dataManager.getConfig().getDouble(player.getUniqueId() + ".speed") / 500);
    showActionBar(player);
  }

  public static void naturalRegen(Player player) {
    double health = dataManager.getConfig().getDouble(player.getUniqueId() + ".health"), intelligence = dataManager.getConfig().getDouble(player.getUniqueId() + ".intelligence"), mana = dataManager.getConfig().getDouble(player.getUniqueId() + ".mana");
    if(!player.isDead()) {
      if(player.getHealth() != health) {
        double addHealth = Math.ceil((0.75 + (health * 0.005)) * 10) / 10;
        if((player.getHealth() + addHealth) >= health) {
          player.setHealth(health);
        } else {
          player.setHealth(player.getHealth() + addHealth);
        }
      }
    }
    if(mana != intelligence) {
      double addMana = intelligence * 0.02;
      if((mana + addMana) >= intelligence) {
        mana = intelligence;
      } else {
        mana += addMana;
      }
      dataManager.getConfig().set(player.getUniqueId() + ".mana", mana);
    }
  }

  public static void showActionBar(Player player) {
    double health = dataManager.getConfig().getDouble(player.getUniqueId() + ".health"), defense = dataManager.getConfig().getDouble(player.getUniqueId() + ".defense"), intelligence = dataManager.getConfig().getDouble(player.getUniqueId() + ".intelligence"), mana = dataManager.getConfig().getDouble(player.getUniqueId() + ".mana");
    NamedTextColor healthColor = NamedTextColor.RED;
    double currentAbsorption = player.getAbsorptionAmount();
    if(currentAbsorption != 0) {
      healthColor = NamedTextColor.GOLD;
    }
    player.sendActionBar(Component.text(Math.round(player.getHealth() + currentAbsorption) + "/" + Math.round(health) + "❤     ", healthColor).append(Component.text(Math.round(defense) + "❈ Defense     ", NamedTextColor.GREEN)).append(Component.text(Math.round(mana) + "/" + Math.round(intelligence) + "✎ Mana", NamedTextColor.AQUA)));
  }

  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    updateStats(event.getPlayer());
    dataManager.getConfig().set(event.getPlayer().getUniqueId() + ".mana", dataManager.getConfig().getDouble(event.getPlayer().getUniqueId() + ".intelligence"));
    showActionBar(event.getPlayer());
  }

  @EventHandler
  public void onArmorSwitch(PlayerArmorChangeEvent event) {
    updateStats(event.getPlayer());
  }

  @EventHandler
  public void onHandItemSwitch(PlayerItemHeldEvent event) {
    Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> updateStats(event.getPlayer()), 5);
  }

}