package pl.creazy.creazylib;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import pl.creazy.creazylib.event.CreazyLibRequestEvent;
import pl.creazy.creazylib.listener.constraints.EventHandlers;
import pl.creazy.creazylib.part.PartManager;
import pl.creazy.creazylib.part.constraints.OnEnable;
import pl.creazy.creazylib.plugin.CreazyPlugin;
import pl.creazy.creazylib.plugin.constraints.Plugin;

/*
 * Creazy Lib - A proprietary Minecraft plugin library.
 * Copyright (c) 2024 Creazy.
 * Use is permitted under the Creazy Lib License.
 */
@Getter
@Plugin
public final class CreazyLib extends CreazyPlugin implements Listener {
  public static final String NAME = "CreazyLib";

  private final PartManager partManager = new PartManager();

  @EventHandler
  void onEvent(CreazyLibRequestEvent event) {
    event.setPlugin(this);
  }

  @OnEnable
  private void registerEventHandler() {
    getServer().getPluginManager().registerEvents(this, this);
  }

  @NotNull
  public static CreazyLib request() {
    var event = new CreazyLibRequestEvent();
    Bukkit.getPluginManager().callEvent(event);

    if (event.getPlugin() == null) {
      throw new RuntimeException("CreazyLib is not fully intialized yet");
    }
    return event.getPlugin();
  }
}