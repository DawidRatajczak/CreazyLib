package pl.creazy.creazylib.screen.menu;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public abstract class MenuPage implements MenuContentSetter {
  private final ItemStack[] content = new ItemStack[getSize()];

  @Override
  public void setContent(@NotNull Inventory inventory) {
    setContent();
    inventory.setContents(content);
  }

  public abstract void onClick(@NotNull InventoryClickEvent event);

  protected abstract void setContent();

  protected int getSize() {
    return 54;
  }

  protected void setIcon(@NotNull ItemStack icon, int index) {
    content[index] = icon;
  }
}
