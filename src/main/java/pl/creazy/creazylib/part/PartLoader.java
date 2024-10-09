package pl.creazy.creazylib.part;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import pl.creazy.creazylib.part.handler.ConfigFileLoader;
import pl.creazy.creazylib.plugin.CreazyPlugin;
import pl.creazy.creazylib.plugin.constraints.Plugin;

import java.util.LinkedList;
import java.util.stream.Collectors;

import static java.lang.String.format;

@AllArgsConstructor
public class PartLoader {
  private final PartManager partManager;

  public void loadParts(@NotNull CreazyPlugin plugin) {
    plugin.getLogger().info(format("Loading %s", plugin.getName()));

    var creator = new PartCreator();
    var connector = new PartConnector(partManager);
    var onEnableInvoker = new OnEnableInvoker(partManager);
    var configFileLoader = new ConfigFileLoader();
    var createdParts = plugin.getPartTypes().stream()
        .map(creator::createPart)
        .collect(Collectors.toCollection(LinkedList::new));

    if (plugin.getClass().isAnnotationPresent(Plugin.class)) {
      createdParts.addFirst(plugin);
    }
    createdParts.forEach(partManager::addPart);
    connector.connectParts(createdParts);
    createdParts.forEach(part -> {
      var options = new PartOptions(part.getClass());
      configFileLoader.onPartCreate(part, partManager, plugin, options);
    });
    createdParts.forEach(onEnableInvoker::invokeAllMethods);

    partManager.getPartCreateHandlers().forEach(handler -> {
      createdParts.forEach(part -> {
        var options = new PartOptions(part.getClass());
        handler.onPartCreate(part, partManager, plugin, options);
      });
    });
    ;

    plugin.getLogger().info(format("Loaded %s", plugin.getName()));
  }
}
