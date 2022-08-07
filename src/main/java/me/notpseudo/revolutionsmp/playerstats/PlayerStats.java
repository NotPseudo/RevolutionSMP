package me.notpseudo.revolutionsmp.playerstats;

import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.mobstats.BaseEntityStats;

import java.io.Serializable;

public class PlayerStats extends BaseEntityStats implements Serializable {

    private AbilityStats abilityStats;
    private double mana;
    private double healthRegenRate;
    private double manaRegenRate;
    private double absorption;
    private FishingStats fishingStats;
    private MiningStats miningStats;
    private GatheringStats gatheringStats;
    private LuckStats luckStats;

    public PlayerStats() {
        super(100, 0, 100, 0, 30, 50, 0, 0);
        abilityStats = new AbilityStats(0, 100);
        mana = 100;
        healthRegenRate = 1;
        manaRegenRate = 1;
        absorption = 0;
        fishingStats = new FishingStats(20, 0);
        miningStats = new MiningStats(0, 0, 0);
        gatheringStats = new GatheringStats(0, 0);
        luckStats = new LuckStats(0, 0);
    }

    public double getIntelligence() {
        return abilityStats.getStatValue(StatType.INTELLIGENCE);
    }

    public void setIntelligence(double intelligence) {
        abilityStats.setStatValue(StatType.INTELLIGENCE, intelligence);
    }

    public double getAbilityDamage() {
        return abilityStats.getStatValue(StatType.ABILITY_DAMAGE);
    }

    public void setAbilityDamage(double abilityDamage) {
        abilityStats.setStatValue(StatType.ABILITY_DAMAGE, abilityDamage);
    }

    public double getMana() {
        return mana;
    }

    public void setMana(double mana) {
        this.mana = mana;
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

    public double getAbsorption() {
        return absorption;
    }

    public void setAbsorption(double absorption) {
        this.absorption = absorption;
    }

    public double getSeaCreatureChance() {
        return fishingStats.getStatValue(StatType.SEA_CREATURE_CHANCE);
    }

    public void setSeaCreatureChance(double seaCreatureChance) {
        fishingStats.setStatValue(StatType.SEA_CREATURE_CHANCE, seaCreatureChance);
    }

    public double getMiningSpeed() {
        return miningStats.getStatValue(StatType.MINING_SPEED);
    }

    public void setMiningSpeed(double miningSpeed) {
        miningStats.setStatValue(StatType.MINING_SPEED, miningSpeed);
    }

    public double getMiningFortune() {
        return miningStats.getStatValue(StatType.MINING_FORTUNE);
    }

    public void setMiningFortune(double miningFortune) {
        miningStats.setStatValue(StatType.MINING_FORTUNE, miningFortune);
    }

    public double getPristine() {
        return miningStats.getStatValue(StatType.PRISTINE);
    }

    public void setPristine(double pristine) {
        miningStats.setStatValue(StatType.PRISTINE, pristine);
    }

    public double getPurity() {
        return miningStats.getStatValue(StatType.PURITY);
    }

    public void setPurity(double purity) {
        miningStats.setStatValue(StatType.PURITY, purity);
    }

    public double getFarmingFortune() {
        return gatheringStats.getStatValue(StatType.FARMING_FORTUNE);
    }

    public void setFarmingFortune(double farmingFortune) {
        gatheringStats.setStatValue(StatType.FARMING_FORTUNE, farmingFortune);
    }

    public double getForagingFortune() {
        return gatheringStats.getStatValue(StatType.FORAGING_FORTUNE);
    }

    public void setForagingFortune(double foragingFortune) {
        gatheringStats.setStatValue(StatType.FORAGING_FORTUNE, foragingFortune);
    }

    public double getMagicFind() {
        return luckStats.getStatValue(StatType.MAGIC_FIND);
    }

    public void setMagicFind(double magicFind) {
        luckStats.setStatValue(StatType.MAGIC_FIND, magicFind);
    }

    public double getPetLuck() {
        return luckStats.getStatValue(StatType.PET_LUCK);
    }

    public void setPetLuck(double petLuck) {
        luckStats.setStatValue(StatType.PET_LUCK, petLuck);
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