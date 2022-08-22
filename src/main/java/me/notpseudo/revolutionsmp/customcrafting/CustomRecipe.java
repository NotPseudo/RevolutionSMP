package me.notpseudo.revolutionsmp.customcrafting.items;

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

    public CustomRecipe(NamespacedKey key, ItemStack result) {
        this.KEY = key;
        this.RESULT = result;
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
        return RESULT;
    }

    public boolean matches(@NotNull ItemStack[][] grid) {
        return false;
    }

    public Map<Integer, Integer> getAmountsNeeded(Map<Integer, @NotNull ItemStack> itemMap) {
        return new HashMap<>();
    }

}