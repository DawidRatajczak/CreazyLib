package pl.creazy.creazylib.item;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.annotations.Since;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ColorableArmorMeta;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.FoodComponent;
import org.bukkit.inventory.meta.components.ToolComponent;
import org.jetbrains.annotations.NotNull;

import org.jetbrains.annotations.Nullable;
import pl.creazy.creazylib.CreazyLib;
import pl.creazy.creazylib.data.persistence.nbt.NbtEditor;
import pl.creazy.creazylib.id.Id;
import pl.creazy.creazylib.plugin.CreazyPlugin;
import pl.creazy.creazylib.util.key.Key;
import pl.creazy.creazylib.util.text.Text;
import static pl.creazy.creazylib.util.text.Text.color;

@SuppressWarnings("UnstableApiUsage")
public class ItemBuilder {
  private static final String UNIQUE_PREFIX = "unique_";

  private final ItemStack item;
  private final ItemMeta meta;

  public ItemBuilder(@NotNull Material type) {
    item = new ItemStack(type);
    meta = item.getItemMeta();
  }

  @NotNull
  public ItemBuilder setType(@NotNull Material type) {
    item.setType(type);
    return this;
  }

  public @NotNull ItemBuilder setDamage(int damage) {
    if (meta instanceof Damageable damageable) {
      damageable.setDamage(damage);
    }
    return this;
  }

  @NotNull
  public ItemBuilder setAmount(int amount) {
    item.setAmount(amount);
    return this;
  }

  public @NotNull ItemBuilder setColor(@NotNull Color color) {
    if (meta instanceof ColorableArmorMeta colorableArmorMeta) {
      colorableArmorMeta.setColor(color);
    }
    return this;
  }

  @NotNull
  public ItemBuilder setDisplayName(@NotNull String name) {
    meta.setDisplayName(color(name));
    return this;
  }

  public @NotNull ItemBuilder setDisplayName(@NotNull String name, @NotNull String color) {
    return setDisplayName(color.concat(name));
  }

  @NotNull
  public ItemBuilder setLore(@NotNull String... lore) {
    meta.setLore(createLore(lore));
    return this;
  }

  public @NotNull ItemBuilder setLore(List<String> lore) {
    meta.setLore(createLore(lore.toArray(String[]::new)));
    return this;
  }

  @NotNull
  public ItemBuilder addLore(@NotNull String... lore) {
    if (meta.getLore() == null) {
      setLore(lore);
    } else {
      var newLore = meta.getLore();
      newLore.addAll(createLore(lore));
      meta.setLore(newLore);
    }
    return this;
  }

  @NotNull
  public ItemBuilder setCustomModelData(@Nullable Integer data) {
    meta.setCustomModelData(data);
    return this;
  }

  public @NotNull ItemBuilder edit(@NotNull BiConsumer<ItemStack, ItemMeta> biConsumer) {
    biConsumer.accept(item, meta);
    return this;
  }

  public @NotNull ItemBuilder setItemNbtTag(@NotNull Supplier<ItemNbtTag> supplier) {
    return setItemNbtTag(supplier.get());
  }

  public @NotNull ItemBuilder setItemNbtTag(@NotNull ItemNbtTag tag) {
    return edit(tag::save);
  }

  public @NotNull ItemBuilder makeUnique(@NotNull String uniqueString, Class<? extends CreazyPlugin> type) {
    return setNbtData(Key.create(UNIQUE_PREFIX.concat(uniqueString), type), UNIQUE_PREFIX.concat(uniqueString));
  }

  @NotNull
  public ItemBuilder setNbtData(@NotNull NamespacedKey key, @NotNull Object value) {
    NbtEditor.of(item, meta).set(key, value);
    return this;
  }

  @NotNull
  public ItemBuilder setNbtData(@NotNull String key, @NotNull Object value) {
    NbtEditor.of(item, meta).set(Key.create(key, CreazyLib.class), value);
    return this;
  }

  @NotNull
  public ItemBuilder setNbtData(@NotNull String key, @NotNull Class<? extends CreazyPlugin> type, @NotNull Object value) {
    NbtEditor.of(item, meta).set(Key.create(key, type), value);
    return this;
  }

  @NotNull
  public ItemBuilder setId(@NotNull Id id) {
    id.set(item, meta);
    return this;
  }

  @NotNull
  public ItemBuilder addEnchant(@NotNull Enchantment enchant, int level) {
    if (meta instanceof EnchantmentStorageMeta book) {
      book.addStoredEnchant(enchant, level, true);
    } else {
      meta.addEnchant(enchant, level, true);
    }
    return this;
  }

  @NotNull
  public ItemBuilder setUnbreakable(boolean flag) {
    meta.setUnbreakable(flag);
    return this;
  }

  @NotNull
  public ItemBuilder setTool(@NotNull Consumer<ToolComponent> consumer) {
    var tool = meta.getTool();
    consumer.accept(tool);
    meta.setTool(tool);
    return this;
  }

  @NotNull
  public ItemBuilder setFood(@NotNull Consumer<FoodComponent> consumer) {
    var food = meta.getFood();
    consumer.accept(food);
    meta.setFood(food);
    return this;
  }

  @NotNull
  public ItemBuilder setRarity(@NotNull ItemRarity rarity) {
    meta.setRarity(rarity);
    return this;
  }

  @NotNull
  public ItemBuilder setMaxStack(@NotNull Integer max) {
    meta.setMaxStackSize(max);
    return this;
  }

  @NotNull
  public ItemBuilder setHideTooltip(boolean flag) {
    meta.setHideTooltip(flag);
    return this;
  }

  @NotNull
  public ItemBuilder setFireResistant(boolean flag) {
    meta.setFireResistant(flag);
    return this;
  }

  @NotNull
  public ItemBuilder addModifier(@NotNull Attribute attribute, @NotNull AttributeModifier modifier) {
    meta.addAttributeModifier(attribute, modifier);
    return this;
  }

  @NotNull
  public ItemBuilder addFlags(@NotNull ItemFlag... flags) {
    meta.addItemFlags(flags);
    return this;
  }

  @NotNull
  public ItemStack build() {
    item.setItemMeta(meta);
    return item;
  }

  @NotNull
  private List<String> createLore(@NotNull String... lore) {
    return Stream.of(lore).map(Text::color).collect(Collectors.toList());
  }
}