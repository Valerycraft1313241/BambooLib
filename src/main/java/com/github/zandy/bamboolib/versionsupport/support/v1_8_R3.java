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
   public void addMetaData(ItemStack var1, String var2, String var3) {
      net.minecraft.server.v1_8_R3.ItemStack var4 = CraftItemStack.asNMSCopy(var1);
      NBTTagCompound var5 = var4.getTag();
      if (var5 == null) {
         var5 = new NBTTagCompound();
         var4.setTag(var5);
      }

      var5.setString(var2, var3);
      ItemMeta var6 = CraftItemStack.getItemMeta(var4);
      var1.setItemMeta(var6);
   }

   public boolean hasMetaData(ItemStack var1, String var2) {
      net.minecraft.server.v1_8_R3.ItemStack var3 = CraftItemStack.asNMSCopy(var1);
      return var3 != null && var3.getTag() != null && var3.getTag().hasKey(var2);
   }

   public String getMetaData(ItemStack var1, String var2) {
      net.minecraft.server.v1_8_R3.ItemStack var3 = CraftItemStack.asNMSCopy(var1);
      return var3.getTag() == null ? "" : var3.getTag().getString(var2);
   }

   public void removeMetaData(ItemStack var1, String var2) {
      net.minecraft.server.v1_8_R3.ItemStack var3 = CraftItemStack.asNMSCopy(var1);
      NBTTagCompound var4 = var3.getTag();
      if (var4 == null) {
         var4 = new NBTTagCompound();
         var3.setTag(var4);
      }

      var4.remove(var2);
      ItemMeta var5 = CraftItemStack.getItemMeta(var3);
      var1.setItemMeta(var5);
   }

   public ItemMeta setUnbreakable(ItemMeta var1, boolean var2) {
      var1.spigot().setUnbreakable(true);
      return var1;
   }

   public void setSkullOwner(ItemStack var1, String var2) {
      SkullMeta var3 = (SkullMeta)var1.getItemMeta();

      try {
         var3.setOwner(Bukkit.getOfflinePlayer(UUID.fromString(var2)).getName());
      } catch (Exception var5) {
         var3.setOwner(var2);
      }

      var1.setItemMeta(var3);
   }

   public void spawnParticle(Player var1, Location var2, String var3, int var4) {
      var1.playEffect(var2, Effect.valueOf(var3), var4);
   }

   public BambooArmorStand getArmorStandVersionClass(Player var1, String var2, Location var3) {
      return new BambooArmorStandNMS(var1, var2, var3);
   }

   public void setInvulnerable(BambooArmorStand var1, boolean var2) {
      Entity var3 = ((CraftEntity)var1.getRawArmorStand()).getHandle();
      NBTTagCompound var4 = new NBTTagCompound();
      var3.c(var4);
      var4.setByte("Invulnerable", (byte)1);
      var3.f(var4);
   }

   public void registerCommand(Command var1) {
      ((CraftServer)BambooLib.getPluginInstance().getServer()).getCommandMap().register(var1.getName(), var1);
   }

   public void sendBorder(Player var1, BorderColor var2, int var3, double var4, double var6, boolean var8, int var9) {
      WorldBorder var10 = new WorldBorder();
      var10.setCenter(var4, var6);
      var10.setSize((double)var3);
      var10.setWarningDistance(0);
      Arrays.asList(EnumWorldBorderAction.SET_SIZE, EnumWorldBorderAction.SET_CENTER, EnumWorldBorderAction.SET_WARNING_BLOCKS).forEach((var2x) -> {
         sendPacket(var1, new PacketPlayOutWorldBorder(var10, var2x));
      });
      if (var2.equals(BorderColor.RED) || var2.equals(BorderColor.GREEN) || var8) {
         var10.transitionSizeBetween(var2.formatSize(var8 ? var9 : var3), var2.formatSizeTo(var3), var8 ? 2500L : 20000000L);
         sendPacket(var1, new PacketPlayOutWorldBorder(var10, EnumWorldBorderAction.LERP_SIZE));
      }
   }

   public void sendTitle(Player var1, String var2, String var3, int var4, int var5, int var6) {
      sendPacket(var1, new PacketPlayOutTitle(EnumTitleAction.TITLE, ChatSerializer.a("{\"text\": \"" + PlaceholderManager.getInstance().setPlaceholders(var1, var2) + "\"}")));
      sendPacket(var1, new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, ChatSerializer.a("{\"text\": \"" + PlaceholderManager.getInstance().setPlaceholders(var1, var3) + "\"}")));
      sendPacket(var1, new PacketPlayOutTitle(var4, var5, var6));
   }

   public void sendTablist(Player var1, List<String> var2, List<String> var3) {
      var2 = PlaceholderManager.getInstance().setPlaceholders(var1, var2);
      var3 = PlaceholderManager.getInstance().setPlaceholders(var1, var3);
      PacketPlayOutPlayerListHeaderFooter var4 = new PacketPlayOutPlayerListHeaderFooter();

      try {
         Field var5 = var4.getClass().getDeclaredField("a");
         var5.setAccessible(true);
         var5.set(var4, new ChatComponentText(String.join("\n", var2)));
         var5.setAccessible(false);
         Field var6 = var4.getClass().getDeclaredField("b");
         var6.setAccessible(true);
         var6.set(var4, new ChatComponentText(String.join("\n", var3)));
         var6.setAccessible(false);
      } catch (Exception var7) {
         throw new BambooErrorException(var7, this.getClass(), "Can't set tablist's header and footer.");
      }

      sendPacket(var1, var4);
   }

   public Fireball setFireballDirection(Fireball var1, Vector var2) {
      EntityFireball var3 = ((CraftFireball)var1).getHandle();
      var3.dirX = var2.getX() * 0.1D;
      var3.dirY = var2.getY() * 0.1D;
      var3.dirZ = var2.getZ() * 0.1D;
      return (Fireball)var3.getBukkitEntity();
   }

   public void sendActionBar(Player var1, String var2) {
      sendPacket(var1, new PacketPlayOutChat(ChatSerializer.a("{\"text\": \"" + PlaceholderManager.getInstance().setPlaceholders(var1, var2) + "\"}"), (byte)2));
   }

   public static void sendPacket(Player var0, Packet<?> var1) {
      ((CraftPlayer)var0).getHandle().playerConnection.sendPacket(var1);
   }
}
