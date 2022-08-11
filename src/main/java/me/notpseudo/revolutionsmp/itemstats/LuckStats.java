package me.notpseudo.revolutionsmp.itemstats;

import java.io.Serializable;
import java.util.List;

public class LuckStats extends StatHolder implements Serializable {

    public LuckStats(double magicFind, double petLuck) {
        super(StatCategory.LUCK, List.of(new StatObject(StatType.MAGIC_FIND, magicFind),
                new StatObject(StatType.PET_LUCK, petLuck)));
    }

    @Override
    public void addStatObject(StatObject newStat) {
        if (containsType(newStat.getType())) {
            return;
        }
        if (newStat.getType().getStatCategory() != StatCategory.LUCK) {
            return;
        }
        super.getStats().add(newStat);
    }

    public static LuckStats createMult() {
        return new LuckStats(1, 1);
    }

    public static LuckStats createZero() {
        return new LuckStats(0, 0);
    }

}