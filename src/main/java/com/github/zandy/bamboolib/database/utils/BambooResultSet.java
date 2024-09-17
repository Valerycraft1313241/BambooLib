package com.github.zandy.bamboolib.database.utils;

import com.github.zandy.bamboolib.database.Database;
import com.github.zandy.bamboolib.exceptions.BambooResultSetException;

import java.sql.ResultSet;
import java.util.HashMap;

public class BambooResultSet {
    private ResultSet mysqlResultSet;
    private HashMap<Object, Object> flatFileResultSet;

    public BambooResultSet(ResultSet mysqlResultSet) {
        this.mysqlResultSet = mysqlResultSet;
    }

    public BambooResultSet(HashMap<Object, Object> flatFileResultSet) {
        this.flatFileResultSet = flatFileResultSet;
    }

    public boolean next() {
        switch (Database.getInstance().getDatabaseType()) {
            case MYSQL:
                try {
                    return this.mysqlResultSet.next();
                } catch (Exception e) {
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
            } catch (Exception e) {
                throw new BambooResultSetException();
            }
        }
    }

    public String getString(String columnLabel) {
        switch (Database.getInstance().getDatabaseType()) {
            case MYSQL:
                try {
                    return this.mysqlResultSet.getString(columnLabel);
                } catch (Exception e) {
                    throw new BambooResultSetException();
                }
            case FLAT_FILE:
                return String.valueOf(this.flatFileResultSet.get(columnLabel));
            default:
                return "";
        }
    }

    public boolean getBoolean(String columnLabel) {
        return Boolean.parseBoolean(this.getString(columnLabel));
    }

    public int getInt(String columnLabel) {
        switch (Database.getInstance().getDatabaseType()) {
            case MYSQL:
                try {
                    return this.mysqlResultSet.getInt(columnLabel);
                } catch (Exception e) {
                    throw new BambooResultSetException();
                }
            case FLAT_FILE:
                return Integer.parseInt(String.valueOf(this.flatFileResultSet.get(columnLabel)));
            default:
                return 0;
        }
    }

    public long getLong(String columnLabel) {
        switch (Database.getInstance().getDatabaseType()) {
            case MYSQL:
                try {
                    return this.mysqlResultSet.getLong(columnLabel);
                } catch (Exception e) {
                    throw new BambooResultSetException();
                }
            case FLAT_FILE:
                return Long.parseLong(String.valueOf(this.flatFileResultSet.get(columnLabel)));
            default:
                return 0L;
        }
    }

    public float getFloat(String columnLabel) {
        switch (Database.getInstance().getDatabaseType()) {
            case MYSQL:
                try {
                    return this.mysqlResultSet.getFloat(columnLabel);
                } catch (Exception e) {
                    throw new BambooResultSetException();
                }
            case FLAT_FILE:
                return Float.parseFloat(String.valueOf(this.flatFileResultSet.get(columnLabel)));
            default:
                return 0.0F;
        }
    }

    public double getDouble(String columnLabel) {
        switch (Database.getInstance().getDatabaseType()) {
            case MYSQL:
                try {
                    return this.mysqlResultSet.getDouble(columnLabel);
                } catch (Exception e) {
                    throw new BambooResultSetException();
                }
            case FLAT_FILE:
                return Double.parseDouble(String.valueOf(this.flatFileResultSet.get(columnLabel)));
            default:
                return 0.0D;
        }
    }
}
