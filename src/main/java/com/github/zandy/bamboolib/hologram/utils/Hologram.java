package com.github.zandy.bamboolib.hologram.utils;

import org.bukkit.Location;

public abstract class Hologram {
   public abstract Hologram addLine(int index, Location location, String text);

   public Hologram refresh() {
      return null;
   }

   public abstract Hologram remove();

   public enum HologramType {
      GLOBAL,
      INDIVIDUAL;
   }
}
