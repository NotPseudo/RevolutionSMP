package me.notpseudo.revolutionsmp.menus;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.economy.EcoUtils;
import me.notpseudo.revolutionsmp.economy.PlayerEcoInfo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class DepositMenu extends Menu {

    public DepositMenu(Player player) {
        super(player);
    }

    @Override
    public Component getTitle() {
        return Component.text("Deposit Coins");
    }

    @Override
    public int getSlots() {
        return 36;
    }

    @Override
    public void setItems() {
        PlayerEcoInfo ecoInfo = EcoUtils.getEcoInfo(player);

        ItemStack depositAll = new ItemStack(Material.CHEST, 64);
        ItemMeta allMeta = depositAll.getItemMeta();
        allMeta.displayName(Component.text("Entire purse", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        allMeta.lore(List.of(
                Component.text("Bank Deposit", NamedTextColor.DARK_GRAY).decoration(TextDecoration.ITALIC, false),
                Component.empty(),
                Component.text("Current Balance: ", NamedTextColor.GRAY).append(Component.text(ecoInfo.getBank(), NamedTextColor.GOLD)).decoration(TextDecoration.ITALIC, false),
                Component.text("Amount to deposit: ", NamedTextColor.GRAY).append(Component.text((int) ecoInfo.getPurse(), NamedTextColor.GRAY)).decoration(TextDecoration.ITALIC, false)
        ));
        inventory.setItem(11, makeMenuItemAction(depositAll, MenuAction.DEPOSIT_ALL));

        ItemStack depositHalf = new ItemStack(Material.CHEST, 32);
        ItemMeta halfMeta = depositHalf.getItemMeta();
        halfMeta.displayName(Component.text("Half your purse", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        halfMeta.lore(List.of(
                Component.text("Bank Deposit", NamedTextColor.DARK_GRAY).decoration(TextDecoration.ITALIC, false),
                Component.empty(),
                Component.text("Current Balance: ", NamedTextColor.GRAY).append(Component.text(ecoInfo.getBank(), NamedTextColor.GOLD)).decoration(TextDecoration.ITALIC, false),
                Component.text("Amount to deposit: ", NamedTextColor.GRAY).append(Component.text((int) (0.5 * ecoInfo.getPurse()), NamedTextColor.GRAY)).decoration(TextDecoration.ITALIC, false)
        ));
        inventory.setItem(13, makeMenuItemAction(depositHalf, MenuAction.DEPOSIT_HALF));

        ItemStack custom = new ItemStack(Material.ANVIL);
        ItemMeta customMeta = custom.getItemMeta();
        customMeta.displayName(Component.text("Custom amount", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        customMeta.lore(List.of(
                Component.text("Current Balance: ", NamedTextColor.GRAY).append(Component.text(ecoInfo.getBank(), NamedTextColor.GOLD)).decoration(TextDecoration.ITALIC, false),
                Component.empty(),
                Component.text("Click to deposit a specific amount of coins", NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)
                ));
        inventory.setItem(15, makeMenuItem(custom, MenuType.CUSTOM_DEPOSIT));

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
        PlayerEcoInfo info = EcoUtils.getEcoInfo(player);
        double percent = 0;
        if (menuItem.getAction() == MenuAction.DEPOSIT_ALL) {
            percent = 100;
        }
        if (menuItem.getAction() == MenuAction.DEPOSIT_HALF) {
            percent = 50;
        }
        player.sendMessage(Component.text("Deposited " + info.depositPercentFromPurse(percent) + " coins into your bank account from your purse", NamedTextColor.GREEN));
        BukkitRunnable back = new BukkitRunnable() {
            @Override
            public void run() {
                new BankMenu(player).open();
            }
        };
        back.runTaskLater(RevolutionSMP.getPlugin(), 1);
    }
}