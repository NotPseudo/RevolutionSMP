package me.notpseudo.revolutionsmp.itemstats;

import java.io.Serializable;
import java.util.List;

public class LuckStats extends StatHolder implements Serializable {

    public LuckStats(double magicFind, double petLuck) {
        super(List.of(new StatObject(StatType.MAGIC_FIND, magicFind),
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

}