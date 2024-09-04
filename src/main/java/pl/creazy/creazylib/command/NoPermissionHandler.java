package pl.creazy.creazylib.command;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface NoPermissionHandler {
  void handleNoPermission(@NotNull Player player, @NotNull String permissionDenied, @NotNull String[] args, @NotNull String commandName);
}
