package me.notpseudo.revolutionsmp.commands;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.itemstats.ItemInfo;
import me.notpseudo.revolutionsmp.itemstats.ItemInfoDataType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class ViewItemStatsCommand implements CommandExecutor {

    public ViewItemStatsCommand(RevolutionSMP plugin) {
        plugin.getCommand("itemstats").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (!player.isOp()) {
                player.sendMessage(Component.text("You do not have permission to use this command", NamedTextColor.RED));
                return true;
            }
            if (player.getInventory().getItemInMainHand().getItemMeta() == null) {
                return true;
            }
            BukkitRunnable infoRun = new BukkitRunnable() {

                int count = 0;

                @Override
                public void run() {
                    if(count == 5) {
                        this.cancel();
                        return;
                    }
                    count++;
                    ItemInfo info = ItemEditor.getInfo(player.getInventory().getItemInMainHand());
                    if (info == null) {
                        this.cancel();
                        return;
                    }
                    player.sendMessage(ChatColor.BLUE + "Tick: " + count);
                    player.sendMessage(ChatColor.YELLOW + "ItemInfo Memory Address: " + info);
                    player.sendMessage(ChatColor.RED + "WeaponStats Memory Address: " + info.getWeaponStats());
                    player.sendMessage(ChatColor.GREEN + "ArmorStats Memory Address: " + info.getArmorStats());
                    player.sendMessage(ChatColor.AQUA + "AbilityStats Memory Address: " + info.getAbilityStats());
                    player.sendMessage(ChatColor.GOLD + "EnchantmentsHolder Memory Address: " + info.getEnchantmentsHolder());
                    player.sendMessage(ChatColor.GRAY + "----------------------------------------------------------------------");
                }
            };
            infoRun.runTaskTimer(RevolutionSMP.getPlugin(), 0, 1);
        }
        return true;
    }
}
