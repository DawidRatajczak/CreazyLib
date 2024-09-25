package pl.creazy.creazylib.part.handler;

import pl.creazy.creazylib.entity.drop.EntityDrop;
import pl.creazy.creazylib.entity.drop.EntityDropManager;
import pl.creazy.creazylib.log.Logger;
import pl.creazy.creazylib.part.PartCreateHandler;
import pl.creazy.creazylib.part.PartManager;
import pl.creazy.creazylib.part.constraints.Handler;
import pl.creazy.creazylib.part.constraints.Injected;
import pl.creazy.creazylib.plugin.CreazyPlugin;

@Handler
class EntityDropLoader implements PartCreateHandler {
  @Injected
  private EntityDropManager entityDropManager;

  @Injected
  private Logger logger;

  @Override
  public void onPartCreate(Object part, PartManager partManager, CreazyPlugin plugin) {
    if (part instanceof EntityDrop drop) {
      entityDropManager.registerDrop(drop);
      logger.info("Registered entity drop.");
    }
  }
}
