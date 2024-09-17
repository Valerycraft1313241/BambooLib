package com.github.zandy.bamboolib.database;

import com.github.zandy.bamboolib.database.adapter.FlatFile;
import com.github.zandy.bamboolib.database.adapter.MySQL;
import com.github.zandy.bamboolib.database.utils.BambooResultSet;
import com.github.zandy.bamboolib.database.utils.Column;
import com.github.zandy.bamboolib.database.utils.ColumnInfo;
import com.github.zandy.bamboolib.database.utils.DatabaseCredentials;
import com.github.zandy.bamboolib.database.utils.DatabaseLog;
import com.github.zandy.bamboolib.database.utils.DatabaseProfile;
import java.util.List;
import java.util.UUID;

public abstract class Database {
   private static Database instance = null;
   private static DatabaseCredentials databaseCredentials = null;
   private Database.DatabaseType databaseType = null;

   public void createTable(String tableName, List<Column> columns) {
   }

   public void addColumn(String tableName, Column column) {
   }

   public abstract boolean hasAccount(UUID uuid, String accountName);

   public abstract void createPlayer(UUID uuid, String playerName, List<ColumnInfo> columnInfoList);

   public abstract BambooResultSet getResultSet(UUID uuid, String query);

   public abstract boolean contains(UUID uuid, String query);

   public abstract String getString(UUID uuid, String tableName, String columnName);

   public abstract void setString(UUID uuid, String tableName, String columnName, String value);

   public abstract boolean getBoolean(UUID uuid, String tableName, String columnName);

   public abstract void setBoolean(UUID uuid, boolean value, String tableName, String columnName);

   public abstract int getInt(UUID uuid, String tableName, String columnName);

   public abstract void setInt(UUID uuid, int value, String tableName, String columnName);

   public abstract long getLong(UUID uuid, String tableName, String columnName);

   public abstract void setLong(UUID uuid, long value, String tableName, String columnName);

   public abstract float getFloat(UUID uuid, String tableName, String columnName);

   public abstract void setFloat(UUID uuid, float value, String tableName, String columnName);

   public abstract double getDouble(UUID uuid, String tableName, String columnName);

   public abstract void setDouble(UUID uuid, double value, String tableName, String columnName);

   public abstract void deleteProfile(UUID uuid, String profileName);

   public void close() {
   }

   public static void init() {
      databaseCredentials = new DatabaseCredentials();
      if (databaseCredentials.isEnabled()) {
         instance = MySQL.getInstance();
         instance.databaseType = Database.DatabaseType.MYSQL;
         if (databaseCredentials.isLogsEnabled()) {
            new DatabaseLog();
         }

      } else {
         instance = FlatFile.getInstance();
         instance.databaseType = Database.DatabaseType.FLAT_FILE;
      }
   }

   public DatabaseProfile getProfile(UUID uuid) {
      return null;
   }

   public static Database getInstance() {
      return instance;
   }

   public static DatabaseCredentials getDatabaseCredentials() {
      return databaseCredentials;
   }

   public Database.DatabaseType getDatabaseType() {
      return this.databaseType;
   }

   public enum DatabaseType {
      MYSQL,
      FLAT_FILE;
   }
}
