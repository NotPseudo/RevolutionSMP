package me.notpseudo.revolutionsmp.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.reflect.FieldAccessException;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import com.destroystokyo.paper.event.entity.EntityAddToWorldEvent;
import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.datacontainers.WeaponStats;
import me.notpseudo.revolutionsmp.datacontainers.WeaponStatsDataType;
import me.notpseudo.revolutionsmp.datamanager.DataManager;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class HealthListeners implements Listener {

  private static RevolutionSMP plugin = RevolutionSMP.getPlugin();
  private DataManager dataManager;
  private static int indicatorCount = 0;

  public HealthListeners(RevolutionSMP plugin) {
    this.plugin = plugin;
    dataManager = plugin.getDataManager();
    Bukkit.getPluginManager().registerEvents(this, this.plugin);
    Bukkit.getScheduler().scheduleSyncRepeatingTask(this.plugin, () -> {
      for(Player player : Bukkit.getOnlinePlayers()) {
        for(Entity entity : player.getNearbyEntities(15, 10, 15)) {
          if(entity instanceof LivingEntity && !(entity instanceof ArmorStand)) {
            updateHealthBar((LivingEntity) entity, (int) Math.round(((LivingEntity) entity).getHealth()), player);
          }
        }
      }
    }, 100, 15);
  }

  private final static NamespacedKey weaponKey = ItemEditor.getWeaponKey();
  private final ChatColor[] chatColors = {ChatColor.WHITE, ChatColor.GOLD, ChatColor.YELLOW, ChatColor.RED};
  private static ConcurrentHashMap<LivingEntity, String> names = new ConcurrentHashMap<>();

  private double getRandomOffset() {
    double random = Math.random();
    if (Math.random() > 0.5) {
      random *= -1;
      random /= 2;
    }
    return random;
  }

  private static int getNextIndicatorCount() {
    indicatorCount++;
    return indicatorCount - 1;
  }

  public static void updateHealthBar(LivingEntity entity, int health) {
    String nameString = "" + ChatColor.RED + getName(entity);
    String healthString;
    double maxHealth = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
    if(health < maxHealth) {
      if(health < 0) {health = 0;}
      healthString = "" + ChatColor.YELLOW + health;
    } else {
      if(health > maxHealth) {health = (int) maxHealth;}
      healthString = "" + ChatColor.GREEN + health;
    }
    String healthBarString = nameString + healthString + ChatColor.GRAY + "/" + ChatColor.GREEN + Math.round(maxHealth) + ChatColor.RED + "❤";
    WrappedDataWatcher dataWatcher = WrappedDataWatcher.getEntityWatcher(entity).deepClone();
    WrappedDataWatcher.Serializer chatSerializer = WrappedDataWatcher.Registry.getChatComponentSerializer(true);
    WrappedDataWatcher.WrappedDataWatcherObject watcherObject = new WrappedDataWatcher.WrappedDataWatcherObject(2, chatSerializer);
    Optional<Object> optional = Optional.of(WrappedChatComponent.fromChatMessage(healthBarString)[0].getHandle());
    dataWatcher.setObject(watcherObject, optional);
    dataWatcher.setObject(3, true);
    PacketContainer packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.ENTITY_METADATA);
    packet.getWatchableCollectionModifier().write(0, dataWatcher.getWatchableObjects());
    packet.getIntegers().write(0, entity.getEntityId());
    try {
      ProtocolLibrary.getProtocolManager().broadcastServerPacket(packet);
    } catch (FieldAccessException e) {
      plugin.getLogger().severe("Unable to update Health Bar packet! Stack trace:");
      e.printStackTrace();
    }
  }

  public static void updateHealthBar(LivingEntity entity, int health, Player player) {
    String nameString = "" + ChatColor.RED + getName(entity);
    String healthString;
    double maxHealth = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
    if(health < maxHealth) {
      if(health < 0) {health = 0;}
      healthString = "" + ChatColor.YELLOW + health;
    } else {
      if(health > maxHealth) {health = (int) maxHealth;}
      healthString = "" + ChatColor.GREEN + health;
    }
    String healthBarString = nameString + healthString + ChatColor.GRAY + "/" + ChatColor.GREEN + Math.round(maxHealth) + ChatColor.RED + "❤";
    WrappedDataWatcher dataWatcher = WrappedDataWatcher.getEntityWatcher(entity).deepClone();
    WrappedDataWatcher.Serializer chatSerializer = WrappedDataWatcher.Registry.getChatComponentSerializer(true);
    WrappedDataWatcher.WrappedDataWatcherObject watcherObject = new WrappedDataWatcher.WrappedDataWatcherObject(2, chatSerializer);
    Optional<Object> optional = Optional.of(WrappedChatComponent.fromChatMessage(healthBarString)[0].getHandle());
    dataWatcher.setObject(watcherObject, optional);
    dataWatcher.setObject(3, true);
    PacketContainer packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.ENTITY_METADATA);
    packet.getWatchableCollectionModifier().write(0, dataWatcher.getWatchableObjects());
    packet.getIntegers().write(0, entity.getEntityId());
    try {
      ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
    } catch (InvocationTargetException e) {
      plugin.getLogger().severe("Unable to update Health Bar packet for player " + player.getName() + "! Stack trace:");
      e.printStackTrace();
    }
  }

  private void showDamage(LivingEntity entity, double damage, boolean critical) {
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
    int entityID = getNextIndicatorCount();
    UUID uuid = UUID.randomUUID();
    PacketContainer spawnPacket = new PacketContainer(PacketType.Play.Server.SPAWN_ENTITY);
    spawnPacket.getIntegers().write(0, entityID);
    spawnPacket.getUUIDs().write(0, uuid);
    spawnPacket.getEntityTypeModifier().write(0, EntityType.ARMOR_STAND);
    spawnPacket.getDoubles().write(0, entity.getLocation().getX() + getRandomOffset());
    spawnPacket.getDoubles().write(1, entity.getLocation().getY() + entity.getHeight() + getRandomOffset());
    spawnPacket.getDoubles().write(2, entity.getLocation().getZ() + getRandomOffset());
    PacketContainer editPacket = new PacketContainer(PacketType.Play.Server.ENTITY_METADATA);
    WrappedDataWatcher dataWatcher = new WrappedDataWatcher();
    WrappedDataWatcher.Serializer chatSerializer = WrappedDataWatcher.Registry.getChatComponentSerializer(true);
    WrappedDataWatcher.WrappedDataWatcherObject watcherObject = new WrappedDataWatcher.WrappedDataWatcherObject(2, chatSerializer);
    Optional<Object> optional = Optional.of(WrappedChatComponent.fromChatMessage(damageString)[0].getHandle());
    dataWatcher.setObject(watcherObject, optional);
    dataWatcher.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(0, WrappedDataWatcher.Registry.get(Byte.class)), (byte) 0x20); //invisible
    dataWatcher.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(5, WrappedDataWatcher.Registry.get(Boolean.class)), true); //noGravity
    dataWatcher.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(15, WrappedDataWatcher.Registry.get(Byte.class)), (byte) (0x01 | 0x08 | 0x10)); //isSmall, noBasePlate, set Marker
    dataWatcher.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(3, WrappedDataWatcher.Registry.get(Boolean.class)), true); //customNameVisible
    editPacket.getWatchableCollectionModifier().write(0, dataWatcher.getWatchableObjects());
    editPacket.getIntegers().write(0, entityID);
    List<Integer> entityIDList = Arrays.asList(entityID);
    PacketContainer removePacket = new PacketContainer(PacketType.Play.Server.ENTITY_DESTROY);
    removePacket.getIntLists().write(0, entityIDList);
    try {
      ProtocolLibrary.getProtocolManager().broadcastServerPacket(spawnPacket);
      ProtocolLibrary.getProtocolManager().broadcastServerPacket(editPacket);
      Bukkit.getScheduler().runTaskLaterAsynchronously(this.plugin, () -> {
        try {
          ProtocolLibrary.getProtocolManager().broadcastServerPacket(removePacket);
        } catch (FieldAccessException e) {
          e.printStackTrace();
        }}, 20);
    } catch (FieldAccessException e) {
      plugin.getLogger().severe("Unable to update Damage Indicator Edit packet for player! Stack trace:");
      e.printStackTrace();
    }
  }

  private static String getName(LivingEntity entity) {
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

  @EventHandler
  public void onSpawn(EntityAddToWorldEvent event) {
    if(event.getEntity() instanceof LivingEntity && !(event.getEntity() instanceof ArmorStand) && !(event.getEntity() instanceof Player)) {
      LivingEntity entity = (LivingEntity) event.getEntity();
      int health = (int) Math.round(entity.getHealth());
      updateHealthBar(entity, health);
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

}
