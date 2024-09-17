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
   private VersionType versionType;

   public void enablePickup() {
      Bukkit.getPluginManager().registerEvents(new PlayerPickup(), BambooLib.getPluginInstance());
   }

   public UUID getSkullOwner(SkullMeta skullMeta) {
      return Bukkit.getPlayer(skullMeta.getOwner()).getUniqueId();
   }

   public void setSkullOwner(ItemStack itemStack, String owner) {
      SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();

      try {
         skullMeta.setOwner(owner);
      } catch (Exception ignored) {
      }

      itemStack.setItemMeta(skullMeta);
   }

   public abstract void addMetaData(ItemStack itemStack, String key, String value);

   public abstract boolean hasMetaData(ItemStack itemStack, String key);

   public abstract String getMetaData(ItemStack itemStack, String key);

   public abstract void removeMetaData(ItemStack itemStack, String key);

   public ItemMeta setUnbreakable(ItemMeta itemMeta, boolean unbreakable) {
      itemMeta.spigot().setUnbreakable(unbreakable);
      return itemMeta;
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

   public abstract BambooArmorStand getArmorStandVersionClass(Player player, String name, Location location);

   public abstract void registerCommand(Command command);

   public abstract void sendBorder(Player player, BorderColor borderColor, int size, double centerX, double centerZ, boolean warning, int warningDistance);

   public String getTexture(String url) {
      return new String(Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes()));
   }

   public void sendTitle(Player player, String title, String subtitle) {
      player.sendTitle(PlaceholderManager.getInstance().setPlaceholders(player, title), PlaceholderManager.getInstance().setPlaceholders(player, subtitle));
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

   public abstract void sendTablist(Player player, List<String> header, List<String> footer);

   public abstract Fireball setFireballDirection(Fireball fireball, Vector direction);

   public static VersionSupport getInstance() {
      if (instance == null) {
         String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

         try {
            instance = (VersionSupport) Class.forName("com.github.zandy.bamboolib.versionsupport.support." + version).newInstance();
         } catch (Exception e) {
            throw new BambooException("&aBambooLib cannot run on &f" + version);
         }

         instance.version = version;
         instance.versionType = VersionType.valueOf(version);
      }

      return instance;
   }

   public String getVersion() {
      return this.version;
   }

   public VersionType getVersionType() {
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

      VersionType(int versionID) {
         this.versionID = versionID;
      }

      public int getVersionID() {
         return this.versionID;
      }
   }
}
