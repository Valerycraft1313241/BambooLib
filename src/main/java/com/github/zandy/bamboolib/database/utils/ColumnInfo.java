package com.github.zandy.bamboolib.database.utils;

public class ColumnInfo {
   private final String columnName;
   private final Object value;

   public ColumnInfo(String var1, Object var2) {
      this.columnName = var1;
      this.value = var2;
   }

   public String getValue() {
      return "'" + this.value + "'";
   }

   public String getColumnName() {
      return this.columnName;
   }
}
