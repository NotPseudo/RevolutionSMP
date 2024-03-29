package me.notpseudo.revolutionsmp.itemstats;

import org.apache.commons.lang3.SerializationUtils;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import java.io.*;

// Credit: CodedRed's Custom Persistent Data Types Tutorial: https://youtu.be/3OLSfOkgPMw
// PersistentDataType for ItemInfo
public class ItemInfoDataType implements PersistentDataType<byte[], ItemInfo> {
    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public Class<ItemInfo> getComplexType() {
        return ItemInfo.class;
    }

    @Override
    public byte[] toPrimitive(ItemInfo complex, PersistentDataAdapterContext context) {
        return SerializationUtils.serialize(complex);
    }

    @Override
    public ItemInfo fromPrimitive(byte[] primitive, PersistentDataAdapterContext context) {
        try {
            InputStream is = new ByteArrayInputStream(primitive);
            ObjectInputStream o = new ObjectInputStream(is);
            return (ItemInfo) o.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            return ItemInfo.newForInvalid();
        }
        return ItemInfo.newForInvalid();
    }
}