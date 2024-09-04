package pl.creazy.creazylib.data;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.bukkit.util.io.BukkitObjectInputStream;
import org.jetbrains.annotations.NotNull;

public class ObjectDeserializer {
  @NotNull
  public <T> T deserializeToBukkitObject(byte[] bytes, @NotNull Class<T> castTo) throws RuntimeException {
    return castTo.cast(deserializeToBukkitObject(bytes));
  }

  @NotNull
  public Object deserializeToBukkitObject(byte[] bytes) throws RuntimeException {
    try {
      ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
      BukkitObjectInputStream objectInputStream = new BukkitObjectInputStream(byteArrayInputStream);

      return objectInputStream.readObject();
    } catch (IOException | ClassNotFoundException exception) {
      throw new RuntimeException(exception);
    }
  }

  @NotNull
  public <T> T deserializeToJavaObject(byte[] bytes, @NotNull Class<T> castTo) throws RuntimeException {
    return castTo.cast(deserializeToJavaObject(bytes));
  }

  @NotNull
  public Object deserializeToJavaObject(byte[] bytes) throws RuntimeException {
    try {
      ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
      ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

      return objectInputStream.readObject();
    } catch (IOException | ClassNotFoundException exception) {
      throw new RuntimeException(exception);
    }
  }
}
