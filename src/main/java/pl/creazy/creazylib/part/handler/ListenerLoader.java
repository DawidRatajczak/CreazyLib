package pl.creazy.creazylib.part.handler;

import org.bukkit.event.Listener;
import pl.creazy.creazylib.listener.constraints.EventHandlers;
import pl.creazy.creazylib.log.Logger;
import pl.creazy.creazylib.part.PartCreateHandler;
import pl.creazy.creazylib.part.PartManager;
import pl.creazy.creazylib.part.PartOptions;
import pl.creazy.creazylib.part.constraints.Handler;
import pl.creazy.creazylib.part.constraints.Injected;
import pl.creazy.creazylib.plugin.CreazyPlugin;
import pl.creazy.creazylib.util.text.Text;

@Handler
class ListenerLoader implements PartCreateHandler {
  @Injected
  private Logger logger;

  @Override
  public void onPartCreate(Object part, PartManager partManager, CreazyPlugin plugin, PartOptions options) {
    if (!part.getClass().isAnnotationPresent(EventHandlers.class)) {
      return;
    }
    if (part instanceof Listener listener) {
      plugin.getServer().getPluginManager().registerEvents(listener, plugin);
      if (options.shouldLog()) {
        logger.success("Registered event handlers from ".concat(Text.getPrettyClassName(part.getClass())).concat("."));
      }
    }
  }
}
