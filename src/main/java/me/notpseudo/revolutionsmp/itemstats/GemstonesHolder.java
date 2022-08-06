package me.notpseudo.revolutionsmp.itemstats;

import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.items.Rarity;
import me.notpseudo.revolutionsmp.mining.GemstoneSlotType;
import me.notpseudo.revolutionsmp.specialiteminfo.GemstoneObject;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GemstonesHolder implements Serializable {

    private ItemInfo holder;
    private GemstoneObject[] gems;
    private GemstoneSlotType[] allowedSlots;
    private int maxSlots;
    private ArrayList<GemstoneObject> oldGems;

    public GemstonesHolder(ItemInfo holder, GemstoneSlotType[] allowed) {
        this.holder = holder;
        allowedSlots = allowed;
        gems = new GemstoneObject[allowedSlots.length];
        this.maxSlots = allowedSlots.length;
        oldGems = new ArrayList<>();
    }

    public GemstoneObject[] getGems() {
        return gems;
    }

    public void forceAdd(GemstoneObject[] oldGemList) {
        oldGems.addAll(List.of(oldGemList));
    }

    public boolean socket(ItemStack item) {
        if (item.getType() == Material.AIR) {
            return false;
        }
        ItemInfo info = item.getItemMeta().getPersistentDataContainer().get(ItemEditor.getItemKey(), new ItemInfoDataType());
        if (info == null) {
            return false;
        }
        if (info.getExtraInfo() == null) {
            return false;
        }
        if (!(info.getExtraInfo() instanceof GemstoneObject gemstone)) {
            return false;
        }
        return add(gemstone);
    }

    public boolean unsocket(int slot) {
        if (slot > gems.length) {
            return false;
        }
        gems[slot] = null;
        return true;
    }

    private boolean add(GemstoneObject newGem) {
        for (int i = 0; i < allowedSlots.length; i++) {
            if (gems[i] == null && allowedSlots[i].allows(newGem.getGem())) {
                gems[i] = newGem;
                return true;
            }
        }
        return false;
    }

    public Component getGemLineLore() {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < allowedSlots.length; i++) {
            ChatColor gemQualityColor = ChatColor.GRAY, gemTypeColor = ChatColor.GRAY;
            if (gems[i] != null) {
                gemQualityColor = gems[i].getGem().getColor();
                gemTypeColor = gems[i].getGem().getColor();
            }
            line.append(gemQualityColor + "[" +
                        gemTypeColor + allowedSlots[i].getSymbol() +
                        gemQualityColor + "] ");
        }
        return Component.text(line.toString());
    }

    public WeaponStats getGemWeapon(Rarity rarity) {
        WeaponStats gemWeapon = new WeaponStats(0, 0, 0, 0, 0, 0);
        for (GemstoneObject gem : gems) {
            if (gem != null) {
                gemWeapon.combine(gem.getBonusWeapon(rarity));
            }
        }
        return gemWeapon;
    }

    public ArmorStats getGemArmor(Rarity rarity) {
        ArmorStats gemArmor = new ArmorStats(0, 0, 0);
        for (GemstoneObject gem : gems) {
            if (gem != null) {
                gemArmor.combine(gem.getBonusArmor(rarity));
            }
        }
        return gemArmor;
    }

    public AbilityStats getGemAbility(Rarity rarity) {
        AbilityStats gemAbility = new AbilityStats(0, 0);
        for (GemstoneObject gem : gems) {
            if (gem != null) {
                gemAbility.combine(gem.getBonusAbility(rarity));
            }
        }
        return gemAbility;
    }

    public MiningStats getGemMining(Rarity rarity) {
        MiningStats gemMining = new MiningStats(0, 0, 0);
        for (GemstoneObject gem : gems) {
            if (gem != null) {
                gemMining.combine(gem.getBonusMining(rarity));
            }
        }
        return gemMining;
    }

    public GatheringStats getGemGathering(Rarity rarity) {
        GatheringStats gemGathering = new GatheringStats(0, 0);
        for (GemstoneObject gem : gems) {
            if (gem != null) {
                gemGathering.combine(gem.getBonusGathering(rarity));
            }
        }
        return gemGathering;
    }

    public FishingStats getGemFishing(Rarity rarity) {
        FishingStats gemFishing = new FishingStats(0, 0);
        for (GemstoneObject gem : gems) {
            if (gem != null) {
                gemFishing.combine(gem.getBonusFishing(rarity));
            }
        }
        return gemFishing;
    }

    public LuckStats getGemLLuck(Rarity rarity) {
        LuckStats gemLuck = new LuckStats(0, 0);
        for (GemstoneObject gem : gems) {
            if (gem != null) {
                gemLuck.combine(gem.getBonusLuck(rarity));
            }
        }
        return gemLuck;
    }

}