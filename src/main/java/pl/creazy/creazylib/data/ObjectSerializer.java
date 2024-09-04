package pl.creazy.creazylib.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jetbrains.annotations.NotNull;

public class ObjectSerializer {
  @NotNull
  public byte[] serializeBukkitObject(@NotNull ConfigurationSerializable bukkitObject) throws RuntimeException {
    try {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      BukkitObjectOutputStream objectOutputStream = new BukkitObjectOutputStream(byteArrayOutputStream);

      objectOutputStream.writeObject(bukkitObject);
      objectOutputStream.flush();

      return byteArrayOutputStream.toByteArray();
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }

  @NotNull
  public byte[] serializeJavaObject(@NotNull Serializable javaObject) throws RuntimeException {
    try {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

      objectOutputStream.writeObject(javaObject);
      objectOutputStream.flush();

      return byteArrayOutputStream.toByteArray();
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }
}
