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
  },
  WITHER_IMPACT {
    @Override
    public void use(Player player) {

    }
    @Override
    public String getAbilityName() {
      return "Wither Impact";
    }
  };
  // Method to use an ability
  public abstract void use(Player player);
  public abstract String getAbilityName();
}
