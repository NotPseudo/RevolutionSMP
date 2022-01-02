package me.notpseudo.revolutionsmp.datacontainers;

import org.apache.commons.lang.SerializationUtils;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

// Credit: CodedRed's Custom Persistent Data Types Tutorial: https://youtu.be/3OLSfOkgPMw
// PersistentDataType for ArmorStats
public class ArmorStatsDataType implements PersistentDataType<byte[], ArmorStats> {
  @Override
  public Class<byte[]> getPrimitiveType() {
    return byte[].class;
  }

  @Override
  public Class<ArmorStats> getComplexType() {
    return ArmorStats.class;
  }

  @Override
  public byte [] toPrimitive(ArmorStats complex, PersistentDataAdapterContext context) {
    return SerializationUtils.serialize(complex);
  }

  @Override
  public ArmorStats fromPrimitive(byte[] primitive, PersistentDataAdapterContext context) {
    try {
      InputStream is = new ByteArrayInputStream(primitive);
      ObjectInputStream o = new ObjectInputStream(is);
      return (ArmorStats) o.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }
}
