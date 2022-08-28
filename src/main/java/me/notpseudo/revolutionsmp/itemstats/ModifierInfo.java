package me.notpseudo.revolutionsmp.itemstats;

import me.notpseudo.revolutionsmp.enchantments.EnchantmentObject;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentType;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.items.ItemID;
import me.notpseudo.revolutionsmp.items.ItemType;

import java.io.Serializable;

public class ModifierInfo implements Serializable {

    private final ItemInfo HOLDER;
    private int totalPotatoBooks;
    private int woodSingularity;
    private int artOfWar;
    private int farmingForDummies;
    private int transmissionTuners;
    private int silex;

    public ModifierInfo(ItemInfo info) {
        HOLDER = info;
        totalPotatoBooks = 0;
        woodSingularity = 0;
        artOfWar = 0;
        farmingForDummies = 0;
        transmissionTuners = 0;
        silex = 0;
    }

    public boolean addHotPotatoBook() {
        if (!HOLDER.getItemType().allowPotatoBooks()) {
            return false;
        }
        if (totalPotatoBooks < 10) {
            totalPotatoBooks++;
            HOLDER.recalculate();
            return true;
        }
        return false;
    }

    public boolean addFumingPotatoBook() {
        if (!HOLDER.getItemType().allowPotatoBooks()) {
            return false;
        }
        if (totalPotatoBooks < 15) {
            totalPotatoBooks++;
            HOLDER.recalculate();
            return true;
        }
        return false;
    }

    public int getTotalPotatoBooks() {
        return totalPotatoBooks;
    }

    public void setTotalBooks(int books) {
        if (!HOLDER.getItemType().allowPotatoBooks()) {
            return;
        }
        totalPotatoBooks = books;
        HOLDER.recalculate();
    }

    public int getWoodSingularity() {
        return woodSingularity;
    }

    public boolean addWoodSingularity() {
        if (!(ItemEditor.isWeapon(HOLDER) && HOLDER.getItemID().getMaterial().toString().contains("WOODEN"))) {
            return false;
        }
        if (woodSingularity < 1) {
            woodSingularity++;
            HOLDER.recalculate();
            return true;
        }
        return false;
    }

    public void setWoodSingularity(int total) {
        if (!(ItemEditor.isWeapon(HOLDER) && HOLDER.getItemID().getMaterial().toString().contains("WOODEN"))) {
            return;
        }
        woodSingularity = total;
        HOLDER.recalculate();
    }

    public int getArtOfWar() {
        return artOfWar;
    }

    public boolean addArtOfWar() {
        if (!ItemEditor.isWeapon(HOLDER)) {
            return false;
        }
        if (artOfWar < 1) {
            artOfWar++;
            HOLDER.recalculate();
            return true;
        }
        return false;
    }

    public void setArtOfWar(int total) {
        if (!ItemEditor.isWeapon(HOLDER)) {
            return;
        }
        artOfWar = total;
        HOLDER.recalculate();
    }

    public int getFarmingForDummies() {
        return farmingForDummies;
    }

    public boolean addFarmingForDummies() {
        if (farmingForDummies < 5) {
            farmingForDummies++;
            HOLDER.recalculate();
            return true;
        }
        return false;
    }

    public void setFarmingForDummies(int total) {
        farmingForDummies = total;
        HOLDER.recalculate();
    }

    public int getTransmissionTuners() {
        return transmissionTuners;
    }

    public boolean addTransmissionTuners() {
        if (HOLDER.getItemID() != ItemID.ASPECT_OF_THE_END) {
            return false;
        }
        if (transmissionTuners < 2) {
            transmissionTuners++;
            HOLDER.recalculate();
            return true;
        }
        return false;
    }

    public int getSilex() {
        return silex;
    }

    public boolean addSilex() {
        if (!(HOLDER.getItemType() == ItemType.PICKAXE || HOLDER.getItemType() == ItemType.DRILL)) {
            return false;
        }
        EnchantmentsHolder enchants = HOLDER.getEnchantmentsHolder();
        if (enchants == null) {
            return false;
        }
        EnchantmentObject eff = enchants.getObjectForType(EnchantmentType.CUSTOM_EFFICIENCY);
        if (eff == null) {
            eff = EnchantmentType.CUSTOM_EFFICIENCY.createObject(0);
        }
        if (silex < 5) {
            silex++;
            eff.setLevel(eff.getLevel() + silex);
            enchants.addEnchant(eff);
            HOLDER.recalculate();
            return true;
        }
        return false;
    }

    public void combine(ModifierInfo other) {
        if (totalPotatoBooks > 15 || other.totalPotatoBooks > 15) {
            totalPotatoBooks += other.totalPotatoBooks;
        } else {
            totalPotatoBooks = Math.min(15, totalPotatoBooks + other.totalPotatoBooks);
        }
        if (woodSingularity > 1 || other.woodSingularity > 1) {
            woodSingularity += other.woodSingularity;
        } else {
            woodSingularity = Math.min(1, woodSingularity + other.woodSingularity);
        }
        if (artOfWar > 1 || other.artOfWar > 1) {
            artOfWar += other.artOfWar;
        } else {
            artOfWar = Math.min(1, artOfWar + other.artOfWar);
        }
        if (farmingForDummies > 5 || other.farmingForDummies > 5) {
            farmingForDummies += other.farmingForDummies;
        } else {
            farmingForDummies = Math.min(5, farmingForDummies + other.farmingForDummies);
        }
        silex = Math.min(5, silex + other.silex);
        transmissionTuners = Math.min(2, transmissionTuners + other.transmissionTuners);
        HOLDER.recalculate();
    }

    public WeaponStats getWeaponStats() {
        if (ItemEditor.isWeapon(HOLDER)) {
            return new WeaponStats(2 * totalPotatoBooks, (2 * totalPotatoBooks) + (5 * artOfWar) + (woodSingularity * 100), 0, 0, 0, 0);
        }
        return null;
    }

    public ArmorStats getArmorStats() {
        if (ItemEditor.isArmor(HOLDER)) {
            return new ArmorStats(4 * totalPotatoBooks, 2 * totalPotatoBooks, 0);
        }
        return null;
    }

    public AbilityStats getAbilityStats() {
        return null;
    }

    public MiningStats getMiningStats() {
        return null;
    }

    public GatheringStats getGatheringStats() {
        return new GatheringStats(farmingForDummies, 0);
    }

    public FishingStats getFishingStats() {
        return FishingStats.createZero();
    }

    public LuckStats getLuckStats() {
        return null;
    }

    public RegenStats getRegenStats() {
        return null;
    }

    public WisdomStats getWisdomStats() {
        return null;
    }

}