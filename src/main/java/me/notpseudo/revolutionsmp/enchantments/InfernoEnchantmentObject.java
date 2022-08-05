package me.notpseudo.revolutionsmp.enchantments;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.itemstats.StatType;
import me.notpseudo.revolutionsmp.listeners.HealthListeners;
import me.notpseudo.revolutionsmp.listeners.MobListeners;
import me.notpseudo.revolutionsmp.listeners.StatsListeners;
import me.notpseudo.revolutionsmp.mobstats.BaseEntityStats;
import me.notpseudo.revolutionsmp.mobstats.MobInfo;
import me.notpseudo.revolutionsmp.mobstats.MobInfoDataType;
import me.notpseudo.revolutionsmp.particles.Particles;
import me.notpseudo.revolutionsmp.playerstats.PlayerStats;
import me.notpseudo.revolutionsmp.playerstats.PlayerStatsDataType;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.UUID;

public class InfernoEnchantmentObject extends EnchantmentObject implements ActionEnchantment, Listener {

    private static final NamespacedKey mobKey = MobListeners.getMobKey();
    private static final NamespacedKey playerKey = StatsListeners.getPlayerStatsKey();

    public InfernoEnchantmentObject() {
        super(EnchantmentType.INFERNO);
    }

    public InfernoEnchantmentObject(int level) {
        super(EnchantmentType.INFERNO, level);
    }

    @Override
    public void action(LivingEntity damager, LivingEntity target, double damage, boolean critical, double showDamage) {
        UUID targetUUID = target.getUniqueId();
        damager.sendMessage("Last Hit: " + super.getLastHit());
        damager.sendMessage("Target UUID: " + targetUUID);
        if(super.getLastHit() == null) {
            super.setLastHit(targetUUID);
        }
        damager.sendMessage("Last Hit after Null Check: " + super.getLastHit());
        if(!(target.getUniqueId().equals(super.getLastHit()))) {
            damager.sendMessage("Last Hit: " + super.getLastHit());
            damager.sendMessage("Target UUID: " + targetUUID);
            super.setLastHit(targetUUID);
            super.setHitCount(0);
        }
        int hitCount = super.getHitCount();
        if (target.getUniqueId().equals(super.getLastHit())) {
            hitCount++;
            super.setHitCount(hitCount);
        }
        if (hitCount >= 10) {
            super.setHitCount(0);
            BaseEntityStats targetStats = target.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
            double speed = 100, vanillaMoveSpeed = 1, vanillaFlySpeed = 1;
            if(!super.getAttacked().containsKey(targetUUID)) {
                if(target instanceof Player) {
                    targetStats = target.getPersistentDataContainer().get(playerKey, new PlayerStatsDataType());
                    speed = targetStats.getArmorStatValue(StatType.SPEED);
                    target.getPersistentDataContainer().set(playerKey, new PlayerStatsDataType(), (PlayerStats) targetStats);
                } else {
                    if(targetStats != null) {
                        targetStats.setArmorStatValue(StatType.SPEED, 0);
                    }
                    if(target.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED) != null) {
                        vanillaMoveSpeed = target.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue();
                        target.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);
                    }
                    if(target.getAttribute(Attribute.GENERIC_FLYING_SPEED) != null) {
                        vanillaFlySpeed = target.getAttribute(Attribute.GENERIC_FLYING_SPEED).getBaseValue();
                        target.getAttribute(Attribute.GENERIC_FLYING_SPEED).setBaseValue(0);
                    }
                }
            }
            double damagePercent = 1 + (super.getLevel() * 0.25);
            BaseEntityStats finalTargetStats = targetStats;
            double finalSpeed = speed, finalVanillaMoveSpeed = vanillaMoveSpeed, finalVanillaFlySpeed = vanillaFlySpeed;
            Particles.dualSpiralUp(Particle.FLAME, target, 1, target.getHeight(), 5, 0);
            Particles.dualSpiralUp(Particle.DRIP_LAVA, target, 1, target.getHeight(), 5, 0);
            BukkitRunnable inferno = new BukkitRunnable() {

                int count = 0;

                @Override
                public void run() {
                    if(!getAttacked().containsKey(targetUUID) || target.isDead()) {
                        getAttacked().remove(targetUUID);
                        this.cancel();
                        return;
                    }
                    if(count == 5) {
                        if(finalTargetStats != null) {
                            finalTargetStats.setArmorStatValue(StatType.SPEED, finalSpeed);
                        }
                        if(target instanceof Player) {
                            target.getPersistentDataContainer().set(playerKey, new PlayerStatsDataType(), (PlayerStats) finalTargetStats);
                        } else {
                            target.getPersistentDataContainer().set(mobKey, new MobInfoDataType(), (MobInfo) finalTargetStats);
                            if(target.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED) != null) {
                                double defaultSpeed = target.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue();
                                target.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(1);
                                target.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(finalVanillaMoveSpeed);
                            }
                            if(target.getAttribute(Attribute.GENERIC_FLYING_SPEED) != null) {
                                target.getAttribute(Attribute.GENERIC_FLYING_SPEED).setBaseValue(finalVanillaFlySpeed);
                            }
                        }
                        getAttacked().remove(targetUUID);
                        this.cancel();
                        return;
                    }
                    target.damage(damagePercent * damage / 5);
                    HealthListeners.showDamage(target, damagePercent * showDamage / 5, false, ChatColor.GOLD);
                    count++;
                }
            };
            inferno.runTaskTimer(RevolutionSMP.getPlugin(), 0, 20);
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        super.getAttacked().remove(event.getEntity().getUniqueId());
    }

}