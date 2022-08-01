package me.notpseudo.revolutionsmp.collections;

import java.io.Serializable;

public class CollectionObject implements Serializable {

    private final CollectionType TYPE;
    private double totalCollected;
    private int currentCollected;
    private int itemsForNextLevel;
    private int level;

    public CollectionObject(CollectionType type) {
        TYPE = type;
        totalCollected = 0;
        currentCollected = 0;
        itemsForNextLevel = 0;
        level = 0;
    }



}