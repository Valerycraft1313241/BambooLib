package com.github.zandy.bamboolib.versionsupport.particles;

import com.github.zandy.bamboolib.versionsupport.VersionSupport;

public enum Particles {
   EXPLOSION_HUGE((new Particle("explosionHuge")).addSupport(VersionSupport.VersionType.v1_8_R3, "EXPLOSION_HUGE").save());

   private final Particle particle;

   Particles(Particle var3) {
      this.particle = var3;
   }

   public Particle getParticle() {
      return this.particle;
   }

}
