package me.notpseudo.revolutionsmp.menus;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public abstract class Menu implements InventoryHolder {

    protected Inventory inventory;

    protected Player player;

    protected ArrayList<ItemStack> nonMenuItems;

    public Menu(Player player) {
        this.player = player;
        nonMenuItems = new ArrayList<>();
    }

    public abstract Component getTitle();

    public abstract int getSlots();

    public void fillGlass() {
        for (int i = 0; i < getSlots(); i++) {
            inventory.setItem(i, makeMenuItem(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1), null));
        }
    }

    public abstract void setItems();

    public abstract void handleClick(InventoryClickEvent event);

    public void open() {
        inventory = Bukkit.createInventory(this, getSlots(), getTitle());
        fillGlass();
        setItems();
        player.openInventory(inventory);
    }

    public ItemStack makeMenuItem(ItemStack item, MenuType nextType) {
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(MenuUtils.getMenuKey(), new MenuItemDataType(), new MenuItem(nextType));
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}