package me.notpseudo.revolutionsmp.economy;

import org.bukkit.Bukkit;

import java.io.Serializable;
import java.util.UUID;

public class PlayerEcoInfo implements Serializable {

    private final UUID player;
    private double purse;
    private double bank;

    public PlayerEcoInfo() {
        player = null;
        purse = 0;
        bank = 0;
    }

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
        purse += Math.round(amount * 100) / 100.0;
        EcoUtils.updatePlayerEco(Bukkit.getPlayer(player), this);
    }

    public void addBank(double amount) {
        bank += Math.round(amount * 100) / 100.0;
        EcoUtils.updatePlayerEco(Bukkit.getPlayer(player), this);
    }

    public void setPurse(double amount) {
        purse = Math.round(amount * 100) / 100.0;
        EcoUtils.updatePlayerEco(Bukkit.getPlayer(player), this);
    }

    public void setBank(double amount) {
        bank = Math.round(amount * 100) / 100.0;
        EcoUtils.updatePlayerEco(Bukkit.getPlayer(player), this);
    }

    public boolean depositFromPurse(double amount) {
        double remain = purse - amount;
        if (remain < 0) {
            EcoUtils.updatePlayerEco(Bukkit.getPlayer(player), this);
            return false;
        }
        bank += Math.round(amount * 100) / 100.0;
        purse = Math.round(remain * 100) / 100.0;
        EcoUtils.updatePlayerEco(Bukkit.getPlayer(player), this);
        return true;
    }

    public double depositPercentFromPurse(double percent) {
        double amountTake = purse * (percent / 100);
        double remain = purse - amountTake;
        if (remain < 0) {
            EcoUtils.updatePlayerEco(Bukkit.getPlayer(player), this);
            return 0;
        }
        bank += Math.round(amountTake * 100) / 100.0;
        purse = Math.round(remain * 100) / 100.0;
        EcoUtils.updatePlayerEco(Bukkit.getPlayer(player), this);
        return Math.round(amountTake * 100) / 100.0;
    }

    public boolean withdrawFromBank(double amount) {
        double remain = bank - amount;
        if (remain < 0) {
            EcoUtils.updatePlayerEco(Bukkit.getPlayer(player), this);
            return false;
        }
        purse += Math.round(amount * 100) / 100.0;
        bank = Math.round(remain * 100) / 100.0;
        EcoUtils.updatePlayerEco(Bukkit.getPlayer(player), this);
        return true;
    }

    public double withdrawPercentFromBank(double percent) {
        double amountTake = bank * (percent / 100);
        double remain = bank - amountTake;
        if (remain < 0) {
            EcoUtils.updatePlayerEco(Bukkit.getPlayer(player), this);
            return 0;
        }
        purse += Math.round(amountTake * 100) / 100.0;
        bank = Math.round(remain * 100) / 100.0;
        EcoUtils.updatePlayerEco(Bukkit.getPlayer(player), this);
        return Math.round(amountTake * 100) / 100.0;
    }

    public double removeDeathAmount(double percent) {
        double amountRemoved = purse * (percent / 100);
        purse += Math.round((-1 * amountRemoved) * 100) / 100.0;
        EcoUtils.updatePlayerEco(Bukkit.getPlayer(player), this);
        return Math.round(amountRemoved * 100) / 100.0;
    }

}