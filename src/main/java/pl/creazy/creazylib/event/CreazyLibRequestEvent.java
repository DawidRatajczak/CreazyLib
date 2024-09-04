package pl.creazy.creazylib.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import pl.creazy.creazylib.CreazyLib;

@Getter
@Setter
public class CreazyLibRequestEvent extends Event {
  private static final HandlerList HANDLERS = new HandlerList();

  private CreazyLib plugin;

  @Override
  @NotNull
  public HandlerList getHandlers() {
    return HANDLERS;
  }

  public static HandlerList getHandlerList() {
    return HANDLERS;
  }
}
