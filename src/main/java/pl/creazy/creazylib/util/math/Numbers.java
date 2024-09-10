package pl.creazy.creazylib.util.math;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

@UtilityClass
public class Numbers {
  public static boolean isDouble(@NotNull String text) {
    try {
      Double.parseDouble(text);
      return true;
    } catch (NumberFormatException ignore) {
      return false;
    }
  }

  public static boolean isInteger(@NotNull String text) {
    try {
      Integer.parseInt(text);
      return true;
    } catch (NumberFormatException ignore) {
      return false;
    }
  }

  public static boolean isLong(@NotNull String text) {
    try {
      Long.parseLong(text);
      return true;
    } catch (NumberFormatException ignore) {
      return false;
    }
  }

  public static int randomInt(int from, int to) {
    if (from > to) {
      throw new IllegalArgumentException("The 'from' value must be less than or equal to the 'to' value.");
    }
    return newRandom().nextInt(to - from + 1) + from;
  }

  @NotNull
  public static Random newRandom() {
    return new Random(new Random().nextLong());
  }

  public static double randomDouble(double from, double to) {
    if (from > to) {
      throw new IllegalArgumentException("The 'from' value must be less than or equal to the 'to' value.");
    }
    return from + (to - from) * newRandom().nextDouble();
  }

  public static boolean percent(double percent) {
    if (percent >= 100D) {
      return true;
    }
    return randomDouble(0D, 100D) <= percent;
  }
}
