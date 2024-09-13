package pl.creazy.creazylib.data.persistence.nbt;

import java.io.Serializable;

import lombok.experimental.UtilityClass;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.persistence.PersistentDataType;

@UtilityClass
public class NbtDataType {
  public static <T> PersistentDataType<byte[], T> serializable(Class<T> type) {
    return new SerializableNbtDataType<>(type);
  }

  public static <T> PersistentDataType<byte[], T> configurationSerializable(Class<T> type) {
    return new ConfigurationSerializableNbtDataType<>(type);
  }

  public static <T> PersistentDataType<byte[], T> match(Class<T> type) {
    if (type.isArray()) {
      if (ConfigurationSerializable.class.isAssignableFrom(type.getComponentType())) {
        return configurationSerializable(type);
      }
      if (Serializable.class.isAssignableFrom(type.getComponentType())) {
        return serializable(type);
      }
    }
    if (Serializable.class.isAssignableFrom(type)) {
      return serializable(type);
    }
    if (ConfigurationSerializable.class.isAssignableFrom(type)) {
      return configurationSerializable(type);
    }
    throw new IllegalArgumentException(String.format(
        "Type %s must implement Serializable or Configuration Serializable",
        type.getName()));
  }
}
