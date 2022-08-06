package me.notpseudo.revolutionsmp.specialiteminfo;

import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.items.Rarity;
import me.notpseudo.revolutionsmp.itemstats.*;
import me.notpseudo.revolutionsmp.mining.GemstoneType;
import me.notpseudo.revolutionsmp.mining.GemstoneUtils;
import org.jetbrains.annotations.NotNull;

public class GemstoneObject extends SpecialItemInfo {

    private Rarity quality;
    private GemstoneType gem;

    public GemstoneObject(ItemInfo holder) {
        super(holder);
        quality = Rarity.COMMON;
        gem = GemstoneType.RUBY;
    }

    public Rarity getQuality() {
        return quality;
    }

    public GemstoneType getGem() {
        return gem;
    }

    public void setQuality(Rarity quality) {
        this.quality = quality;
        reorganize();
    }

    public void setGem(GemstoneType gem) {
        this.gem = gem;
        reorganize();
    }

    public String getRating() {
        return switch (quality) {
            case COMMON -> "Dull";
            case UNCOMMON -> "Polished";
            case RARE -> "Refined";
            case EPIC -> "Shiny";
            case LEGENDARY -> "Perfect";
            default -> ItemEditor.getStringFromEnum(quality);
        };
    }

    public String getTexture() {
        return switch (gem) {
            default -> switch (quality) {
                case COMMON ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDE1OWIwMzI0M2JlMThhMTRmM2VhZTc2M2M0NTY1Yzc4ZjFmMzM5YTg3NDJkMjZmZGU1NDFiZTU5YjdkZTA3In19fQ==";
                case UNCOMMON ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDZkODEwNjhjYmRmNGEzNjQyMzFhMjY0NTNkNmNkNjYwYTAwOTVmOWNkODc5NTMwN2M1YmU2Njc0Mjc3MTJlIn19fQ==";
                case RARE ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTY3Mjk1OTAyOGYyNzRiMzc5ZDQzMGYwNjhmMGYxNWE0Zjc5M2VhYzEyYWZiOTRhZTBiNGU1MGNmODk1ZGYwZiJ9fX0=";
                case EPIC ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTI2YTI0OGZiYmMwNmNmMDZlMmM5MjBlY2ExY2FjOGEyYzk2MTY0ZDMyNjA0OTRiZWQxNDJkNTUzMDI2Y2M2In19fQ==";
                default ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzliNmUwNDdkM2IyYmNhODVlOGNjNDllNTQ4MGY5Nzc0ZDhhMGVhZmU2ZGZhOTU1OTUzMDU5MDI4MzcxNTE0MiJ9fX0=";
            };
            case AMBER -> switch (quality) {
                case COMMON ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGExOTQzNmY2MTUxYTdiNjZkNjVlZDc2MjRhZGQ0MzI1Y2ZiYmMyZWVlODE1ZmNmNzZmNGMyOWRkZjA4Zjc1YiJ9fX0=";
                case UNCOMMON ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTczYmNmYzM5ZWI4NWRmMTg0ODUzNTk4NTIxNDA2MGExYmQxYjNiYjQ3ZGVmZTQyMDE0NzZlZGMzMTY3MTc0NCJ9fX0=";
                case RARE ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGIxY2NlMjJkZTE5ZWQ2NzI3YWJjNWU2YzJkNTc4NjRjODcxYTQ0Yzk1NmJiZTJlYjM5NjAyNjliNjg2YjhiMyJ9fX0=";
                case EPIC ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWRjZTYyZjcwYWMwNDZiODgxMTEzYzZjZjg2Mjk4NzcyNzc3NGUyNjU4ODU1MDFjOWEyNDViMTgwZGIwOGMwZCJ9fX0=";
                default ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzdhZTIzNmNkZWMzZjJhNmY1MWVhZTE1ZTJjOGY2MjI4YjM0ZjEzN2RhMTU2OWZlYzllODAzZjljZDgxNzU5ZCJ9fX0=";
            };
            case AMETHYST -> switch (quality) {
                case COMMON ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTQ5M2M2ZjU0MGM3MDAxZmVkOTdiMDdmNmI0Yzg5MTI4ZTNhN2MzNzU2M2FhMjIzZjBhY2NhMzE0ZjE3NTUxNSJ9fX0=";
                case UNCOMMON ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzFkYjU5MjYwODk1NTc4ZDM3ZTU5NTA1ODgwNjAyZGU5NDBiMDg4ZTVmZmY4ZGEzZTY1MjAxZDczOWM4NmU4NCJ9fX0=";
                case RARE ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2ExZWU1ZmZjZTA0ZWI3ZGE1OTJkNDI0MTRmZjM1ZTFiZjM4MTk0ZDZiODJlMzEwZGJjNjI2MWI0N2ZiOWM5MSJ9fX0=";
                case EPIC ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDM2MjM1MjFjODExMWFkMjllOWRjZjdhY2M1NjA4NWE5YWIwN2RhNzMyZDE1MTg5NzZhZWU2MWQwYjNlM2JkNiJ9fX0=";
                default ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDg4NmUwZjQxMTg1YjE4YTNhZmQ4OTQ4OGQyZWU0Y2FhMDczNTAwOTI0N2NjY2YwMzljZWQ2YWVkNzUyZmYxYSJ9fX0=";
            };
            case JADE -> switch (quality) {
                case COMMON ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2I0YzJhZmQ1NDRkMGE2MTM5ZTZhZThlZjhmMGJmYzA5YTlmZDgzN2QwY2FkNGY1Y2QwZmU3ZjYwN2I3ZDFhMCJ9fX0=";
                case UNCOMMON ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODIyODJjNmJiODM0M2UwZjBkNjFlZTA3NDdkYWRhNzUzNDRmMzMyZTlmZjBhY2FhM2FkY2RmMDkzMjFkM2RkIn19fQ==";
                case RARE ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjI4ZjFjMGM1MDkyZTEyZDMzNzcwZGY0NWM1ODQ1YTk2MTA4ODYwMzliMzRhYmU5M2ExNmM1ZTk0MmRmYzhlNCJ9fX0=";
                case EPIC ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjg5Zjc1ZTBiMDAzNzhhNTgzZGJiYTcyOGRjZGM2ZTkzNDZmMzFkZDYwMWQ0NDhmM2Q2MDYxNWM3NDY1Y2MzZSJ9fX0=";
                default ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2ZjZWQ3OTc3MzgyYmY3MWQ0ZWUxN2ZmNWI5MTllMGViNzk3MjA4M2M0Y2NjZmExNzVjODc1M2FlNDBiYTAwNiJ9fX0=";
            };
            case OPAL -> switch (quality) {
                case COMMON ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTE0ZDNjNTdmODA4MjRiMzgzOWI4YjIyMGYyMTU4YmNhNTA1ZDQ5N2ZkMWM5ZTNmMjlmNDIyYjFlNjIwNmE0NSJ9fX0=";
                case UNCOMMON ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWFkYzNiY2RkN2M3MDFiNjNmOGI4YjRhOTZlNDI5MzE2YTA4Mzg4NjY5ZDlhOThjMWE5ODc5MTcyOWI5NjFkZiJ9fX0=";
                case RARE ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWQ3OThlMjBhNDdkMjUxYTllMzNkNDAzMzI5NzNjNzE4OWFjMTU1MDc2MGJhMjVjNGI5NTZjOTE1OTM2NDU2OCJ9fX0=";
                case EPIC ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWQxNWVkNzBlNzIwMDQwYWQ3MzExZTY5MzU5ZGZkZjVlMTE0ZWFkZDJhNGMxZjk3MWE5NTAxMzQxYTQ1MjY0YiJ9fX0=";
                default ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2U1MDlmMDZiOGRjMzg0MzU4ZmYyNDcyYWI2MmNlNmZkYzJmNjQ2ZTMzOGVmZGMzYzlmYjA1ZGRjNDMxZjY0In19fQ==";
            };
            case SAPPHIRE -> switch (quality) {
                case COMMON ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2ZjZWJlNTRkYmMzNDVlYTdlMjIyMDZmNzAzZTZiMzNiZWZiZTk1YjZhOTE4YmQxNzU0Yjc2MTg4YmM2NWJiNSJ9fX0=";
                case UNCOMMON ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGEwYWY5OWU4ZDg3MDMxOTRhODQ3YTU1MjY4Y2Y1ZWY0YWM0ZWIzYjI0YzBlZDg2NTUxMzM5ZDEwYjY0NjUyOSJ9fX0=";
                case RARE ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzYxNjFkYWEzNTg5ZWM5YzgxODc0NTlhYzM2ZmQ0ZGQyNjQ2YzA0MDY3OGQzYmZhY2I3MmEyMjEwYzZjODAxYyJ9fX0=";
                case EPIC ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTU3Y2ZhOWM3NWJhNTg0NjQ1ZWUyYWY2ZDk4NjdkNzY3ZGRlYTQ2NjdjZGZjNzJkYzEwNjFkZDE5NzVjYTdkMCJ9fX0=";
                default ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGU5M2ViYWNiNjBiNzE3OTMzNTVmZGUwZDRiYmE0M2E5YzVlYzA5YzNmMzg4OTdjNDhjMWY4NTc1MjNhMGEyOSJ9fX0=";
            };
            case TOPAZ -> switch (quality) {
                case COMMON ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2ZkOTYwNzIyZWMyOWM2NjcxNmFlNWNhOTdiOWI2YjI2Mjg5ODRlMWQ2ZjlkMjU5MmNkMDg5OTE0MjA2YTFiIn19fQ==";
                case UNCOMMON ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjYzOTI3NzNkMTE0YmUzMGFlYjNjMDljOTBjYmU2OTFmZmVhY2ViMzk5YjUzMGZlNmZiNTNkZGMwY2VkMzcxNCJ9fX0=";
                case RARE ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTJjYjZlNTFjNDYxZTczNTk1MjZiZWE1ZTA2MjA5Y2RkZGU3YzY0NjlhODE5ZjM0MDVjZjBhMDM4YzE1OTUwMiJ9fX0=";
                case EPIC ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDEwOTY0ZjNjNDc5YWQ3ZDlhZmFmNjhhNDJjYWI3YzEwN2QyZDg4NGY1NzVjYWUyZjA3MGVjNmY5MzViM2JlIn19fQ==";
                default ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2RhNmVjZGNiYzNmZTM1NWNhMDYxMTE5MmEzZmJkMzVkZDU2MzVkNWZjZGYzZmJjNzllZDJiYzFmNGEwMTdmZSJ9fX0=";
            };
            case JASPER -> switch (quality) {
                case COMMON ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjNkMDY0ZWMxNTAxNzJkMDU4NDRjMTFhMTg2MTljMTQyMWJiZmIyZGRkMWRiYjg3Y2RjMTBlMjIyNTJiNzczYiJ9fX0=";
                case UNCOMMON ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTczNTExZTUwNGMzMTZiMTM5ZWRiMzVmZWJlNzNlZjU5MWMwZjQ1NWU4Y2FmOWVlMzUzYmMxMmI2YzE0YTkyMiJ9fX0=";
                case RARE ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWFjMTVmNmZjZjJjZTk2M2VmNGNhNzFmMWE4Njg1YWRiOTdlYjc2OWUxZDExMTk0Y2JiZDJlOTY0YTg4OTc4YyJ9fX0=";
                case EPIC ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmY5OTNkM2E0M2Q0MDU5N2I0NzQ0ODU5NzYxNjBkMGNmNTJhYzY0ZDE1NzMwN2QzYjFjOTQxZGIyMjRkMGFjNiJ9fX0=";
                default ->
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjYzZjk5MWI4ZTAzOGU0NmI4ZWQ3NjMyZjQ0Y2EyZTMwYzE1ZjQyOTc3MDcwYThjOGQ4NzI4ZTNmYzA0ZmM3YyJ9fX0=";
            };
        };
    }

    private void reorganize() {
        if (super.getHolder() == null) {
            return;
        }
        super.getHolder().setName(getRating() + " " + ItemEditor.getStringFromEnum(gem));
        super.getHolder().setTexture(getTexture());
    }

    @NotNull
    public WeaponStats getBonusWeapon(Rarity itemRarity) {
        if (gem == GemstoneType.JASPER) {
            return new WeaponStats(0, GemstoneUtils.jasperValue(quality, itemRarity), 0, 0, 0, 0);
        }
        return new WeaponStats(0, 0, 0, 0, 0, 0);
    }

    @NotNull
    public ArmorStats getBonusArmor(Rarity itemRarity) {
        if (gem == GemstoneType.RUBY) {
            return new ArmorStats(GemstoneUtils.rubyValue(quality, itemRarity), 0, 0);
        }
        if (gem == GemstoneType.AMETHYST) {
            return new ArmorStats(0, GemstoneUtils.amethystValue(quality, itemRarity), 0);
        }
        return new ArmorStats(0, 0, 0);
    }

    @NotNull
    public AbilityStats getBonusAbility(Rarity itemRarity) {
        if (gem == GemstoneType.SAPPHIRE) {
            return new AbilityStats(0, GemstoneUtils.sapphireValue(quality, itemRarity));
        }
        return new AbilityStats(0, 0);
    }

    @NotNull
    public FishingStats getBonusFishing(Rarity itemRarity) {
        return new FishingStats(0, 0);
    }

    @NotNull
    public MiningStats getBonusMining(Rarity itemRarity) {
        if (gem == GemstoneType.AMBER) {
            return new MiningStats(GemstoneUtils.amberValue(quality, itemRarity), 0, 0);
        }
        if (gem == GemstoneType.JADE) {
            return new MiningStats(0, GemstoneUtils.jadeValue(quality, itemRarity), 0);
        }
        if (gem == GemstoneType.TOPAZ) {
            return new MiningStats(0, 0, GemstoneUtils.topazValue(quality, itemRarity));
        }
        return new MiningStats(0, 0, 0);
    }

    @NotNull
    public GatheringStats getBonusGathering(Rarity itemRarity) {
        return new GatheringStats(0, 0);
    }

    @NotNull
    public LuckStats getBonusLuck(Rarity itemRarity) {
        return new LuckStats(0, 0);
    }

}