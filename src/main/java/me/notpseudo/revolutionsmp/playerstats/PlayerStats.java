package me.notpseudo.revolutionsmp.playerstats;

import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.mobstats.BaseEntityStats;

import java.io.Serializable;

public class PlayerStats extends BaseEntityStats implements Serializable {

    private double mana;
    private double absorption;
    private AbilityStats abilityStats;
    private FishingStats fishingStats;
    private MiningStats miningStats;
    private GatheringStats gatheringStats;
    private LuckStats luckStats;
    private AbilityStats abilityStatsMult;
    private FishingStats fishingStatsMult;
    private MiningStats miningStatsMult;
    private GatheringStats gatheringStatsMult;
    private LuckStats luckStatsMult;

    private WisdomStats wisdomStats;

    private RegenStats regenStats;

    public PlayerStats() {
        super(100, 0, 100, 0, 30, 50, 0, 0);
        abilityStats = new AbilityStats(0, 100);
        mana = 100;
        absorption = 0;
        fishingStats = new FishingStats(20, 0);
        miningStats = new MiningStats(0, 0, 0);
        gatheringStats = new GatheringStats(0, 0);
        luckStats = new LuckStats(0, 0);
        abilityStatsMult = AbilityStats.createMult();
        fishingStatsMult = FishingStats.createMult();
        miningStatsMult = MiningStats.createMult();
        gatheringStatsMult = GatheringStats.createMult();
        luckStatsMult = LuckStats.createMult();
        wisdomStats = WisdomStats.createZero();
        regenStats = new RegenStats(100, 100, 100, 100);
    }

    public double getMana() {
        return mana;
    }

    public void setMana(double mana) {
        this.mana = mana;
    }

    public double getAbsorption() {
        return absorption;
    }

    public void setAbsorption(double absorption) {
        this.absorption = absorption;
    }

    @Override
    public double getStatValue(StatType type) {
        return switch (type.getStatCategory()) {
            case COMBAT, ARMOR -> super.getStatValue(type);
            case INTELLIGENCE -> abilityStats.getStatValue(type);
            case FISHING -> fishingStats.getStatValue(type);
            case MINING -> miningStats.getStatValue(type);
            case GATHERING -> gatheringStats.getStatValue(type);
            case LUCK -> luckStats.getStatValue(type);
            case WISDOM -> wisdomStats.getStatValue(type);
            case REGEN -> regenStats.getStatValue(type);
        };
    }

    @Override
    public void setStatValue(StatType type, double value) {
        switch (type.getStatCategory()) {
            case COMBAT, ARMOR -> super.setStatValue(type, value);
            case INTELLIGENCE -> abilityStats.setStatValue(type, value);
            case FISHING -> fishingStats.setStatValue(type, value);
            case MINING -> miningStats.setStatValue(type, value);
            case GATHERING -> gatheringStats.setStatValue(type, value);
            case LUCK -> luckStats.setStatValue(type, value);
            case WISDOM -> wisdomStats.setStatValue(type, value);
            case REGEN -> regenStats.setStatValue(type, value);
        }
    }

    @Override
    public double getStatMultValue(StatType type) {
        return switch (type.getStatCategory()) {
            case COMBAT, ARMOR -> super.getStatMultValue(type);
            case INTELLIGENCE -> abilityStatsMult.getStatValue(type);
            case FISHING -> fishingStatsMult.getStatValue(type);
            case MINING -> miningStatsMult.getStatValue(type);
            case GATHERING -> gatheringStatsMult.getStatValue(type);
            case LUCK -> luckStatsMult.getStatValue(type);
            default -> 1;
        };
    }

    @Override
    public void setStatMultValue(StatType type, double value) {
        switch (type.getStatCategory()) {
            case COMBAT, ARMOR -> super.setStatMultValue(type, value);
            case INTELLIGENCE -> abilityStatsMult.setStatValue(type, value);
            case FISHING -> fishingStatsMult.setStatValue(type, value);
            case MINING -> miningStatsMult.setStatValue(type, value);
            case GATHERING -> gatheringStatsMult.setStatValue(type, value);
            case LUCK -> luckStatsMult.setStatValue(type, value);
            case WISDOM -> wisdomStats.setStatValue(type, value);
        }
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

    public void setRegenStats(RegenStats regenStats) {
        this.regenStats = regenStats;
    }

    public void setWisdomStats(WisdomStats wisdomStats) {
        this.wisdomStats = wisdomStats;
    }

}