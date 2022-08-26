package me.notpseudo.revolutionsmp.specialiteminfo;

import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.itemstats.IncreaseType;
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

    public WeaponStats getEventWeapon(Player damager, LivingEntity target, IncreaseType type) {
        return null;
    }

    public ArmorStats getEventArmor(LivingEntity damager, Player target, IncreaseType type) {
        return null;
    }

    public AbilityStats getEventAbility(Player damager, LivingEntity target, IncreaseType type) {
        return null;
    }

    public FishingStats getEventFishing(Player fisher, IncreaseType type) {
        return null;
    }

    public MiningStats getEventMining(Player miner, Block block, IncreaseType type) {
        return null;
    }

    public GatheringStats getEventGathering(Player harvester, Block block, IncreaseType type) {
        return null;
    }

    public LuckStats getEventLuck(Player attacker, LivingEntity target, IncreaseType type) {
        return null;
    }

    public WeaponStats getBonusWeapon(Player player, IncreaseType type) {
        return null;
    }

    public ArmorStats getBonusArmor(Player player, IncreaseType type) {
        return null;
    }

    public AbilityStats getBonusAbility(Player player, IncreaseType type) {
        return null;
    }

    public FishingStats getBonusFishing(Player fisher, IncreaseType type) {
        return null;
    }

    public MiningStats getBonusMining(Player miner, IncreaseType type) {
        return null;
    }

    public GatheringStats getBonusGathering(Player harvester, IncreaseType type) {
        return null;
    }

    public LuckStats getBonusLuck(Player attacker, IncreaseType type) {
        return null;
    }

    public RegenStats getBonusRegen(Player attacker, IncreaseType type) {
        return null;
    }

    public WisdomStats getBonusWisdom(Player attacker, IncreaseType type) {
        return null;
    }

    public WisdomStats getCombatWisdom(LivingEntity target) {
        return null;
    }

    public WisdomStats getBreakingWisdom(Block block) {
        return null;
    }

    public WisdomStats getRegularWisdom() {
        return null;
    }

    public void update(UUID uuid) {

    }

    public void upgradeFromCrafting() {

    }

    public List<Component> getSpecialLore() {
        return new ArrayList<>();
    }

}
