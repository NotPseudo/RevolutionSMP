package me.notpseudo.revolutionsmp.items;

import me.notpseudo.revolutionsmp.datacontainers.*;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataContainer;

public class ArmorCreator {

  private static NamespacedKey itemKey = ItemEditor.getItemKey();
  private static NamespacedKey weaponKey = ItemEditor.getWeaponKey();
  private static NamespacedKey armorKey = ItemEditor.getArmorKey();
  private static NamespacedKey abilityKey = ItemEditor.getAbilityKey();

  public static ItemStack stormChest;
  public static ItemStack stormLeg;
  public static ItemStack stormBoot;

  public static void createArmors() {
    stormChest();
    stormLeg();
    stormBoot();
  }

  private static void stormChest() {
    stormChest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
    ItemMeta stormChestMeta = stormChest.getItemMeta();
    LeatherArmorMeta leatherMeta = (LeatherArmorMeta) stormChestMeta;
    leatherMeta.setColor(Color.fromRGB(0x1793C4));
    PersistentDataContainer container = stormChestMeta.getPersistentDataContainer();
    container.set(itemKey, new ItemInfoDataType(), new ItemInfo("Storm's Chestplate", Rarity.LEGENDARY, null, ItemType.CHESTPLATE));
    container.set(weaponKey, new WeaponStatsDataType(), new WeaponStats(0, 0, 0, 0, 0));
    container.set(armorKey, new ArmorStatsDataType(), new ArmorStats(260, 120, 0));
    container.set(abilityKey, new AbilityStatsDataType(), new AbilityStats(0, 250, null, true, null, true, null, true));
    ItemEditor.updateLore(stormChestMeta);
    stormChest.setItemMeta(stormChestMeta);
  }

  private static void stormLeg() {
    stormLeg = new ItemStack(Material.LEATHER_LEGGINGS, 1);
    ItemMeta stormLegMeta = stormLeg.getItemMeta();
    LeatherArmorMeta leatherMeta = (LeatherArmorMeta) stormLegMeta;
    leatherMeta.setColor(Color.fromRGB(0x17A8C4));
    PersistentDataContainer container = stormLegMeta.getPersistentDataContainer();
    container.set(itemKey, new ItemInfoDataType(), new ItemInfo("Storm's Leggings", Rarity.LEGENDARY, null, ItemType.LEGGINGS));
    container.set(weaponKey, new WeaponStatsDataType(), new WeaponStats(0, 0, 0, 0, 0));
    container.set(armorKey, new ArmorStatsDataType(), new ArmorStats(230, 105, 0));
    container.set(abilityKey, new AbilityStatsDataType(), new AbilityStats(0, 250, null, true, null, true, null, true));
    ItemEditor.updateLore(stormLegMeta);
    stormLeg.setItemMeta(stormLegMeta);
  }

  private static void stormBoot() {
    stormBoot = new ItemStack(Material.LEATHER_BOOTS, 1);
    ItemMeta stormBootMeta = stormBoot.getItemMeta();
    LeatherArmorMeta leatherMeta = (LeatherArmorMeta) stormBootMeta;
    leatherMeta.setColor(Color.fromRGB(0x1CD4E4));
    PersistentDataContainer container = stormBootMeta.getPersistentDataContainer();
    container.set(itemKey, new ItemInfoDataType(), new ItemInfo("Storm's Boots", Rarity.LEGENDARY, null, ItemType.BOOTS));
    container.set(weaponKey, new WeaponStatsDataType(), new WeaponStats(0, 0, 0, 0, 0));
    container.set(armorKey, new ArmorStatsDataType(), new ArmorStats(145, 65, 0));
    container.set(abilityKey, new AbilityStatsDataType(), new AbilityStats(0, 250, null, true, null, true, null, true));
    ItemEditor.updateLore(stormBootMeta);
    stormBoot.setItemMeta(stormBootMeta);
  }

}
