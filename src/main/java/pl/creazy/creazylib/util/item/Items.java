package pl.creazy.creazylib.util.item;

import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@UtilityClass
public class Items {
  public static boolean isEmpty(@Nullable ItemStack item) {
    return item == null || item.getType() == Material.AIR || item.getItemMeta() == null;
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
}
