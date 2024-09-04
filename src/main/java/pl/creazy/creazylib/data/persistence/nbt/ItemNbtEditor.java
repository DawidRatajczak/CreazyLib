package pl.creazy.creazylib.data.persistence.nbt;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class ItemNbtEditor extends NbtEditorBase<ItemNbtEditor> implements SaveableNbtEditor<ItemNbtEditor> {
  private final ItemStack item;
  private final ItemMeta meta;
  
  @Override
  @NotNull 
  public ItemNbtEditor set(NamespacedKey key, Object object) {
    baseSet(key, object);
    return this;
  }

  @Override
  protected PersistentDataContainer getData() {
    return meta.getPersistentDataContainer();
  }

  @Override
  public void save() {
    item.setItemMeta(meta);
  }
}
