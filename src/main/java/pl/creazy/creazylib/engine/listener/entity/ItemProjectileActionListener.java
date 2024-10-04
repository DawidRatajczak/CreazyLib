package pl.creazy.creazylib.engine.listener.entity;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import pl.creazy.creazylib.id.Id;
import pl.creazy.creazylib.item.action.ItemActionManager;
import pl.creazy.creazylib.listener.constraints.EventHandlers;
import pl.creazy.creazylib.part.constraints.Injected;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@EventHandlers
class ItemProjectileActionListener implements Listener {
  @Injected
  private ItemActionManager itemActionManager;

  private final Map<UUID, Id> interactIds = new HashMap<>();

  @EventHandler
  void interact(PlayerInteractEvent event) {
    Id.find(event.getItem()).ifPresent(id -> interactIds.put(event.getPlayer().getUniqueId(), id));
  }

  @EventHandler
  void test(ProjectileLaunchEvent event) {
    if (!(event.getEntity().getShooter() instanceof Player player)) {
      return;
    }
    var id = interactIds.get(player.getUniqueId());
    if (id != null) {
      id.set(event.getEntity());
      var action = itemActionManager.getItemLaunchAction(id);
      if (action != null) {
        action.handle(event);
      }
    }
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
