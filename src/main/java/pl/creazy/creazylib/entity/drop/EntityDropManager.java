package pl.creazy.creazylib.entity.drop;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.creazy.creazylib.drop.BonusDropSupport;
import pl.creazy.creazylib.drop.DropManagerBase;
import pl.creazy.creazylib.id.Id;
import pl.creazy.creazylib.manager.constraints.Manager;

@Manager
public class EntityDropManager extends DropManagerBase<EntityDrop> {
  public @Nullable EntityDrop getDrop(@Nullable Entity entity) {
    var id = Id.get(entity);
    return getDrop(id);
  }

  public int getFinalDropAmount(@Nullable Player player, @NotNull EntityDrop drop) {
    return getFinalDropAmount(player, drop, BonusDropSupport.LOOTING, Enchantment.LOOTING);
  }
}
