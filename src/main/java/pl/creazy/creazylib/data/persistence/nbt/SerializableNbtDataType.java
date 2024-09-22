package pl.creazy.creazylib.data.persistence.nbt;

import java.io.Serializable;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import pl.creazy.creazylib.data.ObjectDeserializer;
import pl.creazy.creazylib.data.ObjectSerializer;

public class SerializableNbtDataType<T> implements PersistentDataType<byte[], T> {
  private final Class<T> type;

  SerializableNbtDataType(Class<T> type) {
    this.type = type;
  }

  @NotNull
  @Override
  public Class<byte[]> getPrimitiveType() {
    return byte[].class;
  }

  @NotNull
  @Override
  public Class<T> getComplexType() {
    return type;
  }

  @Override
  public byte @NotNull [] toPrimitive(@NotNull T object, @NotNull PersistentDataAdapterContext context) {
    return new ObjectSerializer().serializeJavaObject(object);
  }

  @NotNull
  @Override
  public T fromPrimitive(byte @NotNull [] data, @NotNull PersistentDataAdapterContext context) {
    return new ObjectDeserializer().deserializeToJavaObject(data, type);
  }
}
