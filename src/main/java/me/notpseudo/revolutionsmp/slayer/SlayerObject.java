package me.notpseudo.revolutionsmp.slayer;

import java.io.Serializable;

public class SlayerObject implements Serializable {

    private final SlayerType TYPE;
    private double totalXP;
    private double currentXP;
    private double xpForNextLevel;
    private double level;

    public SlayerObject(SlayerType type) {
        TYPE = type;
        totalXP = 0;
        currentXP = 0;
        xpForNextLevel = 0;
        level = 0;
    }

}