package pl.creazy.creazylib.event.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import pl.creazy.creazylib.screen.menu.Menu;

@Getter
@AllArgsConstructor
public class MenuEvent extends Event {
  private static final HandlerList HANDLERS = new HandlerList();

  private final Menu menu;

  @Override
  @NotNull
  public HandlerList getHandlers() {
    return HANDLERS;
  }

  public static HandlerList getHandlerList() {
    return HANDLERS;
  }
}

