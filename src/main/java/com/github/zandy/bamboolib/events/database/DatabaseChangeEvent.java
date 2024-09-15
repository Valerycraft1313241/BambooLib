package com.github.zandy.bamboolib.events.database;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DatabaseChangeEvent extends Event {
   public static final HandlerList handlers = new HandlerList();
   private final DatabaseAction databaseAction;
   private final String table;
   private final String column;

   public DatabaseChangeEvent(DatabaseAction action, String table, String column) {
      this.databaseAction = action;
      this.table = table;
      this.column = column;
   }

   public static HandlerList getHandlerList() {
      return handlers;
   }

   @Override
   public HandlerList getHandlers() {
      return handlers;
   }

   public DatabaseAction getDatabaseAction() {
      return this.databaseAction;
   }

   public String getTable() {
      return this.table;
   }

   public String getColumn() {
      return this.column;
   }

   public enum DatabaseAction {
      SELECT,
      UPDATE,
      CREATE,
      ALTER,
      DELETE,
      INSERT;
   }
}
