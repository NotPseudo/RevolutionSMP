package me.notpseudo.revolutionsmp.itemstats;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StatHolder implements Serializable {

    private ArrayList<StatObject> stats;

    public StatHolder(List<StatObject> stats) {
        this.stats = new ArrayList<>();
        this.stats.addAll(stats);
    }

    public ArrayList<StatObject> getStats() {
        return stats;
    }

    public double getStatValue(StatType type) {
        for (StatObject stat : stats) {
            if (stat.getType() == type) {
                return stat.getValue();
            }
        }
        return 0;
    }

    public void setStatValue(StatType type, double value) {
        for (StatObject stat : stats) {
            if (stat.getType() == type) {
                stat.setValue(value);
            }
        }
    }

    public void addStatObject(StatObject newStat) {
        if (containsType(newStat.getType())) {
            return;
        }
        stats.add(newStat);
    }

    public StatObject getStatObject(StatType type) {
        for (StatObject stat : stats) {
            if (stat.getType() == type) {
                return stat;
            }
        }
        return null;
    }

    public boolean containsType(StatType type) {
        for (StatObject stat : stats) {
            if (stat.getType() == type) {
                return true;
            }
        }
        return false;
    }

    public void combine(StatHolder other) {
        if (other == null) {
            return;
        }
        for (StatObject stat : stats) {
            stat.add(other.getStatObject(stat.getType()));
        }
    }

    public void additivePercent(StatHolder other) {
        if (other == null) {
            return;
        }
        for (StatObject stat : stats) {
            stat.additivePercent(other.getStatObject(stat.getType()));
        }
    }

    public void multiply(StatHolder other) {
        if (other == null) {
            return;
        }
        for (StatObject stat: stats) {
            stat.multiply(other.getStatObject(stat.getType()));
        }
    }

}