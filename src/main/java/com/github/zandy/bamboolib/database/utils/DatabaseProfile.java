package com.github.zandy.bamboolib.database.utils;

import com.github.zandy.bamboolib.BambooLib;
import com.github.zandy.bamboolib.exceptions.BambooErrorException;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class DatabaseProfile {
   private final YamlConfiguration yml;
   private final File file;

   public DatabaseProfile(String profileName) {
      String directoryPath = "plugins/" + BambooLib.getPluginInstance().getDescription().getName() + "/Profiles";
      new File(directoryPath).mkdirs();
      this.file = new File(directoryPath, profileName + ".yml");
      if (!this.file.exists()) {
         try {
            this.file.createNewFile();
         } catch (IOException e) {
            throw new BambooErrorException(e, this.getClass(), Collections.singletonList("Cannot create file '" + profileName + "' with path '" + directoryPath + "'"));
         }
      }
      this.yml = YamlConfiguration.loadConfiguration(this.file);
   }

   public static boolean exists(String profileName) {
      return new File("plugins/" + BambooLib.getPluginInstance().getDescription().getName() + "/Profiles/", profileName + ".yml").exists();
   }

   public static void remove(String profileName) {
      new File("plugins/" + BambooLib.getPluginInstance().getDescription().getName() + "/Profiles/", profileName + ".yml").delete();
   }

   public void set(String path, Object value) {
      this.yml.set(path, value);
      this.save();
   }

   public void addDefault(String path, Object value) {
      this.yml.addDefault(path, value);
   }

   public void copyDefaults() {
      this.yml.options().copyDefaults(true);
   }

   public Object get(String path) {
      return this.yml.get(path);
   }

   public boolean contains(String path) {
      return this.yml.contains(path);
   }

   public ConfigurationSection getConfigurationSection(String path) {
      return this.yml.getConfigurationSection(path);
   }

   public String getString(String path) {
      return this.yml.getString(path);
   }

   public int getInt(String path) {
      return this.yml.getInt(path);
   }

   public boolean getBoolean(String path) {
      return this.yml.getBoolean(path);
   }

   public long getLong(String path) {
      return this.yml.getLong(path);
   }

   public double getDouble(String path) {
      return this.yml.getDouble(path);
   }

   public void save() {
      try {
         this.yml.save(this.file);
      } catch (IOException e) {
         throw new BambooErrorException(e, this.getClass(), Collections.singletonList("Cannot save file '" + this.file.getName() + "'"));
      }
   }
}
