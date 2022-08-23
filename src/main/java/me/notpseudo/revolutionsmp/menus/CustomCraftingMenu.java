package me.notpseudo.revolutionsmp.menus;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.customcrafting.CustomCraftingUtils;
import me.notpseudo.revolutionsmp.customcrafting.CustomRecipe;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.items.ItemType;
import me.notpseudo.revolutionsmp.itemstats.ItemInfo;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
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
        addBackType(MenuType.MAIN);
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
            checkGrid();
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
            craftItem(event);
        }
        checkGrid();
    }

    @Override
    public void handleDrag(InventoryDragEvent event) {
        checkGrid();
    }

    private void craftItem(InventoryClickEvent event) {
        if (result == null || shownRecipe == null || amountsNeeded == null) {
            return;
        }
        ClickType clickType = event.getClick();
        if (!(clickType == ClickType.LEFT || clickType == ClickType.SHIFT_LEFT)) {
            return;
        }
        if (clickType == ClickType.LEFT) {
            ItemStack onCursor = player.getItemOnCursor();
            if (onCursor.getType() == Material.AIR) {
                ItemStack newItem = new ItemStack(result);
                newItem.editMeta(m -> m.getPersistentDataContainer().remove(MenuUtils.getMenuKey()));
                player.setItemOnCursor(newItem);
            } else if (testItem(result, onCursor)) {
                ItemStack newResult = new ItemStack(onCursor);
                int newAmount = onCursor.getAmount() + result.getAmount();
                if (newAmount > result.getType().getMaxStackSize()) {
                    return;
                }
                newResult.setAmount(newAmount);
                newResult.editMeta(m -> m.getPersistentDataContainer().remove(MenuUtils.getMenuKey()));
                player.setItemOnCursor(newResult);
            }
        } else if (clickType == ClickType.SHIFT_LEFT) {
            ItemStack add = new ItemStack(result);
            add.editMeta(m -> m.getPersistentDataContainer().remove(MenuUtils.getMenuKey()));
            if (player.getInventory().addItem(add).size() > 0) {
                return;
            }
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
        checkGrid();
    }

    private void checkGrid() {
        BukkitRunnable check = new BukkitRunnable() {
            @Override
            public void run() {
                ItemStack middle = inventory.getItem(20);
                ItemStack[][] grid = new ItemStack[][] {
                        new ItemStack[] {inventory.getItem(10), inventory.getItem(11), inventory.getItem(12)},
                        new ItemStack[] {inventory.getItem(19), middle, inventory.getItem(21)},
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
                        ItemInfo middleInfo = ItemEditor.getInfo(middle);
                        if (middleInfo != null && middleInfo.getItemType() != ItemType.VANILLA_ITEM && middleInfo.getItemType() != ItemType.ITEM) {
                            result = makeMenuAction(recipe.getResult(middleInfo), MenuAction.CRAFT);
                        } else {
                            result = makeMenuAction(recipe.getResult(), MenuAction.CRAFT);
                        }
                        amountsNeeded = recipe.getAmountsNeeded(gridMap);
                        inventory.setItem(23, result);
                        return;
                    }
                }
                inventory.setItem(23, null);
                resetRecipe();
            }
        };
        check.runTaskLater(RevolutionSMP.getPlugin(), 1);
    }

    private void resetRecipe() {
        shownRecipe = null;
        result = null;
        amountsNeeded = null;
    }

    private boolean testItem(ItemStack first, ItemStack other) {
        ItemInfo firstInfo = ItemEditor.getInfo(first), otherInfo = ItemEditor.getInfo(other);
        if (firstInfo == null && otherInfo == null) {
            return first.getType() == other.getType();
        }
        if (firstInfo != null && otherInfo != null) {
            return first.getType() == other.getType() && firstInfo.getItemID() == otherInfo.getItemID();
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
            return otherInfo.getVanillaMaterial() == first.getType();
        }
        return false;
    }

}