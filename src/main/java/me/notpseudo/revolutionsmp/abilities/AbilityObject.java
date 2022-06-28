package me.notpseudo.revolutionsmp.abilities;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.ArrayList;

public class AbilityObject implements Serializable {

    private final AbilityType abilityType;
    private double manaCost;

    public AbilityObject(AbilityType abilityType) {
        this.abilityType = abilityType;
        manaCost = abilityType.getManaCost();
    }

    public AbilityType getAbilityType() {
        return abilityType;
    }

    public double getManaCost() {
        return manaCost;
    }

    public void setManaCost(double manaCost) {
        this.manaCost = manaCost;
    }

    public void use(Player player) {
        abilityType.use(player);
    }

    public ArrayList<Component> getText() {
         ArrayList<Component> lines = new ArrayList<>();
         lines.add(Component.text("Ability: " + abilityType, NamedTextColor.GOLD)
                 .append(Component.text(" " + abilityType.getAbilityUseType().toString().toUpperCase(), NamedTextColor.YELLOW, TextDecoration.BOLD)));
         lines.addAll(abilityType.getDescription());
         lines.add(Component.text("Mana Cost: ", NamedTextColor.DARK_GRAY).append(Component.text(Math.round(manaCost), NamedTextColor.DARK_AQUA)));
         return lines;
    }

}