package me.notpseudo.revolutionsmp.items;

import com.google.common.base.Preconditions;
import me.notpseudo.revolutionsmp.itemstats.ItemInfo;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ItemIDChoice implements RecipeChoice {

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
    public @NotNull ItemStack getItemStack() {
        return ids.get(0).getItem();
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
            if (id ==itemID && amount >= count) {
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