package com.github.zandy.bamboolib.utils;

import com.github.zandy.bamboolib.BambooLib;
import com.github.zandy.bamboolib.exceptions.BambooException;
import com.github.zandy.bamboolib.versionsupport.VersionSupport;
import com.github.zandy.bamboolib.versionsupport.material.Materials;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BambooFile {
   private static final UUID uuid = UUID.fromString("eb38bdd9-a3d9-41c5-9f03-2273ed0e8bc7");
   private YamlConfiguration yml;
   private File file;

   public BambooFile(String var1, String var2, boolean var3) {
      this.init(var1, var2, var3);
   }

   public BambooFile(String var1, String var2) {
      this.init(var1, var2, false);
   }

   public BambooFile(String var1) {
      this.init(var1, "", false);
   }

   private void init(String var1, String var2, boolean var3) {
      if (!var3) {
         var2 = "plugins/" + BambooLib.getPluginInstance().getDescription().getName() + "/" + var2;
      }

      (new File(var2)).mkdirs();
      this.file = new File(var2, var1 + ".yml");
      if (!this.file.exists()) {
         try {
            this.file.createNewFile();
         } catch (Exception var5) {
            throw new BambooException(Arrays.asList("Cannot create file '" + var1 + ".yml'", "with path '" + var2 + "'"));
         }
      }

      this.yml = YamlConfiguration.loadConfiguration(this.file);
   }

   public static boolean exists(String var0, String var1) {
      return (new File("plugins/" + BambooLib.getPluginInstance().getDescription().getName() + "/" + var1 + "/" + var0 + ".yml")).exists();
   }

   public void save() {
      try {
         this.yml.save(this.file);
      } catch (Exception var2) {
         throw new BambooException("Cannot save file '" + this.file.getName() + "'");
      }
   }

   public String getName() {
      return this.file.getName();
   }

   public void addDefault(String var1, Object var2) {
      this.yml.addDefault(var1, var2);
   }

   public void copyDefaults() {
      this.yml.options().copyDefaults(true);
   }

   public void set(String var1, Object var2) {
      this.yml.set(var1, var2);
      this.save();
   }

   public Object get(String var1) {
      return this.yml.get(var1);
   }

   public boolean contains(String var1) {
      return this.yml.contains(var1);
   }

   public String getString(String var1) {
      return BambooUtils.colorize(this.yml.getString(var1));
   }

   public String getRawString(String var1) {
      return this.yml.getString(var1);
   }

   public List<String> getStringList(String var1) {
      ArrayList<String> var2 = new ArrayList<>();
      this.yml.getStringList(var1).forEach((var1x) -> var2.add(BambooUtils.colorize(var1x)));
      return var2;
   }

   public List<String> getRawStringList(String var1) {
      return this.yml.getStringList(var1);
   }

   public boolean getBoolean(String var1) {
      return this.yml.getBoolean(var1);
   }

   public int getInt(String var1) {
      return this.yml.getInt(var1);
   }

   public double getDouble(String var1) {
      return this.yml.getDouble(var1);
   }

   public float getFloat(String var1) {
      return (float)this.yml.getDouble(var1);
   }

   public long getLong(String var1) {
      return this.yml.getLong(var1);
   }

   public Materials getMaterial(String var1) {
      return Materials.valueOf(this.getRawString(var1));
   }

   public List<?> getList(String var1) {
      return this.yml.getList(var1);
   }

   public ConfigurationSection getConfigurationSection(String var1) {
      return this.yml.getConfigurationSection(var1);
   }

   public void setLocation(String var1, Location var2) {
      this.yml.set(var1 + ".World", var2.getWorld().getName());
      this.yml.set(var1 + ".X", var2.getX());
      this.yml.set(var1 + ".Y", var2.getY());
      this.yml.set(var1 + ".Z", var2.getZ());
      this.yml.set(var1 + ".Yaw", var2.getYaw());
      this.yml.set(var1 + ".Pitch", var2.getPitch());
      this.save();
   }

   public Location getLocation(String var1) {
      return new Location(Bukkit.getWorld(this.getString(var1 + ".World")), this.getDouble(var1 + ".X"), this.getDouble(var1 + ".Y"), this.getDouble(var1 + ".Z"), this.getFloat(var1 + ".Yaw"), this.getFloat(var1 + ".Pitch"));
   }

   public void setItemStack(String var1, ItemStack var2) {
      this.yml.set(var1 + ".Material", var2.getType().name());
      if (var2.getAmount() > 1) {
         this.yml.set(var1 + ".Amount", var2.getAmount());
      }

      if (var2.getDurability() != var2.getType().getMaxDurability()) {
         this.yml.set(var1 + ".Durability", var2.getDurability());
      }

      if (VersionSupport.getInstance().hasMetaData(var2, "SkullPlayer")) {
         this.yml.set(var1 + ".SkullPlayer", VersionSupport.getInstance().getMetaData(var2, "SkullPlayer"));
      }

      if (VersionSupport.getInstance().hasMetaData(var2, "SkullSkin")) {
         this.yml.set(var1 + ".SkullSkin", VersionSupport.getInstance().getMetaData(var2, "SkullSkin"));
      }

      if (var2.hasItemMeta()) {
         ItemMeta var3 = var2.getItemMeta();
         if (var3.hasDisplayName()) {
            this.yml.set(var1 + ".DisplayName", BambooUtils.decolorize(var3.getDisplayName()));
         }

         if (var3.hasLore()) {
            this.yml.set(var1 + ".Lore", BambooUtils.decolorize(var3.getLore()));
         }

         if (var3.hasEnchants()) {
            var3.getEnchants().keySet().forEach((var3x) -> {
               this.yml.set(var1 + ".Enchantments." + var3x.getName(), var3.getEnchants().get(var3x));
            });
         }

         ArrayList<String> var4 = new ArrayList<>();
         Stream<ItemFlag> var10000 = Arrays.stream(ItemFlag.values());
         Objects.requireNonNull(var3);
         var10000.filter(var3::hasItemFlag).forEach((var1x) -> {
            var4.add(var1x.name());
         });
         if (!var4.isEmpty()) {
            this.yml.set(var1 + ".ItemFlags", var4);
         }
      }

      this.save();
   }

   public ItemStack getItemStack(String var1) {
      if (!this.contains(var1)) {
         return null;
      } else {
         ItemStack var2 = new ItemStack(Material.valueOf(this.getString(var1 + ".Material")));
         ItemMeta var3 = var2.getItemMeta();
         if (this.contains(var1 + ".Amount")) {
            var2.setAmount(this.getInt(var1 + ".Amount"));
         }

         if (this.contains(var1 + ".Durability")) {
            var2.setDurability((short)this.getInt(var1 + ".Durability"));
         }

         if (this.contains(var1 + ".DisplayName")) {
            var3.setDisplayName(BambooUtils.colorize(this.getString(var1 + ".DisplayName")));
         }

         if (this.contains(var1 + ".Lore")) {
            var3.setLore(BambooUtils.colorize(this.getStringList(var1 + ".Lore")));
         }

         if (this.contains(var1 + ".Enchantments")) {
            this.getConfigurationSection(var1 + ".Enchantments").getKeys(false).forEach((var3x) -> {
               var3.addEnchant(Enchantment.getByName(var3x), this.getInt(var1 + ".Enchantments." + var3x), true);
            });
         }

         String var5;
         if (this.contains(var1 + ".ItemFlags")) {

             for (String s : this.getStringList(var1 + ".ItemFlags")) {
                 var5 = s;
                 var3.addItemFlags(ItemFlag.valueOf(var5));
             }
         }

         var2.setItemMeta(var3);
         String var9;
         if (this.contains(var1 + ".SkullPlayer")) {
            var9 = this.getString(var1 + ".SkullPlayer");
            VersionSupport.getInstance().setSkullOwner(var2, var9);
            VersionSupport.getInstance().addMetaData(var2, "SkullPlayer", var9);
         }

         if (this.contains(var1 + ".SkullSkin")) {
            var9 = var2.getType().name();
            if (!var9.equals("PLAYER_HEAD") && !var9.equals("SKULL_ITEM")) {
               return var2;
            }

            var5 = this.getString(var1 + ".SkullSkin");
            if (var5 == null) {
               return var2;
            }

            GameProfile var6 = new GameProfile(uuid, null);
            var6.getProperties().put("textures", new Property("textures", VersionSupport.getInstance().getTexture(var5)));

            try {
               Field var7 = var3.getClass().getDeclaredField("profile");
               var7.setAccessible(true);
               var7.set(var3, var6);
            } catch (Exception var8) {
               throw new BambooException("Cannot set skull's skin.");
            }

            var2.setItemMeta(var3);
            VersionSupport.getInstance().addMetaData(var2, "SkullSkin", var5);
         }

         return var2;
      }
   }

   public void delete() {
      this.file.delete();
   }
}
