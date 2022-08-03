package me.notpseudo.revolutionsmp.collections;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.items.ItemID;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

public class CollectionUtils {

    private static final NamespacedKey collectionsKey = new NamespacedKey(RevolutionSMP.getPlugin(RevolutionSMP.class), "collectionsKey");

    public static NamespacedKey getCollectionsKey() {
        return collectionsKey;
    }

    public static int getCountForNextLevel(CollectionType type, int nextLevel) {
        return 0;
    }

    public static int getCollectionWorth(Material material) {
        return 1;
    }

    public static int getCollectionWorth(ItemID itemID) {
        return 1;
    }

}