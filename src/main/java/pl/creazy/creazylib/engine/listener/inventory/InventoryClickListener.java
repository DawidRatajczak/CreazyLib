package pl.creazy.creazylib.engine.listener.inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import pl.creazy.creazylib.part.constraints.Part;
import pl.creazy.creazylib.screen.menu.MenuHolder;

@Part
class InventoryClickListener implements Listener {
  @EventHandler
  void onEvent(InventoryClickEvent event) {
    var inventory = event.getClickedInventory();

    if (inventory == null) {
      return;
    }

    if (inventory.getHolder() instanceof MenuHolder menu) {
      menu.page().onClick(event);
    }
  }
}
