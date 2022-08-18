package me.notpseudo.revolutionsmp.items;

import com.google.common.base.Preconditions;
import me.notpseudo.revolutionsmp.itemstats.ItemInfo;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ItemIDChoice implements RecipeChoice {

    private List<ItemIDCount> ids;

    public ItemIDChoice(@NotNull ItemID id) {
        this(List.of(id));
    }

    public ItemIDChoice(@NotNull ItemID id, int count) {
        ids = new ArrayList<>(List.of(new ItemIDCount(id, count)));
    }


    public ItemIDChoice(@NotNull ItemID... ids) {
        this(Arrays.asList(ids));
    }

    public ItemIDChoice(@NotNull List<ItemID> ids) {
        Preconditions.checkArgument(ids != null, "ids can not be null");
        Preconditions.checkArgument(!ids.isEmpty(), "Must have at least one ItemID");
        for (ItemID id : ids) {
            Preconditions.checkArgument(id != null, "Can not have null IDs");
        }
        this.ids = new ArrayList<>();
        for (ItemID id : ids) {
            this.ids.add(new ItemIDCount(id, 1));
        }
    }

    public ItemIDChoice(@NotNull List<ItemID> ids, @NotNull List<Integer> counts) {
        Preconditions.checkArgument(ids != null, "ids can not be null");
        Preconditions.checkArgument(!ids.isEmpty(), "Must have at least one ItemID");
        for (ItemID id : ids) {
            Preconditions.checkArgument(id != null, "Can not have null IDs");
        }
        this.ids = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            if (i < counts.size()) {
                this.ids.add(new ItemIDCount(ids.get(i), counts.get(i)));
            } else {
                this.ids.add(new ItemIDCount(ids.get(i), 1));
            }
        }
    }

    @Override
    public @NotNull ItemStack getItemStack() {
        return ids.get(0).getItemID().getItem();
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
        for (ItemIDCount count : ids) {
            if (id == count.getItemID() && amount >= count.getCount()) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    public List<ItemIDCount> getChoices() {
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