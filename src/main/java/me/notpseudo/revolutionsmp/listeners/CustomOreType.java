package me.notpseudo.revolutionsmp.listeners;

import me.notpseudo.revolutionsmp.items.ItemEditor;
import org.bukkit.Material;

public enum CustomOreType {

    MITHRIL {
        @Override
        public Material[] getVanillaMaterials() {
            return new Material[] {Material.GRAY_WOOL, Material.CYAN_TERRACOTTA, Material.PRISMARINE, Material.PRISMARINE_BRICKS, Material.DARK_PRISMARINE};
        }

        @Override
        public double getBlockStrength(Material vanillaMat) {
            return switch(vanillaMat) {
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
        public double getBlockStrength(Material vanillaMat) {
            return switch(vanillaMat) {
                case RED_STAINED_GLASS -> 2500;
                default -> 2300;
            };
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
        public double getBlockStrength(Material vanillaMat) {
            return switch(vanillaMat) {
                case ORANGE_STAINED_GLASS -> 3200;
                default -> 3000;
            };
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
        public double getBlockStrength(Material vanillaMat) {
            return switch(vanillaMat) {
                case PURPLE_STAINED_GLASS -> 3200;
                default -> 3000;
            };
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
        public double getBlockStrength(Material vanillaMat) {
            return switch(vanillaMat) {
                case LIME_STAINED_GLASS -> 3200;
                default -> 3000;
            };
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
        public double getBlockStrength(Material vanillaMat) {
            return switch(vanillaMat) {
                case WHITE_STAINED_GLASS -> 4000;
                default -> 3800;
            };
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
        public double getBlockStrength(Material vanillaMat) {
            return switch(vanillaMat) {
                case CYAN_STAINED_GLASS -> 4000;
                default -> 3800;
            };
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
        public double getBlockStrength(Material vanillaMat) {
            return switch(vanillaMat) {
                case YELLOW_STAINED_GLASS -> 4000;
                default -> 3800;
            };
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
        public double getBlockStrength(Material vanillaMat) {
            return switch(vanillaMat) {
                case YELLOW_STAINED_GLASS -> 5000;
                default -> 4800;
            };
        }
    };

    public abstract Material[] getVanillaMaterials();

    public String getName() {
        return ItemEditor.getStringFromEnum(this);
    }

    public int getBreakingPower() {
        return 4;
    }

    public double getBlockStrength(Material vanillaMat) {
        return 500;
    }

}