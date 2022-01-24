package me.notpseudo.revolutionsmp.items;

import me.notpseudo.revolutionsmp.statobjects.*;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;

// Creates custom weapons
public class WeaponCreator {

  private static final NamespacedKey itemKey = ItemEditor.getItemKey();

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
    container.set(itemKey, new ItemInfoDataType(), new ItemInfo(ItemID.HYPERION));
    ItemEditor.applyDefaultStats(hyperionMeta);
    ItemEditor.updateLore(hyperionMeta);
    hyperion.setItemMeta(hyperionMeta);
  }

  private static void valkyrie() {
    valkyrie = new ItemStack(Material.IRON_SWORD, 1);
    ItemMeta valkyrieMeta = valkyrie.getItemMeta();
    PersistentDataContainer container = valkyrieMeta.getPersistentDataContainer();
    container.set(itemKey, new ItemInfoDataType(), new ItemInfo(ItemID.VALKYRIE));
    ItemEditor.applyDefaultStats(valkyrieMeta);
    ItemEditor.updateLore(valkyrieMeta);
    valkyrie.setItemMeta(valkyrieMeta);
  }

  private static void scylla() {
    scylla = new ItemStack(Material.IRON_SWORD, 1);
    ItemMeta scyllaMeta = scylla.getItemMeta();
    PersistentDataContainer container = scyllaMeta.getPersistentDataContainer();
    container.set(itemKey, new ItemInfoDataType(), new ItemInfo(ItemID.SCYLLA));
    ItemEditor.applyDefaultStats(scyllaMeta);
    ItemEditor.updateLore(scyllaMeta);
    scylla.setItemMeta(scyllaMeta);
  }

  private static void astraea() {
    astraea = new ItemStack(Material.IRON_SWORD, 1);
    ItemMeta astraeaMeta = astraea.getItemMeta();
    PersistentDataContainer container = astraeaMeta.getPersistentDataContainer();
    container.set(itemKey, new ItemInfoDataType(), new ItemInfo(ItemID.ASTRAEA));
    ItemEditor.applyDefaultStats(astraeaMeta);
    ItemEditor.updateLore(astraeaMeta);
    astraea.setItemMeta(astraeaMeta);
  }

  private static void aote() {
    aote = new ItemStack(Material.DIAMOND_SWORD, 1);
    ItemMeta aoteMeta = aote.getItemMeta();
    PersistentDataContainer container = aoteMeta.getPersistentDataContainer();
    container.set(itemKey, new ItemInfoDataType(), new ItemInfo(ItemID.ASPECT_OF_THE_END));
    ItemEditor.applyDefaultStats(aoteMeta);
    ItemEditor.updateLore(aoteMeta);
    aote.setItemMeta(aoteMeta);
  }

  private static void jujuShortbow() {
    jujuShortbow = new ItemStack(Material.BOW, 1);
    ItemMeta jujuShortbowMeta = jujuShortbow.getItemMeta();
    PersistentDataContainer container = jujuShortbowMeta.getPersistentDataContainer();
    container.set(itemKey, new ItemInfoDataType(), new ItemInfo(ItemID.JUJU_SHORTBOW));
    ItemEditor.applyDefaultStats(jujuShortbowMeta);
    ItemEditor.updateLore(jujuShortbowMeta);
    jujuShortbow.setItemMeta(jujuShortbowMeta);
  }

  private static void terminator() {
    terminator = new ItemStack(Material.BOW, 1);
    ItemMeta terminatorMeta = terminator.getItemMeta();
    PersistentDataContainer container = terminatorMeta.getPersistentDataContainer();
    container.set(itemKey, new ItemInfoDataType(), new ItemInfo(ItemID.TERMINATOR));
    ItemEditor.applyDefaultStats(terminatorMeta);
    ItemEditor.updateLore(terminatorMeta);
    terminator.setItemMeta(terminatorMeta);
  }

}
