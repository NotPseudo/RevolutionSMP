package me.notpseudo.revolutionsmp.mobstats;

import me.notpseudo.revolutionsmp.listeners.MobListeners;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
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
    private int hits;

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
        hits = 0;
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
        hits = 0;
    }

    public int getLevel() {
        return level;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
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
        Entity holding = Bukkit.getEntity(holder);
        if (holding instanceof LivingEntity living) {
            MobListeners.updateMobInfo(living, this);
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
