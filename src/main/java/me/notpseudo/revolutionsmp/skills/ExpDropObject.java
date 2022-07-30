package me.notpseudo.revolutionsmp.skills;

public class ExpDropObject {

    private SkillType type;
    private double value;

    public ExpDropObject(SkillType type, double value) {
        this.type = type;
        this.value = value;
    }

    public SkillType getType() {
        return type;
    }

    public double getValue() {
        return value;
    }

}