package me.notpseudo.revolutionsmp.items;

import me.notpseudo.revolutionsmp.abilities.Ability;
import me.notpseudo.revolutionsmp.datacontainers.*;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;

// Creates custom weapons
public class WeaponCreator {

  private static NamespacedKey itemKey = ItemEditor.getItemKey();
  private static NamespacedKey weaponKey = ItemEditor.getWeaponKey();
  private static NamespacedKey armorKey = ItemEditor.getArmorKey();
  private static NamespacedKey abilityKey = ItemEditor.getAbilityKey();

  public static ItemStack hyperion;
  public static ItemStack valkyrie;
  public static ItemStack scylla;
  public static ItemStack astraea;
  public static ItemStack aote;
  public static ItemStack jujuShortbow;
  public static ItemStack terminator;

  public static void createWeapons() {
    hyperion();
    valkyrie();
    scylla();
    astraea();
    aote();
    jujuShortbow();
    terminator();
  }

  private static void hyperion() {
    hyperion = new ItemStack(Material.IRON_SWORD, 1);
    ItemMeta hyperionMeta = hyperion.getItemMeta();
    PersistentDataContainer container = hyperionMeta.getPersistentDataContainer();
    container.set(itemKey, new ItemInfoDataType(), new ItemInfo("Hyperion", Rarity.LEGENDARY, null, ItemType.SWORD));
    container.set(weaponKey, new WeaponStatsDataType(), new WeaponStats(260, 150, 0, 0, 30));
    container.set(armorKey, new ArmorStatsDataType(), new ArmorStats(0, 0, 0));
    container.set(abilityKey, new AbilityStatsDataType(), new AbilityStats(0, 350, null, true, null, true,null, true));
    ItemEditor.updateLore(hyperionMeta);
    hyperion.setItemMeta(hyperionMeta);
  }

  private static void valkyrie() {
    valkyrie = new ItemStack(Material.IRON_SWORD, 1);
    ItemMeta valkyrieMeta = valkyrie.getItemMeta();
    PersistentDataContainer container = valkyrieMeta.getPersistentDataContainer();
    container.set(itemKey, new ItemInfoDataType(), new ItemInfo("Valkyrie", Rarity.LEGENDARY, null, ItemType.SWORD));
    container.set(weaponKey, new WeaponStatsDataType(), new WeaponStats(270, 145, 0, 0, 60));
    container.set(armorKey, new ArmorStatsDataType(), new ArmorStats(0, 0, 0));
    container.set(abilityKey, new AbilityStatsDataType(), new AbilityStats(0, 60, null, true, null, true,null, true));
    ItemEditor.updateLore(valkyrieMeta);
    valkyrie.setItemMeta(valkyrieMeta);
  }

  private static void scylla() {
    scylla = new ItemStack(Material.IRON_SWORD, 1);
    ItemMeta scyllaMeta = scylla.getItemMeta();
    PersistentDataContainer container = scyllaMeta.getPersistentDataContainer();
    container.set(itemKey, new ItemInfoDataType(), new ItemInfo("Scylla", Rarity.LEGENDARY, null, ItemType.SWORD));
    container.set(weaponKey, new WeaponStatsDataType(), new WeaponStats(270, 150, 12, 35, 30));
    container.set(armorKey, new ArmorStatsDataType(), new ArmorStats(0, 0, 0));
    container.set(abilityKey, new AbilityStatsDataType(), new AbilityStats(0, 50, null, true, null, true,null, true));
    ItemEditor.updateLore(scyllaMeta);
    scylla.setItemMeta(scyllaMeta);
  }

  private static void astraea() {
    astraea = new ItemStack(Material.IRON_SWORD, 1);
    ItemMeta astraeaMeta = astraea.getItemMeta();
    PersistentDataContainer container = astraeaMeta.getPersistentDataContainer();
    container.set(itemKey, new ItemInfoDataType(), new ItemInfo("Astraea", Rarity.LEGENDARY, null, ItemType.SWORD));
    container.set(weaponKey, new WeaponStatsDataType(), new WeaponStats(270, 150, 0, 0, 30));
    container.set(armorKey, new ArmorStatsDataType(), new ArmorStats(0, 250, 0));
    container.set(abilityKey, new AbilityStatsDataType(), new AbilityStats(0, 50, null, true, null, true,null, true));
    ItemEditor.updateLore(astraeaMeta);
    astraea.setItemMeta(astraeaMeta);
  }

  private static void aote() {
    aote = new ItemStack(Material.DIAMOND_SWORD, 1);
    ItemMeta aoteMeta = aote.getItemMeta();
    PersistentDataContainer container = aoteMeta.getPersistentDataContainer();
    container.set(itemKey, new ItemInfoDataType(), new ItemInfo("Aspect of the End", Rarity.RARE, null, ItemType.SWORD));
    container.set(weaponKey, new WeaponStatsDataType(), new WeaponStats(100, 100, 0, 0, 0));
    container.set(armorKey, new ArmorStatsDataType(), new ArmorStats(0, 0, 0));
    container.set(abilityKey, new AbilityStatsDataType(), new AbilityStats(0, 0, Ability.INSTANT_TRANSMISSION, true, null, true, null, true));
    ItemEditor.updateLore(aoteMeta);
    aote.setItemMeta(aoteMeta);
  }

  private static void jujuShortbow() {
    jujuShortbow = new ItemStack(Material.BOW, 1);
    ItemMeta jujuShortbowMeta = jujuShortbow.getItemMeta();
    PersistentDataContainer container = jujuShortbowMeta.getPersistentDataContainer();
    container.set(itemKey, new ItemInfoDataType(), new ItemInfo("Juju Shortbow", Rarity.EPIC, null, ItemType.BOW));
    container.set(weaponKey, new WeaponStatsDataType(), new WeaponStats(310, 40, 10, 110, 0));
    container.set(armorKey, new ArmorStatsDataType(), new ArmorStats(0, 0, 0));
    container.set(abilityKey, new AbilityStatsDataType(), new AbilityStats(0, 0, null, true, null, true,null, true));
    ItemEditor.updateLore(jujuShortbowMeta);
    jujuShortbow.setItemMeta(jujuShortbowMeta);
  }

  private static void terminator() {
    terminator = new ItemStack(Material.BOW, 1);
    ItemMeta terminatorMeta = terminator.getItemMeta();
    PersistentDataContainer container = terminatorMeta.getPersistentDataContainer();
    container.set(itemKey, new ItemInfoDataType(), new ItemInfo("Terminator", Rarity.LEGENDARY, null, ItemType.BOW));
    container.set(weaponKey, new WeaponStatsDataType(), new WeaponStats(310, 50, 0, 250, 0));
    container.set(armorKey, new ArmorStatsDataType(), new ArmorStats(0, 0, 0));
    container.set(abilityKey, new AbilityStatsDataType(), new AbilityStats(0, 0, null, true, null, true,null, true));
    ItemEditor.updateLore(terminatorMeta);
    terminator.setItemMeta(terminatorMeta);
  }

}
