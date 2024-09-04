package pl.creazy.creazylib.part;

import java.lang.reflect.Method;

import org.jetbrains.annotations.NotNull;

import pl.creazy.creazylib.part.constraints.OnDisable;

public class OnDisableInvoker extends PartMethodInvoker {
  public OnDisableInvoker(PartManager partManager) {
    super(partManager);
  }

  @Override
  protected boolean isValid(@NotNull Method method) {
    return method.isAnnotationPresent(OnDisable.class);
  }
}
