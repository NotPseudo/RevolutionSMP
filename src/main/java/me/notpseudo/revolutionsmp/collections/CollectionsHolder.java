package me.notpseudo.revolutionsmp.collections;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class CollectionsHolder implements Serializable {

    private ArrayList<CollectionObject> collections;

    public CollectionsHolder() {
        collections = new ArrayList<>();
        for (CollectionType type : CollectionType.values()) {
            collections.add(new CollectionObject(type));
        }
        reorder();
    }

    public CollectionObject getCollection(CollectionType type) {
        for (CollectionObject collection : collections) {
            if (collection.getType() == type) {
                return collection;
            }
        }
        return null;
    }

    public void addCollection(CollectionType type) {
        if (!contains(type)) {
            collections.add(new CollectionObject(type));
        }
        reorder();
    }

    public boolean contains(CollectionType type) {
        for (CollectionObject collection : collections) {
            if (collection.getType() == type) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<CollectionObject> getCollectionsFromCategory(CollectionCategory category) {
        return new ArrayList<>(collections.stream().filter(c -> c.getType().getCategory() == category).toList());
    }

    public void reorder() {
        ArrayList<CollectionObject> collectionsOrdered = new ArrayList<>();
        for (CollectionCategory category : CollectionCategory.values()) {
            ArrayList<CollectionObject> categoryCollections = getCollectionsFromCategory(category);
            Collections.sort(categoryCollections);
            collections.addAll(categoryCollections);
        }
        collections = collectionsOrdered;
    }

}