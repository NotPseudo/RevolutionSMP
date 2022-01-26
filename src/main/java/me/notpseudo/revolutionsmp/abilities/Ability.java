package me.notpseudo.revolutionsmp.abilities;

import org.bukkit.entity.Player;

// Enum of abilities that can be placed on items
public enum Ability {
  INSTANT_TRANSMISSION {
    @Override
    public void use(Player player) {
      Abilities.instantTransmission(player);
    }
    @Override
    public String getAbilityName() {
      return "Instant Transmission";
    }
    @Override
    public AbilityType getAbilityType() {
      return AbilityType.RIGHT_CLICK;
    }
  },
  WITHER_IMPACT {
    @Override
    public void use(Player player) {

    }
    @Override
    public String getAbilityName() {
      return "Wither Impact";
    }
    @Override
    public AbilityType getAbilityType() {
      return AbilityType.RIGHT_CLICK;
    }
  },
  IMPLOSION {
    @Override
    public void use(Player player) {

    }
    @Override
    public String getAbilityName() {
      return "Implosion";
    }
    @Override
    public AbilityType getAbilityType() {
      return AbilityType.RIGHT_CLICK;
    }
  },
  WITHER_SHIELD {
    @Override
    public void use(Player player) {

    }
    @Override
    public String getAbilityName() {
      return "Wither Shield";
    }
    @Override
    public AbilityType getAbilityType() {
      return AbilityType.RIGHT_CLICK;
    }
  },
  SHADOW_WARP {
    @Override
    public void use(Player player) {

    }
    @Override
    public String getAbilityName() {
      return "Shadow Warp";
    }
    @Override
    public AbilityType getAbilityType() {
      return AbilityType.RIGHT_CLICK;
    }
  };
  public abstract void use(Player player); // Method to use an ability
  public abstract String getAbilityName();
  public abstract AbilityType getAbilityType();
}