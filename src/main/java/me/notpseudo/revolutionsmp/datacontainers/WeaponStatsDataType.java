package me.notpseudo.revolutionsmp.datacontainers;

import org.apache.commons.lang.SerializationUtils;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class WeaponStatsDataType implements PersistentDataType<byte[], WeaponStats> {
  @Override
  public Class<byte[]> getPrimitiveType() {
    return byte[].class;
  }

  @Override
  public Class<WeaponStats> getComplexType() {
    return WeaponStats.class;
  }

  @Override
  public byte [] toPrimitive(WeaponStats complex, PersistentDataAdapterContext context) {
    return SerializationUtils.serialize(complex);
  }

  @Override
  public WeaponStats fromPrimitive(byte[] primitive, PersistentDataAdapterContext context) {
    try {
      InputStream is = new ByteArrayInputStream(primitive);
      ObjectInputStream o = new ObjectInputStream(is);
      return (WeaponStats) o.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }
}
