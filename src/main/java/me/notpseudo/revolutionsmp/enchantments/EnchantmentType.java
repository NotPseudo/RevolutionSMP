package me.notpseudo.revolutionsmp.enchantments;

import me.notpseudo.revolutionsmp.items.ItemType;
import me.notpseudo.revolutionsmp.itemstats.AbilityStats;
import me.notpseudo.revolutionsmp.itemstats.ArmorStats;
import me.notpseudo.revolutionsmp.itemstats.WeaponStats;

public enum EnchantmentType {

    SHARPNESS {
        @Override
        public int getMaxLevel() {
            return 7;
        }

        @Override
        public ItemType[] getApplicableItemTypes() {
            return new ItemType[]{ItemType.SWORD};
        }
    };

    public WeaponStats getApplyWeaponStats() {
        return null;
    }

    public ArmorStats getApplyArmorStats() {
        return null;
    }

    public AbilityStats getApplyAbilityStats() {
        return null;
    }

    public double getDamageAdd() {
        return 0;
    }

    public double getDamageMult() {
        return 1;
    }

    public int getMinLevel() {
        return 1;
    }

    public int getMaxLevel() {
        return 5;
    }

    public abstract ItemType[] getApplicableItemTypes();
}