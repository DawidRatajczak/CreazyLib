package pl.creazy.creazylib.util.message;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static pl.creazy.creazylib.util.text.Text.color;

public interface Message {
  static void sendChat(@NotNull CommandSender sender, @Nullable String message) {
    if (message == null) {
      return;
    }
    Message.create(message).sendChat(sender);
  }

  static void sendServer(@Nullable String message) {
    if (message == null) {
      return;
    }
    Message.create(message).sendServer();
  }

  static void sendActionBar(@NotNull Player player, @NotNull String message) {
    Message.create(message).sendActionBar(player);
  }

  static @NotNull Message create(@NotNull String message, @NotNull Placeholder... placeholders) {
    var finalMessage = message;
    for (Placeholder placeholder : placeholders) {
      finalMessage = finalMessage.replace(placeholder.getPlaceholder(), placeholder.getValue().toString());
    }
    return create(finalMessage);
  }

  static @NotNull Message create(@NotNull String message) {
    return new Message() {
      @Override
      public void sendChat(@NotNull CommandSender sender) {
        sender.sendMessage(color(message));
      }

      @Override
      public void sendServer() {
        Bukkit.getServer().broadcastMessage(color(message));
      }

      @Override
      public void sendActionBar(@NotNull Player sender) {
        sender.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(color(message)));
      }

      @Override
      public String getContent() {
        return message;
      }
    };
  }

  String getContent();

  void sendChat(@NotNull CommandSender sender);

  void sendServer();

  void sendActionBar(@NotNull Player sender);

  default void sendChat() {
    Bukkit.getOnlinePlayers().forEach(this::sendChat);
  }

  default void sendActionBar() {
    Bukkit.getOnlinePlayers().forEach(this::sendActionBar);
  }
}
