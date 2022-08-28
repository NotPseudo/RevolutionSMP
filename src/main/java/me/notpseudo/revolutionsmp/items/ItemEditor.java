package me.notpseudo.revolutionsmp.items;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.abilities.AbilityType;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentObject;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentType;
import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.specialiteminfo.InvalidClassInfo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * This class holds mostly utility methods related to custom items
 *
 * @author NotPseudo
 */
public class ItemEditor {

    /**
     * The NamespacedKey used to access ItemInfo held in persistent data
     */
    private final static NamespacedKey itemKey = new NamespacedKey(RevolutionSMP.getPlugin(RevolutionSMP.class), "items");

    private final static UUID skullUUID = UUID.fromString("dbff4122-fa87-4f79-91f5-0986b8e3f9b3");

    /**
     * Returns the ItemInfo NamespacedKey for other classes to access ItemInfo in persistent data
     *
     * @return The ItemInfo NamespacedKey
     */
    public static NamespacedKey getItemKey() {
        return itemKey;
    }

    public static UUID getSkullUUID() {
        return skullUUID;
    }

    /**
     * Reads stats stored in the ItemMeta's persistent data and formats the item lore to display info for Players
     *
     * @param meta The ItemMeta to read stats from and generate lore for
     */
    public static void updateLore(ItemMeta meta) {
        ItemInfo itemInfo = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
        if (itemInfo == null) {
            return;
        }
        if (itemInfo.getItemType() == ItemType.VANILLA_ITEM) {
            ArrayList<Component> lore = new ArrayList<>(List.of(Component.text(itemInfo.getRarity().name(), itemInfo.getRarity().getRarityColor()).decoration(TextDecoration.BOLD, true).decoration(TextDecoration.ITALIC, false)));
            meta.lore(lore);
            return;
        }
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        ItemID id = itemInfo.getItemID();
        if (id == null) {
            return;
        }
        if (id.isUnbreakable()) {
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            meta.setUnbreakable(true);
        }
        if (id.isEnchantGlint()) {
            meta.addEnchant(Enchantment.LUCK, 1, true);
        }
        List<Component> lore = new ArrayList<>();
        boolean hasBonus = false;
        Component name, rarity, fishingTimeDecrease = null, breakingPower = null,
                gemstoneSlots = null,
                canBeReforged = null;
        Rarity itemRarity = itemInfo.getRarity();
        ItemType itemType = itemInfo.getItemType();
        NamedTextColor rarityColor = itemRarity.getRarityColor();
        if (itemInfo.isRecomb()) {
            rarity = Component.text("S ", rarityColor, TextDecoration.OBFUSCATED, TextDecoration.BOLD).append(Component.text(itemRarity.getName().toUpperCase() + " " + itemType.getName().toUpperCase(), rarityColor, TextDecoration.BOLD).decoration(TextDecoration.OBFUSCATED, false).append(Component.text(" S", rarityColor, TextDecoration.OBFUSCATED, TextDecoration.BOLD)));
        } else {
            rarity = Component.text(itemRarity.getName().toUpperCase() + " " + itemType.getName().toUpperCase()).color(rarityColor).decoration(TextDecoration.BOLD, true);
        }
        String reforgeName = "";
        Reforge reforge = itemInfo.getReforge();
        if (itemInfo.getGemstonesHolder() != null) {
            gemstoneSlots = itemInfo.getGemstonesHolder().getGemLineLore();
        }
        List<Component> fullLines = getFullStatLines(itemInfo);
        if (reforge != null) {
            reforgeName = reforge.getName() + " ";
            hasBonus = reforge.hasBonus();
        } else {
            canBeReforged = Component.text("This item can be reforged!", NamedTextColor.DARK_GRAY);
        }
        name = Component.text(reforgeName + itemInfo.getName()).color(rarityColor);
        if (itemInfo.getStatValue(StatType.FISHING_SPEED) != 0) {
            fishingTimeDecrease = Component.text("Increases fishing speed by ", NamedTextColor.GRAY).append(Component.text(getStatString(itemInfo.getStatObject(StatType.FISHING_SPEED)), NamedTextColor.BLUE));
        }
        if (itemInfo.getStatValue(StatType.BREAKING_POWER) != 0) {
            breakingPower = Component.text("Breaking Power " + (int) itemInfo.getStatValue(StatType.BREAKING_POWER), NamedTextColor.DARK_GRAY);
        }
        meta.displayName(name.decoration(TextDecoration.ITALIC, false));
        if (breakingPower != null) {
            lore.add(breakingPower.decoration(TextDecoration.ITALIC, false));
            lore.add(Component.empty());
        }
        int statLines = fullLines.size();
        lore.addAll(fullLines);
        if (gemstoneSlots != null) {
            lore.add(gemstoneSlots.decoration(TextDecoration.ITALIC, false));
            statLines++;
        }
        if (statLines > 0) {
            lore.add(Component.empty());
        }
        EnchantmentsHolder enchantmentsHolder = itemInfo.getEnchantmentsHolder();
        if (enchantmentsHolder != null) {
            int enchantCount = 0;
            for (String str : enchantmentsHolder.getEnchantmentsLore()) {
                lore.add(Component.text(str).decoration(TextDecoration.ITALIC, false));
                enchantCount++;
            }
            for (EnchantmentObject enchant : enchantmentsHolder.getEnchants()) {
                Enchantment vanilla = enchant.getType().getVanillaEnchantment(itemInfo.getItemID());
                if (vanilla != null) {
                    meta.addEnchant(vanilla, Math.min(enchant.getLevel(), vanilla.getMaxLevel()), true);
                }
            }
            if (enchantCount > 0) {
                lore.add(Component.empty());
            }
        }
        AbilitiesHolder abilitiesHolder = itemInfo.getAbilitiesHolder();
        if (abilitiesHolder != null) {
            int abilityCount = 0;
            for (Component component : abilitiesHolder.getAbilitiesLore()) {
                lore.add(component.decoration(TextDecoration.ITALIC, false));
                abilityCount++;
            }
            if (abilityCount > 0) {
                lore.add(Component.empty());
            }
        }
        if (fishingTimeDecrease != null) {
            lore.add(fishingTimeDecrease.decoration(TextDecoration.ITALIC, false));
            lore.add(Component.empty());
        }
        if (itemInfo.getExtraInfo() != null) {
            int lines = 0;
            for (Component line : itemInfo.getExtraInfo().getSpecialLore()) {
                lore.add(line.decoration(TextDecoration.ITALIC, false));
                lines++;
            }
            if (lines > 0) {
                lore.add(Component.empty());
            }
        }
        if (hasBonus) {
            lore.add(Component.text(getStringFromEnum(reforge) + " Bonus", NamedTextColor.BLUE).decoration(TextDecoration.ITALIC, false));
            int lines = 0;
            for (Component line : reforge.getBonusLore(itemInfo.getRarity())) {
                lore.add(line.decoration(TextDecoration.ITALIC, false));
                lines++;
            }
            if (lines > 0) {
                lore.add(Component.empty());
            }
        }
        if (canBeReforged != null && itemType.allowReforge()) {
            lore.add(canBeReforged.decoration(TextDecoration.ITALIC, false));
        }
        lore.add(rarity.decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
    }

    public static void updateItemOwner(ItemStack item, UUID newOwner) {
        if (item == null || item.getType() == Material.AIR) {
            return;
        }
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return;
        }
        ItemInfo itemInfo = getInfo(item);
        if (itemInfo != null) {
            if (itemInfo.getVanillaMaterial() != item.getType()) {
                item.setType(itemInfo.getVanillaMaterial());
            }
            if (!(itemInfo.getItemType() == ItemType.VANILLA_ITEM || itemInfo.getItemType() == ItemType.ITEM)) {
                if (itemInfo.getOwner() == null) {
                    itemInfo.setOwner(newOwner);
                }
                if (!newOwner.equals(itemInfo.getOwner())) {
                    itemInfo.setOwner(newOwner);
                }
            }
            meta.getPersistentDataContainer().set(itemKey, new ItemInfoDataType(), itemInfo);
            updateLore(meta);
            item.setItemMeta(meta);
        } else {
            item.setItemMeta(createMetaFromMat(meta, item.getType()));
        }
    }

    public static ItemStack updateItemInfo(ItemStack item, ItemInfo info) {
        if (item == null || item.getType() == Material.AIR || info == null) {
            return item;
        }
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(itemKey, new ItemInfoDataType(), info);
        updateLore(meta);
        item.setItemMeta(meta);
        return item;
    }

    /**
     * Returns a formatted String for a stat
     *
     * @param statObject The StatObject storing the value to generate a formatted String for
     * @return The formatted String representing the stat
     */
    public static String getStatString(StatObject statObject) {
        double stat = statObject.getValue();
        if (stat == 0) {
            return "";
        }
        String statString = "";
        if (stat > 0) {
            statString += "+";
        }
        if (stat % 1 == 0) {
            statString += (int) stat;
        } else {
            statString += stat;
        }
        if (statObject.getType().isPercentage()) {
            statString += "%";
        }
        return statString;
    }

    public static String getStatString(double stat) {
        String statString = "";
        if (stat > 0) {
            statString += "+";
        }
        if (stat % 1 == 0) {
            statString += (int) stat;
        } else {
            statString += stat;
        }
        return statString;
    }

    public static Map<StatType, Component> getBaseStatLines(ItemInfo info) {
        HashMap<StatType, Component> lines = new HashMap<>();
        if (info == null) {
            for (StatType type : StatType.values()) {
                lines.put(type, Component.empty());
            }
            return lines;
        }
        for (StatType type : StatType.values()) {
            StatObject object = info.getStatObject(type);
            if (object != null && object.getValue() != 0) {
                lines.put(type, getBaseStatComponent(object));
            }
        }
        return lines;
    }

    public static Map<StatType, Component> getReforgeLines(ItemInfo info) {
        HashMap<StatType, Component> lines = new HashMap<>();
        if (info == null || info.getReforge() == null) {
            for (StatType type : StatType.values()) {
                lines.put(type, Component.empty());
            }
            return lines;
        }
        Reforge reforge = info.getReforge();
        UUID owner = info.getOwner();
        Rarity rarity = info.getRarity();
        WeaponStats reforgeWeaponStats = reforge.getWeaponStats(rarity, owner);
        ArmorStats reforgeArmorStats = reforge.getArmorStats(rarity, owner);
        AbilityStats reforgeAbilityStats = reforge.getAbilityStats(rarity, owner);
        FishingStats reforgeFishingStats = reforge.getFishingStats(rarity, owner);
        MiningStats reforgeMiningStats = reforge.getMiningStats(rarity, owner);
        GatheringStats reforgeGatheringStats = reforge.getGatheringStats(rarity, owner);
        LuckStats reforgeLuckStats = reforge.getLuckStats(rarity, owner);
        WisdomStats reforgeWisdomStats = reforge.getWisdomStats(rarity, owner);
        RegenStats reforgeRegenStats = reforge.getRegenStats(rarity, owner);
        for (StatType type : StatType.values()) {
            StatObject object = switch (type.getStatCategory()) {
                case COMBAT -> reforgeWeaponStats.getStatObject(type);
                case ARMOR -> reforgeArmorStats.getStatObject(type);
                case INTELLIGENCE -> reforgeAbilityStats.getStatObject(type);
                case FISHING -> reforgeFishingStats.getStatObject(type);
                case MINING -> reforgeMiningStats.getStatObject(type);
                case GATHERING -> reforgeGatheringStats.getStatObject(type);
                case LUCK -> reforgeLuckStats.getStatObject(type);
                case WISDOM -> reforgeWisdomStats.getStatObject(type);
                case REGEN -> reforgeRegenStats.getStatObject(type);
            };
            if (object != null && object.getValue() != 0) {
                lines.put(type, getBracketComponent(object, NamedTextColor.BLUE));
            } else {
                lines.put(type, Component.empty());
            }
        }
        return lines;
    }

    public static Map<StatType, Component> getModifierLines(ItemInfo info) {
        HashMap<StatType, Component> lines = new HashMap<>();
        if (info == null || info.getModifiers() == null) {
            for (StatType type : StatType.values()) {
                lines.put(type, Component.empty());
            }
            return lines;
        }
        ModifierInfo modifiers = info.getModifiers();
        int potato = modifiers.getTotalPotatoBooks();
        for (StatType type : StatType.values()) {
            switch (type) {
                default -> lines.put(type, Component.empty());
                case DAMAGE -> {
                    Component damage = Component.empty();
                    if (potato > 0 && isWeapon(info)) {
                        damage = getBracketComponent(potato * 2, NamedTextColor.YELLOW);
                    }
                    lines.put(StatType.DAMAGE, damage);
                }
                case DEFENSE -> {
                    Component defense = Component.empty();
                    if (potato > 0 && isArmor(info)) {
                        defense = getBracketComponent(potato * 2, NamedTextColor.YELLOW);
                    }
                    lines.put(StatType.DEFENSE, defense);
                }
                case HEALTH -> {
                    Component health = Component.empty();
                    if (potato > 0 && isArmor(info)) {
                        health = getBracketComponent(potato * 4, NamedTextColor.YELLOW);
                    }
                    lines.put(StatType.HEALTH, health);
                }
                case STRENGTH -> {
                    int wood = modifiers.getWoodSingularity(), art = modifiers.getArtOfWar();
                    Component potatoes = Component.empty(), woods = Component.empty(), arts = Component.empty();
                    if (potato > 0 && isWeapon(info)) {
                        potatoes = getBracketComponent(potato * 2, NamedTextColor.YELLOW);
                    }
                    if (wood > 0) {
                        woods = getBracketComponent(wood * 100, NamedTextColor.YELLOW);
                    }
                    if (art > 0) {
                        arts = Component.text(" [" + (art * 5) + "]", NamedTextColor.GOLD);
                    }
                    lines.put(StatType.STRENGTH, potatoes.append(woods).append(arts));
                }
                case FARMING_FORTUNE -> {
                    Component farming = Component.empty();
                    int farmings = modifiers.getFarmingForDummies();
                    if (farmings > 0) {
                        farming = getBracketComponent(farmings, NamedTextColor.YELLOW);
                    }
                    lines.put(StatType.FARMING_FORTUNE, farming);
                }
            }
        }
        return lines;
    }

    public static Map<StatType, Component> getGemstoneLines(ItemInfo info) {
        HashMap<StatType, Component> lines = new HashMap<>();
        if (info == null || info.getGemstonesHolder() == null) {
            for (StatType type : StatType.values()) {
                lines.put(type, Component.empty());
            }
            return lines;
        }
        GemstonesHolder holder = info.getGemstonesHolder();
        WeaponStats gemWeapon = holder.getGemWeapon();
        ArmorStats gemArmor = holder.getGemArmor();
        AbilityStats gemAbility = holder.getGemAbility();
        FishingStats gemFishing = holder.getGemFishing();
        MiningStats gemMining = holder.getGemMining();
        GatheringStats gemGathering = holder.getGemGathering();
        LuckStats gemLuck = holder.getGemLuck();
        WisdomStats gemWisdom = holder.getGemWisdom();
        RegenStats gemRegen = holder.getGemRegen();
        for (StatType type : StatType.values()) {
            StatObject object = switch (type.getStatCategory()) {
                case COMBAT -> gemWeapon.getStatObject(type);
                case ARMOR -> gemArmor.getStatObject(type);
                case INTELLIGENCE -> gemAbility.getStatObject(type);
                case FISHING -> gemFishing.getStatObject(type);
                case MINING -> gemMining.getStatObject(type);
                case GATHERING -> gemGathering.getStatObject(type);
                case LUCK -> gemLuck.getStatObject(type);
                case WISDOM -> gemWisdom.getStatObject(type);
                case REGEN -> gemRegen.getStatObject(type);
            };
            if (object != null && object.getValue() != 0) {
                lines.put(type, getBracketComponent(object, NamedTextColor.BLUE));
            } else {
                lines.put(type, Component.empty());
            }
        }
        return lines;
    }

    public static List<Component> getFullStatLines(ItemInfo info) {
        Map<StatType, Component> base = getBaseStatLines(info);
        Map<StatType, Component> modifier = getModifierLines(info);
        Map<StatType, Component> reforge = getReforgeLines(info);
        Map<StatType, Component> gem = getGemstoneLines(info);
        ArrayList<Component> lines = new ArrayList<>();
        for (StatType type : StatType.getRegularShownTypes()) {
            Component start = base.get(type);
            if (start == null) {
                continue;
            }
            start = start.append(modifier.get(type)).append(reforge.get(type)).append(gem.get(type)).decoration(TextDecoration.ITALIC, false);
            lines.add(start);
        }
        return lines;
    }

    public static Component getBaseStatComponent(StatObject stat) {
        StatType type = stat.getType();
        return Component.text(type.getName() + ": ", NamedTextColor.GRAY)
                .append(Component.text(getStatString(stat), type.getItemDisplayColor()));
    }

    private static Component getBracketComponent(StatObject stat, NamedTextColor color) {
        String statString = getStatString(stat);
        if (statString.equals("")) {
            return Component.text(" ");
        }
        return Component.text(" (" + getStatString(stat) + ")", color);
    }

    private static Component getBracketComponent(double stat, NamedTextColor color) {
        String statString = getStatString(stat);
        if (statString.equals("")) {
            return Component.text(" ");
        }
        return Component.text(" (" + getStatString(stat) + ")", color);
    }

    /**
     * Recombobulates the item, upgrading its rarity by 1
     *
     * @param item The item to recombobulate
     */
    public static void recombobulate(ItemStack item) {
        ItemInfo info = getInfo(item);
        if (info != null) {
            info.recomb();
            updateItemInfo(item, info);
        }
    }

    public static void addPotatoBook(ItemStack item, int books) {
        ItemInfo itemInfo = getInfo(item);
        if (itemInfo == null) {
            return;
        }
        ModifierInfo mod = itemInfo.getModifiers();
        if (mod != null) {
            mod.setTotalBooks(mod.getTotalPotatoBooks() + books);
            updateItemInfo(item, itemInfo);
        }
    }

    public static void setPotatoBooks(ItemStack item, int books) {
        ItemInfo itemInfo = getInfo(item);
        if (itemInfo == null) {
            return;
        }
        ModifierInfo mod = itemInfo.getModifiers();
        if (mod != null) {
            mod.setTotalBooks(books);
            updateItemInfo(item, itemInfo);
        }
    }

    /**
     * Reforges the Item with the specified Reforged and Rarity
     *
     * @param item    The Item to add the Reforge to
     * @param reforge The specific Reforge to apply
     */
    public static void reforge(ItemStack item, Reforge reforge) {
        if (reforge == null) {
            return;
        }
        ItemInfo itemInfo = getInfo(item);
        if (itemInfo == null) {
            return;
        }
        if (reforge.getItemTypes().contains(itemInfo.getItemType())) {
            itemInfo.setReforge(reforge);
            updateItemInfo(item, itemInfo);
        }
    }

    public static void addEnchant(ItemStack item, EnchantmentType type, int level) {
        ItemInfo itemInfo = getInfo(item);
        if (itemInfo == null) {
            return;
        }
        if (!itemInfo.getItemType().allowEnchants()) {
            return;
        }
        if (!type.getItemTypes().contains(itemInfo.getItemType())) {
            return;
        }
        EnchantmentObject enchant = type.createObject(level);
        itemInfo.addEnchant(enchant);
        updateItemInfo(item, itemInfo);
    }

    public static void addAbility(ItemStack item, AbilityType type) {
        ItemInfo itemInfo = getInfo(item);
        if (itemInfo == null) {
            return;
        }
        itemInfo.addAbility(type);
        updateItemInfo(item, itemInfo);
    }

    /**
     * Creates a new ItemStack and sets meta information based on an ItemID
     *
     * @param itemID The ItemID to create a new item for
     * @return The ItemStack that represents the basic form of the ItemID
     */
    public static ItemStack createItem(ItemID itemID) {
        ItemStack item = new ItemStack(itemID.getMaterial(), 1);
        ItemMeta meta = item.getItemMeta();
        item.setItemMeta(createMetaFromID(meta, itemID));
        return item;
    }

    public static ItemStack createItemFromMat(Material material) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        item.setItemMeta(createMetaFromMat(meta, material));
        return item;
    }

    public static ItemMeta createMetaFromID(ItemMeta meta, ItemID itemID) {
        if (meta == null) {
            return null;
        }
        meta.getPersistentDataContainer().set(itemKey, new ItemInfoDataType(), new ItemInfo(itemID));
        if (meta instanceof LeatherArmorMeta leatherMeta && itemID.getColor() != null) {
            leatherMeta.setColor(itemID.getColor());
        }
        if (meta instanceof SkullMeta skullMeta && itemID.getTexture() != null) {
            PlayerProfile profile = Bukkit.getServer().createProfile(skullUUID);
            profile.setProperty(new ProfileProperty("textures", itemID.getTexture()));
            skullMeta.setPlayerProfile(profile);
        }
        updateLore(meta);
        return meta;
    }

    public static ItemMeta createMetaFromMat(ItemMeta meta, Material material) {
        if (meta == null) {
            return null;
        }
        try {
            ItemID itemID = ItemID.valueOf(material.toString());
            return createMetaFromID(meta, itemID);
        } catch (IllegalArgumentException exception) {
            meta.getPersistentDataContainer().set(itemKey, new ItemInfoDataType(), new ItemInfo(material));
            updateLore(meta);
            return meta;
        }
    }

    /**
     * Returns whether the item is a helmet, chestplate, leggings, or boots
     *
     * @param itemInfo The ItemInfo to check
     * @return true if the item can be worn as armor, false otherwise
     */
    public static boolean isArmor(ItemInfo itemInfo) {
        ItemType type = itemInfo.getItemType();
        return type == ItemType.HELMET || type == ItemType.CHESTPLATE || type == ItemType.LEGGINGS || type == ItemType.BOOTS;
    }

    /**
     * Returns whether the item is a weapon
     *
     * @param itemInfo The ItemInfo to check
     * @return true if the item is considered a weapon
     */
    public static boolean isWeapon(ItemInfo itemInfo) {
        ItemType type = itemInfo.getItemType();
        return type == ItemType.SWORD || type == ItemType.BOW || type == ItemType.FISHING_ROD || type == ItemType.FISHING_WEAPON || type == ItemType.GAUNTLET || type == ItemType.LONGSWORD;
    }

    @Nullable
    public static ItemInfo getMainHandInfo(Player player) {
        return getInfo(player.getInventory().getItemInMainHand());
    }

    @Nullable
    public static ItemInfo getInfo(ItemStack item) {
        if (item == null || item.getType() == Material.AIR) {
            return null;
        }
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return null;
        }
        ItemInfo info = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
        if (info == null || info.getExtraInfo() instanceof InvalidClassInfo) {
            info = new ItemInfo(item.getType());
            updateItemInfo(item, info);
        }
        return info;
    }

    /**
     * Gets a formatted String from an Enum value
     *
     * @param value The Enum value to get the String from
     * @return The formatted String
     */
    public static String getStringFromEnum(Enum value) {
        String[] split = value.toString().split("_");
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            name.append(split[i].charAt(0)).append(split[i].substring(1).toLowerCase());
            if (i < split.length - 1) {
                name.append(" ");
            }
        }
        return name.toString();
    }

    public static String getOriginalEnumString(String string) {
        StringBuilder original = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char letter = string.charAt(i);
            if (letter == ' ') {
                original.append("_");
            } else {
                original.append(letter);
            }
        }
        return original.toString().toUpperCase();
    }

    /**
     * Method to format large numbers with suffixes
     * <p>
     * Taken from <a href="https://stackoverflow.com/a/9769590">...</a>
     * </p>
     *
     * @param number The number to format
     * @return The formatted String
     */
    public static String numberFormatted(double number) {
        if (number < 1000) {
            return "" + number;
        }
        int exp = (int) (Math.log(number) / Math.log(1000));
        return String.format("%.1f%c",
                number / Math.pow(1000, exp),
                "KMBqQ".charAt(exp - 1));
    }

}