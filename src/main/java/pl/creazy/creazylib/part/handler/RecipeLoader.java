package pl.creazy.creazylib.part.handler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.inventory.Recipe;

import pl.creazy.creazylib.part.PartCreateHandler;
import pl.creazy.creazylib.part.PartManager;
import pl.creazy.creazylib.part.constraints.Part;
import pl.creazy.creazylib.plugin.CreazyPlugin;
import pl.creazy.creazylib.recipe.constraints.AddRecipe;
import pl.creazy.creazylib.recipe.constraints.Recipes;

@Part
class RecipeLoader implements PartCreateHandler {
  @Override
  public void onPartCreate(Object part, PartManager partManager, CreazyPlugin plugin) {
    if (!part.getClass().isAnnotationPresent(Recipes.class)) {
      return;
    }
    for (Method method : part.getClass().getDeclaredMethods()) {
      if (method.getAnnotation(AddRecipe.class) == null) {
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
        plugin.getServer().addRecipe(recipe);
      } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
        throw new RuntimeException("Failed to add recipe", exception);
      }
    }
  }
}
