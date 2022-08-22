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
            return WeaponStats.createMult();
        }
        return WeaponStats.createZero();
    }

    @NotNull
    public ArmorStats getEventArmor(LivingEntity damager, Player target, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return ArmorStats.createMult();
        }
        return ArmorStats.createZero();
    }

    @NotNull
    public AbilityStats getEventAbility(Player damager, LivingEntity target, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return AbilityStats.createMult();
        }
        return AbilityStats.createZero();
    }

    @NotNull
    public FishingStats getEventFishing(Player fisher, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return FishingStats.createMult();
        }
        return FishingStats.createZero();
    }

    @NotNull
    public MiningStats getEventMining(Player miner, Block block, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return MiningStats.createMult();
        }
        return MiningStats.createZero();
    }

    @NotNull
    public GatheringStats getEventGathering(Player harvester, Block block, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return GatheringStats.createMult();
        }
        return GatheringStats.createZero();
    }

    @NotNull
    public LuckStats getEventLuck(Player attacker, LivingEntity target, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return LuckStats.createMult();
        }
        return LuckStats.createZero();
    }

    @NotNull
    public WeaponStats getBonusWeapon(Player player, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return WeaponStats.createMult();
        }
        return WeaponStats.createZero();
    }

    @NotNull
    public ArmorStats getBonusArmor(Player player, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return ArmorStats.createMult();
        }
        return ArmorStats.createZero();
    }

    @NotNull
    public AbilityStats getBonusAbility(Player player, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return AbilityStats.createMult();
        }
        return AbilityStats.createZero();
    }

    @NotNull
    public FishingStats getBonusFishing(Player fisher, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return FishingStats.createMult();
        }
        return FishingStats.createZero();
    }

    @NotNull
    public MiningStats getBonusMining(Player miner, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return MiningStats.createMult();
        }
        return MiningStats.createZero();
    }

    @NotNull
    public GatheringStats getBonusGathering(Player harvester, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return GatheringStats.createMult();
        }
        return GatheringStats.createZero();
    }

    @NotNull
    public LuckStats getBonusLuck(Player attacker, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return LuckStats.createMult();
        }
        return LuckStats.createZero();
    }

    public ExpDropObject getCombatEventExpBoost(SkillType type, IncreaseType inc, LivingEntity target) {
        if (inc == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new ExpDropObject(type, 1);
        }
        return new ExpDropObject(type, 0);
    }

    public ExpDropObject getBreakEventExpBoost(SkillType type, IncreaseType inc, Block block) {
        if (inc == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new ExpDropObject(type, 1);
        }
        return new ExpDropObject(type, 0);
    }

    public void update(UUID uuid) {

    }

    public void upgradeFromCrafting() {

    }

    public List<Component> getSpecialLore() {
        return new ArrayList<>();
    }

}
