package me.notpseudo.revolutionsmp.mobstats;

import me.notpseudo.revolutionsmp.skills.SkillType;
import me.notpseudo.revolutionsmp.skills.SkillUtils;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

import java.util.List;

public enum SeaCreature {

    OCTOPUS {
        @Override
        public int getFishLevelReq() {
            return 1;
        }

        @Override
        public double getFishXP() {
            return 41;
        }
    },
    SEA_WALKER {
        @Override
        public int getFishLevelReq() {
            return 2;
        }

        @Override
        public double getFishXP() {
            return 68;
        }
    },
    NIGHT_SQUID {
        @Override
        public int getFishLevelReq() {
            return 3;
        }

        @Override
        public boolean meetsAllRequirements(Player player) {
            return super.meetsAllRequirements(player) && !player.getWorld().isDayTime();
        }

        @Override
        public double getFishXP() {
            return 270;
        }
    },
    SEA_GUARDIAN {
        @Override
        public int getFishLevelReq() {
            return 5;
        }

        @Override
        public double getFishXP() {
            return 101;
        }
    },
    SEA_WITCH {
        @Override
        public int getFishLevelReq() {
            return 7;
        }

        @Override
        public double getFishXP() {
            return 338;
        }
    },
    SEA_ARCHER {
        @Override
        public int getFishLevelReq() {
            return 9;
        }

        @Override
        public double getFishXP() {
            return 169;
        }
    },
    RIDER_OF_THE_DEEP {
        @Override
        public int getFishLevelReq() {
            return 11;
        }

        @Override
        public double getFishXP() {
            return 270;
        }
    },
    CATFISH {
        @Override
        public int getFishLevelReq() {
            return 13;
        }

        @Override
        public double getFishXP() {
            return 405;
        }
    },
    SEA_LEECH {
        @Override
        public int getFishLevelReq() {
            return 16;
        }

        @Override
        public double getFishXP() {
            return 675;
        }
    },
    GUARDIAN_DEFENDER {
        @Override
        public int getFishLevelReq() {
            return 17;
        }

        @Override
        public double getFishXP() {
            return 1013;
        }
    },
    DEEP_SEA_PROTECTOR {
        @Override
        public int getFishLevelReq() {
            return 18;
        }

        @Override
        public double getFishXP() {
            return 1350;
        }
    },
    WATER_HYDRA {
        @Override
        public int getFishLevelReq() {
            return 19;
        }

        @Override
        public double getFishXP() {
            return 4050;
        }
    },
    SEA_EMPEROR {
        @Override
        public int getFishLevelReq() {
            return 20;
        }

        @Override
        public double getFishXP() {
            return 3375;
        }
    },
    WATER_WORM {
        @Override
        public int getFishLevelReq() {
            return 15;
        }

        @Override
        public List<Biome> getBiomes() {
            return List.of(Biome.LUSH_CAVES);
        }

        @Override
        public double getFishXP() {
            return 240;
        }
    },
    POISONED_WATER_WORM {
        @Override
        public int getFishLevelReq() {
            return 17;
        }
        @Override
        public List<Biome> getBiomes() {
            return List.of(Biome.LUSH_CAVES);
        }

        @Override
        public double getFishXP() {
            return 270;
        }
    },
    ZOMBIE_MINER {
        @Override
        public int getFishLevelReq() {
            return 24;
        }
        @Override
        public List<Biome> getBiomes() {
            return List.of(Biome.LUSH_CAVES);
        }

        @Override
        public double getFishXP() {
            return 770;
        }
    },
    FLAMING_WORM {
        @Override
        public int getFishLevelReq() {
            return 19;
        }
        @Override
        public List<Biome> getBiomes() {
            return List.of(Biome.DRIPSTONE_CAVES);
        }

        @Override
        public double getFishXP() {
            return 240;
        }
    },
    LAVA_BLAZE {
        @Override
        public int getFishLevelReq() {
            return 20;
        }
        @Override
        public List<Biome> getBiomes() {
            return List.of(Biome.DRIPSTONE_CAVES);
        }

        @Override
        public double getFishXP() {
            return 548;
        }
    },
    LAVA_PIGLIN {
        @Override
        public int getFishLevelReq() {
            return 22;
        }
        @Override
        public List<Biome> getBiomes() {
            return List.of(Biome.DRIPSTONE_CAVES);
        }

        @Override
        public double getFishXP() {
            return 568;
        }
    },
    MAGMA_SLUG {
        @Override
        public int getFishLevelReq() {
            return 27;
        }

        @Override
        public boolean meetsAllRequirements(Player player) {
            return super.meetsAllRequirements(player) && player.getLocation().getY() < 0;
        }

        @Override
        public double getFishXP() {
            return 730;
        }
    },
    LAVA_LEECH {
        @Override
        public int getFishLevelReq() {
            return 30;
        }
        @Override
        public boolean meetsAllRequirements(Player player) {
            return super.meetsAllRequirements(player) && player.getLocation().getY() < 0;
        }

        @Override
        public double getFishXP() {
            return 1400;
        }
    },
    PYROCLASTIC_WORM {
        @Override
        public int getFishLevelReq() {
            return 31;
        }

        @Override
        public boolean meetsAllRequirements(Player player) {
            return super.meetsAllRequirements(player) && player.getLocation().getY() < 0;
        }

        @Override
        public double getFishXP() {
            return 1100;
        }
    },
    LAVA_FLAME {
        @Override
        public int getFishLevelReq() {
            return 33;
        }

        @Override
        public boolean meetsAllRequirements(Player player) {
            return super.meetsAllRequirements(player) && player.getLocation().getY() < 0;
        }

        @Override
        public double getFishXP() {
            return 2100;
        }
    },
    FIRE_EEL {
        @Override
        public int getFishLevelReq() {
            return 34;
        }

        @Override
        public boolean meetsAllRequirements(Player player) {
            return super.meetsAllRequirements(player) && player.getLocation().getY() < 0;
        }

        @Override
        public double getFishXP() {
            return 2200;
        }
    },
    TAURUS {
        @Override
        public int getFishLevelReq() {
            return 35;
        }

        @Override
        public boolean meetsAllRequirements(Player player) {
            return super.meetsAllRequirements(player) && player.getLocation().getY() < 0;
        }

        @Override
        public double getFishXP() {
            return 4300;
        }
    },
    THUNDER {
        @Override
        public int getFishLevelReq() {
            return 36;
        }

        @Override
        public boolean meetsAllRequirements(Player player) {
            return super.meetsAllRequirements(player) && player.getLocation().getY() < 0;
        }

        @Override
        public double getFishXP() {
            return 12000;
        }
    },
    LORD_JAWBUS {
        @Override
        public int getFishLevelReq() {
            return 45;
        }

        @Override
        public boolean meetsAllRequirements(Player player) {
            return super.meetsAllRequirements(player) && player.getLocation().getY() < 0;
        }

        @Override
        public double getFishXP() {
            return 40000;
        }
    };

    public CustomMobType getCustomMobType() {
        return CustomMobType.valueOf(this.toString());
    }

    public boolean meetsAllRequirements(Player player) {
        boolean biomeReq = true;
        List<Biome> biomes = getBiomes();
        if (biomes != null) {
            biomeReq = biomes.contains(player.getLocation().getBlock().getComputedBiome()) ||
                    (player.getFishHook() != null && biomes.contains(player.getFishHook().getLocation().getBlock().getComputedBiome()));
        }
        return biomeReq && SkillUtils.getHolder(player).getSkill(SkillType.FISHING).getLevel() >= getFishLevelReq();
    }

    public int getFishLevelReq() {
        return 1;
    }

    public List<Biome> getBiomes() {
        return null;
    }

    public double getFishXP() {
        return 50 + getFishLevelReq() * 10;
    }

}