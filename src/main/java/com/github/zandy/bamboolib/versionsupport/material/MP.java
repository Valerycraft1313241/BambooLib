package com.github.zandy.bamboolib.versionsupport.material;

public class MP {
   private final MP.PrepType prepType;
   private String material;
   private int id;
   private int data;
   private boolean placeholder;

   public MP(String var1) {
      this.prepType = MP.PrepType.MAT;
      this.material = var1;
   }

   public MP(int var1) {
      this.prepType = MP.PrepType.ID;
      this.id = var1;
   }

   public MP(int var1, boolean var2) {
      this.prepType = MP.PrepType.ID;
      this.id = var1;
      this.placeholder = var2;
   }

   public MP(int var1, int var2) {
      this.prepType = MP.PrepType.ID_DATA;
      this.id = var1;
      this.data = var2;
   }

   public MP(int var1, int var2, boolean var3) {
      this.prepType = MP.PrepType.ID_DATA;
      this.id = var1;
      this.data = var2;
      this.placeholder = var3;
   }

   public MP.PrepType getPrepType() {
      return this.prepType;
   }

   public String getMaterial() {
      return this.material;
   }

   public int getId() {
      return this.id;
   }

   public int getData() {
      return this.data;
   }

   public boolean isPlaceholder() {
      return this.placeholder;
   }

   public enum PrepType {
      MAT,
      ID,
      ID_DATA
   }
}
