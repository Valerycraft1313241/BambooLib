package com.github.zandy.bamboolib.database.utils;

public class Column {
   private final String name;
   private Column.ColumnType columnType;
   private int length = 0;
   private boolean notNull = false;
   private boolean primaryKey = false;
   private Object def = null;

   public Column(String var1) {
      this.name = var1;
   }

   public Column setType(Column.ColumnType var1) {
      this.columnType = var1;
      return this;
   }

   public Column setLength(int var1) {
      this.length = var1;
      return this;
   }

   public Column setNotNull() {
      this.notNull = true;
      return this;
   }

   public Column setPrimaryKey() {
      this.primaryKey = true;
      return this;
   }

   public Column setDefault(Object var1) {
      this.def = var1;
      return this;
   }

   public String build() {
      String var1 = " ";
      return this.name + var1 + this.columnType.name().replace("_", "") + (this.length == 0 ? "" : "(" + this.length + ")") + (this.notNull ? var1 + "NOT NULL" : "") + (this.def == null ? "" : var1 + "default '" + this.def + "'") + (this.primaryKey ? var1 + ", PRIMARY KEY (" + this.name + ")" : "");
   }

   public String getName() {
      return this.name;
   }

   public enum ColumnType {
      VARCHAR,
      LONG_TEXT,
      INT,
      LONG,
      FLOAT,
      DOUBLE;
   }
}
