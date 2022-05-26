package me.notpseudo.revolutionsmp.enchantments;

import me.notpseudo.revolutionsmp.listeners.MobListeners;
import me.notpseudo.revolutionsmp.mobstats.MobInfo;
import me.notpseudo.revolutionsmp.mobstats.MobInfoDataType;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class LethalityEnchantmentObject extends EnchantmentObject implements ActionEnchantment {

    private ConcurrentHashMap<UUID, Integer> hits;
    private static final NamespacedKey mobKey = MobListeners.getMobKey();

    public LethalityEnchantmentObject() {
        super(EnchantmentType.LETHALITY);
        hits = new ConcurrentHashMap<>();
    }

    public LethalityEnchantmentObject(int level) {
        super(EnchantmentType.LETHALITY, level);
        hits = new ConcurrentHashMap<>();
    }

    @Override
    public void action(LivingEntity damager, LivingEntity target, int damage) {
        if(hits.get(target.getUniqueId()) == 4) {
            return;
        }
        MobInfo targetStats = target.getPersistentDataContainer().get(mobKey, new MobInfoDataType());
        if(targetStats == null) {
            return;
        }
        double percentDecrease;
        int level = super.getLevel();
        switch (level) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                percentDecrease = level * 1.2;
            case 6:
            default:
                percentDecrease = level * 1.5;
        }

    }

}