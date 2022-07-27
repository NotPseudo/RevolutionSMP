package me.notpseudo.revolutionsmp.listeners;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.playerstats.PlayerStats;
import me.notpseudo.revolutionsmp.playerstats.PlayerStatsDataType;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class HarvestingListeners implements Listener {

    private static final NamespacedKey playerKey = StatsListeners.getPlayerStatsKey();

    public HarvestingListeners(RevolutionSMP plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onLogBreak(BlockBreakEvent event) {
        if (!event.getBlock().getType().toString().toLowerCase().contains("log")) {
            return;
        }
        event.setDropItems(false);
        Player player = event.getPlayer();
        PlayerStats playerStats = player.getPersistentDataContainer().get(playerKey, new PlayerStatsDataType());
        if (playerStats == null) {
            playerStats = new PlayerStats();
            player.getPersistentDataContainer().set(playerKey, new PlayerStatsDataType(), playerStats);
        }
        int count = 1 + getAddedTimes(playerStats.getForagingFortune());
        event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(event.getBlock().getType(), count));
    }

    public static int getAddedTimes(double chance) {
        int count = (int) (chance / 100);
        if (Math.random() * 100 < chance % 100.0) {
            count++;
        }
        return count;
    }

}