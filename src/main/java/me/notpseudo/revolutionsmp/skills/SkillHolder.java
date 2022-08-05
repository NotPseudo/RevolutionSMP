package me.notpseudo.revolutionsmp.skills;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;

public class SkillHolder implements Serializable {

    private ArrayList<SkillObject> skills;

    public SkillHolder() {
        skills = new ArrayList<>();
        for (SkillType skillType : SkillType.values()) {
            skills.add(new SkillObject(skillType));
        }
    }

    @NotNull
    public SkillObject getSkill(SkillType type) {
        for (SkillObject skill : skills) {
            if (skill.getType() == type) {
                return skill;
            }
        }
        SkillObject newSkill = new SkillObject(type);
        skills.add(newSkill);
        return newSkill;
    }

    public void addSkill(SkillType type) {
        if (!contains(type)) {
            skills.add(new SkillObject(type));
        }
    }

    public boolean contains(SkillType type) {
        for (SkillObject skill : skills) {
            if (skill.getType() == type) {
                return true;
            }
        }
        return false;
    }

}