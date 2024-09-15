package com.github.zandy.bamboolib.placeholder;

import com.github.zandy.bamboolib.placeholder.support.PlaceholderAPISupport;
import com.github.zandy.bamboolib.placeholder.utils.Placeholder;
import com.github.zandy.bamboolib.utils.BambooUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlaceholderManager {
   private static PlaceholderManager instance = null;
   private final HashMap<String, Placeholder> placeholderMap = new HashMap();
   private boolean placeholderAPIEnabled = false;
   private static String identifier;

   public PlaceholderManager() {
      if (identifier != null && BambooUtils.isPluginEnabled("PlaceholderAPI")) {
         this.placeholderAPIEnabled = true;
         PlaceholderAPISupport.getInstance().register();
      }

   }

   public String formatPlaceholder(String var1) {
      return "%" + getIdentifier() + "_" + var1 + "%";
   }

   public void addPlaceholder(Placeholder var1) {
      this.placeholderMap.put(var1.getName(), var1);
   }

   public String setPlaceholders(Player var1, String var2) {
      var2 = BambooUtils.colorize(var2);
      if (this.placeholderAPIEnabled) {
         return PlaceholderAPISupport.setPlaceholders(var1, var2);
      } else {
         String var3 = var2;
         Iterator var4 = this.placeholderMap.keySet().iterator();

         while(var4.hasNext()) {
            String var5 = (String)var4.next();
            Placeholder var6 = (Placeholder)this.placeholderMap.get(var5);
            String var7 = var6.request(var1);
            if (var7 == null) {
               var7 = var6.request();
            }

            if (var7 != null) {
               var3 = var3.replace(this.formatPlaceholder(var5), var7);
            }
         }

         return var3;
      }
   }

   public List<String> setPlaceholders(Player var1, List<String> var2) {
      ArrayList var3 = new ArrayList();
      var2.forEach((var3x) -> {
         var3.add(this.setPlaceholders(var1, var3x));
      });
      return var3;
   }

   public ItemStack setPlaceholders(Player var1, ItemStack var2) {
      if (!var2.hasItemMeta()) {
         return var2;
      } else {
         ItemMeta var3 = var2.getItemMeta();
         if (var3.hasDisplayName()) {
            var3.setDisplayName(this.setPlaceholders(var1, var3.getDisplayName()));
         }

         if (var3.hasLore()) {
            var3.setLore(this.setPlaceholders(var1, var3.getLore()));
         }

         var2.setItemMeta(var3);
         return var2;
      }
   }

   public boolean hasPlaceholders(String var1) {
      if (this.placeholderAPIEnabled) {
         return PlaceholderAPISupport.hasPlaceholders(var1);
      } else {
         Iterator var2 = this.placeholderMap.keySet().iterator();

         String var3;
         do {
            if (!var2.hasNext()) {
               return false;
            }

            var3 = (String)var2.next();
         } while(!var1.contains(this.formatPlaceholder(var3)));

         return true;
      }
   }

   public boolean hasPlaceholders(List<String> var1) {
      Iterator var2 = var1.iterator();

      String var3;
      do {
         if (!var2.hasNext()) {
            return false;
         }

         var3 = (String)var2.next();
      } while(!this.hasPlaceholders(var3));

      return true;
   }

   public boolean hasPlaceholders(ItemStack var1) {
      if (!var1.hasItemMeta()) {
         return false;
      } else {
         ItemMeta var2 = var1.getItemMeta();
         return var2.hasDisplayName() && this.hasPlaceholders(var2.getDisplayName()) || var2.hasLore() && this.hasPlaceholders(var2.getLore());
      }
   }

   public static PlaceholderManager getInstance() {
      if (instance == null) {
         instance = new PlaceholderManager();
      }

      return instance;
   }

   public HashMap<String, Placeholder> getPlaceholderMap() {
      return this.placeholderMap;
   }

   public boolean isPlaceholderAPIEnabled() {
      return this.placeholderAPIEnabled;
   }

   public static String getIdentifier() {
      return identifier;
   }

   public static void setIdentifier(String var0) {
      identifier = var0;
   }
}
