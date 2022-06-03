package me.notpseudo.revolutionsmp.enchantments;

import me.notpseudo.revolutionsmp.listeners.StatsListeners;
import me.notpseudo.revolutionsmp.playerstats.PlayerStats;
import me.notpseudo.revolutionsmp.playerstats.PlayerStatsDataType;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class ManaStealEnchantmentObject extends EnchantmentObject implements ActionEnchantment {

    private static final NamespacedKey playerKey = StatsListeners.getPlayerStatsKey();

    public ManaStealEnchantmentObject() {
        super(EnchantmentType.MANA_STEAL);
    }

    public ManaStealEnchantmentObject(int level) {
        super(EnchantmentType.MANA_STEAL, level);
    }

    @Override
    public void action(LivingEntity damager, LivingEntity target, double damage, boolean critical, double showDamage) {
        if (!(damager instanceof Player)) {
            return;
        }
        PlayerStats playerStats = damager.getPersistentDataContainer().get(playerKey, new PlayerStatsDataType());
        if (playerStats == null) {
            playerStats = new PlayerStats();
        }
        double percentToAdd = super.getLevel() * 0.0025;
        double mana = playerStats.getMana(), intelligence = playerStats.getIntelligence();
        if (mana != intelligence) {
            double addMana = percentToAdd * intelligence;
            double finalMana = Math.min((mana + addMana), intelligence);
            playerStats.setMana(finalMana);
        }
        damager.getPersistentDataContainer().set(playerKey, new PlayerStatsDataType(), playerStats);
    }
}