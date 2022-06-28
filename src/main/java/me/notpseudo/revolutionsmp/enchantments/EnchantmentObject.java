package me.notpseudo.revolutionsmp.enchantments;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class EnchantmentObject implements Serializable, Comparable<EnchantmentObject> {

    private final EnchantmentType type;
    private int level;
    private int enchTableMax;
    private int maxLevel;
    private HashMap<UUID, Integer> attacked;
    private UUID lastHit;
    private int hitCount;


    public EnchantmentObject(EnchantmentType type) {
        this.type = type;
        level = type.getMinLevel();
        enchTableMax = type.getEnchTableMax();
        maxLevel = type.getMaxLevel();
        attacked = new HashMap<>();
        hitCount = 0;
    }

    public EnchantmentObject(EnchantmentType type, int level) {
        this.type = type;
        this.level = level;
        enchTableMax = type.getEnchTableMax();
        maxLevel = type.getMaxLevel();
        attacked = new HashMap<>();
        hitCount = 0;
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

    public HashMap<UUID, Integer> getAttacked() {
        return attacked;
    }

    public void setAttacked(HashMap<UUID, Integer> attacked) {
        this.attacked = attacked;
    }

    public UUID getLastHit() {
        return lastHit;
    }

    public void setLastHit(UUID lastHit) {
        this.lastHit = lastHit;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    public String getText() {
        if (type.isUltimate()) {
            return "" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + type.toString() + " " + level;
        } else if (level >= type.getMaxLevel()) {
            return "" + ChatColor.GOLD + type.toString() + " " + level;
        } else if (level > type.getEnchTableMax()) {
            return "" + ChatColor.DARK_PURPLE + type.toString() + " " + level;
        } else {
            return "" + ChatColor.BLUE + type.toString() + " " + level;
        }
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