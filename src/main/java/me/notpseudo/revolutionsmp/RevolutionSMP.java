package me.notpseudo.revolutionsmp;

import me.notpseudo.revolutionsmp.abilities.Abilities;
import me.notpseudo.revolutionsmp.commands.PotatoBook;
import me.notpseudo.revolutionsmp.commands.Recombobulate;
import me.notpseudo.revolutionsmp.commands.ReforgeCommand;
import me.notpseudo.revolutionsmp.commands.Test;
import me.notpseudo.revolutionsmp.items.ArmorCreator;
import me.notpseudo.revolutionsmp.items.WeaponCreator;
import me.notpseudo.revolutionsmp.listeners.HealthListeners;
import me.notpseudo.revolutionsmp.listeners.ItemUse;
import me.notpseudo.revolutionsmp.listeners.StatsListeners;
import org.bukkit.plugin.java.JavaPlugin;

public final class RevolutionSMP extends JavaPlugin {

  @Override
  public void onEnable() {
    new StatsListeners(this);
    new HealthListeners(this);
    new ItemUse(this);
    new Test(this);
    new Recombobulate(this);
    new PotatoBook(this);
    new ReforgeCommand(this);
    WeaponCreator.createWeapons();
    ArmorCreator.createArmors();
    Abilities.createPassSet();

  }

  @Override
  public void onDisable() {
    HealthListeners.clearHealthBars();
  }

}
