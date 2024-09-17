package com.github.zandy.bamboolib.events.player;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerPickupItemEvent extends Event implements Cancellable {
   private static final HandlerList handlers = new HandlerList();
   private final Player player;
   private final Item item;
   private final int remaining;
   private boolean cancelled = false;

   public PlayerPickupItemEvent(Player player, Item item, int remaining) {
      this.player = player;
      this.item = item;
      this.remaining = remaining;
   }

   public static HandlerList getHandlerList() {
      return handlers;
   }

   public HandlerList getHandlers() {
      return handlers;
   }

   public Player getPlayer() {
      return this.player;
   }

   public Item getItem() {
      return this.item;
   }

   public int getRemaining() {
      return this.remaining;
   }

   public boolean isCancelled() {
      return this.cancelled;
   }

   public void setCancelled(boolean cancelled) {
      this.cancelled = cancelled;
   }
}
