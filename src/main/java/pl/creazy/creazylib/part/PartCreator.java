package pl.creazy.creazylib.part;

import java.lang.reflect.InvocationTargetException;

import org.jetbrains.annotations.NotNull;

public class PartCreator {
  @NotNull
  public Object createPart(@NotNull Class<?> type) {
    try {
      var constructor = type.getDeclaredConstructor();
      constructor.setAccessible(true);
      var part = constructor.newInstance();
      return part;
    } catch (NoSuchMethodException | SecurityException | InstantiationException
        | IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
      throw new RuntimeException("Part must have an empty constructor", exception);
    }
  }
}
