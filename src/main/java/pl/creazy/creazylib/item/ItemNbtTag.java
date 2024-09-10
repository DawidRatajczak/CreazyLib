package pl.creazy.creazylib.item;

import org.bukkit.Keyed;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import pl.creazy.creazylib.data.persistence.nbt.NbtEditor;

import java.io.Serializable;

public interface ItemNbtTag extends Keyed, Serializable {
  default ItemStack save(@NotNull ItemStack item) {
    NbtEditor.of(item).set(getKey(), this).save();
    return item;
  }

  default ItemStack save(@NotNull ItemStack item, @NotNull ItemMeta meta) {
    NbtEditor.of(item, meta).set(getKey(), this).save();
    return item;
  }
}
