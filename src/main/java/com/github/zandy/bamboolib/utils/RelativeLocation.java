package com.github.zandy.bamboolib.utils;

import org.bukkit.Location;

public class RelativeLocation {
   private final float x;
   private final float y;
   private final float z;

   public RelativeLocation(float var1, float var2, float var3) {
      this.x = var1;
      this.y = var2;
      this.z = var3;
   }

   public RelativeLocation(Location var1, Location var2) {
      this.x = (float)(var2.getBlockX() - var1.getBlockX());
      this.y = (float)(var2.getBlockY() - var1.getBlockY());
      this.z = (float)(var2.getBlockZ() - var1.getBlockZ());
   }

   public float getRelativeX() {
      return this.x;
   }

   public float getRelativeY() {
      return this.y;
   }

   public float getRelativeZ() {
      return this.z;
   }

   public Location getLocationRelativeTo(Location var1) {
      return new Location(var1.getWorld(), (double)((float)var1.getBlockX() + this.x), (double)((float)var1.getBlockY() + this.y), (double)((float)var1.getBlockZ() + this.z));
   }
}
