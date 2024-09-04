package pl.creazy.creazylib.part.handler;

import static java.lang.String.format;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.inventory.ItemStack;

import pl.creazy.creazylib.item.ItemManager;
import pl.creazy.creazylib.item.constraints.AddItem;
import pl.creazy.creazylib.item.constraints.Items;
import pl.creazy.creazylib.part.PartCreateHandler;
import pl.creazy.creazylib.part.PartManager;
import pl.creazy.creazylib.part.constraints.Part;
import pl.creazy.creazylib.plugin.CreazyPlugin;

@Part
public class ItemLoader implements PartCreateHandler {
  @Part
  private ItemManager itemManager;

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
          } else {
            itemManager.addItem(addItem.value(), item);
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
          } else {
            itemManager.addItem(addItem.value(), item);
          }
        } catch (InvocationTargetException | IllegalAccessException exception) {
          throw new RuntimeException(
                  format("Failed to add method item: %s", addItem.value().isEmpty() ? method.getName() : addItem.value()));
        }
      }
    }
  }
}
