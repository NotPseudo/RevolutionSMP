package me.notpseudo.revolutionsmp.menus;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentObject;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentType;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentUtils;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.itemstats.EnchantmentsHolder;
import me.notpseudo.revolutionsmp.itemstats.ItemInfo;
import me.notpseudo.revolutionsmp.skills.SkillType;
import me.notpseudo.revolutionsmp.skills.SkillUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnchantmentTableMenu extends Menu {

    private static final List<EnchantmentType> tableEnchants = Stream.of(EnchantmentType.values()).filter(EnchantmentType::showInEnchantTable).toList();

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

    public EnchantmentTableMenu(Player player, @NotNull ItemStack item) {
        super(player);
        firstShown = 0;
        lastShown = 0;
        shownEnchants = new ArrayList<>();
        itemToEnchant = item;
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
        fillGlass();

        inventory.setItem(19, itemToEnchant);

        checkItem();

        ItemStack enchant = new ItemStack(Material.ENCHANTING_TABLE);
        ItemMeta enchantMeta = enchant.getItemMeta();
        enchantMeta.displayName(Component.text("Enchant Item", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        enchantMeta.lore(List.of(
                Component.text("Place an item into the slot above to see available enchantments", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)
        ));
        enchant.setItemMeta(enchantMeta);
        inventory.setItem(28, makeMenuType(enchant, null));

        addBackType(MenuType.MAIN);
        addCloseButton();
    }

    @Override
    public void handleClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) {
            checkItem();
            return;
        }
        MenuItem menuItem = event.getCurrentItem().getItemMeta().getPersistentDataContainer().get(MenuUtils.getMenuKey(), new MenuItemDataType());
        if (menuItem == null) {
            checkItem();
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
            menuItem.getType().getNext(player).open();
        }
        if (menuItem.getAction() == MenuAction.BACK) {
            setItems();
        }
        if (menuItem.getAction() == MenuAction.ENCHANT_DETAIL) {
            EnchantmentObject enchant = menuItem.getEnchant();
            if (enchant == null) {
                return;
            }
            showEnchantDetails(enchant.getType());
            return;
        }
        if (menuItem.getAction() == MenuAction.ADD_ENCHANT) {
            ItemInfo itemInfo = ItemEditor.getInfo(itemToEnchant);
            if (itemInfo == null) {
                return;
            }
            if (!itemInfo.getItemType().allowEnchants()) {
                return;
            }
            EnchantmentObject enchant = menuItem.getEnchant();
            if (enchant == null) {
                return;
            }
            int levelNeeded = enchant.getType().getExpLevelsNeeded(enchant.getLevel());
            if (!(player.getLevel() >= levelNeeded || player.getGameMode() == GameMode.CREATIVE)) {
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1F, 0.5F);
                return;
            }
            itemInfo.addEnchant(enchant);
            ItemEditor.updateItemInfo(itemToEnchant, itemInfo);
            player.giveExpLevels(-1 * levelNeeded);
            player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
            SkillUtils.addRegularXP(SkillType.ENCHANTING, player, EnchantmentUtils.getExpFromLevel(levelNeeded));
            setItems();
        }
        if (menuItem.getAction() == MenuAction.REMOVE_ENCHANT) {
            ItemInfo itemInfo = ItemEditor.getInfo(itemToEnchant);
            if (itemInfo == null) {
                return;
            }
            if (!itemInfo.getItemType().allowEnchants()) {
                return;
            }
            EnchantmentObject enchant = menuItem.getEnchant();
            if (enchant == null) {
                return;
            }
            int levelNeeded = enchant.getType().getExpLevelsNeeded(enchant.getLevel());
            if (!(player.getLevel() >= levelNeeded || player.getGameMode() == GameMode.CREATIVE)) {
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1F, 0.5F);
                return;
            }
            itemInfo.removeEnchant(enchant.getType());
            ItemEditor.updateItemInfo(itemToEnchant, itemInfo);
            player.playSound(player, Sound.ENTITY_ARROW_HIT_PLAYER, 1f, 0.5f);
            setItems();
        }
        if (menuItem.getAction() == MenuAction.NEXT_PAGE) {
            if (moreHigher()) {
                firstShown = lastShown + 1;
                checkItem();
            }
        }
        if (menuItem.getAction() == MenuAction.PREVIOUS_PAGE) {
            if (moreLower()) {
                firstShown = Math.max(0, firstShown - 15);
                checkItem();
            }
        }
    }

    @Override
    public void handleClose(InventoryCloseEvent event) {
        Set<ItemStack> nonMenu = Arrays.stream(inventory.getContents()).filter(i -> i != null && MenuUtils.getMenuInfo(i) == null).collect(Collectors.toSet());
        if (itemToEnchant != null) {
            nonMenu.add(itemToEnchant);
        }
        for (ItemStack item : player.getInventory().addItem(nonMenu.toArray(new ItemStack[0])).values()) {
            player.getWorld().dropItemNaturally(player.getLocation(), item);
        }
    }

    private void showEnchants(int start) {
        if (shownEnchants.size() < 1) {
            return;
        }
        inventory.setItem(23, makeMenuGlass(new ItemStack(Material.GRAY_STAINED_GLASS_PANE)));
        int actualStart = Math.max(0, start);
        firstShown = actualStart;
        for (int menuIndex = 0; menuIndex < 15; menuIndex++) {
            int actualIndex = ((menuIndex / 5 + 1) * 9) + ((menuIndex % 5) + 3);
            ItemStack item;
            if (menuIndex + actualStart < shownEnchants.size()) {
                EnchantmentType type = shownEnchants.get(menuIndex + actualStart);
                item = makeMenuEnchant(EnchantmentUtils.createGeneral(type), type.createObject(type.getMinLevel()), MenuAction.ENCHANT_DETAIL);
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
            inventory.setItem(17, makeMenuAction(back, MenuAction.PREVIOUS_PAGE));
        } else {
            inventory.setItem(17, makeMenuGlass(new ItemStack(Material.GRAY_STAINED_GLASS_PANE)));
        }
        if (moreHigher()) {
            ItemStack next = new ItemStack(Material.ARROW);
            ItemMeta nextMeta = next.getItemMeta();
            nextMeta.displayName(Component.text("Next Page", NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
            nextMeta.lore(List.of(
                    Component.text("Click to view the next page of enchants", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)
            ));
            next.setItemMeta(nextMeta);
            inventory.setItem(35, makeMenuAction(next, MenuAction.NEXT_PAGE));
        } else {
            inventory.setItem(35, makeMenuGlass(new ItemStack(Material.GRAY_STAINED_GLASS_PANE)));
        }
    }

    private void showEnchantDetails(EnchantmentType type) {
        fillGlass();
        ItemInfo info = ItemEditor.getInfo(itemToEnchant);
        if (info == null) {
            return;
        }
        EnchantmentsHolder holder = info.getEnchantmentsHolder();
        if (holder == null) {
            return;
        }
        EnchantmentObject enchant = holder.getObjectForType(type);
        for (int enchLevel = type.getMinLevel(); enchLevel <= type.getEnchTableMax(); enchLevel++) {
            int levelFromLowest = enchLevel - type.getMinLevel();
            int row = levelFromLowest / 7 + 2, column = levelFromLowest % 7 + 1;
            int actualLocation = row * 9 + column;
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
                inventory.setItem(actualLocation, EnchantmentTableMenu.makeMenuEnchant(book, type.createObject(enchLevel), MenuAction.ADD_ENCHANT));
            } else {
                if (enchLevel < enchant.getLevel()) {
                    lore.add(Component.text("Higher level of this enchantment is already present!", NamedTextColor.RED).decoration(TextDecoration.ITALIC, false));
                    bookMeta.lore(lore);
                    book.setItemMeta(bookMeta);
                    inventory.setItem(actualLocation, makeMenuAction(book, null));
                } else if (enchLevel == enchant.getLevel()) {
                    lore.add(Component.text("This enchantment is already present", NamedTextColor.RED).decoration(TextDecoration.ITALIC, false));
                    lore.add(Component.text("Click to remove this enchantment", NamedTextColor.RED).decoration(TextDecoration.ITALIC, false));
                    bookMeta.lore(lore);
                    book.setItemMeta(bookMeta);
                    inventory.setItem(actualLocation, EnchantmentTableMenu.makeMenuEnchant(book, type.createObject(enchLevel), MenuAction.REMOVE_ENCHANT));
                } else {
                    lore.add(Component.text("Click to apply this enchantment on your item", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
                    bookMeta.lore(lore);
                    book.setItemMeta(bookMeta);
                    inventory.setItem(actualLocation, EnchantmentTableMenu.makeMenuEnchant(book, type.createObject(enchLevel), MenuAction.ADD_ENCHANT));
                }
            }
        }
        ItemStack back = new ItemStack(Material.ARROW);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.displayName(Component.text("Back", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        back.setItemMeta(backMeta);
        inventory.setItem(48, makeMenuAction(back, MenuAction.BACK));
        addCloseButton();
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

    private void checkItem() {
        BukkitRunnable check = new BukkitRunnable() {
            @Override
            public void run() {
                itemToEnchant = inventory.getItem(19);
                if (itemToEnchant == null || itemToEnchant.getType() == Material.AIR) {
                    clearEnchants();
                    showPlaceItem(false);
                    firstShown = 0;
                    lastShown = 0;
                    return;
                }
                ItemInfo info = ItemEditor.getInfo(itemToEnchant);
                if (info == null || !info.getItemType().allowEnchants()) {
                    clearEnchants();
                    showPlaceItem(true);
                    firstShown = 0;
                    lastShown = 0;
                    return;
                }
                shownEnchants = new ArrayList<>();
                shownEnchants.addAll(tableEnchants.stream().filter(e -> e.getItemTypes().contains(info.getItemType())).toList());
                Collections.sort(shownEnchants);
                showEnchants(firstShown);
            }
        };
        check.runTaskLater(RevolutionSMP.getPlugin(), 1);
    }

    private void clearEnchants() {
        for (int row = 1; row < 4; row++) {
            for (int col = 3; col < 9; col++) {
                inventory.setItem(row * 9 + col, makeMenuGlass(new ItemStack(Material.GRAY_STAINED_GLASS_PANE)));
            }
        }
    }

    /**
     * Shows the item in the middle of the menu representing if the item can be enchanted
     * @param hasItem If there is actually an item in the enchanting slot
     */
    private void showPlaceItem(boolean hasItem) {
        ItemStack place;
        if (hasItem) {
            place = new ItemStack(Material.RED_DYE);
            ItemMeta placeMeta = place.getItemMeta();
            placeMeta.displayName(Component.text("Invalid Item", NamedTextColor.RED).decoration(TextDecoration.ITALIC, false));
            placeMeta.lore(List.of(
                    Component.text("This item can not be enchanted", NamedTextColor.RED).decoration(TextDecoration.ITALIC, false)
            ));
            place.setItemMeta(placeMeta);
        } else {
            place = new ItemStack(Material.GRAY_DYE);
            ItemMeta placeMeta = place.getItemMeta();
            placeMeta.displayName(Component.text("Enchant Item", NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
            placeMeta.lore(List.of(
                    Component.text("Place an item into the open slot to see available enchantments", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)
            ));
            place.setItemMeta(placeMeta);
        }
        inventory.setItem(23, makeMenuType(place, null));
    }

    public static ItemStack makeMenuEnchant(ItemStack item, EnchantmentObject enchant, MenuAction action) {
        if (item == null || item.getType() == Material.AIR) {
            return null;
        }
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(MenuUtils.getMenuKey(), new MenuItemDataType(), new MenuItem(enchant, action));
        item.setItemMeta(meta);
        return item;
    }

}