package me.notpseudo.revolutionsmp.extraiteminfo;

import me.notpseudo.revolutionsmp.abilities.Ability;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

import java.util.List;

public class NecronBladeExtraInfo extends ExtraItemInfo{

    List<Ability> scrollAbilityList;
    List<Component> scrollAbilityLore;

    public NecronBladeExtraInfo() {
        super(null, null);
    }

    public List<Ability> getAbilityList() {
        return scrollAbilityList;
    }

    public void addAbility(Ability ability) {
        if(ability == Ability.IMPLOSION || ability == Ability.SHADOW_WARP || ability == Ability.WITHER_SHIELD) {
            if(scrollAbilityList.contains(ability)) return;
            scrollAbilityList.add(ability);
            if(scrollAbilityList.contains(Ability.IMPLOSION) && scrollAbilityList.contains(Ability.SHADOW_WARP) && scrollAbilityList.contains(Ability.WITHER_IMPACT)) {
                scrollAbilityList.clear();
                scrollAbilityList.add(Ability.WITHER_IMPACT);
            }
            updateScrollAbilityLore();
        }
    }

    private void updateScrollAbilityLore() {
        if(scrollAbilityList != null) {
            scrollAbilityLore.add(Component.text("Scroll Abilities:", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
            for(Ability ability : scrollAbilityList) {
                scrollAbilityLore.add(Component.text("Ability: " + ability.getAbilityName() + " ", NamedTextColor.GOLD).append(Component.text(ability.getAbilityType().getName(), NamedTextColor.YELLOW, TextDecoration.BOLD)).decoration(TextDecoration.ITALIC, false));
            }
        }
    }

}
