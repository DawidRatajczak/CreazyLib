package pl.creazy.creazylib.engine.command;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.creazy.creazylib.command.constraints.Args;
import pl.creazy.creazylib.command.constraints.Command;
import pl.creazy.creazylib.log.Logger;
import pl.creazy.creazylib.part.constraints.Part;
import pl.creazy.creazylib.util.mc.Mc;
import pl.creazy.creazylib.util.message.Message;

@Part
@Command("test")
class TestCommand {
  @Part
  private Logger logger;

  @Args("mc has")
  void testHas(Player player) {
    Message.sendChat(player, String.valueOf(Mc.hasItems(player, new ItemStack(Material.LEATHER), 5)));
  }

  @Args("mc add")
  void testAdd(Player player) {
    Message.sendChat(player, String.valueOf(Mc.addItems(player, new ItemStack(Material.DAMAGED_ANVIL, 8))));
  }

  @Args("mc remove")
  void testRemove(Player player) {
    Message.sendChat(player, String.valueOf(Mc.removeItems(player, new ItemStack(Material.APPLE), 8)));
  }
}

