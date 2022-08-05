package me.notpseudo.revolutionsmp.itemstats;

import java.io.Serializable;
import java.util.List;

public class MiningStats extends StatHolder implements Serializable {

    public MiningStats(double miningSpeed, double miningFortune, double pristine) {
        super(List.of(new StatObject(StatType.MINING_SPEED, miningSpeed),
                new StatObject(StatType.MINING_FORTUNE, miningFortune),
                new StatObject(StatType.PRISTINE, pristine)));
    }

    public MiningStats(double miningSpeed, double miningFortune, double pristine, double breakingPower) {
        super(List.of(new StatObject(StatType.MINING_SPEED, miningSpeed),
                new StatObject(StatType.MINING_FORTUNE, miningFortune),
                new StatObject(StatType.PRISTINE, pristine),
                new StatObject(StatType.BREAKING_POWER , breakingPower)));
    }

    @Override
    public void addStatObject(StatObject newStat) {
        if (containsType(newStat.getType())) {
            return;
        }
        if (newStat.getType().getStatCategory() != StatCategory.MINING) {
            return;
        }
        super.getStats().add(newStat);
    }

}