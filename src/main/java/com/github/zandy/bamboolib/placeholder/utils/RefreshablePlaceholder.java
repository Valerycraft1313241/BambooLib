package com.github.zandy.bamboolib.placeholder.utils;

import com.github.zandy.bamboolib.BambooLib;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public abstract class RefreshablePlaceholder {
   private final String placeholder;
   private String value;
   private BukkitTask bukkitTask;

   public RefreshablePlaceholder(String var1, int var2) {
      this.placeholder = var1;
      this.value = this.refresh();
      if (var2 != 0) {
         this.bukkitTask = Bukkit.getScheduler().runTaskTimerAsynchronously(BambooLib.getPluginInstance(), () -> {
            this.value = this.refresh();
         }, 0L, (long)var2);
      }
   }

   public String getValue() {
      return this.value != null ? this.value : "";
   }

   public void cancelRefresh() {
      if (this.bukkitTask != null) {
         this.bukkitTask.cancel();
      }

   }

   public abstract String refresh();

   public String getPlaceholder() {
      return this.placeholder;
   }
}
