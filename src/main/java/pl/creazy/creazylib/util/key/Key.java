package pl.creazy.creazylib.util.key;

import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

import pl.creazy.creazylib.CreazyLib;
import pl.creazy.creazylib.plugin.CreazyPlugin;

public final class Key {
  private Key() {
  }

  @NotNull
  public static NamespacedKey create(@NotNull String key, @NotNull Class<? extends CreazyPlugin> pluginType) {
    return new NamespacedKey(CreazyLib.request(), pluginType.getName().concat("_").concat(key));
  }
}
