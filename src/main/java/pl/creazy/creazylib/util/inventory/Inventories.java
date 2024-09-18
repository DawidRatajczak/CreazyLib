package pl.creazy.creazylib.util.inventory;

import lombok.experimental.UtilityClass;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;

@UtilityClass
public class Inventories {
  public static void removeItem(@NotNull ItemStack item, @NotNull Inventory inventory) {
    var itemAmount = item.getAmount();
    for (ItemStack slot : inventory.getContents()) {
      if (slot != null && slot.isSimilar(item)) {
        var amount = slot.getAmount();
        if (amount > itemAmount) {
          slot.setAmount(amount - itemAmount);
          return;
        }
        if (amount == itemAmount) {
          inventory.remove(slot);
          return;
        }
      }
    }
  }

  public static boolean hasItem(@NotNull ItemStack item, @NotNull Inventory inventory) {
    return Arrays.stream(inventory.getContents())
        .filter(Objects::nonNull)
        .anyMatch(otherItem -> otherItem.isSimilar(item) && otherItem.getAmount() >= item.getAmount());
  }
}
