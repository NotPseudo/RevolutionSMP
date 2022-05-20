package me.notpseudo.revolutionsmp.itemstats;


import java.io.Serializable;

// Ability stats stored in an ItemStack's PersistentDataContainer
public class AbilityStats implements Serializable {

  private double abilityDamage;
  private final double baseAbilityDamage;
  private double intelligence;
  private final double baseIntelligence;

  public AbilityStats(double abilityDamage, double intelligence) {
    this.abilityDamage = abilityDamage;
    this.baseAbilityDamage = abilityDamage;
    this.intelligence = intelligence;
    this.baseIntelligence = intelligence;
  }

  public double getAbilityDamage() {
    return abilityDamage;
  }

  public void setAbilityDamage(double abilityDamage) {
    this.abilityDamage = abilityDamage;
  }

  public double getBaseAbilityDamage() {
    return baseAbilityDamage;
  }

  public double getIntelligence() {
    return intelligence;
  }

  public void setIntelligence(double intelligence) {
    this.intelligence = intelligence;
  }

  public double getBaseIntelligence() {
    return baseIntelligence;
  }

  public void combine(AbilityStats other) {
    if(other == null) return;
    abilityDamage += other.abilityDamage;
    intelligence += other.intelligence;
  }

}