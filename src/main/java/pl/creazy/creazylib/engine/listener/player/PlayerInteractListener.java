package pl.creazy.creazylib.engine.listener.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import pl.creazy.creazylib.id.Id;
import pl.creazy.creazylib.item.action.ItemActionManager;
import pl.creazy.creazylib.listener.constraints.EventListener;
import pl.creazy.creazylib.part.constraints.Injected;

@EventListener
class PlayerInteractListener implements Listener {
  @Injected
  private ItemActionManager itemActionManager;

  @EventHandler
  void handleItemClickAction(PlayerInteractEvent event) {
    var action = itemActionManager.getItemClickAction(Id.get(event.getItem()));

    if (action != null) {
      action.handle(event);
    }
  }
}
