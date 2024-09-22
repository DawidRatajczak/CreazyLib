package pl.creazy.creazylib.engine.listener.inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import pl.creazy.creazylib.part.constraints.Part;
import pl.creazy.creazylib.screen.menu.MenuHolder;

@Part
class InventoryCloseListener implements Listener {
  @EventHandler
  void onEvent(InventoryCloseEvent event) {
    if (event.getInventory().getHolder() instanceof MenuHolder menu) {
      menu.menu().onClose(event);
    }
  }
}
