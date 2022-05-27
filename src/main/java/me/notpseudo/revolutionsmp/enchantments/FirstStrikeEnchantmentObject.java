package me.notpseudo.revolutionsmp.enchantments;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.ArrayList;
import java.util.UUID;

public class FirstStrikeEnchantmentObject extends EnchantmentObject implements Listener {

    private ArrayList<UUID> attacked;

    public FirstStrikeEnchantmentObject() {
        super(EnchantmentType.FIRST_STRIKE);
        attacked = new ArrayList<>();
    }

    public FirstStrikeEnchantmentObject(int level) {
        super(EnchantmentType.FIRST_STRIKE, level);
        attacked = new ArrayList<>();
    }

    @Override
    public double getDamagePercentIncrease(LivingEntity damager, LivingEntity target) {
        if (attacked.contains(target.getUniqueId())) {
            return 0;
        }
        attacked.add(target.getUniqueId());
        return super.getLevel() * 25;
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        attacked.remove(event.getEntity().getUniqueId());
    }

}