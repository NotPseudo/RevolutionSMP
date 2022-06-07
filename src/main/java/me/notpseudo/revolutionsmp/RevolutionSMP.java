package me.notpseudo.revolutionsmp;

import me.notpseudo.revolutionsmp.abilities.Abilities;
import me.notpseudo.revolutionsmp.commands.*;
import me.notpseudo.revolutionsmp.items.ArmorCreator;
import me.notpseudo.revolutionsmp.items.WeaponCreator;
import me.notpseudo.revolutionsmp.listeners.HealthListeners;
import me.notpseudo.revolutionsmp.listeners.ItemUse;
import me.notpseudo.revolutionsmp.listeners.MobListeners;
import me.notpseudo.revolutionsmp.listeners.StatsListeners;
import org.bukkit.plugin.java.JavaPlugin;

public final class RevolutionSMP extends JavaPlugin {

    private static RevolutionSMP plugin;

    /**
     * Method that runs when the plugin is loaded
     */
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        new StatsListeners(this);
        new MobListeners(this);
        new HealthListeners(this);
        new ItemUse(this);
        new TestCommand(this);
        new RecombCommand(this);
        new ProfileCommand(this);
        new ResetStatsCommand(this);
        new PotatoBookCommand(this);
        new ReforgeCommand(this);
        new EnchantCommand(this);
        new ViewItemStatsCommand(this);
        WeaponCreator.createWeapons();
        ArmorCreator.createArmors();
        Abilities.createPassSet();
        plugin = this;
    }

    /**
     * Method that runs when the plugin is unloaded
     */
    @Override
    public void onDisable() {

    }

    /**
     * Returns an instance of the plugin for other classes to use
     *
     * @return Instance of the plugin
     */
    public static RevolutionSMP getPlugin() {
        return plugin;
    }

}