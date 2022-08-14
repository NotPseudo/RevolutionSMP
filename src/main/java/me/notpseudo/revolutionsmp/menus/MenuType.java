package me.notpseudo.revolutionsmp.menus;

import me.notpseudo.revolutionsmp.skills.SkillType;
import org.bukkit.entity.Player;

public enum MenuType {

    MAIN {
        @Override
        public Menu getNext(Player player) {
            return new MainMenu(player);
        }
    },
    PROFILE {
        @Override
        public Menu getNext(Player player) {
            return new ProfileMenu(player);
        }
    },
    SKILLS {
        @Override
        public Menu getNext(Player player) {
            return new SkillsMenu(player);
        }
    },
    COLLECTIONS {
        @Override
        public Menu getNext(Player player) {
            return new CollectionsMenu(player);
        }
    },
    FARMING_COLLECTIONS {
        @Override
        public Menu getNext(Player player) {
            return new CollectionTypeMenu(player, SkillType.FARMING);
        }
    },
    MINING_COLLECTIONS {
        @Override
        public Menu getNext(Player player) {
            return new CollectionTypeMenu(player, SkillType.MINING);
        }
    },
    COMBAT_COLLECTIONS {
        @Override
        public Menu getNext(Player player) {
            return new CollectionTypeMenu(player, SkillType.COMBAT);
        }
    },
    FORAGING_COLLECTIONS {
        @Override
        public Menu getNext(Player player) {
            return new CollectionTypeMenu(player, SkillType.FORAGING);
        }
    },
    FISHING_COLLECTIONS {
        @Override
        public Menu getNext(Player player) {
            return new CollectionTypeMenu(player, SkillType.FISHING);
        }
    },
    BUILDER,
    ENCHANT,
    BANK,
    REFORGE {
        @Override
        public Menu getNext(Player player) {
            return new ReforgeMenu(player);
        }
    },
    ADVANCED_REFORGE {
        @Override
        public Menu getNext(Player player) {
            return new AdvancedReforgeMenu(player);
        }
    },
    SLAYER,
    CLOSE;

    public Menu getNext(Player player) {
        return null;
    }

    public boolean meetsRequirement(Player player) {
        return true;
    }

}