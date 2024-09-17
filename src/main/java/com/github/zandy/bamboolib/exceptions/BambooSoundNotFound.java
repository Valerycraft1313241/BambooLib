package com.github.zandy.bamboolib.exceptions;

public class BambooSoundNotFound extends RuntimeException {
   public BambooSoundNotFound(String sound) {
      super("The sound " + sound + " was not found.");
   }
}
