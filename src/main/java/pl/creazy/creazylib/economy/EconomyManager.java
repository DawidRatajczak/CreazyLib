package pl.creazy.creazylib.economy;

import lombok.extern.flogger.Flogger;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import pl.creazy.creazylib.CreazyLib;
import pl.creazy.creazylib.data.persistence.config.Config;
import pl.creazy.creazylib.engine.config.EconomyMessageConfig;
import pl.creazy.creazylib.log.Logger;
import pl.creazy.creazylib.part.constraints.OnDisable;
import pl.creazy.creazylib.part.constraints.OnEnable;
import pl.creazy.creazylib.part.constraints.Part;
import pl.creazy.creazylib.util.message.Message;
import pl.creazy.creazylib.util.message.Placeholder;
import pl.creazy.creazylib.util.text.Text;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Part
public class EconomyManager {
  @Part
  private EconomyMessageConfig messages;

  private Config data;

  private final Set<String> economyValues = new HashSet<>();

  private static final EconomyValue BALANCE = () -> "balance";

  @OnEnable
  private void setup(@Part CreazyLib plugin, @Part Logger logger) {
    data = Config.getConfig("economy", "data", plugin);
    Bukkit.getScheduler().runTaskTimer(
        plugin,
        () -> {
          logger.info("Saving economy data...");
          save();
          logger.info("Saved economy data.");
        },
        0,
        Config.getConfig("economy", plugin).getLong("auto-save", 12000L));
    registerEconomyValue(BALANCE);
  }

  @OnDisable
  private void save() {
    data.save();
  }

  public void registerEconomyValue(@NotNull EconomyValue value) {
    economyValues.add(value.getName());
  }

  public @NotNull Collection<String> getEconomyValues() {
    return Arrays.asList(economyValues.toArray(String[]::new));
  }

  public @NotNull Message setBalanceMessage(@NotNull String playerName, double balance) {
    return setBalanceMessage(playerName, BALANCE, balance);
  }

  public @NotNull Message setBalanceMessage(@NotNull String playerName, @NotNull EconomyValue value, double balance) {
    return Message.create(
        messages.getBalanceSet(),
        Placeholder.amount(setBalance(playerName, value, balance)),
        Placeholder.playerName(playerName));
  }

  public @NotNull Message getBalanceMessage(@NotNull String playerName) {
    return getBalanceMessage(playerName, BALANCE);
  }

  public @NotNull Message getBalanceMessage(@NotNull String playerName, @NotNull EconomyValue value) {
    var balance = getBalance(playerName, value);
    return Message.create(
        messages.getBalanceGet(),
        Placeholder.playerName(playerName),
        Placeholder.amount(balance)
    );
  }

  public double setBalance(@NotNull String playerName, double balance) {
    return setBalance(playerName, BALANCE, balance);
  }

  public double getBalance(@NotNull String playerName) {
    return getBalance(playerName, BALANCE);
  }

  public double setBalance(@NotNull String playerName, @NotNull EconomyValue value, double balance) {
    if (!economyValues.contains(value.getName())) {
      return -1D;
    }
    data.set(Text.create(playerName, ".", value.getName()), balance);
    return balance;
  }

  public double getBalance(@NotNull String playerName, @NotNull EconomyValue value) {
    if (!economyValues.contains(value.getName())) {
      return -1D;
    }
    return data.getDouble(Text.create(playerName, ".", value.getName()), 0D);
  }
}
