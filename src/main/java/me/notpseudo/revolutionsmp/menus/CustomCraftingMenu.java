package me.notpseudo.revolutionsmp.menus;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.customcrafting.CustomCraftingUtils;
import me.notpseudo.revolutionsmp.items.CustomRecipe;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.items.ItemType;
import me.notpseudo.revolutionsmp.itemstats.ItemInfo;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomCraftingMenu extends Menu {

    private static final List<CustomRecipe> customRecipes = CustomCraftingUtils.getCustomRecipes();
    private static final int[] gridSlots = new int[] {10, 11, 12, 19, 20, 21, 28, 29, 30};

    private ItemStack result;
    private CustomRecipe shownRecipe;
    private Map<Integer, Integer> amountsNeeded;


    public CustomCraftingMenu(Player player) {
        super(player);
        resetRecipe();
    }

    @Override
    public Component getTitle() {
        return Component.text("Custom Crafting");
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void setItems() {
        fillGlass();
        setOpenSlots();
        addCloseButton();
        addBackButton(MenuType.MAIN);
    }

    private void setOpenSlots() {
        for (int index : gridSlots) {
            inventory.setItem(index, null);
        }
        inventory.setItem(23, null);
    }

    @Override
    public void handleClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) {
            return;
        }
        MenuItem menuItem = event.getCurrentItem().getItemMeta().getPersistentDataContainer().get(MenuUtils.getMenuKey(), new MenuItemDataType());
        if (menuItem == null) {
            checkGrid();
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
                return;
            }
        }
        if (menuItem.getAction() == MenuAction.CRAFT) {
            craftItem();
        }
        checkGrid();
    }

    private void craftItem() {
        if (result == null || shownRecipe == null || amountsNeeded == null) {
            return;
        }
        ItemStack onCursor = player.getItemOnCursor();
        if (onCursor.getType() == Material.AIR) {
            result.getItemMeta().getPersistentDataContainer().remove(MenuUtils.getMenuKey());
            player.setItemOnCursor(new ItemStack(result));
        } else if (testItem(result, onCursor)) {
            ItemStack newAmount = new ItemStack(onCursor);
            newAmount.setAmount(onCursor.getAmount() + result.getAmount());
            player.setItemOnCursor(newAmount);
        }
        inventory.setItem(23, null);
        for (Integer index : amountsNeeded.keySet()) {
            ItemStack itemAtIndex = inventory.getItem(index);
            if (itemAtIndex == null) {
                continue;
            }
            int amountAtIndex = itemAtIndex.getAmount(), amountNeeded = amountsNeeded.get(index);
            if (amountAtIndex == amountNeeded) {
                inventory.setItem(index, null);
            } else if (amountAtIndex > amountNeeded) {
                itemAtIndex.setAmount(amountAtIndex - amountNeeded);
                inventory.setItem(index, itemAtIndex);
            }
        }
        resetRecipe();
        if (checkGrid()) {
            inventory.setItem(23, result);
        }
    }

    private boolean checkGrid() {
        ItemStack[][] grid = new ItemStack[][] {
                new ItemStack[] {inventory.getItem(10), inventory.getItem(11), inventory.getItem(12)},
                new ItemStack[] {inventory.getItem(19), inventory.getItem(20), inventory.getItem(21)},
                new ItemStack[] {inventory.getItem(28), inventory.getItem(29), inventory.getItem(30)}
        };
        Map<Integer, ItemStack> gridMap = new HashMap<>();
        for (int index : gridSlots) {
            if (inventory.getItem(index) != null) {
                gridMap.put(index, inventory.getItem(index));
            }
        }
        for (CustomRecipe recipe : customRecipes) {
            if (recipe.matches(grid)) {
                shownRecipe = recipe;
                result = makeMenuItemAction(new ItemStack(recipe.getResult()), MenuAction.CRAFT);
                amountsNeeded = recipe.getAmountsNeeded(gridMap);
                return true;
            }
        }
        resetRecipe();
        return false;
    }

    private void resetRecipe() {
        shownRecipe = null;
        result = null;
        amountsNeeded = null;
    }

    private boolean testItem(ItemStack first, ItemStack other) {
        ItemInfo firstInfo = ItemEditor.getInfo(first), otherInfo = ItemEditor.getInfo(other);
        if (firstInfo == null && otherInfo == null) {
            return first.isSimilar(other);
        }
        if (firstInfo != null && otherInfo != null) {
            return first.isSimilar(other) && firstInfo.getItemID() == otherInfo.getItemID();
        }
        if (firstInfo != null && otherInfo == null) {
            if (firstInfo.getItemType() != ItemType.VANILLA_ITEM) {
                return false;
            }
            return firstInfo.getVanillaMaterial() == other.getType();
        }
        if (firstInfo == null && otherInfo != null) {
            if (otherInfo.getItemType() != ItemType.VANILLA_ITEM) {
                return false;
            }
            return otherInfo.getVanillaMaterial() == other.getType();
        }
        return false;
    }

}