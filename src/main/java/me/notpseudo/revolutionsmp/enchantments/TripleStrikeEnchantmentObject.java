package me.notpseudo.revolutionsmp.enchantments;

import me.notpseudo.revolutionsmp.itemstats.WeaponStats;
import me.notpseudo.revolutionsmp.itemstats.IncreaseType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class TripleStrikeEnchantmentObject extends EnchantmentObject implements Listener {

    public TripleStrikeEnchantmentObject() {
        super(EnchantmentType.TRIPLE_STRIKE);
    }

    public TripleStrikeEnchantmentObject(int level) {
        super(EnchantmentType.TRIPLE_STRIKE, level);
    }

    @Override
    public WeaponStats getEventWeapon(Player damager, LivingEntity target, IncreaseType inc) {
        if (inc == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return WeaponStats.createMult();
        }
        if (inc == IncreaseType.ADDITIVE_PERCENT) {
            return new WeaponStats(10 * super.getLevel(), 0, 0, 0, 0, 0);
        }
        return WeaponStats.createZero();
    }

    /*
    @Override
    public StatObject getDamageStatAddPercent(LivingEntity damager, LivingEntity target, StatType type) {
        if (!super.getAttacked().containsKey(targetUUID)) {
            super.getAttacked().put(targetUUID, 0);
        }
        if (super.getAttacked().get(targetUUID) >= 3) {
            return new StatObject(StatType.DAMAGE, 0);
        }
        super.getAttacked().put(targetUUID, super.getAttacked().get(targetUUID) + 1);
        return new StatObject(StatType.DAMAGE, super.getLevel() * 10);
    }
    */

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        super.getAttacked().remove(event.getEntity().getUniqueId());
    }

}