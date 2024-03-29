package me.notpseudo.revolutionsmp.playerstats;

import org.apache.commons.lang3.SerializationUtils;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import java.io.*;

// Credit: CodedRed's Custom Persistent Data Types Tutorial: https://youtu.be/3OLSfOkgPMw
// PersistentDataType for MobInfo
public class PlayerStatsDataType implements PersistentDataType<byte[], PlayerStats> {
    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public Class<PlayerStats> getComplexType() {
        return PlayerStats.class;
    }

    @Override
    public byte [] toPrimitive(PlayerStats complex, PersistentDataAdapterContext context) {
        return SerializationUtils.serialize(complex);
    }

    @Override
    public PlayerStats fromPrimitive(byte[] primitive, PersistentDataAdapterContext context) {
        try {
            InputStream is = new ByteArrayInputStream(primitive);
            ObjectInputStream o = new ObjectInputStream(is);
            return (PlayerStats) o.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            if (exception instanceof InvalidClassException) {
                return new PlayerStats();
            }
            exception.printStackTrace();
        }
        return new PlayerStats();
    }
}