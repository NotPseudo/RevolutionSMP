package me.notpseudo.revolutionsmp.itemstats;

import me.notpseudo.revolutionsmp.enchantments.EnchantmentObject;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnchantmentsHolder implements Serializable {

    private final ItemInfo HOLDER;
    private ArrayList<EnchantmentObject> enchants;

    public EnchantmentsHolder(ItemInfo holder) {
        HOLDER = holder;
        enchants = new ArrayList<>();
    }

    public ArrayList<EnchantmentObject> getEnchants() {
        return enchants;
    }

    public void addEnchant(EnchantmentObject addEnchant) {
        if (!addEnchant.getType().getItemTypes().contains(HOLDER.getItemType())) {
            return;
        }
        for (EnchantmentType incompatibleType : addEnchant.getType().getIncompatibleEnchants()) {
            removeEnchant(incompatibleType);
        }
        if (addEnchant.getType() == EnchantmentType.CUSTOM_EFFICIENCY) {
            int silex = 0;
            ModifierInfo mod = HOLDER.getModifiers();
            if (mod != null) {
                silex = mod.getSilex();
            }
            addEnchant.setLevel(Math.min(10, addEnchant.getLevel() + silex));
        }
        EnchantmentObject toAdd = getObjectForType(addEnchant.getType());
        if (toAdd != null) {
            int level = toAdd.getLevel(), enchLevel = toAdd.getType().getEnchTableMax();
            if (addEnchant.getLevel() > level) {
                toAdd.setLevel(addEnchant.getLevel());
            } else if (addEnchant.getLevel() == level) {
                if ((level < enchLevel || level > enchLevel) && level < toAdd.getType().getMaxLevel()) {
                    toAdd.setLevel(level + 1);
                }
            }
        } else {
            enchants.add(addEnchant);
        }
        reorganize();
    }

    public void combine(EnchantmentsHolder other) {
        if (other == null) {
            return;
        }
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

    public List<String> getEnchantmentsLore() {
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