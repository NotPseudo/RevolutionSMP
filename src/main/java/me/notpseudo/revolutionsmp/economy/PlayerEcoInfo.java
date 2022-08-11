package me.notpseudo.revolutionsmp.economy;

import org.bukkit.Bukkit;

import java.io.Serializable;
import java.util.UUID;

public class PlayerEcoInfo implements Serializable {

    private UUID player;
    private double purse;
    private double bank;

    public PlayerEcoInfo(UUID player) {
        this.player = player;
        purse = 0;
        bank = 0;
    }

    public UUID getPlayer() {
        return player;
    }

    public double getPurse() {
        return purse;
    }

    public double getBank() {
        return bank;
    }

    public void addPurse(double amount) {
        purse += amount;
        EcoUtils.updatePlayerEco(Bukkit.getPlayer(player), this);
    }

    public void addBank(double amount) {
        bank += amount;
        EcoUtils.updatePlayerEco(Bukkit.getPlayer(player), this);
    }

    public boolean addToBankFromPurse(double amount) {
        double remain = purse - amount;
        if (remain < 0) {
            EcoUtils.updatePlayerEco(Bukkit.getPlayer(player), this);
            return false;
        }
        purse = remain;
        bank += amount;
        EcoUtils.updatePlayerEco(Bukkit.getPlayer(player), this);
        return true;
    }

    public double removeDeathAmount(double percent) {
        double amountRemoved = Math.round(purse * (percent / 100) * 100) / 100.0;
        purse -= amountRemoved;
        EcoUtils.updatePlayerEco(Bukkit.getPlayer(player), this);
        return amountRemoved;
    }

}