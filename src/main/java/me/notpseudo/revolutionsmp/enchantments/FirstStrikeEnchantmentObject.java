package me.notpseudo.revolutionsmp.enchantments;

import me.notpseudo.revolutionsmp.itemstats.StatObject;
import me.notpseudo.revolutionsmp.itemstats.StatType;
import me.notpseudo.revolutionsmp.itemstats.WeaponStats;
import me.notpseudo.revolutionsmp.listeners.IncreaseType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.UUID;

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
            return new WeaponStats(1, 1, 1, 1, 1, 1);
        }
        if (!super.getAttacked().containsKey(target.getUniqueId())) {
            return new WeaponStats(0, 0, 0, 0, 0, 0);
        }
        if (inc == IncreaseType.ADDITIVE_PERCENT) {
            return new WeaponStats(25 * super.getLevel(), 0, 0, 0, 0, 0);
        }
        return new WeaponStats(0, 0, 0, 0, 0, 0);
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        super.getAttacked().remove(event.getEntity().getUniqueId());
    }

}