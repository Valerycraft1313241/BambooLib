package com.github.zandy.bamboolib.placeholder;

import com.github.zandy.bamboolib.placeholder.support.PlaceholderAPISupport;
import com.github.zandy.bamboolib.placeholder.utils.Placeholder;
import com.github.zandy.bamboolib.utils.BambooUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlaceholderManager {
   private static PlaceholderManager instance = null;
   private final HashMap<String, Placeholder> placeholderMap = new HashMap<>();
   private boolean placeholderAPIEnabled = false;
   private static String identifier;

   public PlaceholderManager() {
      if (identifier != null && BambooUtils.isPluginEnabled("PlaceholderAPI")) {
         this.placeholderAPIEnabled = true;
         PlaceholderAPISupport.getInstance().register();
      }
   }

   public String formatPlaceholder(String placeholder) {
      return "%" + getIdentifier() + "_" + placeholder + "%";
   }

   public void addPlaceholder(Placeholder placeholder) {
      this.placeholderMap.put(placeholder.getName(), placeholder);
   }

   public String setPlaceholders(Player player, String text) {
      text = BambooUtils.colorize(text);
      if (this.placeholderAPIEnabled) {
         return PlaceholderAPISupport.setPlaceholders(player, text);
      } else {
         String formattedText = text;

         for (String key : this.placeholderMap.keySet()) {
            Placeholder placeholder = this.placeholderMap.get(key);
            String value = placeholder.request(player);
            if (value == null) {
               value = placeholder.request();
            }

            if (value != null) {
               formattedText = formattedText.replace(this.formatPlaceholder(key), value);
            }
         }

         return formattedText;
      }
   }

   public List<String> setPlaceholders(Player player, List<String> texts) {
      List<String> formattedTexts = new ArrayList<>();
      texts.forEach((text) -> {
         formattedTexts.add(this.setPlaceholders(player, text));
      });
      return formattedTexts;
   }

   public ItemStack setPlaceholders(Player player, ItemStack item) {
      if (item.hasItemMeta()) {
         ItemMeta meta = item.getItemMeta();
         if (meta.hasDisplayName()) {
            meta.setDisplayName(this.setPlaceholders(player, meta.getDisplayName()));
         }

         if (meta.hasLore()) {
            meta.setLore(this.setPlaceholders(player, meta.getLore()));
         }

         item.setItemMeta(meta);
      }
      return item;
   }

   public boolean hasPlaceholders(String text) {
      if (this.placeholderAPIEnabled) {
         return PlaceholderAPISupport.hasPlaceholders(text);
      } else {
         for (String key : this.placeholderMap.keySet()) {
            if (text.contains(this.formatPlaceholder(key))) {
               return true;
            }
         }
         return false;
      }
   }

   public boolean hasPlaceholders(List<String> texts) {
      for (String text : texts) {
         if (this.hasPlaceholders(text)) {
            return true;
         }
      }
      return false;
   }

   public boolean hasPlaceholders(ItemStack item) {
      if (!item.hasItemMeta()) {
         return false;
      } else {
         ItemMeta meta = item.getItemMeta();
         return (meta.hasDisplayName() && this.hasPlaceholders(meta.getDisplayName())) || (meta.hasLore() && this.hasPlaceholders(meta.getLore()));
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

   public static void setIdentifier(String newIdentifier) {
      identifier = newIdentifier;
   }
}
