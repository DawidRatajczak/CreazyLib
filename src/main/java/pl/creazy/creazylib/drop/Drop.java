package pl.creazy.creazylib.drop;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.creazy.creazylib.id.Identifiable;

public interface Drop extends Identifiable {
  @NotNull Iterable<ItemStack> getDrops(@Nullable Player player);

  @NotNull BonusDropSupport getBonusDropSupport(@Nullable Player player);

  int getAmount(@Nullable Player player);

  int getBonusExp(@Nullable Player player);
}
