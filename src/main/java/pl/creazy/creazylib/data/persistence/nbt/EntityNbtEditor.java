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
  public @NotNull EntityNbtEditor set(NamespacedKey key, Object object) {
    baseSet(key, object);
    return this;
  }

  @Override
  protected PersistentDataContainer getData() {
    return entity.getPersistentDataContainer();
  }
}
