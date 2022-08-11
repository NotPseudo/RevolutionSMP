package me.notpseudo.revolutionsmp.itemstats;

import java.io.Serializable;
import java.util.List;

public class GatheringStats extends StatHolder implements Serializable {

    public GatheringStats(double farmingFortune, double foragingFortune) {
        super(StatCategory.GATHERING, List.of(new StatObject(StatType.FARMING_FORTUNE, farmingFortune),
                new StatObject(StatType.FORAGING_FORTUNE, foragingFortune)));
    }

    @Override
    public void addStatObject(StatObject newStat) {
        if (containsType(newStat.getType())) {
            return;
        }
        if (newStat.getType().getStatCategory() != StatCategory.GATHERING) {
            return;
        }
        super.getStats().add(newStat);
    }

    public static GatheringStats createMult() {
        return new GatheringStats(1, 1);
    }

    public static GatheringStats createZero() {
        return new GatheringStats(0, 0);
    }

}