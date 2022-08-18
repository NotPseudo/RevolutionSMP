package me.notpseudo.revolutionsmp.items;

import org.jetbrains.annotations.NotNull;

public class ItemIDCount {

    private final ItemID ITEMID;
    private final int COUNT;

    public ItemIDCount(@NotNull ItemID id) {
        ITEMID = id;
        COUNT = 1;
    }

    public ItemIDCount(@NotNull ItemID id, int count) {
        ITEMID = id;
        if (count < 1) {
            COUNT = 1;
        } else {
            COUNT = count;
        }
    }

    @NotNull
    public ItemID getItemID() {
        return ITEMID;
    }

    public int getCount() {
        return COUNT;
    }

    @Override
    public String toString() {
        return "{itemid=" + ITEMID + ", count=" + COUNT + "}";
    }
}