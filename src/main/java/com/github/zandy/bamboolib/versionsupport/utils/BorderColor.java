package com.github.zandy.bamboolib.versionsupport.utils;

public enum BorderColor {
   RED(0.0D, 1.0D),
   GREEN(0.2D, 0.1D);

   double size;
   double sizeTo;

   BorderColor(double size, double sizeTo) {
      this.size = size;
      this.sizeTo = sizeTo;
   }

   BorderColor() {
   }

   public double formatSize(int size2) {
      return (double)size2 - this.size;
   }

   public double formatSizeTo(int size2) {
      return (double)size2 - this.sizeTo;
   }
}
