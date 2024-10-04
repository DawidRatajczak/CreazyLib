package pl.creazy.creazylib.engine.listener.entity;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import pl.creazy.creazylib.id.Id;
import pl.creazy.creazylib.item.action.ItemActionManager;
import pl.creazy.creazylib.listener.constraints.EventHandlers;

@EventHandlers
@SuppressWarnings("ALL")
class EntityDamageByEntityListener implements Listener {
  private ItemActionManager itemActionManager;

  @EventHandler
  void onEvent(EntityDamageByEntityEvent event) {
    if (!(event.getDamager() instanceof Player player)) {
      return;
    }

    var action = itemActionManager.getItemEntityDamageAction(Id.get(player.getInventory().getItemInMainHand()));

    if (action != null) {
      action.handle(event);
    }
  }
}
