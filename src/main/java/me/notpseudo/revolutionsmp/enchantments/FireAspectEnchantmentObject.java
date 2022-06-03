package me.notpseudo.revolutionsmp.enchantments;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.listeners.HealthListeners;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

public class FireAspectEnchantmentObject extends EnchantmentObject implements ActionEnchantment {

    public FireAspectEnchantmentObject() {
        super(EnchantmentType.FIRE_ASPECT);
    }

    public FireAspectEnchantmentObject(int level) {
        super(EnchantmentType.FIRE_ASPECT, level);
    }

    @Override
    public void action(LivingEntity damager, LivingEntity target, double damage, boolean critical, double showDamage) {
        int seconds = 4;
        damager.sendMessage("fire aspect recognized");
        double damagePercent = 0.03 * super.getLevel();
        if (super.getLevel() == 1) {
            seconds = 3;
        }
        int finalSeconds = seconds;
        BukkitRunnable fireDamage = new BukkitRunnable() {
            int count = 1;

            @Override
            public void run() {
                if (!target.isDead() && count <= finalSeconds) {
                    damager.sendMessage("fire aspect seconds left: " + (finalSeconds - count));
                    target.setMaximumNoDamageTicks(0);
                    target.setNoDamageTicks(0);
                    target.damage(damage * damagePercent);
                    HealthListeners.showDamage(target, damagePercent * showDamage, false, ChatColor.GOLD);
                    count++;
                } else {
                    this.cancel();
                }
            }
        };
        fireDamage.runTaskTimer(RevolutionSMP.getPlugin(), 0, 20);
    }
}
