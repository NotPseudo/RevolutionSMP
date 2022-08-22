package me.notpseudo.revolutionsmp.menus;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentObject;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentType;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentUtils;
import me.notpseudo.revolutionsmp.customcrafting.items.ItemEditor;
import me.notpseudo.revolutionsmp.itemstats.EnchantmentsHolder;
import me.notpseudo.revolutionsmp.itemstats.ItemInfo;
import me.notpseudo.revolutionsmp.itemstats.ItemInfoDataType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class EnchantmentTableMenu extends Menu {

    private static final List<EnchantmentType> tableEnchants = Stream.of(EnchantmentType.values()).filter(e -> !e.isUltimate()).toList();

    private int firstShown;
    private int lastShown;
    private ArrayList<EnchantmentType> shownEnchants;
    private ItemStack itemToEnchant;

    public EnchantmentTableMenu(Player player) {
        super(player);
        firstShown = 0;
        lastShown = 0;
        shownEnchants = new ArrayList<>();
        itemToEnchant = null;
    }

    @Override
    public Component getTitle() {
        return Component.text("Enchantment Table");
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void setItems() {
        inventory.setItem(19, itemToEnchant);

        ItemStack place = new ItemStack(Material.GRAY_DYE);
        ItemMeta placeMeta = place.getItemMeta();
        placeMeta.displayName(Component.text("Enchant Item", NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        placeMeta.lore(List.of(
                Component.text("Place an item into the open slot to see available enchantments", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)
        ));
        place.setItemMeta(placeMeta);
        inventory.setItem(23, makeMenuItem(place, null));

        ItemStack enchant = new ItemStack(Material.ENCHANTING_TABLE);
        ItemMeta enchantMeta = enchant.getItemMeta();
        enchantMeta.displayName(Component.text("Enchant Item", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        enchantMeta.lore(List.of(
                Component.text("Place an item into the slot above to see available enchantments", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)
        ));
        enchant.setItemMeta(enchantMeta);
        inventory.setItem(28, makeMenuItem(enchant, null));
        addCloseButton();

        if (checkItem()) {
            showEnchants(firstShown);
        }
    }

    @Override
    public void handleClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) {
            return;
        }
        MenuItem menuItem = event.getCurrentItem().getItemMeta().getPersistentDataContainer().get(MenuUtils.getMenuKey(), new MenuItemDataType());
        if (menuItem == null) {
            BukkitRunnable check = new BukkitRunnable() {
                @Override
                public void run() {
                    boolean show = checkItem();
                    setItems();
                    if (show) {
                        showEnchants(0);
                    } else {
                        ItemStack place = new ItemStack(Material.RED_DYE);
                        ItemMeta placeMeta = place.getItemMeta();
                        placeMeta.displayName(Component.text("Invalid Item", NamedTextColor.RED).decoration(TextDecoration.ITALIC, false));
                        placeMeta.lore(List.of(
                                Component.text("This item can not be enchanted", NamedTextColor.RED).decoration(TextDecoration.ITALIC, false)
                        ));
                        place.setItemMeta(placeMeta);
                        inventory.setItem(23, makeMenuItem(place, null));
                    }
                }
            };
            check.runTaskLater(RevolutionSMP.getPlugin(), 1);
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
        if (menuItem.getAction() == MenuAction.NEXT_PAGE) {
            if (moreHigher()) {
                showEnchants(lastShown + 1);
            }
        }
        if (menuItem.getAction() == MenuAction.PREVIOUS_PAGE) {
            if (moreLower()) {
                showEnchants(firstShown - 15);
            }
        }
        if (menuItem.getAction() == MenuAction.BACK) {
            setItems();
        }
        if (menuItem.getAction() == MenuAction.ENCHANT_DETAILS) {
            ItemInfo info = ItemEditor.getInfo(event.getCurrentItem());
            if (info == null) {
                return;
            }
            if (info.getEnchantmentsHolder() == null) {
                return;
            }
            if (info.getEnchantmentsHolder().getEnchants().size() < 1) {
                return;
            }
            showDetails(info.getEnchantmentsHolder().getEnchants().get(0).getType());
        }
        if (menuItem.getAction() == MenuAction.ADD_ENCHANT) {
            ItemInfo itemInfo = ItemEditor.getInfo(itemToEnchant), bookInfo = ItemEditor.getInfo(event.getCurrentItem());
            if (itemInfo == null || bookInfo == null) {
                return;
            }
            if (!itemInfo.getItemType().allowEnchants() || bookInfo.getEnchantmentsHolder() == null) {
                return;
            }
            if (bookInfo.getEnchantmentsHolder().getEnchants().size() < 1) {
                return;
            }
            EnchantmentObject enchant = bookInfo.getEnchantmentsHolder().getEnchants().get(0);
            itemInfo.addEnchant(enchant);
            ItemMeta itemMeta = itemToEnchant.getItemMeta();
            itemMeta.getPersistentDataContainer().set(ItemEditor.getItemKey(), new ItemInfoDataType(), itemInfo);
            ItemEditor.updateLore(itemMeta);
            itemToEnchant.setItemMeta(itemMeta);
        }
        if (menuItem.getAction() == MenuAction.REMOVE_ENCHANT) {
            ItemInfo itemInfo = ItemEditor.getInfo(itemToEnchant), bookInfo = ItemEditor.getInfo(event.getCurrentItem());
            if (itemInfo == null || bookInfo == null) {
                return;
            }
            if (!itemInfo.getItemType().allowEnchants() || bookInfo.getEnchantmentsHolder() == null) {
                return;
            }
            if (bookInfo.getEnchantmentsHolder().getEnchants().size() < 1) {
                return;
            }
            EnchantmentType enchant = bookInfo.getEnchantmentsHolder().getEnchants().get(0).getType();
            itemInfo.removeEnchant(enchant);
            ItemMeta itemMeta = itemToEnchant.getItemMeta();
            itemMeta.getPersistentDataContainer().set(ItemEditor.getItemKey(), new ItemInfoDataType(), itemInfo);
            ItemEditor.updateLore(itemMeta);
            itemToEnchant.setItemMeta(itemMeta);
        }
    }

    private void showDetails(EnchantmentType type) {
        if (type.getEnchTableMax() < type.getMinLevel()) {
            return;
        }
        if (!checkItem()) {
            return;
        }
        fillGlass();
        addCloseButton();
        ItemInfo info = ItemEditor.getInfo(itemToEnchant);
        EnchantmentsHolder holder = info.getEnchantmentsHolder();
        EnchantmentObject enchant = holder.getObjectForType(type);
        for (int enchLevel = type.getMinLevel(); enchLevel <= type.getEnchTableMax(); enchLevel++) {
            int actualLocation = ((enchLevel - 1) / 7 + 2) * 9 + ((enchLevel - 1) % 7 + 1);
            ItemStack book = EnchantmentUtils.createGeneral(type, enchLevel);
            ItemMeta bookMeta = book.getItemMeta();
            List<Component> lore = bookMeta.lore();
            if (lore == null) {
                lore = new ArrayList<>();
            }
            if (enchant == null) {
                lore.add(Component.text("Click to apply this enchantment on your item", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
                bookMeta.lore(lore);
                book.setItemMeta(bookMeta);
                inventory.setItem(actualLocation, makeMenuItemAction(book, MenuAction.ADD_ENCHANT));
            } else {
                if (enchLevel < enchant.getLevel()) {
                    lore.add(Component.text("Higher level of this enchantment is already present!", NamedTextColor.RED).decoration(TextDecoration.ITALIC, false));
                    bookMeta.lore(lore);
                    book.setItemMeta(bookMeta);
                    inventory.setItem(actualLocation, makeMenuItemAction(book, null));
                } else if (enchLevel == enchant.getLevel()) {
                    lore.add(Component.text("This enchantment is already present", NamedTextColor.RED).decoration(TextDecoration.ITALIC, false));
                    lore.add(Component.text("Click to remove this enchantment", NamedTextColor.RED).decoration(TextDecoration.ITALIC, false));
                    bookMeta.lore(lore);
                    book.setItemMeta(bookMeta);
                    inventory.setItem(actualLocation, makeMenuItemAction(book, MenuAction.REMOVE_ENCHANT));
                } else {
                    lore.add(Component.text("Click to apply this enchantment on your item", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
                    bookMeta.lore(lore);
                    book.setItemMeta(bookMeta);
                    inventory.setItem(actualLocation, makeMenuItemAction(book, MenuAction.ADD_ENCHANT));
                }
            }
        }
    }

    private void showEnchants(int start) {
        if (shownEnchants.size() < 1) {
            return;
        }
        int actualStart = Math.max(0, start);
        firstShown = actualStart;
        for (int menuIndex = 0; menuIndex < 15; menuIndex++) {
            int actualIndex = ((menuIndex / 5 + 1) * 9) + ((menuIndex % 5) + 3);
            ItemStack item;
            if (menuIndex + actualStart < shownEnchants.size()) {
                item = makeMenuItemAction(EnchantmentUtils.createGeneral(shownEnchants.get(menuIndex + actualStart)), MenuAction.ENCHANT_DETAILS);
                lastShown = menuIndex + actualStart;
            } else {
                item = makeMenuGlass(new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
            }
            inventory.setItem(actualIndex, item);
        }
        if (moreLower()) {
            ItemStack back = new ItemStack(Material.FEATHER);
            ItemMeta backMeta = back.getItemMeta();
            backMeta.displayName(Component.text("Previous Page", NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
            backMeta.lore(List.of(
               Component.text("Click to view the previous page of enchants", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)
            ));
            back.setItemMeta(backMeta);
            inventory.setItem(17, makeMenuItemAction(back, MenuAction.PREVIOUS_PAGE));
        }
        if (moreHigher()) {
            ItemStack next = new ItemStack(Material.ARROW);
            ItemMeta nextMeta = next.getItemMeta();
            nextMeta.displayName(Component.text("Next Page", NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
            nextMeta.lore(List.of(
                    Component.text("Click to view the next page of enchants", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)
            ));
            next.setItemMeta(nextMeta);
            inventory.setItem(35, makeMenuItemAction(next, MenuAction.NEXT_PAGE));
        }
    }

    /**
     * Returns if there are more enchants to the right of the current page that aren't shown
     * @return true if there are more enchants to the right of the current page
     */
    private boolean moreHigher() {
        return lastShown < shownEnchants.size() - 1;
    }

    /**
     * Returns if there are more enchants to the left of the current page that aren't shown
     * @return true if there are enchants to the left of the current page
     */
    private boolean moreLower() {
        return firstShown > 0;
    }

    private boolean checkItem() {
        itemToEnchant = inventory.getItem(19);
        if (itemToEnchant == null || itemToEnchant.getType() == Material.AIR) {
            firstShown = 0;
            lastShown = 0;
            return false;
        }
        ItemInfo info = ItemEditor.getInfo(itemToEnchant);
        if (info == null) {
            firstShown = 0;
            lastShown = 0;
            return false;
        }
        if (!info.getItemType().allowEnchants()) {
            firstShown = 0;
            lastShown = 0;
            return false;
        }
        shownEnchants = new ArrayList<>();
        shownEnchants.addAll(tableEnchants.stream().filter(e -> e.getItemTypes().contains(info.getItemType())).toList());
        Collections.sort(shownEnchants);
        return true;
    }

}