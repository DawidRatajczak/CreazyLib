package pl.creazy.creazylib.part.handler;

import org.bukkit.event.Listener;
import pl.creazy.creazylib.part.PartCreateHandler;
import pl.creazy.creazylib.part.PartManager;
import pl.creazy.creazylib.part.constraints.Part;
import pl.creazy.creazylib.plugin.CreazyPlugin;

@Part
class ListenerLoader implements PartCreateHandler {
  @Override
  public void onPartCreate(Object part, PartManager partManager, CreazyPlugin plugin) {
    if (part instanceof Listener listener) {
      plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }
  }
}
