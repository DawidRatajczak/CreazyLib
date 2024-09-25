package pl.creazy.creazylib.item.action;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.creazy.creazylib.action.ActionManagerBase;
import pl.creazy.creazylib.id.Id;
import pl.creazy.creazylib.manager.constraints.Manager;

import java.util.HashMap;
import java.util.Map;

@Manager
public class ItemActionManager extends ActionManagerBase<ItemAction<?>> {
  private final Map<Id, ItemClickAction> clickActions = new HashMap<>();
  private final Map<Id, ItemClickAtEntityAction> clickAtEntityActions = new HashMap<>();
  private final Map<Id, ItemEntityDeathAction> entityDeathActions = new HashMap<>();
  private final Map<Id, ItemEntityDamageAction> entityDamageActions = new HashMap<>();
  private final Map<Id, ItemBlockBreakAction> blockBreakActions = new HashMap<>();

  public void registerItemAction(@NotNull ItemAction<?> itemAction) {
    if (itemAction instanceof ItemClickAction clickAction) {
      registerAction(clickAction, clickActions);
    }
    if (itemAction instanceof ItemClickAtEntityAction clickAtEntityAction) {
      registerAction(clickAtEntityAction, clickAtEntityActions);
    }
    if (itemAction instanceof ItemEntityDeathAction entityDeathAction) {
      registerAction(entityDeathAction, entityDeathActions);
    }
    if (itemAction instanceof ItemEntityDamageAction entityDamageAction) {
      registerAction(entityDamageAction, entityDamageActions);
    }
    if (itemAction instanceof ItemBlockBreakAction blockBreakAction) {
      registerAction(blockBreakAction, blockBreakActions);
    }
  }

  @Nullable
  public ItemBlockBreakAction getItemBlockBreakAction(@Nullable Id id) {
    return getAction(id, blockBreakActions);
  }

  @Nullable
  public ItemEntityDamageAction getItemEntityDamageAction(@Nullable Id id) {
    return getAction(id, entityDamageActions);
  }

  @Nullable
  public ItemEntityDeathAction getItemEntityDeathAction(@Nullable Id id) {
    return getAction(id, entityDeathActions);
  }

  @Nullable
  public ItemClickAction getItemClickAction(@Nullable Id id) {
    return getAction(id, clickActions);
  }

  @Nullable
  public ItemClickAtEntityAction getItemClickAtEntityAction(@Nullable Id id) {
    return getAction(id, clickAtEntityActions);
  }
}
