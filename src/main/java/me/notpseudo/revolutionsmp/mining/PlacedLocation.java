package me.notpseudo.revolutionsmp.mining;

import org.bukkit.Location;

import java.io.Serializable;

public class PlacedLocation implements Serializable {

    private int x;
    private int y;
    private int z;

    public PlacedLocation(Location location) {
        x = location.getBlockX();
        y = location.getBlockY();
        z = location.getBlockZ();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void add(int changeX, int changeY, int changeZ) {
        x += changeX;
        y += changeY;
        z += changeZ;
    }

    public void subtract(int changeX, int changeY, int changeZ) {
        x -= changeX;
        y -= changeY;
        z -= changeZ;
    }
    public boolean equals(Location other) {
        return x == other.getBlockX() && y == other.getBlockY() && z == other.getBlockZ();
    }

}