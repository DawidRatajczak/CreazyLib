package pl.creazy.creazylib.command;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface NoMatchHandler {
  void handleNoMatch(@NotNull Player player, @NotNull String[] args);
}
