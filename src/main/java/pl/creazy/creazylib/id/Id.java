package pl.creazy.creazylib.id;

import java.io.Serializable;
import java.util.Optional;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.checkerframework.checker.units.qual.N;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import pl.creazy.creazylib.CreazyLib;
import pl.creazy.creazylib.data.persistence.config.Config;
import pl.creazy.creazylib.data.persistence.nbt.NbtEditor;
import pl.creazy.creazylib.plugin.CreazyPlugin;
import pl.creazy.creazylib.util.item.Items;
import pl.creazy.creazylib.util.key.Key;
import pl.creazy.creazylib.util.text.Text;

@Getter
@ToString
@EqualsAndHashCode
public class Id implements Serializable {
  private static final String NAMESPACE = "creazy_id";

  private final String id;
  private final String pluginClassName;

  public Id(@NotNull String id, @NotNull Class<? extends CreazyPlugin> type) {
    this.id = id;
    pluginClassName = type.getName();
  }

  public static @NotNull Optional<Id> find(@Nullable ItemStack item) {
    return Optional.ofNullable(get(item));
  }

  public static @NotNull Optional<Id> find(@Nullable Entity entity) {
    return Optional.ofNullable(get(entity));
  }

  public static @NotNull Optional<Id> find(@Nullable TileState block) {
    return Optional.ofNullable(get(block));
  }

  public static @Nullable Id get(@Nullable ItemStack item) {
    if (Items.isEmpty(item)) {
      return null;
    }
    return NbtEditor.of(item).get(createKey(), Id.class);
  }

  public static @Nullable Id get(@Nullable Entity entity) {
    if (entity == null) {
      return null;
    }
    return NbtEditor.of(entity).get(createKey(), Id.class);
  }

  public static @Nullable Id get(@Nullable TileState tileState) {
    if (tileState == null) {
      return null;
    }
    return NbtEditor.of(tileState).get(createKey(), Id.class);
  }

  public void set(@NotNull TileState block) {
    NbtEditor.of(block).set(createKey(), this).save();
  }

  public void set(@NotNull ItemStack item) {
    NbtEditor.of(item).set(createKey(), this).save();
  }

  public void set(@NotNull ItemStack item, @NotNull ItemMeta meta) {
    NbtEditor.of(item, meta)
        .set(createKey(), this)
        .save();
  }

  public void set(@NotNull Entity entity) {
    NbtEditor.of(entity).set(createKey(), this);
  }

  @NotNull
  private static NamespacedKey createKey() {
    return Key.create(NAMESPACE, CreazyLib.class);
  }
}
