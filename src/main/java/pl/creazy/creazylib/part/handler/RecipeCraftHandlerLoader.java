package pl.creazy.creazylib.part.handler;

import pl.creazy.creazylib.log.Logger;
import pl.creazy.creazylib.part.PartCreateHandler;
import pl.creazy.creazylib.part.PartManager;
import pl.creazy.creazylib.part.PartOptions;
import pl.creazy.creazylib.part.constraints.Handler;
import pl.creazy.creazylib.part.constraints.Injected;
import pl.creazy.creazylib.plugin.CreazyPlugin;
import pl.creazy.creazylib.recipe.RecipeCraftHandler;
import pl.creazy.creazylib.recipe.RecipeManager;
import pl.creazy.creazylib.util.text.Text;

import java.util.Optional;

@Handler
class RecipeCraftHandlerLoader implements PartCreateHandler {
  @Injected
  private RecipeManager recipeManager;

  @Injected
  private Logger logger;

  @Override
  public void onPartCreate(Object part, PartManager partManager, CreazyPlugin plugin, PartOptions options) {
    if (!part.getClass().isAnnotationPresent(Handler.class)) {
      return;
    }
    if (part instanceof RecipeCraftHandler handler) {
      recipeManager.registerRecipeCraftHandler(handler);
      if (options.shouldLog()) {
        logger.info("Registered recipe craft handler from ".concat(Text.getPrettyClassName(part.getClass())).concat("."));
      }
    }
  }
}
