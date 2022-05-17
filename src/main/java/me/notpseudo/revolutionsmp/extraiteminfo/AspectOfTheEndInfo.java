package me.notpseudo.revolutionsmp.extraiteminfo;

import me.notpseudo.revolutionsmp.abilities.AbilityType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

import java.util.ArrayList;
import java.util.List;

public class AspectOfTheEndInfo extends ExtraItemInfo {

    private int teleportUpgrades;
    private boolean ether;
    private List<AbilityType> abilityList;

    public AspectOfTheEndInfo() {
        super(null);
        teleportUpgrades = 0;
        ether = false;
        abilityList = new ArrayList<>(List.of(AbilityType.INSTANT_TRANSMISSION));
    }

    @Override
    public List<AbilityType> getAbilityList() {
        return abilityList;
    }

    @Override
    public void setAbilityList(List<AbilityType> abilityList) {
        this.abilityList = abilityList;
    }

    @Override
    public void addAbility(AbilityType ability) {
        abilityList.add(ability);
    }

    @Override
    public List<Component> getAbilityLore() {
        List<Component> abilityLore = new ArrayList<>();
        abilityLore.add(Component.text(""));
        abilityLore.add(Component.text("Ability: Instant Transmission ", NamedTextColor.GOLD).append(Component.text(AbilityType.INSTANT_TRANSMISSION.getAbilityType().getName(), NamedTextColor.YELLOW, TextDecoration.BOLD)).decoration(TextDecoration.ITALIC, false));
        abilityLore.add(Component.text("Teleport ", NamedTextColor.GRAY).append(Component.text(8 + teleportUpgrades + " blocks ", NamedTextColor.GREEN)).append(Component.text("ahead of", NamedTextColor.GRAY)).decoration(TextDecoration.ITALIC, false));
        abilityLore.add(Component.text("you and gain +50 ", NamedTextColor.GRAY).append(Component.text("✦ Speed", NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        abilityLore.add(Component.text("for ", NamedTextColor.GRAY).append(Component.text("3 seconds", NamedTextColor.GREEN)).append(Component.text(".", NamedTextColor.GRAY)).decoration(TextDecoration.ITALIC, false));
        return abilityLore;
    }

    public int getTeleportUpgrades() {
        return teleportUpgrades;
    }

}
