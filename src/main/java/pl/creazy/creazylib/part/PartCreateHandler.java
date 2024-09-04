package pl.creazy.creazylib.part;

import pl.creazy.creazylib.plugin.CreazyPlugin;

public interface PartCreateHandler {
  void onPartCreate(Object part, PartManager partManager, CreazyPlugin plugin);
}
