package com.github.zandy.bamboolib.database.adapter;

import com.github.zandy.bamboolib.database.Database;
import com.github.zandy.bamboolib.database.utils.BambooResultSet;
import com.github.zandy.bamboolib.database.utils.ColumnInfo;
import com.github.zandy.bamboolib.database.utils.DatabaseProfile;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class FlatFile extends Database {
   private static volatile FlatFile instance = null;
   private final HashMap<UUID, DatabaseProfile> databaseProfileMap = new HashMap<>();

   public static FlatFile getInstance() {
      if (instance == null) {
         synchronized (FlatFile.class) {
            if (instance == null) {
               instance = new FlatFile();
            }
         }
      }
      return instance;
   }

   public boolean hasAccount(UUID uuid, String key) {
      DatabaseProfile profile = getProfile(uuid);
      return profile != null && profile.contains(key);
   }

   public DatabaseProfile getProfile(UUID uuid) {
      return databaseProfileMap.computeIfAbsent(uuid, k -> new DatabaseProfile(k.toString()));
   }

   public void createPlayer(UUID uuid, String key, List<ColumnInfo> columns) {
      DatabaseProfile profile = getProfile(uuid);
      columns.stream()
              .filter(column -> !column.getColumnName().equalsIgnoreCase("UUID") && !column.getColumnName().equalsIgnoreCase("Player"))
              .forEach(column -> profile.set(key + "." + column.getColumnName(), column.getValue().replace("'", "")));
   }

   public BambooResultSet getResultSet(UUID uuid, String key) {
      HashMap<Object, Object> resultMap = new HashMap<>();
      DatabaseProfile profile = getProfile(uuid);
       if (DatabaseProfile.exists(uuid.toString()) || profile.contains(key) || !profile.getConfigurationSection(key).getKeys(false).isEmpty()) {
           profile.getConfigurationSection(key).getKeys(false).forEach(subKey -> resultMap.put(subKey, profile.get(key + "." + subKey)));
       }
       return new BambooResultSet(resultMap);
   }

   public boolean contains(UUID uuid, String key) {
      return hasAccount(uuid, key) && getProfile(uuid).contains(key);
   }

   public String getString(UUID uuid, String subKey, String key) {
      DatabaseProfile profile = getProfile(uuid);
      return hasAccount(uuid, key) && profile.contains(key + "." + subKey) ? profile.getString(key + "." + subKey) : "";
   }

   public void setString(UUID uuid, String value, String subKey, String key) {
      getProfile(uuid).set(key + "." + subKey, value);
   }

   public boolean getBoolean(UUID uuid, String subKey, String key) {
      DatabaseProfile profile = getProfile(uuid);
      return hasAccount(uuid, key) && profile.contains(key + "." + subKey) && profile.getBoolean(key + "." + subKey);
   }

   public void setBoolean(UUID uuid, boolean value, String subKey, String key) {
      getProfile(uuid).set(key + "." + subKey, value);
   }

   public int getInt(UUID uuid, String subKey, String key) {
      DatabaseProfile profile = getProfile(uuid);
      return hasAccount(uuid, key) && profile.contains(key + "." + subKey) ? profile.getInt(key + "." + subKey) : 0;
   }

   public void setInt(UUID uuid, int value, String subKey, String key) {
      getProfile(uuid).set(key + "." + subKey, value);
   }

   public long getLong(UUID uuid, String subKey, String key) {
      DatabaseProfile profile = getProfile(uuid);
      return hasAccount(uuid, key) && profile.contains(key + "." + subKey) ? profile.getLong(key + "." + subKey) : 0L;
   }

   public void setLong(UUID uuid, long value, String subKey, String key) {
      getProfile(uuid).set(key + "." + subKey, value);
   }

   public float getFloat(UUID uuid, String subKey, String key) {
      DatabaseProfile profile = getProfile(uuid);
      return hasAccount(uuid, key) && profile.contains(key + "." + subKey) ? (float) profile.getDouble(key + "." + subKey) : 0.0F;
   }

   public void setFloat(UUID uuid, float value, String subKey, String key) {
      getProfile(uuid).set(key + "." + subKey, value);
   }

   public double getDouble(UUID uuid, String subKey, String key) {
      DatabaseProfile profile = getProfile(uuid);
      return hasAccount(uuid, key) && profile.contains(key + "." + subKey) ? profile.getDouble(key + "." + subKey) : 0.0D;
   }

   public void setDouble(UUID uuid, double value, String subKey, String key) {
      getProfile(uuid).set(key + "." + subKey, value);
   }

   public void deleteProfile(UUID uuid, String key) {
      DatabaseProfile.remove(uuid.toString());
   }
}
