package me.notpseudo.revolutionsmp.mobstats;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.*;

public class MobInfo extends BaseEntityStats implements Serializable {

    private UUID holder;
    private UUID owningPlayer;
    private HashMap<UUID, Double> attackers;
    private MobCategory mobCategory;
    private CustomMobType customMobType;
    private String name;
    private int level;

    private double magicResistance;

    public MobInfo(CustomMobType customMobType, int level, UUID holder) {
        super(customMobType, level);
        mobCategory = customMobType.getMobBehavior();
        this.customMobType = customMobType;
        name = customMobType.getName();
        this.level = level;
        magicResistance = customMobType.getMagicResistance();
        owningPlayer = null;
        attackers = new HashMap<>();
        this.holder = holder;
    }

    public MobInfo(CustomMobType customMobType, int level, UUID holder, UUID player) {
        super(customMobType, level);
        mobCategory = customMobType.getMobBehavior();
        this.customMobType = customMobType;
        name = customMobType.getName();
        this.level = level;
        magicResistance = customMobType.getMagicResistance();
        owningPlayer = player;
        this.holder = holder;
        attackers = new HashMap<>();
    }

    public int getLevel() {
        return level;
    }

    public double getMagicResistance() {
        return magicResistance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MobCategory getMobBehavior() {
        return mobCategory;
    }

    public void setMobBehavior(MobCategory mobCategory) {
        this.mobCategory = mobCategory;
    }

    public UUID getOwningPlayer() {
        return owningPlayer;
    }

    public void setOwningPlayer(UUID owningPlayer) {
        this.owningPlayer = owningPlayer;
    }

    public HashMap<UUID, Double> getAttackers() {
        return attackers;
    }

    public void addAttacker(Player player, double damage) {
        if (attackers.containsKey(player.getUniqueId())) {
            attackers.put(player.getUniqueId(), attackers.get(player.getUniqueId()) + damage);
        } else {
            attackers.put(player.getUniqueId(), damage);
        }
    }

    public void setVanillaMobType(EntityType vanillaMobType) {
        List<CustomMobType> customMobTypes = CustomMobType.getCustomMobTypeList(vanillaMobType);
        if (customMobTypes == null) {
            return;
        }
        CustomMobType newCustomType = customMobTypes.get((int) (Math.random() * customMobTypes.size()));
        customMobType = newCustomType;
        super.updateStats(newCustomType, calculateAndSetLevel());
    }

    public CustomMobType getCustomMobType() {
        return customMobType;
    }

    public void setCustomMobType(CustomMobType customMobType) {
        this.customMobType = customMobType;
    }

    public int calculateAndSetLevel() {
        mobCategory = customMobType.getMobBehavior();
        level = (int) (Math.random() * (customMobType.getMaxLevel() - customMobType.getMinLevel() + 1)) + customMobType.getMinLevel();
        return level;
    }

    public void updateMobStats() {
        super.updateStats(customMobType, level);
    }

}
