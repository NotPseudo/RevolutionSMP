package me.notpseudo.revolutionsmp.menus;

import me.notpseudo.revolutionsmp.itemstats.StatType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProfileMenu extends Menu {

    public ProfileMenu(Player player) {
        super(player);
    }

    @Override
    public Component getTitle() {
        return Component.text("Equipment and Stats");
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void setItems() {
        PlayerInventory playerInv = player.getInventory();
        inventory.setItem(2, makeMenuType(playerInv.getItemInMainHand().clone(), null));
        if (playerInv.getHelmet() != null) {
            inventory.setItem(11, makeMenuType(playerInv.getHelmet().clone(), null));
        } else {
            inventory.setItem(11, makeMenuType(null, null));
        }
        if (playerInv.getChestplate() != null) {
            inventory.setItem(20, makeMenuType(playerInv.getChestplate().clone(), null));
        } else {
            inventory.setItem(20, makeMenuType(null, null));
        }
        if (playerInv.getLeggings() != null) {
            inventory.setItem(29, makeMenuType(playerInv.getLeggings().clone(), null));
        } else {
            inventory.setItem(29, makeMenuType(null, null));
        }
        if (playerInv.getBoots() != null) {
            inventory.setItem(38, makeMenuType(playerInv.getBoots().clone(), null));
        } else {
            inventory.setItem(38, makeMenuType(null, null));
        }

        Map<StatType, Component> lines = getStatComponents(player);

        ItemStack combat = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta combatMeta = combat.getItemMeta();
        combatMeta.displayName(Component.text("Combat Stats", NamedTextColor.RED).decoration(TextDecoration.ITALIC, false));
        List<Component> combatLore = new ArrayList<>();
        combatLore.add(Component.text("Statistics useful in combat and fighting", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        combatLore.add(Component.empty());
        combatLore.add(lines.get(StatType.HEALTH));
        combatLore.add(lines.get(StatType.DEFENSE));
        combatLore.add(lines.get(StatType.STRENGTH));
        combatLore.add(lines.get(StatType.INTELLIGENCE));
        combatLore.add(lines.get(StatType.CRIT_CHANCE));
        combatLore.add(lines.get(StatType.CRIT_DAMAGE));
        combatLore.add(lines.get(StatType.ATTACK_SPEED));
        combatLore.add(lines.get(StatType.ABILITY_DAMAGE));
        combatLore.add(lines.get(StatType.TRUE_DEFENSE));
        combatLore.add(lines.get(StatType.FEROCITY));
        combatLore.add(lines.get(StatType.HEALTH_REGEN));
        combatLore.add(lines.get(StatType.MANA_REGEN));
        combatLore.add(lines.get(StatType.VITALITY));
        combatLore.add(lines.get(StatType.MENDING));
        combatMeta.lore(combatLore);
        combat.setItemMeta(combatMeta);
        inventory.setItem(24, makeMenuType(combat, null));

        ItemStack gather = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta gatherMeta = gather.getItemMeta();
        gatherMeta.displayName(Component.text("Gathering Stats", NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        List<Component> gatherLore = new ArrayList<>();
        gatherLore.add(Component.text("Statistics that improve harvesting items", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        gatherLore.add(Component.empty());
        gatherLore.add(lines.get(StatType.MINING_SPEED));
        gatherLore.add(lines.get(StatType.MINING_FORTUNE));
        gatherLore.add(lines.get(StatType.FARMING_FORTUNE));
        gatherLore.add(lines.get(StatType.FORAGING_FORTUNE));
        gatherLore.add(lines.get(StatType.BREAKING_POWER));
        gatherLore.add(lines.get(StatType.PRISTINE));
        gatherLore.add(lines.get(StatType.PURITY));
        gatherMeta.lore(gatherLore);
        gather.setItemMeta(gatherMeta);
        inventory.setItem(25, makeMenuType(gather, null));

        ItemStack wisdom = new ItemStack(Material.BOOK);
        ItemMeta wisdomMeta = wisdom.getItemMeta();
        wisdomMeta.displayName(Component.text("Wisdom Stats", NamedTextColor.DARK_AQUA).decoration(TextDecoration.ITALIC, false));
        List<Component> wisdomLore = new ArrayList<>();
        wisdomLore.add(Component.text("Increases the ", NamedTextColor.GRAY).append(Component.text("Skill XP ", NamedTextColor.DARK_AQUA)).append(Component.text("you gain", NamedTextColor.GRAY)).decoration(TextDecoration.ITALIC, false));
        wisdomLore.add(Component.empty());
        wisdomLore.add(lines.get(StatType.COMBAT_WISDOM));
        wisdomLore.add(lines.get(StatType.MINING_WISDOM));
        wisdomLore.add(lines.get(StatType.FARMING_WISDOM));
        wisdomLore.add(lines.get(StatType.FORAGING_WISDOM));
        wisdomLore.add(lines.get(StatType.FISHING_WISDOM));
        wisdomLore.add(lines.get(StatType.ENCHANTING_WISDOM));
        wisdomLore.add(lines.get(StatType.ALCHEMY_WISDOM));
        wisdomMeta.lore(wisdomLore);
        wisdom.setItemMeta(wisdomMeta);
        inventory.setItem(33, makeMenuType(wisdom, null));

        ItemStack misc = new ItemStack(Material.CLOCK);
        ItemMeta miscMeta = misc.getItemMeta();
        miscMeta.displayName(Component.text("Misc Stats", NamedTextColor.LIGHT_PURPLE).decoration(TextDecoration.ITALIC, false));
        List<Component> miscLore = new ArrayList<>();
        miscLore.add(Component.text("Affects other aspect of your gameplay", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        miscLore.add(Component.empty());
        miscLore.add(lines.get(StatType.SPEED));
        miscLore.add(lines.get(StatType.MAGIC_FIND));
        miscLore.add(lines.get(StatType.PET_LUCK));
        miscLore.add(lines.get(StatType.SEA_CREATURE_CHANCE));
        miscLore.add(lines.get(StatType.FISHING_SPEED));
        miscMeta.lore(miscLore);
        misc.setItemMeta(miscMeta);
        inventory.setItem(34, makeMenuType(misc, null));

        addBackType(MenuType.MAIN);

        addCloseButton();
    }

}