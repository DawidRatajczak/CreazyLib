package pl.creazy.creazylib.part.handler;

import lombok.SneakyThrows;
import pl.creazy.creazylib.data.persistence.config.Config;
import pl.creazy.creazylib.data.persistence.config.constraints.ConfigFile;
import pl.creazy.creazylib.data.persistence.config.constraints.ConfigVar;
import pl.creazy.creazylib.part.PartCreateHandler;
import pl.creazy.creazylib.part.PartManager;
import pl.creazy.creazylib.plugin.CreazyPlugin;

import java.lang.reflect.Field;

public class ConfigFileLoader implements PartCreateHandler {
  @Override
  @SneakyThrows
  public void onPartCreate(Object part, PartManager partManager, CreazyPlugin plugin) {
    var configFile = part.getClass().getAnnotation(ConfigFile.class);
    if (configFile == null) {
      return;
    }
    var config = Config.getConfig(configFile.name(), configFile.path(), plugin);
    for (Field field : part.getClass().getDeclaredFields()) {
      var configVar = field.getAnnotation(ConfigVar.class);
      if (configVar == null) {
        continue;
      }
      var path = configVar.value();
      field.setAccessible(true);
      var value = config.getObject(path, field.getType(), null);
      if (value == null) {
        config.set(path, field.get(part));
      } else {
        field.set(part, value);
      }
    }
    config.save();
  }
}
