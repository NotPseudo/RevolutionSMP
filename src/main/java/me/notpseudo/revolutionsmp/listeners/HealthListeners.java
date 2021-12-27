package me.notpseudo.revolutionsmp.listeners;

import com.destroystokyo.paper.event.entity.EntityAddToWorldEvent;
import com.destroystokyo.paper.event.entity.EntityRemoveFromWorldEvent;
import io.papermc.paper.event.entity.EntityMoveEvent;
import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.datacontainers.WeaponStats;
import me.notpseudo.revolutionsmp.datacontainers.WeaponStatsDataType;
import me.notpseudo.revolutionsmp.datamanager.DataManager;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.concurrent.ConcurrentHashMap;

public class HealthListeners implements Listener {

  private final RevolutionSMP plugin;
  private DataManager dataManager;

  public HealthListeners(RevolutionSMP plugin) {
    this.plugin = plugin;
    dataManager = plugin.getDataManager();
    Bukkit.getPluginManager().registerEvents(this, this.plugin);
  }

  private final static NamespacedKey weaponKey = ItemEditor.getWeaponKey();
  private final ChatColor[] chatColors = {ChatColor.WHITE, ChatColor.GOLD, ChatColor.YELLOW, ChatColor.RED};
  private static ConcurrentHashMap<LivingEntity, ArmorStand> healthBars = new ConcurrentHashMap<>();
  private ConcurrentHashMap<LivingEntity, String> names = new ConcurrentHashMap<>();

  private double getRandomOffset() {
    double random = Math.random();
    if (Math.random() > 0.5) {
      random *= -1;
      random /= 2;
    }
    return random;
  }

  private void updateHealthBar(LivingEntity entity, int health) {
    ArmorStand healthBar = healthBars.get(entity);
    if(healthBar == null) {
      healthBar = (ArmorStand) entity.getWorld().spawnEntity(entity.getLocation().add(0, entity.getHeight(), 0), EntityType.ARMOR_STAND);
      healthBar.setInvisible(true);
      healthBar.setMarker(true);
      healthBar.setCustomNameVisible(true);
      healthBars.put(entity, healthBar);
    }
    String nameString = "" + ChatColor.RED + getName(entity);
    String healthString = "";
    double maxHealth = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
    if(health < maxHealth) {
      if(health < 0) {health = 0;}
      healthString = "" + ChatColor.YELLOW + health;
    } else {
      if(health > maxHealth) {health = (int) maxHealth;}
      healthString = "" + ChatColor.GREEN + health;
    }
    healthBar.setCustomName(nameString + healthString + ChatColor.GRAY + "/" + ChatColor.GREEN + Math.round(maxHealth) + ChatColor.RED + "❤");
  }

  private void showDamage(LivingEntity entity, double damage, boolean critical) {
    ArmorStand damageIndicator = (ArmorStand) entity.getWorld().spawnEntity(entity.getLocation().add(getRandomOffset(), 2 + getRandomOffset(), getRandomOffset()), EntityType.ARMOR_STAND);
    damageIndicator.setInvisible(true);
    damageIndicator.setGravity(false);
    damageIndicator.setMarker(true);
    String damageString = "" + ChatColor.GRAY + Math.round(damage);
    if(critical) {
      damageString = "✧" + Math.round(damage) + "✧";
      String[] critStringList = damageString.split("");
      String critString = "";
      for(int i= 0; i < critStringList.length; i++) {
        critString += chatColors[i % 4] + critStringList[i];
      }
      damageString = critString;
    }
    damageIndicator.setCustomName(damageString);
    damageIndicator.setCustomNameVisible(true);
    Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, damageIndicator::remove, 20);
  }

  private String getName(LivingEntity entity) {
    String name = entity.getCustomName();
    if(name == null) {
      String[] nameList = entity.getType().getKey().getKey().split("_");
      name = "";
      for (String word : nameList) {
        name += word.substring(0, 1).toUpperCase() + word.substring(1) + " ";
      }
    } else {
      name = entity.getCustomName() + " ";
    }
    names.put(entity, name);
    return name;
  }

  public static void clearHealthBars() {
    for(LivingEntity entity : healthBars.keySet()) {
      healthBars.get(entity).remove();
    }
  }

  @EventHandler
  public void onSpawn(EntityAddToWorldEvent event) {
    if(event.getEntity() instanceof LivingEntity && !(event.getEntity() instanceof ArmorStand) && !(event.getEntity() instanceof Player)) {
      LivingEntity entity = (LivingEntity) event.getEntity();
      int health = (int) Math.round(entity.getHealth());
      updateHealthBar(entity, health);
    }
  }

  @EventHandler
  public void onDespawn(EntityRemoveFromWorldEvent event) {
    if(event.getEntity() instanceof LivingEntity && !(event.getEntity() instanceof ArmorStand) && !(event.getEntity() instanceof Player)) {
      LivingEntity entity = (LivingEntity) event.getEntity();
      if(healthBars.get(entity) != null) {
        healthBars.get(entity).remove();
      }
      if(healthBars.containsKey(entity)) {
        healthBars.remove(entity);
      }
    }
  }

  @EventHandler
  public void onDamage(EntityDamageEvent event) {
    if(event.getEntity() instanceof LivingEntity && !(event.getEntity() instanceof ArmorStand)) {
      if(event instanceof EntityDamageByEntityEvent) {
        return;
      }
      LivingEntity entity = (LivingEntity) event.getEntity();
      double finalDamage = event.getDamage(), defense, actualDamagePercent = 1;
      if(entity instanceof Player) {
        Player player = (Player) entity;
        defense = dataManager.getConfig().getDouble(player.getUniqueId() + ".defense");
        actualDamagePercent = 1 - (defense / (defense + 100));
      }
      finalDamage *= actualDamagePercent;
      event.setDamage(finalDamage);
      if(!(entity instanceof Player)) {
        int health = (int) Math.round(entity.getHealth() - finalDamage);
        updateHealthBar(entity, health);
      } else {
        StatsListeners.showActionBar((Player) entity);
      }
    }
  }

  @EventHandler
  public void onRegen(EntityRegainHealthEvent event) {
    if(event.getEntity() instanceof LivingEntity && !(event.getEntity() instanceof ArmorStand)) {
      LivingEntity entity = (LivingEntity) event.getEntity();
      int health = (int) Math.round(entity.getHealth() + event.getAmount());
      if(!(entity instanceof Player)) {
        updateHealthBar(entity, health);
      } else {
        StatsListeners.showActionBar((Player) entity);
      }
    }
  }

  @EventHandler
  public void onDamageEntity(EntityDamageByEntityEvent event) {
    if(event.getEntity() instanceof LivingEntity && !(event.getEntity() instanceof ArmorStand)) {
      LivingEntity entity = (LivingEntity) event.getEntity();
      double weaponDamage = event.getDamage(), strength = 0, critDamage = 50, critChance = 30, defense, actualDamagePercent = 1;
      Player player = null;
      if(event.getDamager() instanceof Player) {
        player = (Player) event.getDamager();
      }
      if(event.getDamager() instanceof Arrow) {
        if(((Arrow) event.getDamager()).getShooter() instanceof Player) {
          player = (Player) ((Arrow) event.getDamager()).getShooter();
        }
      }
      if(player != null) {
        strength = dataManager.getConfig().getDouble(player.getUniqueId() + ".strength");
        critDamage = dataManager.getConfig().getDouble(player.getUniqueId() + ".critDamage");
        critChance = dataManager.getConfig().getDouble(player.getUniqueId() + ".critChance");
        if(player.getInventory().getItemInMainHand() != null) {
          if(player.getInventory().getItemInMainHand().getItemMeta() != null) {
            ItemMeta mainHandMeta = player.getInventory().getItemInMainHand().getItemMeta();
            if(mainHandMeta != null) {
              WeaponStats mainHandWeaponStats = mainHandMeta.getPersistentDataContainer().get(weaponKey, new WeaponStatsDataType());
              if(mainHandWeaponStats != null) {
                weaponDamage += mainHandWeaponStats.getDamage();
              }
            }
          }
        }
      }
      double randomCrit = Math.random() * 100;
      boolean critical = randomCrit <= critChance;
      if(!critical) {
        critDamage = 0;
      }
      double finalDamage = (weaponDamage * (1 + (strength / 100))) * (1 + (critDamage / 100));
      if(entity instanceof Player) {
        Player damagedPlayer = (Player) entity;
        defense = dataManager.getConfig().getDouble(damagedPlayer.getUniqueId() + ".defense");
        actualDamagePercent = 1 - (defense / (defense + 100));
      }
      finalDamage *= actualDamagePercent;
      event.setDamage(finalDamage);
      if(!(entity instanceof Player)) {
        int health = (int) Math.round(entity.getHealth() - finalDamage);
        updateHealthBar(entity, health);
      } else {
        StatsListeners.showActionBar((Player) entity);
      }
      showDamage(entity, finalDamage, critical);
    }
  }

  @EventHandler
  public void onEntityMove(EntityMoveEvent event) {
    if(!(event.getEntity() instanceof Player) && event.hasChangedPosition()) {
      LivingEntity entity = event.getEntity();
      ArmorStand healthBar = healthBars.get(entity);
      if(healthBar != null) {
        healthBar.teleport(entity.getLocation().add(0, entity.getHeight(), 0));
      }
    }
  }

}
