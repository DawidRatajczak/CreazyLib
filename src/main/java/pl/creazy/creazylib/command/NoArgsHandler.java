package pl.creazy.creazylib.command;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface NoArgsHandler {
  void handleNoArgs(@NotNull Player player);
}
