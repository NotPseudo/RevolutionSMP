package me.notpseudo.revolutionsmp.enchantments;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.listeners.MobListeners;
import me.notpseudo.revolutionsmp.listeners.StatsListeners;
import me.notpseudo.revolutionsmp.mobstats.MobInfo;
import me.notpseudo.revolutionsmp.mobstats.MobInfoDataType;
import me.notpseudo.revolutionsmp.playerstats.PlayerStats;
import me.notpseudo.revolutionsmp.playerstats.PlayerStatsDataType;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class LethalityEnchantmentObject extends EnchantmentObject implements ActionEnchantment, Listener {

    private HashMap<UUID, Integer> hits;
    private static final NamespacedKey mobKey = MobListeners.getMobKey();
    private static final NamespacedKey playerKey = StatsListeners.getPlayerStatsKey();

    public LethalityEnchantmentObject() {
        super(EnchantmentType.LETHALITY);
        hits = new HashMap<>();
    }

    public LethalityEnchantmentObject(int level) {
        super(EnchantmentType.LETHALITY, level);
        hits = new HashMap<>();
    }

    @Override
    public void action(LivingEntity damager, LivingEntity target, double damage, boolean critical, double showDamage) {
        UUID targetUUID = target.getUniqueId();
        if(!hits.containsKey(targetUUID)) {
            hits.put(targetUUID, 0);
        }
        if (hits.get(targetUUID) == 4) {
            return;
        }
        final MobInfo[] targetStats = {target.getPersistentDataContainer().get(mobKey, new MobInfoDataType())};
        double percentDecrease;
        int level = super.getLevel();
        switch (level) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                percentDecrease = level * .012;
            case 6:
            default:
                percentDecrease = Math.min(0.99, level * .015);
        }
        double remainingDefense = 1 - percentDecrease;
        if (hits.containsKey(targetUUID)) {
            hits.put(targetUUID, hits.get(targetUUID) + 1);
        } else {
            hits.put(targetUUID, 1);
        }
        if (target instanceof Player) {
            final PlayerStats[] playerStats = {target.getPersistentDataContainer().get(playerKey, new PlayerStatsDataType())};
            if (playerStats[0] == null) {
                playerStats[0] = new PlayerStats();
            }
            playerStats[0].setDefenseMultiplier(playerStats[0].getDefenseMultiplier() * remainingDefense);
            target.getPersistentDataContainer().set(playerKey, new PlayerStatsDataType(), playerStats[0]);
            BukkitRunnable playerLethality = new BukkitRunnable() {
                @Override
                public void run() {
                    if (!hits.containsKey(targetUUID)) {
                        this.cancel();
                        return;
                    }
                    playerStats[0] = target.getPersistentDataContainer().get(playerKey, new PlayerStatsDataType());
                    playerStats[0].setDefenseMultiplier(playerStats[0].getDefense() / remainingDefense);
                    target.getPersistentDataContainer().set(playerKey, new PlayerStatsDataType(), playerStats[0]);
                    if (hits.get(targetUUID) - 1 == 0) {
                        hits.remove(targetUUID);
                    } else {
                        hits.put(targetUUID, hits.get(targetUUID) - 1);
                    }
                }
            };
            playerLethality.runTaskLater(RevolutionSMP.getPlugin(), 80);
        } else {
            if(targetStats[0] == null) {
                return;
            }
            targetStats[0].setDefense(targetStats[0].getDefense() * remainingDefense);
            target.getPersistentDataContainer().set(mobKey, new MobInfoDataType(), targetStats[0]);
            BukkitRunnable mobLethality = new BukkitRunnable() {
                @Override
                public void run() {
                    if (target.isDead()) {
                        hits.remove(targetUUID);
                        this.cancel();
                        return;
                    }
                    targetStats[0] = target.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
                    targetStats[0].setDefense(targetStats[0].getDefense() / remainingDefense);
                    target.getPersistentDataContainer().set(mobKey, new MobInfoDataType(), targetStats[0]);
                }
            };
            mobLethality.runTaskLater(RevolutionSMP.getPlugin(), 80);
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        hits.remove(event.getEntity().getUniqueId());
    }

}