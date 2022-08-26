package me.notpseudo.revolutionsmp.abilities;

import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.listeners.StatsListeners;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.UUID;

public class InstantTransmissionObject extends AbilityObject {

    private static ArrayList<UUID> speedUsed = new ArrayList<>();

    public InstantTransmissionObject(AbilitiesHolder holder) {
        super(holder, AbilityType.INSTANT_TRANSMISSION);
    }

    @Override
    public void use(Player player) {
        int tuners = 0;
        ItemInfo info = HOLDER.getHolder();
        if (info != null) {
            ModifierInfo modifier = info.getModifiers();
            if (modifier != null) {
                tuners = modifier.getTransmissionTuners();
            }
        }
        AbilitiesUtil.teleportWithSound(player, 8 + tuners * 2);
        takeMana(player);
        if(speedUsed.contains(player.getUniqueId())) {
            return;
        }
        speedUsed.add(player.getUniqueId());
        StatsListeners.updateStats(player);
        BukkitRunnable speed = new BukkitRunnable() {
            @Override
            public void run() {
                speedUsed.remove(player.getUniqueId());

            }
        };
        speed.runTaskLater(RevolutionSMP.getPlugin(), 60);
    }

    @Override
    public ArrayList<Component> getText() {
        ArrayList<Component> lines = new ArrayList<>(super.getText());
        lines.remove(lines.size() - 1);
        int tuners = 0;
        ItemInfo info = HOLDER.getHolder();
        if (info != null) {
            ModifierInfo modifier = info.getModifiers();
            if (modifier != null) {
                tuners = modifier.getTransmissionTuners();
            }
        }
        lines.add(Component.text("Teleport ", NamedTextColor.GRAY).append(Component.text(8 + tuners * 2 + " blocks ", NamedTextColor.GREEN)).append(Component.text("ahead of", NamedTextColor.GRAY)).decoration(TextDecoration.ITALIC, false));
        lines.add(Component.text("you and gain ", NamedTextColor.GRAY).append(Component.text("+50 ", NamedTextColor.GREEN)).append(Component.text("✦ Speed", NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        lines.add(Component.text("for ", NamedTextColor.GRAY).append(Component.text("3 seconds", NamedTextColor.GREEN)).append(Component.text(".", NamedTextColor.GRAY)).decoration(TextDecoration.ITALIC, false));
        lines.add(Component.text("Mana Cost: ", NamedTextColor.DARK_GRAY).append(Component.text(Math.round(super.getManaCost()), NamedTextColor.DARK_AQUA)));
        return lines;
    }

    @Override
    public ArmorStats getBonusArmor(Player player, IncreaseType inc) {
        if (inc == IncreaseType.MULTIPLICATIVE_PERCENT) {
            return null;
        }
        if (inc != IncreaseType.INCREASE && speedUsed.contains(player.getUniqueId())) {
            return new ArmorStats(0, 0, 50);
        }
        return null;
    }
}
