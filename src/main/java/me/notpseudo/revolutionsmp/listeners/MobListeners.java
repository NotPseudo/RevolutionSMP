package me.notpseudo.revolutionsmp.listeners;

import com.destroystokyo.paper.event.entity.EntityAddToWorldEvent;
import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.mobstats.*;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.EntityTransformEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class MobListeners implements Listener {

    // NamespacedKey to access PersistentData
    private final static NamespacedKey mobKey = new NamespacedKey(RevolutionSMP.getPlugin(RevolutionSMP.class), "mobs");

    // Instance of main plugin
    private final RevolutionSMP plugin;

    public MobListeners(RevolutionSMP plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    // Returns NamespacedKey for other classes to use
    public static NamespacedKey getMobKey() {
        return mobKey;
    }

    // Creates MobInfo for the LivingEntity
    public static MobInfo createMobInfo(Creature entity) {
        CustomMobType[] customMobTypes = CustomMobType.getCustomMobTypeList(entity.getType());
        if (customMobTypes == null) return null;
        CustomMobType customMobType = customMobTypes[(int) (Math.random() * customMobTypes.length)];
        int level = (int) (Math.random() * (customMobType.getMobBehavior().getHighestLevel() - customMobType.getMobBehavior().getLowestLevel() + 1)) + customMobType.getMobBehavior().getLowestLevel();
        MobInfo mobInfo = new MobInfo(customMobType, entity.getType(), level);
        if (entity.customName() != null) {
            mobInfo.setName(((TextComponent) entity.customName()).content());
        }
        if (entity instanceof Tameable && (((Tameable) entity).isTamed())) {
            mobInfo.setMobBehavior(MobBehavior.TAMED);
        }
        entity.getPersistentDataContainer().set(mobKey, new MobInfoDataType(), mobInfo);
        double vanillaHealth = Math.min(2048, mobInfo.getMaxHealth());
        double healthPercent = entity.getHealth() / entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(vanillaHealth);
        entity.setHealth(healthPercent * vanillaHealth);
        // Adjusts an entity's Speed
        double speed = mobInfo.getSpeed();
        double defaultSpeed = entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue();
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(defaultSpeed * (1 + (speed / 100)));
        HealthListeners.updateHealthBar(entity);
        return mobInfo;
    }

    // When a LivingEntity is added to the world for any reason
    @EventHandler
    public void onSpawn(CreatureSpawnEvent event) {
        if (event.getEntity() instanceof Creature entity && event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.CUSTOM) {
            createMobInfo(entity);
        }
    }

    // Sets name in Entity's MobInfo when the Entity gets renamed by a Name Tag
    @EventHandler
    public void onRename(PlayerInteractAtEntityEvent event) {
        if (event.getPlayer().getInventory().getItemInMainHand().getType() != Material.NAME_TAG) {
            return;
        }
        if (!(event.getRightClicked() instanceof Creature)) return;
        MobInfo mobInfo = event.getRightClicked().getPersistentDataContainer().get(mobKey, new MobInfoDataType());
        if (mobInfo == null) {
            mobInfo = createMobInfo((Creature) event.getRightClicked());
            if (mobInfo == null) {
                return;
            }
        }
        if (mobInfo.getMobBehavior() == MobBehavior.BOSS) {
            return;
        }
        MobInfo finalMobInfo = mobInfo;
        BukkitRunnable rename = new BukkitRunnable() {
            @Override
            public void run() {
                finalMobInfo.setName(((TextComponent) event.getRightClicked().customName()).content());
                event.getRightClicked().getPersistentDataContainer().set(mobKey, new MobInfoDataType(), finalMobInfo);
            }
        };
        rename.runTaskLater(plugin, 20);
    }

    // Updates MobInfo when an Entity gets converted into another type
    @EventHandler
    public void onTransform(EntityTransformEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof Creature)) return;
        MobInfo mobInfo = event.getEntity().getPersistentDataContainer().get(mobKey, new MobInfoDataType());
        if (mobInfo == null) {
            mobInfo = createMobInfo((Creature) entity);
            if (mobInfo == null) return;
        }
        mobInfo.setVanillaMobType(entity.getType());
        mobInfo.updateMobStats();
        entity.getPersistentDataContainer().set(mobKey, new MobInfoDataType(), mobInfo);
    }

    // Updates MobInfo when an Entity gets tamed
    @EventHandler
    public void onTame(EntityTameEvent event) {
        if (!(event.getEntity() instanceof Creature)) return;
        MobInfo mobInfo = event.getEntity().getPersistentDataContainer().get(mobKey, new MobInfoDataType());
        if (mobInfo == null) {
            mobInfo = createMobInfo((Creature) event.getEntity());
            if (mobInfo == null) return;
        }
        mobInfo.setMobBehavior(MobBehavior.TAMED);
        double vanillaMaxHealth = Math.min(2048, mobInfo.getMaxHealth());
        event.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(vanillaMaxHealth);
        event.getEntity().getPersistentDataContainer().set(mobKey, new MobInfoDataType(), mobInfo);
    }

    // Updates MobInfo when an Entity targets or untargets
    @EventHandler
    public void onTarget(EntityTargetLivingEntityEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof Creature)) return;
        MobInfo mobInfo = event.getEntity().getPersistentDataContainer().get(mobKey, new MobInfoDataType());
        if (mobInfo == null) {
            mobInfo = createMobInfo((Creature) event.getEntity());
            if (mobInfo == null) return;
        }
        if (mobInfo.getMobBehavior() != MobBehavior.TAMED && mobInfo.getMobBehavior() != MobBehavior.BOSS) {
            if (event.getTarget() != null) {
                mobInfo.setMobBehavior(MobBehavior.HOSTILE);
            } else {
                mobInfo.setMobBehavior(mobInfo.getCustomMobType().getMobBehavior());
            }
            entity.getPersistentDataContainer().set(mobKey, new MobInfoDataType(), mobInfo);
        }
    }

}
