package me.notpseudo.revolutionsmp.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.reflect.FieldAccessException;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import com.destroystokyo.paper.event.entity.EntityAddToWorldEvent;
import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.statobjects.ItemInfoDataType;
import me.notpseudo.revolutionsmp.statobjects.WeaponStats;
import me.notpseudo.revolutionsmp.datamanager.DataManager;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class HealthListeners implements Listener {

  // Instance of main plugin class, DataManager to access config files, indicatorCount to make entity IDs for Damage Indicators
  private static RevolutionSMP plugin = RevolutionSMP.getPlugin();
  private final DataManager dataManager;
  private static int indicatorCount = 0;

  public HealthListeners(RevolutionSMP plugin) {
    HealthListeners.plugin = plugin;
    dataManager = plugin.getDataManager();
    Bukkit.getPluginManager().registerEvents(this, HealthListeners.plugin);
    Bukkit.getScheduler().scheduleSyncRepeatingTask(HealthListeners.plugin, () -> {
      // Every 15 ticks, for every player, get LivingEntities in specified radius and show their health bars
      for(Player player : Bukkit.getOnlinePlayers()) {
        for(Entity entity : player.getNearbyEntities(15, 10, 15)) {
          if(entity instanceof LivingEntity && !(entity instanceof ArmorStand)) {
            updateHealthBar((LivingEntity) entity, (int) Math.round(((LivingEntity) entity).getHealth()), player);
          }
        }
      }
    }, 100, 15);
  }

  // NamespacedKey to access item stats, list of colors for critical Damage Indicator, HashMap for every LivingEntity and its name
  private final ChatColor[] chatColors = {ChatColor.WHITE, ChatColor.GOLD, ChatColor.YELLOW, ChatColor.RED};
  private static final ConcurrentHashMap<LivingEntity, String> names = new ConcurrentHashMap<>();

  // Generates random offset for location coordinates
  private double getRandomOffset() {
    double random = Math.random();
    if (Math.random() > 0.5) {
      random *= -1;
      random /= 2;
    }
    return random;
  }

  // Gets next entity ID to create a Damage Indicator
  private static int getNextIndicatorCount() {
    indicatorCount++;
    return indicatorCount - 1;
  }

  // Edits the LivingEntity's health bar
  public static void updateHealthBar(LivingEntity entity, int health) {
    // Makes the LivingEntity's name red
    String nameString = "" + ChatColor.RED + getName(entity);
    String healthString;
    // Gets max Health of the LivingEntity
    double maxHealth = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
    if(health < maxHealth) {
      // If the current Health is not full
      if(health < 0) {health = 0;} // If current Health is negative, display it as 0
      // Not-full health is displayed as yellow
      healthString = "" + ChatColor.YELLOW + health;
    } else {
      if(health > maxHealth) {health = (int) maxHealth;} // If current Health exceeds max Health, display it as max Health
      // Full health is displayed as Green
      healthString = "" + ChatColor.GREEN + health;
    }
    // Health bar shows name and current Health out of max Health
    String healthBarString = nameString + healthString + ChatColor.GRAY + "/" + ChatColor.GREEN + Math.round(maxHealth) + ChatColor.RED + "❤";
    // Working with packets using ProtocolLib
    WrappedDataWatcher dataWatcher = WrappedDataWatcher.getEntityWatcher(entity).deepClone();
    WrappedDataWatcher.Serializer chatSerializer = WrappedDataWatcher.Registry.getChatComponentSerializer(true);
    WrappedDataWatcher.WrappedDataWatcherObject watcherObject = new WrappedDataWatcher.WrappedDataWatcherObject(2, chatSerializer);
    Optional<Object> optional = Optional.of(WrappedChatComponent.fromChatMessage(healthBarString)[0].getHandle());
    // Sets entity data's custom name as the healthBarString with a chat serializer that reads the healthBarString
    dataWatcher.setObject(watcherObject, optional);
    // Allows the entity's custom name to be visible
    dataWatcher.setObject(3, true);
    // Creates new Entity Metadata or info packet to be sent
    PacketContainer packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.ENTITY_METADATA);
    // The data with the custom name is added to the packet
    packet.getWatchableCollectionModifier().write(0, dataWatcher.getWatchableObjects());
    // The entity this packet refers to is the entity's health bar we want to update or display
    packet.getIntegers().write(0, entity.getEntityId());
    try {
      // Tries to send this info packet or health bar to all players
      ProtocolLibrary.getProtocolManager().broadcastServerPacket(packet);
    } catch (FieldAccessException e) {
      // Prints stack trace in console if an error occurs
      plugin.getLogger().severe("Unable to update Health Bar packet! Stack trace:");
      e.printStackTrace();
    }
  }

  // Edits the LivingEntity's health bar but shows only to the specified Player
  // Very similar to above overloaded method
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
      // Tries to send this info packet or health bar to specified Player
      ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
    } catch (InvocationTargetException e) {
      // Prints stack trace in console if the packet could not be sent to the Player
      plugin.getLogger().severe("Unable to update Health Bar packet for player " + player.getName() + "! Stack trace:");
      e.printStackTrace();
    }
  }

  // Shows a Damage Indicator
  private void showDamage(LivingEntity entity, double damage, boolean critical) {
    // Puts the damage dealt into a gray message
    String damageString = "" + ChatColor.GRAY + Math.round(damage);
    if(critical) {
      // If it was a critical hit, add stars before and after
      damageString = "✧" + Math.round(damage) + "✧";
      // Adds colors to critical damage message
      String[] critStringList = damageString.split("");
      StringBuilder critString = new StringBuilder();
      for(int i= 0; i < critStringList.length; i++) {
        critString.append(chatColors[i % 4]).append(critStringList[i]);
      }
      damageString = critString.toString();
    }
    // Generates entity ID and unique ID for packet
    int entityID = getNextIndicatorCount();
    UUID uuid = UUID.randomUUID();
    // Creates a new Spawn Entity packet to be sent. This is a fake packet that will trick clients that an invisible Armor Stand spawned
    PacketContainer spawnPacket = new PacketContainer(PacketType.Play.Server.SPAWN_ENTITY);
    // Packet will refer to unique entity
    spawnPacket.getIntegers().write(0, entityID);
    spawnPacket.getUUIDs().write(0, uuid);
    // Make the entity that spawns an Armor Stand
    spawnPacket.getEntityTypeModifier().write(0, EntityType.ARMOR_STAND);
    // Sets the X, Y, and Z location coordinates for the Damage Indicator
    spawnPacket.getDoubles().write(0, entity.getLocation().getX() + getRandomOffset());
    spawnPacket.getDoubles().write(1, entity.getLocation().getY() + entity.getHeight() + getRandomOffset());
    spawnPacket.getDoubles().write(2, entity.getLocation().getZ() + getRandomOffset());
    // Creates a new Entity Metadata packet to be sent. This fake packet will trick clients that the invisible armor stand's name is the damageString
    PacketContainer editPacket = new PacketContainer(PacketType.Play.Server.ENTITY_METADATA);
    WrappedDataWatcher dataWatcher = new WrappedDataWatcher();
    WrappedDataWatcher.Serializer chatSerializer = WrappedDataWatcher.Registry.getChatComponentSerializer(true);
    WrappedDataWatcher.WrappedDataWatcherObject watcherObject = new WrappedDataWatcher.WrappedDataWatcherObject(2, chatSerializer);
    Optional<Object> optional = Optional.of(WrappedChatComponent.fromChatMessage(damageString)[0].getHandle());
    // Sets entity data's custom name as the damageString with a chat serializer that reads the damageString
    dataWatcher.setObject(watcherObject, optional);
    dataWatcher.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(0, WrappedDataWatcher.Registry.get(Byte.class)), (byte) 0x20); // Invisible
    dataWatcher.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(5, WrappedDataWatcher.Registry.get(Boolean.class)), true); // noGravity
    dataWatcher.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(15, WrappedDataWatcher.Registry.get(Byte.class)), (byte) (0x01 | 0x08 | 0x10)); // isSmall, noBasePlate, set Marker
    dataWatcher.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(3, WrappedDataWatcher.Registry.get(Boolean.class)), true); // customNameVisible
    // Puts the data into the packet
    editPacket.getWatchableCollectionModifier().write(0, dataWatcher.getWatchableObjects());
    // Makes the packet edit the invisible armor stand that was spawned
    editPacket.getIntegers().write(0, entityID);
    List<Integer> entityIDList = List.of(entityID);
    // Makes an Entity Destroy packet to remove the invisible armor stand
    PacketContainer removePacket = new PacketContainer(PacketType.Play.Server.ENTITY_DESTROY);
    // Makes the packet remove the invisible armor stand that was created and edited
    removePacket.getIntLists().write(0, entityIDList);
    try {
      // Try to send the spawn and edit packet
      ProtocolLibrary.getProtocolManager().broadcastServerPacket(spawnPacket);
      ProtocolLibrary.getProtocolManager().broadcastServerPacket(editPacket);
      Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {
        try {
          // After 20 game ticks, try to send the remove packet
          ProtocolLibrary.getProtocolManager().broadcastServerPacket(removePacket);
        } catch (FieldAccessException e) {
          e.printStackTrace();
        }}, 20);
    } catch (FieldAccessException e) {
      plugin.getLogger().severe("Unable to update Damage Indicator Edit packet for player! Stack trace:");
      e.printStackTrace();
    }
  }

  // Gets and sets LivingEntity's name
  private static String getName(LivingEntity entity) {
    String name = "";
    if(entity.getCustomName() == null) {
      String[] nameList = entity.getType().getKey().getKey().split("_");
      for (String word : nameList) {
        name += word.substring(0, 1).toUpperCase() + word.substring(1) + " ";
      }
    } else {
      name = entity.getCustomName() + " ";
    }
    names.put(entity, name);
    return name;
  }

  // When a LivingEntity is added to the world for any reason
  @EventHandler
  public void onSpawn(EntityAddToWorldEvent event) {
    if(event.getEntity() instanceof LivingEntity && !(event.getEntity() instanceof ArmorStand) && !(event.getEntity() instanceof Player)) {
      LivingEntity entity = (LivingEntity) event.getEntity();
      int health = (int) Math.round(entity.getHealth());
      updateHealthBar(entity, health);
    }
  }

  // WHen a LivingEntity takes damage not caused by another entity
  @EventHandler
  public void onDamage(EntityDamageEvent event) {
    if(event.getEntity() instanceof LivingEntity && !(event.getEntity() instanceof ArmorStand)) {
      if(event instanceof EntityDamageByEntityEvent) {
        return;
      }
      LivingEntity entity = (LivingEntity) event.getEntity();
      if(!(entity instanceof Player)) {
        int health = (int) Math.round(entity.getHealth() - event.getDamage());
        updateHealthBar(entity, health);
      } else {
        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> StatsListeners.showActionBar((Player) entity), 5);
      }
    }
  }

  // When a LivingEntity regenerates Health
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

  // When a LivingEntity gets attacked by another Entity
  @EventHandler
  public void onDamageEntity(EntityDamageByEntityEvent event) {
    if(event.getEntity() instanceof LivingEntity && !(event.getEntity() instanceof ArmorStand)) {
      LivingEntity entity = (LivingEntity) event.getEntity();
      // Sets base stat values
      double weaponDamage = event.getDamage(), strength = 0, critDamage = 50, critChance = 30, defense, actualDamagePercent = 1;
      Player player = null;
      if(event.getDamager() instanceof Player) {
        // If the attacker is a player
        player = (Player) event.getDamager();
      }
      if(event.getDamager() instanceof Arrow) {
        if(((Arrow) event.getDamager()).getShooter() instanceof Player) {
          // If a player-shot arrow caused the damage
          player = (Player) ((Arrow) event.getDamager()).getShooter();
        }
      }
      if(player != null) {
        // Gets damage stats from the player
        strength = dataManager.getConfig().getDouble(player.getUniqueId() + ".strength");
        critDamage = dataManager.getConfig().getDouble(player.getUniqueId() + ".critDamage");
        critChance = dataManager.getConfig().getDouble(player.getUniqueId() + ".critChance");
        if(player.getInventory().getItemInMainHand().getType() != Material.AIR && player.getInventory().getItemInMainHand().getItemMeta() != null) {
          ItemMeta mainHandMeta = player.getInventory().getItemInMainHand().getItemMeta();
          if(mainHandMeta != null) {
            WeaponStats mainHandWeaponStats = mainHandMeta.getPersistentDataContainer().get(ItemEditor.getItemKey(), new ItemInfoDataType()).getWeaponStats();
            if(mainHandWeaponStats != null) {
              weaponDamage += mainHandWeaponStats.getDamage();
            }
          }
        }
      }
      // Determine if the hit should be critical
      double randomCrit = Math.random() * 100;
      boolean critical = randomCrit <= critChance;
      if(!critical) {
        critDamage = 0;
      }
      // Damage calculation
      double finalDamage = (weaponDamage * (1 + (strength / 100))) * (1 + (critDamage / 100));
      if(entity instanceof Player) {
        // If the damaged LivingEntity is a player, get their Defense
        Player damagedPlayer = (Player) entity;
        defense = dataManager.getConfig().getDouble(damagedPlayer.getUniqueId() + ".defense");
        actualDamagePercent = 1 - (defense / (defense + 100));
      }
      // Adjust the final damage and set it
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
