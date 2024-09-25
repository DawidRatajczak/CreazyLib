package pl.creazy.creazylib.engine.listener.inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import pl.creazy.creazylib.listener.constraints.EventListener;
import pl.creazy.creazylib.screen.menu.MenuHolder;

@EventListener
class InventoryOpenListener implements Listener {
  @EventHandler
  void onEvent(InventoryOpenEvent event) {
    if (event.getInventory().getHolder() instanceof MenuHolder menu) {
      menu.menu().onOpen(event);
    }
  }
}
