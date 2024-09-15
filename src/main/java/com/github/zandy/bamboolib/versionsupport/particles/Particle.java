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

   public Particle(String var1) {
      this.name = var1;
      this.particleVersionCache = new HashMap<>();
   }

   public String getParticleName() {
      VersionSupport.VersionType var1 = VersionSupport.VersionType.valueOf(VersionSupport.getInstance().getVersion());
      return this.particleVersionCache.getOrDefault(var1, null);
   }

   public Particle addSupport(VersionSupport.VersionType var1, String var2) {
      this.particleVersionCache.put(var1, var2);
      return this;
   }

   public void mergeParticle() {
      VersionSupport.VersionType var1 = VersionSupport.VersionType.valueOf(VersionSupport.getInstance().getVersion());
      if (!this.particleVersionCache.containsKey(var1)) {

          for (VersionSupport.VersionType var3 : this.particleVersionCache.keySet()) {
              String var4 = this.particleVersionCache.get(var3);

              try {
                  this.addSupport(var1, var4);
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

   public void spawn(Player var1, Location var2, int var3) {
      VersionSupport.VersionType var4 = VersionSupport.VersionType.valueOf(VersionSupport.getInstance().getVersion());
      if (!this.particleVersionCache.containsKey(var4)) {
         throw new BambooParticleError(var4);
      } else {
         VersionSupport.getInstance().spawnParticle(var1, var2, this.particleVersionCache.get(var4), var3);
      }
   }

   public void spawn(Player var1, int var2) {
      VersionSupport.VersionType var3 = VersionSupport.VersionType.valueOf(VersionSupport.getInstance().getVersion());
      if (!this.particleVersionCache.containsKey(var3)) {
         throw new BambooParticleError(var3);
      } else {
         VersionSupport.getInstance().spawnParticle(var1, var1.getLocation(), this.particleVersionCache.get(var3), var2);
      }
   }

   public static Particle getBambooParticle(String var0) {
      return particleCache.getOrDefault(var0, null);
   }

   public String getName() {
      return this.name;
   }
}
