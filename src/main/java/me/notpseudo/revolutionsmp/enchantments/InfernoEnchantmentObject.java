package me.notpseudo.revolutionsmp.enchantments;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.listeners.HealthListeners;
import me.notpseudo.revolutionsmp.listeners.MobListeners;
import me.notpseudo.revolutionsmp.listeners.StatsListeners;
import me.notpseudo.revolutionsmp.mobstats.BaseEntityStats;
import me.notpseudo.revolutionsmp.mobstats.MobInfo;
import me.notpseudo.revolutionsmp.mobstats.MobInfoDataType;
import me.notpseudo.revolutionsmp.playerstats.PlayerStats;
import me.notpseudo.revolutionsmp.playerstats.PlayerStatsDataType;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
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

    private UUID lastHit;
    private int hitCount;
    private ArrayList<UUID> trapped;
    private static final NamespacedKey mobKey = MobListeners.getMobKey();
    private static final NamespacedKey playerKey = StatsListeners.getPlayerStatsKey();

    public InfernoEnchantmentObject() {
        super(EnchantmentType.INFERNO);
        hitCount = 0;
        lastHit = null;
        trapped = new ArrayList<>();
    }

    public InfernoEnchantmentObject(int level) {
        super(EnchantmentType.INFERNO, level);
        hitCount = 0;
        lastHit = null;
        trapped = new ArrayList<>();
    }

    @Override
    public void action(LivingEntity damager, LivingEntity target, double damage, boolean critical) {
        if (lastHit == null) {
            lastHit = target.getUniqueId();
        }
        if (target.getUniqueId() != lastHit) {
            hitCount = 1;
            return;
        }
        hitCount++;
        if (hitCount >= 10) {
            hitCount = 0;
            BaseEntityStats targetStats = target.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
            double speed = 100, vanillaMoveSpeed = 1, vanillaFlySpeed = 1;
            if(target instanceof Player) {
                targetStats = target.getPersistentDataContainer().get(playerKey, new PlayerStatsDataType());
                speed = targetStats.getSpeed();
                targetStats.setSpeed(0);
                target.getPersistentDataContainer().set(playerKey, new PlayerStatsDataType(), (PlayerStats) targetStats);
            } else {
                if(targetStats != null) {
                    targetStats.setSpeed(0);
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
            double damagePercent = 1 + (super.getLevel() * 0.25);
            BaseEntityStats finalTargetStats = targetStats;
            double finalSpeed = speed, finalVanillaMoveSpeed = vanillaMoveSpeed, finalVanillaFlySpeed = vanillaFlySpeed;
            BukkitRunnable inferno = new BukkitRunnable() {

                int count = 0;

                @Override
                public void run() {
                    if(!trapped.contains(target.getUniqueId())) {
                        this.cancel();
                        return;
                    }
                    if(count == 5) {
                        if(finalTargetStats != null) {
                            finalTargetStats.setSpeed(finalSpeed);
                        }
                        if(target instanceof Player) {
                            target.getPersistentDataContainer().set(playerKey, new PlayerStatsDataType(), (PlayerStats) finalTargetStats);
                        } else {
                            target.getPersistentDataContainer().set(mobKey, new MobInfoDataType(), (MobInfo) finalTargetStats);
                            if(target.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED) != null) {
                                target.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(finalVanillaMoveSpeed);
                            }
                            if(target.getAttribute(Attribute.GENERIC_FLYING_SPEED) != null) {
                                target.getAttribute(Attribute.GENERIC_FLYING_SPEED).setBaseValue(finalVanillaFlySpeed);
                            }
                        }
                        this.cancel();
                        return;
                    }
                    target.damage(damagePercent * damage / 5);
                    HealthListeners.showDamage(target, damagePercent * damage / 5, false, ChatColor.GOLD);
                    count++;
                }
            };
            inferno.runTaskTimer(RevolutionSMP.getPlugin(), 0, 20);
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        trapped.remove(event.getEntity().getUniqueId());
    }

}