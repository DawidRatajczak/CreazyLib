package pl.creazy.creazylib.part.handler;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.BlastingRecipe;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.SmokingRecipe;
import pl.creazy.creazylib.log.Logger;
import pl.creazy.creazylib.part.PartCreateHandler;
import pl.creazy.creazylib.part.PartManager;
import pl.creazy.creazylib.part.PartOptions;
import pl.creazy.creazylib.part.constraints.Handler;
import pl.creazy.creazylib.part.constraints.Injected;
import pl.creazy.creazylib.plugin.CreazyPlugin;
import pl.creazy.creazylib.recipe.constraints.AddRecipe;
import pl.creazy.creazylib.recipe.constraints.Recipes;
import pl.creazy.creazylib.util.text.Text;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.Key;
import java.util.Objects;
import java.util.Optional;

@Handler
class RecipeLoader implements PartCreateHandler {
  @Injected
  private Logger logger;

  @Override
  public void onPartCreate(Object part, PartManager partManager, CreazyPlugin plugin, PartOptions options) {
    if (!part.getClass().isAnnotationPresent(Recipes.class)) {
      return;
    }
    for (Method method : part.getClass().getDeclaredMethods()) {
      var addRecipe = method.getAnnotation(AddRecipe.class);
      if (addRecipe == null) {
        continue;
      }
      if (!Recipe.class.isAssignableFrom(method.getReturnType())) {
        continue;
      }
      if (method.getParameterCount() != 0) {
        continue;
      }
      try {
        method.setAccessible(true);
        var recipe = (Recipe) method.invoke(part);
        handleIncludeForSmoker(addRecipe, plugin, recipe, options);
        handleIncludeForBlastFurnace(addRecipe, plugin, recipe, options);
        plugin.getServer().addRecipe(recipe);
        if (options.shouldLog()) {
          if (recipe instanceof Keyed keyed) {
            logger.success("Added recipe with identifier ".concat(Text.pretty(keyed.getKey())));
          } else {
            logger.success("Added recipe from ".concat(Text.getPrettyClassName(part.getClass())).concat("."));
          }
        }
      } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
        throw new RuntimeException("Failed to add recipe", exception);
      }
    }
  }

  private void handleIncludeForSmoker(AddRecipe addRecipe, CreazyPlugin plugin, Recipe recipe, PartOptions options) {
    if (addRecipe.includeForSmoker() && recipe instanceof FurnaceRecipe furnaceRecipe) {
      var key = new NamespacedKey(plugin, furnaceRecipe.getKey().getKey().concat("_for_smoker"));
      var smokerRecipe = new SmokingRecipe(
          key,
          furnaceRecipe.getResult(),
          furnaceRecipe.getInputChoice(),
          furnaceRecipe.getExperience(),
          furnaceRecipe.getCookingTime() / addRecipe.divideTimeForSmoker()
      );
      plugin.getServer().addRecipe(smokerRecipe);
      if (options.shouldLog()) {
        logger.info("Added smoker recipe created from furnace recipe.");
      }
    }
  }

  private void handleIncludeForBlastFurnace(AddRecipe addRecipe, CreazyPlugin plugin, Recipe recipe, PartOptions options) {
    if (addRecipe.includeForBlastFurnace() && recipe instanceof FurnaceRecipe furnaceRecipe) {
      var key = new NamespacedKey(plugin, furnaceRecipe.getKey().getKey().concat("_for_blast_furnace"));
      var blastFurnaceRecipe = new BlastingRecipe(
          key,
          furnaceRecipe.getResult(),
          furnaceRecipe.getInputChoice(),
          furnaceRecipe.getExperience(),
          furnaceRecipe.getCookingTime() / addRecipe.divideTimeForBlastFurnace()
      );
      plugin.getServer().addRecipe(blastFurnaceRecipe);
      if (options.shouldLog()) {
        logger.info("Added blast furnace recipe created from furnace recipe.");
      }
    }
  }
}
