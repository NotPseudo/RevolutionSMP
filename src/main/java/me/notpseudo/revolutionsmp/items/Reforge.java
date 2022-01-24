package me.notpseudo.revolutionsmp.items;

import me.notpseudo.revolutionsmp.statobjects.AbilityStats;
import me.notpseudo.revolutionsmp.statobjects.ArmorStats;
import me.notpseudo.revolutionsmp.statobjects.WeaponStats;

import java.util.List;

// Enum of Reforges that can be applied to a custom item
public enum Reforge {
  EPIC {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.SWORD);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 15, 0, 10, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null);}
   },
  SHARP {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.SWORD);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 0, 10, 20, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null);}
  },
  HEROIC {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.SWORD);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 15, 0, 0, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 40,null);}
  },
  SPICY {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.SWORD);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 2, 1, 25, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0, null);}
  },
  LEGENDARY {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.SWORD);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 3, 5, 5, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 5,null);}
  },
  DIRTY {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.SWORD);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 2, 0, 0, 2);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null);}
  },
  FABLED {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.SWORD);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 30, 0, 15, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null);}
  },
  SUSPICIOUS {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.SWORD);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 0, 1, 30, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null);}
  },
  WITHERED {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.SWORD);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 70, 0, 0, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null);}
  },
  BULKY {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.SWORD);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 0, 0, 0, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(4, 2, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null);}
  },
  GRAND {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.BOW);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 25, 0, 0, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null);}
  },
  HASTY {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.BOW);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 3, 20, 0, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null);}
  },
  RAPID {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.BOW);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 2, 0, 35, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null);}
  },
  UNREAL {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.BOW);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 3, 8, 5, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null);}
  },
  AWKWARD {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.BOW);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 0, 10, 5, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, -5,null);}
  },
  PRECISE {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.BOW);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 3, 8, 5, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null);}
  },
  SPIRITUAL {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.BOW);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 4, 7, 10, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null);}
  },
  FIERCE {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 2, 2, 4, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null);}
  },
  HEAVY {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 0, 0, -1, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 25, -1);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null);}
  },
  LIGHT {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 0, 1, 1, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(5, 1, 20);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null);}
  },
  MYTHIC {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 2, 1, 1, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(2, 2, 2);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 20,null);}
  },
  SMART {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 0, 0, 0, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(4, 4, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 20,null);}
  },
  TITANIC {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 0, 0, 0, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(10, 10, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null);}
  },
  WISE {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 0, 0, 0, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(6, 0, 1);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 25,null);}
  },
  PERFECT {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 0, 0, 0, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 25, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null);}
  },
  NECROTIC {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 0, 0, 0, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 30,null);}
  },
  ANCIENT {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 4, 3, 0, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(7, 7, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 6,null);}
  },
  RENOWNED {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 3, 2, 3, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(2, 2, 1);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 3,null);}
  },
  LOVING {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.CHESTPLATE);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 0, 0, 0, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(4, 4, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(5, 20,null);}
  },
  GIANT {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 0, 0, 0, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(50, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null);}
  },
  ARMORTEST {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(0, 100, 100, 100, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(100, 100, 20);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null);}
  },
  WEAPONTEST {
    @Override
    public List<ItemType> getItemTypes() {return List.of(ItemType.SWORD, ItemType.BOW);}
    @Override
    public WeaponStats getWeaponStats() {return new WeaponStats(100, 1000, 100, 100, 0);}
    @Override
    public ArmorStats getArmorStats() {return new ArmorStats(0, 0, 0);}
    @Override
    public AbilityStats getAbilityStats() {return new AbilityStats(0, 0,null);}
  };
  // Gets list of ItemTypes the Reforge can be applied to
  public abstract List<ItemType> getItemTypes();
  // Gets the weapon stats the Reforge boosts
  public abstract WeaponStats getWeaponStats();
  // Gets the armor stats the Reforge boosts
  public abstract ArmorStats getArmorStats();
  // Gets the ability statsd the Reforge boosts
  public abstract AbilityStats getAbilityStats();
}
