package me.notpseudo.revolutionsmp.mobstats;

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
    /**
     * The current health of the entity
     */
    private double currentHealth;
    /**
     * The defense of the entity
     */
    private double defense;
    /**
     * The movement speed of the entity
     */
    private double speed;
    /**
     * The strength of the entity
     */
    private double strength;
    /**
     * The critical hit chance percent
     */
    private double critChance;
    /**
     * The critical hit damage percent to add on
     */
    private double critDamage;

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
    public BaseEntityStats(double maxHealth, double defense, double speed, double strength, double critChance, double critDamage) {
        this.maxHealth = maxHealth;
        currentHealth = maxHealth;
        this.defense = defense;
        this.speed = speed;
        this.strength = strength;
        this.critChance = critChance;
        this.critDamage = critDamage;
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

    /**
     * Returns the current health of the entity
     *
     * @return The current health of the entity
     */
    public double getCurrentHealth() {
        return currentHealth;
    }

    /**
     * Sets the current health of the entity
     *
     * @param currentHealth The new current health
     */
    public void setCurrentHealth(double currentHealth) {
        this.currentHealth = currentHealth;
    }

    /**
     * Returns the defense of the entity
     *
     * @return The defense of the entity
     */
    public double getDefense() {
        return defense;
    }

    /**
     * Sets the defense of the entity
     *
     * @param defense The new defense
     */
    public void setDefense(double defense) {
        this.defense = defense;
    }

    /**
     * Returns the movement speed of the entity
     *
     * @return The movement speed of the entity
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Sets the movement speed of the entity
     *
     * @param speed The new movement speed
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Returns the strength of the entity
     *
     * @return The strength of the entity
     */

    public double getStrength() {
        return strength;
    }

    /**
     * Sets the strength of the entity
     *
     * @param strength The new strength
     */
    public void setStrength(double strength) {
        this.strength = strength;
    }

    /**
     * Returns the critical hit chance of the entity
     *
     * @return The critical hit chance of the entity
     */
    public double getCritChance() {
        return critChance;
    }

    /**
     * Sets the critical hit chance of the entity
     *
     * @param critChance The new critical hit chance
     */
    public void setCritChance(double critChance) {
        this.critChance = critChance;
    }

    /**
     * Returns the critical hit damage of the entity
     *
     * @return The critical hit damage of the entity
     */
    public double getCritDamage() {
        return critDamage;
    }

    /**
     * Sets the critical hit damage of the entity
     *
     * @param critDamage The new critical hit damage
     */
    public void setCritDamage(double critDamage) {
        this.critDamage = critDamage;
    }

    /**
     * Updates stats with a new CustomMobType and changes values based on the level
     *
     * @param customMobType The CustomMobType to get stat values from
     * @param level         The level of the mob used to correctly set stats
     */
    public void updateStats(CustomMobType customMobType, int level) {
        MobBehavior mobBehavior = customMobType.getMobBehavior();
        int increase = level - mobBehavior.getLowestLevel();
        maxHealth = customMobType.getHealth() + (increase * customMobType.getHealth() / 10);
        currentHealth = maxHealth;
        defense = customMobType.getDefense() + (increase * customMobType.getDefense() / (100 / 7.5));
        speed = customMobType.getSpeed() + (increase * customMobType.getSpeed() / 50);
        strength = customMobType.getStrength() + (increase * customMobType.getStrength() / 10);
        critChance = customMobType.getCritChance() + (increase * customMobType.getCritChance() / 20);
        critDamage = customMobType.getCritDamage() + (increase * customMobType.getCritDamage() / 10);
    }
}
