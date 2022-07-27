package me.notpseudo.revolutionsmp.itemstats;

import java.io.Serializable;

// Armor stats stored in an ItemStack's PersistentDataContainer
public class ArmorStats implements Serializable {

  private double health;
  private final double baseHealth;
  private double defense;
  private final double baseDefense;
  private double speed;
  private final double baseSpeed;
  private double trueDefense;
  private final double baseTrueDefense;

  public ArmorStats(double health, double defense, double speed) {
    this.health = health;
    this.baseHealth = health;
    this.defense = defense;
    this.baseDefense = defense;
    this.speed = speed;
    this.baseSpeed = speed;
    trueDefense = 0;
    baseTrueDefense = 0;
  }

  public ArmorStats(double health, double defense, double speed, double trueDefense) {
    this.health = health;
    this.baseHealth = health;
    this.defense = defense;
    this.baseDefense = defense;
    this.speed = speed;
    this.baseSpeed = speed;
    this.trueDefense = trueDefense;
    this.baseTrueDefense = trueDefense;
  }

  public double getHealth() {
    return health;
  }

  public void setHealth(double health) {
    this.health = health;
  }

  public double getBaseHealth() {
    return baseHealth;
  }

  public double getDefense() {
    return defense;
  }

  public void setDefense(double defense) {
    this.defense = defense;
  }

  public double getBaseDefense() {
    return baseDefense;
  }

  public double getSpeed() {
    return speed;
  }

  public void setSpeed(double speed) {
    this.speed = speed;
  }

  public double getBaseSpeed() {
    return baseSpeed;
  }

  public double getTrueDefense() {
    return trueDefense;
  }

  public void setTrueDefense(double trueDefense) {
    this.trueDefense = trueDefense;
  }

  public double getBaseTrueDefense() {
    return baseTrueDefense;
  }

  public void combine(ArmorStats other) {
    if (other == null) {
      return;
    }
    health += other.health;
    defense += other.defense;
    speed += other.speed;
    trueDefense += other.trueDefense;
  }

}