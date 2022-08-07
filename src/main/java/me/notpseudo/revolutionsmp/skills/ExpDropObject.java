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

    public void add(ExpDropObject other) {
        if (other == null) {
            return;
        }
        value += other.value;
    }

    public void multiply(ExpDropObject other) {
        if (other == null) {
            return;
        }
        value *= other.value;
    }

}