package me.notpseudo.revolutionsmp.statobjects;

import java.io.Serializable;

// Weapon stats stored in an ItemStack's PersistentDataContainer
public class WeaponStats implements Serializable {

  private double damage;
  private final double baseDamage;
  private double strength;
  private final double baseStrength;
  private double critChance;
  private final double baseCritChance;
  private double critDamage;
  private final double baseCritDamage;
  private double ferocity;
  private final double baseFerocity;

  public WeaponStats( double damage, double strength, double critChance, double critDamage, double ferocity) {
    this.damage = damage;
    this.baseDamage = damage;
    this.strength = strength;
    this.baseStrength = strength;
    this.critChance = critChance;
    this.baseCritChance = critChance;
    this.critDamage = critDamage;
    this.baseCritDamage = critDamage;
    this.ferocity = ferocity;
    this.baseFerocity = ferocity;
  }

  public double getDamage() {
    return damage;
  }

  public void setDamage(double damage) {
    this.damage = damage;
  }

  public double getBaseDamage() {
    return baseDamage;
  }

  public double getStrength() {
    return strength;
  }

  public void setStrength(double strength) {
    this.strength = strength;
  }

  public double getBaseStrength() {
    return baseStrength;
  }

  public double getCritChance() {
    return critChance;
  }

  public void setCritChance(double critChance) {
    this.critChance = critChance;
  }

  public double getBaseCritChance() {
    return baseCritChance;
  }

  public double getCritDamage() {
    return critDamage;
  }

  public double getBaseCritDamage() {
    return baseCritDamage;
  }

  public void setCritDamage(double critDamage) {
    this.critDamage = critDamage;
  }

  public double getFerocity() {
    return ferocity;
  }

  public void setFerocity(double ferocity) {
    this.ferocity = ferocity;
  }

  public double getBaseFerocity() {
    return baseFerocity;
  }

}
