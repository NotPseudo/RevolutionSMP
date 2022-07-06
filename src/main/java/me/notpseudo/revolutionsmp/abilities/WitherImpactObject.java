package me.notpseudo.revolutionsmp.abilities;

import org.bukkit.entity.Player;

import java.util.UUID;

public class WitherImpactObject extends AbilityObject {

    public WitherImpactObject() {
        super(AbilityType.WITHER_IMPACT);
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
}