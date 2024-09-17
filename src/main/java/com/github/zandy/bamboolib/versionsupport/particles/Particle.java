package com.github.zandy.bamboolib.versionsupport.particles;

import com.github.zandy.bamboolib.exceptions.BambooParticleError;
import com.github.zandy.bamboolib.versionsupport.VersionSupport;
import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Particle {
   public static HashMap<String, Particle> particleCache = new HashMap<>();
   private final HashMap<VersionSupport.VersionType, String> particleVersionCache;
   private final String name;

   public Particle(String name) {
      this.name = name;
      this.particleVersionCache = new HashMap<>();
   }

   public String getParticleName() {
      VersionSupport.VersionType versionType = VersionSupport.VersionType.valueOf(VersionSupport.getInstance().getVersion());
      return this.particleVersionCache.getOrDefault(versionType, null);
   }

   public Particle addSupport(VersionSupport.VersionType versionType, String particleName) {
      this.particleVersionCache.put(versionType, particleName);
      return this;
   }

   public void mergeParticle() {
      VersionSupport.VersionType currentVersion = VersionSupport.VersionType.valueOf(VersionSupport.getInstance().getVersion());
      if (!this.particleVersionCache.containsKey(currentVersion)) {
         for (VersionSupport.VersionType versionType : this.particleVersionCache.keySet()) {
            String particleName = this.particleVersionCache.get(versionType);

            try {
               this.addSupport(currentVersion, particleName);
               return;
            } catch (Exception ignored) {
            }
         }
      }
   }

   public Particle save() {
      this.mergeParticle();
      particleCache.put(this.name, this);
      return this;
   }

   public void spawn(Player player, Location location, int count) {
      VersionSupport.VersionType versionType = VersionSupport.VersionType.valueOf(VersionSupport.getInstance().getVersion());
      if (!this.particleVersionCache.containsKey(versionType)) {
         throw new BambooParticleError(versionType);
      } else {
         VersionSupport.getInstance().spawnParticle(player, location, this.particleVersionCache.get(versionType), count);
      }
   }

   public void spawn(Player player, int count) {
      VersionSupport.VersionType versionType = VersionSupport.VersionType.valueOf(VersionSupport.getInstance().getVersion());
      if (!this.particleVersionCache.containsKey(versionType)) {
         throw new BambooParticleError(versionType);
      } else {
         VersionSupport.getInstance().spawnParticle(player, player.getLocation(), this.particleVersionCache.get(versionType), count);
      }
   }

   public static Particle getBambooParticle(String name) {
      return particleCache.getOrDefault(name, null);
   }

   public String getName() {
      return this.name;
   }
}
