package pl.creazy.creazylib.item.armor.action;

import org.bukkit.entity.Player;
import pl.creazy.creazylib.action.Action;

public interface ArmorAction extends Action<Player> {
  @Override
  void handle(Player player);
}
