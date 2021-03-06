package me.notpseudo.revolutionsmp.enchantments;

import me.notpseudo.revolutionsmp.listeners.MobListeners;
import me.notpseudo.revolutionsmp.listeners.StatsListeners;
import me.notpseudo.revolutionsmp.mobstats.BaseEntityStats;
import me.notpseudo.revolutionsmp.mobstats.MobInfo;
import me.notpseudo.revolutionsmp.mobstats.MobInfoDataType;
import me.notpseudo.revolutionsmp.playerstats.PlayerStats;
import me.notpseudo.revolutionsmp.playerstats.PlayerStatsDataType;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class SyphonEnchantmentObject extends EnchantmentObject implements ActionEnchantment {

    private static final NamespacedKey mobKey = MobListeners.getMobKey();
    private static final NamespacedKey playerKey = StatsListeners.getPlayerStatsKey();

    public SyphonEnchantmentObject() {
        super(EnchantmentType.SYPHON);
    }

    public SyphonEnchantmentObject(int level) {
        super(EnchantmentType.SYPHON, level);
    }

    @Override
    public void action(LivingEntity damager, LivingEntity target, double damage, boolean critical, double showDamage) {
        if (!critical) {
            return;
        }
        BaseEntityStats damagerStats = damager.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
        if (damager instanceof Player) {
            damagerStats = damager.getPersistentDataContainer().get(playerKey, new PlayerStatsDataType());
        }
        double percentToHeal = (0.001 + (super.getLevel() * 0.001)) * (int) Math.min(1000, damage) / 100;
        double currentHealth = damager.getHealth(), maxHealth = damager.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
        if (damagerStats != null) {
            currentHealth = damagerStats.getCurrentHealth();
            maxHealth = damagerStats.getMaxHealth();
        }
        if (currentHealth != maxHealth) {
            double addHealth = percentToHeal * maxHealth;
            double finalHealth = Math.min((currentHealth + addHealth), maxHealth);
            damager.setHealth(Math.min(finalHealth, (finalHealth / maxHealth) * 2048));
            if (damagerStats != null) {
                damagerStats.setCurrentHealth(finalHealth);
                if (damager instanceof Player) {
                    damager.getPersistentDataContainer().set(playerKey, new PlayerStatsDataType(), (PlayerStats) damagerStats);
                } else {
                    damager.getPersistentDataContainer().set(mobKey, new MobInfoDataType(), (MobInfo) damagerStats);
                }
            }
        }
    }
}