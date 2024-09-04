package pl.creazy.creazylib.part.handler;

import pl.creazy.creazylib.block.drop.BlockDrop;
import pl.creazy.creazylib.block.drop.BlockDropManager;
import pl.creazy.creazylib.part.PartCreateHandler;
import pl.creazy.creazylib.part.PartManager;
import pl.creazy.creazylib.part.constraints.Part;
import pl.creazy.creazylib.plugin.CreazyPlugin;

@Part
class BlockDropLoader implements PartCreateHandler {
  @Part
  private BlockDropManager blockDropManager;

  @Override
  public void onPartCreate(Object part, PartManager partManager, CreazyPlugin plugin) {
    if (part instanceof BlockDrop drop) {
      blockDropManager.registerDrop(drop);
    }
  }
}
