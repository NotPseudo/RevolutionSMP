package me.notpseudo.revolutionsmp.itemstats;

import me.notpseudo.revolutionsmp.abilities.AbilityType;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentObject;
import me.notpseudo.revolutionsmp.specialiteminfo.SpecialItemInfo;
import me.notpseudo.revolutionsmp.items.*;

import java.io.Serializable;

// Item info stored in an ItemStack's PersistentDataContainer
public class ItemInfo implements Serializable {

    private String name;
    private ItemID itemID;
    private Rarity rarity;
    private boolean recomb;
    private ItemType itemType;
    private int potatoBooks;
    private Reforge reforge;
    private WeaponStats weaponStats;
    private ArmorStats armorStats;
    private AbilityStats abilityStats;
    private FishingStats fishingStats;
    private MiningStats miningStats;
    private GatheringStats gatheringStats;
    private LuckStats luckStats;
    private EnchantmentsHolder enchantmentsHolder;
    private AbilitiesHolder abilitiesHolder;
    private SpecialItemInfo extraInfo;

    public ItemInfo(ItemID itemID) {
        this.itemID = itemID;
        this.recomb = false;
        this.potatoBooks = 0;
        this.reforge = null;
        name = itemID.getDefaultName();
        rarity = itemID.getDefaultRarity();
        itemType = itemID.getItemType();
        weaponStats = itemID.getDefaultWeaponStats();
        armorStats = itemID.getDefaultArmorStats();
        abilityStats = itemID.getDefaultAbilityStats();
        fishingStats = itemID.getDefaultFishingStats();
        miningStats = itemID.getDafaultMiningStats();
        gatheringStats = itemID.getDefaultGatheringStats();
        luckStats = itemID.getDefaultLuckStats();
        if (itemID.getItemType().allowEnchants()) {
            enchantmentsHolder = new EnchantmentsHolder();
        } else {
            enchantmentsHolder = null;
        }
        if (itemID.getItemType().allowAbilities()) {
            abilitiesHolder = new AbilitiesHolder(this);
            for(AbilityType type : itemID.getDefaultAbilities()) {
                abilitiesHolder.addAbility(type);
            }
        } else {
            abilitiesHolder = null;
        }
        extraInfo = itemID.getSpecialItemInfo();
        recalculate();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
        recalculate();
    }

    public boolean isRecomb() {
        return recomb;
    }

    public void setRecomb(boolean recomb) {
        this.recomb = recomb;
        recalculate();
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Reforge getReforge() {
        return reforge;
    }

    public void setReforge(Reforge reforge) {
        if (reforge.getItemTypes().contains(itemType)) {
            this.reforge = reforge;
            recalculate();
        }
    }

    public int getPotatoBooks() {
        return potatoBooks;
    }

    public void setPotatoBooks(int potatoBooks) {
        this.potatoBooks = potatoBooks;
        recalculate();
    }

    public ItemID getItemID() {
        return itemID;
    }

    public void setItemID(ItemID itemID) {
        this.itemID = itemID;
        recalculate();
    }

    public WeaponStats getWeaponStats() {
        return weaponStats;
    }

    public void setWeaponStats(WeaponStats weaponStats) {
        this.weaponStats = weaponStats;
        recalculate();
    }

    public ArmorStats getArmorStats() {
        return armorStats;
    }

    public void setArmorStats(ArmorStats armorStats) {
        this.armorStats = armorStats;
        recalculate();
    }

    public AbilityStats getAbilityStats() {
        return abilityStats;
    }

    public void setAbilityStats(AbilityStats abilityStats) {
        this.abilityStats = abilityStats;
        recalculate();
    }

    public FishingStats getFishingStats() {
        return fishingStats;
    }

    public void setFishingStats(FishingStats fishingStats) {
        this.fishingStats = fishingStats;
        recalculate();
    }

    public MiningStats getMiningStats() {
        return miningStats;
    }

    public void setMiningStats(MiningStats miningStats) {
        this.miningStats = miningStats;
        recalculate();
    }

    public GatheringStats getGatheringStats() {
        return gatheringStats;
    }

    public void setGatheringStats(GatheringStats gatheringStats) {
        this.gatheringStats = gatheringStats;
        recalculate();
    }

    public LuckStats getLuckStats() {
        return luckStats;
    }

    public void setLuckStats(LuckStats luckStats) {
        this.luckStats = luckStats;
        recalculate();
    }

    public EnchantmentsHolder getEnchantmentsHolder() {
        return enchantmentsHolder;
    }

    public void setEnchantmentsHolder(EnchantmentsHolder enchantmentsHolder) {
        this.enchantmentsHolder = enchantmentsHolder;
        recalculate();
    }

    public AbilitiesHolder getAbilitiesHolder() {
        return abilitiesHolder;
    }

    public void setAbilitiesHolder(AbilitiesHolder abilitiesHolder) {
        this.abilitiesHolder = abilitiesHolder;
        recalculate();
    }

    public SpecialItemInfo getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(SpecialItemInfo extraInfo) {
        this.extraInfo = extraInfo;
        recalculate();
    }

    public void recalculate() {
        weaponStats = itemID.getDefaultWeaponStats();
        armorStats = itemID.getDefaultArmorStats();
        abilityStats = itemID.getDefaultAbilityStats();
        fishingStats = itemID.getDefaultFishingStats();
        miningStats = itemID.getDafaultMiningStats();
        gatheringStats = itemID.getDefaultGatheringStats();
        luckStats = itemID.getDefaultLuckStats();
        if (reforge != null) {
            weaponStats.combine(reforge.getWeaponStats(rarity));
            armorStats.combine(reforge.getArmorStats(rarity));
            abilityStats.combine(reforge.getAbilityStats(rarity));
            fishingStats.combine(reforge.getFishingStats(rarity));
            miningStats.combine(reforge.getMiningStats(rarity));
            gatheringStats.combine(reforge.getGatheringStats(rarity));
            luckStats.combine(reforge.getLuckStats(rarity));
        }
        if (enchantmentsHolder != null) {
            for (EnchantmentObject enchant : enchantmentsHolder.getEnchants()) {
                weaponStats.combine(enchant.getType().getApplyWeaponStats(enchant.getLevel()));
                armorStats.combine(enchant.getType().getApplyArmorStats(enchant.getLevel()));
                abilityStats.combine(enchant.getType().getApplyAbilityStats(enchant.getLevel()));
                fishingStats.combine(enchant.getType().getApplyFishingStats(enchant.getLevel()));
                miningStats.combine(enchant.getType().getApplyMiningStats(enchant.getLevel()));
                gatheringStats.combine(enchant.getType().getApplyGatheringStats(enchant.getLevel()));
                luckStats.combine(enchant.getType().getApplyLuckStats(enchant.getLevel()));
            }
        }
        if (abilitiesHolder != null) {
            abilitiesHolder.reorganize();
        }
        if (ItemEditor.isWeapon(this)) {
            weaponStats.setDamage(weaponStats.getDamage() + potatoBooks * 2);
            weaponStats.setStrength(weaponStats.getStrength() + potatoBooks * 2);
        }
        if (ItemEditor.isArmor(this)) {
            armorStats.setHealth(armorStats.getHealth() + potatoBooks * 4);
            armorStats.setDefense(armorStats.getDefense() + potatoBooks * 2);
        }
    }

}