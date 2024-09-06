package pl.creazy.creazylib.action;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.creazy.creazylib.id.Id;

import java.util.Map;

import static java.lang.String.format;

public abstract class ActionManagerBase<T extends Action<?>> {
  protected @Nullable <U extends T> U getAction(@Nullable Id id, @NotNull Map<Id, U> actions) {
    if (id == null) {
      return null;
    }
    return actions.get(id);
  }

  protected <U extends T> void registerAction(@NotNull U action, @NotNull Map<Id, U> actions) {
    if (actions.containsKey(action.getId())) {
      throw new IllegalArgumentException(format("Item action with id %s is already registered", action.getId()));
    }
    actions.put(action.getId(), action);
  }
}
