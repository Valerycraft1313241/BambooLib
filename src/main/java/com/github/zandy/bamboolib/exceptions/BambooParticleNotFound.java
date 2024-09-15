package com.github.zandy.bamboolib.exceptions;

public class BambooParticleNotFound extends RuntimeException {
   public BambooParticleNotFound(String var1) {
      super("The particle " + var1 + " was not found.");
   }
}
