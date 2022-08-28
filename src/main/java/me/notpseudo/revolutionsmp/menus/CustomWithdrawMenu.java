package me.notpseudo.revolutionsmp.menus;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.economy.EcoUtils;
import me.notpseudo.revolutionsmp.economy.PlayerEcoInfo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class CustomWithdrawMenu extends Menu {

    public CustomWithdrawMenu(Player player) {
        super(player);
    }

    @Override
    public Component getTitle() {
        return Component.text("Custom Withdraw Amount");
    }

    @Override
    public int getSlots() {
        return 0;
    }

    @Override
    public void setItems() {
        ItemStack left = new ItemStack(Material.PAPER);
        ItemMeta leftMeta = left.getItemMeta();
        leftMeta.displayName(Component.text("Enter a custom amount", NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        leftMeta.lore(List.of(
                Component.text("Enter an amount of coins in the top", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                Component.empty(),
                Component.text("Click THIS AGAIN to withdraw the amount you wrote", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)
        ));
        left.setItemMeta(leftMeta);
        inventory.setItem(0, makeMenuAction(left, MenuAction.WITHDRAW_CUSTOM));

        /*
        ItemStack right = new ItemStack(Material.BOOK);
        ItemMeta rightMeta = right.getItemMeta();
        rightMeta.displayName(Component.text("Click to withdraw", NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        rightMeta.lore(List.of(
                Component.text("Enter an amount of coins in the top", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                Component.empty(),
                Component.text("Click this to withdraw the amount you wrote", NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)
        ));
        right.setItemMeta(rightMeta);
        inventory.setItem(1, makeMenuItemAction(right, MenuAction.WITHDRAW_CUSTOM));
         */
    }

    @Override
    public void open() {
        InventoryView view = player.openAnvil(player.getLocation(), true);
        if (view == null) {
            return;
        }
        inventory = view.getTopInventory();
        setItems();
        player.openInventory(inventory);
    }

    @Override
    public void handleClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) {
            player.sendMessage("Click was null");
            return;
        }
        MenuItem menuItem = event.getCurrentItem().getItemMeta().getPersistentDataContainer().get(MenuUtils.getMenuKey(), new MenuItemDataType());
        if (menuItem == null) {
            player.sendMessage("Menu was null");
            return;
        }
        player.sendMessage("Set cancelled");
        player.sendMessage("Action: " + menuItem.getAction());
        event.setCancelled(true);
        if (menuItem.getAction() == MenuAction.WITHDRAW_CUSTOM) {
            player.sendMessage("Menu was withdraw");
            withdrawCustom();
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
    }

    private void withdrawCustom() {
        player.sendMessage("Starting withdraw");
        if (inventory.getType() != InventoryType.ANVIL) {
            player.sendMessage("Not an anvil");
            return;
        }
        AnvilInventory anvil = (AnvilInventory) inventory;
        String coinString = anvil.getRenameText();
        double coins;
        try {
            coins = Double.parseDouble(coinString);
        } catch (NumberFormatException exception) {
            player.sendMessage(Component.text("Could not read this number", NamedTextColor.RED));
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1F, 0.5F);
            return;
        }
        PlayerEcoInfo info = EcoUtils.getEcoInfo(player);
        coins = Math.round(coins * 100) / 100.0;
        if (info.withdrawFromBank(coins)) {
            player.sendMessage(Component.text("Withdrew " + coins + " coins from your bank account into your purse", NamedTextColor.GREEN));
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
        } else {
            player.sendMessage(Component.text("Could not withdraw coins from your account", NamedTextColor.RED));
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1F, 0.5F);
        }
        BukkitRunnable back = new BukkitRunnable() {
            @Override
            public void run() {
                new BankMenu(player).open();
            }
        };
        back.runTaskLater(RevolutionSMP.getPlugin(), 1);
    }
}
