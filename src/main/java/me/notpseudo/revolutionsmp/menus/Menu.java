package me.notpseudo.revolutionsmp.menus;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.collections.CollectionObject;
import me.notpseudo.revolutionsmp.collections.CollectionType;
import me.notpseudo.revolutionsmp.collections.CollectionsHolder;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.skills.SkillObject;
import me.notpseudo.revolutionsmp.skills.SkillType;
import me.notpseudo.revolutionsmp.skills.SkillUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

    public void handleClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) {
            return;
        }
        MenuItem menuItem = event.getCurrentItem().getItemMeta().getPersistentDataContainer().get(MenuUtils.getMenuKey(), new MenuItemDataType());
        if (menuItem == null) {
            return;
        }
        event.setCancelled(true);
        if (menuItem.getType() == null) {
            return;
        }
        if (menuItem.getType() == MenuType.CLOSE) {
            BukkitRunnable close = new BukkitRunnable() {
                @Override
                public void run() {
                    player.closeInventory();
                }
            };
            close.runTaskLater(RevolutionSMP.getPlugin(), 1);
            return;
        }
        if (menuItem.getType().meetsRequirement(player)) {
            Menu next = menuItem.getType().getNext(player);
            if (next != null) {
                next.open();
            }
        }
    };

    public void handleMoveOut(InventoryMoveItemEvent event) {
        MenuItem menuItem = event.getItem().getItemMeta().getPersistentDataContainer().get(MenuUtils.getMenuKey(), new MenuItemDataType());
        if (menuItem != null) {
            event.setCancelled(true);
            return;
        }
        nonMenuItems.remove(event.getItem());
    }

    public void handleMoveIn(InventoryMoveItemEvent event) {
        MenuItem menuItem = event.getItem().getItemMeta().getPersistentDataContainer().get(MenuUtils.getMenuKey(), new MenuItemDataType());
        if (menuItem != null) {
            event.setCancelled(true);
            return;
        }
        if (!(nonMenuItems.contains(event.getItem()))) {
            nonMenuItems.add(event.getItem());
        }
    }

    public void handleClose(InventoryCloseEvent event) {
        for (ItemStack item : player.getInventory().addItem(nonMenuItems.toArray(new ItemStack[0])).values()) {
            player.getWorld().dropItemNaturally(player.getLocation(), item);
        }
    }

    public void open() {
        inventory = Bukkit.createInventory(this, getSlots(), getTitle());
        fillGlass();
        setItems();
        player.openInventory(inventory);
    }

    public ItemStack makeMenuItem(ItemStack item, MenuType nextType) {
        if (item == null || item.getType() == Material.AIR) {
            return null;
        }
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(MenuUtils.getMenuKey(), new MenuItemDataType(), new MenuItem(nextType));
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }

    public String getProgressString(double percent) {
        int green = (int) percent * 20;
        StringBuilder bar = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            if (i < green) {
                bar.append(ChatColor.GREEN + "-");
            } else {
                bar.append(ChatColor.WHITE + "-");
            }
        }
        return bar.toString();
    }

    public List<Component> getProgressList(SkillObject skill) {
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text("Progress to Level " + (int) skill.getLevel() + 1 + ": ", NamedTextColor.GRAY).append(Component.text(Math.round(skill.getPercent() * 100) + "%", NamedTextColor.YELLOW)).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text(getProgressString(skill.getPercent())).append(Component.text(" " + skill.getCurrentXP(), NamedTextColor.YELLOW)).append(Component.text("/", NamedTextColor.GOLD)).append(Component.text(skill.getXpForNextLevel(), NamedTextColor.YELLOW)).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.empty());
        lore.addAll(SkillUtils.getSkillRewards(skill));
        return lore;
    }

    public List<Component> getTotalCollectionProgress(CollectionsHolder holder, SkillType type) {
        ArrayList<Component> lore = new ArrayList<>();
        if (type == null) {
            lore.add(Component.text("View your Collections progression. Collect more of a material to unlock rewards", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        } else {
            lore.add(Component.text("View your " + ItemEditor.getStringFromEnum(type) + " Collection!", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        }
        lore.add(Component.empty());
        String progressType = "Collection Unlocked: ";
        double percent = holder.getPercentUnlocked(type);
        int part = holder.getCollectionsUnlocked(type), total;
        if (type == null) {
            total = CollectionType.values().length;
        } else {
            total = (int) Stream.of(CollectionType.values()).filter(t -> t.getCategory() == type).count();
        }
        if (percent >= 1) {
            percent = holder.getPercentMaxed(type);
            part = holder.getCollectionsMaxed(type);
            progressType = "Collection Maxed: ";
        }
        lore.add(Component.text(progressType, NamedTextColor.GRAY).append(Component.text(Math.round(percent * 100) + "%", NamedTextColor.YELLOW)).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text(getProgressString(percent)).append(Component.text(" " + part, NamedTextColor.YELLOW)).append(Component.text("/", NamedTextColor.GOLD)).append(Component.text(total, NamedTextColor.YELLOW)).decoration(TextDecoration.ITALIC, false));
        return lore;
    }

    public List<Component> getCollectionProgressList(CollectionObject collection) {
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text("Progress to Level " + collection.getLevel() + 1 + ": ", NamedTextColor.GRAY).append(Component.text(Math.round(collection.getPercent() * 100) + "%", NamedTextColor.YELLOW)).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text(getProgressString(collection.getPercent())).append(Component.text(" " + collection.getCurrentCollected(), NamedTextColor.YELLOW)).append(Component.text("/", NamedTextColor.GOLD)).append(Component.text(collection.getItemsForNextLevel(), NamedTextColor.YELLOW)).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.empty());
        lore.add(Component.text("Level " + collection.getLevel() + " Rewards: ", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        return lore;
    }

}