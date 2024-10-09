package pl.creazy.creazylib.part.handler;

import pl.creazy.creazylib.command.PlayerCommandWrapper;
import pl.creazy.creazylib.command.constraints.Command;
import pl.creazy.creazylib.log.Logger;
import pl.creazy.creazylib.part.PartCreateHandler;
import pl.creazy.creazylib.part.PartManager;
import pl.creazy.creazylib.part.PartOptions;
import pl.creazy.creazylib.part.constraints.Handler;
import pl.creazy.creazylib.part.constraints.Injected;
import pl.creazy.creazylib.plugin.CreazyPlugin;
import pl.creazy.creazylib.util.text.Text;

import java.util.Objects;

import static java.lang.String.format;

@Handler
class CommandLoader implements PartCreateHandler {
  @Injected
  private Logger logger;

  @Override
  public void onPartCreate(Object part, PartManager partManager, CreazyPlugin plugin, PartOptions options) {
    var commandData = part.getClass().getAnnotation(Command.class);
    if (commandData != null) {
      var wrapper = new PlayerCommandWrapper(part);
      var command = plugin.getCommand(commandData.value());
      if (command == null) {
        logger.error(Text.create(
            "Failed to get command ", commandData.value(),
            ". Add your command to plugin.yml file in your project."));
        return;
      }
      command.setExecutor(wrapper);
      command.setTabCompleter(wrapper);
      if (options.shouldLog()) {
        logger.success(format("Registered command %s.", commandData.value()));
      }
    }
  }
}
