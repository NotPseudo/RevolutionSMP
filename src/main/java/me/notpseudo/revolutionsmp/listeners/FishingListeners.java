package me.notpseudo.revolutionsmp.listeners;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.itemstats.FishingStats;
import me.notpseudo.revolutionsmp.itemstats.IncreaseType;
import me.notpseudo.revolutionsmp.itemstats.StatType;
import me.notpseudo.revolutionsmp.mobstats.SeaCreature;
import me.notpseudo.revolutionsmp.playerstats.PlayerStats;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.List;
import java.util.stream.Stream;

public class FishingListeners implements Listener {

    public FishingListeners(RevolutionSMP plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onFish(PlayerFishEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(Component.text("Fishing Event State: " + ItemEditor.getStringFromEnum(event.getState()), NamedTextColor.GREEN));
        PlayerStats playerStats = StatsListeners.getPlayerStats(player);
        FishingStats add = StatsListeners.getEventFishing(player, IncreaseType.INCREASE),
                addPercent = StatsListeners.getEventFishing(player, IncreaseType.ADDITIVE_PERCENT),
                mult = StatsListeners.getEventFishing(player, IncreaseType.MULTIPLICATIVE_PERCENT);

        if (event.getState() == PlayerFishEvent.State.FISHING) {
            double fishingSpeed = (playerStats.getStat(StatType.FISHING_SPEED) + add.getStatValue(StatType.FISHING_SPEED)) *
                    (1 + (addPercent.getStatValue(StatType.FISHING_SPEED) / 100)) *
                    mult.getStatValue(StatType.FISHING_SPEED);
            double ticks = (int) (Math.random() * 201) + 200;
            ticks *= (1 - (fishingSpeed / 100));
            event.getHook().setMaxWaitTime((int) ticks);
            return;
        }
        double seaCreatureChance = (playerStats.getStat(StatType.SEA_CREATURE_CHANCE) + add.getStatValue(StatType.SEA_CREATURE_CHANCE)) *
                (1 + (addPercent.getStatValue(StatType.SEA_CREATURE_CHANCE) / 100) *
                        mult.getStatValue(StatType.SEA_CREATURE_CHANCE));
        if (Math.random() * 100 >= seaCreatureChance) {
            return;
        }
        if (event.getState() == PlayerFishEvent.State.REEL_IN) {
            List<SeaCreature> possibleCreatures = Stream.of(SeaCreature.values()).filter(c -> c.meetsAllRequirements(player)).toList();
            if (possibleCreatures.size() < 1) {
                return;
            }
            SeaCreature creature = possibleCreatures.get((int) (Math.random() * possibleCreatures.size()));
            event.getHook().setHookedEntity(MobListeners.spawnCustom(creature.getCustomMobType(), event.getHook().getLocation(), player));
        }
    }


}