package me.notpseudo.revolutionsmp.itemstats;

import java.io.Serializable;
import java.util.List;

// Armor stats stored in an ItemStack's PersistentDataContainer
public class ArmorStats extends StatHolder implements Serializable {

    public ArmorStats(double health, double defense, double speed) {
        super(List.of(new StatObject(StatType.HEALTH, health),
                new StatObject(StatType.DEFENSE, defense),
                new StatObject(StatType.SPEED, speed),
                new StatObject(StatType.TRUE_DEFENSE, 0)));
    }

    public ArmorStats(double health, double defense, double speed, double trueDefense) {
        super(List.of(new StatObject(StatType.HEALTH, health),
                new StatObject(StatType.DEFENSE, defense),
                new StatObject(StatType.SPEED, speed),
                new StatObject(StatType.TRUE_DEFENSE, trueDefense)));
    }

    @Override
    public void addStatObject(StatObject newStat) {
        if (containsType(newStat.getType())) {
            return;
        }
        if (newStat.getType().getStatCategory() != StatCategory.ARMOR) {
            return;
        }
        super.getStats().add(newStat);
    }

}