package pl.creazy.creazylib.part;

import static java.lang.String.format;

import java.lang.reflect.Field;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PartConnector {
  private final PartManager partManager;

  public void connectParts(@NotNull List<Object> parts) {
    parts.forEach(part -> {
      for (Field field : part.getClass().getDeclaredFields()) {
        var fieldPart = partManager.getPart(field.getType());
        if (fieldPart != null) {
          field.setAccessible(true);
          try {
            field.set(part, fieldPart);
          } catch (IllegalArgumentException | IllegalAccessException exception) {
            throw new RuntimeException(format("Failed to connect part %s", part.getClass().getName()), exception);
          }
        }
      }
    });
  }
}
