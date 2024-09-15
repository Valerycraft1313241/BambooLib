package com.github.zandy.bamboolib.versionsupport.sound;

import com.github.zandy.bamboolib.exceptions.BambooSoundError;
import com.github.zandy.bamboolib.exceptions.BambooSoundNotFound;
import com.github.zandy.bamboolib.versionsupport.VersionSupport;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Sound {
   public static HashMap<String, Sound> soundCache = new HashMap<>();
   private final HashMap<VersionSupport.VersionType, String> soundVersionCache;
   private final String name;
   private float volume = 10.0F;
   private float pitch = 0.0F;

   public Sound(String var1) {
      this.name = var1;
      this.soundVersionCache = new HashMap<>();
   }

   public Sound(String var1, float var2, float var3) {
      this.name = var1;
      this.soundVersionCache = new HashMap<>();
      this.volume = var2;
      this.pitch = var3;
   }

   public Sound(String var1, String var2) {
      this.name = var1;
      this.soundVersionCache = new HashMap<>();
      this.soundVersionCache.put(VersionSupport.VersionType.valueOf(VersionSupport.getInstance().getVersion()), var2);
   }

   public Sound(String var1, String var2, float var3, float var4) {
      this.name = var1;
      this.soundVersionCache = new HashMap<>();
      this.soundVersionCache.put(VersionSupport.VersionType.valueOf(VersionSupport.getInstance().getVersion()), var2);
      this.volume = var3;
      this.pitch = var4;
   }

   public String getSoundName() {
      VersionSupport.VersionType var1 = VersionSupport.VersionType.valueOf(VersionSupport.getInstance().getVersion());
      return this.soundVersionCache.getOrDefault(var1, null);
   }

   public Sound addSupport(VersionSupport.VersionType var1, String var2) {
      this.soundVersionCache.put(var1, var2);
      return this;
   }

   public void mergeSound() {
      VersionSupport.VersionType var1 = VersionSupport.VersionType.valueOf(VersionSupport.getInstance().getVersion());
      if (!this.soundVersionCache.containsKey(var1)) {

          for (VersionSupport.VersionType var3 : this.soundVersionCache.keySet()) {
              String var4 = this.soundVersionCache.get(var3);

              try {
                  org.bukkit.Sound.valueOf(var4);
                  this.addSupport(var1, var4);
                  return;
              } catch (Exception ignored) {
              }
          }

      }
   }

   public Sound save() {
      this.mergeSound();
      soundCache.put(this.name, this);
      return this;
   }

   public float getPitch() {
      return this.pitch;
   }

   public float getVolume() {
      return this.volume;
   }

   public void play(Player var1, Location var2, float var3, float var4) {
      VersionSupport.VersionType var5 = VersionSupport.VersionType.valueOf(VersionSupport.getInstance().getVersion());
      if (!this.soundVersionCache.containsKey(var5)) {
         throw new BambooSoundError(var5);
      } else {
         var1.playSound(var2, org.bukkit.Sound.valueOf(this.soundVersionCache.get(var5)), var3, var4);
      }
   }

   public void play(Player var1, float var2, float var3) {
      this.play(var1, var1.getLocation(), var2, var3);
   }

   public void play(Player var1) {
      this.play(var1, var1.getLocation(), this.volume, this.pitch);
   }

   public void play(Player var1, Location var2) {
      this.play(var1, var2, this.volume, this.pitch);
   }

   public static Sound getSound(String var0) {
      return soundCache.getOrDefault(var0, null);
   }

   public static void playSound(Player var0, String var1, Location var2, float var3, float var4) {
      Sound var5 = getSound(var1);
      if (var5 == null) {
         throw new BambooSoundNotFound(var1);
      } else {
         var5.play(var0, var2, var3, var4);
      }
   }

   public static void playSound(Player var0, String var1, float var2, float var3) {
      Sound var4 = getSound(var1);
      if (var4 == null) {
         throw new BambooSoundNotFound(var1);
      } else {
         var4.play(var0, var2, var3);
      }
   }

   public static void playSound(Player var0, String var1) {
      Sound var2 = getSound(var1);
      if (var2 == null) {
         throw new BambooSoundNotFound(var1);
      } else {
         var2.play(var0);
      }
   }

   public static void playSound(Player var0, String var1, Location var2) {
      Sound var3 = getSound(var1);
      if (var3 == null) {
         throw new BambooSoundNotFound(var1);
      } else {
         var3.play(var0, var2);
      }
   }

   public String getName() {
      return this.name;
   }
}
