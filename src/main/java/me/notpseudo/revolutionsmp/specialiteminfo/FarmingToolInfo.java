package me.notpseudo.revolutionsmp.specialiteminfo;

import me.notpseudo.revolutionsmp.itemstats.ItemInfo;
import org.bukkit.Material;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FarmingToolInfo extends SpecialItemInfo implements Serializable {

    private ItemInfo holder;
    private ArrayList<Material> canBreak;
    private double counter;
    private int tier;

    public FarmingToolInfo(ItemInfo holder, List<Material> canBreak) {
        this.holder = holder;
        this.canBreak = new ArrayList<>(canBreak);
        counter = 0;
        tier = 1;
    }

}