package pl.creazy.creazylib.engine.listener.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import pl.creazy.creazylib.id.Id;
import pl.creazy.creazylib.item.action.ItemActionManager;
import pl.creazy.creazylib.part.constraints.Part;

@Part
class PlayerInteractAtEntityListener implements Listener {
  private ItemActionManager itemActionManager;

  @EventHandler
  void onEvent(PlayerInteractAtEntityEvent event) {
    var action = itemActionManager.getItemClickAtEntityAction(Id.get(event.getPlayer().getInventory().getItemInMainHand()));
    
    if (action != null) {
      action.handle(event);
    }
  } 
}