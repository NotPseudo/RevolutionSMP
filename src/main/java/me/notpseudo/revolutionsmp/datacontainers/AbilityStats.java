package me.notpseudo.revolutionsmp.datacontainers;

import me.notpseudo.revolutionsmp.abilities.Ability;

import java.io.Serializable;

public class AbilityStats implements Serializable {

  private double abilityDamage;
  private double baseAbilityDamage;
  private double intelligence;
  private double baseIntelligence;
  private Ability rightClickAbility;
  private boolean showRight;
  private Ability sneakRightClickAbility;
  private boolean showSneakRight;
  private Ability leftClickAbility;
  private boolean showLeft;

  public AbilityStats(double abilityDamage, double intelligence, Ability rightClickAbility, boolean showRight, Ability sneakRightClickAbility, boolean showSneakRight, Ability leftClickAbility, boolean showLeft) {
    this.abilityDamage = abilityDamage;
    this.baseAbilityDamage = abilityDamage;
    this.intelligence = intelligence;
    this.baseIntelligence = intelligence;
    this.rightClickAbility = rightClickAbility;
    this.showRight = showRight;
    this.sneakRightClickAbility = sneakRightClickAbility;
    this.showSneakRight = showSneakRight;
    this.leftClickAbility = leftClickAbility;
    this.showLeft = showLeft;
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

  public Ability getRightClickAbility() {
    return rightClickAbility;
  }

  public void setRightClickAbility(Ability rightClickAbility) {
    this.rightClickAbility = rightClickAbility;
  }

  public boolean isShowRight() {
    return showRight;
  }

  public void setShowRight(boolean showRight) {
    this.showRight = showRight;
  }

  public Ability getSneakRightClickAbility() {
    return sneakRightClickAbility;
  }

  public void setSneakRightClickAbility(Ability sneakRightClickAbility) {
    this.sneakRightClickAbility = sneakRightClickAbility;
  }

  public boolean isShowSneakRight() {
    return showSneakRight;
  }

  public void setShowSneakRight(boolean showSneakRight) {
    this.showSneakRight = showSneakRight;
  }

  public Ability getLeftClickAbility() {
    return leftClickAbility;
  }

  public void setLeftClickAbility(Ability leftClickAbility) {
    this.leftClickAbility = leftClickAbility;
  }

  public boolean isShowLeft() {
    return showLeft;
  }

  public void setShowLeft(boolean showLeft) {
    this.showLeft = showLeft;
  }

}
