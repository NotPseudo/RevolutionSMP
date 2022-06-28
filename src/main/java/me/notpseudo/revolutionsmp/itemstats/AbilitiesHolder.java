package me.notpseudo.revolutionsmp.itemstats;

import me.notpseudo.revolutionsmp.abilities.AbilityObject;
import me.notpseudo.revolutionsmp.abilities.AbilityType;
import me.notpseudo.revolutionsmp.abilities.AbilityUseType;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentObject;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentType;
import net.kyori.adventure.text.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AbilitiesHolder implements Serializable {

    private ArrayList<AbilityObject> abilities;
    private ItemInfo holder;

    public AbilitiesHolder(ItemInfo holder) {
        abilities = new ArrayList<>();
        this.holder = holder;
    }

    public ArrayList<AbilityObject> getAbilities() {
        return abilities;
    }

    public ItemInfo getHolder() {
        return holder;
    }

    public void addAbility(AbilityType type) {
        if(!containsAbility(type)) {
            abilities.add(type.createObject());
        }
    }

    public void combine(AbilitiesHolder other) {
        for(AbilityObject ability: other.abilities) {
            addAbility(ability.getAbilityType());
        }
    }

    public void removeAbility(AbilityType type) {
        abilities.removeIf(ability -> ability.getAbilityType() == type);
    }

    public boolean containsAbility(AbilityType type) {
        for(AbilityObject ability : abilities) {
            if(ability.getAbilityType() == type) {
                return true;
            }
        }
        return false;
    }

    public boolean containsUseType(AbilityUseType type) {
        for(AbilityObject ability : abilities) {
            if(ability.getAbilityType().getAbilityUseType() == type) {
                return  true;
            }
        }
        return false;
    }

    public List<Component> getAbilitiesLore() {
        List<Component> lore = new ArrayList<>();
        for(AbilityObject abilityObject : abilities) {
            lore.addAll(abilityObject.getText());
            lore.add(Component.text(""));
        }
        return lore;
    }

    public void reorganize() {
        if(holder.getEnchantmentsHolder() != null) {
            EnchantmentObject ultimateWise = holder.getEnchantmentsHolder().getObjectForType(EnchantmentType.ULTIMATE_WISE);
            if(ultimateWise != null) {
                int level = ultimateWise.getLevel();
                double costPercent = 1 - (0.1 * level);
                for(AbilityObject ability : abilities) {
                    ability.setManaCost(ability.getAbilityType().getManaCost() * costPercent);
                }
            }
        }
    }

}