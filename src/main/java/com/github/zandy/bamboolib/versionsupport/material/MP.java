package com.github.zandy.bamboolib.versionsupport.material;

public class MP {
   private final MP.PrepType prepType;
   private String material;
   private int id;
   private int data;
   private boolean isPlaceholder;

   public MP(String material) {
      this.prepType = MP.PrepType.MAT;
      this.material = material;
   }

   public MP(int id) {
      this.prepType = MP.PrepType.ID;
      this.id = id;
   }

   public MP(int id, boolean isPlaceholder) {
      this.prepType = MP.PrepType.ID;
      this.id = id;
      this.isPlaceholder = isPlaceholder;
   }

   public MP(int id, int data) {
      this.prepType = MP.PrepType.ID_DATA;
      this.id = id;
      this.data = data;
   }

   public MP(int id, int data, boolean isPlaceholder) {
      this.prepType = MP.PrepType.ID_DATA;
      this.id = id;
      this.data = data;
      this.isPlaceholder = isPlaceholder;
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
      return this.isPlaceholder;
   }

   public enum PrepType {
      MAT,
      ID,
      ID_DATA
   }
}
