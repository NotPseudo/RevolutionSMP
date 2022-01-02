package me.notpseudo.revolutionsmp.listeners;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.datacontainers.AbilityStats;
import me.notpseudo.revolutionsmp.datacontainers.AbilityStatsDataType;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataContainer;

// Methods that run to simulate Abilities
public class ItemUse implements Listener {

  private RevolutionSMP plugin;

  public ItemUse(RevolutionSMP plugin) {
    this.plugin = plugin;
    Bukkit.getPluginManager().registerEvents(this, this.plugin);
  }

  @EventHandler
  public void onRightClick(PlayerInteractEvent event) {
    if(!event.hasItem()) {
      return;
    }
    if(!event.getItem().hasItemMeta()) {
      return;
    }
    PersistentDataContainer container = event.getItem().getItemMeta().getPersistentDataContainer();
    if(event.getItem().getItemMeta().getPersistentDataContainer().has(ItemEditor.getAbilityKey(), new AbilityStatsDataType())) {
      Player player = event.getPlayer();
      NamespacedKey abilityKey = ItemEditor.getAbilityKey();
      AbilityStats abilityStats = container.get(abilityKey, new AbilityStatsDataType());
      if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
        if(player.isSneaking()) {
          if(abilityStats.getSneakRightClickAbility() != null) {
            abilityStats.getRightClickAbility().use(player);
          } else if(abilityStats.getRightClickAbility() != null) {
            abilityStats.getRightClickAbility().use(player);
          }
        } else {
          if(abilityStats.getRightClickAbility() != null) {
            abilityStats.getRightClickAbility().use(player);
          }
        }
      } else if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
        if(abilityStats.getLeftClickAbility() != null) {
          abilityStats.getLeftClickAbility().use(player);
        }
      }
    }
  }

}
