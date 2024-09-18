package pl.creazy.creazylib.util.player;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.UUID;

@UtilityClass
public class Players {
  public static @NotNull String nameFromUuid(@NotNull UUID uuid) {
    return Objects.requireNonNull(Bukkit.getOfflinePlayer(uuid).getName());
  }
}
