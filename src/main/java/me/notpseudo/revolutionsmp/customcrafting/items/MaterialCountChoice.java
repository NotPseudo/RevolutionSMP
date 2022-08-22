package me.notpseudo.revolutionsmp.customcrafting.items;

import com.google.common.base.Preconditions;
import me.notpseudo.revolutionsmp.itemstats.ItemInfo;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class MaterialCountChoice implements RecipeChoice {

    private List<Material> materials;
    private int count;

    public MaterialCountChoice(@NotNull Material material) {
        this(List.of(material));
        count = 1;
    }

    public MaterialCountChoice(@NotNull Material material, int count) {
        this(List.of(material));
        this.count = count;
    }


    public MaterialCountChoice(@NotNull Material... materials) {
        this(Arrays.asList(materials));
        count = 1;
    }

    public MaterialCountChoice(@NotNull List<Material> materials) {
        Preconditions.checkArgument(materials != null, "materials can not be null");
        Preconditions.checkArgument(!materials.isEmpty(), "Must have at least one Material");
        for (Material material : materials) {
            Preconditions.checkArgument(material != null, "Can not have null IDs");
        }
        this.materials = new ArrayList<>(materials);
    }

    public MaterialCountChoice(@NotNull List<Material> materials, int count) {
        Preconditions.checkArgument(materials != null, "materials can not be null");
        Preconditions.checkArgument(!materials.isEmpty(), "Must have at least one Material");
        for (Material material : materials) {
            Preconditions.checkArgument(materials != null, "Can not have null IDs");
        }
        this.materials = new ArrayList<>(materials);
        this.count = count;
    }

    @Override
    public @NotNull ItemStack getItemStack() {
        return new ItemStack(materials.get(0), count);
    }

    @Override
    public @NotNull MaterialCountChoice clone() {
        try {
            MaterialCountChoice clone = (MaterialCountChoice) super.clone();
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
        final MaterialCountChoice other = (MaterialCountChoice) obj;
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