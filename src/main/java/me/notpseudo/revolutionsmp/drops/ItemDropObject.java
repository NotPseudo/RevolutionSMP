package me.notpseudo.revolutionsmp.drops;

import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.items.ItemID;
import me.notpseudo.revolutionsmp.items.Rarity;
import me.notpseudo.revolutionsmp.mining.GemstoneType;
import me.notpseudo.revolutionsmp.mining.GemstoneUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemDropObject {

    private double chance;
    private ItemStack item;
    private boolean isCustom;

    public ItemDropObject(double chance, ItemID id) {
        this.chance = chance;
        item = ItemEditor.createItem(id);
        isCustom = true;
    }

    public ItemDropObject(ItemID id) {
        chance = 100;
        item = ItemEditor.createItem(id);
        isCustom = true;
    }

    public ItemDropObject(ItemStack stack) {
        item = stack;
        chance = 100;
    }

    public ItemDropObject(ItemStack stack, double chance) {
        item = stack;
        this.chance = chance;
    }

    public ItemDropObject(ItemID id, int count) {
        chance = 100;
        item = ItemEditor.createItem(id);
        isCustom = true;
        item.setAmount(count);
    }

    public ItemDropObject(double chance, ItemID id, int count) {
        this.chance = chance;
        item = ItemEditor.createItem(id);
        isCustom = true;
        item.setAmount(count);
    }

    public ItemDropObject(GemstoneType gemType, double chance, int count) {
        this.chance = chance;
        item = GemstoneUtils.createGemstone(gemType, Rarity.COMMON, count);
        isCustom = true;
    }

    public ItemDropObject(GemstoneType gemType, int count) {
        chance = 100;
        item = GemstoneUtils.createGemstone(gemType, Rarity.COMMON, count);
        isCustom = true;
    }

    public ItemDropObject(Material material, int count) {
        chance = 100;
        item = ItemEditor.createItemFromMat(material);
        isCustom = false;
        item.setAmount(count);
    }

    public ItemDropObject(double chance, Material material, int count) {
        this.chance = chance;
        item = ItemEditor.createItemFromMat(material);
        isCustom = false;
        item.setAmount(count);
    }

    public ItemStack getItem() {
        return item;
    }

    public void addDirectly(Player player, double randomPercent) {
        if (randomPercent < chance) {
            player.getInventory().addItem(item);
        }
    }

    public void drop(Location location) {
        location.getWorld().dropItemNaturally(location, item);
    }

    public void drop(Location location, double percent) {
        if (percent < chance) {
            location.getWorld().dropItemNaturally(location, item);
        }
    }

}