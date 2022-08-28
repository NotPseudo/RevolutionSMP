package me.notpseudo.revolutionsmp.listeners;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.abilities.AbilityObject;
import me.notpseudo.revolutionsmp.abilities.AbilityUseType;
import me.notpseudo.revolutionsmp.items.ItemType;
import me.notpseudo.revolutionsmp.itemstats.AbilitiesHolder;
import me.notpseudo.revolutionsmp.itemstats.ItemInfo;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

// Methods that run to simulate Abilities
public class AbilityUseListeners implements Listener {

    private static final NamespacedKey itemKey = ItemEditor.getItemKey();
    private static final NamespacedKey playerKey = StatsListeners.getPlayerStatsKey();

    public AbilityUseListeners(RevolutionSMP plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item == null || item.getType() == Material.AIR) {
            return;
        }
        ItemInfo info = ItemEditor.getInfo(item);
        if (info == null) {
            return;
        }
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();
            if (block != null && block.getState() instanceof Container) {
                return;
            }
        }
        if (info.getItemType() == ItemType.VANILLA_ITEM) {
            return;
        }
        Player player = event.getPlayer();
        AbilitiesHolder abilitiesHolder = info.getAbilitiesHolder();
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