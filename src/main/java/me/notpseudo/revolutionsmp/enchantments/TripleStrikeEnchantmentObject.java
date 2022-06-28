package me.notpseudo.revolutionsmp.enchantments;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.HashMap;
import java.util.UUID;

public class TripleStrikeEnchantmentObject extends EnchantmentObject implements Listener {

    public TripleStrikeEnchantmentObject() {
        super(EnchantmentType.TRIPLE_STRIKE);
    }

    public TripleStrikeEnchantmentObject(int level) {
        super(EnchantmentType.TRIPLE_STRIKE, level);
    }

    @Override
    public double getDamagePercentIncrease(LivingEntity damager, LivingEntity target) {
        UUID targetUUID = target.getUniqueId();
        if (!super.getAttacked().containsKey(targetUUID)) {
            super.getAttacked().put(targetUUID, 0);
        }
        if (super.getAttacked().get(targetUUID) >= 3) {
            return 0;
        }
        super.getAttacked().put(targetUUID, super.getAttacked().get(targetUUID) + 1);
        return super.getLevel() * 10;
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        super.getAttacked().remove(event.getEntity().getUniqueId());
    }

}