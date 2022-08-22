package me.notpseudo.revolutionsmp.customcrafting;

import me.notpseudo.revolutionsmp.customcrafting.CustomRecipe;
import me.notpseudo.revolutionsmp.items.ItemID;

import java.util.ArrayList;
import java.util.List;

public class CustomCraftingUtils {

    private static final List<CustomRecipe> customRecipes;

    public static List<CustomRecipe> getCustomRecipes() {
        return customRecipes;
    }

    static {
        customRecipes = new ArrayList<>();
        for (ItemID item : ItemID.values()) {
            if (item.getRecipe() != null) {
                customRecipes.add(item.getRecipe());
            }
        }
    }

}