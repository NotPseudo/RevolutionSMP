package me.notpseudo.revolutionsmp.specialiteminfo;

import me.notpseudo.revolutionsmp.collections.*;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.mining.CustomOreLocation;
import me.notpseudo.revolutionsmp.skills.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FarmingToolInfo extends SpecialItemInfo implements Serializable {

    private CollectionType collectionType;
    private ArrayList<Material> vanillaMaterials;
    private double counter;
    private int craftTier;
    private int countTier;
    private int cropsUntilNextCountTier;
    private boolean count2Reached;

    public FarmingToolInfo(ItemInfo holder, CollectionType type) {
        super(holder);
        collectionType = type;
        this.vanillaMaterials = new ArrayList<>(type.getVanillaMaterials());
        counter = 0;
        craftTier = 1;
        countTier = 1;
        cropsUntilNextCountTier = 10000;
        count2Reached = false;
    }

    @Override
    public void upgradeFromCrafting() {
        craftTier++;
        super.getHolder().upgradeRarity();
        switch (craftTier) {
            case 1 -> super.getHolder().setMaterial(Material.STONE_HOE);
            case 2 -> super.getHolder().setMaterial(Material.IRON_HOE);
            case 3 -> super.getHolder().setMaterial(Material.DIAMOND_HOE);
            case 4 -> super.getHolder().setMaterial(Material.NETHERITE_HOE);
        }
    }

    private void upgradeFromCount() {
        if (countTier == 1) {
            cropsUntilNextCountTier = 1000000;
        } else if (countTier == 2) {
            count2Reached = true;
            cropsUntilNextCountTier = 0;
        }
        countTier++;
        super.getHolder().upgradeRarity();
    }

    public void add(Material material, int count) {
        if (!hasMaterial(material)) {
            return;
        }
        counter += count;
        if(!count2Reached && counter >= cropsUntilNextCountTier) {
            upgradeFromCount();
        }
    }

    private int digitsInValue(double value) {
        if (Math.floor(value) == 0) {
            return 1;
        }
        return (int) (Math.log10(Math.floor(value)) + 1);
    }

    private int getBaseAddPercent() {
        return switch (super.getHolder().getRarity()) {
            case COMMON ->  10;
            case UNCOMMON -> 25;
            default -> 50;
        };
    }

    private int getXpBoost() {
        return switch (super.getHolder().getRarity()) {
            case COMMON -> 1;
            case UNCOMMON -> 2;
            case RARE -> 3;
            case EPIC -> 5;
            case LEGENDARY -> 8;
            default -> 12;
        };
    }

    private int logarithmicCounter() {
        if (craftTier < 2) {
            return 0;
        }
        return 16 * Math.max(digitsInValue(counter) - 3, 0);
    }

    private int collectionAnalysis(Player player) {
        if (craftTier < 3) {
            return 0;
        }
        if (player == null) {
            player = Bukkit.getPlayer(super.getOwner());
            if (player == null) {
                return 0;
            }
        }
        CollectionsHolder collectionsHolder = player.getPersistentDataContainer().get(CollectionUtils.getCollectionsKey(), new CollectionsDataType());
        if (collectionsHolder == null) {
            return 0;
        }
        CollectionObject collection = collectionsHolder.getCollection(collectionType);
        if (collection == null) {
            return 0;
        }
        return 8 * Math.max(digitsInValue(collection.getTotalCollected()) - 3, 0);
    }

    private int knowledgeOfTheLand(Player player) {
        if (craftTier < 4) {
            return 0;
        }
        if (player == null) {
            player = Bukkit.getPlayer(super.getOwner());
            if (player == null) {
                return 0;
            }
        }
        SkillHolder skillHolder = SkillUtils.getHolder(player);
        SkillObject farming = skillHolder.getSkill(SkillType.FARMING);
        return 6 * Math.max(digitsInValue(farming.getTotalXP()) - 3, 0);
    }

    public boolean hasMaterial(Material material) {
        return vanillaMaterials.contains(material);
    }

    @Override
    public GatheringStats getEventGathering(Player harvester, Block block, IncreaseType inc) {
        if (inc == IncreaseType.MULTIPLICATIVE_PERCENT) {
                return null;
        }
        if (!hasMaterial(block.getType())) {
                return null;
        }
        if (inc != IncreaseType.INCREASE) {
            return null;
        }
        int addAmount = getBaseAddPercent() + logarithmicCounter() + collectionAnalysis(harvester) + knowledgeOfTheLand(harvester);
        return new GatheringStats(addAmount, 0);
    }

    @Override
    public WisdomStats getBreakingWisdom(Block block, CustomOreLocation ore) {
        if (!(vanillaMaterials.contains(block.getType()))) {
            return WisdomStats.createZero();
        }
        return new WisdomStats(0, 0, 0, getXpBoost(), 0, 0, 0);
    }

    @Override
    public List<Component> getSpecialLore() {
        String cropName = ItemEditor.getStringFromEnum(collectionType);
        ArrayList<Component> specialLore = new ArrayList<>();
        specialLore.add(Component.text("Harvest ", NamedTextColor.GRAY).append(Component.text("+" + getBaseAddPercent() + "% " + cropName, NamedTextColor.GREEN)));
        specialLore.add(Component.text("Gain ", NamedTextColor.GRAY).append(Component.text("+" + getXpBoost() + " ", NamedTextColor.GREEN)).append(StatType.FARMING_WISDOM.getNameWithSymbol()));
        specialLore.add(Component.text("Counter: ", NamedTextColor.GRAY).append(Component.text((int) (counter) + " " + cropName, NamedTextColor.YELLOW)));
        specialLore.add(Component.empty());
        if (craftTier >= 2) {
            specialLore.add(Component.text("Logarithmic Counter", NamedTextColor.GOLD));
            specialLore.add(Component.text("Harvest ", NamedTextColor.GRAY).append(Component.text("+16% ", NamedTextColor.GREEN)).append(Component.text(cropName + " per digits on the Counter, minus 3!")));
            specialLore.add(Component.text("+" + logarithmicCounter() + " ", NamedTextColor.GREEN).append(StatType.FARMING_FORTUNE.getNameWithSymbol()).append(Component.text("for " + cropName)));
            specialLore.add(Component.empty());
        }
        if (craftTier >= 3) {
            specialLore.add(Component.text("Collection Analysis", NamedTextColor.GOLD));
            specialLore.add(Component.text("Harvest ", NamedTextColor.GRAY).append(Component.text("+8% ", NamedTextColor.GREEN)).append(Component.text(cropName + " per digits of your collection, minus 3!")));
            specialLore.add(Component.text("+" + collectionAnalysis(null) + " ", NamedTextColor.GREEN).append(StatType.FARMING_FORTUNE.getNameWithSymbol()).append(Component.text("for " + cropName)));
            specialLore.add(Component.empty());
        }
        if (craftTier >= 4) {
            specialLore.add(Component.text("Knowledge of the Land", NamedTextColor.GOLD));
            specialLore.add(Component.text("Harvest ", NamedTextColor.GRAY).append(Component.text("+6% ", NamedTextColor.GREEN)).append(Component.text(cropName + " per digits of your total Farming Exp, minus 3!")));
            specialLore.add(Component.text("+" + knowledgeOfTheLand(null) + " ", NamedTextColor.GREEN).append(StatType.FARMING_FORTUNE.getNameWithSymbol()).append(Component.text("for " + cropName)));
            specialLore.add(Component.empty());
        }
        if (!count2Reached) {
            specialLore.add(Component.text("Reach " + ItemEditor.numberFormatted(cropsUntilNextCountTier) + " on the Counter to upgrade the Rarity!", NamedTextColor.YELLOW));
        }
        return specialLore;
    }

}