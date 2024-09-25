package pl.creazy.creazylib.part.handler;

import org.bukkit.inventory.ItemStack;
import pl.creazy.creazylib.item.ItemManager;
import pl.creazy.creazylib.item.constraints.AddItem;
import pl.creazy.creazylib.item.constraints.Items;
import pl.creazy.creazylib.log.Logger;
import pl.creazy.creazylib.part.PartCreateHandler;
import pl.creazy.creazylib.part.PartManager;
import pl.creazy.creazylib.part.constraints.Handler;
import pl.creazy.creazylib.part.constraints.Injected;
import pl.creazy.creazylib.plugin.CreazyPlugin;

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
  public void onPartCreate(Object part, PartManager partManager, CreazyPlugin plugin) {
    if (!part.getClass().isAnnotationPresent(Items.class)) {
      return;
    }
    for (Field field : part.getClass().getDeclaredFields()) {
      var addItem = field.getAnnotation(AddItem.class);
      if (addItem != null) {
        try {
          field.setAccessible(true);
          var item = (ItemStack) field.get(part);
          if (addItem.value().isEmpty()) {
            itemManager.addItem(field.getName(), item);
            logger.info("Added item: ".concat(field.getName()));
          } else {
            itemManager.addItem(addItem.value(), item);
            logger.info("Added item: ".concat(addItem.value()));
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
          if (addItem.value().isEmpty()) {
            itemManager.addItem(method.getName(), item);
            logger.info("Added item: ".concat(method.getName()));
          } else {
            itemManager.addItem(addItem.value(), item);
            logger.info("Added item: ".concat(addItem.value()));
          }
        } catch (InvocationTargetException | IllegalAccessException exception) {
          throw new RuntimeException(
              format("Failed to add method item: %s", addItem.value().isEmpty() ? method.getName() : addItem.value()));
        }
      }
    }
  }
}
