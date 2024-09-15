package com.github.zandy.bamboolib.database.adapter;

import com.github.zandy.bamboolib.database.Database;
import com.github.zandy.bamboolib.database.utils.BambooResultSet;
import com.github.zandy.bamboolib.database.utils.ColumnInfo;
import com.github.zandy.bamboolib.database.utils.DatabaseProfile;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class FlatFile extends Database {
   private static FlatFile instance = null;
   private final HashMap<UUID, DatabaseProfile> databaseProfileMap = new HashMap();

   public static FlatFile getInstance() {
      if (instance == null) {
         instance = new FlatFile();
      }

      return instance;
   }

   public boolean hasAccount(UUID var1, String var2) {
      return this.getProfile(var1) == null ? false : this.getProfile(var1).contains(var2);
   }

   public DatabaseProfile getProfile(UUID var1) {
      if (this.databaseProfileMap.containsKey(var1)) {
         return (DatabaseProfile)this.databaseProfileMap.get(var1);
      } else {
         DatabaseProfile var2 = new DatabaseProfile(var1.toString());
         this.databaseProfileMap.put(var1, var2);
         return var2;
      }
   }

   public void createPlayer(UUID var1, String var2, List<ColumnInfo> var3) {
      var3.stream().filter((var0) -> {
         return !var0.getColumnName().equalsIgnoreCase("UUID") && !var0.getColumnName().equalsIgnoreCase("Player");
      }).forEach((var3x) -> {
         this.getProfile(var1).set(var2 + "." + var3x.getColumnName(), var3x.getValue().replace("'", ""));
      });
   }

   public BambooResultSet getResultSet(UUID var1, String var2) {
      HashMap var3 = new HashMap();
      DatabaseProfile var4 = this.getProfile(var1);
      if (!DatabaseProfile.exists(var1.toString()) && !var4.contains(var2) && var4.getConfigurationSection(var2).getKeys(false).size() != 0) {
         return new BambooResultSet(var3);
      } else {
         var4.getConfigurationSection(var2).getKeys(false).forEach((var3x) -> {
            var3.put(var3x, var4.get(var2 + "." + var3x));
         });
         return new BambooResultSet(var3);
      }
   }

   public boolean contains(UUID var1, String var2) {
      return !this.hasAccount(var1, var2) ? false : this.getProfile(var1).contains(var2);
   }

   public String getString(UUID var1, String var2, String var3) {
      if (!this.hasAccount(var1, var3)) {
         return "";
      } else {
         return !this.getProfile(var1).contains(var3 + "." + var2) ? "" : this.getProfile(var1).getString(var3 + "." + var2);
      }
   }

   public void setString(UUID var1, String var2, String var3, String var4) {
      this.getProfile(var1).set(var4 + "." + var3, var2);
   }

   public boolean getBoolean(UUID var1, String var2, String var3) {
      if (!this.hasAccount(var1, var3)) {
         return false;
      } else {
         return !this.getProfile(var1).contains(var3 + "." + var2) ? false : this.getProfile(var1).getBoolean(var3 + "." + var2);
      }
   }

   public void setBoolean(UUID var1, boolean var2, String var3, String var4) {
      this.getProfile(var1).set(var4 + "." + var3, var2);
   }

   public int getInt(UUID var1, String var2, String var3) {
      if (!this.hasAccount(var1, var3)) {
         return 0;
      } else {
         return !this.getProfile(var1).contains(var3 + "." + var2) ? 0 : this.getProfile(var1).getInt(var3 + "." + var2);
      }
   }

   public void setInt(UUID var1, int var2, String var3, String var4) {
      this.getProfile(var1).set(var4 + "." + var3, var2);
   }

   public long getLong(UUID var1, String var2, String var3) {
      if (!this.hasAccount(var1, var3)) {
         return 0L;
      } else {
         return !this.getProfile(var1).contains(var3 + "." + var2) ? 0L : this.getProfile(var1).getLong(var3 + "." + var2);
      }
   }

   public void setLong(UUID var1, long var2, String var4, String var5) {
      this.getProfile(var1).set(var5 + "." + var4, var2);
   }

   public float getFloat(UUID var1, String var2, String var3) {
      if (!this.hasAccount(var1, var3)) {
         return 0.0F;
      } else {
         return !this.getProfile(var1).contains(var3 + "." + var2) ? 0.0F : (float)this.getProfile(var1).getDouble(var3 + "." + var2);
      }
   }

   public void setFloat(UUID var1, float var2, String var3, String var4) {
      this.getProfile(var1).set(var4 + "." + var3, var2);
   }

   public double getDouble(UUID var1, String var2, String var3) {
      if (!this.hasAccount(var1, var3)) {
         return 0.0D;
      } else {
         return !this.getProfile(var1).contains(var3 + "." + var2) ? 0.0D : this.getProfile(var1).getDouble(var3 + "." + var2);
      }
   }

   public void setDouble(UUID var1, double var2, String var4, String var5) {
      this.getProfile(var1).set(var5 + "." + var4, var2);
   }

   public void deleteProfile(UUID var1, String var2) {
      DatabaseProfile.remove(var1.toString());
   }
}
