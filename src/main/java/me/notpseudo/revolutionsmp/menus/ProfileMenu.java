package me.notpseudo.revolutionsmp.menus;

import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.itemstats.StatType;
import me.notpseudo.revolutionsmp.listeners.StatsListeners;
import me.notpseudo.revolutionsmp.playerstats.PlayerStats;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ProfileMenu extends Menu {

    public ProfileMenu(Player player) {
        super(player);
    }

    @Override
    public Component getTitle() {
        return null;
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void setItems() {
        PlayerInventory playerInv = player.getInventory();
        inventory.setItem(2, makeMenuItem(playerInv.getItemInMainHand().clone(), null));
        if (playerInv.getHelmet() != null) {
            inventory.setItem(11, makeMenuItem(playerInv.getHelmet().clone(), null));
        } else {
            inventory.setItem(11, makeMenuItem(null, null));
        }
        if (playerInv.getChestplate() != null) {
            inventory.setItem(20, makeMenuItem(playerInv.getChestplate().clone(), null));
        } else {
            inventory.setItem(20, makeMenuItem(null, null));
        }
        if (playerInv.getLeggings() != null) {
            inventory.setItem(29, makeMenuItem(playerInv.getLeggings().clone(), null));
        } else {
            inventory.setItem(29, makeMenuItem(null, null));
        }
        if (playerInv.getBoots() != null) {
            inventory.setItem(38, makeMenuItem(playerInv.getBoots().clone(), null));
        } else {
            inventory.setItem(38, makeMenuItem(null, null));
        }

        ItemStack general = new ItemStack(Material.COMPASS, 1);
        ItemMeta generalMeta = general.getItemMeta();
        generalMeta.displayName(Component.text("General Stats", NamedTextColor.DARK_AQUA).decoration(TextDecoration.ITALIC, false));
        List<Component> generalLore = new ArrayList<>();
        generalLore.add(Component.text("Statistics useful in combat and usual situations", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        generalLore.add(Component.empty());
        PlayerStats stats = StatsListeners.getPlayerStats(player);
        generalLore.add(StatType.HEALTH.getNameWithSymbol().append(Component.text(" " + stats.getStat(StatType.HEALTH), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        generalLore.add(StatType.DEFENSE.getNameWithSymbol().append(Component.text(" " + stats.getStat(StatType.DEFENSE), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        generalLore.add(StatType.STRENGTH.getNameWithSymbol().append(Component.text(" " + stats.getStat(StatType.STRENGTH), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        generalLore.add(StatType.SPEED.getNameWithSymbol().append(Component.text(" " + stats.getStat(StatType.SPEED), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        generalLore.add(StatType.CRIT_CHANCE.getNameWithSymbol().append(Component.text(" " + stats.getStat(StatType.CRIT_CHANCE), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        generalLore.add(StatType.CRIT_DAMAGE.getNameWithSymbol().append(Component.text(" " + stats.getStat(StatType.CRIT_DAMAGE), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        generalLore.add(StatType.INTELLIGENCE.getNameWithSymbol().append(Component.text(" " + stats.getStat(StatType.INTELLIGENCE), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        generalLore.add(StatType.MINING_SPEED.getNameWithSymbol().append(Component.text(" " + stats.getStat(StatType.MINING_SPEED), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        generalLore.add(StatType.ATTACK_SPEED.getNameWithSymbol().append(Component.text(" " + stats.getStat(StatType.ATTACK_SPEED), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        generalLore.add(StatType.SEA_CREATURE_CHANCE.getNameWithSymbol().append(Component.text(" " + stats.getStat(StatType.SEA_CREATURE_CHANCE), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        generalLore.add(StatType.MAGIC_FIND.getNameWithSymbol().append(Component.text(" " + stats.getStat(StatType.MAGIC_FIND), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        generalLore.add(StatType.TRUE_DEFENSE.getNameWithSymbol().append(Component.text(" " + stats.getStat(StatType.TRUE_DEFENSE), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        generalLore.add(StatType.PET_LUCK.getNameWithSymbol().append(Component.text(" " + stats.getStat(StatType.PET_LUCK), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        generalLore.add(StatType.FEROCITY.getNameWithSymbol().append(Component.text(" " + stats.getStat(StatType.FEROCITY), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        generalLore.add(StatType.ABILITY_DAMAGE.getNameWithSymbol().append(Component.text(" " + stats.getStat(StatType.ABILITY_DAMAGE), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        generalMeta.lore(generalLore);
        inventory.setItem(24, makeMenuItem(general, null));

        ItemStack skill = new ItemStack(Material.CLOCK, 1);
        ItemMeta skillMeta = skill.getItemMeta();
        skillMeta.displayName(Component.text("Skill Stats", NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        List<Component> skillLore = new ArrayList<>();
        skillLore.add(Component.text("Skill statistics that improve some activities", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        skillLore.add(Component.empty());
        generalLore.add(StatType.FORAGING_FORTUNE.getNameWithSymbol().append(Component.text(" " + stats.getStat(StatType.FARMING_FORTUNE), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        generalLore.add(StatType.MINING_FORTUNE.getNameWithSymbol().append(Component.text(" " + stats.getStat(StatType.MINING_FORTUNE), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        generalLore.add(StatType.FARMING_FORTUNE.getNameWithSymbol().append(Component.text(" " + stats.getStat(StatType.FARMING_FORTUNE), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        generalLore.add(StatType.BREAKING_POWER.getNameWithSymbol().append(Component.text(" " + stats.getStat(StatType.BREAKING_POWER), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        generalLore.add(StatType.PRISTINE.getNameWithSymbol().append(Component.text(" " + stats.getStat(StatType.PRISTINE), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        generalLore.add(StatType.PURITY.getNameWithSymbol().append(Component.text(" " + stats.getStat(StatType.PURITY), NamedTextColor.WHITE)).decoration(TextDecoration.ITALIC, false));
        skillMeta.lore(skillLore);
        inventory.setItem(25, makeMenuItem(skill, null));

        ItemStack back = new ItemStack(Material.ARROW, 1);
        back.getItemMeta().displayName(Component.text("Back", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        inventory.setItem(48, makeMenuItem(back, MenuType.MAIN));

        ItemStack close = new ItemStack(Material.BARRIER, 1);
        close.getItemMeta().displayName(Component.text("Close", NamedTextColor.RED).decoration(TextDecoration.ITALIC, false));
        inventory.setItem(49, makeMenuItem(close, MenuType.CLOSE));
    }

}