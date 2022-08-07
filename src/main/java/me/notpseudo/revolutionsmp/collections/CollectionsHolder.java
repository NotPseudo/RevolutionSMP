package me.notpseudo.revolutionsmp.collections;

import me.notpseudo.revolutionsmp.skills.SkillType;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class CollectionsHolder implements Serializable {

    private final static NamespacedKey collectionsKey = CollectionUtils.getCollectionsKey();
    private final UUID player;
    private ArrayList<CollectionObject> collections;

    public CollectionsHolder(UUID player) {
        this.player = player;
        collections = new ArrayList<>();
        for (CollectionType type : CollectionType.values()) {
            collections.add(new CollectionObject(this, type));
        }
        reorder();
        Bukkit.getPlayer(player).getPersistentDataContainer().set(collectionsKey, new CollectionsDataType(), this);
    }

    public UUID getPlayer() {
        return player;
    }

    @NotNull
    public CollectionObject getCollection(CollectionType type) {
        for (CollectionObject collection : collections) {
            if (collection.getType() == type) {
                return collection;
            }
        }
        CollectionObject newCollection = new CollectionObject(this, type);
        collections.add(newCollection);
        Bukkit.getPlayer(player).getPersistentDataContainer().set(collectionsKey, new CollectionsDataType(), this);
        return newCollection;
    }

    public void addCollection(CollectionType type) {
        if (!contains(type)) {
            collections.add(new CollectionObject(this, type));
            reorder();
            Bukkit.getPlayer(player).getPersistentDataContainer().set(collectionsKey, new CollectionsDataType(), this);
        }
    }

    public boolean contains(CollectionType type) {
        for (CollectionObject collection : collections) {
            if (collection.getType() == type) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<CollectionObject> getCollectionsFromCategory(SkillType category) {
        return new ArrayList<>(collections.stream().filter(c -> c.getType().getCategory() == category).toList());
    }

    public void reorder() {
        ArrayList<CollectionObject> collectionsOrdered = new ArrayList<>();
        for (SkillType category : SkillType.values()) {
            ArrayList<CollectionObject> categoryCollections = getCollectionsFromCategory(category);
            Collections.sort(categoryCollections);
            collections.addAll(categoryCollections);
        }
        collections = collectionsOrdered;
    }

    public void addCollectionAmount(CollectionType type, int amount) {
        getCollection(type).add(amount);
    }

}