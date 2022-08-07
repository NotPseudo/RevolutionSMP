package me.notpseudo.revolutionsmp.mining;

import me.notpseudo.revolutionsmp.drops.ItemDropObject;
import me.notpseudo.revolutionsmp.items.ItemEditor;
import me.notpseudo.revolutionsmp.items.ItemID;
import me.notpseudo.revolutionsmp.items.Rarity;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum CustomOreType {

    MITHRIL {
        @Override
        public Material[] getVanillaMaterials() {
            return new Material[] {Material.GRAY_WOOL, Material.CYAN_TERRACOTTA, Material.PRISMARINE, Material.PRISMARINE_BRICKS, Material.DARK_PRISMARINE, Material.LIGHT_BLUE_WOOL};
        }

        @Override
        public double getBlockStrength(Material vanillaMat) {
            return switch(vanillaMat) {
                case LIGHT_BLUE_WOOL -> 1500;
                case PRISMARINE, PRISMARINE_BRICKS, DARK_PRISMARINE -> 800;
                default -> 500;
            };
        }
    },
    TITANIUM {
        @Override
        public Material[] getVanillaMaterials() {
            return new Material[] {Material.DIORITE, Material.POLISHED_DIORITE};
        }

        @Override
        public int getBreakingPower() {
            return 5;
        }

        @Override
        public double getBlockStrength(Material vanillaMat) {
            return switch(vanillaMat) {
                case POLISHED_DIORITE -> 2000;
                default -> 1600;
            };
        }

        @Override
        public double getXp() {
            return 100;
        }
    },
    RUBY {
        @Override
        public Material[] getVanillaMaterials() {
            return new Material[] {Material.RED_STAINED_GLASS, Material.RED_STAINED_GLASS_PANE};
        }

        @Override
        public int getBreakingPower() {
            return 6;
        }

        @Override
        public boolean isGem() {
            return true;
        }

        @Override
        public double getBlockStrength(Material vanillaMat) {
            return switch(vanillaMat) {
                case RED_STAINED_GLASS -> 2500;
                default -> 2300;
            };
        }

        @Override
        public ItemStack getItemDrop(Material material) {
            int count = (int) (Math.random() * 3) + 2;
            if (material == Material.RED_STAINED_GLASS_PANE) {
                count = (int) (Math.random() * 4) + 3;
            }
            return GemstoneUtils.createGemstone(GemstoneType.RUBY, Rarity.COMMON, count);
        }

        @Override
        public double getXp() {
            return 70;
        }
    },
    AMBER {
        @Override
        public Material[] getVanillaMaterials() {
            return new Material[] {Material.ORANGE_STAINED_GLASS, Material.ORANGE_STAINED_GLASS_PANE};
        }
        @Override
        public int getBreakingPower() {
            return 7;
        }

        @Override
        public boolean isGem() {
            return true;
        }

        @Override
        public double getBlockStrength(Material vanillaMat) {
            return switch(vanillaMat) {
                case ORANGE_STAINED_GLASS -> 3200;
                default -> 3000;
            };
        }

        @Override
        public ItemStack getItemDrop(Material material) {
            int count = (int) (Math.random() * 3) + 2;
            if (material == Material.ORANGE_STAINED_GLASS_PANE) {
                count = (int) (Math.random() * 4) + 3;
            }
            return GemstoneUtils.createGemstone(GemstoneType.AMBER, Rarity.COMMON, count);
        }

        @Override
        public double getXp() {
            return 70;
        }
    },
    AMETHYST {
        @Override
        public Material[] getVanillaMaterials() {
            return new Material[] {Material.PURPLE_STAINED_GLASS, Material.PURPLE_STAINED_GLASS_PANE};
        }
        @Override
        public int getBreakingPower() {
            return 7;
        }

        @Override
        public boolean isGem() {
            return true;
        }

        @Override
        public double getBlockStrength(Material vanillaMat) {
            return switch(vanillaMat) {
                case PURPLE_STAINED_GLASS -> 3200;
                default -> 3000;
            };
        }

        @Override
        public ItemStack getItemDrop(Material material) {
            int count = (int) (Math.random() * 3) + 2;
            if (material == Material.PURPLE_STAINED_GLASS_PANE) {
                count = (int) (Math.random() * 4) + 3;
            }
            return GemstoneUtils.createGemstone(GemstoneType.AMETHYST, Rarity.COMMON, count);
        }

        @Override
        public double getXp() {
            return 70;
        }
    },
    JADE {
        @Override
        public Material[] getVanillaMaterials() {
            return new Material[] {Material.LIME_STAINED_GLASS, Material.LIME_STAINED_GLASS_PANE};
        }
        @Override
        public int getBreakingPower() {
            return 7;
        }

        @Override
        public boolean isGem() {
            return true;
        }

        @Override
        public double getBlockStrength(Material vanillaMat) {
            return switch(vanillaMat) {
                case LIME_STAINED_GLASS -> 3200;
                default -> 3000;
            };
        }

        @Override
        public ItemStack getItemDrop(Material material) {
            int count = (int) (Math.random() * 3) + 2;
            if (material == Material.LIME_STAINED_GLASS_PANE) {
                count = (int) (Math.random() * 4) + 3;
            }
            return GemstoneUtils.createGemstone(GemstoneType.JADE, Rarity.COMMON, count);
        }

        @Override
        public double getXp() {
            return 70;
        }

    },
    OPAL {
        @Override
        public Material[] getVanillaMaterials() {
            return new Material[] {Material.WHITE_STAINED_GLASS, Material.WHITE_STAINED_GLASS_PANE};
        }
        @Override
        public int getBreakingPower() {
            return 7;
        }

        @Override
        public boolean isGem() {
            return true;
        }

        @Override
        public double getBlockStrength(Material vanillaMat) {
            return switch(vanillaMat) {
                case WHITE_STAINED_GLASS -> 4000;
                default -> 3800;
            };
        }

        @Override
        public ItemStack getItemDrop(Material material) {
            int count = (int) (Math.random() * 3) + 2;
            if (material == Material.WHITE_STAINED_GLASS_PANE) {
                count = (int) (Math.random() * 4) + 3;
            }
            return GemstoneUtils.createGemstone(GemstoneType.OPAL, Rarity.COMMON, count);
        }

        @Override
        public double getXp() {
            return 70;
        }
    },
    PEARL {
        @Override
        public Material[] getVanillaMaterials() {
            return new Material[] {Material.WHITE_STAINED_GLASS, Material.WHITE_STAINED_GLASS_PANE};
        }
        @Override
        public int getBreakingPower() {
            return 7;
        }

        @Override
        public boolean isGem() {
            return true;
        }

        @Override
        public double getBlockStrength(Material vanillaMat) {
            return switch(vanillaMat) {
                case WHITE_STAINED_GLASS -> 4000;
                default -> 3800;
            };
        }

        @Override
        public ItemStack getItemDrop(Material material) {
            int count = (int) (Math.random() * 3) + 2;
            if (material == Material.WHITE_STAINED_GLASS_PANE) {
                count = (int) (Math.random() * 4) + 3;
            }
            return GemstoneUtils.createGemstone(GemstoneType.PEARL, Rarity.COMMON, count);
        }

        @Override
        public double getXp() {
            return 70;
        }
    },
    SAPPHIRE {
        @Override
        public Material[] getVanillaMaterials() {
            return new Material[] {Material.CYAN_STAINED_GLASS, Material.CYAN_STAINED_GLASS_PANE};
        }
        @Override
        public int getBreakingPower() {
            return 7;
        }

        @Override
        public boolean isGem() {
            return true;
        }

        @Override
        public double getBlockStrength(Material vanillaMat) {
            return switch(vanillaMat) {
                case CYAN_STAINED_GLASS -> 4000;
                default -> 3800;
            };
        }

        @Override
        public ItemStack getItemDrop(Material material) {
            int count = (int) (Math.random() * 3) + 2;
            if (material == Material.CYAN_STAINED_GLASS_PANE) {
                count = (int) (Math.random() * 4) + 3;
            }
            return GemstoneUtils.createGemstone(GemstoneType.SAPPHIRE, Rarity.COMMON, count);
        }

        @Override
        public double getXp() {
            return 70;
        }
    },
    TOPAZ {
        @Override
        public Material[] getVanillaMaterials() {
            return new Material[] {Material.YELLOW_STAINED_GLASS, Material.YELLOW_STAINED_GLASS_PANE};
        }
        @Override
        public int getBreakingPower() {
            return 8;
        }

        @Override
        public boolean isGem() {
            return true;
        }

        @Override
        public double getBlockStrength(Material vanillaMat) {
            return switch(vanillaMat) {
                case YELLOW_STAINED_GLASS -> 4000;
                default -> 3800;
            };
        }

        @Override
        public ItemStack getItemDrop(Material material) {
            int count = (int) (Math.random() * 3) + 2;
            if (material == Material.YELLOW_STAINED_GLASS_PANE) {
                count = (int) (Math.random() * 4) + 3;
            }
            return GemstoneUtils.createGemstone(GemstoneType.TOPAZ, Rarity.COMMON, count);
        }

        @Override
        public double getXp() {
            return 70;
        }
    },
    JASPER {
        @Override
        public Material[] getVanillaMaterials() {
            return new Material[] {Material.MAGENTA_STAINED_GLASS, Material.MAGENTA_STAINED_GLASS_PANE};
        }
        @Override
        public int getBreakingPower() {
            return 9;
        }

        @Override
        public boolean isGem() {
            return true;
        }

        @Override
        public double getBlockStrength(Material vanillaMat) {
            return switch(vanillaMat) {
                case YELLOW_STAINED_GLASS -> 5000;
                default -> 4800;
            };
        }

        @Override
        public ItemStack getItemDrop(Material material) {
            int count = (int) (Math.random() * 3) + 2;
            if (material == Material.MAGENTA_STAINED_GLASS_PANE) {
                count = (int) (Math.random() * 4) + 3;
            }
            return GemstoneUtils.createGemstone(GemstoneType.JASPER, Rarity.COMMON, count);
        }

        @Override
        public double getXp() {
            return 70;
        }
    };

    public abstract Material[] getVanillaMaterials();

    public String getName() {
        return ItemEditor.getStringFromEnum(this);
    }

    public int getBreakingPower() {
        return 4;
    }

    public boolean isGem() {
        return false;
    }

    public GemstoneType getGemstoneType() {
        if (!isGem()) {
            return null;
        }
        try {
            return GemstoneType.valueOf(this.toString());
        } catch (IllegalArgumentException exception) {
            return null;
        }
    }

    public double getBlockStrength(Material vanillaMat) {
        return 500;
    }

    public ItemStack getItemDrop(Material material) {
        return ItemEditor.createItem(ItemID.valueOf(this.toString()));
    }

    public double getXp() {
        return 45;
    }

}