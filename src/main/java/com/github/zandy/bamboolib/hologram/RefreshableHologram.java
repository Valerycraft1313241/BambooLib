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

   public RefreshableHologram(Player player, String id, Location baseLocation, List<String> lines, List<RefreshablePlaceholder> placeholders, int refreshTick) {
      this.hologramType = Hologram.HologramType.INDIVIDUAL;
      this.placeholderLines = new ArrayList<>();
      this.placeholders = placeholders;
      this.tasksList = new ArrayList<>();
      this.placeholderMap = new HashMap<>();
      this.id = id;
      this.baseLocation = baseLocation;
      this.player = player;
      this.refreshTick = refreshTick;
      this.init(lines);
   }

   public RefreshableHologram(String id, Location baseLocation, List<String> lines, List<RefreshablePlaceholder> placeholders, int refreshTick) {
      this.hologramType = Hologram.HologramType.GLOBAL;
      this.placeholderLines = new ArrayList<>();
      this.placeholders = placeholders;
      this.tasksList = new ArrayList<>();
      this.placeholderMap = new HashMap<>();
      this.id = id;
      this.baseLocation = baseLocation;
      this.refreshTick = refreshTick;
      this.init(lines);
   }

   private void init(List<String> lines) {
      Location currentLocation = this.baseLocation.clone().add(0.0D, 0.23D * (double)lines.size() - 1.97D, 0.0D);

      for(int i = 0; i < lines.size(); ++i) {
         this.addLine(i, currentLocation, lines.get(i));
         currentLocation.subtract(0.0D, 0.23D, 0.0D);
      }

      this.refresh();
   }

   public Hologram addLine(int index, Location location, String text) {
      BambooArmorStand armorStand = this.hologramType.equals(Hologram.HologramType.GLOBAL) ? new BambooArmorStandBukkit(this.id + "_" + index, location) : VersionSupport.getInstance().getArmorStandVersionClass(this.player, this.id + "_" + index, location);
      armorStand.setCustomName(BambooUtils.colorize(text));
      armorStand.setGravity(false);
      armorStand.setVisible(false);
      this.textLines.add(armorStand);
      this.placeholders.forEach((placeholder) -> {
         armorStand.setCustomNameVisible(true);
         if (text.contains(placeholder.getPlaceholder())) {
            this.placeholderLines.add(armorStand);
            this.placeholderMap.put(armorStand, armorStand.getCustomName());
         }

      });
      return this;
   }

   public Hologram refresh() {
      BukkitTask task = Bukkit.getScheduler().runTaskTimer(BambooLib.getPluginInstance(), () -> {
         try {
            this.textLines.forEach((armorStand) -> {
               if (armorStand.isCustomNameVisible()) {
                  armorStand.setCustomNameVisible(true);
               }

               if (this.placeholderMap.containsKey(armorStand)) {
                  String customName = this.placeholderMap.get(armorStand);

                  for (RefreshablePlaceholder placeholder : this.placeholders) {
                     if (customName.contains(placeholder.getPlaceholder())) {
                        customName = customName.replace(placeholder.getPlaceholder(), placeholder.getValue());
                     }
                  }

                  armorStand.setCustomName(customName);
               }

            });
         } catch (Exception ignored) {
         }

      }, 0L, this.refreshTick);
      this.tasksList.add(task);
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
