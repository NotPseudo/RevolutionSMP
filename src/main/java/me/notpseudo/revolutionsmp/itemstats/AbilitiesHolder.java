package me.notpseudo.revolutionsmp.itemstats;

import me.notpseudo.revolutionsmp.abilities.AbilityObject;
import me.notpseudo.revolutionsmp.abilities.AbilityType;
import me.notpseudo.revolutionsmp.abilities.AbilityUseType;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentObject;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
            abilities.add(type.createObject(this));
        }
        int totalWitherScrollAbilityCount = (int) Arrays.stream(AbilityType.values()).filter(AbilityType::isWitherScrollAbility).count() - 1;
        int heldWitherScrollAbilities = (int) abilities.stream().filter(heldType -> heldType.getAbilityType().isWitherScrollAbility()).count();
        if(heldWitherScrollAbilities == totalWitherScrollAbilityCount || containsAbility(AbilityType.WITHER_IMPACT)) {
            abilities.removeIf(remove -> remove.getAbilityType().isWitherScrollAbility());
            abilities.add(AbilityType.WITHER_IMPACT.createObject(this));
        }
    }

    public void combine(AbilitiesHolder other) {
        if (other == null) {
            return;
        }
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
        ArrayList<AbilityObject> witherScrollAbilities = new ArrayList<>();
        for(AbilityObject abilityObject : abilities) {
            if(abilityObject.getAbilityType().isWitherScrollAbility()) {
                witherScrollAbilities.add(abilityObject);
            } else {
                lore.addAll(abilityObject.getText());
                if (abilities.indexOf(abilityObject) < abilities.size() - 1) {
                    lore.add(Component.empty());
                }
            }
        }
        if(witherScrollAbilities.size() > 0) {
            lore.add(Component.text("Scroll Abilities:", NamedTextColor.GREEN));
            for(AbilityObject witherScrollAbility : witherScrollAbilities) {
                lore.addAll(witherScrollAbility.getText());
                if (witherScrollAbilities.indexOf(witherScrollAbility) < witherScrollAbilities.size() - 1) {
                    lore.add(Component.empty());
                }
            }
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
                    ability.setCooldown(ability.getAbilityType().getCooldown());
                }
            }
        }
    }

}