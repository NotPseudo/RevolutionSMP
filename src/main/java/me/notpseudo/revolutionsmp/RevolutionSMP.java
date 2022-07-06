package me.notpseudo.revolutionsmp;

import me.notpseudo.revolutionsmp.abilities.AbilitiesUtil;
import me.notpseudo.revolutionsmp.commands.*;
import me.notpseudo.revolutionsmp.items.ArmorCreator;
import me.notpseudo.revolutionsmp.items.WeaponCreator;
import me.notpseudo.revolutionsmp.listeners.HealthListeners;
import me.notpseudo.revolutionsmp.listeners.AbilityUseListeners;
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
        plugin = this;
        this.saveDefaultConfig();
        new StatsListeners(this);
        new MobListeners(this);
        new HealthListeners(this);
        new AbilityUseListeners(this);
        new GiveCommand(this);
        new RecombCommand(this);
        new ProfileCommand(this);
        new ResetStatsCommand(this);
        new PotatoBookCommand(this);
        new ReforgeCommand(this);
        new EnchantCommand(this);
        new AddAbilityCommand(this);
        new EditAbilityCommand(this);
        new ViewItemStatsCommand(this);
        new ParticleTestCommand(this);
        WeaponCreator.createWeapons();
        ArmorCreator.createArmors();
        AbilitiesUtil.createPassSet();
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