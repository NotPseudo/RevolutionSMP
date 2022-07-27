package me.notpseudo.revolutionsmp.itemstats;

import java.io.Serializable;

public class LuckStats implements Serializable {

    private double magicFind;
    private double petLuck;

    public LuckStats(double magicFind, double petLuck) {
        this.magicFind = magicFind;
        this.petLuck = petLuck;
    }

    public double getMagicFind() {
        return magicFind;
    }

    public void setMagicFind(double magicFind) {
        this.magicFind = magicFind;
    }

    public double getPetLuck() {
        return petLuck;
    }

    public void setPetLuck(double petLuck) {
        this.petLuck = petLuck;
    }

    public void combine(LuckStats other) {
        if (other == null) {
            return;
        }
        magicFind += other.magicFind;
        petLuck += other.petLuck;
    }

}