package me.notpseudo.revolutionsmp.specialiteminfo;

import me.notpseudo.revolutionsmp.customcrafting.items.ItemEditor;
import me.notpseudo.revolutionsmp.customcrafting.items.ItemType;
import me.notpseudo.revolutionsmp.customcrafting.items.Rarity;
import me.notpseudo.revolutionsmp.customcrafting.items.Reforge;
import me.notpseudo.revolutionsmp.itemstats.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

import java.util.ArrayList;
import java.util.List;

public class ReforgeStoneInfo extends SpecialItemInfo {

    private final Reforge REFORGE;

    public ReforgeStoneInfo(ItemInfo holder, Reforge reforge) {
        super(holder);
        REFORGE = reforge;
    }

    public Reforge getReforge() {
        return REFORGE;
    }

    @Override
    public List<Component> getSpecialLore() {
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text("Reforge Stone", NamedTextColor.DARK_GRAY).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.empty());
        lore.add(Component.text("Can be used in the Advanced Reforge Menu", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text("to apply the ", NamedTextColor.GRAY).append(Component.text(REFORGE.getName() + " ", NamedTextColor.BLUE)).append(Component.text("reforge to:", NamedTextColor.GRAY)).decoration(TextDecoration.ITALIC, false));
        for (ItemType type : REFORGE.getItemTypes()) {
            lore.add(Component.text("- " + type.getName(), NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        }
        lore.add(Component.empty());
        ArrayList<Component> bonus = new ArrayList<>();
        List<StatHolder> holders = List.of(REFORGE.getWeaponStats(Rarity.LEGENDARY, null),
                REFORGE.getArmorStats(Rarity.LEGENDARY, null),
                REFORGE.getAbilityStats(Rarity.LEGENDARY, null),
                REFORGE.getFishingStats(Rarity.LEGENDARY, null),
                REFORGE.getMiningStats(Rarity.LEGENDARY, null),
                REFORGE.getGatheringStats(Rarity.LEGENDARY, null),
                REFORGE.getLuckStats(Rarity.LEGENDARY, null));
        for (StatHolder holder: holders) {
            for (StatObject stat : holder.getStats()) {
                if (stat.getValue() != 0) {
                    bonus.add(Component.text(ItemEditor.getStatString(stat) + " ", NamedTextColor.GREEN).append(stat.getType().getNameWithSymbol()));
                }
            }
        }
        if (bonus.size() > 0) {
            bonus.add(0, Component.text(REFORGE.getName() + " Bonus", NamedTextColor.BLUE).decoration(TextDecoration.ITALIC, false));
            bonus.add(Component.empty());
        }
        lore.addAll(bonus);
        if (REFORGE.hasBonus()) {
            lore.addAll(REFORGE.getBonusLore(Rarity.LEGENDARY));
        }
        return lore;
    }
}