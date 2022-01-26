package me.notpseudo.revolutionsmp.statobjects;

import java.io.Serializable;

// Armor stats stored in an ItemStack's PersistentDataContainer
public class ArmorStats implements Serializable {

  private double health;
  private final double baseHealth;
  private double defense;
  private final double baseDefense;
  private double speed;
  private final double baseSpeed;

  public ArmorStats(double health, double defense, double speed) {
    this.health = health;
    this.baseHealth = health;
    this.defense = defense;
    this.baseDefense = defense;
    this.speed = speed;
    this.baseSpeed = speed;
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

}