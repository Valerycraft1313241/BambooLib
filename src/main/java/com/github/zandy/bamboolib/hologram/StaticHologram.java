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

   public StaticHologram(Player var1, String var2, Location var3, List<String> var4) {
      this.hologramType = Hologram.HologramType.INDIVIDUAL;
      this.id = var2;
      this.baseLocation = var3;
      this.player = var1;
      this.init(var4);
   }

   public StaticHologram(String var1, Location var2, List<String> var3) {
      this.hologramType = Hologram.HologramType.GLOBAL;
      this.id = var1;
      this.baseLocation = var2;
      this.init(var3);
   }

   private void init(List<String> var1) {
      Location var2 = this.baseLocation.clone().add(0.0D, 0.23D * (double)var1.size() - 1.97D, 0.0D);

      for(int var3 = 0; var3 < var1.size(); ++var3) {
         this.addLine(var3, var2, var1.get(var3));
         var2.subtract(0.0D, 0.23D, 0.0D);
      }

   }

   public Hologram addLine(int var1, Location var2, String var3) {
      BambooArmorStand var4 = this.hologramType.equals(Hologram.HologramType.GLOBAL) ? new BambooArmorStandBukkit(this.id + "_" + var1, var2) : VersionSupport.getInstance().getArmorStandVersionClass(this.player, this.id + "_" + var1, var2);
      var4.setCustomName(BambooUtils.colorize(var3));
      var4.setCustomNameVisible(true);
      var4.setGravity(false);
      var4.setVisible(false);
      this.textLines.add(var4);
      return this;
   }

   public Hologram remove() {
      this.textLines.forEach(BambooArmorStand::remove);
      this.textLines.clear();
      return this;
   }
}
