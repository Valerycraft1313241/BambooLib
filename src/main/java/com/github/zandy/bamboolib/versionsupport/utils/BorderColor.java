package com.github.zandy.bamboolib.versionsupport.utils;

public enum BorderColor {
   RED(0.0D, 1.0D),
   GREEN(0.2D, 0.1D);

   double size;
   double sizeTo;

   BorderColor(double var3, double var5) {
      this.size = var3;
      this.sizeTo = var5;
   }

   BorderColor() {
   }

   public double formatSize(int var1) {
      return (double)var1 - this.size;
   }

   public double formatSizeTo(int var1) {
      return (double)var1 - this.sizeTo;
   }
}
