package pl.creazy.creazylib.data.persistence.config;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import pl.creazy.creazylib.data.persistence.config.constraints.Skip;

import java.lang.reflect.Field;
import java.util.Map;

@AllArgsConstructor
public class ConfigObjectDeserializer {
  private final Object object;
  private final boolean deserializeSuper;

  public void deserialize(@NotNull Map<String, Object> data) {
    for (Field field : object.getClass().getDeclaredFields()) {
      deserialize(field, object, data);
    }
    if (deserializeSuper) {
      var type = object.getClass().getSuperclass();
      while (type != null) {
        for (Field field : type.getDeclaredFields()) {
          deserialize(field, object, data);
        }
        type = type.getSuperclass();
      }
    }
  }

  @SneakyThrows
  private void deserialize(Field field, Object object, Map<String, Object> data) {
    if (field.isAnnotationPresent(Skip.class)) {
      return;
    }
    field.setAccessible(true);
    if (data.containsKey(field.getName())) {
      Object value = data.get(field.getName());
      field.set(object, value);
    }
  }
}
