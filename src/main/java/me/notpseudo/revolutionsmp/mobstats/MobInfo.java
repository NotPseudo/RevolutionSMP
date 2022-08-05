package me.notpseudo.revolutionsmp.mobstats;

import org.bukkit.entity.EntityType;

import java.io.Serializable;

public class MobInfo extends BaseEntityStats implements Serializable {

    private MobBehavior mobBehavior;
    private EntityType vanillaMobType;
    private CustomMobType customMobType;
    private String name;
    private int level;

    private double magicResistance;

    public MobInfo(CustomMobType customMobType, EntityType vanillaMobType, int level) {
        super(customMobType, level);
        mobBehavior = customMobType.getMobBehavior();
        this.vanillaMobType = vanillaMobType;
        this.customMobType = customMobType;
        name = customMobType.getName();
        this.level = level;
        magicResistance = customMobType.getMagicResistance();
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

    public MobBehavior getMobBehavior() {
        return mobBehavior;
    }

    public void setMobBehavior(MobBehavior mobBehavior) {
        this.mobBehavior = mobBehavior;
    }

    public EntityType getVanillaMobType() {
        return vanillaMobType;
    }

    public void setVanillaMobType(EntityType vanillaMobType) {
        this.vanillaMobType = vanillaMobType;
        CustomMobType[] customMobTypes = CustomMobType.getCustomMobTypeList(vanillaMobType);
        if(customMobTypes == null) return;
        CustomMobType newCustomType = customMobTypes[(int) (Math.random() * customMobTypes.length)];
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
        mobBehavior = customMobType.getMobBehavior();
        level = (int) (Math.random() * (mobBehavior.getHighestLevel() - mobBehavior.getLowestLevel() + 1)) + mobBehavior.getLowestLevel();
        return level;
    }

    public void updateMobStats() {
        super.updateStats(customMobType, level);
    }

}
