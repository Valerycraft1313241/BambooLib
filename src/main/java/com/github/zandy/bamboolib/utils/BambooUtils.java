package com.github.zandy.bamboolib.utils;

import com.github.zandy.bamboolib.BambooLib;
import com.github.zandy.bamboolib.versionsupport.VersionSupport;
import java.util.ArrayList;
import java.util.Iterator;
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
   public static void consolePrint(String var0) {
      Bukkit.getConsoleSender().sendMessage("[" + BambooLib.getPluginInstance().getDescription().getName() + "] " + colorize(var0));
   }

   public static String colorize(String var0) {
      return ChatColor.translateAlternateColorCodes('&', var0);
   }

   public static List<String> colorize(List<String> var0) {
      ArrayList<String> var1 = new ArrayList<>();

       for (String var3 : var0) {
           var1.add(colorize(var3));
       }

      return var1;
   }

   public static String decolorize(String var0) {
      return var0.replace(String.valueOf('§'), "&");
   }

   public static List<String> decolorize(List<String> var0) {
      ArrayList<String> var1 = new ArrayList<>();

       for (String var3 : var0) {
           var1.add(decolorize(var3));
       }

      return var1;
   }

   public static boolean isVersion(Integer... var0) {

       for (Integer var4 : var0) {
           if (VersionSupport.getInstance().getVersion().split("_")[1].equals(String.valueOf(var4))) {
               return true;
           }
       }

      return false;
   }

   public static void sendTextComponent(Player var0, String var1, String var2, String var3, Action var4) {
      TextComponent var5 = new TextComponent(colorize(var1));
      var5.setClickEvent(new ClickEvent(var4, var2));
      var5.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(colorize(var3))).create()));
      var0.spigot().sendMessage(var5);
   }

   public static void sendTextComponent(Player var0, String var1, String var2, Action var3) {
      TextComponent var4 = new TextComponent(colorize(var1));
      var4.setClickEvent(new ClickEvent(var3, var2));
      var0.spigot().sendMessage(var4);
   }

   public static void sendTextComponent(Player var0, String var1, String var2) {
      TextComponent var3 = new TextComponent(colorize(var1));
      var3.setClickEvent(new ClickEvent(Action.SUGGEST_COMMAND, var2));
      var0.spigot().sendMessage(var3);
   }

   public static void sendTextComponentHover(Player var0, String var1, String var2) {
      TextComponent var3 = new TextComponent(colorize(var1));
      var3.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(colorize(var2))).create()));
      var0.spigot().sendMessage(var3);
   }

   public static void registerEvent(Listener var0) {
      Bukkit.getPluginManager().registerEvents(var0, BambooLib.getPluginInstance());
   }

   public static boolean isPluginEnabled(String var0) {
      return Bukkit.getPluginManager().isPluginEnabled(var0);
   }

   public static String capitalizeFirstLetter(String var0) {
      return var0.substring(0, 1).toUpperCase() + var0.substring(1);
   }
}
