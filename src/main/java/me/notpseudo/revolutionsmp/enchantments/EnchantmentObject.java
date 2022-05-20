package me.notpseudo.revolutionsmp.enchantments;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EnchantmentObject {

    private final EnchantmentType type;
    private int level;
    private int maxLevel;
    private double damage;

    public EnchantmentObject(EnchantmentType type) {
        this.type = type;
        level = type.getMinLevel();
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

    public double getDamageAdd(EntityDamageByEntityEvent event) {
        return 0;
    }

}