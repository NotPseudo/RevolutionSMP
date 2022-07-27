package me.notpseudo.revolutionsmp.itemstats;

import java.io.Serializable;

public class MiningStats implements Serializable {

    private double miningSpeed;
    private double miningFortune;
    private double pristine;

    public MiningStats(double miningSpeed, double miningFortune, double pristine) {
        this.miningSpeed = miningSpeed;
        this.miningFortune = miningFortune;
        this.pristine = pristine;
    }

    public double getMiningSpeed() {
        return miningSpeed;
    }

    public void setMiningSpeed(double miningSpeed) {
        this.miningSpeed = miningSpeed;
    }

    public double getMiningFortune() {
        return miningFortune;
    }

    public void setMiningFortune(double miningFortune) {
        this.miningFortune = miningFortune;
    }

    public double getPristine() {
        return pristine;
    }

    public void setPristine(double pristine) {
        this.pristine = pristine;
    }

    public void combine(MiningStats other) {
        if (other == null) {
            return;
        }
        miningSpeed += other.miningSpeed;
        miningFortune += other.miningFortune;
        pristine += other.pristine;
    }

}