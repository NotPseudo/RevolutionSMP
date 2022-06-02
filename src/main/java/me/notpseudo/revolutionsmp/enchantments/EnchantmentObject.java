package me.notpseudo.revolutionsmp.enchantments;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.jetbrains.annotations.NotNull;

public class EnchantmentObject implements Comparable<EnchantmentObject>{

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public EnchantmentType getType() {
        return type;
    }

    public double getAddDamage(EntityDamageByEntityEvent event) {
        return type.getAddDamage(event, level);
    }

    public double getDamagePercentIncrease(LivingEntity damager, LivingEntity target) {
        return type.getDamagePercentIncrease(damager, target, level);
    }

    public double getMultDamage(EntityDamageByEntityEvent event) {
        return type.getMultDamage(event, level);
    }

    @Override
    public int compareTo(@NotNull EnchantmentObject o) {
        return type.toString().compareTo(o.getType().toString());
    }

}