package me.notpseudo.revolutionsmp.items;

import me.notpseudo.revolutionsmp.itemstats.*;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

// Creates custom armors
public class ArmorCreator {

    private static final NamespacedKey itemKey = ItemEditor.getItemKey();

    public static ItemStack stormChest;
    public static ItemStack stormLeg;
    public static ItemStack stormBoot;

    public static void createArmors() {
        stormChest();
        stormLeg();
        stormBoot();
    }

    private static void stormChest() {
        stormChest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemMeta stormChestMeta = stormChest.getItemMeta();
        LeatherArmorMeta leatherMeta = (LeatherArmorMeta) stormChestMeta;
        leatherMeta.setColor(Color.fromRGB(0x1793C4));
        stormChestMeta.getPersistentDataContainer().set(itemKey, new ItemInfoDataType(), new ItemInfo(ItemID.STORM_CHESTPLATE));
        ItemEditor.updateLore(stormChestMeta);
        stormChest.setItemMeta(stormChestMeta);
    }

    private static void stormLeg() {
        stormLeg = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemMeta stormLegMeta = stormLeg.getItemMeta();
        LeatherArmorMeta leatherMeta = (LeatherArmorMeta) stormLegMeta;
        leatherMeta.setColor(Color.fromRGB(0x17A8C4));
        stormLegMeta.getPersistentDataContainer().set(itemKey, new ItemInfoDataType(), new ItemInfo(ItemID.STORM_LEGGINGS));
        ItemEditor.updateLore(stormLegMeta);
        stormLeg.setItemMeta(stormLegMeta);
    }

    private static void stormBoot() {
        stormBoot = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemMeta stormBootMeta = stormBoot.getItemMeta();
        LeatherArmorMeta leatherMeta = (LeatherArmorMeta) stormBootMeta;
        leatherMeta.setColor(Color.fromRGB(0x1CD4E4));
        stormBootMeta.getPersistentDataContainer().set(itemKey, new ItemInfoDataType(), new ItemInfo(ItemID.STORM_BOOTS));
        ItemEditor.updateLore(stormBootMeta);
        stormBoot.setItemMeta(stormBootMeta);
    }

}