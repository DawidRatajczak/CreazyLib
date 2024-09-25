package pl.creazy.creazylib.engine.listener.block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import pl.creazy.creazylib.id.Id;
import pl.creazy.creazylib.item.action.ItemActionManager;
import pl.creazy.creazylib.listener.constraints.EventListener;
import pl.creazy.creazylib.part.constraints.Injected;

@EventListener
public class BlockBreakListener implements Listener {
  @Injected
  private ItemActionManager itemActionManager;

  @EventHandler
  void handleItemAction(BlockBreakEvent event) {
    var action = itemActionManager.getItemBlockBreakAction(Id.get(event.getPlayer().getInventory().getItemInMainHand()));

    if (action != null) {
      action.handle(event);
    }
  }
}
