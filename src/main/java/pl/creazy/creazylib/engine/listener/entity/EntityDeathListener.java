package pl.creazy.creazylib.engine.listener.entity;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import pl.creazy.creazylib.entity.drop.EntityDropManager;
import pl.creazy.creazylib.id.Id;
import pl.creazy.creazylib.item.action.ItemActionManager;
import pl.creazy.creazylib.listener.constraints.EventListener;
import pl.creazy.creazylib.part.constraints.Injected;

@EventListener
class EntityDeathListener implements Listener {
  @Injected
  private ItemActionManager itemActionManager;

  @Injected
  private EntityDropManager entityDropManager;

  @EventHandler
  void handleItemAction(EntityDeathEvent event) {
    var player = event.getEntity().getKiller();

    if (player == null) {
      return;
    }

    var action = itemActionManager.getItemEntityDeathAction(Id.get(player.getInventory().getItemInMainHand()));

    if (action != null) {
      action.handle(event);
    }
  }

  @EventHandler
  void handleDrops(EntityDeathEvent event) {
    var drop = entityDropManager.getDrop(event.getEntity());

    if (drop == null) {
      return;
    }

    if (!drop.canDrop(event.getEntity())) {
      return;
    }
    var amount = entityDropManager.getFinalDropAmount(event.getEntity().getKiller(), drop);
    var location = event.getEntity().getLocation();
    var world = event.getEntity().getWorld();

    for (int i = 0; i < amount; i++) {
      drop.getDrops(event.getEntity().getKiller()).forEach(item -> world.dropItem(location, item));
    }
    event.setDroppedExp(event.getDroppedExp() + drop.getBonusExp(event.getEntity().getKiller()));
  }
}

