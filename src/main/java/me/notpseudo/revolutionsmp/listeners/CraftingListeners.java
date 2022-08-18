package me.notpseudo.revolutionsmp.listeners;

import com.google.common.base.Preconditions;
import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.itemstats.ItemInfo;
import me.notpseudo.revolutionsmp.skills.SkillUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        ItemStack result = event.getRecipe().getResult();
        ItemInfo info = ItemEditor.getInfo(result);
        ItemMeta meta = result.getItemMeta();
        if (info == null) {
            meta = ItemEditor.createMetaFromMat(meta, result.getType());
            result.setItemMeta(meta);
            info = ItemEditor.getInfo(result);
        }
        Preconditions.checkArgument(info != null);
        double xp = switch (info.getRarity()) {
            default -> 10;
            case UNCOMMON -> 50;
            case RARE -> 100;
            case EPIC -> 500;
            case LEGENDARY -> 1000;
            case MYTHIC -> 1500;
            case DIVINE, SPECIAL -> 2500;
        };
        if (event.getView().getPlayer() instanceof Player player) {
            SkillUtils.addBuildingXpToPlayer(player, xp * result.getAmount());
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        SkillUtils.addBuildingXpToPlayer(event.getPlayer(), 10);
    }

}