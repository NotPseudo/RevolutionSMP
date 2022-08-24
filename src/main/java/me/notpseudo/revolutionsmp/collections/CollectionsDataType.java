package me.notpseudo.revolutionsmp.collections;

import org.apache.commons.lang3.SerializationUtils;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import java.io.*;

public class CollectionsDataType implements PersistentDataType<byte[], CollectionsHolder> {
    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public Class<CollectionsHolder> getComplexType() {
        return CollectionsHolder.class;
    }

    @Override
    public byte [] toPrimitive(CollectionsHolder complex, PersistentDataAdapterContext context) {
        return SerializationUtils.serialize(complex);
    }

    @Override
    public CollectionsHolder fromPrimitive(byte[] primitive, PersistentDataAdapterContext context) {
        try {
            InputStream is = new ByteArrayInputStream(primitive);
            ObjectInputStream o = new ObjectInputStream(is);
            return (CollectionsHolder) o.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            if (exception instanceof InvalidClassException) {
                return new CollectionsHolder();
            }
            exception.printStackTrace();
        }
        return new CollectionsHolder();
    }

}