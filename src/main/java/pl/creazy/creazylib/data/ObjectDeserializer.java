package pl.creazy.creazylib.data;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.bukkit.util.io.BukkitObjectInputStream;
import org.jetbrains.annotations.NotNull;

public class ObjectDeserializer {
  public @NotNull <T> T deserializeToBukkitObject(byte @NotNull [] bytes, @NotNull Class<T> castTo) throws RuntimeException {
    return castTo.cast(deserializeToBukkitObject(bytes));
  }

  public @NotNull Object deserializeToBukkitObject(byte @NotNull [] bytes) throws RuntimeException {
    try {
      ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
      BukkitObjectInputStream objectInputStream = new BukkitObjectInputStream(byteArrayInputStream);

      return objectInputStream.readObject();
    } catch (IOException | ClassNotFoundException exception) {
      throw new RuntimeException(exception);
    }
  }

  public @NotNull <T> T deserializeToJavaObject(byte @NotNull [] bytes, @NotNull Class<T> castTo) throws RuntimeException {
    return castTo.cast(deserializeToJavaObject(bytes));
  }

  public @NotNull Object deserializeToJavaObject(byte @NotNull [] bytes) throws RuntimeException {
    try {
      ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
      ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

      return objectInputStream.readObject();
    } catch (IOException | ClassNotFoundException exception) {
      throw new RuntimeException(exception);
    }
  }
}
