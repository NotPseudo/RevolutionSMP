package me.notpseudo.revolutionsmp.items;

import me.notpseudo.revolutionsmp.datacontainers.AbilityStats;
import me.notpseudo.revolutionsmp.datacontainers.ArmorStats;
import me.notpseudo.revolutionsmp.datacontainers.WeaponStats;

import java.util.List;

public enum Reforge {
  EPIC {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.SWORD);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 15, 0, 10, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null, false, null, false, null, false);}
   },
  SHARP {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.SWORD);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 0, 10, 20, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null, false, null, false, null, false);}
  },
  HEROIC {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.SWORD);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 15, 0, 0, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 40,null, false, null, false, null, false);}
  },
  SPICY {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.SWORD);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 2, 1, 25, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0, null, false, null, false, null, false);}
  },
  LEGENDARY {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.SWORD);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 3, 5, 5, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 5,null, false, null, false, null, false);}
  },
  DIRTY {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.SWORD);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 2, 0, 0, 2);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null, false, null, false, null, false);}
  },
  FABLED {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.SWORD);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 30, 0, 15, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null, false, null, false, null, false);}
  },
  SUSPICIOUS {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.SWORD);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 0, 1, 30, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null, false, null, false, null, false);}
  },
  WITHERED {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.SWORD);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 70, 0, 0, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null, false, null, false, null, false);}
  },
  BULKY {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.SWORD);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 0, 0, 0, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(4, 2, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null, false, null, false, null, false);}
  },
  GRAND {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.BOW);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 25, 0, 0, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null, false, null, false, null, false);}
  },
  HASTY {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.BOW);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 3, 20, 0, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null, false, null, false, null, false);}
  },
  RAPID {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.BOW);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 2, 0, 35, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null, false, null, false, null, false);}
  },
  UNREAL {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.BOW);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 3, 8, 5, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null, false, null, false, null, false);}
  },
  AWKWARD {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.BOW);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 0, 10, 5, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, -5,null, false, null, false, null, false);}
  },
  PRECISE {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.BOW);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 3, 8, 5, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null, false, null, false, null, false);}
  },
  SPIRITUAL {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.BOW);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 4, 7, 10, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null, false, null, false, null, false);}
  },
  FIERCE {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 2, 2, 4, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null, false, null, false, null, false);}
  },
  HEAVY {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 0, 0, -1, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 25, -1);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null, false, null, false, null, false);}
  },
  LIGHT {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 0, 1, 1, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(5, 1, 20);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null, false, null, false, null, false);}
  },
  MYTHIC {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 2, 1, 1, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(2, 2, 2);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 20,null, false, null, false, null, false);}
  },
  SMART {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 0, 0, 0, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(4, 4, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 20,null, false, null, false, null, false);}
  },
  TITANIC {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 0, 0, 0, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(10, 10, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null, false, null, false, null, false);}
  },
  WISE {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 0, 0, 0, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(6, 0, 1);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 25,null, false, null, false, null, false);}
  },
  PERFECT {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 0, 0, 0, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 25, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null, false, null, false, null, false);}
  },
  NECROTIC {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 0, 0, 0, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 30,null, false, null, false, null, false);}
  },
  ANCIENT {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 4, 3, 0, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(7, 7, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 6,null, false, null, false, null, false);}
  },
  RENOWNED {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 3, 2, 3, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(2, 2, 1);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 3,null, false, null, false, null, false);}
  },
  LOVING {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.CHESTPLATE);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 0, 0, 0, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(4, 4, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(5, 20,null, false, null, false, null, false);}
  },
  GIANT {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 0, 0, 0, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(50, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null, false, null, false, null, false);}
  };
  public abstract List<ItemType> getItemTypes();
  public abstract WeaponStats getWeaponStats();
  public abstract ArmorStats getArmorStats();
  public abstract AbilityStats getAbilityStats();
}
