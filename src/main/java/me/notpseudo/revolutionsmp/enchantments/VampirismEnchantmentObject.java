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

public class VampirismEnchantmentObject extends EnchantmentObject implements ActionEnchantment {

    private static int testNum = 0;
    private int level;

    public VampirismEnchantmentObject() {
        super(EnchantmentType.VAMPIRISM);
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

    }

    @Override
    public WeaponStats getEventWeapon(Player damager, LivingEntity target, IncreaseType inc) {
        if (inc == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return WeaponStats.createMult();
        }
        if (inc == IncreaseType.ADDITIVE_PERCENT) {
            return new WeaponStats(25 * level, 0, 0, 0, 0, 0);
        }
        return WeaponStats.createZero();
    }

}
