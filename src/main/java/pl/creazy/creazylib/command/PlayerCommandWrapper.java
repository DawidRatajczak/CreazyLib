package pl.creazy.creazylib.command;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import pl.creazy.creazylib.command.constraints.Args;
import pl.creazy.creazylib.exception.permission.NoPermissionException;

public class PlayerCommandWrapper implements CommandExecutor, TabCompleter {
  private final Object commandPart;
  private final List<ArgsCommand> commands = new ArrayList<>();

  public PlayerCommandWrapper(@NotNull Object commandPart) {
    this.commandPart = commandPart;
    for (Method method : commandPart.getClass().getDeclaredMethods()) {
      var args = method.getAnnotation(Args.class);

      if (args != null) {
        var argCommand = new ArgsCommand(args.value(), method);
        commands.add(argCommand);
      }
    }
  }

  @Override
  public boolean onCommand(
      @NotNull CommandSender sender,
      @NotNull Command command,
      @NotNull String label,
      @NotNull String[] args) {
    if (!(sender instanceof Player player)) {
      return true;
    }
    if (args.length == 0) {
      if (commandPart instanceof NoArgsHandler handler) {
        handler.handleNoArgs(player);
      }
      return true;
    }
    for (var argsCommand : commands) {
      try {
        var methodArgs = argsCommand.getRequiredMethodArgs(args, player);
        if (methodArgs != null) {
          try {
            var method = argsCommand.getMethod();
            var finalArgs = new ArrayList<>();
            if (method.getParameterTypes()[0].equals(Player.class)) {
              finalArgs.add(player);
            }
            finalArgs.addAll(Arrays.asList(methodArgs));
            argsCommand.getMethod().setAccessible(true);
            argsCommand.getMethod().invoke(commandPart, finalArgs.toArray());
            return true;
          } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
            throw new RuntimeException("Failed to run command", exception);
          }
        }
      } catch (NoPermissionException exception) {
        if (commandPart instanceof NoPermissionHandler handler) {
          handler.handleNoPermission(player, exception.getPermissionDenied(), args, label);
          return true;
        }
      }
    }

    if (commandPart instanceof NoMatchHandler handler) {
      handler.handleNoMatch(player, args);
    }
    return true;
  }

  @Override
  @Nullable
  public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player player)) {
      return null;
    }
    if (commandPart instanceof TabCompleteHandler handler) {
      var completer = handler.handleTabComplete(player, args);
      if (completer != null) {
        return completer;
      }
    }
    return commands.stream()
        .map(ArgsCommand::getArgsPattern)
        .filter(pattern -> pattern.length >= args.length)
        .filter(pattern -> comparePatternForTabCompleter(pattern, args))
        .map(pattern -> pattern[args.length - 1])
        .filter(pattern -> !pattern.startsWith("?"))
        .toList();
  }

  private boolean comparePatternForTabCompleter(String[] pattern, String[] args) {
    
    final boolean startsWith = pattern[args.length - 1].startsWith(args[args.length - 1]);
    if (args.length >= 2) {
      return startsWith && pattern[args.length - 2].equalsIgnoreCase(args[args.length - 2]);
    }
    return startsWith;
  }
}
