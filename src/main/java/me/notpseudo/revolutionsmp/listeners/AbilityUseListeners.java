package me.notpseudo.revolutionsmp.listeners;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.abilities.AbilityObject;
import me.notpseudo.revolutionsmp.abilities.AbilityUseType;
import me.notpseudo.revolutionsmp.itemstats.AbilitiesHolder;
import me.notpseudo.revolutionsmp.itemstats.ItemInfoDataType;
import me.notpseudo.revolutionsmp.customcrafting.items.ItemEditor;
import me.notpseudo.revolutionsmp.playerstats.PlayerStats;
import me.notpseudo.revolutionsmp.playerstats.PlayerStatsDataType;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

// Methods that run to simulate Abilities
public class AbilityUseListeners implements Listener {

    private static final NamespacedKey itemKey = ItemEditor.getItemKey();
    private static final NamespacedKey playerKey = StatsListeners.getPlayerStatsKey();

    public AbilityUseListeners(RevolutionSMP plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (!event.hasItem()) return;
        if (!event.getItem().hasItemMeta()) return;
        if (!event.getItem().getItemMeta().getPersistentDataContainer().has(itemKey, new ItemInfoDataType())) {
            return;
        }
        Player player = event.getPlayer();
        AbilitiesHolder abilitiesHolder = event.getItem().getItemMeta().getPersistentDataContainer().get(itemKey, new ItemInfoDataType()).getAbilitiesHolder();
        if (abilitiesHolder == null) {
            return;
        }
        AbilityUseType useType = null;
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (player.isSneaking() && abilitiesHolder.containsUseType(AbilityUseType.SNEAK_RIGHT_CLICK)) {
                useType = AbilityUseType.SNEAK_RIGHT_CLICK;
            } else {
                useType = AbilityUseType.RIGHT_CLICK;
            }
        } else if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            useType = AbilityUseType.LEFT_CLICK;
        }
        if(useType != null) {
            PlayerStats playerStats = player.getPersistentDataContainer().get(playerKey, new PlayerStatsDataType());
            for(AbilityObject ability : abilitiesHolder.getAbilities()) {
                if(ability.getAbilityType().getAbilityUseType() == useType) {
                    if(ability.canUse(player)) {
                        ability.use(player);
                    }
                }
            }
        }
    }

}