package pl.creazy.creazylib.data.persistence.nbt;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import pl.creazy.creazylib.data.ObjectDeserializer;
import pl.creazy.creazylib.data.ObjectSerializer;

public class ConfigurationSerializableNbtDataType<T> implements PersistentDataType<byte[], T> {
  private final Class<T> type;

  ConfigurationSerializableNbtDataType(Class<T> type) {
    if (!ConfigurationSerializable.class.isAssignableFrom(type)) {
      throw new RuntimeException(String.format("Type %s must implement Configuration Serializable", type.getName()));
    }
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
  public byte @NotNull [] toPrimitive(@NotNull T object,
      @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
    return new ObjectSerializer().serializeBukkitObject((ConfigurationSerializable) object);
  }

  @NotNull
  @Override
  public T fromPrimitive(byte @NotNull [] data, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
    return new ObjectDeserializer().deserializeToBukkitObject(data, type);
  }
}
