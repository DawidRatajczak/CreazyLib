package pl.creazy.creazylib.part.handler;

import pl.creazy.creazylib.item.armor.action.ArmorAction;
import pl.creazy.creazylib.item.armor.action.ArmorActionManager;
import pl.creazy.creazylib.part.PartCreateHandler;
import pl.creazy.creazylib.part.PartManager;
import pl.creazy.creazylib.part.constraints.Part;
import pl.creazy.creazylib.plugin.CreazyPlugin;

@Part
class ArmorActionLoader implements PartCreateHandler {
  private ArmorActionManager armorActionManager;

  @Override
  public void onPartCreate(Object part, PartManager partManager, CreazyPlugin plugin) {
    if (part instanceof ArmorAction armorAction) {
      armorActionManager.registerAction(armorAction);
    }
  }
}
