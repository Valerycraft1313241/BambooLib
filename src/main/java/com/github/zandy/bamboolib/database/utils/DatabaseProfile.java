package com.github.zandy.bamboolib.database.utils;

import com.github.zandy.bamboolib.BambooLib;
import com.github.zandy.bamboolib.exceptions.BambooErrorException;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class DatabaseProfile {
   private final YamlConfiguration yml;
   private final File file;

   public DatabaseProfile(String var1) {
      String var2 = "plugins/" + BambooLib.getPluginInstance().getDescription().getName() + "/Profiles";
      (new File(var2)).mkdirs();
      this.file = new File(var2, var1 + ".yml");
      if (!this.file.exists()) {
         try {
            this.file.createNewFile();
         } catch (Exception var4) {
            throw new BambooErrorException(var4, this.getClass(), Arrays.asList("Cannot create file '" + var1 + "'", "with path '" + var2 + "'"));
         }
      }

      this.yml = YamlConfiguration.loadConfiguration(this.file);
   }

   public static boolean exists(String var0) {
      return (new File("plugins/" + BambooLib.getPluginInstance().getDescription().getName() + "/Profiles/", var0 + ".yml")).exists();
   }

   public static void remove(String var0) {
      (new File("plugins/" + BambooLib.getPluginInstance().getDescription().getName() + "/Profiles/", var0 + ".yml")).delete();
   }

   public void set(String var1, Object var2) {
      this.yml.set(var1, var2);
      this.save();
   }

   public void addDefault(String var1, Object var2) {
      this.yml.addDefault(var1, var2);
   }

   public void copyDefaults() {
      this.yml.options().copyDefaults(true);
   }

   public Object get(String var1) {
      return this.yml.get(var1);
   }

   public boolean contains(String var1) {
      return this.yml.contains(var1);
   }

   public ConfigurationSection getConfigurationSection(String var1) {
      return this.yml.getConfigurationSection(var1);
   }

   public String getString(String var1) {
      return this.yml.getString(var1);
   }

   public int getInt(String var1) {
      return this.yml.getInt(var1);
   }

   public boolean getBoolean(String var1) {
      return this.yml.getBoolean(var1);
   }

   public long getLong(String var1) {
      return this.yml.getLong(var1);
   }

   public double getDouble(String var1) {
      return this.yml.getDouble(var1);
   }

   public void save() {
      try {
         this.yml.save(this.file);
      } catch (Exception var2) {
         throw new BambooErrorException(var2, this.getClass(), Collections.singletonList("Cannot save file '" + this.file.getName() + "'"));
      }
   }
}
