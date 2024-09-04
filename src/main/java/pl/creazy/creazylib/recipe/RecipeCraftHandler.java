package pl.creazy.creazylib.recipe;

import org.bukkit.Keyed;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface RecipeCraftHandler extends Keyed {
  boolean canBeCrafted(@NotNull Player player, @NotNull ItemStack[] matrix, @Nullable ItemStack result);
}
