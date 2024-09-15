package com.github.zandy.bamboolib.hologram.utils;

import org.bukkit.Location;

public abstract class Hologram {
   public abstract Hologram addLine(int var1, Location var2, String var3);

   public Hologram refresh() {
      return null;
   }

   public abstract Hologram remove();

   public static enum HologramType {
      GLOBAL,
      INDIVIDUAL;
   }
}
