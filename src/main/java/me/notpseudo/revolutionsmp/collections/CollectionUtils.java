package me.notpseudo.revolutionsmp.collections;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.items.ItemID;
import me.notpseudo.revolutionsmp.items.ItemType;
import me.notpseudo.revolutionsmp.itemstats.ItemInfo;
import me.notpseudo.revolutionsmp.itemstats.ItemInfoDataType;
import me.notpseudo.revolutionsmp.listeners.HarvestingListeners;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CollectionUtils implements Listener {

    private static final NamespacedKey collectionsKey = new NamespacedKey(RevolutionSMP.getPlugin(RevolutionSMP.class), "collectionsKey");

    private static final NamespacedKey playerDroppedKey = new NamespacedKey(RevolutionSMP.getPlugin(RevolutionSMP.class), "playerDroppedKey");

    private static final NamespacedKey itemKey = ItemEditor.getItemKey();

    public CollectionUtils(RevolutionSMP plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent event) {
        event.getItemDrop().getPersistentDataContainer().set(playerDroppedKey, new UUIDDataType(), event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void inventoryMove(InventoryMoveItemEvent event) {
        if (!(event.getDestination() instanceof PlayerInventory playerInventory)) {
            return;
        }
        ItemStack item = event.getItem();
        handleCollection(item, (Player) playerInventory.getHolder());
        BukkitRunnable addPlayerDrop = new BukkitRunnable() {
            @Override
            public void run() {
                item.getItemMeta().getPersistentDataContainer().set(playerDroppedKey, new UUIDDataType(), playerInventory.getHolder().getUniqueId());
            }
        };
        addPlayerDrop.runTaskLater(RevolutionSMP.getPlugin(), 20);
    }



    @EventHandler
    public void onBlockDrop(BlockDropItemEvent event) {
        if (HarvestingListeners.getPlacedLocationList(event.getBlock()).removeDropLocation(event.getBlock().getLocation())) {
            for (Item item : event.getItems()) {
                item.getPersistentDataContainer().set(playerDroppedKey, new UUIDDataType(), event.getPlayer().getUniqueId());
            }
        }
    }

    @EventHandler
    public void onEntityPickup(EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }
        if (event.getItem().getPersistentDataContainer().get(playerDroppedKey, new UUIDDataType()) != null) {
            return;
        }
        handleCollection(event.getItem().getItemStack(), player);
    }

    @EventHandler
    public void onDropSpawn(ItemSpawnEvent event) {
        ItemStack item = event.getEntity().getItemStack();
        ItemMeta meta = item.getItemMeta();
        ItemInfo info = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
        if (info != null) {
            return;
        }
        item.setItemMeta(ItemEditor.createMetaFromMat(meta, item.getType()));
    }

    @NotNull
    public static CollectionsHolder getCollectionHolder(Player player) {
        CollectionsHolder holder = player.getPersistentDataContainer().get(collectionsKey, new CollectionsDataType());
        if (holder == null) {
            holder = new CollectionsHolder(player.getUniqueId());
            player.getPersistentDataContainer().set(collectionsKey, new CollectionsDataType(), holder);
        }
        return holder;
    }

    @Nullable
    public static CollectionType getCollectionForMat(Material material) {
        for (CollectionType type : CollectionType.values()) {
            if (type.getVanillaMaterials().contains(material)) {
                return type;
            }
        }
        return null;
    }

    @Nullable
    public static CollectionType getCollectionForCustom(ItemID material) {
        for (CollectionType type : CollectionType.values()) {
            if (type.getCustomMaterials().contains(material)) {
                return type;
            }
        }
        return null;
    }

    public void handleCollection(ItemStack item, Player player) {
        if (item.getItemMeta().getPersistentDataContainer().get(playerDroppedKey, new UUIDDataType()) != null) {
            return;
        }
        ItemInfo info = item.getItemMeta().getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
        int count;
        CollectionType type;
        if (info != null && info.getItemType() != ItemType.VANILLA_ITEM) {
            type = getCollectionForCustom(info.getItemID());
            count = getCollectionWorth(info.getItemID());
        } else {
            type = getCollectionForMat(item.getType());
            count = getCollectionWorth(item.getType());
        }
        if (type == null) {
            return;
        }
        getCollectionHolder(player).addCollectionAmount(type, count);
    }

    public static NamespacedKey getCollectionsKey() {
        return collectionsKey;
    }

    public static int getCountForNextLevel(CollectionType type, int nextLevel) {
        return switch (nextLevel) {
            case 1 -> 25;
            case 2 -> 50;
            case 3 -> 100;
            case 4 -> 250;
            case 5 -> 500;
            case 6 -> 1000;
            case 7 -> 2500;
            case 8 -> 5000;
            case 9 -> 10000;
            case 10 -> 25000;
            case 11 -> 50000;
            case 12 -> 75000;
            case 13 -> 100000;
            default -> 0;
        };
    }

    public static int getCollectionWorth(Material material) {
        return 1;
    }

    public static int getCollectionWorth(ItemID itemID) {
        return 1;
    }

}