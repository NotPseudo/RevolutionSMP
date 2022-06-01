package me.notpseudo.revolutionsmp.itemstats;

import me.notpseudo.revolutionsmp.enchantments.EnchantmentObject;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentType;

import java.io.Serializable;
import java.util.ArrayList;

public class EnchantmentsHolder implements Serializable {

    private ArrayList<EnchantmentObject> enchants;

    public EnchantmentsHolder() {
        enchants = new ArrayList<>();
    }

    public void addEnchant(EnchantmentObject addEnchant) {
        for(EnchantmentType incompatibleType : addEnchant.getType().getIncompatibleEnchants()) {
            removeEnchant(incompatibleType);
        }
        if(!containsEnchant(addEnchant.getType())) {
            enchants.add(addEnchant);
            return;
        }
        EnchantmentObject toAdd = getObjectForType(addEnchant.getType());
        if(toAdd != null) {
            if(addEnchant.getLevel() > toAdd.getLevel()) {
                toAdd.setLevel(addEnchant.getLevel());
            } else if(addEnchant.getLevel() == toAdd.getLevel()) {
                if(toAdd.getLevel() < toAdd.getType().getEnchTableMax()) {
                    toAdd.setLevel(toAdd.getLevel() + 1);
                }
            }
        }
    }

    public void removeEnchant(EnchantmentType removeEnchant) {
        enchants.removeIf(enchant -> enchant.getType() == removeEnchant);
    }

    public EnchantmentObject getObjectForType(EnchantmentType type) {
        for(EnchantmentObject enchant : enchants) {
            if(enchant.getType() == type) {
                return enchant;
            }
        }
        return null;
    }

    public boolean containsEnchant(EnchantmentType type) {
        for(EnchantmentObject enchant : enchants) {
            if(enchant.getType() == type) {
                return true;
            }
        }
        return false;
    }

}