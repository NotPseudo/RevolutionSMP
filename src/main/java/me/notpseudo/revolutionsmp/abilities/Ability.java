package me.notpseudo.revolutionsmp.abilities;

import org.bukkit.entity.Player;

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
  public abstract void use(Player player);
  public abstract String getAbilityName();
}
