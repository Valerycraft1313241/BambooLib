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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;

public class MySQL extends Database {
   private static volatile MySQL instance;
   private final HashMap<UUID, List<String>> playerAccounts;
   private Connection connection;

   public static MySQL getInstance() {
      if (instance == null) {
         synchronized (MySQL.class) {
            if (instance == null) {
               instance = new MySQL();
            }
         }
      }
      return instance;
   }

   private MySQL() {
      this.connect();
      this.playerAccounts = new HashMap<>();
   }

   private void connect() {
      DatabaseCredentials credentials = getDatabaseCredentials();
      HikariConfig hikariConfig = new HikariConfig();
      hikariConfig.setJdbcUrl("jdbc:mysql://" + credentials.getHost() + ":" + credentials.getPort() + "/" + credentials.getDatabase());
      hikariConfig.setUsername(credentials.getUsername());
      hikariConfig.setPassword(credentials.getPassword());

      try {
         this.connection = (new HikariDataSource(hikariConfig)).getConnection();
      } catch (Exception e) {
         throw new BambooErrorException(e, this.getClass(), Arrays.asList("Cannot connect to the MySQL server.", "Probably, the MySQL server is closed", "or the credentials are set wrong."));
      }
   }

   public void createTable(String tableName, List<Column> columns) {
      this.toConnect();
      StringBuilder columnDefinitions = new StringBuilder();
      StringBuilder columnNames = new StringBuilder();
      boolean first = true;

      for (Column column : columns) {
         if (first) {
            first = false;
            columnDefinitions.append(column.build());
            columnNames.append(column.getName());
         } else {
            columnDefinitions.append(", ").append(column.build());
            columnNames.append(", ").append(column.getName());
         }
      }

      try (Statement statement = this.connection.createStatement()) {
         statement.executeUpdate("CREATE TABLE IF NOT EXISTS `" + tableName + "` (" + columnDefinitions + ");");
         Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.CREATE, tableName, columnNames.toString()));
      } catch (Exception e) {
         throw new BambooErrorException(e, this.getClass(), Arrays.asList("Cannot create Table in MySQL.", "Probably, the MySQL server is down", "or your machine cannot reach the MySQL server."));
      }
   }

   public void addColumn(String tableName, Column column) {
      this.toConnect();

      try (Statement statement = this.connection.createStatement()) {
         statement.executeUpdate("ALTER TABLE `" + tableName + "` ADD COLUMN " + column.build() + ";");
         Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.ALTER, tableName, column.getName()));
      } catch (Exception ignored) {
      }
   }

   public boolean hasAccount(UUID uuid, String tableName) {
      if (this.playerAccounts.containsKey(uuid) && this.playerAccounts.get(uuid).contains(tableName)) {
         return true;
      } else {
         this.toConnect();

         try (Statement statement = this.connection.createStatement()) {
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.SELECT, tableName, "Not Defined"));
            boolean exists = (new BambooResultSet(statement.executeQuery("SELECT UUID FROM `" + tableName + "` WHERE UUID = '" + uuid.toString() + "';"))).next();
            if (exists) {
               List<String> playerAccount = this.playerAccounts.getOrDefault(uuid, new ArrayList<>());
               playerAccount.add(tableName);
               this.playerAccounts.put(uuid, playerAccount);
            }
            return exists;
         } catch (SQLException e) {
            throw new BambooErrorException(e, this.getClass(), Arrays.asList("Cannot get profile from MySQL.", "Probably, the MySQL server is down", "or your machine cannot reach the MySQL server."));
         }
      }
   }

   public void createPlayer(UUID uuid, String tableName, List<ColumnInfo> columns) {
      this.toConnect();
      StringBuilder columnNames = new StringBuilder();
      StringBuilder columnValues = new StringBuilder();
      boolean first = true;

      for (ColumnInfo column : columns) {
         if (first) {
            first = false;
            columnNames.append(column.getColumnName());
            columnValues.append(column.getValue());
         } else {
            columnNames.append(",").append(column.getColumnName());
            columnValues.append(",").append(column.getValue());
         }
      }

      try (Statement statement = this.connection.createStatement()) {
         statement.executeUpdate("INSERT INTO `" + tableName + "` (" + columnNames + ") VALUES (" + columnValues + ");");
         Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.INSERT, tableName, columnNames.toString()));
      } catch (Exception e) {
         throw new BambooErrorException(e, this.getClass(), Arrays.asList("Cannot create Player Profile in MySQL.", "Probably, the MySQL server is down", "or your machine cannot reach the MySQL server."));
      }
   }

   public BambooResultSet getResultSet(UUID uuid, String tableName) {
      this.toConnect();

      try (Statement statement = this.connection.createStatement()) {
         BambooResultSet resultSet = new BambooResultSet(statement.executeQuery("SELECT * FROM `" + tableName + "` WHERE UUID = '" + uuid + "';"));
         Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.SELECT, tableName, "Not Defined"));
         return resultSet.next() ? resultSet : null;
      } catch (Exception e) {
         throw new BambooErrorException(e, this.getClass(), Arrays.asList("Cannot get ResultSet from the MySQL server.", "Probably, the MySQL server is down", "or your machine cannot reach the MySQL server."));
      }
   }

   public boolean contains(UUID uuid, String tableName) {
      this.toConnect();
      if (!this.hasAccount(uuid, tableName)) {
         return false;
      } else {
         try (Statement statement = this.connection.createStatement()) {
            BambooResultSet resultSet = new BambooResultSet(statement.executeQuery("SELECT * FROM `" + tableName + "` WHERE UUID = '" + uuid.toString() + "';"));
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.SELECT, tableName, "Not Defined"));
            return resultSet.next() && resultSet.next();
         } catch (Exception e) {
            throw new BambooErrorException(e, this.getClass(), Arrays.asList("Cannot check if the MySQL server contains an object.", "Probably, the MySQL server is down", "or your machine cannot reach the MySQL server."));
         }
      }
   }

   public String getString(UUID uuid, String column, String table) {
      this.toConnect();
      if (!this.hasAccount(uuid, table)) {
         return "";
      } else {
         try (Statement statement = this.connection.createStatement()) {
            BambooResultSet resultSet = new BambooResultSet(statement.executeQuery("SELECT " + column + " FROM `" + table + "` WHERE UUID = '" + uuid + "';"));
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.SELECT, table, column));
            return resultSet.next() ? resultSet.getString(column) : "";
         } catch (Exception e) {
            throw new BambooErrorException(e, this.getClass(), Arrays.asList("Cannot get String from the MySQL server.", "Probably, the MySQL server is down", "or your machine cannot reach the MySQL server."));
         }
      }
   }

   public void setString(UUID uuid, String value, String column, String table) {
      this.toConnect();
      if (this.hasAccount(uuid, table)) {
         try (Statement statement = this.connection.createStatement()) {
            statement.execute("UPDATE `" + table + "` SET " + column + " = '" + value + "' WHERE UUID = '" + uuid + "';");
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.UPDATE, table, column));
         } catch (Exception e) {
            throw new BambooErrorException(e, this.getClass(), Arrays.asList("Cannot set String into the MySQL server.", "Probably, the MySQL server is down", "or your machine cannot reach the MySQL server."));
         }
      }
   }

   public boolean getBoolean(UUID uuid, String column, String table) {
      this.toConnect();
      if (!this.hasAccount(uuid, table)) {
         return false;
      } else {
         try (Statement statement = this.connection.createStatement()) {
            BambooResultSet resultSet = new BambooResultSet(statement.executeQuery("SELECT " + column + " FROM `" + table + "` WHERE UUID = '" + uuid + "';"));
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.SELECT, table, column));
            return resultSet.next() && Boolean.parseBoolean(resultSet.getString(column));
         } catch (Exception e) {
            throw new BambooErrorException(e, this.getClass(), Arrays.asList("Cannot get Boolean from the MySQL server.", "Probably, the MySQL server is down", "or your machine cannot reach the MySQL server."));
         }
      }
   }

   public void setBoolean(UUID uuid, boolean value, String column, String table) {
      this.setString(uuid, String.valueOf(value), column, table);
   }

   public int getInt(UUID uuid, String column, String table) {
      this.toConnect();
      if (!this.hasAccount(uuid, table)) {
         return 0;
      } else {
         try (Statement statement = this.connection.createStatement()) {
            BambooResultSet resultSet = new BambooResultSet(statement.executeQuery("SELECT " + column + " FROM `" + table + "` WHERE UUID = '" + uuid + "';"));
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.SELECT, table, column));
            return resultSet.next() ? resultSet.getInt(column) : 0;
         } catch (Exception e) {
            throw new BambooErrorException(e, this.getClass(), Arrays.asList("Cannot get Integer from the MySQL server.", "Probably, the MySQL server is down", "or your machine cannot reach the MySQL server."));
         }
      }
   }

   public void setInt(UUID uuid, int value, String column, String table) {
      this.setString(uuid, String.valueOf(value), column, table);
   }

   public long getLong(UUID uuid, String column, String table) {
      this.toConnect();
      if (!this.hasAccount(uuid, table)) {
         return 0L;
      } else {
         try (Statement statement = this.connection.createStatement()) {
            BambooResultSet resultSet = new BambooResultSet(statement.executeQuery("SELECT " + column + " FROM `" + table + "` WHERE UUID = '" + uuid + "';"));
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.SELECT, table, column));
            return resultSet.next() ? resultSet.getLong(column) : 0L;
         } catch (Exception e) {
            throw new BambooErrorException(e, this.getClass(), Arrays.asList("Cannot get Long from the MySQL server.", "Probably, the MySQL server is down", "or your machine cannot reach the MySQL server."));
         }
      }
   }

   public void setLong(UUID uuid, long value, String column, String table) {
      this.setString(uuid, String.valueOf(value), column, table);
   }

   public float getFloat(UUID uuid, String column, String table) {
      this.toConnect();
      if (!this.hasAccount(uuid, table)) {
         return 0.0F;
      } else {
         try (Statement statement = this.connection.createStatement()) {
            BambooResultSet resultSet = new BambooResultSet(statement.executeQuery("SELECT " + column + " FROM `" + table + "` WHERE UUID = '" + uuid + "';"));
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.SELECT, table, column));
            return resultSet.next() ? resultSet.getFloat(column) : 0.0F;
         } catch (Exception e) {
            throw new BambooErrorException(e, this.getClass(), Arrays.asList("Cannot get Float from the MySQL server.", "Probably, the MySQL server is down", "or your machine cannot reach the MySQL server."));
         }
      }
   }

   public void setFloat(UUID uuid, float value, String column, String table) {
      this.setString(uuid, String.valueOf(value), column, table);
   }

   public double getDouble(UUID uuid, String column, String table) {
      this.toConnect();
      if (!this.hasAccount(uuid, table)) {
         return 0.0D;
      } else {
         try (Statement statement = this.connection.createStatement()) {
            BambooResultSet resultSet = new BambooResultSet(statement.executeQuery("SELECT " + column + " FROM `" + table + "` WHERE UUID = '" + uuid + "';"));
            Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.SELECT, table, column));
            return resultSet.next() ? resultSet.getDouble(column) : 0.0D;
         } catch (Exception e) {
            throw new BambooErrorException(e, this.getClass(), Arrays.asList("Cannot get Double from the MySQL server.", "Probably, the MySQL server is down", "or your machine cannot reach the MySQL server."));
         }
      }
   }

   public void setDouble(UUID uuid, double value, String column, String table) {
      this.setString(uuid, String.valueOf(value), column, table);
   }

   public void deleteProfile(UUID uuid, String table) {
      this.toConnect();

      try (Statement statement = this.connection.createStatement()) {
         statement.executeUpdate("DELETE FROM `" + table + "` WHERE UUID = '" + uuid.toString() + "';");
         Bukkit.getPluginManager().callEvent(new DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction.DELETE, table, "Not Defined"));
      } catch (Exception e) {
         throw new BambooErrorException(e, this.getClass(), Arrays.asList("Cannot delete the given profile from the MySQL server.", "Probably, the MySQL server is down", "or your machine cannot reach the MySQL server."));
      }
   }


   public void close() {
      if (this.connection != null) {
         try {
            this.connection.close();
         } catch (Exception exception) {
            throw new BambooErrorException(exception, this.getClass(), Arrays.asList("Cannot close the connection to the MySQL server.", "Probably, the MySQL server is down", "or your machine cannot reach the MySQL server."));
         }
      }
   }

   private void toConnect() {
      try {
         if (this.connection == null || this.connection.isClosed()) {
            this.connect();
         }

      } catch (Throwable throwable) {
         throwable.printStackTrace();
      }
   }
}
