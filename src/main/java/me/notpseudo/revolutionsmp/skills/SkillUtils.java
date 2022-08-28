package me.notpseudo.revolutionsmp.skills;

import com.destroystokyo.paper.event.player.PlayerPickupExperienceEvent;
import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.listeners.MobListeners;
import me.notpseudo.revolutionsmp.listeners.StatsListeners;
import me.notpseudo.revolutionsmp.mining.CustomOreLocation;
import me.notpseudo.revolutionsmp.mobstats.CustomMobType;
import me.notpseudo.revolutionsmp.mobstats.MobInfo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SkillUtils implements Listener {

    private static final NamespacedKey skillKey = new NamespacedKey(RevolutionSMP.getPlugin(RevolutionSMP.class), "skillKey");

    public SkillUtils(RevolutionSMP plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static NamespacedKey getSkillKey() {
        return skillKey;
    }

    public static double getXpForNextLevel(SkillType type, int nextLevel) {
        int multiplier = 1;
        if (type == SkillType.BUILDING) {
            multiplier = 2;
        }
        return multiplier * switch ((nextLevel - 1) / 5) {
            case 0 -> 500 * nextLevel;
            case 1 -> -2500 + 1000 * nextLevel;
            case 2 -> -17500 + 2500 * nextLevel;
            case 3 -> -50500 + 4700 * nextLevel;
            case 4 -> -94500 + 6900 * nextLevel;
            case 5 -> -234500 + 12500 * nextLevel;
            case 6 -> -384000 + 17500 * nextLevel;
            case 7 -> -647000 + 25000 * nextLevel;
            case 8 -> -1327000 + 42500 * nextLevel;
            case 9 -> -2677000 + 72500 * nextLevel;
            case 10 -> -5302000 + 125000 * nextLevel;
            case 11 -> -9427000 + 200000 * nextLevel;
            default -> 0;
        };
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (!(event.getEntity() instanceof Creature || event.getEntity() instanceof ComplexLivingEntity)) {
            return;
        }
        MobInfo info = MobListeners.getMobInfo(event.getEntity());
        if (info == null) {
            return;
        }
        CustomMobType type = info.getCustomMobType();
        double exp = Math.max(event.getDroppedExp(), Math.max(1, type.getVanillaExp()));
        exp *= type.getExpMult();
        event.setDroppedExp((int) exp);
        ExpDropObject baseDrop = info.getCustomMobType().getExp(info.getLevel());
        for (UUID id : info.getAttackers().keySet()) {
            Player player = Bukkit.getPlayer(id);
            if (player != null) {
                addCombatXpToPlayer(player, baseDrop.getType(), event.getEntity(), baseDrop.getValue());
            }
        }
    }

    @EventHandler
    public void onPlayerDeathMessage(PlayerDeathEvent event) {
        EntityDamageEvent damageEvent = event.getPlayer().getLastDamageCause();
        if (!(damageEvent instanceof EntityDamageByEntityEvent damageEnt)) {
            return;
        }
        Entity attacker = damageEnt.getDamager();
        if (!(attacker instanceof LivingEntity living)) {
            return;
        }
        MobInfo info = MobListeners.getMobInfo(living);
        if (info == null) {
            return;
        }
        event.deathMessage(Component.text(event.getPlayer().getName() + " was killed by " + info.getName()));
    }

    @EventHandler
    public void onExpPickUp(PlayerPickupExperienceEvent event) {
        int level = (int) getHolder(event.getPlayer()).getSkill(SkillType.ENCHANTING).getLevel();
        ExperienceOrb orb = event.getExperienceOrb();
        int finalExp = (int) (orb.getExperience() * (1 + (level * .04)));
        orb.setExperience(finalExp);
    }

    public static void updatePlayerSkills(Player player, SkillHolder holder) {
        if (player == null) {
            return;
        }
        player.getPersistentDataContainer().set(skillKey, new SkillsDataType(), holder);
    }

    public static void addCombatXpToPlayer(Player player, SkillType type, LivingEntity target, double exp) {
        if (player.getGameMode() == GameMode.CREATIVE) {
            return;
        }
        double boost = StatsListeners.getCombatWisdomStat(type, player, target);
        double finalExp = exp * (1 + (boost / 100));
        getHolder(player).addExp(new ExpDropObject(type, finalExp));
    }

    public static void addBreakingXpToPlayer(Player player, SkillType type, Block block, CustomOreLocation ore, double exp) {
        if (player.getGameMode() == GameMode.CREATIVE) {
            return;
        }
        double boost = StatsListeners.getBreakWisdomStat(type, player, block, ore);
        double finalExp = exp * (1 + (boost / 100));
        getHolder(player).addExp(new ExpDropObject(type, finalExp));
    }

    public static void addRegularXP(SkillType type, Player player, double exp) {
        if (player.getGameMode() == GameMode.CREATIVE) {
            return;
        }
        double boost = StatsListeners.getRegularWisdomStat(type, player);
        double finalExp = exp * (1 + (boost / 100));
        getHolder(player).addExp(new ExpDropObject(type, finalExp));
    }

    public static void handleCraftingXP(Player player, ItemStack result) {
        ItemInfo info = ItemEditor.getInfo(result);
        if (info == null) {
            return;
        }
        double xp = switch (info.getRarity()) {
            default -> 10;
            case UNCOMMON -> 50;
            case RARE -> 100;
            case EPIC -> 500;
            case LEGENDARY -> 1000;
            case MYTHIC -> 1500;
            case DIVINE, SPECIAL -> 2500;
        };
        SkillUtils.addRegularXP(SkillType.BUILDING, player, xp * result.getAmount());
    }

    public static double getTotalXpForMaxLevel(SkillType type) {
        double total = 0;
        for (int i = 0; i < type.getMaxLevel(); i++) {
            total += getXpForNextLevel(type, i + 1);
        }
        return total;
    }

    @NotNull
    public static SkillHolder getHolder(Player player) {
        SkillHolder holder = player.getPersistentDataContainer().get(skillKey, new SkillsDataType());
        if (holder == null) {
            holder = new SkillHolder(player.getUniqueId());
            updatePlayerSkills(player, holder);
        }
        if (holder.getPlayer() == null) {
            holder = new SkillHolder(player.getUniqueId());
            updatePlayerSkills(player, holder);
            player.sendMessage(Component.text("There was a slight issue while processing your Skills. If you notice any issues, please contact a staff member", NamedTextColor.YELLOW));
        }
        return holder;
    }

    public static List<Component> getSkillRewards(SkillObject skill) {
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text("Level " + (int) skill.getLevel() + " Rewards: ", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        GatheringStats gather = SkillUtils.getBonusGathering(skill, IncreaseType.INCREASE);
        ArmorStats armor = SkillUtils.getBonusArmor(skill, IncreaseType.INCREASE);
        MiningStats mining = SkillUtils.getBonusMining(skill, IncreaseType.INCREASE);
        WeaponStats weapon = SkillUtils.getBonusWeapon(skill, IncreaseType.INCREASE);
        AbilityStats ability = SkillUtils.getBonusAbility(skill, IncreaseType.INCREASE);
        switch (skill.getType()) {
            case FARMING -> {
                lore.add(Component.text(ItemEditor.getStatString(gather.getStatValue(StatType.FARMING_FORTUNE)) + " ", NamedTextColor.GREEN).append(StatType.FARMING_FORTUNE.getNameWithSymbol()).decoration(TextDecoration.ITALIC, false));
                lore.add(Component.text(ItemEditor.getStatString(armor.getStatValue(StatType.HEALTH)) + " ", NamedTextColor.GREEN).append(StatType.HEALTH.getNameWithSymbol()).decoration(TextDecoration.ITALIC, false));
            }
            case MINING -> {
                lore.add(Component.text(ItemEditor.getStatString(mining.getStatValue(StatType.MINING_FORTUNE)) + " ", NamedTextColor.GREEN).append(StatType.MINING_FORTUNE.getNameWithSymbol()).decoration(TextDecoration.ITALIC, false));
                lore.add(Component.text(ItemEditor.getStatString(armor.getStatValue(StatType.DEFENSE)) + " ", NamedTextColor.GREEN).append(StatType.DEFENSE.getNameWithSymbol()).decoration(TextDecoration.ITALIC, false));
            }
            case COMBAT -> {
                WeaponStats incDamage = SkillUtils.getEventWeapon(skill, null, IncreaseType.ADDITIVE_PERCENT);
                lore.add(Component.text(ItemEditor.getStatString(incDamage.getStatValue(StatType.DAMAGE)) + "% ", NamedTextColor.GREEN).append(StatType.DAMAGE.getNameWithSymbol()).decoration(TextDecoration.ITALIC, false));
                lore.add(Component.text(ItemEditor.getStatString(weapon.getStatValue(StatType.CRIT_CHANCE)) + " ", NamedTextColor.GREEN).append(StatType.CRIT_CHANCE.getNameWithSymbol()).decoration(TextDecoration.ITALIC, false));
            }
            case FORAGING -> {
                lore.add(Component.text(ItemEditor.getStatString(gather.getStatValue(StatType.FORAGING_FORTUNE)) + " ", NamedTextColor.GREEN).append(StatType.FORAGING_FORTUNE.getNameWithSymbol()).decoration(TextDecoration.ITALIC, false));
                lore.add(Component.text(ItemEditor.getStatString(weapon.getStatValue(StatType.STRENGTH)) + " ", NamedTextColor.GREEN).append(StatType.STRENGTH.getNameWithSymbol()).decoration(TextDecoration.ITALIC, false));
            }
            case FISHING -> {
                lore.add(Component.text("Increases the quality of treasure fished up each level", NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
                lore.add(Component.text(ItemEditor.getStatString(armor.getStatValue(StatType.HEALTH)) + " ", NamedTextColor.GREEN).append(StatType.HEALTH.getNameWithSymbol()).decoration(TextDecoration.ITALIC, false));
            }
            case ENCHANTING -> {
                lore.add(Component.text("Gain ", NamedTextColor.WHITE).append(Component.text(4 * (int) skill.getLevel() + "% ", NamedTextColor.GREEN)).append(Component.text("more experience orbs from all sources", NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
                lore.add(Component.text(ItemEditor.getStatString(ability.getStatValue(StatType.ABILITY_DAMAGE)) + " ", NamedTextColor.GREEN).append(StatType.ABILITY_DAMAGE.getNameWithSymbol()).decoration(TextDecoration.ITALIC, false));
                lore.add(Component.text(ItemEditor.getStatString(ability.getStatValue(StatType.INTELLIGENCE)) + " ", NamedTextColor.GREEN).append(StatType.INTELLIGENCE.getNameWithSymbol()).decoration(TextDecoration.ITALIC, false));
            }
            case ALCHEMY -> {
                lore.add(Component.text(ItemEditor.getStatString(ability.getStatValue(StatType.ABILITY_DAMAGE)) + " ", NamedTextColor.GREEN).append(StatType.ABILITY_DAMAGE.getNameWithSymbol()).decoration(TextDecoration.ITALIC, false));
                lore.add(Component.text(ItemEditor.getStatString(ability.getStatValue(StatType.INTELLIGENCE)) + " ", NamedTextColor.GREEN).append(StatType.INTELLIGENCE.getNameWithSymbol()).decoration(TextDecoration.ITALIC, false));
            }
            case BUILDING ->
                    lore.add(Component.text(ItemEditor.getStatString(armor.getStatValue(StatType.HEALTH)) + " ", NamedTextColor.GREEN).append(StatType.HEALTH.getNameWithSymbol()).decoration(TextDecoration.ITALIC, false));
        }
        return lore;
    }

    @NotNull
    public static WeaponStats getBonusWeapon(SkillObject skill, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return WeaponStats.createMult();
        }
        if (type == IncreaseType.ADDITIVE_PERCENT) {
            return WeaponStats.createZero();
        }
        return switch (skill.getType()) {
            case COMBAT -> new WeaponStats(0, 0, 0.5 * (int) skill.getLevel(), 0, 0, 0);
            case FORAGING -> new WeaponStats(0, (int) skill.getLevel(), 0, 0, 0, 0);
            default -> WeaponStats.createZero();
        };
    }

    @NotNull
    public static ArmorStats getBonusArmor(SkillObject skill, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return ArmorStats.createMult();
        }
        if (type == IncreaseType.ADDITIVE_PERCENT) {
            return ArmorStats.createZero();
        }
        return switch (skill.getType()) {
            case FARMING, FISHING -> new ArmorStats(2 * (int) skill.getLevel(), 0, 0);
            case BUILDING -> new ArmorStats((int) skill.getLevel(), 0, 0);
            case MINING -> new ArmorStats(0, (int) skill.getLevel(), 0);
            default -> ArmorStats.createZero();
        };
    }

    @NotNull
    public static AbilityStats getBonusAbility(SkillObject skill, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return AbilityStats.createMult();
        }
        if (type == IncreaseType.ADDITIVE_PERCENT) {
            return AbilityStats.createZero();
        }
        return switch (skill.getType()) {
            case ENCHANTING, ALCHEMY -> new AbilityStats(0.5 * (int) skill.getLevel(), (int) skill.getLevel());
            default -> AbilityStats.createZero();
        };
    }

    @NotNull
    public static FishingStats getBonusFishing(SkillObject skill, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return FishingStats.createMult();
        }
        if (type == IncreaseType.ADDITIVE_PERCENT) {
            return FishingStats.createZero();
        }
        return switch (skill.getType()) {
            default -> FishingStats.createZero();
        };
    }

    @NotNull
    public static MiningStats getBonusMining(SkillObject skill, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return MiningStats.createMult();
        }
        if (type == IncreaseType.ADDITIVE_PERCENT) {
            return MiningStats.createZero();
        }
        return switch (skill.getType()) {
            case MINING -> new MiningStats(0, 4 * (int) skill.getLevel(), 0, 0, 0);
            default -> MiningStats.createZero();
        };
    }

    @NotNull
    public static GatheringStats getBonusGathering(SkillObject skill, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return GatheringStats.createMult();
        }
        if (type == IncreaseType.ADDITIVE_PERCENT) {
            return GatheringStats.createZero();
        }
        return switch (skill.getType()) {
            case FARMING -> new GatheringStats(4 * (int) skill.getLevel(), 0);
            case FORAGING -> new GatheringStats(0, 4 * (int) skill.getLevel());
            default -> GatheringStats.createZero();
        };
    }

    @NotNull
    public static LuckStats getBonusLuck(SkillObject skill, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return LuckStats.createMult();
        }
        if (type == IncreaseType.ADDITIVE_PERCENT) {
            return LuckStats.createZero();
        }
        return switch (skill.getType()) {
            default -> LuckStats.createZero();
        };
    }

    @NotNull
    public static RegenStats getBonusRegen(SkillObject skill, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return RegenStats.createMult();
        }
        if (type == IncreaseType.ADDITIVE_PERCENT) {
            return RegenStats.createZero();
        }
        return switch (skill.getType()) {
            default -> RegenStats.createZero();
        };
    }

    @NotNull
    public static WisdomStats getBonusWisdom(SkillObject skill, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return WisdomStats.createMult();
        }
        if (type == IncreaseType.ADDITIVE_PERCENT) {
            return WisdomStats.createZero();
        }
        return switch (skill.getType()) {
            default -> WisdomStats.createZero();
        };
    }

    @NotNull
    public static WeaponStats getEventWeapon(SkillObject skill, EntityDamageEvent event, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return WeaponStats.createMult();
        }
        if (type == IncreaseType.INCREASE) {
            return WeaponStats.createZero();
        }
        return switch (skill.getType()) {
            case COMBAT -> new WeaponStats(4 * (int) skill.getLevel(), 0, 0, 0, 0, 0);
            default -> WeaponStats.createZero();
        };
    }

    @NotNull
    public static ArmorStats getEventArmor(SkillObject skillObject, EntityDamageEvent event, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return ArmorStats.createMult();
        }
        return ArmorStats.createZero();
    }

    @NotNull
    public static AbilityStats getEventAbility(SkillObject skillObject, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return AbilityStats.createMult();
        }
        return AbilityStats.createZero();
    }

    @NotNull
    public static FishingStats getEventFishing(SkillObject skillObject, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return FishingStats.createMult();
        }
        return FishingStats.createZero();
    }

    @NotNull
    public static MiningStats getEventMining(SkillObject skillObject, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return MiningStats.createMult();
        }
        return MiningStats.createZero();
    }

    @NotNull
    public static GatheringStats getEventGathering(SkillObject skillObject, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return GatheringStats.createMult();
        }
        return GatheringStats.createZero();
    }

    @NotNull
    public static LuckStats getEventLuck(SkillObject skillObject, IncreaseType type) {
        if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return LuckStats.createMult();
        }
        return LuckStats.createZero();
    }

}