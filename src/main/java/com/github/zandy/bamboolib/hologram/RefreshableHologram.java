package com.github.zandy.bamboolib.hologram;

import com.github.zandy.bamboolib.BambooLib;
import com.github.zandy.bamboolib.hologram.utils.Hologram;
import com.github.zandy.bamboolib.placeholder.utils.RefreshablePlaceholder;
import com.github.zandy.bamboolib.utils.BambooUtils;
import com.github.zandy.bamboolib.versionsupport.VersionSupport;
import com.github.zandy.bamboolib.versionsupport.entity.armorstand.BambooArmorStand;
import com.github.zandy.bamboolib.versionsupport.entity.armorstand.support.BambooArmorStandBukkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class RefreshableHologram extends Hologram {
   private final List<BambooArmorStand> textLines = new ArrayList<>();
   private final List<BambooArmorStand> placeholderLines;
   private final List<RefreshablePlaceholder> placeholders;
   private final List<BukkitTask> tasksList;
   private final HashMap<BambooArmorStand, String> placeholderMap;
   private final Hologram.HologramType hologramType;
   private final String id;
   private final Location baseLocation;
   private final int refreshTick;
   private Player player;

   public RefreshableHologram(Player var1, String var2, Location var3, List<String> var4, List<RefreshablePlaceholder> var5, int var6) {
      this.hologramType = Hologram.HologramType.INDIVIDUAL;
      this.placeholderLines = new ArrayList<>();
      this.placeholders = var5;
      this.tasksList = new ArrayList<>();
      this.placeholderMap = new HashMap<>();
      this.id = var2;
      this.baseLocation = var3;
      this.player = var1;
      this.refreshTick = var6;
      this.init(var4);
   }

   public RefreshableHologram(String var1, Location var2, List<String> var3, List<RefreshablePlaceholder> var4, int var5) {
      this.hologramType = Hologram.HologramType.GLOBAL;
      this.placeholderLines = new ArrayList<>();
      this.placeholders = var4;
      this.tasksList = new ArrayList<>();
      this.placeholderMap = new HashMap<>();
      this.id = var1;
      this.baseLocation = var2;
      this.refreshTick = var5;
      this.init(var3);
   }

   private void init(List<String> var1) {
      Location var2 = this.baseLocation.clone().add(0.0D, 0.23D * (double)var1.size() - 1.97D, 0.0D);

      for(int var3 = 0; var3 < var1.size(); ++var3) {
         this.addLine(var3, var2, var1.get(var3));
         var2.subtract(0.0D, 0.23D, 0.0D);
      }

      this.refresh();
   }

   public Hologram addLine(int var1, Location var2, String var3) {
      BambooArmorStand var4 = this.hologramType.equals(Hologram.HologramType.GLOBAL) ? new BambooArmorStandBukkit(this.id + "_" + var1, var2) : VersionSupport.getInstance().getArmorStandVersionClass(this.player, this.id + "_" + var1, var2);
      var4.setCustomName(BambooUtils.colorize(var3));
      var4.setGravity(false);
      var4.setVisible(false);
      this.textLines.add(var4);
      this.placeholders.forEach((var3x) -> {
         var4.setCustomNameVisible(true);
         if (var3.contains(var3x.getPlaceholder())) {
            this.placeholderLines.add(var4);
            this.placeholderMap.put(var4, var4.getCustomName());
         }

      });
      return this;
   }

   public Hologram refresh() {
      BukkitTask var = Bukkit.getScheduler().runTaskTimer(BambooLib.getPluginInstance(), () -> {
         try {
            this.textLines.forEach((var1) -> {
               if (var1.isCustomNameVisible()) {
                  var1.setCustomNameVisible(true);
               }

               if (this.placeholderMap.containsKey(var1)) {
                  String var2 = this.placeholderMap.get(var1);

                   for (RefreshablePlaceholder var4 : this.placeholders) {
                       if (var2.contains(var4.getPlaceholder())) {
                           var2 = var2.replace(var4.getPlaceholder(), var4.getValue());
                       }
                   }

                  var1.setCustomName(var2);
               }

            });
         } catch (Exception ignored) {
         }

      }, 0L, this.refreshTick);
      this.tasksList.add(var);
      return this;
   }

   public Hologram remove() {
      this.textLines.forEach(BambooArmorStand::remove);
      this.placeholderLines.forEach(BambooArmorStand::remove);
      this.tasksList.forEach(BukkitTask::cancel);
      this.textLines.clear();
      this.placeholderLines.clear();
      this.tasksList.clear();
      return this;
   }
}
