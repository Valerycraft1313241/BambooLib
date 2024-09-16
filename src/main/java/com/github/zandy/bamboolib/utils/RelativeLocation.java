package com.github.zandy.bamboolib.utils;

import org.bukkit.Location;

public class RelativeLocation {
   private final float relativeX;
   private final float relativeY;
   private final float relativeZ;

   public RelativeLocation(float x, float y, float z) {
      this.relativeX = x;
      this.relativeY = y;
      this.relativeZ = z;
   }

   public RelativeLocation(Location baseLocation, Location targetLocation) {
      this.relativeX = (float) (targetLocation.getBlockX() - baseLocation.getBlockX());
      this.relativeY = (float) (targetLocation.getBlockY() - baseLocation.getBlockY());
      this.relativeZ = (float) (targetLocation.getBlockZ() - baseLocation.getBlockZ());
   }

   public float getRelativeX() {
      return this.relativeX;
   }

   public float getRelativeY() {
      return this.relativeY;
   }

   public float getRelativeZ() {
      return this.relativeZ;
   }

   public Location getLocationRelativeTo(Location baseLocation) {
      return new Location(
              baseLocation.getWorld(),
              baseLocation.getBlockX() + this.relativeX,
              baseLocation.getBlockY() + this.relativeY,
              baseLocation.getBlockZ() + this.relativeZ
      );
   }
}
