package me.notpseudo.revolutionsmp.datacontainers;

import me.notpseudo.revolutionsmp.items.ItemType;
import me.notpseudo.revolutionsmp.items.Rarity;
import me.notpseudo.revolutionsmp.items.Reforge;
import net.kyori.adventure.text.Component;

import java.io.Serializable;
import java.util.List;

public class ItemInfo implements Serializable {

  private String name;
  private Rarity rarity;
  private boolean recomb;
  private List<Component> lore;
  private ItemType itemType;
  private int potatoBooks;
  private Reforge reforge;

  public ItemInfo(String name, Rarity rarity, List<Component> lore, ItemType itemType) {
    this.name = name;
    this.rarity = rarity;
    this.recomb = false;
    this.lore = lore;
    this.itemType = itemType;
    this.potatoBooks = 0;
    this.reforge = null;
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

  public List<Component> getLore() {
    return lore;
  }

  public void setLore(List<Component> lore) {
    this.lore = lore;
  }

  public ItemType getItemType() {
    return itemType;
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

}
