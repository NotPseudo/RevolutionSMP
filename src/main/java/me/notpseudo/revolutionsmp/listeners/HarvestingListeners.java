package me.notpseudo.revolutionsmp.listeners;

import com.destroystokyo.paper.event.block.BlockDestroyEvent;
import io.papermc.paper.event.block.BlockBreakBlockEvent;
import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.drops.ItemDropObject;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentType;
import me.notpseudo.revolutionsmp.customcrafting.items.ItemEditor;
import me.notpseudo.revolutionsmp.customcrafting.items.ItemType;
import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.mining.*;
import me.notpseudo.revolutionsmp.playerstats.PlayerStats;
import me.notpseudo.revolutionsmp.skills.SkillType;
import me.notpseudo.revolutionsmp.skills.SkillUtils;
import me.notpseudo.revolutionsmp.specialiteminfo.FarmingToolInfo;
import me.notpseudo.revolutionsmp.specialiteminfo.GemstoneObject;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.type.CaveVinesPlant;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class HarvestingListeners implements Listener {

    private static final NamespacedKey worldPlacedKey = new NamespacedKey(RevolutionSMP.getPlugin(RevolutionSMP.class), "chunkPlaced");
    private static final Set<Material> foragingCollectionBlocks = new HashSet<>();
    private static final Set<Material> flowers = new HashSet<>();
    private static final Set<Material> miningCollectionBlocks = new HashSet<>();
    private static final Set<Material> affectedByMiningSpeed = new HashSet<>();
    private static final Set<Material> farmingCollectionBlocks = new HashSet<>();

    private static final Set<Material> transparent = new HashSet<>(List.of(Material.AIR, Material.CAVE_AIR, Material.VOID_AIR, Material.WATER, Material.LAVA));

    private final RevolutionSMP plugin;

    public HarvestingListeners(RevolutionSMP plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
        flowers.addAll(List.of(Material.DANDELION, Material.POPPY, Material.BLUE_ORCHID, Material.ALLIUM, Material.AZURE_BLUET,
                Material.RED_TULIP, Material.ORANGE_TULIP, Material.WHITE_TULIP, Material.PINK_TULIP,
                Material.OXEYE_DAISY, Material.CORNFLOWER, Material.LILY_OF_THE_VALLEY, Material.SUNFLOWER, Material.LILAC, Material.ROSE_BUSH, Material.PEONY,
                Material.AZALEA, Material.FLOWERING_AZALEA, Material.SPORE_BLOSSOM));
        foragingCollectionBlocks.addAll(flowers);
        foragingCollectionBlocks.addAll(List.of(Material.CRIMSON_STEM, Material.STRIPPED_CRIMSON_STEM,
                Material.WARPED_STEM, Material.STRIPPED_WARPED_STEM,
                Material.OAK_LOG, Material.OAK_WOOD, Material.STRIPPED_OAK_WOOD, Material.STRIPPED_OAK_LOG,
                Material.DARK_OAK_LOG,
                Material.BIRCH_LOG,
                Material.JUNGLE_LOG,
                Material.ACACIA_WOOD, Material.ACACIA_LOG, Material.STRIPPED_ACACIA_LOG,
                Material.SPRUCE_WOOD, Material.SPRUCE_LOG, Material.STRIPPED_SPRUCE_WOOD, Material.STRIPPED_SPRUCE_LOG,
                Material.MANGROVE_LOG));
        miningCollectionBlocks.addAll(List.of(Material.COBBLESTONE, Material.MOSSY_COBBLESTONE, Material.STONE, Material.DEEPSLATE, Material.COBBLED_DEEPSLATE, Material.ANDESITE, Material.DIORITE, Material.GRANITE, Material.CALCITE, Material.BONE_BLOCK,
                Material.COAL_ORE, Material.DEEPSLATE_COAL_ORE, Material.COAL_BLOCK,
                Material.GRAVEL, Material.SAND, Material.SPONGE, Material.ICE, Material.PACKED_ICE, Material.BLUE_ICE,
                Material.GLOWSTONE, Material.BASALT, Material.BLACKSTONE, Material.POLISHED_BASALT, Material.SMOOTH_BASALT, Material.OBSIDIAN, Material.CRYING_OBSIDIAN, Material.TUFF, Material.MAGMA_BLOCK, Material.NETHERRACK, Material.CRIMSON_NYLIUM, Material.WARPED_NYLIUM,
                Material.COPPER_ORE, Material.DEEPSLATE_COPPER_ORE, Material.RAW_COPPER_BLOCK, Material.COPPER_BLOCK,
                Material.LAPIS_ORE, Material.DEEPSLATE_LAPIS_ORE, Material.LAPIS_BLOCK,
                Material.IRON_ORE, Material.DEEPSLATE_IRON_ORE, Material.IRON_BLOCK, Material.RAW_IRON_BLOCK,
                Material.REDSTONE_ORE, Material.DEEPSLATE_REDSTONE_ORE, Material.REDSTONE_BLOCK,
                Material.DIAMOND_ORE, Material.DEEPSLATE_DIAMOND_ORE, Material.DIAMOND_BLOCK,
                Material.GOLD_ORE, Material.RAW_GOLD_BLOCK, Material.NETHER_GOLD_ORE, Material.GOLD_BLOCK,
                Material.EMERALD_ORE, Material.EMERALD_ORE, Material.EMERALD_BLOCK,
                Material.NETHER_QUARTZ_ORE, Material.QUARTZ_BLOCK,
                Material.ANCIENT_DEBRIS, Material.NETHERITE_BLOCK,
                Material.SMALL_AMETHYST_BUD, Material.MEDIUM_AMETHYST_BUD, Material.LARGE_AMETHYST_BUD, Material.AMETHYST_CLUSTER,
                Material.BUDDING_AMETHYST, Material.AMETHYST_BLOCK,
                Material.PRISMARINE, Material.PRISMARINE_BRICKS, Material.DARK_PRISMARINE,
                Material.SCULK, Material.SCULK_VEIN, Material.SCULK_SENSOR, Material.SCULK_CATALYST, Material.SCULK_SHRIEKER));
        farmingCollectionBlocks.addAll(List.of(Material.WHEAT, Material.POTATOES, Material.CARROTS,
                Material.PUMPKIN, Material.CARVED_PUMPKIN, Material.PUMPKIN_STEM, Material.ATTACHED_PUMPKIN_STEM,
                Material.MELON, Material.MELON_STEM, Material.ATTACHED_MELON_STEM,
                Material.CACTUS,
                Material.NETHER_WART, Material.NETHER_WART_BLOCK, Material.WARPED_WART_BLOCK,
                Material.COCOA,
                Material.RED_MUSHROOM, Material.RED_MUSHROOM_BLOCK, Material.BROWN_MUSHROOM, Material.BROWN_MUSHROOM_BLOCK, Material.MUSHROOM_STEM,
                Material.SUGAR_CANE,
                Material.BEETROOTS,
                Material.SWEET_BERRY_BUSH,
                Material.CRIMSON_FUNGUS, Material.WARPED_FUNGUS,
                Material.CAVE_VINES, Material.CAVE_VINES_PLANT,
                Material.DEAD_BUSH));
        affectedByMiningSpeed.addAll(miningCollectionBlocks);
        affectedByMiningSpeed.addAll(Arrays.stream(Material.values()).filter(m -> m.isBlock() && m.toString().contains("BLACKSTONE") && !m.isLegacy()).collect(Collectors.toSet()));
        affectedByMiningSpeed.addAll(Arrays.stream(Material.values()).filter(m -> m.isBlock() && m.toString().contains("DEEPSLATE") && !m.isLegacy()).collect(Collectors.toSet()));
        affectedByMiningSpeed.addAll(Arrays.stream(Material.values()).filter(m -> m.isBlock() && m.toString().contains("PRISMARINE") && !m.isLegacy()).collect(Collectors.toSet()));
        affectedByMiningSpeed.addAll(Arrays.stream(Material.values()).filter(m -> m.isBlock() && m.toString().contains("ANDESITE") && !m.isLegacy()).collect(Collectors.toSet()));
        affectedByMiningSpeed.addAll(Arrays.stream(Material.values()).filter(m -> m.isBlock() && m.toString().contains("DIORITE") && !m.isLegacy()).collect(Collectors.toSet()));
        affectedByMiningSpeed.addAll(Arrays.stream(Material.values()).filter(m -> m.isBlock() && m.toString().contains("GRANITE") && !m.isLegacy()).collect(Collectors.toSet()));
        affectedByMiningSpeed.addAll(Arrays.stream(Material.values()).filter(m -> m.isBlock() && m.toString().contains("COPPER") && !m.isLegacy()).collect(Collectors.toSet()));
        affectedByMiningSpeed.addAll(Arrays.stream(Material.values()).filter(m -> m.isBlock() && m.toString().contains("STONE") && !m.isLegacy()).collect(Collectors.toSet()));
        affectedByMiningSpeed.addAll(Arrays.stream(Material.values()).filter(m -> m.isBlock() && m.toString().contains("BRICK") && !m.isLegacy()).collect(Collectors.toSet()));
        affectedByMiningSpeed.addAll(Arrays.stream(Material.values()).filter(m -> m.isBlock() && m.toString().contains("PURPUR") && !m.isLegacy()).collect(Collectors.toSet()));
        affectedByMiningSpeed.addAll(Arrays.stream(Material.values()).filter(m -> m.isBlock() && m.toString().contains("CONCRETE") && !m.toString().contains("CONCRETE_POWDER") && !m.isLegacy()).collect(Collectors.toSet()));
        affectedByMiningSpeed.addAll(Arrays.stream(Material.values()).filter(m -> m.isBlock() && m.toString().contains("TERRACOTTA") && !m.isLegacy()).collect(Collectors.toSet()));
        affectedByMiningSpeed.addAll(Arrays.stream(Material.values()).filter(m -> m.isBlock() && m.toString().contains("QUARTZ") && !m.isLegacy()).collect(Collectors.toSet()));
        affectedByMiningSpeed.addAll(List.of(
                Material.ANVIL, Material.BELL, Material.BREWING_STAND, Material.CAULDRON, Material.CHAIN, Material.HOPPER, Material.IRON_BARS, Material.IRON_DOOR, Material.IRON_TRAPDOOR, Material.LANTERN, Material.LIGHT_WEIGHTED_PRESSURE_PLATE, Material.HEAVY_WEIGHTED_PRESSURE_PLATE,
                Material.LIGHTNING_ROD,
                Material.PISTON, Material.STICKY_PISTON,
                Material.CONDUIT, Material.SHULKER_BOX,
                Material.ACTIVATOR_RAIL, Material.DETECTOR_RAIL, Material.POWERED_RAIL, Material.RAIL,
                Material.BLAST_FURNACE, Material.DISPENSER, Material.DROPPER, Material.DRIPSTONE_BLOCK, Material.ENCHANTING_TABLE, Material.ENDER_CHEST, Material.FURNACE, Material.GRINDSTONE, Material.LODESTONE, Material.OBSERVER, Material.PACKED_MUD, Material.POINTED_DRIPSTONE, Material.SMOKER, Material.SPAWNER, Material.STONECUTTER,
                Material.RESPAWN_ANCHOR,
                Material.BEACON, Material.SEA_LANTERN, Material.REDSTONE_LAMP));
        List.of(Material.SCULK, Material.SCULK_VEIN, Material.SCULK_SENSOR, Material.SCULK_CATALYST, Material.SCULK_SHRIEKER, Material.SAND, Material.GRAVEL, Material.SPONGE, Material.WET_SPONGE, Material.REINFORCED_DEEPSLATE).forEach(affectedByMiningSpeed::remove);
    }

    public static NamespacedKey getWorldPlacedKey() {
        return worldPlacedKey;
    }

    @EventHandler
    public void onOreBreak(BlockDamageEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
            return;
        }
        ItemInfo info = ItemEditor.getMainHandInfo(event.getPlayer());
        double breakingPower = 0, hardness = event.getBlock().getType().getHardness(), breakingPowerNeeded = getBreakingPower(event.getBlock().getType());
        String material = ItemEditor.getStringFromEnum(event.getBlock().getType());
        if (info != null &&
                (info.getItemType() == ItemType.PICKAXE || info.getItemType() == ItemType.DRILL || info.getItemType() == ItemType.GAUNTLET) &&
                info.getMiningStats() != null) {
            breakingPower = info.getMiningStats().getStatValue(StatType.BREAKING_POWER);
        }
        if (getPlacedLocationList(event.getBlock()).containsCustomOre(event.getBlock().getLocation())) {
            CustomOreLocation customOreLocation = getPlacedLocationList(event.getBlock()).getCustomOreFromLocation(event.getBlock().getLocation());
            breakingPowerNeeded = customOreLocation.getType().getBreakingPower();
            hardness = customOreLocation.getBlockStrength();
            material = customOreLocation.getType().getName();
        } else {
            if (!affectedByMiningSpeed.contains(event.getBlock().getType())) {
                return;
            }
        }
        if (breakingPower < breakingPowerNeeded) {
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1F, 0.5F);
            event.getPlayer().sendMessage(Component.text("You need a stronger tool to get drops from " + material, NamedTextColor.RED));
        }
        CustomMiningUtils.addSlow(event.getPlayer(), (int) (hardness * 1.5));
        CustomMiningUtils.createBreakingBlock(event.getBlock(), hardness * 0.15);
    }

    @EventHandler
    public void onDamageAbort(BlockDamageAbortEvent event) {
        CustomMiningUtils.abortRemove(event.getBlock().getLocation());
        CustomMiningUtils.removeSlow(event.getPlayer());
    }

    @EventHandler
    public void onOreBlockDamage(PlayerAnimationEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
            return;
        }
        Player player = event.getPlayer();
        Block block = player.getTargetBlock(transparent, 5);
        boolean isVanilla = true;
        if (getPlacedLocationList(block).containsCustomOre(block.getLocation())) {
            isVanilla = false;
        } else {
            if (!affectedByMiningSpeed.contains(block.getType())) {
                return;
            }
        }
        Location blockLoc = block.getLocation();
        if (!CustomMiningUtils.isBreakingBlock(blockLoc)) {
            return;
        }
        if (player.getLocation().distanceSquared(blockLoc) > 49) {
            return;
        }
        PlayerStats playerStats = StatsListeners.getPlayerStats(player);
        MiningStats add = StatsListeners.getEventMining(player, block, IncreaseType.INCREASE),
                addPercent = StatsListeners.getEventMining(player, block, IncreaseType.ADDITIVE_PERCENT),
                mult = StatsListeners.getEventMining(player, block, IncreaseType.MULTIPLICATIVE_PERCENT);
        double miningSpeed = (playerStats.getStatValue(StatType.MINING_SPEED) + add.getStatValue(StatType.MINING_SPEED)) * (1 + (addPercent.getStatValue(StatType.MINING_SPEED) / 100)) * mult.getStatValue(StatType.MINING_SPEED);
        BreakingBlock breakBlock = CustomMiningUtils.getBreakingBlock(blockLoc);
        double damage = Math.max(1, miningSpeed) / 20;
        if (isVanilla) {
            damage /= 25;
        }
        breakBlock.damage(player, damage);
    }

    @EventHandler
    public void onOreBreak(BlockBreakEvent event) {
        PlacedLocationList locations = getPlacedLocationList(event.getBlock());
        CustomOreLocation customOre = locations.getCustomOreFromLocation(event.getBlock().getLocation());
        boolean isCustom = removeOreLocation(event), playerPlaced = removePlacedLocation(event);
        CustomMiningUtils.removeBreakingBlock(event.getBlock().getLocation());
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if (player.getGameMode() == GameMode.CREATIVE) {
            return;
        }
        int breakingPower = 0;
        ItemInfo mainHand = ItemEditor.getMainHandInfo(player);
        if (mainHand != null) {
            if (mainHand.getEnchantmentsHolder() != null && mainHand.getEnchantmentsHolder().containsEnchant(EnchantmentType.SILK_TOUCH)) {
                return;
            }
            if (mainHand.getMiningStats() != null) {
                breakingPower = (int) mainHand.getMiningStats().getStatValue(StatType.BREAKING_POWER);
            }
        }
        List<ItemDropObject> drops;
        PlayerStats playerStats = StatsListeners.getPlayerStats(player);
        MiningStats add = StatsListeners.getEventMining(player, block, IncreaseType.INCREASE),
                addPercent = StatsListeners.getEventMining(player, block, IncreaseType.ADDITIVE_PERCENT),
                mult = StatsListeners.getEventMining(player, block, IncreaseType.MULTIPLICATIVE_PERCENT);
        double miningFortune = (playerStats.getStatValue(StatType.MINING_FORTUNE) + add.getStatValue(StatType.MINING_FORTUNE)) * (1 + (addPercent.getStatValue(StatType.MINING_FORTUNE) / 100)) * mult.getStatValue(StatType.MINING_FORTUNE),
                pristine = (playerStats.getStatValue(StatType.PRISTINE) + add.getStatValue(StatType.PRISTINE)) * (1 + (addPercent.getStatValue(StatType.PRISTINE) / 100)) * mult.getStatValue(StatType.PRISTINE),
                purity = (playerStats.getStatValue(StatType.PRISTINE) + add.getStatValue(StatType.PURITY)) * (1 + (addPercent.getStatValue(StatType.PURITY) / 100)) * mult.getStatValue(StatType.PURITY);
        if (playerPlaced) {
            miningFortune = 0;
            pristine = 0;
            purity = 0;
        }
        int breakingPowerNeeded = getBreakingPower(block.getType());
        List<Material> replaceBlock = null;
        List<CustomOreType> replaceCustomBlock = null;
        double exp = 0;
        if (isCustom) {
            breakingPowerNeeded = customOre.getType().getBreakingPower();
            drops = customOre.getDrops();
            exp = customOre.getType().getXp();
        } else {
            if (!miningCollectionBlocks.contains(event.getBlock().getType())) {
                if (affectedByMiningSpeed.contains(event.getBlock().getType())) {
                    if (breakingPower < breakingPowerNeeded) {
                        event.setDropItems(false);
                    } else {
                        event.setDropItems(true);
                    }
                    return;
                }
                return;
            }
            exp = getXp(block.getType());
            if (Math.random() * 100 < purity) {
                if (Math.random() < 0.5) {
                    replaceBlock = getReplaceBlock(block.getType());
                } else {
                    replaceCustomBlock = getReplaceCustomBlocks(block.getType());
                }
            }
            drops = getMiningDropsForMaterial(block.getType(), miningFortune);
        }
        if (breakingPower < breakingPowerNeeded) {
            event.setDropItems(false);
            return;
        }
        event.setDropItems(false);
        for (ItemDropObject drop : drops) {
            ItemDropObject pristineDrop = GemstoneUtils.handlePristine(drop, miningFortune, pristine);
            if (pristineDrop != null) {
                pristineDrop.drop(block.getLocation());
                GemstoneObject gem = GemstoneUtils.getGemObject(pristineDrop.getItem());
                player.sendMessage(Component.text("PRISTINE ", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD)
                        .append(Component.text("You found ", NamedTextColor.WHITE))
                        .append(Component.text(gem.getGem().getStat().getSymbol() + " " + gem.getRating() + " " + ItemEditor.getStringFromEnum(gem.getGem()) + " ", gem.getGem().getStat().getColor()))
                        .append(Component.text("x" + pristineDrop.getItem().getAmount(), NamedTextColor.DARK_GRAY)).append(Component.text("!", NamedTextColor.WHITE)).decoration(TextDecoration.BOLD, false));
            }
            drop.drop(block.getLocation());
        }
        if (!playerPlaced) {
            SkillUtils.addBreakingXpToPlayer(player, SkillType.MINING, block, exp);
        }
        if (replaceBlock != null) {
            Material finalReplaceBlock = replaceBlock.get((int) (Math.random() * replaceBlock.size()));
            if (breakingPower >= getBreakingPower(finalReplaceBlock)) {
                BukkitRunnable run = new BukkitRunnable() {
                    @Override
                    public void run() {
                        block.setType(finalReplaceBlock);
                        event.getPlayer().sendMessage(Component.text("Purified! You have uncovered the true material of this block. A ", NamedTextColor.WHITE)
                                .append(Component.text(ItemEditor.getStringFromEnum(finalReplaceBlock), NamedTextColor.AQUA))
                                .append(Component.text(" has spawned", NamedTextColor.WHITE)));
                    }
                };
                run.runTaskLater(plugin, 3);
            }
        }
        if (replaceCustomBlock != null) {
            CustomOreType customType = replaceCustomBlock.get((int) (Math.random() * replaceCustomBlock.size()));
            if (breakingPower >= customType.getBreakingPower()) {
                BukkitRunnable run = new BukkitRunnable() {
                    @Override
                    public void run() {
                        createCustomBlock(customType, block.getLocation());
                        event.getPlayer().sendMessage(Component.text("Purified! You have uncovered the true material of this block. A ", NamedTextColor.WHITE)
                                .append(Component.text(ItemEditor.getStringFromEnum(customType), NamedTextColor.AQUA))
                                .append(Component.text(" has spawned", NamedTextColor.WHITE)));
                    }
                };
                run.runTaskLater(plugin, 3);
            }
        }
    }

    public static void createCustomBlock(CustomOreType type, Location location) {
        PlacedLocationList placedLocationList = getPlacedLocationList(location.getBlock());
        CustomOreLocation customOreLocation = placedLocationList.addCustomOreLocation(location, type);
        location.getBlock().setType(customOreLocation.getVanillaMaterial());
        CustomMiningUtils.createBreakingBlock(location.getBlock(), customOreLocation.getBlockStrength() * 0.15);
        location.getBlock().getWorld().getPersistentDataContainer().set(worldPlacedKey, new PlacedLocationListDataType(), placedLocationList);
    }

    @EventHandler
    public void onLogBreak(BlockBreakEvent event) {
        if (!foragingCollectionBlocks.contains(event.getBlock().getType())) {
            return;
        }
        if (removePlacedLocation(event)) {
            return;
        }
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
            return;
        }
        event.setDropItems(false);
        Player player = event.getPlayer();
        PlayerStats playerStats = StatsListeners.getPlayerStats(player);
        GatheringStats add = StatsListeners.getEventGathering(player, event.getBlock(), IncreaseType.INCREASE),
                addPercent = StatsListeners.getEventGathering(player, event.getBlock(), IncreaseType.ADDITIVE_PERCENT),
                mult = StatsListeners.getEventGathering(player, event.getBlock(), IncreaseType.MULTIPLICATIVE_PERCENT);
        double foragingFortune = (playerStats.getStatValue(StatType.FORAGING_FORTUNE) + add.getStatValue(StatType.FORAGING_FORTUNE)) * (1 + (addPercent.getStatValue(StatType.FORAGING_FORTUNE) / 100)) * mult.getStatValue(StatType.FORAGING_FORTUNE);
        for (ItemDropObject drop : getDropsForMaterial(event.getBlock().getType(), foragingFortune)) {
            drop.drop(event.getBlock().getLocation());
        }
        SkillUtils.addBreakingXpToPlayer(player, SkillType.FORAGING, event.getBlock(), getXp(event.getBlock().getType()));
    }

    @EventHandler
    public void onGravityCropBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() != Material.SUGAR_CANE && event.getBlock().getType() != Material.CACTUS
                && event.getBlock().getType() != Material.CAVE_VINES && event.getBlock().getType() != Material.CAVE_VINES_PLANT) {
            return;
        }
        int notPlaced = 0;
        double exp = 0;
        if (!removePlacedLocation(event)) {
            notPlaced++;
            exp += getXp(event.getBlock().getType());
        }
        removeOreLocation(event);
        Block block = event.getBlock();
        Material material = event.getBlock().getType();
        PlacedLocationList placedList = getPlacedLocationList(event.getBlock());
        PlayerStats playerStats = StatsListeners.getPlayerStats(event.getPlayer());
        GatheringStats add = StatsListeners.getEventGathering(event.getPlayer(), event.getBlock(), IncreaseType.INCREASE),
                addPercent = StatsListeners.getEventGathering(event.getPlayer(), event.getBlock(), IncreaseType.ADDITIVE_PERCENT),
                mult = StatsListeners.getEventGathering(event.getPlayer(), event.getBlock(), IncreaseType.MULTIPLICATIVE_PERCENT);
        double farming = (playerStats.getStatValue(StatType.FARMING_FORTUNE) + add.getStatValue(StatType.FARMING_FORTUNE)) * (1 + (addPercent.getStatValue(StatType.FARMING_FORTUNE) / 100)) * mult.getStatValue(StatType.FARMING_FORTUNE);
        if (block.getType() == Material.SUGAR_CANE || block.getType() == Material.CACTUS) {
            List<Block> above = getBlocksInDirectionMatching(block.getLocation(), block.getType(), BlockFace.UP);
            for (Block aboveBlock : above) {
                if (!placedList.removePlacedLocation(aboveBlock.getLocation())) {
                    exp += getXp(aboveBlock.getType());
                    notPlaced++;
                }
            }
        }
        if (block.getType() == Material.CAVE_VINES || block.getType() == Material.CAVE_VINES_PLANT) {
            material = Material.GLOW_BERRIES;
            List<Block> downVines = getBlocksInDirectionMatching(block.getLocation(), Material.CAVE_VINES, BlockFace.DOWN);
            downVines.addAll(getBlocksInDirectionMatching(block.getLocation(), Material.CAVE_VINES_PLANT, BlockFace.DOWN));
            for (Block downBlock : downVines) {
                if (!placedList.removePlacedLocation(downBlock.getLocation()) && downBlock.getBlockData() instanceof CaveVinesPlant vinesPlant && vinesPlant.isBerries()) {
                    exp += getXp(downBlock.getType());
                    notPlaced++;
                }
            }
        }
        block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(material, notPlaced * getAddedTimes(farming)));
        ItemStack mainHand = event.getPlayer().getInventory().getItemInMainHand();
        ItemInfo info = ItemEditor.getInfo(mainHand);
        if (info != null && info.getExtraInfo() != null && info.getExtraInfo() instanceof FarmingToolInfo farmTool) {
            farmTool.add(block.getType(), notPlaced);
            info.setExtraInfo(farmTool);
            mainHand.getItemMeta().getPersistentDataContainer().set(ItemEditor.getItemKey(), new ItemInfoDataType(), info);
        }
        SkillUtils.addBreakingXpToPlayer(event.getPlayer(), SkillType.FARMING, block, exp);
    }

    @EventHandler
    public void onCropBreak(BlockBreakEvent event) {
        if (!farmingCollectionBlocks.contains(event.getBlock().getType())) {
            return;
        }
        if (event.getBlock().getType() == Material.SUGAR_CANE || event.getBlock().getType() == Material.CACTUS
                || event.getBlock().getType() == Material.CAVE_VINES || event.getBlock().getType() == Material.CAVE_VINES_PLANT) {
            return;
        }
        if (event.getBlock().getBlockData() instanceof Ageable age) {
            if (age.getAge() < age.getMaximumAge()) {
                removePlacedLocation(event);
                removeOreLocation(event);
                return;
            }
        }
        removeOreLocation(event);
        Material mat = event.getBlock().getType();
        if (removePlacedLocation(event)) {
            if (mat == Material.PUMPKIN || mat == Material.CARVED_PUMPKIN
                    || mat == Material.MELON
                    || mat == Material.RED_MUSHROOM || mat == Material.RED_MUSHROOM_BLOCK || mat == Material.BROWN_MUSHROOM || mat == Material.BROWN_MUSHROOM_BLOCK || mat == Material.MUSHROOM_STEM
                    || mat == Material.CRIMSON_FUNGUS || mat == Material.WARPED_FUNGUS
                    || mat == Material.CAVE_VINES || mat == Material.CAVE_VINES_PLANT
                    || mat == Material.DEAD_BUSH) {
                return;
            } else {
                getPlacedLocationList(event.getBlock()).removeDropLocation(event.getBlock().getLocation());
            }
        }
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
            return;
        }
        event.setDropItems(false);
        SkillType expType = SkillType.FARMING;
        if (mat == Material.SWEET_BERRY_BUSH) {
            expType = SkillType.FORAGING;
        }
        if (mat == Material.NETHER_WART || mat == Material.CRIMSON_FUNGUS || mat == Material.WARPED_FUNGUS) {
            expType = SkillType.ALCHEMY;
        }
        PlayerStats playerStats = StatsListeners.getPlayerStats(event.getPlayer());
        GatheringStats add = StatsListeners.getEventGathering(event.getPlayer(), event.getBlock(), IncreaseType.INCREASE),
                addPercent = StatsListeners.getEventGathering(event.getPlayer(), event.getBlock(), IncreaseType.ADDITIVE_PERCENT),
                mult = StatsListeners.getEventGathering(event.getPlayer(), event.getBlock(), IncreaseType.MULTIPLICATIVE_PERCENT);
        double farmingFortune = (playerStats.getStatValue(StatType.FARMING_FORTUNE) + add.getStatValue(StatType.FARMING_FORTUNE)) * (1 + (addPercent.getStatValue(StatType.FARMING_FORTUNE) / 100)) * mult.getStatValue(StatType.FARMING_FORTUNE);
        for (ItemDropObject drop : getDropsForMaterial(event.getBlock().getType(), farmingFortune)) {
            drop.drop(event.getBlock().getLocation());
        }
        SkillUtils.addBreakingXpToPlayer(event.getPlayer(), expType, event.getBlock(), getXp(mat));
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        addPlacedLocation(event);
    }

    @EventHandler
    public void onBlockBurn(BlockBurnEvent event) {
        removePlacedLocation(event);
        removeOreLocation(event);
    }

    @EventHandler
    public void onBlockBreakBlock(BlockBreakBlockEvent event) {
        removePlacedLocation(event);
        removeOreLocation(event);
    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent event) {
        removePlacedLocation(event);
        removeOreLocation(event);
    }

    @EventHandler
    public void onBlockFade(BlockFadeEvent event) {
        removePlacedLocation(event);
        removeOreLocation(event);
    }

    @EventHandler
    public void onLeafDecay(LeavesDecayEvent event) {
        removePlacedLocation(event);
        removeOreLocation(event);
    }

    @EventHandler
    public void onBlockDestroy(BlockDestroyEvent event) {
        removePlacedLocation(event);
        removeOreLocation(event);
    }

    @EventHandler
    public void onPistonPush(BlockPistonExtendEvent event) {
        updatePistonPlaced(event.getDirection(), event.getBlocks(), event);
    }

    @EventHandler
    public void onPistonRetract(BlockPistonRetractEvent event) {
        updatePistonPlaced(event.getDirection(), event.getBlocks(), event);
    }

    private void updatePistonPlaced(BlockFace direction, List<Block> blocks, BlockPistonEvent event) {
        PlacedLocationList placedList = getPlacedLocationList(event.getBlock());
        int changeX = 0, changeY = 0, changeZ = 0;
        switch (direction) {
            case NORTH -> changeZ = -1;
            case SOUTH -> changeZ = 1;
            case EAST -> changeX = 1;
            case WEST -> changeX = -1;
            case UP -> changeY = 1;
            case DOWN -> changeY = -1;
        }
        for (Block block : blocks) {
            PlacedLocation newLocation = placedList.getPlacedFromLocation(block.getLocation());
            if (newLocation != null) {
                newLocation.add(changeX, changeY, changeZ);
            }
            PlacedLocation newOreLocation = placedList.getCustomOreFromLocation(block.getLocation());
            if (newOreLocation != null) {
                newOreLocation.add(changeX, changeY, changeZ);
            }
        }
        event.getBlock().getWorld().getPersistentDataContainer().set(worldPlacedKey, new PlacedLocationListDataType(), placedList);
    }

    public static List<ItemDropObject> getDropsForMaterial(Material material, double fortune) {
        int multiplier = 1 + getAddedTimes(fortune);
        return switch (material) {
            default -> List.of(new ItemDropObject(material, 1));
            case WHEAT ->
                    List.of(new ItemDropObject(Material.WHEAT, multiplier), new ItemDropObject(Material.WHEAT_SEEDS, ((int) (Math.random() * 3)) * multiplier));
            case POTATOES ->
                    List.of(new ItemDropObject(Material.POTATO, ((int) (Math.random() * 5) + 1) * multiplier), new ItemDropObject(2, Material.POISONOUS_POTATO, multiplier));
            case CARROTS -> List.of(new ItemDropObject(Material.CARROT, ((int) (Math.random() * 4) + 2) * multiplier));
            case PUMPKIN_STEM, ATTACHED_PUMPKIN_STEM ->
                    List.of(new ItemDropObject(Material.PUMPKIN_SEEDS, (int) (Math.random() * 4) * multiplier));
            case MELON ->
                    List.of(new ItemDropObject(Material.MELON_SLICE, ((int) (Math.random() * 5) + 3) * multiplier));
            case MELON_STEM, ATTACHED_MELON_STEM ->
                    List.of(new ItemDropObject(Material.MELON_SEEDS, (int) (Math.random() * 4) * multiplier));
            case NETHER_WART ->
                    List.of(new ItemDropObject(Material.NETHER_WART, ((int) (Math.random() * 3) + 2) * multiplier));
            case COCOA ->
                    List.of(new ItemDropObject(Material.COCOA_BEANS, ((int) (Math.random() * 2) + 2) * multiplier));
            case RED_MUSHROOM_BLOCK ->
                    List.of(new ItemDropObject(Material.RED_MUSHROOM, (int) (Math.random() * 3) * multiplier));
            case BROWN_MUSHROOM_BLOCK ->
                    List.of(new ItemDropObject(Material.BROWN_MUSHROOM, (int) (Math.random() * 3) * multiplier));
            case MUSHROOM_STEM ->
                    List.of(new ItemDropObject(Math.random() < 0.5 ? Material.RED_MUSHROOM : Material.BROWN_MUSHROOM, (int) (Math.random() * 3) * multiplier));
            case BEETROOTS ->
                    List.of(new ItemDropObject(Material.BEETROOT, multiplier), new ItemDropObject(Material.BEETROOT_SEEDS, ((int) (Math.random() * 4) + 1) * multiplier));
            case SWEET_BERRY_BUSH ->
                    List.of(new ItemDropObject(Material.SWEET_BERRIES, ((int) (Math.random() * 2) + 2) * multiplier));
            case CAVE_VINES, CAVE_VINES_PLANT -> List.of(new ItemDropObject(Material.GLOW_BERRIES, multiplier));
            case DEAD_BUSH ->
                    List.of(new ItemDropObject(75, Material.STICK, (int) (Math.random() * 3)), new ItemDropObject(25, Material.DEAD_BUSH, multiplier));
        };
    }

    public static List<ItemDropObject> getMiningDropsForMaterial(Material material, double fortune) {
        int multiplier = 1 + getAddedTimes(fortune), gemDefault = (int) (Math.random() * 3) + 2;
        return switch (material) {
            default -> List.of(new ItemDropObject(material, multiplier));
            case STONE -> List.of(new ItemDropObject(Material.COBBLESTONE, multiplier));
            case COAL_ORE, DEEPSLATE_COAL_ORE ->
                    List.of(new ItemDropObject(Material.COAL, multiplier), new ItemDropObject(GemstoneType.PEARL, 4, gemDefault));
            case GLOWSTONE ->
                    List.of(new ItemDropObject(Material.GLOWSTONE_DUST, (gemDefault) * multiplier), new ItemDropObject(GemstoneType.TOPAZ, 4, gemDefault), new ItemDropObject(GemstoneType.AMBER, 2, gemDefault));
            case COPPER_ORE, DEEPSLATE_COPPER_ORE ->
                    List.of(new ItemDropObject(Material.RAW_COPPER, ((int) (Math.random() * 4) + 2) * multiplier), new ItemDropObject(GemstoneType.AMBER, 4, gemDefault));
            case LAPIS_ORE, DEEPSLATE_LAPIS_ORE ->
                    List.of(new ItemDropObject(Material.LAPIS_LAZULI, ((int) (Math.random() * 6) + 4) * multiplier), new ItemDropObject(GemstoneType.SAPPHIRE, 5, gemDefault));
            case IRON_ORE, DEEPSLATE_IRON_ORE -> List.of(new ItemDropObject(Material.RAW_IRON, multiplier));
            case REDSTONE_ORE, DEEPSLATE_REDSTONE_ORE ->
                    List.of(new ItemDropObject(Material.REDSTONE, ((int) (Math.random() * 2) + 4) * multiplier), new ItemDropObject(GemstoneType.RUBY, 4, gemDefault));
            case DIAMOND_ORE, DEEPSLATE_DIAMOND_ORE ->
                    List.of(new ItemDropObject(Material.DIAMOND, multiplier), new ItemDropObject(GemstoneType.JASPER, 2, gemDefault), new ItemDropObject(GemstoneType.SAPPHIRE, 4, gemDefault));
            case GOLD_ORE, DEEPSLATE_GOLD_ORE ->
                    List.of(new ItemDropObject(Material.RAW_GOLD, multiplier), new ItemDropObject(GemstoneType.TOPAZ, 2, gemDefault));
            case NETHER_GOLD_ORE ->
                    List.of(new ItemDropObject(Material.GOLD_NUGGET, ((int) (Math.random() * 5) + 2) * multiplier), new ItemDropObject(GemstoneType.TOPAZ, 3, gemDefault));
            case EMERALD_ORE, DEEPSLATE_EMERALD_ORE ->
                    List.of(new ItemDropObject(Material.EMERALD, multiplier), new ItemDropObject(GemstoneType.JASPER, 2, gemDefault));
            case NETHER_QUARTZ_ORE ->
                    List.of(new ItemDropObject(Material.QUARTZ, multiplier), new ItemDropObject(GemstoneType.OPAL, 4, gemDefault));
            case SMALL_AMETHYST_BUD, MEDIUM_AMETHYST_BUD, LARGE_AMETHYST_BUD, AMETHYST_CLUSTER ->
                    List.of(new ItemDropObject(Material.AMETHYST_SHARD, 4 * multiplier), new ItemDropObject(GemstoneType.AMETHYST, 2, gemDefault));
            case BUDDING_AMETHYST ->
                    List.of(new ItemDropObject(Material.AMETHYST_BLOCK, multiplier), new ItemDropObject(GemstoneType.AMETHYST, 4, gemDefault));
        };
    }

    public static double getXp(Material material) {
        if (foragingCollectionBlocks.contains(material)) {
            if (flowers.contains(material)) {
                return 3;
            }
            if (material == Material.CRIMSON_STEM || material == Material.WARPED_STEM ||
                    material == Material.STRIPPED_CRIMSON_STEM || material == Material.STRIPPED_WARPED_STEM) {
                return 18;
            }
            return 10;
        }
        return switch (material) {
            default -> 0;
            case NETHERRACK, ICE -> 0.5;
            case COBBLESTONE, STONE, ANDESITE, DIORITE, GRANITE, CALCITE, GRAVEL, DEEPSLATE -> 1;
            case SUGAR_CANE, RED_MUSHROOM_BLOCK, BROWN_MUSHROOM_BLOCK, MUSHROOM_STEM, CACTUS, CAVE_VINES, CAVE_VINES_PLANT ->
                    2;
            case SAND, SCULK_VEIN -> 3;
            case WHEAT, MELON, POTATOES, CARROTS, COCOA, BEETROOTS, SWEET_BERRY_BUSH -> 4;
            case PUMPKIN, CARVED_PUMPKIN -> 4.5;
            case COAL_ORE, IRON_ORE, NETHER_QUARTZ_ORE, COPPER_ORE, SCULK -> 5;
            case GOLD_ORE, RED_MUSHROOM, BROWN_MUSHROOM, DEEPSLATE_COAL_ORE, DEEPSLATE_IRON_ORE, DEEPSLATE_COPPER_ORE ->
                    6;
            case LAPIS_ORE, REDSTONE_ORE, GLOWSTONE, DEEPSLATE_GOLD_ORE, SCULK_SENSOR, SCULK_CATALYST, SCULK_SHRIEKER ->
                    7;
            case NETHER_WART -> 8;
            case DEEPSLATE_LAPIS_ORE, DEEPSLATE_REDSTONE_ORE, RAW_IRON_BLOCK, RAW_COPPER_BLOCK -> 9;
            case DIAMOND_ORE, EMERALD_ORE, COAL_BLOCK, IRON_BLOCK, QUARTZ_BLOCK, COPPER_BLOCK, RAW_GOLD_BLOCK -> 10;
            case DEEPSLATE_DIAMOND_ORE, DEEPSLATE_EMERALD_ORE, GOLD_BLOCK -> 11;
            case LAPIS_BLOCK, REDSTONE_BLOCK -> 12;
            case DIAMOND_BLOCK, EMERALD_BLOCK -> 15;
            case OBSIDIAN, CRYING_OBSIDIAN -> 20;
        };
    }

    public int getBreakingPower(Material material) {
        if (!affectedByMiningSpeed.contains(material)) {
            return 0;
        }
        if (material.toString().contains("SHULKER_BOX")) {
            return 0;
        }
        if (material.toString().contains("COPPER")) {
            return 2;
        }
        return switch (material) {
            case BEACON, GLOWSTONE, REDSTONE_LAMP, ACTIVATOR_RAIL, DETECTOR_RAIL, POWERED_RAIL, RAIL, PISTON, STICKY_PISTON, CONDUIT ->
                    0;
            default -> 1;
            case DEEPSLATE, IRON_ORE, DEEPSLATE_IRON_ORE, RAW_IRON_BLOCK, IRON_BLOCK, LAPIS_ORE, DEEPSLATE_LAPIS_ORE, LAPIS_BLOCK, COPPER_ORE, DEEPSLATE_COPPER_ORE, LIGHTNING_ROD ->
                    2;
            case DIAMOND_ORE, DEEPSLATE_DIAMOND_ORE, DIAMOND_BLOCK, EMERALD_ORE, DEEPSLATE_EMERALD_ORE, EMERALD_BLOCK, GOLD_ORE, DEEPSLATE_GOLD_ORE, RAW_GOLD_BLOCK, GOLD_BLOCK, REDSTONE_ORE, DEEPSLATE_REDSTONE_ORE ->
                    3;
            case OBSIDIAN, CRYING_OBSIDIAN, NETHERITE_BLOCK, RESPAWN_ANCHOR, ANCIENT_DEBRIS -> 4;
        };
    }

    public static int getAddedTimes(double chance) {
        int count = (int) (chance / 100);
        if (Math.random() * 100 < chance % 100.0) {
            count++;
        }
        return count;
    }

    private static List<Block> getBlocksInDirectionMatching(Location location, Material material, BlockFace direction) {
        List<Block> blocks = new ArrayList<>();
        Block current = location.getBlock();
        while (current.getRelative(direction).getType() == material && current.getRelative(direction).getType() != Material.AIR) {
            blocks.add(current.getRelative(direction));
            current = current.getRelative(direction);
        }
        return blocks;
    }

    private static List<Material> getReplaceBlock(Material material) {
        if (!miningCollectionBlocks.contains(material)) {
            return null;
        }
        return switch (material) {
            default -> null;
            case COAL_ORE -> List.of(Material.COAL_BLOCK, Material.DIAMOND_ORE);
            case DEEPSLATE_COAL_ORE -> List.of(Material.COAL_BLOCK, Material.DEEPSLATE_DIAMOND_ORE);
            case COPPER_ORE, DEEPSLATE_COPPER_ORE -> List.of(Material.COPPER_BLOCK, Material.RAW_COPPER_BLOCK);
            case IRON_ORE, DEEPSLATE_IRON_ORE -> List.of(Material.IRON_BLOCK, Material.RAW_IRON_BLOCK);
            case LAPIS_ORE, DEEPSLATE_LAPIS_ORE -> List.of(Material.LAPIS_BLOCK);
            case REDSTONE_ORE, DEEPSLATE_REDSTONE_ORE -> List.of(Material.REDSTONE_BLOCK);
            case GOLD_ORE, DEEPSLATE_GOLD_ORE -> List.of(Material.GOLD_BLOCK);
            case DIAMOND_ORE, DEEPSLATE_DIAMOND_ORE -> List.of(Material.DIAMOND_BLOCK);
            case EMERALD_ORE, DEEPSLATE_EMERALD_ORE -> List.of(Material.EMERALD_BLOCK);
            case NETHER_QUARTZ_ORE -> List.of(Material.QUARTZ_BLOCK);
            case ANCIENT_DEBRIS -> List.of(Material.NETHERITE_BLOCK);
        };
    }

    private static List<CustomOreType> getReplaceCustomBlocks(Material original) {
        if (!(miningCollectionBlocks.contains(original))) {
            return null;
        }
        return switch (original) {
            case STONE, COBBLESTONE, DEEPSLATE, ANDESITE, DIORITE, GRANITE, CALCITE, IRON_ORE, DEEPSLATE_IRON_ORE, IRON_BLOCK ->
                    List.of(CustomOreType.MITHRIL, CustomOreType.TITANIUM);
            case GLOWSTONE, COPPER_ORE, DEEPSLATE_COPPER_ORE, GOLD_ORE, DEEPSLATE_GOLD_ORE, NETHER_GOLD_ORE, GOLD_BLOCK ->
                    List.of(CustomOreType.AMBER, CustomOreType.TOPAZ);
            case COAL_ORE, DEEPSLATE_COAL_ORE, COAL_BLOCK ->
                    List.of(CustomOreType.RUBY, CustomOreType.SAPPHIRE, CustomOreType.PEARL);
            case LAPIS_ORE, DEEPSLATE_LAPIS_ORE, LAPIS_BLOCK ->
                    List.of(CustomOreType.JADE, CustomOreType.SAPPHIRE, CustomOreType.AMETHYST);
            case REDSTONE_ORE, DEEPSLATE_REDSTONE_ORE, REDSTONE_BLOCK ->
                    List.of(CustomOreType.RUBY, CustomOreType.JADE);
            case DIAMOND_ORE, DEEPSLATE_DIAMOND_ORE, DIAMOND_BLOCK ->
                    List.of(CustomOreType.SAPPHIRE, CustomOreType.JASPER);
            case EMERALD_ORE, DEEPSLATE_EMERALD_ORE, EMERALD_BLOCK ->
                    List.of(CustomOreType.JASPER, CustomOreType.PEARL);
            case NETHER_QUARTZ_ORE, QUARTZ_BLOCK, NETHERRACK -> List.of(CustomOreType.OPAL, CustomOreType.PEARL);
            case ANCIENT_DEBRIS, NETHERITE_BLOCK -> List.of(CustomOreType.JASPER, CustomOreType.OPAL);
            case OBSIDIAN, CRYING_OBSIDIAN, TUFF, MAGMA_BLOCK -> List.of(CustomOreType.TOPAZ, CustomOreType.OPAL);
            case SMALL_AMETHYST_BUD, MEDIUM_AMETHYST_BUD, LARGE_AMETHYST_BUD, AMETHYST_CLUSTER, BUDDING_AMETHYST ->
                    List.of(CustomOreType.AMETHYST);
            default -> null;
        };
    }

    @NotNull
    public static PlacedLocationList getPlacedLocationList(Block block) {
        PlacedLocationList placedList = block.getWorld().getPersistentDataContainer().get(worldPlacedKey, new PlacedLocationListDataType());
        if (placedList == null) {
            placedList = new PlacedLocationList();
            block.getWorld().getPersistentDataContainer().set(worldPlacedKey, new PlacedLocationListDataType(), placedList);
        }
        return placedList;
    }

    public static void setPlacedLocationList(Block block, PlacedLocationList list) {
        block.getWorld().getPersistentDataContainer().set(worldPlacedKey, new PlacedLocationListDataType(), list);
    }

    /**
     * Tries to add the event block's location as player-placed if the block is affected by fortune. Returns if the location was not already player-placed
     *
     * @param event The event containing the block location
     * @return true if the block is affect by fortune and the location was not already marked as player-placed
     */
    private boolean addPlacedLocation(BlockEvent event) {
        if (!(foragingCollectionBlocks.contains(event.getBlock().getType()) || miningCollectionBlocks.contains(event.getBlock().getType()) || farmingCollectionBlocks.contains(event.getBlock().getType()))) {
            return false;
        }
        PlacedLocationList placed = getPlacedLocationList(event.getBlock());
        Location loc = event.getBlock().getLocation();
        boolean isNew = placed.addPlacedLocation(loc);
        event.getBlock().getWorld().getPersistentDataContainer().set(worldPlacedKey, new PlacedLocationListDataType(), placed);
        return isNew;
    }

    /**
     * Tries to remove the event block's location from the player-placed list. Returns if the location was previously player-placed
     *
     * @param event The event containing the block location
     * @return true if the location was marked as player-placed
     */
    private boolean removePlacedLocation(BlockEvent event) {
        PlacedLocationList placed = getPlacedLocationList(event.getBlock());
        return placed.removePlacedLocation(event.getBlock().getLocation());
    }

    private boolean removeOreLocation(BlockEvent event) {
        PlacedLocationList placed = getPlacedLocationList(event.getBlock());
        return placed.removeCustomOreLocation(event.getBlock().getLocation());
    }

}