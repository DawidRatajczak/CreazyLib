package pl.creazy.creazylib.engine.command;

import static java.lang.String.format;
import static pl.creazy.creazylib.util.message.Message.sendChat;

import java.util.List;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import pl.creazy.creazylib.command.TabCompleteHandler;
import pl.creazy.creazylib.command.constraints.Args;
import pl.creazy.creazylib.command.constraints.Command;
import pl.creazy.creazylib.command.constraints.HasPermissions;
import pl.creazy.creazylib.item.ItemManager;
import pl.creazy.creazylib.part.constraints.Injected;
import pl.creazy.creazylib.part.constraints.Part;

@Command("item")
class ItemCommand implements TabCompleteHandler {
  @Injected
  private ItemManager itemManager;

  @HasPermissions("creazylib.item.get")
  @Args("get ?s")
  void getItem(Player player, String itemName) {
    var item = itemManager.getItem(itemName);

    if (item == null) {
      sendChat(player, format("&aNie ma takiego przedmiotu: %s", itemName));
      return;
    }

    player.getInventory().addItem(item);
  }

  @Override
  @Nullable
  public List<String> handleTabComplete(@NotNull Player player, @NotNull String[] args) {
    if (args.length != 2) {
      return null;
    }
    return itemManager.names().stream().filter(name -> name.startsWith(args[1])).toList();
  }
}
