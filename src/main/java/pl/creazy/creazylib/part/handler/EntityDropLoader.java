package pl.creazy.creazylib.part.handler;

import pl.creazy.creazylib.drop.constraints.Drop;
import pl.creazy.creazylib.entity.drop.EntityDrop;
import pl.creazy.creazylib.entity.drop.EntityDropManager;
import pl.creazy.creazylib.log.Logger;
import pl.creazy.creazylib.part.PartCreateHandler;
import pl.creazy.creazylib.part.PartManager;
import pl.creazy.creazylib.part.PartOptions;
import pl.creazy.creazylib.part.constraints.Handler;
import pl.creazy.creazylib.part.constraints.Injected;
import pl.creazy.creazylib.plugin.CreazyPlugin;
import pl.creazy.creazylib.util.text.Text;

@Handler
class EntityDropLoader implements PartCreateHandler {
  @Injected
  private EntityDropManager entityDropManager;

  @Injected
  private Logger logger;

  @Override
  public void onPartCreate(Object part, PartManager partManager, CreazyPlugin plugin, PartOptions options) {
    if (!part.getClass().isAnnotationPresent(Drop.class)) {
      return;
    }
    if (part instanceof EntityDrop drop) {
      entityDropManager.registerDrop(drop);
      if (options.shouldLog()) {
        logger.info("Registered entity drop from ".concat(Text.getPrettyClassName(part.getClass())).concat("."));
      }
    }
  }
}
