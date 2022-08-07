package me.notpseudo.revolutionsmp.listeners;

import me.notpseudo.revolutionsmp.mining.CustomOreLocation;
import me.notpseudo.revolutionsmp.mining.CustomOreType;
import org.bukkit.Location;

import java.io.Serializable;
import java.util.ArrayList;

public class PlacedLocationList implements Serializable {

    private ArrayList<PlacedLocation> placedLocations;
    private ArrayList<CustomOreLocation> customOreLocations;

    public PlacedLocationList() {
        placedLocations = new ArrayList<>();
        customOreLocations = new ArrayList();
    }

    public ArrayList<PlacedLocation> getPlacedLocations() {
        return placedLocations;
    }

    public ArrayList<CustomOreLocation> getOreLocations() {
        return customOreLocations;
    }

    public boolean containsPlaced(Location location) {
        for (PlacedLocation placedLocation : placedLocations) {
            if (placedLocation.equals(location)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsCustomOre(Location location) {
        for (CustomOreLocation customOre : customOreLocations) {
            if (customOre.equals(location)) {
                return true;
            }
        }
        return false;
    }

    public PlacedLocation getPlacedFromLocation(Location location) {
        for (PlacedLocation placedLocation : placedLocations) {
            if (placedLocation.equals(location)) {
                return placedLocation;
            }
        }
        return null;
    }

    public CustomOreLocation getCustomOreFromLocation(Location location) {
        for (CustomOreLocation customOre : customOreLocations) {
            if (customOre.equals(location)) {
                return customOre;
            }
        }
        return null;
    }

    public boolean addPlacedLocation(Location location) {
        if (!containsPlaced(location)) {
            placedLocations.add(new PlacedLocation(location));
            location.getWorld().getPersistentDataContainer().set(HarvestingListeners.getWorldPlacedKey(), new PlacedLocationListDataType(), this);
            return true;
        }
        return false;
    }

    public CustomOreLocation addCustomOreLocation(Location location, CustomOreType type) {
        if (!containsCustomOre(location)) {
            CustomOreLocation newOre = new CustomOreLocation(location, type);
            customOreLocations.add(newOre);
            location.getWorld().getPersistentDataContainer().set(HarvestingListeners.getWorldPlacedKey(), new PlacedLocationListDataType(), this);
            return newOre;
        }
        return getCustomOreFromLocation(location);
    }

    public boolean removePlacedLocation(Location location) {
        for (PlacedLocation placedLocation : placedLocations) {
            if (placedLocation.equals(location)) {
                placedLocations.remove(placedLocation);
                location.getWorld().getPersistentDataContainer().set(HarvestingListeners.getWorldPlacedKey(), new PlacedLocationListDataType(), this);
                return true;
            }
        }
        return false;
    }

    public boolean removeCustomOreLocation(Location location) {
        for (CustomOreLocation customOreLocation : customOreLocations) {
            if (customOreLocation.equals(location)) {
                customOreLocations.remove(customOreLocation);
                location.getWorld().getPersistentDataContainer().set(HarvestingListeners.getWorldPlacedKey(), new PlacedLocationListDataType(), this);
                return true;
            }
        }
        location.getWorld().getPersistentDataContainer().set(HarvestingListeners.getWorldPlacedKey(), new PlacedLocationListDataType(), this);
        return false;
    }

}