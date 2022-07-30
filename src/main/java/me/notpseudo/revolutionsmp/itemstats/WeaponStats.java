package me.notpseudo.revolutionsmp.itemstats;

import java.io.Serializable;
import java.util.List;

// Weapon stats stored in an ItemStack's PersistentDataContainer
public class WeaponStats extends StatHolder implements Serializable {

    public WeaponStats(double damage, double strength, double critChance, double critDamage, double attackSpeed, double ferocity) {
        super(List.of(new StatObject(StatType.DAMAGE, damage),
                new StatObject(StatType.STRENGTH, strength),
                new StatObject(StatType.CRIT_CHANCE, critChance),
                new StatObject(StatType.CRIT_DAMAGE, critDamage),
                new StatObject(StatType.ATTACK_SPEED, attackSpeed),
                new StatObject(StatType.FEROCITY, ferocity)));
    }

    @Override
    public void addStatObject(StatObject newStat) {
        if (containsType(newStat.getType())) {
            return;
        }
        if (newStat.getType() != StatType.DAMAGE ||
                newStat.getType() != StatType.STRENGTH ||
                newStat.getType() != StatType.CRIT_CHANCE ||
                newStat.getType() != StatType.CRIT_DAMAGE ||
                newStat.getType() != StatType.ATTACK_SPEED||
                newStat.getType() != StatType.FEROCITY) {
            return;
        }
            super.getStats().add(newStat);
    }

}