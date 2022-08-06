package me.notpseudo.revolutionsmp.enchantments;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.itemstats.IncreaseType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class VampirismEnchantmentObject extends EnchantmentObject implements ActionEnchantment, Listener {

    private static int testNum = 0;
    private int level;

    public VampirismEnchantmentObject() {
        super(EnchantmentType.VAMPIRISM);
        Bukkit.getPluginManager().registerEvents(this, RevolutionSMP.getPlugin());
        level = super.getLevel();
        testNum = 0;
    }

    public VampirismEnchantmentObject(int level) {
        super(EnchantmentType.VAMPIRISM, level);
        level = super.getLevel();
        testNum = 0;
    }


    @Override
    public void action(LivingEntity damager, LivingEntity target, double damage, boolean critical, double showDamage) {
        damager.sendMessage("Vampirism action Method Called");
        testNum++;
        damager.sendMessage("Test Num: " + testNum);
        damager.sendMessage("Subclass Level: " + level);
        damager.sendMessage("Superclass Level: " + super.getLevel());
    }

    @Override
    public WeaponStats getEventWeapon(Player damager, LivingEntity target, IncreaseType inc) {
        if (inc == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return new WeaponStats(1, 1, 1, 1, 1, 1);
        }
        if (inc == IncreaseType.ADDITIVE_PERCENT) {
            return new WeaponStats(25 * level, 0, 0, 0, 0, 0);
        }
        return new WeaponStats(0, 0, 0, 0, 0, 0);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if(!(event.getDamager() instanceof Player player)) {
            return;
        }
        if(player.getInventory().getItemInMainHand().getItemMeta() == null) {
            return;
        }
        ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
        if(meta == null) {
            return;
        }
        ItemInfo itemInfo = meta.getPersistentDataContainer().get(ItemEditor.getItemKey(), new ItemInfoDataType());
        if(itemInfo == null) {
            return;
        }
        EnchantmentsHolder holder = itemInfo.getEnchantmentsHolder();
        if(holder == null) {
            return;
        }
        if(holder.getEnchants().contains(this)) {
            testNum++;
            player.sendMessage(ChatColor.GREEN + "Hit recognized test num is now: " + testNum);
        }
    }

}
