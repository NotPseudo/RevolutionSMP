package me.notpseudo.revolutionsmp.specialiteminfo;

import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.itemstats.IncreaseType;
import me.notpseudo.revolutionsmp.skills.ExpDropObject;
import me.notpseudo.revolutionsmp.skills.SkillType;
import net.kyori.adventure.text.Component;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SpecialItemInfo implements Serializable {

    private ItemInfo holder;

    public SpecialItemInfo(ItemInfo holder) {
        this.holder = holder;
    }

    public ItemInfo getHolder() {
        return holder;
    }

    public UUID getOwner() {
        return holder.getOwner();
    }

    @NotNull
    public WeaponStats getEventWeapon(Player damager, LivingEntity target, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new WeaponStats(1, 1, 1, 1, 1, 1);
        }
        return new WeaponStats(0, 0, 0, 0, 0, 0);
    }

    @NotNull
    public ArmorStats getEventArmor(LivingEntity damager, Player target, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new ArmorStats(1, 1, 1, 1);
        }
        return new ArmorStats(0, 0, 0);
    }

    @NotNull
    public AbilityStats getEventAbility(Player damager, LivingEntity target, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new AbilityStats(1, 1);
        }
        return new AbilityStats(0, 0);
    }

    @NotNull
    public FishingStats getEventFishing(Player fisher, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new FishingStats(1, 1);
        }
        return new FishingStats(0, 0);
    }

    @NotNull
    public MiningStats getEventMining(Player miner, Block block, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new MiningStats(1, 1, 1);
        }
        return new MiningStats(0, 0, 0);
    }

    @NotNull
    public GatheringStats getEventGathering(Player harvester, Block block, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new GatheringStats(1, 1);
        }
        return new GatheringStats(0, 0);
    }

    @NotNull
    public LuckStats getEventLuck(Player attacker, LivingEntity target, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new LuckStats(1, 1);
        }
        return new LuckStats(0, 0);
    }

    @NotNull
    public WeaponStats getBonusWeapon(Player player, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new WeaponStats(1, 1, 1, 1, 1, 1);
        }
        return new WeaponStats(0, 0, 0, 0, 0, 0);
    }

    @NotNull
    public ArmorStats getBonusArmor(Player player, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new ArmorStats(1, 1, 1, 1);
        }
        return new ArmorStats(0, 0, 0);
    }

    @NotNull
    public AbilityStats getBonusAbility(Player player, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new AbilityStats(1, 1);
        }
        return new AbilityStats(0, 0);
    }

    @NotNull
    public FishingStats getBonusFishing(Player fisher, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new FishingStats(1, 1);
        }
        return new FishingStats(0, 0);
    }

    @NotNull
    public MiningStats getBonusMining(Player miner, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new MiningStats(1, 1, 1);
        }
        return new MiningStats(0, 0, 0);
    }

    @NotNull
    public GatheringStats getBonusGathering(Player harvester, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new GatheringStats(1, 1);
        }
        return new GatheringStats(0, 0);
    }

    @NotNull
    public LuckStats getBonusLuck(Player attacker, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new LuckStats(1, 1);
        }
        return new LuckStats(0, 0);
    }

    public ExpDropObject getEventExpBoost(SkillType type, IncreaseType inc) {
        if (inc == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new ExpDropObject(type, 1);
        }
        return new ExpDropObject(type, 0);
    }

    public void update(UUID uuid) {

    }

    public List<Component> getSpecialLore() {
        return new ArrayList<>();
    }

}
