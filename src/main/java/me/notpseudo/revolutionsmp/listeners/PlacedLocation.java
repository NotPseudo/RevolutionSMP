package me.notpseudo.revolutionsmp.listeners;

import org.bukkit.Location;

import java.io.Serializable;

public class PlacedLocation implements Serializable {

    private double x;
    private double y;
    private double z;

    public PlacedLocation(Location location) {
        x = location.getX();
        y = location.getY();
        z = location.getZ();
    }

    public PlacedLocation(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void add(double changeX, double changeY, double changeZ) {
        x += changeX;
        y += changeY;
        z += changeZ;
    }

    public void subtract(double changeX, double changeY, double changeZ) {
        x -= changeX;
        y -= changeY;
        z -= changeZ;
    }

    public boolean equals(PlacedLocation other) {
        return x == other.x && y == other.y && z == other.z;
    }

    public boolean equals(Location other) {
        return x == other.getX() && y == other.getY() && z == other.getZ();
    }

}