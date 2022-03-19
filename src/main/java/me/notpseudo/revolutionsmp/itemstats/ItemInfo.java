package me.notpseudo.revolutionsmp.itemstats;

import me.notpseudo.revolutionsmp.extraiteminfo.ExtraItemInfo;
import me.notpseudo.revolutionsmp.items.ItemID;
import me.notpseudo.revolutionsmp.items.ItemType;
import me.notpseudo.revolutionsmp.items.Rarity;
import me.notpseudo.revolutionsmp.items.Reforge;

import java.io.Serializable;

// Item info stored in an ItemStack's PersistentDataContainer
public class ItemInfo implements Serializable {

  private String name;
  private ItemID itemID;
  private Rarity rarity;
  private boolean recomb;
  private ItemType itemType;
  private int potatoBooks;
  private Reforge reforge;
  private WeaponStats weaponStats;
  private ArmorStats armorStats;
  private AbilityStats abilityStats;
  private ExtraItemInfo extraInfo;

  public ItemInfo(ItemID itemID) {
    this.itemID = itemID;
    this.recomb = false;
    this.potatoBooks = 0;
    this.reforge = null;
    name = itemID.getDefaultName();
    rarity = itemID.getDefaultRarity();
    itemType = itemID.getItemType();
    weaponStats = itemID.getDefaultWeaponStats();
    armorStats = itemID.getDefaultArmorStats();
    abilityStats = itemID.getDefaultAbilityStats();
    extraInfo = itemID.getDefaultExtraInfo();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Rarity getRarity() {
    return rarity;
  }

  public void setRarity(Rarity rarity) {
    this.rarity = rarity;
  }

  public boolean isRecomb() {
    return recomb;
  }

  public void setRecomb(boolean recomb) {
    this.recomb = recomb;
  }

  public ItemType getItemType() {
    return itemType;
  }

  public void setItemType(ItemType itemType) {
    this.itemType = itemType;
  }

  public Reforge getReforge() {
    return reforge;
  }

  public void setReforge(Reforge reforge) {
    if(reforge.getItemTypes().contains(itemType)) {
      this.reforge = reforge;
    }
  }

  public int getPotatoBooks() {
    return potatoBooks;
  }

  public void setPotatoBooks(int potatoBooks) {
    if(potatoBooks <= 15) {
      this.potatoBooks = potatoBooks;
    }
  }

  public ItemID getItemID() {
    return itemID;
  }

  public void setItemID(ItemID itemID) {
    this.itemID = itemID;
  }

  public WeaponStats getWeaponStats() {
    return weaponStats;
  }

  public void setWeaponStats(WeaponStats weaponStats) {
    this.weaponStats = weaponStats;
  }

  public ArmorStats getArmorStats() {
    return armorStats;
  }

  public void setArmorStats(ArmorStats armorStats) {
    this.armorStats = armorStats;
  }

  public AbilityStats getAbilityStats() {
    return abilityStats;
  }

  public void setAbilityStats(AbilityStats abilityStats) {
    this.abilityStats = abilityStats;
  }

  public ExtraItemInfo getExtraInfo() {
    return extraInfo;
  }

  public void setExtraInfo(ExtraItemInfo extraInfo) {
    this.extraInfo = extraInfo;
  }

}