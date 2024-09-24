package pl.creazy.creazylib.data.persistence.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import pl.creazy.creazylib.plugin.CreazyPlugin;
import pl.creazy.creazylib.util.text.Text;

class ConfigFile extends Config {
  private YamlConfiguration yamlConfig;
  private final File file;

  ConfigFile(@NotNull String name, @NotNull String path, @NotNull CreazyPlugin plugin) {
    final String finalPath = Text.path(plugin.getDataFolder().getPath().concat("/").concat(path));
    file = new File(finalPath, name.concat(".yml"));
  }

  ConfigFile(@NotNull File file) {
    this.file = file;
  }

  ConfigFile(@NotNull String name, @NotNull CreazyPlugin plugin) {
    file = new File(plugin.getDataFolder().getPath(), name.concat(".yml"));
  }

  @Override
  protected YamlConfiguration getYamlConfig() {
    return yamlConfig;
  }

  @Override
  public void save() {
    try {
      yamlConfig.save(file);
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }

  @Override
  public boolean isEmpty() {
    return file.length() == 0;
  }
  
  @SuppressWarnings("ResultOfMethodCallIgnored")
  public ConfigFile reload() {
    if (!file.exists()) {
      final File parent = file.getParentFile();

      if (!parent.exists()) {
        parent.mkdirs();
      }

      try {
        file.createNewFile();
      } catch (IOException exception) {
        throw new RuntimeException("Failed to create config file", exception);
      }
    }

    yamlConfig = YamlConfiguration.loadConfiguration(file);
    return this;
  }
}
