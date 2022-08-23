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
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public abstract class Menu implements InventoryHolder {

    protected Inventory inventory;

    protected Player player;

    public Menu(Player player) {
        this.player = player;
    }

    public abstract Component getTitle();

    public abstract int getSlots();

    public void fillGlass() {
        for (int i = 0; i < getSlots(); i++) {
            inventory.setItem(i, makeMenuGlass(new ItemStack(Material.GRAY_STAINED_GLASS_PANE)));
        }
    }

    public void addCloseButton() {
        int lastRowMiddle = getSlots() - 5;
        addCloseButton(lastRowMiddle);
    }

    public void addCloseButton(int index) {
        if (index >= getSlots()) {
            return;
        }
        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.displayName(Component.text("Close", NamedTextColor.RED).decoration(TextDecoration.ITALIC, false));
        close.setItemMeta(closeMeta);
        inventory.setItem(index, makeMenuType(close, MenuType.CLOSE));
    }

    public void addBackType(MenuType previous) {
        int lastRowPosition = getSlots() - 6;
        addBackType(lastRowPosition, previous);
    }

    public void addBackType(int index, MenuType previous) {
        if (index >= getSlots()) {
            return;
        }
        ItemStack back = new ItemStack(Material.ARROW);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.displayName(Component.text("Back", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        back.setItemMeta(backMeta);
        inventory.setItem(index, makeMenuType(back, previous));
    }

    public void addBackMenu(Menu previous) {
        addBackMenu(getSlots() - 5, previous);
    }

    public void addBackMenu(int index, Menu previous) {
        if (index >= getSlots()) {
            return;
        }
        ItemStack back = new ItemStack(Material.ARROW);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.displayName(Component.text("Back", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        back.setItemMeta(backMeta);
        inventory.setItem(index, makeMenuNext(back, previous));
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
        if (menuItem.getNext(player) != null) {
            menuItem.getNext(player).open();
            return;
        }
        if (menuItem.getType().meetsRequirement(player)) {
            Menu next = menuItem.getType().getNext(player);
            if (next != null) {
                next.open();
            }
        }
    }

    public void handleDrag(InventoryDragEvent event) {

    }

    public void handleClose(InventoryCloseEvent event) {
        List<ItemStack> nonMenu = Arrays.stream(inventory.getContents()).filter(i -> i != null && MenuUtils.getMenuInfo(i) == null).toList();
        for (ItemStack item : player.getInventory().addItem(nonMenu.toArray(new ItemStack[0])).values()) {
            player.getWorld().dropItemNaturally(player.getLocation(), item);
        }
    }

    public void open() {
        inventory = Bukkit.createInventory(this, getSlots(), getTitle());
        fillGlass();
        setItems();
        player.openInventory(inventory);
    }

    public ItemStack makeMenuType(ItemStack item, MenuType nextType) {
        if (item == null || item.getType() == Material.AIR) {
            return null;
        }
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS);
        meta.getPersistentDataContainer().set(MenuUtils.getMenuKey(), new MenuItemDataType(), new MenuItem(nextType));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack makeMenuNext(ItemStack item, Menu next) {
        if (item == null || item.getType() == Material.AIR) {
            return null;
        }
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS);
        meta.getPersistentDataContainer().set(MenuUtils.getMenuKey(), new MenuItemDataType(), new MenuItem(next));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack makeMenuAction(ItemStack item, MenuAction action) {
        if (item == null || item.getType() == Material.AIR) {
            return null;
        }
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(MenuUtils.getMenuKey(), new MenuItemDataType(), new MenuItem(action));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack makeMenuGlass(ItemStack item) {
        if (item == null || item.getType() == Material.AIR) {
            return null;
        }
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.empty());
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS);
        meta.getPersistentDataContainer().set(MenuUtils.getMenuKey(), new MenuItemDataType(), new MenuItem());
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

    public String getFilledProgressString(double percent) {
        int green = (int) percent * 20;
        StringBuilder bar = new StringBuilder();
        for (int i = 0; i < green; i++) {
            bar.append("-");
        }
        return bar.toString();
    }

    public String getEmptyProgressString(double percent) {
        int green = (int) percent * 20;
        StringBuilder bar = new StringBuilder();
        for (int i = green; i < 20; i++) {
            bar.append("-");
        }
        return bar.toString();
    }

    public List<Component> getProgressList(SkillObject skill) {
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text("Progress to Level " + (int) (skill.getLevel() + 1) + ": ", NamedTextColor.GRAY).append(Component.text(Math.round(skill.getPercent() * 100) + "%", NamedTextColor.YELLOW)).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text(getFilledProgressString(skill.getPercent()), NamedTextColor.GREEN).append(Component.text(getEmptyProgressString(skill.getPercent()), NamedTextColor.WHITE)).append(Component.text(" " + skill.getCurrentXP(), NamedTextColor.YELLOW)).append(Component.text("/", NamedTextColor.GOLD)).append(Component.text(skill.getXpForNextLevel(), NamedTextColor.YELLOW)).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.empty());
        lore.addAll(SkillUtils.getSkillRewards(skill));
        return lore;
    }

    public List<Component> getTotalCollectionProgress(CollectionsHolder holder, SkillType type) {
        ArrayList<Component> lore = new ArrayList<>();
        if (type == null) {
            lore.add(Component.text("View your Collections progression", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
            lore.add(Component.text("Collect more of a material to unlock rewards", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
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
        lore.add(Component.text(getFilledProgressString(percent), NamedTextColor.GREEN).append(Component.text(getEmptyProgressString(percent), NamedTextColor.WHITE)).append(Component.text(" " + part, NamedTextColor.YELLOW)).append(Component.text("/", NamedTextColor.GOLD)).append(Component.text(total, NamedTextColor.YELLOW)).decoration(TextDecoration.ITALIC, false));
        return lore;
    }

    public List<Component> getCollectionProgressList(CollectionObject collection) {
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text("Progress to Level " + collection.getLevel() + 1 + ": ", NamedTextColor.GRAY).append(Component.text(Math.round(collection.getPercent() * 100) + "%", NamedTextColor.YELLOW)).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text(getFilledProgressString(collection.getPercent()), NamedTextColor.GREEN).append(Component.text(getEmptyProgressString(collection.getPercent()), NamedTextColor.WHITE)).append(Component.text(" " + collection.getCurrentCollected(), NamedTextColor.YELLOW)).append(Component.text("/", NamedTextColor.GOLD)).append(Component.text(collection.getItemsForNextLevel(), NamedTextColor.YELLOW)).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.empty());
        lore.add(Component.text("Level " + collection.getLevel() + " Rewards: ", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        return lore;
    }

}