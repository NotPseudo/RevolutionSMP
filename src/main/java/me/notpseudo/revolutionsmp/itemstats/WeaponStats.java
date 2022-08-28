package me.notpseudo.revolutionsmp.itemstats;

import java.io.Serializable;
import java.util.List;

// Weapon stats stored in an ItemStack's PersistentDataContainer
public class WeaponStats extends StatHolder implements Serializable {

    public WeaponStats(double damage, double strength, double critChance, double critDamage, double attackSpeed, double ferocity) {
        super(StatCategory.COMBAT, List.of(new StatObject(StatType.DAMAGE, damage),
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
        if (newStat.getType().getStatCategory() != StatCategory.COMBAT) {
            return;
        }
            super.getStats().add(newStat);
    }

    public static WeaponStats createMult() {
        return new WeaponStats(1, 1, 1, 1, 1, 1);
    }

    public static WeaponStats createZero() {
        return new WeaponStats(0, 0, 0, 0, 0, 0);
    }

}