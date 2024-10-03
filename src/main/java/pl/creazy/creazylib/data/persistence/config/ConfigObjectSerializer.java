package pl.creazy.creazylib.data.persistence.config;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import pl.creazy.creazylib.data.persistence.config.constraints.Skip;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class ConfigObjectSerializer {
  private final Object object;
  private final boolean serializeSuper;

  public @NotNull Map<String, Object> serialize() {
    var data = new HashMap<String, Object>();
    for (Field field : object.getClass().getDeclaredFields()) {
      serialize(field, object, data);
    }
    if (serializeSuper) {
      var type = object.getClass().getSuperclass();
      while (type != null) {
        for (Field field : type.getDeclaredFields()) {
          serialize(field, object, data);
        }
        type = type.getSuperclass();
      }
    }
    return data;
  }

  @SneakyThrows
  private void serialize(Field field, Object object, Map<String, Object> data) {
    if (field.isAnnotationPresent(Skip.class)) {
      return;
    }
    field.setAccessible(true);
    data.put(field.getName(), field.get(object));
  }
}
