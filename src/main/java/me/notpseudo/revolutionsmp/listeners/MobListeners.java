package me.notpseudo.revolutionsmp.listeners;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.itemstats.StatType;
import me.notpseudo.revolutionsmp.mobstats.*;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
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
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.Nullable;

import java.util.List;

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

    public static LivingEntity spawnCustom(CustomMobType type, Location location, Player owner) {
        if (location.getWorld().spawnEntity(location, type.getVanillaVersion(), CreatureSpawnEvent.SpawnReason.CUSTOM) instanceof LivingEntity living) {
            if (!(living instanceof Creature || living instanceof ComplexLivingEntity)) {
                return null;
            }
            int level = (int) (Math.random() * (type.getMaxLevel() - type.getMinLevel() + 1)) + type.getMinLevel();
            MobInfo mobInfo = new MobInfo(type, level, living.getUniqueId());
            if (living.customName() != null) {
                mobInfo.setName(((TextComponent) living.customName()).content());
            }
            if (living instanceof Tameable && (((Tameable) living).isTamed())) {
                mobInfo.setMobBehavior(MobCategory.TAMED);
            }
            if (owner != null) {
                mobInfo.setOwningPlayer(owner.getUniqueId());
                if (living instanceof Mob mob) {
                    mob.setTarget(owner);
                }
            }
            living.getPersistentDataContainer().set(mobKey, new MobInfoDataType(), mobInfo);
            double vanillaHealth = Math.min(2048, mobInfo.getMaxHealth());
            double healthPercent = living.getHealth() / living.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
            living.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(vanillaHealth);
            living.setHealth(healthPercent * vanillaHealth);
            // Adjusts an entity's Speed
            double speed = mobInfo.getArmorStatValue(StatType.SPEED);
            double defaultSpeed = living.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue();
            living.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(defaultSpeed * (1 + (speed / 100)));
            HealthListeners.updateHealthBar(living);
            ItemStack[] equipment = type.getEquipment();
            if (equipment != null && living.getEquipment() != null) {
                if (equipment[0] != null) {
                    living.getEquipment().setHelmet(equipment[0]);
                }
                if (equipment[1] != null) {
                    living.getEquipment().setChestplate(equipment[1]);
                }
                if (equipment[2] != null) {
                    living.getEquipment().setLeggings(equipment[2]);
                }
                if (equipment[3] != null) {
                    living.getEquipment().setBoots(equipment[3]);
                }
                if (equipment[4] != null) {
                    living.getEquipment().setItemInMainHand(equipment[4]);
                }
            }
            return living;
        }
        return null;
    }

    // Creates MobInfo for the LivingEntity
    public static MobInfo createMobInfo(LivingEntity entity) {
        if (!(entity instanceof Creature || entity instanceof ComplexLivingEntity)) {
            return null;
        }
        List<CustomMobType> customMobTypes = CustomMobType.getCustomMobTypeList(entity.getType());
        if (customMobTypes == null) {
            return null;
        }
        CustomMobType customMobType = customMobTypes.get((int) (Math.random() * customMobTypes.size()));
        int level = (int) (Math.random() * (customMobType.getMaxLevel() - customMobType.getMinLevel() + 1)) + customMobType.getMinLevel();
        MobInfo mobInfo = new MobInfo(customMobType, level, entity.getUniqueId());
        if (entity.customName() != null) {
            mobInfo.setName(((TextComponent) entity.customName()).content());
        }
        if (entity instanceof Tameable && (((Tameable) entity).isTamed())) {
            mobInfo.setMobBehavior(MobCategory.TAMED);
        }
        entity.getPersistentDataContainer().set(mobKey, new MobInfoDataType(), mobInfo);
        double vanillaHealth = Math.min(2048, mobInfo.getMaxHealth());
        double healthPercent = entity.getHealth() / entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(vanillaHealth);
        entity.setHealth(healthPercent * vanillaHealth);
        // Adjusts an entity's Speed
        double speed = mobInfo.getArmorStatValue(StatType.SPEED);
        double defaultSpeed = entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue();
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(defaultSpeed * (1 + (speed / 100)));
        HealthListeners.updateHealthBar(entity);
        ItemStack[] equipment = customMobType.getEquipment();
        if (equipment != null && entity.getEquipment() != null) {
            if (equipment[0] != null) {
                entity.getEquipment().setHelmet(equipment[0]);
            }
            if (equipment[1] != null) {
                entity.getEquipment().setChestplate(equipment[1]);
            }
            if (equipment[2] != null) {
                entity.getEquipment().setLeggings(equipment[2]);
            }
            if (equipment[3] != null) {
                entity.getEquipment().setBoots(equipment[3]);
            }
            if (equipment[4] != null) {
                entity.getEquipment().setItemInMainHand(equipment[4]);
            }
        }
        return mobInfo;
    }

    // When a LivingEntity is added to the world for any reason
    @EventHandler
    public void onSpawn(CreatureSpawnEvent event) {
        if (event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.CUSTOM) {
            createMobInfo(event.getEntity());
        }
    }

    // Sets name in Entity's MobInfo when the Entity gets renamed by a Name Tag
    @EventHandler
    public void onRename(PlayerInteractAtEntityEvent event) {
        if (event.getPlayer().getInventory().getItemInMainHand().getType() != Material.NAME_TAG) {
            return;
        }
        if (!(event.getRightClicked() instanceof LivingEntity living)) {
            return;
        }
        MobInfo mobInfo = getMobInfo(living);
        if (mobInfo == null) {
            return;
        }
        if (mobInfo.getMobBehavior() == MobCategory.BOSS) {
            return;
        }
        BukkitRunnable rename = new BukkitRunnable() {
            @Override
            public void run() {
                if (event.getRightClicked().customName() == null) {
                    return;
                }
                mobInfo.setName(((TextComponent) event.getRightClicked().customName()).content());
                event.getRightClicked().getPersistentDataContainer().set(mobKey, new MobInfoDataType(), mobInfo);
            }
        };
        rename.runTaskLater(plugin, 20);
    }

    // Updates MobInfo when an Entity gets converted into another type
    @EventHandler
    public void onTransform(EntityTransformEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof LivingEntity living)) {
            return;
        }
        MobInfo mobInfo = getMobInfo(living);
        if (mobInfo == null) {
            return;
        }
        mobInfo.setVanillaMobType(entity.getType());
        mobInfo.updateMobStats();
        entity.getPersistentDataContainer().set(mobKey, new MobInfoDataType(), mobInfo);
    }

    // Updates MobInfo when an Entity gets tamed
    @EventHandler
    public void onTame(EntityTameEvent event) {
        if (!(event.getEntity() instanceof Creature || event.getEntity() instanceof ComplexLivingEntity)) {
            return;
        }
        MobInfo mobInfo = getMobInfo(event.getEntity());
        if (mobInfo == null) {
            return;
        }
        mobInfo.setMobBehavior(MobCategory.TAMED);
        double vanillaMaxHealth = Math.min(2048, mobInfo.getMaxHealth());
        event.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(vanillaMaxHealth);
        event.getEntity().getPersistentDataContainer().set(mobKey, new MobInfoDataType(), mobInfo);
    }

    // Updates MobInfo when an Entity targets or untargets
    @EventHandler
    public void onTarget(EntityTargetLivingEntityEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof LivingEntity living)) {
            return;
        }
        MobInfo mobInfo = getMobInfo(living);
        if (mobInfo == null) {
            return;
        }
        if (mobInfo.getMobBehavior() != MobCategory.TAMED && mobInfo.getMobBehavior() != MobCategory.BOSS && mobInfo.getMobBehavior() != MobCategory.PASSIVE) {
            if (event.getTarget() != null) {
                mobInfo.setMobBehavior(MobCategory.HOSTILE);
            } else {
                mobInfo.setMobBehavior(mobInfo.getCustomMobType().getMobBehavior());
            }
            entity.getPersistentDataContainer().set(mobKey, new MobInfoDataType(), mobInfo);
        }
    }

    @Nullable
    public static MobInfo getMobInfo(LivingEntity entity) {
        if (!(entity instanceof Creature || entity instanceof ComplexLivingEntity)) {
            return null;
        }
        MobInfo mobInfo = entity.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
        if (mobInfo == null) {
            mobInfo = createMobInfo(entity);
        }
        return mobInfo;
    }

}