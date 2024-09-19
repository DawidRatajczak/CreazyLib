package pl.creazy.creazylib.engine.command;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.creazy.creazylib.command.constraints.Args;
import pl.creazy.creazylib.command.constraints.Command;
import pl.creazy.creazylib.part.constraints.Part;
import pl.creazy.creazylib.util.message.Message;

@Part
@Command("test")
class TestCommand {
  @Args("block")
  void testBlock(@NotNull Player player) {
    Message.sendChat(player, player.getLocation().getBlock().getType().name());
  }
}

