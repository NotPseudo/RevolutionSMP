package me.notpseudo.revolutionsmp.collections;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.items.ItemID;
import me.notpseudo.revolutionsmp.items.ItemType;
import me.notpseudo.revolutionsmp.itemstats.ItemInfo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

public class CollectionUtils implements Listener {

    private static final NamespacedKey collectionsKey = new NamespacedKey(RevolutionSMP.getPlugin(RevolutionSMP.class), "collectionsKey");

    private static final NamespacedKey playerDroppedKey = new NamespacedKey(RevolutionSMP.getPlugin(RevolutionSMP.class), "playerDroppedKey");

    public CollectionUtils(RevolutionSMP plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static NamespacedKey getPlayerDropKey() {
        return playerDroppedKey;
    }

    public static void updatePlayerCollections(Player player, CollectionsHolder holder) {
        if (player == null) {
            return;
        }
        player.getPersistentDataContainer().set(collectionsKey, new CollectionsDataType(), holder);
    }

    public static void makePlayerDropped(Player player, Item item) {
        if (item == null) {
            return;
        }
        item.getPersistentDataContainer().set(playerDroppedKey, new UUIDDataType(), player.getUniqueId());
    }

    @EventHandler
    public void onContainerBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (!(block.getState() instanceof Container container)) {
            return;
        }
        Inventory inventory = container.getInventory();
        Player player = event.getPlayer();
        World world = block.getWorld();
        Location location = block.getLocation();
        for (ItemStack item : inventory.getContents()) {
            if (item != null && item.getType() != Material.AIR) {
                makePlayerDropped(player, world.dropItem(location, item));
            }
        }
        inventory.clear();
    }

    @EventHandler
    public void onPlayerDeathItems(PlayerDeathEvent event) {
        if (event.getKeepInventory()) {
            return;
        }
        Player player = event.getPlayer();
        World world = player.getWorld();
        Location location = player.getLocation();
        for (Iterator<ItemStack> iterator = event.getDrops().iterator(); iterator.hasNext(); ) {
            ItemStack drop = iterator.next();
            iterator.remove();
            ItemInfo itemInfo = ItemEditor.getInfo(drop);
            if (itemInfo != null && itemInfo.getItemType() != ItemType.VANILLA_ITEM) {
                event.getItemsToKeep().add(drop);
            } else {
                makePlayerDropped(player, world.dropItem(location, drop));
            }
        }
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
    public void onEntityPickup(EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }
        if (event.getItem().getPersistentDataContainer().get(playerDroppedKey, new UUIDDataType()) != null) {
            return;
        }
        ItemStack drop = event.getItem().getItemStack();
        if (drop.getItemMeta().getPersistentDataContainer().get(playerDroppedKey, new UUIDDataType()) != null) {
            return;
        }
        handleCollection(drop, player);
    }

    @EventHandler
    public void onDropSpawn(ItemSpawnEvent event) {
        ItemStack item = event.getEntity().getItemStack();
        ItemInfo info = ItemEditor.getInfo(item);
        if (info != null) {
            return;
        }
        ItemEditor.updateItemInfo(item, info);
    }

    @NotNull
    public static CollectionsHolder getCollectionHolder(Player player) {
        CollectionsHolder holder = player.getPersistentDataContainer().get(collectionsKey, new CollectionsDataType());
        if (holder == null) {
            holder = new CollectionsHolder(player.getUniqueId());
            updatePlayerCollections(player, holder);
        }
        if (holder.getPlayer() == null) {
            holder = new CollectionsHolder(player.getUniqueId());
            updatePlayerCollections(player, holder);
            player.sendMessage(Component.text("There was a slight issue while processing your Collections. If you notice any issues, please contact a staff member", NamedTextColor.YELLOW));
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
    public static CollectionType getCollectionForCustom(@Nullable ItemID material) {
        if (material == null) {
            return null;
        }
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
        ItemInfo info = ItemEditor.getInfo(item);
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
        getCollectionHolder(player).addCollectionAmount(type, item.getAmount() * count);
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

    public static int getCollectionWorth(@Nullable ItemID itemID) {
        if (itemID == null) {
            return 0;
        }
        return 1;
    }

    public static boolean isAlreadyCollected(ItemStack item) {
        if (item == null || item.getType() == Material.AIR) {
            return false;
        }
        return item.getItemMeta().getPersistentDataContainer().get(playerDroppedKey, new UUIDDataType()) != null;
    }

}