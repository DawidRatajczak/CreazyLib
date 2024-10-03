package pl.creazy.creazylib.engine.listener.entity;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import pl.creazy.creazylib.id.Id;
import pl.creazy.creazylib.item.action.ItemActionManager;
import pl.creazy.creazylib.listener.constraints.EventListener;
import pl.creazy.creazylib.part.constraints.Injected;

@EventListener
class ItemBowActionListener implements Listener {
  @Injected
  private ItemActionManager itemActionManager;

  @EventHandler
  void onEntityShoot(EntityShootBowEvent event) {
    var bow = event.getBow();
    if (bow == null) {
      return;
    }
    Id.find(bow).ifPresent(id -> {
      id.set(event.getProjectile());
    });
  }

  @EventHandler
  void onProjectileHit(ProjectileHitEvent event) {
    Id.find(event.getEntity()).ifPresent(id -> {
      var action = itemActionManager.getItemBowHitAction(id);
      if (action != null) {
        action.handle(event);
      }
    });
  }
}
