package me.notpseudo.revolutionsmp.enchantments;

import me.notpseudo.revolutionsmp.listeners.HealthListeners;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;

import java.util.UUID;

public class ThunderlordEnchantmentObject extends EnchantmentObject implements ActionEnchantment {

    public ThunderlordEnchantmentObject() {
        super(EnchantmentType.THUNDERLORD);
    }

    public ThunderlordEnchantmentObject(int level) {
        super(EnchantmentType.THUNDERLORD, level);
    }

    @Override
    public void action(LivingEntity damager, LivingEntity target, double damage, boolean critical, double showDamage) {
        UUID targetUUID = target.getUniqueId();
        if (super.getLastHit() == null) {
            super.setLastHit(targetUUID);
        }
        if (!super.getLastHit().equals(targetUUID)) {
            super.setHitCount(0);
            super.setLastHit(targetUUID);
            return;
        }
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
                    damagePercent = level * 0.08;
                case 6:
                case 7:
                default:
                    damagePercent = (level - 1) * 0.1;
            }
            target.getWorld().strikeLightningEffect(target.getLocation());
            target.damage(damagePercent * damage);
            HealthListeners.showDamage(target, damagePercent * showDamage, false, ChatColor.YELLOW);
        }
    }
}