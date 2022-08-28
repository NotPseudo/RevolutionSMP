package me.notpseudo.revolutionsmp.abilities;

import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.listeners.StatsListeners;
import me.notpseudo.revolutionsmp.mining.CustomOreLocation;
import me.notpseudo.revolutionsmp.playerstats.PlayerStats;
import me.notpseudo.revolutionsmp.playerstats.PlayerStatsDataType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

import java.io.Serializable;
import java.util.ArrayList;

public class AbilityObject implements Serializable {

    protected final AbilitiesHolder HOLDER;
    private final AbilityType abilityType;
    private double manaCost;
    private double cooldown;

    public AbilityObject(AbilitiesHolder holder, AbilityType abilityType) {
        HOLDER = holder;
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

    public WeaponStats getBonusWeapon(Player player, IncreaseType inc) {
        return abilityType.getBonusWeapon(player, inc);
    }

    public ArmorStats getBonusArmor(Player player, IncreaseType inc) {
        return abilityType.getBonusArmor(player, inc);
    }

    public AbilityStats getBonusAbility(Player player, IncreaseType inc) {
        return abilityType.getBonusAbility(player, inc);
    }

    public FishingStats getBonusFishing(Player fisher, IncreaseType inc) {
        return abilityType.getBonusFishing(fisher, inc);
    }

    public MiningStats getBonusMining(Player miner, IncreaseType inc) {
        return abilityType.getBonusMining(miner, inc);
    }

    public GatheringStats getBonusGathering(Player harvester, IncreaseType inc) {
        return abilityType.getBonusGathering(harvester, inc);
    }

    public LuckStats getBonusLuck(Player player, IncreaseType inc) {
        return abilityType.getBonusLuck(player, inc);
    }

    public RegenStats getBonusRegen(Player player, IncreaseType inc) {
        return abilityType.getBonusRegen(player, inc);
    }

    public WisdomStats getBonusWisdom(Player player, IncreaseType inc) {
        return abilityType.getBonusWisdom(player, inc);
    }

    public WeaponStats getEventWeapon(Player damager, LivingEntity target, EntityDamageEvent event, IncreaseType inc) {
        return abilityType.getEventWeapon(damager, target, event, inc);
    }

    public ArmorStats getEventArmor(LivingEntity damager, Player target, EntityDamageEvent event, IncreaseType inc) {
        return abilityType.getEventArmor(damager, target, event, inc);
    }

    public AbilityStats getEventAbility(Player damager, LivingEntity target, IncreaseType inc) {
        return abilityType.getEventAbility(damager, target, inc);
    }

    public FishingStats getEventFishing(Player fisher, IncreaseType inc) {
        return abilityType.getEventFishing(fisher, inc);
    }

    public MiningStats getEventMining(Player miner, Block block, CustomOreLocation ore, IncreaseType inc) {
        return abilityType.getEventMining(miner, block, ore, inc);
    }

    public GatheringStats getEventGathering(Player harvester, Block block, IncreaseType inc) {
        return abilityType.getEventGathering(harvester, block, inc);
    }

    public LuckStats getEventLuck(Player player, LivingEntity target, IncreaseType inc) {
        return abilityType.getEventLuck(player, target, inc);
    }

}