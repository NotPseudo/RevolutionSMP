package me.notpseudo.revolutionsmp.listeners;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.abilities.Ability;
import me.notpseudo.revolutionsmp.abilities.AbilityType;
import me.notpseudo.revolutionsmp.statobjects.AbilityStats;
import me.notpseudo.revolutionsmp.statobjects.ItemInfoDataType;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;

// Methods that run to simulate Abilities
public class ItemUse implements Listener {

  public ItemUse(RevolutionSMP plugin) {
    Bukkit.getPluginManager().registerEvents(this, plugin);
  }

  @EventHandler
  public void onRightClick(PlayerInteractEvent event) {
    if(!event.hasItem()) return;
    if(!event.getItem().hasItemMeta()) return;
    if(!event.getItem().getItemMeta().getPersistentDataContainer().has(ItemEditor.getItemKey(), new ItemInfoDataType())) return;
    Player player = event.getPlayer();
    AbilityStats abilityStats = event.getItem().getItemMeta().getPersistentDataContainer().get(ItemEditor.getItemKey(), new ItemInfoDataType()).getAbilityStats();
    if(abilityStats == null) return;
    List<Ability> abilityList = abilityStats.getAbilityList();
    if(abilityList == null) return;
    if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
      if(player.isSneaking()) {
        int usedAbilities = 0;
        for(Ability ability : abilityList) {
          if(ability.getAbilityType() == AbilityType.SNEAK_RIGHT_CLICK) {
            ability.use(player);
            usedAbilities++;
          }
        }
        if(usedAbilities == 0) {
          for(Ability ability : abilityList) {
            if(ability.getAbilityType() == AbilityType.RIGHT_CLICK) {
              ability.use(player);
            }
          }
        }
      } else {
        for(Ability ability : abilityList) {
          if(ability.getAbilityType() == AbilityType.RIGHT_CLICK) {
            ability.use(player);
          }
        }
      }
    } else if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
      for(Ability ability : abilityList) {
        if(ability.getAbilityType() == AbilityType.LEFT_CLICK) {
          ability.use(player);
        }
      }
    }
  }

}