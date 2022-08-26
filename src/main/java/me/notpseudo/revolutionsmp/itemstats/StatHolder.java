package me.notpseudo.revolutionsmp.itemstats;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StatHolder implements Serializable {

    private final StatCategory CATEGORY;
    private ArrayList<StatObject> stats;

    public StatHolder(StatCategory stat, List<StatObject> stats) {
        CATEGORY = stat;
        this.stats = new ArrayList<>();
        this.stats.addAll(stats);
    }

    public StatHolder(StatCategory stat) {
        CATEGORY = stat;
        this.stats = new ArrayList<>();
    }

    public StatCategory getStatCategory() {
        return CATEGORY;
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
        if (newStat.getType().getStatCategory() != CATEGORY) {
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

    public void additivePercent(@Nullable StatHolder other) {
        if (other == null) {
            return;
        }
        for (StatObject stat : stats) {
            stat.additivePercent(other.getStatObject(stat.getType()));
        }
    }

    public void multiply(@Nullable StatHolder other) {
        if (other == null) {
            return;
        }
        for (StatObject stat: stats) {
            stat.multiply(other.getStatObject(stat.getType()));
        }
    }

    public static StatHolder createMult(StatCategory category) {
        StatHolder newHolder = new StatHolder(category);
        for (StatType type : Stream.of(StatType.values()).filter(t -> t.getStatCategory() == category).toList()) {
            newHolder.stats.add(new StatObject(type, 1));
        }
        return newHolder;
    }

    public static StatHolder createZero(StatCategory category) {
        StatHolder newHolder = new StatHolder(category);
        for (StatType type : Stream.of(StatType.values()).filter(t -> t.getStatCategory() == category).toList()) {
            newHolder.stats.add(new StatObject(type, 0));
        }
        return newHolder;
    }

}