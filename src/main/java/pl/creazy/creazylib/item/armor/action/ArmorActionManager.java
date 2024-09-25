package pl.creazy.creazylib.item.armor.action;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.creazy.creazylib.action.ActionManagerBase;
import pl.creazy.creazylib.id.Id;
import pl.creazy.creazylib.manager.constraints.Manager;

import java.util.HashMap;
import java.util.Map;

@Manager
public class ArmorActionManager extends ActionManagerBase<ArmorAction> {
  private final Map<Id, ArmorOnAction> armorOnActions = new HashMap<>();

  public @Nullable ArmorOnAction getArmorOnAction(@Nullable Id id) {
    return getAction(id, armorOnActions);
  }

  public void registerAction(@NotNull ArmorAction armorAction) {
    if (armorAction instanceof ArmorOnAction action) {
      registerAction(action, armorOnActions);
    }
  }
}
