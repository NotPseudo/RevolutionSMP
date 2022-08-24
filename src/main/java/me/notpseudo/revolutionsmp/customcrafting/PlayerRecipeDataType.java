package me.notpseudo.revolutionsmp.customcrafting;

import org.apache.commons.lang3.SerializationUtils;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import java.io.*;

public class PlayerRecipeDataType implements PersistentDataType<byte[], PlayerRecipeInfo> {
    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public Class<PlayerRecipeInfo> getComplexType() {
        return PlayerRecipeInfo.class;
    }

    @Override
    public byte [] toPrimitive(PlayerRecipeInfo complex, PersistentDataAdapterContext context) {
        return SerializationUtils.serialize(complex);
    }

    @Override
    public PlayerRecipeInfo fromPrimitive(byte[] primitive, PersistentDataAdapterContext context) {
        try {
            InputStream is = new ByteArrayInputStream(primitive);
            ObjectInputStream o = new ObjectInputStream(is);
            return (PlayerRecipeInfo) o.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            if (exception instanceof InvalidClassException) {
                return new PlayerRecipeInfo();
            }
            exception.printStackTrace();
        }
        return new PlayerRecipeInfo();
    }

}