package me.notpseudo.revolutionsmp.menus;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.items.ItemType;
import me.notpseudo.revolutionsmp.items.Reforge;
import me.notpseudo.revolutionsmp.itemstats.ItemInfo;
import me.notpseudo.revolutionsmp.itemstats.ItemInfoDataType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReforgeMenu extends Menu {

    private static final Set<Reforge> NO_STONE = Stream.of(Reforge.values()).filter(r -> r.getReforgeStone() == null).collect(Collectors.toSet());

    public ReforgeMenu(Player player) {
        super(player);
    }

    @Override
    public Component getTitle() {
        return Component.text("Basic Reforge");
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void setItems() {
        setSides(Material.RED_STAINED_GLASS_PANE);

        inventory.setItem(13, null);

        ItemStack reforge = new ItemStack(Material.ANVIL);
        ItemMeta reforgeMeta = reforge.getItemMeta();
        reforgeMeta.displayName(Component.text("Reforge Item", NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        reforgeMeta.lore(List.of(Component.text("Place an item above and click here to randomly reforge it!", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                Component.text("Reforging an item gives it stat boosts", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)));
        inventory.setItem(22, makeMenuItemAction(reforge, MenuAction.REFORGE));

        addCloseButton();
    }

    private void setSides(Material material) {
        for (int i = 0; i < 6; i++) {
            inventory.setItem(i * 9, makeMenuItem(new ItemStack(material), null));
            inventory.setItem(i * 9 + 8, makeMenuItem(new ItemStack(material), null));
        }
    }

    private boolean reforge() {
        ItemStack item = inventory.getItem(13);
        if (item == null || item.getType() == Material.AIR) {
            return false;
        }
        ItemInfo info = ItemEditor.getInfo(item);
        if (info == null || info.getItemType() == ItemType.VANILLA_ITEM) {
            return false;
        }
        List<Reforge> matches = NO_STONE.stream().filter(r -> r.getItemTypes().contains(info.getItemType())).toList();
        if (matches.size() < 1) {
            return false;
        }
        Reforge reforge = matches.get((int) (Math.random() * matches.size()));
        info.setReforge(reforge);
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(ItemEditor.getItemKey(), new ItemInfoDataType(), info);
        ItemEditor.updateLore(meta);
        item.setItemMeta(meta);
        player.sendMessage(Component.text(info.getName() + " was reforged to " + reforge.getName(), NamedTextColor.GREEN));
        return true;
    }

    @Override
    public void handleClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) {
            return;
        }
        MenuItem menuItem = event.getCurrentItem().getItemMeta().getPersistentDataContainer().get(MenuUtils.getMenuKey(), new MenuItemDataType());
        if (menuItem == null) {
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
                player.sendMessage(Component.text("This item could not be reforged!", NamedTextColor.RED));
            }
        }
    }

    @Override
    public void handleMoveOut(InventoryMoveItemEvent event) {
        super.handleMoveOut(event);
        BukkitRunnable checkReforge = new BukkitRunnable() {
            @Override
            public void run() {
                checkItem();
            }
        };
        checkReforge.runTaskLater(RevolutionSMP.getPlugin(), 1);
    }

    @Override
    public void handleMoveIn(InventoryMoveItemEvent event) {
        super.handleMoveIn(event);
        BukkitRunnable checkReforge = new BukkitRunnable() {
            @Override
            public void run() {
                checkItem();
            }
        };
        checkReforge.runTaskLater(RevolutionSMP.getPlugin(), 1);
    }

    private void checkItem() {
        ItemStack item = inventory.getItem(13);
        if (item == null || item.getType() == Material.AIR) {
            setSides(Material.RED_STAINED_GLASS_PANE);
            return;
        }
        ItemInfo info = ItemEditor.getInfo(item);
        if (info == null) {
            setSides(Material.RED_STAINED_GLASS_PANE);
            return;
        }
        if (!info.getItemType().allowReforge()) {
            setSides(Material.RED_STAINED_GLASS_PANE);
            return;
        }
        setSides(Material.LIME_STAINED_GLASS_PANE);
    }

}