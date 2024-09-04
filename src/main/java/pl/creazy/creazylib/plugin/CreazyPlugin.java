package pl.creazy.creazylib.plugin;

import static java.lang.String.format;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import pl.creazy.creazylib.CreazyLib;
import pl.creazy.creazylib.part.OnDisableInvoker;
import pl.creazy.creazylib.part.PartLoader;
import pl.creazy.creazylib.part.constraints.Part;

public abstract class CreazyPlugin extends JavaPlugin {
  @NotNull
  public JarFile getJarFile() {
    try {
      return new JarFile(getFile());
    } catch (IOException exception) {
      throw new RuntimeException(format("Failed to get jar file of plugin %s", getName()), exception);
    }
  }

  @NotNull
  public List<Class<?>> getPartTypes() {
    var classes = new ArrayList<Class<?>>();
    var entries = getJarFile().entries();
    var entry = entries.nextElement();

    while (entries.hasMoreElements()) {
      var name = entry.getName();
      if (name.endsWith(".class") && !name.contains("module-info")) {
        try {
          var className = name.replace("/", ".").substring(0, name.length() - 6);
          if (className.startsWith(getClass().getPackageName())) {
            var type = Class.forName(className);

            if (type.isAnnotationPresent(Part.class)) {
              classes.add(type);
            }
          }
        } catch (ClassNotFoundException exception) {
          throw new RuntimeException(format("Failed to get classes of plugin %s", getName()), exception);
        }
      }
      entry = entries.nextElement();
    }
    return classes;
  }

  @Override
  public final void onEnable() {
    var partManager = CreazyLib.getPlugin(CreazyLib.class).getPartManager();
    var loader = new PartLoader(partManager);
    loader.loadParts(this);
  }

  @Override 
  public final void onDisable() {
    var partManager = CreazyLib.getPlugin(CreazyLib.class).getPartManager();
    var onDisableInvoker = new OnDisableInvoker(partManager);
    partManager.getParts(this).forEach(onDisableInvoker::invokeAllMethods);
  }
}
