package me.notpseudo.revolutionsmp.mining;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import me.notpseudo.revolutionsmp.drops.ItemDropObject;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.items.ItemID;
import me.notpseudo.revolutionsmp.items.Rarity;
import me.notpseudo.revolutionsmp.itemstats.ItemInfo;
import me.notpseudo.revolutionsmp.itemstats.ItemInfoDataType;
import me.notpseudo.revolutionsmp.listeners.HarvestingListeners;
import me.notpseudo.revolutionsmp.specialiteminfo.GemstoneObject;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class GemstoneUtils {

    public static double rubyValue(Rarity quality, Rarity rarity) {
        return switch (quality) {
            case COMMON -> switch (rarity) {
                case COMMON -> 1;
                case UNCOMMON -> 2;
                case RARE -> 3;
                case EPIC -> 4;
                case LEGENDARY -> 5;
                default -> 7;
            };
            case UNCOMMON -> switch (rarity) {
                case COMMON -> 3;
                case UNCOMMON -> 4;
                case RARE -> 5;
                case EPIC -> 6;
                case LEGENDARY -> 8;
                default -> 10;
            };
            case RARE -> switch (rarity) {
                case COMMON -> 4;
                case UNCOMMON -> 5;
                case RARE -> 6;
                case EPIC -> 8;
                case LEGENDARY -> 10;
                default -> 14;
            };
            case EPIC -> switch (rarity) {
                case COMMON -> 5;
                case UNCOMMON -> 7;
                case RARE -> 10;
                case EPIC -> 14;
                case LEGENDARY -> 18;
                default -> 22;
            };
            default -> switch (rarity) {
                case COMMON -> 6;
                case UNCOMMON -> 9;
                case RARE -> 13;
                case EPIC -> 18;
                case LEGENDARY -> 24;
                default -> 30;
            };
        };
    }

    public static double amberValue(Rarity quality, Rarity rarity) {
        return switch (quality) {
            case COMMON -> switch (rarity) {
                case COMMON -> 4;
                case UNCOMMON -> 8;
                case RARE -> 12;
                case EPIC -> 16;
                case LEGENDARY -> 20;
                case MYTHIC -> 24;
                default -> 28;
            };
            case UNCOMMON -> switch (rarity) {
                case COMMON -> 6;
                case UNCOMMON -> 10;
                case RARE -> 14;
                case EPIC -> 18;
                case LEGENDARY -> 24;
                case MYTHIC -> 30;
                default -> 36;
            };
            case RARE -> switch (rarity) {
                case COMMON -> 10;
                case UNCOMMON -> 14;
                case RARE -> 20;
                case EPIC -> 28;
                case LEGENDARY -> 36;
                case MYTHIC -> 45;
                default -> 54;
            };
            case EPIC -> switch (rarity) {
                case COMMON -> 14;
                case UNCOMMON -> 20;
                case RARE -> 30;
                case EPIC -> 44;
                case LEGENDARY -> 58;
                case MYTHIC -> 75;
                default -> 92;
            };
            default -> switch (rarity) {
                case COMMON -> 20;
                case UNCOMMON -> 28;
                case RARE -> 40;
                case EPIC -> 60;
                case LEGENDARY -> 80;
                case MYTHIC -> 100;
                default -> 120;
            };
        };
    }

    public static double topazValue(Rarity quality, Rarity rarity) {
        return switch (quality) {
            case COMMON -> switch (rarity) {
                case COMMON, UNCOMMON, RARE, EPIC, LEGENDARY, MYTHIC -> 0.4;
                default -> 0.5;
            };
            case UNCOMMON -> switch (rarity) {
                case COMMON, UNCOMMON, RARE, EPIC, LEGENDARY, MYTHIC -> 0.8;
                default -> 0.9;
            };
            case RARE -> switch (rarity) {
                case COMMON, UNCOMMON, RARE, EPIC, LEGENDARY, MYTHIC -> 1.2;
                default -> 1.3;
            };
            case EPIC -> switch (rarity) {
                case COMMON, UNCOMMON, RARE, EPIC, LEGENDARY, MYTHIC -> 1.6;
                default -> 1.8;
            };
            default -> switch (rarity) {
                case COMMON, UNCOMMON, RARE, EPIC, LEGENDARY, MYTHIC -> 2;
                default -> 2.2;
            };
        };
    }

    public static double pearlValue(Rarity quality, Rarity rarity) {
        return switch (quality) {
            case COMMON -> switch (rarity) {
                case COMMON, UNCOMMON, RARE, EPIC, LEGENDARY -> 0.2;
                default -> 0.3;
            };
            case UNCOMMON -> switch (rarity) {
                case COMMON, UNCOMMON, RARE, EPIC, LEGENDARY -> 0.6;
                default -> 0.7;
            };
            case RARE -> switch (rarity) {
                case COMMON, UNCOMMON, RARE, EPIC, LEGENDARY -> 1;
                default -> 1.1;
            };
            case EPIC -> switch (rarity) {
                case COMMON, UNCOMMON, RARE, EPIC, LEGENDARY, MYTHIC -> 1.4;
                default -> 1.5;
            };
            default -> switch (rarity) {
                case COMMON, UNCOMMON, RARE, EPIC, LEGENDARY, MYTHIC -> 1.8;
                default -> 2;
            };
        };
    }

    public static double jadeValue(Rarity quality, Rarity rarity) {
        return switch (quality) {
            case COMMON -> switch (rarity) {
                case COMMON -> 2;
                case UNCOMMON -> 4;
                case RARE -> 6;
                case EPIC -> 8;
                case LEGENDARY -> 10;
                case MYTHIC -> 12;
                default -> 14;
            };
            case UNCOMMON -> switch (rarity) {
                case COMMON -> 3;
                case UNCOMMON -> 5;
                case RARE -> 7;
                case EPIC -> 10;
                case LEGENDARY -> 14;
                case MYTHIC -> 18;
                default -> 22;
            };
            case RARE -> switch (rarity) {
                case COMMON -> 5;
                case UNCOMMON -> 7;
                case RARE -> 10;
                case EPIC -> 15;
                case LEGENDARY -> 20;
                case MYTHIC -> 25;
                default -> 30;
            };
            case EPIC -> switch (rarity) {
                case COMMON -> 7;
                case UNCOMMON -> 10;
                case RARE -> 15;
                case EPIC -> 20;
                case LEGENDARY -> 27;
                case MYTHIC -> 35;
                default -> 44;
            };
            default -> switch (rarity) {
                case COMMON -> 10;
                case UNCOMMON -> 14;
                case RARE -> 20;
                case EPIC -> 30;
                case LEGENDARY -> 40;
                case MYTHIC -> 50;
                default -> 60;
            };
        };
    }

    public static double sapphireValue(Rarity quality, Rarity rarity) {
        return switch (quality) {
            case COMMON -> switch (rarity) {
                case COMMON -> 2;
                case UNCOMMON -> 3;
                case RARE -> 4;
                case EPIC -> 5;
                case LEGENDARY -> 6;
                default -> 7;
            };
            case UNCOMMON -> switch (rarity) {
                case COMMON, UNCOMMON -> 5;
                case RARE -> 6;
                case EPIC -> 7;
                case LEGENDARY -> 8;
                default -> 10;
            };
            case RARE -> switch (rarity) {
                case COMMON -> 7;
                case UNCOMMON -> 8;
                case RARE -> 9;
                case EPIC -> 10;
                case LEGENDARY -> 11;
                default -> 12;
            };
            case EPIC -> switch (rarity) {
                case COMMON -> 10;
                case UNCOMMON -> 11;
                case RARE -> 12;
                case EPIC -> 14;
                case LEGENDARY -> 17;
                default -> 20;
            };
            default -> switch (rarity) {
                case COMMON -> 12;
                case UNCOMMON -> 14;
                case RARE -> 17;
                case EPIC -> 20;
                case LEGENDARY -> 24;
                default -> 30;
            };
        };
    }

    public static double amethystValue(Rarity quality, Rarity rarity) {
        return switch (quality) {
            case COMMON -> switch (rarity) {
                case COMMON -> 1;
                case UNCOMMON -> 2;
                case RARE -> 3;
                case EPIC -> 4;
                case LEGENDARY -> 5;
                default -> 7;
            };
            case UNCOMMON -> switch (rarity) {
                case COMMON -> 3;
                case UNCOMMON -> 4;
                case RARE -> 5;
                case EPIC -> 6;
                case LEGENDARY -> 8;
                default -> 10;
            };
            case RARE -> switch (rarity) {
                case COMMON -> 4;
                case UNCOMMON -> 5;
                case RARE -> 6;
                case EPIC -> 8;
                case LEGENDARY -> 10;
                default -> 14;
            };
            case EPIC -> switch (rarity) {
                case COMMON -> 5;
                case UNCOMMON -> 7;
                case RARE -> 10;
                case EPIC -> 14;
                case LEGENDARY -> 18;
                default -> 22;
            };
            default -> switch (rarity) {
                case COMMON -> 6;
                case UNCOMMON -> 9;
                case RARE -> 13;
                case EPIC -> 18;
                case LEGENDARY -> 24;
                default -> 30;
            };
        };
    }

    public static double jasperValue(Rarity quality, Rarity rarity) {
        return switch (quality) {
            case COMMON -> switch (rarity) {
                case COMMON, UNCOMMON, RARE -> 1;
                case EPIC -> 2;
                case LEGENDARY -> 3;
                default -> 4;
            };
            case UNCOMMON -> switch (rarity) {
                case COMMON, UNCOMMON -> 2;
                case RARE -> 3;
                case EPIC, LEGENDARY -> 4;
                default -> 5;
            };
            case RARE -> switch (rarity) {
                case COMMON, UNCOMMON -> 3;
                case RARE -> 4;
                case EPIC -> 5;
                case LEGENDARY -> 6;
                default -> 7;
            };
            case EPIC -> switch (rarity) {
                case COMMON -> 5;
                case UNCOMMON -> 6;
                case RARE -> 7;
                case EPIC -> 8;
                case LEGENDARY -> 10;
                default -> 12;
            };
            default -> switch (rarity) {
                case COMMON -> 6;
                case UNCOMMON -> 7;
                case RARE -> 9;
                case EPIC -> 1;
                case LEGENDARY -> 13;
                default -> 16;
            };
        };
    }

    public static double opalValue(Rarity quality, Rarity rarity) {
        return switch (quality) {
            case COMMON -> switch (rarity) {
                case COMMON, UNCOMMON, RARE -> 1;
                case EPIC, LEGENDARY -> 2;
                default -> 3;
            };
            case UNCOMMON -> switch (rarity) {
                case COMMON, UNCOMMON, RARE -> 2;
                case EPIC, LEGENDARY -> 3;
                default -> 4;
            };
            case RARE -> switch (rarity) {
                case COMMON, UNCOMMON, RARE -> 3;
                case EPIC, LEGENDARY -> 4;
                default -> 5;
            };
            case EPIC -> switch (rarity) {
                case COMMON, UNCOMMON -> 4;
                case RARE -> 5;
                case EPIC -> 6;
                case LEGENDARY -> 8;
                default -> 9;
            };
            default -> switch (rarity) {
                case COMMON -> 5;
                case UNCOMMON -> 6;
                case RARE -> 7;
                case EPIC -> 9;
                case LEGENDARY -> 1;
                default -> 13;
            };
        };
    }

    public static ItemStack createGemstone(GemstoneType type, Rarity rarity) {
        return createGemstone(type, rarity, 1);
    }

    public static ItemStack createGemstone(GemstoneType type, Rarity rarity, int count) {
        ItemStack item = ItemEditor.createItem(ItemID.GEMSTONE);
        ItemMeta meta = item.getItemMeta();
        ItemInfo info = ItemEditor.getInfo(item);
        GemstoneObject gemInfo = (GemstoneObject) info.getExtraInfo();
        gemInfo.setGem(type);
        gemInfo.setQuality(rarity);
        info.setRarity(rarity);
        PlayerProfile profile = Bukkit.getServer().createProfile(ItemEditor.getSkullUUID());
        profile.setProperty(new ProfileProperty("textures", gemInfo.getTexture()));
        ((SkullMeta) meta).setPlayerProfile(profile);
        meta.getPersistentDataContainer().set(ItemEditor.getItemKey(), new ItemInfoDataType(), info);
        ItemEditor.updateLore(meta);
        item.setItemMeta(meta);
        item.setAmount(count);
        return item;
    }

    public static boolean isGemstone(ItemStack item) {
        if (item == null || item.getType() == Material.AIR) {
            return false;
        }
        ItemInfo info = ItemEditor.getInfo(item);
        if (info == null) {
            return false;
        }
        if (info.getExtraInfo() == null) {
            return false;
        }
        return info.getExtraInfo() instanceof GemstoneObject;
    }

    public static boolean isGemstone(ItemDropObject itemDrop) {
        return isGemstone(itemDrop.getItem());
    }

    public static GemstoneType getGemTypeFromItem(ItemStack item) {
        if (item == null ||item.getType() == Material.AIR) {
            return null;
        }
        ItemInfo info = ItemEditor.getInfo(item);
        if (info == null) {
            return null;
        }
        if (info.getExtraInfo() == null) {
            return null;
        }
        if (!(info.getExtraInfo() instanceof GemstoneObject gem)) {
            return null;
        }
        return gem.getGem();
    }

    public static GemstoneObject getGemObject(ItemStack item) {
        if (item == null ||item.getType() == Material.AIR) {
            return null;
        }
        ItemInfo info = ItemEditor.getInfo(item);
        if (info == null) {
            return null;
        }
        if (info.getExtraInfo() == null) {
            return null;
        }
        if (!(info.getExtraInfo() instanceof GemstoneObject gem)) {
            return null;
        }
        return gem;
    }

    public static String getRating(ItemStack item) {
        if (item == null ||item.getType() == Material.AIR) {
            return null;
        }
        ItemInfo info = ItemEditor.getInfo(item);
        if (info == null) {
            return null;
        }
        if (info.getExtraInfo() == null) {
            return null;
        }
        if (!(info.getExtraInfo() instanceof GemstoneObject gem)) {
            return null;
        }
        return gem.getRating();
    }

    public static ItemDropObject handlePristine(ItemDropObject drop, double fortune, double pristine) {
        if (isGemstone(drop.getItem())) {
            int originalGemCount = drop.getItem().getAmount(), pristineAmount = 0;
            for (int i = 0; i < drop.getItem().getAmount(); i++) {
                if (Math.random() * 100 < pristine) {
                    pristineAmount++;
                    originalGemCount--;
                }
            }
            drop.getItem().setAmount(originalGemCount * (1 + HarvestingListeners.getAddedTimes(fortune)));
            if (pristineAmount > 0) {
                return new ItemDropObject(createGemstone(getGemTypeFromItem(drop.getItem()), Rarity.UNCOMMON, pristineAmount * (1 + HarvestingListeners.getAddedTimes(fortune))));
            }
        }
        return null;
    }

}