package me.notpseudo.revolutionsmp.extraiteminfo;

import me.notpseudo.revolutionsmp.abilities.Ability;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExtraItemInfo implements Serializable {

    private List<Ability> abilityList;

    public ExtraItemInfo(List<Ability> abilityList) {
        this.abilityList = abilityList;
    }

    public List<Ability> getAbilityList() {
        return abilityList;
    }

    public void setAbilityList(List<Ability> abilityList) {
        this.abilityList = abilityList;
    }

    public void addAbility(Ability ability) {
        abilityList.add(ability);
    }

    public List<Component> getAbilityLore() {
        if(abilityList != null) {
            List<Component> abilityLore = new ArrayList<>();
            for(Ability ability : abilityList) {
                abilityLore.add(Component.text(""));
                abilityLore.add(Component.text("Ability: " + ability.getAbilityName() + " ", NamedTextColor.GOLD).append(Component.text(ability.getAbilityType().getName(), NamedTextColor.YELLOW, TextDecoration.BOLD)).decoration(TextDecoration.ITALIC, false));
                abilityLore.addAll(ability.getAbilityInfo());
            }
            return abilityLore;
        }
        return null;
    }

}
