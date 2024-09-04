package pl.creazy.creazylib.part.handler;

import static pl.creazy.creazylib.util.text.Text.path;

import pl.creazy.creazylib.item.action.ItemAction;
import pl.creazy.creazylib.item.action.ItemActionManager;
import pl.creazy.creazylib.part.PartCreateHandler;
import pl.creazy.creazylib.part.PartManager;
import pl.creazy.creazylib.part.constraints.Part;
import pl.creazy.creazylib.plugin.CreazyPlugin;

@Part
public class ItemActionLoader implements PartCreateHandler {
  private ItemActionManager itemActionManager;

  @Override
  public void onPartCreate(Object part, PartManager partManager, CreazyPlugin plugin) {
    if (part instanceof ItemAction<?> action) {
      itemActionManager.registerItemAction(action);
    }
  }
}
