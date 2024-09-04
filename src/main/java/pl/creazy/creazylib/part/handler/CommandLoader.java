package pl.creazy.creazylib.part.handler;

import static java.lang.String.format;

import org.bukkit.command.TabCompleter;

import pl.creazy.creazylib.command.PlayerCommandWrapper;
import pl.creazy.creazylib.command.constraints.Command;
import pl.creazy.creazylib.log.Logger;
import pl.creazy.creazylib.part.PartCreateHandler;
import pl.creazy.creazylib.part.PartManager;
import pl.creazy.creazylib.part.constraints.Part;
import pl.creazy.creazylib.plugin.CreazyPlugin;

import java.util.Objects;

@Part
class CommandLoader implements PartCreateHandler {
  @Part
  private Logger logger;

  @Override
  public void onPartCreate(Object part, PartManager partManager, CreazyPlugin plugin) {
    var commandData = part.getClass().getAnnotation(Command.class);
    if (commandData != null) {
      var command = new PlayerCommandWrapper(part);
      Objects.requireNonNull(plugin.getCommand(commandData.value())).setExecutor(command);
      Objects.requireNonNull(plugin.getCommand(commandData.value())).setTabCompleter(command);
      logger.success(format("Registered command: %s.", commandData.value()));
    }
  }
}
