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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class WithdrawMenu extends Menu {

    public WithdrawMenu(Player player) {
        super(player);
    }

    @Override
    public Component getTitle() {
        return Component.text("Withdraw Coins");
    }

    @Override
    public int getSlots() {
        return 36;
    }

    @Override
    public void setItems() {
        PlayerEcoInfo ecoInfo = EcoUtils.getEcoInfo(player);
        // 10, 12, 14, 16
        ItemStack all = new ItemStack(Material.DROPPER, 64);
        ItemMeta allMeta = all.getItemMeta();
        allMeta.displayName(Component.text("Entire bank account", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        allMeta.lore(List.of(
                Component.text("Bank Withdrawal", NamedTextColor.DARK_GRAY).decoration(TextDecoration.ITALIC, false),
                Component.empty(),
                Component.text("Current Balance: ", NamedTextColor.GRAY).append(Component.text(ecoInfo.getBank(), NamedTextColor.GOLD)).decoration(TextDecoration.ITALIC, false),
                Component.text("Amount to withdraw: ", NamedTextColor.GRAY).append(Component.text(ecoInfo.getBank(), NamedTextColor.GRAY)).decoration(TextDecoration.ITALIC, false)
        ));
        all.setItemMeta(allMeta);
        inventory.setItem(10, makeMenuItemAction(all, MenuAction.WITHDRAW_ALL));

        ItemStack half = new ItemStack(Material.DROPPER, 32);
        ItemMeta halfMeta = half.getItemMeta();
        halfMeta.displayName(Component.text("Half your bank account", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        halfMeta.lore(List.of(
                Component.text("Bank Withdrawal", NamedTextColor.DARK_GRAY).decoration(TextDecoration.ITALIC, false),
                Component.empty(),
                Component.text("Current Balance: ", NamedTextColor.GRAY).append(Component.text(ecoInfo.getBank(), NamedTextColor.GOLD)).decoration(TextDecoration.ITALIC, false),
                Component.text("Amount to withdraw: ", NamedTextColor.GRAY).append(Component.text((int) (0.5 * ecoInfo.getBank()), NamedTextColor.GRAY)).decoration(TextDecoration.ITALIC, false)
        ));
        half.setItemMeta(halfMeta);
        inventory.setItem(12, makeMenuItemAction(half, MenuAction.WITHDRAW_HALF));

        ItemStack quarter = new ItemStack(Material.DROPPER, 16);
        ItemMeta quarterMeta = quarter.getItemMeta();
        quarterMeta.displayName(Component.text("25% of your bank account", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        quarterMeta.lore(List.of(
                Component.text("Bank Withdrawal", NamedTextColor.DARK_GRAY).decoration(TextDecoration.ITALIC, false),
                Component.empty(),
                Component.text("Current Balance: ", NamedTextColor.GRAY).append(Component.text(ecoInfo.getBank(), NamedTextColor.GOLD)).decoration(TextDecoration.ITALIC, false),
                Component.text("Amount to withdraw: ", NamedTextColor.GRAY).append(Component.text((int) (0.25 * ecoInfo.getBank()), NamedTextColor.GRAY)).decoration(TextDecoration.ITALIC, false)
        ));
        quarter.setItemMeta(quarterMeta);
        inventory.setItem(14, makeMenuItemAction(quarter, MenuAction.WITHDRAW_25));

        ItemStack custom = new ItemStack(Material.ANVIL);
        ItemMeta customMeta = custom.getItemMeta();
        customMeta.displayName(Component.text("Custom amount", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        customMeta.lore(List.of(
                Component.text("Current Balance: ", NamedTextColor.GRAY).append(Component.text(ecoInfo.getBank(), NamedTextColor.GOLD)).decoration(TextDecoration.ITALIC, false),
                Component.empty(),
                Component.text("Click to withdraw a specific amount of coins", NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)
        ));
        custom.setItemMeta(customMeta);
        inventory.setItem(16, makeMenuItem(custom, MenuType.CUSTOM_WITHDRAW));

        addBackButton(31, MenuType.BANK);
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
        if (menuItem.getType() != null && menuItem.getType().meetsRequirement(player)) {
            Menu next = menuItem.getType().getNext(player);
            if (next != null) {
                next.open();
            }
            return;
        }
        PlayerEcoInfo info = EcoUtils.getEcoInfo(player);
        double amount = 0;
        if (menuItem.getAction() == MenuAction.WITHDRAW_ALL) {
            amount = info.withdrawPercentFromBank(100);
        }
        if (menuItem.getAction() == MenuAction.WITHDRAW_HALF) {
            amount = info.withdrawPercentFromBank(50);
        }
        if (menuItem.getAction() == MenuAction.WITHDRAW_25) {
            amount = info.withdrawPercentFromBank(25);
        }
        player.sendMessage(Component.text("Withdrew " + amount + " coins into your purse from your bank account", NamedTextColor.GREEN));
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
        BukkitRunnable back = new BukkitRunnable() {
            @Override
            public void run() {
                new BankMenu(player).open();
            }
        };
        back.runTaskLater(RevolutionSMP.getPlugin(), 1);
    }
}