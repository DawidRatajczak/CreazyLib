package pl.creazy.creazylib.part.handler;

import pl.creazy.creazylib.block.action.BlockAction;
import pl.creazy.creazylib.block.action.BlockActionManager;
import pl.creazy.creazylib.part.PartCreateHandler;
import pl.creazy.creazylib.part.PartManager;
import pl.creazy.creazylib.part.constraints.Part;
import pl.creazy.creazylib.plugin.CreazyPlugin;

@Part
public class BlockActionLoader implements PartCreateHandler {
  @Part
  private BlockActionManager blockActionManager;

  @Override
  public void onPartCreate(Object part, PartManager partManager, CreazyPlugin plugin) {
    if (part instanceof BlockAction<?> action) {
      blockActionManager.registerBlockAction(action);
    }
  }
}
