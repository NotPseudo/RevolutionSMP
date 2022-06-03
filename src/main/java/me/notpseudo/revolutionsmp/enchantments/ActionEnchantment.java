package me.notpseudo.revolutionsmp.enchantments;

import org.bukkit.entity.LivingEntity;

public interface ActionEnchantment {

    public void action(LivingEntity damager, LivingEntity target, double damage, boolean critical, double showDamage);

}
