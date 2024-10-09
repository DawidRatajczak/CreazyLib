package pl.creazy.creazylib.part.handler;

import org.bukkit.inventory.ItemStack;
import pl.creazy.creazylib.item.ItemManager;
import pl.creazy.creazylib.item.constraints.AddItem;
import pl.creazy.creazylib.item.constraints.Items;
import pl.creazy.creazylib.log.Logger;
import pl.creazy.creazylib.part.PartCreateHandler;
import pl.creazy.creazylib.part.PartManager;
import pl.creazy.creazylib.part.PartOptions;
import pl.creazy.creazylib.part.constraints.Handler;
import pl.creazy.creazylib.part.constraints.Injected;
import pl.creazy.creazylib.plugin.CreazyPlugin;
import pl.creazy.creazylib.util.text.Text;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static java.lang.String.format;

@Handler
public class ItemLoader implements PartCreateHandler {
  @Injected
  private ItemManager itemManager;

  @Injected
  private Logger logger;

  @Override
  public void onPartCreate(Object part, PartManager partManager, CreazyPlugin plugin, PartOptions options) {
    if (!part.getClass().isAnnotationPresent(Items.class)) {
      return;
    }
    for (Field field : part.getClass().getDeclaredFields()) {
      var addItem = field.getAnnotation(AddItem.class);
      if (addItem != null) {
        try {
          field.setAccessible(true);
          var item = (ItemStack) field.get(part);
          var name = Text.orElse(addItem.value(), field.getName());
          itemManager.addItem(name, item);
          if (options.shouldLog()) {
            logger.success("Added item ".concat(name).concat("."));
          }
        } catch (IllegalAccessException | IllegalArgumentException exception) {
          throw new RuntimeException(
              format("Failed to add field item: %s", addItem.value().isEmpty() ? field.getName() : addItem.value()));
        }
      }
    }
    for (Method method : part.getClass().getDeclaredMethods()) {
      var addItem = method.getAnnotation(AddItem.class);
      if (addItem != null) {
        try {
          method.setAccessible(true);
          var item = (ItemStack) method.invoke(part);
          var name = Text.orElse(addItem.value(), method.getName());
          itemManager.addItem(name, item);
          if (options.shouldLog()) {
            logger.success("Added item ".concat(name).concat("."));
          }
        } catch (InvocationTargetException | IllegalAccessException exception) {
          throw new RuntimeException(
              format("Failed to add method item: %s", addItem.value().isEmpty() ? method.getName() : addItem.value()));
        }
      }
    }
  }
}
