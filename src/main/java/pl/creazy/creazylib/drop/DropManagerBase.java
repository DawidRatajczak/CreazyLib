package pl.creazy.creazylib.drop;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.creazy.creazylib.id.Id;
import pl.creazy.creazylib.util.math.Numbers;
import pl.creazy.creazylib.util.mc.Mc;

import java.util.HashMap;
import java.util.Map;

public abstract class DropManagerBase<T extends Drop> {
  private final Map<Id, T> drops = new HashMap<>();

  public @Nullable T getDrop(@Nullable Id id) {
    if (id == null) {
      return null;
    }
    return drops.get(id);
  }

  public void registerDrop(@NotNull T drop) {
    if (drops.containsKey(drop.getId())) {
      throw new RuntimeException(String.format("Drop with id %s is already registered", drop.getId()));
    }
    drops.put(drop.getId(), drop);
  }

  @SuppressWarnings("DataFlowIssue")
  protected int getFinalDropAmount(
      @Nullable Player player,
      @NotNull T drop,
      @NotNull BonusDropSupport support,
      @NotNull Enchantment enchant) {
    if (player == null) {
      return drop.getAmount(null);
    }
    if (drop.getBonusDropSupport(player) != BonusDropSupport.ALL && drop.getBonusDropSupport(player) != support) {
      return drop.getAmount(player);
    }
    var item = player.getInventory().getItemInMainHand();

    if (!Mc.isEmpty(item)) {
      var level = item.getItemMeta().getEnchantLevel(enchant);

      if (level == 0) {
        return drop.getAmount(player);
      }
      return drop.getAmount(player) * Numbers.randomInt(1, level + 1);
    }
    return drop.getAmount(player);
  }
}
