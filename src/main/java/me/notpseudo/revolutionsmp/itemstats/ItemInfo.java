package me.notpseudo.revolutionsmp.itemstats;

import me.notpseudo.revolutionsmp.abilities.AbilityType;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentObject;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentType;
import me.notpseudo.revolutionsmp.specialiteminfo.GemstoneObject;
import me.notpseudo.revolutionsmp.specialiteminfo.SpecialItemInfo;
import me.notpseudo.revolutionsmp.items.*;
import org.bukkit.Material;

import java.io.Serializable;
import java.util.UUID;

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
    private GemstonesHolder gemstonesHolder;
    private EnchantmentsHolder enchantmentsHolder;
    private AbilitiesHolder abilitiesHolder;
    private SpecialItemInfo extraInfo;
    private Material vanillaMaterial;
    private String texture;
    private UUID owner;

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
        miningStats = itemID.getDefaultMiningStats();
        gatheringStats = itemID.getDefaultGatheringStats();
        luckStats = itemID.getDefaultLuckStats();
        if (itemID.getGemstoneSlots() != null) {
            gemstonesHolder = new GemstonesHolder(this, itemID.getGemstoneSlots());
        }
        enchantmentsHolder = new EnchantmentsHolder(this);
        abilitiesHolder = new AbilitiesHolder(this);
        for (AbilityType type : itemID.getDefaultAbilities()) {
            abilitiesHolder.addAbility(type);
        }
        extraInfo = itemID.getSpecialItemInfo(this);
        vanillaMaterial = itemID.getMaterial();
        texture = itemID.getTexture();
        owner = null;
        recalculate();
    }

    public ItemInfo(Material material) {
        recomb = false;
        potatoBooks = 0;
        reforge = null;
        try {
            ItemID itemId = ItemID.valueOf(material.toString());
            name = itemID.getDefaultName();
            rarity = itemID.getDefaultRarity();
            itemType = itemID.getItemType();
            weaponStats = itemID.getDefaultWeaponStats();
            armorStats = itemID.getDefaultArmorStats();
            abilityStats = itemID.getDefaultAbilityStats();
            fishingStats = itemID.getDefaultFishingStats();
            miningStats = itemID.getDefaultMiningStats();
            gatheringStats = itemID.getDefaultGatheringStats();
            luckStats = itemID.getDefaultLuckStats();
            if (itemID.getGemstoneSlots() != null) {
                gemstonesHolder = new GemstonesHolder(this, itemID.getGemstoneSlots());
            }
            enchantmentsHolder = new EnchantmentsHolder(this);
            abilitiesHolder = new AbilitiesHolder(this);
            for (AbilityType type : itemID.getDefaultAbilities()) {
                abilitiesHolder.addAbility(type);
            }
            extraInfo = itemID.getSpecialItemInfo(this);
            vanillaMaterial = itemID.getMaterial();
            texture = itemID.getTexture();
            owner = null;
            recalculate();
        } catch (IllegalArgumentException exception) {
            itemID = null;
            name = null;
            try {
                if (material.isItem()) {
                    rarity = Rarity.valueOf(material.getItemRarity().toString());
                } else {
                    rarity = Rarity.COMMON;
                }
            } catch (IllegalArgumentException exception1) {
                rarity = Rarity.COMMON;
            }
            itemType = ItemType.VANILLA_ITEM;
            weaponStats = new WeaponStats(0, 0, 0, 0, 0, 0);
            armorStats = new ArmorStats(0, 0, 0);
            abilityStats = new AbilityStats(0, 0);
            fishingStats = new FishingStats(0, 0);
            miningStats = new MiningStats(0, 0, 0);
            gatheringStats = new GatheringStats(0, 0);
            luckStats = new LuckStats(0, 0);
            gemstonesHolder = null;
            enchantmentsHolder = null;
            abilitiesHolder = null;
            extraInfo = null;
            vanillaMaterial = material;
            owner = null;
        }
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

    public GemstonesHolder getGemstonesHolder() {
        return gemstonesHolder;
    }

    public void setGemstonesHolder(GemstonesHolder gemstonesHolder) {
        this.gemstonesHolder = gemstonesHolder;
        recalculate();
    }

    public EnchantmentsHolder getEnchantmentsHolder() {
        return enchantmentsHolder;
    }

    public void setEnchantmentsHolder(EnchantmentsHolder enchantmentsHolder) {
        this.enchantmentsHolder = enchantmentsHolder;
        recalculate();
    }

    public void addEnchant(EnchantmentObject enchant) {
        if (enchantmentsHolder != null) {
            enchantmentsHolder.addEnchant(enchant);
            recalculate();
        }
    }

    public void removeEnchant(EnchantmentType enchant) {
        if (enchantmentsHolder != null) {
            enchantmentsHolder.removeEnchant(enchant);
            recalculate();
        }
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

    public Material getVanillaMaterial() {
        return vanillaMaterial;
    }

    public void setMaterial(Material material) {
        this.vanillaMaterial = material;
        recalculate();
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
        recalculate();
    }

    public void recomb() {
        if (!recomb) {
            recomb = true;
            upgradeRarity();
        }
    }

    public void upgradeRarity() {
        if (rarity == Rarity.SPECIAL) {
            return;
        }
        rarity = rarity.next();
        recalculate();
    }

    public void copy(ItemInfo other) {
        potatoBooks = other.potatoBooks;
        reforge = other.reforge;
        if (other.recomb) {
            recomb();
        }
        if (enchantmentsHolder != null && other.enchantmentsHolder != null) {
            enchantmentsHolder.combine(other.enchantmentsHolder);
        }
        if (abilitiesHolder != null && other.abilitiesHolder != null) {
            abilitiesHolder.combine(other.abilitiesHolder);
        }
        if (gemstonesHolder != null && other.gemstonesHolder != null) {
            gemstonesHolder.combine(other.gemstonesHolder);
        }
        if (texture == null) {
            texture = other.texture;
        }
        owner = other.owner;
        recalculate();
    }

    public void recalculate() {
        if (itemID != null) {
            weaponStats = itemID.getDefaultWeaponStats();
            armorStats = itemID.getDefaultArmorStats();
            abilityStats = itemID.getDefaultAbilityStats();
            fishingStats = itemID.getDefaultFishingStats();
            miningStats = itemID.getDefaultMiningStats();
            gatheringStats = itemID.getDefaultGatheringStats();
            luckStats = itemID.getDefaultLuckStats();
        }
        if (reforge != null) {
            weaponStats.combine(reforge.getWeaponStats(rarity, owner));
            armorStats.combine(reforge.getArmorStats(rarity, owner));
            abilityStats.combine(reforge.getAbilityStats(rarity, owner));
            fishingStats.combine(reforge.getFishingStats(rarity, owner));
            miningStats.combine(reforge.getMiningStats(rarity, owner));
            gatheringStats.combine(reforge.getGatheringStats(rarity, owner));
            luckStats.combine(reforge.getLuckStats(rarity, owner));
        }
        if (enchantmentsHolder != null && itemType != ItemType.ITEM) {
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
        if (gemstonesHolder != null) {
            weaponStats.combine(gemstonesHolder.getGemWeapon(rarity));
            armorStats.combine(gemstonesHolder.getGemArmor(rarity));
            abilityStats.combine(gemstonesHolder.getGemAbility(rarity));
            fishingStats.combine(gemstonesHolder.getGemFishing(rarity));
            miningStats.combine(gemstonesHolder.getGemMining(rarity));
            gatheringStats.combine(gemstonesHolder.getGemGathering(rarity));
            luckStats.combine(gemstonesHolder.getGemLLuck(rarity));
        }
        if (abilitiesHolder != null) {
            abilitiesHolder.reorganize();
        }
        if (ItemEditor.isWeapon(this)) {
            weaponStats.setStatValue(StatType.DAMAGE, weaponStats.getStatValue(StatType.DAMAGE) + potatoBooks * 2);
            weaponStats.setStatValue(StatType.STRENGTH, weaponStats.getStatValue(StatType.STRENGTH) + potatoBooks * 2);
        }
        if (ItemEditor.isArmor(this)) {
            armorStats.setStatValue(StatType.HEALTH, armorStats.getStatValue(StatType.HEALTH) + potatoBooks * 4);
            armorStats.setStatValue(StatType.DEFENSE, armorStats.getStatValue(StatType.DEFENSE) + potatoBooks * 2);
        }
    }


}