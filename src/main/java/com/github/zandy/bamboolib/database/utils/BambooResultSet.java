package com.github.zandy.bamboolib.database.utils;

import com.github.zandy.bamboolib.database.Database;
import com.github.zandy.bamboolib.exceptions.BambooResultSetException;

import java.sql.ResultSet;
import java.util.HashMap;

public class BambooResultSet {
    private ResultSet mysqlResultSet;
    private HashMap<Object, Object> flatFileResultSet;

    public BambooResultSet(ResultSet var1) {
        this.mysqlResultSet = var1;
    }

    public BambooResultSet(HashMap<Object, Object> var1) {
        this.flatFileResultSet = var1;
    }

    public boolean next() {
        switch (Database.getInstance().getDatabaseType()) {
            case MYSQL:
                try {
                    return this.mysqlResultSet.next();
                } catch (Exception var2) {
                    throw new BambooResultSetException();
                }
            case FLAT_FILE:
                return true;
            default:
                return false;
        }
    }

    public void close() {
        if (Database.getInstance().getDatabaseType().equals(Database.DatabaseType.MYSQL)) {
            try {
                this.mysqlResultSet.close();
            } catch (Exception var2) {
                throw new BambooResultSetException();
            }
        }
    }

    public String getString(String var1) {
        switch (Database.getInstance().getDatabaseType()) {
            case MYSQL:
                try {
                    return this.mysqlResultSet.getString(var1);
                } catch (Exception var3) {
                    throw new BambooResultSetException();
                }
            case FLAT_FILE:
                return String.valueOf(this.flatFileResultSet.get(var1));
            default:
                return "";
        }
    }

    public boolean getBoolean(String var1) {
        return Boolean.parseBoolean(this.getString(var1));
    }

    public int getInt(String var1) {
        switch (Database.getInstance().getDatabaseType()) {
            case MYSQL:
                try {
                    return this.mysqlResultSet.getInt(var1);
                } catch (Exception var3) {
                    throw new BambooResultSetException();
                }
            case FLAT_FILE:
                return Integer.parseInt(String.valueOf(this.flatFileResultSet.get(var1)));
            default:
                return 0;
        }
    }

    public long getLong(String var1) {
        switch (Database.getInstance().getDatabaseType()) {
            case MYSQL:
                try {
                    return this.mysqlResultSet.getLong(var1);
                } catch (Exception var3) {
                    throw new BambooResultSetException();
                }
            case FLAT_FILE:
                return Long.parseLong(String.valueOf(this.flatFileResultSet.get(var1)));
            default:
                return 0L;
        }
    }

    public float getFloat(String var1) {
        switch (Database.getInstance().getDatabaseType()) {
            case MYSQL:
                try {
                    return this.mysqlResultSet.getFloat(var1);
                } catch (Exception var3) {
                    throw new BambooResultSetException();
                }
            case FLAT_FILE:
                return Float.parseFloat(String.valueOf(this.flatFileResultSet.get(var1)));
            default:
                return 0.0F;
        }
    }

    public double getDouble(String var1) {
        switch (Database.getInstance().getDatabaseType()) {
            case MYSQL:
                try {
                    return this.mysqlResultSet.getDouble(var1);
                } catch (Exception var3) {
                    throw new BambooResultSetException();
                }
            case FLAT_FILE:
                return Double.parseDouble(String.valueOf(this.flatFileResultSet.get(var1)));
            default:
                return 0.0D;
        }
    }
}
