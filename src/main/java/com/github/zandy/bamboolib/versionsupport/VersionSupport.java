package com.github.zandy.bamboolib.versionsupport;

import com.github.zandy.bamboolib.BambooLib;
import com.github.zandy.bamboolib.exceptions.BambooException;
import com.github.zandy.bamboolib.placeholder.PlaceholderManager;
import com.github.zandy.bamboolib.versionsupport.entity.armorstand.BambooArmorStand;
import com.github.zandy.bamboolib.versionsupport.pickup.PlayerPickup;
import com.github.zandy.bamboolib.versionsupport.utils.BorderColor;
import java.util.List;
import java.util.UUID;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.util.Vector;

public abstract class VersionSupport {
   private static VersionSupport instance = null;
   private String version = null;
   private VersionSupport.VersionType versionType;

   public void enablePickup() {
      Bukkit.getPluginManager().registerEvents(new PlayerPickup(), BambooLib.getPluginInstance());
   }

   public UUID getSkullOwner(SkullMeta var1) {
      return Bukkit.getPlayer(var1.getOwner()).getUniqueId();
   }

   public void setSkullOwner(ItemStack var1, String var2) {
      SkullMeta var3 = (SkullMeta)var1.getItemMeta();

      try {
         var3.setOwner(var2);
      } catch (Exception ignored) {
      }

      var1.setItemMeta(var3);
   }

   public abstract void addMetaData(ItemStack var1, String var2, String var3);

   public abstract boolean hasMetaData(ItemStack var1, String var2);

   public abstract String getMetaData(ItemStack var1, String var2);

   public abstract void removeMetaData(ItemStack var1, String var2);

   public ItemMeta setUnbreakable(ItemMeta var1, boolean var2) {
      var1.spigot().setUnbreakable(var2);
      return var1;
   }


   public void spawnParticle(Player player, Location location, String particleName, int count) {
      EnumParticle particle = EnumParticle.valueOf(particleName);

      // Convert Bukkit Location to NMS Location
      ((CraftWorld) player.getWorld()).getHandle().a(
              particle,                      // Particle type
              true,                           // Whether it should force visibility
              location.getX(),                // X coordinate
              location.getY(),                // Y coordinate
              location.getZ(),                // Z coordinate
              count,                          // Number of particles
              0, 0, 0,                        // The speed on the x, y, z axes
              0                               // Extra data (can be 0)
      );
   }

   public abstract BambooArmorStand getArmorStandVersionClass(Player var1, String var2, Location var3);

   public abstract void registerCommand(Command var1);

   public abstract void sendBorder(Player var1, BorderColor var2, int var3, double var4, double var6, boolean var8, int var9);

   public String getTexture(String var1) {
      return new String(Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", var1).getBytes()));
   }

   public void sendTitle(Player var1, String var2, String var3) {
      var1.sendTitle(PlaceholderManager.getInstance().setPlaceholders(var1, var2), PlaceholderManager.getInstance().setPlaceholders(var1, var3));
   }

   public void sendActionBar(Player player, String message) {
      // Apply placeholders (if needed)
      String finalMessage = PlaceholderManager.getInstance().setPlaceholders(player, message);

      // Create the chat component for the action bar message
      IChatBaseComponent chatComponent = new ChatComponentText(finalMessage);

      // Create the packet with the action bar message (2 indicates ACTION_BAR in 1.8.9)
      PacketPlayOutChat packet = new PacketPlayOutChat(chatComponent, (byte) 2);

      // Send the packet to the player
      ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
   }

   public abstract void sendTablist(Player var1, List<String> var2, List<String> var3);

   public abstract Fireball setFireballDirection(Fireball var1, Vector var2);

   public static VersionSupport getInstance() {
      if (instance == null) {
         String var0 = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

         try {
            instance = (VersionSupport)Class.forName("com.github.zandy.bamboolib.versionsupport.support." + var0).newInstance();
         } catch (Exception var2) {
            throw new BambooException("&aBambooLib cannot run on &f" + var0);
         }

         instance.version = var0;
         instance.versionType = VersionSupport.VersionType.valueOf(var0);
      }

      return instance;
   }

   public String getVersion() {
      return this.version;
   }

   public VersionSupport.VersionType getVersionType() {
      return this.versionType;
   }

   public enum VersionType {
      v1_8_R3(1),
      v1_12_R1(2),
      v1_17_R1(3),
      v1_18_R2(4),
      v1_19_R1(5),
      v1_19_R2(6);

      final int versionID;

      VersionType(int var3) {
         this.versionID = var3;
      }

      public int getVersionID() {
         return this.versionID;
      }

   }
}
