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

   public static String setPlaceholders(Player var0, String var1) {
      return PlaceholderAPI.setPlaceholders(var0, var1);
   }

   public static boolean hasPlaceholders(String var0) {
      return PlaceholderAPI.containsPlaceholders(var0);
   }

   @NotNull
   public String getIdentifier() {
      String var10000 = PlaceholderManager.getIdentifier();
      if (var10000 == null) {
         $$$reportNull$$$0(0);
      }

      return var10000;
   }

   public boolean canRegister() {
      return true;
   }

   @NotNull
   public String getAuthor() {
      String var10000 = (String)BambooLib.getPluginInstance().getDescription().getAuthors().get(0);
      if (var10000 == null) {
         $$$reportNull$$$0(1);
      }

      return var10000;
   }

   @NotNull
   public String getVersion() {
      String var10000 = BambooLib.getPluginInstance().getDescription().getVersion();
      if (var10000 == null) {
         $$$reportNull$$$0(2);
      }

      return var10000;
   }

   public String onPlaceholderRequest(Player var1, String var2) {
      if (!PlaceholderManager.getInstance().getPlaceholderMap().containsKey(var2.toLowerCase())) {
         return null;
      } else {
         Placeholder var3 = (Placeholder)PlaceholderManager.getInstance().getPlaceholderMap().get(var2.toLowerCase());
         String var4 = var3.request(var1);
         if (var4 == null) {
            var4 = var3.request();
         }

         return var4;
      }
   }

   public static PlaceholderAPISupport getInstance() {
      if (instance == null) {
         instance = new PlaceholderAPISupport();
      }

      return instance;
   }

   // $FF: synthetic method
   private static void $$$reportNull$$$0(int var0) {
      Object[] var10001 = new Object[]{"com/github/zandy/bamboolib/placeholder/support/PlaceholderAPISupport", null};
      switch(var0) {
      case 0:
      default:
         var10001[1] = "getIdentifier";
         break;
      case 1:
         var10001[1] = "getAuthor";
         break;
      case 2:
         var10001[1] = "getVersion";
      }

      throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", var10001));
   }
}
