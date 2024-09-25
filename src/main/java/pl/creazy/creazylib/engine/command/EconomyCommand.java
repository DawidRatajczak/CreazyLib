package pl.creazy.creazylib.engine.command;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.creazy.creazylib.command.constraints.Args;
import pl.creazy.creazylib.command.constraints.Command;
import pl.creazy.creazylib.command.constraints.HasPermissions;
import pl.creazy.creazylib.economy.EconomyManager;
import pl.creazy.creazylib.part.constraints.Injected;
import pl.creazy.creazylib.part.constraints.Part;

@Command("economy")
class EconomyCommand {
  @Injected
  private EconomyManager economyManager;

  @Args("balance get ?s")
  @HasPermissions("creazylib.economy.balance.get")
  void getPlayerBalance(@NotNull Player sender, @NotNull String playerName) {
    economyManager.getBalanceMessage(playerName).sendChat(sender);
  }

  @Args("balance get ?s ?s")
  @HasPermissions("creazylib.economy.balance.get")
  void getPlayerBalance(@NotNull Player sender, @NotNull String playerName, @NotNull String value) {
    economyManager.getBalanceMessage(playerName, () -> value).sendChat(sender);
  }

  @Args("balance set ?i ?s ?s")
  @HasPermissions("creazylib.economy.balance.set")
  void setPlayerBalance(@NotNull Player sender, int balance, @NotNull String playerName, @NotNull String value) {
    economyManager.setBalanceMessage(playerName, () -> value, balance).sendChat(sender);
  }

  @Args("balance set ?i ?s")
  @HasPermissions("creazylib.economy.balance.set")
  void setPlayerBalance(@NotNull Player sender, int balance, @NotNull String playerName) {
    economyManager.setBalanceMessage(playerName, balance).sendChat(sender);
  }
}
