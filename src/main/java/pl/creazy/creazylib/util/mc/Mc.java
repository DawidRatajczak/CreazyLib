package pl.creazy.creazylib.util.mc;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

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
    return type == Material.SALMON || type == Material.COD || type == Material.TROPICAL_FISH || type == Material.PUFFERFISH;
  }

  public static int getEnchantLevel(@NotNull Player player, @NotNull Enchantment enchant) {
    return getEnchantLevel(player.getInventory().getItemInMainHand(), enchant);
  }

  public static void replace(@NotNull ItemStack item, @NotNull ItemStack replacer) {
    item.setItemMeta(replacer.getItemMeta());
    item.setType(replacer.getType());
    item.setData(replacer.getData());
    item.setAmount(replacer.getAmount());
  }

  public static @NotNull String getPlayerName(@NotNull UUID playerUuid) {
    return Objects.requireNonNull(Bukkit.getOfflinePlayer(playerUuid).getName());
  }
}
