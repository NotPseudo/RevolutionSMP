package me.notpseudo.revolutionsmp.customcrafting.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomShapelessRecipe extends me.notpseudo.revolutionsmp.customcrafting.items.CustomRecipe {

    private List<me.notpseudo.revolutionsmp.customcrafting.items.CustomRecipeChoice> choices;

    public CustomShapelessRecipe(NamespacedKey key, ItemStack result) {
        super(key, result);
        choices = new ArrayList<>();
    }

    /**
     * Adds a material to the ingredients
     *
     * @param ingredient The material to add to the ingredients
     */
    public void addIngredient(Material ingredient) {
        addIngredient(ingredient, 1);
    }

    /**
     * Adds a material with a specified required amount to the ingredients
     *
     * @param ingredient The material to add to the ingredients
     * @param count      The amount of material needed for a slot
     */
    public void addIngredient(Material ingredient, int count) {
        addIngredient(new ItemStack(ingredient, count));
    }

    /**
     * Adds a material with a specified required amount to the ingredients
     *
     * @param ingredient The ItemStack containing the material and required amount to add
     */
    public void addIngredient(ItemStack ingredient) {
        if (choices.size() >= 9) {
            return;
        }
        choices.add(new me.notpseudo.revolutionsmp.customcrafting.items.CustomRecipeChoice.ItemStackChoice(ingredient));
    }

    /**
     * Adds a custom material to the ingredients
     *
     * @param id The ItemID material to add to the ingredients
     */
    public void addIngredient(ItemID id) {
        addIngredient(id, 1);
    }

    /**
     * Adds a custom material with a specified required amount to the ingredients
     *
     * @param id    The ItemID material to add to the ingredients
     * @param count The amount of material needed for a slot
     */
    public void addIngredient(ItemID id, int count) {
        if (choices.size() >= 9) {
            return;
        }
        choices.add(new me.notpseudo.revolutionsmp.customcrafting.items.CustomRecipeChoice.ItemIDChoice(id, count));
    }

    @Override
    public boolean matches(@NotNull ItemStack[][] grid) {
        List<ItemStack> actualItems = new ArrayList<>();
        for (ItemStack[] row : grid) {
            for (ItemStack item : row) {
                if (item != null) {
                    actualItems.add(item);
                }
            }
        }
        if (actualItems.size() != choices.size()) {
            return false;
        }
        for (me.notpseudo.revolutionsmp.customcrafting.items.CustomRecipeChoice choice : choices) {
            boolean found = false;
            for (ItemStack item : actualItems) {
                if (choice.test(item)) {
                    actualItems.remove(item);
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a Map of index to amount needed for crafting grid slots
     * <b>This method should not be called until matches() has been evaluated to true</b>
     *
     * @param itemMap The Map of crafting inventory indexes to Item
     * @return A Map of crafting grid indexes to amount needed of the material in that slot
     */
    @Override
    public Map<Integer, Integer> getAmountsNeeded(Map<Integer, @NotNull ItemStack> itemMap) {
        Map<Integer, Integer> amounts = new HashMap<>();
        for (me.notpseudo.revolutionsmp.customcrafting.items.CustomRecipeChoice choice : choices) {
            for (Integer index : itemMap.keySet()) {
                if (choice.test(itemMap.get(index))) {
                    amounts.put(index, choice.getCount());
                    itemMap.remove(index);
                    break;
                }
            }
        }
        return amounts;
    }

}