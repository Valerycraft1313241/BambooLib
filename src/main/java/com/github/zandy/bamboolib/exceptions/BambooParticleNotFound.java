package com.github.zandy.bamboolib.exceptions;

public class BambooParticleNotFound extends RuntimeException {
   public BambooParticleNotFound(String particle) {
      super("The particle " + particle + " was not found.");
   }
}
