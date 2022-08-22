package me.notpseudo.revolutionsmp.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CustomShapelessRecipe extends CustomRecipe {

    private List<CustomRecipeChoice> choices;

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
        choices.add(new CustomRecipeChoice.ItemStackChoice(ingredient));
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
        choices.add(new CustomRecipeChoice.ItemIDChoice(id, count));
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
        for (CustomRecipeChoice choice : choices) {
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

}