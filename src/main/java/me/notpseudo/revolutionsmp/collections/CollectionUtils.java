package me.notpseudo.revolutionsmp.collections;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.items.ItemID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.UUID;

public class CollectionUtils implements Listener {

    private static final NamespacedKey collectionsKey = new NamespacedKey(RevolutionSMP.getPlugin(RevolutionSMP.class), "collectionsKey");

    private static final NamespacedKey playerDroppedKey = new NamespacedKey(RevolutionSMP.getPlugin(RevolutionSMP.class), "playerDroppedKey");

    public CollectionUtils(RevolutionSMP plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent event) {
        event.getItemDrop().getPersistentDataContainer().set(playerDroppedKey, new UUIDDataType(), event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onEntityPickup(EntityPickupItemEvent event) {

    }

    public static NamespacedKey getCollectionsKey() {
        return collectionsKey;
    }

    public static int getCountForNextLevel(CollectionType type, int nextLevel) {
        return 0;
    }

    public static int getCollectionWorth(Material material) {
        return 1;
    }

    public static int getCollectionWorth(ItemID itemID) {
        return 1;
    }

}