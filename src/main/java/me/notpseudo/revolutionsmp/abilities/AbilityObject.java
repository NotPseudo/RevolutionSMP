package me.notpseudo.revolutionsmp.abilities;

import me.notpseudo.revolutionsmp.listeners.StatsListeners;
import me.notpseudo.revolutionsmp.playerstats.PlayerStats;
import me.notpseudo.revolutionsmp.playerstats.PlayerStatsDataType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.ArrayList;

public class AbilityObject implements Serializable {

    private final AbilityType abilityType;
    private double manaCost;
    private double cooldown;

    public AbilityObject(AbilityType abilityType) {
        this.abilityType = abilityType;
        manaCost = abilityType.getManaCost();
        cooldown = abilityType.getCooldown();
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

    public double getCooldown() {
        return cooldown;
    }

    public void setCooldown(double cooldown) {
        this.cooldown = cooldown;
    }

    public void use(Player player) {
        abilityType.use(player);
        takeMana(player);
        abilityType.addToCooldownList(player.getUniqueId(), abilityType.getCooldown());
    }

    public ArrayList<Component> getText() {
        ArrayList<Component> lines = new ArrayList<>();
        lines.add(Component.text("Ability: " + abilityType, NamedTextColor.GOLD)
                .append(Component.text(" " + abilityType.getAbilityUseType().toString().toUpperCase(), NamedTextColor.YELLOW, TextDecoration.BOLD)));
        lines.addAll(abilityType.getDescription());
        if (manaCost != 0) {
            lines.add(Component.text("Mana Cost: ", NamedTextColor.DARK_GRAY).append(Component.text(Math.round(manaCost), NamedTextColor.DARK_AQUA)));
        }
        if (abilityType.getCooldown() != 0 && abilityType.showCooldown()) {
            lines.add(Component.text("Cooldown: ", NamedTextColor.DARK_GRAY).append(Component.text(Math.round(abilityType.getCooldown()) + "s", NamedTextColor.GREEN)));
        }
        return lines;
    }

    public boolean canUse(Player player) {
        if (!AbilitiesUtil.hasEnoughMana(player, this)) {
            StatsListeners.showNoManaActionBar(player);
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1F, 0.5F);
            return false;
        }
        if (!AbilitiesUtil.outOfCooldown(player, this)) {
            if (abilityType.showCooldown()) {
                AbilitiesUtil.sendCooldownMessage(player);
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1F, 0.5F);
            }
            return false;
        }
        return true;
    }

    public void takeMana(Player player) {
        double actualCost = 0;
        if(!AbilitiesUtil.getNoMana().contains(player.getUniqueId())) {
            PlayerStats playerStats = player.getPersistentDataContainer().get(StatsListeners.getPlayerStatsKey(), new PlayerStatsDataType());
            if (playerStats == null) {
                playerStats = new PlayerStats();
            }
            playerStats.setMana(playerStats.getMana() - manaCost);
            player.getPersistentDataContainer().set(StatsListeners.getPlayerStatsKey(), new PlayerStatsDataType(), playerStats);
            actualCost = manaCost;
        }
        StatsListeners.showAbilityActionBar(player, abilityType, actualCost);
    }

}