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

public class MoneyMenu extends Menu {

    public MoneyMenu(Player player) {
        super(player);
    }

    @Override
    public Component getTitle() {
        return Component.text("Money");
    }

    @Override
    public int getSlots() {
        return 36;
    }

    @Override
    public void setItems() {
        PlayerEcoInfo ecoInfo = EcoUtils.getEcoInfo(player);

        ItemStack bank = new ItemStack(Material.ENDER_CHEST);
        ItemMeta bankMeta = bank.getItemMeta();
        bankMeta.displayName(Component.text("Bank Account", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        bankMeta.lore(List.of(
                Component.text("Keep your coins safe by", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                Component.text("putting them in the bank", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                Component.empty(),
                Component.text("Speak to a Nitwit to access the bank menu", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                Component.text("Reach a high enough ", NamedTextColor.GRAY).append(Component.text("Emerald Collection ", NamedTextColor.GREEN)).decoration(TextDecoration.ITALIC, false),
                Component.text("to access the menu from anywhere", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                Component.empty(),
                Component.text("Balance: ", NamedTextColor.GRAY).append(Component.text(ecoInfo.getBank(), NamedTextColor.GOLD)).decoration(TextDecoration.ITALIC, false)
        ));
        bank.setItemMeta(bankMeta);
        inventory.setItem(11, makeMenuType(bank, MenuType.BANK));

        ItemStack purse = new ItemStack(Material.CHEST);
        ItemMeta purseMeta = purse.getItemMeta();
        purseMeta.displayName(Component.text("Purse", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        purseMeta.lore(List.of(
                Component.text("Your purse shows how many coins you carry on you", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                Component.text("Be careful! You lose a percent of your purse when you die!", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                Component.text("Deposit your coins into your bank account to keep them safe", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                Component.empty(),
                Component.text("Purse: ", NamedTextColor.GRAY).append(Component.text(ecoInfo.getPurse(), NamedTextColor.GOLD)).decoration(TextDecoration.ITALIC, false)
        ));
        purse.setItemMeta(purseMeta);
        inventory.setItem(15, makeMenuType(purse, null));

        addBackType(MenuType.MAIN);
        addCloseButton();
    }

}