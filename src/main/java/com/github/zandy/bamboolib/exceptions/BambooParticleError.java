package com.github.zandy.bamboolib.exceptions;

import com.github.zandy.bamboolib.versionsupport.VersionSupport;

public class BambooParticleError extends RuntimeException {
   public BambooParticleError(VersionSupport.VersionType var1) {
      super("This particle does not support version " + var1);
   }
}