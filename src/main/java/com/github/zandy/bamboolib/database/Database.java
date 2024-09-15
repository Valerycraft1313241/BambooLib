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

   public void createTable(String var1, List<Column> var2) {
   }

   public void addColumn(String var1, Column var2) {
   }

   public abstract boolean hasAccount(UUID var1, String var2);

   public abstract void createPlayer(UUID var1, String var2, List<ColumnInfo> var3);

   public abstract BambooResultSet getResultSet(UUID var1, String var2);

   public abstract boolean contains(UUID var1, String var2);

   public abstract String getString(UUID var1, String var2, String var3);

   public abstract void setString(UUID var1, String var2, String var3, String var4);

   public abstract boolean getBoolean(UUID var1, String var2, String var3);

   public abstract void setBoolean(UUID var1, boolean var2, String var3, String var4);

   public abstract int getInt(UUID var1, String var2, String var3);

   public abstract void setInt(UUID var1, int var2, String var3, String var4);

   public abstract long getLong(UUID var1, String var2, String var3);

   public abstract void setLong(UUID var1, long var2, String var4, String var5);

   public abstract float getFloat(UUID var1, String var2, String var3);

   public abstract void setFloat(UUID var1, float var2, String var3, String var4);

   public abstract double getDouble(UUID var1, String var2, String var3);

   public abstract void setDouble(UUID var1, double var2, String var4, String var5);

   public abstract void deleteProfile(UUID var1, String var2);

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

   public DatabaseProfile getProfile(UUID var1) {
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

   public static enum DatabaseType {
      MYSQL,
      FLAT_FILE;
   }
}
