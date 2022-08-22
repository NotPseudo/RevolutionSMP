package me.notpseudo.revolutionsmp.customcrafting;

import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.items.ItemType;
import me.notpseudo.revolutionsmp.itemstats.ItemInfo;
import me.notpseudo.revolutionsmp.itemstats.ItemInfoDataType;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a custom crafting recipe
 *
 * @author NotPseudo
 */
public class CustomRecipe {

    private final NamespacedKey KEY;
    private final ItemStack RESULT;
    private final ItemInfo RESULT_INFO;

    public CustomRecipe(NamespacedKey key, ItemStack result) {
        this.KEY = key;
        this.RESULT = result;
        ItemInfo info = ItemEditor.getInfo(result);
        if (info == null) {
            RESULT_INFO = ItemEditor.createMetaFromMat(result.getItemMeta(), result.getType()).getPersistentDataContainer().get(ItemEditor.getItemKey(), new ItemInfoDataType());
        } else {
            RESULT_INFO = info;
        }
    }

    /**
     * Returns the unique identifier key assigned to this recipe
     *
     * @return The NamedspacedKey identifier for this recipe
     */
    public NamespacedKey getKey() {
        return KEY;
    }

    /**
     * Returns the result of crafting this recipe
     *
     * @return The ItemStack result of crafting this recipe
     */
    public ItemStack getResult() {
        return new ItemStack(RESULT);
    }

    public ItemStack getResult(ItemInfo old) {
        if (old == null) {
            return getResult();
        }
        if (old.getItemType() == ItemType.VANILLA_ITEM) {
            return getResult();
        }
        ItemStack result = new ItemStack(RESULT);
        ItemInfo resultInfo = ItemEditor.getInfo(result);
        if (resultInfo == null) {
            return getResult();
        }
        if (old.getItemID().getUpgradedVersion() != null && old.getItemID().getUpgradedVersion().contains(RESULT_INFO.getItemID())) {
            resultInfo.upgradeFrom(old);
        } else {
            resultInfo.copy(old);
        }
        ItemEditor.updateItemInfo(result, resultInfo);
        return result;
    }

    public boolean matches(@NotNull ItemStack[][] grid) {
        return false;
    }

    public Map<Integer, Integer> getAmountsNeeded(Map<Integer, @NotNull ItemStack> itemMap) {
        return new HashMap<>();
    }

}