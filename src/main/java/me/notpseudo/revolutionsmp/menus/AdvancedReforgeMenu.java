package me.notpseudo.revolutionsmp.menus;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.customcrafting.items.ItemEditor;
import me.notpseudo.revolutionsmp.customcrafting.items.Reforge;
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

public class AdvancedReforgeMenu extends Menu {

    public AdvancedReforgeMenu(Player player) {
        super(player);
    }

    @Override
    public Component getTitle() {
        return Component.text("Advanced Reforge");
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

        ItemStack reforge = new ItemStack(Material.ANVIL);
        ItemMeta reforgeMeta = reforge.getItemMeta();
        reforgeMeta.displayName(Component.text("Reforge Item", NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        reforgeMeta.lore(List.of(
                Component.text("Place an item to reforge on the left", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                Component.empty(),
                Component.text("Place a Reforge Stone on the right", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                Component.empty(),
                Component.text("Click the anvil to apply the reforge", NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)
        ));
        reforge.setItemMeta(reforgeMeta);
        inventory.setItem(22, makeMenuItemAction(reforge, MenuAction.REFORGE));

        addCloseButton();
    }

    @Override
    public void handleClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) {
            return;
        }
        MenuItem menuItem = event.getCurrentItem().getItemMeta().getPersistentDataContainer().get(MenuUtils.getMenuKey(), new MenuItemDataType());
        if (menuItem == null) {
            BukkitRunnable checkItem = new BukkitRunnable() {
                @Override
                public void run() {
                    checkLeft();
                    checkRight();
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
        if (menuItem.getAction() == MenuAction.REFORGE) {
            if (reforge()) {
                player.getWorld().playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
            } else {
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1F, 0.5F);
            }
        }
    }

    private boolean reforge() {
        if (!checkBoth()) {
            return false;
        }
        ItemStack result = inventory.getItem(33);
        if (result != null) {
            result.getItemMeta().getPersistentDataContainer().remove(MenuUtils.getMenuKey());
            inventory.setItem(29, null);
            inventory.setItem(33, null);
        }
        return true;
    }

    private boolean checkBoth() {
        ItemStack left = inventory.getItem(29), right = inventory.getItem(33);
        if (left == null || left.getType() == Material.AIR || right == null || right.getType() == Material.AIR) {
            return false;
        }
        ItemInfo leftInfo = ItemEditor.getInfo(left), rightInfo = ItemEditor.getInfo(right);
        if (leftInfo == null || rightInfo == null) {
            return false;
        }
        if (!leftInfo.getItemType().allowReforge()) {
            return false;
        }
        if (!(rightInfo.getExtraInfo() instanceof ReforgeStoneInfo reforgeInfo)) {
            return false;
        }
        Reforge reforge = reforgeInfo.getReforge();
        if (!(reforge.getItemTypes().contains(leftInfo.getItemType()))) {
            return false;
        }
        ItemStack itemClone = new ItemStack(left);
        ItemInfo infoClone = ItemEditor.getInfo(itemClone);
        infoClone.setReforge(reforge);
        ItemMeta meta = itemClone.getItemMeta();
        meta.getPersistentDataContainer().set(ItemEditor.getItemKey(), new ItemInfoDataType(), infoClone);
        ItemEditor.updateLore(meta);
        itemClone.setItemMeta(meta);
        inventory.setItem(13, makeMenuItem(itemClone, null));
        return true;
    }

    private void checkLeft() {
        ItemStack left = inventory.getItem(29);
        if (left == null || left.getType() == Material.AIR) {
            setLeft(Material.RED_STAINED_GLASS_PANE);
            return;
        }
        ItemInfo info = ItemEditor.getInfo(left);
        if (info == null) {
            setLeft(Material.RED_STAINED_GLASS_PANE);
            return;
        }
        if (!info.getItemType().allowReforge()) {
            setLeft(Material.RED_STAINED_GLASS_PANE);
            return;
        }
        setLeft(Material.LIME_STAINED_GLASS_PANE);
    }

    private void checkRight() {
        ItemStack right = inventory.getItem(33);
        if (right == null || right.getType() == Material.AIR) {
            setRight(Material.RED_STAINED_GLASS_PANE);
            return;
        }
        ItemInfo info = ItemEditor.getInfo(right);
        if (info == null) {
            setRight(Material.RED_STAINED_GLASS_PANE);
            return;
        }
        if (!(info.getExtraInfo() instanceof ReforgeStoneInfo)) {
            setRight(Material.RED_STAINED_GLASS_PANE);
            return;
        }
        setRight(Material.LIME_STAINED_GLASS_PANE);
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