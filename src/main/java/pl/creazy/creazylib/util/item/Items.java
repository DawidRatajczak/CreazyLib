package pl.creazy.creazylib.util.item;

import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@UtilityClass
public class Items {
  public static boolean isEmpty(@Nullable ItemStack item) {
    return item == null || item.getType() == Material.AIR || item.getItemMeta() == null;
  }

  @SuppressWarnings("DataFlowIssue")
  public static int getEnchantLevel(@Nullable ItemStack item, @NotNull Enchantment enchant) {
    if (isEmpty(item)) {
      return 0;
    }
    return item.getItemMeta().getEnchantLevel(enchant);
  }

  public static @Nullable ItemStack getRod(@NotNull Player player) {
    var main = player.getInventory().getItemInMainHand();
    if (main.getType() == Material.FISHING_ROD) {
      return main;
    }
    var off = player.getInventory().getItemInOffHand();
    if (off.getType() == Material.FISHING_ROD) {
      return off;
    }
    return null;
  }

  public static boolean isFish(@NotNull ItemStack item) {
    var type = item.getType();
    return type == Material.SALMON || type == Material.COD || type == Material.TROPICAL_FISH;
  }

  public static int getEnchantLevel(@NotNull Player player, @NotNull Enchantment enchant) {
    return getEnchantLevel(player.getInventory().getItemInMainHand(), enchant);
  }

  public static void add(@NotNull Player player, @NotNull Iterable<ItemStack> items) {
    var inventory = player.getInventory();

    for (ItemStack item : items) {
      if (inventory.firstEmpty() == -1) {
        player.getWorld().dropItem(player.getLocation(), item);
      } else {
        inventory.addItem(item);
      }
    }
  }

  public static void replace(@NotNull ItemStack item, @NotNull ItemStack replacer) {
    item.setItemMeta(replacer.getItemMeta());
    item.setType(replacer.getType());
    item.setData(replacer.getData());
    item.setAmount(replacer.getAmount());
  }
}
