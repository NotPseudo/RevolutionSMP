package me.notpseudo.revolutionsmp.listeners;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.itemstats.ItemInfo;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

public class CraftingListeners implements Listener {

    public CraftingListeners(RevolutionSMP plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPrepare(PrepareItemCraftEvent event) {
        if (event.getRecipe() == null) {
            return;
        }
        CraftingInventory table = event.getInventory();
        ItemStack middle = table.getItem(4);
        ItemInfo info = ItemEditor.getInfo(middle);
        if (info == null || info.getItemID() == null) {
            return;
        }
        ItemInfo resultInfo = ItemEditor.getInfo(event.getRecipe().getResult());
        if (resultInfo == null || resultInfo.getItemID() == null) {
            return;
        }
        ItemStack newResult = resultInfo.getItemID().getItem();
        resultInfo.copy(info);
        table.setResult(ItemEditor.updateItemInfo(newResult, resultInfo));
    }

}