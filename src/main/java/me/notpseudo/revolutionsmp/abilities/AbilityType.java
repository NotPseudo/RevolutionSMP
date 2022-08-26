package me.notpseudo.revolutionsmp.abilities;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.itemstats.IncreaseType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.UUID;

// Enum of abilities that can be placed on items
public enum AbilityType {
    INSTANT_TRANSMISSION {
        @Override
        public double getManaCost() {
            return 100;
        }

        @Override
        public AbilityObject createObject(AbilitiesHolder holder) {
            return new InstantTransmissionObject(holder);
        }
    },
    WITHER_IMPACT {

        private static ArrayList<UUID> impactCooldownList = new ArrayList<>();

        @Override
        public void use(Player player) {

        }

        @Override
        public double getManaCost() {
            return 300;
        }

        @Override
        public double getBaseAbilityDamage() {
            return 10000;
        }

        @Override
        public double getAbilityScaling() {
            return 0.3;
        }

        @Override
        public boolean isWitherScrollAbility() {
            return true;
        }

        @Override
        public double getCooldown() {
            return 0.15;
        }

        @Override
        public boolean showCooldown() {
            return false;
        }

        @Override
        public ArrayList<UUID> getCooldownList() {
            return impactCooldownList;
        }

        @Override
        public AbilityObject createObject(AbilitiesHolder holder) {
            return new WitherImpactObject(holder);
        }

        @Override
        public ArrayList<Component> getDescription() {
            ArrayList<Component> description = new ArrayList<>();
            description.add(Component.text("Teleport ", NamedTextColor.GRAY).append(Component.text("10 blocks ", NamedTextColor.GREEN).append(Component.text("ahead of", NamedTextColor.GRAY))));
            description.add(Component.text("you. Then implode dealing", NamedTextColor.GRAY));
            description.add(Component.text(Math.round(getBaseAbilityDamage()) + " ", NamedTextColor.RED).append(Component.text("damage to nearby", NamedTextColor.GRAY)));
            description.add(Component.text("enemies. Also applies the wither", NamedTextColor.GRAY));
            description.add(Component.text("shield scroll ability reducing", NamedTextColor.GRAY));
            description.add(Component.text("damage taken and granting an", NamedTextColor.GRAY));
            description.add(Component.text("absorption shield for ", NamedTextColor.GRAY).append(Component.text(5, NamedTextColor.YELLOW)));
            description.add(Component.text("seconds.", NamedTextColor.GRAY));
            return description;
        }
    },
    IMPLOSION {

        private static ArrayList<UUID> implosionCooldownList = new ArrayList<>();

        @Override
        public void use(Player player) {
            AbilitiesUtil.implosion(player, 6);
        }

        @Override
        public double getManaCost() {
            return 300;
        }

        @Override
        public double getBaseAbilityDamage() {
            return 10000;
        }

        @Override
        public double getAbilityScaling() {
            return 0.3;
        }

        @Override
        public boolean isWitherScrollAbility() {
            return true;
        }

        @Override
        public double getCooldown() {
            return 10;
        }

        @Override
        public ArrayList<UUID> getCooldownList() {
            return implosionCooldownList;
        }

        @Override
        public ArrayList<Component> getDescription() {
            ArrayList<Component> description = new ArrayList<>();
            description.add(Component.text("Deals ", NamedTextColor.GRAY).append(Component.text(Math.round(getBaseAbilityDamage()) + " ", NamedTextColor.RED).append(Component.text("damage to", NamedTextColor.GRAY))));
            description.add(Component.text("nearby enemies.", NamedTextColor.GRAY));
            return description;
        }

    },
    WITHER_SHIELD {

        private static ArrayList<UUID> witherShieldCooldownList = new ArrayList<>();

        @Override
        public void use(Player player) {
            AbilitiesUtil.witherShield(player);
        }

        @Override
        public double getManaCost() {
            return 150;
        }

        @Override
        public double getBaseAbilityDamage() {
            return 10000;
        }

        @Override
        public double getAbilityScaling() {
            return 0.3;
        }

        @Override
        public boolean isWitherScrollAbility() {
            return true;
        }

        @Override
        public double getCooldown() {
            return 10;
        }

        @Override
        public ArrayList<UUID> getCooldownList() {
            return witherShieldCooldownList;
        }

        @Override
        public ArmorStats getEventArmor(LivingEntity damager, Player target, IncreaseType inc) {
            if (inc == IncreaseType.MULTIPLICATIVE_PERCENT) {
                if (witherShieldCooldownList.contains(target.getUniqueId())) {
                    return new ArmorStats(0.9, 1, 1, 1);
                }
                return null;
            }
            return null;
        }

        @Override
        public ArrayList<Component> getDescription() {
            ArrayList<Component> description = new ArrayList<>();
            description.add(Component.text("Reduces damage taken by ", NamedTextColor.GRAY).append(Component.text("10%", NamedTextColor.RED)));
            description.add(Component.text("for ", NamedTextColor.GRAY).append(Component.text("5 ", NamedTextColor.YELLOW).append(Component.text("seconds. Also grants", NamedTextColor.GRAY))));
            description.add(Component.text("an absorption shield that gives", NamedTextColor.GRAY));
            description.add(Component.text("150% ", NamedTextColor.BLUE).append(Component.text("of your Critical Damage", NamedTextColor.GRAY)));
            description.add(Component.text("as health. After ", NamedTextColor.GRAY).append(Component.text("5 ", NamedTextColor.YELLOW).append(Component.text("seconds", NamedTextColor.GRAY))));
            description.add(Component.text("50% ", NamedTextColor.GREEN).append(Component.text("of the shield is", NamedTextColor.GRAY)));
            description.add(Component.text("converted into healing.", NamedTextColor.GRAY));
            return description;
        }

    },
    SHADOW_WARP {

        private static ArrayList<UUID> shadowWarpCooldownList = new ArrayList<>();

        @Override
        public void use(Player player) {

        }

        @Override
        public double getManaCost() {
            return 300;
        }

        @Override
        public double getBaseAbilityDamage() {
            return 10000;
        }

        @Override
        public double getAbilityScaling() {
            return 0.3;
        }

        @Override
        public boolean isWitherScrollAbility() {
            return true;
        }

        @Override
        public double getCooldown() {
            return 10;
        }

        @Override
        public ArrayList<UUID> getCooldownList() {
            return shadowWarpCooldownList;
        }

        @Override
        public ArrayList<Component> getDescription() {
            ArrayList<Component> description = new ArrayList<>();
            description.add(Component.text("Create a spacial distortion", NamedTextColor.GRAY));
            description.add(Component.text(10 + " ", NamedTextColor.YELLOW).append(Component.text("blocks ahead of you that", NamedTextColor.GRAY)));
            description.add(Component.text("sucks all enemies around it.", NamedTextColor.GRAY));
            description.add(Component.text("Use this ability again within", NamedTextColor.GRAY));
            description.add(Component.text(5 + " ", NamedTextColor.YELLOW).append(Component.text("seconds to detonate the", NamedTextColor.GRAY)));
            description.add(Component.text("warp and deal damage to enemies", NamedTextColor.GRAY));
            description.add(Component.text("near it.", NamedTextColor.GRAY));
            return description;
        }

        @Override
        public AbilityObject createObject(AbilitiesHolder holder) {
            return new ShadowWarpObject(holder);
        }

    },
    GRAVITY_STORM {

        private static ArrayList<UUID> gravityStormCooldownList = new ArrayList<>();

        @Override
        public void use(Player player) {
            AbilitiesUtil.gravityStorm(player);
        }

        @Override
        public double getManaCost() {
            return 1200;
        }

        @Override
        public double getCooldown() {
            return 30;
        }

        @Override
        public ArrayList<UUID> getCooldownList() {
            return gravityStormCooldownList;
        }

        @Override
        public ArrayList<Component> getDescription() {
            ArrayList<Component> description = new ArrayList<>();
            description.add(Component.text("Create a large ", NamedTextColor.GRAY).append(Component.text("rift ", NamedTextColor.DARK_PURPLE).append(Component.text("at aimed", NamedTextColor.GRAY))));
            description.add(Component.text("location, pulling all mobs", NamedTextColor.GRAY));
            description.add(Component.text("together.", NamedTextColor.GRAY));
            description.add(Component.text("Regen mana 10x slower for 3s", NamedTextColor.DARK_GRAY));
            description.add(Component.text("after cast.", NamedTextColor.DARK_GRAY));
            return description;
        }

        @Override
        public AbilityUseType getAbilityUseType() {
            return AbilityUseType.LEFT_CLICK;
        }
    };

    public void use(Player player) {

    }

    public String toString() {
        String[] split = super.toString().split("_");
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            name.append(split[i].charAt(0)).append(split[i].substring(1).toLowerCase());
            if (i < split.length - 1) {
                name.append(" ");
            }
        }
        return name.toString();
    }

    public double getManaCost() {
        return 0;
    }

    public double getCooldown() {
        return 0;
    }

    public boolean showCooldown() {
        return true;
    }

    public ArrayList<UUID> getCooldownList() {
        return null;
    }

    public void addToCooldownList(UUID playerID, double time) {
        if (getCooldownList() != null && !getCooldownList().contains(playerID) && !AbilitiesUtil.getNoCooldown().contains(playerID)) {
            getCooldownList().add(playerID);
            BukkitRunnable remove = new BukkitRunnable() {
                @Override
                public void run() {
                    getCooldownList().remove(playerID);
                }
            };
            remove.runTaskLater(RevolutionSMP.getPlugin(), Math.round(time * 20));
        }
    }

    public double getBaseAbilityDamage() {
        return 0;
    }

    public double getAbilityScaling() {
        return 0;
    }

    public boolean isWitherScrollAbility() {
        return false;
    }

    public boolean allowCrit() {
        return false;
    }

    public ArrayList<Component> getDescription() {
        return new ArrayList<>();
    }

    public AbilityUseType getAbilityUseType() {
        return AbilityUseType.RIGHT_CLICK;
    }

    public AbilityObject createObject(AbilitiesHolder holder) {
        return new AbilityObject(holder, this);
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

}