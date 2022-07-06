package me.notpseudo.revolutionsmp.items;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.abilities.AbilityObject;
import me.notpseudo.revolutionsmp.abilities.AbilityType;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentObject;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentType;
import me.notpseudo.revolutionsmp.itemstats.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    /**
     * Returns the ItemInfo NamespacedKey for other classes to access ItemInfo in persistent data
     *
     * @return The ItemInfo NamespacedKey
     */
    public static NamespacedKey getItemKey() {
        return itemKey;
    }

    /**
     * Reads stats stored in the ItemMeta's persistent data and formats the item lore to display info for Players
     *
     * @param meta The ItemMeta to read stats from and generate lore for
     */
    public static void updateLore(ItemMeta meta) {
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        ItemInfo itemInfo = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
        if (itemInfo == null) {
            return;
        }
        WeaponStats weaponStats = itemInfo.getWeaponStats();
        ArmorStats armorStats = itemInfo.getArmorStats();
        AbilityStats abilityStats = itemInfo.getAbilityStats();
        EnchantmentsHolder enchantmentsHolder = itemInfo.getEnchantmentsHolder();
        AbilitiesHolder abilitiesHolder = itemInfo.getAbilitiesHolder();
        List<Component> lore = new ArrayList<>();
        Component name, rarity, damage = null, strength = null, critChance = null, critDamage = null, attackSpeed = null, ferocity = null, health = null, defense = null, speed = null, abilityDamage = null, intelligence = null, hasReforge = null;
        Component potatoDamage = Component.text(""), potatoStrength = Component.text(""), potatoHealth = Component.text(""), potatoDefense = Component.text("");
        Component reforgeDamage = Component.text(""), reforgeStrength = Component.text(""), reforgeCritChance = Component.text(""), reforgeCritDamage = Component.text(""), reforgeAttackSpeed = Component.text(""), reforgeFerocity = Component.text(""), reforgeHealth = Component.text(""), reforgeDefense = Component.text(""), reforgeSpeed = Component.text(""), reforgeAbilityDamage = Component.text(""), reforgeIntelligence = Component.text("");
        String rarityName = itemInfo.getRarity().name();
        String itemType = itemInfo.getItemType().name();
        NamedTextColor rarityColor = itemInfo.getRarity().getRarityColor();
        if (itemInfo.isRecomb()) {
            rarity = Component.text("S ", rarityColor, TextDecoration.OBFUSCATED, TextDecoration.BOLD).append(Component.text(rarityName + " " + itemType, rarityColor, TextDecoration.BOLD).decoration(TextDecoration.OBFUSCATED, false).append(Component.text(" S", rarityColor, TextDecoration.OBFUSCATED, TextDecoration.BOLD)));
        } else {
            rarity = Component.text(rarityName + " " + itemType).color(rarityColor).decoration(TextDecoration.BOLD, true);
        }
        if (itemInfo.getPotatoBooks() != 0) {
            if (isArmor(itemInfo)) {
                potatoHealth = Component.text(" (" + getStatString(itemInfo.getPotatoBooks() * 4) + ")", NamedTextColor.YELLOW);
                potatoDefense = Component.text(" (" + getStatString(itemInfo.getPotatoBooks() * 2) + ")", NamedTextColor.YELLOW);
            } else if (isWeapon(itemInfo)) {
                potatoDamage = Component.text(" (" + getStatString(itemInfo.getPotatoBooks() * 2) + ")", NamedTextColor.YELLOW);
                potatoStrength = Component.text(" (" + getStatString(itemInfo.getPotatoBooks() * 2) + ")", NamedTextColor.YELLOW);
            }
        }
        String reforgeName = "";
        if (itemInfo.getReforge() != null) {
            Reforge reforge = itemInfo.getReforge();
            reforgeName = reforge.name().charAt(0) + reforge.name().substring(1).toLowerCase() + " ";
            WeaponStats reforgeWeaponStats = reforge.getWeaponStats(itemInfo.getRarity());
            ArmorStats reforgeArmorStats = reforge.getArmorStats(itemInfo.getRarity());
            AbilityStats reforgeAbilityStats = reforge.getAbilityStats(itemInfo.getRarity());
            if (reforgeWeaponStats.getDamage() != 0) {
                reforgeDamage = Component.text(" (" + reforgeName + getStatString(reforgeWeaponStats.getDamage()) + ")", NamedTextColor.BLUE);
            }
            if (reforgeWeaponStats.getStrength() != 0) {
                reforgeStrength = Component.text(" (" + reforgeName + getStatString(reforgeWeaponStats.getStrength()) + ")", NamedTextColor.BLUE);
            }
            if (reforgeWeaponStats.getCritChance() != 0) {
                reforgeCritChance = Component.text(" (" + reforgeName + getStatString(reforgeWeaponStats.getCritChance()) + "%)", NamedTextColor.BLUE);
            }
            if (reforgeWeaponStats.getCritDamage() != 0) {
                reforgeCritDamage = Component.text(" (" + reforgeName + getStatString(reforgeWeaponStats.getCritDamage()) + "%)", NamedTextColor.BLUE);
            }
            if (reforgeWeaponStats.getAttackSpeed() != 0) {
                reforgeAttackSpeed = Component.text(" (" + reforgeName + getStatString(reforgeWeaponStats.getAttackSpeed()) + "%)", NamedTextColor.BLUE);
            }
            if (reforgeWeaponStats.getFerocity() != 0) {
                reforgeFerocity = Component.text(" (" + reforgeName + getStatString(reforgeWeaponStats.getFerocity()) + ")", NamedTextColor.BLUE);
            }
            if (reforgeArmorStats.getHealth() != 0) {
                reforgeHealth = Component.text(" (" + reforgeName + getStatString(reforgeArmorStats.getHealth()) + ")", NamedTextColor.BLUE);
            }
            if (reforgeArmorStats.getDefense() != 0) {
                reforgeDefense = Component.text(" (" + reforgeName + getStatString(reforgeArmorStats.getDefense()) + ")", NamedTextColor.BLUE);
            }
            if (reforgeArmorStats.getSpeed() != 0) {
                reforgeSpeed = Component.text(" (" + reforgeName + getStatString(reforgeArmorStats.getSpeed()) + ")", NamedTextColor.BLUE);
            }
            if (reforgeAbilityStats.getAbilityDamage() != 0) {
                reforgeAbilityDamage = Component.text(" (" + reforgeName + getStatString(reforgeAbilityStats.getAbilityDamage()) + ")", NamedTextColor.BLUE);
            }
            if (reforgeAbilityStats.getIntelligence() != 0) {
                reforgeIntelligence = Component.text(" (" + reforgeName + getStatString(reforgeAbilityStats.getIntelligence()) + ")", NamedTextColor.BLUE);
            }
        } else {
            hasReforge = Component.text("This item can be reforged!", NamedTextColor.DARK_GRAY);
        }
        name = Component.text(reforgeName + itemInfo.getName()).color(itemInfo.getRarity().getRarityColor()); // The displayed name of the item is Reforge + Item Name
        if (weaponStats != null) {
            if (weaponStats.getDamage() != 0) {
                damage = Component.text("Damage: ", NamedTextColor.GRAY).append(Component.text(getStatString(weaponStats.getDamage()), NamedTextColor.RED)).append(potatoDamage).append(reforgeDamage);
            }
            if (weaponStats.getStrength() != 0) {
                strength = Component.text("Strength: ", NamedTextColor.GRAY).append(Component.text(getStatString(weaponStats.getStrength()), NamedTextColor.RED)).append(potatoStrength).append(reforgeStrength);
            }
            if (weaponStats.getCritChance() != 0) {
                critChance = Component.text("Crit Chance: ", NamedTextColor.GRAY).append(Component.text(getStatString(weaponStats.getCritChance()) + "%", NamedTextColor.RED)).append(reforgeCritChance);
            }
            if (weaponStats.getCritDamage() != 0) {
                critDamage = Component.text("Crit Damage: ", NamedTextColor.GRAY).append(Component.text(getStatString(weaponStats.getCritDamage()) + "%", NamedTextColor.RED)).append(reforgeCritDamage);
            }
            if (weaponStats.getAttackSpeed() != 0) {
                attackSpeed = Component.text("Bonus Attack Speed: ", NamedTextColor.GRAY).append(Component.text(getStatString(weaponStats.getAttackSpeed()) + "%", NamedTextColor.RED)).append(reforgeAttackSpeed);
            }
            if (weaponStats.getFerocity() != 0) {
                ferocity = Component.text("Ferocity: ", NamedTextColor.GRAY).append(Component.text(getStatString(weaponStats.getFerocity()), NamedTextColor.GREEN)).append(reforgeFerocity);
            }
        }
        if (armorStats != null) {
            if (armorStats.getHealth() != 0) {
                health = Component.text("Health: ", NamedTextColor.GRAY).append(Component.text(getStatString(armorStats.getHealth()), NamedTextColor.GREEN)).append(potatoHealth).append(reforgeHealth);
            }
            if (armorStats.getDefense() != 0) {
                defense = Component.text("Defense: ", NamedTextColor.GRAY).append(Component.text(getStatString(armorStats.getDefense()), NamedTextColor.GREEN)).append(potatoDefense).append(reforgeDefense);
            }
            if (armorStats.getSpeed() != 0) {
                speed = Component.text("Speed: ", NamedTextColor.GRAY).append(Component.text(getStatString(armorStats.getSpeed()), NamedTextColor.GREEN)).append(reforgeSpeed);
            }
        }
        if (abilityStats != null) {
            if (abilityStats.getAbilityDamage() != 0) {
                abilityDamage = Component.text("Ability Damage: ", NamedTextColor.GRAY).append(Component.text(getStatString(abilityStats.getAbilityDamage()) + "%", NamedTextColor.RED)).append(reforgeAbilityDamage);
            }
            if (abilityStats.getIntelligence() != 0) {
                intelligence = Component.text("Intelligence: ", NamedTextColor.GRAY).append(Component.text(getStatString(abilityStats.getIntelligence()), NamedTextColor.GREEN)).append(reforgeIntelligence);
            }
        }
        meta.displayName(name.decoration(TextDecoration.ITALIC, false));
        if (damage != null) {
            lore.add(damage.decoration(TextDecoration.ITALIC, false));
        }
        if (strength != null) {
            lore.add(strength.decoration(TextDecoration.ITALIC, false));
        }
        if (critChance != null) {
            lore.add(critChance.decoration(TextDecoration.ITALIC, false));
        }
        if (critDamage != null) {
            lore.add(critDamage.decoration(TextDecoration.ITALIC, false));
        }
        if (attackSpeed != null) {
            lore.add(attackSpeed.decoration(TextDecoration.ITALIC, false));
        }
        if (abilityDamage != null) {
            lore.add(abilityDamage.decoration(TextDecoration.ITALIC, false));
        }
        if (health != null) {
            lore.add(health.decoration(TextDecoration.ITALIC, false));
        }
        if (defense != null) {
            lore.add(defense.decoration(TextDecoration.ITALIC, false));
        }
        if (speed != null) {
            lore.add(speed.decoration(TextDecoration.ITALIC, false));
        }
        if (intelligence != null) {
            lore.add(intelligence.decoration(TextDecoration.ITALIC, false));
        }
        if (ferocity != null) {
            lore.add(ferocity.decoration(TextDecoration.ITALIC, false));
        }
        lore.add(Component.text(""));
        if (enchantmentsHolder != null) {
            for(String str : enchantmentsHolder.getEnchantmentsLore()) {
                lore.add(Component.text(str).decoration(TextDecoration.ITALIC, false));
            }
            lore.add(Component.text(""));
        }
        if (abilitiesHolder != null) {
            for(Component component : abilitiesHolder.getAbilitiesLore()) {
                lore.add(component.decoration(TextDecoration.ITALIC, false));
            }
        }
        if (hasReforge != null) {
            lore.add(hasReforge.decoration(TextDecoration.ITALIC, false));
        }
        lore.add(rarity.decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
    }

    /**
     * Returns a formatted String for a stat
     *
     * @param stat The stat value to generate a formatted String for
     * @return The formatted String representing the stat
     */
    private static String getStatString(double stat) {
        String statString = "";
        if (stat > 0) statString += "+";
        if (stat % 1 == 0) {
            return statString + (int) stat;
        } else {
            return statString + stat;
        }
    }

    /**
     * Recombobulates the item, upgrading its rarity by 1
     *
     * @param item The item to recombobulate
     */
    public static void recombobulate(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        ItemInfo info = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
        if (info != null) {
            Rarity currentRarity = info.getRarity();
            if (currentRarity != Rarity.SPECIAL && !(info.isRecomb())) {
                Rarity recombRarity = currentRarity.next();
                info.setRecomb(true);
                info.setRarity(recombRarity);
                meta.getPersistentDataContainer().set(itemKey, new ItemInfoDataType(), info);
                updateLore(meta);
                item.setItemMeta(meta);
            }
        }
    }

    /**
     * Adds a potato book to the Item held in the Player's main hand
     *
     * @param item The item to add the book to
     */
    public static void addHotPotatoBook(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        ItemInfo itemInfo = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
        if (itemInfo == null) {
            return;
        }
        int currentPotatoBooks = itemInfo.getPotatoBooks();
        itemInfo.setPotatoBooks(currentPotatoBooks + 1);
        meta.getPersistentDataContainer().set(itemKey, new ItemInfoDataType(), itemInfo);
        updateLore(meta);
        item.setItemMeta(meta);
    }

    public static void addHotPotatoBook(ItemStack item, int books) {
        ItemMeta meta = item.getItemMeta();
        ItemInfo itemInfo = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
        if (itemInfo == null) {
            return;
        }
        int currentPotatoBooks = itemInfo.getPotatoBooks();
        itemInfo.setPotatoBooks(currentPotatoBooks + books);
        meta.getPersistentDataContainer().set(itemKey, new ItemInfoDataType(), itemInfo);
        updateLore(meta);
        item.setItemMeta(meta);
    }

    public static void setHotPotatoBook(ItemStack item, int books) {
        ItemMeta meta = item.getItemMeta();
        ItemInfo itemInfo = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
        if (itemInfo == null) {
            return;
        }
        itemInfo.setPotatoBooks(books);
        meta.getPersistentDataContainer().set(itemKey, new ItemInfoDataType(), itemInfo);
        updateLore(meta);
        item.setItemMeta(meta);
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
        ItemMeta meta = item.getItemMeta();
        ItemInfo itemInfo = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
        if (itemInfo == null) {
            return;
        }
        if (reforge.getItemTypes().contains(itemInfo.getItemType())) {
            itemInfo.setReforge(reforge);
            meta.getPersistentDataContainer().set(itemKey, new ItemInfoDataType(), itemInfo);
            updateLore(meta);
            item.setItemMeta(meta);
        }
    }

    public static void addEnchant(ItemStack item, EnchantmentType type, int level) {
        ItemMeta meta = item.getItemMeta();
        ItemInfo itemInfo = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
        if (itemInfo == null) {
            return;
        }
        if (!(Arrays.asList(type.getItemTypes()).contains(itemInfo.getItemType()))) {
            return;
        }
        EnchantmentsHolder enchantHolder = itemInfo.getEnchantmentsHolder();
        if (enchantHolder == null) {
            enchantHolder = new EnchantmentsHolder();
        }
        EnchantmentObject enchant = type.createObject(type);
        enchant.setLevel(level);
        enchantHolder.addEnchant(enchant);
        itemInfo.setEnchantmentsHolder(enchantHolder);
        meta.getPersistentDataContainer().set(itemKey, new ItemInfoDataType(), itemInfo);
        updateLore(meta);
        item.setItemMeta(meta);
    }

    public static void addAbility(ItemStack item, AbilityType type) {
        ItemMeta meta = item.getItemMeta();
        ItemInfo itemInfo = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
        if (itemInfo == null) {
            return;
        }
        AbilitiesHolder abilityHolder = itemInfo.getAbilitiesHolder();
        if (abilityHolder == null) {
            abilityHolder = new AbilitiesHolder(itemInfo);
        }
        abilityHolder.addAbility(type);
        itemInfo.setAbilitiesHolder(abilityHolder);
        meta.getPersistentDataContainer().set(itemKey, new ItemInfoDataType(), itemInfo);
        updateLore(meta);
        item.setItemMeta(meta);
    }

    public static void editManaMultiplier(ItemStack item, double multiplier) {
        ItemMeta meta = item.getItemMeta();
        ItemInfo itemInfo = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
        if (itemInfo == null) {
            return;
        }
        AbilitiesHolder abilityHolder = itemInfo.getAbilitiesHolder();
        if (abilityHolder == null) {
            abilityHolder = new AbilitiesHolder(itemInfo);
        }
        abilityHolder.setManaMultiplier(multiplier);
    }

    public static void editCooldownMultiplier(ItemStack item, double multiplier) {
        ItemMeta meta = item.getItemMeta();
        ItemInfo itemInfo = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
        if (itemInfo == null) {
            return;
        }
        AbilitiesHolder abilityHolder = itemInfo.getAbilitiesHolder();
        if (abilityHolder == null) {
            abilityHolder = new AbilitiesHolder(itemInfo);
        }
        abilityHolder.setCooldownMultiplier(multiplier);
        abilityHolder.reorganize();
    }

    /**
     * Returns if the item is a helmet, chestplate, leggings, or boots
     *
     * @param itemInfo The ItemInfo to check
     * @return true if the item is can be worn as armor, false otherwise
     */
    public static boolean isArmor(ItemInfo itemInfo) {
        ItemType type = itemInfo.getItemType();
        return type == ItemType.HELMET || type == ItemType.CHESTPLATE || type == ItemType.LEGGINGS || type == ItemType.BOOTS;
    }

    public static boolean isWeapon(ItemInfo itemInfo) {
        ItemType type = itemInfo.getItemType();
        return type == ItemType.SWORD || type == ItemType.BOW;
    }

}