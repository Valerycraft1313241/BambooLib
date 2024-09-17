package com.github.zandy.bamboolib;

import com.github.zandy.bamboolib.exceptions.BambooException;
import com.github.zandy.bamboolib.versionsupport.material.Materials;
import org.bukkit.plugin.java.JavaPlugin;

public class BambooLib {
   private static JavaPlugin pluginInstance;

   public static void setPluginInstance(JavaPlugin plugin) {
      if (pluginInstance != null) {
         throw new BambooException("BambooLib cannot be instantiated multiple times.");
      } else if (plugin == null) {
         throw new BambooException("BambooLib cannot run with a null plugin instance.");
      } else {
         pluginInstance = plugin;

         try {
            Class.forName("org.spigotmc.SpigotConfig");
         } catch (ClassNotFoundException exception) {
            throw new BambooException("BambooLib can run on Spigot, PaperSpigot or other forks.");
         }

         Materials.init();
      }
   }


   public static JavaPlugin getPluginInstance() {
      return pluginInstance;
   }
}
