package me.notpseudo.revolutionsmp.mobstats;

import org.apache.commons.lang3.SerializationUtils;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

// Credit: CodedRed's Custom Persistent Data Types Tutorial: https://youtu.be/3OLSfOkgPMw
// PersistentDataType for MobInfo
public class MobInfoDataType implements PersistentDataType<byte[], MobInfo> {
    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public Class<MobInfo> getComplexType() {
        return MobInfo.class;
    }

    @Override
    public byte [] toPrimitive(MobInfo complex, PersistentDataAdapterContext context) {
        return SerializationUtils.serialize(complex);
    }

    @Override
    public MobInfo fromPrimitive(byte[] primitive, PersistentDataAdapterContext context) {
        try {
            InputStream is = new ByteArrayInputStream(primitive);
            ObjectInputStream o = new ObjectInputStream(is);
            return (MobInfo) o.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            return MobInfo.newForInvalid();
        }
        return MobInfo.newForInvalid();
    }
}