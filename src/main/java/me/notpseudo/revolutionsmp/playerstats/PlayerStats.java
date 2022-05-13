package me.notpseudo.revolutionsmp.playerstats;

import me.notpseudo.revolutionsmp.mobstats.BaseEntityStats;

import java.io.Serializable;

public class PlayerStats extends BaseEntityStats implements Serializable {

    private double attackSpeed;
    private double ferocity;
    private double intelligence;
    private double abilityDamage;
    private double mana;

    public PlayerStats() {
        super(100, 0, 100, 0, 30, 50);
        attackSpeed = 0;
        ferocity = 0;
        intelligence = 100;
        abilityDamage = 0;
        mana = 100;
    }

    public PlayerStats(double maxHealth, double defense, double speed, double strength, double critChance, double critDamage, double attackSpeed, double ferocity, double intelligence, double abilityDamage) {
        super(maxHealth, defense, speed, strength, critChance, critDamage);
        this.attackSpeed = attackSpeed;
        this.ferocity = ferocity;
        this.intelligence = intelligence;
        this.abilityDamage = abilityDamage;
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

}