package pl.creazy.creazylib.part;

import static java.lang.String.format;

import java.lang.reflect.Method;

import org.jetbrains.annotations.NotNull;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PartFinder {
  private final PartManager partManager;

  @NotNull
  public Object[] findPartsForTypePattern(@NotNull Class<?>[] types) {
    var parts = new Object[types.length];
    var index = 0;

    for (Class<?> type : types) {
      var part = partManager.getPart(type);

      if (part == null) {
        throw new RuntimeException(format("Failed to find part with name %s", type.getName()));
      }
      
      parts[index] = part;
      index++;
    }

    return parts;
  }

  @NotNull
  public Object[] findPartsForMethod(@NotNull Method method) {
    return findPartsForTypePattern(method.getParameterTypes());
  }
}
