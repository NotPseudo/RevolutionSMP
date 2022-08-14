package me.notpseudo.revolutionsmp.mobstats;

import me.notpseudo.revolutionsmp.itemstats.ArmorStats;
import me.notpseudo.revolutionsmp.itemstats.StatCategory;
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
        MobCategory mobCategory = customMobType.getMobBehavior();
        int increase = 0;
        if (customMobType.getMobBehavior() != MobCategory.SEA_CREATURE) {
            increase = level - mobCategory.getLowestLevel();
        }
        maxHealth = customMobType.getHealth() + (increase * customMobType.getHealth() / 10);
        double damage = customMobType.getDamage() + (increase * customMobType.getDamage() / 10);
        double defense = customMobType.getDefense() + (increase * customMobType.getDefense() / (100 / 7.5));
        double speed = customMobType.getSpeed() + (increase * customMobType.getSpeed() / 50);
        double strength = customMobType.getStrength() + (increase * customMobType.getStrength() / 10);
        double critChance = customMobType.getCritChance() + (increase * customMobType.getCritChance() / 20);
        double critDamage = customMobType.getCritDamage() + (increase * customMobType.getCritDamage() / 10);
        healthStats = new ArmorStats(maxHealth, defense, speed);
        damageStats = new WeaponStats(damage, strength, critChance, critDamage, 0, 0);
        healthStatsMult = ArmorStats.createMult();
        damageStatsMult = WeaponStats.createMult();
    }
}
