package pl.creazy.creazylib.util.message;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface Placeholder {
  static Placeholder create(@NotNull String placeholder, @NotNull Object value) {
    return new Placeholder() {
      @Override
      public @NotNull String getPlaceholder() {
        return placeholder;
      }

      @Override
      public @NotNull Object getValue() {
        return value;
      }
    };
  }

  static Placeholder playerName(@NotNull String playerName) {
    return create("${PLAYER_NAME}", playerName);
  }

  static Placeholder playerUuid(@NotNull UUID playerUuid) {
    return create("${PLAYER_UUID}", playerUuid);
  }

  @NotNull String getPlaceholder();

  @NotNull Object getValue();
}
