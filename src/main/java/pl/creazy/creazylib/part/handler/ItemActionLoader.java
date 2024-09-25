package pl.creazy.creazylib.part.handler;

import pl.creazy.creazylib.item.action.ItemAction;
import pl.creazy.creazylib.item.action.ItemActionManager;
import pl.creazy.creazylib.log.Logger;
import pl.creazy.creazylib.part.PartCreateHandler;
import pl.creazy.creazylib.part.PartManager;
import pl.creazy.creazylib.part.constraints.Handler;
import pl.creazy.creazylib.part.constraints.Injected;
import pl.creazy.creazylib.plugin.CreazyPlugin;

@Handler
public class ItemActionLoader implements PartCreateHandler {
  @Injected
  private ItemActionManager itemActionManager;

  @Injected
  private Logger logger;

  @Override
  public void onPartCreate(Object part, PartManager partManager, CreazyPlugin plugin) {
    if (part instanceof ItemAction<?> action) {
      itemActionManager.registerItemAction(action);
      logger.info("Registered item action.");
    }
  }
}
