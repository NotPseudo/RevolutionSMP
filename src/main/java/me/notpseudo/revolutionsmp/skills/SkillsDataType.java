package me.notpseudo.revolutionsmp.skills;

import org.apache.commons.lang3.SerializationUtils;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import java.io.*;

public class SkillsDataType implements PersistentDataType<byte[], SkillHolder> {
    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public Class<SkillHolder> getComplexType() {
        return SkillHolder.class;
    }

    @Override
    public byte [] toPrimitive(SkillHolder complex, PersistentDataAdapterContext context) {
        return SerializationUtils.serialize(complex);
    }

    @Override
    public SkillHolder fromPrimitive(byte[] primitive, PersistentDataAdapterContext context) {
        try {
            InputStream is = new ByteArrayInputStream(primitive);
            ObjectInputStream o = new ObjectInputStream(is);
            return (SkillHolder) o.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            if (exception instanceof InvalidClassException) {
                return new SkillHolder();
            }
            exception.printStackTrace();
        }
        return new SkillHolder();
    }

}