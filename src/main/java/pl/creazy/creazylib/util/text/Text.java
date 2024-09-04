package pl.creazy.creazylib.util.text;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jetbrains.annotations.NotNull;

import net.md_5.bungee.api.ChatColor;

public class Text {
  @NotNull
  public static String color(@NotNull String text) {
    Pattern pattern = Pattern.compile("<#[a-fA-F0-9]{6}>");
    Matcher matcher = pattern.matcher(text);

    while (matcher.find()) {
      String color = text.substring(matcher.start(), matcher.end());
      text = text.replace(color, ChatColor.of(color.substring(1, color.length() - 1)) + "");
      matcher = pattern.matcher(text);
    }

    return ChatColor.translateAlternateColorCodes('&', text);
  }

  @NotNull
  public static String path(@NotNull String path) {
    return path.replace("/", File.separator);
  }
}
