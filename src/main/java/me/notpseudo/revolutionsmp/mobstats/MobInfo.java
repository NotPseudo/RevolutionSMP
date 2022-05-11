package me.notpseudo.revolutionsmp.mobstats;

import java.io.Serializable;

public class MobInfo extends BaseEntityStats implements Serializable {

    private double maxHealth;
    private double currentHealth;
    private double defense;
    private double speed;
    private double strength;
    private double critChance;
    private double critDamage;

    private MobBehavior mobBehavior;
    private VanillaMobType vanillaMobType;
    private CustomMobType customMobType;
    private String name;
    private int level;
    private double damage;

    public MobInfo(CustomMobType customMobType) {
        super(customMobType);
        this.vanillaMobType = ;
        name = customMobType.getName();
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(double currentHealth) {
        this.currentHealth = currentHealth;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public double getDefense() {
        return defense;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public double getCritChance() {
        return critChance;
    }

    public void setCritChance(double critChance) {
        this.critChance = critChance;
    }

    public double getCritDamage() {
        return critDamage;
    }

    public void setCritDamage(double critDamage) {
        this.critDamage = critDamage;
    }

    public MobBehavior getMobBehavior() {
        return mobBehavior;
    }

    public void setMobBehavior(MobBehavior mobBehavior) {
        this.mobBehavior = mobBehavior;
    }

    public VanillaMobType getVanillaMobType() {
        return vanillaMobType;
    }

    public void setVanillaMobType(VanillaMobType vanillaMobType) {
        this.vanillaMobType = vanillaMobType;
    }

    public CustomMobType getCustomMobType() {
        return customMobType;
    }

    public void setCustomMobType(CustomMobType customMobType) {
        this.customMobType = customMobType;
    }

    public void updateMobStats() {
        customMobType = vanillaMobType.getCustomTypes()[(int) (Math.random() * vanillaMobType.getCustomTypes().length)];
        mobBehavior = customMobType.getMobBehavior();
        level = (int) (Math.random() * (mobBehavior.getHighestLevel() - mobBehavior.getLowestLevel() + 1)) + mobBehavior.getLowestLevel();
        int increase = level - mobBehavior.getLowestLevel();
        maxHealth = customMobType.getHealth() + (increase * customMobType.getHealth() / 10);
        currentHealth = customMobType.getHealth() + (increase * customMobType.getHealth() / 10);
        defense = customMobType.getDefense() + (increase * customMobType.getDefense() / 10);
        speed = customMobType.getSpeed() + (increase * customMobType.getSpeed() / 10);
        damage = customMobType.getDamage() + (increase * customMobType.getDamage() / 10);
        strength = customMobType.getStrength() + (increase * customMobType.getStrength() / 10);
        critChance = customMobType.getCritChance() + (increase * customMobType.getCritChance() / 10);
        critDamage = customMobType.getCritDamage() + (increase * customMobType.getCritDamage() / 10);
    }

}
