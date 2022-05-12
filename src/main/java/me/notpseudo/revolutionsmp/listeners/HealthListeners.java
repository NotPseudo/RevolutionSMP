package me.notpseudo.revolutionsmp.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.reflect.FieldAccessException;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.itemstats.ItemInfo;
import me.notpseudo.revolutionsmp.itemstats.ItemInfoDataType;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.mobstats.MobInfo;
import me.notpseudo.revolutionsmp.mobstats.MobInfoDataType;
import org.bson.Document;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * This class holds listeners that handle how entities take damage and attack
 */
public class HealthListeners implements Listener {

  /**
   * Holds an instance of the plugin
   */
  private static RevolutionSMP plugin = RevolutionSMP.getPlugin();
  /**
   * The NamedspacedKey from the MobListeners class used to access MobInfo stored in Persistent Data
   */
  private final static NamespacedKey mobKey = MobListeners.getMobKey();
  private MongoDatabase playerDatabase;
  /**
   * Number to represent the next available Entity ID used for creating custom armor stands with packets
   */
  private static int indicatorCount = 999999;

  /**
   * List of ChatColors used to decorate critical damage Strings
   */
  private final ChatColor[] chatColors = {ChatColor.WHITE, ChatColor.GOLD, ChatColor.YELLOW, ChatColor.RED};

  /**
   * Instantiates a new HealthListeners object to allow the listeners to work<p>Starts a repeating task to show health bars of mobs each second</p>
   * @param plugin Instance of the plugin
   */
  public HealthListeners(RevolutionSMP plugin) {
    HealthListeners.plugin = plugin;
    Bukkit.getPluginManager().registerEvents(this, HealthListeners.plugin);
    Bukkit.getScheduler().scheduleSyncRepeatingTask(HealthListeners.plugin, () -> {
      // Every 15 ticks, for every player, get LivingEntities in specified radius and show their health bars
      for(Player player : Bukkit.getOnlinePlayers()) {
        for(Entity entity : player.getNearbyEntities(15, 10, 15)) {
          if(entity instanceof LivingEntity && !(entity instanceof ArmorStand)) {
            if(entity.getPersistentDataContainer().get(mobKey, new MobInfoDataType()) != null) {
              updateHealthBar((LivingEntity) entity, (int) Math.round((entity.getPersistentDataContainer().get(mobKey, new MobInfoDataType()).getCurrentHealth())), player);
            }
          }
        }
      }
    }, 100, 15);
  }

  /**
   * Generates a random number from -0.5 to 0.5
   * @return The random number
   */
  private double getRandomOffset() {
    return ((int) (Math.random() * 2) - 0.5) / 2.0;
  }

  /**
   * Gets the next available Entity ID to create a custom armor stand with packets
   * @return The available Entity ID
   */
  private static int getNextIndicatorCount() {
    indicatorCount++;
    return indicatorCount - 1;
  }

  /**
   * Edits the specified LivingEntity's health bar with packets for all players
   * @param entity The LivingEntity to update the health bar for
   * @param health The health value to display on the health bar
   */
  public static void updateHealthBar(LivingEntity entity, int health) {
    MobInfo mobInfo = entity.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
    if(mobInfo == null) return;
    // Makes the LivingEntity's name red
    String nameString = "" + mobInfo.getMobBehavior().getBehaviorColor() + mobInfo.getName();
    String healthString;
    // Gets max Health of the LivingEntity
    double maxHealth = mobInfo.getMaxHealth();
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
    String healthBarString = ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv" + mobInfo.getLevel() + ChatColor.DARK_GRAY + "] " + nameString + " " + healthString + ChatColor.GRAY + "/" + ChatColor.GREEN + Math.round(maxHealth) + ChatColor.RED + "❤";
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
      plugin.getLogger().severe("Unable to update Health Bar packet! Stack trace:" + e);
    }
  }

  /**
   * Edits the specified LivingEntity's health bar with packets for a specific player
   * @param entity The LivingEntity to update the health bar for
   * @param health The health value to display on the health bar
   * @param player The Player to show the health bar to
   */
  public static void updateHealthBar(LivingEntity entity, int health, Player player) {
    MobInfo mobInfo = entity.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
    if(mobInfo == null) return;
    String nameString = "" + mobInfo.getMobBehavior().getBehaviorColor() + mobInfo.getName();
    String healthString;
    double maxHealth = mobInfo.getMaxHealth();
    if(health < maxHealth / 2) {
      if(health < 0) {health = 0;}
      healthString = "" + ChatColor.YELLOW + health;
    } else {
      if(health > maxHealth) {health = (int) maxHealth;}
      healthString = "" + ChatColor.GREEN + health;
    }
    String healthBarString = ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv" + mobInfo.getLevel() + ChatColor.DARK_GRAY + "] " + nameString + " " + healthString + ChatColor.GRAY + "/" + ChatColor.GREEN + Math.round(maxHealth) + ChatColor.RED + "❤";
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
      plugin.getLogger().severe("Unable to update Health Bar packet for player " + player.getName() + "! Stack trace:" + e);
    }
  }

  /**
   * Creates and shows a Damage Indicator with custom armor stand packets
   * @param entity The LivingEntity to spawn the Damage Indicator by
   * @param damage The amount of damage dealt and to be shown
   * @param critical Represents if the damage was a critical hit or not
   */
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

  /**
   * Handles damage taken when a LivingEntity takes damage not caused by an Entity
   * @param event The Event fired when an Entity takes damage
   */
  @EventHandler
  public void onDamage(EntityDamageEvent event) {
    if(event.getEntity() instanceof LivingEntity && !(event.getEntity() instanceof ArmorStand)) {
      if(event instanceof EntityDamageByEntityEvent) {
        return;
      }
      LivingEntity entity = (LivingEntity) event.getEntity();
      if(entity instanceof  Player) return;
      MobInfo mobInfo = entity.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
      if(mobInfo == null) return;
      int health = (int) Math.round(mobInfo.getCurrentHealth() - event.getDamage());
      mobInfo.setCurrentHealth(health);
      entity.getPersistentDataContainer().set(mobKey, new MobInfoDataType(), mobInfo);
      updateHealthBar(entity, health);
    }
  }

  /**
   * Updates health bar when a LivingEntity regenerates health
   * @param event The Event fired when an Entity regains health
   */
  @EventHandler
  public void onRegen(EntityRegainHealthEvent event) {
    if(event.getEntity() instanceof LivingEntity && !(event.getEntity() instanceof ArmorStand)) {
      LivingEntity entity = (LivingEntity) event.getEntity();
      if(entity instanceof Player) return;
      MobInfo mobInfo = entity.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
      if(mobInfo == null) return;
      int health = (int) Math.round(mobInfo.getCurrentHealth() + event.getAmount());
      mobInfo.setCurrentHealth(health);
      entity.getPersistentDataContainer().set(mobKey, new MobInfoDataType(), mobInfo);
      updateHealthBar(entity, health);
    }
  }

  /**
   * Handles damage when a LivingEntity is attacked by another Entity
   * @param event The Event fired when an Entity takes damage from an Entity
   */
  @EventHandler
  public void onDamageEntity(EntityDamageByEntityEvent event) {
    if(event.getEntity() instanceof LivingEntity && !(event.getEntity() instanceof ArmorStand)) {
      LivingEntity entity = (LivingEntity) event.getEntity();
      // Sets base stat values
      double weaponDamage = event.getDamage(), strength = 0, critDamage = 50, critChance = 30, defense, actualDamagePercent = 1, ferocity = 0;
      Player player = null;
      Document playerStats;
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
        playerStats = playerDatabase.getCollection("" + player.getUniqueId()).find(new Document("docType", "PLAYERSTATS")).first();
        strength = playerStats.getDouble("strength");
        critDamage = playerStats.getDouble("critDamage");
        critChance = playerStats.getDouble("critChance");
        ferocity = playerStats.getDouble("ferocity");
        if(player.getInventory().getItemInMainHand().getType() != Material.AIR && player.getInventory().getItemInMainHand().getItemMeta() != null) {
          ItemMeta mainHandMeta = player.getInventory().getItemInMainHand().getItemMeta();
          if(mainHandMeta != null) {
            ItemInfo mainHandInfo = mainHandMeta.getPersistentDataContainer().get(ItemEditor.getItemKey(), new ItemInfoDataType());
            if(mainHandInfo != null) {
              weaponDamage += mainHandInfo.getWeaponStats().getDamage();
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
        defense = playerDatabase.getCollection("" + damagedPlayer.getUniqueId()).find(new Document("docType", "PLAYERSTATS")).first().getDouble("defense");
        actualDamagePercent = 1 - (defense / (defense + 100));
      }
      // Adjust the final damage and set it
      MobInfo mobInfo = entity.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
      finalDamage *= actualDamagePercent;
      double actual = finalDamage / mobInfo.getMaxHealth() * entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
      event.setDamage(actual);
      showDamage(entity, finalDamage, critical);
      if(mobInfo != null) {
        event.getDamager().sendMessage("MobInfo Current Health: " + mobInfo.getCurrentHealth());
        event.getDamager().sendMessage("MobInfo Max Health: " + mobInfo.getMaxHealth());
        event.getDamager().sendMessage("Mob Current Health: " + entity.getHealth());
        event.getDamager().sendMessage("Mob Max Health: " + entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
        if(!(entity instanceof Player)) {
          int health = (int) Math.round(mobInfo.getCurrentHealth() - finalDamage);
          mobInfo.setCurrentHealth(health);
          entity.getPersistentDataContainer().set(mobKey, new MobInfoDataType(), mobInfo);
          updateHealthBar(entity, health);
        }
      }
      if(player != null && ferocity > 0) {
        ferocityAttack(player, entity, actual, ferocity, critical);
      }
    }
  }

  /**
   * Repeatedly damages the target based on the attacker's ferocity
   * @param damager The attacking Player
   * @param target The LivingEntity to be attacked
   * @param damage The amount of damage originally dealt
   * @param ferocity The ferocity stat of the attacker
   * @param critical Represents if the damage was a critical hit or not
   */
  private void ferocityAttack(Player damager, LivingEntity target, double damage, double ferocity, boolean critical) {
    int hits = (int) (ferocity / 100);
    double random = Math.random();
    double extraFerocityChance = ((ferocity % 100) / 100);
    if(random < extraFerocityChance) {
      hits++;
    }
    final int[] count = {0};
    int finalHits = hits;
    BukkitRunnable feroHit = new BukkitRunnable() {
      @Override
      public void run() {
        count[0]++;
        if(count[0] > finalHits) {
          this.cancel();
        } else {
          if(!target.isDead()) {
            target.setMaximumNoDamageTicks(0);
            target.setNoDamageTicks(0);
            target.damage(damage);
            showDamage(target, damage, critical);
            damager.playSound(damager, Sound.ITEM_FLINTANDSTEEL_USE, 2f, 0.605f);
          } else {
            count[0] = finalHits + 1;
          }
        }
      }
    };
    feroHit.runTaskTimer(plugin, 2, 1);
  }

}