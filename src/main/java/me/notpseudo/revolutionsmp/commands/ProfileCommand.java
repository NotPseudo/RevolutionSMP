package me.notpseudo.revolutionsmp.commands;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.listeners.StatsListeners;
import me.notpseudo.revolutionsmp.playerstats.PlayerStats;
import me.notpseudo.revolutionsmp.playerstats.PlayerStatsDataType;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ProfileCommand implements CommandExecutor {

    private static final NamespacedKey playerKey = StatsListeners.getPlayerStatsKey();

    public ProfileCommand(RevolutionSMP plugin) {
        plugin.getCommand("profile").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player player) {
            PlayerStats playerStats = player.getPersistentDataContainer().get(playerKey, new PlayerStatsDataType());
            if(playerStats == null) {
                player.sendMessage("Your Player Stats were null");
                return false;
            }
            player.sendMessage("Current Health: " + playerStats.getCurrentHealth());
            player.sendMessage("Max Health: " + playerStats.getMaxHealth());
            player.sendMessage("Defense: " + playerStats.getDefense());
            player.sendMessage("Speed: " + playerStats.getSpeed());
            player.sendMessage("Strength: " + playerStats.getStrength());
            player.sendMessage("Crit Chance: " + playerStats.getCritChance());
            player.sendMessage("Crit Damage: " + playerStats.getCritDamage());
            player.sendMessage("Attack Speed: " + playerStats.getAttackSpeed());
            player.sendMessage("Ferocity: " + playerStats.getFerocity());
            player.sendMessage("Intelligence: " + playerStats.getIntelligence());
            player.sendMessage("Ability Damage: " + playerStats.getAbilityDamage());
            player.sendMessage("Damage Taken Multiplier: " + playerStats.getDamageTakenMultiplier());
            player.sendMessage("Absorption: " + playerStats.getAbsorption());
        }
        return true;
    }
}
