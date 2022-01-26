package me.notpseudo.revolutionsmp.statobjects;

import me.notpseudo.revolutionsmp.abilities.Ability;

import java.io.Serializable;
import java.util.List;

// Ability stats stored in an ItemStack's PersistentDataContainer
public class AbilityStats implements Serializable {

  private double abilityDamage;
  private final double baseAbilityDamage;
  private double intelligence;
  private final double baseIntelligence;
  private List<Ability> abilityList;

  public AbilityStats(double abilityDamage, double intelligence, List<Ability> abilityList) {
    this.abilityDamage = abilityDamage;
    this.baseAbilityDamage = abilityDamage;
    this.intelligence = intelligence;
    this.baseIntelligence = intelligence;
    this.abilityList = abilityList;
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

  public void setAbilityList(List<Ability> abilityList) {
    this.abilityList = abilityList;
  }

  public void addAbility(Ability ability) {
    abilityList.add(ability);
  }

  public List<Ability> getAbilityList() {
    return abilityList;
  }

}