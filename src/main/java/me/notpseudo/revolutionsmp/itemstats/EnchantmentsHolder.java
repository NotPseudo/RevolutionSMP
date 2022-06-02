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

    public List<Component> getLoreList() {
        List<Component> loreList = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < Math.ceil(enchants.size() / 3.0); i++) {
            Component line = Component.text("");
            for (int j = 0; j < 3; j++) {
                if (count < enchants.size()) {
                    EnchantmentObject enchant = enchants.get(count);
                    NamedTextColor color = NamedTextColor.BLUE;
                    Component enchantComponent;
                    if(enchant.getLevel() >= enchant.getType().getMaxLevel()) {
                        color = NamedTextColor.GOLD;
                    }
                    if(enchant.getType().isUltimate()) {
                        color = NamedTextColor.LIGHT_PURPLE;
                    }
                    if(count % 3 == 2) {
                        enchantComponent = Component.text(enchant.getType().toString() + " " + enchant.getLevel(), color);
                    } else {
                        enchantComponent = Component.text(enchant.getType().toString() + " " + enchant.getLevel() + ", ", color);
                    }
                    if(enchant.getType().isUltimate()) {
                        enchantComponent.decoration(TextDecoration.BOLD, true);
                    }
                    line.append(enchantComponent);
                }
                count++;
            }
            line.decoration(TextDecoration.ITALIC, false);
            loreList.add(line);
        }
        return loreList;
    }

}