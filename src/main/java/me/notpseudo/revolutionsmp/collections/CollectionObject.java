package me.notpseudo.revolutionsmp.collections;

import me.notpseudo.revolutionsmp.customcrafting.CustomCraftingUtils;
import me.notpseudo.revolutionsmp.customcrafting.PlayerRecipeInfo;
import me.notpseudo.revolutionsmp.items.ItemID;
import me.notpseudo.revolutionsmp.skills.SkillType;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;

public class CollectionObject implements Serializable, Comparable<CollectionObject> {

    private final CollectionsHolder holder;
    private final CollectionType TYPE;
    private final SkillType CATEGORY;
    private double totalCollected;
    private int currentCollected;
    private int itemsForNextLevel;
    private int level;

    public CollectionObject(CollectionsHolder holder, CollectionType type) {
        this.holder = holder;
        TYPE = type;
        CATEGORY = type.getCategory();
        totalCollected = 0;
        currentCollected = 0;
        itemsForNextLevel = CollectionUtils.getCountForNextLevel(TYPE, 1);
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
        return TYPE.getVanillaMaterials().contains(material);
    }

    public boolean contains(ItemID itemID) {
        return TYPE.getCustomMaterials().contains(itemID);
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
            itemsForNextLevel = CollectionUtils.getCountForNextLevel(TYPE, level + 1);
            currentCollected += itemsForNextLevel;
        }
        while (currentCollected >= itemsForNextLevel && level <= TYPE.getMaxLevel()) {
            currentCollected -= itemsForNextLevel;
            level++;
            itemsForNextLevel = CollectionUtils.getCountForNextLevel(TYPE, level + 1);
            Player player = Bukkit.getPlayer(holder.getPlayer());
            if (player != null) {
                sendLevelUpMessage(player);
            }
        }
        itemsForNextLevel = CollectionUtils.getCountForNextLevel(TYPE, level + 1);
    }

    private void sendLevelUpMessage(Player player) {
        player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        for (Component line : TYPE.getLevelUpMessage(level)) {
            player.sendMessage(line);
        }
        PlayerRecipeInfo info = CustomCraftingUtils.getRecipeInfo(player);
        for (ItemID item : TYPE.getLevelRecipeRewards(level)) {
            if (item.getRecipe() != null) {
                info.unlockNewRecipe(item.getRecipe());
            }
        }
        CustomCraftingUtils.updateRecipeInfo(player, info);
    }

    @Override
    public int compareTo(@NotNull CollectionObject o) {
        return TYPE.toString().compareTo(o.TYPE.toString());
    }

    public double getPercent() {
        return (double) currentCollected / itemsForNextLevel;
    }

}