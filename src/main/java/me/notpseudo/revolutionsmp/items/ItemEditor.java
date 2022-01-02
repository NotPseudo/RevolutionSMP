package me.notpseudo.revolutionsmp.items;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.datacontainers.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

// Many utility methods to edit item stats and lore
public class ItemEditor {

  // NamespacedKeys are needed to access PersistentData
  private final static NamespacedKey itemKey = new NamespacedKey(RevolutionSMP.getPlugin(RevolutionSMP.class), "items");
  private final static NamespacedKey weaponKey = new NamespacedKey(RevolutionSMP.getPlugin(RevolutionSMP.class), "weapons");
  private final static NamespacedKey armorKey = new NamespacedKey(RevolutionSMP.getPlugin(RevolutionSMP.class), "armors");
  private final static NamespacedKey abilityKey = new NamespacedKey(RevolutionSMP.getPlugin(RevolutionSMP.class), "abilities");

  // Returns above NamespacedKeys for other classes to use
  public static NamespacedKey getItemKey() {
    return itemKey;
  }
  public static NamespacedKey getWeaponKey() {
    return weaponKey;
  }
  public static NamespacedKey getArmorKey() {
    return armorKey;
  }
  public static NamespacedKey getAbilityKey() {
    return abilityKey;
  }

  // Method that reads stats stored in an ItemMeta's PersistentData and formats the lore to display info for Players
  public static void updateLore(ItemMeta meta) {
    // Makes item unbreakable and hides enchants and attributes
    meta.setUnbreakable(true);
    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
    // Gets stats from PersistentData
    ItemInfo itemInfo = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
    WeaponStats weaponStats = meta.getPersistentDataContainer().get(weaponKey, new WeaponStatsDataType());
    ArmorStats armorStats = meta.getPersistentDataContainer().get(armorKey, new ArmorStatsDataType());
    AbilityStats abilityStats = meta.getPersistentDataContainer().get(abilityKey, new AbilityStatsDataType());
    // Represents the ItemStack's lore
    List<Component> lore = new ArrayList<>();
    // Components show custom item stats
    Component name = null, rarity = null, damage = null, strength = null,  critChance = null, critDamage = null, ferocity = null, health = null, defense = null,  speed = null, abilityDamage = null, intelligence = null, rightClickAbility = null, sneakRightClickAbility = null, leftClickAbility = null, hasReforge = null;
    // Components that show extra stats added by Potato Books
    Component potatoDamage = Component.text(""), potatoStrength = Component.text(""), potatoHealth = Component.text(""), potatoDefense = Component.text("");
    // Components that show extra stats added by Reforge
    Component reforgeDamage = Component.text(""), reforgeStrength = Component.text(""), reforgeCritChance = Component.text(""), reforgeCritDamage = Component.text(""), reforgeFerocity = Component.text(""), reforgeHealth = Component.text(""), reforgeDefense = Component.text(""), reforgeSpeed = Component.text(""), reforgeAbilityDamage  = Component.text(""), reforgeIntelligence = Component.text("");
    if (itemInfo != null) {
      // Makes sure the ItemInfo is not null before calling methods. This also checks to make sure the ItemStack is a custom item
      String rarityName = itemInfo.getRarity().name();
      String itemType = itemInfo.getItemType().name();
      NamedTextColor rarityColor = itemInfo.getRarity().getRarityColor();
      if (itemInfo.isRecomb()) {
        rarity = Component.text("S ", rarityColor, TextDecoration.OBFUSCATED, TextDecoration.BOLD).append(Component.text(rarityName + " " + itemType, rarityColor, TextDecoration.BOLD).decoration(TextDecoration.OBFUSCATED, false).append(Component.text(" S", rarityColor, TextDecoration.OBFUSCATED, TextDecoration.BOLD)));
      } else {
        rarity = Component.text(rarityName + " " + itemType).color(rarityColor).decoration(TextDecoration.BOLD, true);
      }
      if(itemInfo.getPotatoBooks() != 0) {
        if(itemInfo.getItemType() == ItemType.HELMET || itemInfo.getItemType() == ItemType.CHESTPLATE || itemInfo.getItemType() == ItemType.LEGGINGS || itemInfo.getItemType() == ItemType.BOOTS) {
          // If the Item is armor, Potato Books add Health and Defense
          potatoHealth = Component.text(" (+" + (itemInfo.getPotatoBooks() * 4) + ")", NamedTextColor.YELLOW);
          potatoDefense = Component.text(" (+" + (itemInfo.getPotatoBooks() * 2) + ")", NamedTextColor.YELLOW);
        } else if(itemInfo.getItemType() == ItemType.SWORD || itemInfo.getItemType() == ItemType.BOW) {
          // If the Item is a weapon, Potato Books add Damage and Strength
          potatoDamage = Component.text(" (+" + (itemInfo.getPotatoBooks() * 2) + ")", NamedTextColor.YELLOW);
          potatoStrength = Component.text(" (+" + (itemInfo.getPotatoBooks() * 2) + ")", NamedTextColor.YELLOW);
        }
      }
      String reforgeName = "";
      if (itemInfo.getReforge() != null) {
        // If the Item has a Reforge
        Reforge reforge = itemInfo.getReforge();
        reforgeName = reforge.name().charAt(0) + reforge.name().substring(1).toLowerCase() + " ";
        // Gets stats added by Reforge
        WeaponStats reforgeWeaponStats = reforge.getWeaponStats();
        ArmorStats reforgeArmorStats = reforge.getArmorStats();
        AbilityStats reforgeAbilityStats = reforge.getAbilityStats();
        // Each stat's Component will only show if the Reforge increases or decreases the stat
        if(reforgeWeaponStats.getDamage() > 0) {
          reforgeDamage = Component.text(" (" + reforgeName + "+" + reforgeWeaponStats.getDamage() + ")", NamedTextColor.BLUE);
        } else if(reforgeWeaponStats.getDamage() < 0) {
          reforgeDamage = Component.text(" (" + reforgeName + reforgeWeaponStats.getDamage() + ")", NamedTextColor.BLUE);
        }
        if(reforgeWeaponStats.getStrength() > 0) {
          reforgeStrength = Component.text(" (" + reforgeName + "+" + reforgeWeaponStats.getStrength() + ")", NamedTextColor.BLUE);
        } else if(reforgeWeaponStats.getStrength() < 0) {
          reforgeStrength = Component.text(" (" + reforgeName + reforgeWeaponStats.getStrength() + ")", NamedTextColor.BLUE);
        }
        if(reforgeWeaponStats.getCritChance() > 0) {
          reforgeCritChance = Component.text(" (" + reforgeName + "+" + reforgeWeaponStats.getCritChance() + "%)", NamedTextColor.BLUE);
        } else if(reforgeWeaponStats.getCritChance() < 0) {
          reforgeCritChance = Component.text(" (" + reforgeName + reforgeWeaponStats.getCritChance() + "%)", NamedTextColor.BLUE);
        }
        if(reforgeWeaponStats.getCritDamage() > 0) {
          reforgeCritDamage = Component.text(" (" + reforgeName + "+" + reforgeWeaponStats.getCritDamage() + "%)", NamedTextColor.BLUE);
        } else if(reforgeWeaponStats.getCritDamage() < 0) {
          reforgeCritDamage = Component.text(" (" + reforgeName + reforgeWeaponStats.getCritDamage() + "%)", NamedTextColor.BLUE);
        }
        if(reforgeWeaponStats.getFerocity() > 0) {
          reforgeFerocity = Component.text(" (" + reforgeName + "+" + reforgeWeaponStats.getFerocity() + ")", NamedTextColor.BLUE);
        } else if(reforgeWeaponStats.getFerocity() < 0) {
          reforgeFerocity = Component.text(" (" + reforgeName + reforgeWeaponStats.getFerocity() + ")", NamedTextColor.BLUE);
        }
        if(reforgeArmorStats.getHealth() > 0) {
          reforgeHealth = Component.text(" (" + reforgeName + "+" + reforgeArmorStats.getHealth() + ")", NamedTextColor.BLUE);
        } else if(reforgeArmorStats.getHealth() < 0) {
          reforgeHealth = Component.text(" (" + reforgeName + reforgeArmorStats.getHealth() + ")", NamedTextColor.BLUE);
        }
        if(reforgeArmorStats.getDefense() > 0) {
          reforgeDefense = Component.text(" (" + reforgeName + "+" + reforgeArmorStats.getDefense() + ")", NamedTextColor.BLUE);
        } else if(reforgeArmorStats.getDefense() < 0) {
          reforgeDefense = Component.text(" (" + reforgeName + reforgeArmorStats.getDefense() + ")", NamedTextColor.BLUE);
        }
        if(reforgeArmorStats.getSpeed() > 0) {
          reforgeSpeed = Component.text(" (" + reforgeName + "+" + reforgeArmorStats.getSpeed() + ")", NamedTextColor.BLUE);
        } else if(reforgeArmorStats.getSpeed() < 0) {
          reforgeSpeed = Component.text(" (" + reforgeName + reforgeArmorStats.getSpeed() + ")", NamedTextColor.BLUE);
        }
        if(reforgeAbilityStats.getAbilityDamage() > 0) {
          reforgeAbilityDamage = Component.text(" (" + reforgeName + "+" + reforgeAbilityStats.getAbilityDamage() + ")", NamedTextColor.BLUE);
        } else if(reforgeAbilityStats.getAbilityDamage() < 0) {
          reforgeAbilityDamage = Component.text(" (" + reforgeName + reforgeAbilityStats.getAbilityDamage() + ")", NamedTextColor.BLUE);
        }
        if(reforgeAbilityStats.getIntelligence() > 0) {
          reforgeIntelligence = Component.text(" (" + reforgeName + "+" + reforgeAbilityStats.getIntelligence() + ")", NamedTextColor.BLUE);
        } else if(reforgeAbilityStats.getIntelligence() < 0) {
          reforgeIntelligence = Component.text(" (" + reforgeName + reforgeAbilityStats.getIntelligence() + ")", NamedTextColor.BLUE);
        }
      } else {
        // If the Item does not have a Reforge, the Item lore will indicate the item can be Reforged
        hasReforge = Component.text("This item can be reforged!", NamedTextColor.DARK_GRAY);
      }
      // The displayed name of the item is Reforge + Item Name
      name = Component.text(reforgeName + itemInfo.getName()).color(itemInfo.getRarity().getRarityColor());
    }
    if (weaponStats != null) {
      // Makes sure the WeaponStats is not null before calling methods
      // Each stat's Component will only show if the Item increases or decreases the stat
      // Appends the Reforge stat boost Component after the custom item's stats
      if (weaponStats.getDamage() > 0) {
        damage = Component.text("Damage: ", NamedTextColor.GRAY).append(Component.text("+" + (int) weaponStats.getDamage(), NamedTextColor.RED)).append(potatoDamage).append(reforgeDamage);
      } else if(weaponStats.getDamage() < 0) {
        damage = Component.text("Damage: ", NamedTextColor.GRAY).append(Component.text((int) weaponStats.getDamage(), NamedTextColor.RED)).append(potatoDamage).append(reforgeDamage);
      }
      if (weaponStats.getStrength() > 0) {
        strength = Component.text("Strength: ", NamedTextColor.GRAY).append(Component.text("+" + (int) weaponStats.getStrength(), NamedTextColor.RED)).append(potatoStrength).append(reforgeStrength);
      } else if(weaponStats.getStrength() < 0) {
        strength = Component.text("Strength: ", NamedTextColor.GRAY).append(Component.text((int) weaponStats.getStrength(), NamedTextColor.RED)).append(potatoStrength).append(reforgeStrength);
      }
      if (weaponStats.getCritChance() > 0) {
        critChance = Component.text("Crit Chance: ", NamedTextColor.GRAY).append(Component.text("+" + (int) weaponStats.getCritChance() + "%", NamedTextColor.RED)).append(reforgeCritChance);
      } else if(weaponStats.getCritChance() < 0) {
        critChance = Component.text("Crit Chance: ", NamedTextColor.GRAY).append(Component.text((int) weaponStats.getCritChance() + "%", NamedTextColor.RED)).append(reforgeCritChance);
      }
      if (weaponStats.getCritDamage() > 0) {
        critDamage = Component.text("Crit Damage: ", NamedTextColor.GRAY).append(Component.text("+" + (int) weaponStats.getCritDamage() + "%", NamedTextColor.RED)).append(reforgeCritDamage);
      } else if(weaponStats.getCritDamage() < 0) {
        critDamage = Component.text("Crit Damage: ", NamedTextColor.GRAY).append(Component.text((int) weaponStats.getCritDamage() + "%", NamedTextColor.RED)).append(reforgeCritDamage);
      }
      if (weaponStats.getFerocity() > 0) {
        ferocity = Component.text("Ferocity: ", NamedTextColor.GRAY).append(Component.text("+" + (int) weaponStats.getFerocity(), NamedTextColor.GREEN)).append(reforgeFerocity);
      } else if (weaponStats.getFerocity() < 0) {
        ferocity = Component.text("Ferocity: ", NamedTextColor.GRAY).append(Component.text((int) weaponStats.getFerocity(), NamedTextColor.GREEN)).append(reforgeFerocity);
      }
    }
    if (armorStats != null) {
      // Makes sure the ArmorStats is not null before calling methods
      // Each stat's Component will only show if the Item increases or decreases the stat
      // Appends the Reforge stat boost Component after the custom item's stats
      if (armorStats.getHealth() > 0) {
        health = Component.text("Health: ", NamedTextColor.GRAY).append(Component.text("+" + (int) armorStats.getHealth(), NamedTextColor.GREEN)).append(potatoHealth).append(reforgeHealth);
      } else if (armorStats.getHealth() < 0) {
        health = Component.text("Health: ", NamedTextColor.GRAY).append(Component.text((int) armorStats.getHealth(), NamedTextColor.GREEN)).append(potatoHealth).append(reforgeHealth);
      }
      if (armorStats.getDefense() > 0) {
        defense = Component.text("Defense: ", NamedTextColor.GRAY).append(Component.text("+" + (int) armorStats.getDefense(), NamedTextColor.GREEN)).append(potatoDefense).append(reforgeDefense);
      } else if (armorStats.getDefense() < 0) {
        defense = Component.text("Defense: ", NamedTextColor.GRAY).append(Component.text((int) armorStats.getDefense(), NamedTextColor.GREEN)).append(potatoDefense).append(reforgeDefense);
      }
      if (armorStats.getSpeed() > 0) {
        speed = Component.text("Speed: ", NamedTextColor.GRAY).append(Component.text("+" + (int) armorStats.getSpeed(), NamedTextColor.GREEN)).append(reforgeSpeed);
      } else if (armorStats.getSpeed() < 0) {
        speed = Component.text("Speed: ", NamedTextColor.GRAY).append(Component.text((int) armorStats.getSpeed(), NamedTextColor.GREEN)).append(reforgeSpeed);
      }
    }
    if (abilityStats != null) {
      // Makes sure the AbilityStats is not null before calling methods
      // Each stat's Component will only show if the Item increases or decreases the stat
      // Appends the Reforge stat boost Component after the custom item's stats
      if (abilityStats.getAbilityDamage() > 0) {
        abilityDamage = Component.text("Ability Damage: ", NamedTextColor.GRAY).append(Component.text("+" + (int) abilityStats.getAbilityDamage() + "%", NamedTextColor.RED)).append(reforgeAbilityDamage);
      } else if (abilityStats.getAbilityDamage() < 0) {
        abilityDamage = Component.text("Ability Damage: ", NamedTextColor.GRAY).append(Component.text((int) abilityStats.getAbilityDamage() + "%", NamedTextColor.RED)).append(reforgeAbilityDamage);
      }
      if (abilityStats.getIntelligence() > 0) {
        intelligence = Component.text("Intelligence: ", NamedTextColor.GRAY).append(Component.text("+" + (int) abilityStats.getIntelligence(), NamedTextColor.GREEN)).append(reforgeIntelligence);
      } else if (abilityStats.getIntelligence() < 0) {
        intelligence = Component.text("Intelligence: ", NamedTextColor.GRAY).append(Component.text((int) abilityStats.getIntelligence(), NamedTextColor.GREEN)).append(reforgeIntelligence);
      }
      // If the custom item has Abilities, the Components will update and be shown in the lore
      if (abilityStats.getRightClickAbility() != null && abilityStats.isShowRight()) {
        rightClickAbility = Component.text("Ability: " + abilityStats.getRightClickAbility().getAbilityName() + " ", NamedTextColor.GOLD).append(Component.text("RIGHT CLICK", NamedTextColor.YELLOW, TextDecoration.BOLD));
      }
      if (abilityStats.getSneakRightClickAbility() != null && abilityStats.isShowSneakRight()) {
        sneakRightClickAbility = Component.text("Ability: " + abilityStats.getSneakRightClickAbility().getAbilityName() + " ", NamedTextColor.GOLD).append(Component.text("SNEAK RIGHT CLICK", NamedTextColor.YELLOW, TextDecoration.BOLD));
      }
      if (abilityStats.getLeftClickAbility() != null && abilityStats.isShowLeft()) {
        leftClickAbility = Component.text("Ability: " + abilityStats.getRightClickAbility().getAbilityName() + " ", NamedTextColor.GOLD).append(Component.text("LEFT CLICK", NamedTextColor.YELLOW, TextDecoration.BOLD));
      }
    }
    // If the stat's Component is not null, it will be added to the lore
    if(name != null) {meta.displayName(name.decoration(TextDecoration.ITALIC, false));}
    if(damage != null) {lore.add(damage.decoration(TextDecoration.ITALIC, false));}
    if(strength != null) {lore.add(strength.decoration(TextDecoration.ITALIC, false));}
    if(critChance != null) {lore.add(critChance.decoration(TextDecoration.ITALIC, false));}
    if(critDamage != null) {lore.add(critDamage.decoration(TextDecoration.ITALIC, false));}
    if(abilityDamage != null) {lore.add(abilityDamage.decoration(TextDecoration.ITALIC, false));}
    if(health != null) {lore.add(health.decoration(TextDecoration.ITALIC, false));}
    if(defense != null) {lore.add(defense.decoration(TextDecoration.ITALIC, false));}
    if(speed != null) {lore.add(speed.decoration(TextDecoration.ITALIC, false));}
    if(intelligence != null) {lore.add(intelligence.decoration(TextDecoration.ITALIC, false));}
    if(ferocity != null) {lore.add(ferocity.decoration(TextDecoration.ITALIC, false));}
    if(rightClickAbility != null) {
      lore.add(Component.text(""));
      lore.add(rightClickAbility.decoration(TextDecoration.ITALIC, false));
    }
    if(sneakRightClickAbility != null) {
      lore.add(Component.text(""));
      lore.add(sneakRightClickAbility.decoration(TextDecoration.ITALIC, false));
    }
    if(leftClickAbility != null) {
      lore.add(Component.text(""));
      lore.add(leftClickAbility.decoration(TextDecoration.ITALIC, false));
    }
    lore.add(Component.text(""));
    if(hasReforge != null) {
      lore.add(hasReforge.decoration(TextDecoration.ITALIC, false));
    }
    if(rarity != null) {
      lore.add(rarity.decoration(TextDecoration.ITALIC, false));
    }
    meta.lore(lore);
  }

  // Recombobulates the custom item in the Player's main hand. This upgrades the rarity by 1
  public static void recombobulate(Player player) {
    ItemStack item = player.getInventory().getItemInMainHand();
    ItemMeta meta = item.getItemMeta();
    ItemInfo info = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
    if(info != null) {
      Rarity currentRarity = info.getRarity();
      if(currentRarity != Rarity.SPECIAL && !(info.isRecomb())) {
        Rarity recombRarity = currentRarity.next();
        info.setRarity(recombRarity);
        info.setRecomb(true);
        meta.getPersistentDataContainer().set(itemKey, new ItemInfoDataType(), info);
        updateLore(meta);
        item.setItemMeta(meta);
        player.sendMessage(Component.text("Your ").append(Component.text(currentRarity.name(), currentRarity.getRarityColor(), TextDecoration.BOLD)).append(Component.text(" item has been upgraded to ")).append(Component.text(recombRarity.name(), recombRarity.getRarityColor(), TextDecoration.BOLD)).decoration(TextDecoration.ITALIC, false));
      }
    }
  }

  // If the custom item does not already have 15 Potato Books, adds a Potato Book to the item in the Player's main hand
  public static void hotPotatoBook(Player player) {
    ItemStack item = player.getInventory().getItemInMainHand();
    ItemMeta meta = item.getItemMeta();
    ItemInfo itemInfo = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
    if(itemInfo != null) {
      int currentPotatoBooks = itemInfo.getPotatoBooks();
      if(currentPotatoBooks < 15) {
        itemInfo.setPotatoBooks(currentPotatoBooks + 1);
        WeaponStats weaponStats = meta.getPersistentDataContainer().get(weaponKey, new WeaponStatsDataType());
        ArmorStats armorStats = meta.getPersistentDataContainer().get(armorKey, new ArmorStatsDataType());
        double reforgeHealth = 0, reforgeDefense = 0, reforgeDamage = 0, reforgeStrength = 0;
        if(itemInfo.getReforge() != null) {
          WeaponStats reforgeWeaponStats = itemInfo.getReforge().getWeaponStats();
          ArmorStats reforgeArmorStats = itemInfo.getReforge().getArmorStats();
          reforgeHealth = reforgeArmorStats.getHealth();
          reforgeDefense = reforgeArmorStats.getDefense();
          reforgeDamage = reforgeWeaponStats.getDamage();
          reforgeStrength = reforgeWeaponStats.getStrength();
        }
        if(itemInfo.getItemType() == ItemType.HELMET || itemInfo.getItemType() == ItemType.CHESTPLATE || itemInfo.getItemType() == ItemType.LEGGINGS || itemInfo.getItemType() == ItemType.BOOTS) {
          armorStats.setHealth(armorStats.getBaseHealth() + itemInfo.getPotatoBooks() * 4 + reforgeHealth);
          armorStats.setDefense(armorStats.getBaseDefense() + itemInfo.getPotatoBooks() * 2 + reforgeDefense);
        } else if(itemInfo.getItemType() == ItemType.SWORD || itemInfo.getItemType() == ItemType.BOW) {
          weaponStats.setDamage(weaponStats.getBaseDamage() + itemInfo.getPotatoBooks() * 2 + reforgeDamage);
          weaponStats.setStrength(weaponStats.getBaseStrength() + itemInfo.getPotatoBooks() * 2 + reforgeStrength);
        }
        meta.getPersistentDataContainer().set(itemKey, new ItemInfoDataType(), itemInfo);
        meta.getPersistentDataContainer().set(weaponKey, new WeaponStatsDataType(), weaponStats);
        meta.getPersistentDataContainer().set(armorKey, new ArmorStatsDataType(), armorStats);
        updateLore(meta);
        item.setItemMeta(meta);
        player.sendMessage(Component.text("You applied a Potato Book to your item with").append(Component.text(" " + currentPotatoBooks, NamedTextColor.GOLD)).append(Component.text(" Potato Books. It now has")).append(Component.text(" " + (currentPotatoBooks + 1), NamedTextColor.GOLD)).append(Component.text(" Potato Books out of a maximum of")).append(Component.text(" 15", NamedTextColor.GOLD)).decoration(TextDecoration.ITALIC, false));
      } else {
        player.sendMessage(Component.text("Your held item already has a maximum of").append(Component.text(" 15", NamedTextColor.GOLD)).append(Component.text(" Potato Books")).decoration(TextDecoration.ITALIC, false));
      }
    }
  }

  // Reforges the custom item in the Player's main hand with the Reforge
  public static void reforge(Player player, Reforge reforge) {
    ItemStack item = player.getInventory().getItemInMainHand();
    ItemMeta meta = item.getItemMeta();
    ItemInfo itemInfo = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
    if(itemInfo != null) {
      if(reforge.getItemTypes().contains(itemInfo.getItemType())) {
        itemInfo.setReforge(reforge);
        WeaponStats weaponStats = meta.getPersistentDataContainer().get(weaponKey, new WeaponStatsDataType());
        ArmorStats armorStats = meta.getPersistentDataContainer().get(armorKey, new ArmorStatsDataType());
        AbilityStats abilityStats = meta.getPersistentDataContainer().get(abilityKey, new AbilityStatsDataType());
        WeaponStats reforgeWeaponStats = reforge.getWeaponStats();
        ArmorStats reforgeArmorStats = reforge.getArmorStats();
        AbilityStats reforgeAbilityStats = reforge.getAbilityStats();
        if(itemInfo.getItemType() == ItemType.SWORD || itemInfo.getItemType() == ItemType.BOW) {
          weaponStats.setDamage(weaponStats.getBaseDamage() + itemInfo.getPotatoBooks() * 2 + reforgeWeaponStats.getDamage());
          weaponStats.setStrength(weaponStats.getBaseStrength() + itemInfo.getPotatoBooks() * 2 + reforgeWeaponStats.getStrength());
        } else {
          weaponStats.setDamage(weaponStats.getBaseDamage() + reforgeWeaponStats.getDamage());
          weaponStats.setStrength(weaponStats.getBaseStrength() + reforgeWeaponStats.getStrength());
        }
        weaponStats.setCritChance(weaponStats.getBaseCritChance() + reforgeWeaponStats.getCritChance());
        weaponStats.setCritDamage(weaponStats.getBaseCritDamage() + reforgeWeaponStats.getCritDamage());
        weaponStats.setFerocity(weaponStats.getBaseFerocity() + reforgeWeaponStats.getFerocity());
        if(itemInfo.getItemType() == ItemType.HELMET || itemInfo.getItemType() == ItemType.CHESTPLATE || itemInfo.getItemType() == ItemType.LEGGINGS || itemInfo.getItemType() == ItemType.BOOTS) {
          armorStats.setHealth(armorStats.getBaseHealth() + itemInfo.getPotatoBooks() * 4 + reforgeArmorStats.getHealth());
          armorStats.setDefense(armorStats.getBaseDefense() + itemInfo.getPotatoBooks() * 2 + reforgeArmorStats.getDefense());
        } else {
          armorStats.setHealth(armorStats.getBaseHealth() + reforgeArmorStats.getHealth());
          armorStats.setDefense(armorStats.getBaseDefense() + reforgeArmorStats.getDefense());
        }
        armorStats.setSpeed(armorStats.getBaseSpeed() + reforgeArmorStats.getSpeed());
        abilityStats.setAbilityDamage(abilityStats.getBaseAbilityDamage() + reforgeAbilityStats.getAbilityDamage());
        abilityStats.setIntelligence(abilityStats.getBaseIntelligence() + reforgeAbilityStats.getIntelligence());
        meta.getPersistentDataContainer().set(itemKey, new ItemInfoDataType(), itemInfo);
        meta.getPersistentDataContainer().set(weaponKey, new WeaponStatsDataType(), weaponStats);
        meta.getPersistentDataContainer().set(armorKey, new ArmorStatsDataType(), armorStats);
        meta.getPersistentDataContainer().set(abilityKey, new AbilityStatsDataType(), abilityStats);
        updateLore(meta);
        item.setItemMeta(meta);
      } else {
        player.sendMessage(Component.text(reforge.name(), itemInfo.getRarity().getRarityColor()).append(Component.text(" can not be applied to ", NamedTextColor.RED)).append(Component.text(itemInfo.getItemType().name(), itemInfo.getRarity().getRarityColor())));
      }
    }
  }

}
