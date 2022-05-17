package me.notpseudo.revolutionsmp.playerstats;

import me.notpseudo.revolutionsmp.mobstats.BaseEntityStats;

import java.io.Serializable;

public class PlayerStats extends BaseEntityStats implements Serializable {

    private double attackSpeed;
    private double ferocity;
    private double intelligence;
    private double abilityDamage;
    private double mana;
    private double healthRegenRate;
    private double manaRegenRate;

    public PlayerStats() {
        super(100, 0, 100, 0, 30, 50);
        attackSpeed = 0;
        ferocity = 0;
        intelligence = 100;
        abilityDamage = 0;
        mana = 100;
        healthRegenRate = 1;
        manaRegenRate = 1;
    }

    public PlayerStats(double maxHealth, double defense, double speed, double strength, double critChance, double critDamage, double attackSpeed, double ferocity, double intelligence, double abilityDamage) {
        super(maxHealth, defense, speed, strength, critChance, critDamage);
        this.attackSpeed = attackSpeed;
        this.ferocity = ferocity;
        this.intelligence = intelligence;
        this.abilityDamage = abilityDamage;
        healthRegenRate = 1;
        manaRegenRate = 1;
    }

    public PlayerStats(double maxHealth, double defense, double speed, double strength, double critChance, double critDamage, double attackSpeed, double ferocity, double intelligence, double abilityDamage, double healthRegenRate, double manaRegenRate) {
        super(maxHealth, defense, speed, strength, critChance, critDamage);
        this.attackSpeed = attackSpeed;
        this.ferocity = ferocity;
        this.intelligence = intelligence;
        this.abilityDamage = abilityDamage;
        this.healthRegenRate = healthRegenRate;
        this.manaRegenRate = manaRegenRate;
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

    public double getMana() {
        return mana;
    }

    public void setMana(double mana) {
        this.mana = mana;
    }

    public double getHealthRegenRate() {
        return healthRegenRate;
    }

    public void setHealthRegenRate(double healthRegenRate) {
        this.healthRegenRate = healthRegenRate;
    }

    public double getManaRegenRate() {
        return manaRegenRate;
    }

    public void setManaRegenRate(double manaRegenRate) {
        this.manaRegenRate = manaRegenRate;
    }

}