package pl.creazy.creazylib.part.handler;

import org.bukkit.scheduler.BukkitRunnable;
import pl.creazy.creazylib.part.PartCreateHandler;
import pl.creazy.creazylib.part.PartManager;
import pl.creazy.creazylib.part.PartOptions;
import pl.creazy.creazylib.part.constraints.Handler;
import pl.creazy.creazylib.plugin.CreazyPlugin;
import pl.creazy.creazylib.task.constraints.Task;

@Handler
class BukkitRunnableRunner implements PartCreateHandler {
  @Override
  public void onPartCreate(Object part, PartManager partManager, CreazyPlugin plugin, PartOptions options) {
    var task = part.getClass().getAnnotation(Task.class);
    if (task == null) {
      return;
    }
    if (!(part instanceof BukkitRunnable runnable)) {
      return;
    }
    task.run().run(runnable, plugin, task.delay(), task.period());
  }
}
