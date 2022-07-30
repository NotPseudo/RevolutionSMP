package me.notpseudo.revolutionsmp.itemstats;

import java.io.Serializable;
import java.util.List;

public class MiningStats extends StatHolder implements Serializable {

    public MiningStats(double miningSpeed, double miningFortune, double pristine) {
        super(List.of(new StatObject(StatType.MINING_SPEED, miningSpeed),
                new StatObject(StatType.MINING_FORTUNE, miningFortune),
                new StatObject(StatType.PRISTINE, pristine)));
    }

    @Override
    public void addStatObject(StatObject newStat) {
        if (containsType(newStat.getType())) {
            return;
        }
        if (newStat.getType() != StatType.MINING_SPEED ||
                newStat.getType() != StatType.MINING_FORTUNE ||
                newStat.getType() != StatType.PRISTINE) {
            return;
        }
        super.getStats().add(newStat);
    }

}