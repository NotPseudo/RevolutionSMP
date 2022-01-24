package me.notpseudo.revolutionsmp.extraiteminfo;

import me.notpseudo.revolutionsmp.abilities.Ability;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

import java.util.List;

public class ExtraItemInfo {

    private List<Component> loreList;
    private List<Component> abilityLore;
    private List<Ability> abilityList;

    public ExtraItemInfo(List<Component> loreList, List<Ability> abilityList) {
        this.loreList = loreList;
        this.abilityList = abilityList;
        updateAbilityLore();
    }

    public List<Component> getLoreList() {
        return loreList;
    }

    public void setLoreList(List<Component> loreList) {
        this.loreList = loreList;
    }

    public List<Ability> getAbilityList() {
        return abilityList;
    }

    public void setAbilityList(List<Ability> abilityList) {
        this.abilityList = abilityList;
        updateAbilityLore();
    }

    public void addAbility(Ability ability) {
        abilityList.add(ability);
        updateAbilityLore();
    }

    public List<Component> getAbilityLore() {
        return abilityLore;
    }

    private void updateAbilityLore() {
        if(abilityList != null) {
            for(Ability ability : abilityList) {
                abilityLore.add(Component.text(""));
                abilityLore.add(Component.text("Ability: " + ability.getAbilityName() + " ", NamedTextColor.GOLD).append(Component.text(ability.getAbilityType().getName(), NamedTextColor.YELLOW, TextDecoration.BOLD)).decoration(TextDecoration.ITALIC, false));
            }
        }
    }
}
