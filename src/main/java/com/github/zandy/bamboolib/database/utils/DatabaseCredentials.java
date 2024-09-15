package com.github.zandy.bamboolib.database.utils;

import com.github.zandy.bamboolib.utils.BambooFile;

public class DatabaseCredentials extends BambooFile {
   public DatabaseCredentials() {
      super("Database");
      this.addDefault("Enabled", false);
      this.addDefault("Host", "127.0.0.1");
      this.addDefault("Port", 3306);
      this.addDefault("Database", "MyDatabase");
      this.addDefault("SSL", false);
      this.addDefault("Username", "root");
      this.addDefault("Password", "MyPassword");
      this.addDefault("Logs", false);
      this.copyDefaults();
      this.save();
   }

   public boolean isEnabled() {
      return this.getBoolean("Enabled");
   }

   public String getHost() {
      return this.getString("Host");
   }

   public int getPort() {
      return this.getInt("Port");
   }

   public String getDatabase() {
      return this.getString("Database");
   }

   public boolean isSSLCertificateEnabled() {
      return this.getBoolean("SSL");
   }

   public String getUsername() {
      return this.getString("Username");
   }

   public String getPassword() {
      return this.getString("Password");
   }

   public boolean isLogsEnabled() {
      return this.getBoolean("Logs");
   }
}
