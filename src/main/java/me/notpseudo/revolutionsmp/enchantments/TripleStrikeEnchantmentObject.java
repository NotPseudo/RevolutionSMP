package me.notpseudo.revolutionsmp.enchantments;

import me.notpseudo.revolutionsmp.itemstats.StatObject;
import me.notpseudo.revolutionsmp.itemstats.StatType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.UUID;

public class TripleStrikeEnchantmentObject extends EnchantmentObject implements Listener {

    public TripleStrikeEnchantmentObject() {
        super(EnchantmentType.TRIPLE_STRIKE);
    }

    public TripleStrikeEnchantmentObject(int level) {
        super(EnchantmentType.TRIPLE_STRIKE, level);
    }

    @Override
    public StatObject getDamageStatAdditivePercent(LivingEntity damager, LivingEntity target, StatType type) {
        UUID targetUUID = target.getUniqueId();
        if (!super.getAttacked().containsKey(targetUUID)) {
            super.getAttacked().put(targetUUID, 0);
        }
        if (super.getAttacked().get(targetUUID) >= 3) {
            return new StatObject(StatType.DAMAGE, 0);
        }
        super.getAttacked().put(targetUUID, super.getAttacked().get(targetUUID) + 1);
        return new StatObject(StatType.DAMAGE, super.getLevel() * 10);
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        super.getAttacked().remove(event.getEntity().getUniqueId());
    }

}