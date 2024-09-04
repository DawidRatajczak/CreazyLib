package pl.creazy.creazylib.block.action;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.creazy.creazylib.action.ActionManagerBase;
import pl.creazy.creazylib.id.Id;
import pl.creazy.creazylib.part.constraints.Part;

import java.util.HashMap;
import java.util.Map;

@Part
public class BlockActionManager extends ActionManagerBase<BlockAction<?>> {
  private final Map<Id, BlockBreakAction> breakActions = new HashMap<>();
  private final Map<Id, BlockClickAction> clickActions = new HashMap<>();

  public void registerBlockAction(@NotNull BlockAction<?> blockAction) {
    if (blockAction instanceof BlockBreakAction breakAction) {
      registerAction(breakAction, breakActions);
    }
    if (blockAction instanceof BlockClickAction clickAction) {
      registerAction(clickAction, clickActions);
    }
  }

  public @Nullable BlockClickAction getBlockClickAction(@Nullable Id id) {
    return getAction(id, clickActions);
  }

  public @Nullable BlockBreakAction getBlockBreakAction(@Nullable Id id) {
    return getAction(id, breakActions);
  }
}
