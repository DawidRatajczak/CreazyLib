package pl.creazy.creazylib.util.message;

import static pl.creazy.creazylib.util.text.Text.color;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public interface Message {
  static void sendChat(CommandSender sender, String message) {
    Message.create(message).sendChat(sender);
  }

  static void sendServer(String message) {
    Message.create(message).sendServer();
  }

  static void sendActionBar(Player player, String message) {
    Message.create(message).sendActionBar(player);
  }

  static Message create(String message) {
    return new Message() {
      @Override
      public void sendChat(CommandSender sender) {
        sender.sendMessage(color(message));
      }

      @Override
      public void sendServer() {
        Bukkit.getServer().broadcastMessage(color(message));
      }

      @Override
      public void sendActionBar(Player sender) {
        sender.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(color(message)));
      }

      @Override
      public String getContent() {
        return message;
      }
    };
  }

  String getContent();

  void sendChat(CommandSender sender);

  void sendServer();

  void sendActionBar(Player sender);

  default void sendChat() {
    Bukkit.getOnlinePlayers().forEach(this::sendChat);
  }

  default void sendActionBar() {
    Bukkit.getOnlinePlayers().forEach(this::sendActionBar);
  }
}
