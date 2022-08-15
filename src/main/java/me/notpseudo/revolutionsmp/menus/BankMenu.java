package me.notpseudo.revolutionsmp.menus;

import me.notpseudo.revolutionsmp.economy.EcoUtils;
import me.notpseudo.revolutionsmp.economy.PlayerEcoInfo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class BankMenu extends Menu {

    public BankMenu(Player player) {
        super(player);
    }

    @Override
    public Component getTitle() {
        return Component.text("Bank Account");
    }

    @Override
    public int getSlots() {
        return 36;
    }

    @Override
    public void setItems() {
        PlayerEcoInfo ecoInfo = EcoUtils.getEcoInfo(player);

        ItemStack deposit = new ItemStack(Material.CHEST);
        ItemMeta depositMeta = deposit.getItemMeta();
        depositMeta.displayName(Component.text("Deposit Coins", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        depositMeta.lore(List.of(
                Component.text("Current Balance: ", NamedTextColor.GRAY).append(Component.text(ecoInfo.getBank(), NamedTextColor.GOLD)).decoration(TextDecoration.ITALIC, false),
                Component.empty(),
                Component.text("Keep your coins safe by putting them in the bank", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)
                ));
        deposit.setItemMeta(depositMeta);
        inventory.setItem(11, makeMenuItem(deposit, MenuType.DEPOSIT));

        ItemStack withdraw = new ItemStack(Material.DROPPER);
        ItemMeta withdrawMeta = withdraw.getItemMeta();
        withdrawMeta.displayName(Component.text("Withdraw Coins", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        withdrawMeta.lore(List.of(
                Component.text("Current Balance: ", NamedTextColor.GRAY).append(Component.text(ecoInfo.getBank(), NamedTextColor.GOLD)).decoration(TextDecoration.ITALIC, false),
                Component.empty(),
                Component.text("Take your coins out of your account to use them", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)
                ));
        withdraw.setItemMeta(withdrawMeta);
        inventory.setItem(15, makeMenuItem(withdraw, MenuType.WITHDRAW));

        addCloseButton();
    }

}