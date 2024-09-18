package pl.creazy.creazylib.data.persistence.nbt;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EntityNbtEditor extends NbtEditorBase<EntityNbtEditor> {
  private final Entity entity;

  @Override
  public @NotNull EntityNbtEditor remove(@NotNull NamespacedKey key) {
    baseRemove(key);
    return this;
  }

  @Override
  public @NotNull EntityNbtEditor set(@NotNull NamespacedKey key, @NotNull Object object) {
    baseSet(key, object);
    return this;
  }

  @Override
  protected @NotNull PersistentDataContainer getData() {
    return entity.getPersistentDataContainer();
  }
}
