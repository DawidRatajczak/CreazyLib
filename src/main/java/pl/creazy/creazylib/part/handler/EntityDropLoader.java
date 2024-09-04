package pl.creazy.creazylib.part.handler;

import pl.creazy.creazylib.entity.drop.EntityDrop;
import pl.creazy.creazylib.entity.drop.EntityDropManager;
import pl.creazy.creazylib.part.PartCreateHandler;
import pl.creazy.creazylib.part.PartManager;
import pl.creazy.creazylib.part.constraints.Part;
import pl.creazy.creazylib.plugin.CreazyPlugin;

@Part
class EntityDropLoader implements PartCreateHandler {
  @Part
  private EntityDropManager entityDropManager;

  @Override
  public void onPartCreate(Object part, PartManager partManager, CreazyPlugin plugin) {
    if (part instanceof EntityDrop drop) {
      entityDropManager.registerDrop(drop);
    }
  }
}
