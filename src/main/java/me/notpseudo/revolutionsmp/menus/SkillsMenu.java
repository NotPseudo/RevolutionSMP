package me.notpseudo.revolutionsmp.menus;

import me.notpseudo.revolutionsmp.skills.SkillHolder;
import me.notpseudo.revolutionsmp.skills.SkillType;
import me.notpseudo.revolutionsmp.skills.SkillUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SkillsMenu extends Menu {


    public SkillsMenu(Player player) {
        super(player);
    }

    @Override
    public Component getTitle() {
        return Component.text("Skills");
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void setItems() {
        ItemStack skills = new ItemStack(Material.NETHERITE_SWORD, 1);
        ItemMeta skillsMeta = skills.getItemMeta();
        skillsMeta.displayName(Component.text("Skills", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        skillsMeta.lore(List.of(Component.text("View your Skill progression and rewards", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false)));
        skills.setItemMeta(skillsMeta);
        inventory.setItem(4, makeMenuItem(skills, null));

        SkillHolder holder = SkillUtils.getHolder(player);

        ItemStack farming = new ItemStack(Material.GOLDEN_HOE, 1);
        ItemMeta farmingMeta = farming.getItemMeta();
        farmingMeta.displayName(Component.text("Farming", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        List<Component> farmingLore = new ArrayList<>();
        farmingLore.add(Component.text("Harvest crops to earn Farming XP!", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        farmingLore.add(Component.empty());
        farmingLore.addAll(getProgressList(holder.getSkill(SkillType.FARMING)));
        farmingMeta.lore(farmingLore);
        farming.setItemMeta(farmingMeta);
        inventory.setItem(19, makeMenuItem(farming, null));

        ItemStack mining = new ItemStack(Material.STONE_PICKAXE, 1);
        ItemMeta miningMeta = mining.getItemMeta();
        miningMeta.displayName(Component.text("Mining", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        List<Component> miningLore = new ArrayList<>();
        miningLore.add(Component.text("Explore deep into the ground", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        miningLore.add(Component.text("for ores and valuable materials to earn Mining XP!", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        miningLore.add(Component.empty());
        miningLore.addAll(getProgressList(holder.getSkill(SkillType.MINING)));
        miningMeta.lore(miningLore);
        mining.setItemMeta(miningMeta);
        inventory.setItem(20, makeMenuItem(mining, null));

        ItemStack combat = new ItemStack(Material.STONE_SWORD, 1);
        ItemMeta combatMeta = combat.getItemMeta();
        combatMeta.displayName(Component.text("Combat", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        List<Component> combatLore = new ArrayList<>();
        combatLore.add(Component.text("Slay mobs to earn Combat XP!", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        combatLore.add(Component.empty());
        combatLore.addAll(getProgressList(holder.getSkill(SkillType.COMBAT)));
        combatMeta.lore(combatLore);
        combat.setItemMeta(combatMeta);
        inventory.setItem(21, makeMenuItem(combat, null));

        ItemStack foraging = new ItemStack(Material.JUNGLE_SAPLING, 1);
        ItemMeta foragingMeta = foraging.getItemMeta();
        foragingMeta.displayName(Component.text("Foraging", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        List<Component> foragingLore = new ArrayList<>();
        foragingLore.add(Component.text("Cut trees and forage plants to earn Foraging XP!", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        foragingLore.add(Component.empty());
        foragingLore.addAll(getProgressList(holder.getSkill(SkillType.FORAGING)));
        foragingMeta.lore(foragingLore);
        foraging.setItemMeta(foragingMeta);
        inventory.setItem(22, makeMenuItem(foraging, null));

        ItemStack fishing = new ItemStack(Material.FISHING_ROD, 1);
        ItemMeta fishingMeta = fishing.getItemMeta();
        fishingMeta.displayName(Component.text("Fishing", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        List<Component> fishingLore = new ArrayList<>();
        fishingLore.add(Component.text("Fish up items, treasure, and powerful creatures", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        fishingLore.add(Component.text("to earn Fishing XP!", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        fishingLore.add(Component.empty());
        fishingLore.addAll(getProgressList(holder.getSkill(SkillType.FISHING)));
        fishingMeta.lore(fishingLore);
        fishing.setItemMeta(fishingMeta);
        inventory.setItem(23, makeMenuItem(fishing, null));

        ItemStack enchanting = new ItemStack(Material.ENCHANTING_TABLE, 1);
        ItemMeta enchantingMeta = enchanting.getItemMeta();
        enchantingMeta.displayName(Component.text("Enchanting", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        List<Component> enchantingLore = new ArrayList<>();
        enchantingLore.add(Component.text("Enchant items to earn Enchanting XP!", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        enchantingLore.add(Component.empty());
        enchantingLore.addAll(getProgressList(holder.getSkill(SkillType.ENCHANTING)));
        enchantingMeta.lore(enchantingLore);
        enchanting.setItemMeta(enchantingMeta);
        inventory.setItem(24, makeMenuItem(enchanting, null));

        ItemStack alchemy = new ItemStack(Material.BREWING_STAND, 1);
        ItemMeta alchemyMeta = alchemy.getItemMeta();
        alchemyMeta.displayName(Component.text("Alchemy", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        List<Component> alchemyLore = new ArrayList<>();
        alchemyLore.add(Component.text("Brew potions to earn alchemy XP!", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        alchemyLore.add(Component.empty());
        alchemyLore.addAll(getProgressList(holder.getSkill(SkillType.ALCHEMY)));
        alchemyMeta.lore(alchemyLore);
        alchemy.setItemMeta(alchemyMeta);
        inventory.setItem(25, makeMenuItem(alchemy, null));

        addBackButton(MenuType.MAIN);

        addCloseButton();
    }

}