package com.github.zandy.bamboolib.versionsupport.pickup;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerPickup implements Listener {
   @EventHandler
   private void onPlayerPickup(PlayerPickupItemEvent event) {
      com.github.zandy.bamboolib.events.player.PlayerPickupItemEvent event1 = new com.github.zandy.bamboolib.events.player.PlayerPickupItemEvent(event.getPlayer(), event.getItem(), event.getRemaining());
      Bukkit.getPluginManager().callEvent(event1);
      event.setCancelled(event1.isCancelled());
   }
}
