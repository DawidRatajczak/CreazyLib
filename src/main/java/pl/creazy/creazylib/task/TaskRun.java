package pl.creazy.creazylib.task;

import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import pl.creazy.creazylib.plugin.CreazyPlugin;

public enum TaskRun {
  LATER,
  TIMER,
  RUN,
  LATER_ASYNC,
  TIMER_ASYNC,
  RUN_ASYNC;

  public void run(@NotNull BukkitRunnable runnable, @NotNull CreazyPlugin plugin, long delay, long period) {
    switch (this) {
      case LATER -> runnable.runTaskLater(plugin, delay);
      case TIMER -> runnable.runTaskTimer(plugin, delay, period);
      case RUN -> runnable.runTask(plugin);
      case LATER_ASYNC -> runnable.runTaskLaterAsynchronously(plugin, delay);
      case TIMER_ASYNC -> runnable.runTaskTimerAsynchronously(plugin, delay, period);
      case RUN_ASYNC -> runnable.runTaskAsynchronously(plugin);
    }
  }
}
