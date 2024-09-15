package com.github.zandy.bamboolib.versionsupport.entity.armorstand.support.v1_8_R3;

import com.github.zandy.bamboolib.versionsupport.entity.armorstand.BambooArmorStand;
import com.github.zandy.bamboolib.versionsupport.support.v1_8_R3;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftArmorStand;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

public class BambooArmorStandNMS extends BambooArmorStand {
   private final CraftArmorStand craftArmorStand;
   private final Player player;
   private final int id;

   public BambooArmorStandNMS(Player var1, String var2, Location var3) {
      super(var2, var3);
      EntityArmorStand var4 = new EntityArmorStand(((CraftWorld)var1.getWorld()).getHandle());
      var4.setLocation(var3.getX(), var3.getY(), var3.getZ(), var3.getYaw(), var3.getPitch());
      this.craftArmorStand = (CraftArmorStand)var4.getBukkitEntity();
      this.player = var1;
      this.id = var4.getId();
      this.sendSpawnPacket();
   }

   public Player getOwningPlayer() {
      return this.player;
   }

   public ArmorStand getRawArmorStand() {
      return (ArmorStand)this.craftArmorStand.getHandle().getBukkitEntity();
   }

   public void setCustomName(String var1) {
      this.craftArmorStand.setCustomName(var1);
      this.sendMetadataPacket();
   }

   public String getCustomName() {
      return this.craftArmorStand.getCustomName();
   }

   public void setCustomNameVisible(boolean var1) {
      this.craftArmorStand.setCustomNameVisible(var1);
      this.sendMetadataPacket();
   }

   public boolean isCustomNameVisible() {
      return this.craftArmorStand.isCustomNameVisible();
   }

   public void setGravity(boolean var1) {
      this.craftArmorStand.setGravity(var1);
      this.sendMetadataPacket();
   }

   public void setInvulnerable(BambooArmorStand var1, boolean var2) {
      this.sendMetadataPacket();
   }

   public void remove() {
      this.craftArmorStand.remove();
      ((CraftPlayer)this.player).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(new int[]{this.id}));
   }

   @Override
   public void setInvulnerable() {

   }

   public ItemStack getItemInHand() {
      return this.craftArmorStand.getItemInHand();
   }

   public void setItemInHand(ItemStack var1) {
      this.craftArmorStand.setItemInHand(var1);
      this.sendMetadataPacket();
   }

   public ItemStack getBoots() {
      return this.craftArmorStand.getBoots();
   }

   public void setBoots(ItemStack var1) {
      this.craftArmorStand.setBoots(var1);
      this.sendMetadataPacket();
   }

   public ItemStack getLeggings() {
      return this.craftArmorStand.getLeggings();
   }

   public void setLeggings(ItemStack var1) {
      this.craftArmorStand.setLeggings(var1);
      this.sendMetadataPacket();
   }

   public ItemStack getChestplate() {
      return this.craftArmorStand.getChestplate();
   }

   public void setChestplate(ItemStack var1) {
      this.craftArmorStand.setChestplate(var1);
      this.sendMetadataPacket();
   }

   public ItemStack getHelmet() {
      return this.craftArmorStand.getHelmet();
   }

   public void setHelmet(ItemStack var1) {
      this.craftArmorStand.setHelmet(var1);
      this.sendMetadataPacket();
   }

   public EulerAngle getBodyPose() {
      return this.craftArmorStand.getBodyPose();
   }

   public void setBodyPose(EulerAngle var1) {
      this.craftArmorStand.setBodyPose(var1);
      this.sendMetadataPacket();
   }

   public EulerAngle getLeftArmPose() {
      return this.craftArmorStand.getLeftArmPose();
   }

   public void setLeftArmPose(EulerAngle var1) {
      this.craftArmorStand.setLeftArmPose(var1);
      this.sendMetadataPacket();
   }

   public EulerAngle getRightArmPose() {
      return this.craftArmorStand.getRightArmPose();
   }

   public void setRightArmPose(EulerAngle var1) {
      this.craftArmorStand.setRightArmPose(var1);
      this.sendMetadataPacket();
   }

   public EulerAngle getLeftLegPose() {
      return this.craftArmorStand.getLeftLegPose();
   }

   public void setLeftLegPose(EulerAngle var1) {
      this.craftArmorStand.setLeftLegPose(var1);
      this.sendMetadataPacket();
   }

   public EulerAngle getRightLegPose() {
      return this.craftArmorStand.getRightLegPose();
   }

   public void setRightLegPose(EulerAngle var1) {
      this.craftArmorStand.setRightLegPose(var1);
      this.sendMetadataPacket();
   }

   public EulerAngle getHeadPose() {
      return this.craftArmorStand.getHeadPose();
   }

   public void setHeadPose(EulerAngle var1) {
      this.craftArmorStand.setHeadPose(var1);
      this.sendMetadataPacket();
   }

   public boolean hasBasePlate() {
      return this.craftArmorStand.hasBasePlate();
   }

   public void setBasePlate(boolean var1) {
      this.craftArmorStand.setBasePlate(var1);
      this.sendMetadataPacket();
   }

   public boolean isVisible() {
      return this.craftArmorStand.isVisible();
   }

   public void setVisible(boolean var1) {
      this.craftArmorStand.setVisible(var1);
      this.sendMetadataPacket();
   }

   public boolean hasArms() {
      return this.craftArmorStand.hasArms();
   }

   public void setArms(boolean var1) {
      this.craftArmorStand.setArms(var1);
      this.sendMetadataPacket();
   }

   public boolean isSmall() {
      return this.craftArmorStand.isSmall();
   }

   public void setSmall(boolean var1) {
      this.craftArmorStand.setSmall(var1);
      this.sendMetadataPacket();
   }

   public boolean hasHitBox() {
      return this.craftArmorStand.isMarker();
   }

   public void setHitBox(boolean var1) {
      this.craftArmorStand.setMarker(var1);
      this.sendMetadataPacket();
   }

   private void sendMetadataPacket() {
      v1_8_R3.sendPacket(this.player, new PacketPlayOutEntityMetadata(this.id, this.craftArmorStand.getHandle().getDataWatcher(), false));
   }

   private void sendSpawnPacket() {
      v1_8_R3.sendPacket(this.player, new PacketPlayOutSpawnEntityLiving(this.craftArmorStand.getHandle()));
      this.sendMetadataPacket();
   }
}
