package me.notpseudo.revolutionsmp.itemstats;

import java.io.Serializable;

public class StatObject implements Serializable {

    private final StatType TYPE;
    private double value;

    public StatObject(StatType type, double value) {
        TYPE = type;
        this.value = value;
    }

    public StatType getType() {
        return TYPE;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void add(StatObject other) {
        if (other == null) {
            return;
        }
        if (other.TYPE != TYPE) {
            return;
        }
        value += other.value;
    }

}