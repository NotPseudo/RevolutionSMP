package me.notpseudo.revolutionsmp.economy;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.listeners.MobListeners;
import me.notpseudo.revolutionsmp.mobstats.MobInfo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;

public class EcoUtils implements Listener {

    private static final NamespacedKey playerEcoKey = new NamespacedKey(RevolutionSMP.getPlugin(RevolutionSMP.class), "playerEcoKey");

    public EcoUtils(RevolutionSMP plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static NamespacedKey getEcoKey() {
        return playerEcoKey;
    }

    public static void updatePlayerEco(Player player, PlayerEcoInfo info) {
        if (player == null) {
            return;
        }
        player.getPersistentDataContainer().set(playerEcoKey, new PlayerEcoDataType(), info);
    }

    @NotNull
    public static PlayerEcoInfo getEcoInfo(Player player) {
        PlayerEcoInfo info = player.getPersistentDataContainer().get(playerEcoKey, new PlayerEcoDataType());
        if (info == null) {
            info = new PlayerEcoInfo(player.getUniqueId());
            updatePlayerEco(player, info);
        }
        if (info.getPlayer() == null) {
            info = new PlayerEcoInfo(player.getUniqueId());
            updatePlayerEco(player, info);
            player.sendMessage(Component.text("There was a slight issue while processing your Bank and Purse. If you notice any issues, please contact a staff member", NamedTextColor.YELLOW));
        }
        return info;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE) {
            return;
        }
        double lost = getEcoInfo(player).removeDeathAmount(25);
        if (lost > 0) {
            player.sendMessage(Component.text("You died and lost " + lost + " coins!", NamedTextColor.RED));
        } else {
            player.sendMessage(Component.text("You died but your coins were saved!", NamedTextColor.GOLD));
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        LivingEntity dead = event.getEntity();
        MobInfo info = MobListeners.getMobInfo(dead);
        if (info == null) {
            return;
        }
        Player player = event.getEntity().getKiller();
        if (player == null) {
            return;
        }
        getEcoInfo(player).addPurse(info.getCustomMobType().getCoins(info.getLevel()));
    }

}