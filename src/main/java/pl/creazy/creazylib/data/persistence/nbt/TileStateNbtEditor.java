package pl.creazy.creazylib.data.persistence.nbt;

import org.bukkit.NamespacedKey;
import org.bukkit.block.TileState;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class TileStateNbtEditor extends NbtEditorBase<TileStateNbtEditor> implements SaveableNbtEditor<TileStateNbtEditor> {
  private final TileState state;

  @Override
  @NotNull 
  public TileStateNbtEditor set(NamespacedKey key, Object object) {
    baseSet(key, object);
    return this;
  }

  @Override
  protected PersistentDataContainer getData() {
    return state.getPersistentDataContainer();
  }

  @Override
  public void save() {
    state.update();
  }
}
