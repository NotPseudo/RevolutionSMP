package me.notpseudo.revolutionsmp.specialiteminfo;

import me.notpseudo.revolutionsmp.abilities.AbilityType;
import me.notpseudo.revolutionsmp.itemstats.StatObject;
import me.notpseudo.revolutionsmp.itemstats.StatType;
import me.notpseudo.revolutionsmp.skills.ExpDropObject;
import me.notpseudo.revolutionsmp.skills.SkillType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SpecialItemInfo implements Serializable {

    private UUID owner;

    public SpecialItemInfo() {
        owner = null;
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID newOwner) {
        owner = newOwner;
    }

    public StatObject getDamageStatAdditiveAmount(LivingEntity damager, LivingEntity target, StatType type) {
        return new StatObject(type, 0);
    }

    public StatObject getDamageStatAdditivePercent(LivingEntity damager, LivingEntity target, StatType type) {
        return new StatObject(type, 0);
    }

    public StatObject getDamageStatMultiplicativePercent(LivingEntity damager, LivingEntity target, StatType type) {
        return new StatObject(type, 1);
    }

    public StatObject getHealthStatAdditiveAmount(LivingEntity damager, LivingEntity target, StatType type) {
        return new StatObject(type, 0);
    }

    public StatObject getHealthStatAdditivePercent(LivingEntity damager, LivingEntity target, StatType type) {
        return new StatObject(type, 0);
    }

    public StatObject getHealthStatMultiplicativePercent(LivingEntity damager, LivingEntity target, StatType type) {
        return new StatObject(type, 1);
    }

    public StatObject getAbilityStatAdditiveAmount(LivingEntity damager, StatType type) {
        return new StatObject(type, 0);
    }

    public StatObject getAbilityStatAdditivePercent(LivingEntity damager, StatType type) {
        return new StatObject(type, 0);
    }

    public StatObject getAbilityStatMultiplicativePercent(LivingEntity damager, StatType type) {
        return new StatObject(type, 1);
    }

    public StatObject getFishingStatAdditiveAmount(LivingEntity damager, StatType type) {
        return new StatObject(type, 0);
    }

    public StatObject getFishingStatAdditivePercent(LivingEntity damager, StatType type) {
        return new StatObject(type, 0);
    }

    public StatObject getFishingStatMultiplicativePercent(LivingEntity damager, StatType type) {
        return new StatObject(type, 1);
    }

    public StatObject geBreakingStatAdditiveAmount(Player harvester, Block block, StatType type) {
        return new StatObject(type, 0);
    }

    public StatObject getBreakingStatAdditivePercent(Player harvester, Block block, StatType type) {
        return new StatObject(type, 0);
    }

    public StatObject getBreakingStatMultiplicativePercent(Player harvester, Block block, StatType type) {
        return new StatObject(type, 1);
    }

    public StatObject getLuckStatAdditiveAmount(LivingEntity damager, LivingEntity target, StatType type) {
        return new StatObject(type, 0);
    }

    public StatObject getLuckStatAdditivePercent(LivingEntity damager, LivingEntity target, StatType type) {
        return new StatObject(type, 0);
    }

    public StatObject getLuckStatMultiplicativePercent(LivingEntity damager, LivingEntity target, StatType type) {
        return new StatObject(type, 1);
    }

    public ExpDropObject getExpAdditiveAmount(SkillType type) {
        return new ExpDropObject(type, 0);
    }

    public ExpDropObject getExpAdditivePercent(SkillType type) {
        return new ExpDropObject(type, 0);
    }

    public ExpDropObject getExpMultiplicativePercent(SkillType type) {
        return new ExpDropObject(type, 1);
    }

    public void update(UUID uuid) {

    }

    public List<Component> getSpecialLore() {
        return new ArrayList<>();
    }

}
