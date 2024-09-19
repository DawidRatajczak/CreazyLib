package pl.creazy.creazylib.engine.listener.block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import pl.creazy.creazylib.id.Id;
import pl.creazy.creazylib.item.action.ItemActionManager;
import pl.creazy.creazylib.part.constraints.Part;

@Part
public class BlockBreakListener implements Listener {
  @Part
  private ItemActionManager itemActionManager;

  @EventHandler
  void handleItemAction(BlockBreakEvent event) {
    var action = itemActionManager.getItemBlockBreakAction(Id.get(event.getPlayer().getInventory().getItemInMainHand()));

    if (action != null) {
      action.handle(event);
    }
  }
}
