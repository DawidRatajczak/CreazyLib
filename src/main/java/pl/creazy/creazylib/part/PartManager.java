package pl.creazy.creazylib.part;

import static java.lang.String.format;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import pl.creazy.creazylib.plugin.CreazyPlugin;

public class PartManager {
  private final Map<String, Object> parts = new HashMap<>();

  public void addPart(@NotNull Object part) {
    var name = part.getClass().getName();
    if (parts.containsKey(name)) {
      throw new IllegalArgumentException(format("Part with name %s already exists", name));
    }
    parts.put(name, part);
  }

  @NotNull
  public List<PartCreateHandler> getPartCreateHandlers() {
    return parts.values().stream()
        .filter(part -> PartCreateHandler.class.isAssignableFrom(part.getClass()))
        .map(handler -> (PartCreateHandler) handler)
        .toList();
  }

  @NotNull
  public List<Object> getParts(@NotNull CreazyPlugin plugin) {
    return parts.entrySet().stream()
        .filter(entry -> entry.getKey().startsWith(plugin.getClass().getPackageName()))
        .map(Map.Entry::getValue)
        .toList();
  }

  @Nullable
  public Object getPart(String name) {
    return parts.get(name);
  }

  @Nullable
  public Object getPart(Class<?> type) {
    return getPart(type.getName());
  } 
}
