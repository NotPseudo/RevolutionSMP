package me.notpseudo.revolutionsmp.customcrafting;

import org.bukkit.Bukkit;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PlayerRecipeInfo implements Serializable {

    private final UUID PLAYER;
    private Set<String> unlockedRecipes;

    public PlayerRecipeInfo() {
        PLAYER = null;
        unlockedRecipes = new HashSet<>();
    }

    public PlayerRecipeInfo(UUID player) {
        PLAYER = player;
        unlockedRecipes = new HashSet<>();
        CustomCraftingUtils.updateRecipeInfo(Bukkit.getPlayer(PLAYER), this);
    }

    public UUID getPlayer() {
        return PLAYER;
    }

    public boolean hasUnlocked(CustomRecipe recipe) {
        return unlockedRecipes.contains(recipe.getKey().getKey());
    }

    public boolean unlockNewRecipe(CustomRecipe recipe) {
        if (recipe == null) {
            return false;
        }
        boolean added = unlockedRecipes.add(recipe.getKey().getKey());
        if (added) {
            CustomCraftingUtils.updateRecipeInfo(Bukkit.getPlayer(PLAYER), this);
        }
        return added;
    }

}