package me.notpseudo.revolutionsmp.itemstats;

import java.io.Serializable;

public class FishingStats implements Serializable {

    private double seaCreatureChance;
    private double fishingTimeDecrease;

    public FishingStats(double seaCreatureChance, double fishingTimeDecrease) {
        this.seaCreatureChance = seaCreatureChance;
        this.fishingTimeDecrease = fishingTimeDecrease;
    }

    public double getSeaCreatureChance() {
        return seaCreatureChance;
    }

    public void setSeaCreatureChance(double seaCreatureChance) {
        this.seaCreatureChance = seaCreatureChance;
    }

    public double getFishingTimeDecrease() {
        return fishingTimeDecrease;
    }

    public void setFishingTimeDecrease(double fishingTimeDecrease) {
        this.fishingTimeDecrease = fishingTimeDecrease;
    }

    public void combine(FishingStats other) {
        if (other == null) {
            return;
        }
        seaCreatureChance += other.seaCreatureChance;
        fishingTimeDecrease += other.fishingTimeDecrease;
    }

}