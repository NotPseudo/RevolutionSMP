package me.notpseudo.revolutionsmp.items;

import net.kyori.adventure.text.format.NamedTextColor;

// Enum of Rarities a custom item can be
public enum Rarity {
  COMMON {
    @Override
    public NamedTextColor getRarityColor() {
      return NamedTextColor.WHITE;
    }
  },
  UNCOMMON {
    @Override
    public NamedTextColor getRarityColor() {
      return NamedTextColor.GREEN;
    }
  },
  RARE {
    @Override
    public NamedTextColor getRarityColor() {
      return NamedTextColor.BLUE;
    }
  },
  EPIC {
    @Override
    public NamedTextColor getRarityColor() {
      return NamedTextColor.DARK_PURPLE;
    }
  },
  LEGENDARY {
    @Override
    public NamedTextColor getRarityColor() {
      return NamedTextColor.GOLD;
    }
  },
  MYTHIC {
    @Override
    public NamedTextColor getRarityColor() {
      return NamedTextColor.LIGHT_PURPLE;
    }
  },
  DIVINE {
    @Override
    public NamedTextColor getRarityColor() {
      return NamedTextColor.AQUA;
    }
  },
  SPECIAL {
    @Override
    public NamedTextColor getRarityColor() {
      return NamedTextColor.RED;
    }
  };
  // Gets the color associated with the Rarity
  public abstract NamedTextColor getRarityColor();
  private static Rarity[] values = values();

  // Gets the next higher Rarity
  public Rarity next()
  {
    return values[(this.ordinal() + 1) % values.length];
  }
}
