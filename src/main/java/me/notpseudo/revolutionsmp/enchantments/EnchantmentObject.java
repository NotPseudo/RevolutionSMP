package me.notpseudo.revolutionsmp.enchantments;

import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.mining.CustomOreLocation;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class EnchantmentObject implements Serializable, Comparable<EnchantmentObject> {

    private final EnchantmentType type;
    private int level;

    public EnchantmentObject(EnchantmentType type) {
        this.type = type;
        level = type.getMinLevel();
    }

    public EnchantmentObject(EnchantmentType type, int level) {
        this.type = type;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public EnchantmentType getType() {
        return type;
    }

    public void attackAction(Player attacker, LivingEntity target, double damage, boolean critical, EntityDamageEvent event) {
        type.playerAttackAction(this, attacker, target, damage, critical, event);
    }

    public void defenseAction(LivingEntity attacker, Player target, double damage, boolean critical, EntityDamageEvent event) {
        type.defenseAction(this, attacker, target, damage, critical, event);
    }

    public String getText() {
        if (type.isUltimate()) {
            return "" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + type.getName() + " " + level;
        } else if (level >= type.getMaxLevel()) {
            return "" + ChatColor.GOLD + type.getName() + " " + level;
        } else if (level > type.getEnchTableMax()) {
            return "" + ChatColor.DARK_PURPLE + type.getName() + " " + level;
        } else {
            return "" + ChatColor.BLUE + type.getName() + " " + level;
        }
    }

    public WeaponStats getBonusWeapon(Player player, IncreaseType inc) {
        return type.getBonusWeapon(player, level, inc);
    }

    public ArmorStats getBonusArmor(Player player, IncreaseType inc) {
        return type.getBonusArmor(player, level, inc);
    }

    public AbilityStats getBonusAbility(Player player, IncreaseType inc) {
        return type.getBonusAbility(player, level, inc);
    }

    public FishingStats getBonusFishing(Player fisher, IncreaseType inc) {
        return type.getBonusFishing(fisher, level, inc);
    }

    public MiningStats getBonusMining(Player miner, IncreaseType inc) {
        return type.getBonusMining(miner, level, inc);
    }

    public GatheringStats getBonusGathering(Player harvester, IncreaseType inc) {
        return type.getBonusGathering(harvester, level, inc);
    }

    public LuckStats getBonusLuck(Player player, IncreaseType inc) {
        return type.getBonusLuck(player, level, inc);
    }

    public RegenStats getBonusRegen(Player player, IncreaseType inc) {
        return type.getBonusRegen(player, level, inc);
    }

    public WisdomStats getBonusWisdom(Player player, IncreaseType inc) {
        return type.getBonusWisdom(player, level, inc);
    }

    public WeaponStats getEventWeapon(Player damager, LivingEntity target, EntityDamageEvent event, IncreaseType inc) {
        return type.getEventWeapon(damager, target, level, event, inc);
    }

    public ArmorStats getEventArmor(LivingEntity damager, Player target, EntityDamageEvent event, IncreaseType inc) {
        return type.getEventArmor(damager, target, level, event, inc);
    }

    public AbilityStats getEventAbility(Player damager, LivingEntity target, IncreaseType inc) {
        return type.getEventAbility(damager, target, level, inc);
    }

    public FishingStats getEventFishing(Player fisher, IncreaseType inc) {
        return type.getEventFishing(fisher, level, inc);
    }

    public MiningStats getEventMining(Player miner, Block block, CustomOreLocation ore, IncreaseType inc) {
        return type.getEventMining(miner, block, level, ore, inc);
    }

    public GatheringStats getEventGathering(Player harvester, Block block, IncreaseType inc) {
        return type.getEventGathering(harvester, block, level, inc);
    }

    public LuckStats getEventLuck(Player player, LivingEntity target, IncreaseType inc) {
        return type.getEventLuck(player, target, level, inc);
    }

    @Override
    public int compareTo(@NotNull EnchantmentObject o) {
        return type.toString().compareTo(o.getType().toString());
    }

}