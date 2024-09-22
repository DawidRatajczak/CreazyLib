package pl.creazy.creazylib.screen.menu;

import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public interface MenuContentSetter {
  void setContent(@NotNull Inventory inventory);
}
