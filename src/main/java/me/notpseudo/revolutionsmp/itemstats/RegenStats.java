package me.notpseudo.revolutionsmp.itemstats;

import java.io.Serializable;
import java.util.List;

public class RegenStats extends StatHolder implements Serializable {

    public RegenStats(double healthRegen, double manaRegen, double vitality, double mending) {
        super(StatCategory.REGEN, List.of(
                new StatObject(StatType.HEALTH_REGEN, healthRegen),
                new StatObject(StatType.MANA_REGEN, manaRegen),
                new StatObject(StatType.VITALITY, vitality),
                new StatObject(StatType.MENDING, mending)
        ));
    }

    @Override
    public void addStatObject(StatObject newStat) {
        if (containsType(newStat.getType())) {
            return;
        }
        if (newStat.getType().getStatCategory() != StatCategory.REGEN) {
            return;
        }
        super.getStats().add(newStat);
    }

    public static RegenStats createMult() {
        return new RegenStats(1, 1, 1, 1);
    }

    public static RegenStats createZero() {
        return new RegenStats(0, 0, 0, 0);
    }

}