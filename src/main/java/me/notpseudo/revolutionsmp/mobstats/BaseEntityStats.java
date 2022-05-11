package me.notpseudo.revolutionsmp.mobstats;

import java.io.Serializable;

public class BaseEntityStats implements Serializable {
    private double maxHealth;
    private double currentHealth;
    private double defense;
    private double speed;
    private double strength;
    private double critChance;
    private double critDamage;

    public BaseEntityStats(double maxHealth, double defense, double speed, double strength, double critChance, double critDamage) {
        this.maxHealth = maxHealth;
        currentHealth = maxHealth;
        this.defense = defense;
        this.speed = speed;
        this.strength = strength;
        this.critChance = critChance;
        this.critDamage = critDamage;
    }

    public BaseEntityStats(CustomMobType customMobType) {
        updateMobStats(customMobType);
    }

    public void updateMobStats(CustomMobType customMobType) {
        MobBehavior mobBehavior = customMobType.getMobBehavior();
        int level = (int) (Math.random() * (mobBehavior.getHighestLevel() - mobBehavior.getLowestLevel() + 1)) + mobBehavior.getLowestLevel();
        int increase = level - mobBehavior.getLowestLevel();
        maxHealth = customMobType.getHealth() + (increase * customMobType.getHealth() / 10);
        currentHealth = maxHealth;
        defense = customMobType.getDefense() + (increase * customMobType.getDefense() / 10);
        speed = customMobType.getSpeed() + (increase * customMobType.getSpeed() / 10);
        strength = customMobType.getStrength() + (increase * customMobType.getStrength() / 10);
        critChance = customMobType.getCritChance() + (increase * customMobType.getCritChance() / 10);
        critDamage = customMobType.getCritDamage() + (increase * customMobType.getCritDamage() / 10);
    }
}
