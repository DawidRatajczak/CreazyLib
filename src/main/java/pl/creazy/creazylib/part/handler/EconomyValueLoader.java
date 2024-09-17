package pl.creazy.creazylib.part.handler;

import pl.creazy.creazylib.economy.EconomyManager;
import pl.creazy.creazylib.economy.EconomyValue;
import pl.creazy.creazylib.log.Logger;
import pl.creazy.creazylib.part.PartCreateHandler;
import pl.creazy.creazylib.part.PartManager;
import pl.creazy.creazylib.part.constraints.Part;
import pl.creazy.creazylib.plugin.CreazyPlugin;
import pl.creazy.creazylib.util.text.Text;

@Part
class EconomyValueLoader implements PartCreateHandler {
  @Part
  private EconomyManager economyManager;

  @Part
  private Logger logger;

  @Override
  public void onPartCreate(Object part, PartManager partManager, CreazyPlugin plugin) {
    if (part instanceof EconomyValue economyValue) {
      if (part.getClass().isEnum()) {
        for (Object constant : part.getClass().getEnumConstants()) {
          if (constant instanceof EconomyValue value) {
            economyManager.registerEconomyValue(value);
            logger.info(Text.create("Registered economy value: ", value.getName()));
          }
        }
        return;
      }
      logger.info(Text.create("Registered economy value: ", economyValue.getName()));
      economyManager.registerEconomyValue(economyValue);
    }
  }
}
