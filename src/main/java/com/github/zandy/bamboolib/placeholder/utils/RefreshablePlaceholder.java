package com.github.zandy.bamboolib.placeholder.utils;

import com.github.zandy.bamboolib.BambooLib;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public abstract class RefreshablePlaceholder {
   private final String placeholder;
   private String value;
   private BukkitTask refreshTask;

   public RefreshablePlaceholder(String placeholder, int refreshInterval) {
      this.placeholder = placeholder;
      this.value = this.refresh();
      if (refreshInterval != 0) {
         this.refreshTask = Bukkit.getScheduler().runTaskTimerAsynchronously(BambooLib.getPluginInstance(), () -> {
            this.value = this.refresh();
         }, 0L, refreshInterval);
      }
   }

   public String getValue() {
      return this.value != null ? this.value : "";
   }

   public void cancelRefresh() {
      if (this.refreshTask != null) {
         this.refreshTask.cancel();
      }
   }

   public abstract String refresh();

   public String getPlaceholder() {
      return this.placeholder;
   }
}
