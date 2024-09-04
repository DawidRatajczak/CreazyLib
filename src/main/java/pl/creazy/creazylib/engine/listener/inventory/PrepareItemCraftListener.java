package pl.creazy.creazylib.engine.listener.inventory;

import org.bukkit.Keyed;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;

import pl.creazy.creazylib.part.constraints.Part;
import pl.creazy.creazylib.recipe.RecipeManager;

@Part
class PrepareItemCraftListener implements Listener {
  @Part
  private RecipeManager recipeManager;

  @EventHandler
  void onEvent(PrepareItemCraftEvent event) {
    if (!(event.getInventory().getHolder() instanceof Player player)) {
      return;
    }
    if (!(event.getRecipe() instanceof Keyed keyed)) {
      return;
    }
    recipeManager.getRecipeCraftHandler(keyed.getKey()).ifPresent(handler -> {
      if (!handler.canBeCrafted(player, event.getInventory().getMatrix(), event.getInventory().getResult())) {
        event.getInventory().setResult(null);
      }
    });
  }
}
