package pl.creazy.creazylib.part;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PartOptions {
  private final boolean log;
  private final String name;

  public PartOptions(@NotNull Class<?> part) {
    var options = part.getAnnotation(pl.creazy.creazylib.part.constraints.PartOptions.class);
    if (options != null) {
      log = options.log();
      name = options.name().isBlank() ? null : options.name();
    } else {
      log = true;
      name = null;
    }
  }

  public boolean shouldLog() {
    return log;
  }

  public @Nullable String getName() {
    return name;
  }
}
