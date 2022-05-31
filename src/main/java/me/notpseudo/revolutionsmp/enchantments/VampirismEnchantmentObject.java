package me.notpseudo.revolutionsmp.enchantments;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class VampirismEnchantmentObject extends EnchantmentObject implements Listener {


    public VampirismEnchantmentObject() {
        super(EnchantmentType.VAMPIRISM);
    }

    public VampirismEnchantmentObject(int level) {
        super(EnchantmentType.VAMPIRISM, level);
    }

}
