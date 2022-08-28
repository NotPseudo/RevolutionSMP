package me.notpseudo.revolutionsmp.mining;

import org.apache.commons.lang3.SerializationUtils;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class PlacedLocationListDataType implements PersistentDataType<byte[], PlacedLocationList> {

    @Override
    public @NotNull Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public @NotNull Class<PlacedLocationList> getComplexType() {
        return PlacedLocationList.class;
    }

    @Override
    public byte @NotNull [] toPrimitive(@NotNull PlacedLocationList complex, @NotNull PersistentDataAdapterContext context) {
        return SerializationUtils.serialize(complex);
    }

    @Override
    public @NotNull PlacedLocationList fromPrimitive(byte @NotNull [] primitive, @NotNull PersistentDataAdapterContext context) {
        try {
            InputStream is = new ByteArrayInputStream(primitive);
            ObjectInputStream o = new ObjectInputStream(is);
            return (PlacedLocationList) o.readObject();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            if (exception instanceof InvalidClassException) {
                return new PlacedLocationList();
            }
            exception.printStackTrace();
        }
        return new PlacedLocationList();
    }

}