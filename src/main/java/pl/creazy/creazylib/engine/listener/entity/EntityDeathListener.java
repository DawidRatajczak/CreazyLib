package pl.creazy.creazylib.engine.listener.entity;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.creazy.creazylib.drop.BonusDropSupport;
import pl.creazy.creazylib.entity.drop.EntityDrop;
import pl.creazy.creazylib.entity.drop.EntityDropManager;
import pl.creazy.creazylib.id.Id;
import pl.creazy.creazylib.item.action.ItemActionManager;
import pl.creazy.creazylib.part.constraints.Part;
import pl.creazy.creazylib.util.item.Items;
import pl.creazy.creazylib.util.math.Numbers;

@Part
class EntityDeathListener implements Listener {
  @Part
  private ItemActionManager itemActionManager;

  @Part
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

