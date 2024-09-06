package pl.creazy.creazylib.item.food;

import lombok.AllArgsConstructor;
import org.bukkit.inventory.meta.components.FoodComponent;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@SuppressWarnings("UnstableApiUsage")
@AllArgsConstructor
public class FoodEffect implements FoodComponent.FoodEffect {
  private PotionEffect effect;

  private float probability;

  public FoodEffect(@NotNull Map<String, Object> data) {
    effect = (PotionEffect) data.get("effect");
    probability = (float) data.get("probability");
  }

  @Override
  public @NotNull PotionEffect getEffect() {
    return effect;
  }

  @Override
  public void setEffect(@NotNull PotionEffect potionEffect) {
    effect = potionEffect;
  }

  @Override
  public float getProbability() {
    return probability;
  }

  @Override
  public void setProbability(float v) {
    probability = v;
  }

  @Override
  public @NotNull Map<String, Object> serialize() {
    return Map.of(
        "effect", effect,
        "probability", probability
    );
  }
}
