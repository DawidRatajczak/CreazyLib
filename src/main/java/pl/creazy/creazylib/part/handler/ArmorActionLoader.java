package pl.creazy.creazylib.part.handler;

import pl.creazy.creazylib.item.armor.action.ArmorAction;
import pl.creazy.creazylib.item.armor.action.ArmorActionManager;
import pl.creazy.creazylib.log.Logger;
import pl.creazy.creazylib.part.PartCreateHandler;
import pl.creazy.creazylib.part.PartManager;
import pl.creazy.creazylib.part.constraints.Handler;
import pl.creazy.creazylib.part.constraints.Injected;
import pl.creazy.creazylib.plugin.CreazyPlugin;

@Handler
class ArmorActionLoader implements PartCreateHandler {
  @Injected
  private ArmorActionManager armorActionManager;

  @Injected
  private Logger logger;

  @Override
  public void onPartCreate(Object part, PartManager partManager, CreazyPlugin plugin) {
    if (part instanceof ArmorAction armorAction) {
      armorActionManager.registerAction(armorAction);
      logger.info("Registered armor action.");
    }
  }
}
