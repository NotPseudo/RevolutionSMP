package me.notpseudo.revolutionsmp.enchantments;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EnchantmentObject {

    private final EnchantmentType type;
    private int level;
    private int enchTableMax;
    private int maxLevel;

    public EnchantmentObject(EnchantmentType type) {
        this.type = type;
        level = type.getMinLevel();
        enchTableMax = type.getEnchTableMax();
        maxLevel = type.getMaxLevel();
    }

    public EnchantmentObject(EnchantmentType type, int level) {
        this.type = type;
        this.level = level;
        maxLevel = type.getMaxLevel();
    }

    public EnchantmentType getType() {
        return type;
    }

    public double getAddDamage(EntityDamageByEntityEvent event) {
        return type.getAddDamage(event, level);
    }

    public double getAddMultDamage(EntityDamageByEntityEvent event) {
        return type.getAddDamageMult(event, level);
    }

    public double getMultDamage(EntityDamageByEntityEvent event) {
        return type.getMultDamage(event, level);
    }

}