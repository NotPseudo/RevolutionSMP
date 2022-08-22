package me.notpseudo.revolutionsmp.collections;

import me.notpseudo.revolutionsmp.customcrafting.items.ItemID;
import me.notpseudo.revolutionsmp.skills.SkillType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;

public class CollectionObject implements Serializable, Comparable<CollectionObject> {

    private final CollectionsHolder holder;
    private final CollectionType TYPE;
    private final SkillType CATEGORY;
    private ArrayList<Material> vanillaMaterials;
    private ArrayList<ItemID> customMaterials;
    private double totalCollected;
    private int currentCollected;
    private int itemsForNextLevel;
    private int level;

    public CollectionObject(CollectionsHolder holder, CollectionType type) {
        this.holder = holder;
        TYPE = type;
        CATEGORY = type.getCategory();
        vanillaMaterials = new ArrayList<>(type.getVanillaMaterials());
        customMaterials = new ArrayList<>(type.getCustomMaterials());
        totalCollected = 0;
        currentCollected = 0;
        itemsForNextLevel = 0;
        level = 0;
        recalculate();
    }

    public CollectionType getType() {
        return TYPE;
    }

    public SkillType getCategory() {
        return CATEGORY;
    }

    public double getTotalCollected() {
        return totalCollected;
    }

    public int getCurrentCollected() {
        return currentCollected;
    }

    public int getItemsForNextLevel() {
        return itemsForNextLevel;
    }

    public int getLevel() {
        return level;
    }

    public boolean contains(Material material) {
        return vanillaMaterials.contains(material);
    }

    public boolean contains(ItemID itemID) {
        return customMaterials.contains(itemID);
    }

    public void add(int count) {
        totalCollected += count;
        currentCollected += count;
        recalculate();
    }

    public void add(ItemID itemID, int count) {
        if (!contains(itemID)) {
            return;
        }
        totalCollected += CollectionUtils.getCollectionWorth(itemID) * count;
        currentCollected += CollectionUtils.getCollectionWorth(itemID) * count;
        recalculate();
    }

    public void recalculate() {
        while (currentCollected < 0) {
            level--;
            itemsForNextLevel = CollectionUtils.getCountForNextLevel(TYPE, (int) level + 1);
            currentCollected += itemsForNextLevel;
        }
        while (currentCollected >= itemsForNextLevel && level <= TYPE.getMaxLevel()) {
            currentCollected -= itemsForNextLevel;
            level++;
            itemsForNextLevel = CollectionUtils.getCountForNextLevel(TYPE, (int) level + 1);
            Player player = Bukkit.getPlayer(holder.getPlayer());
            if (player != null) {
                sendLevelUpMessage(player);
            }
        }
        itemsForNextLevel = CollectionUtils.getCountForNextLevel(TYPE, (int) level + 1);
    }

    public void addNewMaterial(Material material) {
        if (!contains(material)) {
            vanillaMaterials.add(material);
        }
    }

    public void addNewMaterial(ItemID material) {
        if (!contains(material)) {
            customMaterials.add(material);
        }
    }

    private void sendLevelUpMessage(Player player) {

    }

    @Override
    public int compareTo(@NotNull CollectionObject o) {
        return TYPE.toString().compareTo(o.TYPE.toString());
    }

    public double getPercent() {
        return (double) currentCollected / itemsForNextLevel;
    }

}