package pl.creazy.creazylib.util.mc;

import lombok.experimental.UtilityClass;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

@UtilityClass
public class Mc {
  public static boolean addItems(@NotNull InventoryHolder holder, @NotNull ItemStack... items) {
    var inventory = holder.getInventory();
    for (ItemStack item : items) {
      if (!hasEmptySlot(holder)) {
        if (holder instanceof LivingEntity entity) {
          var world = entity.getWorld();
          var location = entity.getLocation();
          world.dropItemNaturally(location, item);
        } else {
          return false;
        }
      } else {
        inventory.addItem(item);
      }
    }
    return true;
  }

  public static boolean addItems(@NotNull InventoryHolder holder, @NotNull Collection<ItemStack> items) {
    return addItems(holder, items.toArray(ItemStack[]::new));
  }

  public static boolean hasItems(@NotNull InventoryHolder holder, @NotNull ItemStack item, int itemAmount) {
    var amount = 0;
    for (ItemStack slot : holder.getInventory().getContents()) {
      if (item.isSimilar(slot)) {
        amount += slot.getAmount();
        if (amount >= itemAmount) {
          return true;
        }
      }
    }
    return false;
  }

  public static boolean removeItems(@NotNull InventoryHolder holder, @NotNull ItemStack item, int itemAmount) {
    var inventory = holder.getInventory();
    var items = new ArrayList<ItemStack>();
    var remaining = itemAmount;
    for (ItemStack slot : inventory.getContents()) {
      if (item.isSimilar(slot)) {
        var slotAmount = slot.getAmount();
        if (slotAmount >= itemAmount) {
          slot.setAmount(slotAmount - itemAmount);
          return true;
        } else {
          remaining -= slotAmount;
          items.add(slot);
        }
        if (remaining <= 0) {
          var removed = 0;
          for (ItemStack otherItem : items) {
            removed += otherItem.getAmount();
            if (removed > itemAmount) {
              otherItem.setAmount(removed - itemAmount);
              return true;
            } else {
              otherItem.setAmount(0);
              if (removed == itemAmount) {
                return true;
              }
            }
          }
        }
      }
    }
    return false;
  }

  public boolean hasEmptySlot(@NotNull InventoryHolder holder) {
    return holder.getInventory().firstEmpty() != -1;
  }
}
