package me.notpseudo.revolutionsmp.skills;

import me.notpseudo.revolutionsmp.listeners.StatsListeners;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class SkillHolder implements Serializable {

    private final static NamespacedKey skillKey = SkillUtils.getSkillKey();

    private UUID player;
    private ArrayList<SkillObject> skills;

    public SkillHolder(UUID player) {
        this.player = player;
        skills = new ArrayList<>();
        for (SkillType skillType : SkillType.values()) {
            skills.add(new SkillObject(this, skillType));
        }
        Bukkit.getPlayer(player).getPersistentDataContainer().set(skillKey, new SkillsDataType(), this);
    }

    public UUID getPlayer() {
        return player;
    }

    @NotNull
    public SkillObject getSkill(SkillType type) {
        for (SkillObject skill : skills) {
            if (skill.getType() == type) {
                return skill;
            }
        }
        SkillObject newSkill = new SkillObject(this, type);
        skills.add(newSkill);
        Bukkit.getPlayer(player).getPersistentDataContainer().set(skillKey, new SkillsDataType(), this);
        return newSkill;
    }

    public void addSkill(SkillType type) {
        if (!contains(type)) {
            skills.add(new SkillObject(this, type));
            Bukkit.getPlayer(player).getPersistentDataContainer().set(skillKey, new SkillsDataType(), this);
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
     public void addExp(ExpDropObject exp) {
        SkillObject skill = getSkill(exp.getType());
        skill.addXp(exp);
        Player owner = Bukkit.getPlayer(player);
        if (owner != null) {
            StatsListeners.showExpGainBar(owner, exp, skill.getPercent());
        }
         Bukkit.getPlayer(player).getPersistentDataContainer().set(skillKey, new SkillsDataType(), this);
     }

}