package me.notpseudo.revolutionsmp.abilities;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.List;

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
    @Override
    public List<Component> getAbilityInfo() {
      return null;
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
    @Override
    public List<Component> getAbilityInfo() {
      return null;
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
    @Override
    public List<Component> getAbilityInfo() {
      return null;
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
    @Override
    public List<Component> getAbilityInfo() {
      return null;
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
    @Override
    public List<Component> getAbilityInfo() {
      return null;
    }
  };
  public abstract void use(Player player); // Method to use an ability
  public abstract String getAbilityName();
  public abstract AbilityType getAbilityType();
  public abstract List<Component> getAbilityInfo();
}