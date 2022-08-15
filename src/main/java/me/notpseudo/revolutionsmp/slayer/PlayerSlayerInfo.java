package me.notpseudo.revolutionsmp.slayer;

import java.io.Serializable;
import java.util.UUID;

public class PlayerSlayerInfo implements Serializable {

    private final UUID player;

    public PlayerSlayerInfo(UUID player) {
        this.player = player;
    }



}