package com.github.zandy.bamboolib.exceptions;

public class BambooSlotOccupiedException extends RuntimeException {
   public BambooSlotOccupiedException() {
      super("You cannot set that item here. This slot is already occupied");
   }
}
