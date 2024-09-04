package pl.creazy.creazylib.part;

import java.lang.reflect.Method;

import org.jetbrains.annotations.NotNull;

import pl.creazy.creazylib.part.constraints.OnEnable;

public class OnEnableInvoker extends PartMethodInvoker {
  public OnEnableInvoker(PartManager partManager) {
    super(partManager);
  }

  @Override
  protected boolean isValid(@NotNull Method method) {
    return method.isAnnotationPresent(OnEnable.class);
  }
}
