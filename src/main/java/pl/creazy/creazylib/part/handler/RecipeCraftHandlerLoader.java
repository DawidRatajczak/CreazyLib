package pl.creazy.creazylib.part.handler;

import pl.creazy.creazylib.part.PartCreateHandler;
import pl.creazy.creazylib.part.PartManager;
import pl.creazy.creazylib.part.constraints.Part;
import pl.creazy.creazylib.plugin.CreazyPlugin;
import pl.creazy.creazylib.recipe.RecipeCraftHandler;
import pl.creazy.creazylib.recipe.RecipeManager;

@Part
class RecipeCraftHandlerLoader implements PartCreateHandler {
  private RecipeManager recipeManager;
  
  @Override
  public void onPartCreate(Object part, PartManager partManager, CreazyPlugin plugin) {
    if (part instanceof RecipeCraftHandler handler) {
      recipeManager.registerRecipeCraftHandler(handler);
    }
  }
}
