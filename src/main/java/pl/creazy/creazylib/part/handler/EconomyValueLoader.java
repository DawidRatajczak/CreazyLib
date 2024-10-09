package pl.creazy.creazylib.part.handler;

import pl.creazy.creazylib.economy.EconomyManager;
import pl.creazy.creazylib.economy.EconomyValue;
import pl.creazy.creazylib.log.Logger;
import pl.creazy.creazylib.part.PartCreateHandler;
import pl.creazy.creazylib.part.PartManager;
import pl.creazy.creazylib.part.PartOptions;
import pl.creazy.creazylib.part.constraints.Handler;
import pl.creazy.creazylib.part.constraints.Injected;
import pl.creazy.creazylib.plugin.CreazyPlugin;
import pl.creazy.creazylib.util.text.Text;

@Handler
class EconomyValueLoader implements PartCreateHandler {
  @Injected
  private EconomyManager economyManager;

  @Injected
  private Logger logger;

  @Override
  public void onPartCreate(Object part, PartManager partManager, CreazyPlugin plugin, PartOptions options) {
    if (part instanceof EconomyValue economyValue) {
      if (part.getClass().isEnum()) {
        for (Object constant : part.getClass().getEnumConstants()) {
          if (constant instanceof EconomyValue value) {
            economyManager.registerEconomyValue(value);
            logger.info(Text.create("Registered economy value ", value.getName()).concat("."));
          }
        }
        return;
      }
      economyManager.registerEconomyValue(economyValue);
      if (options.shouldLog()) {
        logger.info(Text.create("Registered economy value ", economyValue.getName()).concat("."));
      }
    }
  }
}
