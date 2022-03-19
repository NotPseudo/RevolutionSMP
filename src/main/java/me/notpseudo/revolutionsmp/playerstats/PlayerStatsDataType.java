package me.notpseudo.revolutionsmp.playerstats;

import org.apache.commons.lang.SerializationUtils;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

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
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}