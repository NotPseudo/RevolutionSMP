package me.notpseudo.revolutionsmp.itemstats;

import java.io.Serializable;
import java.util.List;

public class FishingStats extends StatHolder implements Serializable {

    public FishingStats(double seaCreatureChance, double fishingTimeDecrease) {
        super(StatCategory.FISHING, List.of(new StatObject(StatType.SEA_CREATURE_CHANCE, seaCreatureChance),
                new StatObject(StatType.FISHING_SPEED, fishingTimeDecrease)));
    }

    @Override
    public void addStatObject(StatObject newStat) {
        if (containsType(newStat.getType())) {
            return;
        }
        if (newStat.getType().getStatCategory() != StatCategory.FISHING) {
            return;
        }
        super.getStats().add(newStat);
    }

    public static FishingStats createMult() {
        return (FishingStats) createMult(StatCategory.FISHING);
    }

    public static FishingStats createZero() {
        return (FishingStats) createZero(StatCategory.FISHING);
    }

}