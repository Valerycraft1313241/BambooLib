package com.github.zandy.bamboolib.database.utils;

public class Column {
   private final String name;
   private ColumnType columnType;
   private int length = 0;
   private boolean notNull = false;
   private boolean primaryKey = false;
   private Object defaultValue = null;

   public Column(String name) {
      this.name = name;
   }

   public Column setType(ColumnType columnType) {
      this.columnType = columnType;
      return this;
   }

   public Column setLength(int length) {
      this.length = length;
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

   public Column setDefault(Object defaultValue) {
      this.defaultValue = defaultValue;
      return this;
   }

   public String build() {
      String space = " ";
      return this.name + space + this.columnType.name().replace("_", "") +
              (this.length == 0 ? "" : "(" + this.length + ")") +
              (this.notNull ? space + "NOT NULL" : "") +
              (this.defaultValue == null ? "" : space + "default '" + this.defaultValue + "'") +
              (this.primaryKey ? space + ", PRIMARY KEY (" + this.name + ")" : "");
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
