package com.github.zandy.bamboolib.exceptions;

public class BambooSoundNotFound extends RuntimeException {
   public BambooSoundNotFound(String var1) {
      super("The sound " + var1 + " was not found.");
   }
}
