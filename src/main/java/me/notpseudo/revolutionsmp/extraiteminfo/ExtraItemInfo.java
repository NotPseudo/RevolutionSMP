package me.notpseudo.revolutionsmp.extraiteminfo;

import me.notpseudo.revolutionsmp.abilities.AbilityType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExtraItemInfo implements Serializable {

    private List<AbilityType> abilityList;

    public ExtraItemInfo(List<AbilityType> abilityList) {
        this.abilityList = abilityList;
    }

    public List<AbilityType> getAbilityList() {
        return abilityList;
    }

    public void setAbilityList(List<AbilityType> abilityList) {
        this.abilityList = abilityList;
    }

    public void addAbility(AbilityType ability) {
        abilityList.add(ability);
    }

    public List<Component> getAbilityLore() {
        if(abilityList != null) {
            List<Component> abilityLore = new ArrayList<>();
            for(AbilityType ability : abilityList) {
                abilityLore.add(Component.text(""));
                abilityLore.add(Component.text("Ability: " + ability.getAbilityName() + " ", NamedTextColor.GOLD).append(Component.text(ability.getAbilityType().getName(), NamedTextColor.YELLOW, TextDecoration.BOLD)).decoration(TextDecoration.ITALIC, false));
                abilityLore.addAll(ability.getAbilityInfo());
            }
            return abilityLore;
        }
        return null;
    }

}
