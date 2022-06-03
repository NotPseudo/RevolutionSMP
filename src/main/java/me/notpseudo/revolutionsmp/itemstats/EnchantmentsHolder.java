package me.notpseudo.revolutionsmp.itemstats;

import me.notpseudo.revolutionsmp.enchantments.EnchantmentObject;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnchantmentsHolder implements Serializable {

    private ArrayList<EnchantmentObject> enchants;

    public EnchantmentsHolder() {
        enchants = new ArrayList<>();
    }

    public ArrayList<EnchantmentObject> getEnchants() {
        return enchants;
    }

    public void addEnchant(EnchantmentObject addEnchant) {
        for (EnchantmentType incompatibleType : addEnchant.getType().getIncompatibleEnchants()) {
            removeEnchant(incompatibleType);
        }
        EnchantmentObject toAdd = getObjectForType(addEnchant.getType());
        if (toAdd != null) {
            if (addEnchant.getLevel() > toAdd.getLevel()) {
                toAdd.setLevel(addEnchant.getLevel());
            } else if (addEnchant.getLevel() == toAdd.getLevel()) {
                if (toAdd.getLevel() < toAdd.getType().getEnchTableMax()) {
                    toAdd.setLevel(toAdd.getLevel() + 1);
                }
            }
        } else {
            enchants.add(addEnchant);
        }
        reorganize();
    }

    public void combine(EnchantmentsHolder other) {
        for (EnchantmentObject addEnchant : other.enchants) {
            addEnchant(addEnchant);
        }
        reorganize();
    }

    public void removeEnchant(EnchantmentType removeEnchant) {
        enchants.removeIf(enchant -> enchant.getType() == removeEnchant);
        reorganize();
    }

    public EnchantmentObject getObjectForType(EnchantmentType type) {
        for (EnchantmentObject enchant : enchants) {
            if (enchant.getType() == type) {
                return enchant;
            }
        }
        return null;
    }

    public boolean containsEnchant(EnchantmentType type) {
        for (EnchantmentObject enchant : enchants) {
            if (enchant.getType() == type) {
                return true;
            }
        }
        return false;
    }

    public void reorganize() {
        ArrayList<EnchantmentObject> ultimate = new ArrayList<>();
        ArrayList<EnchantmentObject> normal = new ArrayList<>();
        for (EnchantmentObject enchant : enchants) {
            if (enchant.getType().isUltimate()) {
                ultimate.add(enchant);
            } else {
                normal.add(enchant);
            }
        }
        Collections.sort(ultimate);
        Collections.sort(normal);
        ultimate.addAll(normal);
        enchants = ultimate;
    }

    public List<String> getLoreList() {
        List<String> loreList = new ArrayList<>();
        for(int i = 0; i < Math.ceil(enchants.size() / 3.0); i++) {
            StringBuilder line = new StringBuilder();
            for(int j = 0; j < 3; j++) {
                int index = i * 3 + j;
                if(index < enchants.size()) {
                    line.append(enchants.get(index).getText());
                    if(index % 3 != 2 && index != enchants.size() - 1) {
                        line.append(", ");
                    }
                }
            }
            loreList.add(line.toString());
        }
        return loreList;
    }

}