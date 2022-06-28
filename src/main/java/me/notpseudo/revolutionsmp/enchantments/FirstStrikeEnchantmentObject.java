package me.notpseudo.revolutionsmp.enchantments;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.ArrayList;
import java.util.UUID;

public class FirstStrikeEnchantmentObject extends EnchantmentObject implements Listener {

    public FirstStrikeEnchantmentObject() {
        super(EnchantmentType.FIRST_STRIKE);
    }

    public FirstStrikeEnchantmentObject(int level) {
        super(EnchantmentType.FIRST_STRIKE, level);
    }

    @Override
    public double getDamagePercentIncrease(LivingEntity damager, LivingEntity target) {
        UUID targetUUID = target.getUniqueId();
        if (super.getAttacked().containsKey(targetUUID)) {
            return 0;
        }
        super.getAttacked().put(targetUUID, 0);
        return super.getLevel() * 25;
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        super.getAttacked().remove(event.getEntity().getUniqueId());
    }

}