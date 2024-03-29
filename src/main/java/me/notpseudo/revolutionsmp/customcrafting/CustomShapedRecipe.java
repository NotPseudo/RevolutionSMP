package me.notpseudo.revolutionsmp.customcrafting;

import me.notpseudo.revolutionsmp.items.ItemID;
import org.apache.commons.lang3.Validate;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a CustomRecipe used in more complicated custom item crafting
 * <p>
 * SOURCE:
 * 3ricL (<a href="https://www.spigotmc.org/members/3ricl.951517/">...</a>)
 * and
 * _Crytox_ (<a href="https://www.spigotmc.org/members/_crytox_.277354/">...</a>)
 * <p>
 * Modified by NotPseudo for RevolutionSMP plugin
 */
public class CustomShapedRecipe extends CustomRecipe {

    private String[] rows;
    private Map<Character, CustomRecipeChoice> ingredients = new HashMap<>();

    /**
     * Creates a new CustomRecipe with the specified key and result
     *
     * @param key    Unique identifier to differentiate this recipe from others
     * @param result The result of crafting the recipe
     */
    public CustomShapedRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result) {
        super(key, result);
    }

    /**
     * Sets the shape of the recipe
     * Each slot of the recipe can represent a different character(i.e different ingredient)
     * Up to a 3x3 field (with this code); change "(shape.length > 0) && (shape.length < 4)" and  "(row.length() > 0) && (row.length() < 4)," for a bigger/smaller field.
     * <p>
     * Needed to be called after the constructor and before setting the ingredients
     *
     * @param shape The shape of the recipe. Three rows of three characters
     */
    public void setShape(@NotNull String... shape) {
        Validate.notNull(shape, "Must provide a shape");
        Validate.isTrue((shape.length > 0) && (shape.length < 4), "Crafting recipes should be 1-3 rows, not ", shape.length);
        int lastLen = -1;
        for (String row : shape) {
            Validate.notNull(row, "Shape cannot have null rows");
            Validate.isTrue((row.length() > 0) && (row.length() < 4), "Crafting rows should be 1-3 characters, not ", row.length());
            Validate.isTrue((lastLen == -1) || (lastLen == row.length()), "Crafting recipes must be rectangular");
            lastLen = row.length();
        }

        this.rows = new String[shape.length];
        for (int i = 0; i < shape.length; i++) {
            this.rows[i] = shape[i];
        }

        HashMap<Character, CustomRecipeChoice> newIngredients = new HashMap<>();
        for (String row : shape) {
            for (Character c : row.toCharArray()) {
                newIngredients.put(c, this.ingredients.get(c));
            }
        }
        newIngredients.put(' ', null);
        this.ingredients = newIngredients;
    }

    /**
     * Sets the ingredient that a character represents
     *
     * @param key        The character in the recipe shape
     * @param ingredient The Material the character represents
     */
    public void setIngredient(char key, Material ingredient) {
        setIngredient(key, ingredient, 1);
    }

    /**
     * Sets the ingredient that a character represents
     *
     * @param key        The character in the recipe shape
     * @param ingredient The Material the character represents
     * @param count      The amount of material this ingredient slot needs
     */
    public void setIngredient(char key, Material ingredient, int count) {
        setIngredient(key, new ItemStack(ingredient, count));
    }

    /**
     * Sets multiple materials to the slot the character represents
     *
     * @param key         The character in the recipe shape
     * @param ingredients The materials to set to the character
     */
    public void setIngredient(char key, Material... ingredients) {
        setIngredient(key, 1, ingredients);
    }

    /**
     * Sets multiple materials to the slot the character represents with a required amount
     *
     * @param key         The character in the recipe shape
     * @param count       The required amount of this material
     * @param ingredients The materials to set to the character
     */
    public void setIngredient(char key, int count, Material... ingredients) {
        Validate.isTrue(this.ingredients.containsKey(key), "Symbol does not appear in the shape:", key);
        this.ingredients.put(key, new CustomRecipeChoice.ItemStackChoice(List.of(ingredients), count));
    }

    /**
     * Sets the ingredient that a character represents
     *
     * @param key        The character in the recipe shape
     * @param ingredient The ItemStack the character represents
     */
    public void setIngredient(char key, ItemStack ingredient) {
        Validate.isTrue(this.ingredients.containsKey(key), "Symbol does not appear in the shape:", key);
        this.ingredients.put(key, new CustomRecipeChoice.ItemStackChoice(ingredient));
    }

    /**
     * Sets the ingredient that a character represents
     *
     * @param key The character in the recipe shape
     * @param id  The ItemID material the character represents
     */
    public void setIngredient(char key, ItemID id) {
        setIngredient(key, id, 1);
    }

    /**
     * Sets the ingredient that a character represents
     *
     * @param key   The character in the recipe shape
     * @param id    The ItemID the character represents
     * @param count The amount of material this ingredient slot needs
     */
    public void setIngredient(char key, ItemID id, int count) {
        Validate.isTrue(this.ingredients.containsKey(key), "Symbol does not appear in the shape:", key);
        this.ingredients.put(key, new CustomRecipeChoice.ItemIDChoice(id, count));
    }

    /**
     * Sets multiple custom materials to the slot the character represents
     *
     * @param key         The character in the recipe shape
     * @param ingredients The custom materials to set to the character
     */
    public void setIngredient(char key, ItemID... ingredients) {
        setIngredient(key, 1, ingredients);
    }

    /**
     * Sets multiple custom materials to the slot the character represents with a required amount
     *
     * @param key         The character in the recipe shape
     * @param count       The required amount of this material
     * @param ingredients The custom  materials to set to the character
     */
    public void setIngredient(char key, int count, ItemID... ingredients) {
        Validate.isTrue(this.ingredients.containsKey(key), "Symbol does not appear in the shape:", key);
        this.ingredients.put(key, new CustomRecipeChoice.ItemIDChoice(List.of(ingredients), count));
    }

    /**
     * Checks if the matrix of ItemStacks matches the ingredients of this recipe
     * <p>
     * SOURCE:
     * 3ricL (<a href="https://www.spigotmc.org/members/3ricl.951517/">...</a>)
     *
     * @param grid 3-by-3 2D Array of ItemStacks representing the crafting grid
     * @return true if the crafting matrix matches the ingredients of this recipe
     */
    @Override
    public boolean matches(@NotNull ItemStack[][] grid) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                CustomRecipeChoice recipeChoice = this.ingredients.get(this.rows[(i)].charAt(j));
                ItemStack itemInGrid = grid[i][j];
                if (recipeChoice == null) {
                    if (itemInGrid != null) {
                        return false;
                    }
                } else {
                    if (itemInGrid == null) {
                        return false;
                    }
                    if (!recipeChoice.test(itemInGrid)) {
                        return false;
                    }
                }

            }
        }
        return true;
    }

    /*
    @Nullable
    public ItemStack getItemStack(int row, int column) {
        if (hasItemStack(row, column)) {
            return this.ingredients.get(rows[(row - 1)].charAt(column - 1));
        } else {
            return null;
        }

    }
     */

    /**
     * Returns the amount of material needed of the ingredient at the specified row and colum if there is one
     *
     * @param row    The row an ingredient may be located in
     * @param column The column the ingredient may be located in
     * @return The amount of the ingredient at the row and column needed. If there is no ingredient, this method returns 0
     */
    public int getAmountNeeded(int row, int column) {
        CustomRecipeChoice choice = this.ingredients.get(rows[(row - 1)].charAt(column - 1));
        if (choice != null) {
            return choice.getCount();
        }
        return 0;
    }

    /**
     * Returns a Map of index to amount needed for crafting grid slots
     *
     * @param itemMap The Map of crafting inventory indexes to Item
     * @return A Map of crafting grid indexes to amount needed of the material in that slot
     */
    @Override
    public Map<Integer, Integer> getAmountsNeeded(Map<Integer, @NotNull ItemStack> itemMap) {
        Map<Integer, Integer> amounts = new HashMap<>();
        for (Integer index : itemMap.keySet()) {
            int row = index / 9, column = index % 9;
            if (row >= 4 || column >= 4) {
                amounts.put(index, 0);
            } else {
                amounts.put(index, getAmountNeeded(row, column));
            }
        }
        return amounts;
    }

    /**
     * Checks if there is an ingredient at the row and column
     *
     * @param row    The row where an ingredient may be
     * @param column The column where an ingredient may be
     * @return true if there is an ingredient at the row and column
     */
    public boolean hasItemStack(int row, int column) {
        CustomRecipeChoice choice = this.ingredients.get(rows[(row - 1)].charAt(column - 1));
        return choice != null;
    }

}