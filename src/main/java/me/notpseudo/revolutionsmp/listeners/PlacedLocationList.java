package me.notpseudo.revolutionsmp.listeners;

import org.bukkit.Location;

import java.io.Serializable;
import java.util.ArrayList;

public class PlacedLocationList implements Serializable {

    private ArrayList<PlacedLocation> placedLocations;

    public PlacedLocationList() {
        placedLocations = new ArrayList<>();
    }

    public ArrayList<PlacedLocation> getPlacedLocations() {
        return placedLocations;
    }

    public boolean contains(Location location) {
        for (PlacedLocation placedLocation : placedLocations) {
            if (placedLocation.equals(location)) {
                return true;
            }
        }
        return false;
    }

    public PlacedLocation getFromLocation(Location location) {
        for (PlacedLocation placedLocation : placedLocations) {
            if (placedLocation.equals(location)) {
                return placedLocation;
            }
        }
        return null;
    }

    public boolean add(Location location) {
        if (!contains(location)) {
            placedLocations.add(new PlacedLocation(location));
            return true;
        }
        return false;
    }

    public boolean remove(Location location) {
        for (PlacedLocation placedLocation : placedLocations) {
            if (placedLocation.equals(location)) {
                placedLocations.remove(placedLocation);
                return true;
            }
        }
        return false;
    }

}