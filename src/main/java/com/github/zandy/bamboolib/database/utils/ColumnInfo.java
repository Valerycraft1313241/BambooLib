package com.github.zandy.bamboolib.database.utils;

public class ColumnInfo {
   private final String columnName;
   private final Object value;

   public ColumnInfo(String columnName, Object value) {
      this.columnName = columnName;
      this.value = value;
   }

   public String getValue() {
      return "'" + this.value + "'";
   }

   public String getColumnName() {
      return this.columnName;
   }
}
