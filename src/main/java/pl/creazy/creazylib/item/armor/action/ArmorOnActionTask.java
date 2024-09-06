package pl.creazy.creazylib.item.armor.action;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import pl.creazy.creazylib.CreazyLib;
import pl.creazy.creazylib.id.Id;
import pl.creazy.creazylib.part.constraints.OnEnable;
import pl.creazy.creazylib.part.constraints.Part;

@Part
class ArmorOnActionTask extends BukkitRunnable {
  private ArmorActionManager armorActionManager;

  @OnEnable
  void start(CreazyLib plugin) {
    runTaskTimer(plugin, 0, 20);
  }

  @Override
  public void run() {
    Bukkit.getOnlinePlayers().forEach(player -> {
      for (ItemStack armor : player.getInventory().getArmorContents()) {
        var action = armorActionManager.getArmorOnAction(Id.get(armor));
        if (action != null) {
          action.handle(player);
        }
      }
    });
  }
}
