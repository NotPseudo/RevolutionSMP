package me.notpseudo.revolutionsmp.customcrafting;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.collections.CollectionObject;
import me.notpseudo.revolutionsmp.collections.CollectionType;
import me.notpseudo.revolutionsmp.collections.CollectionUtils;
import me.notpseudo.revolutionsmp.collections.CollectionsHolder;
import me.notpseudo.revolutionsmp.items.ItemID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CustomCraftingUtils {

    private static final List<CustomRecipe> customRecipes;
    private static final NamespacedKey playerRecipeKey = new NamespacedKey(RevolutionSMP.getPlugin(RevolutionSMP.class), "playerRecipeKey");

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

    @NotNull
    public static PlayerRecipeInfo getRecipeInfo(Player player) {
        PlayerRecipeInfo info = player.getPersistentDataContainer().get(playerRecipeKey, new PlayerRecipeDataType());
        if (info == null) {
            info = new PlayerRecipeInfo(player.getUniqueId());
            updateRecipeInfo(player, info);
        }
        if (info.getPlayer() == null) {
            info = new PlayerRecipeInfo(player.getUniqueId());
            updateRecipeInfo(player, info);
            player.sendMessage(Component.text("There was a slight issue while processing your Unlocked Recipes. If you notice any issues, please contact a staff member", NamedTextColor.YELLOW));
        }
        return info;
    }

    public static void reloadRecipes(Player player) {
        if (player == null) {
            return;
        }
        PlayerRecipeInfo recipeInfo = getRecipeInfo(player);
        CollectionsHolder collectionsHolder = CollectionUtils.getCollectionHolder(player);
        for (CollectionObject collection : collectionsHolder.getCollections()) {
            int collectionLevel = collection.getLevel();
            if (collectionLevel == 0) {
                continue;
            }
            CollectionType type = collection.getType();
            for (int i = 1 ; i <= collectionLevel; i++) {
                for (ItemID unlock : type.getLevelRecipeRewards(i)) {
                    recipeInfo.unlockNewRecipe(unlock.getRecipe());
                }
            }
        }
    }

    public static void updateRecipeInfo(Player player, PlayerRecipeInfo info) {
        if (player == null) {
            return;
        }
        player.getPersistentDataContainer().set(playerRecipeKey, new PlayerRecipeDataType(), info);
    }

}