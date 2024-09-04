package pl.creazy.creazylib.action;

import org.bukkit.event.Event;
import pl.creazy.creazylib.id.Identifiable;

public interface Action<T extends Event> extends Identifiable {
  void handle(T event);
}
