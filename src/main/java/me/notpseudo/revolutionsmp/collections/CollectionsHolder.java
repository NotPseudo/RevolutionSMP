package me.notpseudo.revolutionsmp.collections;

import me.notpseudo.revolutionsmp.skills.SkillType;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class CollectionsHolder implements Serializable {

    private final UUID player;
    private ArrayList<CollectionObject> collections;

    public CollectionsHolder(UUID player) {
        this.player = player;
        collections = new ArrayList<>();
        for (CollectionType type : CollectionType.values()) {
            collections.add(new CollectionObject(this, type));
        }
        reorder();
        CollectionUtils.updatePlayerCollections(Bukkit.getPlayer(player), this);
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
        CollectionUtils.updatePlayerCollections(Bukkit.getPlayer(player), this);
        return newCollection;
    }

    public void addCollection(CollectionType type) {
        if (!contains(type)) {
            collections.add(new CollectionObject(this, type));
            reorder();
            CollectionUtils.updatePlayerCollections(Bukkit.getPlayer(player), this);
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
        CollectionUtils.updatePlayerCollections(Bukkit.getPlayer(player), this);
    }

    public int getCollectionsUnlocked(@Nullable SkillType category) {
        ArrayList<CollectionObject> countCollections;
        int count = 0;
        if (category == null) {
            countCollections = collections;
        } else {
            countCollections = getCollectionsFromCategory(category);
        }
        for (CollectionObject collection : countCollections) {
            if (collection.getTotalCollected() > 0) {
                count++;
            }
        }
        return count;
    }

    public double getPercentUnlocked(@Nullable SkillType category) {
        double total;
        if (category == null) {
            total = CollectionType.values().length;
        } else {
            total = getCollectionsFromCategory(category).size();
        }
        return getCollectionsUnlocked(category) / total;
    }

    public int getCollectionsMaxed(@Nullable SkillType category) {
        ArrayList<CollectionObject> countCollections;
        int count = 0;
        if (category == null) {
            countCollections = collections;
        } else {
            countCollections = getCollectionsFromCategory(category);
        }
        for (CollectionObject collection : countCollections) {
            if (collection.getLevel() >= collection.getType().getMaxLevel()) {
                count++;
            }
        }
        return count;
    }

    public double getPercentMaxed(@Nullable SkillType category) {
        double total;
        if (category == null) {
            total = CollectionType.values().length;
        } else {
            total = getCollectionsFromCategory(category).size();
        }
        return getCollectionsMaxed(category) / total;
    }

}