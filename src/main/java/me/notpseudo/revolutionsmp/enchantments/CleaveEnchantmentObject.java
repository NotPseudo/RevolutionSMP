package me.notpseudo.revolutionsmp.enchantments;

import me.notpseudo.revolutionsmp.listeners.HealthListeners;
import me.notpseudo.revolutionsmp.listeners.MobListeners;
import me.notpseudo.revolutionsmp.mobstats.MobCategory;
import me.notpseudo.revolutionsmp.mobstats.MobInfoDataType;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;

import java.util.Collection;

public class CleaveEnchantmentObject extends EnchantmentObject implements ActionEnchantment {

    private final static NamespacedKey mobKey = MobListeners.getMobKey();

    public CleaveEnchantmentObject() {
        super(EnchantmentType.CLEAVE);
    }

    public CleaveEnchantmentObject(int level) {
        super(EnchantmentType.CLEAVE, level);
    }

    @Override
    public void action(LivingEntity damager, LivingEntity target, double damage, boolean critical, double showDamage) {
        int level = super.getLevel();
        double damagePercent, range = 3 + (0.3 * level);
        switch (level) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                damagePercent = 0.03 * level;
                break;
            case 6:
                damagePercent = 0.2;
                break;
            default:
                damagePercent = 0.4 * level;
        }
        Collection<LivingEntity> enemies = damager.getLocation().getNearbyLivingEntities(range).stream()
                .filter(c -> c.getPersistentDataContainer().get(mobKey, new MobInfoDataType()) != null
                        && c.getPersistentDataContainer().get(mobKey, new MobInfoDataType()).getMobBehavior() != MobCategory.TAMED
                ).toList();
        for (LivingEntity enemy : enemies) {
            enemy.damage(damagePercent * damage);
            HealthListeners.showDamage(enemy, damagePercent * showDamage, false, ChatColor.GRAY);
        }
    }
}