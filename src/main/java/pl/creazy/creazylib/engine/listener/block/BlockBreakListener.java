package pl.creazy.creazylib.engine.listener.block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import pl.creazy.creazylib.block.action.BlockActionManager;
import pl.creazy.creazylib.block.drop.BlockDropManager;
import pl.creazy.creazylib.id.Id;
import pl.creazy.creazylib.item.action.ItemActionManager;
import pl.creazy.creazylib.part.constraints.Part;

@Part
public class BlockBreakListener implements Listener {
  @Part
  private ItemActionManager itemActionManager;

  @Part
  private BlockActionManager blockActionManager;

  @Part
  private BlockDropManager blockDropManager;

  @EventHandler
  void handleItemAction(BlockBreakEvent event) {
    var action = itemActionManager.getItemBlockBreakAction(Id.get(event.getPlayer().getInventory().getItemInMainHand()));

    if (action != null) {
      action.handle(event);
    }
  }

  @EventHandler
  void handleBlockAction(BlockBreakEvent event) {
    var action = blockActionManager.getBlockBreakAction(Id.get(event.getBlock()));

    if (action != null) {
      action.handle(event);
    }
  }

  @EventHandler
  void handleBlockDrops(BlockBreakEvent event) {
    var drop = blockDropManager.getDrop(event.getBlock());

    if (drop == null) {
      return;
    }
    if (!drop.canDrop(event.getBlock())) {
      return;
    }

    var amount = blockDropManager.getFinalDropAmount(event.getPlayer(), drop);
    var location = event.getBlock().getLocation();
    var world = event.getBlock().getWorld();

    for (int i = 0; i < amount; i++) {
      drop.getDrops(event.getPlayer()).forEach(item -> world.dropItem(location, item));
    }
    event.setExpToDrop(event.getExpToDrop() + drop.getBonusExp(event.getPlayer()));
  }
}
