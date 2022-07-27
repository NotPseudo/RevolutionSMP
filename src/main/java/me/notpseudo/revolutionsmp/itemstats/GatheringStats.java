package me.notpseudo.revolutionsmp.itemstats;

import java.io.Serializable;

public class GatheringStats implements Serializable {

    private double farmingFortune;
    private double foragingFortune;

    public GatheringStats(double farmingFortune, double foragingFortune) {
        this.farmingFortune = farmingFortune;
        this.foragingFortune = foragingFortune;
    }

    public double getFarmingFortune() {
        return farmingFortune;
    }

    public void setFarmingFortune(double farmingFortune) {
        this.farmingFortune = farmingFortune;
    }

    public double getForagingFortune() {
        return foragingFortune;
    }

    public void setForagingFortune(double foragingFortune) {
        this.foragingFortune = foragingFortune;
    }

    public void combine(GatheringStats other) {
        if (other == null) {
            return;
        }
        farmingFortune += other.farmingFortune;
        foragingFortune += other.foragingFortune;
    }

}