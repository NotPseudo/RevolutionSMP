package me.notpseudo.revolutionsmp.items;

import com.google.common.base.Preconditions;
import me.notpseudo.revolutionsmp.itemstats.ItemInfo;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Predicate;

/**
 * Custom version of RecipeChoice
 * Represents a possible item match in a custom recipe. All choices in a recipe must be satisfied for the result to be crafted
 *
 * @author NotPseudo
 */
public interface CustomRecipeChoice extends Predicate<ItemStack>, Cloneable {

    /**
     * Returns the amount of the material needed
     * @return The amount of the material needed
     */
    public int getCount();

    /**
     * Checks if an item satisfies the CustomRecipeChoice
     * @param item The ItemStack to test
     * @return true if the ItemStack satisfies the CustomRecipeChoice
     */
    @Override
    public boolean test(ItemStack item);

    @NotNull
    public CustomRecipeChoice clone();

    /**
     * Represents a choice that be valid only if the tested item has a matching ItemID and a sufficient amount
     */
    public static class ItemIDChoice implements CustomRecipeChoice {

        private List<ItemID> ids;
        private int count;

        public ItemIDChoice(@NotNull ItemID id) {
            this(List.of(id));
            count = 1;
        }

        public ItemIDChoice(@NotNull ItemID id, int count) {
            this(List.of(id));
            this.count = count;
        }


        public ItemIDChoice(@NotNull ItemID... ids) {
            this(Arrays.asList(ids));
            count = 1;
        }

        public ItemIDChoice(@NotNull List<ItemID> ids) {
            Preconditions.checkArgument(ids != null, "ids can not be null");
            Preconditions.checkArgument(!ids.isEmpty(), "Must have at least one ItemID");
            for (ItemID id : ids) {
                Preconditions.checkArgument(id != null, "Can not have null IDs");
            }
            this.ids = new ArrayList<>(ids);
        }

        public ItemIDChoice(@NotNull List<ItemID> ids, int count) {
            Preconditions.checkArgument(ids != null, "ids can not be null");
            Preconditions.checkArgument(!ids.isEmpty(), "Must have at least one ItemID");
            for (ItemID id : ids) {
                Preconditions.checkArgument(id != null, "Can not have null IDs");
            }
            this.ids = new ArrayList<>(ids);
            this.count = count;
        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public @NotNull ItemIDChoice clone() {
            try {
                ItemIDChoice clone = (ItemIDChoice) super.clone();
                clone.ids = new ArrayList<>(ids);
                return clone;
            } catch (CloneNotSupportedException exception) {
                throw new AssertionError(exception);
            }
        }

        @Override
        public boolean test(@NotNull ItemStack itemStack) {
            ItemInfo info = ItemEditor.getInfo(itemStack);
            if (info == null) {
                return false;
            }
            ItemID id = info.getItemID();
            int amount = itemStack.getAmount();
            for (ItemID itemID : ids) {
                if (id == itemID && amount >= count) {
                    return true;
                }
            }
            return false;
        }

        @NotNull
        public List<ItemID> getChoices() {
            return Collections.unmodifiableList(ids);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final ItemIDChoice other = (ItemIDChoice) obj;
            return Objects.equals(this.ids, other.ids);
        }

        @Override
        public int hashCode() {
            int hash = 9;
            hash = 47 * hash + Objects.hashCode(this.ids);
            return hash;
        }

        @Override
        public String toString() {
            return "ItemIdChoice{" + "ids=" + ids + '}';
        }

    }

    /**
     * Represents a choice that will be valid only if the tested item has a matching material and sufficient amount. The item must also be a vanilla item
     */
    public static class ItemStackChoice implements CustomRecipeChoice {

        private List<Material> materials;
        private int count;

        public ItemStackChoice(@NotNull Material material) {
            this(List.of(material));
            count = 1;
        }

        public ItemStackChoice(@NotNull Material material, int count) {
            this(List.of(material));
            this.count = count;
        }

        public ItemStackChoice(@NotNull ItemStack item) {
            this(List.of(item.getType()));
            this.count = item.getAmount();
        }


        public ItemStackChoice(@NotNull Material... materials) {
            this(Arrays.asList(materials));
            count = 1;
        }

        public ItemStackChoice(@NotNull List<Material> materials) {
            Preconditions.checkArgument(materials != null, "materials can not be null");
            Preconditions.checkArgument(!materials.isEmpty(), "Must have at least one Material");
            for (Material material : materials) {
                Preconditions.checkArgument(material != null, "Can not have null IDs");
            }
            this.materials = new ArrayList<>(materials);
        }

        public ItemStackChoice(@NotNull List<Material> materials, int count) {
            Preconditions.checkArgument(materials != null, "materials can not be null");
            Preconditions.checkArgument(!materials.isEmpty(), "Must have at least one Material");
            for (Material material : materials) {
                Preconditions.checkArgument(materials != null, "Can not have null IDs");
            }
            this.materials = new ArrayList<>(materials);
            this.count = count;
        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public @NotNull ItemStackChoice clone() {
            try {
                ItemStackChoice clone = (ItemStackChoice) super.clone();
                clone.materials = new ArrayList<>(materials);
                return clone;
            } catch (CloneNotSupportedException exception) {
                throw new AssertionError(exception);
            }
        }

        @Override
        public boolean test(@NotNull ItemStack itemStack) {
            ItemInfo info = ItemEditor.getInfo(itemStack);
            if (info == null || info.getItemType() == ItemType.VANILLA_ITEM) {
                return materials.contains(itemStack.getType()) && itemStack.getAmount() >= count;
            } else {
                return false;
            }
        }

        @NotNull
        public List<Material> getChoices() {
            return Collections.unmodifiableList(materials);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final ItemStackChoice other = (ItemStackChoice) obj;
            return Objects.equals(this.materials, other.materials);
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 37 * hash + Objects.hashCode(this.materials);
            return hash;
        }

        @Override
        public String toString() {
            return "MaterialCountChoice{" + "materials=" + materials + '}';
        }

    }

}