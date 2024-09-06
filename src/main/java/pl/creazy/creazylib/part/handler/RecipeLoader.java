package pl.creazy.creazylib.part.handler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.BlastingRecipe;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.Recipe;

import org.bukkit.inventory.SmokingRecipe;
import pl.creazy.creazylib.log.Logger;
import pl.creazy.creazylib.part.PartCreateHandler;
import pl.creazy.creazylib.part.PartManager;
import pl.creazy.creazylib.part.constraints.Part;
import pl.creazy.creazylib.plugin.CreazyPlugin;
import pl.creazy.creazylib.recipe.constraints.AddRecipe;
import pl.creazy.creazylib.recipe.constraints.Recipes;

@Part
class RecipeLoader implements PartCreateHandler {
  @Part
  private Logger logger;

  @Override
  public void onPartCreate(Object part, PartManager partManager, CreazyPlugin plugin) {
    if (!part.getClass().isAnnotationPresent(Recipes.class)) {
      return;
    }
    for (Method method : part.getClass().getDeclaredMethods()) {
      var addRecipe = method.getAnnotation(AddRecipe.class);
      if (addRecipe == null) {
        continue;
      }
      if (!Recipe.class.isAssignableFrom(method.getReturnType())) {
        plugin.getLogger().info("not recipe");
        continue;
      }
      if (method.getParameterCount() != 0) {
        continue;
      }
      try {
        method.setAccessible(true);
        var recipe = (Recipe) method.invoke(part);
        handleIncludeForSmoker(addRecipe, plugin, recipe);
        handleIncludeForBlastFurnace(addRecipe, plugin, recipe);
        plugin.getServer().addRecipe(recipe);
        logger.info("Added recipe.");
      } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
        throw new RuntimeException("Failed to add recipe", exception);
      }
    }
  }

  private void handleIncludeForSmoker(AddRecipe addRecipe, CreazyPlugin plugin, Recipe recipe) {
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
      logger.info("Added smoker recipe created from furnace recipe.");
    }
  }

  private void handleIncludeForBlastFurnace(AddRecipe addRecipe, CreazyPlugin plugin, Recipe recipe) {
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
      logger.info("Added blast furnace recipe created from furnace recipe.");
    }
  }
}
