package com.github.zandy.bamboolib.placeholder.support;

import com.github.zandy.bamboolib.BambooLib;
import com.github.zandy.bamboolib.placeholder.PlaceholderManager;
import com.github.zandy.bamboolib.placeholder.utils.Placeholder;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlaceholderAPISupport extends PlaceholderExpansion {
   private static PlaceholderAPISupport instance = null;

   public static String setPlaceholders(Player player, String text) {
      return PlaceholderAPI.setPlaceholders(player, text);
   }

   public static boolean hasPlaceholders(String text) {
      return PlaceholderAPI.containsPlaceholders(text);
   }

   @NotNull
   public String getIdentifier() {
      return PlaceholderManager.getIdentifier();
   }

   public boolean canRegister() {
      return true;
   }

   @NotNull
   public String getAuthor() {
      return BambooLib.getPluginInstance().getDescription().getAuthors().get(0);
   }

   @NotNull
   public String getVersion() {
      return BambooLib.getPluginInstance().getDescription().getVersion();
   }

   public String onPlaceholderRequest(Player player, String identifier) {
      if (!PlaceholderManager.getInstance().getPlaceholderMap().containsKey(identifier.toLowerCase())) {
         return null;
      } else {
         Placeholder placeholder = PlaceholderManager.getInstance().getPlaceholderMap().get(identifier.toLowerCase());
         String result = placeholder.request(player);
         if (result == null) {
            result = placeholder.request();
         }

         return result;
      }
   }

   public static PlaceholderAPISupport getInstance() {
      if (instance == null) {
         instance = new PlaceholderAPISupport();
      }

      return instance;
   }

}
