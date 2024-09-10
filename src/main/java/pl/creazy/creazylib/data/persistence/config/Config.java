package pl.creazy.creazylib.data.persistence.config;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import pl.creazy.creazylib.plugin.CreazyPlugin;

public abstract class Config {
  public static final String DEFAULT_PATH = "DATA_FOLDER";

  @NotNull
  public static Config getConfig(@NotNull String name, @NotNull String path, @NotNull CreazyPlugin plugin) {
    if (path.equals(DEFAULT_PATH)) {
      return new ConfigFile(name, plugin).reload();
    }
    return new ConfigFile(name, path, plugin).reload();
  }

  @NotNull
  public static Config getConfig(@NotNull String name, @NotNull CreazyPlugin plugin) {
    return new ConfigFile(name, plugin).reload();
  }

  protected abstract YamlConfiguration getYamlConfig();

  public abstract void save();

  public abstract boolean isEmpty();

  public void addDefault(String path, Object value) {
    getYamlConfig().addDefault(path, value);
  }

  public boolean contains(String path) {
    return getYamlConfig().contains(path);
  }

  public boolean contains(String path, boolean ignoreDefaults) {
    return getYamlConfig().contains(path, ignoreDefaults);
  }

  public ConfigurationSection createSection(String path) {
    return getYamlConfig().createSection(path);
  }

  public ConfigurationSection createSection(String path, Map<?, ?> map) {
    return getYamlConfig().createSection(path, map);
  }

  public Object getConfig(String path) {
    return getYamlConfig().get(path);
  }

  public Object getConfig(String path, Object def) {
    return getYamlConfig().get(path, def);
  }

  public boolean getBoolean(String path) {
    return getYamlConfig().getBoolean(path);
  }

  public boolean getBoolean(String path, boolean def) {
    return getYamlConfig().getBoolean(path, def);
  }

  public List<Boolean> getBooleanList(String path) {
    return getYamlConfig().getBooleanList(path);
  }

  public List<Byte> getByteList(String path) {
    return getYamlConfig().getByteList(path);
  }

  public List<Character> getCharacterList(String path) {
    return getYamlConfig().getCharacterList(path);
  }

  public Color getColor(String path) {
    return getYamlConfig().getColor(path);
  }

  public Color getColor(String path, Color def) {
    return getYamlConfig().getColor(path, def);
  }

  public List<String> getComments(String path) {
    return getYamlConfig().getComments(path);
  }

  public ConfigurationSection getConfigurationSection(String path) {
    return getYamlConfig().getConfigurationSection(path);
  }

  public String getCurrentPath() {
    return getYamlConfig().getCurrentPath();
  }

  public ConfigurationSection getDefaultSection() {
    return getYamlConfig().getDefaultSection();
  }

  public double getDouble(String path) {
    return getYamlConfig().getDouble(path);
  }

  public double getDouble(String path, double def) {
    return getYamlConfig().getDouble(path, def);
  }

  public List<Double> getDoubleList(String path) {
    return getYamlConfig().getDoubleList(path);
  }

  public List<Float> getFloatList(String path) {
    return getYamlConfig().getFloatList(path);
  }

  public List<String> getInlineComments(String path) {
    return getYamlConfig().getInlineComments(path);
  }

  public int getInt(String path) {
    return getYamlConfig().getInt(path);
  }

  public int getInt(String path, int def) {
    return getYamlConfig().getInt(path, def);
  }

  public List<Integer> getIntegerList(String path) {
    return getYamlConfig().getIntegerList(path);
  }

  public ItemStack getItemStack(String path) {
    return getYamlConfig().getItemStack(path);
  }

  public ItemStack getItemStack(String path, ItemStack def) {
    return getYamlConfig().getItemStack(path, def);
  }

  public Set<String> getKeys() {
    return getKeys(false);
  }

  public Set<String> getKeys(boolean deep) {
    return getYamlConfig().getKeys(deep);
  }

  public List<?> getList(String path) {
    return getYamlConfig().getList(path);
  }

  public List<?> getList(String path, List<?> def) {
    return getYamlConfig().getList(path, def);
  }

  public Location getLocation(String path) {
    return getYamlConfig().getLocation(path);
  }

  public Location getLocation(String path, Location def) {
    return getYamlConfig().getLocation(path, def);
  }

  public long getLong(String path) {
    return getYamlConfig().getLong(path);
  }

  public long getLong(String path, long def) {
    return getYamlConfig().getLong(path, def);
  }

  public List<Long> getLongList(String path) {
    return getYamlConfig().getLongList(path);
  }

  public List<Map<?, ?>> getMapList(String path) {
    return getYamlConfig().getMapList(path);
  }

  public String getName() {
    return getYamlConfig().getName();
  }

  public <T> T getObject(String path, Class<T> type) {
    return getYamlConfig().getObject(path, type);
  }

  public <T> T getObject(String path, Class<T> type, T def) {
    return getYamlConfig().getObject(path, type, def);
  }

  public OfflinePlayer getOfflinePlayer(String path) {
    return getYamlConfig().getOfflinePlayer(path);
  }

  public OfflinePlayer getOfflinePlayer(String path, OfflinePlayer def) {
    return getYamlConfig().getOfflinePlayer(path, def);
  }

  public ConfigurationSection getParent() {
    return getYamlConfig().getParent();
  }

  public Configuration getRoot() {
    return getYamlConfig().getRoot();
  }

  public <T extends ConfigurationSerializable> T getSerializable(String path, Class<T> type) {
    return getYamlConfig().getSerializable(path, type);
  }

  public <T extends ConfigurationSerializable> T getSerializable(String path, Class<T> type, T def) {
    return getYamlConfig().getSerializable(path, type, def);
  }

  public List<Short> getShortList(String path) {
    return getYamlConfig().getShortList(path);
  }

  public String getString(String path) {
    return getYamlConfig().getString(path);
  }

  public String getString(String path, String def) {
    return getYamlConfig().getString(path, def);
  }

  public List<String> getStringList(String path) {
    return getYamlConfig().getStringList(path);
  }

  public Map<String, Object> getValues() {
    return getYamlConfig().getValues(false);
  }

  public Map<String, Object> getValues(boolean deep) {
    return getYamlConfig().getValues(deep);
  }

  public Vector getVector(String path) {
    return getYamlConfig().getVector(path);
  }

  public Vector getVector(String path, Vector def) {
    return getYamlConfig().getVector(path, def);
  }

  public Config set(String path, Object value) {
    getYamlConfig().set(path, value);
    return this;
  }

  public Config setIfNull(String path, Object value) {
    if (getYamlConfig().get(path, null) == null) {
      return set(path, value);
    }
    return this;
  }

  public void setComments(String path, List<String> comments) {
    getYamlConfig().setComments(path, comments);
  }

  public void setInlineComments(String path, List<String> comments) {
    getYamlConfig().setInlineComments(path, comments);
  }

  public Object get(String path) {
    return getYamlConfig().get(path);
  }

  public <T> T getCast(String path, Class<T> type) {
    return type.cast(get(path));
  }

  public Object get(String path, Object def) {
    return getYamlConfig().get(path, def);
  }
}
