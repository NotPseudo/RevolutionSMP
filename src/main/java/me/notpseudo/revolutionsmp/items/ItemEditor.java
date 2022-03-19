package me.notpseudo.revolutionsmp.items;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.itemstats.*;
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

// Utility methods to edit item stats and lore
public class ItemEditor {

  // NamespacedKey to access PersistentData
  private final static NamespacedKey itemKey = new NamespacedKey(RevolutionSMP.getPlugin(RevolutionSMP.class), "items");

  // Returns NamespacedKey for other classes to use
  public static NamespacedKey getItemKey() {
    return itemKey;
  }


  // Method that reads stats stored in an ItemMeta's PersistentData and formats the lore to display info for Players
  public static void updateLore(ItemMeta meta) {
    // Hides enchants and attributes
    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
    // Gets stats from PersistentData
    ItemInfo itemInfo = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
    WeaponStats weaponStats = itemInfo.getWeaponStats();
    ArmorStats armorStats = itemInfo.getArmorStats();
    AbilityStats abilityStats = itemInfo.getAbilityStats();
    // Represents the ItemStack's lore
    List<Component> lore = new ArrayList<>();
    // Components that show custom item stats
    Component name, rarity, damage = null, strength = null,  critChance = null, critDamage = null, attackSpeed = null, ferocity = null, health = null, defense = null,  speed = null, abilityDamage = null, intelligence = null, hasReforge = null;
    // Components that show extra stats added by Potato Books
    Component potatoDamage = Component.text(""), potatoStrength = Component.text(""), potatoHealth = Component.text(""), potatoDefense = Component.text("");
    // Components that show extra stats added by Reforge
    Component reforgeDamage = Component.text(""), reforgeStrength = Component.text(""), reforgeCritChance = Component.text(""), reforgeCritDamage = Component.text(""), reforgeAttackSpeed = Component.text(""), reforgeFerocity = Component.text(""), reforgeHealth = Component.text(""), reforgeDefense = Component.text(""), reforgeSpeed = Component.text(""), reforgeAbilityDamage  = Component.text(""), reforgeIntelligence = Component.text("");
    if (itemInfo == null) return; // Makes sure the ItemInfo is not null before calling methods. Checks to make sure the ItemStack is a custom item
    String rarityName = itemInfo.getRarity().name();
    String itemType = itemInfo.getItemType().name();
    NamedTextColor rarityColor = itemInfo.getRarity().getRarityColor();
    if (itemInfo.isRecomb()) {
      rarity = Component.text("S ", rarityColor, TextDecoration.OBFUSCATED, TextDecoration.BOLD).append(Component.text(rarityName + " " + itemType, rarityColor, TextDecoration.BOLD).decoration(TextDecoration.OBFUSCATED, false).append(Component.text(" S", rarityColor, TextDecoration.OBFUSCATED, TextDecoration.BOLD)));
    } else {
      rarity = Component.text(rarityName + " " + itemType).color(rarityColor).decoration(TextDecoration.BOLD, true);
    }
    if(itemInfo.getPotatoBooks() != 0) {
      if(itemInfo.getItemType() == ItemType.HELMET || itemInfo.getItemType() == ItemType.CHESTPLATE || itemInfo.getItemType() == ItemType.LEGGINGS || itemInfo.getItemType() == ItemType.BOOTS) { // If the Item is armor, Potato Books add Health and Defense
        potatoHealth = Component.text(" (+" + (itemInfo.getPotatoBooks() * 4) + ")", NamedTextColor.YELLOW);
        potatoDefense = Component.text(" (+" + (itemInfo.getPotatoBooks() * 2) + ")", NamedTextColor.YELLOW);
      } else if(itemInfo.getItemType() == ItemType.SWORD || itemInfo.getItemType() == ItemType.BOW) { // If the Item is a weapon, Potato Books add Damage and Strength
        potatoDamage = Component.text(" (+" + (itemInfo.getPotatoBooks() * 2) + ")", NamedTextColor.YELLOW);
        potatoStrength = Component.text(" (+" + (itemInfo.getPotatoBooks() * 2) + ")", NamedTextColor.YELLOW);
      }
    }
    String reforgeName = "";
    if (itemInfo.getReforge() != null) { // If the Item has a Reforge
      Reforge reforge = itemInfo.getReforge();
      reforgeName = reforge.name().charAt(0) + reforge.name().substring(1).toLowerCase() + " ";
      // Gets stats added by Reforge
      WeaponStats reforgeWeaponStats = reforge.getWeaponStats(itemInfo.getRarity());
      ArmorStats reforgeArmorStats = reforge.getArmorStats(itemInfo.getRarity());
      AbilityStats reforgeAbilityStats = reforge.getAbilityStats(itemInfo.getRarity());
      // Each stat's Component will only show if the Reforge increases or decreases the stat
      if(reforgeWeaponStats.getDamage() != 0) {
        reforgeDamage = Component.text(" (" + reforgeName + getStatString(reforgeWeaponStats.getDamage()) + ")", NamedTextColor.BLUE);
      }
      if(reforgeWeaponStats.getStrength() != 0) {
        reforgeStrength = Component.text(" (" + reforgeName + getStatString(reforgeWeaponStats.getStrength()) + ")", NamedTextColor.BLUE);
      }
      if(reforgeWeaponStats.getCritChance() != 0) {
        reforgeCritChance = Component.text(" (" + reforgeName + getStatString(reforgeWeaponStats.getCritChance()) + "%)", NamedTextColor.BLUE);
      }
      if(reforgeWeaponStats.getCritDamage() != 0) {
        reforgeCritDamage = Component.text(" (" + reforgeName + getStatString(reforgeWeaponStats.getCritDamage()) + "%)", NamedTextColor.BLUE);
      }
      if(reforgeWeaponStats.getAttackSpeed() != 0) {
        reforgeAttackSpeed = Component.text(" (" + reforgeName + getStatString(reforgeWeaponStats.getAttackSpeed()) + "%)", NamedTextColor.BLUE);
      }
      if(reforgeWeaponStats.getFerocity() != 0) {
        reforgeFerocity = Component.text(" (" + reforgeName + getStatString(reforgeWeaponStats.getFerocity()) + ")", NamedTextColor.BLUE);
      }
      if(reforgeArmorStats.getHealth() != 0) {
        reforgeHealth = Component.text(" (" + reforgeName + getStatString(reforgeArmorStats.getHealth()) + ")", NamedTextColor.BLUE);
      }
      if(reforgeArmorStats.getDefense() != 0) {
        reforgeDefense = Component.text(" (" + reforgeName + getStatString(reforgeArmorStats.getDefense()) + ")", NamedTextColor.BLUE);
      }
      if(reforgeArmorStats.getSpeed() != 0) {
        reforgeSpeed = Component.text(" (" + reforgeName + getStatString(reforgeArmorStats.getSpeed()) + ")", NamedTextColor.BLUE);
      }
      if(reforgeAbilityStats.getAbilityDamage() != 0) {
        reforgeAbilityDamage = Component.text(" (" + reforgeName + getStatString(reforgeAbilityStats.getAbilityDamage()) + ")", NamedTextColor.BLUE);
      }
      if(reforgeAbilityStats.getIntelligence() != 0) {
        reforgeIntelligence = Component.text(" (" + reforgeName + getStatString(reforgeAbilityStats.getIntelligence()) + ")", NamedTextColor.BLUE);
      }
    } else { // If the Item does not have a Reforge, the Item lore will indicate the item can be Reforged
      hasReforge = Component.text("This item can be reforged!", NamedTextColor.DARK_GRAY);
    }
    name = Component.text(reforgeName + itemInfo.getName()).color(itemInfo.getRarity().getRarityColor()); // The displayed name of the item is Reforge + Item Name
    if (weaponStats != null) { // Makes sure the WeaponStats is not null before calling methods
      // Each stat's Component will only show if the Item increases or decreases the stat
      // Appends the Reforge stat boost Component after the custom item's stats
      if (weaponStats.getDamage() != 0) {
        damage = Component.text("Damage: ", NamedTextColor.GRAY).append(Component.text(getStatString(weaponStats.getDamage()), NamedTextColor.RED)).append(potatoDamage).append(reforgeDamage);
      }
      if (weaponStats.getStrength() != 0) {
        strength = Component.text("Strength: ", NamedTextColor.GRAY).append(Component.text(getStatString(weaponStats.getStrength()), NamedTextColor.RED)).append(potatoStrength).append(reforgeStrength);
      }
      if (weaponStats.getCritChance() != 0) {
        critChance = Component.text("Crit Chance: ", NamedTextColor.GRAY).append(Component.text(getStatString(weaponStats.getCritChance()) + "%", NamedTextColor.RED)).append(reforgeCritChance);
      }
      if (weaponStats.getCritDamage() != 0) {
        critDamage = Component.text("Crit Damage: ", NamedTextColor.GRAY).append(Component.text(getStatString(weaponStats.getCritDamage()) + "%", NamedTextColor.RED)).append(reforgeCritDamage);
      }
      if(weaponStats.getAttackSpeed() != 0) {
        attackSpeed = Component.text("Bonus Attack Speed: ", NamedTextColor.GRAY).append(Component.text(getStatString(weaponStats.getAttackSpeed()) + "%", NamedTextColor.RED)).append(reforgeAttackSpeed);
      }
      if (weaponStats.getFerocity() != 0) {
        ferocity = Component.text("Ferocity: ", NamedTextColor.GRAY).append(Component.text(getStatString(weaponStats.getFerocity()), NamedTextColor.GREEN)).append(reforgeFerocity);
      }
    }
    if (armorStats != null) { // Makes sure the ArmorStats is not null before calling methods
      // Each stat's Component will only show if the Item increases or decreases the stat
      // Appends the Reforge stat boost Component after the custom item's stats
      if (armorStats.getHealth() != 0) {
        health = Component.text("Health: ", NamedTextColor.GRAY).append(Component.text(getStatString(armorStats.getHealth()), NamedTextColor.GREEN)).append(potatoHealth).append(reforgeHealth);
      }
      if (armorStats.getDefense() != 0) {
        defense = Component.text("Defense: ", NamedTextColor.GRAY).append(Component.text(getStatString(armorStats.getDefense()), NamedTextColor.GREEN)).append(potatoDefense).append(reforgeDefense);
      }
      if (armorStats.getSpeed() != 0) {
        speed = Component.text("Speed: ", NamedTextColor.GRAY).append(Component.text(getStatString(armorStats.getSpeed()), NamedTextColor.GREEN)).append(reforgeSpeed);
      }
    }
    if (abilityStats != null) { // Makes sure the AbilityStats is not null before calling methods
      // Each stat's Component will only show if the Item increases or decreases the stat
      // Appends the Reforge stat boost Component after the custom item's stats
      if (abilityStats.getAbilityDamage() != 0) {
        abilityDamage = Component.text("Ability Damage: ", NamedTextColor.GRAY).append(Component.text(getStatString(abilityStats.getAbilityDamage()) + "%", NamedTextColor.RED)).append(reforgeAbilityDamage);
      }
      if (abilityStats.getIntelligence() != 0) {
        intelligence = Component.text("Intelligence: ", NamedTextColor.GRAY).append(Component.text(getStatString(abilityStats.getIntelligence()), NamedTextColor.GREEN)).append(reforgeIntelligence);
      }
    }
    // If the stat's Component is not null, it will be added to the lore
    meta.displayName(name.decoration(TextDecoration.ITALIC, false));
    if(damage != null) {lore.add(damage.decoration(TextDecoration.ITALIC, false));}
    if(strength != null) {lore.add(strength.decoration(TextDecoration.ITALIC, false));}
    if(critChance != null) {lore.add(critChance.decoration(TextDecoration.ITALIC, false));}
    if(critDamage != null) {lore.add(critDamage.decoration(TextDecoration.ITALIC, false));}
    if(attackSpeed != null) {lore.add(attackSpeed.decoration(TextDecoration.ITALIC, false));}
    if(abilityDamage != null) {lore.add(abilityDamage.decoration(TextDecoration.ITALIC, false));}
    if(health != null) {lore.add(health.decoration(TextDecoration.ITALIC, false));}
    if(defense != null) {lore.add(defense.decoration(TextDecoration.ITALIC, false));}
    if(speed != null) {lore.add(speed.decoration(TextDecoration.ITALIC, false));}
    if(intelligence != null) {lore.add(intelligence.decoration(TextDecoration.ITALIC, false));}
    if(ferocity != null) {lore.add(ferocity.decoration(TextDecoration.ITALIC, false));}
    if(itemInfo.getExtraInfo() != null) {
      if(itemInfo.getExtraInfo().getAbilityLore() != null) {
        lore.addAll(itemInfo.getExtraInfo().getAbilityLore());
      }
    }
    lore.add(Component.text(""));
    if(hasReforge != null) {
      lore.add(hasReforge.decoration(TextDecoration.ITALIC, false));
    }
    lore.add(rarity.decoration(TextDecoration.ITALIC, false));
    meta.lore(lore);
  }

  // Returns formatted String for a stat
  private static String getStatString(double stat) {
    String statString = "";
    if(stat > 0) statString += "+";
    if(stat % 1 == 0) {
      return statString + (int) stat;
    } else {
      return statString + stat;
    }
  }

  // Recombobulates the custom item in the Player's main hand. This upgrades the rarity by 1
  public static void recombobulate(ItemStack item) {

    ItemMeta meta = item.getItemMeta();
    ItemInfo info = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
    if(info != null) {
      Rarity currentRarity = info.getRarity();
      if(currentRarity != Rarity.SPECIAL && !(info.isRecomb())) {
        Rarity recombRarity = currentRarity.next();
        info.setRarity(recombRarity);
        info.setRecomb(true);
        if(info.getReforge() != null) {
          Reforge reforge = info.getReforge();
          WeaponStats weaponStats = info.getWeaponStats();
          ArmorStats armorStats = info.getArmorStats();
          AbilityStats abilityStats = info.getAbilityStats();
          WeaponStats reforgeWeaponStats = reforge.getWeaponStats(recombRarity);
          ArmorStats reforgeArmorStats = reforge.getArmorStats(recombRarity);
          AbilityStats reforgeAbilityStats = reforge.getAbilityStats(recombRarity);
          info.setReforge(reforge);
          if(info.getItemType() == ItemType.SWORD || info.getItemType() == ItemType.BOW) {
            weaponStats.setDamage(weaponStats.getBaseDamage() + info.getPotatoBooks() * 2 + reforgeWeaponStats.getDamage());
            weaponStats.setStrength(weaponStats.getBaseStrength() + info.getPotatoBooks() * 2 + reforgeWeaponStats.getStrength());
          } else {
            weaponStats.setDamage(weaponStats.getBaseDamage() + reforgeWeaponStats.getDamage());
            weaponStats.setStrength(weaponStats.getBaseStrength() + reforgeWeaponStats.getStrength());
          }
          weaponStats.setCritChance(weaponStats.getBaseCritChance() + reforgeWeaponStats.getCritChance());
          weaponStats.setCritDamage(weaponStats.getBaseCritDamage() + reforgeWeaponStats.getCritDamage());
          weaponStats.setAttackSpeed(weaponStats.getBaseAttackSpeed() + reforgeWeaponStats.getAttackSpeed());
          weaponStats.setFerocity(weaponStats.getBaseFerocity() + reforgeWeaponStats.getFerocity());
          if(info.getItemType() == ItemType.HELMET || info.getItemType() == ItemType.CHESTPLATE || info.getItemType() == ItemType.LEGGINGS || info.getItemType() == ItemType.BOOTS) {
            armorStats.setHealth(armorStats.getBaseHealth() + info.getPotatoBooks() * 4 + reforgeArmorStats.getHealth());
            armorStats.setDefense(armorStats.getBaseDefense() + info.getPotatoBooks() * 2 + reforgeArmorStats.getDefense());
          } else {
            armorStats.setHealth(armorStats.getBaseHealth() + reforgeArmorStats.getHealth());
            armorStats.setDefense(armorStats.getBaseDefense() + reforgeArmorStats.getDefense());
          }
          armorStats.setSpeed(armorStats.getBaseSpeed() + reforgeArmorStats.getSpeed());
          abilityStats.setAbilityDamage(abilityStats.getBaseAbilityDamage() + reforgeAbilityStats.getAbilityDamage());
          abilityStats.setIntelligence(abilityStats.getBaseIntelligence() + reforgeAbilityStats.getIntelligence());
          info.setWeaponStats(weaponStats);
          info.setArmorStats(armorStats);
          info.setAbilityStats(abilityStats);
        }
        meta.getPersistentDataContainer().set(itemKey, new ItemInfoDataType(), info);
        updateLore(meta);
        item.setItemMeta(meta);
      }
    }
  }

  // If the custom item does not already have 15 Potato Books, adds a Potato Book to the item in the Player's main hand
  public static void hotPotatoBook(Player player) {
    ItemStack item = player.getInventory().getItemInMainHand();
    ItemMeta meta = item.getItemMeta();
    ItemInfo itemInfo = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
    if(itemInfo == null) return;
    int currentPotatoBooks = itemInfo.getPotatoBooks();
    if(currentPotatoBooks < 15) {
      itemInfo.setPotatoBooks(currentPotatoBooks + 1);
      WeaponStats weaponStats = itemInfo.getWeaponStats();
      ArmorStats armorStats = itemInfo.getArmorStats();
      double reforgeHealth = 0, reforgeDefense = 0, reforgeDamage = 0, reforgeStrength = 0;
      if(itemInfo.getReforge() != null) {
        WeaponStats reforgeWeaponStats = itemInfo.getReforge().getWeaponStats(itemInfo.getRarity());
        ArmorStats reforgeArmorStats = itemInfo.getReforge().getArmorStats(itemInfo.getRarity());
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
      itemInfo.setWeaponStats(weaponStats);
      itemInfo.setArmorStats(armorStats);
      meta.getPersistentDataContainer().set(itemKey, new ItemInfoDataType(), itemInfo);
      updateLore(meta);
      item.setItemMeta(meta);
      player.sendMessage(Component.text("You applied a Potato Book to your item with").append(Component.text(" " + currentPotatoBooks, NamedTextColor.GOLD)).append(Component.text(" Potato Books. It now has")).append(Component.text(" " + (currentPotatoBooks + 1), NamedTextColor.GOLD)).append(Component.text(" Potato Books out of a maximum of")).append(Component.text(" 15", NamedTextColor.GOLD)).decoration(TextDecoration.ITALIC, false));
    } else {
      player.sendMessage(Component.text("Your held item already has a maximum of").append(Component.text(" 15", NamedTextColor.GOLD)).append(Component.text(" Potato Books")).decoration(TextDecoration.ITALIC, false));
    }
  }

  // Reforges the custom item in the Player's main hand with the Reforge
  public static void reforge(ItemStack item, Reforge reforge, Rarity rarity) {
    if(reforge == null) return;
    ItemMeta meta = item.getItemMeta();
    ItemInfo itemInfo = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
    if(itemInfo == null) return;
    if(rarity == null) {
      rarity = itemInfo.getRarity();
    }
    if(reforge.getItemTypes().contains(itemInfo.getItemType())) {
      WeaponStats weaponStats = itemInfo.getWeaponStats();
      ArmorStats armorStats = itemInfo.getArmorStats();
      AbilityStats abilityStats = itemInfo.getAbilityStats();
      WeaponStats reforgeWeaponStats = reforge.getWeaponStats(rarity);
      ArmorStats reforgeArmorStats = reforge.getArmorStats(rarity);
      AbilityStats reforgeAbilityStats = reforge.getAbilityStats(rarity);
      itemInfo.setReforge(reforge);
      if(itemInfo.getItemType() == ItemType.SWORD || itemInfo.getItemType() == ItemType.BOW) {
        weaponStats.setDamage(weaponStats.getBaseDamage() + itemInfo.getPotatoBooks() * 2 + reforgeWeaponStats.getDamage());
        weaponStats.setStrength(weaponStats.getBaseStrength() + itemInfo.getPotatoBooks() * 2 + reforgeWeaponStats.getStrength());
      } else {
        weaponStats.setDamage(weaponStats.getBaseDamage() + reforgeWeaponStats.getDamage());
        weaponStats.setStrength(weaponStats.getBaseStrength() + reforgeWeaponStats.getStrength());
      }
      weaponStats.setCritChance(weaponStats.getBaseCritChance() + reforgeWeaponStats.getCritChance());
      weaponStats.setCritDamage(weaponStats.getBaseCritDamage() + reforgeWeaponStats.getCritDamage());
      weaponStats.setAttackSpeed(weaponStats.getBaseAttackSpeed() + reforgeWeaponStats.getAttackSpeed());
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
      itemInfo.setWeaponStats(weaponStats);
      itemInfo.setArmorStats(armorStats);
      itemInfo.setAbilityStats(abilityStats);
      meta.getPersistentDataContainer().set(itemKey, new ItemInfoDataType(), itemInfo);
      updateLore(meta);
      item.setItemMeta(meta);
    }
  }


}
