package me.notpseudo.revolutionsmp.enchantments;

import me.notpseudo.revolutionsmp.itemstats.WeaponStats;
import me.notpseudo.revolutionsmp.itemstats.IncreaseType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class FirstStrikeEnchantmentObject extends EnchantmentObject implements Listener {

    public FirstStrikeEnchantmentObject() {
        super(EnchantmentType.FIRST_STRIKE);
    }

    public FirstStrikeEnchantmentObject(int level) {
        super(EnchantmentType.FIRST_STRIKE, level);
    }

    @Override
    public WeaponStats getEventWeapon(Player damager, LivingEntity target, IncreaseType inc) {
        if (inc == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return WeaponStats.createMult();
        }
        if (!super.getAttacked().containsKey(target.getUniqueId())) {
            return WeaponStats.createZero();
        }
        if (inc == IncreaseType.ADDITIVE_PERCENT) {
            return new WeaponStats(25 * super.getLevel(), 0, 0, 0, 0, 0);
        }
        return WeaponStats.createZero();
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        super.getAttacked().remove(event.getEntity().getUniqueId());
    }

}