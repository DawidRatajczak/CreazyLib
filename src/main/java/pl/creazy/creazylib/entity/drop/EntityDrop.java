package pl.creazy.creazylib.entity.drop;

import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import pl.creazy.creazylib.drop.Drop;

public interface EntityDrop extends Drop {
  default boolean canDrop(@NotNull LivingEntity entity) {
    return true;
  }
}
