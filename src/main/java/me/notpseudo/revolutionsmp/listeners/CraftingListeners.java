package me.notpseudo.revolutionsmp.listeners;

import com.google.common.base.Preconditions;
import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.collections.CollectionUtils;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.items.ItemType;
import me.notpseudo.revolutionsmp.itemstats.ItemInfo;
import me.notpseudo.revolutionsmp.skills.SkillType;
import me.notpseudo.revolutionsmp.skills.SkillUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;

public class CraftingListeners implements Listener {

    public CraftingListeners(RevolutionSMP plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        ItemStack result = event.getRecipe().getResult();
        ItemInfo info = ItemEditor.getInfo(result);
        ItemMeta meta = result.getItemMeta();
        if (info == null) {
            meta = ItemEditor.createMetaFromMat(meta, result.getType());
            result.setItemMeta(meta);
        }
        SkillUtils.handleCraftingXP((Player) event.getView().getPlayer(), result);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        ItemInfo info = ItemEditor.getInfo(event.getItemInHand());
        if (!(info == null || info.getItemType() == ItemType.VANILLA_ITEM)) {
            event.setCancelled(true);
            return;
        }
        SkillUtils.addRegularXP(SkillType.BUILDING, event.getPlayer(), 10);
    }

    @EventHandler
    public void onBrew(InventoryClickEvent event) {
        if (!(event.getInventory() instanceof BrewerInventory brewingStand)) {
            return;
        }
        if (event.getSlot() >= 3) {
            return;
        }
        ItemStack potion = event.getCurrentItem();
        if (potion == null) {
            return;
        }
        if (!(potion.getItemMeta() instanceof PotionMeta potionMeta)) {
            return;
        }
        if (CollectionUtils.isAlreadyCollected(potion)) {
            return;
        }
        double multiplier = potionMeta.getCustomEffects().size() + 1;
        PotionData data = potionMeta.getBasePotionData();
        if (data.isUpgraded()) {
            multiplier *= 2;
        }
        if (data.isExtended()) {
            multiplier *= 2;
        }
        SkillUtils.addRegularXP(SkillType.ALCHEMY, (Player) event.getWhoClicked(), multiplier * 50);
    }

    @EventHandler
    public void onEnchant(EnchantItemEvent event) {
        ItemStack item = event.getItem();
        ItemInfo info = ItemEditor.getInfo(item);
        if (info == null || info.getItemType() == ItemType.VANILLA_ITEM) {
            SkillUtils.addRegularXP(SkillType.ENCHANTING, event.getEnchanter(), event.getExpLevelCost() * 10);
            return;
        }
        event.setCancelled(true);
    }

}