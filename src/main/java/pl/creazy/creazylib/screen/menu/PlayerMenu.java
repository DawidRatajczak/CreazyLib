package pl.creazy.creazylib.screen.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.jetbrains.annotations.NotNull;
import pl.creazy.creazylib.CreazyLib;
import pl.creazy.creazylib.util.text.Text;

import java.util.Objects;

public interface PlayerMenu {
  static void open(@NotNull Class<? extends PlayerMenu> type, @NotNull Player player) {
    open(type, player, 0);
  }

  static void open(@NotNull Class<? extends PlayerMenu> type, @NotNull Player owner, int pageIndex) {
    var menu = (PlayerMenu) CreazyLib.request().getPartManager().getPart(type);
    Objects.requireNonNull(menu).open(owner, pageIndex);
  }

  default void open(@NotNull Player owner) {
    open(owner, 0);
  }

  default @NotNull String getTitle(int pageIndex, @NotNull Player player, int size) {
    return "Menu";
  }

  default void open(@NotNull Player owner, int pageIndex) {
    var page = getPage(pageIndex, owner);
    var title = Text.color(getTitle(pageIndex, owner, page.getSize()));
    var inventory = Bukkit.createInventory(new MenuHolder(this, page, owner), page.getSize(), title);
    page.setContent(inventory);
    owner.openInventory(inventory);
  }

  default void onClose(@NotNull InventoryCloseEvent event) {
  }

  default void onOpen(@NotNull InventoryOpenEvent event) {
  }

  @NotNull MenuPage getPage(int pageIndex, @NotNull Player owner);
}
