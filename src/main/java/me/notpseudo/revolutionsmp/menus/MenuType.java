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
    CRAFTING {
        @Override
        public Menu getNext(Player player) {
            return new CustomCraftingMenu(player);
        }
    },
    ENCHANT {
        @Override
        public Menu getNext(Player player) {
            return new EnchantmentTableMenu(player);
        }
    },
    MONEY {
        @Override
        public Menu getNext(Player player) {
            return new MoneyMenu(player);
        }
    },
    BANK {
        @Override
        public Menu getNext(Player player) {
            return new BankMenu(player);
        }
    },
    DEPOSIT {
        @Override
        public Menu getNext(Player player) {
            return new DepositMenu(player);
        }
    },
    WITHDRAW {
        @Override
        public Menu getNext(Player player) {
            return new WithdrawMenu(player);
        }
    },
    CUSTOM_DEPOSIT {
        @Override
        public Menu getNext(Player player) {
            return new CustomDepositMenu(player);
        }
    },
    CUSTOM_WITHDRAW {
        @Override
        public Menu getNext(Player player) {
            return new CustomWithdrawMenu(player);
        }
    },
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