package pl.creazy.creazylib.log;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.creazy.creazylib.CreazyLib;
import pl.creazy.creazylib.part.constraints.Part;

@Part
public class Logger {
  private CreazyLib plugin;
  private final List<LogType> disabledLogTypes = new ArrayList<>();

  public void disableLogType(@NotNull LogType type) {
    if (!disabledLogTypes.contains(type)) {
      disabledLogTypes.add(type);
    }
  }

  public void enableLogType(@NotNull LogType type) {
    disabledLogTypes.remove(type);
  }

  public void log(@Nullable Object message) {
    log(LogType.LOG, message);
  }

  public void test(@Nullable Object message) {
    log(LogType.TEST, message);
  }

  public void error(@Nullable Object message) {
    log(LogType.ERROR, message);
  }

  public void warning(@Nullable Object message) {
    log(LogType.WARNING, message);
  }

  public void info(@Nullable Object message) {
    log(LogType.INFO, message);
  }

  public void success(@Nullable Object message) {
    log(LogType.SUCCESS, message);
  }

  private void log(@NotNull LogType type, @Nullable Object message) {
    if (disabledLogTypes.contains(type)) {
      return;
    }
    plugin.getServer().getConsoleSender().sendMessage(format("[%s] %s", type.name(), String.valueOf(message)));
  }
}
