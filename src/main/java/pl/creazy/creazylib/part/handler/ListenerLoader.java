package pl.creazy.creazylib.part.handler;

import org.bukkit.event.Listener;
import pl.creazy.creazylib.log.Logger;
import pl.creazy.creazylib.part.PartCreateHandler;
import pl.creazy.creazylib.part.PartManager;
import pl.creazy.creazylib.part.constraints.Handler;
import pl.creazy.creazylib.part.constraints.Injected;
import pl.creazy.creazylib.plugin.CreazyPlugin;

@Handler
class ListenerLoader implements PartCreateHandler {
  @Injected
  private Logger logger;

  @Override
  public void onPartCreate(Object part, PartManager partManager, CreazyPlugin plugin) {
    if (part instanceof Listener listener) {
      plugin.getServer().getPluginManager().registerEvents(listener, plugin);
      logger.info("Registered listener.");
    }
  }
}
