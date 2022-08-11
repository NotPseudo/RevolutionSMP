package me.notpseudo.revolutionsmp.enchantments;

import me.notpseudo.revolutionsmp.listeners.HealthListeners;
import me.notpseudo.revolutionsmp.listeners.MobListeners;
import me.notpseudo.revolutionsmp.mobstats.MobCategory;
import me.notpseudo.revolutionsmp.mobstats.MobInfoDataType;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;

import java.util.Collection;

public class ThunderboltEnchantmentObject extends EnchantmentObject implements ActionEnchantment {

    private static final NamespacedKey mobKey = MobListeners.getMobKey();

    public ThunderboltEnchantmentObject() {
        super(EnchantmentType.THUNDERBOLT);
    }

    public ThunderboltEnchantmentObject(int level) {
        super(EnchantmentType.THUNDERBOLT, level);
    }

    @Override
    public void action(LivingEntity damager, LivingEntity target, double damage, boolean critical, double showDamage) {
        super.setHitCount(super.getHitCount() + 1);
        if (super.getHitCount() >= 3) {
            super.setHitCount(0);
            int level = super.getLevel();
            double damagePercent;
            switch (level) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    damagePercent = level * 0.04;
                case 6:
                default:
                    damagePercent = (level - 1) * 0.05;
            }
            Collection<LivingEntity> enemies = damager.getLocation().getNearbyLivingEntities(2).stream()
                    .filter(c -> c.getPersistentDataContainer().get(mobKey, new MobInfoDataType()) != null
                            && c.getPersistentDataContainer().get(mobKey, new MobInfoDataType()).getMobBehavior() != MobCategory.TAMED
                    ).toList();
            for (LivingEntity entity : enemies) {
                entity.getWorld().strikeLightningEffect(entity.getLocation());
                entity.damage(damagePercent * damage);
                HealthListeners.showDamage(entity, damagePercent * showDamage, false, ChatColor.YELLOW);
            }
        }
    }
}
