package me.notpseudo.revolutionsmp.mining;

import me.notpseudo.revolutionsmp.drops.ItemDropObject;
import org.bukkit.Location;
import org.bukkit.Material;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustomOreLocation extends PlacedLocation implements Serializable {

    private final CustomOreType TYPE;
    private final Material vanillaMaterial;

    public CustomOreLocation(Location location, CustomOreType type) {
        super(location);
        TYPE = type;
        vanillaMaterial = type.getVanillaMaterials()[(int) (Math.random() * type.getVanillaMaterials().length)];
    }

    public CustomOreType getType() {
        return TYPE;
    }

    public Material getVanillaMaterial() {
        return vanillaMaterial;
    }

    public double getBlockStrength() {
        return TYPE.getBlockStrength(vanillaMaterial);
    }

    public List<ItemDropObject> getDrops() {
        return new ArrayList<>(List.of(new ItemDropObject(TYPE.getItemDrop(vanillaMaterial))));
    }

}