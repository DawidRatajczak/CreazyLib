package pl.creazy.creazylib.command;

import java.util.List;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface TabCompleteHandler {
  @Nullable
  List<String> handleTabComplete(@NotNull Player player, @NotNull String[] args);
}
