package com.github.zandy.bamboolib.database.adapter;

import com.github.zandy.bamboolib.database.Database;
import com.github.zandy.bamboolib.database.utils.BambooResultSet;
import com.github.zandy.bamboolib.database.utils.Column;
import com.github.zandy.bamboolib.database.utils.ColumnInfo;
import com.github.zandy.bamboolib.database.utils.DatabaseCredentials;
import com.github.zandy.bamboolib.events.database.DatabaseChangeEvent;
import com.github.zandy.bamboolib.exceptions.BambooErrorException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;

public class MySQL extends Database {
   private static MySQL instance;
   private final HashMap<UUID, List<String>> playerAccounts;
   private Connection connection;

   public static MySQL getInstance() {
      if (instance == null) {
         instance = new MySQL();
      }

      return instance;
   }

   private MySQL() {
      this.connect();
      this.playerAccounts = new HashMap();
   }

   private void connect() {
      DatabaseCredentials var1 = getDatabaseCredentials();
      HikariConfig var2 = new HikariConfig();
      var2.setJdbcUrl("jdbc:mysql://" + var1.getHost() + ":" + var1.getPort() + "/" + var1.getDatabase());
      var2.setUsername(var1.getUsername());
      var2.setPassword(var1.getPassword());

      try {
         this.connection = (new HikariDataSource(var2)).getConnection();
      } catch (Exception var4) {
         throw new BambooErrorException(var4, this.getClass(), Arrays.asList("Cannot connect to the MySQL server.", "Probably, the MySQL server is closed", "or the credentials are set wrong."));
      }
   }

   public void createTable(String var1, List<Column> var2) {
      this.toConnect();
      StringBuilder var3 = new StringBuilder();
      StringBuilder var4 = new StringBuilder();
      boolean var5 = true;
      Iterator var6 = var2.iterator();

      while(var6.hasNext()) {
         Column var7 = (Column)var6.next();
         if (var5) {
            var5 = false;
            var3 = new StringBuilder(var7.build());
            var4 = new StringBuilder(var7.getName());
         } else {
            var3.append(", ").append(var7.build());
            var4.append(", ").append(var7.getName());
         }
      }

      try {
         this.connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS `" + var1 + "` (" + var3 + ");");
         Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.CREATE, var1, var4.toString()));
      } catch (Exception var8) {
         throw new BambooErrorException(var8, this.getClass(), Arrays.asList("Cannot create Table in MySQL.", "Probably, the MySQL server is down", "or your machine cannot reach the MySQL server."));
      }
   }

   public void addColumn(String var1, Column var2) {
      this.toConnect();

      try {
         this.connection.createStatement().executeUpdate("ALTER TABLE `" + var1 + "` ADD COLUMN " + var2.build() + ";");
         Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.ALTER, var1, var2.getName()));
      } catch (Exception var4) {
      }

   }

   public boolean hasAccount(UUID var1, String var2) {
      if (this.playerAccounts.containsKey(var1) && ((List)this.playerAccounts.get(var1)).contains(var2)) {
         return true;
      } else {
         this.toConnect();

         try {
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.SELECT, var2, "Not Defined"));
            boolean var3 = (new BambooResultSet(this.connection.createStatement().executeQuery("SELECT UUID FROM `" + var2 + "` WHERE UUID = '" + var1.toString() + "';"))).next();
            if (var3) {
               List var4 = (List)this.playerAccounts.getOrDefault(var1, new ArrayList());
               var4.add(var2);
               this.playerAccounts.put(var1, var4);
            }

            return var3;
         } catch (SQLException var5) {
            throw new BambooErrorException(var5, this.getClass(), Arrays.asList("Cannot get profile from MySQL.", "Probably, the MySQL server is down", "or your machine cannot reach the MySQL server."));
         }
      }
   }

   public void createPlayer(UUID var1, String var2, List<ColumnInfo> var3) {
      this.toConnect();
      StringBuilder var4 = new StringBuilder();
      StringBuilder var5 = new StringBuilder();
      boolean var6 = true;
      Iterator var7 = var3.iterator();

      while(var7.hasNext()) {
         ColumnInfo var8 = (ColumnInfo)var7.next();
         if (var6) {
            var6 = false;
            var4 = new StringBuilder(var8.getColumnName());
            var5 = new StringBuilder(var8.getValue());
         } else {
            var4.append(",").append(var8.getColumnName());
            var5.append(",").append(var8.getValue());
         }
      }

      try {
         this.connection.createStatement().executeUpdate("INSERT INTO `" + var2 + "` (" + var4 + ") VALUES (" + var5 + ");");
         Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.INSERT, var2, var4.toString()));
      } catch (Exception var9) {
         throw new BambooErrorException(var9, this.getClass(), Arrays.asList("Cannot create Player Profile in MySQL.", "Probably, the MySQL server is down", "or your machine cannot reach the MySQL server."));
      }
   }

   public BambooResultSet getResultSet(UUID var1, String var2) {
      this.toConnect();

      try {
         BambooResultSet var3 = new BambooResultSet(this.connection.createStatement().executeQuery("SELECT * FROM `" + var2 + "` WHERE UUID = '" + var1 + "';"));
         Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.SELECT, var2, "Not Defined"));
         return var3.next() ? var3 : null;
      } catch (Exception var4) {
         throw new BambooErrorException(var4, this.getClass(), Arrays.asList("Cannot get ResultSet from the MySQL server.", "Probably, the MySQL server is down", "or your machine cannot reach the MySQL server."));
      }
   }

   public boolean contains(UUID var1, String var2) {
      this.toConnect();
      if (!this.hasAccount(var1, var2)) {
         return false;
      } else {
         try {
            BambooResultSet var3 = new BambooResultSet(this.connection.createStatement().executeQuery("SELECT * FROM `" + var2 + "` WHERE UUID = '" + var1.toString() + "';"));
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.SELECT, var2, "Not Defined"));
            return var3.next() ? var3.next() : false;
         } catch (Exception var4) {
            throw new BambooErrorException(var4, this.getClass(), Arrays.asList("Cannot check if the MySQL server contains an object.", "Probably, the MySQL server is down", "or your machine cannot reach the MySQL server."));
         }
      }
   }

   public String getString(UUID var1, String var2, String var3) {
      this.toConnect();
      if (!this.hasAccount(var1, var3)) {
         return "";
      } else {
         try {
            BambooResultSet var4 = new BambooResultSet(this.connection.createStatement().executeQuery("SELECT " + var2 + " FROM `" + var3 + "` WHERE UUID = '" + var1 + "';"));
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.SELECT, var3, var2));
            return var4.next() ? var4.getString(var2) : "";
         } catch (Exception var5) {
            throw new BambooErrorException(var5, this.getClass(), Arrays.asList("Cannot get String from the MySQL server.", "Probably, the MySQL server is down", "or your machine cannot reach the MySQL server."));
         }
      }
   }

   public void setString(UUID var1, String var2, String var3, String var4) {
      this.toConnect();
      if (this.hasAccount(var1, var4)) {
         try {
            this.connection.createStatement().execute("UPDATE `" + var4 + "` SET " + var3 + " = '" + var2 + "' WHERE UUID = '" + var1 + "';");
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.UPDATE, var4, var3));
         } catch (Exception var6) {
            throw new BambooErrorException(var6, this.getClass(), Arrays.asList("Cannot set String into the MySQL server.", "Probably, the MySQL server is down", "or your machine cannot reach the MySQL server."));
         }
      }
   }

   public boolean getBoolean(UUID var1, String var2, String var3) {
      this.toConnect();
      if (!this.hasAccount(var1, var3)) {
         return false;
      } else {
         try {
            BambooResultSet var4 = new BambooResultSet(this.connection.createStatement().executeQuery("SELECT " + var2 + " FROM `" + var3 + "` WHERE UUID = '" + var1 + "';"));
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.SELECT, var3, var2));
            return var4.next() ? Boolean.parseBoolean(var4.getString(var2)) : false;
         } catch (Exception var5) {
            throw new BambooErrorException(var5, this.getClass(), Arrays.asList("Cannot get Boolean from the MySQL server.", "Probably, the MySQL server is down", "or your machine cannot reach the MySQL server."));
         }
      }
   }

   public void setBoolean(UUID var1, boolean var2, String var3, String var4) {
      this.setString(var1, String.valueOf(var2), var3, var4);
   }

   public int getInt(UUID var1, String var2, String var3) {
      this.toConnect();
      if (!this.hasAccount(var1, var3)) {
         return 0;
      } else {
         try {
            BambooResultSet var4 = new BambooResultSet(this.connection.createStatement().executeQuery("SELECT " + var2 + " FROM `" + var3 + "` WHERE UUID = '" + var1 + "';"));
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.SELECT, var3, var2));
            return var4.next() ? var4.getInt(var2) : 0;
         } catch (Exception var5) {
            throw new BambooErrorException(var5, this.getClass(), Arrays.asList("Cannot get Integer from the MySQL server.", "Probably, the MySQL server is down", "or your machine cannot reach the MySQL server."));
         }
      }
   }

   public void setInt(UUID var1, int var2, String var3, String var4) {
      this.setString(var1, String.valueOf(var2), var3, var4);
   }

   public long getLong(UUID var1, String var2, String var3) {
      this.toConnect();
      if (!this.hasAccount(var1, var3)) {
         return 0L;
      } else {
         try {
            BambooResultSet var4 = new BambooResultSet(this.connection.createStatement().executeQuery("SELECT " + var2 + " FROM `" + var3 + "` WHERE UUID = '" + var1 + "';"));
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.SELECT, var3, var2));
            return var4.next() ? var4.getLong(var2) : 0L;
         } catch (Exception var5) {
            throw new BambooErrorException(var5, this.getClass(), Arrays.asList("Cannot get Long from the MySQL server.", "Probably, the MySQL server is down", "or your machine cannot reach the MySQL server."));
         }
      }
   }

   public void setLong(UUID var1, long var2, String var4, String var5) {
      this.setString(var1, String.valueOf(var2), var4, var5);
   }

   public float getFloat(UUID var1, String var2, String var3) {
      this.toConnect();
      if (!this.hasAccount(var1, var3)) {
         return 0.0F;
      } else {
         try {
            BambooResultSet var4 = new BambooResultSet(this.connection.createStatement().executeQuery("SELECT " + var2 + " FROM `" + var3 + "` WHERE UUID = '" + var1 + "';"));
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.SELECT, var3, var2));
            return var4.next() ? var4.getFloat(var2) : 0.0F;
         } catch (Exception var5) {
            throw new BambooErrorException(var5, this.getClass(), Arrays.asList("Cannot get Float from the MySQL server.", "Probably, the MySQL server is down", "or your machine cannot reach the MySQL server."));
         }
      }
   }

   public void setFloat(UUID var1, float var2, String var3, String var4) {
      this.setString(var1, String.valueOf(var2), var3, var4);
   }

   public double getDouble(UUID var1, String var2, String var3) {
      this.toConnect();
      if (!this.hasAccount(var1, var3)) {
         return 0.0D;
      } else {
         try {
            BambooResultSet var4 = new BambooResultSet(this.connection.createStatement().executeQuery("SELECT " + var2 + " FROM `" + var3 + "` WHERE UUID = '" + var1 + "';"));
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.SELECT, var3, var2));
            return var4.next() ? var4.getDouble(var2) : 0.0D;
         } catch (Exception var5) {
            throw new BambooErrorException(var5, this.getClass(), Arrays.asList("Cannot get Double from the MySQL server.", "Probably, the MySQL server is down", "or your machine cannot reach the MySQL server."));
         }
      }
   }

   public void setDouble(UUID var1, double var2, String var4, String var5) {
      this.setString(var1, String.valueOf(var2), var4, var5);
   }

   public void deleteProfile(UUID var1, String var2) {
      this.toConnect();

      try {
         this.connection.createStatement().executeUpdate("DELETE FROM `" + var2 + "` WHERE UUID = '" + var1.toString() + "';");
         Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.DELETE, var2, "Not Defined"));
      } catch (Exception var4) {
         throw new BambooErrorException(var4, this.getClass(), Arrays.asList("Cannot delete the given profile from the MySQL server.", "Probably, the MySQL server is down", "or your machine cannot reach the MySQL server."));
      }
   }

   public void close() {
      if (this.connection != null) {
         try {
            this.connection.close();
         } catch (Exception var2) {
            throw new BambooErrorException(var2, this.getClass(), Arrays.asList("Cannot close the connection to the MySQL server.", "Probably, the MySQL server is down", "or your machine cannot reach the MySQL server."));
         }
      }
   }

   private void toConnect() {
      try {
         if (this.connection == null || this.connection.isClosed()) {
            this.connect();
         }

      } catch (Throwable var2) {
         var2.printStackTrace();
      }
   }
}
