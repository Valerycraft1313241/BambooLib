package com.github.zandy.bamboolib.events.database;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DatabaseChangeEvent extends Event {
   public static final HandlerList handlers = new HandlerList();
   private final DatabaseChangeEvent.DatabaseAction databaseAction;
   private final String table;
   private final String column;

   public DatabaseChangeEvent(DatabaseChangeEvent.DatabaseAction var1, String var2, String var3) {
      this.databaseAction = var1;
      this.table = var2;
      this.column = var3;
   }

   public static HandlerList getHandlerList() {
      return handlers;
   }

   public HandlerList getHandlers() {
      return handlers;
   }

   public DatabaseChangeEvent.DatabaseAction getDatabaseAction() {
      return this.databaseAction;
   }

   public String getTable() {
      return this.table;
   }

   public String getColumn() {
      return this.column;
   }

   public static enum DatabaseAction {
      SELECT,
      UPDATE,
      CREATE,
      ALTER,
      DELETE,
      INSERT;

   }
}
