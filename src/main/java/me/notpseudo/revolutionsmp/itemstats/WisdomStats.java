package me.notpseudo.revolutionsmp.itemstats;

import java.io.Serializable;
import java.util.List;

public class WisdomStats extends StatHolder implements Serializable {

    public WisdomStats(double combat, double mining, double foraging, double farming, double fishing, double alchemy, double enchanting) {
        super(StatCategory.WISDOM, List.of(new StatObject(StatType.COMBAT_WISDOM, combat),
                new StatObject(StatType.MINING_WISDOM, mining),
                new StatObject(StatType.FORAGING_WISDOM, foraging),
                new StatObject(StatType.FARMING_WISDOM, farming),
                new StatObject(StatType.FISHING_WISDOM, fishing),
                new StatObject(StatType.ALCHEMY_WISDOM, alchemy),
                new StatObject(StatType.ENCHANTING_WISDOM, enchanting)));
    }

    @Override
    public void addStatObject(StatObject newStat) {
        super.addStatObject(newStat);
    }

    public static WisdomStats createZero() {
        return new WisdomStats(0, 0, 0, 0, 0, 0, 0);
    }

    public static WisdomStats createMult() {
        return new WisdomStats(1, 1, 1, 1, 1 ,1, 1);
    }

}