package pl.creazy.creazylib.part.handler;

import pl.creazy.creazylib.log.Logger;
import pl.creazy.creazylib.part.PartCreateHandler;
import pl.creazy.creazylib.part.PartManager;
import pl.creazy.creazylib.part.PartOptions;
import pl.creazy.creazylib.part.constraints.Handler;
import pl.creazy.creazylib.part.constraints.Injected;
import pl.creazy.creazylib.plugin.CreazyPlugin;
import pl.creazy.creazylib.util.text.Text;

@Handler
class PartLogHandler implements PartCreateHandler {
  @Injected
  private Logger logger;

  @Override
  public void onPartCreate(Object part, PartManager partManager, CreazyPlugin plugin, PartOptions options) {
    if (!options.shouldLog()) {
      return;
    }
    var name = options.getName() == null ? Text.getPrettyClassName(part.getClass()) : options.getName();
    logger.info("Created part: ".concat(name));
  }
}
