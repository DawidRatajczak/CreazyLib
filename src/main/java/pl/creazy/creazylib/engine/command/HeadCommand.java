package pl.creazy.creazylib.engine.command;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.creazy.creazylib.command.constraints.Args;
import pl.creazy.creazylib.command.constraints.Command;
import pl.creazy.creazylib.command.constraints.HasPermissions;
import pl.creazy.creazylib.item.ItemBuilder;
import pl.creazy.creazylib.util.mc.Mc;

@Command("head")
class HeadCommand {
  @Args("?s")
  @HasPermissions("creazylib.head")
  void giveHead(Player sender, String texture) {
    var head = new ItemBuilder(Material.PLAYER_HEAD)
        .setHead(texture)
        .build();
    Mc.addItems(sender, head);
  }
}
