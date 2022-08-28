package me.notpseudo.revolutionsmp.abilities;

import me.notpseudo.revolutionsmp.itemstats.AbilitiesHolder;
import me.notpseudo.revolutionsmp.itemstats.ArmorStats;
import me.notpseudo.revolutionsmp.itemstats.IncreaseType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class WitherImpactObject extends AbilityObject {

    public WitherImpactObject(AbilitiesHolder holder) {
        super(holder, AbilityType.WITHER_IMPACT);
    }

    @Override
    public void use(Player player) {
        UUID playerID = player.getUniqueId();
        AbilitiesUtil.teleportWithSound(player, 10);
        AbilitiesUtil.implosion(player, 6);
        if (!AbilityType.WITHER_SHIELD.getCooldownList().contains(playerID)) {
            AbilitiesUtil.witherShield(player);
            AbilityType.WITHER_SHIELD.addToCooldownList(playerID, AbilityType.WITHER_SHIELD.getCooldown());
        }
        takeMana(player);
        AbilityType.WITHER_IMPACT.addToCooldownList(playerID, getCooldown());
    }

    @Override
    public @NotNull ArmorStats getEventArmor(LivingEntity damager, Player target, EntityDamageEvent event, IncreaseType inc) {
        return AbilityType.WITHER_SHIELD.getEventArmor(damager, target, event, inc);
    }

}