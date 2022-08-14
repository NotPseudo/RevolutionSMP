package me.notpseudo.revolutionsmp.items;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import me.notpseudo.revolutionsmp.RevolutionSMP;
import me.notpseudo.revolutionsmp.abilities.AbilityType;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentObject;
import me.notpseudo.revolutionsmp.enchantments.EnchantmentType;
import me.notpseudo.revolutionsmp.itemstats.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * This class holds mostly utility methods related to custom items
 *
 * @author NotPseudo
 */
public class ItemEditor {

    /**
     * The NamespacedKey used to access ItemInfo held in persistent data
     */
    private final static NamespacedKey itemKey = new NamespacedKey(RevolutionSMP.getPlugin(RevolutionSMP.class), "items");

    private final static UUID skullUUID = UUID.fromString("dbff4122-fa87-4f79-91f5-0986b8e3f9b3");


    /**
     * Returns the ItemInfo NamespacedKey for other classes to access ItemInfo in persistent data
     *
     * @return The ItemInfo NamespacedKey
     */
    public static NamespacedKey getItemKey() {
        return itemKey;
    }

    public static UUID getSkullUUID() {
        return skullUUID;
    }

    /**
     * Reads stats stored in the ItemMeta's persistent data and formats the item lore to display info for Players
     *
     * @param meta The ItemMeta to read stats from and generate lore for
     */
    public static void updateLore(ItemMeta meta) {
        ItemInfo itemInfo = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
        if (itemInfo != null) {
            if (itemInfo.getItemType() == ItemType.VANILLA_ITEM) {
                ArrayList<Component> lore = new ArrayList<>(List.of(Component.text(itemInfo.getRarity().name(), itemInfo.getRarity().getRarityColor()).decoration(TextDecoration.BOLD, true).decoration(TextDecoration.ITALIC, false)));
                meta.lore(lore);
                return;
            }
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            if (itemInfo.getItemID() != null && itemInfo.getItemID().isUnbreakable()) {
                meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                meta.setUnbreakable(true);
            }
            if (itemInfo.getItemID() != null && itemInfo.getItemID().isEnchantGlint()) {
                meta.addEnchant(Enchantment.LUCK, 1, true);
            }
            WeaponStats weaponStats = itemInfo.getWeaponStats();
            ArmorStats armorStats = itemInfo.getArmorStats();
            AbilityStats abilityStats = itemInfo.getAbilityStats();
            FishingStats fishingStats = itemInfo.getFishingStats();
            MiningStats miningStats = itemInfo.getMiningStats();
            GatheringStats gatheringStats = itemInfo.getGatheringStats();
            LuckStats luckStats = itemInfo.getLuckStats();
            EnchantmentsHolder enchantmentsHolder = itemInfo.getEnchantmentsHolder();
            AbilitiesHolder abilitiesHolder = itemInfo.getAbilitiesHolder();
            List<Component> lore = new ArrayList<>();
            boolean hasBonus = false;
            Component name, rarity, damage = null, strength = null, critChance = null, critDamage = null, attackSpeed = null, ferocity = null,
                    health = null, defense = null, speed = null, trueDefense = null,
                    abilityDamage = null, intelligence = null,
                    seaCreatureChance = null, fishingTimeDecrease = null,
                    miningSpeed = null, miningFortune = null, pristine = null, breakingPower = null,
                    farmingFortune = null, foragingFortune = null,
                    magicFind = null, petLuck = null, gemstoneSlots = null,
                    canBeReforged = null;
            Component potatoDamage = Component.empty(), potatoStrength = Component.empty(), potatoHealth = Component.empty(), potatoDefense = Component.empty();
            Component reforgeDamage = Component.empty(), reforgeStrength = Component.empty(), reforgeCritChance = Component.empty(), reforgeCritDamage = Component.empty(), reforgeAttackSpeed = Component.empty(), reforgeFerocity = Component.empty(),
                    reforgeHealth = Component.empty(), reforgeDefense = Component.empty(), reforgeSpeed = Component.empty(), reforgeTrueDefense = Component.empty(),
                    reforgeAbilityDamage = Component.empty(), reforgeIntelligence = Component.empty(),
                    reforgeSeaCreatureChance = Component.empty(),
                    reforgeMiningSpeed = Component.empty(), reforgeMiningFortune = Component.empty(), reforgePristine = Component.empty(),
                    reforgeFarmingFortune = Component.empty(), reforgeForagingFortune = Component.empty(),
                    reforgeMagicFind = Component.empty(), reforgePetLuck = Component.empty();
            Component gemStrength = Component.empty(), gemHealth = Component.empty(), gemDef = Component.empty(), gemTrueDef = Component.empty(),
                    gemIntel = Component.empty(), gemMineSpeed = Component.empty(), gemMineFortune = Component.empty(), gemPristine = Component.empty();
            String rarityName = itemInfo.getRarity().name();
            String itemType = itemInfo.getItemType().name();
            NamedTextColor rarityColor = itemInfo.getRarity().getRarityColor();
            if (itemInfo.isRecomb()) {
                rarity = Component.text("S ", rarityColor, TextDecoration.OBFUSCATED, TextDecoration.BOLD).append(Component.text(rarityName + " " + itemType, rarityColor, TextDecoration.BOLD).decoration(TextDecoration.OBFUSCATED, false).append(Component.text(" S", rarityColor, TextDecoration.OBFUSCATED, TextDecoration.BOLD)));
            } else {
                rarity = Component.text(rarityName + " " + itemType).color(rarityColor).decoration(TextDecoration.BOLD, true);
            }
            if (itemInfo.getPotatoBooks() != 0) {
                if (isArmor(itemInfo)) {
                    potatoHealth = getBracketComponent(itemInfo.getPotatoBooks() * 4, NamedTextColor.YELLOW);
                    potatoDefense = getBracketComponent(itemInfo.getPotatoBooks() * 2, NamedTextColor.YELLOW);
                } else if (isWeapon(itemInfo)) {
                    potatoDamage = getBracketComponent(itemInfo.getPotatoBooks() * 2, NamedTextColor.YELLOW);
                    potatoStrength = getBracketComponent(itemInfo.getPotatoBooks() * 2, NamedTextColor.YELLOW);
                }
            }
            String reforgeName = "";
            Reforge reforge = itemInfo.getReforge();
            if (reforge != null) {
                reforgeName = reforge.name().charAt(0) + reforge.name().substring(1).toLowerCase() + " ";
                WeaponStats reforgeWeaponStats = reforge.getWeaponStats(itemInfo.getRarity(), itemInfo.getOwner());
                ArmorStats reforgeArmorStats = reforge.getArmorStats(itemInfo.getRarity(), itemInfo.getOwner());
                AbilityStats reforgeAbilityStats = reforge.getAbilityStats(itemInfo.getRarity(), itemInfo.getOwner());
                FishingStats reforgeFishingStats = reforge.getFishingStats(itemInfo.getRarity(), itemInfo.getOwner());
                MiningStats reforgeMiningStats = reforge.getMiningStats(itemInfo.getRarity(), itemInfo.getOwner());
                GatheringStats reforgeGatheringStats = reforge.getGatheringStats(itemInfo.getRarity(), itemInfo.getOwner());
                LuckStats reforgeLuckStats = reforge.getLuckStats(itemInfo.getRarity(), itemInfo.getOwner());
                if (reforgeWeaponStats.getStatValue(StatType.DAMAGE) != 0) {
                    reforgeDamage = getBracketComponent(reforgeWeaponStats.getStatObject(StatType.DAMAGE), NamedTextColor.BLUE);
                }
                if (reforgeWeaponStats.getStatValue(StatType.STRENGTH) != 0) {
                    reforgeStrength = getBracketComponent(reforgeWeaponStats.getStatObject(StatType.STRENGTH), NamedTextColor.BLUE);
                }
                if (reforgeWeaponStats.getStatValue(StatType.CRIT_CHANCE) != 0) {
                    reforgeCritChance = getBracketComponent(reforgeWeaponStats.getStatObject(StatType.CRIT_CHANCE), NamedTextColor.BLUE);
                }
                if (reforgeWeaponStats.getStatValue(StatType.CRIT_DAMAGE) != 0) {
                    reforgeCritDamage = getBracketComponent(reforgeWeaponStats.getStatObject(StatType.CRIT_DAMAGE), NamedTextColor.BLUE);
                }
                if (reforgeWeaponStats.getStatValue(StatType.ATTACK_SPEED) != 0) {
                    reforgeAttackSpeed = getBracketComponent(reforgeWeaponStats.getStatObject(StatType.ATTACK_SPEED), NamedTextColor.BLUE);
                }
                if (reforgeWeaponStats.getStatValue(StatType.FEROCITY) != 0) {
                    reforgeFerocity = getBracketComponent(reforgeWeaponStats.getStatObject(StatType.FEROCITY), NamedTextColor.BLUE);
                }
                if (reforgeArmorStats.getStatValue(StatType.HEALTH) != 0) {
                    reforgeHealth = getBracketComponent(reforgeArmorStats.getStatObject(StatType.HEALTH), NamedTextColor.BLUE);
                }
                if (reforgeArmorStats.getStatValue(StatType.DEFENSE) != 0) {
                    reforgeDefense = getBracketComponent(reforgeArmorStats.getStatObject(StatType.DEFENSE), NamedTextColor.BLUE);
                }
                if (reforgeArmorStats.getStatValue(StatType.SPEED) != 0) {
                    reforgeSpeed = getBracketComponent(reforgeArmorStats.getStatObject(StatType.SPEED), NamedTextColor.BLUE);
                }
                if (reforgeArmorStats.getStatValue(StatType.TRUE_DEFENSE) != 0) {
                    reforgeTrueDefense = getBracketComponent(reforgeArmorStats.getStatObject(StatType.TRUE_DEFENSE), NamedTextColor.BLUE);
                }
                if (reforgeAbilityStats.getStatValue(StatType.ABILITY_DAMAGE) != 0) {
                    reforgeAbilityDamage = getBracketComponent(reforgeAbilityStats.getStatObject(StatType.ABILITY_DAMAGE), NamedTextColor.BLUE);
                }
                if (reforgeAbilityStats.getStatValue(StatType.INTELLIGENCE) != 0) {
                    reforgeIntelligence = getBracketComponent(reforgeAbilityStats.getStatObject(StatType.INTELLIGENCE), NamedTextColor.BLUE);
                }
                if (reforgeFishingStats.getStatValue(StatType.SEA_CREATURE_CHANCE) != 0) {
                    reforgeSeaCreatureChance = getBracketComponent(reforgeFishingStats.getStatObject(StatType.SEA_CREATURE_CHANCE), NamedTextColor.BLUE);
                }
                if (reforgeMiningStats.getStatValue(StatType.MINING_SPEED) != 0) {
                    reforgeMiningSpeed = getBracketComponent(reforgeMiningStats.getStatObject(StatType.MINING_SPEED), NamedTextColor.BLUE);
                }
                if (reforgeMiningStats.getStatValue(StatType.MINING_FORTUNE) != 0) {
                    reforgeMiningFortune = getBracketComponent(reforgeMiningStats.getStatObject(StatType.MINING_FORTUNE), NamedTextColor.BLUE);
                }
                if (reforgeMiningStats.getStatValue(StatType.PRISTINE) != 0) {
                    reforgePristine = getBracketComponent(reforgeMiningStats.getStatObject(StatType.PRISTINE), NamedTextColor.BLUE);
                }
                if (reforgeGatheringStats.getStatValue(StatType.FARMING_FORTUNE) != 0) {
                    reforgeFarmingFortune = getBracketComponent(reforgeGatheringStats.getStatObject(StatType.FARMING_FORTUNE), NamedTextColor.BLUE);
                }
                if (reforgeGatheringStats.getStatValue(StatType.FORAGING_FORTUNE) != 0) {
                    reforgeForagingFortune = getBracketComponent(reforgeGatheringStats.getStatObject(StatType.FORAGING_FORTUNE), NamedTextColor.BLUE);
                }
                if (reforgeLuckStats.getStatValue(StatType.MAGIC_FIND) != 0) {
                    reforgeMagicFind = getBracketComponent(reforgeLuckStats.getStatObject(StatType.MAGIC_FIND), NamedTextColor.BLUE);
                }
                if (reforgeLuckStats.getStatValue(StatType.PET_LUCK) != 0) {
                    reforgePetLuck = getBracketComponent(reforgeLuckStats.getStatObject(StatType.PET_LUCK), NamedTextColor.BLUE);
                }
                hasBonus = reforge.hasBonus();
            } else {
                canBeReforged = Component.text("This item can be reforged!", NamedTextColor.DARK_GRAY);
            }
            if (itemInfo.getGemstonesHolder() != null) {
                GemstonesHolder gemHolder = itemInfo.getGemstonesHolder();
                gemstoneSlots = gemHolder.getGemLineLore();
                WeaponStats gemWeapon = gemHolder.getGemWeapon(itemInfo.getRarity());
                ArmorStats gemArmor = gemHolder.getGemArmor(itemInfo.getRarity());
                AbilityStats gemAbility = gemHolder.getGemAbility(itemInfo.getRarity());
                MiningStats gemMining = gemHolder.getGemMining(itemInfo.getRarity());
                if (gemWeapon.getStatValue(StatType.STRENGTH) != 0) {
                    gemStrength = getBracketComponent(gemWeapon.getStatObject(StatType.STRENGTH), NamedTextColor.LIGHT_PURPLE);
                }
                if (gemArmor.getStatValue(StatType.HEALTH) != 0) {
                    gemHealth = getBracketComponent(gemArmor.getStatObject(StatType.HEALTH), NamedTextColor.LIGHT_PURPLE);
                }
                if (gemArmor.getStatValue(StatType.DEFENSE) != 0) {
                    gemDef = getBracketComponent(gemArmor.getStatObject(StatType.DEFENSE), NamedTextColor.LIGHT_PURPLE);
                }
                if (gemAbility.getStatValue(StatType.INTELLIGENCE) != 0) {
                    gemIntel = getBracketComponent(gemAbility.getStatObject(StatType.INTELLIGENCE), NamedTextColor.LIGHT_PURPLE);
                }
                if (gemMining.getStatValue(StatType.MINING_SPEED) != 0) {
                    gemMineSpeed = getBracketComponent(gemMining.getStatObject(StatType.MINING_SPEED), NamedTextColor.LIGHT_PURPLE);
                }
                if (gemMining.getStatValue(StatType.MINING_FORTUNE) != 0) {
                    gemMineFortune = getBracketComponent(gemMining.getStatObject(StatType.MINING_FORTUNE), NamedTextColor.LIGHT_PURPLE);
                }
                if (gemMining.getStatValue(StatType.PRISTINE) != 0) {
                    gemPristine = getBracketComponent(gemMining.getStatObject(StatType.PRISTINE), NamedTextColor.LIGHT_PURPLE);
                }
            }
            name = Component.text(reforgeName + itemInfo.getName()).color(itemInfo.getRarity().getRarityColor()); // The displayed name of the item is Reforge + Item Name
            if (weaponStats != null) {
                if (weaponStats.getStatValue(StatType.DAMAGE) != 0) {
                    damage = Component.text("Damage: ", NamedTextColor.GRAY).append(Component.text(getStatString(weaponStats.getStatObject(StatType.DAMAGE)), NamedTextColor.RED)).append(potatoDamage).append(reforgeDamage);
                }
                if (weaponStats.getStatValue(StatType.STRENGTH) != 0) {
                    strength = Component.text("Strength: ", NamedTextColor.GRAY).append(Component.text(getStatString(weaponStats.getStatObject(StatType.STRENGTH)), NamedTextColor.RED)).append(potatoStrength).append(reforgeStrength).append(gemStrength);
                }
                if (weaponStats.getStatValue(StatType.CRIT_CHANCE) != 0) {
                    critChance = Component.text("Crit Chance: ", NamedTextColor.GRAY).append(Component.text(getStatString(weaponStats.getStatObject(StatType.CRIT_CHANCE)), NamedTextColor.RED)).append(reforgeCritChance);
                }
                if (weaponStats.getStatValue(StatType.CRIT_DAMAGE) != 0) {
                    critDamage = Component.text("Crit Damage: ", NamedTextColor.GRAY).append(Component.text(getStatString(weaponStats.getStatObject(StatType.CRIT_DAMAGE)), NamedTextColor.RED)).append(reforgeCritDamage);
                }
                if (weaponStats.getStatValue(StatType.ATTACK_SPEED) != 0) {
                    attackSpeed = Component.text("Bonus Attack Speed: ", NamedTextColor.GRAY).append(Component.text(getStatString(weaponStats.getStatObject(StatType.ATTACK_SPEED)), NamedTextColor.RED)).append(reforgeAttackSpeed);
                }
                if (weaponStats.getStatValue(StatType.FEROCITY) != 0) {
                    ferocity = Component.text("Ferocity: ", NamedTextColor.GRAY).append(Component.text(getStatString(weaponStats.getStatObject(StatType.FEROCITY)), NamedTextColor.GREEN)).append(reforgeFerocity);
                }
            }
            if (armorStats != null) {
                if (armorStats.getStatValue(StatType.HEALTH) != 0) {
                    health = Component.text("Health: ", NamedTextColor.GRAY).append(Component.text(getStatString(armorStats.getStatObject(StatType.HEALTH)), NamedTextColor.GREEN)).append(potatoHealth).append(reforgeHealth).append(gemHealth);
                }
                if (armorStats.getStatValue(StatType.DEFENSE) != 0) {
                    defense = Component.text("Defense: ", NamedTextColor.GRAY).append(Component.text(getStatString(armorStats.getStatObject(StatType.DEFENSE)), NamedTextColor.GREEN)).append(potatoDefense).append(reforgeDefense).append(gemDef);
                }
                if (armorStats.getStatValue(StatType.SPEED) != 0) {
                    speed = Component.text("Speed: ", NamedTextColor.GRAY).append(Component.text(getStatString(armorStats.getStatObject(StatType.SPEED)), NamedTextColor.GREEN)).append(reforgeSpeed);
                }
                if (armorStats.getStatValue(StatType.TRUE_DEFENSE) != 0) {
                    trueDefense = Component.text("True Defense: ", NamedTextColor.GRAY).append(Component.text(getStatString(armorStats.getStatObject(StatType.TRUE_DEFENSE)), NamedTextColor.GREEN)).append(reforgeTrueDefense).append(gemTrueDef);
                }
            }
            if (abilityStats != null) {
                if (abilityStats.getStatValue(StatType.ABILITY_DAMAGE) != 0) {
                    abilityDamage = Component.text("Ability Damage: ", NamedTextColor.GRAY).append(Component.text(getStatString(abilityStats.getStatObject(StatType.ABILITY_DAMAGE)), NamedTextColor.RED)).append(reforgeAbilityDamage);
                }
                if (abilityStats.getStatValue(StatType.INTELLIGENCE) != 0) {
                    intelligence = Component.text("Intelligence: ", NamedTextColor.GRAY).append(Component.text(getStatString(abilityStats.getStatObject(StatType.INTELLIGENCE)), NamedTextColor.GREEN)).append(reforgeIntelligence).append(gemIntel);
                }
            }
            if (fishingStats != null) {
                if (fishingStats.getStatValue(StatType.SEA_CREATURE_CHANCE) != 0) {
                    seaCreatureChance = Component.text("Sea Creature Chance: ", NamedTextColor.GRAY).append(Component.text(getStatString(fishingStats.getStatObject(StatType.SEA_CREATURE_CHANCE)), NamedTextColor.RED)).append(reforgeSeaCreatureChance);
                }
                if (fishingStats.getStatValue(StatType.FISHING_SPEED) != 0) {
                    fishingTimeDecrease = Component.text("Increases fishing speed by ", NamedTextColor.GRAY).append(Component.text(getStatString(fishingStats.getStatObject(StatType.FISHING_SPEED)), NamedTextColor.BLUE));
                }
            }
            if (miningStats != null) {
                if (miningStats.getStatValue(StatType.MINING_SPEED) != 0) {
                    miningSpeed = Component.text("Mining Speed: ", NamedTextColor.GRAY).append(Component.text(getStatString(miningStats.getStatObject(StatType.MINING_SPEED)), NamedTextColor.GREEN)).append(reforgeMiningSpeed).append(gemMineSpeed);
                }
                if (miningStats.getStatValue(StatType.MINING_FORTUNE) != 0) {
                    miningFortune = Component.text("Mining Fortune: ", NamedTextColor.GRAY).append(Component.text(getStatString(miningStats.getStatObject(StatType.MINING_FORTUNE)), NamedTextColor.GREEN)).append(reforgeMiningFortune).append(gemMineFortune);
                }
                if (miningStats.getStatValue(StatType.PRISTINE) != 0) {
                    pristine = Component.text("Pristine: ", NamedTextColor.GRAY).append(Component.text(getStatString(miningStats.getStatObject(StatType.PRISTINE)), NamedTextColor.GREEN)).append(reforgePristine).append(gemPristine);
                }
                if (miningStats.getStatValue(StatType.BREAKING_POWER) != 0) {
                    breakingPower = Component.text("Breaking Power " + (int) miningStats.getStatValue(StatType.BREAKING_POWER), NamedTextColor.DARK_GRAY);
                }
            }
            if (gatheringStats != null) {
                if (gatheringStats.getStatValue(StatType.FARMING_FORTUNE) != 0) {
                    farmingFortune = Component.text("Farming Fortune: ", NamedTextColor.GRAY).append(Component.text(getStatString(gatheringStats.getStatObject(StatType.FARMING_FORTUNE)), NamedTextColor.GREEN)).append(reforgeFarmingFortune);
                }
                if (gatheringStats.getStatValue(StatType.FORAGING_FORTUNE) != 0) {
                    foragingFortune = Component.text("Foraging Fortune: ", NamedTextColor.GRAY).append(Component.text(getStatString(gatheringStats.getStatObject(StatType.FORAGING_FORTUNE)), NamedTextColor.GREEN)).append(reforgeForagingFortune);
                }
            }
            if (luckStats != null) {
                if (luckStats.getStatValue(StatType.MAGIC_FIND) != 0) {
                    magicFind = Component.text("Magic Find: ", NamedTextColor.GRAY).append(Component.text(getStatString(luckStats.getStatObject(StatType.MAGIC_FIND)), NamedTextColor.GREEN)).append(reforgeMagicFind);
                }
                if (luckStats.getStatValue(StatType.PET_LUCK) != 0) {
                    petLuck = Component.text("Pet Luck: ", NamedTextColor.GRAY).append(Component.text(getStatString(luckStats.getStatObject(StatType.PET_LUCK)), NamedTextColor.GREEN)).append(reforgePetLuck);
                }
            }
            meta.displayName(name.decoration(TextDecoration.ITALIC, false));
            if (breakingPower != null) {
                lore.add(breakingPower.decoration(TextDecoration.ITALIC, false));
                lore.add(Component.empty());
            }
            int statLines = 0;
            if (damage != null) {
                lore.add(damage.decoration(TextDecoration.ITALIC, false));
                statLines++;
            }
            if (strength != null) {
                lore.add(strength.decoration(TextDecoration.ITALIC, false));
                statLines++;
            }
            if (critChance != null) {
                lore.add(critChance.decoration(TextDecoration.ITALIC, false));
                statLines++;
            }
            if (critDamage != null) {
                lore.add(critDamage.decoration(TextDecoration.ITALIC, false));
                statLines++;
            }
            if (attackSpeed != null) {
                lore.add(attackSpeed.decoration(TextDecoration.ITALIC, false));
                statLines++;
            }
            if (abilityDamage != null) {
                lore.add(abilityDamage.decoration(TextDecoration.ITALIC, false));
                statLines++;
            }
            if (health != null) {
                lore.add(health.decoration(TextDecoration.ITALIC, false));
                statLines++;
            }
            if (defense != null) {
                lore.add(defense.decoration(TextDecoration.ITALIC, false));
                statLines++;
            }
            if (speed != null) {
                lore.add(speed.decoration(TextDecoration.ITALIC, false));
                statLines++;
            }
            if (intelligence != null) {
                lore.add(intelligence.decoration(TextDecoration.ITALIC, false));
                statLines++;
            }
            if (trueDefense != null) {
                lore.add(trueDefense.decoration(TextDecoration.ITALIC, false));
                statLines++;
            }
            if (seaCreatureChance != null) {
                lore.add(seaCreatureChance.decoration(TextDecoration.ITALIC, false));
                statLines++;
            }
            if (magicFind != null) {
                lore.add(magicFind.decoration(TextDecoration.ITALIC, false));
                statLines++;
            }
            if (petLuck != null) {
                lore.add(petLuck.decoration(TextDecoration.ITALIC, false));
                statLines++;
            }
            if (ferocity != null) {
                lore.add(ferocity.decoration(TextDecoration.ITALIC, false));
                statLines++;
            }
            if (miningSpeed != null) {
                lore.add(miningSpeed.decoration(TextDecoration.ITALIC, false));
                statLines++;
            }
            if (miningFortune != null) {
                lore.add(miningFortune.decoration(TextDecoration.ITALIC, false));
                statLines++;
            }
            if (pristine != null) {
                lore.add(pristine.decoration(TextDecoration.ITALIC, false));
                statLines++;
            }
            if (farmingFortune != null) {
                lore.add(farmingFortune.decoration(TextDecoration.ITALIC, false));
                statLines++;
            }
            if (foragingFortune != null) {
                lore.add(foragingFortune.decoration(TextDecoration.ITALIC, false));
                statLines++;
            }
            if (gemstoneSlots != null) {
                lore.add(gemstoneSlots.decoration(TextDecoration.ITALIC, false));
                statLines++;
            }
            if (statLines > 0) {
                lore.add(Component.empty());
            }
            if (enchantmentsHolder != null) {
                int enchantCount = 0;
                for (String str : enchantmentsHolder.getEnchantmentsLore()) {
                    lore.add(Component.text(str).decoration(TextDecoration.ITALIC, false));
                    enchantCount++;
                }
                if (enchantCount > 0) {
                    lore.add(Component.empty());
                }
            }
            if (abilitiesHolder != null) {
                int abilityCount = 0;
                for (Component component : abilitiesHolder.getAbilitiesLore()) {
                    lore.add(component.decoration(TextDecoration.ITALIC, false));
                    abilityCount++;
                }
                if (abilityCount > 0) {
                    lore.add(Component.empty());
                }
            }
            if (fishingTimeDecrease != null) {
                lore.add(fishingTimeDecrease.decoration(TextDecoration.ITALIC, false));
                lore.add(Component.empty());
            }
            if (itemInfo.getExtraInfo() != null) {
                int lines = 0;
                for (Component line : itemInfo.getExtraInfo().getSpecialLore()) {
                    lore.add(line.decoration(TextDecoration.ITALIC, false));
                    lines++;
                }
                if (lines > 0) {
                    lore.add(Component.empty());
                }
            }
            if (hasBonus) {
                lore.add(Component.text(getStringFromEnum(reforge) + " Bonus", NamedTextColor.BLUE).decoration(TextDecoration.ITALIC, false));
                int lines = 0;
                for (Component line : reforge.getBonusLore(itemInfo.getRarity())) {
                    lore.add(line.decoration(TextDecoration.ITALIC, false));
                    lines++;
                }
                if (lines > 0) {
                    lore.add(Component.empty());
                }
            }
            if (canBeReforged != null && itemInfo.getItemType().allowReforge()) {
                lore.add(canBeReforged.decoration(TextDecoration.ITALIC, false));
            }
            lore.add(rarity.decoration(TextDecoration.ITALIC, false));
            meta.lore(lore);
        }
    }

    public static void updateItem(ItemStack item, UUID newOwner) {
        ItemMeta meta = item.getItemMeta();
        if (item.getType() == Material.AIR || meta == null) {
            return;
        }
        ItemInfo itemInfo = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
        if (itemInfo != null) {
            if (itemInfo.getVanillaMaterial() != item.getType()) {
                item.setType(itemInfo.getVanillaMaterial());
            }
            if (!newOwner.equals(itemInfo.getOwner())) {
                itemInfo.setOwner(newOwner);
            }
            updateLore(meta);
            item.setItemMeta(meta);
        } else {
            item.setItemMeta(createMetaFromMat(meta, item.getType()));
        }
    }

    /**
     * Returns a formatted String for a stat
     *
     * @param statObject The StatObject storing the value to generate a formatted String for
     * @return The formatted String representing the stat
     */
    public static String getStatString(StatObject statObject) {
        double stat = statObject.getValue();
        if (stat == 0) {
            return "";
        }
        String statString = "";
        if (stat > 0) {
            statString += "+";
        }
        if (stat % 1 == 0) {
            statString += (int) stat;
        } else {
            statString += stat;
        }
        if (statObject.getType().isPercentage()) {
            statString += "%";
        }
        return statString;
    }

    public static String getStatString(double stat) {
        String statString = "";
        if (stat > 0) {
            statString += "+";
        }
        if (stat % 1 == 0) {
            statString += (int) stat;
        } else {
            statString += stat;
        }
        return statString;
    }

    private static Component getBracketComponent(StatObject stat, NamedTextColor color) {
        String statString = getStatString(stat);
        if (statString.equals("")) {
            return Component.text(" ");
        }
        return Component.text(" (" + getStatString(stat) + ") ", color);
    }

    private static Component getBracketComponent(double stat, NamedTextColor color) {
        String statString = getStatString(stat);
        if (statString.equals("")) {
            return Component.text(" ");
        }
        return Component.text(" (" + getStatString(stat) + ") ", color);
    }

    /**
     * Recombobulates the item, upgrading its rarity by 1
     *
     * @param item The item to recombobulate
     */
    public static void recombobulate(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        ItemInfo info = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
        if (info != null) {
            info.recomb();
            meta.getPersistentDataContainer().set(itemKey, new ItemInfoDataType(), info);
            updateLore(meta);
            item.setItemMeta(meta);
        }
    }

    /**
     * Adds a potato book to the Item held in the Player's main hand
     *
     * @param item The item to add the book to
     */
    public static void addHotPotatoBook(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        ItemInfo itemInfo = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
        if (itemInfo == null) {
            return;
        }
        int currentPotatoBooks = itemInfo.getPotatoBooks();
        itemInfo.setPotatoBooks(currentPotatoBooks + 1);
        meta.getPersistentDataContainer().set(itemKey, new ItemInfoDataType(), itemInfo);
        updateLore(meta);
        item.setItemMeta(meta);
    }

    public static void addHotPotatoBook(ItemStack item, int books) {
        ItemMeta meta = item.getItemMeta();
        ItemInfo itemInfo = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
        if (itemInfo == null) {
            return;
        }
        int currentPotatoBooks = itemInfo.getPotatoBooks();
        itemInfo.setPotatoBooks(currentPotatoBooks + books);
        meta.getPersistentDataContainer().set(itemKey, new ItemInfoDataType(), itemInfo);
        updateLore(meta);
        item.setItemMeta(meta);
    }

    public static void setHotPotatoBook(ItemStack item, int books) {
        ItemMeta meta = item.getItemMeta();
        ItemInfo itemInfo = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
        if (itemInfo == null) {
            return;
        }
        itemInfo.setPotatoBooks(books);
        meta.getPersistentDataContainer().set(itemKey, new ItemInfoDataType(), itemInfo);
        updateLore(meta);
        item.setItemMeta(meta);
    }

    /**
     * Reforges the Item with the specified Reforged and Rarity
     *
     * @param item    The Item to add the Reforge to
     * @param reforge The specific Reforge to apply
     */
    public static void reforge(ItemStack item, Reforge reforge) {
        if (reforge == null) {
            return;
        }
        ItemMeta meta = item.getItemMeta();
        ItemInfo itemInfo = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
        if (itemInfo == null) {
            return;
        }
        if (reforge.getItemTypes().contains(itemInfo.getItemType())) {
            itemInfo.setReforge(reforge);
            meta.getPersistentDataContainer().set(itemKey, new ItemInfoDataType(), itemInfo);
            updateLore(meta);
            item.setItemMeta(meta);
        }
    }

    public static void addEnchant(ItemStack item, EnchantmentType type, int level) {
        ItemMeta meta = item.getItemMeta();
        ItemInfo itemInfo = getInfo(item);
        if (itemInfo == null) {
            return;
        }
        if (!itemInfo.getItemType().allowEnchants()) {
            return;
        }
        if (!type.getItemTypes().contains(itemInfo.getItemType())) {
            return;
        }
        EnchantmentObject enchant = type.createObject(level);
        itemInfo.addEnchant(enchant);
        meta.getPersistentDataContainer().set(itemKey, new ItemInfoDataType(), itemInfo);
        updateLore(meta);
        item.setItemMeta(meta);
    }

    public static void addAbility(ItemStack item, AbilityType type) {
        ItemMeta meta = item.getItemMeta();
        ItemInfo itemInfo = meta.getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
        if (itemInfo == null) {
            return;
        }
        AbilitiesHolder abilityHolder = itemInfo.getAbilitiesHolder();
        if (abilityHolder == null) {
            abilityHolder = new AbilitiesHolder(itemInfo);
        }
        abilityHolder.addAbility(type);
        itemInfo.setAbilitiesHolder(abilityHolder);
        meta.getPersistentDataContainer().set(itemKey, new ItemInfoDataType(), itemInfo);
        updateLore(meta);
        item.setItemMeta(meta);
    }

    /**
     * Creates a new ItemStack and sets meta information based on an ItemID
     * @param itemID The ItemID to create a new item for
     * @return The ItemStack that represents the basic form of the ItemID
     */
    public static ItemStack createItem(ItemID itemID) {
        ItemStack item = new ItemStack(itemID.getMaterial(), 1);
        ItemMeta meta = item.getItemMeta();
        item.setItemMeta(createMetaFromID(meta, itemID));
        return item;
    }

    public static ItemStack createItemFromMat(Material material) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        item.setItemMeta(createMetaFromMat(meta, material));
        return item;
    }

    public static ItemMeta createMetaFromID(ItemMeta meta, ItemID itemID) {
        meta.getPersistentDataContainer().set(itemKey, new ItemInfoDataType(), new ItemInfo(itemID));
        if (meta instanceof LeatherArmorMeta leatherMeta && itemID.getColor() != null) {
            leatherMeta.setColor(itemID.getColor());
        }
        if (meta instanceof SkullMeta skullMeta && itemID.getTexture() != null) {
            PlayerProfile profile = Bukkit.getServer().createProfile(skullUUID);
            profile.setProperty(new ProfileProperty("textures", itemID.getTexture()));
            skullMeta.setPlayerProfile(profile);
        }
        updateLore(meta);
        return meta;
    }

    public static ItemMeta createMetaFromMat(ItemMeta meta, Material material) {
        try {
            ItemID itemID = ItemID.valueOf(material.toString());
            return createMetaFromID(meta, itemID);
        } catch (IllegalArgumentException exception) {
            meta.getPersistentDataContainer().set(itemKey, new ItemInfoDataType(), new ItemInfo(material));
            updateLore(meta);
            return meta;
        }
    }

    /**
     * Returns whether the item is a helmet, chestplate, leggings, or boots
     *
     * @param itemInfo The ItemInfo to check
     * @return true if the item can be worn as armor, false otherwise
     */
    public static boolean isArmor(ItemInfo itemInfo) {
        ItemType type = itemInfo.getItemType();
        return type == ItemType.HELMET || type == ItemType.CHESTPLATE || type == ItemType.LEGGINGS || type == ItemType.BOOTS;
    }

    /**
     * Returns whether the item is a weapon
     * @param itemInfo The ItemInfo to check
     * @return true if the item is considered a weapon
     */
    public static boolean isWeapon(ItemInfo itemInfo) {
        ItemType type = itemInfo.getItemType();
        return type == ItemType.SWORD || type == ItemType.BOW || type == ItemType.FISHING_WEAPON || type == ItemType.LONGSWORD;
    }

    @Nullable
    public static ItemInfo getMainHandInfo(Player player) {
        if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
            return null;
        }
        return player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
    }

    @Nullable
    public static ItemInfo getOffHandInfo(Player player) {
        if (player.getInventory().getItemInOffHand().getType() == Material.AIR) {
            return null;
        }
        return player.getInventory().getItemInOffHand().getItemMeta().getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
    }

    @Nullable
    public static ItemInfo getInfo(ItemStack item) {
        if (item == null || item.getType() == Material.AIR) {
            return null;
        }
        return item.getItemMeta().getPersistentDataContainer().get(itemKey, new ItemInfoDataType());
    }

    /**
     * Gets a formatted String from an Enum value
     * @param value The Enum value to get the String from
     * @return The formatted String
     */
    public static String getStringFromEnum(Enum value) {
        String[] split = value.toString().split("_");
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            name.append(split[i].charAt(0)).append(split[i].substring(1).toLowerCase());
            if (i < split.length - 1) {
                name.append(" ");
            }
        }
        return name.toString();
    }

    /**
     * Method to format large numbers with suffixes
     * <p>
     *     Taken from https://stackoverflow.com/a/9769590
     * </p>
     * @param number The number to format
     * @return The formatted String
     */
    public static String numberFormatted(double number) {
        if (number < 1000) {
            return "" + number;
        }
        int exp = (int) (Math.log(number) / Math.log(1000));
        return String.format("%.1f%c",
                number / Math.pow(1000, exp),
                "KMBqQ".charAt(exp-1));
    }

}