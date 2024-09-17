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

   public Sound(String name) {
      this.name = name;
      this.soundVersionCache = new HashMap<>();
   }

   public Sound(String name, float volume, float pitch) {
      this.name = name;
      this.soundVersionCache = new HashMap<>();
      this.volume = volume;
      this.pitch = pitch;
   }

   public Sound(String name, String versionSound) {
      this.name = name;
      this.soundVersionCache = new HashMap<>();
      this.soundVersionCache.put(VersionSupport.VersionType.valueOf(VersionSupport.getInstance().getVersion()), versionSound);
   }

   public Sound(String name, String versionSound, float volume, float pitch) {
      this.name = name;
      this.soundVersionCache = new HashMap<>();
      this.soundVersionCache.put(VersionSupport.VersionType.valueOf(VersionSupport.getInstance().getVersion()), versionSound);
      this.volume = volume;
      this.pitch = pitch;
   }

   public String getSoundName() {
      VersionSupport.VersionType versionType = VersionSupport.VersionType.valueOf(VersionSupport.getInstance().getVersion());
      return this.soundVersionCache.getOrDefault(versionType, null);
   }

   public Sound addSupport(VersionSupport.VersionType versionType, String versionSound) {
      this.soundVersionCache.put(versionType, versionSound);
      return this;
   }

   public void mergeSound() {
      VersionSupport.VersionType currentVersion = VersionSupport.VersionType.valueOf(VersionSupport.getInstance().getVersion());
      if (!this.soundVersionCache.containsKey(currentVersion)) {
         for (VersionSupport.VersionType versionType : this.soundVersionCache.keySet()) {
            String versionSound = this.soundVersionCache.get(versionType);

            try {
               org.bukkit.Sound.valueOf(versionSound);
               this.addSupport(currentVersion, versionSound);
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

   public void play(Player player, Location location, float volume, float pitch) {
      VersionSupport.VersionType versionType = VersionSupport.VersionType.valueOf(VersionSupport.getInstance().getVersion());
      if (!this.soundVersionCache.containsKey(versionType)) {
         throw new BambooSoundError(versionType);
      } else {
         player.playSound(location, org.bukkit.Sound.valueOf(this.soundVersionCache.get(versionType)), volume, pitch);
      }
   }

   public void play(Player player, float volume, float pitch) {
      this.play(player, player.getLocation(), volume, pitch);
   }

   public void play(Player player) {
      this.play(player, player.getLocation(), this.volume, this.pitch);
   }

   public void play(Player player, Location location) {
      this.play(player, location, this.volume, this.pitch);
   }

   public static Sound getSound(String name) {
      return soundCache.getOrDefault(name, null);
   }

   public static void playSound(Player player, String soundName, Location location, float volume, float pitch) {
      Sound sound = getSound(soundName);
      if (sound == null) {
         throw new BambooSoundNotFound(soundName);
      } else {
         sound.play(player, location, volume, pitch);
      }
   }

   public static void playSound(Player player, String soundName, float volume, float pitch) {
      Sound sound = getSound(soundName);
      if (sound == null) {
         throw new BambooSoundNotFound(soundName);
      } else {
         sound.play(player, volume, pitch);
      }
   }

   public static void playSound(Player player, String soundName) {
      Sound sound = getSound(soundName);
      if (sound == null) {
         throw new BambooSoundNotFound(soundName);
      } else {
         sound.play(player);
      }
   }

   public static void playSound(Player player, String soundName, Location location) {
      Sound sound = getSound(soundName);
      if (sound == null) {
         throw new BambooSoundNotFound(soundName);
      } else {
         sound.play(player, location);
      }
   }

   public String getName() {
      return this.name;
   }
}
