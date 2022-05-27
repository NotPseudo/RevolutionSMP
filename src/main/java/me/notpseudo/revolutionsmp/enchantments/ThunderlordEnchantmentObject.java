package me.notpseudo.revolutionsmp.enchantments;

import me.notpseudo.revolutionsmp.listeners.HealthListeners;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;

import java.util.UUID;

public class ThunderlordEnchantmentObject extends EnchantmentObject implements ActionEnchantment {

    private UUID lastHit;
    private int hitCount;

    public ThunderlordEnchantmentObject() {
        super(EnchantmentType.THUNDERLORD);
        hitCount = 0;
    }

    public ThunderlordEnchantmentObject(int level) {
        super(EnchantmentType.THUNDERLORD, level);
        hitCount = 0;
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
        if (hitCount >= 3) {
            hitCount = 0;
            int level = super.getLevel();
            double damagePercent;
            switch (level) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    damagePercent = level * 0.08;
                case 6:
                case 7:
                default:
                    damagePercent = (level - 1) * 0.1;
            }
            target.getWorld().strikeLightningEffect(target.getLocation());
            target.damage(damagePercent * damage);
            HealthListeners.showDamage(target, damagePercent * damage, false, ChatColor.YELLOW);
        }
    }
}