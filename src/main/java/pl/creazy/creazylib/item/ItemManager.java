package pl.creazy.creazylib.item;

import static java.lang.String.format;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import pl.creazy.creazylib.part.constraints.Part;

@Part
public class ItemManager {
  private final Map<String, ItemStack> items = new HashMap<>();

  public void addItem(@NotNull String name, @NotNull ItemStack item) {
    if (items.containsKey(name)) {
      throw new RuntimeException(format("Item with name %s already exists", name));
    }
    items.put(name, item);
  }

  @Nullable
  public ItemStack getItem(@NotNull String name) {
    return items.get(name);
  }

  @NotNull
  public Set<String> names() {
    return items.keySet();
  }
}
