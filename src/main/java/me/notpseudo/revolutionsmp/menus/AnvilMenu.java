package me.notpseudo.revolutionsmp.menus;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.items.Reforge;
import me.notpseudo.revolutionsmp.itemstats.ItemInfo;
import me.notpseudo.revolutionsmp.itemstats.ItemInfoDataType;
import me.notpseudo.revolutionsmp.specialiteminfo.ReforgeStoneInfo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class AnvilMenu extends Menu {

    public AnvilMenu(Player player) {
        super(player);
    }

    @Override
    public Component getTitle() {
        return Component.text("Custom Anvil");
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void setItems() {
        setLeft(Material.RED_STAINED_GLASS_PANE);
        setRight(Material.RED_STAINED_GLASS_PANE);
        setBottomRow(Material.RED_STAINED_GLASS_PANE);

        inventory.setItem(13, null);
        inventory.setItem(29, null);
        inventory.setItem(33, null);
        setBlock();

        ItemStack combine = new ItemStack(Material.ANVIL);
        ItemMeta combineMeta = combine.getItemMeta();
        combineMeta.displayName(Component.text("Anvil", NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        combineMeta.lore(List.of(
                Component.text("Place an item to upgrade on the left", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                Component.empty(),
                Component.text("Place an item to sacrifice on the right", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                Component.empty(),
                Component.text("Click the anvil to combine the items", NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)
        ));
        combine.setItemMeta(combineMeta);
        inventory.setItem(22, makeMenuAction(combine, MenuAction.COMBINE));

        addBackType(MenuType.MAIN);
        addCloseButton();
    }

    @Override
    public void handleClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) {
            BukkitRunnable checkItem = new BukkitRunnable() {
                @Override
                public void run() {
                    checkBoth();
                }
            };
            checkItem.runTaskLater(RevolutionSMP.getPlugin(), 1);
            return;
        }
        MenuItem menuItem = event.getCurrentItem().getItemMeta().getPersistentDataContainer().get(MenuUtils.getMenuKey(), new MenuItemDataType());
        if (menuItem == null) {
            BukkitRunnable checkItem = new BukkitRunnable() {
                @Override
                public void run() {
                    checkBoth();
                }
            };
            checkItem.runTaskLater(RevolutionSMP.getPlugin(), 1);
            return;
        }
        event.setCancelled(true);
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
        if (menuItem.getType() != null && menuItem.getType().meetsRequirement(player)) {
            menuItem.getType().getNext(player).open();
            return;
        }
        if (menuItem.getAction() == MenuAction.COMBINE) {
            if (combine()) {
                player.getWorld().playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
            } else {
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1F, 0.5F);
            }
        }
    }

    private boolean combine() {
        if (!checkBoth()) {
            return false;
        }
        ItemStack result = inventory.getItem(33);
        if (result != null) {
            result.editMeta(m -> m.getPersistentDataContainer().remove(MenuUtils.getMenuKey()));
            inventory.setItem(29, null);
            inventory.setItem(33, null);
        }
        return true;
    }

    private boolean checkBoth() {
        if (!(checkLeft() && checkRight())) {
            return false;
        }
        ItemStack left = inventory.getItem(29), right = inventory.getItem(33);
        ItemInfo leftInfo = ItemEditor.getInfo(left), rightInfo = ItemEditor.getInfo(right);
        if (leftInfo == null) {
            setLeft(Material.RED_STAINED_GLASS_PANE);
            setBlock();
            return false;
        }
        if (rightInfo == null) {
            setRight(Material.RED_STAINED_GLASS_PANE);
            setBlock();
            return false;
        }
        if (!leftInfo.canCombine(rightInfo)) {
            setLeft(Material.RED_STAINED_GLASS_PANE);
            setRight(Material.RED_STAINED_GLASS_PANE);
            setBlock();
            return false;
        }
        ItemStack itemClone = new ItemStack(left);
        ItemInfo infoClone = ItemEditor.getInfo(itemClone);
        if (infoClone == null) {
            setLeft(Material.RED_STAINED_GLASS_PANE);
            setRight(Material.RED_STAINED_GLASS_PANE);
            setBlock();
            return false;
        }
        ItemMeta meta = itemClone.getItemMeta();
        meta.getPersistentDataContainer().set(ItemEditor.getItemKey(), new ItemInfoDataType(), infoClone);
        ItemEditor.updateLore(meta);
        itemClone.setItemMeta(meta);
        inventory.setItem(13, makeMenuType(itemClone, null));
        setBottomRow(Material.LIME_STAINED_GLASS_PANE);
        return true;
    }

    private void setBlock() {
        ItemStack block = new ItemStack(Material.BARRIER);
        ItemMeta blockMeta = block.getItemMeta();
        blockMeta.displayName(Component.text("Valid Items Required", NamedTextColor.RED).decoration(TextDecoration.ITALIC, false));
        block.lore(List.of(
                Component.text("Place an item to upgrade on the left", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                Component.empty(),
                Component.text("Place an item to sacrifice on the right", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                Component.empty(),
                Component.text("Click the anvil below to combine", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)
        ));
        block.setItemMeta(blockMeta);
        inventory.setItem(23, makeMenuType(block, null));
    }

    private boolean checkLeft() {
        ItemStack left = inventory.getItem(29);
        if (left == null || left.getType() == Material.AIR) {
            setLeft(Material.RED_STAINED_GLASS_PANE);
            return false;
        }
        ItemInfo info = ItemEditor.getInfo(left);
        if (info == null) {
            setLeft(Material.RED_STAINED_GLASS_PANE);
            return false;
        }
        if (!info.getItemType().allowAnvil()) {
            setLeft(Material.RED_STAINED_GLASS_PANE);
            return false;
        }
        setLeft(Material.LIME_STAINED_GLASS_PANE);
        return true;
    }

    private boolean checkRight() {
        ItemStack right = inventory.getItem(33);
        if (right == null || right.getType() == Material.AIR) {
            setRight(Material.RED_STAINED_GLASS_PANE);
            return false;
        }
        ItemInfo info = ItemEditor.getInfo(right);
        if (info == null) {
            setRight(Material.RED_STAINED_GLASS_PANE);
            return false;
        }
        if (!info.getItemType().allowAnvil()) {
            setRight(Material.RED_STAINED_GLASS_PANE);
            return false;
        }
        setRight(Material.LIME_STAINED_GLASS_PANE);
        return true;
    }

    private void setLeft(Material material) {
        inventory.setItem(11, makeMenuGlass(new ItemStack(material)));
        inventory.setItem(12, makeMenuGlass(new ItemStack(material)));
        inventory.setItem(20, makeMenuGlass(new ItemStack(material)));
    }

    private void setRight(Material material) {
        inventory.setItem(14, makeMenuGlass(new ItemStack(material)));
        inventory.setItem(15, makeMenuGlass(new ItemStack(material)));
        inventory.setItem(24, makeMenuGlass(new ItemStack(material)));
    }

    private void setBottomRow(Material material) {
        for (int i = 45; i < 54; i++) {
            if (i != 49) {
                inventory.setItem(i, makeMenuGlass(new ItemStack(material)));
            }
        }
    }

}