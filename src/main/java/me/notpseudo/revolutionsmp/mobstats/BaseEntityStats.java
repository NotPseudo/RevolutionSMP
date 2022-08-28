package me.notpseudo.revolutionsmp.mobstats;

import me.notpseudo.revolutionsmp.itemstats.ArmorStats;
import me.notpseudo.revolutionsmp.itemstats.StatType;
import me.notpseudo.revolutionsmp.itemstats.WeaponStats;

import java.io.Serializable;

/**
 * This class holds basic stats that both players and mobs can have
 *
 * @author NotPseudo
 */
public class BaseEntityStats implements Serializable {
    /**
     * The maximum health the entity can have
     */
    private double maxHealth;
    private ArmorStats healthStats;
    private WeaponStats damageStats;

    private ArmorStats healthStatsMult;

    private WeaponStats damageStatsMult;

    /**
     * Creates a new BaseEntityStats object with pre-known values, usually for players
     *
     * @param maxHealth  The maximum health of the entity
     * @param defense    The defense of the entity
     * @param speed      The movement speed of the entity
     * @param strength   The strength of the entity
     * @param critChance The critical hit chance
     * @param critDamage The critical hit damage
     */
    public BaseEntityStats(double maxHealth, double defense, double speed, double strength, double critChance, double critDamage, double attackSpeed, double ferocity) {
        this.maxHealth = maxHealth;
        healthStats = new ArmorStats(maxHealth, defense, speed);
        damageStats = new WeaponStats(0, strength, critChance, critDamage, attackSpeed, ferocity);
        healthStatsMult = ArmorStats.createMult();
        damageStatsMult = WeaponStats.createMult();
    }

    /**
     * Creates a new BaseEntityStats using values from a CustomMobType, used for mobs
     *
     * @param customMobType The CustomMobType to get stat values from
     * @param level         The level of the mob used to correctly set stats
     */
    public BaseEntityStats(CustomMobType customMobType, int level) {
        updateStats(customMobType, level);
    }

    /**
     * Returns the maximum health of the entity
     *
     * @return The maximum health
     */
    public double getMaxHealth() {
        return maxHealth;
    }

    /**
     * Sets the maximum health of the entity
     *
     * @param maxHealth The new maximum health
     */
    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setHealthStats(ArmorStats armor) {
        healthStats = armor;
    }

    public void setDamageStats(WeaponStats weapon) {
        damageStats = weapon;
    }

    public double getStatValue(StatType type) {
        return switch (type.getStatCategory()) {
            case COMBAT -> damageStats.getStatValue(type);
            case ARMOR -> healthStats.getStatValue(type);
            default -> 0;
        };
    }

    public void setStatValue(StatType type, double value) {
        switch (type.getStatCategory()) {
            case COMBAT -> damageStats.setStatValue(type, value);
            case ARMOR -> healthStats.setStatValue(type, value);
        }
    }

    public double getStatMultValue(StatType type) {
        return switch (type.getStatCategory()) {
            case COMBAT -> damageStatsMult.getStatValue(type);
            case ARMOR -> healthStatsMult.getStatValue(type);
            default -> 1;
        };
    }
    public void setStatMultValue(StatType type, double value) {
        switch (type.getStatCategory()) {
            case COMBAT -> damageStatsMult.setStatValue(type, value);
            case ARMOR -> healthStatsMult.setStatValue(type, value);
        }
    }

    /**
     * Updates stats with a new CustomMobType and changes values based on the level
     *
     * @param customMobType The CustomMobType to get stat values from
     * @param level         The level of the mob used to correctly set stats
     */
    public void updateStats(CustomMobType customMobType, int level) {
        double multiplier = 1;
        if (customMobType.getMobBehavior() != MobCategory.SEA_CREATURE) {
            int low = customMobType.getMinLevel(), high = customMobType.getMaxLevel();
            int range = high - low;
            double average = low + high / 2.0;
            multiplier = ((level - average) / range) + 1;
        }
        double difference = multiplier - 1;
        maxHealth = customMobType.getHealth() * multiplier;
        double damage = customMobType.getDamage() * multiplier;
        double defense = customMobType.getDefense() * (1 + difference * 0.75);
        double speed = customMobType.getSpeed() * (1 + difference * 0.2);
        double strength = customMobType.getStrength() * multiplier;
        double critChance = customMobType.getCritChance() * (1 + difference * 0.75);
        double critDamage = customMobType.getCritDamage() * multiplier;
        healthStats = new ArmorStats(maxHealth, defense, speed);
        damageStats = new WeaponStats(damage, strength, critChance, critDamage, 0, 0);
        healthStatsMult = ArmorStats.createMult();
        damageStatsMult = WeaponStats.createMult();
    }
}
