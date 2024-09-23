package pl.creazy.creazylib.screen.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public record MenuHolder(Menu menu, MenuPage page, Player owner) implements InventoryHolder {
  @NotNull
  @Override
  public Inventory getInventory() {
    return owner.getOpenInventory().getTopInventory();
  }
}
