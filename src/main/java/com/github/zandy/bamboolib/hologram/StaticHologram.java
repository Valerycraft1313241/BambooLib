package com.github.zandy.bamboolib.hologram;

import com.github.zandy.bamboolib.hologram.utils.Hologram;
import com.github.zandy.bamboolib.utils.BambooUtils;
import com.github.zandy.bamboolib.versionsupport.VersionSupport;
import com.github.zandy.bamboolib.versionsupport.entity.armorstand.BambooArmorStand;
import com.github.zandy.bamboolib.versionsupport.entity.armorstand.support.BambooArmorStandBukkit;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class StaticHologram extends Hologram {
   private final List<BambooArmorStand> textLines = new ArrayList<>();
   private final Hologram.HologramType hologramType;
   private final String id;
   private final Location baseLocation;
   private Player player;

   public StaticHologram(Player player, String id, Location baseLocation, List<String> lines) {
      this.hologramType = Hologram.HologramType.INDIVIDUAL;
      this.id = id;
      this.baseLocation = baseLocation;
      this.player = player;
      this.init(lines);
   }

   public StaticHologram(String id, Location baseLocation, List<String> lines) {
      this.hologramType = Hologram.HologramType.GLOBAL;
      this.id = id;
      this.baseLocation = baseLocation;
      this.init(lines);
   }

   private void init(List<String> lines) {
      Location currentLocation = this.baseLocation.clone().add(0.0D, 0.23D * (double)lines.size() - 1.97D, 0.0D);

      for(int i = 0; i < lines.size(); ++i) {
         this.addLine(i, currentLocation, lines.get(i));
         currentLocation.subtract(0.0D, 0.23D, 0.0D);
      }

   }

   public Hologram addLine(int index, Location location, String text) {
      BambooArmorStand armorStand = this.hologramType.equals(Hologram.HologramType.GLOBAL) ? new BambooArmorStandBukkit(this.id + "_" + index, location) : VersionSupport.getInstance().getArmorStandVersionClass(this.player, this.id + "_" + index, location);
      armorStand.setCustomName(BambooUtils.colorize(text));
      armorStand.setCustomNameVisible(true);
      armorStand.setGravity(false);
      armorStand.setVisible(false);
      this.textLines.add(armorStand);
      return this;
   }

   public Hologram remove() {
      this.textLines.forEach(BambooArmorStand::remove);
      this.textLines.clear();
      return this;
   }
}
