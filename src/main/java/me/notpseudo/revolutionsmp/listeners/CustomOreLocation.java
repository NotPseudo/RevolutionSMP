package me.notpseudo.revolutionsmp.listeners;

import org.bukkit.Location;
import org.bukkit.Material;

import java.io.Serializable;

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

}