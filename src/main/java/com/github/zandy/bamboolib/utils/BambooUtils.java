package com.github.zandy.bamboolib.utils;

import com.github.zandy.bamboolib.BambooLib;
import com.github.zandy.bamboolib.versionsupport.VersionSupport;
import java.util.ArrayList;
import java.util.List;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class BambooUtils {
   public static void consolePrint(String message) {
      Bukkit.getConsoleSender().sendMessage("[" + BambooLib.getPluginInstance().getDescription().getName() + "] " + colorize(message));
   }

   public static String colorize(String message) {
      return ChatColor.translateAlternateColorCodes('&', message);
   }

   public static List<String> colorize(List<String> messages) {
      ArrayList<String> coloredMessages = new ArrayList<>();

      for (String message : messages) {
         coloredMessages.add(colorize(message));
      }

      return coloredMessages;
   }

   public static String decolorize(String message) {
      return message.replace(String.valueOf('§'), "&");
   }

   public static List<String> decolorize(List<String> messages) {
      ArrayList<String> decoloredMessages = new ArrayList<>();

      for (String message : messages) {
         decoloredMessages.add(decolorize(message));
      }

      return decoloredMessages;
   }

   public static boolean isVersion(Integer... versions) {
      for (Integer version : versions) {
         if (VersionSupport.getInstance().getVersion().split("_")[1].equals(String.valueOf(version))) {
            return true;
         }
      }
      return false;
   }

   public static void sendTextComponent(Player player, String message, String clickValue, String hoverValue, Action clickAction) {
      TextComponent textComponent = new TextComponent(colorize(message));
      textComponent.setClickEvent(new ClickEvent(clickAction, clickValue));
      textComponent.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(colorize(hoverValue))).create()));
      player.spigot().sendMessage(textComponent);
   }

   public static void sendTextComponent(Player player, String message, String clickValue, Action clickAction) {
      TextComponent textComponent = new TextComponent(colorize(message));
      textComponent.setClickEvent(new ClickEvent(clickAction, clickValue));
      player.spigot().sendMessage(textComponent);
   }

   public static void sendTextComponent(Player player, String message, String clickValue) {
      TextComponent textComponent = new TextComponent(colorize(message));
      textComponent.setClickEvent(new ClickEvent(Action.SUGGEST_COMMAND, clickValue));
      player.spigot().sendMessage(textComponent);
   }

   public static void sendTextComponentHover(Player player, String message, String hoverValue) {
      TextComponent textComponent = new TextComponent(colorize(message));
      textComponent.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(colorize(hoverValue))).create()));
      player.spigot().sendMessage(textComponent);
   }

   public static void registerEvent(Listener listener) {
      Bukkit.getPluginManager().registerEvents(listener, BambooLib.getPluginInstance());
   }

   public static boolean isPluginEnabled(String pluginName) {
      return Bukkit.getPluginManager().isPluginEnabled(pluginName);
   }

   public static String capitalizeFirstLetter(String input) {
      return input.substring(0, 1).toUpperCase() + input.substring(1);
   }
}
