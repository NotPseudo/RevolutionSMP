package me.notpseudo.revolutionsmp.datacontainers;

import org.apache.commons.lang.SerializationUtils;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class AbilityStatsDataType implements PersistentDataType<byte[], AbilityStats> {
  @Override
  public Class<byte[]> getPrimitiveType() {
    return byte[].class;
  }

  @Override
  public Class<AbilityStats> getComplexType() {
    return AbilityStats.class;
  }

  @Override
  public byte [] toPrimitive(AbilityStats complex, PersistentDataAdapterContext context) {
    return SerializationUtils.serialize(complex);
  }

  @Override
  public AbilityStats fromPrimitive(byte[] primitive, PersistentDataAdapterContext context) {
    try {
      InputStream is = new ByteArrayInputStream(primitive);
      ObjectInputStream o = new ObjectInputStream(is);
      return (AbilityStats) o.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }
}
