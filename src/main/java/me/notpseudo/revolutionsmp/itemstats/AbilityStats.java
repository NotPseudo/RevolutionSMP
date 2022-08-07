package me.notpseudo.revolutionsmp.itemstats;


import java.io.Serializable;
import java.util.List;

// Ability stats stored in an ItemStack's PersistentDataContainer
public class AbilityStats extends StatHolder implements Serializable {

    public AbilityStats(double abilityDamage, double intelligence) {
        super(StatCategory.INTELLIGENCE, List.of(new StatObject(StatType.ABILITY_DAMAGE, abilityDamage),
                new StatObject(StatType.INTELLIGENCE, intelligence)));
    }

    @Override
    public void addStatObject(StatObject newStat) {
        if (containsType(newStat.getType())) {
            return;
        }
        if (newStat.getType().getStatCategory() != StatCategory.INTELLIGENCE) {
            return;
        }
        super.getStats().add(newStat);
    }

    public static AbilityStats createMult() {
        return (AbilityStats) createMult(StatCategory.INTELLIGENCE);
    }

    public static AbilityStats createZero() {
        return (AbilityStats) createZero(StatCategory.INTELLIGENCE);
    }

}