package me.notpseudo.revolutionsmp.listeners;

import com.destroystokyo.paper.event.entity.EntityAddToWorldEvent;
import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.mobstats.MobBehavior;
import me.notpseudo.revolutionsmp.mobstats.MobInfo;
import me.notpseudo.revolutionsmp.mobstats.MobInfoDataType;
import me.notpseudo.revolutionsmp.mobstats.VanillaMobType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
    private void createMobInfo(LivingEntity entity) {
        VanillaMobType vanillaMobType;
        try {
            vanillaMobType = VanillaMobType.valueOf(entity.getType().name());
            MobInfo mobInfo = new MobInfo(vanillaMobType);
            if(entity.customName() != null) {
                mobInfo.setName(String.valueOf(entity.customName()));
            }
            if(entity instanceof Tameable && (((Tameable) entity).isTamed())) {
                mobInfo.setMobBehavior(MobBehavior.TAMED);
            }
            entity.getPersistentDataContainer().set(mobKey, new MobInfoDataType(), mobInfo);
            double health = Math.min(2048, mobInfo.getMaxHealth());
            double healthPercent = entity.getHealth() / entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
            entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
            entity.setHealth(healthPercent * health);
            // Adjusts an entity's Speed
            double speed = mobInfo.getSpeed();
            double defaultSpeed = entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue();
            entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(defaultSpeed * (1 + (speed / 100)));
            HealthListeners.updateHealthBar(entity, (int) health);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return;
        }
    }

    // When a LivingEntity is added to the world for any reason
    @EventHandler
    public void onSpawn(EntityAddToWorldEvent event) {
        if(event.getEntity() instanceof LivingEntity && !(event.getEntity() instanceof ArmorStand) && !(event.getEntity() instanceof Player)) {
            LivingEntity entity = (LivingEntity) event.getEntity();
            createMobInfo(entity);
        }
    }

    // Sets name in Entity's MobInfo when the Entity gets renamed by a Name Tag
    @EventHandler
    public void onRename(PlayerInteractAtEntityEvent event) {
        if(event.getPlayer().getInventory().getItemInMainHand().getType() != Material.NAME_TAG) return;
        if(!(event.getRightClicked() instanceof LivingEntity)) return;
        MobInfo mobInfo = event.getRightClicked().getPersistentDataContainer().get(mobKey, new MobInfoDataType());
        if(mobInfo == null) {
            createMobInfo((LivingEntity) event.getRightClicked());
            return;
        }
        BukkitRunnable rename = new BukkitRunnable() {
            @Override
            public void run() {
                mobInfo.setName(event.getRightClicked().getCustomName());
                event.getRightClicked().getPersistentDataContainer().set(mobKey, new MobInfoDataType(), mobInfo);
            }
        };
        rename.runTaskLater(plugin, 20);
    }

    // Updates MobInfo when an Entity gets converted into another type
    @EventHandler
    public void onTransform(EntityTransformEvent event) {
        Entity entity = event.getEntity();
        if(!(entity instanceof LivingEntity)) return;
        MobInfo mobInfo = event.getEntity().getPersistentDataContainer().get(mobKey, new MobInfoDataType());
        if(mobInfo == null) {
            createMobInfo((LivingEntity) entity);
            return;
        }
        try {
            mobInfo.setVanillaMobType(VanillaMobType.valueOf(entity.getType().name()));
            mobInfo.updateMobStats();
            entity.getPersistentDataContainer().set(mobKey, new MobInfoDataType(), mobInfo);
        } catch (IllegalArgumentException e) {
            return;
        }
    }

    // Updates MobInfo when an Entity gets tamed
    @EventHandler
    public void onTame(EntityTameEvent event) {
        MobInfo mobInfo = event.getEntity().getPersistentDataContainer().get(mobKey, new MobInfoDataType());
        if(mobInfo == null) {
            createMobInfo(event.getEntity());
            return;
        }
        mobInfo.setMobBehavior(MobBehavior.TAMED);
        double health = Math.min(2048, mobInfo.getMaxHealth());
        double healthPercent = event.getEntity().getHealth() / event.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
        event.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
        event.getEntity().getPersistentDataContainer().set(mobKey, new MobInfoDataType(), mobInfo);
    }

    // Updates MobInfo when an Entity targets or untargets
    @EventHandler
    public void onTarget(EntityTargetLivingEntityEvent event) {
        Entity entity = event.getEntity();
        if(!(entity instanceof LivingEntity)) return;
        MobInfo mobInfo = event.getEntity().getPersistentDataContainer().get(mobKey, new MobInfoDataType());
        if(mobInfo == null) {
            createMobInfo((LivingEntity) event.getEntity());
            return;
        }
        if(entity instanceof Mob && mobInfo.getMobBehavior() != MobBehavior.TAMED && mobInfo.getMobBehavior() != MobBehavior.BOSS) {
            if(event.getTarget() != null) {
                mobInfo.setMobBehavior(MobBehavior.HOSTILE);
            } else {
                mobInfo.setMobBehavior(mobInfo.getCustomMobType().getMobBehavior());
            }
            entity.getPersistentDataContainer().set(mobKey, new MobInfoDataType(), mobInfo);
        }
    }

}
