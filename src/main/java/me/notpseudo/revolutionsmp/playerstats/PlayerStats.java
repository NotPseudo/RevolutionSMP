package me.notpseudo.revolutionsmp.playerstats;

import java.io.Serializable;

public class PlayerStats implements Serializable {

    private double maxHealth;
    private double currentHealth;
    private double defense;
    private double speed;
    private double strength;
    private double critChance;
    private double critDamage;
    private double attackSpeed;
    private double ferocity;
    private double intelligence;
    private double abilityDamage;
    private double mana;

    public PlayerStats() {
        maxHealth = 100;
        defense = 0;
        speed = 100;
        strength = 0;
        critChance = 30;
        critDamage = 50;
        attackSpeed = 0;
        ferocity = 0;
        intelligence = 100;
        abilityDamage = 0;
        currentHealth = 100;
        mana = 100;
    }

    public PlayerStats(double maxHealth, double defense, double speed, double strength, double critChance, double critDamage, double attackSpeed, double ferocity, double intelligence, double abilityDamage) {
        this.maxHealth = maxHealth;
        this.defense = defense;
        this.speed = speed;
        this.strength = strength;
        this.critChance = critChance;
        this.critDamage = critDamage;
        this.attackSpeed = attackSpeed;
        this.ferocity = ferocity;
        this.intelligence = intelligence;
        this.abilityDamage = abilityDamage;
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

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public double getFerocity() {
        return ferocity;
    }

    public void setFerocity(double ferocity) {
        this.ferocity = ferocity;
    }

    public double getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(double intelligence) {
        this.intelligence = intelligence;
    }

    public double getAbilityDamage() {
        return abilityDamage;
    }

    public void setAbilityDamage(double abilityDamage) {
        this.abilityDamage = abilityDamage;
    }

    public double getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(double currentHealth) {
        this.currentHealth = currentHealth;
    }

    public double getMana() {
        return mana;
    }

    public void setMana(double mana) {
        this.mana = mana;
    }

}