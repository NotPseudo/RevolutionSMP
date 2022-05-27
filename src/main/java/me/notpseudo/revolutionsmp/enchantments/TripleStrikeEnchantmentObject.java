package me.notpseudo.revolutionsmp.enchantments;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TripleStrikeEnchantmentObject extends EnchantmentObject implements Listener {

    private ConcurrentHashMap<UUID, Integer> hitMobs;

    public TripleStrikeEnchantmentObject() {
        super(EnchantmentType.TRIPLE_STRIKE);
        hitMobs = new ConcurrentHashMap<>();
    }

    public TripleStrikeEnchantmentObject(int level) {
        super(EnchantmentType.TRIPLE_STRIKE, level);
        hitMobs = new ConcurrentHashMap<>();
    }

    @Override
    public double getDamagePercentIncrease(LivingEntity damager, LivingEntity target) {
        UUID targetUUID = target.getUniqueId();
        if (!hitMobs.containsKey(targetUUID)) {
            hitMobs.put(targetUUID, 0);
        }
        if (hitMobs.get(targetUUID) >= 3) {
            return 0;
        }
        hitMobs.put(targetUUID, hitMobs.get(targetUUID) + 1);
        return super.getLevel() * 10;
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        hitMobs.remove(event.getEntity().getUniqueId());
    }

}