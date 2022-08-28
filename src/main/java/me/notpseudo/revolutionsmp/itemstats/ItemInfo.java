package me.notpseudo.revolutionsmp.itemstats;

import me.notpseudo.revolutionsmp.abilities.AbilityObject;
import me.notpseudo.revolutionsmp.abilities.AbilityType;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentObject;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentType;
import me.notpseudo.revolutionsmp.items.*;
import me.notpseudo.revolutionsmp.mining.CustomOreLocation;
import me.notpseudo.revolutionsmp.specialiteminfo.InvalidClassInfo;
import me.notpseudo.revolutionsmp.specialiteminfo.SpecialItemInfo;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.UUID;

// Item info stored in an ItemStack's PersistentDataContainer
public class ItemInfo implements Serializable {

    private String name;
    private ItemID itemID;
    private ItemType itemType;
    private Rarity rarity;
    private boolean recomb;
    private Reforge reforge;
    private WeaponStats weaponStats;
    private ArmorStats armorStats;
    private AbilityStats abilityStats;
    private FishingStats fishingStats;
    private MiningStats miningStats;
    private GatheringStats gatheringStats;
    private LuckStats luckStats;
    private RegenStats regenStats;
    private WisdomStats wisdomStats;
    private GemstonesHolder gemstonesHolder;
    private EnchantmentsHolder enchantmentsHolder;
    private AbilitiesHolder abilitiesHolder;
    private ModifierInfo modifiers;
    private SpecialItemInfo extraInfo;
    private Material vanillaMaterial;
    private String texture;
    private UUID owner;

    public ItemInfo(ItemID itemID) {
        this.itemID = itemID;
        this.recomb = false;
        this.reforge = null;
        name = itemID.getName();
        rarity = itemID.getDefaultRarity();
        itemType = itemID.getItemType();
        weaponStats = itemID.getDefaultWeaponStats();
        armorStats = itemID.getDefaultArmorStats();
        abilityStats = itemID.getDefaultAbilityStats();
        fishingStats = itemID.getDefaultFishingStats();
        miningStats = itemID.getDefaultMiningStats();
        gatheringStats = itemID.getDefaultGatheringStats();
        luckStats = itemID.getDefaultLuckStats();
        regenStats = itemID.getDefaultRegenStats();
        wisdomStats = itemID.getDefaultWisdomStats();
        if (itemType.allowModifiers()) {
            modifiers = new ModifierInfo(this);
        } else {
            modifiers = null;
        }
        if (itemID.getGemstoneSlots() != null) {
            gemstonesHolder = new GemstonesHolder(this, itemID.getGemstoneSlots());
        } else {
            gemstonesHolder = null;
        }
        if (itemType.allowEnchants() || itemID == ItemID.ENCHANTED_BOOK) {
            enchantmentsHolder = new EnchantmentsHolder(this);
            for (EnchantmentObject enchant : itemID.getDefaultEnchantments()) {
                enchantmentsHolder.addEnchant(enchant);
            }
        } else {
            enchantmentsHolder = null;
        }
        if (itemType.allowAbilities()) {
            abilitiesHolder = new AbilitiesHolder(this);
            for (AbilityType type : itemID.getDefaultAbilities()) {
                abilitiesHolder.addAbility(type);
            }
        } else {
            abilitiesHolder = null;
        }
        extraInfo = itemID.getSpecialItemInfo(this);
        vanillaMaterial = itemID.getMaterial();
        texture = itemID.getTexture();
        owner = null;
        recalculate();
    }

    public ItemInfo(Material material) {
        recomb = false;
        reforge = null;
        try {
            itemID = ItemID.valueOf(material.toString());
            name = itemID.getName();
            rarity = itemID.getDefaultRarity();
            itemType = itemID.getItemType();
            weaponStats = itemID.getDefaultWeaponStats();
            armorStats = itemID.getDefaultArmorStats();
            abilityStats = itemID.getDefaultAbilityStats();
            fishingStats = itemID.getDefaultFishingStats();
            miningStats = itemID.getDefaultMiningStats();
            gatheringStats = itemID.getDefaultGatheringStats();
            luckStats = itemID.getDefaultLuckStats();
            regenStats = itemID.getDefaultRegenStats();
            wisdomStats = itemID.getDefaultWisdomStats();
            if (itemType.allowModifiers()) {
                modifiers = new ModifierInfo(this);
            } else {
                modifiers = null;
            }
            if (itemID.getGemstoneSlots() != null) {
                gemstonesHolder = new GemstonesHolder(this, itemID.getGemstoneSlots());
            } else {
                gemstonesHolder = null;
            }
            if (itemType.allowEnchants() || itemID == ItemID.ENCHANTED_BOOK) {
                enchantmentsHolder = new EnchantmentsHolder(this);
                for (EnchantmentObject enchant : itemID.getDefaultEnchantments()) {
                    enchantmentsHolder.addEnchant(enchant);
                }
            } else {
                enchantmentsHolder = null;
            }
            if (itemType.allowAbilities()) {
                abilitiesHolder = new AbilitiesHolder(this);
                for (AbilityType type : itemID.getDefaultAbilities()) {
                    abilitiesHolder.addAbility(type);
                }
            } else {
                abilitiesHolder = null;
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
            weaponStats = null;
            armorStats = null;
            abilityStats = null;
            fishingStats = null;
            miningStats = null;
            gatheringStats = null;
            luckStats = null;
            regenStats = null;
            wisdomStats = null;
            modifiers = null;
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

    public @NotNull Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
        recalculate();
    }

    public boolean isRecomb() {
        return recomb;
    }

    public @NotNull ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public @Nullable Reforge getReforge() {
        return reforge;
    }

    public void setReforge(Reforge reforge) {
        if (reforge.getItemTypes().contains(itemType)) {
            this.reforge = reforge;
            recalculate();
        }
    }

    public @Nullable ItemID getItemID() {
        return itemID;
    }

    public void setItemID(ItemID itemID) {
        this.itemID = itemID;
        recalculate();
    }

    public @Nullable GemstonesHolder getGemstonesHolder() {
        return gemstonesHolder;
    }

    public @Nullable EnchantmentsHolder getEnchantmentsHolder() {
        return enchantmentsHolder;
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

    public @Nullable AbilitiesHolder getAbilitiesHolder() {
        return abilitiesHolder;
    }

    public void addAbility(AbilityType type) {
        if (abilitiesHolder != null) {
            abilitiesHolder.addAbility(type);
            recalculate();
        }
    }

    public void removeAbility(AbilityType type) {
        if (abilitiesHolder != null) {
            abilitiesHolder.removeAbility(type);
            recalculate();
        }
    }

    public @Nullable ModifierInfo getModifiers() {
        return modifiers;
    }

    public @Nullable SpecialItemInfo getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(SpecialItemInfo extraInfo) {
        this.extraInfo = extraInfo;
        recalculate();
    }

    public @NotNull Material getVanillaMaterial() {
        return vanillaMaterial;
    }

    public void setMaterial(Material material) {
        this.vanillaMaterial = material;
        recalculate();
    }

    public @Nullable String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public @Nullable UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        if (itemType == ItemType.VANILLA_ITEM || itemType == ItemType.ITEM) {
            return;
        }
        this.owner = owner;
        recalculate();
    }

    public void recomb() {
        if (!itemType.allowRecomb()) {
            return;
        }
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

    public void copy(ItemInfo older) {
        if (itemType == ItemType.VANILLA_ITEM) {
            return;
        }
        modifiers = older.modifiers;
        reforge = older.reforge;
        if (older.recomb) {
            recomb();
        }
        if (enchantmentsHolder != null && older.enchantmentsHolder != null) {
            enchantmentsHolder.combine(older.enchantmentsHolder);
        }
        if (abilitiesHolder != null && older.abilitiesHolder != null) {
            abilitiesHolder.combine(older.abilitiesHolder);
        }
        if (gemstonesHolder != null && older.gemstonesHolder != null) {
            gemstonesHolder.combine(older.gemstonesHolder);
        }
        if (texture == null) {
            texture = older.texture;
        }
        owner = older.owner;
        recalculate();
    }

    public boolean canCombine(@NotNull ItemInfo other) {
        if (itemType == ItemType.VANILLA_ITEM) {
            return false;
        }
        if (other.itemID == ItemID.HOT_POTATO_BOOK) {
            return itemType.allowModifiers() && modifiers.getTotalPotatoBooks() < 10;
        }
        if (other.itemID == ItemID.FUMING_POTATO_BOOK) {
            return itemType.allowModifiers() && modifiers.getTotalPotatoBooks() < 15;
        }
        if (other.itemID == ItemID.RECOMBOBULATOR_3000) {
            return itemType.allowRecomb() && !isRecomb();
        }
        if (other.itemID == ItemID.ENCHANTED_BOOK) {
            return itemType.allowEnchants() || itemID == ItemID.ENCHANTED_BOOK;
        }
        return itemID == other.itemID;
    }

    public boolean combine(ItemInfo other) {
        if (!canCombine(other)) {
            return false;
        }
        if (other.itemID == ItemID.HOT_POTATO_BOOK) {
            return modifiers.addHotPotatoBook();
        }
        if (other.itemID == ItemID.FUMING_POTATO_BOOK) {
            return modifiers.addFumingPotatoBook();
        }
        if (other.itemID == ItemID.RECOMBOBULATOR_3000) {
            recomb();
            return true;
        }
        if (other.itemID == ItemID.ENCHANTED_BOOK) {
            if (enchantmentsHolder == null) {
                return false;
            }
            enchantmentsHolder.combine(other.enchantmentsHolder);
            recalculate();
            return true;
        }
        if (other.recomb) {
            recomb();
        }
        if (reforge == null) {
            reforge = other.reforge;
        }
        if (modifiers != null) {
            modifiers.combine(other.modifiers);
        }
        if (enchantmentsHolder != null) {
            enchantmentsHolder.combine(other.enchantmentsHolder);
        }
        if (abilitiesHolder != null) {
            abilitiesHolder.combine(other.abilitiesHolder);
        }
        if (gemstonesHolder != null) {
            gemstonesHolder.combine(other.gemstonesHolder);
        }
        recalculate();
        return true;
    }

    public void upgradeFrom(ItemInfo older) {
        if (itemType == ItemType.VANILLA_ITEM || itemType == ItemType.ITEM) {
            return;
        }
        modifiers.combine(older.modifiers);
        reforge = older.reforge;
        if (older.recomb) {
            recomb();
        }
        if (enchantmentsHolder != null && older.enchantmentsHolder != null) {
            enchantmentsHolder.combine(older.enchantmentsHolder);
        }
        if (abilitiesHolder != null && older.abilitiesHolder != null) {
            abilitiesHolder.combine(older.abilitiesHolder);
        }
        if (gemstonesHolder != null && older.gemstonesHolder != null) {
            gemstonesHolder.combine(older.gemstonesHolder);
        }
        if (older.extraInfo != null) {
            extraInfo = older.extraInfo;
            extraInfo.upgradeFromCrafting();
        }
        owner = older.owner;
        recalculate();
    }

    public void recalculate() {
        if (itemType == ItemType.VANILLA_ITEM) {
            return;
        }
        if (itemID != null) {
            weaponStats = itemID.getDefaultWeaponStats();
            armorStats = itemID.getDefaultArmorStats();
            abilityStats = itemID.getDefaultAbilityStats();
            fishingStats = itemID.getDefaultFishingStats();
            miningStats = itemID.getDefaultMiningStats();
            gatheringStats = itemID.getDefaultGatheringStats();
            luckStats = itemID.getDefaultLuckStats();
            regenStats = itemID.getDefaultRegenStats();
            wisdomStats = itemID.getDefaultWisdomStats();
        }
        if (reforge != null) {
            getWeaponStats().combine(reforge.getWeaponStats(rarity, owner));
            getArmorStats().combine(reforge.getArmorStats(rarity, owner));
            getAbilityStats().combine(reforge.getAbilityStats(rarity, owner));
            getFishingStats().combine(reforge.getFishingStats(rarity, owner));
            getMiningStats().combine(reforge.getMiningStats(rarity, owner));
            getGatheringStats().combine(reforge.getGatheringStats(rarity, owner));
            getLuckStats().combine(reforge.getLuckStats(rarity, owner));
            getRegenStats().combine(reforge.getRegenStats(rarity, owner));
            getWisdomStats().combine(reforge.getWisdomStats(rarity, owner));
        }
        if (enchantmentsHolder != null && itemType != ItemType.ITEM) {
            for (EnchantmentObject enchant : enchantmentsHolder.getEnchants()) {
                int level = enchant.getLevel();
                getWeaponStats().combine(enchant.getType().getApplyWeaponStats(level));
                getArmorStats().combine(enchant.getType().getApplyArmorStats(level));
                getAbilityStats().combine(enchant.getType().getApplyAbilityStats(level));
                getFishingStats().combine(enchant.getType().getApplyFishingStats(level));
                getMiningStats().combine(enchant.getType().getApplyMiningStats(level));
                getGatheringStats().combine(enchant.getType().getApplyGatheringStats(level));
                getLuckStats().combine(enchant.getType().getApplyLuckStats(level));
                getRegenStats().combine(enchant.getType().getApplyRegenStats(level));
                getWisdomStats().combine(enchant.getType().getApplyWisdomStats(level));
            }
        }
        if (gemstonesHolder != null) {
            getWeaponStats().combine(gemstonesHolder.getGemWeapon());
            getArmorStats().combine(gemstonesHolder.getGemArmor());
            getAbilityStats().combine(gemstonesHolder.getGemAbility());
            getFishingStats().combine(gemstonesHolder.getGemFishing());
            getMiningStats().combine(gemstonesHolder.getGemMining());
            getGatheringStats().combine(gemstonesHolder.getGemGathering());
            getLuckStats().combine(gemstonesHolder.getGemLuck());
            getRegenStats().combine(gemstonesHolder.getGemRegen());
            getWisdomStats().combine(gemstonesHolder.getGemWisdom());
        }
        if (abilitiesHolder != null) {
            abilitiesHolder.reorganize();
        }
        if (modifiers != null) {
            getWeaponStats().combine(modifiers.getWeaponStats());
            getArmorStats().combine(modifiers.getArmorStats());
            getAbilityStats().combine(modifiers.getArmorStats());
            getFishingStats().combine(modifiers.getFishingStats());
            getMiningStats().combine(modifiers.getMiningStats());
            getGatheringStats().combine(modifiers.getGatheringStats());
            getLuckStats().combine(modifiers.getLuckStats());
            getRegenStats().combine(modifiers.getRegenStats());
            getWisdomStats().combine(modifiers.getWisdomStats());
        }
    }

    private ItemInfo() {
        extraInfo = new InvalidClassInfo(this);

    }

    /**
     * Returns a new empty ItemInfo when one can not be deserialized
     * <b>Should only be used by ItemInfoDataType for InvalidClassException</b>
     *
     * @return A new ItemInfo with InvalidClass special info
     */
    public static ItemInfo newForInvalid() {
        return new ItemInfo();
    }

    /*
    Default stats that are stored on the item
     */

    public double getStatValue(StatType type) {
        return switch (type.getStatCategory()) {
            case COMBAT -> getWeaponStats().getStatValue(type);
            case ARMOR -> getArmorStats().getStatValue(type);
            case INTELLIGENCE -> getAbilityStats().getStatValue(type);
            case FISHING -> getFishingStats().getStatValue(type);
            case MINING -> getMiningStats().getStatValue(type);
            case GATHERING -> getGatheringStats().getStatValue(type);
            case LUCK -> getLuckStats().getStatValue(type);
            case WISDOM -> getWisdomStats().getStatValue(type);
            case REGEN -> getRegenStats().getStatValue(type);
        };
    }

    public StatObject getStatObject(StatType type) {
        return switch (type.getStatCategory()) {
            case COMBAT -> getWeaponStats().getStatObject(type);
            case ARMOR -> getArmorStats().getStatObject(type);
            case INTELLIGENCE -> getAbilityStats().getStatObject(type);
            case FISHING -> getFishingStats().getStatObject(type);
            case MINING -> getMiningStats().getStatObject(type);
            case GATHERING -> getGatheringStats().getStatObject(type);
            case LUCK -> getLuckStats().getStatObject(type);
            case WISDOM -> getWisdomStats().getStatObject(type);
            case REGEN -> getRegenStats().getStatObject(type);
        };
    }

    @NotNull
    public WeaponStats getWeaponStats() {
        if (weaponStats == null) {
            return WeaponStats.createZero();
        }
        return weaponStats;
    }

    @NotNull
    public ArmorStats getArmorStats() {
        if (armorStats == null) {
            return ArmorStats.createZero();
        }
        return armorStats;
    }

    @NotNull
    public AbilityStats getAbilityStats() {
        if (abilityStats == null) {
            return AbilityStats.createZero();
        }
        return abilityStats;
    }

    @NotNull
    public FishingStats getFishingStats() {
        if (fishingStats == null) {
            return FishingStats.createZero();
        }
        return fishingStats;
    }

    @NotNull
    public MiningStats getMiningStats() {
        if (miningStats == null) {
            return MiningStats.createZero();
        }
        return miningStats;
    }

    @NotNull
    public GatheringStats getGatheringStats() {
        if (gatheringStats == null) {
            return GatheringStats.createZero();
        }
        return gatheringStats;
    }

    @NotNull
    public LuckStats getLuckStats() {
        if (luckStats == null) {
            return LuckStats.createZero();
        }
        return luckStats;
    }

    @NotNull
    public RegenStats getRegenStats() {
        if (regenStats == null) {
            return RegenStats.createZero();
        }
        return regenStats;
    }

    @NotNull
    public WisdomStats getWisdomStats() {
        if (wisdomStats == null) {
            return WisdomStats.createZero();
        }
        return wisdomStats;
    }

    /*
    Stat boosts that buff players but do not apply on items
     */

    @NotNull
    public WeaponStats getBonusWeapon(IncreaseType type) {
        Player player = Bukkit.getPlayer(owner);
        WeaponStats bonusWeapon = itemID.getBonusWeapon(player, type);
        if (reforge != null && rarity != null) {
            WeaponStats reforgeStats = reforge.getBonusWeapon(rarity, player, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonusWeapon.multiply(reforgeStats);
            } else {
                bonusWeapon.combine(reforgeStats);
            }
        }
        if (enchantmentsHolder != null) {
            for (EnchantmentObject enchant : enchantmentsHolder.getEnchants()) {
                WeaponStats enchantStat = enchant.getBonusWeapon(player, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusWeapon.multiply(enchantStat);
                } else {
                    bonusWeapon.combine(enchantStat);
                }
            }
        }
        if (abilitiesHolder != null) {
            for (AbilityObject ability : abilitiesHolder.getAbilities()) {
                WeaponStats abilityStat = ability.getBonusWeapon(player, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusWeapon.multiply(abilityStat);
                } else {
                    bonusWeapon.combine(abilityStat);
                }
            }
        }
        if (extraInfo != null) {
            WeaponStats extraStats = extraInfo.getBonusWeapon(player, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonusWeapon.multiply(extraStats);
            } else {
                bonusWeapon.combine(extraStats);
            }
        }
        return bonusWeapon;
    }

    @NotNull
    public ArmorStats getBonusArmor(IncreaseType type) {
        Player player = Bukkit.getPlayer(owner);
        ArmorStats bonusArmor = itemID.getBonusArmor(player, type);
        if (reforge != null && rarity != null) {
            ArmorStats reforgeStats = reforge.getBonusArmor(rarity, player, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonusArmor.multiply(reforgeStats);
            } else {
                bonusArmor.combine(reforgeStats);
            }
        }
        if (enchantmentsHolder != null) {
            for (EnchantmentObject enchant : enchantmentsHolder.getEnchants()) {
                ArmorStats enchantStat = enchant.getBonusArmor(player, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusArmor.multiply(enchantStat);
                } else {
                    bonusArmor.combine(enchantStat);
                }
            }
        }
        if (abilitiesHolder != null) {
            for (AbilityObject ability : abilitiesHolder.getAbilities()) {
                ArmorStats abilityStat = ability.getBonusArmor(player, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusArmor.multiply(abilityStat);
                } else {
                    bonusArmor.combine(abilityStat);
                }
            }
        }
        if (extraInfo != null) {
            ArmorStats extraStats = extraInfo.getBonusArmor(player, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonusArmor.multiply(extraStats);
            } else {
                bonusArmor.combine(extraStats);
            }
        }
        return bonusArmor;
    }

    @NotNull
    public AbilityStats getBonusAbility(IncreaseType type) {
        Player player = Bukkit.getPlayer(owner);
        AbilityStats bonusAbility = itemID.getBonusAbility(player, type);
        if (reforge != null && rarity != null) {
            AbilityStats reforgeStats = reforge.getBonusAbility(rarity, player, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonusAbility.multiply(reforgeStats);
            } else {
                bonusAbility.combine(reforgeStats);
            }
        }
        if (enchantmentsHolder != null) {
            for (EnchantmentObject enchant : enchantmentsHolder.getEnchants()) {
                AbilityStats enchantStat = enchant.getBonusAbility(player, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusAbility.multiply(enchantStat);
                } else {
                    bonusAbility.combine(enchantStat);
                }
            }
        }
        if (abilitiesHolder != null) {
            for (AbilityObject ability : abilitiesHolder.getAbilities()) {
                AbilityStats abilityStat = ability.getBonusAbility(player, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusAbility.multiply(abilityStat);
                } else {
                    bonusAbility.combine(abilityStat);
                }
            }
        }
        if (extraInfo != null) {
            AbilityStats extraStats = extraInfo.getBonusAbility(player, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonusAbility.multiply(extraStats);
            } else {
                bonusAbility.combine(extraStats);
            }
        }
        return bonusAbility;
    }

    @NotNull
    public FishingStats getBonusFishing(IncreaseType type) {
        Player player = Bukkit.getPlayer(owner);
        FishingStats bonusFishing = itemID.getBonusFishing(player, type);
        if (reforge != null && rarity != null) {
            FishingStats reforgeStats = reforge.getBonusFishing(rarity, player, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonusFishing.multiply(reforgeStats);
            } else {
                bonusFishing.combine(reforgeStats);
            }
        }
        if (enchantmentsHolder != null) {
            for (EnchantmentObject enchant : enchantmentsHolder.getEnchants()) {
                FishingStats enchantStat = enchant.getBonusFishing(player, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusFishing.multiply(enchantStat);
                } else {
                    bonusFishing.combine(enchantStat);
                }
            }
        }
        if (abilitiesHolder != null) {
            for (AbilityObject ability : abilitiesHolder.getAbilities()) {
                FishingStats abilityStat = ability.getBonusFishing(player, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusFishing.multiply(abilityStat);
                } else {
                    bonusFishing.combine(abilityStat);
                }
            }
        }
        if (extraInfo != null) {
            FishingStats extraStats = extraInfo.getBonusFishing(player, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonusFishing.multiply(extraStats);
            } else {
                bonusFishing.combine(extraStats);
            }
        }
        return bonusFishing;
    }

    @NotNull
    public MiningStats getBonusMining(IncreaseType type) {
        Player player = Bukkit.getPlayer(owner);
        MiningStats bonusMining = itemID.getBonusMining(player, type);
        if (reforge != null && rarity != null) {
            MiningStats reforgeStats = reforge.getBonusMining(rarity, player, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonusMining.multiply(reforgeStats);
            } else {
                bonusMining.combine(reforgeStats);
            }
        }
        if (enchantmentsHolder != null) {
            for (EnchantmentObject enchant : enchantmentsHolder.getEnchants()) {
                MiningStats enchantStat = enchant.getBonusMining(player, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusMining.multiply(enchantStat);
                } else {
                    bonusMining.combine(enchantStat);
                }
            }
        }
        if (abilitiesHolder != null) {
            for (AbilityObject ability : abilitiesHolder.getAbilities()) {
                MiningStats abilityStat = ability.getBonusMining(player, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusMining.multiply(abilityStat);
                } else {
                    bonusMining.combine(abilityStat);
                }
            }
        }
        if (extraInfo != null) {
            MiningStats extraStats = extraInfo.getBonusMining(player, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonusMining.multiply(extraStats);
            } else {
                bonusMining.combine(extraStats);
            }
        }
        return bonusMining;
    }

    @NotNull
    public GatheringStats getBonusGathering(IncreaseType type) {
        Player player = Bukkit.getPlayer(owner);
        GatheringStats bonusGathering = itemID.getBonusGathering(player, type);
        if (reforge != null && rarity != null) {
            GatheringStats reforgeStats = reforge.getBonusGathering(rarity, player, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonusGathering.multiply(reforgeStats);
            } else {
                bonusGathering.combine(reforgeStats);
            }
        }
        if (enchantmentsHolder != null) {
            for (EnchantmentObject enchant : enchantmentsHolder.getEnchants()) {
                GatheringStats enchantStat = enchant.getBonusGathering(player, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusGathering.multiply(enchantStat);
                } else {
                    bonusGathering.combine(enchantStat);
                }
            }
        }
        if (abilitiesHolder != null) {
            for (AbilityObject ability : abilitiesHolder.getAbilities()) {
                GatheringStats abilityStat = ability.getBonusGathering(player, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusGathering.multiply(abilityStat);
                } else {
                    bonusGathering.combine(abilityStat);
                }
            }
        }
        if (extraInfo != null) {
            GatheringStats extraStats = extraInfo.getBonusGathering(player, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonusGathering.multiply(extraStats);
            } else {
                bonusGathering.combine(extraStats);
            }
        }
        return bonusGathering;
    }

    @NotNull
    public LuckStats getBonusLuck(IncreaseType type) {
        Player player = Bukkit.getPlayer(owner);
        LuckStats bonusLuck = itemID.getBonusLuck(player, type);
        if (reforge != null && rarity != null) {
            LuckStats reforgeStats = reforge.getBonusLuck(rarity, player, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonusLuck.multiply(reforgeStats);
            } else {
                bonusLuck.combine(reforgeStats);
            }
        }
        if (enchantmentsHolder != null) {
            for (EnchantmentObject enchant : enchantmentsHolder.getEnchants()) {
                LuckStats enchantStat = enchant.getBonusLuck(player, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusLuck.multiply(enchantStat);
                } else {
                    bonusLuck.combine(enchantStat);
                }
            }
        }
        if (abilitiesHolder != null) {
            for (AbilityObject ability : abilitiesHolder.getAbilities()) {
                LuckStats abilityStat = ability.getBonusLuck(player, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusLuck.multiply(abilityStat);
                } else {
                    bonusLuck.combine(abilityStat);
                }
            }
        }
        if (extraInfo != null) {
            LuckStats extraStats = extraInfo.getBonusLuck(player, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonusLuck.multiply(extraStats);
            } else {
                bonusLuck.combine(extraStats);
            }
        }
        return bonusLuck;
    }

    @NotNull
    public RegenStats getBonusRegen(IncreaseType type) {
        Player player = Bukkit.getPlayer(owner);
        RegenStats bonusRegen = itemID.getBonusRegen(player, type);
        if (reforge != null && rarity != null) {
            RegenStats reforgeStats = reforge.getBonusRegen(rarity, player, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonusRegen.multiply(reforgeStats);
            } else {
                bonusRegen.combine(reforgeStats);
            }
        }
        if (enchantmentsHolder != null) {
            for (EnchantmentObject enchant : enchantmentsHolder.getEnchants()) {
                RegenStats enchantStat = enchant.getBonusRegen(player, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusRegen.multiply(enchantStat);
                } else {
                    bonusRegen.combine(enchantStat);
                }
            }
        }
        if (abilitiesHolder != null) {
            for (AbilityObject ability : abilitiesHolder.getAbilities()) {
                RegenStats abilityStat = ability.getBonusRegen(player, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusRegen.multiply(abilityStat);
                } else {
                    bonusRegen.combine(abilityStat);
                }
            }
        }
        if (extraInfo != null) {
            RegenStats extraStats = extraInfo.getBonusRegen(player, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonusRegen.multiply(extraStats);
            } else {
                bonusRegen.combine(extraStats);
            }
        }
        return bonusRegen;
    }

    @NotNull
    public WisdomStats getBonusWisdom(IncreaseType type) {
        Player player = Bukkit.getPlayer(owner);
        WisdomStats bonusRegen = itemID.getBonusWisdom(player, type);
        if (reforge != null && rarity != null) {
            WisdomStats reforgeStats = reforge.getBonusWisdom(rarity, player, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonusRegen.multiply(reforgeStats);
            } else {
                bonusRegen.combine(reforgeStats);
            }
        }
        if (enchantmentsHolder != null) {
            for (EnchantmentObject enchant : enchantmentsHolder.getEnchants()) {
                WisdomStats enchantStat = enchant.getBonusWisdom(player, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusRegen.multiply(enchantStat);
                } else {
                    bonusRegen.combine(enchantStat);
                }
            }
        }
        if (abilitiesHolder != null) {
            for (AbilityObject ability : abilitiesHolder.getAbilities()) {
                WisdomStats abilityStat = ability.getBonusWisdom(player, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    bonusRegen.multiply(abilityStat);
                } else {
                    bonusRegen.combine(abilityStat);
                }
            }
        }
        if (extraInfo != null) {
            WisdomStats extraStats = extraInfo.getBonusWisdom(player, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                bonusRegen.multiply(extraStats);
            } else {
                bonusRegen.combine(extraStats);
            }
        }
        return bonusRegen;
    }

    /*
    Stat boosts that apply during events
     */

    @NotNull
    public WeaponStats getEventWeaponStats(LivingEntity target, EntityDamageEvent event, IncreaseType type) {
        Player player = Bukkit.getPlayer(owner);
        WeaponStats eventWeapon = itemID.getEventWeapon(player, target, event, type);
        if (reforge != null && rarity != null) {
            WeaponStats reforgeStats = reforge.getEventWeapon(rarity, player, target, event, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                eventWeapon.multiply(reforgeStats);
            } else {
                eventWeapon.combine(reforgeStats);
            }
        }
        if (enchantmentsHolder != null) {
            for (EnchantmentObject enchant : enchantmentsHolder.getEnchants()) {
                WeaponStats enchantStats = enchant.getEventWeapon(player, target, event, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    eventWeapon.multiply(enchantStats);
                } else {
                    eventWeapon.combine(enchantStats);
                }
            }
        }
        if (abilitiesHolder != null) {
            for (AbilityObject ability : abilitiesHolder.getAbilities()) {
                WeaponStats abilityStats = ability.getEventWeapon(player, target, event, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    eventWeapon.multiply(abilityStats);
                } else {
                    eventWeapon.combine(abilityStats);
                }
            }
        }
        if (extraInfo != null) {
            WeaponStats extraStats = extraInfo.getEventWeapon(player, target, event, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                eventWeapon.multiply(extraStats);
            } else {
                eventWeapon.combine(extraStats);
            }
        }
        return eventWeapon;
    }

    @NotNull
    public ArmorStats getEventArmorStats(LivingEntity attacker, EntityDamageEvent event, IncreaseType type) {
        Player player = Bukkit.getPlayer(owner);
        ArmorStats eventArmor = itemID.getEventArmor(attacker, player, event, type);
        if (reforge != null && rarity != null) {
            ArmorStats reforgeStats = reforge.getEventArmor(rarity, attacker, player, event, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                eventArmor.multiply(reforgeStats);
            } else {
                eventArmor.combine(reforgeStats);
            }
        }
        if (enchantmentsHolder != null) {
            for (EnchantmentObject enchant : enchantmentsHolder.getEnchants()) {
                ArmorStats enchantStats = enchant.getEventArmor(attacker, player, event, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    eventArmor.multiply(enchantStats);
                } else {
                    eventArmor.combine(enchantStats);
                }
            }
        }
        if (abilitiesHolder != null) {
            for (AbilityObject ability : abilitiesHolder.getAbilities()) {
                ArmorStats abilityStats = ability.getEventArmor(attacker, player, event, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    eventArmor.multiply(abilityStats);
                } else {
                    eventArmor.combine(abilityStats);
                }
            }
        }
        if (extraInfo != null) {
            ArmorStats extraStats = extraInfo.getEventArmor(attacker, player, event, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                eventArmor.multiply(extraStats);
            } else {
                eventArmor.combine(extraStats);
            }
        }
        return eventArmor;
    }

    @NotNull
    public AbilityStats getEventAbilityStats(LivingEntity target, IncreaseType type) {
        Player player = Bukkit.getPlayer(owner);
        AbilityStats eventAbility = itemID.getEventAbility(player, target, type);
        if (reforge != null && rarity != null) {
            AbilityStats reforgeStat = reforge.getEventAbility(rarity, player, target, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                eventAbility.multiply(reforgeStat);
            } else {
                eventAbility.combine(reforgeStat);
            }
        }
        if (enchantmentsHolder != null) {
            for (EnchantmentObject enchant : enchantmentsHolder.getEnchants()) {
                AbilityStats enchantStat = enchant.getEventAbility(player, target, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    eventAbility.multiply(enchantStat);
                } else {
                    eventAbility.combine(enchantStat);
                }
            }
        }
        if (abilitiesHolder != null) {
            for (AbilityObject ability : abilitiesHolder.getAbilities()) {
                AbilityStats abilityStat = ability.getEventAbility(player, target, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    eventAbility.multiply(abilityStat);
                } else {
                    eventAbility.combine(abilityStat);
                }
            }
        }
        if (extraInfo != null) {
            AbilityStats extraStat = extraInfo.getEventAbility(player, target, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                eventAbility.multiply(extraStat);
            } else {
                eventAbility.combine(extraStat);
            }
        }
        return eventAbility;
    }

    @NotNull
    public FishingStats getEventFishing(IncreaseType type) {
        Player player = Bukkit.getPlayer(owner);
        FishingStats eventFishing = itemID.getEventFishing(player, type);
        if (reforge != null && rarity != null) {
            FishingStats reforgeStat = reforge.getEventFishing(rarity, player, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                eventFishing.multiply(reforgeStat);
            } else {
                eventFishing.combine(reforgeStat);
            }
        }
        if (enchantmentsHolder != null) {
            for (EnchantmentObject enchant : enchantmentsHolder.getEnchants()) {
                FishingStats enchantStat = enchant.getEventFishing(player, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    eventFishing.multiply(enchantStat);
                } else {
                    eventFishing.combine(enchantStat);
                }
            }
        }
        if (abilitiesHolder != null) {
            for (AbilityObject ability : abilitiesHolder.getAbilities()) {
                FishingStats abilityStat = ability.getEventFishing(player, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    eventFishing.multiply(abilityStat);
                } else {
                    eventFishing.combine(abilityStat);
                }
            }
        }
        if (extraInfo != null) {
            FishingStats extraStat = extraInfo.getEventFishing(player, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                eventFishing.multiply(extraStat);
            } else {
                eventFishing.combine(extraStat);
            }
        }
        return eventFishing;
    }

    @NotNull
    public MiningStats getEventMining(Block block, CustomOreLocation ore, IncreaseType type) {
        Player player = Bukkit.getPlayer(owner);
        MiningStats eventMining = itemID.getEventMining(player, block, ore, type);
        if (reforge != null && rarity != null) {
            MiningStats reforgeStat = reforge.getEventMining(rarity, player, block, ore, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                eventMining.multiply(reforgeStat);
            } else {
                eventMining.combine(reforgeStat);
            }
        }
        if (enchantmentsHolder != null) {
            for (EnchantmentObject enchant : enchantmentsHolder.getEnchants()) {
                MiningStats enchantStat = enchant.getEventMining(player, block, ore, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    eventMining.multiply(enchantStat);
                } else {
                    eventMining.combine(enchantStat);
                }
            }
        }
        if (abilitiesHolder != null) {
            for (AbilityObject ability : abilitiesHolder.getAbilities()) {
                MiningStats abilityStat = ability.getEventMining(player, block, ore, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    eventMining.multiply(abilityStat);
                } else {
                    eventMining.combine(abilityStat);
                }
            }
        }
        if (extraInfo != null) {
            MiningStats extraStat = extraInfo.getEventMining(player, block, ore, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                eventMining.multiply(extraStat);
            } else {
                eventMining.combine(extraStat);
            }
        }
        return eventMining;
    }

    @NotNull
    public GatheringStats getEventGathering(Block block, IncreaseType type) {
        Player player = Bukkit.getPlayer(owner);
        GatheringStats eventGathering = itemID.getEventGathering(player, block, type);
        if (reforge != null && rarity != null) {
            GatheringStats reforgeStat = reforge.getEventGathering(rarity, player, block, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                eventGathering.multiply(reforgeStat);
            } else {
                eventGathering.combine(reforgeStat);
            }
        }
        if (enchantmentsHolder != null) {
            for (EnchantmentObject enchant : enchantmentsHolder.getEnchants()) {
                GatheringStats enchantStat = enchant.getEventGathering(player, block, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    eventGathering.multiply(enchantStat);
                } else {
                    eventGathering.combine(enchantStat);
                }
            }
        }
        if (abilitiesHolder != null) {
            for (AbilityObject ability : abilitiesHolder.getAbilities()) {
                GatheringStats abilityStat = ability.getEventGathering(player, block, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    eventGathering.multiply(abilityStat);
                } else {
                    eventGathering.combine(abilityStat);
                }
            }
        }
        if (extraInfo != null) {
            GatheringStats extraStat = extraInfo.getEventGathering(player, block, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                eventGathering.multiply(extraStat);
            } else {
                eventGathering.combine(extraStat);
            }
        }
        return eventGathering;
    }

    @NotNull
    public LuckStats getEventLuck(LivingEntity target, IncreaseType type) {
        Player player = Bukkit.getPlayer(owner);
        LuckStats eventLuck = itemID.getEventLuck(player, target, type);
        if (reforge != null && rarity != null) {
            LuckStats reforgeStat = reforge.getEventLuck(rarity, player, target, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                eventLuck.multiply(reforgeStat);
            } else {
                eventLuck.combine(reforgeStat);
            }
        }
        if (enchantmentsHolder != null) {
            for (EnchantmentObject enchant : enchantmentsHolder.getEnchants()) {
                LuckStats enchantStat = enchant.getEventLuck(player, target, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    eventLuck.multiply(enchantStat);
                } else {
                    eventLuck.combine(enchantStat);
                }
            }
        }
        if (abilitiesHolder != null) {
            for (AbilityObject ability : abilitiesHolder.getAbilities()) {
                LuckStats abilityStat = ability.getEventLuck(player, target, type);
                if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                    eventLuck.multiply(abilityStat);
                } else {
                    eventLuck.combine(abilityStat);
                }
            }
        }
        if (extraInfo != null) {
            LuckStats extraStat = extraInfo.getEventLuck(player, target, type);
            if (type == IncreaseType.MULTIPLICATIVE_PERCENT) {
                eventLuck.multiply(extraStat);
            } else {
                eventLuck.combine(extraStat);
            }
        }
        return eventLuck;
    }

    @NotNull
    public WisdomStats getCombatWisdom(LivingEntity target) {
        if (itemID == null) {
            return WisdomStats.createZero();
        }
        Player player = Bukkit.getPlayer(owner);
        WisdomStats combat = itemID.getCombatWisdom(player, target);
        if (extraInfo != null) {
            combat.combine(extraInfo.getCombatWisdom(target));
        }
        return combat;
    }

    @NotNull
    public WisdomStats getBreakingWisdom(Block block, CustomOreLocation ore) {
        if (itemID == null) {
            return WisdomStats.createZero();
        }
        Player player = Bukkit.getPlayer(owner);
        WisdomStats breaking = itemID.getBreakingWisdom(player, block, ore);
        if (extraInfo != null) {
            breaking.combine(extraInfo.getBreakingWisdom(block, ore));
        }
        return breaking;
    }

    @NotNull
    public WisdomStats getRegularWisdom() {
        if (itemID == null) {
            return WisdomStats.createZero();
        }
        Player player = Bukkit.getPlayer(owner);
        WisdomStats breaking = itemID.getRegularWisdom(player);
        if (extraInfo != null) {
            breaking.combine(extraInfo.getRegularWisdom());
        }
        return breaking;
    }

}