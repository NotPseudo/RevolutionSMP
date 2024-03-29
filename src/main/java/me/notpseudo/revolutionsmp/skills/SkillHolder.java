package me.notpseudo.revolutionsmp.skills;

import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.listeners.StatsListeners;
import me.notpseudo.revolutionsmp.mining.CustomOreLocation;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class SkillHolder implements Serializable {

    private final UUID player;
    private ArrayList<SkillObject> skills;

    /**
     * Default constructor that doesn't have a known player
     * <b>Should only be used in a PersistentDataType catching an InvalidClassException</b>
     */
    public SkillHolder() {
        player = null;
        skills = new ArrayList<>();
        for (SkillType skillType : SkillType.values()) {
            skills.add(new SkillObject(this, skillType));
        }
    }

    public SkillHolder(UUID player) {
        this.player = player;
        skills = new ArrayList<>();
        for (SkillType skillType : SkillType.values()) {
            skills.add(new SkillObject(this, skillType));
        }
        SkillUtils.updatePlayerSkills(Bukkit.getPlayer(player), this);
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
        SkillUtils.updatePlayerSkills(Bukkit.getPlayer(player), this);
        return newSkill;
    }

    public void addSkill(SkillType type) {
        if (!contains(type)) {
            skills.add(new SkillObject(this, type));
            SkillUtils.updatePlayerSkills(Bukkit.getPlayer(player), this);
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
        SkillUtils.updatePlayerSkills(Bukkit.getPlayer(player), this);
    }

    @NotNull
    public WeaponStats getEventWeapon(LivingEntity target, EntityDamageEvent damageEvent, IncreaseType type) {
        WeaponStats event = WeaponStats.createZero();
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            event = WeaponStats.createMult();
        }
        for (SkillObject skill : skills) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                event.multiply(SkillUtils.getEventWeapon(skill, damageEvent, type));
            } else {
                event.combine(SkillUtils.getEventWeapon(skill, damageEvent, type));
            }
        }
        return event;
    }

    @NotNull
    public ArmorStats getEventArmor(LivingEntity damager, EntityDamageEvent damageEvent, IncreaseType type) {
        ArmorStats event = ArmorStats.createZero();
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            event = ArmorStats.createMult();
        }
        for (SkillObject skill : skills) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                event.multiply(SkillUtils.getEventArmor(skill, damageEvent, type));
            } else {
                event.combine(SkillUtils.getEventArmor(skill, damageEvent, type));
            }
        }
        return event;
    }

    @NotNull
    public AbilityStats getEventAbility(LivingEntity target, IncreaseType type) {
        AbilityStats event = AbilityStats.createZero();
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            event = AbilityStats.createMult();
        }
        for (SkillObject skill : skills) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                event.multiply(SkillUtils.getEventAbility(skill, type));
            } else {
                event.combine(SkillUtils.getEventAbility(skill, type));
            }
        }
        return event;
    }

    @NotNull
    public FishingStats getEventFishing(IncreaseType type) {
        FishingStats event = FishingStats.createZero();
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            event = FishingStats.createMult();
        }
        for (SkillObject skill : skills) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                event.multiply(SkillUtils.getEventFishing(skill, type));
            } else {
                event.combine(SkillUtils.getEventFishing(skill, type));
            }
        }
        return event;
    }

    @NotNull
    public MiningStats getEventMining(Block block, CustomOreLocation ore, IncreaseType type) {
        MiningStats event = MiningStats.createZero();
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            event = MiningStats.createMult();
        }
        for (SkillObject skill : skills) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                event.multiply(SkillUtils.getEventMining(skill, type));
            } else {
                event.combine(SkillUtils.getEventMining(skill, type));
            }
        }
        return event;
    }

    @NotNull
    public GatheringStats getEventGathering(Block block, IncreaseType type) {
        GatheringStats event = GatheringStats.createZero();
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            event = GatheringStats.createMult();
        }
        for (SkillObject skill : skills) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                event.multiply(SkillUtils.getEventGathering(skill, type));
            } else {
                event.combine(SkillUtils.getEventGathering(skill, type));
            }
        }
        return event;
    }

    @NotNull
    public LuckStats getEventLuck(LivingEntity target, IncreaseType type) {
        LuckStats event = LuckStats.createZero();
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            event = LuckStats.createMult();
        }
        for (SkillObject skill : skills) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                event.multiply(SkillUtils.getEventLuck(skill, type));
            } else {
                event.combine(SkillUtils.getEventLuck(skill, type));
            }
        }
        return event;
    }

    @NotNull
    public WeaponStats getBonusWeapon(IncreaseType type) {
        WeaponStats bonus = WeaponStats.createZero();
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            bonus = WeaponStats.createMult();
        }
        for (SkillObject skill : skills) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonus.multiply(SkillUtils.getBonusWeapon(skill, type));
            } else {
                bonus.combine(SkillUtils.getBonusWeapon(skill, type));
            }
        }
        return bonus;
    }

    @NotNull
    public ArmorStats getBonusArmor(IncreaseType type) {
        ArmorStats bonus = ArmorStats.createZero();
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            bonus = ArmorStats.createMult();
        }
        for (SkillObject skill : skills) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonus.multiply(SkillUtils.getBonusArmor(skill, type));
            } else {
                bonus.combine(SkillUtils.getBonusArmor(skill, type));
            }
        }
        return bonus;
    }

    @NotNull
    public AbilityStats getBonusAbility(IncreaseType type) {
        AbilityStats bonus = AbilityStats.createZero();
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            bonus = AbilityStats.createMult();
        }
        for (SkillObject skill : skills) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonus.multiply(SkillUtils.getBonusAbility(skill, type));
            } else {
                bonus.combine(SkillUtils.getBonusAbility(skill, type));
            }
        }
        return bonus;
    }

    @NotNull
    public FishingStats getBonusFishing(IncreaseType type) {
        FishingStats bonus = FishingStats.createZero();
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            bonus = FishingStats.createMult();
        }
        for (SkillObject skill : skills) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonus.multiply(SkillUtils.getBonusFishing(skill, type));
            } else {
                bonus.combine(SkillUtils.getBonusFishing(skill, type));
            }
        }
        return bonus;
    }

    @NotNull
    public MiningStats getBonusMining(IncreaseType type) {
        MiningStats bonus = MiningStats.createZero();
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            bonus = MiningStats.createMult();
        }
        for (SkillObject skill : skills) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonus.multiply(SkillUtils.getBonusMining(skill, type));
            } else {
                bonus.combine(SkillUtils.getBonusMining(skill, type));
            }
        }
        return bonus;
    }

    @NotNull
    public GatheringStats getBonusGathering(IncreaseType type) {
        GatheringStats bonus = GatheringStats.createZero();
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            bonus = GatheringStats.createMult();
        }
        for (SkillObject skill : skills) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonus.multiply(SkillUtils.getBonusGathering(skill, type));
            } else {
                bonus.combine(SkillUtils.getBonusGathering(skill, type));
            }
        }
        return bonus;
    }

    @NotNull
    public LuckStats getBonusLuck(IncreaseType type) {
        LuckStats bonus = LuckStats.createZero();
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            bonus = LuckStats.createMult();
        }
        for (SkillObject skill : skills) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonus.multiply(SkillUtils.getBonusLuck(skill, type));
            } else {
                bonus.combine(SkillUtils.getBonusLuck(skill, type));
            }
        }
        return bonus;
    }

    @NotNull
    public RegenStats getBonusRegen(IncreaseType type) {
        RegenStats bonus = RegenStats.createZero();
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            bonus = RegenStats.createMult();
        }
        for (SkillObject skill : skills) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonus.multiply(SkillUtils.getBonusRegen(skill, type));
            } else {
                bonus.combine(SkillUtils.getBonusRegen(skill, type));
            }
        }
        return bonus;
    }

    @NotNull
    public WisdomStats getBonusWisdom(IncreaseType type) {
        WisdomStats bonus = WisdomStats.createZero();
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            bonus = WisdomStats.createMult();
        }
        for (SkillObject skill : skills) {
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonus.multiply(SkillUtils.getBonusWisdom(skill, type));
            } else {
                bonus.combine(SkillUtils.getBonusWisdom(skill, type));
            }
        }
        return bonus;
    }

}