package me.notpseudo.revolutionsmp.playerstats;

import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.mobstats.BaseEntityStats;

import java.io.Serializable;

public class PlayerStats extends BaseEntityStats implements Serializable {

    private double attackSpeed;
    private double ferocity;
    private AbilityStats abilityStats;
    private double mana;
    private double trueDefense;
    private double healthRegenRate;
    private double manaRegenRate;
    private double defenseMultiplier;
    private double speedMultiplier;
    private double addSpeed;
    private double damageTakenMultiplier;
    private double absorption;
    private FishingStats fishingStats;
    private MiningStats miningStats;
    private GatheringStats gatheringStats;
    private LuckStats luckStats;

    public PlayerStats() {
        super(100, 0, 100, 0, 30, 50);
        attackSpeed = 0;
        ferocity = 0;
        abilityStats = new AbilityStats(0, 100);
        mana = 100;
        healthRegenRate = 1;
        manaRegenRate = 1;
        defenseMultiplier = 1;
        speedMultiplier = 1;
        addSpeed = 0;
        damageTakenMultiplier = 1;
        absorption = 0;
        fishingStats = new FishingStats(20, 0);
        miningStats = new MiningStats(0, 0, 0);
        gatheringStats = new GatheringStats(0, 0);
        luckStats = new LuckStats(0, 0);
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public double getFerocity() {
        return ferocity;
    }

    public void setFerocity(double ferocity) {
        this.ferocity = ferocity;
    }

    public double getIntelligence() {
        return abilityStats.getIntelligence();
    }

    public void setIntelligence(double intelligence) {
        abilityStats.setIntelligence(intelligence);
    }

    public double getAbilityDamage() {
        return abilityStats.getAbilityDamage();
    }

    public void setAbilityDamage(double abilityDamage) {
        abilityStats.setAbilityDamage(abilityDamage);
    }

    public double getMana() {
        return mana;
    }

    public void setMana(double mana) {
        this.mana = mana;
    }

    public double getTrueDefense() {
        return trueDefense;
    }

    public void setTrueDefense(double trueDefense) {
        this.trueDefense = trueDefense;
    }

    public double getHealthRegenRate() {
        return healthRegenRate;
    }

    public void setHealthRegenRate(double healthRegenRate) {
        this.healthRegenRate = healthRegenRate;
    }

    public double getManaRegenRate() {
        return manaRegenRate;
    }

    public void setManaRegenRate(double manaRegenRate) {
        this.manaRegenRate = manaRegenRate;
    }

    public double getDefenseMultiplier() {
        return defenseMultiplier;
    }

    public void setDefenseMultiplier(double defenseMultiplier) {
        this.defenseMultiplier = defenseMultiplier;
    }

    public double getSpeedMultiplier() {
        return speedMultiplier;
    }

    public void setSpeedMultiplier(double speedMultiplier) {
        this.speedMultiplier = speedMultiplier;
    }

    public double getAddSpeed() {
        return addSpeed;
    }

    public void setAddSpeed(double addSpeed) {
        this.addSpeed = addSpeed;
    }

    public double getDamageTakenMultiplier() {
        return damageTakenMultiplier;
    }

    public void setDamageTakenMultiplier(double damageTakenMultiplier) {
        this.damageTakenMultiplier = damageTakenMultiplier;
    }

    public double getAbsorption() {
        return absorption;
    }

    public void setAbsorption(double absorption) {
        this.absorption = absorption;
    }

    public double getSeaCreatureChance() {
        return fishingStats.getSeaCreatureChance();
    }

    public void setSeaCreatureChance(double seaCreatureChance) {
        fishingStats.setSeaCreatureChance(seaCreatureChance);
    }

    public double getMiningSpeed() {
        return miningStats.getMiningSpeed();
    }

    public void setMiningSpeed(double miningSpeed) {
        miningStats.setMiningSpeed(miningSpeed);
    }

    public double getMiningFortune() {
        return miningStats.getMiningFortune();
    }

    public void setMiningFortune(double miningFortune) {
        miningStats.setMiningFortune(miningFortune);
    }

    public double getPristine() {
        return miningStats.getPristine();
    }

    public void setPristine(double pristine) {
        miningStats.setPristine(pristine);
    }

    public double getFarmingFortune() {
        return gatheringStats.getFarmingFortune();
    }

    public void setFarmingFortune(double farmingFortune) {
        gatheringStats.setFarmingFortune(farmingFortune);
    }

    public double getForagingFortune() {
        return gatheringStats.getForagingFortune();
    }

    public void setForagingFortune(double foragingFortune) {
        gatheringStats.setForagingFortune(foragingFortune);
    }

    public double getMagicFind() {
        return luckStats.getMagicFind();
    }

    public void setMagicFind(double magicFind) {
        luckStats.setMagicFind(magicFind);
    }

    public double getPetLuck() {
        return luckStats.getPetLuck();
    }

    public void setPetLuck(double petLuck) {
        luckStats.setPetLuck(petLuck);
    }

    public void setAbilityStats(AbilityStats abilityStats) {
        this.abilityStats = abilityStats;
    }

    public void setFishingStats(FishingStats fishingStats) {
        this.fishingStats = fishingStats;
    }

    public void setMiningStats(MiningStats miningStats) {
        this.miningStats = miningStats;
    }

    public void setGatheringStats(GatheringStats gatheringStats) {
        this.gatheringStats = gatheringStats;
    }

    public void setLuckStats(LuckStats luckStats) {
        this.luckStats = luckStats;
    }
}