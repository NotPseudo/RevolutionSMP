package me.notpseudo.revolutionsmp.economy;

import org.apache.commons.lang3.SerializationUtils;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import java.io.*;

public class PlayerEcoDataType implements PersistentDataType<byte[], PlayerEcoInfo> {
    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public Class<PlayerEcoInfo> getComplexType() {
        return PlayerEcoInfo.class;
    }

    @Override
    public byte [] toPrimitive(PlayerEcoInfo complex, PersistentDataAdapterContext context) {
        return SerializationUtils.serialize(complex);
    }

    @Override
    public PlayerEcoInfo fromPrimitive(byte[] primitive, PersistentDataAdapterContext context) {
        try {
            InputStream is = new ByteArrayInputStream(primitive);
            ObjectInputStream o = new ObjectInputStream(is);
            return (PlayerEcoInfo) o.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            if (exception instanceof InvalidClassException) {
                return new PlayerEcoInfo();
            }
            exception.printStackTrace();
        }
        return new PlayerEcoInfo();
    }

}