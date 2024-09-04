package pl.creazy.creazylib.part;

import static java.lang.String.format;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.jetbrains.annotations.NotNull;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class PartMethodInvoker {
  private final PartManager partManager;

  protected abstract boolean isValid(@NotNull Method method);

  public void invokeAllMethods(@NotNull Object part) {
    var finder = new PartFinder(partManager);
    for (Method method : part.getClass().getDeclaredMethods()) {
      if (isValid(method)) {
        var args = finder.findPartsForMethod(method);
        method.setAccessible(true);
        try {
          method.invoke(part, args);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
          throw new RuntimeException(format("Failed to run method %s on %s", method.getName(), part.getClass().getName()), exception);
        }
      }
    }
  }
}
