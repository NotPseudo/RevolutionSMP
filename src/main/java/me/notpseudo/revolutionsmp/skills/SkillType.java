package me.notpseudo.revolutionsmp.skills;

import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.itemstats.StatType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

import java.util.ArrayList;
import java.util.List;

public enum SkillType {

    FARMING,
    MINING,
    COMBAT,
    FORAGING,
    FISHING,
    ENCHANTING,
    ALCHEMY,
    BUILDING,
    TAMING,
    ORIGIN,
    ABYSS,
    ASCENDANCE;

    public String getName() {
        return ItemEditor.getStringFromEnum(this);
    }

    public int getMaxLevel() {
        return 50;
    }

    public StatType getExpBooster() {
        try {
            return StatType.valueOf(this + "_WISDOM");
        } catch (IllegalArgumentException exception) {
            return null;
        }
    }

    public List<Component> getLevelUpMessage(int level) {
        ArrayList<Component> message = new ArrayList<>();
        message.add(Component.text("------------------------------", NamedTextColor.DARK_AQUA, TextDecoration.BOLD));
        message.add(Component.text(" SKILL LEVEL UP", NamedTextColor.AQUA, TextDecoration.BOLD));
        message.add(Component.empty());
        message.add(Component.text(" " + getName(), NamedTextColor.DARK_AQUA).append(Component.text(" " + (level - 1) + "➡", NamedTextColor.DARK_GRAY)).append(Component.text(level, NamedTextColor.DARK_AQUA)));
        message.add(Component.empty());
        List<Component> levelRewards = SkillUtils.getSkillRewards(new SkillObject(this, level));
        for (int i = 0; i < levelRewards.size(); i++) {
            if (i == 0) {
                message.add(Component.text(" ").append(levelRewards.get(i).color(NamedTextColor.GREEN)));
            } else {
                message.add(Component.text("  ").append(levelRewards.get(i)));

            }
        }
        message.add(Component.text("------------------------------", NamedTextColor.DARK_AQUA, TextDecoration.BOLD));
        return message;
    }

}