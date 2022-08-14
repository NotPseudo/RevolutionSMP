package me.notpseudo.revolutionsmp.enchantments;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.itemstats.StatType;
import me.notpseudo.revolutionsmp.listeners.HealthListeners;
import me.notpseudo.revolutionsmp.listeners.MobListeners;
import me.notpseudo.revolutionsmp.listeners.StatsListeners;
import me.notpseudo.revolutionsmp.mobstats.BaseEntityStats;
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

public class VenemousEnchantmentObject extends EnchantmentObject implements ActionEnchantment, Listener {

    private static final NamespacedKey mobKey = MobListeners.getMobKey();
    private static final NamespacedKey playerKey = StatsListeners.getPlayerStatsKey();

    public VenemousEnchantmentObject() {
        super(EnchantmentType.VENEMOUS);
    }

    public VenemousEnchantmentObject(int level) {
        super(EnchantmentType.VENEMOUS, level);
    }

    @Override
    public void action(LivingEntity damager, LivingEntity target, double damage, boolean critical, double showDamage) {
        if (super.getHitCount() >= 40) {
            return;
        }
        super.setHitCount(super.getHitCount() + 1);
        UUID targetUUID = target.getUniqueId();
        if (!super.getAttacked().containsKey(targetUUID)) {
            super.getAttacked().put(targetUUID, 0);
        }
        double damagePercent = super.getLevel() * 0.003;
        BaseEntityStats targetStats = target.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
        if (target instanceof Player) {
            targetStats = target.getPersistentDataContainer().get(playerKey, new PlayerStatsDataType());
            if (targetStats == null) {
                targetStats = new PlayerStats();
            }
            // ((PlayerStats) targetStats).setSpeedMultiplier(((PlayerStats) targetStats).getSpeedMultiplier() * 0.95);
        } else {
            double speed = targetStats.getStatValue(StatType.SPEED);
            double originalSpeed = target.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue();
            target.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(originalSpeed * 0.95 * (1 + (speed / 100)));
        }
        BaseEntityStats finalTargetStats = targetStats;
        BukkitRunnable venemous = new BukkitRunnable() {

            int count = 0;

            @Override
            public void run() {
                if (!getAttacked().containsKey(targetUUID) || target.isDead()) {
                    getAttacked().remove(targetUUID);
                    this.cancel();
                    return;
                }
                if (count == 5) {
                    if (target instanceof Player) {
                        // ((PlayerStats) finalTargetStats).setSpeedMultiplier(((PlayerStats) finalTargetStats).getSpeedMultiplier() / 0.95);
                    } else {
                        double speed = finalTargetStats.getStatValue(StatType.SPEED);
                        double originalSpeed = target.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue();
                        target.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(originalSpeed / 0.95 * (1 + (speed / 100)));
                    }
                    this.cancel();
                    getAttacked().remove(targetUUID);
                    return;
                }
                target.damage(damagePercent * damagePercent);
                HealthListeners.showDamage(target, damagePercent * showDamage, false, ChatColor.GREEN);
                count++;
            }
        };
        venemous.runTaskTimer(RevolutionSMP.getPlugin(), 0, 20);
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        super.getAttacked().remove(event.getEntity().getUniqueId());
    }

}
