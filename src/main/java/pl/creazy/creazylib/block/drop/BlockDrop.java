package pl.creazy.creazylib.block.drop;

import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;
import pl.creazy.creazylib.drop.Drop;

public interface BlockDrop extends Drop {
  default boolean canDrop(@NotNull Block block) {
    return true;
  }
}
