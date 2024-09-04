package pl.creazy.creazylib.data.persistence.nbt;

import java.util.Optional;

import org.bukkit.NamespacedKey;
import org.bukkit.block.TileState;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface NbtEditor<T extends NbtEditor<T>> {
  @NotNull
  static SaveableNbtEditor<?> of(@NotNull ItemStack item) {
    return new ItemNbtEditor(item, item.getItemMeta());
  }

  @NotNull
  static SaveableNbtEditor<?> of(@NotNull TileState state) {
    return new TileStateNbtEditor(state);
  }

  @NotNull
  static NbtEditor<?> of(@NotNull Entity entity) {
    return new EntityNbtEditor(entity);
  }

  @NotNull
  static SaveableNbtEditor<?> of(@NotNull ItemStack item, @NotNull ItemMeta meta) {
    return new ItemNbtEditor(item, meta);
  }

  @NotNull
  T set(NamespacedKey key, Object object);

  @Nullable
  <U> U get(@NotNull NamespacedKey key, @NotNull Class<U> type);

  default boolean has(@NotNull NamespacedKey key, @NotNull Class<?> type) {
    return get(key, type) != null;
  }

  @NotNull
  default <U> Optional<U> find(@NotNull NamespacedKey key, @NotNull Class<U> type) {
    return Optional.ofNullable(get(key, type));
  }
}
