package pl.creazy.creazylib.part.handler;

import pl.creazy.creazylib.item.action.ItemAction;
import pl.creazy.creazylib.item.action.ItemActionManager;
import pl.creazy.creazylib.log.Logger;
import pl.creazy.creazylib.part.PartCreateHandler;
import pl.creazy.creazylib.part.PartManager;
import pl.creazy.creazylib.part.PartOptions;
import pl.creazy.creazylib.part.constraints.Handler;
import pl.creazy.creazylib.part.constraints.Injected;
import pl.creazy.creazylib.plugin.CreazyPlugin;
import pl.creazy.creazylib.util.text.Text;

import java.util.Optional;

@Handler
public class ItemActionLoader implements PartCreateHandler {
  @Injected
  private ItemActionManager itemActionManager;

  @Injected
  private Logger logger;

  @Override
  public void onPartCreate(Object part, PartManager partManager, CreazyPlugin plugin, PartOptions options) {
    if (part instanceof ItemAction<?> action) {
      itemActionManager.registerItemAction(action);
      if (options.shouldLog()) {
        logger.info("Registered item action from ".concat(Text.getPrettyClassName(part.getClass())).concat("."));
      }
    }
  }
}
