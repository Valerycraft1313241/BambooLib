package com.github.zandy.bamboolib.exceptions;

import com.github.zandy.bamboolib.versionsupport.VersionSupport;

public class BambooSoundError extends RuntimeException {
   public BambooSoundError(VersionSupport.VersionType var1) {
      super("This sound does not support version " + var1);
   }
}
