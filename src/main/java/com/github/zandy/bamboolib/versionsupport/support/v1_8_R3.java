package com.github.zandy.bamboolib.versionsupport.support;

import com.github.zandy.bamboolib.BambooLib;
import com.github.zandy.bamboolib.exceptions.BambooErrorException;
import com.github.zandy.bamboolib.placeholder.PlaceholderManager;
import com.github.zandy.bamboolib.versionsupport.VersionSupport;
import com.github.zandy.bamboolib.versionsupport.entity.armorstand.BambooArmorStand;
import com.github.zandy.bamboolib.versionsupport.entity.armorstand.support.v1_8_R3.BambooArmorStandNMS;
import com.github.zandy.bamboolib.versionsupport.utils.BorderColor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityFireball;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldBorder;
import net.minecraft.server.v1_8_R3.WorldBorder;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldBorder.EnumWorldBorderAction;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftFireball;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.util.Vector;

public class v1_8_R3 extends VersionSupport {
   public void addMetaData(ItemStack itemStack, String key, String value) {
      net.minecraft.server.v1_8_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
      NBTTagCompound tagCompound = nmsItemStack.getTag();
      if (tagCompound == null) {
         tagCompound = new NBTTagCompound();
         nmsItemStack.setTag(tagCompound);
      }

      tagCompound.setString(key, value);
      ItemMeta itemMeta = CraftItemStack.getItemMeta(nmsItemStack);
      itemStack.setItemMeta(itemMeta);
   }

   public boolean hasMetaData(ItemStack itemStack, String key) {
      net.minecraft.server.v1_8_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
      return nmsItemStack != null && nmsItemStack.getTag() != null && nmsItemStack.getTag().hasKey(key);
   }

   public String getMetaData(ItemStack itemStack, String key) {
      net.minecraft.server.v1_8_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
      return nmsItemStack.getTag() == null ? "" : nmsItemStack.getTag().getString(key);
   }

   public void removeMetaData(ItemStack itemStack, String key) {
      net.minecraft.server.v1_8_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
      NBTTagCompound tagCompound = nmsItemStack.getTag();
      if (tagCompound == null) {
         tagCompound = new NBTTagCompound();
         nmsItemStack.setTag(tagCompound);
      }

      tagCompound.remove(key);
      ItemMeta itemMeta = CraftItemStack.getItemMeta(nmsItemStack);
      itemStack.setItemMeta(itemMeta);
   }

   public ItemMeta setUnbreakable(ItemMeta itemMeta, boolean unbreakable) {
      itemMeta.spigot().setUnbreakable(unbreakable);
      return itemMeta;
   }

   public void setSkullOwner(ItemStack itemStack, String owner) {
      SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();

      try {
         skullMeta.setOwner(Bukkit.getOfflinePlayer(UUID.fromString(owner)).getName());
      } catch (Exception e) {
         skullMeta.setOwner(owner);
      }

      itemStack.setItemMeta(skullMeta);
   }

   public void spawnParticle(Player player, Location location, String effectName, int data) {
      player.playEffect(location, Effect.valueOf(effectName), data);
   }

   public BambooArmorStand getArmorStandVersionClass(Player player, String name, Location location) {
      return new BambooArmorStandNMS(player, name, location);
   }

   public void setInvulnerable(BambooArmorStand armorStand, boolean invulnerable) {
      Entity entity = ((CraftEntity) armorStand.getRawArmorStand()).getHandle();
      NBTTagCompound tagCompound = new NBTTagCompound();
      entity.c(tagCompound);
      tagCompound.setByte("Invulnerable", (byte) 1);
      entity.f(tagCompound);
   }

   public void registerCommand(Command command) {
      ((CraftServer) BambooLib.getPluginInstance().getServer()).getCommandMap().register(command.getName(), command);
   }

   public void sendBorder(Player player, BorderColor borderColor, int size, double centerX, double centerZ, boolean warning, int warningDistance) {
      WorldBorder worldBorder = new WorldBorder();
      worldBorder.setCenter(centerX, centerZ);
      worldBorder.setSize(size);
      worldBorder.setWarningDistance(0);
      Arrays.asList(EnumWorldBorderAction.SET_SIZE, EnumWorldBorderAction.SET_CENTER, EnumWorldBorderAction.SET_WARNING_BLOCKS).forEach(action -> {
         sendPacket(player, new PacketPlayOutWorldBorder(worldBorder, action));
      });
      if (borderColor.equals(BorderColor.RED) || borderColor.equals(BorderColor.GREEN) || warning) {
         worldBorder.transitionSizeBetween(borderColor.formatSize(warning ? warningDistance : size), borderColor.formatSizeTo(size), warning ? 2500L : 20000000L);
         sendPacket(player, new PacketPlayOutWorldBorder(worldBorder, EnumWorldBorderAction.LERP_SIZE));
      }
   }

   public void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
      sendPacket(player, new PacketPlayOutTitle(EnumTitleAction.TITLE, ChatSerializer.a("{\"text\": \"" + PlaceholderManager.getInstance().setPlaceholders(player, title) + "\"}")));
      sendPacket(player, new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, ChatSerializer.a("{\"text\": \"" + PlaceholderManager.getInstance().setPlaceholders(player, subtitle) + "\"}")));
      sendPacket(player, new PacketPlayOutTitle(fadeIn, stay, fadeOut));
   }

   public void sendTablist(Player player, List<String> header, List<String> footer) {
      header = PlaceholderManager.getInstance().setPlaceholders(player, header);
      footer = PlaceholderManager.getInstance().setPlaceholders(player, footer);
      PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();

      try {
         Field headerField = packet.getClass().getDeclaredField("a");
         headerField.setAccessible(true);
         headerField.set(packet, new ChatComponentText(String.join("\n", header)));
         headerField.setAccessible(false);
         Field footerField = packet.getClass().getDeclaredField("b");
         footerField.setAccessible(true);
         footerField.set(packet, new ChatComponentText(String.join("\n", footer)));
         footerField.setAccessible(false);
      } catch (Exception e) {
         throw new BambooErrorException(e, this.getClass(), "Can't set tablist's header and footer.");
      }

      sendPacket(player, packet);
   }

   public Fireball setFireballDirection(Fireball fireball, Vector direction) {
      EntityFireball entityFireball = ((CraftFireball) fireball).getHandle();
      entityFireball.dirX = direction.getX() * 0.1D;
      entityFireball.dirY = direction.getY() * 0.1D;
      entityFireball.dirZ = direction.getZ() * 0.1D;
      return (Fireball) entityFireball.getBukkitEntity();
   }

   public void sendActionBar(Player player, String message) {
      sendPacket(player, new PacketPlayOutChat(ChatSerializer.a("{\"text\": \"" + PlaceholderManager.getInstance().setPlaceholders(player, message) + "\"}"), (byte) 2));
   }

   public static void sendPacket(Player player, Packet<?> packet) {
      ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
   }
}
