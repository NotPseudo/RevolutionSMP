package me.notpseudo.revolutionsmp.enchantments;

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
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.UUID;

public class InfernoEnchantmentObject extends EnchantmentObject implements ActionEnchantment {

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
            double speed, vanillaMoveSpeed, vanillaFlySpeed;
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
            BukkitRunnable inferno = new BukkitRunnable() {
                @Override
                public void run() {

                }
            };
            target.damage(damagePercent * damage);
            HealthListeners.showDamage(target, damagePercent * damage, false, ChatColor.YELLOW);
        }
    }
}