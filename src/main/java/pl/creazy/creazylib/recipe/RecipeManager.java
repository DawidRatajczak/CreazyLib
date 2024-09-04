package pl.creazy.creazylib.recipe;

import static java.lang.String.format;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;
import pl.creazy.creazylib.part.constraints.Part;

@Part
public class RecipeManager {
  private final Map<NamespacedKey, RecipeCraftHandler> recipeCraftHandlers = new HashMap<>();

  public void registerRecipeCraftHandler(@NotNull RecipeCraftHandler handler) {
    if (recipeCraftHandlers.containsKey(handler.getKey())) {
      throw new IllegalArgumentException(format(
        "Handler with namespaced key %s is already registered",
        handler.getKey().toString()
      ));
    }
    recipeCraftHandlers.put(handler.getKey(), handler);
  }

  @NotNull
  public Optional<RecipeCraftHandler> getRecipeCraftHandler(@NotNull NamespacedKey key) {
    return Optional.ofNullable(recipeCraftHandlers.get(key));
  }
}
