package pl.creazy.creazylib.engine.listener.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import pl.creazy.creazylib.block.action.BlockActionManager;
import pl.creazy.creazylib.id.Id;
import pl.creazy.creazylib.item.action.ItemActionManager;
import pl.creazy.creazylib.part.constraints.Part;

@Part
class PlayerInteractListener implements Listener {
  @Part
  private ItemActionManager itemActionManager;

  @Part
  private BlockActionManager blockActionManager;

  @EventHandler
  void handleItemClickAction(PlayerInteractEvent event) {
    var action = itemActionManager.getItemClickAction(Id.get(event.getItem()));
    
    if (action != null) {
      action.handle(event);
    }
  }

//  @EventHandler
//  void handleBlockClickAction(PlayerInteractEvent event) {
//    var action = blockActionManager.getBlockClickAction(Id.get(event.getClickedBlock()));
//
//    if (action != null) {
//      action.handle(event);
//    }
//  }
}
