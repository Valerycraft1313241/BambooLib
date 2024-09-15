package com.github.zandy.bamboolib.versionsupport.pickup;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerPickup implements Listener {
   @EventHandler
   private void onPlayerPickup(PlayerPickupItemEvent var1) {
      com.github.zandy.bamboolib.events.player.PlayerPickupItemEvent var2 = new com.github.zandy.bamboolib.events.player.PlayerPickupItemEvent(var1.getPlayer(), var1.getItem(), var1.getRemaining());
      Bukkit.getPluginManager().callEvent(var2);
      var1.setCancelled(var2.isCancelled());
   }
}
