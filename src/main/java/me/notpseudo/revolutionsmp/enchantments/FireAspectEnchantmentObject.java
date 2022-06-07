package me.notpseudo.revolutionsmp.enchantments;


import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.listeners.HealthListeners;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.UUID;

public class FireAspectEnchantmentObject extends EnchantmentObject implements ActionEnchantment, Listener {

    private ArrayList<UUID> burning;
    private int numRun;

    public FireAspectEnchantmentObject() {
        super(EnchantmentType.FIRE_ASPECT);
        burning = new ArrayList<>();
    }

    public FireAspectEnchantmentObject(int level) {
        super(EnchantmentType.FIRE_ASPECT, level);
        burning = new ArrayList<>();
    }


    @Override
    public void action(LivingEntity damager, LivingEntity target, double damage, boolean critical, double showDamage) {
        damager.sendMessage("Fire Aspect action Method Called");
        damager.sendMessage("Burning contains target UUID? " + burning.contains(target.getUniqueId()));
        this.numRun++;
        damager.sendMessage("Number of times run: " + this.numRun);
        if(burning.contains(target.getUniqueId())) {
            damager.sendMessage("Burning already contains target UUID " + target.getUniqueId());
            return;
        }
        damager.sendMessage("Burning did not contain target UUID " + target.getUniqueId() + ". Target UUID should be added and seen next time");
        burning.add(target.getUniqueId());
        int seconds = 4;
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
                    target.setMaximumNoDamageTicks(0);
                    target.setNoDamageTicks(0);
                    target.damage(damage * damagePercent);
                    HealthListeners.showDamage(target, damagePercent * showDamage, false, ChatColor.GOLD);
                    count++;
                } else {
                    burning.remove(target);
                    this.cancel();
                }
            }
        };
        fireDamage.runTaskTimer(RevolutionSMP.getPlugin(), 0, 20);

    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        //burning.remove(event.getEntity());
    }

}