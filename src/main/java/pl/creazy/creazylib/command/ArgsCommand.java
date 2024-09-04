package pl.creazy.creazylib.command;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import lombok.Getter;
import pl.creazy.creazylib.command.constraints.HasPermissions;
import pl.creazy.creazylib.exception.permission.NoPermissionException;
import pl.creazy.creazylib.util.math.Numbers;

@Getter
public class ArgsCommand {
  static final String ANY = "?";
  static final String ANY_STRING = ANY.concat("s");
  static final String ANY_INTEGER = ANY.concat("i");
  static final String ANY_DOUBLE = ANY.concat("d");
  static final String ANY_LONG = ANY.concat("l");
  static final String ANY_ENUM = ANY.concat("e");

  private final String[] argsPattern;
  private final Method method;
  private Map<String, Enum<?>> enumMappings;
  private String[] permissions;

  public ArgsCommand(@NotNull String argsPattern, @NotNull Method method) {
    this.argsPattern = argsPattern.split(" ");
    this.method = method;

    var permissions = method.getAnnotation(HasPermissions.class);
    if (permissions != null) {
      this.permissions = permissions.value();
    }

    for (Class<?> type : method.getParameterTypes()) {
      if (type.isEnum()) {
        if (enumMappings == null) {
          enumMappings = new HashMap<>();
        }
        for (Enum<?> enumConstant : type.asSubclass(Enum.class).getEnumConstants()) {
          enumMappings.put(enumConstant.name(), enumConstant);
        }
      }
    }
  }

  @Nullable
  public Object[] getRequiredMethodArgs(@NotNull String[] commandArgs, Player player) throws NoPermissionException {
    var start = System.currentTimeMillis();
    if (commandArgs.length != argsPattern.length) {
      return null;
    }

    var methodArgs = new ArrayList<>();
    var index = 0;
    for (String argPattern : argsPattern) {
      var arg = commandArgs[index];
      // check for matching hard coded text
      if (!argPattern.startsWith(ANY) && !argPattern.equals(arg)) {
        return null;
      }

      // check for matching any double
      if (argPattern.equals(ANY_DOUBLE)) {
        if (!Numbers.isDouble(arg)) {
          return null;
        }
        methodArgs.add(Double.parseDouble(arg));
      }

      // check for matching any integer
      if (argPattern.equals(ANY_INTEGER)) {
        if (!Numbers.isInteger(arg)) {
          return null;
        }
        methodArgs.add(Integer.parseInt(arg));
      }

      // check for matching any long
      if (argPattern.equals(ANY_LONG)) {
        if (!Numbers.isLong(arg)) {
          return null;
        }
        methodArgs.add(Long.parseLong(arg));
      }

      // check for matching any enum
      if (argPattern.equals(ANY_ENUM)) {
        var enumConstant = enumMappings.get(arg.toUpperCase());
        if (enumConstant == null) {
          return null;
        }
        methodArgs.add(enumConstant);
      }

      // check for matching any string
      if (argPattern.equals(ANY_STRING)) {
        methodArgs.add(arg);
      }
      index++;
    }

    if (permissions != null) {
      for (String permission : permissions) {
        if (!player.hasPermission(permission)) {
          throw new NoPermissionException(permission);
        }
      }
    }
    System.out.println(System.currentTimeMillis() - start);
    return methodArgs.toArray();
  }
}
