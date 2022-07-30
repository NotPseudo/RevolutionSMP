package me.notpseudo.revolutionsmp.itemstats;

import java.io.Serializable;
import java.util.List;

public class GatheringStats extends StatHolder implements Serializable {

    public GatheringStats(double farmingFortune, double foragingFortune) {
        super(List.of(new StatObject(StatType.FARMING_FORTUNE, farmingFortune),
                new StatObject(StatType.FORAGING_FORTUNE, foragingFortune)));
    }

    @Override
    public void addStatObject(StatObject newStat) {
        if (containsType(newStat.getType())) {
            return;
        }
        if (newStat.getType() != StatType.FARMING_FORTUNE ||
                newStat.getType() != StatType.FORAGING_FORTUNE) {
            return;
        }
        super.getStats().add(newStat);
    }

}