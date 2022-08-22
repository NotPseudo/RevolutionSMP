package me.notpseudo.revolutionsmp.customcrafting.items;

import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;

// Enum of Rarities a custom item can be
public enum Rarity {
  COMMON {
    @Override
    public NamedTextColor getRarityColor() {
      return NamedTextColor.WHITE;
    }
    @Override
    public ChatColor getColor() {
      return ChatColor.WHITE;
    }
  },
  UNCOMMON {
    @Override
    public NamedTextColor getRarityColor() {
      return NamedTextColor.GREEN;
    }
    @Override
    public ChatColor getColor() {
      return ChatColor.GREEN;
    }
  },
  RARE {
    @Override
    public NamedTextColor getRarityColor() {
      return NamedTextColor.BLUE;
    }
    @Override
    public ChatColor getColor() {
      return ChatColor.BLUE;
    }
  },
  EPIC {
    @Override
    public NamedTextColor getRarityColor() {
      return NamedTextColor.DARK_PURPLE;
    }
    @Override
    public ChatColor getColor() {
      return ChatColor.DARK_PURPLE;
    }
  },
  LEGENDARY {
    @Override
    public NamedTextColor getRarityColor() {
      return NamedTextColor.GOLD;
    }
    @Override
    public ChatColor getColor() {
      return ChatColor.GOLD;
    }
  },
  MYTHIC {
    @Override
    public NamedTextColor getRarityColor() {
      return NamedTextColor.LIGHT_PURPLE;
    }
    @Override
    public ChatColor getColor() {
      return ChatColor.LIGHT_PURPLE;
    }
  },
  DIVINE {
    @Override
    public NamedTextColor getRarityColor() {
      return NamedTextColor.AQUA;
    }
    @Override
    public ChatColor getColor() {
      return ChatColor.AQUA;
    }
  },
  SPECIAL {
    @Override
    public NamedTextColor getRarityColor() {
      return NamedTextColor.RED;
    }

    @Override
    public ChatColor getColor() {
      return ChatColor.RED;
    }
  };
  // Gets the color associated with the Rarity
  public abstract NamedTextColor getRarityColor();
  public abstract ChatColor getColor();
  private static final Rarity[] values = values();

  // Gets the next higher Rarity
  public Rarity next()
  {
    return values[(this.ordinal() + 1) % values.length];
  }

  public String getName() {
    return ItemEditor.getStringFromEnum(this);
  }

}