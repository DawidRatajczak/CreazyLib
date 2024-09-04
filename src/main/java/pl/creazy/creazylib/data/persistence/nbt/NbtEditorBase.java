package pl.creazy.creazylib.data.persistence.nbt;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class NbtEditorBase<T extends NbtEditor<T>> implements NbtEditor<T> {
  @Override
  @Nullable 
  public <U> U get(@NotNull NamespacedKey key, @NotNull Class<U> type) {
    return getData().get(key, NbtDataType.match(type));
  }

  @SuppressWarnings("unchecked")
  protected <U> void baseSet(NamespacedKey key, U object) {
    getData().set(key, NbtDataType.match((Class<U>) object.getClass()), object);
  }
  protected abstract PersistentDataContainer getData();
}
